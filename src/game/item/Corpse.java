package game.item;

import game.FoodSkill;
// TODO: for different species
public class Corpse extends PortableDinoItem {
	
	public Corpse(String name, char displayChar) {
		super(name, displayChar);
		sellValue = 15;
		addSkill(FoodSkill.CARNIVORE);
	}

	@Override
	public boolean isSellable() {
		return true;
	}

	
}
