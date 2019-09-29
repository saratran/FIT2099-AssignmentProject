package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.interfaces.EdibleInterface;
import game.dinosaur.Dinosaur;

public class FeedAction extends EatAction {
	private Actor target;

	public FeedAction(Item item, Actor target) {
		super((EdibleInterface) item);
		this.target = target;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		super.execute(target, map);
		actor.removeItemFromInventory((Item) food);
		return actor + " fed " + food.toString();
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " feeds " + food.toString() + " to " + target.toString();
	}

}
