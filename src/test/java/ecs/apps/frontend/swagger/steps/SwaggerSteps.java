package ecs.apps.frontend.swagger.steps;

import ecs.apps.frontend.swagger.pages.RequestDescriptionPage;
import ecs.apps.frontend.swagger.pages.SwaggerDocumentationPage;
import net.thucydides.core.annotations.Step;

public class SwaggerSteps {

	SwaggerDocumentationPage swaggerDocumentationPage;
	RequestDescriptionPage requestDescriptionPage;

	@Step
	public void navigateTo(String url) {
		swaggerDocumentationPage.getDriver().get(url);
	}

	@Step
	public boolean isUiVisible() {
		return swaggerDocumentationPage.isUiVisible();
	}

	@Step
	public void extractSwaggerList() {
		int size = swaggerDocumentationPage.extractListSize();
		for (int i = 0; i < size; i++) {
			
			//expand request details
			boolean isExpanded = swaggerDocumentationPage.expandItemRow(i);
			
			System.out.println("container-Id: " + swaggerDocumentationPage.extractContainerId(i));
			
			if (isExpanded) {
				requestDescriptionPage.extractRequestParams();
				//click try command - this will open the edit mode for the parameters
				requestDescriptionPage.clickTryCommand();
				requestDescriptionPage.populateExtractRequestParams(swaggerDocumentationPage.extractContainerId(i));
				requestDescriptionPage.clickExecuteCommand();
				requestDescriptionPage.extractCurlCommand();
				
				//close after extraction -  there should always be just one description container
				swaggerDocumentationPage.expandItemRow(i);
			}
		}
		System.out.println("--------------------------" );
	}

}
