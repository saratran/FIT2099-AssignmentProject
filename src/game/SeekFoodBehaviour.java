package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;
import edu.monash.fit2099.engine.NumberRange;

public class SeekFoodBehaviour implements Behaviour {

	@Override
	public Action getAction(Actor actor, GameMap map) {
		HashMap<String, Location> checkedLocations = new HashMap<String, Location>();

		if (!(actor instanceof Dinosaur)) {
			return null;
		}
		Dinosaur dinosaur = (Dinosaur) actor;

		if (!((Dinosaur) actor).isHungry()) {
			return null;
		}

		Location here = map.locationOf(dinosaur);

		List<Location> locationsToGetExits = new ArrayList<Location>();

		checkedLocations.put(locationToKey(here), here);
		
		// Checking nearby locations first
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

		while (!locationsToGetExits.isEmpty()) {
			List<Exit> exits = new ArrayList<Exit>();
			for (Location location : locationsToGetExits) {
				exits.addAll(location.getExits());
			}
			locationsToGetExits.clear();

			for (Exit exit : exits) {
				Location destination = exit.getDestination();

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
		return null;
	}
	
	private String locationToKey(Location location) {
		return  location.x() + "," + location.y();
	}
}
