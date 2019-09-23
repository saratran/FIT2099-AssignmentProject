package game.item;

public abstract class FoodItem extends PortableDinoItem {
	// TODO: herbivore and carnivore food, food replenish to full 
	// Integer myInf = Integer.MAX_VALUE;

	public FoodItem(String name, char displayChar) {
		super(name, displayChar);
		foodValue = 100000; // hacky-ish
//		foodValue = Integer.MAX_VALUE; // doesn't work, overflow to negative number when added
	}

	@Override
	public boolean isFeedable() {
		return true;
	}
	
	
}
