package game.item;

import game.FoodSkill;
// TODO: for different species
public abstract class Corpse extends PortableDinoItem {
	
	public Corpse(String name, char displayChar) {
		super(name, displayChar);
		addSkill(FoodSkill.CARNIVORE);
		foodValue = 50;
	}

	@Override
	public boolean isSellable() {
		return true;
	}

	
}
