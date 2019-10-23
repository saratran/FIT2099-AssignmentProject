package game.item;

import game.dinosaur.DinoSkill;

public class Boat extends PortableDinoItem {

	public Boat() {
		super("boat", 'U');
		addSkill(DinoSkill.MARINE);
	}
}
