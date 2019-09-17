package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;
import edu.monash.fit2099.engine.NumberRange;

public class SeekFoodBehaviour implements Behaviour {

	@Override
	public Action getAction(Actor actor, GameMap map) {
		if (!(actor instanceof Dinosaur)) {
			return null;
		}
		Dinosaur dinosaur = (Dinosaur) actor;

		if (!((Dinosaur) actor).isHungry()) {
			return null;
		}
		
		Location here = map.locationOf(dinosaur);
		int x = here.x();
		int y = here.y();

		// Checking nearby locations first
		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination();
			if (dinosaur.isFood(destination.getActor())) {
				return new AttackAction(destination.getActor());
			}

			if (dinosaur.isFood(destination.getGround())) {
				return new EatGroundAction(destination.getGround(), destination);
			}
			for (Item item : destination.getItems()) {
				if (dinosaur.isFood(item)) {
					return new EatItemAction(item, destination);
				}
			}

		}

//		here.getExits();
//		List<Exit> checkedExits = new ArrayList<Exit>();
//		List<Location> nextLocations = new ArrayList<Location>();
//
//		for (Exit exit : here.getExits()) {
//			nextLocations.add(exit.getDestination());
//			checkedExits.add(exit);
//		}
//		
//		int xMax = map.getXRange().max();
//		int xMin = map.getXRange().min();
//		int yMax = map.getYRange().max();
//		int yMin = map.getYRange().min();

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
