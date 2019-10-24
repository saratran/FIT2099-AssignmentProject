package game.behaviour;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public abstract class MoveBehaviour implements Behaviour {
	protected int speed;
	
	public MoveBehaviour(int speed) {
		this.speed = speed;
	}
	
	protected List<Location> getPossibleLocations(Actor actor, GameMap map) {
		List<Location> locations = new ArrayList<Location>();
		Location current = map.locationOf(actor);
		for (Exit exit : map.locationOf(actor).getExits()) {
			Location destination = exit.getDestination();
			int x_final = current.x() + this.speed*(destination.x() - current.x());
			int y_final = current.y() + this.speed*(destination.y() - current.y());
			if ((map.getXRange().contains(x_final)) & (map.getYRange().contains(y_final))){
				Location possibleDestination = map.at(x_final, y_final);
				locations.add(possibleDestination);
			}
		}
		return locations;
	}

}
