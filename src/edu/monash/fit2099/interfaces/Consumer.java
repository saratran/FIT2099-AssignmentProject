package edu.monash.fit2099.interfaces;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import game.FoodSkill;

public interface Consumer {
	/*
	 * TODO: Same tradeoffs as EdibleInterface - Actor that has SeekFoodBehaviour
	 * but doesn't implement Consumer --> doesn't get any action from the behaviour
	 * 
	 * But can't really implement these as default methods
	 */
	public boolean isFood(Item item);

	public boolean isFood(Ground ground);

	public boolean isFood(Actor actor);

	public boolean isHungry();

	public void addFoodValue(int food_value);
}
