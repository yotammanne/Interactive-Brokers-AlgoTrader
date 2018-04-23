package Strategies;

import com.ib.client.Contract;


import Main.EWrapperImpl;

public abstract class AbstractStrategy implements Strategy {
	
	protected boolean executing;
	protected final String symbol;
	public final int id;
	protected Contract contract;
	protected double position;
	protected EWrapperImpl wrapper;
	protected int numOfOrders;

	public AbstractStrategy(EWrapperImpl wrapper,int id, String symbol) {
		this.executing=false;
		this.id=id;
		this.symbol=symbol;
		this.wrapper=wrapper;
		this.position=0;
		this.numOfOrders=0;
	}
	
	@Override
	public boolean isExecuting() {
		return executing;
	}
	

	@Override
	public String getSymbol() {
		return this.symbol;
	}
	
	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public double getPosition() {
		return position;
	}
}
