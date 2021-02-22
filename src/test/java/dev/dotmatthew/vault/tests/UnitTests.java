package dev.dotmatthew.vault.tests;

import dev.dotmatthew.vault.Vault;
import dev.dotmatthew.vault.response.VaultResponse;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Mathias Dollenbacher on 21.02.21.
 *
 * <p>
 *
 * @author Mathias Dollenbacher
 */
public class UnitTests {

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

    @Test
    public void PostTest() {

        final Vault vault = new Vault("***REMOVED***", "***REMOVED***");
        final Map<String, Object> data = new HashMap<>();
        data.put("foo", "bar");
        final boolean response = vault.write("cubbyhole/test", data);

        System.out.println(response);

    }

    @Test
    public void deleteTest() {

        final Vault vault = new Vault("***REMOVED***", "***REMOVED***");

        final Map<String, Object> data = new HashMap<>();

        data.put("foo", "bar");
        data.put("bar", "foo");

        final boolean response = vault.write("cubbyhole/test", data);

        System.out.println(response);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final boolean deleteResponse = vault.delete("cubbyhole/test");

        System.out.println(deleteResponse);
        assertTrue(deleteResponse);

    }

}
