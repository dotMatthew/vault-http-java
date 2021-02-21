package dev.dotmatthew.vault.exceptions;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Mathias Dollenbacher on 21.02.21.
 *
 * <p>
 *
 * @author Mathias Dollenbacher
 */
public class VaultResponseCodeException extends RuntimeException {

    public VaultResponseCodeException(@NotNull final String message) {
        super(message);
    }

}
