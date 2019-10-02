package game.item;

import edu.monash.fit2099.engine.Location;
import game.FoodSkill;
import game.Species;
import game.dinosaur.Dinosaur;
import game.dinosaur.Maturity;

public class Egg extends PortableDinoItem {
	private int age = 0;
	private int hatch_age = 10;
	protected Species species;
	private int buyValue = 0;
	private int sellValue = 0;
	private Dinosaur dinosaur;

	
	public Egg(String name, char displayChar, Dinosaur dinosaur) {
		super(name, displayChar);
		assert dinosaur.getMaturity() == Maturity.BABY;
		this.species = dinosaur.getSpecies();
		addSkill(FoodSkill.CARNIVORE);
		initValues();
		this.dinosaur = dinosaur;
	}
	
	public Egg(Dinosaur dinosaur) {
		this(dinosaur.getSpecies().toString().toLowerCase() + " egg", 'e', dinosaur);
	}

	private void initValues() {
//		 TODO: can factor this out as a separate class to take in enum Species and return a suitable value
		// Or can make methods in Dino to return eggBuyValue and eggSellValue
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

	private Dinosaur hatchInto() {
		return dinosaur;
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

	@Override
	public boolean isBuyable() {
		return true;
	}

	@Override
	public boolean isSellable() {
		return true;
	}

	
}
