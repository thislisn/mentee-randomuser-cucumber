package framework.context;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BaseContext {
    private static Logger logger = LoggerFactory.getLogger(BaseContext.class);
    private static final int MAX_VALUE_LENGTH_TO_PRINT = 5_000;
    private static InheritableThreadLocal<String> scenarioName = new InheritableThreadLocal<>();
    private static InheritableThreadLocal<Map<String, Object>> projectContext = new InheritableThreadLocal<>();

    public static String getScenarioName() {
        return scenarioName.get();
    }

    public static void setScenarioName(String scenarioName) {
        BaseContext.scenarioName.set(scenarioName);
    }

    public static void initContext() {
        if (Objects.isNull(projectContext.get())) {
            projectContext.set(new HashMap<>());
        }
    }

    public static void destroyContext() {
        projectContext.remove();
    }

    public static Object getValue(Enum<?> key) {
        return getValue(key.toString());
    }

    public static Object getValue(String key) {
        initContext();
        Object value = projectContext.get().get(key);
        logger.info(String.format("Retrieving value for key '%s': %n%s", key, getValueToPrint(value)));
        return value;
    }

    public static void setValue(Enum<?> key, Object value) {
        initContext();
        setValue(key.toString(), value);
    }

    public static void setValue(String key, Object value) {
        projectContext.get().put(key, value);
        logger.info(String.format("Saving value for key '%s': %n%s", key, getValueToPrint(value)));
    }

    private static String getValueToPrint(Object value) {
        if (value != null)
            return value.toString().length() > MAX_VALUE_LENGTH_TO_PRINT
                    ? value.toString().substring(0, MAX_VALUE_LENGTH_TO_PRINT)
                    : value.toString();
        return "";
    }
}
