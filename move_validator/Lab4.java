import chess_pieces.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;

public class Lab4 extends JFrame {

    //these are dropdown menus for the user to select values
    private JComboBox<PieceType> pieceBox;
    private JComboBox<chess_pieces.Color> colorBox;
    private JComboBox<Column> startColBox;
    private JComboBox<Column> targetColBox;

    //these are txt fields because the user types the row number
    private JTextField startRowField;
    private JTextField targetRowField;

    //these buttons control what the program does
    private JButton placeButton;
    private JButton moveButton;
    private JButton clearButton;

    //this panel is where the chess board will be shown
    private JPanel boardPanel;
    private JLabel[][] squares; //this 2D array holds all the squares on the board

    //this stores the current piece being used
    private ChessPiece currentPiece;
    private ChessBoard board; //checks if positions are inside the board
    private Set<PieceType> usedPieces; //we used a HashSet so the same piece cannot be selected twice

    public Lab4() {
        
        //creating the board checker and HashSet
        board = new ChessBoard();
        usedPieces = new HashSet<>();

        
        //setting up the GUI window
        setTitle("Chess Move Validator");
        setSize(800, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // opens window in the center
        setLayout(new BorderLayout(10, 10));

        //these methods build the GUI
        createInputPanel();
        createBoardPanel();

        //makes the window visble 
        setVisible(true);
    }

    private void createInputPanel() {
        
        //this panel holds the dropdowns, txt fields, and buttons
        JPanel inputPanel = new JPanel(new GridLayout(4, 4, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Chess Controls"));

        //creatin dropdowns with enum values 
        pieceBox = new JComboBox<>(PieceType.values());
        colorBox = new JComboBox<>(chess_pieces.Color.values());
        startColBox = new JComboBox<>(Column.values());
        targetColBox = new JComboBox<>(Column.values());

        //crreating row input boxes 
        startRowField = new JTextField();
        targetRowField = new JTextField();

        //creatin buttons 
        placeButton = new JButton("Place Piece");
        moveButton = new JButton("Move Piece");
        clearButton = new JButton("Clear Board");

        //we add labels and inputs to the panel 
        inputPanel.add(new JLabel("Piece:"));
        inputPanel.add(pieceBox);

        inputPanel.add(new JLabel("Color:"));
        inputPanel.add(colorBox);

        inputPanel.add(new JLabel("Start Column:"));
        inputPanel.add(startColBox);

        inputPanel.add(new JLabel("Start Row (1-8):"));
        inputPanel.add(startRowField);

        inputPanel.add(new JLabel("Target Column:"));
        inputPanel.add(targetColBox);

        inputPanel.add(new JLabel("Target Row (1-8):"));
        inputPanel.add(targetRowField);

        inputPanel.add(placeButton);
        inputPanel.add(moveButton);
        inputPanel.add(clearButton);

        //placing the input panel at the top of the window 
        add(inputPanel, BorderLayout.NORTH);

        //these tell the buttons what method to run when selected 
        placeButton.addActionListener(e -> placePiece());
        moveButton.addActionListener(e -> movePiece());
        clearButton.addActionListener(e -> clearBoard());
    }

    private void createBoardPanel() {
        
        //creating an 8 by 8 board
        boardPanel = new JPanel(new GridLayout(8, 8));
        boardPanel.setBorder(BorderFactory.createTitledBorder("Chess Board"));

        //each square is a JLabel
        squares = new JLabel[8][8];

        //this loop creates all 64 sqauress 
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {

                JLabel square = new JLabel("", SwingConstants.CENTER);
                square.setOpaque(true);
                square.setFont(new Font("Arial", Font.BOLD, 14));
                square.setBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK));

                //this makes the board alternate colors
                if ((row + col) % 2 == 0) {
                    square.setBackground(java.awt.Color.WHITE);
                } else {
                    square.setBackground(java.awt.Color.LIGHT_GRAY);
                }

                squares[row][col] = square; //ssaving the square in the array
                boardPanel.add(square);
            }
        }

        add(boardPanel, BorderLayout.CENTER); //placing the board in the center of the window
    }

    private void placePiece() {

        //assignment states board must be cleared before placing another piece
        if (currentPiece != null) {
            JOptionPane.showMessageDialog(this, "Clear the board before placing a new piece.");
            return;
        }

        //getting the values the user selected
        PieceType selectedPiece = (PieceType) pieceBox.getSelectedItem();
        chess_pieces.Color selectedColor = (chess_pieces.Color) colorBox.getSelectedItem();
        Column startCol = (Column) startColBox.getSelectedItem();

        int startRow;

        //check if the row is actually a number
        try {
            startRow = Integer.parseInt(startRowField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Start row must be a number from 1 to 8.");
            return;
        }

        //check if the starting position is valid
        if (!board.verifyCoordinate(startCol, startRow)) {
            JOptionPane.showMessageDialog(this, "Invalid starting position. Row must be between 1 and 8.");
            return;
        }

        //tets our HashSet, prebents selecting the same piece twice
        if (usedPieces.contains(selectedPiece)) {
            JOptionPane.showMessageDialog(this, "This piece has already been used. Pick a different piece.");
            return;
        }

        //create the correct object based on what the user selects
        switch (selectedPiece) {
            case PAWN:
                currentPiece = new Pawn(selectedColor, startCol, startRow);
                break;
            case ROOK:
                currentPiece = new Rook(selectedColor, startCol, startRow);
                break;
            case KNIGHT:
                currentPiece = new Knight(selectedColor, startCol, startRow);
                break;
            case BISHOP:
                currentPiece = new Bishop(selectedColor, startCol, startRow);
                break;
            case QUEEN:
                currentPiece = new Queen(selectedColor, startCol, startRow);
                break;
            case KING:
                currentPiece = new King(selectedColor, startCol, startRow);
                break;
        }

        //add piece type to the HashSet so it cannot be used again
        usedPieces.add(selectedPiece);
        drawPiece(); //show piece on the board 

        JOptionPane.showMessageDialog(this, currentPiece.toString() + " placed on the board.");
    }

    private void movePiece() {

        //user cannot move anything until a piece is placed
        if (currentPiece == null) {
            JOptionPane.showMessageDialog(this, "Place a piece first.");
            return;
        }

        //get target position 
        Column targetCol = (Column) targetColBox.getSelectedItem();

        int targetRow;

        //check if target row is a num
        try {
            targetRow = Integer.parseInt(targetRowField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Target row must be a number from 1 to 8.");
            return;
        }

        //chek if the target pos is inside the board 
        if (!board.verifyCoordinate(targetCol, targetRow)) {
            JOptionPane.showMessageDialog(this, "Invalid target position. Row must be between 1 and 8.");
            return;
        }

        //this is where polymorphism happens
        //each piece has its own verifyMove logic
        if (currentPiece.verifyMove(targetCol, targetRow)) {
            
            //if valid update the piece pos
            currentPiece.setColumn(targetCol);
            currentPiece.setRow(targetRow);

            drawPiece(); //redraw piece in the new spot 

            JOptionPane.showMessageDialog(this, "Valid move. Piece moved.");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid move. Piece stays in the same position.");
        }
    }

    private void drawPiece() {
        
        //clear board before drawing the current piece
        clearBoardOnly();

        
        //convert chess row to GUI row
        //chess row 1 is at the bottom but GUI row 0 is at the top
        int guiRow = 8 - currentPiece.getRow();
        
        //ordinal gives the col index a=0, b=1, c=2, etc.
        int guiCol = currentPiece.getColumn().ordinal();

        
        //this is what shows inside the square, like W-PAWN or B-KING
        String text = currentPiece.getColor().name().charAt(0) + "-" +
                      currentPiece.getPieceName().name();

        squares[guiRow][guiCol].setText(text);
    }

    private void clearBoard() {
        
        //clear the visual board
        clearBoardOnly();
        currentPiece = null; //remove the current active piece 
        usedPieces.removeAll(usedPieces);

        JOptionPane.showMessageDialog(this, "Board cleared. You may select another piece.");
    }

    private void clearBoardOnly() {
        
        //loops thru every square and removes the txt
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                squares[row][col].setText("");
            }
        }
    }

    public static void main(String[] args) {
        new Lab4(); //start program 
    }
}