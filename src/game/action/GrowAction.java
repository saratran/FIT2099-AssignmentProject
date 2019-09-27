package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class GrowAction extends Action {
	
	private Actor adult;

	public GrowAction(Actor adult) {
		this.adult = adult;
		
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		Location here = map.locationOf(actor);
		map.removeActor(actor);
		here.addActor(adult);
		return actor.toString() + " grew into " + adult.toString();
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor.toString() + " grows into " + adult.toString();
	}

}
