package modelo;

import java.util.ArrayList;
import java.util.List;

public class Memory {

	private enum CommandType {
		RESET, SIGNAL, NUMBER, DIV, MULT, SUB, SUM, EQUAL, COMMA;
	};

	private static Memory instancia = new Memory();

	private final List<ObserverMemory> observers = new ArrayList<>();
	
	private CommandType lastOperation;
	private boolean toReplace = false;
	private String textCurret = "";
	private String BufferText = "";
	
	private Memory() {
		// TODO Auto-generated constructor stub
	}

	public static Memory getInstancia() {
		return instancia;
	}

	public static void setInstancia(Memory instancia) {
		Memory.instancia = instancia;
	}

	public void AddObserver(ObserverMemory observer) {
		observers.add(observer);
	}

	public String getText() {
		return textCurret.isEmpty() ? "0" : textCurret;
	}

	public void processCommand(String value) {

		CommandType commandType = decttypecommand(value);

		if(commandType == null) {
			return;
		}else if(commandType == CommandType.RESET) {
			textCurret = "";
			BufferText = "";
			toReplace = false;
			lastOperation = null; 
		}else if(commandType == CommandType.SIGNAL && textCurret.contains("-")) {
		textCurret =  textCurret.substring(1);
		}else if(commandType == CommandType.SIGNAL && !textCurret.contains("-")) {
		textCurret = "-" + textCurret;

		} else if (commandType == CommandType.NUMBER 
				|| commandType == CommandType.COMMA){
			textCurret = toReplace ? value : textCurret + value;
			toReplace = false;
		} else {
			toReplace = true;
			textCurret = getOperationResult();
			BufferText = textCurret;
			lastOperation = commandType;
		}
			
		
		observers.forEach(o -> o.changedValue(getText()));
	}
	private String getOperationResult() {
		if(lastOperation == null || lastOperation ==  CommandType.EQUAL) {
			return textCurret;
		}
		double BufferNumber =
				Double.parseDouble(BufferText.replace(",", "."));

		double BufferAtual =
				Double.parseDouble(textCurret.replace(",", "."));
		
		double result = 0;
		
		if(lastOperation == CommandType.SUM) {
			result = BufferNumber + BufferAtual;
		}else if (lastOperation == CommandType.SUB) {
			result = BufferNumber - BufferAtual;
		}else if (lastOperation == CommandType.MULT){
			result = BufferNumber * BufferAtual;
		}else if (lastOperation == CommandType.DIV) {
			result = BufferNumber / BufferAtual;
		}
		String stringResult = Double.toString(result).replace(".", ",");
		boolean integer = stringResult.endsWith(",0");
		return integer ? stringResult.replace(",0","") : stringResult;
	}

	private CommandType decttypecommand(String text) {

		if (text.isEmpty() && text == "0") {
			return null;
		}
		try {
			Integer.parseInt(text);
			return CommandType.NUMBER;		
		} catch (NumberFormatException e) {
			//when it's not a number
			if ("AC".equals(text)) {
				return CommandType.RESET;
			}else if ("/".equals(text)) {
				return CommandType.DIV;
			}else if ("*".equals(text)) {
				return CommandType.MULT;
			}else if ("+".equals(text)) {
				return CommandType.SUM;
			}else if ("-".equals(text)) {
				return CommandType.SUB;
			}else if ("=".equals(text)) {
				return CommandType.EQUAL;
			}else if ("±".equals(text)) {
				return CommandType.SIGNAL;
			}else if (",".equals(text) 
					&&  !textCurret.contains(",")) {
				return CommandType.COMMA;
			}
			}
		return null;
	}

}
