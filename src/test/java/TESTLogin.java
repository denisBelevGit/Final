package Exam;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TESTLogin extends Exam.TESTBase {
    @DataProvider(name = "getUsers")
    public Object[][] getUsers() {
        return new Object[][]{
                {"blablablo", "111111A"}
        };
    }

    @DataProvider(name = "getInvalidUsers")
    public Object[][] getInvalidUsers() {
        return new Object[][]{
                {"invalidUser", "invalidPassword"}
        };
    }

    @DataProvider(name = "getInvalidUsersUsername")
    public Object[][] getInvalidUsersUsername() {
        return new Object[][]{
                {"", "invalidPassword"}
        };
    }

    @DataProvider(name = "getInvalidUsersPassword")
    public Object[][] getInvalidUsersPassword() {
        return new Object[][]{
                {"blablablo", "invalidPassword"}
        };
    }

    @Test(dataProvider = "getUsers")
    public void testLogin(String username, String password) {
        WebDriver driver = super.getDriver();

        // Open Skillo Home Page
        Home homePage = new Home(driver);
        homePage.navigateToHome();
        homePage.isUrlLoaded();

        // Click login
        Header headerMenu = new Header(driver);
        headerMenu.clickLogin();

        // Verify login page url is loaded
        Login loginPage = new Login(driver);
        loginPage.isUrlLoaded();

        // Get Sign in text
        String elemText = loginPage.getSignInElementText();
        Assert.assertEquals(elemText, "Sign in");

        // Fill in username
        loginPage.populateUsername(username);

        // Fill in password
        loginPage.populatePassword(password);

        // Click on Sign in
        loginPage.clickSignIn();
    }

    @Test(dataProvider = "getInvalidUsers")
    public void testUnsuccessfulLogin(String username, String password) {
        WebDriver driver = super.getDriver();

        // Open Skillo Home Page
        Home homePage = new Home(driver);
        homePage.navigateToHome();
        homePage.isUrlLoaded();

        // Click login
        Header headerMenu = new Header(driver);
        headerMenu.clickLogin();

        // Verify login page url is loaded
        Login loginPage = new Login(driver);
        loginPage.isUrlLoaded();

        // Get Sign in text
        String elemText = loginPage.getSignInElementText();
        Assert.assertEquals(elemText, "Sign in");

        // Fill in username
        loginPage.populateUsername(username);

        // Fill in password
        loginPage.populatePassword(password);

        // Click on Sign in
        loginPage.clickSignIn();

        // Verify that the user is still in the login page after unsuccessful login
        Assert.assertEquals(elemText, "Sign in", "User is Signed in successfully!");

        // Verify there is an error message displayed
        Assert.assertTrue(loginPage.isUserNotFoundDisplayed());
    }

    @Test(dataProvider = "getInvalidUsersUsername")
    public void testInvalidUsername(String username, String password) {
        WebDriver driver = super.getDriver();

        // Open iskillo Home Page
        Home homePage = new Home(driver);
        homePage.navigateToHome();
        homePage.isUrlLoaded();

        // Click login
        Header headerMenu = new Header(driver);
        headerMenu.clickLogin();

        // Verify login page url is loaded
        Login loginPage = new Login(driver);
        loginPage.isUrlLoaded();

        // Get Sign in text
        String elemText = loginPage.getSignInElementText();
        Assert.assertEquals(elemText, "Sign in");

        // Fill in username
        loginPage.populateUsername(username);

        // Fill in password
        loginPage.populatePassword(password);

        // Click on Sign in
        loginPage.clickSignIn();

        // Verify that the user is still in the login page after unsuccessful login
        Assert.assertEquals(elemText, "Sign in");

        // Verify there is an error message displayed
        Assert.assertTrue(loginPage.isInvalidUsernameDisplayed());
    }

    @Test(dataProvider = "getInvalidUsersPassword")
    public void testInvalidPassword(String username, String password) {
        WebDriver driver = super.getDriver();

        // Open iskillo Home Page
        Home homePage = new Home(driver);
        homePage.navigateToHome();
        homePage.isUrlLoaded();

        // Click login
        Header headerMenu = new Header(driver);
        headerMenu.clickLogin();

        // Verify login page url is loaded
        Login loginPage = new Login(driver);
        loginPage.isUrlLoaded();

        // Get Sign in text
        String elemText = loginPage.getSignInElementText();
        Assert.assertEquals(elemText, "Sign in");

        // Fill in username
        loginPage.populateUsername(username);

        // Fill in password
        loginPage.populatePassword(password);

        // Click on Sign in
        loginPage.clickSignIn();

        // Verify that the user is still in the login page after unsuccessful login
        Assert.assertEquals(elemText, "Sign in");

        // Verify there is an error message displayed
        Assert.assertTrue(loginPage.isInvalidPasswordDisplayed());
    }
}
