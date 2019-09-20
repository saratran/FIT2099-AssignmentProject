package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class SellAction extends Action {
	private Item item;

	public SellAction(Item item) {
		this.item = item;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		if(actor instanceof Player) {
			Player player = (Player) actor;
			player.addMoney(item.getSellValue());
			player.removeItemFromInventory(item);
		}
		return actor + " sold " + item.toString() + " and gained $" + item.getSellValue();
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Sells " + item.toString() + " ($" + item.getSellValue()+")";
	}

}
