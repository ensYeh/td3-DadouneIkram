package fr.uvsq.cprog.collex;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import fr.uvsq.cprog.collex.commands.Commande;

public class Main {
    public static void main(String[] args) throws IOException {
        Dns dns = new Dns(); 
        AtomicBoolean running = new AtomicBoolean(true);
        
        DnsTUI tui = new DnsTUI(dns, running);

        while (running.get()) {
            Commande cmd = tui.nextCommande();
            if (cmd != null) {
                cmd.execute();
                if ("quit".equals(cmd.getClass().getSimpleName().replace("Command", "").toLowerCase())) {
                    running.set(false); 
                }
            }
        }

        System.out.println("Application terminated.");
    }
}