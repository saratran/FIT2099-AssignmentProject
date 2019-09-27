package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.actor.Player;


public class BuyAction extends Action {
	private Item item;

	public BuyAction(Item item) {
		this.item = item;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO: need to check for actor instanceof Player before casting? Should check
		// when calling execute or inside or both
		if (actor instanceof Player) {
			Player player = (Player) actor;
			if (player.getMoney() < item.getBuyValue()) {
				return "Player does not have enough money to buy " + item.toString() + "\nCurrent balance is $"
						+ player.getMoney();
			}
//			// Downcasting for specific items
//			// TODO: good way to this? --> NOPE
//			if (item instanceof Egg) {
//				item = (Egg) item;
//				player.addItemToInventory(new Egg(item.toString(), item.getDisplayChar(), ((Egg) item).getSpecies()));
//			} else {
//				actor.addItemToInventory(new PortableDinoItem(item.toString(), item.getDisplayChar()));
//			}

			
//			// Add a shallow copy to player inventory
			// Allow passing in the same Item object from Store
//			try {
//				player.addItemToInventory((Item) ((PortableDinoItem)item).clone());
//			} catch (CloneNotSupportedException e) {
//				e.printStackTrace();
//			}
			
			player.addItemToInventory(item);
			player.deductMoney(item.getBuyValue());
		}
		return actor + " bought " + item.toString();
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Buy " + item.toString() + " ($" + item.getBuyValue() + ")";
	}

}
