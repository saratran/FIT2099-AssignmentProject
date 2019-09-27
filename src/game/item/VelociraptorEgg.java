package game.item;

import edu.monash.fit2099.engine.Location;
import game.dinosaur.BabyVelociraptor;
import game.dinosaur.Dinosaur;
import game.dinosaur.Velociraptor;

public class VelociraptorEgg extends Egg {

	public VelociraptorEgg(String name, char displayChar) {
		super(name, displayChar);
		buyValue = 1000;
		sellValue = 100;
	}

	@Override
	protected Dinosaur hatchInto() {
		// TODO: change to baby version
		return new BabyVelociraptor("Baby velociraptor");
	}

}
