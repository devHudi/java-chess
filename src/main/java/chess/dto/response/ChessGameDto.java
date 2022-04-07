package chess.dto.response;

import chess.domain.piece.PieceColor;

public class ChessGameDto {
    private final String gameId;
    private final PieceColorDto currentTurn;

    public ChessGameDto(String gameId, PieceColorDto currentTurn) {
        this.gameId = gameId;
        this.currentTurn = currentTurn;
    }

    public static ChessGameDto from(String gameId, String turn) {
        return new ChessGameDto(gameId, PieceColorDto.from(turn));
    }

    public PieceColor getCurrentTurnAsPieceColor() {
        return currentTurn.toPieceColor();
    }

    @Override
    public String toString() {
        return "GameDto{" +
                "gameId='" + gameId + '\'' +
                ", currentTurnDto=" + currentTurn +
                '}';
    }
}
