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
		// TODO: need to check for actor instanceof Player before casting? Should check when calling execute or inside or both
		Player player = (Player) actor;
		if (player.getMoney() < item.getBuyValue()) {
			return "Player does not have enough money to buy " + item.toString() + "\nCurrent balance is ($"
					+ player.getMoney() + ")";
		}
		// Downcasting for specific items
		// TODO: good way to this?
		if (item instanceof Egg) {
			item = (Egg) item;
			player.addItemToInventory(new Egg(item.toString(), item.getDisplayChar(), ((Egg) item).getSpecies()));
		} else {
			actor.addItemToInventory(new PortableDinoItem(item.toString(), item.getDisplayChar()));
		}
		player.deductMoney(item.getBuyValue());
		return "Player bought " + item.toString();
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Buy " + item.toString() + " ($" + item.getBuyValue() + ")";
	}

}
