package wooteco.chess.domain.piece;

import wooteco.chess.domain.board.Path;
import wooteco.chess.domain.board.Position;

public class Blank implements Piece {
    private static final int INITIAL_X_THREE = 3;
    private static final int INITIAL_X_FOUR = 4;
    private static final int INITIAL_X_FIVE = 5;
    private static final int INITIAL_X_SIX = 6;

    public Blank() {
    }

    @Override
    public boolean isMovable(Path path) {
        return false;
    }

    @Override
    public boolean isInitialPosition(Position position) {
        return position.isOnX(INITIAL_X_THREE)
                || position.isOnX(INITIAL_X_FOUR)
                || position.isOnX(INITIAL_X_FIVE)
                || position.isOnX(INITIAL_X_SIX);
    }
}
