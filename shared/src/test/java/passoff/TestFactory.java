package passoff;

import chess.*;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Assertions;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Used for testing your code
 */
public class TestFactory {
    //Chess tests utilities
    // ------------------------------------------------------------------------------------------------------------------
    static public void validateMoves(String boardText, ChessPosition startPosition, int[][] endPositions) {
        var board = loadBoard(boardText);
        var testPiece = board.getPiece(startPosition);
        var validMoves = loadMoves(startPosition, endPositions);
        validateMoves(board, testPiece, startPosition, validMoves);
    }

    static public void validateMoves(ChessBoard board, ChessPiece testPiece, ChessPosition startPosition, Set<ChessMove> validMoves) {
        var pieceMoves = new HashSet<>(testPiece.pieceMoves(board, startPosition));
        assertCollectionsEquals(validMoves, pieceMoves, "Wrong moves");
    }

    static public <T> void assertCollectionsEquals(Collection<T> first, Collection<T> second, String message) {
        Assertions.assertEquals(new HashSet<>(first), new HashSet<>(second), message);
        Assertions.assertEquals(first.size(), second.size(), "Collections not the same size");
    }

    final static Map<Character, ChessPiece.PieceType> CHAR_TO_TYPE_MAP = Map.of(
            'p', ChessPiece.PieceType.PAWN,
            'n', ChessPiece.PieceType.KNIGHT,
            'r', ChessPiece.PieceType.ROOK,
            'q', ChessPiece.PieceType.QUEEN,
            'k', ChessPiece.PieceType.KING,
            'b', ChessPiece.PieceType.BISHOP);

    public static ChessBoard loadBoard(String boardText) {
        var board = new ChessBoard();
        int row = 8;
        int column = 1;
        for (var c : boardText.toCharArray()) {
            switch (c) {
                case '\n' -> {
                    column = 1;
                    row--;
                }
                case ' ' -> column++;
                case '|' -> {
                }
                default -> {
                    ChessGame.TeamColor color = Character.isLowerCase(c) ? ChessGame.TeamColor.BLACK
                            : ChessGame.TeamColor.WHITE;
                    var type = CHAR_TO_TYPE_MAP.get(Character.toLowerCase(c));
                    var position = new ChessPosition(row, column);
                    var piece = new ChessPiece(color, type);
                    board.addPiece(position, piece);
                    column++;
                }
            }
        }
        return board;
    }

    public static Set<ChessMove> loadMoves(ChessPosition startPosition, int[][] endPositions) {
        var validMoves = new HashSet<ChessMove>();
        for (var endPosition : endPositions) {
            validMoves.add(new ChessMove(startPosition,
                    new ChessPosition(endPosition[0], endPosition[1]), null));
        }
        return validMoves;
    }

    public static void assertMoves(ChessGame game, Set<ChessMove> validMoves, ChessPosition position) {
        var generatedMoves = game.validMoves(position);
        var actualMoves = new HashSet<>(generatedMoves);
        Assertions.assertEquals(generatedMoves.size(), actualMoves.size(), "Duplicate move");
        Assertions.assertEquals(validMoves, actualMoves,
                "ChessGame validMoves did not return the correct moves");
    }
    // ------------------------------------------------------------------------------------------------------------------


    // Websocket Tests
    // ------------------------------------------------------------------------------------------------------------------
    public static Long getMessageTime() {
        /*
         * Changing this will change how long tests will wait for the server to send
         * messages.
         * 3000 Milliseconds (3 seconds) will be enough for most computers. Feel free to
         * change as you see fit,
         * just know increasing it can make tests take longer to run.
         * (On the flip side, if you've got a good computer feel free to decrease it)
         */
        return 3000L;
    }

    public static GsonBuilder getGsonBuilder() {
        /*                  **NOT APPLICABLE TO MOST STUDENTS**
         * If you would like to change the way the web socket test cases serialize
         * or deserialize chess objects like ChessMove, you may add type adapters here.
         */
        GsonBuilder builder = new GsonBuilder();
        // builder.registerTypeAdapter(ChessMove.class, /*type adapter or json serializer */);
        return builder;
    }
    // ------------------------------------------------------------------------------------------------------------------
}