package game.item;

import edu.monash.fit2099.engine.Location;
import game.Species;
import game.actor.dinosaur.Dinosaur;

public class VelociraptorEgg extends Egg {

	public VelociraptorEgg(String name, char displayChar) {
		super(name, displayChar, Species.VELOCIRAPTOR);
		buyValue = 1000;
		sellValue = 100;
	}

	@Override
	protected Dinosaur hatchInto() {
		// TODO: change to baby version
		return new Velociraptor("Velociraptor");
	}

}
