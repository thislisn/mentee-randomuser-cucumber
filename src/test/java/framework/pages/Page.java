package framework.pages;

public interface Page {
    void open();
    boolean isOpened();
    void waitUntilPageOpened();
}
