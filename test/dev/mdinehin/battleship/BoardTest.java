package dev.mdinehin.battleship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest
{
    @Test
    void shouldSuccessfullyPlaceSubmarine()
    {
        // Arrange
        Board board = new Board();
        // Act
        boolean result = board.addShip(0, 0, new Submarine());
        // Assert
        assertTrue(result);
    }

    @Test
    void shouldThrowExceptionWhenPlacingOutsideBoardRow()
    {
        // Arrange
        Board board = new Board();
        // Act
        boolean result = board.addShip(-1, 0, new Submarine());
        // Assert
        assertFalse(result);
    }

    @Test
    void shouldThrowExceptionWhenPlacingOutsideBoardColumn()
    {
        // Arrange
        Board board = new Board();
        // Act
        boolean result = board.addShip(0, -1, new Submarine());
        // Assert
        assertFalse(result);
    }
}
