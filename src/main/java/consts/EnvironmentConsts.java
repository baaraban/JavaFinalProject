package consts;


import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class EnvironmentConsts implements Serializable {
    public final int MAX_SECONDS_VALUE = 60;
    public final int MIN_SECONDS_VALUE = 0;
    public final int MAX_MINUTES_VALUE = 120;
    public final int MIN_MINUTES_VALUE = 0;
    public final int ONE_TIME_DURATION = 45;

    public final String BOTH_ACTION_MARKING = "(both)";
    public final String FROM_ACTION_MARKING = "(from)";
    public final String TO_ACTION_MARKING = "(to)";

    public static final String PROD = "production";
    public static final String DEV = "development";
}
