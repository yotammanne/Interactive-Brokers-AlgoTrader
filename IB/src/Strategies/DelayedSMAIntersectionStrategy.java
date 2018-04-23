package Strategies;

import com.ib.client.Contract;

import Main.EWrapperImpl;

public class DelayedSMAIntersectionStrategy extends SMAIntersectionStrategy {
	public DelayedSMAIntersectionStrategy(EWrapperImpl wrapper,int id, String symbol, int range1, int range2) {
		super(wrapper,id,symbol,range1,range2);
	}
	
	@Override
	protected void requestData(Contract contract) {
		wrapper.getClient().reqMktData(id, contract, null, false, false, null);
	}
}
