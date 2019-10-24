package game.behaviour;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;

/**
 * A class that is used to determine a MoveAction that will move the actor one step 
 * closer to a target Location.
 *
 */
public class ToLocationBehaviour extends MoveBehaviour {
	protected Location target_location;

	/**
	 * Constructor
	 * @param target target location to go to
	 * @param speed TODO
	 */
	public ToLocationBehaviour(Location target, int speed) {
		super(speed);
		this.target_location = target;
	}

	public ToLocationBehaviour(Location target) {
		this(target, 1);
	}
	
	/**
	 * return a MoveActorAction to move the actor nearer to the target location
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		List<Location> destinations = getPossibleLocations(actor, map);
		Location here = map.locationOf(actor);
		int minDistance = distance(here, target_location);
		Location chosenLocation = here;
		for(Location destination : destinations) {
			int newDistance = distance(destination, target_location);
			if (newDistance < minDistance) {
				chosenLocation = destination;
			}
		}
		return new MoveActorAction(chosenLocation,"towards a location");
		
//		int currentDistance = distance(here, target_location);
//		
//		
//		for (Exit exit : here.getExits()) {
//			Location destination = exit.getDestination();
//			if (destination.canActorEnter(actor)) {
//				int newDistance = distance(destination, target_location);
//				if (newDistance < currentDistance) {
//					return new MoveActorAction(destination, exit.getName());
//				}
//			}
//		}
//		return null;
	}
	
	/**
	 * Compute the Manhattan distance between two locations.
	 * 
	 * @param a the first location
	 * @param b the first location
	 * @return the number of steps between a and b if you only move in the four cardinal directions.
	 */
	private int distance(Location a, Location b) {
		return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
	}

}
