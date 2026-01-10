package com.hapifyme.api.utils;

import java.util.concurrent.Callable;
import static org.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

public class WaitUtils {

    public static void waitForCondition(String alias, Callable<Boolean> condition) {
        await()
                .alias(alias)
                .atMost(25, SECONDS)
                .pollInterval(3, SECONDS)
                .ignoreExceptions()
                .until(condition);
    }
}