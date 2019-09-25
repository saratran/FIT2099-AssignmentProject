package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.dinosaur.Dinosaur;

public class FeedAction extends Action {
	private Item food;
	private Actor target;

	public FeedAction(Item item, Actor target) {
		food = item;
		this.target = target;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO: checking and error handling?
		((Dinosaur)target).addFoodValue(food.getFoodValue());
		actor.removeItemFromInventory(food);
		return actor + " fed " + food.toString();
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " feeds " + food.toString() + " to " + target.toString();
	}

}
