package game.item;

import game.FoodSkill;

public class FoodItem extends PortableDinoItem {
	protected int buyValue = 0;
	
	public FoodItem(String name, char displayChar, FoodSkill foodSkill, int buyValue) {
		super(name, displayChar);
		this.addSkill(foodSkill);
		this.buyValue = buyValue;
	}
	
	public FoodItem(FoodSkill foodSkill, int buyValue) {
		this(foodSkill.toString().toLowerCase() + " food item", 'f', foodSkill, buyValue);
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

	@Override
	public boolean isBuyable() {
		return true;
	}
	
	
	
	
}
