package game;
/**
 * Used as a database for prices of item.
 * 
 */
public enum Price {
	ProtoceratopsEgg(50, 10),
	ProtoceratopsCorpse(0, 5),
	VelociraptorEgg(500, 100),
	VelociraptorCorpse(0, 50),
	PlesiosaurEgg(2000, 500),
	PlesiosaurCorpse(0, 300),
	PteranodonEgg(2000, 500),
	PteranodonCorpse(0, 300),
	TyrannosaurusEgg(20000,2500),
	TyrannosaurusCorpse(0, 2000),
	HerbivoreFoodItem(20,0),
	CarnivoreFoodItem(100,0),
	MarineFoodItem(200, 0);
	
	
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
	public int buyValue() {
		return buyValue;
	}

	/**
	 * The amount of money received when selling the item
	 * 
	 * @return The amount of money received when selling the item
	 */
	public int sellValue() {
		return sellValue;
	}
	
	
}
