package passoff.chess;

import chess.ChessGame;
import chess.ChessPiece;

import java.util.Collection;
import java.util.List;

public class ChessPieceTests extends EqualsTestingUtility<ChessPiece> {
    @Override
    protected String getItemsPlural() {
        return "pieces";
    }

    @Override
    protected ChessPiece buildOriginal() {
        return new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING);
    }

    @Override
    protected Collection<ChessPiece> buildAllDifferent() {
        return List.of(
                new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.QUEEN)
        );
    }

}
