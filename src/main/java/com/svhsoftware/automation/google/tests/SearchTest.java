package com.svhsoftware.automation.google.tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.svhsoftware.automation.framework.BaseTest;
import com.svhsoftware.automation.google.pageobjects.SearchPage;
import com.svhsoftware.automation.google.pageobjects.SearchResultsPage;

public class SearchTest extends BaseTest {

    @Test (priority = 0)
    public void searchGoogle_svhsoftware () throws InterruptedException {
    	SearchPage searchPage = getPageInstance(SearchPage.class);
    	searchPage.goToPage();
    	SearchResultsPage searchResultsPage = searchPage.searchFor("SVH Software");
    	assertTrue(searchResultsPage.isPageLoaded());
    }
}
