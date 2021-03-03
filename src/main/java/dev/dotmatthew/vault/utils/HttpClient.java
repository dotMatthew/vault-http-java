package dev.dotmatthew.vault.utils;

import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;

import javax.net.ssl.*;

/**
 * Created by Mathias Dollenbacher on 03.03.21.
 *
 * <p> https://stackoverflow.com/questions/50961123/how-to-ignore-ssl-error-in-okhttp
 * this is not my class!
 *
 * @author Mathias Dollenbacher
 */
public class HttpClient {

    public static OkHttpClient trustAllSslClient(@NotNull final OkHttpClient client) {
        final OkHttpClient.Builder builder = client.newBuilder();
        builder.sslSocketFactory(trustAllSslSocketFactory, (X509TrustManager)trustAllCerts[0]);
        builder.hostnameVerifier((hostname, session) -> true);
        return builder.build();
    }

    public static final TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[]{};
                }
            }
    };

    public static final SSLContext trustAllSslContext;

    static {
        try {
            trustAllSslContext = SSLContext.getInstance("SSL");
            trustAllSslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static final SSLSocketFactory trustAllSslSocketFactory = trustAllSslContext.getSocketFactory();

}
