package game.behaviour;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;
/**
 * Causes actors to move around the map randomly.
 *
 */
public class WanderBehaviour extends MoveBehaviour {

	private Random random = new Random();
//	private int speed;

	public WanderBehaviour(int speed) {
		super(speed);
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
	@Override
	public Action getAction(Actor actor, GameMap map) {
		List<Action> actions = new ArrayList<Action>();
		List<Location> destinations = getPossibleLocations(actor, map);
		for(Location destination : destinations) {
			actions.add(new MoveActorAction(destination,"around"));
		}
		if (!actions.isEmpty()) {
			return actions.get(random.nextInt(actions.size()));
		}
		else {
			return null;
		}

	}
}
