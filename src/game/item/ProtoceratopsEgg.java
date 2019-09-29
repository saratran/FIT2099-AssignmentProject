package game.item;

import game.Species;
import game.dinosaur.Dinosaur;
import game.dinosaur.Protoceratops;

public class ProtoceratopsEgg extends Egg{

	public ProtoceratopsEgg(String name, char displayChar) {
		super(name, displayChar, Species.PROTOCERATOPS);
		buyValue = 50;
		sellValue = 10;
	}
	
	public ProtoceratopsEgg() {
		this("Protoceratops egg", 'e');
	}


}
