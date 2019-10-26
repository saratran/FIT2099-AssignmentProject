package game.behaviour;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Base class for behaviours that involve moving actor to another location.
 * @author Sara Tran
 *
 */
public abstract class MoveBehaviour implements Behaviour {
	protected int speed;

	/**
	 * Constructor
	 * @param speed maximum number of squares the actor can move in 1 turn
	 */
	public MoveBehaviour(int speed) {
		this.speed = speed;
	}

	/**
	 * @return a list of locations that the actor can enter
	 */
	protected List<Location> getPossibleLocations(Actor actor, GameMap map) {
		List<Location> locations = new ArrayList<Location>();
		Location current = map.locationOf(actor);
		for (Exit exit : map.locationOf(actor).getExits()) {
			Location destination = exit.getDestination();
			int x_final = current.x() + this.speed * (destination.x() - current.x());
			int y_final = current.y() + this.speed * (destination.y() - current.y());
			if ((map.getXRange().contains(x_final)) && (map.getYRange().contains(y_final))) {
				Location possibleDestination = map.at(x_final, y_final);
				if (!possibleDestination.containsAnActor() && possibleDestination.canActorEnter(actor)) {
					locations.add(possibleDestination);
				}
			}
		}
		return locations;
	}

}
