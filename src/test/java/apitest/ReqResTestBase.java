package apitest;
import org.json.JSONObject;

import static constants.api.ReqResRequestConnst.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ReqResTestBase {
   private JSONObject requestParams;

    public void verifyUserDataPresenceOnPage(int pageNumber) {
        given().
                param(PAGE, pageNumber).
                when().
                get(USERS_URL).
                then().
                log().ifValidationFails().
                statusCode(200);

    }
    public void verifyUsersAmountOnPage(int pageNumber, int usersAmount) {
        given().
                when().
                param(PAGE, pageNumber).
                get(USERS_URL).
                then().
                log().body().
                statusCode(200).
                body(DATA, hasSize(3));
    }
    public void verifyUserPresenceById(int userId) {
        given().
                when().
                get(USERS_URL+userId).
                then().
                log().body().
                statusCode(200);
    }
    public void verifyUserNamesById(int userId, String firstName, String lastName) {
        given().
                when().
                get(USERS_URL+userId).
                then().
                log().body().
                statusCode(200).
                body(DATA+"."+FIRST_NAME, equalTo(firstName)).
                body(DATA+"."+LAST_NAME, equalTo(lastName));
    }
    public void verifyUserAbsenceById(int userId) {
        given().
                when().
                get(USERS_URL+userId).
                then().
                log().body().
                statusCode(404);
    }
    public void verifyResourceDataById(int resourceId, String resourceName,int resourceYear, String resourceColor) {
        given().
                when().
                get(UNKNOWN_RESOURCE_URL+resourceId).
                then().
                log().body().
                statusCode(200).
                body(DATA+"."+NAME, equalTo(resourceName), DATA+"."+YEAR, equalTo(resourceYear),DATA+"."+COLOR, equalTo(resourceColor));
    }

    public void verifyUserCreation(String userName, String userJob) {
        requestParams = new JSONObject();
        requestParams.put(NAME, userName).put(JOB, userJob);
        given().
               headers("Content-Type", "application/json").
                body(requestParams.toString()).
                when().
                post(USERS_URL).
                then().
                log().body().
                statusCode(201).
                body(NAME, equalTo(userName), JOB, equalTo(userJob));
    }

    public void verifyUserUpdate(String userName, String userJob) {
        requestParams = new JSONObject();
        requestParams.put(NAME, userName).put(JOB, userJob);
        given().
                headers("Content-Type", "application/json").
                body(requestParams.toString()).
                when().
                put(USERS_URL).
                then().
                log().body().
                statusCode(200).
                body(NAME, equalTo(userName), JOB, equalTo(userJob), APDATED_AT, anything());
    }
    public void verifyUserDeleteById(int userID) {
        given().
                when().
                delete(USERS_URL+userID).
                then().
                log().body().
                statusCode(204);
    }
    public void verifyUnsuccessfulLogin(String password) {
        requestParams = new JSONObject();
        requestParams.put(PASSWORD, password);
        given().
                body(requestParams.toString()).
                when().
                post(LOGIN_URL).
                then().
                log().body().
                statusCode(400).
                body(ERROR, equalTo("Missing email or username"));
    }



}
