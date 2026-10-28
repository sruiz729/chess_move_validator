# chess\_move\_validator



&#x09;A past final lab assignment in a Object-Oriented Programming class. Groups were tasked with combining all the topics learned throughout the semester to create a final iteration of a program that would create a chess piece object in Java and place that piece in a GUI of a chessboard using JOptionPane.



Relevant Object-Oriented Concepts:



* Polymorphism
* Inheritance
* Information Hiding
* Packages
* Abstraction



An interface IntFigure is defined to have a single universal method verifyMove() which each of the different types of chess pieces will have their own implementations for. An abstract class ChessFigure implements IntFigure and declares attributes to be used by all subsequent classes. Each type of chess piece has their logic for verifying a valid move.



Classes and interfaces about the chessboard are also declared. These handle checking if piece's coordinates are within the bounds of the chess board.



Enum types for the color of piece, type of piece, and x coordinate are created since there are only a set amount.



all the above files are enclosed in a package chess\_pieces, which the main file will import.





The main file (named Lab4.java) is where all the GUI is handled. 



1. Save 
2. Compile with javac Lab4.java
3. Run with java Lab4



A window will appear with the GUI. Users can place a piece anywhere, given it is within the bounds of the board. The axes of the board as follows: from left to right a-h, and bottom to top 1-8. Assume that white pieces would begin at the bottom and black pieces at the top. Once valid coordinates, color, and piece type are specified, the piece can be placed. Uniquely, since the pawns can only move in one direction, the program only allows white pawns to move up and black pawns to move down by one tile. 



One last thing to mention is only one piece can verified at a time. Verifying a second piece necessitates clearing the board and repeating the process of adding the new piece to board.



This lab assignment was originally a team effort, with all previous lab assignments being completed by myself and 2 of my classmates. I assisted in writing the code and handling creating reports which included class diagrams. Specifically, I implemented the logic for removing all the pieces when the clearBoard() is called. It admittedly is not a whole lot, is being only one line, but it is important to make sure the entire board is cleared.

