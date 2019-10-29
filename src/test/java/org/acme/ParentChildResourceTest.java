package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.json.Json;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ParentChildResourceTest {

    @Test
    public void testParentChild() {
        given()
            .when()
            .get("/parents")
            .then()
            .statusCode(200);

        given()
            .when()
            .contentType("application/json")
            .body(
                    Json.createObjectBuilder()
                            .add("name", "test-parent")
                            .build()
                            .toString())
            .post("/parents")
            .then()
            .statusCode(201);

        given()
            .when().get("/parents/test-parent")
            .then()
            .statusCode(200)
            .body("name", is("test-parent"));

        given()
                .when()
                .get("/parents/children")
                .then()
                .statusCode(200);

        given()
            .when()
            .contentType("application/json")
            .body(
                Json.createObjectBuilder()
                    .add("name", "test-child")
                    .build()
                    .toString())
            .post("/parents/children")
            .then()
            .statusCode(201);

        given()
                .when().get("/parents/children/test-child")
                .then()
                .statusCode(200)
                .body("name", is("test-child"));
    }

}