package fr.uvsq.cprog.collex.commands;

import fr.uvsq.cprog.collex.Dns;
import fr.uvsq.cprog.collex.DnsTUI;

public class ListDomainCommand implements Commande {
    private final String domain;
    private final boolean sortByIp;
    private final DnsTUI tui;

    public ListDomainCommand(Dns dns, String domain, boolean sortByIp, DnsTUI tui) {
        this.domain = domain;
        this.sortByIp = sortByIp;
        this.tui = tui;
    }

  @Override
public String execute() {
    tui.affiche("Listing domains for: " + domain + " (sort by IP: " + sortByIp + ")");
    return null;
}

}