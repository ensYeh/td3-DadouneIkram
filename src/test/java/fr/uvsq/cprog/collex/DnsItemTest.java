package fr.uvsq.cprog.collex;

import org.junit.Test;
import static org.junit.Assert.*;

public class DnsItemTest {

    @Test
    public void testGetters() {
        AdresseIP ip = new AdresseIP("192.168.0.1");
        NomMachine name = new NomMachine("example.com");
        DnsItem item = new DnsItem(ip, name);

        assertEquals(ip, item.getIp());
        assertEquals(name, item.getName());
    }

    @Test
    public void testToString() {
        AdresseIP ip = new AdresseIP("8.8.8.8");
        NomMachine name = new NomMachine("google.com");
        DnsItem item = new DnsItem(ip, name);

        assertEquals("google.com 8.8.8.8", item.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        AdresseIP ip1 = new AdresseIP("1.1.1.1");
        NomMachine name1 = new NomMachine("cloudflare.com");
        DnsItem item1 = new DnsItem(ip1, name1);
        DnsItem item2 = new DnsItem(new AdresseIP("1.1.1.1"), new NomMachine("cloudflare.com"));
        DnsItem item3 = new DnsItem(new AdresseIP("8.8.8.8"), new NomMachine("google.com"));

        assertEquals(item1, item2);
        assertNotEquals(item1, item3);
        assertEquals(item1.hashCode(), item2.hashCode());
        assertNotEquals(item1.hashCode(), item3.hashCode());
    }

    @Test
    public void testEqualsWithDifferentObject() {
        AdresseIP ip = new AdresseIP("127.0.0.1");
        NomMachine name = new NomMachine("localhost");
        DnsItem item = new DnsItem(ip, name);

        assertNotEquals(item, null);
        assertNotEquals(item, "some string");
    }
}
