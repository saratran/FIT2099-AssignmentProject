package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.Skill;
import game.actor.Player;
import game.dinosaur.Dinosaur;
import game.dinosaur.Maturity;

/**
 * Action to place a tag item on a dinosaur.
 * 
 * @author Harun Ergi
 *
 */
public class PlaceTagAction extends Action {

	private Item tag;
	private Actor target;

	/**
	 * Constructor.
	 * 
	 * @param tag	The tag that is to be placed.
	 * @param target	The dinosaur that the tag is being placed on.
	 */
	public PlaceTagAction(Item tag, Actor target) {
		this.tag = tag;
		this.target = target;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		String fail = actor + " could not place tag on " + target.toString();

		/*
		 * TODO: About casting to Player: 
		 * - The current method (ie keeping reference to tagged Actors) would require specific Player method to be called
		 * PROS:
		 * 		- Doesn't need to check the whole map for tagged Actors before selling
		 * CONS:
		 * 		- Require casting and maybe harder to expand in the future (since it's specific to Player class only)
		 * 		- May arise problem when the Player is in a different map than the tagged Actor and tries to sell them 
		 * (see comment in SellTaggedConsumerAction)
		 * 		- Not as important: SellTaggedConsumerAction is currently for 1 Actor only --> may clutter UI if tagged too many dinos
		 * 
		 * ALTERNATIVES:
		 * 		- Put the addTaggedDino() in ActorInterface so doesn't need casting (same drawback as before, may violate ISP)
		 * 		- The Skill method:
		 * 			+ Instead of keeping track of tagged Actors, just add Skill.TAGGED to them
		 * 			+ Then make SellTaggedConsumerAction to do the checking through the map for tagged Actors and sell them all at once
		 * 			+ This wouldn't require any Player, Consumer or Dinosaur casting (only cast Trader because need money methods)
		 * 			+ At the cost of making it slightly more inefficient (but shouldn't matter too much
		 */

		/*
		 * TODO: side thought - not too important - World in engine knows about which Actor is a player -->
		 * would be better if it some how has method to check for that, instead of
		 * checking for Player class (which is not part of the engine, it's only the
		 * player because world.addPlayer(actor) in Application
		 */
		
		if (!target.hasSkill(Skill.TAGGED)) {
			actor.removeItemFromInventory(tag);
			actor.addTaggedActor(target);
			target.addItemToInventory(tag);
			target.addSkill(Skill.TAGGED);
			return actor + " placed tag on " + target.toString();
		}
		return fail;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " places tag on " + target.toString();
	}
}