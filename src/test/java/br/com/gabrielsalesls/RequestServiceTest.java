package br.com.gabrielsalesls;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import static java.util.Objects.requireNonNull;

class RequestServiceTest {

    private static String VALID_RESPONSE;
    public MockWebServer mockWebServer;

    @BeforeEach
    void init() {
        this.mockWebServer = new MockWebServer();
    }

    @Test
    void getElementTest() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {

        String body = "{\n" +
                "    \"args\": {\n" +
                "        \"element\": \"1\"\n" +
                "    }\n" +
                "}";

        mockWebServer.enqueue(new MockResponse()
                .setBody(body)
                .addHeader("Content-Type", "application/json"));

        HttpClientConfig httpClientConfig = new HttpClientConfig();
        CloseableHttpClient httpClient = httpClientConfig.getClient();
        RequestService requestService = new RequestService(httpClient);

        String url = "https://httpbin.org/get?element=1";
        String customKey = "custom-key";

        String element = requestService.getRequest(mockWebServer.url("/").toString(), customKey);

        Assertions.assertNotEquals(null, element);
    }

    @Test
    void testPostId() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {

        String body = "{\n" +
                "    \"args\": {\n" +
                "        \"element\": \"1\"\n" +
                "    }\n" +
                "}";

        mockWebServer.enqueue(new MockResponse()
                .setBody(body)
                .addHeader("Content-Type", "application/json"));

        HttpClientConfig httpClientConfig = new HttpClientConfig();
        CloseableHttpClient httpClient = httpClientConfig.getClient();
        RequestService requestService = new RequestService(httpClient);

        String url = "https://httpbin.org/get?element=1";
        String customKey = "custom-key";

        String element = requestService.postRequest(mockWebServer.url("/").toString(), body, "1");

        Assertions.assertNotEquals(null, element);
    }

}
