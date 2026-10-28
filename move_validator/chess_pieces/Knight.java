package chess_pieces;

public class Knight extends ChessPiece {

    public Knight() {
        super(PieceType.KNIGHT, Color.WHITE, Column.b, 1);
    }

    public Knight(Color color, Column col, int row) {
        super(PieceType.KNIGHT, color, col, row);
    }

    @Override
    public boolean verifyMove(Column targetCol, int targetRow) {
        //targetCol = Character.toLowerCase(targetCol);

        int colDiff = Math.abs(targetCol.ordinal() - col.ordinal());
        int rowDiff = Math.abs(targetRow - row);

        return (colDiff == 2 && rowDiff == 1) || (colDiff == 1 && rowDiff == 2);
    }
    public String toString () {
        return color.name()+" "+PieceType.KNIGHT+" at "+col.name()+", "+row;
    }
}
