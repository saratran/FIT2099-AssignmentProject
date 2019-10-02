package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.Skill;
import game.actor.Player;
import game.dinosaur.Dinosaur;
import game.dinosaur.Maturity;

public class PlaceTagAction extends Action {

	private Item tag;
	private Dinosaur target;

	public PlaceTagAction(Item tag, Dinosaur target) {
		this.tag = tag;
		this.target = target;
	}
	@Override
	public String execute(Actor actor, GameMap map) {
		String fail = actor + " could not place tag on " + target.toString();
		if(actor instanceof Player && target.getMaturity() == Maturity.ADULT) {
			Player player = (Player) actor;
			if (!target.hasSkill(Skill.TAGGED)) {
				player.removeItemFromInventory(tag);
				player.addTaggedDino(target);
				target.addItemToInventory(tag);
				target.addSkill(Skill.TAGGED);
				return actor + " placed tag on " + target.toString();
			}
		} 
		return fail;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " places tag on " + target.toString();
	}
}