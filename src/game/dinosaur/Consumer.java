package game.dinosaur;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import game.FoodSkill;
import game.behaviour.Behaviour;

/* Analysis/note:
 * - This can be an interface but it has the drawback of having to do type checking, casting and maybe runtime errors and overall can be confusing.
 * 
 * - Alternatively these methods can be put in ActorInterface:
 * 		+ violate ISP, but better than having to deal with the drawbacks using separate interfaces
 * 		+ It can be a pain to have to implement these in classes that don't need them 
 * 
 * - I think using an abstract class here is a good middle-ground but this has 1 obvious drawback:
 * 		+ Less flexibility : Java doesn't allow multiple inheritance of abstract classes 
 * (ie an Actor can't extend Consumer and Trader at the same time)
 */

/**
 * An Actor that has food levels and food related methods.
 *
 */
public abstract class Consumer extends Actor {
	protected int foodLevel = 30;
	protected int maxFoodLevel = 15;
	protected int hungryLevel = 50;

	protected List<Item> foodItems = new ArrayList<Item>();
	protected List<Ground> foodGrounds = new ArrayList<Ground>();
	protected List<Actor> foodActors = new ArrayList<Actor>();
	protected List<FoodSkill> edibleFoodSkills = new ArrayList<FoodSkill>(); // List of food skills that the Consumer can eat
	protected List<FoodSkill> nonEdibleFoodSkills = new ArrayList<FoodSkill>(); // List of food skills that the Consumer cannot eat
	
	public Consumer(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
	}
	
	/* Analysis/note:
	 * Currently has 2 different methods to keep track of what is edible:
	 * 		+ Using FoodSkill for broader control since Skill can be added to an abstract class (ie like Protoceratops can eat all Vegetation)
	 * 		+ Using lists of edible food for finer control
	 */
	
	/**
	 * Check if item is food
	 * 
	 * @param item
	 * @return 
	 */
	public boolean isFood(Item item) {
		for (Item food_item : foodItems) {
			// This means the dino can either eat or not eat a class
			if (item.getClass().equals(food_item.getClass())) {
				return true;
			}
		}
		for (FoodSkill skill : nonEdibleFoodSkills) {
			if(item.hasSkill(skill)) {
				return false;
			}
		}
		for (FoodSkill skill : edibleFoodSkills) {
			if (item.hasSkill(skill)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check if ground is food
	 * 
	 * @param ground
	 * @return
	 */
	public boolean isFood(Ground ground) {
		for (Ground food_ground : foodGrounds) {
			// This means the dino can either eat or not eat a class
			if (ground.getClass().equals(food_ground.getClass())) {
				return true;
			}
		}
		for (FoodSkill skill : nonEdibleFoodSkills) {
			if(ground.hasSkill(skill)) {
				return false;
			}
		}
		for (FoodSkill skill : edibleFoodSkills) {
			if (ground.hasSkill(skill)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check if actor is food
	 * 
	 * @param actor
	 * @return
	 */
	public boolean isFood(Actor actor) {
		for (Actor food_actor : foodActors) {
			// This means the dino can either eat or not eat a class
			if (actor.getClass().equals(food_actor.getClass())) {
				return true;
			}
		}
		for (FoodSkill skill : nonEdibleFoodSkills) {
			if(actor.hasSkill(skill)) {
				return false;
			}
		}
		for (FoodSkill skill : edibleFoodSkills) {
			if (actor.hasSkill(skill)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @return true if food level is below hungry level
	 */
	public boolean isHungry() {
		return (foodLevel <= hungryLevel);
	}

	/**
	 * Add food value
	 * 
	 * @param food_value food value to add
	 */
	public void addFoodValue(int food_value) {
		foodLevel += food_value;
		foodLevel = Math.min(foodLevel, maxFoodLevel); // capped at max
	}

	/**
	 * Used to set the initial food levels of the consumer;
	 * structures that specific food levels depending on
	 * the status of the actor are found here.
	 */
	protected abstract void initFoodLevel();
	
	/**
	 * Sets the food levels of the consumer.
	 * 
	 * @param current the current food level
	 * @param max	the maximum food level
	 * @param hungry	the food level at which the consumer is considered hungry
	 */
	protected void setFoodLevel(int current, int max, int hungry) {
		foodLevel = current;
		maxFoodLevel = max;
		hungryLevel = hungry;
	}


}
