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
		if(direction == Direction.NORTH) {
			for(int x=0; x<=getXRange().max(); x++) {
				Location destination = map.at(x, map.getYRange().max());
				this.at(x,0).addExit(new Exit("North", destination, "8"));
			}
		}
	}


}
