package edu.monash.fit2099.interfaces;

import game.Species;
import game.dinosaur.DinoFactory;

public interface SpeciesInterface {
	public DinoFactory dinoFactory = new DinoFactory();

	public Species getSpecies();
}
