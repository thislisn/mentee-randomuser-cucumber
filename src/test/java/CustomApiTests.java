import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.stream.IntStream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(DataProviderRunner.class)
public class CustomApiTests extends Hooks {
    @Test
    @UseDataProvider(value = "resultsPerPage", location = RandomUserDataProvider.class)
    public void checkPersonGenderAllTheSame(int resultsPerPage) {
        given()
                .param("results", resultsPerPage)
                .param("inc", "gender")
                .when()
                .get()
                .then()
                .assertThat()
                .body("results.gender", response -> everyItem(equalTo(response.path("results[0].gender"))));
    }

    @Test
    @UseDataProvider(value = "resultsPerPage", location = RandomUserDataProvider.class)
    public void checkAllPasswordsMatchPattern1Digit1Capital(int resultsPerPage) {
        String pattern = "^(?=.*?[A-Z])(?=.*?[0-9]).*$";

        given()
                .param("results", resultsPerPage)
                .param("inc", "login")
                .when()
                .get()
                .then()
                .assertThat()
                .body("results.login.password", everyItem(matchesPattern(pattern)));
    }

    @Test
    @UseDataProvider(value = "resultsPerPage", location = RandomUserDataProvider.class)
    public void checkPasswordNotContainsUserName(int resultsPerPage) {
        List<String> passwords =
                given()
                        .param("results", resultsPerPage)
                        .param("inc", "login")
                        .when()
                        .get()
                        .then()
                        .extract()
                        .body()
                        .jsonPath()
                        .getList("results.login.password");

        List<String> usernames =
                given()
                        .param("results", resultsPerPage)
                        .param("inc", "login")
                        .when()
                        .get()
                        .then()
                        .extract()
                        .body()
                        .jsonPath()
                        .getList("results.login.username");

        IntStream.range(0, passwords.size()).forEach(i ->
                assertThat(passwords.get(i), not(containsString(usernames.get(i)))));
    }

    @Test
    @UseDataProvider(value = "resultsPerPage", location = RandomUserDataProvider.class)
    public void checkAnyMatchMrsOrMsOrMr(int resultsPerPage) {
        given()
                .param("results", resultsPerPage)
                .param("inc", "name")
                .when()
                .get()
                .then()
                .assertThat()
                .body("results.name.title", hasItem(
                        either(is("Mrs"))
                                .or(is("Ms"))
                                .or(is("Mr"))));
    }

    @Test
    @UseDataProvider(value = "resultsPerPage", location = RandomUserDataProvider.class)
    public void checkAverageAgeLoverThan40(int resultsPerPage) {
        given()
                .param("results", resultsPerPage)
                .when()
                .get()
                .then()
                .assertThat()
                .body("results.collect{it.dob.age}.average()", lessThan(40));
    }

}
