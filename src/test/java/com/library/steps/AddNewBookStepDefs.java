package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AddNewBookStepDefs extends BookPage {

    @When("the librarian click to add book")
    public void the_librarian_click_to_add_book() {
        addBook.click();

    }
    @When("the librarian enter book name {string}")
    public void the_librarian_enter_book_name(String BookName) {
        bookName.sendKeys(BookName);
    }
    @When("the librarian enter ISBN {string}")
    public void the_librarian_enter_isbn(String ISBN) {
        isbn.sendKeys(ISBN);
    }
    @When("the librarian enter year {string}")
    public void the_librarian_enter_year(String Year) {
        year.sendKeys(Year);
    }
    @When("the librarian enter author {string}")
    public void the_librarian_enter_author(String Author) {
        author.sendKeys(Author);
    }
    @When("the librarian choose the book category {string}")
    public void the_librarian_choose_the_book_category(String BookCategory) {
        Select select=new Select(categoryDropdown);
        select.selectByVisibleText(BookCategory);
    }
    @When("the librarian click to save changes")
    public void the_librarian_click_to_save_changes() {
        saveChanges.click();
    }
    @Then("verify {string} message is displayed")
    public void verify_message_is_displayed(String expectedMessage) {

        String actualMessage= toastMessage.getText();
        Assert.assertEquals(expectedMessage,actualMessage);
    }
    @Then("verify {string} information must match with DB")
    public void verify_information_must_match_with_db(String expectedBookName) {
        verifyBookInformation(expectedBookName);
    }

}
