package model;

/**
 * Created by Vinicius Avellar.
 * Classe que representa os dados de autenticação.
 */
public class AuthToken {


    private String access_token;
    private int expires_in;
    private String token_type;

    public String getAccessToken() {
        return access_token;
    }

    public void setAccessToken(String accessToken) {
        this.access_token = accessToken;
    }

    public int getExpiresIn() {
        return expires_in;
    }

    public void setExpiresIn(int expiresIn) {
        this.expires_in = expiresIn;
    }

    public String getTokenType() {
        return token_type;
    }

    public void setTokenType(String tokenType) {
        this.token_type = tokenType;
    }

    @Override
    public String toString() {
        return getAccessToken();
    }

}
