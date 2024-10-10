package domaine;

public enum Promo {
    PUB("Remise publicitaire"), SOLDE("Remise pour solde"),
    DESTOCKAGE("Remise pour déstockage");

    private String typePromo;

    private Promo(String typePromo) {
        this.typePromo = typePromo;
    }

    public String getTypePromo() {
        return typePromo;
    }


}
