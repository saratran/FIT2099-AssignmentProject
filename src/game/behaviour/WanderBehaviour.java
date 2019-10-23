package game.behaviour;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
/**
 * Causes actors to move around the map randomly.
 *
 */
public class WanderBehaviour implements Behaviour {

	private Random random = new Random();
	private int speed;

	public WanderBehaviour(int speed) {
		this.speed = speed;
	}

	public WanderBehaviour() {
		this(1);
	}

	/**
	 * Returns a MoveAction to wander to a random location, if possible.  
	 * If no movement is possible, returns null.
	 * 
	 * @param actor the Actor enacting the behaviour
	 * @param map the map that actor is currently on
	 * @return an Action, or null if no MoveAction is possible
	 */
	//	@Override
	//	public Action getAction(Actor actor, GameMap map) {
	//		ArrayList<Location> possibleLocations = new ArrayList<Location>();
	//		ArrayList<Action> actions = new ArrayList<Action>();
	//		Location location = map.locationOf(actor);
	//		
	//		for (int x = 0; x < this.speed; x++) {
	//			for (Exit exit : location.getExits()) {
	//	            Location destination = exit.getDestination();
	//	            if (destination.canActorEnter(actor)) {
	//	            	possibleLocations.add(destination);
	//	            }
	//	            if ((x < speed-1) & (destination.canActorEnter(actor))) {
	//	            	actions.add(exit.getDestination().getMoveAction(actor, "around", exit.getHotKey()));
	//	            }
	//	        }
	//		
	//		if (!possibleLocations.isEmpty()) {
	//			location =  possibleLocations.get(random.nextInt(possibleLocations.size()));
	//		} else {
	//			return null;
	//		}
	//		}
	//		
	//		if (!actions.isEmpty()) {
	//			return actions.get(random.nextInt(actions.size()));
	//		}
	//		else {
	//			return null;
	//		}
	//
	//	}
	@Override
	public Action getAction(Actor actor, GameMap map) {
		ArrayList<Action> actions = new ArrayList<Action>();
		Location current = map.locationOf(actor);

		for (Exit exit : map.locationOf(actor).getExits()) {
			Location destination = exit.getDestination();
			int x_final = current.x() + this.speed*(destination.x() - current.x());
			int y_final = current.y() + this.speed*(destination.y() - current.y());
			if ((map.getXRange().contains(x_final)) & (map.getYRange().contains(y_final))){
				Location possibleDestination = map.at(x_final, y_final);
				if (possibleDestination.canActorEnter(actor)) {
					actions.add(possibleDestination.getMoveAction(actor, "around", exit.getHotKey()));
				}
			}
		}
		
		if (!actions.isEmpty()) {
			return actions.get(random.nextInt(actions.size()));
		}
		else {
			return null;
		}

	}
	//	@Override
	//	public Action getAction(Actor actor, GameMap map) {
	//		ArrayList<Action> actions = new ArrayList<Action>();
	//		for (Exit exit : map.locationOf(actor).getExits()) {
	//            Location destination = exit.getDestination();
	//            if (destination.canActorEnter(actor)) {
	//            	actions.add(exit.getDestination().getMoveAction(actor, "around", exit.getHotKey()));
	//            }
	//        }
	//		
	//		if (!actions.isEmpty()) {
	//			return actions.get(random.nextInt(actions.size()));
	//		}
	//		else {
	//			return null;
	//		}
	//
	//	}
}
