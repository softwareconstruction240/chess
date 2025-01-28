package passoff.chess;

import chess.ChessGame;
import chess.ChessPiece;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessPieceTests {
    private ChessPiece original;
    private ChessPiece equal;
    private ChessPiece different;
    @BeforeEach
    public void setUp() {
        original = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING);
        equal = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING);
        different = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.QUEEN);
    }

    @Test
    @DisplayName("Equals Testing")
    public void equalsTest() {
        Assertions.assertEquals(original, equal, "equals returned false for equal pieces");
        Assertions.assertNotEquals(original, different, "equals returned true for different pieces");
    }

    @Test
    @DisplayName("HashCode Testing")
    public void hashTest() {
        Assertions.assertEquals(original.hashCode(), equal.hashCode(),
                "hashCode returned different values for equal pieces");
        Assertions.assertNotEquals(original.hashCode(), different.hashCode(),
                "hashCode returned the same value for different pieces");
    }

    @Test
    @DisplayName("Combined Testing")
    public void hashSetTest() {
        // FIXME: Add thorough testing for equality differences
    }
}
