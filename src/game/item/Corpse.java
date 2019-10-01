package game.item;

import game.FoodSkill;

import game.Species;
// TODO: for different species
public class Corpse extends PortableDinoItem {
	private Species species;
	private int sellValue = 0;
	public Corpse(String name, char displayChar, Species species) {
		super(name, displayChar);
		this.species = species;
		sellValue = 15;
		addSkill(FoodSkill.CARNIVORE);
	}

//	@Override
//	public boolean isSellable() {
//		return true;
//	}

	@Override
	public int getSellValue() {
		return sellValue;
	}

	@Override
	public boolean isSellable() {
		return true;
	}
	
}
