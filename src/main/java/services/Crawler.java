package services;

import models.CrawledUserInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Crawler {
    private WebDriver webDriver = new ChromeDriver();
    //private static WebDriver webDriver = new JBrowserDriver();

    public Crawler(String login, String password) {
        webDriver.get("http://vk.com");
        webDriver.findElement(By.id("index_email"))
                .sendKeys(login);
        webDriver.findElement(By.id("index_pass"))
                .sendKeys(password);
        webDriver.findElement(By.id("index_login_button"))
                .click();
    }

    public CrawledUserInfo getUserInfo(String userId) throws InterruptedException {
        Thread.sleep(1000);
        webDriver.get("http://vk.com/"+userId);

        Thread.sleep(1000);
        Boolean hasCommonFriends = webDriver.findElements(By.id("profile_common_friends")).size() > 0;
        String commonFriendUrl = null;
        if (hasCommonFriends) {
            commonFriendUrl = webDriver.findElement(By.cssSelector("#profile_common_friends > div > div > div:nth-child(1) > a")).getAttribute("href");
            commonFriendUrl = commonFriendUrl.substring(1);
        }

        WebElement friendsPageLink = webDriver.findElement(By.cssSelector("[href^=\"/friends?id=\"]"));
        String friendsPageUrl = friendsPageLink.getAttribute("href");

        friendsPageLink.click();
        Thread.sleep(1000);

        String firstFriendUrl = webDriver
                .findElements(By.cssSelector(".friends_field_title a"))
                .toArray(new WebElement[1])[0]
                .getAttribute("href");

        return new CrawledUserInfo(firstFriendUrl, friendsPageUrl, hasCommonFriends, commonFriendUrl);
    }

}
