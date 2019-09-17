package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

public class EatGroundAction extends Action {
	private Ground target;
	private Location location;

	public EatGroundAction(Ground target, Location location) {
		this.target = target;
		this.location = location;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO: Any checking before casting?
		((Dinosaur) actor).addFoodValue(target.getFoodValue());
		
		// TODO: Better way to set new ground?
		location.setGround(new Dirt());
		return actor + " ate " + target.toString() + " and gained " + target.getFoodValue() + " food points";
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " eats " + target.toString();
	}

}
