package erserver.module2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CookieGrid {

    private HashMap<String, GridPoint> grid;
    private int verticalLinePosition;
    private int horizontalLinePosition;

    public CookieGrid() {
        grid = new HashMap<String, GridPoint>();
        verticalLinePosition = 0;
        horizontalLinePosition = 0;
    }

    public void addCookieAtPoint(int x, int y) {
        StringBuilder builder = new StringBuilder();
        String cookieId = builder.append(x).append(",").append(y).toString();
        GridPoint point = null;
        point = grid.get(cookieId);
        if (point == null) {
            point = new GridPoint(x, y);
            grid.put(cookieId, point);
        }
        point.addCookie();
    }

    public void drawVerticalLine(int x) {
        this.verticalLinePosition = x;
    }

    public void drawHorizontalLine(int y) {
        this.horizontalLinePosition = y;
    }

    public int numberOfCookiesInUpperRight() {
        List<GridPoint> cookiePoints = new ArrayList<>(grid.values());
        return cookiePoints.stream()
                .filter( p -> ( (p.getX() > verticalLinePosition) && (p.getY() > horizontalLinePosition) ))
                .mapToInt(GridPoint::getNumberOfCookies)
                .sum();
    }
}
