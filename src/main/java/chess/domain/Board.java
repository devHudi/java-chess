package chess.domain;

import chess.domain.piece.*;
import chess.dto.BoardDto;
import chess.view.WebUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static chess.domain.Team.BLACK;
import static chess.domain.Team.WHITE;

public class Board {
    private final Map<Position, Piece> board;

    private Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board init() {
        Map<Position, Piece> board = new HashMap<>();
        IntStream.rangeClosed(1, 8)
                .forEach(i -> {
                    board.put(new Position(new Coordinate(i), new Coordinate(8)), chessPieces(BLACK).get(i - 1));
                    board.put(new Position(new Coordinate(i), new Coordinate(1)), chessPieces(WHITE).get(i - 1));
                    board.put(new Position(new Coordinate(i), new Coordinate(7)), new Pawn(BLACK));
                    board.put(new Position(new Coordinate(i), new Coordinate(2)), new Pawn(WHITE));
                });
        return new Board(board);
    }

    public static Board load(Map<Position, Piece> board) {
        return new Board(board);
    }

    private static List<Piece> chessPieces(final Team team) {
        return Arrays.asList(
                new Rook(team), new Knight(team), new Bishop(team),
                new Queen(team), new King(team), new Bishop(team),
                new Knight(team), new Rook(team)
        );
    }

    public Piece at(final Position position) {
        return board.get(position);
    }

    public void move(Position source, Position target, Piece sourcePiece) {
        board.remove(source);
        board.put(target, sourcePiece);
    }

    public List<BoardDto> toDto() {
        return board.entrySet()
                .stream()
                .map((entry) -> {
                    Position position = entry.getKey();
                    Piece piece = entry.getValue();
                    return new BoardDto(WebUtil.positionParser(position), piece.getName(), piece.getTeam().name());
                })
                .collect(Collectors.toList());
    }
}