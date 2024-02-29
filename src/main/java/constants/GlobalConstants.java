package constants;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
public final class GlobalConstants {

    private static GlobalConstants globalInstance;

    private GlobalConstants() {

    }

    public static synchronized GlobalConstants getGlobalConstants() {
        if (globalInstance == null) {
            globalInstance = new GlobalConstants();
        }
        return globalInstance;
    }

    private final Duration LONG_TIMEOUT = Duration.ofSeconds(30);
    private final String REPORT_TITLE = "Source Demo";
    private final String AUTHOR = "Mai Van Sinh";
}
