package dev.dotmatthew.vault.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

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

    private final String leaseId;
    private final boolean renewable;
    private final int leaseDuration;
    private final Map<String, Object> data;

}
