package ictProxy;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


public class ProxyService extends HttpServlet {
	
	private String RestUrl = "http://atltstapp01ibo.uss.net:7381/";
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		PrintWriter out = response.getWriter();
		String uri = request.getRequestURI();
		System.out.println(uri);
		out.write(uri);
		if (request.getQueryString() != null) {
			uri += "?" + request.getQueryString();
		}
		RestUrl += request.getRequestURI();
		out.write(RestUrl);
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(RestUrl);
		
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		RestTemplate template = new RestTemplate();
		ResponseEntity<String> templateResponse = template.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, String.class);

		System.out.println(templateResponse.getBody());
	}

}
