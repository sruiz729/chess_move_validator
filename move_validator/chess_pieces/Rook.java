package chess_pieces;

public class Rook extends ChessPiece {

    public Rook() {
        super(PieceType.ROOK, Color.WHITE, Column.a, 1);
    }

    public Rook(Color color, Column col, int row) {
        super(PieceType.ROOK, color, col, row);
    }

    @Override
    public boolean verifyMove(Column targetCol, int targetRow) {
        //targetCol = Character.toLowerCase(targetCol);

        int colDistance = Math.abs(targetCol.ordinal() - col.ordinal());
        int rowDistance = Math.abs(targetRow - row);

        return (colDistance == 0 && rowDistance > 0) ||
               (rowDistance == 0 && colDistance > 0);
    }
    public String toString () {
        return color.name()+" "+PieceType.ROOK+" at "+col.name()+", "+row;
    }
}