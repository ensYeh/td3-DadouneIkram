package fr.uvsq.cprog.collex;

import org.junit.Test;
import static org.junit.Assert.*;

public class AdresseIPTest {

    @Test
    public void testValidIp() {
        AdresseIP adresse = new AdresseIP("192.168.0.1");
        assertEquals("192.168.0.1", adresse.getIp());
        assertEquals("192.168.0.1", adresse.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidIp_Null() {
        new AdresseIP(null);
    }

    @Test
    public void testInvalidIp_WrongFormat() {
        assertThrows(IllegalArgumentException.class, () -> new AdresseIP("192.168.1"));
        assertThrows(IllegalArgumentException.class, () -> new AdresseIP("192.168.1.1.1"));
        assertThrows(IllegalArgumentException.class, () -> new AdresseIP("abc.def.ghi.jkl"));
        assertThrows(IllegalArgumentException.class, () -> new AdresseIP("256.168.0.1"));
        assertThrows(IllegalArgumentException.class, () -> new AdresseIP("-1.0.0.0"));
    }

    @Test
    public void testEqualsAndHashCode() {
        AdresseIP ip1 = new AdresseIP("10.0.0.1");
        AdresseIP ip2 = new AdresseIP("10.0.0.1");
        AdresseIP ip3 = new AdresseIP("10.0.0.2");

        assertEquals(ip1, ip2);
        assertNotEquals(ip1, ip3);
        assertEquals(ip1.hashCode(), ip2.hashCode());
        assertNotEquals(ip1.hashCode(), ip3.hashCode());
    }

    @Test
    public void testEqualsWithDifferentObject() {
        AdresseIP ip = new AdresseIP("127.0.0.1");
        assertNotEquals(ip, "127.0.0.1");
        assertNotEquals(ip, null);
    }
}
