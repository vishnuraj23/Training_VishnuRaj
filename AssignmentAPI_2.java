package restassuredtestcase;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class AssignmentAPI_2 {
	
    
	@Test
	public void testPostRequestRegisterUnsuccessful() {
		//Register a user >> extract id and token
		baseURI = "https://reqres.in/api";

		JSONObject json = new JSONObject();
		json.put("email", "eve.holt@reqres.in");
		json.put("password", "cityslicka");
		System.out.println(json.toJSONString());

		String Token1 = given().body(json.toJSONString())

				.contentType(ContentType.JSON).accept(ContentType.JSON).header("Connection", "keep alive").when()
				.post("/register").then().extract().path("token");
		// validated token
		System.out.println("The register token is: " + Token1);

		int id = given().body(json.toJSONString())

				.contentType(ContentType.JSON).accept(ContentType.JSON).header("Connection", "keep alive").when()
				.post("/register").then().extract().path("id");
		// validated id
		System.out.println("The register id is: " + id);


		System.out.println(json.toJSONString());

		String token2 = given().body(json.toJSONString())

				.contentType(ContentType.JSON).accept(ContentType.JSON).header("Connection", "keep alive")

				.when().post("/login").then().extract().path("token");

		System.out.println("The Login token is: " + token2);

		given().body(json.toJSONString())

				.contentType(ContentType.JSON).accept(ContentType.JSON).header("Connection", "keep alive")

				.get("/users/"+id).then().statusCode(200)
				.body("data.id", equalTo(4)).body("data.first_name", equalTo("Eve"))
				.body("data.last_name", equalTo("Holt")).body("data.email", equalTo("eve.holt@reqres.in")).body("data.avatar", equalTo("https://reqres.in/img/faces/4-image.jpg"));

		

		given().get("/unknown/"+id).then().body("data.color", equalTo("#7BC4C4"));


		// update

		json.put("first_name", "Vishnu");
		json.put("email", "vishnu@gmail.com");

		System.out.println(json.toJSONString());

		given().headers("Connection","keep alive")
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(json.toJSONString())
		.when()
		.put("/users/"+id)
		
		.then()
		.statusCode(200);

		
				

		System.out.println("Update is done");

		// Patch

		json.put("name","John");
		json.put("job","Teacher1");



		System.out.println(json.toJSONString()); 
		
		given()
		
		.body(json.toJSONString())
		.when()
		.patch("/users/"+id)
		.then()
		.statusCode(200)
		.log().body();

		System.out.println("Patch update is done");

		// Delete

		given().body(json.toJSONString())

				.contentType(ContentType.JSON).accept(ContentType.JSON).header("Connection", "keep alive")

				.delete("/users/2").then().statusCode(204).log().all();

	}
}
