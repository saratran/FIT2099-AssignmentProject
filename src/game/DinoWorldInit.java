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

public class DinoWorldInit {
	private World world;
	private DinoGameMap gameMap;
	private DinoGameMap gameMap2;
	
	public World worldInit() {
		this.world = new World(new Display());
		addMaps();
		addPlayer();
//		addActorsAndItems();
//		scenarioTyrannosaurus();
//		scenarioPteranodon();
		scenarioPlesiosaur();
		return world;
	}
	
	private void addMaps() {
		FancyGroundFactory groundFactory = new FancyGroundFactory(new Grass(), new Dirt(), new Wall(), new Floor(), new Tree(), new Water(), new Reed(), new Store());
		List<String> map = Arrays.asList(
				 "..............................................................................",
				 "..............................................................................",
		 	 	 ".....#######..................................................................",
				 ".....#___S_#..................................................................",
				 ".....#_____#..................................................................",
				 ".....###.###..................................................................",
				 "..............................................................................",
				 "......................................+++.....................................",
				 ".......................................++++...................................",
				 "...................................+++++......................................",
				 ".....................................++++++...................................",
				 "......................................+++.....................................",
				 ".....................................+++......................................",
				 "..............................~~~~~~~~~~~~~...................................",
				 "............+++...............~~~~~~~~~~~~~...................................",
				 ".............+++++............~~~~~~~~~~~~~...................................",
				 "...............++.............~~~~~~~~~~~~~..............++++.................",
				 "........,....+++..............~~~~~~~~~~~~~.........+++++++...................",
				 "............+++...............~~~~~~~~~~~~~...........++......................",
				 "..............................................................................",
				 ".......................................................................++.....",
				 "......................................................................++.++...",
				 ".......................................................................++++...",
				 ".........................................................................++...",
				 "..............................................................................");
		
		List<String> map2 = Arrays.asList(
				"....................................................................",
				"......................................+++...........................",
				".......................................++++.........................",
				"...................................+++++............................",
				"..................~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~...............",
				"..................~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~...............",
				"..................~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~...............",
				"..................~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~...............",
				"............+++...~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~...............",
				".............+++++~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~...............",
				"...............++.~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~.....++........",
				"........,....+++..~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~+++++++........",
				"..................~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~........++.....",
				"..................~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~........++.....",
				"..................~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~........++.....",
				"............................................................++.++...",
				".............................................................++++...",
				"..............................................................++....",
				"....................................................................");
		this.gameMap = new DinoGameMap(groundFactory, map);
		this.gameMap2 = new DinoGameMap(groundFactory, map2);
		
		gameMap.addAdjacentMap(gameMap2, Direction.NORTH);
		gameMap2.addAdjacentMap(gameMap,Direction.SOUTH);
		world.addGameMap(gameMap);
		world.addGameMap(gameMap2);
//		gameMap.at(9, 3).setGround(new Store('S'));
	}
	
	private void addPlayer() {
		Player player = new Player("Player", '@', 100);
		player.addMoney(1000000);
		player.addItemToInventory(new FoodItem(FoodSkill.HERBIVORE, Price.HerbivoreFoodItem.buyValue()));
		player.addItemToInventory(new Corpse("protoceratops corpse", Price.ProtoceratopsCorpse.sellValue()));
		player.addItemToInventory(new DinosaurTag());
		player.addItemToInventory(new Boat());
		world.addPlayer(player, gameMap.at(9, 4));
	}
	
	private void addActorsAndItems() {
		gameMap.at(9, 5).addActor(new Protoceratops());
		gameMap.at(8, 9).addActor(new Protoceratops());
		gameMap.at(8, 14).addActor(new Velociraptor());
		gameMap.at(8, 15).addActor(new Protoceratops());
		gameMap.at(8,16).addActor(new Tyrannosaurus());
		gameMap.at(14,12).addActor(new Pteranodon());
		gameMap.at(20, 14).addActor(new Velociraptor(Maturity.BABY));


		gameMap.at(9, 6).addItem(new Egg(new Protoceratops(Maturity.BABY), Price.ProtoceratopsEgg.buyValue(), Price.ProtoceratopsEgg.sellValue()));

		gameMap.at(24, 18).addItem(new Corpse("protoceratops corpse", Price.ProtoceratopsCorpse.sellValue()));
		gameMap.at(24, 18).addItem(new Corpse("protoceratops corpse", Price.ProtoceratopsCorpse.sellValue()));
		gameMap.at(29, 18).addItem(new Egg(new Plesiosaur(Maturity.BABY), Price.ProtoceratopsEgg.buyValue(), Price.ProtoceratopsEgg.sellValue()));                          
		gameMap.at(24, 18).addItem(new Corpse("protoceratops corpse", Price.ProtoceratopsCorpse.sellValue()));
		gameMap.at(24, 18).addItem(new Corpse("protoceratops corpse", Price.ProtoceratopsCorpse.sellValue()));
		gameMap.at(24, 18).addItem(new Corpse("protoceratops corpse", Price.ProtoceratopsCorpse.sellValue()));
		gameMap.at(24, 18).addItem(new Corpse("protoceratops corpse", Price.ProtoceratopsCorpse.sellValue()));
		
		gameMap.at(30, 12).addActor(new Protoceratops("Protoceratops"));
		gameMap.at(8, 4).addActor(new Protoceratops("Protoceratops"));
	}
	
	private void scenarioTyrannosaurus() { 
		// This is to test Game Won when captive-bred tyrannosaurus reaches adulthood
		gameMap.at(29, 18).addItem(new Egg(new Tyrannosaurus(Maturity.BABY), Price.ProtoceratopsEgg.buyValue(), Price.ProtoceratopsEgg.sellValue()));                          
	}
	
	private void scenarioPteranodon() {
		// This it to see the movements of the Pteranodon. It can move 2 spaces, over land and water
		gameMap.at(29,18).addActor(new Pteranodon());
	}
	
	private void scenarioPlesiosaur() {
		
		// Egg near water
		gameMap.at(29, 18).addItem(new Egg(new Plesiosaur(Maturity.BABY), Price.ProtoceratopsEgg.buyValue(), Price.ProtoceratopsEgg.sellValue()));                          
		gameMap.at(29, 18).addItem(new Egg(new Plesiosaur(Maturity.BABY), Price.ProtoceratopsEgg.buyValue(), Price.ProtoceratopsEgg.sellValue()));                          
		gameMap.at(29, 17).addItem(new Egg(new Plesiosaur(Maturity.BABY), Price.ProtoceratopsEgg.buyValue(), Price.ProtoceratopsEgg.sellValue()));                          

		// Egg not near water
		gameMap.at(24, 18).addItem(new Egg(new Plesiosaur(Maturity.BABY), Price.ProtoceratopsEgg.buyValue(), Price.ProtoceratopsEgg.sellValue()));                          
	}
}
