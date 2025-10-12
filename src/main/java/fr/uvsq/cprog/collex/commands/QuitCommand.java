package fr.uvsq.cprog.collex.commands;

public class QuitCommand implements Commande {
    @Override
    public String execute() {
        System.out.println("Exiting DNS application...");
        return "quit";
    }
}
