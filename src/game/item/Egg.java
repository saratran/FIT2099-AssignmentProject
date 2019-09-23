package game.item;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Location;
import game.Species;
import game.actor.dinosaur.Protoceratops;

public class Egg extends PortableDinoItem {
	private int age = 0;
	private int hatch_age = 10;
	private Species species;

	public Egg(String name, char displayChar, Species species) {
		super(name, displayChar);
		this.species = species;
		foodValue = 10;
		switch (species) {
		case PROTOCERATOPS:
			buyValue = 50;
			sellValue = 10;
		}

//		this.allowableActions.add(new EatItemAction(this));
	}

	@Override
	public void tick(Location currentLocation) {
		age++;
		if (age >= hatch_age) {
			switch (species) {
			case PROTOCERATOPS:
				if (!currentLocation.containsAnActor()) {
					currentLocation.addActor(new Protoceratops("Protoceratops"));
				}
			}
			currentLocation.removeItem(this);
		}
	}

	public Species getSpecies() {
		return species;
	}

	@Override
	public boolean isSellable() {
		return true;
	}

	
}
