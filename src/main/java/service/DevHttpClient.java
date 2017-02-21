package service;

import okhttp3.OkHttpClient;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by Vinicius Avellar.
 * HttpClient customizado para ignorar os certificados inválidos do ambiente de desenvolvimento.
 * Não deve ser usado em produção.
 */
public class DevHttpClient {

    public static OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                .sslSocketFactory(getSSLContext().getSocketFactory())
                .build();
    }

    private static SSLContext getSSLContext() {
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {}
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {}
            public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
        }};

        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            return sslContext;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
