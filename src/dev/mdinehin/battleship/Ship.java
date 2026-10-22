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

    /**
     * Assigns a specific board cell to a designated deck segment of this ship.
     *
     * @param cell       the board cell where this deck segment is being positioned
     * @param deckNumber the index of the ship's deck (0-indexed, ranging from 0 to the total deck count minus one)
     * @throws IllegalArgumentException if the provided cell is null or the deckNumber is out of valid bounds
     */
    void assignCell(Cell cell, int deckNumber);
}
