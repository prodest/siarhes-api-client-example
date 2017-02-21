package service;

import model.AuthToken;
import model.Provimento;
import model.Vinculo;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * Created by Vinicius Avellar.
 * Interface que define os endpoints da API, é através desta interface que as chamadas são feitas pelo Retrofit.
 */
public interface SiarhesAPI {

    /**
     * Url para autenticação e obtenção do token.
     * @param grantType Sempre client_credentials.
     * @param clientId Seu client_id.
     * @param clientSecret Seu client_secret.
     * @param scope Scope que se deseja ter acesso, padrão: siarhes_basico.
     * @return Objeto com as informações do token.
     */
    @FormUrlEncoded
    @POST("prodest/acessocidadao/is/connect/token")
    Call<AuthToken> getAuthToken(
            @Field("grant_type") String grantType,
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("scope") String scope);

    /**
     * Retorna uma chamada para a lista de vínculos.
     * @param numfunc Filtrar por Número Funcional.
     * @param numvinc Filtrar por Número do Vínculo.
     * @param situacao Filtrar por Situação.
     * @return Chamada para a API.
     */
    @GET("v1/vinculos")
    Call<List<Vinculo>> getVinculos(
            @Query("numfunc") Integer numfunc,
            @Query("numvinc") Integer numvinc,
            @Query("situacao") String situacao);

    /**
     * Retorna uma chamada para a lista de provimentos.
     * Esta chamada pode ter mais parâmetros, veja a documentação.
     * @param numfunc Número Funcional (Obrigatório).
     * @param numvinc Número do Vínculo (Obrigatório).
     * @return Chamada para a API.
     */
    @GET("v1/provimentos")
    Call<List<Provimento>> getProvimentos(
            @Query("numfunc") Integer numfunc,
            @Query("numvinc") Integer numvinc);

}
