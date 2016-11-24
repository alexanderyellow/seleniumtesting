package selenium.common;

/**
 * Language enum contains the HeaderComponent languages
 */
public enum Language {

    RU("RU"),
    LV("LV");

    String value;

    Language(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }

}
