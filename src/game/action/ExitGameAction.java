package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * An action to exit game. The executing actor needs to be the player.
 * @author Sara Tran
 *
 */
public class ExitGameAction extends Action {

	private String description;

	public ExitGameAction(String description) {
		this.description = description;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		map.removeActor(actor);
		return description;
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Exit game";
	}

}
