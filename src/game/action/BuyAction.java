package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.actor.Trader;
import game.actor.Player;

/**
 * Action to buy an Item
 * 
 * @author Sara Tran
 *
 */
public class BuyAction extends Action {
	/**
	 * Item that is to be bought
	 */
	private Item item;

	public BuyAction(Item item) {
		this.item = item;
	}


	@Override
	public String execute(Actor actor, GameMap map) {
		// Needs actor as a Trader to support money related methods
		if (actor instanceof Trader) {
			Trader buyer = (Trader) actor;
			if (buyer.getMoney() < item.getBuyValue()) {
				return "Player does not have enough money to buy " + item.toString() + "\nCurrent balance is $"
						+ buyer.getMoney();
			}
			
			buyer.addItemToInventory(item);
			buyer.deductMoney(item.getBuyValue());
			return actor + " bought " + item.toString();
		}
		return "";
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Buy " + item.toString() + " ($" + item.getBuyValue() + ")";
	}

}
