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
public class SellTaggedActorAction extends Action {
	
	private Actor target;
	/**
	 * Constructor.
	 * 
	 * @param consumer	The tagged consumer that is to be sold.
	 */
	
	public SellTaggedActorAction(Actor consumer) {
		this.target = consumer;
	}
	
	
	@Override
	public String execute(Actor actor, GameMap map) {
		if(actor instanceof Trader) {
			Trader seller = (Trader) actor;
			seller.addMoney(target.getSellValue());
			map.removeActor(target); // TODO: potential problem if the player is not on the same Map as the target
		}
		actor.getTaggedActors().remove(target);
		return actor + " sold " + target.toString() + " and gained $" + target.getSellValue();
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Sells " + target.toString() + " ($" + target.getSellValue()+")";
	}

}
