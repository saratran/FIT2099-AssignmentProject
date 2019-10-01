package edu.monash.fit2099.interfaces;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import game.FoodSkill;

public interface Consumer {
	public boolean isFood(Item item);

	public boolean isFood(Ground ground);

	public boolean isFood(Actor actor);

	public boolean isHungry();
	
	public void addFoodValue(int food_value);
}
