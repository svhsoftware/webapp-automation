package com.svhsoftware.automation.tests.google;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.svhsoftware.automation.framework.BaseTest;
import com.svhsoftware.automation.pageobjects.google.SearchPage;
import com.svhsoftware.automation.pageobjects.google.SearchResultsPage;

public class SearchTest extends BaseTest {

    @Test (priority = 0)
    public void searchGoogle_svhsoftware () throws InterruptedException {
    	SearchPage searchPage = getPageInstance(SearchPage.class);
    	searchPage.goToPage();
    	searchPage.search("SVH Software");
    	
    	SearchResultsPage searchResultsPage = getPageInstance(SearchResultsPage.class);
    	assertTrue(searchResultsPage.isPageOpened());
    }
}
