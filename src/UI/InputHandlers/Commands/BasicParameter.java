package UI.InputHandlers.Commands;

public class BasicParameter {
	
	private final String parameterName;
	private final int parameterID;
	private double[] parameterValues;
	private boolean active = false, errored = false;
	
	public BasicParameter(String name, int ID, int length) {
		parameterName = name;
		parameterID = ID;
		setParametersSize(length);
	}
	
	public String getParameterName() { return parameterName; }
	
	public int getParameterID() { return parameterID; }
	
	private void setParametersSize(int size) {
		parameterValues = new double[size];
	}
	
	public boolean isActive() { return active; }
	
	public void activate() { active = true; }
	
	public void disactivate() { active = false; errored = false; }
	
	public void setParameter(int paramNum, double value) {
		try {
			parameterValues[paramNum] = value;
			System.out.print(value + ", ");
			active = true;
		}
		catch (NullPointerException Ex) {
			active = false;
			errored = true;
		}
	}
	
	public double[] getValues() { return parameterValues; }
	
	public boolean isErrored() { return errored; }
}
