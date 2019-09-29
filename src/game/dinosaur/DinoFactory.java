package game.dinosaur;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import edu.monash.fit2099.interfaces.SpeciesInterface;
import game.Species;
import game.item.Corpse;
import game.item.Egg;

public class DinoFactory {
	private Map<Species, Constructor<? extends SpeciesInterface>> dinoMap = new HashMap<Species, Constructor<? extends SpeciesInterface>>();
//	private Map<Species, Constructor<? extends SpeciesInterface>> eggMap = new HashMap<Species, Constructor<? extends SpeciesInterface>>();
//	private Map<Species, Constructor<? extends SpeciesInterface>> corpseMap = new HashMap<Species, Constructor<? extends SpeciesInterface>>();

	/**
	 * Constructor. Modify this when adding new species.
	 */
	public DinoFactory() {
		createMap(dinoMap, new Protoceratops());
//		createSpeciesMap(eggMap, new Egg(Species.PROTOCERATOPS));
	}

	public Dinosaur newDinosaur(Species species) {
		return (Dinosaur) newSpeciesObject(dinoMap, species);
	}

//	public Egg newEgg(Species species) {
//		try {
//			return (Egg) newConstructor(eggMap, species).newInstance(species);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	public Corpse newCorpse(Species species) {
//		try {
//			return (Corpse) newConstructor(corpseMap, species).newInstance(species);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;	}

//	private Constructor<? extends SpeciesInterface> newConstructor(
//			Map<Species, Constructor<? extends SpeciesInterface>> map, Species species) {
//		return map.get(species);
//	}

	private SpeciesInterface newSpeciesObject(Map<Species, Constructor<? extends SpeciesInterface>> map,
			Species species) {
		try {
			return map.get(species).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Create mapping for objects with empty constructor
	 * @param map
	 * @param objects 
	 */
	private void createMap(Map<Species, Constructor<? extends SpeciesInterface>> map, SpeciesInterface... objects) {
		for (SpeciesInterface object : objects) {
			try {
				Class<? extends SpeciesInterface> cls = object.getClass();
				Constructor<? extends SpeciesInterface> constructor;
				constructor = cls.getConstructor();
				map.put(object.getSpecies(), constructor);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
//	
//	/**
//	 * Create mapping for objects with constructor (Species species)
//	 * @param map
//	 * @param objects
//	 */
//	private void createSpeciesMap(Map<Species, Constructor<? extends SpeciesInterface>> map, SpeciesInterface... objects) {
//		// TODO: better naming :/
//		for (SpeciesInterface object : objects) {
//			try {
//				Class<? extends SpeciesInterface> cls = object.getClass();
//				Constructor<? extends SpeciesInterface> constructor;
//				constructor = cls.getConstructor(Species.class);
//				map.put(object.getSpecies(), constructor);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
}
