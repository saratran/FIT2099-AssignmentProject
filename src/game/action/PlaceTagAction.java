package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.actor.Player;
import game.dinosaur.Dinosaur;

public class PlaceTagAction extends Action {

	private Item tag;
	private Actor target;

	public PlaceTagAction(Item item, Actor target) {
		tag = item;
		this.target = target;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		if(actor instanceof Player) {
			Player player = (Player) actor;
			player.addMoney(target.getSellValue());
			player.removeItemFromInventory(tag);
			// TODO: error handling, what if actor doesn't have the item in their inventory?
			map.removeActor(target);
		}
		return actor + " placed tag on " + target.toString() + " and gained $" +target.getSellValue();
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " places tag on " + target.toString();
	}
}
