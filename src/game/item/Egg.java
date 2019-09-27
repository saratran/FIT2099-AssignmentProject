package game.item;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Location;
import game.FoodSkill;
import game.dinosaur.Dinosaur;
import game.dinosaur.Protoceratops;

public abstract class Egg extends PortableDinoItem {
	private int age = 0;
	private int hatch_age = 10;

	public Egg(String name, char displayChar) {
		super(name, displayChar);
		foodValue = 10;
		addSkill(FoodSkill.CARNIVORE);
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

	@Override
	public boolean isSellable() {
		return true;
	}

	protected abstract Dinosaur hatchInto();
	
	

}
