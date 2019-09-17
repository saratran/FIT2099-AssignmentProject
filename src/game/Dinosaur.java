package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;

public abstract class Dinosaur extends Actor {
	protected int age = 0;
	protected Species species;
	protected List<Behaviour> behaviours = new ArrayList<Behaviour>();
	protected int food_level;
	protected int MAX_FOOD_LEVEL;
	protected int HUNGRY_LEVEL;

	protected List<Item> food_items = new ArrayList<Item>();
	protected List<Ground> food_grounds = new ArrayList<Ground>();
	protected List<Actor> food_actors = new ArrayList<Actor>();

	public Dinosaur(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		food_level--;
		if(this.isHungry()) {
			// TODO: Good place to print?
			System.out.println(name + " is hungry!");
		}
		return null;
	}

	public boolean die(GameMap map) {
		if (food_level <= 0) {
			// TODO generalise corpse created
			map.locationOf(this).addItem(new Corpse("protoceratops corpse", 'c', Species.PROTOCERATOPS));
			map.removeActor(this);
			return true;
		}
		return false;
	}

	public boolean isFood(Item item) {
		for (Item food_item : food_items) {
			// This means the dino can either eat or not eat a class
			if (item.getClass().equals(food_item.getClass())) {
				return true;
			}
		}
		return false;
	}

	public boolean isFood(Ground ground) {
		for (Ground food_ground : food_grounds) {
			// This means the dino can either eat or not eat a class
			if (ground.getClass().equals(food_ground.getClass())) {
				return true;
			}
		}
		return false;
	}

	public boolean isFood(Actor actor) {
		for (Actor food_actor : food_actors) {
			// This means the dino can either eat or not eat a class
			if (actor.getClass().equals(food_actor.getClass())) {
				return true;
			}
		}
		return false;
	}

	public boolean isHungry() {
		return (food_level <= HUNGRY_LEVEL) ? true : false;
	}
	
	public void addFoodValue(int food_value) {
		this.food_level += food_value;
	}

}
