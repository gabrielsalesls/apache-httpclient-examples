package br.com.gabrielsalesls;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class RequestService {

    static final Logger logger = LoggerFactory.getLogger(RequestService.class);

    CloseableHttpClient httpClient;

    public RequestService(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public String getRequest(String url, String autorization) throws IOException {

        HttpGet request = new HttpGet(url);

        request.addHeader(autorization, "Auth");
        request.addHeader(HttpHeaders.USER_AGENT, "GoogleBot");

        try {
            HttpResponse response = httpClient.execute(request);
            return EntityUtils.toString(response.getEntity());
        } catch (IOException ex) {
            throw new IOException("Element not found");
        }
    }

    public String postRequest(String url, String body, String id) throws IOException {

        HttpPost httpPost = new HttpPost(url);

        StringEntity entity = new StringEntity(body);
        httpPost.setEntity(entity);
        httpPost.setHeader("ID", id);

        try {
            HttpResponse response = httpClient.execute(httpPost);
            return EntityUtils.toString(response.getEntity());
        } catch (IOException exception) {
            throw new IOException("Erro na requisição");
        }
    }
}
