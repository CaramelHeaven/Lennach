package com.caramelheaven.lennach.network;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Sergey F on 08.01.2018.
 */

public class ApiKeyInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url().newBuilder().build();
        request = request.newBuilder().url(url).build();
        return chain.proceed(request);
    }
}
