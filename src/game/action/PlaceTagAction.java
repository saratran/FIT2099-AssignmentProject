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