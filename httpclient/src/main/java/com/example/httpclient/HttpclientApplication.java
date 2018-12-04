package com.example.httpclient;




import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class HttpclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(HttpclientApplication.class, args);
	}
}

@Service
class Runner implements CommandLineRunner{

	@Override
	public void run(String... args) throws Exception {
		final String uri = "https://localhost:8443/access";
		TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

		SSLContext sslContext;
			sslContext = org.apache.http.ssl.SSLContexts.custom()
					.build();

			SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

		//	CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();
			CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();
			HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

			requestFactory.setHttpClient(httpClient);
			RestTemplate restTemplate = new RestTemplate(requestFactory);
			RestTemplate restTemplate1 = new RestTemplate();

	    String result = restTemplate.getForObject(uri, String.class);
	     
	    System.out.println("Test Invocation------------------------------------------------ "+result);
	   // System.out.println("Test Invocation1111111111------------------------------------------------ "+restTemplate1.getForObject(uri, String.class));
	
}
}