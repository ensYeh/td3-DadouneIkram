package fr.uvsq.cprog.collex;

import org.junit.Test;
import static org.junit.Assert.*;

public class NomMachineTest {

    @Test
    public void testValidNameWithoutDomain() {
        NomMachine nm = new NomMachine("localhost");
        assertEquals("localhost", nm.getName());
        assertEquals("", nm.getDomain());
        assertEquals("localhost", nm.getFullName());
        assertEquals("localhost", nm.toString());
    }

    @Test
    public void testValidNameWithDomain() {
        NomMachine nm = new NomMachine("example.com");
        assertEquals("example", nm.getName());
        assertEquals("com", nm.getDomain());
        assertEquals("example.com", nm.getFullName());
        assertEquals("example.com", nm.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidNameNull() {
        new NomMachine(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidNameEmpty() {
        new NomMachine("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidNameSpecialChars() {
        new NomMachine("exa$mple.com");
    }

    @Test
    public void testEqualsAndHashCode() {
        NomMachine nm1 = new NomMachine("example.com");
        NomMachine nm2 = new NomMachine("example.com");
        NomMachine nm3 = new NomMachine("example.net");

        assertEquals(nm1, nm2);
        assertNotEquals(nm1, nm3);
        assertEquals(nm1.hashCode(), nm2.hashCode());
        assertNotEquals(nm1.hashCode(), nm3.hashCode());
    }

    @Test
    public void testEqualsWithDifferentObject() {
        NomMachine nm = new NomMachine("example.com");
        assertNotEquals(nm, null);
        assertNotEquals(nm, "example.com");
    }
}
