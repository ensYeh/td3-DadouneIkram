package fr.uvsq.cprog.collex;

import java.io.IOException;
import fr.uvsq.cprog.collex.commands.Commande;


public class DnsApp {

    private final Dns dns;
    private final DnsTUI tui;

  
    public DnsApp() throws IOException {
        this.dns = new Dns();
        this.tui = new DnsTUI(dns, null);
    }

  
    public void run() {
        System.out.println("=== DnsApp - Simulation DNS ===");
        System.out.println("Tapez une commande (ou 'quit' pour quitter)");

        boolean running = true;
        while (running) {
            try {
                Commande cmd = tui.nextCommande();
                if (cmd == null) {
                    continue; 
                }

                cmd.execute();

                if (cmd.getClass().getSimpleName().equalsIgnoreCase("QuitCommande")) {
                    running = false;
                }

            } catch (Exception e) {
                System.err.println("Erreur : " + e.getMessage());
            }
        }

        System.out.println("Fin du programme DNS.");
    }

    /**
 * Point d'entrée du programme.
 * @param args arguments de la ligne de commande
 */
public static void main(String[] args) {

        try {
            new DnsApp().run();
        } catch (IOException e) {
            System.err.println("Erreur d'initialisation : " + e.getMessage());
        }
    }
}
