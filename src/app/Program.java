package app;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();	
		List<ChessPiece> captured = new ArrayList<>();
		
		while (!chessMatch.getCheckMate()) {
			try {
				UI.clearScreen();
				UI.printMatch(chessMatch, captured);
				System.out.println();
				System.out.print("digite a posi��o: ");
				ChessPosition source = UI.readChessPosition(sc);
				
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves);
				System.out.println();
				System.out.print("digite o destino: ");
				ChessPosition target = UI.readChessPosition(sc);
				
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
				
				if (capturedPiece != null) {
					captured.add(capturedPiece);
				}
				if (chessMatch.getPromoted() != null) {
					System.out.print("Digite a letra a ser promovida = (B/C/R/Q)? ");
					String type = sc.nextLine();
					chessMatch.replacePromotedPiece(type);
				}
			}	
			
			catch (ChessException e) {
				System.out.print(e.getMessage());
				sc.hasNextLine();
			}
			
			catch (InputMismatchException e) {
				System.out.print(e.getMessage());
				sc.hasNextLine(); 
			}
		}
		UI.clearScreen();
		UI.printMatch(chessMatch, captured);
	}
}

