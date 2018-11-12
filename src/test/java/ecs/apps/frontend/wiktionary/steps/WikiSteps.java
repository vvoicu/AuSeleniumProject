package ecs.apps.frontend.wiktionary.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;

import ecs.apps.frontend.wiktionary.pages.WikiDictionaryPage;
import net.thucydides.core.annotations.Step;

public class WikiSteps {

    WikiDictionaryPage wikiDictionaryPage;

    @Step
    public void typeKeyword(String keyword) {
        wikiDictionaryPage.enterKeywords(keyword);
    }

    @Step
    public void clickSearch() {
        wikiDictionaryPage.clickSearchButton();
    }

    @Step
    public void verifyDefinition(String definition) {
        assertThat(wikiDictionaryPage.getDefinitions(), hasItem(containsString(definition)));
    }

    @Step
    public void navigateToWikiUrl() {
    	wikiDictionaryPage.getDriver().get("http://en.wiktionary.org/wiki/Wiktionary");
    }

    @Step
    public void searchFor(String term) {
        typeKeyword(term);
        clickSearch();
    }
}