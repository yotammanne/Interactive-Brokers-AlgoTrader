package Indicators;
import java.util.concurrent.ArrayBlockingQueue;
public class SimpleMovingAverage extends AbstractMovingAverage{
	
	private double sum;
	
	public SimpleMovingAverage(int range) {
		this.SMARange=range;
		this.sum=0;
		this.dataTicks=new ArrayBlockingQueue<Double>(range);
	}
	
	@Override
	public void calcMA(double price) {
		try {
			dataTicks.add(price);
			sum+=price;
			SMA= (SMARange>dataTicks.size()) ? sum/dataTicks.size() : sum/SMARange;
		}catch(IllegalStateException e) {
			sum-=dataTicks.poll();
			sum+=price;
			dataTicks.add(price);
			SMA= sum/dataTicks.size();
		}
	}
	

	
}