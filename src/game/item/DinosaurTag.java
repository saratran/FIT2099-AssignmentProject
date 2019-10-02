package game.item;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;
import game.dinosaur.*;

public class DinosaurTag extends PortableDinoItem {

	private boolean sellable = false;
	private Dinosaur dinosaur = null;
	
	public DinosaurTag(String name, char displayChar) {
		super(name, displayChar);
	}

	public DinosaurTag() {
		this("Dinosaur Tag", '-');
	}
	@Override
	public int getBuyValue() {
		return 0;
	}

	@Override
	public void tick(Location currentLocation, Actor actor) {
		if (this.isSellable() && !actor.getInventory().contains(this)) {
			map.locationOf(actor);
		}
	}
	
	@Override
	public boolean isBuyable() {
		return true;
	}

	@Override
	public boolean isSellable() {
		return sellable;
	}
		
	public void addDino(Dinosaur dinosaur) {
		this.dinosaur = dinosaur;
		this.sellable = true;
	}
	
	public Dinosaur getDino() {
		return dinosaur;
	}
	
}
