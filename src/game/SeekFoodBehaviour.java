package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class SeekFoodBehaviour implements Behaviour {

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
		
		// Checking locations nearby first
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
			locationsToGetExits.clear(); // Empty list after getting the exits

			for (Exit exit : exits) {
				Location destination = exit.getDestination();

				// Only continue to check if location has not been checked before
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
		return  location.x() + "," + location.y();
	}
}
