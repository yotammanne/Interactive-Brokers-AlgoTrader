package OrderHandling;

public class OrderStatus {
	int orderId;
	String status;
	double filled;
	double remaining;
	double lastFillPrice;
	String whyHeld;
	double mktCapPrice;
	int requsetId;
	
	public OrderStatus(int orderId, String status, double filled, double remaining, double lastFillPrice,
			String whyHeld, double mktCapPrice,int reqId) {
		super();
		this.orderId = orderId;
		this.status = status;
		this.filled = filled;
		this.remaining = remaining;
		this.lastFillPrice = lastFillPrice;
		this.whyHeld = whyHeld;
		this.mktCapPrice = mktCapPrice;
		this.requsetId=reqId;
	}
	
	int getOrderId() {
		return orderId;
	}
	void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	String getStatus() {
		return status;
	}
	void setStatus(String status) {
		this.status = status;
	}
	double getFilled() {
		return filled;
	}
	void setFilled(double filled) {
		this.filled = filled;
	}
	double getRemaining() {
		return remaining;
	}
	void setRemaining(double remaining) {
		this.remaining = remaining;
	}
	double getLastFillPrice() {
		return lastFillPrice;
	}
	void setLastFillPrice(double lastFillPrice) {
		this.lastFillPrice = lastFillPrice;
	}
	String getWhyHeld() {
		return whyHeld;
	}
	void setWhyHeld(String whyHeld) {
		this.whyHeld = whyHeld;
	}
	double getMktCapPrice() {
		return mktCapPrice;
	}
	void setMktCapPrice(double mktCapPrice) {
		this.mktCapPrice = mktCapPrice;
	}
	int getRequestId() {
		return this.requsetId;
	}
	
}
