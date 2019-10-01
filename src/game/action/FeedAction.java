package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.dinosaur.Dinosaur;

public class FeedAction extends Action {
	private Actor target;
	private Item food;


	public FeedAction(Item item, Actor target) {
		this.food = item;
		this.target = target;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO: use something else instead of casting Dinosaur
		((Dinosaur) actor).addFoodValue(food.getFoodValue());
		actor.removeItemFromInventory((Item) food);
		return actor + " fed " + food.toString();
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " feeds " + food.toString() + " to " + target.toString();
	}

}
