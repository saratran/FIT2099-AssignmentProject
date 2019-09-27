package game.item;

import game.dinosaur.BabyProtoceratops;
import game.dinosaur.Dinosaur;
import game.dinosaur.Protoceratops;

public class ProceratopsEgg extends Egg{

	public ProceratopsEgg(String name, char displayChar) {
		super(name, displayChar);
		buyValue = 50;
		sellValue = 10;
	}

	@Override
	protected Dinosaur hatchInto() {
		// TODO: change to baby version
		return new BabyProtoceratops("Baby protoceratops");
	}

}
