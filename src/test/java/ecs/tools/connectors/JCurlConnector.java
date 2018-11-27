package ecs.tools.connectors;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.security.cert.CertificateParsingException;
import java.util.HashMap;
import java.util.Map;

import org.symphonyoss.symphony.jcurl.JCurl;

import ecs.tools.ConfigUtils;

/**
 * Implementation of jCurl. {@link https://github.com/symphonyoss/JCurl}
 * 
 * @author voicu.turcu
 *
 */
public class JCurlConnector {

//	public static void main(String[] args) throws CertificateParsingException, IOException {
//		JCurlConnector jCurlConnector = new JCurlConnector();
//		JCurl.Response response = jCurlConnector.postRequest("https://www.google.com", "");
//
//		System.out.println(response.getOutput());
//	}

	/**
	 * Request will receive two Strings. It will return the response of the request.
	 * 
	 * @param uri  - Full request URI
	 * @param body - request body as string
	 * @return ResponseBody {@link JCurl}
	 * @throws IOException
	 * @throws CertificateParsingException
	 */
	public JCurl.Response postRequest(String uri, String body) throws CertificateParsingException, IOException {
		return request(uri, JCurl.HttpMethod.POST, new HashMap<>(), body);
	}

	public JCurl.Response getRequest(String uri) throws CertificateParsingException, IOException {
		return request(uri, JCurl.HttpMethod.POST, new HashMap<>(), "");
	}

	
	
	
	private JCurl.Response request(String uri, JCurl.HttpMethod method, Map<String, String> headers, String body)
			throws IOException, CertificateParsingException {
		JCurl jcurl;

		// get value if to use proxy from the configs
		boolean enableProxy = Boolean.valueOf(ConfigUtils.getProperty("JCURL_PROXY").toLowerCase().trim()) == true
				? true
				: false;

		// set PROXY if true
		jcurl = enableProxy
				? JCurl.builder().proxy(ConfigUtils.getProperty("JCURL_PROXY_URL")).method(method).data(body).build()
				: JCurl.builder().method(method).data(body).build();

		// TODO set certificates
		// Set user certificate for authentication
//		jcurl = JCurl.builder().keystore("bot.user1.p12").storepass("changeit").storetype("pkcs12").build();

		// send request to URI
		HttpURLConnection connection = jcurl.connect(uri);

		// pass response
		return jcurl.processResponse(connection);

	}

}
