package game.item;

import edu.monash.fit2099.interfaces.BuyableInterface;
import edu.monash.fit2099.interfaces.EdibleInterface;
import edu.monash.fit2099.interfaces.SellableInterface;
import game.FoodSkill;

import game.Species;
// TODO: for different species
public class Corpse extends PortableDinoItem implements BuyableInterface, SellableInterface, EdibleInterface {
	private Species species;
	private int sellValue = 0;
	private int buyValue = 0;
	private int foodValue = 0;

	public Corpse(String name, char displayChar, Species species) {
		super(name, displayChar);
		this.species = species;
		initValues();
		addSkill(FoodSkill.CARNIVORE);
	}

	private void initValues() {
		//		 TODO: can factor this out as a separate class to take in enum Species and return a suitable value
		switch (this.species) {
		case PROTOCERATOPS:
			buyValue = 75;
			sellValue = 15;
			foodValue = 50;
			break;
		case VELOCIRAPTOR:
			buyValue = 250;
			sellValue = 50;
			foodValue = 50;
			break;
		}		
	}
	@Override
	public int getSellValue() {
		return sellValue;
	}
	@Override
	public int getBuyValue() {
		return buyValue;
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
