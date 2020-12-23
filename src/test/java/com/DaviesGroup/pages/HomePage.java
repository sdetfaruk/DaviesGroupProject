package com.DaviesGroup.pages;

import com.DaviesGroup.utilities.BrowserUtils;
import com.DaviesGroup.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.*;

public class HomePage extends BasePage {

    @FindBy(css = ".footer__menu")
    public WebElement footerMenu;

    @FindBy(xpath = "//ul[@class=\"footer__socials\"]/li")
    public List<WebElement> socialMediaLinks;



    ArrayList<String> socialMediaLinksPageTitle;

    public void switchToWindow(){

        BrowserUtils.waitForPageToLoad(10);
        BrowserUtils.scrollToEndOfPage();

        socialMediaLinksPageTitle = new ArrayList<>();

        String currentWindow = Driver.get().getWindowHandle();
        for (WebElement socialMediaLink : socialMediaLinks) {
            socialMediaLink.click();
            BrowserUtils.waitForClickablility(socialMediaLink, 10);

        }
        Set<String> windowHandles = Driver.get().getWindowHandles();

        Iterator<String> window =windowHandles.iterator();

        do{
            String windowHandle = window.next().toString();
            Driver.get().switchTo().window(windowHandle);
            BrowserUtils.waitForPageToLoad(10);
            BrowserUtils.waitFor(5);
            socialMediaLinksPageTitle.add(getPageTitle());
        }while(window.hasNext());


        Driver.get().switchTo().window(currentWindow);
        System.out.println("socialMediaLinksPageTitle = " + socialMediaLinksPageTitle);

    }

    public boolean verifyPageTitle(List<String> socialMediaLinks) {

        boolean flag;
        boolean twitterFlag = false;
        boolean linkedinFlag = false;

        for (String pageTitle : socialMediaLinksPageTitle) {
            for (String socialMediaLink : socialMediaLinks) {
                if (pageTitle.contains(socialMediaLink)) {
                    System.out.println("pageTitle = " + pageTitle);
                    if(socialMediaLink.equals("Twitter")) {
                        twitterFlag = true;
                    }else if(socialMediaLink.equals("LinkedIn"))
                       linkedinFlag = true;
                }
            }
        }
        System.out.println("linkedinFlag = " + linkedinFlag);
        System.out.println("twitterFlag = " + twitterFlag);

        flag = twitterFlag && linkedinFlag;

        System.out.println("flag = " + flag);
        return flag;

    }
}
