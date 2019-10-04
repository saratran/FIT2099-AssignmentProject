package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.dinosaur.Dinosaur;

/**
 * Action for a Consumer to eat an Item
 *
 */
public class EatItemAction extends Action {
	private Location location;
	private Item food;

	/**
	 * Constructor
	 * 
	 * @param target the Item to be eaten
	 * @param location the Location of the Item
	 */
	public EatItemAction(Item target, Location location) {
		this.food = target;
		this.location = location;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		// note: can't change method signature to Consumer consumer so have to do casting
		if (actor.asConsumer() != null) {
			actor.asConsumer().addFoodValue(food.getFoodValue());
			location.removeItem((Item) food);
			return actor + " ate " + food + " and gained " + food.getFoodValue() + " food points";
		}
		return "";
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " eats " + food;
	}
}
