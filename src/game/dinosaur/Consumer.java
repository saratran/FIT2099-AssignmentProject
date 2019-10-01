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
import game.Species;
import game.behaviour.Behaviour;

public abstract class Consumer extends Actor {
	protected int foodLevel = 30;
	protected int maxFoodLevel = 15;
	protected int hungryLevel = 50;

	protected List<Item> foodItems = new ArrayList<Item>();
	protected List<Ground> foodGrounds = new ArrayList<Ground>();
	protected List<Actor> foodActors = new ArrayList<Actor>();
	protected List<FoodSkill> edibleFoodSkills = new ArrayList<FoodSkill>(); // List of food skills that the dino can eat

	
	public Consumer(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
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
		return (foodLevel <= hungryLevel);
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

}
