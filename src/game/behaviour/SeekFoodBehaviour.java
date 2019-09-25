package game.behaviour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.action.AttackAction;
import game.action.EatGroundAction;
import game.action.EatItemAction;
import game.dinosaur.Dinosaur;

/**
 * A class that is used to determine the appropriate action for a Dinosaur to
 * seek and eat nearby food
 * 
 * @author Sara Tran
 *
 */
public class SeekFoodBehaviour implements Behaviour {

	/**
	 * <p>Return the appropriate action depends on the situation.
	 * 
	 * <p>1) When the actor is not a Dinosaur, is not hungry or there is no food available on the map, return null
	 * 
	 * <p>2) When there is food nearby the Dinosaur (ie 1 space away), the action returned can be one of these:
	 * <ul>
	 * <li> AttackAction if there is an edible Actor nearby
	 * <li> EatGroundAction if there is an edible Ground nearby
	 * <li> EatItemAction if there is an edible Item nearby
	 * </ul>
	 * 
	 * <p>3) When there is food further away, the action returned can be:
	 * <ul>
	 * <li> the action returned by FollowBehaviour for an edible Actor
	 * <li> the action returned by ToLocationBehaviour for the location that has an edible Item or Ground
	 * </ul>
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		HashMap<String, Location> checkedLocations = new HashMap<String, Location>(); // To store locations that have been checked for food
		List<Location> locationsToGetExits = new ArrayList<Location>(); // To store locations that need to get Exits next

		if (!(actor instanceof Dinosaur)) {
			return null;
		}
		Dinosaur dinosaur = (Dinosaur) actor;

		if (!((Dinosaur) actor).isHungry()) {
			return null;
		}

		Location here = map.locationOf(dinosaur);
		checkedLocations.put(locationToKey(here), here);

		// Checking locations nearby first (ie 1 space away)
		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination();
			checkedLocations.put(locationToKey(destination), destination);
			locationsToGetExits.add(destination);

			if (dinosaur.isFood(destination.getActor())) {
				return new AttackAction(destination.getActor());
			}

			if (dinosaur.isFood(destination.getGround())) {
				return new EatGroundAction(destination.getGround(), destination);
			}
			for (Item item : destination.getItems()) {
				if (dinosaur.isFood(item)) {
					return new EatItemAction(item, destination);
				}
			}
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

					if (dinosaur.isFood(destination.getActor())) {
						return new FollowBehaviour(destination.getActor()).getAction(actor, map);
					}
					if (dinosaur.isFood(destination.getGround())) {
						return new ToLocationBehaviour(destination).getAction(actor, map);
					}
					for (Item item : destination.getItems()) {
						if (dinosaur.isFood(item)) {
							return new ToLocationBehaviour(destination).getAction(actor, map);
						}
					}
				}
			}
		}

		// null when there is no available food
		return null;
	}

	private String locationToKey(Location location) {
		return location.x() + "," + location.y();
	}
}
