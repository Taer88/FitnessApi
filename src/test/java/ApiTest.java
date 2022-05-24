import api.Register;
import api.Specifications;
import api.SucsessReg;
import api.UserData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpHeaders;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Assert;
import org.junit.Test;

import java.net.HttpURLConnection;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

public class ApiTest {
    private final static String URL = "https://api.staging.fit-af.in/";

    String apiKey = "f&a543";

    @Test
    public void checkUsers() {
        Specifications.installSpecifications(Specifications.requestSpec(URL), Specifications.responseSpecOk200());
        List<UserData> members = given()
                .when()
                .header("X-API-Key", apiKey)
                .header("X-Session-Key", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NCwib3MiOiJBUFBMRSIsImlhdCI6MTY1MjY5NTA3MSwiZXhwIjoxNjU1Mjg3MDcxfQ.buDwmCHoinKmZlYgAJ_BeyN53tw6Vs0Crd_9_CIF6TQ")
                .get("api/challenge/get-community-challenge")
                .then().log().all()
                .extract().body().jsonPath().getList("members", UserData.class);
        members.forEach(x -> Assert.assertFalse(x.getFirstName().contains(x.getFitCash().toString()))); //проверямем что имена не содержат данные из ФитКеш

        List<String> firstName = members.stream().map(UserData::getFirstName).collect(Collectors.toList());
        List<String> fitCash = members.stream().map(x -> x.getFitCash().toString()).collect(Collectors.toList());

        for (int i = 0; i < firstName.size(); i++) {
            Assert.assertFalse(firstName.get(i).contains(fitCash.get(i)));
        }
    }

    @Test
            public void successRegTest(){
        Specifications.installSpecifications(Specifications.requestSpec(URL), Specifications.responseSpecOk200());
        Register user = new Register("maxkov+126@messapps.com", "Password1@");
        SucsessReg sucsessReg = given()
                .body(user)
                .when()
                .header("X-API-Key", apiKey)
                .post("api/auth/sign-up")
                .then().log().all()
                .extract().as(SucsessReg.class);
        Assert.assertNotNull(sucsessReg.getId());
        //Assert.assertEquals(Specifications.responseSpecOk200(), 200);
        }
    }

/*
    @Test
    public void testGet() throws KeyStoreException {
        Response response1;
        response1 = given()
                .log().all()
                .when()
                .header("X-API-Key", "f&a543")
                .header("X-Session-Key", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NCwib3MiOiJBUFBMRSIsImlhdCI6MTY1MjY5NTA3MSwiZXhwIjoxNjU1Mjg3MDcxfQ.buDwmCHoinKmZlYgAJ_BeyN53tw6Vs0Crd_9_CIF6TQ")
                .get(URL+"api/profile/get-user-profile")
                .then()
                .log().all()
                .statusCode(200)
                //.body("termsAndConditions", equalTo("https://api.staging.fit-af.in/terms-and-conditions/index.html"))
                //.body("args.a", equalTo("1"))
                .extract()
                .response();
        response1.getBody().print();
    }

 */

