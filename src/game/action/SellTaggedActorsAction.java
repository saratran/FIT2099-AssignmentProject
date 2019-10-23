package game.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.actor.Player;
import game.actor.Trader;
import game.dinosaur.Consumer;
/**
 * Action used to sell tagged actors.
 *
 */
public class SellTaggedActorsAction extends Action {
	
	private List<Actor> targets = new ArrayList<Actor>();
	private String actorType;
	private int totalEarnings;
	
	/**
	 * Constructor.
	 * 
	 * @param consumer	The tagged actors that are to be sold.
	 */
	public SellTaggedActorsAction(List<Actor> actor) {
		this.targets = actor;
		this.actorType = targets.get(0).getClass().getSuperclass().getSimpleName();
		
	}
	
	/**
	 * Sells the tagged actors. Only sells the actors if they are on
	 * the same map as the actor selling them and they are alive. Removes
	 * the tagged actor from the list of tagged actors that the "seller" 
	 * possesses if they are sold successfully of if they are dead.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		if(actor instanceof Trader) {
			Trader seller = (Trader) actor;
			Iterator<Actor> iterator = targets.iterator();
			totalEarnings = 0;
			List<Actor> soldActors = new ArrayList<Actor>();
			while (iterator.hasNext()) {
				Actor target = iterator.next();
				if (target.isConscious() && map.contains(target)) {
				seller.addMoney(target.getSellValue());
				map.removeActor(target);
				totalEarnings += target.getSellValue();
				soldActors.add(target);
				} else if (!target.isConscious()) {
					actor.getTaggedActors().remove(target);
				}
			}
			for (Actor sold : soldActors) {
				actor.getTaggedActors().remove(sold);
			}
		}
		return actor + " sold all tagged " + actorType + " and gained $" + totalEarnings;
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Sells all tagged " + actorType;
	}

}
