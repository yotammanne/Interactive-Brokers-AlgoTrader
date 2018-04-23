package Main;
import java.time.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ib.client.EClientSocket;
import com.ib.client.EReader;
import com.ib.client.EReaderSignal;
import com.ib.client.ExecutionFilter;
import com.ib.client.Order;
import com.ib.client.Types.FADataType;

import Strategies.DelayedSMAIntersectionStrategy;
import Strategies.SMAIntersectionStrategy;
import Strategies.Strategy;

public class Testbed {
	
	//private static final String listLocation ="/Users/yotammanne/Library/Mobile Documents/com~apple~CloudDocs/ת IB/Execution List.csv";
	public static Map<Integer,Strategy> executions = new HashMap<>();
	public static final int orderIdOffset = 1000;
	
	public static void main(String[] args) throws InterruptedException {
		
		EWrapperImpl wrapper = new EWrapperImpl();
		
		final EClientSocket m_client = wrapper.getClient();
		final EReaderSignal m_signal = wrapper.getSignal();
		//! [connect]
		m_client.eConnect("127.0.0.1", 7497, 0);
		//! [connect]
		//! [ereader]
		final EReader reader = new EReader(m_client, m_signal);   
		
		reader.start();
		//An additional thread is created in this program design to empty the messaging queue
		new Thread(() -> {
		    while (m_client.isConnected()) {
		        m_signal.waitForSignal();
		        try {
		            reader.processMsgs();
		        } catch (Exception e) {
		            System.out.println("Exception: "+e.getMessage());
		        }
		    }
		}).start();
		//! [ereader]
		// A pause to give the application time to establish the connection
		// In a production application, it would be best to wait for callbacks to confirm the connection is complete
		Thread.sleep(1000);

		
		//calcs and executions
		//Utils.sendTestOrder(wrapper, 1037, "AAPL", 100, "SELL");
		Strategy appleSMA = new SMAIntersectionStrategy(wrapper,1,"AAPL",13,34);
		executions.put(appleSMA.getId(), appleSMA);
		//wrapper.getClient().reqMarketDataType(3);
		Utils.executeAll(executions);
		
		Instant now = Instant.now();
		String nowS=now.toString();
		Instant end = Instant.parse(nowS.substring(0, nowS.indexOf("T")+1)+"22:45:00.00z");
		Duration d = Duration.between(now, end);
		
		Thread.sleep(d.toMillis());

		
		Utils.stopAll(executions);
		Utils.closeAllPositions(executions);
		
		m_client.eDisconnect();
	}
}
	