package ecs.runners.tests.reqres;

import java.io.IOException;
import java.security.cert.CertificateParsingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.symphonyoss.symphony.jcurl.JCurl;

import ecs.apps.backend.reqres.post.create.ReqresCreateData;
import ecs.tools.connectors.JCurlConnector;

@RunWith(JUnit4.class)
public class TC002CreatePostTest {

	@Test
	public void tc002CreatePostTest() throws CertificateParsingException, IOException {
		
		JCurlConnector jcurl = new JCurlConnector();
		
		ReqresCreateData data = new ReqresCreateData();
		String body = data.generateCreatePostBody();
		
		JCurl.Response  res = jcurl.postRequest("https://reqres.in/api/users", body);
		
		System.out.println("REsponse: " + res.getOutput());
	}
}
