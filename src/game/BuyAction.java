package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/*
 * TODO:
 * - Need to take money into account
 * - Potential problem(?): after buying an item, menu shows option to drop it --> can get clutter if bought many item
 * - Egg bought from store cannot hatch due super class PortableDinoItem does not have Egg tick() method implementation
 */
public class BuyAction extends Action {
	private Item item;

	public BuyAction(Item item) {
		this.item = item;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		// Downcasting for specific items
		if (item instanceof Egg) {
			item = (Egg) item;
			actor.addItemToInventory(new Egg(item.toString(), item.getDisplayChar(), ((Egg) item).getSpecies()));
		}else {
			actor.addItemToInventory(new PortableDinoItem(item.toString(), item.getDisplayChar()));
		}
		return "Player bought " + item.toString();
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Buy " + item.toString();
	}

}
