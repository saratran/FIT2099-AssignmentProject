package game;
/**
 * Used as a database for prices of item.
 * 
 */
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

	/**
	 * The cost of purchasing the item
	 *
	 * @return The cost of purchasing the item
	 */
	public int getBuyValue() {
		return buyValue;
	}

	/**
	 * The amount of money received when selling the item
	 * 
	 * @return The amount of money received when selling the item
	 */
	public int getSellValue() {
		return sellValue;
	}
	
	
}
