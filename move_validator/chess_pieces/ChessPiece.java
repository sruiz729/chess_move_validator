package chess_pieces;

//our abstarct base class, all chess piecews inherit from 
public abstract class ChessPiece implements IntFigure{

    protected PieceType piece_name;
    protected Color color;
    protected Column col;  //A to h
    protected int row;   //1 to 8

    //empty constructor
    public ChessPiece(){}

    //-------------------------------
    //constructor
    public ChessPiece(PieceType piece_name, Color color, Column col, int row){
        this.piece_name = piece_name;
        this.color = color;
        this.col = col;
        this.row = row;
    }
    //-----------------------------
    //getters
    public Color getColor(){ 
        return color; 
    }
    public Column getColumn(){ 
        return col; 
    }

    public int getRow(){ 
        return row; 
    }

    public PieceType getPieceName(){ 
        return piece_name; 
    }
    //--------------------------------------
    //setters
    public void setColumn(Column col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    //-------------------------------------
    //our abstract method
    public abstract boolean verifyMove(Column newX, int newY);
    public abstract String toString ();
}
