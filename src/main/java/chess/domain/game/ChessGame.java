package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.game.gamestate.Ready;
import chess.domain.game.gamestate.State;
import chess.domain.position.Position;

public class ChessGame {

    private State state;

    public ChessGame() {
        this.state = new Ready();
    }

    public void startGame() {
        this.state = state.startGame();
    }

    public void movePiece(Position from, Position to) {
        this.state = state.move(from, to);
    }

    public void showStatus() {
        this.state = state.showStatus();
    }

    public void endGame() {
        this.state = state.endGame();
    }

    public Board getBoard() {
        return state.getBoard();
    }
}