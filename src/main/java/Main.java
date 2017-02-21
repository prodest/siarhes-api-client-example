import model.AuthToken;
import model.Provimento;
import model.Vinculo;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import service.AuthInterceptor;
import service.DevHttpClient;
import service.SiarhesAPI;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/*
 * Links úteis:
 * https://square.github.io/retrofit/
 * https://github.com/square/okhttp/wiki/Recipes
 */
public class Main {

    private static String authUrl;
    private static String apiUrl;
    private static String clientId;
    private static String clientSecret;

    private static SiarhesAPI mAPI;

    public static void main(String[] args) throws IOException {
        loadConfig();
        initClient();

        List<Vinculo> vinculos = getResponseBody(mAPI.getVinculos(434064, null, null).execute());
        for (Vinculo v : vinculos) {
            List<Provimento> provimentos = getResponseBody(mAPI.getProvimentos(v.getNumFunc(), v.getNumero()).execute());
            v.setProvimentos(provimentos);

            System.out.println("Vínculo:\n" + v.toString());
            System.out.println("    Provimentos:");
            for (Provimento p : v.getProvimentos()) {
                System.out.println("    " + p.toString());
            }
        }
    }

    /**
     * Carrega a configuração do arquivo Config.ini.
     * @throws IOException Caso não consiga ler o arquivo.
     */
    private static void loadConfig() throws IOException {
        Properties defaultProps = new Properties();
        FileInputStream in = new FileInputStream("config.ini");
        defaultProps.load(in);
        in.close();

        authUrl = defaultProps.getProperty("auth_url");
        apiUrl = defaultProps.getProperty("api_url");
        clientId = defaultProps.getProperty("client_id");
        clientSecret = defaultProps.getProperty("client_secret");
    }

    /**
     * Pega o token de autenticação usando as configurações e cria o cliente da api. O token tem a duração de 1h, então
     * cada execução do aplicativo deve ser obtido um novo token;
     * @throws IOException Caso falhe a autenticação.
     */
    private static void initClient() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .client(DevHttpClient.getClient()) // Usado para aceitar o certificado auto-assinado de desenv, remover em produção.
                .baseUrl(authUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SiarhesAPI siarhesAPI = retrofit.create(SiarhesAPI.class);

        Response<AuthToken> response =
                siarhesAPI.getAuthToken("client_credentials", clientId, clientSecret, "siarhes_basico")
                        .execute();

        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody().string());
        } else {
            AuthToken authToken = response.body();
            System.out.println(authToken);

            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .addInterceptor(new AuthInterceptor(authToken))
                    .build();

            mAPI = new Retrofit.Builder()
                    .baseUrl(apiUrl)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(SiarhesAPI.class);
        }
    }

    /**
     * Função genérica que verifica a resposta e retorna o corpo, ou erro.
     * @param response Resposta da requisição http.
     * @return Resposta com o mesmo tipo que foi passado como parâmetro.
     * @throws IOException Caso o código de retorno seja diferente de 200.
     */
    private static <E> E getResponseBody(Response<E> response) throws IOException {
        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody().string());
        } else {
            return response.body();
        }
    }

}
