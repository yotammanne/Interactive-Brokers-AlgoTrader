package TickTypes;

public class RealTimeBar implements Tick {
	private long time;
	private double open;
	private double high;
	private double low;
	private double close;
	private long volume;
	private double wap;
	private int count;
	
	public RealTimeBar(long time, double open, double high, double low, double close, long volume, double wap,
			int count) {
		super();
		this.time = time;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
		this.wap = wap;
		this.count = count;
	}

	public long getTime() {
		return time;
	}

	public double getOpen() {
		return open;
	}

	public double getHigh() {
		return high;
	}

	public double getLow() {
		return low;
	}

	public double getClose() {
		return close;
	}

	public long getVolume() {
		return volume;
	}

	public double getWap() {
		return wap;
	}

	public int getCount() {
		return count;
	}
	
	public double getPrice() {
		return this.getClose();
	}
	
}
