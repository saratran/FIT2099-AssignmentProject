package game.dinosaur;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import game.FoodSkill;
import game.behaviour.Behaviour;
import game.behaviour.SeekFoodBehaviour;
import game.behaviour.WanderBehaviour;
import game.ground.Tree;
import game.item.Corpse;

public abstract class Dinosaur extends Actor {
	private static double LAY_EGG_CHANCE = 0.01;
	protected int age = 0;
	public List<Behaviour> behaviours = new ArrayList<Behaviour>();// TODO: access modifier

	protected int foodLevel = 30;
	protected int maxFoodLevel = 15;
	protected int hungryLevel = 50;

	protected List<Item> foodItems = new ArrayList<Item>();
	protected List<Ground> foodGrounds = new ArrayList<Ground>();
	protected List<Actor> foodActors = new ArrayList<Actor>();
	protected List<FoodSkill> edibleFoodSkills = new ArrayList<FoodSkill>(); // List of food skills that the dino can
																				// eat

	public Dinosaur(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		addSkill(FoodSkill.CARNIVORE); // All dinosaurs are meat --> Velociraptor (and any carnivore dino) can eat and
										// attack all other dino

		behaviours.add(new SeekFoodBehaviour());
		behaviours.add(new WanderBehaviour());
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		age++;
		foodLevel--;
		if (isDead()) {
			this.die(map);
			return new DoNothingAction();
		}
		if (this.isHungry()) {
			System.out.println(name + " is hungry!");
		} else {
			// TODO: Only lay egg if not a baby
			if (isAdult() && Math.random() <= Dinosaur.LAY_EGG_CHANCE) {
				map.locationOf(this).addItem(getEgg());
			}
		}
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}

		return new DoNothingAction();
	}

	protected abstract Item getEgg();

	public void die(GameMap map) {
		for (Item item : itemsDroppedWhenDead()) {
			map.locationOf(this).addItem(item);
		}
		map.removeActor(this);
	}

	protected abstract List<Item> itemsDroppedWhenDead(); // Support adding multiple items to ground when dino dies

	public boolean isDead() {
		return (foodLevel <= 0) ? true : false;
	}

	public boolean isFood(Item item) {
		for (Item food_item : foodItems) {
			// This means the dino can either eat or not eat a class
			if (item.getClass().equals(food_item.getClass())) {
				return true;
			}
		}

		for (FoodSkill skill : edibleFoodSkills) {
			if (item.hasSkill(skill)) {
				return true;
			}
		}
		return false;
	}

	public boolean isFood(Ground ground) {
		for (Ground food_ground : foodGrounds) {
			// This means the dino can either eat or not eat a class
			if (ground.getClass().equals(food_ground.getClass())) {
				return true;
			}
		}
		for (FoodSkill skill : edibleFoodSkills) {
			if (ground.hasSkill(skill)) {
				return true;
			}
		}
		return false;
	}

	public boolean isFood(Actor actor) {
		for (Actor food_actor : foodActors) {
			// This means the dino can either eat or not eat a class
			if (actor.getClass().equals(food_actor.getClass())) {
				return true;
			}
		}

		for (FoodSkill skill : edibleFoodSkills) {
			if (actor.hasSkill(skill)) {
				return true;
			}
		}
		return false;
	}

	public boolean isHungry() {
		return (foodLevel <= hungryLevel) ? true : false;
	}

	public void addFoodValue(int food_value) {
		foodLevel += food_value;
		foodLevel = Math.min(foodLevel, maxFoodLevel); // capped at max
	}

	protected void initFoodLevel(int current, int max, int hungry) {
		foodLevel = current;
		maxFoodLevel = max;
		hungryLevel = hungry;
	}

	/**
	 * Override this to set dinosaur as a baby (not breedable)
	 * @return
	 */
	protected boolean isAdult() {
		return true;
	}

}
