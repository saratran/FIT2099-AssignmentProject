package game;

import java.util.ArrayList;
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
		if (!(actor instanceof Dinosaur)) {
			return null;
		}
		Dinosaur dinosaur = (Dinosaur) actor;

		if (!((Dinosaur) actor).isHungry()) {
			return null;
		}

		Location here = map.locationOf(dinosaur);
		int x = here.x();
		int y = here.y();

		List<Location> checkedLocations = new ArrayList<Location>();
		List<Location> locationsToGetExits = new ArrayList<Location>();

		checkedLocations.add(here);

		// Checking nearby locations first
		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination();
			checkedLocations.add(destination);
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
			// TODO: need to break when checked all of the map
			List<Exit> exits = new ArrayList<Exit>();
			for (Location location : locationsToGetExits) {
				exits.addAll(location.getExits());
			}
			locationsToGetExits.clear();
			
			for(Exit exit : exits) {
				Location destination = exit.getDestination();
				// TODO: could improve efficiency, currently searching through ArrayList is O(N)
				if(!checkedLocations.contains(destination)) {
					checkedLocations.add(destination);
					locationsToGetExits.add(destination);
					if (dinosaur.isFood(destination.getActor())) {
						return new FollowBehaviour(destination.getActor()).getAction(actor, map);
					}
					if (dinosaur.isFood(destination.getGround())){
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

}
