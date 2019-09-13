package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;

public abstract class Dinosaur extends Actor {
	protected int age = 0;
	protected Species species;
	protected List<Behaviour> behaviours = new ArrayList<Behaviour>();
	protected int food_level;
	protected int MAX_FOOD_LEVEL;
	
	public Dinosaur(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		food_level--;
		return null;
	}
	
	public boolean die(GameMap map) {
		if (food_level <= 0) {
			//TODO generalise corpse created
			map.locationOf(this).addItem(new Corpse("protoceratops corpse", 'c', Species.PROTOCERATOPS));
			map.removeActor(this);
			return true;
		} 
		return false;
	}
	
	

}
