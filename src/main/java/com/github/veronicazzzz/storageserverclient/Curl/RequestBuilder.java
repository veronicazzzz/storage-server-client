package com.github.veronicazzzz.storageserverclient.Curl;

import okhttp3.Request;
import okhttp3.RequestBody;

public class RequestBuilder {
    public static Request build(String url, String method, RequestBody body) {
        return new Request.Builder()
                .url(url)
                .method(method, body)
                .build();
    }
}
