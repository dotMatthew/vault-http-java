package dev.dotmatthew.vault;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dev.dotmatthew.vault.exceptions.VaultResponseCodeException;
import dev.dotmatthew.vault.response.VaultResponse;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Mathias Dollenbacher on 21.02.21.
 *
 * <p>
 *
 * @author Mathias Dollenbacher
 */
public class Vault {

    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private final OkHttpClient client;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private final String vaultServer;
    private final String vaultToken;

    /**
     *
     * Creates a new instance of the vault class
     *
     * @param vaultServer the address of the vault server
     * @param vaultToken the token to authenticate at the @vaultServer
     */
    public Vault(@NotNull final String vaultServer,@NotNull final String vaultToken) {
        this.vaultServer = vaultServer;
        this.vaultToken = vaultToken;
        this.client = new OkHttpClient();
    }

    /**
     *
     * Read value from the vault
     *
     * @param path the path where the secret is stored
     * @return a response object filled with data
     * @throws VaultResponseCodeException if API returns anything except 200
     */
    public VaultResponse readPath(@NotNull String path) {
        final Request request = new Request.Builder()
                .url(vaultServer+"/v1/"+path)
                .addHeader("X-Vault-Token", this.vaultToken)
                .build();

        try (Response response = client.newCall(request).execute()) {

            final String jsonResponse = Objects.requireNonNull(response.body()).string();

            if(response.code() != 200) {

                throw new VaultResponseCodeException(
                        "The response was not succesful (Code: "
                                + response.code()+")");
            }

            final JsonObject object = this.gson.fromJson(jsonResponse, JsonObject.class);

            return new VaultResponse(
                    object.get("lease_id").getAsString(),
                    object.get("renewable").getAsBoolean(),
                    object.get("lease_duration").getAsInt(),
                    this.gson.fromJson(object.get("data"), Map.class)
            );

        } catch (final IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * Write key/value secret to the vault
     *
     * @param path the path to the secret
     * @param data the pair of data (k/v) to write to the secret
     * @return true if written successfully or false if not
     */
    public boolean write(@NotNull final String path, @NotNull Map<@NotNull String, @NotNull Object> data) {
        final RequestBody body = RequestBody.create(JSON, this.gson.toJson(data));

        final Request request = new Request.Builder()
                .url(vaultServer+"/v1/"+path).
                addHeader("X-Vault-Token", this.vaultToken)
                .post(body)
                .build();

        try (final Response response = client.newCall(request).execute()) {
            return response.code() == 200;
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * Delete a complete path from the vault
     *
     * @param path the path to the secret
     * @return true if the path was deleted successfully or false if not
     */
    public boolean delete(@NotNull final String path) {
        final Request request = new Request.Builder()
                .url(vaultServer+"/v1/"+path)
                .addHeader("X-Vault-Token", this.vaultToken)
                .delete()
                .build();

        try (final Response response = client.newCall(request).execute()) {
            return response.code() == 200;
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}