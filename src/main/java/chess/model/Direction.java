package chess.model;

public enum Direction {
    E(1, 0),
    NE(1, 1),
    N(0, 1),
    NW(-1, 1),
    W(-1, 0),
    SW(-1, -1),
    S(0, -1),
    SE(1, -1),
    UNDEFINED(0, 0);

    private static final String NOTRH = "N";
    private static final String SOUTH = "S";
    private static final String EAST = "E";
    private static final String WEST = "W";
    private static final String NORTH_EAST = "NE";
    private static final String NORTH_WEST = "NW";
    private static final String SOUTH_EAST = "SE";
    private static final String SOUTH_WEST = "SW";
    private final int deltaX;
    private final int deltaY;

    Direction(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public static Direction valueOf(Point source, Point target) {
        String result = "";

        if (source.calculateYsSub(target) < 0) {
            result += NOTRH;
        }

        if (source.calculateYsSub(target) > 0) {
            result += SOUTH;
        }

        if (source.calculateXsSub(target) < 0) {
            result += EAST;
        }

        if (source.calculateXsSub(target) > 0) {
            result += WEST;
        }

        if ((result.equals(NORTH_EAST) || result.equals(NORTH_WEST) || result.equals(SOUTH_EAST) || result.equals(SOUTH_WEST))
                && (source.calculateXsDiff(target) != source.calculateYsDiff(target))) {
            return Direction.UNDEFINED;
        }

        return Direction.valueOf(result);
    }

    public int getDeltaX() {
        return deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }
}
