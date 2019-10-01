package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.dinosaur.Dinosaur;
import game.ground.Dirt;

public class EatGroundAction extends Action {
	private Location location;
	private Ground target;

	public EatGroundAction(Ground target, Location location) {
		this.target = target;
		this.location = location;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		if (actor.asConsumer() != null) {
			// TODO: alternative --> all food methods in ActorInterface
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
