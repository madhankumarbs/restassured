package tests;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.springframework.context.annotation.Description;
import org.testng.annotations.Test;

import base.BaseClass;
import base.Requesthdrs;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import rrpairs.Create;

public class VerifyCreate extends Create {
	public String Responsestr;

	@Test(description = "Validate the Test Case", groups = { "RegressionTest", "SmokeTest" })
	@Description("Description : Validate the Test Case")

	public String Createsome() throws IOException {
		System.out.println(ENV.gethost());
		RestAssured.urlEncodingEnabled = false;
		RestAssured.baseURI = ENV.gethost();
		RestAssured.port = ENV.PORT();

		Response response = BaseClass.givenreq().headers(Requesthdrs.webheaders()).contentType(ContentType.JSON)
				.body(Create.createsomething()).log().all().when().post(ENV.getpath());
		Responsestr = response.getBody().asString();

		response.then().log().all().assertThat().body("a.b[0].c", Matchers.notNullValue()).and().assertThat()
				.body("a.c", Matchers.equalTo("Testing")).and().assertThat().body("a.c.d", Matchers.equalTo(ENV.Key1()))
				.assertThat().body("b.d.c", Matchers.equalTo(ENV.Key3()));

		return Responsestr;

	}

	@Test(description = "Validate the Other Test Case", groups = { "RegressionTest" })
	@Description("Description : Validate the Other Test Case")

	public String CreateOther() throws IOException {
		System.out.println(ENV.gethost());
		RestAssured.urlEncodingEnabled = false;
		RestAssured.baseURI = ENV.gethost();
		RestAssured.port = ENV.PORT();

		Response response = BaseClass.givenreq().headers(Requesthdrs.webheaders()).contentType(ContentType.JSON)
				.body(Create.createotherthing()).log().all().when().post(ENV.getpath());
		Responsestr = response.getBody().asString();

		response.then().log().all().assertThat().body("a.b[0].c", Matchers.notNullValue()).and().assertThat()
				.body("a.c", Matchers.equalTo("Testing")).and().assertThat().body("a.c.d", Matchers.equalTo(ENV.Key1()))
				.assertThat().body("b.d.c", Matchers.equalTo(ENV.Key3()));

		return Responsestr;

	}

}