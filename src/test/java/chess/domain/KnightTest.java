package chess.domain;

import chess.domain.Square;
import chess.domain.piece.Color;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class KnightTest {
    @Test
    @DisplayName("두 동일한 객체를 가져왔을 때 같은지 확인")
    void checkSameInstance() {
        Knight knight = Knight.of(Color.BLACK);
        assertThat(knight).isEqualTo(Knight.of(Color.BLACK));
    }

    @Test
    @DisplayName("null check")
    void nullTest() {
        assertThatThrownBy(() -> Knight.of(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 입력입니다");
    }

    @ParameterizedTest
    @ValueSource(strings = {"c4", "d5", "d1", "f1", "f5", "g2"})
    @DisplayName("판의 정보를 가져와서 나이트가 갈 수 있는 칸에 장애물이 있는지 판단하여 이동할 수 있는 리스트 반환하는 테스트")
    void movableKnightSquareTest(String input) {
        HashMap<Square, Piece> board = new HashMap<>();
        board.put(Square.of("d5"), Knight.of(Color.BLACK));
        board.put(Square.of("c2"), Knight.of(Color.WHITE));
        board.put(Square.of("g4"), Knight.of(Color.WHITE));
        board.put(Square.of("e3"), Knight.of(Color.WHITE));
        Knight knight = Knight.of(Color.WHITE);
        Set<Square> availableSquares = knight.findMovable(Square.of("e3"), board);
        assertThat(availableSquares.contains(Square.of(input))).isTrue();
        assertThat(availableSquares.size()).isEqualTo(6);
    }
}