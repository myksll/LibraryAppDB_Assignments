package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.Map;

public class BookInfoStepDefs extends BookPage {

    //US04
    String globalBookName;
    @When("the user searches for {string} book")
    public void the_user_searches_for_book(String bookName) {

        globalBookName=bookName;
        search.sendKeys(bookName);
    }


    @When("the user clicks edit book button")
    public void the_user_clicks_edit_book_button() {

    editBook(globalBookName).click();
    }
    @Then("book information must match the Database")
    public void book_information_must_match_the_database() {

        Map<String,String> expectedBookInfo= getExpectedBookInfo();

        DB_Util.runQuery("select name, isbn, year, author from books\n" +
                "where name= 'End of the Course'");

       Map<String,String> actualBookInfo = DB_Util.getRowMap(1);

        Assert.assertEquals(expectedBookInfo,actualBookInfo);
    }
}
