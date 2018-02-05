package apitest;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class ReqResTest extends BaseTest {
    private ReqResTestBase rs;

    @BeforeMethod
    public void beforeMethod() {
        rs = new ReqResTestBase();
    }

    @Test
    public void getUserDataByPageTest() {
        rs.verifyUserDataPresenceOnPage(3);
    }

    @Test
    public void verifyUsersAmountOnPage() {
        rs.verifyUsersAmountOnPage(1, 3);
    }

    @Test
    public void verifyUserPresenceById() {
        rs.verifyUserPresenceById(3);
    }

    @Test
    public void verifyUserNamesById() {
        rs.verifyUserNamesById(5, "Charles", "Morris");
    }

    @Test
    public void verifyUserAbsenceById() {
        rs.verifyUserAbsenceById(100);
    }

    @Test
    public void verifyResourceDataById() {
        rs.verifyResourceDataById(1, "true red", 2002, "#BF1932");
    }

    @Test
    public void verifyUserCreation() {
        rs.verifyUserCreation("Alex", "manager");
    }

    @Test
    public void verifyUserUpdate() {

        rs.verifyUserUpdate("Alex", "manager");
    }

    @Test
    public void verifyUserDeleteById() {
        rs.verifyUserDeleteById(2);
    }

    @Test
    public void verifyUnsuccessfulLogin() {
        rs.verifyUnsuccessfulLogin("aaaail");
    }
}
