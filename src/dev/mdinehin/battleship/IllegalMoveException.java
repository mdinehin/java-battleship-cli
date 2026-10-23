package dev.mdinehin.battleship;

public class IllegalMoveException extends RuntimeException
{
    public IllegalMoveException(String message)
    {
        super(message);
    }
}
