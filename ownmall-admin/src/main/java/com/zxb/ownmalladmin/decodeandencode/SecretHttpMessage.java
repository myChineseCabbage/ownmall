package com.zxb.ownmalladmin.decodeandencode;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;

import java.io.IOException;
import java.io.InputStream;

public class SecretHttpMessage  implements HttpInputMessage {
    private  InputStream body;
    private  HttpHeaders httpHeaders;

    public SecretHttpMessage(InputStream body, HttpHeaders httpHeaders) {
        this.body = body;
        this.httpHeaders = httpHeaders;
    }

    @Override
    public InputStream getBody() throws IOException {
        return this.body;
    }

    @Override
    public HttpHeaders getHeaders() {
        return this.httpHeaders;
    }
}
