package dev.dotmatthew.vault.tests.GetTest;

import dev.dotmatthew.vault.Vault;
import dev.dotmatthew.vault.response.VaultResponse;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Mathias Dollenbacher on 21.02.21.
 *
 * <p>
 *
 * @author Mathias Dollenbacher
 */
public class GetTest {

    @Test
    public void GetTest() {

        final Vault vault = new Vault("***REMOVED***", "***REMOVED***");
        final VaultResponse response = vault.readPath("database/creds/temp_user");

        assertNotNull(response);
        assertNotNull(response.getData());
        assertNotNull(response.getLeaseId());
        assertTrue(response.isRenewable());

        System.out.println("Lease Duration: " + response.getLeaseDuration());
        System.out.println("Lease ID: " + response.getLeaseId());
        System.out.println("Is Renewable: " + response.isRenewable());
        response.getData().forEach((k, v) -> System.out.println("Data: Key:" + k + " // Value: " + v));

    }

}
