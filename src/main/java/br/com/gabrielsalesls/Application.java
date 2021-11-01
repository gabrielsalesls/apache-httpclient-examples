package br.com.gabrielsalesls;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class Application {

    static final Logger logger = LoggerFactory.getLogger(Application.class);
    public static final HttpClientConfig httpClientConfig = new HttpClientConfig();


    public static void main(String[] args) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {

        CloseableHttpClient httpClient = httpClientConfig.getClient();

        // GET
        String urlGet = "https://httpbin.org/get?element=1";
        String header = "custom-key";
        String idFromGetRequest = getIdFromGetRequest(urlGet, header, httpClient);
        logger.info("Get element -> {} ", idFromGetRequest);

        // POST
        String urlPost = "https://httpbin.org/post?id=1";
        String idPost = "1";
        String body = generateBody();
        String idFromPostRequest = getIdFromPostRequest(urlPost, body, idPost, httpClient);
        logger.info("Post element -> {} ", idFromPostRequest);

        httpClient.close();
    }

    public static String generateBody() {
        Gson gsonObj = new Gson().newBuilder().setPrettyPrinting().create();
        Map<Object, Object> bodyMap = new HashMap<>();
        bodyMap.put("file", "file");
        bodyMap.put("origin", "house");
        bodyMap.put("destiny", "work");
        return gsonObj.toJson(bodyMap);
    }

    public static String getIdFromPostRequest(String urlPost, String body, String idPost, CloseableHttpClient httpClient) throws IOException {
        RequestService requestService = new RequestService(httpClient);
        String responseBodyPost = requestService.postRequest(urlPost, body, idPost);
        JsonObject jsonObject = new JsonParser().parse(responseBodyPost).getAsJsonObject();
        JsonObject argumentos = jsonObject.getAsJsonObject("args");
        return argumentos.get("id").getAsString();
    }

    private static String getIdFromGetRequest(String urlGet, String header, CloseableHttpClient httpClient) throws IOException {

        RequestService requestService = new RequestService(httpClient);
        String responseBodyGet = requestService.getRequest(urlGet, header);
        JsonObject jsonObject = new JsonParser().parse(responseBodyGet).getAsJsonObject();
        JsonObject argumentos = jsonObject.getAsJsonObject("args");
        return argumentos.get("element").getAsString();
    }
}


