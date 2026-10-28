package chess_pieces;

//inherit from chees piece 
public class Pawn extends ChessPiece{

    //empty constructor 
    public Pawn(){
        super(PieceType.PAWN, Color.WHITE, Column.a, 2);
    }

    //seting color and pos col/row
    public Pawn(Color color, Column col, int row) {
        super(PieceType.PAWN, color, col, row);
    }

    //we overide the abstract method from ChessPiece
    @Override
    public boolean verifyMove(Column targetCol, int targetRow) {
        

        //must stay in same col
        if (targetCol.ordinal() != col.ordinal()){
            return false;
        } 

        if (color == Color.WHITE && targetRow == row + 1){
            return true; //we move forward 
        }
        
        if (color == Color.BLACK && targetRow == row - 1){
            return true; //we move downward so we decrease 
        }
        
        return false;
    }

    public String toString () {
        return color.name()+" "+PieceType.PAWN+" at "+col.name()+", "+row;
    }
}
