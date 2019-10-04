package game.item;


import game.FoodSkill;

public class Corpse extends PortableDinoItem {
	private int sellValue = 0;
	private int foodValue = 40;

	public Corpse(String name, char displayChar, int sellValue) {
		super(name, displayChar);
		this.sellValue = sellValue;
		addSkill(FoodSkill.CARNIVORE);
	}

	public Corpse(String name, int sellValue) {
		this(name, 'c', sellValue);
	}

	@Override
	public int getSellValue() {
		return sellValue;
	}

	@Override
	public boolean isSellable() {
		return true;
	}
	
	@Override
	public int getFoodValue() {
		return foodValue;
	//		TODO: Should we allow species to each their own type of species? i.e. carnivorous velociraptors eating velociraptors?
	}




}
