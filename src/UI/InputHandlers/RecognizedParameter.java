package UI.InputHandlers;

public class RecognizedParameter {
	String parameterName;
	double[] parameterValues;
	
	public RecognizedParameter(String name, double[] values) {
		parameterName = name; parameterValues = values;
	}
	
	public String getName() { return parameterName; }
	
	public void setName(String name) { parameterName = name; }
	
	public String getValuesAsString(char divider) {
		String temp = "";
		if(parameterValues != null)
		for(double item : parameterValues) {
			temp += item + "" + divider;
		}
		return temp;
	}
	
	public double[] getValues() {
		return parameterValues;
	}
}