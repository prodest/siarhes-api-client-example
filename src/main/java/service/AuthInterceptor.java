package service;

import model.AuthToken;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Created by Vinicius Avellar.
 * Classe responsável por colocar o HEADER Authorization em cada requisição feita na API.
 */
public class AuthInterceptor implements Interceptor {

    private AuthToken authToken;

    public AuthInterceptor(AuthToken authToken) {
        this.authToken = authToken;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request newRequest = chain.request().newBuilder()
                .addHeader("Authorization", authToken.getTokenType() + " " + authToken.getAccessToken())
                .build();

        return chain.proceed(newRequest);
    }

}
