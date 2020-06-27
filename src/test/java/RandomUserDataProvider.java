import com.tngtech.java.junit.dataprovider.DataProvider;

public class RandomUserDataProvider {
    @com.tngtech.java.junit.dataprovider.DataProvider
    public static Object[][] passwords() {
        return new Object[][]{
                {"upper", "[A-Z]+"},
                {"lower", "[a-z]+"},
                {"upper,3-7", "[A-Z]{3,7}\\b"},
                {"lower,8-16", "[a-z]{8,16}\\b"}
        };
    }

    @com.tngtech.java.junit.dataprovider.DataProvider
    public static Object[][] resultsPerPage() {
        return new Object[][]{
                {5}, {10}, {15}, {20}, {25}, {30}, {35}, {40}, {45}, {50}
        };
    }
}
