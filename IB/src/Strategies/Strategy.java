package Strategies;

import TickTypes.Tick;

public interface Strategy {
	public String strategyIndicator(Tick tick);
	public boolean isExecuting();
	public void execute();
	public void stop();
	public String getSymbol();
	public int getId();
	public void placeOrder(String action, int qty);
	public double getPosition();
	public void closePosition();
}
