package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.interfaces.EdibleInterface;
import game.dinosaur.Dinosaur;

public abstract class EatAction extends Action {
	protected EdibleInterface food;

	public EatAction(EdibleInterface food) {
		this.food = food;
	}
	@Override
	public String execute(Actor actor, GameMap map) {
		// Any checking before casting?
		// TODO: EaterInterface?
		((Dinosaur) actor).addFoodValue(food.getFoodValue());

		// Any checking before removing? (here or in constructor)
		return actor + " ate " + food.getClass().getSimpleName() + " and gained "
				+ food.getFoodValue() + " food points";
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " eats " + food.getClass().getSimpleName();
	}

}
