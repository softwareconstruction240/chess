package passoff.chess;

import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessPiece;
import chess.ChessPosition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class ChessBoardTests extends EqualsTestingUtility<ChessBoard> {
    public ChessBoardTests() {
        super("ChessBoard", "boards");
    }

    @Test
    @DisplayName("Construct Empty ChessBoard")
    public void constructChessBoard() {
        ChessBoard board = new ChessBoard();

        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 8; col++) {
                Assertions.assertNull(
                        board.getPiece(new ChessPosition(row, col)),
                        "Immediately upon construction, a ChessBoard should be empty."
                );
            }
        }

    }

    @Test
    @DisplayName("Add and Get Piece")
    public void getAddPiece() {
        ChessPosition position = new ChessPosition(4, 4);
        ChessPiece piece = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP);

        var board = new ChessBoard();
        board.addPiece(position, piece);

        ChessPiece foundPiece = board.getPiece(position);

        Assertions.assertEquals(piece.getPieceType(), foundPiece.getPieceType(),
                "ChessPiece returned by getPiece had the wrong piece type");
        Assertions.assertEquals(piece.getTeamColor(), foundPiece.getTeamColor(),
                "ChessPiece returned by getPiece had the wrong team color");
    }

    @Test
    @DisplayName("Reset Board")
    public void defaultGameBoard() {
        var expectedBoard = TestUtilities.defaultBoard();

        var actualBoard = new ChessBoard();
        actualBoard.resetBoard();

        Assertions.assertEquals(expectedBoard, actualBoard);
    }

    @Override
    protected ChessBoard buildOriginal() {
        var basicBoard = new ChessBoard();
        basicBoard.resetBoard();
        return basicBoard;
    }

    @Override
    protected Collection<ChessBoard> buildAllDifferent() {
        List<ChessBoard> differentBoards = new ArrayList<>();

        differentBoards.add(new ChessBoard()); // An empty board

        // Generate boards each with one random piece added an edge or the primary diagonal
        /*
        Each 'X' is a different default board that will be tested.

                |X| | | | | | |X|
                |X| | | | | |X| |
                |X| | | | |X| | |
                |X| | | |X| | | |
                |X| | |X| | | | |
                |X| |X| | | | | |
                |X|X| | | | | | |
                |X|X|X|X|X|X|X|X|
         */
        Random random = new Random();
        for (int i = 1; i <= 8; i++) {
            differentBoards.add(createBoardWithRandomPieceAddedInPosition(i, i, random));
            if (i != 1) {
                differentBoards.add(createBoardWithRandomPieceAddedInPosition(1, i, random));
                differentBoards.add(createBoardWithRandomPieceAddedInPosition(i, 1, random));
            }
        }

        return differentBoards;
    }

    private ChessBoard createBoardWithRandomPieceAddedInPosition(int row, int col, Random random) {
        var board = new ChessBoard();

        var pieceTypes = List.of(ChessPiece.PieceType.values());
        var randomPieceType = pieceTypes.get(random.nextInt(pieceTypes.size()));
        var randomTeamColor = random.nextBoolean() ? ChessGame.TeamColor.WHITE : ChessGame.TeamColor.BLACK;
        var piece = new ChessPiece(randomTeamColor, randomPieceType);

        var position = new ChessPosition(row, col);
        board.addPiece(position, piece);

        return board;
    }

}
