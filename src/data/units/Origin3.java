package data.units;

public class Origin3 {
	
	double angleAlpha, angleBeta, angleGamma;
	EulerSequence sequence = EulerSequence.XYZ;
	
	public Origin3() {
		angleAlpha = 0; angleBeta = 0; angleGamma = 0;
	}
	
	public Origin3(double angleX, double angleY, double angleZ) {
		this.angleAlpha = angleX; this.angleBeta = angleX; this.angleGamma = angleX;
	}
	
	public void setAlpha (double angleX) { angleAlpha = angleX; }
	public void setBeta (double angleY) { angleBeta = angleY; }
	public void setGamma (double angleZ) { angleGamma = angleZ; }
	
	public double getAlpha() { return angleAlpha; }
	public double getBeta() { return angleBeta; }
	public double getGamma() { return angleGamma; }
	
	public EulerSequence getOriginSequence() {
		return sequence;
	}
}
