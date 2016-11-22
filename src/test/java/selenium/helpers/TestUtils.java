package selenium.helpers;

import org.apache.commons.lang.StringUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Test Utils
 */
public class TestUtils {

    /**
     * Pauses execution
     *
     * @param millis Milliseconds
     * @param nanos  Nanoseconds
     * @see Thread#sleep(long, int);
     */
    public static void sleep(long millis, int nanos) {
        try {
            Thread.sleep(millis, nanos);
        } catch (Exception ignore) {

        }
    }

    /**
     * Pauses execution
     *
     * @param millis Milliseconds
     * @see Thread#sleep(long);
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception ignore) {
        }
    }


    /**
     * Generates full text description of exception
     *
     * @param t Exception
     * @return String description with three causes
     */
    public static String getThrowableFullDescription(Throwable t) {
        return t == null ? "" : t.getClass().getName() + ": " + t.getMessage() + "\n\t"
                + StringUtils.join(t.getStackTrace(), "\n\t")
                + processCause(t.getCause(), 1);
    }

    /**
     * Generates full text description of current cause
     *
     * @param cause      Cause
     * @param causeIndex Sequential index of current cause in stackTrace
     * @return String description with three causes
     */
    private static String processCause(Throwable cause, int causeIndex) {
        if (cause == null) return "";
        if (causeIndex > 4) return "";

        int totalLength = cause.getStackTrace().length;

        String causeString = "\nCaused by: " + cause.getClass().getName() + ": " + cause.getMessage() + "\n\t";
        causeString += StringUtils.join(cause.getStackTrace(), "\n\t", 0,
                (totalLength >= 5 ? 5 : totalLength));
        causeString += "\t\n... " + (totalLength - 5) + " more";

        return causeString + processCause(cause.getCause(), causeIndex + 1);
    }

    public static String mapToString(Map<?, ?> map, String sep, String entitySep) {
        List<String> entities = new LinkedList<String>();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            entities.add(entry.getKey() + sep + entry.getValue());
        }

        return StringUtils.join(entities, entitySep);
    }

}
