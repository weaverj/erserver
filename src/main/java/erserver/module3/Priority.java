package erserver.module3;

public enum Priority {
    RED,
    YELLOW,
    GREEN,
    BLACK;

    public static Priority getByString(String priority) {
        for (Priority p : Priority.values()) {
            if (p.name().equalsIgnoreCase(priority)) {
                return p;
            }
        }
        throw new RuntimeException("No such priority " + priority);
    }
}
