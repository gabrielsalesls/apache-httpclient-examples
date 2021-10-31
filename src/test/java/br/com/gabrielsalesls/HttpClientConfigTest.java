package br.com.gabrielsalesls;

import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

class HttpClientConfigTest {

    @Test
    void shouldReturnAClient() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

        HttpClientConfig httpClientConfig = new HttpClientConfig();

        CloseableHttpClient client = httpClientConfig.getClient();

        Assertions.assertNotEquals(null, client);

    }
}
