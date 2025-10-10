package fr.uvsq.cprog.collex;


public class AdresseIP {
  private final String ip;

  
  public AdresseIP(String ip) {
    if (!isValidIp(ip)) {
      throw new IllegalArgumentException("Adresse IP invalide : " + ip);
    }
    this.ip = ip;
  }

  private boolean isValidIp(String ip) {
    if (ip == null) {
      return false;
    }
    String[] parts = ip.split("\\.");
    if (parts.length != 4) {
      return false;
    }
    try {
      for (String part : parts) {
        int value = Integer.parseInt(part);
        if (value < 0 || value > 255) {
          return false;
        }
      }
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }


  public String getIp() {
    return ip;
  }

  @Override
  public String toString() {
    return ip;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AdresseIP other = (AdresseIP) o;
    return ip.equals(other.ip);
  }

  @Override
  public int hashCode() {
    return ip.hashCode();
  }
}