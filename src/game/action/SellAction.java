package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.actor.Player;
import game.actor.Trader;

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
		if(actor instanceof Trader) {
			Trader seller = (Trader) actor;
			seller.addMoney(item.getSellValue());
			seller.removeItemFromInventory(item);
			return actor + " sold " + item.toString() + " and gained $" + item.getSellValue();

		}
		return "";
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Sells " + item.toString() + " ($" + item.getSellValue()+")";
	}

}
