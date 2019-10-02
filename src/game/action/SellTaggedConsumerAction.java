package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.actor.Player;
import game.actor.Trader;
import game.dinosaur.Consumer;
/**
 * Action used to sell tagged consumers at the store.
 * @author Harun Ergi
 *
 */
public class SellTaggedConsumerAction extends Action {
	
	private Consumer target;
	/**
	 * Constructor.
	 * 
	 * @param consumer	The tagged consumer that is to be sold.
	 */
	public SellTaggedConsumerAction(Consumer consumer) {
		this.target = consumer;
	}
	
	
	@Override
	public String execute(Actor actor, GameMap map) {
		if(actor instanceof Trader) {
			Trader seller = (Trader) actor;
			seller.addMoney(target.getSellValue());
			map.removeActor(target);
		}
		if (actor instanceof Player) {
			Player player = (Player) actor;
			player.getTaggedDinosaurs().remove(target);
		}
		return actor + " sold " + target.toString() + " and gained $" + target.getSellValue();
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Sells " + target.toString() + " ($" + target.getSellValue()+")";
	}

}
