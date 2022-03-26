package chess.domain.game.gamestate;

import chess.domain.board.Board;
import chess.domain.position.Position;

public class FinishedStatus extends BeforeRunning {

    public FinishedStatus(Board board) {
        super(board);
    }

    @Override
    public State endGame() {
        throw new IllegalStateException("이미 게임이 종료되었습니다.");
    }

    @Override
    public State move(Position from, Position to) {
        throw new IllegalStateException("게임 중이 아니어서 말을 움직일 수 없습니다.");
    }

    @Override
    public State showStatus() {
        // TODO: 점수를 출력하는 로직
        return new Ready();
    }
}