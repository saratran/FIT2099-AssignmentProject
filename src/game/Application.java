package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;
import game.DinoGameMap.Direction;
import game.actor.Player;
import game.dinosaur.Maturity;
import game.dinosaur.Plesiosaur;
import game.dinosaur.Protoceratops;
import game.dinosaur.Velociraptor;
import game.ground.Dirt;
import game.ground.Floor;
import game.ground.Grass;
import game.ground.Reed;
import game.ground.Store;
import game.ground.Tree;
import game.ground.Wall;
import game.ground.Water;
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
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Grass(), new Dirt(), new Wall(), new Floor(), new Tree(), new Water(), new Reed());

		List<String> map = Arrays.asList(
				 "..............................................................................",
				 "..............................................................................",
		 	 	 ".....#######..................................................................",
				 ".....#_____#..................................................................",
				 ".....#_____#..................................................................",
				 ".....###.###..................................................................",
				 "..............................................................................",
				 "......................................+++.....................................",
				 ".......................................++++...................................",
				 "...................................+++++......................................",
				 ".....................................++++++...................................",
				 "......................................+++.....................................",
				 ".....................................+++......................................",
				 "..............................................................................",
				 "............+++...............................................................",
				 ".............+++++............~~~~~~~~~~~~~...................................",
				 "...............++.............~~~~~~~~~~~~~..............++++.................",
				 "........,....+++..............~~~~~~~~~~~~~.........+++++++...................",
				 "............+++...............~~~~~~~~~~~~~...........++......................",
				 "..............................~~~~~~~~~~~~~...................................",
				 "..............................~~~~~~~~~~~~~............................++.....",
				 "......................................................................++.++...",
				 ".......................................................................++++...",
				 ".........................................................................++...",
				 "..............................................................................");
		
		List<String> map2 = Arrays.asList(
				".....#######........................................................",
				".....#_____#........................................................",
				".............................................................++.....",
				".....###.###........................................................",
				"....................................................................",
				"......................................+++...........................",
				".......................................++++.........................",
				"...................................+++++............................",
				".....................................++++++.........................",
				"......................................+++...........................",
				".....................................+++............................",
				"....................................................................",
				"............+++.....................................................",
				".............+++++............~~~~~~~~~~~~~.........................",
				"...............++.............~~~~~~~~~~~~~..............++.........",
				"........,....+++..............~~~~~~~~~~~~~.........+++++++.........",
				".............................................................++.....",
				".............................................................++.....",
				".............................................................++.....",
				"............................................................++.++...",
				".............................................................++++...",
				"..............................................................++....",
				"....................................................................");
		DinoGameMap gameMap = new DinoGameMap(groundFactory, map);
		DinoGameMap gameMap2 = new DinoGameMap(groundFactory, map2);
		gameMap.addAdjacentMap(gameMap2, Direction.NORTH);
		gameMap2.addAdjacentMap(gameMap,Direction.SOUTH);
		world.addGameMap(gameMap);
		world.addGameMap(gameMap2);

		Player player = new Player("Player", '@', 100);
		player.addMoney(1000);
		player.addItemToInventory(new FoodItem(FoodSkill.HERBIVORE, Price.HerbivoreFoodItem.getBuyValue()));
		player.addItemToInventory(new Corpse("protoceratops corpse", Price.ProtoceratopsCorpse.getSellValue()));
		player.addItemToInventory(new DinosaurTag());
		world.addPlayer(player, gameMap.at(9, 4));
//		gameMap.at(9, 5).addActor(new Protoceratops());

//		gameMap.at(8, 9).addActor(new Protoceratops());
		gameMap.at(8, 14).addActor(new Velociraptor());
//		gameMap.at(20, 14).addActor(new Velociraptor(Maturity.BABY));

		gameMap.at(9, 3).setGround(new Store('S'));

//		gameMap.at(9, 6).addItem(new Egg(new Protoceratops(Maturity.BABY), Price.ProtoceratopsEgg.getBuyValue(), Price.ProtoceratopsEgg.getSellValue()));

		gameMap.at(24, 18).addItem(new Corpse("protoceratops corpse", Price.ProtoceratopsCorpse.getSellValue()));
		gameMap.at(24, 18).addItem(new Corpse("protoceratops corpse", Price.ProtoceratopsCorpse.getSellValue()));
		gameMap.at(29, 18).addItem(new Egg(new Plesiosaur(Maturity.BABY), Price.ProtoceratopsEgg.getBuyValue(), Price.ProtoceratopsEgg.getSellValue()));                          
//		gameMap.at(24, 18).addItem(new Corpse("protoceratops corpse", Price.ProtoceratopsCorpse.getSellValue()));
//		gameMap.at(24, 18).addItem(new Corpse("protoceratops corpse", Price.ProtoceratopsCorpse.getSellValue()));
//		gameMap.at(24, 18).addItem(new Corpse("protoceratops corpse", Price.ProtoceratopsCorpse.getSellValue()));
//		gameMap.at(24, 18).addItem(new Corpse("protoceratops corpse", Price.ProtoceratopsCorpse.getSellValue()));
		
		// Place a pair of protoceratops in the middle of the map
//		gameMap.at(30, 12).addActor(new Protoceratops("Protoceratops"));
//		gameMap.at(8, 4).addActor(new Protoceratops("Protoceratops"));

		world.run();
	}
}
