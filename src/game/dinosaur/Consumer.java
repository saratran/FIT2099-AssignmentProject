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
import edu.monash.fit2099.engine.Skilled;
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

	protected List<FoodSkill> edibleFoodSkills = new ArrayList<FoodSkill>(); // List of food skills that the Consumer
																				// can eat
	protected List<FoodSkill> nonEdibleFoodSkills = new ArrayList<FoodSkill>(); // List of food skills that the Consumer
																				// cannot eat
	protected List<Object> foodObjects = new ArrayList<Object>();

	public Consumer(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
	}

	/**
	 * Check if an object is edible
	 * @param object
	 * @return
	 */
	public boolean isFood(Skilled object) {
		for (Object food : foodObjects) {
			if (object.getClass().equals(food.getClass())) {
				return true;
			}
		}
		for (FoodSkill skill : nonEdibleFoodSkills) {
			if (object.hasSkill(skill)) {
				return false;
			}
		}
		for (FoodSkill skill : edibleFoodSkills) {
			if (object.hasSkill(skill)) {
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
