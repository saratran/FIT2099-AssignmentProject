package game;

import java.util.HashMap;
import java.util.List;

import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.GroundFactory;
import edu.monash.fit2099.engine.Location;

public class DinoGameMap extends GameMap {
	private HashMap<Direction, GameMap> adjacentMaps = new HashMap<Direction, GameMap>();
	private String string = "something";

	public enum Direction {
		NORTH, SOUTH, EAST, WEST
	}

	public DinoGameMap(GroundFactory groundFactory, List<String> lines) {
		super(groundFactory, lines);
	}

	public void addAdjacentMap(GameMap map, Direction direction) {
		adjacentMaps.put(direction, map);
		int maxX = Math.min(getXRange().max(), map.getXRange().max());
		int maxY = Math.min(getYRange().max(), map.getYRange().max());

		if (direction == Direction.NORTH) {
			for (int x = 0; x <= maxX; x++) {
				Location destination = map.at(x, map.getYRange().max());
				this.at(x, 0).addExit(new Exit("North", destination, "8"));
			}
		} else if (direction == Direction.SOUTH) {
			for (int x = 0; x <= maxX; x++) {
				Location destination = map.at(x, map.getYRange().min());
				this.at(x, getYRange().max()).addExit(new Exit("South", destination, "2"));
			}
		} else if (direction == Direction.WEST) {
			for (int y = 0; y <= maxY; y++) {
				Location destination = map.at(map.getXRange().min(), y);
				this.at(getXRange().max(), y).addExit(new Exit("West", destination, "6"));
			}
		} else if (direction == Direction.EAST) {
			for (int y = 0; y <= maxY; y++) {
				Location destination = map.at(map.getXRange().max(), y);
				this.at(getXRange().min(), y).addExit(new Exit("East", destination, "4"));
			}
		}
		
		// this works but expanding may get a little messy
		// the method above just ignore the locations that exceed the limit
//		if (direction == Direction.SOUTH) {
//			for (int x = 0; x <= getXRange().max(); x++) {
//				Location destination;
//				if (map.getXRange().contains(x)) {
//					destination = map.at(x, 0);
//					int y = getYRange().max();
//					this.at(x, y).addExit(new Exit("South", destination, "2"));
//				}
//				if (map.getXRange().max() < this.getXRange().max()) {
//					if (x < map.getXRange().min()) {
//						destination = map.at(map.getXRange().min(), 0);
//					} else if (x > map.getXRange().max()) {
//						destination = map.at(map.getXRange().max(), 0);
//					} else {
//						destination = map.at(x, 0);
//					}
//					int y = getYRange().max();
//					this.at(x, y).addExit(new Exit("South", destination, "2"));
//				}
//			}
//		}
	}

}
