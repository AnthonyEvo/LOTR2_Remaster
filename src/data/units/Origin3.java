package data.units;

public class Origin3 {	// Describes 3 rotation degree of freedom
	
	double angleAlpha, angleBeta, angleGamma; // Variables for Euler's angles
	EulerSequence sequence = EulerSequence.XYX;
	
	public Origin3() { // Default constructor
		angleAlpha = 0; angleBeta = 0; angleGamma = 0;
	}
	
	public Origin3(double angleX, double angleY, double angleZ, boolean isRad) {	// Constructor 1
		if(isRad) {
			this.angleAlpha = angleX; this.angleBeta = angleX; this.angleGamma = angleX;
		} else {
			this.angleAlpha = angleX * Math.PI / 180;
			this.angleBeta = angleY * Math.PI / 180;
			this.angleGamma = angleZ * Math.PI / 180;
		}
	}
	
	public void setAlpha (double angleX, boolean isRad) { // Sets Eulers angles of object, here alpha
		if(isRad) {	 angleAlpha = angleX; }
		else { angleAlpha = angleX / Math.PI * 180; }
	}
	public void setBeta (double angleY, boolean isRad) { 	// Beta
		if(isRad) { angleBeta = angleY; }
		else { angleBeta = angleY / Math.PI * 180; }
	}
	public void setGamma (double angleZ, boolean isRad) { 	// Gamma
		if(isRad) { angleGamma = angleZ; }
		else { angleGamma = angleZ / Math.PI * 180; }
	}
	
	public void setOriginAngle(Origin3 origin) {	// Setting all origin angles through object
		this.angleAlpha = origin.getAlpha();
		this.angleBeta = origin.getBeta();
		this.angleGamma = origin.getGamma();
	}
	
	public double getAlpha() { return angleAlpha; }	// Get angles
	public double getBeta() { return angleBeta; }
	public double getGamma() { return angleGamma; }	// ...
	
	public void setOriginSequence(EulerSequence sequence) { // Sets angles calculation sequence
		this.sequence = sequence;
	}
	
	public EulerSequence getOriginSequence() {	// Get Euler angles sequence for calculation
		return sequence;
	}
}
