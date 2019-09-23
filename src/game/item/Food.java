package game.item;

public class Food extends PortableDinoItem {
	// TODO: herbivore and carnivore food, food replenish to full 
	// Integer myInf = Integer.MAX_VALUE;

	public Food(String name, char displayChar) {
		super(name, displayChar);
		foodValue = 100;
		
	}

	@Override
	public boolean isFeedable() {
		return true;
	}
	
	
}
