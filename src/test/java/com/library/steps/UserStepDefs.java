package com.library.steps;

import com.library.pages.UsersPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class UserStepDefs {

    String actualUserCount;

    @Given("Establish the database connection")
    public void establish_the_database_connection() {
        //Make a connection with library
        // DB_Util.createConnection();
        System.out.println("--------------------------------------------------");
        System.out.println("--- CONNECTION WILL BE DONE WITH BEFORE HOOK -----");
        System.out.println("--------------------------------------------------");
    }

    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() {
//select id from users
        String query = "SELECT count(Id) FROM users";
        DB_Util.runQuery(query);

        actualUserCount = DB_Util.getFirstRowFirstColumn();
        System.out.println(actualUserCount);
    }

    @Then("verify all users has unique ID")
    public void verify_all_users_has_unique_id() {
//select distinct id from users;
        String query = "SELECT count(distinct Id) FROM users";
        DB_Util.runQuery(query);

        String expectedUserCount = DB_Util.getFirstRowFirstColumn();
        System.out.println(expectedUserCount);

        Assert.assertEquals(expectedUserCount, actualUserCount);


        //Close Conn
        // DB_Util.destroy();
        System.out.println("--------------------------------------------------");
        System.out.println("--- CONNECTION WILL BE CLOSED WITH AFTER HOOK -----");
        System.out.println("--------------------------------------------------");
    }

    //US01-2
    List<String> actualColumnList;

    @When("Execute query to get all columns")
    public void execute_query_to_get_all_columns() {

        String query = "SELECT * FROM users";
        DB_Util.runQuery(query);

        actualColumnList = DB_Util.getAllColumnNamesAsList();

        for (String eachColumn : actualColumnList) {
            System.out.println(eachColumn);
        }
    }

    @Then("verify the below columns are listed in result")
    public void verify_the_below_columns_are_listed_in_result(List<String> expectedColumnList) {

        for (String eachColumn : expectedColumnList) {
            System.out.println(eachColumn);
        }

        Assert.assertEquals(expectedColumnList, actualColumnList);
    }

    //------------------------------------------------------------------------------------------

    UsersPage usersPage = new UsersPage();
    String actualCount;
    //US8
    @When("the user selected status {string}")
    public void the_user_selected_status(String inactive) {
        // Select select = new Select(usersPage.statusDropdown);way 1
        //select.selectByVisibleText(inactive);

        //way 2
        BrowserUtil.selectOptionDropdown(usersPage.statusDropdown, inactive);
    }

    @When("the gets number of users")
    public void the_gets_number_of_users() {
        BrowserUtil.waitFor(2);


        //String expectedCount= usersPage.userCount.getText(); //way 1
        // System.out.println("expectedCount = " + expectedCount);

        actualCount = usersPage.getUserCount();
        System.out.println("expectedUserCounts = " + actualCount);
    }

    @Then("verify {string} status users count matching with DB")
    public void verify_status_users_count_matching_with_db(String status) {

        DB_Util.runQuery("select count(*) from users where status='" + status + "'");

        String expectedCount= DB_Util.getFirstRowFirstColumn();

        Assert.assertEquals(expectedCount,actualCount);

    }
}