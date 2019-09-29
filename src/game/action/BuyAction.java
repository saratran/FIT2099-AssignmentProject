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
		if (actor instanceof Player) {
			Player player = (Player) actor;
			if (player.getMoney() < item.getBuyValue()) {
				return "Player does not have enough money to buy " + item.toString() + "\nCurrent balance is $"
						+ player.getMoney();
			}
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
