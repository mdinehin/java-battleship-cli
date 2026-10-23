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
        board.placeShip(0, 0, new Submarine());

        Cell cell = board.getCell(0, 0);
        assertEquals(CellState.SHIP, cell.getState());
    }

    @Test
    void shouldThrowExceptionWhenPlacingFiveSubmarines() throws Exception
    {
        board.placeShip(1, 1, new Submarine());
        board.placeShip(2, 2, new Submarine());
        board.placeShip(3, 3, new Submarine());
        board.placeShip(4, 4, new Submarine());

        IllegalPlacementException exception = assertThrows(IllegalPlacementException.class, () -> {
            board.placeShip(5, 5, new Submarine());
        });

        assertEquals("The maximum number of ships of this type has been reached [Deck count: 1]", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenPlacingTwoBattleships() throws Exception
    {
        board.placeShip(1, 1, new Battleship(Orientation.HORIZONTAL));

        IllegalPlacementException exception = assertThrows(IllegalPlacementException.class, () -> {
            board.placeShip(2, 2, new Battleship(Orientation.HORIZONTAL));
        });

        assertEquals("The maximum number of ships of this type has been reached [Deck count: 4]", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenPlacingOutsideBoardRow()
    {
        IllegalPlacementException exception = assertThrows(IllegalPlacementException.class, () -> {
            board.placeShip(-1, 0, new Submarine());
        });

        assertEquals("Cannot place ship outside the board boundaries", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenPlacingOutsideBoardColumn()
    {
        IllegalPlacementException exception = assertThrows(IllegalPlacementException.class, () -> {
            board.placeShip(0, 10, new Submarine());
        });

        assertEquals("Cannot place ship outside the board boundaries", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenShipExtendsOutsideBoardHorizontally()
    {
        IllegalPlacementException exception = assertThrows(IllegalPlacementException.class, () -> {
            board.placeShip(0, 9, new Destroyer(Orientation.HORIZONTAL));
        });

        assertEquals("Cannot place ship outside the board boundaries", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenShipExtendsOutsideBoardVertically()
    {
        IllegalPlacementException exception = assertThrows(IllegalPlacementException.class, () -> {
            board.placeShip(7, 0, new Battleship(Orientation.VERTICAL));
        });

        assertEquals("Cannot place ship outside the board boundaries", exception.getMessage());
    }
}
