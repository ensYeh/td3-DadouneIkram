package fr.uvsq.cprog.collex;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.*;

public class DnsTest {

    private Path tempFile;
    private Dns dns;

    @Before
    public void setUp() throws IOException {
        tempFile = Files.createTempFile("dns_test", ".db");
        Files.writeString(tempFile, "example.com 192.168.0.1\n");

        dns = new Dns() {
            {
                try {
                    java.lang.reflect.Field field = Dns.class.getDeclaredField("dbPath");
                    field.setAccessible(true);
                    field.set(this, tempFile);
                    java.lang.reflect.Field dbField = Dns.class.getDeclaredField("database");
                    dbField.setAccessible(true);
                    ((java.util.List)dbField.get(this)).clear();
                    loadDatabase();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            protected void loadDatabase() throws IOException {
                super.loadDatabase();
            }
        };
    }

    @After
    public void tearDown() throws IOException {
        Files.deleteIfExists(tempFile);
    }

    @Test
    public void testGetItemByIp() {
        AdresseIP ip = new AdresseIP("192.168.0.1");
        DnsItem item = dns.getItem(ip);
        assertNotNull(item);
        assertEquals("example.com", item.getName().getFullName());
    }

    @Test
    public void testGetItemByName() {
        NomMachine name = new NomMachine("example.com");
        DnsItem item = dns.getItem(name);
        assertNotNull(item);
        assertEquals("192.168.0.1", item.getIp().getIp());
    }

    @Test
    public void testAddItem() throws IOException {
        AdresseIP ip = new AdresseIP("10.0.0.1");
        NomMachine name = new NomMachine("test.com");
        dns.addItem(ip, name);

        DnsItem item = dns.getItem(ip);
        assertNotNull(item);
        assertEquals("test.com", item.getName().getFullName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddDuplicateItem() throws IOException {
        AdresseIP ip = new AdresseIP("192.168.0.1");
        NomMachine name = new NomMachine("example.com");
        dns.addItem(ip, name);
    }

    @Test
    public void testGetIp() {
        NomMachine name = new NomMachine("example.com");
        assertEquals("192.168.0.1", dns.getIp(name));
    }

    @Test
    public void testGetName() {
        AdresseIP ip = new AdresseIP("192.168.0.1");
        assertEquals("example.com", dns.getName(ip));
    }
}
