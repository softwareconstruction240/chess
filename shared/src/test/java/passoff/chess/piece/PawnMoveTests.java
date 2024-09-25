package passoff.chess.piece;

import chess.ChessMove;
import chess.ChessPiece;
import chess.ChessPosition;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static passoff.chess.TestUtilities.loadBoard;
import static passoff.chess.TestUtilities.validateMoves;

public class PawnMoveTests {

    @Test
    public void pawnMiddleOfBoardWhite() {
        validateMoves("""
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | |P| | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        """,
                new ChessPosition(4, 4),
                new int[][]{{5, 4}}
        );
    }

    @Test
    public void pawnMiddleOfBoardBlack() {
        validateMoves("""
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | |p| | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        """,
                new ChessPosition(4, 4),
                new int[][]{{3, 4}}
        );
    }


    @Test
    public void pawnInitialMoveWhite() {
        validateMoves("""
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | |P| | | |
                        | | | | | | | | |
                        """,
                new ChessPosition(2, 5),
                new int[][]{{3, 5}, {4, 5}}
        );
    }

    @Test
    public void pawnInitialMoveBlack() {
        validateMoves("""
                        | | | | | | | | |
                        | | |p| | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        """,
                new ChessPosition(7, 3),
                new int[][]{{6, 3}, {5, 3}}
        );
    }


    @Test
    public void pawnPromotionWhite() {
        validatePromotion("""
                        | | | | | | | | |
                        | | |P| | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        """,
                new ChessPosition(7, 3),
                new int[][]{{8, 3}}
        );
    }


    @Test
    public void edgePromotionBlack() {
        validatePromotion("""
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | |p| | | | | |
                        | | | | | | | | |
                        """,
                new ChessPosition(2, 3),
                new int[][]{{1, 3}}
        );
    }


    @Test
    public void pawnPromotionCapture() {
        validatePromotion("""
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | |p| | | | | | |
                        |N| | | | | | | |
                        """,
                new ChessPosition(2, 2),
                new int[][]{{1, 1}, {1, 2}}
        );
    }


    @Test
    public void pawnAdvanceBlockedWhite() {
        validateMoves("""
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | |n| | | | |
                        | | | |P| | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        """,
                new ChessPosition(4, 4),
                new int[][]{}
        );
    }

    @Test
    public void pawnAdvanceBlockedBlack() {
        validateMoves("""
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | |p| | | | |
                        | | | |r| | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        """,
                new ChessPosition(5, 4),
                new int[][]{}
        );
    }


    @Test
    public void pawnAdvanceBlockedDoubleMoveWhite() {
        validateMoves("""
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | |p| |
                        | | | | | | | | |
                        | | | | | | |P| |
                        | | | | | | | | |
                        """,
                new ChessPosition(2, 7),
                new int[][]{{3, 7}}
        );
    }

    @Test
    public void pawnAdvanceBlockedDoubleMoveBlack() {
        validateMoves("""
                        | | | | | | | | |
                        | | |p| | | | | |
                        | | |p| | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        """,
                new ChessPosition(7, 3),
                new int[][]{}
        );
    }


    @Test
    public void pawnCaptureWhite() {
        validateMoves("""
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | |r| |N| | | |
                        | | | |P| | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        """,
                new ChessPosition(4, 4),
                new int[][]{{5, 3}, {5, 4}}
        );
    }

    @Test
    public void pawnCaptureBlack() {
        validateMoves("""
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | |p| | | | |
                        | | | |n|R| | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        """,
                new ChessPosition(4, 4),
                new int[][]{{3, 5}}
        );
    }

    @Test
    public void pawnMoveFromEdgeWhite() {
        validateMoves("""
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | |P|
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        """,
                new ChessPosition(4, 8),
                new int[][]{{5, 8}}
        );
    }

    @Test
    public void pawnMoveFromEdgeBlack() {
        validateMoves("""
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | |p|
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        """,
                new ChessPosition(5, 8),
                new int[][]{{4, 8}}
        );
    }

    @Test
    public void pawnCaptureFromEdgeWhite() {
        validateMoves("""
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | |r| |
                        | | | | | | | |P|
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        """,
                new ChessPosition(4, 8),
                new int[][]{{5, 8}, {5, 7}}
        );
    }

    @Test
    public void pawnCaptureFromEdgeBlack() {
        validateMoves("""
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | |p|
                        | | | | | | |R| |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        """,
                new ChessPosition(5, 8),
                new int[][]{{4, 8}, {4, 7}}
        );
    }

    @Test
    public void pawnCaptureFromStartWhite() {
        validateMoves("""
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | |r| | | | | |
                        | | |r| | | | | |
                        | | | |P| | | | |
                        | | | | | | | | |
                        """,
                new ChessPosition(2, 4),
                new int[][]{{3, 3}, {3, 4}, {4, 4}}
        );
    }

    @Test
    public void pawnCaptureFromStartBlack() {
        validateMoves("""
                        | | | | | | | | |
                        | | | |p| | | | |
                        | | |R| | | | | |
                        | | |R| | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        """,
                new ChessPosition(7, 4),
                new int[][]{{6, 4}, {5, 4}, {6, 3}}
        );
    }

    @Test
    public void captureAndPromoteWhite() {
        validatePromotion("""
                        | | |r| | | | | |
                        | | | |P| | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        """,
                new ChessPosition(7, 4),
                new int[][]{{8, 3}, {8, 4}}
        );
    }

    @Test
    public void captureAndPromoteBlack() {
        validatePromotion("""
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | |p| | | | |
                        | | |R| | | | | |
                        """,
                new ChessPosition(2, 4),
                new int[][]{{1, 3}, {1, 4}}
        );
    }

    @Test
    public void pawnCannotCaptureBackwardWhite() {
        validateMoves("""
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | |P| | | | | |
                        | | |r|r| | | | |
                        | | | | | | | | |
                        """,
                new ChessPosition(3, 3),
                new int[][]{{4, 3}}
        );
    }

    @Test
    public void pawnCannotCaptureBackwardBlack() {
        validateMoves("""
                        | | | | | | | | |
                        | | |R|R| | | | |
                        | | |p| | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        | | | | | | | | |
                        """,
                new ChessPosition(6, 3),
                new int[][]{{5, 3}}
        );
    }


    private void validatePromotion(String boardText, ChessPosition startingPosition, int[][] endPositions) {
        var board = loadBoard(boardText);
        var testPiece = board.getPiece(startingPosition);
        var validMoves = new HashSet<ChessMove>();
        for (var endPosition : endPositions) {
            var end = new ChessPosition(endPosition[0], endPosition[1]);
            validMoves.add(new ChessMove(startingPosition, end, ChessPiece.PieceType.QUEEN));
            validMoves.add(new ChessMove(startingPosition, end, ChessPiece.PieceType.BISHOP));
            validMoves.add(new ChessMove(startingPosition, end, ChessPiece.PieceType.ROOK));
            validMoves.add(new ChessMove(startingPosition, end, ChessPiece.PieceType.KNIGHT));
        }

        validateMoves(board, testPiece, startingPosition, validMoves);
    }

}
