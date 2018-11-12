package ecs.runners.tests.wiktionary;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Steps;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import ecs.apps.frontend.wiktionary.steps.WikiSteps;

@RunWith(SerenityRunner.class)
public class TC0001SearchWikiTest {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public WikiSteps wikiSteps;

    @Issue("#WIKI-1")
    @Test
    public void searchingByKeywordAppleShouldDisplayTheCorrespondingArticleTest() {
        wikiSteps.navigateToWikiUrl();
        wikiSteps.searchFor("apple");
        wikiSteps.verifyDefinition("A common, round fruit produced by the tree Malus domestica, cultivated in temperate climates.");

    }

    @Test
    public void searchingByKeywordPearShouldDisplayTheCorrespondingArticleTest() {
        wikiSteps.navigateToWikiUrl();
        wikiSteps.searchFor("pear");
        wikiSteps.verifyDefinition("An edible fruit produced by the pear tree, similar to an apple but elongated towards the stem.");
    }

    @Pending @Test
    public void searchingByAmbiguiousKeywordShouldDisplayTheDisambiguationPageTest() {
    }
} 