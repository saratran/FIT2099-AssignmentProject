package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;
import game.actor.Player;
import game.dinosaur.DinoSkill;
import game.dinosaur.Maturity;
import game.dinosaur.Plesiosaur;
import game.dinosaur.Protoceratops;
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
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Grass(), new Dirt(), new Wall(), new Floor(), new Tree(), new Water(), new Reed());

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
				".............+++++............~~~~~~~~~~~~~.....................................",
				"...............++.............~~~~~~~~~~~~~..............+++++..................",
				"........,....+++..............~~~~~~~~~~~~~.........++++++++....................",
				"............+++...............~~~~~~~~~~~~~...........+++.......................",
				"..............................~~~~~~~~~~~~~.....................................",
				"..............................~~~~~~~~~~~~~..............................++.....",
				"........................................................................++.++...",
				".........................................................................++++...",
				"..........................................................................++....",
				"................................................................................");
		GameMap gameMap = new GameMap(groundFactory, map);
		world.addGameMap(gameMap);

		Player player = new Player("Player", '@', 100);
		player.addMoney(1000);
		player.addItemToInventory(new FoodItem(FoodSkill.HERBIVORE, Price.HerbivoreFoodItem.buyValue()));
		player.addItemToInventory(new Corpse("protoceratops corpse", Price.ProtoceratopsCorpse.sellValue()));
		player.addItemToInventory(new DinosaurTag());
		player.addItemToInventory(new Boat());
		world.addPlayer(player, gameMap.at(9, 4));
//		gameMap.at(9, 5).addActor(new Protoceratops());

//		gameMap.at(8, 9).addActor(new Protoceratops());
		gameMap.at(8, 14).addActor(new Velociraptor());
		gameMap.at(8, 15).addActor(new Protoceratops("Protoceratops"));
		Tyrannosaurus t = new Tyrannosaurus();
		t.addSkill(DinoSkill.CAPTIVE_BRED);
		gameMap.at(8,16).addActor(t);
//		gameMap.at(20, 14).addActor(new Velociraptor(Maturity.BABY));

		gameMap.at(9, 3).setGround(new Store('S'));

//		gameMap.at(9, 6).addItem(new Egg(new Protoceratops(Maturity.BABY), Price.ProtoceratopsEgg.getBuyValue(), Price.ProtoceratopsEgg.getSellValue()));

//		gameMap.at(24, 18).addItem(new Corpse("protoceratops corpse", Price.ProtoceratopsCorpse.sellValue()));
//		gameMap.at(24, 18).addItem(new Corpse("protoceratops corpse", Price.ProtoceratopsCorpse.sellValue()));
//		gameMap.at(29, 18).addItem(new Egg(new Plesiosaur(Maturity.BABY), Price.ProtoceratopsEgg.buyValue(), Price.ProtoceratopsEgg.sellValue()));                          
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
