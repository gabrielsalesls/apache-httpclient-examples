package br.com.gabrielsalesls;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Requests {

    static final Logger logger = LoggerFactory.getLogger(Requests.class);

    CloseableHttpClient httpClient;

    public Requests(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public String getId(String url, String autorization) throws IOException {
        String argumento;

        try {
            HttpGet request = new HttpGet(url);

            request.addHeader(autorization, "sales");
            request.addHeader(HttpHeaders.USER_AGENT, "GoogleBot");

            CloseableHttpResponse response = httpClient.execute(request);

            try {

                HttpEntity entity = response.getEntity();
                String json = EntityUtils.toString(entity);

                JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
                JsonObject argumentos = jsonObject.getAsJsonObject("args");
                argumento = argumentos.get("element").getAsString();

            }catch (NullPointerException ex){
                throw new NullPointerException("Element not found");
            } finally {
                response.close();
            }
        } finally {
            httpClient.close();
        }
        return argumento;
    }
}
