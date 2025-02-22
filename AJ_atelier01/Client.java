import java.util.Objects;

public class Client {

  private static int numeroSuivant = 1;
  private int numero;
  private String nom;
  private String prenom;
  private String telephone;
  private Commande commandeEnCours;

  public Client(String nom, String prenom, String telephone) {
    this.nom = nom;
    this.prenom = prenom;
    this.telephone = telephone;
    this.numero = numeroSuivant++;
  }

  public int getNumero() {
    return numero;
  }

  public String getNom() {
    return nom;
  }

  public String getPrenom() {
    return prenom;
  }

  public String getTelephone() {
    return telephone;
  }

  public Commande getCommandeEnCours() { return commandeEnCours; }

  /**
   * @return représentation textuelle du client
   */
  @Override
  public String toString() {
    return "client numero " + numero + " (" + prenom  + " " + nom + ", telephone : " + telephone +")";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Client client = (Client) o;
    return numero == client.numero;
  }

  @Override
  public int hashCode() {
    return Objects.hash(numero);
  }

  public boolean enregistrer(Commande commande) {
    if(commandeEnCours != null)
      return false;
    if(commande.getClient() != this)
      return false;
    commandeEnCours = commande;
    return true ;
  }

  public boolean cloturerCommandeEnCours() {
    if(commandeEnCours !=null) {
      commandeEnCours = null;
      return true;
    }
    return false;
  }
}
