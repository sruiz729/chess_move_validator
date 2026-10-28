package chess_pieces;

public class Queen extends Rook implements IntBishop {

    public Queen() {
        super(Color.WHITE, Column.d, 1);
        this.piece_name = PieceType.QUEEN;
    }

    public Queen(Color color, Column col, int row) {
        super(color, col, row);
        this.piece_name = PieceType.QUEEN;
    }

    @Override
    public boolean verifyMove(Column targetCol, int targetRow) {
        //targetCol = Character.toLowerCase(targetCol);

        int colDiff = Math.abs(targetCol.ordinal() - col.ordinal());
        int rowDiff = Math.abs(targetRow - row);

        boolean straight = (colDiff == 0 && rowDiff > 0) || (rowDiff == 0 && colDiff > 0);
        boolean diagonal = (colDiff == rowDiff && colDiff > 0);

        return straight || diagonal;
    }
    public boolean verifyBishopMove (Column targetCol, int targetRow) {
        return verifyMove(targetCol, targetRow);
    }
    public String toString () {
        return color.name()+" "+PieceType.QUEEN+" at "+col.name()+", "+row;
    }
}
