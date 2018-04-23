package Strategies;

import com.ib.client.Contract;
import com.ib.client.Order;

import Indicators.SimpleMovingAverage;
import Main.EWrapperImpl;
import Main.Testbed;
import TickTypes.Tick;

public class SMAIntersectionStrategy extends AbstractStrategy{
	protected SimpleMovingAverage ShortSMA;
	protected SimpleMovingAverage LongSMA;
	private int delayCounter;
	
	
	public SMAIntersectionStrategy(EWrapperImpl wrapper,int id, String symbol, int range1, int range2) {
		super(wrapper,id,symbol);
		this.ShortSMA = new SimpleMovingAverage(range1);
		this.LongSMA = new SimpleMovingAverage(range2);
		this.contract = new Contract();
		this.contract.symbol(symbol);
		this.contract.secType("STK");
		this.contract.currency("USD");
		this.contract.exchange("SMART");
		this.delayCounter=0;
	}
	
	protected void requestData(Contract contract) {
		wrapper.getClient().reqRealTimeBars(id, contract, 5, "MIDPOINT", true, null);
		
	}
	
	//assuming real time bars represent 5 seconds
	protected void update(double price) {
		if(delayCounter<12) { //need to "ignore" 12 bars for 1 minute update frequency
			delayCounter++;
		}
		else {
			this.ShortSMA.calcMA(price);
			this.LongSMA.calcMA(price);
			this.delayCounter=0;
		}
	}
	
	@Override
	public String strategyIndicator(Tick tick) {
		double price = tick.getPrice();
		double tempShort = ShortSMA.getSMA();
		double tempLong = LongSMA.getSMA();
		this.update(price);
		if(tempShort>tempLong && this.LongSMA.getSMA()>=this.ShortSMA.getSMA()) {
			return "SELL";
		}
		else if(tempShort<tempLong && this.LongSMA.getSMA()<=this.ShortSMA.getSMA()) {
			return "BUY";
		}
		else return null;
	}

	protected void stopData() {
		wrapper.getClient().cancelRealTimeBars(id);
	}
	
	@Override
	public void execute() {
		this.requestData(contract);
		executing=true;
	}

	@Override
	public void stop() {
		this.stopData();
		executing=false;
	}

	@Override
	public void placeOrder(String action, int qty) {
		Order order = new Order();
		order.action(action);
		order.orderType("MKT");
		order.totalQuantity(qty);
		wrapper.getClient().placeOrder(this.wrapper.getNextValidId(), contract, order);
		switch (action) {
			case "BUY":
				position+=qty;
				break;
			case "SELL":
				position-=qty;
		}
	}
	
	@Override
	public void closePosition() {
		if(position>0) {
			this.placeOrder("SELL", (int)position);
		}
		else {
			this.placeOrder("BUY", (int)-position);
		}
	}
	

}
