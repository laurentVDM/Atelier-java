import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Iterator;

public class Commande {

  private static int numeroSuivant = 1;
  private int numero;
  private Client client;
  private LocalDateTime date;
  private ArrayList<LigneDeCommande> lignesCommande;

  public Commande(Client client) {
    if(client.getCommandeEnCours() != null) {
      throw new IllegalArgumentException("impossible de créer une commande pour un client ayant encore une commande en cours");
    }
    this.client = client;
    this.date = LocalDateTime.now();
    this.numero = numeroSuivant++;
    client.enregistrer(this);
    lignesCommande = new ArrayList<>();
  }

  public int getNumero() {
    return numero;
  }

  public Client getClient() {
    return client;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public boolean ajouter(Pizza pizza, int quantite){
    if (getClient().getCommandeEnCours() != this){
      return false;
    }
    LigneDeCommande temp = new LigneDeCommande(pizza,quantite);

    for (LigneDeCommande l : lignesCommande) {
      if (l.getPizza() == temp.getPizza()) {
        l.setQuantite(l.getQuantite() + quantite);
        return true;
      }
    }
    lignesCommande.add(temp);
    return true;
  }

  public boolean ajouter(Pizza pizza){
    return ajouter(pizza, 1);
  }

  public double calculerMontantTotal() {
    double total = 0;
    for(LigneDeCommande l : lignesCommande) {
      total += (l.getQuantite() * l.getPrixUnitaire());
    }
    return total;
  }

  public String detailler() {
    String detail ="";
    for(LigneDeCommande l : lignesCommande) {
      detail+= l + "\n";
    }
    return detail;
  }

  public Iterator<LigneDeCommande> iterator() {
    return null;
  }

  public String toString() {
    DateTimeFormatter formater = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
    String encours = "";
    if (client.getCommandeEnCours() == this)
      encours = " (en cours)";
    return "Commande n° " + numero + encours + " du " + client + "\ndate : " + formater.format(date);
  }
}
