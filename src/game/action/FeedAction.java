package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.dinosaur.Consumer;
import game.dinosaur.Dinosaur;

/**
 * Action for an Actor to feed an Item to another Consumer
 *
 */
public class FeedAction extends Action {
	private Consumer target;
	private Item food;

	/**
	 * Constructor
	 * 
	 * @param item the item to feed
	 * @param target the actor to be fed to
	 */
	public FeedAction(Item item, Consumer target) {
		this.food = item;
		this.target = target;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		target.addFoodValue(food.getFoodValue());
		actor.removeItemFromInventory((Item) food);
		return actor + " fed " + food.toString() + " to " + target.toString();
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " feeds " + food.toString() + " to " + target.toString();
	}

}
