import io.restassured.http.Header;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

class GetRequestTest {

    @Test
    void requestAnswer200() {

        Header header = new Header(
                "Authorization",
                "OAuth oauth_consumer_key=\"RKCGzna7bv9YD57c\"," +
                        "oauth_signature_method=\"HMAC-SHA1\"," +
                        "oauth_timestamp=\"1472121261\"," +
                        "oauth_nonce=\"ki0RQW\"," +
                        "oauth_version=\"1.0\"," +
                        "oauth_signature=\"s0rK92Myxx7ceUBVzlMaxiiXU00%3D\""
        );

        Map<String, String> form = new HashMap<String, String>();
        form.put("code", "xWnkliVQJURqB2x1");
        form.put("grant_type", "authorization_code");
        form.put("redirect_uri", "https://www.getpostman.com/oauth2/callback");
        form.put("client_id", "abc123");
        form.put("client_secret", "ssh-secret");

        given()
                .baseUri("https://postman-echo.com/oauth1")
                .header(header)
                .formParams(form)
                .log().everything()
                .get()
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    void requestAnswer401() {

        Header header = new Header(
                "Authorization",
                "OAuth oauth_consumer_key=\"RKCGzna7bv9YD57c_wrong\"," +
                        "oauth_signature_method=\"HMAC-SHA1\"," +
                        "oauth_timestamp=\"1472121261\"," +
                        "oauth_nonce=\"ki0RQW\"," +
                        "oauth_version=\"1.0\"," +
                        "oauth_signature=\"s0rK92Myxx7ceUBVzlMaxiiXU00%3D\""
        );

        Map<String, String> form = new HashMap<String, String>();
        form.put("code", "xWnkliVQJURqB2x1");
        form.put("grant_type", "authorization_code");
        form.put("redirect_uri", "https://www.getpostman.com/oauth2/callback");
        form.put("client_id", "abc123");
        form.put("client_secret", "ssh-secret");

        given()
                .baseUri("https://postman-echo.com/oauth1")
                .header(header)
                .formParams(form)
                .log().everything()
                .get()
                .then()
                .assertThat()
                .statusCode(401);
    }

    @Test
    void requestAnswerBodyCheck() {

        Header header = new Header(
                "Authorization",
                "OAuth oauth_consumer_key=\"RKCGzna7bv9YD57c_wrong\"," +
                        "oauth_signature_method=\"HMAC-SHA1\"," +
                        "oauth_timestamp=\"1472121261\"," +
                        "oauth_nonce=\"ki0RQW\"," +
                        "oauth_version=\"1.0\"," +
                        "oauth_signature=\"s0rK92Myxx7ceUBVzlMaxiiXU00%3D\""
        );

        Map<String, String> form = new HashMap<String, String>();
        form.put("code", "xWnkliVQJURqB2x1");
        form.put("grant_type", "authorization_code");
        form.put("redirect_uri", "https://www.getpostman.com/oauth2/callback");
        form.put("client_id", "abc123");
        form.put("client_secret", "ssh-secret");

        given()
                .baseUri("https://postman-echo.com/oauth1")
                .header(header)
                .formParams(form)
                .log().everything()
                .get()
                .then()
                .body(containsString("pass"));
    }
}
