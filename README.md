# vault-http-java
Java implementation of the [Vault](https://www.vaultproject.io/) project HTTP API.
Mainly inspired by [@jhaals](https://github.com/jhaals/vault-java/) his Project.

### What is vault?
Just read [this](https://www.vaultproject.io/) :)

### How to implement?
##### Maven
    <dependency>
      <groupId>dev.dotmatthew</groupId>
      <artifactId>vault-http-java</artifactId>
      <version>1.0.0</version>
    </dependency>

### Examples (made by [@jhaals](https://github.com/jhaals/))

##### Read

    Vault vault = new Vault("http://127.0.0.1:8200", "c7e6d2f3-dc1a-a841-cf29-0cf7bec8ed42");

    VaultResponse vaultResponse = vault.read("aws/creds/deploy");
    System.out.println(vaultResponse.getData().get("access_key"));
    System.out.println(vaultResponse.getData().get("secret_key"));

    System.out.println(vault.readPath("secret/hello").getData().get("value"));

##### Write

	HashMap<String, String> data = new HashMap<>();
	data.put("value", "hello");
	vault.write("secret/hello", data);

##### Delete

	vault.delete("secret/hello");
