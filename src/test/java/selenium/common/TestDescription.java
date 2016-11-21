package selenium.common;

/**
 * TestDescription
 */
public class TestDescription {

    private String name = "";
    private String description = "'";
    private String methodName = "";
    private String className;
    private boolean isConfig = false;

    public String getName() {
        return name;
    }

    public TestDescription setName(String name) {
        this.name = name;
        return this;
    }

    public String isConfig() {
        return isConfig + "";
    }

    public TestDescription setIsConfig(boolean value) {
        this.isConfig = value;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TestDescription setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getMethodName() {
        return methodName;
    }

    public TestDescription setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public String getClassName() {
        return className;
    }

    public TestDescription setClassName(String className) {
        this.className = className;
        return this;
    }

}