import org.junit.Test;
import org.junit.runner.RunWith;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@RunWith(DataProviderRunner.class)
public class CommonApiTests extends Hooks {
    @Test
    public void checkPaginationPageNumber() {
        given()
                .param("page", 3)
                .param("seed", "abc")
                .when()
                .get()
                .then()
                .assertThat()
                .body("info.page", is(3));
    }

    @Test
    public void checkPaginationResultsCountPerPage() {
        given()
                .param("results", 10)
                .param("seed", "abc")
                .when()
                .get()
                .then()
                .assertThat()
                .body("results.size()", is(10));
    }

    @Test
    public void checkNationalitiesNotContainNonLatinSymbols() {
        String pattern = "([A-Z])\\w+";
        given()
                .param("inc", "name,nat")
                .param("results", "10")
                .param("nat", "gb")
                .when()
                .get()
                .then()
                .assertThat()
                .body("results.name.first", everyItem(matchesPattern(pattern)),
                        "results.name.last", everyItem(matchesPattern(pattern)));
    }

    @Test
    @UseDataProvider(value = "passwords", location = RandomUserDataProvider.class)
    public void checkPasswordsPatterns(String condition, String pattern) {
        given()
                .param("inc", "login")
                .param("results", "10")
                .param("password", condition)
                .when()
                .get()
                .then()
                .assertThat()
                .body("results.login.password", everyItem(matchesPattern(pattern)));
    }
}
