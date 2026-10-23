package dev.mdinehin.battleship;

import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest
{
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void shouldUpdateCellStateToShipWhenShipPlaced() throws Exception
    {
        board.placeShip(new Submarine(), 0, 0);

        Cell cell = board.getCell(0, 0);
        assertEquals(CellState.SHIP, cell.getState());
    }

    @Test
    void shouldThrowExceptionWhenPlacingFiveSubmarines() throws Exception
    {
        board.placeShip(new Submarine(), 0, 0);
        board.placeShip(new Submarine(), 2, 0);
        board.placeShip(new Submarine(), 4, 0);
        board.placeShip(new Submarine(), 6, 0);

        IllegalPlacementException exception = assertThrows(IllegalPlacementException.class, () -> {
            board.placeShip(new Submarine(), 8, 0);
        });

        assertEquals(
            "The maximum number of ships of this type has been reached [Deck count: 1]", exception.getMessage()
        );
    }

    @Test
    void shouldThrowExceptionWhenPlacingTwoBattleships() throws Exception
    {
        board.placeShip(new Battleship(Orientation.HORIZONTAL), 0, 0);

        IllegalPlacementException exception = assertThrows(IllegalPlacementException.class, () -> {
            board.placeShip(new Battleship(Orientation.HORIZONTAL), 2, 0);
        });

        assertEquals(
            "The maximum number of ships of this type has been reached [Deck count: 4]", exception.getMessage()
        );
    }

    @Test
    void shouldThrowExceptionWhenPlacingOutsideBoardRow()
    {
        IllegalPlacementException exception = assertThrows(IllegalPlacementException.class, () -> {
            board.placeShip(new Submarine(), -1, 0);
        });

        assertEquals("Cannot place ship outside the board boundaries", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenPlacingOutsideBoardColumn()
    {
        IllegalPlacementException exception = assertThrows(IllegalPlacementException.class, () -> {
            board.placeShip(new Submarine(), 0, 10);
        });

        assertEquals("Cannot place ship outside the board boundaries", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenShipExtendsOutsideBoardHorizontally()
    {
        IllegalPlacementException exception = assertThrows(IllegalPlacementException.class, () -> {
            board.placeShip(new Destroyer(Orientation.HORIZONTAL), 0, 9);
        });

        assertEquals("Cannot place ship outside the board boundaries", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenShipExtendsOutsideBoardVertically()
    {
        IllegalPlacementException exception = assertThrows(IllegalPlacementException.class, () -> {
            board.placeShip(new Battleship(Orientation.VERTICAL), 7, 0);
        });

        assertEquals("Cannot place ship outside the board boundaries", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenShipOverlapsExistingShipsOrShipIsAdjacentToAnotherShip()
    {
        IllegalPlacementException exception = assertThrows(IllegalPlacementException.class, () -> {
            board.placeShip(new Submarine(), 0, 0);
            board.placeShip(new Submarine(), 1, 1);
        });

        assertEquals(
            "Ship placement overlaps existing ships or violates the no-touching rule", exception.getMessage()
        );
    }
}
