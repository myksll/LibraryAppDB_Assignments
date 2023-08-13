package com.library.pages;

import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import com.library.utility.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BookPage extends BasePage {

    @FindBy(xpath = "//table/tbody/tr")
    public List<WebElement> allRows;

    @FindBy(xpath = "//input[@type='search']")
    public WebElement search;

    @FindBy(id = "book_categories")
    public WebElement mainCategoryElement;

    @FindBy(name = "name")
    public WebElement bookName;


    @FindBy(xpath = "(//input[@type='text'])[4]")
    public WebElement author;

    @FindBy(xpath = "//div[@class='portlet-title']//a")
    public WebElement addBook;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement saveChanges;

    @FindBy(xpath = "//div[@class='toast-message']")
    public WebElement toastMessage;

    @FindBy(name = "year")
    public WebElement year;

    @FindBy(name = "isbn")
    public WebElement isbn;

    @FindBy(id = "book_group_id")
    public WebElement categoryDropdown;



    @FindBy(id = "description")
    public WebElement description;



    public WebElement editBook(String book) {
        String xpath = "//td[3][.='" + book + "']/../td/a";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }

    public WebElement borrowBook(String book) {
        String xpath = "//td[3][.='" + book + "']/../td/a";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }

    public void verifyBookInformation(String expectedBookName) {

        String query ="select name from books where name in" +
                " ('"+expectedBookName+"', '"+expectedBookName+"') order by added_date desc";
        DB_Util.runQuery(query);
        String actualBookName = DB_Util.getFirstRowFirstColumn();
        Assert.assertEquals(expectedBookName, actualBookName);
    }

    public String getBookInfo(String infoName){
        String locator="//form[@id='edit_book_form']//label[.='"+infoName+"']/../input";
        return Driver.getDriver().findElement(By.xpath(locator)).getAttribute("value");
    }

    public Map<String, String> getExpectedBookInfo() {

        Map<String, String> expectedBookInformation = new LinkedHashMap<>();
        BrowserUtil.waitForVisibility(bookName, 10);
        expectedBookInformation.put("name", bookName.getAttribute("value"));
        expectedBookInformation.put("isbn", isbn.getAttribute("value"));
        expectedBookInformation.put("year", year.getAttribute("value"));
        expectedBookInformation.put("author", author.getAttribute("value"));
        return expectedBookInformation;
    }




}
