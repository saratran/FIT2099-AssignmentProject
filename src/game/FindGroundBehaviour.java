package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;

public class FindGroundBehaviour implements Behaviour {

	@Override
	public Action getAction(Actor actor, GameMap map) {
		Location here = map.locationOf(actor);
		int x = here.x();
		int y = here.y();

		here.getExits();

//		int offset = 0;
//		while (true) {
//			offset += 1;
//			for (int m = x - offset; m <= x + 1; m++) {
//				for (int n = y - offset; n <= y + 1; n++) {
//					// TODO: loop to check 1 space away, then 2, 3, etc.
//					// Currently repeated inner points
//					Ground ground = map.at(m, n).getGround();
//					if (ground.getClass().equals(Tree.class)) {
//						// TODO: out of map range
//						Location there = map.at(m, n);
//						int currentDistance = distance(here, there);
//						for (Exit exit : here.getExits()) {
//							Location destination = exit.getDestination();
//							if (destination.canActorEnter(actor)) {
//								int newDistance = distance(destination, there);
//								if (newDistance < currentDistance) {
//									return new MoveActorAction(destination, exit.getName());
//								}
//							}
//						}
//					}
//				}
//			}
//		}
		return null;
	}

	private int distance(Location a, Location b) {
		return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
	}
}
