package restassuredtestcase;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

public class AssignmentInAPI {
	@Test
	public void GetRequest_singleusernotfound() {

		baseURI = "https://reqres.in/api";

		given().get("/users/23").then().statusCode(404).log().status();
	}

	@Test
	public void GetRequest_Delay() {

		baseURI = "https://reqres.in/api";

		given().get("/users?delay=3").then().statusCode(200)

				.body("data[0].email", equalTo("george.bluth@reqres.in")).body("data[0].first_name", equalTo("George"))
				.body("data[0].last_name", equalTo("Bluth"))
				.body("data.avatar", hasItems("https://reqres.in/img/faces/1-image.jpg",
						"https://reqres.in/img/faces/2-image.jpg", "https://reqres.in/img/faces/3-image.jpg"))
				.log().all();
	}

	@Test
	public void GetRequest_Listresources() {

		baseURI = "https://reqres.in/api";

		given().get("/unknown").then().statusCode(200)

				.body("data[0].name", equalTo("cerulean")).body("data[0].year", equalTo(2000))
				.body("data.name", hasItems("fuchsia rose", "true red", "aqua sky")).log().all();
	}

	@Test
	public void Get_SingleResource() {
		baseURI = "https://reqres.in/api";

		given().get("/unknown/2").then().statusCode(200)

				.body("data.name", equalTo("fuchsia rose")).body("data.year", equalTo(2001)).log().all();

	}

	@Test
	public void Get_SingleNotFound() {

		baseURI = "https://reqres.in/api";

		given().get("/users/23").then().statusCode(404).log().body();
	}

	@Test
	public void Post_Register_Successful() {

		baseURI = "https://reqres.in/api";

		JSONObject reqData = new JSONObject();

		reqData.put("email", "Vishnu@gmail.com");
		reqData.put("password", "Testing");

		System.out.println(reqData.toJSONString());

		given().headers("Connection", "keep alive").contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(reqData.toJSONString()).when().post("/register/4").then().log().ifStatusCodeIsEqualTo(200).log()
				.body();

	}

	@Test
	public void Post_Register_Unsuccessful() {

		baseURI = "https://reqres.in/api";

		JSONObject reqData = new JSONObject();

		reqData.put("email", "Vishnu@gmail.com");

		System.out.println(reqData.toJSONString());

		given().headers("Connection", "keep alive").contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(reqData.toJSONString()).when().post("/register").then().statusCode(400).log().body();

	}

	@Test
	public void Post_Login_Unsuccessful() {

		baseURI = "https://reqres.in/api";

		JSONObject reqData = new JSONObject();

		reqData.put("email", "Vishnu@gmail.com");

		System.out.println(reqData.toJSONString());

		given().headers("Connection", "keep alive").contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(reqData.toJSONString()).when().post("/login").then().statusCode(400).log().body();

	}
}
