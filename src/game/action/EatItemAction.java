package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.dinosaur.Dinosaur;

public class EatItemAction extends Action {
	private Location location;
	private Item food;

	public EatItemAction(Item target, Location location) {
		this.food = target;
		this.location = location;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO: note, can't change method signature to Consumer consumer so have to do casting
		if (actor.asConsumer() != null) {
			// TODO: alternative --> all food methods in ActorInterface
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
