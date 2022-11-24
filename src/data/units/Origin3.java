package data.units;

public class Origin3 {
	
	double angleAlpha, angleBeta, angleGamma;
	EulerSequence sequence = EulerSequence.XYZ;
	
	public Origin3() {
		angleAlpha = 0; angleBeta = 0; angleGamma = 0;
	}
	
	public Origin3(double angleX, double angleY, double angleZ, boolean isRad) {
		if(isRad) {
			this.angleAlpha = angleX; this.angleBeta = angleX; this.angleGamma = angleX;
		} else {
			this.angleAlpha = angleX * Math.PI / 180;
			this.angleBeta = angleY * Math.PI / 180;
			this.angleGamma = angleZ * Math.PI / 180;
		}
	}
	
	public void setAlpha (double angleX) { angleAlpha = angleX; }
	public void setBeta (double angleY) { angleBeta = angleY; }
	public void setGamma (double angleZ) { angleGamma = angleZ; }
	
	public void setOriginAngle(Origin3 origin) {
		this.angleAlpha = origin.getAlpha();
		this.angleBeta = origin.getBeta();
		this.angleGamma = origin.getGamma();
	}
	
	public double getAlpha() { return angleAlpha; }
	public double getBeta() { return angleBeta; }
	public double getGamma() { return angleGamma; }
	
	public EulerSequence getOriginSequence() {
		return sequence;
	}
}
