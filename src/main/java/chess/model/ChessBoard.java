package chess.model;

import chess.model.piece.*;

import java.util.HashMap;
import java.util.Map;

public class ChessBoard {
    private Map<Point, AbstractChessPiece> board;

    public ChessBoard(final Map<Point, AbstractChessPiece> board) {
        this.board = board;
    }

    public ChessBoard() {
        board = new HashMap<>();
        initializeOthers(1, ChessPieceColor.WHITE);
        initializeOthers(8, ChessPieceColor.BLACK);
        initializePawns(2, ChessPieceColor.WHITE);
        initializePawns(7, ChessPieceColor.BLACK);
    }

    private void initializePawns(int y, ChessPieceColor color) {
        for (int x = 1; x <= 8; x++) {
            board.put(new Point(x, y), AbstractPawn.getInstance(color));
        }
    }

    private void initializeOthers(int y, ChessPieceColor color) {
        board.put(new Point(1, y), new Rook(color));
        board.put(new Point(2, y), new Knight(color));
        board.put(new Point(3, y), new Bishop(color));
        board.put(new Point(4, y), new King(color));
        board.put(new Point(5, y), new Queen(color));
        board.put(new Point(6, y), new Bishop(color));
        board.put(new Point(7, y), new Knight(color));
        board.put(new Point(8, y), new Rook(color));
    }

    public boolean isEmpty(final Point point) {
        return board.get(point) == null;
    }

    public boolean isSameColor(final Point point, final ChessPieceColor color) {
        return board.get(point).isSameColor(color);
    }

    public boolean canMove(final Point source, final Point target) {
        return board.get(source).canMove(source, target, (Point p) -> board.get(p));
    }

    public void move(final Point source, final Point target) {
        board.put(target, board.get(source));
        board.remove(source);
    }

    public boolean isSameType(final Point point, final ChessPieceType type) {
        return board.get(point).isType(type);
    }

    public double getScore(ChessPieceColor color) {
        double sum = 0.0;
        Map<Point, AbstractChessPiece> newBoard = getBoard(color);

        for (Map.Entry<Point, AbstractChessPiece> entry : newBoard.entrySet()) {
            sum += entry.getValue().getScore(entry.getKey(), newBoard::get);
        }
        return sum;
    }

    private Map<Point, AbstractChessPiece> getBoard(ChessPieceColor color) {
        Map<Point, AbstractChessPiece> newBoard = new HashMap<>();
        for (Map.Entry<Point, AbstractChessPiece> entry : board.entrySet()) {
            checkColor(color, newBoard, entry);
        }
        return newBoard;
    }

    private void checkColor(final ChessPieceColor color, final Map<Point, AbstractChessPiece> newBoard, final Map.Entry<Point, AbstractChessPiece> entry) {
        if (isSameColor(entry.getKey(), color)) {
            newBoard.put(entry.getKey(), entry.getValue());
        }
    }

    public Map<Point, AbstractChessPiece> getBoard() {
        return board;
    }
}
