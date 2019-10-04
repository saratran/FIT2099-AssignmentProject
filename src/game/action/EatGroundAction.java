package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.dinosaur.Dinosaur;
import game.ground.Dirt;

/**
 * Action for a Consumer to eat a Ground
 *
 */
public class EatGroundAction extends Action {
	private Location location;
	private Ground target;

	/**
	 * Constructor
	 * 
	 * @param target The Ground to be eaten
	 * @param location Location of the Ground to be eaten
	 */
	public EatGroundAction(Ground target, Location location) {
		this.target = target;
		this.location = location;
	}


	@Override
	public String execute(Actor actor, GameMap map) {
		// Need actor to be a Consumer to have food related methods
		if (actor.asConsumer() != null) {
			actor.asConsumer().addFoodValue(target.getFoodValue());
			location.setGround(target.eatenGround());
			return actor + " ate " + target.getClass().getSimpleName() + " and gained " + target.getFoodValue()
					+ " food points";
		}
		return "";
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " eats " + target.getClass().getSimpleName();
	}

}
