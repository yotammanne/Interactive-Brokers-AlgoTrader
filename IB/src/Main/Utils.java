package Main;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.ib.client.Contract;
import com.ib.client.Order;

import Strategies.Strategy;
import TickTypes.Tick;

public class Utils {

	
	public static Contract createStockContract(String symbol) {
		Contract contract = new Contract();
        contract.symbol(symbol);
        contract.secType("STK");
        contract.currency("USD");
        contract.exchange("SMART");
        return contract;
	}
	
	public static void marketOrder(EWrapperImpl wrapper,int id,Contract contract,int qty,String action) {
		Order order = new Order();
		order.action(action);
		order.orderType("MKT");
		order.totalQuantity(qty);
		wrapper.getClient().placeOrder(id, contract, order);
	}
	
	
	
	public static void executeAll(Map<Integer,Strategy> map) {
		Set<Integer> ids = map.keySet();
		for(int i : ids) {
			map.get(i).execute();
		}
	}
	
	public static void stopAll(Map<Integer,Strategy> map) {
		Set<Integer> ids = map.keySet();
		for(int i : ids) {
			map.get(i).stop();
		}
	}
	
	public static void handleTick(int id, Tick tick) {
		Strategy s = Testbed.executions.get(id);
		String action = s.strategyIndicator(tick);
		if(action != null) {
			s.placeOrder(action, 100);
		}
	}
	
	public static void closeAllPositions(Map<Integer,Strategy> map) {
		Set<Integer> ids = map.keySet();
		for(int i : ids) {
			map.get(i).closePosition();
		}
	}
	
	public static void sendTestOrder(EWrapperImpl wrapper, int id, String symbol,int qty, String action) {
		marketOrder(wrapper,id,createStockContract(symbol),qty,action);
	}
}
