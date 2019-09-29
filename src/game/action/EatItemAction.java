package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.interfaces.EdibleInterface;
import game.dinosaur.Dinosaur;

public class EatItemAction extends EatAction {
	private Location location;

	public EatItemAction(Item target, Location location) {
		super((EdibleInterface) target); // TODO: check before casting?
		this.location = location;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO: any check?
		location.removeItem((Item)food);
		return super.execute(actor, map);
	}
}
