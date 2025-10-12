package fr.uvsq.cprog.collex;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import fr.uvsq.cprog.collex.commands.AddItemCommand;
import fr.uvsq.cprog.collex.commands.Commande;
import fr.uvsq.cprog.collex.commands.GetIpCommand;
import fr.uvsq.cprog.collex.commands.GetNameCommand;
import fr.uvsq.cprog.collex.commands.ListDomainCommand;
import fr.uvsq.cprog.collex.commands.QuitCommand;

public class DnsTUI {
    private final Dns dns;
    private final Scanner scanner;

    public DnsTUI(Dns dns, AtomicBoolean running) {
        this.dns = dns;
        this.scanner = new Scanner(System.in);
    }

    public Commande nextCommande() {
        System.out.print("> ");
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            return null;
        }

        String[] parts = input.split("\\s+");
        try {
            if (parts[0].equals("quit")) {
                return new QuitCommand();
            }
            if (parts[0].equals("ls")) {
                boolean sortByIp = parts.length > 2 && parts[1].equals("-a");
                String domain = sortByIp ? parts[2] : (parts.length > 1 ? parts[1] : "");
                if (domain.isEmpty()) {
                    throw new IllegalArgumentException("Domaine manquant");
                }
                return new ListDomainCommand(dns, domain, sortByIp, this); 
            }
            if (parts[0].equals("add") && parts.length == 3) {
                AdresseIP ip = new AdresseIP(parts[1]);
                NomMachine name = new NomMachine(parts[2]);
                return new AddItemCommand(dns, ip, name, this); 
            }
            if (parts[0].equals("get") && parts.length == 2) {
                try {
                    AdresseIP ip = new AdresseIP(parts[1]);
                    return new GetNameCommand(dns, ip, this);
                } catch (IllegalArgumentException e) {
                    NomMachine name = new NomMachine(parts[1]);
                    return new GetIpCommand(dns, name, this);
                }
            }
            if (parts.length == 1) {
                try {
                    AdresseIP ip = new AdresseIP(parts[0]);
                    return new GetNameCommand(dns, ip, this);
                } catch (IllegalArgumentException ignored) {
                    NomMachine name = new NomMachine(parts[0]);
                    return new GetIpCommand(dns, name, this); 
                }
            }
            throw new IllegalArgumentException("Commande inconnue ou arguments invalides");
        } catch (IllegalArgumentException e) {
            affiche("ERREUR : " + e.getMessage());
            return null;
        }
    }

    public void affiche(String message) {
        System.out.println(message);
    }
}