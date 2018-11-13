package ecs.apps.frontend.google.steps;

import ecs.apps.frontend.google.pages.GoogleHomePage;
import net.thucydides.core.annotations.Step;

public class GoogleSearchSteps {

	GoogleHomePage googleHomePage;
	
	@Step
	public void inputSearchTerm(String search) {
		googleHomePage.inputSearchTerm(search);
	}
	
}
