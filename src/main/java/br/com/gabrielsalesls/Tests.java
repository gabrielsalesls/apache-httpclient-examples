package br.com.gabrielsalesls;

import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public class Tests {

    static final Logger logger = LoggerFactory.getLogger(Tests.class);

    public static void main(String[] args) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {

        HttpClientConfig httpClientConfig = new HttpClientConfig();
        CloseableHttpClient httpClient = httpClientConfig.getClient();
        Requests requests = new Requests(httpClient);

        String url = "https://httpbin.org/get?element=1";
        String customKey = "custom-key";

        String element = requests.getId(url, customKey);
        logger.info(element);

    }
}