package game.dinosaur;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import edu.monash.fit2099.interfaces.SpeciesInterface;
import game.Species;

public class DinoFactory {
	private Map<Species, Constructor<? extends SpeciesInterface>> dinoMap = new HashMap<Species, Constructor<? extends SpeciesInterface>>();

	/**
	 * Constructor. Modify this when adding new species.
	 */
	public DinoFactory() {
		createMap(dinoMap, new Protoceratops());
	}

	public Dinosaur newDinosaur(Species species) {
		return (Dinosaur) newSpeciesObject(dinoMap, species);
	}


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

}
