package game.behaviour;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;

/**
 * A class that is used to determine a MoveAction that will move the actor one step 
 * closer to a target Location.
 * @author Sara Tran
 *
 */
public class ToLocationBehaviour implements Behaviour {
	protected Location target_location;

	/**
	 * Constructor
	 * @param target target location to go to
	 */
	public ToLocationBehaviour(Location target) {
		this.target_location = target;
	}

	protected ToLocationBehaviour() {
		
	}
	
	/**
	 * return a MoveActorAction to move the actor nearer to the target location
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		Location here = map.locationOf(actor);
		int currentDistance = distance(here, target_location);
		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination();
			if (destination.canActorEnter(actor)) {
				int newDistance = distance(destination, target_location);
				if (newDistance < currentDistance) {
					return new MoveActorAction(destination, exit.getName());
				}
			}
		}
		return null;
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
