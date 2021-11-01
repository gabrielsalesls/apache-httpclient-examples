package br.com.gabrielsalesls;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ApplicationTest {

    @Test
    void shouldReturnBody() {

        String body = Application.generateBody();
        Assertions.assertNotEquals(null, body);
    }
}
