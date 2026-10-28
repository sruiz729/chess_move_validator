package chess_pieces;

public class ChessBoard implements IntChessBoard {

    public static final int MAX_ROW = 8;
    public static final int MIN_ROW = 1;

    @Override
    public boolean verifyCoordinate(Column x, int y) {
        return x != null && y >= MIN_ROW && y <= MAX_ROW;
    }
}