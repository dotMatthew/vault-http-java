package dev.dotmatthew.vault;

import dev.dotmatthew.vault.exceptions.VaultResponseCodeException;
import dev.dotmatthew.vault.exceptions.VaultServerException;
import dev.dotmatthew.vault.response.VaultResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

/**
 * Created by Mathias Dollenbacher on 21.02.21.
 *
 * <p>
 *
 * @author Mathias Dollenbacher
 */
public class Vault {

    private final OkHttpClient client;

    private final String vaultServer;
    private final String vaultToken;

    public Vault(@NotNull final String vaultServer,@NotNull final String vaultToken) {
        this.vaultServer = vaultServer;
        this.vaultToken = vaultToken;
        this.client = new OkHttpClient();
    }

    public VaultResponse readPath(@NotNull String path) {
        final Request request = new Request.Builder()
                .url(vaultServer+"/v1/"+path)
                .addHeader("X-Vault-Token", this.vaultToken)
                .build();

        try (Response response = client.newCall(request).execute()) {

            final String jsonResponse = Objects.requireNonNull(response.body()).string();

            if(response.code() != 200) {
                final JSONArray array = new JSONObject(jsonResponse).getJSONArray("errors");
                throw new VaultResponseCodeException(
                        "The response was not succesful (Code: "
                                + response.code()+")!" +
                                "\n\tMessage: "
                                + array.get(0).toString().trim()
                                + "\n");
            }
            
            System.out.println("Bis hier hin gehts!");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
