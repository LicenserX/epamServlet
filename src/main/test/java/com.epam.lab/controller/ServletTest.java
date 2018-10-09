package com.epam.lab.controller;

import com.epam.lab.model.Entity;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ServletTest {
    @Before
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    public void crudOperations() {
        final String url = "/";
        final String idField = "id";
        final String nameField = "name";

        final String testValue = "test";

        Response response;

        // POST - CREATE NEW

        response = RestAssured.given()
                .param(nameField, testValue)
                .when()
                .post(url)
                .then()
                .body(idField, Matchers.notNullValue())
                .body(nameField, Matchers.equalTo(testValue))
                .statusCode(200)
                .extract().response();

        Entity createdValue = response.jsonPath().getObject("", Entity.class);
        Assert.assertNotNull(createdValue);

        Gson gson = new Gson();

        // GET WITH ADD
        RestAssured.given()
                .when()
                .get(url + "?getAll=getAll")
                .then()
                .body(Matchers.containsString(gson.toJson(createdValue)))
                .statusCode(200);

        // PUT TO ADD
        response = RestAssured.given()
                .when()
                .put(url + "?id=" + createdValue.getId() + "&value=" + createdValue.getName() + "upd")
                .then()
                .body(idField, Matchers.equalTo(createdValue.getId()))
                .body(nameField, Matchers.equalTo(createdValue.getName() + "upd"))
                .statusCode(200)
                .extract().response();

        Entity updatedValue = response.jsonPath().getObject("", Entity.class);
        Assert.assertNotNull(updatedValue);

        // GET WITH UPDATED
        RestAssured.given()
                .when()
                .get(url + "?getAll=getAll")
                .then()
                .body(Matchers.containsString(gson.toJson(updatedValue)))
                .statusCode(200);

        // DELETE UPDATED
        response = RestAssured.given()
                .when()
                .delete(url + "?idValue=" + updatedValue.getId())
                .then()
                .body(idField, Matchers.equalTo(createdValue.getId()))
                .body(nameField, Matchers.equalTo(createdValue.getName() + "upd"))
                .statusCode(200)
                .extract().response();

        Entity deletedValue = response.jsonPath().getObject("", Entity.class);
        Assert.assertNotNull(deletedValue);

        // GET WITHOUT DELETED
        RestAssured.given()
                .when()
                .get(url + "?getAll=getAll")
                .then()
                .body(Matchers.not(Matchers.containsString(gson.toJson(deletedValue))))
                .statusCode(200);
    }
}
