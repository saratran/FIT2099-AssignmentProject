package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;
import game.actor.Player;
import game.dinosaur.Maturity;
import game.dinosaur.Protoceratops;
import game.dinosaur.Velociraptor;
import game.ground.Dirt;
import game.ground.Floor;
import game.ground.Grass;
import game.ground.Store;
import game.ground.Tree;
import game.ground.Wall;
import game.item.Corpse;
import game.item.Egg;
import game.item.FoodItem;

/**
 * The main class for the dinosaur park game.
 *
 */
public class Application {

	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Grass(), new Dirt(), new Wall(), new Floor(), new Tree());

		List<String> map = Arrays.asList(
				"................................................................................",
				"................................................................................",
				".....#######....................................................................",
				".....#_____#....................................................................",
				".....#_____#....................................................................",
				".....###.###....................................................................",
				"................................................................................",
				"......................................+++.......................................",
				".......................................++++.....................................",
				"...................................+++++........................................",
				".....................................++++++.....................................",
				"......................................+++.......................................",
				".....................................+++........................................",
				"................................................................................",
				"............+++.................................................................",
				".............+++++..............................................................",
				"...............++........................................+++++..................",
				"........~....+++....................................++++++++....................",
				"............+++.......................................+++.......................",
				"................................................................................",
				".........................................................................++.....",
				"........................................................................++.++...",
				".........................................................................++++...",
				"..........................................................................++....",
				"................................................................................");
		GameMap gameMap = new GameMap(groundFactory, map);
		world.addGameMap(gameMap);

		Player player = new Player("Player", '@', 100);
		player.addMoney(100);
		player.addItemToInventory(new FoodItem(FoodSkill.HERBIVORE, 20));
		player.addItemToInventory(new Corpse(Species.PROTOCERATOPS, 15));
		world.addPlayer(player, gameMap.at(9, 4));
		gameMap.at(8, 9).addActor(new Protoceratops());
		gameMap.at(8, 14).addActor(new Velociraptor());

		gameMap.at(9, 3).setGround(new Store('S'));
		gameMap.at(0, 0).setGround(new Store('S'));


		gameMap.at(9, 6).addItem(new Egg(new Protoceratops(Maturity.BABY), 50, 10));
		gameMap.at(19, 6).addItem(new Egg(new Protoceratops(Maturity.BABY), 50 ,10));
		gameMap.at(9, 16).addItem(new Egg(new Protoceratops(Maturity.BABY), 50 ,10));

		
		// Place a pair of protoceratops in the middle of the map
//		gameMap.at(30, 12).addActor(new Protoceratops("Protoceratops"));
//		gameMap.at(8, 4).addActor(new Protoceratops("Protoceratops"));


		world.run();
	}
}
