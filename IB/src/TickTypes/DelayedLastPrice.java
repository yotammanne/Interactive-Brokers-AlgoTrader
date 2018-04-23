package TickTypes;

public class DelayedLastPrice implements Tick {

	private double price;
	
	public DelayedLastPrice(double price) {
		this.price=price;
	}
	
	@Override
	public double getPrice() {
		return price;
	}

	@Override
	public long getVolume() {
		return 0;
	}

}
