package game.item;

import game.Species;

public class Corpse extends PortableDinoItem {
	private Species species;
	
	public Corpse(String name, char displayChar, Species species) {
		super(name, displayChar);
		this.species = species;
	}

}
