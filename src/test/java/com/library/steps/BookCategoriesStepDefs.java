package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.DashBoardPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class BookCategoriesStepDefs extends BookPage{

    @When("the user navigates to {string} page")
    public void the_user_navigates_to_page(String Books) {
        navigateModule(Books);
        BrowserUtil.waitFor(2);
    }
    List<String> actualResult;
    @When("the user clicks book categories")
    public void the_user_clicks_book_categories() {
        actualResult=BrowserUtil.getAllSelectOptions(mainCategoryElement);
        actualResult.remove(0);

    }
    @Then("verify book categories must match book_categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {
        DB_Util.runQuery("select name from book_categories");

        List<String> expectedResult= DB_Util.getColumnDataAsList(1);
        Assert.assertEquals(expectedResult,actualResult);
    }

}
