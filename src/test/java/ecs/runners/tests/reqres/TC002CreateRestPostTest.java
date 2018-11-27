package ecs.runners.tests.reqres;

import java.io.IOException;
import java.security.cert.CertificateParsingException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.symphonyoss.symphony.jcurl.JCurl;

import ecs.apps.backend.reqres.post.create.ReqresCreateData;
import ecs.tools.ConfigUtils;
import ecs.tools.ConfigUtils.ConfigKeys;
import ecs.tools.connectors.JCurlConnector;

@RunWith(JUnit4.class)
public class TC002CreateRestPostTest {

	private JCurlConnector jcurlConnector = new JCurlConnector();
	
	private String requestBody;
	private String requestUri;

	@Before
	public void setupData() {

		// This Method will actually generate new sets of data each time the test is ran
		requestBody = ReqresCreateData.generateCreatePostBody();

		// building URL for Post Request to https://reqres.in/api/users
		requestUri = ConfigUtils.getProperty(ConfigKeys.BASE_URL_REQRES) + "/api/users";
	}

	@Test
	public void tc002CreatePostTest() throws CertificateParsingException, IOException {

		JCurl.Response response = jcurlConnector.postRequest(requestUri, requestBody);

		System.out.println("Response: " + response.getOutput());
	}
}
