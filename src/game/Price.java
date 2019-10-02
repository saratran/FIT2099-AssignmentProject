package game;

public enum Price {
	ProtoceratopsEgg(50, 10),
	ProtoceratopsCorpse(0, 15),
	VelociraptorEgg(1000, 100),
	VelociraptorCorpse(0, 50),
	HerbivoreFoodItem(20,0),
	CarnivoreFoodItem(100,0);
	
	
	private int buyValue;
	private int sellValue;
	
	Price(int buyValue, int sellValue){
		this.buyValue = buyValue;
		this.sellValue = sellValue;
	}

	public int getBuyValue() {
		return buyValue;
	}

	public int getSellValue() {
		return sellValue;
	}
	
	
}
