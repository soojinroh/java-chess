package chess.model.piece;

import chess.model.AbstractBoardNavigator;
import chess.model.Direction;
import chess.model.Point;

public abstract class AbstractRangeChessPiece extends AbstractChessPiece {
    public AbstractRangeChessPiece(final ChessPieceType type, final ChessPieceColor color) {
        super(type, color);
    }

    protected boolean isObstacleBetween(final Point source, final Point target, final AbstractBoardNavigator navigator) {
        Direction dir = Direction.valueOf(source, target);
        Point tmp = source;
        while (!tmp.moveOneStep(dir).equals(target)) {
            tmp = tmp.moveOneStep(dir);
            if (navigator.getPieceAt(tmp) != null) {
                return true;
            }
        }
        return false;
    }
}
