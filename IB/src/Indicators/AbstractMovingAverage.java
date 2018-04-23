package Indicators;

import java.util.Queue;

public abstract class AbstractMovingAverage {
	protected Queue<Double> dataTicks;
	protected int SMARange;
	protected double SMA;
	
	public double getSMA() {return this.SMA;}
	public abstract void calcMA(double price);
}
