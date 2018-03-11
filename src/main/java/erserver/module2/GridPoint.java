package erserver.module2;

public class GridPoint {

    private int x;
    private int y;
    private String gridId;

    private int cookies = 0;

    public GridPoint(int x, int y) {
        this.x = x;
        this.y = y;
        this.gridId = this.x + "," + this.y;
    }

    public void addCookie() {
        cookies += 1;
    }

    public String getGridId() {
        return gridId;
    }

    public int getNumberOfCookies() {
        return cookies;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
