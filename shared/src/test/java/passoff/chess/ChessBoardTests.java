package passoff.chess;

import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessPiece;
import chess.ChessPosition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

public class ChessBoardTests extends EqualsTestingUtility<ChessBoard> {
    public ChessBoardTests() {
        super("ChessBoard", "boards");
    }

    @Override
    protected ChessBoard buildOriginal() {
        var basicBoard = new ChessBoard();
        basicBoard.resetBoard();
        return basicBoard;
    }

    @Override
    protected Collection<ChessBoard> buildAllDifferent() {
        return List.of(
                new ChessBoard() // Empty
        );
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

}
