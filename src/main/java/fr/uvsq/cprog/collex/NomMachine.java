package fr.uvsq.cprog.collex;


public class NomMachine {
  private final String name;
  private final String domain;

  public NomMachine(String fullName) {
    if (!isValidName(fullName)) {
      throw new IllegalArgumentException("Nom de machine invalide : " + fullName);
    }
    String[] parts = fullName.split("\\.", 2);
    this.name = parts[0];
    this.domain = parts.length > 1 ? parts[1] : "";
  }


  private boolean isValidName(String name) {
    if (name == null || name.isEmpty()) {
      return false;
    }
    return name.matches("[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)*");
  }


  public String getName() {
    return name;
  }

  public String getDomain() {
    return domain;
  }

  public String getFullName() {
    return domain.isEmpty() ? name : name + "." + domain;
  }


  @Override
  public String toString() {
    return getFullName();
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NomMachine other = (NomMachine) o;
    return name.equals(other.name) && domain.equals(other.domain);
  }


  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 31 * result + domain.hashCode();
    return result;
  }
}