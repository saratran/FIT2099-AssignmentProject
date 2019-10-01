package game.item;

import edu.monash.fit2099.interfaces.BuyableInterface;

public abstract class FoodItem extends PortableDinoItem implements BuyableInterface {
	protected int buyValue = 0;
	
	public FoodItem(String name, char displayChar) {
		super(name, displayChar);
//		foodValue = 100000; // TODO: hacky-ish, may result in overflow
	}

	@Override
	public boolean isFeedable() {
		return true;
	}

	@Override
	public int getFoodValue() {
		return 100000;
	}

	@Override
	public int getBuyValue() {
		return buyValue;
	}
	
	
	
}
