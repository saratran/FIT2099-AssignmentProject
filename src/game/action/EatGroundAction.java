package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.interfaces.EdibleGroundInterface;
import edu.monash.fit2099.interfaces.EdibleInterface;
import game.dinosaur.Dinosaur;
import game.ground.Dirt;

public class EatGroundAction extends EatAction {
	private Location location;

	public EatGroundAction(Ground target, Location location) {
		super((EdibleInterface) target); // TODO: check before casting?
		this.location = location;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO: Better way to set new ground
		// EdibleGroundInterface
		location.setGround(((EdibleGroundInterface)food).eatenGround());
		return super.execute(actor, map);
	}

}
