package dev.dotmatthew.vault;

import dev.dotmatthew.vault.response.VaultResponse;

/**
 * Created by Mathias Dollenbacher on 21.02.21.
 *
 * <p>
 *
 * @author Mathias Dollenbacher
 */
public class Main {

    public static void main(String[] args) {
        Vault vault = new Vault("https://vault.dotmatthew.dev:8200", "s.NyV3vKMWO57vyGDSkjyuqIkK");
        vault.readPath("database/creds/temp_user");
    }

}
