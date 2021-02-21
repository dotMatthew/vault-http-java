package dev.dotmatthew.vault.response;

import dev.dotmatthew.vault.Vault;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Created by Mathias Dollenbacher on 21.02.21.
 *
 * <p>
 *
 * @author Mathias Dollenbacher
 */
@AllArgsConstructor @Getter
public class VaultResponse {

    private final String requestId;
    private final String leaseId;
    private final boolean renewable;
    private final int leaseDuration;
    private final Map<String, Object> data;

}
