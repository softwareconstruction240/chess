package passoff.chess.game;

import chess.ChessGame;
import chess.ChessMove;
import chess.ChessPosition;
import chess.InvalidMoveException;
import passoff.chess.EqualsTestingUtility;
import passoff.chess.TestUtilities;

import java.util.ArrayList;
import java.util.Collection;

public class ChessGameTests extends EqualsTestingUtility<ChessGame> {
    public ChessGameTests() {
        super("ChessGame", "games");
    }

    @Override
    protected ChessGame buildOriginal() {
        return new ChessGame();
    }

    @Override
    protected Collection<ChessGame> buildAllDifferent() {
        Collection<ChessGame> differentGames = new ArrayList<>();

        // Different team turn
        ChessGame game1 = new ChessGame();
        game1.setTeamTurn(ChessGame.TeamColor.BLACK);
        differentGames.add(game1);

        // Set board 1
        ChessGame game2 = new ChessGame();
        game2.setBoard(
            TestUtilities.loadBoard("""
                | | | |R| | | | |
                | | | | | | | | |
                | | |p|n|p| | | |
                |R| |n|k|r| | |R|
                | | |p|q|P| | | |
                | | | | |K| | | |
                | | |P|P|P|P|P|P|
                |R|N|B|Q|K|B|N|R|
                """)
        );
        differentGames.add(game2);

        // Set board 2
        ChessGame game3 = new ChessGame();
        game3.setBoard(
            TestUtilities.loadBoard("""
                | | |B|R| |P| | |
                | |P| | |n| |q| |
                | | | |n|p| | | |
                |R| |n|k|r| |r| |
                | | |p|q|P| | |R|
                | |B| | |K| | | |
                | | |P|P|P|P|P| |
                |R| | |Q| | |N| |
                """)
        );
        differentGames.add(game3);

        // Set board and different team turn
        ChessGame game4 = new ChessGame();
        game4.setBoard(TestUtilities.loadBoard("""
                | | | |R| | | | |
                | | | | | | | | |
                | | |p|n|p| | | |
                |R| |n|k|r| | |R|
                | | |p|q| | | | |
                | | | | | |K| | |
                | | | | |P| | | |
                | | | |R| | | | |
                """));
        game4.setTeamTurn(ChessGame.TeamColor.BLACK);
        differentGames.add(game4);

        return differentGames;
    }
}
