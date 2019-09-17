package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class EatItemAction extends Action {
	private Item target;
	private Location location;

	public EatItemAction(Item target, Location location) {
		this.target = target;
		this.location = location;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		// Any checking before casting?
		((Dinosaur) actor).addFoodValue(target.getFoodValue());
		
		// Any checking before removing? (here or in constructor)
		location.removeItem(target);
		return actor + " ate " + target.getClass().getName() + " and gained " + target.getFoodValue() + " food points";
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO: target name
		return actor + " eats " + target.getClass().getName();
	}

}
