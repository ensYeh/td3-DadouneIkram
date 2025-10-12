package fr.uvsq.cprog.collex.commands;

import fr.uvsq.cprog.collex.Dns;
import fr.uvsq.cprog.collex.NomMachine;
import fr.uvsq.cprog.collex.DnsTUI;

public class GetIpCommand implements Commande {
    private final Dns dns;
    private final NomMachine name;
    private final DnsTUI tui;

    public GetIpCommand(Dns dns, NomMachine name, DnsTUI tui) {
        this.dns = dns;
        this.name = name;
        this.tui = tui;
    }

    @Override
    public String execute() {
        String ip = dns.getIp(name);
        tui.affiche("IP for " + name + ": " + (ip != null ? ip : "Not found"));
        return ip;
    }
}