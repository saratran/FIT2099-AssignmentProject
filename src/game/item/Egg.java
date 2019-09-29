package game.item;

import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.interfaces.BuyableInterface;
import edu.monash.fit2099.interfaces.EdibleInterface;
import edu.monash.fit2099.interfaces.SellableInterface;
import edu.monash.fit2099.interfaces.SpeciesInterface;
import game.FoodSkill;
import game.Species;
import game.dinosaur.Dinosaur;

public class Egg extends PortableDinoItem implements SpeciesInterface, EdibleInterface, BuyableInterface, SellableInterface {
	private int age = 0;
	private int hatch_age = 10;
	protected Species species;
	private int buyValue = 0;
	private int sellValue = 0;

	
	public Egg(String name, char displayChar, Species species) {
		super(name, displayChar);
		this.species = species;
//		foodValue = 10;
		addSkill(FoodSkill.CARNIVORE);
		initValues();

	}

	private void initValues() {
//		 TODO: can factor this out as a separate class to take in enum Species and return a suitable value
		switch (this.species) {
		case PROTOCERATOPS:
			buyValue = 50;
			sellValue = 10;
			break;
		case VELOCIRAPTOR:
			buyValue = 1000;
			sellValue = 100;
			break;
		}		
	}

	public Egg(Species species) {
		this(species.name().toLowerCase() + " egg", 'e', species);
	}

	@Override
	public void tick(Location currentLocation) {
		age++;
		// Hatches after certain age and only if there is no Actor standing on top of it
		if (age >= hatch_age && !currentLocation.containsAnActor()) {
			currentLocation.addActor(this.hatchInto());
			currentLocation.removeItem(this);
			System.out.println(this.toString() + " hatches");
		}
	}

	public Species getSpecies() {
		return species;
	}

//	@Override
//	public boolean isSellable() {
//		return true;
//	}

	private Dinosaur hatchInto() {
		return dinoFactory.newDinosaur(species);
	}

	@Override
	public int getFoodValue() {
		return 10;
	}

	@Override
	public int getBuyValue() {
		return buyValue;
	}

	@Override
	public int getSellValue() {
		return sellValue;
	}

}
