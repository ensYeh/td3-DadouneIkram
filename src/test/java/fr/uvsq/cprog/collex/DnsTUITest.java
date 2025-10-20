package fr.uvsq.cprog.collex;

import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.*;

public class DnsTUITest {

    @Test
    public void testQuitCommand() throws IOException {
        String input = "quit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Dns dns = new Dns(); // Assume you have a default constructor
        AtomicBoolean running = new AtomicBoolean(true);
        DnsTUI tui = new DnsTUI(dns, running);

        assertTrue(tui.nextCommande() instanceof fr.uvsq.cprog.collex.commands.QuitCommand);
    }

    @Test
    public void testAddCommand() throws IOException {
        String input = "add 192.168.0.1 example.com\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Dns dns = new Dns();
        AtomicBoolean running = new AtomicBoolean(true);
        DnsTUI tui = new DnsTUI(dns, running);

        assertTrue(tui.nextCommande() instanceof fr.uvsq.cprog.collex.commands.AddItemCommand);
    }

    @Test
    public void testInvalidCommand() throws IOException {
        String input = "unknowncommand\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Capture output
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Dns dns = new Dns();
        AtomicBoolean running = new AtomicBoolean(true);
        DnsTUI tui = new DnsTUI(dns, running);

        assertNull(tui.nextCommande());
        String output = out.toString();
        assertTrue(output.contains("ERREUR"));
    }
}
