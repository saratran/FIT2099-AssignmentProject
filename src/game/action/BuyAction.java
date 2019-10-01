package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.actor.Buyer;
import game.actor.Player;


public class BuyAction extends Action {
	private Item item;

	public BuyAction(Item item) {
		this.item = item;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO: asBuyer in ActorInterface?
		if (actor instanceof Buyer) {
			Buyer buyer = (Buyer) actor;
			if (buyer.getMoney() < item.getBuyValue()) {
				return "Player does not have enough money to buy " + item.toString() + "\nCurrent balance is $"
						+ buyer.getMoney();
			}
			
			// TODO: asItem() in BuyableInterface?
			buyer.addItemToInventory(item);
			buyer.deductMoney(item.getBuyValue());
		}
		return actor + " bought " + item.toString();
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Buy " + item.toString() + " ($" + item.getBuyValue() + ")";
	}

}
