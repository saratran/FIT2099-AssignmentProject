package game.dinosaur;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import edu.monash.fit2099.interfaces.SpeciesInterface;
import game.Species;
import game.item.Corpse;
import game.item.Egg;
import game.item.ProtoceratopsEgg;

public class DinoFactory {
	private Map<Species, Constructor<? extends SpeciesInterface>> dinoMap = new HashMap<Species, Constructor<? extends SpeciesInterface>>();
	private Map<Species, Constructor<? extends SpeciesInterface>> eggMap = new HashMap<Species, Constructor<? extends SpeciesInterface>>();
	private Map<Species, Constructor<? extends SpeciesInterface>> corpseMap = new HashMap<Species, Constructor<? extends SpeciesInterface>>();

	/**
	 * Constructor. Modify this when adding new species.
	 */
	public DinoFactory() {
		createMap(dinoMap, new Protoceratops());
		createMap(eggMap, new ProtoceratopsEgg());
	}

	public Dinosaur newDinosaur(Species species) {
		return (Dinosaur) newSpeciesObject(dinoMap, species);
	}
	
	public Egg newEgg(Species species) {
		return (Egg) newSpeciesObject(eggMap, species);
	}
	
	public Corpse newCorpse(Species species) {
		return (Corpse) newSpeciesObject(eggMap, species);
	}
	
	private SpeciesInterface newSpeciesObject(Map<Species, Constructor<? extends SpeciesInterface>> map, Species species) {
		try {
			return map.get(species).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	private void createMap(Map<Species, Constructor<? extends SpeciesInterface>> map, SpeciesInterface...objects ) {
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
