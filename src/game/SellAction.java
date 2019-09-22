package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Special Action that allows the Player to sell an Item from their inventory
 * @author Sara Tran
 *
 */
public class SellAction extends Action {
	private Item item;

	/**
	 * Constructor
	 * @param item item that is being sold
	 */
	public SellAction(Item item) {
		this.item = item;
	}

	/**
	 * Carry out the selling action. Money is added to the Player and the Item is removed from the inventory
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		if(actor instanceof Player) {
			Player player = (Player) actor;
			player.addMoney(item.getSellValue());
			// TODO: error handling, what if actor doesn't have the item in their inventory?
			player.removeItemFromInventory(item);
		}
		return actor + " sold " + item.toString() + " and gained $" + item.getSellValue();
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Sells " + item.toString() + " ($" + item.getSellValue()+")";
	}

}
