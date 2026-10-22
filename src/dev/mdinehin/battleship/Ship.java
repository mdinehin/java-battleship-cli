package dev.mdinehin.battleship;

public interface Ship
{
    /**
     * Returns the total number of decks (segments or size) that make up this ship.
     *
     * @return the total deck count of the ship
     */
    int getDeckCount();

    /**
     * Registers a hit on this ship, reducing its remaining intact decks by one.
     */
    void registerHit();

    /**
     * Checks whether this ship has been completely destroyed.
     *
     * @return true if all the ship's decks have been hit, false otherwise
     */
    boolean isSunk();
}
