package game.item;

<<<<<<<
import edu.monash.fit2099.interfaces.BuyableInterface;
import edu.monash.fit2099.interfaces.EdibleInterface;
import edu.monash.fit2099.interfaces.SellableInterface;
=======

>>>>>>>
import game.FoodSkill;

import game.Species;
// TODO: for different species
public class Corpse extends PortableDinoItem {
	private Species species;
	private int sellValue = 0;
	
	public Corpse(String name, char displayChar, Species species, int sellValue) {
	private int foodValue = 0;
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
	public int getBuyValue() {
		return buyValue;
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

	public Species getSpecies() {
		return species;
	}



}
