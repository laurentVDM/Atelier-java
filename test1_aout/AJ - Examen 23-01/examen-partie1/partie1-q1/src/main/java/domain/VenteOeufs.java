package domain;

import domain.Ferme.Acheteur;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class VenteOeufs {

  private LocalDate date;
  private int nombreOeufsVendus;
  private double prixPiece;

  private Acheteur acheteur;

  private String venteId;

  private final static String NOM_ACHETEUR_PAR_DEFAUT = "Particulier";

  public VenteOeufs(LocalDate date, int nombreOeufsVendus, double prixPiece, Acheteur acheteur) {
    this.date = date;
    this.nombreOeufsVendus = nombreOeufsVendus;
    this.prixPiece = prixPiece;
    this.acheteur = acheteur;
    venteId = UUID.randomUUID().toString();
  }

  public LocalDate getDate() {
    return date;
  }

  public double calculerPrixVente() {
    return this.nombreOeufsVendus * this.prixPiece;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    VenteOeufs that = (VenteOeufs) o;

    return venteId.equals(that.venteId);
  }

  @Override
  public int hashCode() {
    return venteId.hashCode();
  }

  public Acheteur getAcheteur() {
    return acheteur;
  }

  public double getPrixPiece() {
    return prixPiece;
  }

  public int getNombreOeufsVendus() {
    return nombreOeufsVendus;
  }

  public String getVenteId() {
    return venteId;
  }

  @Override
  public String toString() {
    return
        "date=" + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            +
            ", nombreOeufsVendus=" + nombreOeufsVendus +
            ", prixPiece=" + prixPiece +
            ", nomAcheteur=" + acheteur.getNom();
  }
}