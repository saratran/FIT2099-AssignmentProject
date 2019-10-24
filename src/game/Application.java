package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;
import game.DinoGameMap.Direction;
import game.actor.Player;
import game.dinosaur.DinoSkill;
import game.dinosaur.Maturity;
import game.dinosaur.Plesiosaur;
import game.dinosaur.Protoceratops;
import game.dinosaur.Pteranodon;
import game.dinosaur.Tyrannosaurus;
import game.dinosaur.Velociraptor;
import game.ground.Dirt;
import game.ground.Floor;
import game.ground.Grass;
import game.ground.Reed;
import game.ground.Store;
import game.ground.Tree;
import game.ground.Wall;
import game.ground.Water;
import game.item.Boat;
import game.item.Corpse;
import game.item.DinosaurTag;
import game.item.Egg;
import game.item.FoodItem;

/**
 * The main class for the dinosaur park game.
 *
 */
public class Application {

	public static void main(String[] args) {
		World world = (new DinoWorldInit()).worldInit();
		world.run();
	}
	
}
