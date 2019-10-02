package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.actor.Player;
import game.dinosaur.Dinosaur;
import game.item.DinosaurTag;

public class PlaceTagAction extends Action {
	
	private DinosaurTag tag;
	private Dinosaur target;
	
	public PlaceTagAction(DinosaurTag tag, Dinosaur target) {
		this.tag = tag;
		this.target = target;
 	}

	@Override
	public String execute(Actor actor, GameMap map) {
		if(actor instanceof Player && target.isMatureAge()) {
			Player player = (Player) actor;
			if (player.getInventory().contains(tag) && tag.getDino() == null) {
				tag.addDino(target);
				target.addTag(tag);
			}
		}
		return actor + " placed tag on " + target.toString() + " and gained $" +target.getSellValue();
	}

	@Override
	public String menuDescription(Actor actor) {
		return null;
	}

}
