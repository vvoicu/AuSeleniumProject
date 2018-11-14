package ecs.tools.connectors;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.security.cert.CertificateParsingException;

import org.symphonyoss.symphony.jcurl.JCurl;

public class JCurlConnector {
	
	
	
	
	public void postRequest() throws IOException, CertificateParsingException {
		
		//Create an IM with user 123456

		JCurl jcurl  = JCurl.builder()
		    .method(JCurl.HttpMethod.POST)              //Set implicitly by specifying ".data()"; this line can be skipped
		    .header("sessionToken", "abc")       //Set the session token in the request header
		    .data("[123456]")                           //Set the JSON payload of the request
		    .extract("sid", "id")                       //Extract the stream ID of the conversation as "sid"
		    .build();

		HttpURLConnection connection = jcurl.connect("https://localhost.symphony.com:8443/pod/v1/im/create");
		JCurl.Response response = jcurl.processResponse(connection);
		String streamId = response.getTag("sid");

		System.out.println("Stream ID: " + streamId);

		//Print the output of the call
		System.out.println(response.getOutput());      
	}

}
