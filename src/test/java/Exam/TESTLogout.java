package Exam;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TESTLogout extends TESTBase {
    @DataProvider(name = "getUsers")
    public Object[][] getUsers() {
        return new Object[][]{
                {"blablablo", "111111A"}
        };
    }

    @Test(dataProvider = "getUsers")
    public void logoutFromLoggedInStateFromHome(String username, String password) {
        WebDriver driver = super.getDriver();

        // Login to the application
        Login loginPage = new Login(driver);
        loginPage.navigateTo();
        loginPage.login(username, password);

        // Click logout
        Header header = new Header(driver);
        header.clickLogout();

        // Verify that the user is logged out and redirected to the login page
        String elemText = loginPage.getSignInElementText();
        Assert.assertEquals(elemText, "Sign in");
    }

    @Test(dataProvider = "getUsers")
    public void logoutFromLoggedInStateFromProfile(String username, String password) {
        WebDriver driver = super.getDriver();

        // Login to the application
        Login loginPage = new Login(driver);
        loginPage.navigateTo();
        loginPage.login(username, password);

        // Navigate to the profile link and click
        Header header = new Header(driver);
        header.clickProfile();

        Profile profilePage = new Profile(driver);

        // Validate profile page URL
        profilePage.isUrlLoaded();

        // Click logout
        header.clickLogout();

        // Verify that the user is logged out and redirected to the login page
        String elemText = loginPage.getSignInElementText();
        Assert.assertEquals(elemText, "Sign in");
    }

    @Test(dataProvider = "getUsers")
    public void logoutFromLoggedInStateFromNewPost(String username, String password) {
        WebDriver driver = super.getDriver();

        // Login to the application
        Login loginPage = new Login(driver);
        loginPage.navigateTo();
        loginPage.login(username, password);

        // Navigate to the profile link and click
        Header header = new Header(driver);
        header.clickNewPost();

        // Validate new post page URL
        MakePost newPostPage = new MakePost(driver);
        newPostPage.isUrlLoaded();

        // Click logout
        header.clickLogout();

        // Verify that the user is logged out and redirected to the login page
        String elemText = loginPage.getSignInElementText();
        Assert.assertEquals(elemText, "Sign in");
    }

    @Test
    public void logoutFromLoggedOutState() {
        WebDriver driver = super.getDriver();

        // Open iskillo Home page
        Home homePage = new Home(driver);
        homePage.navigateToHome();
        homePage.isUrlLoaded();

        // Validate page title is matching the expected page
        String pageTitle = driver.getTitle();
        Assert.assertEquals("ISkillo", pageTitle);

        // Ensure that no user is logged in by finding login button
        Header header = new Header(driver);
        header.isLoginLinkVisible();

        // Navigate to the logout button and throw exception when not found
        try {
            WebElement logoutButton = driver.findElement(By.cssSelector("i.fas.fa-sign-out-alt"));
            Assert.fail("Logout button should not be present.");
        } catch (NoSuchElementException e) {
            System.out.println("There is no logout button.");
        }
    }
}
