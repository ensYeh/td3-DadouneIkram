package fr.uvsq.cprog.collex.commands;

import fr.uvsq.cprog.collex.Dns;
import fr.uvsq.cprog.collex.AdresseIP;
import fr.uvsq.cprog.collex.DnsTUI;

public class GetNameCommand implements Commande {
    private final Dns dns;
    private final AdresseIP ip;
    private final DnsTUI tui;

    public GetNameCommand(Dns dns, AdresseIP ip, DnsTUI tui) {
        this.dns = dns;
        this.ip = ip;
        this.tui = tui;
    }

    @Override
    public String execute() {
        String name = dns.getName(ip); 
        tui.affiche("Name for IP " + ip + ": " + (name != null ? name : "Not found"));
        return name;
    }
}