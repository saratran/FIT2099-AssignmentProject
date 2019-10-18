package edu.monash.fit2099.interfaces;

/**
 * This interface provides the ability to add methods to Ground, without
 * modifying code in the engine, or downcasting references in the game.
 */
public interface ItemInterface {

	/**
	 * Override this to make Item feedable
	 * @return
	 */
	default boolean isFeedable() {
		return false;
	}
	
	/**
	 * Override this to change Item food value
	 * @return
	 */
	default int getFoodValue() {
		return 0;
	};

	/**
	 * Override this to change Item buy value
	 * @return
	 */
	default int getBuyValue() {
		return 0;
	};
	
	/**
	 * Override this to make item buyable
	 * @return
	 */
	default boolean isBuyable() {
		return false;
	}
	
	/**
	 * Override this to change Item sell value
	 * @return
	 */
	default int getSellValue() {
		return 0;
	};
	
	/**
	 * Override this to make item sellable
	 * @return
	 */
	default boolean isSellable() {
		return false;
	}
	
	default int getFoodPriority() {
		return 2;
	}

}
