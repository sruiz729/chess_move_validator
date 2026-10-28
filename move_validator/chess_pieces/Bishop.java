package chess_pieces;

//this class represents the Bishop piece
//i made it extend ChessPiece so it works with all the other pieces
//and we can use polymorphism (verifyMove like everything else)
public class Bishop extends ChessPiece implements IntBishop {

    //defalt construcstor 
    public Bishop() {
        
        //calling parent constructor to set default values
        super(PieceType.BISHOP, Color.WHITE, Column.c, 1);
    }

    //main construcstor 
    public Bishop(Color color, Column col, int row) {
        super(PieceType.BISHOP, color, col, row);
    }

    //this method is required because ChessPiece has an abstract verifyMove()
    //instead of rewriting everything i just call my bishop logic below
    @Override
    public boolean verifyMove(Column targetCol, int targetRow) {
        return verifyBishopMove(targetCol, targetRow);
    }

    //this is where the bishop movement logic is
    @Override
    public boolean verifyBishopMove(Column targetCol, int targetRow) {
        
        //checkin how far it moves left/right
        int colDistance = Math.abs(targetCol.ordinal() - col.ordinal());
        //checkin how far it moves up/down
        int rowDistance = Math.abs(targetRow - row);

        //for bishop, both distances have to be the same (diagonal move)
        //also making sure it actually moves somewhere and not staying still
        return colDistance == rowDistance && colDistance > 0;
    }
    //this just prints the piece nicely
    @Override
    public String toString() {
        return color.name() + " " + PieceType.BISHOP + " at " + col.name() + ", " + row;
    }
}