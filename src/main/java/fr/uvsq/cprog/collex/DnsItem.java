package fr.uvsq.cprog.collex;


public class DnsItem {
  private final AdresseIP ip;
  private final NomMachine name;


  public DnsItem(AdresseIP ip, NomMachine name) {
    this.ip = ip;
    this.name = name;
  }


  public AdresseIP getIp() {
    return ip;
  }


  public NomMachine getName() {
    return name;
  }


  @Override
  public String toString() {
    return name.getFullName() + " " + ip.getIp();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DnsItem dnsItem = (DnsItem) o;
    return ip.equals(dnsItem.ip) && name.equals(dnsItem.name);
  }

  @Override
  public int hashCode() {
    int result = ip.hashCode();
    result = 31 * result + name.hashCode();
    return result;
  }
}