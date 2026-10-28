package chess_pieces;

public interface IntBishop extends IntFigure {
    boolean verifyBishopMove(Column newX, int newY);
    String toString();
}