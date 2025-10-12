package fr.uvsq.cprog.collex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class Dns {  
    private final List<DnsItem> database = new ArrayList<>();
    private final Path dbPath;

    public Dns() throws IOException {
        Properties props = new Properties();  
        try (var inputStream = getClass().getResourceAsStream("/dns.properties")) {
            if (inputStream == null) {
                throw new IOException("Fichier dns.properties non trouvé");
            }
            props.load(inputStream);
        }
        String fileName = props.getProperty("dns.file", "dns.db");
        this.dbPath = Paths.get(fileName);

        if (!Files.exists(dbPath)) {
            try (var inputStream = getClass().getResourceAsStream("/" + fileName)) {
                if (inputStream != null) {
                    Files.copy(inputStream, dbPath);
                } else {
                    Files.createFile(dbPath);
                }
            }
        }
        loadDatabase();
    }

    private void loadDatabase() throws IOException {
        List<String> lines = Files.readAllLines(dbPath);
        for (String line : lines) {
            if (line.trim().isEmpty()) {
                continue;
            }
            String[] parts = line.trim().split("\\s+", 2);
            if (parts.length == 2) {
                try {
                    database.add(new DnsItem(new AdresseIP(parts[1]), new NomMachine(parts[0])));
                } catch (IllegalArgumentException e) {
                    System.err.println("Erreur de chargement pour ligne : " + line + " - " + e.getMessage());
                }
            }
        }
    }

    public DnsItem getItem(AdresseIP ip) {
        return database.stream()
                .filter(item -> item.getIp().equals(ip))
                .findFirst()
                .orElse(null);
    }

    public DnsItem getItem(NomMachine name) {
        return database.stream()
                .filter(item -> item.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public List<DnsItem> getItems(String domain) {
        return database.stream()
                .filter(item -> item.getName().getDomain().equals(domain))
                .collect(Collectors.toList());
    }

    public void addItem(AdresseIP ip, NomMachine name) throws IOException {
        if (getItem(ip) != null || getItem(name) != null) {
            throw new IllegalArgumentException("L'adresse IP ou le nom de machine existe déjà !");
        }
        DnsItem item = new DnsItem(ip, name);
        database.add(item);
        Files.writeString(dbPath, item.toString() + System.lineSeparator(), StandardOpenOption.APPEND);
    }

    public String getIp(NomMachine name) {
        DnsItem item = getItem(name);
        return item != null ? item.getIp().toString() : null;
    }

    public String getName(AdresseIP ip) {
        DnsItem item = getItem(ip);
        return item != null ? item.getName().toString() : null;
    }

    public void add(AdresseIP ip, NomMachine name) {
    try {
        addItem(ip, name);
    } catch (IOException e) {
        System.err.println("Erreur lors de l'ajout : " + e.getMessage());
    }
}


  
}