package game.item;

import game.FoodSkill;

import game.Species;
// TODO: for different species
public class Corpse extends PortableDinoItem {
	private Species species;
	private int sellValue = 0;
	
	public Corpse(String name, char displayChar, Species species, int sellValue) {
		super(name, displayChar);
		this.species = species;
		this.sellValue = sellValue;
		addSkill(FoodSkill.CARNIVORE);
	}

	public Corpse(Species species, int sellValue) {
		this(species.toString() + " corpse", 'c', species, sellValue);
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
