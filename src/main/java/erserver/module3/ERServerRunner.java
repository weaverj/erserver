package erserver.module3;

import static spark.Spark.*;

public class ERServerRunner {
    public static void main(String[] args) {
        port(8088);
        EREndpoints.initializeEndpoints();
    }
}
