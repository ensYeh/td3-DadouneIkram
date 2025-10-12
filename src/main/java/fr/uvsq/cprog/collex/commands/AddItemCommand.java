package fr.uvsq.cprog.collex.commands;

import fr.uvsq.cprog.collex.Dns;
import fr.uvsq.cprog.collex.AdresseIP;
import fr.uvsq.cprog.collex.NomMachine;
import fr.uvsq.cprog.collex.DnsTUI;

public class AddItemCommand implements Commande {
    private final Dns dns;
    private final AdresseIP ip;
    private final NomMachine name;
    private final DnsTUI tui;

    public AddItemCommand(Dns dns, AdresseIP ip, NomMachine name, DnsTUI tui) {
        this.dns = dns;
        this.ip = ip;
        this.name = name;
        this.tui = tui;
    }

    @Override
    public String execute() {
        dns.add(ip, name); 
        tui.affiche("Added " + name + " with IP " + ip);
        return null;
    }
}