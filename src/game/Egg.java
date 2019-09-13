package game;

import edu.monash.fit2099.engine.Location;

public class Egg extends PortableDinoItem {
	private int age = 0;
	private int hatch_age = 10;
	private Species species;

	
	public Egg(String name, char displayChar, Species species) {
		super(name, displayChar);
		this.species = species;
	}

	@Override
	public void tick(Location currentLocation) {
		age++;
		if(age >= hatch_age) {
			switch(species) {
			case PROTOCERATOPS:
				currentLocation.addActor(new Protoceratops("Protoceratops"));
			}
			currentLocation.removeItem(this);
		}
	}

	public Species getSpecies() {
		return species;
	}
	
	

}
