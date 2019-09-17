package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Location;

public class Egg extends PortableDinoItem {
	private int age = 0;
	private int hatch_age = 10;
	private Species species;

	
	public Egg(String name, char displayChar, Species species) {
		super(name, displayChar);
		this.species = species;
//		this.allowableActions.add(new EatItemAction(this));
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
