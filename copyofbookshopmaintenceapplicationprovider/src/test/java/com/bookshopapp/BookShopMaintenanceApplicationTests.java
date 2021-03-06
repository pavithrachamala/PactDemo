package com.bookshopapp;

import java.util.Arrays;
import org.apache.http.HttpRequest;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import au.com.dius.pact.provider.junit.IgnoreNoPactsToVerify;
import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.TargetRequestFilter;
import au.com.dius.pact.provider.junit.VerificationReports;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;

@RunWith(PactRunner.class)
@PactFolder("../acceptedPacts")
@Provider("mybookservice")
@VerificationReports({"console", "markdown", "json"})
@IgnoreNoPactsToVerify
public class BookShopMaintenanceApplicationTests {
	private int PORT = 8090;

	@TestTarget
	public final Target target = new HttpTarget("http", "localhost", PORT);

	@BeforeClass
	public static void start() {
		System.setProperty("pact.provider.version", "94");
		SpringApplication.run(BookShopMaintenanceApplication.class);

	}

	// This method acts as an interceptor before the validation happens
	// so if the provider needs to inject andything in the header or body
	// like access token,encrypted password can be done in a secured way
	// without putting the secret/access token in the pact document
	@TargetRequestFilter
	public void printTheRequestHeaders(HttpRequest request) {

		Arrays.asList(request.getAllHeaders())
				.forEach(header -> System.out.println(header.getName() + "->" + header.getValue()));
	}

}
