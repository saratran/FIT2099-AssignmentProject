package game.actor;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

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
		// TODO Auto-generated method stub
		return "Exit game";
	}

}
