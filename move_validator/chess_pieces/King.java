package chess_pieces;

public class King extends Queen {

    public King() {
        super(Color.WHITE, Column.e, 1);
        this.piece_name = PieceType.KING;
    }

    public King(Color color, Column col, int row) {
        super(color, col, row);
        this.piece_name = PieceType.KING;
    }

    @Override
    public boolean verifyMove(Column targetCol, int targetRow) {
        //targetCol = Character.toLowerCase(targetCol);

        int colSteps = Math.abs(targetCol.ordinal() - col.ordinal());
        int rowSteps = Math.abs(targetRow - row);

        return (colSteps <= 1 && rowSteps <= 1) && !(colSteps == 0 && rowSteps == 0);
    }
    public String toString () {
        return color.name()+" "+PieceType.KING+" at "+col.name()+", "+row;
    }
}
