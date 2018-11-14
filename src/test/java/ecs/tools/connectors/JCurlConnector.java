package ecs.tools.connectors;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.security.cert.CertificateParsingException;

import org.symphonyoss.symphony.jcurl.JCurl;

public class JCurlConnector {

//	public static void main(String[] args) throws CertificateParsingException, IOException {
//		JCurlConnector jCurlConnector = new JCurlConnector();
//		JCurl.Response response = jCurlConnector.postRequest("https://www.google.com", "");
//
//		System.out.println(response.getOutput());
//	}

	/**
	 * Request will receive two Strings. It will return the response of the request.
	 * @param uri - Full request URI
	 * @param body - request body as string
	 * @return ResponseBody {@link JCurl}
	 * @throws IOException
	 * @throws CertificateParsingException
	 */
	public JCurl.Response postRequest(String uri, String body) throws IOException, CertificateParsingException {
		//build request data
		JCurl jcurl = JCurl.builder().method(JCurl.HttpMethod.POST).data(body).build();

		//send request to URI
		HttpURLConnection connection = jcurl.connect(uri);
		
		//pass response
		return jcurl.processResponse(connection);
	}

}
