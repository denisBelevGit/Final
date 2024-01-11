package Exam;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class TESTNewPost extends Exam.TESTBase {
    @DataProvider(name = "getUsersForPosting")
    public Object[][] getUsersForPosting() {
        File postPicture = new File("src/main/resources/uploads/img1.jpg");
        String caption = "Custom caption for posting image";

        return new Object[][]{
                {"blablablo", "111111A", "BlaBlaBlo", postPicture, caption}
        };
    }

    @Test(dataProvider = "getUsersForPosting")
    public void testCreatePost(String user, String password, String username, File file, String caption) {
        WebDriver driver = super.getDriver();

        // Login to the website
        Login loginPage = new Login(driver);
        loginPage.navigateTo();
        loginPage.login(user, password);

        // Click New post
        Header header = new Header(driver);
        header.clickNewPost();

        // Validate post URL is loaded
        MakePost postPage = new MakePost(driver);
        Assert.assertTrue(postPage.isUrlLoaded(), "The POST URL is not correct!");

        // Upload image
        postPage.uploadPicture(file);
        Assert.assertTrue(postPage.isImageVisible(), "The image is not visible!");
        Assert.assertEquals(file.getName(), postPage.getImageName(), "The image name is incorrect!");

        // Populate post caption
        postPage.populatePostCaption(caption);

        // Click create post
        postPage.clickCreatePost();

        // Validate profile url is correct
         Profile profilePage = new Profile(driver);
        Assert.assertTrue(profilePage.isUrlLoaded(), "The Profile URL is not correct!");
        profilePage.clickPost(0);

        // Validate image is visible, the username is the same
        ModolPost postModal = new ModolPost(driver);
        Assert.assertTrue(postModal.isImageVisible(), "The image is not visible!");
        Assert.assertEquals(postModal.getPostUser(), username);
    }
}
