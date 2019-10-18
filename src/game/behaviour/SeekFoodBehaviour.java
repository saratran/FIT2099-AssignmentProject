package game.behaviour;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.FoodSkill;
import game.action.AttackAction;
import game.action.EatGroundAction;
import game.action.EatItemAction;
import game.actor.Player;
import game.dinosaur.Consumer;
import game.dinosaur.Dinosaur;

/**
 * A class that is used to determine the appropriate action for a Dinosaur to
 * seek and eat nearby food
 *
 */
public class SeekFoodBehaviour implements Behaviour {
	private List<Pair<Double, Action>> pairs = new ArrayList<Pair<Double, Action>>();

	/**
	 * <p>
	 * Return the appropriate action depends on the situation.
	 * 
	 * <p>
	 * 1) When the actor is not a Dinosaur, is not hungry or there is no food
	 * available on the map, return null
	 * 
	 * <p>
	 * 2) When there is food nearby the Dinosaur (ie 1 space away), the action
	 * returned can be one of these:
	 * <ul>
	 * <li>AttackAction if there is an edible Actor nearby
	 * <li>EatGroundAction if there is an edible Ground nearby
	 * <li>EatItemAction if there is an edible Item nearby
	 * </ul>
	 * 
	 * <p>
	 * 3) When there is food further away, the action returned can be:
	 * <ul>
	 * <li>the action returned by FollowBehaviour for an edible Actor
	 * <li>the action returned by ToLocationBehaviour for the location that has an
	 * edible Item or Ground
	 * </ul>
	 */

	@Override
	public Action getAction(Actor actor, GameMap map) {
		HashMap<String, Location> checkedLocations = new HashMap<String, Location>(); // To store locations that have
																						// been checked for food
		List<Location> locationsToGetExits = new ArrayList<Location>(); // To store locations that need to get Exits
																		// next

		if (actor.asConsumer() == null) {
			return null;
		}
		Consumer consumer = actor.asConsumer();

		if (!consumer.isHungry()) {
			return null;
		}

		Location here = map.locationOf(consumer);
		checkedLocations.put(locationToKey(here), here);

		// Checking locations nearby first (ie 1 space away)
		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination();
			checkedLocations.put(locationToKey(destination), destination);
			locationsToGetExits.add(destination);

			Action action = null;
			Double foodScore = 0.0;
			// If ground is food
			if (consumer.isFood(destination.getGround())) {
				action = new EatGroundAction(destination.getGround(), destination);
				foodScore = foodPriority(here, destination, destination.getGround());
			}

			// If item is food
			for (Item item : destination.getItems()) {
				if (consumer.isFood(item)) {
					action = new EatItemAction(item, destination);
					foodScore = foodPriority(here, destination, item);
				}
			}

			// If actor is food
			// FoodSkill.NOT_FOOD is used to prevent attack on Actor that carries food items
			// (ie the Player)
			if (destination.containsAnActor() && !(destination.getActor().hasSkill(FoodSkill.NOT_FOOD))
					&& consumer.isFood(destination.getActor())) {
				action = new AttackAction(destination.getActor());
				foodScore = foodPriority(here, destination, destination.getActor());
			}
			pairs.add(new Pair<Double, Action>(foodScore, action));
		}

		// Check locations further away
		while (!locationsToGetExits.isEmpty()) {
			List<Exit> exits = new ArrayList<Exit>();
			for (Location location : locationsToGetExits) {
				exits.addAll(location.getExits());
			}
			locationsToGetExits.clear(); // Empty list after getting all the exits

			for (Exit exit : exits) {
				Location destination = exit.getDestination();

				// Only continue to check for food if location has not been checked before
				if (!checkedLocations.containsKey(locationToKey(destination))) {
					checkedLocations.put(locationToKey(destination), destination);
					locationsToGetExits.add(destination);

					Action action = null;
					Double foodScore = 0.0;
					if (consumer.isFood(destination.getGround())) {
						action = new ToLocationBehaviour(destination).getAction(actor, map);
						foodScore = foodPriority(here, destination, destination.getGround());
					}

					for (Item item : destination.getItems()) {
						if (consumer.isFood(item)) {
							action = new ToLocationBehaviour(destination).getAction(actor, map);
							foodScore = foodPriority(here, destination, item);
						}
					}

					if (destination.containsAnActor() && !(destination.getActor().hasSkill(FoodSkill.NOT_FOOD))
							&& consumer.isFood(destination.getActor())) {
						action = new FollowBehaviour(destination.getActor()).getAction(actor, map);
						foodScore = foodPriority(here, destination, destination.getActor());
					}
				}
			}
		}

		Action bestAction = null;
		Double bestScore = 0.0;
		for (Pair<Double, Action> pair : pairs) {
			if (pair.getL() > bestScore) {
				bestAction = pair.getR();
			}
		}

		// null when there is no available food
		return bestAction;
	}

	private String locationToKey(Location location) {
		return location.x() + "," + location.y();
	}

	/**
	 * Compute the Manhattan distance between two locations.
	 * 
	 * @param a the first location
	 * @param b the first location
	 * @return the number of steps between a and b if you only move in the four
	 *         cardinal directions.
	 */
	private int distance(Location a, Location b) {
		return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
	}

	private double foodPriority(Location here, Location there, Ground ground) {
		return 1 / distance(here, there) + ground.getFoodPriority();
	}

	private double foodPriority(Location here, Location there, Item item) {
		return 1 / distance(here, there) + item.getFoodPriority();
	}

	private double foodPriority(Location here, Location there, Actor actor) {
		return 1 / distance(here, there) + actor.getFoodPriority();
	}
}

class Pair<L, R> {
	private L l;
	private R r;

	public Pair(L l, R r) {
		this.l = l;
		this.r = r;
	}

	public L getL() {
		return l;
	}

	public R getR() {
		return r;
	}

	public void setL(L l) {
		this.l = l;
	}

	public void setR(R r) {
		this.r = r;
	}
}
