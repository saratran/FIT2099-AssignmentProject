package edu.monash.fit2099.interfaces;

/**
 * This interface provides the ability to add methods to Ground, without
 * modifying code in the engine, or downcasting references in the game.
 */
public interface ItemInterface {

	default int getFoodValue() {
		return 0;
	}

	default int getBuyValue() {
		return 0;
	}

	default int getSellValue() {
		return 0;
	}
}
