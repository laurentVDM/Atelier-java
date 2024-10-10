import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public abstract class Pizza implements Iterable<Ingredient>{

  private static double PRIX_BASE = 5.0;
  private ArrayList<Ingredient> ingredients;
  private String title;
  private String description;

  public Pizza(String title, String description) {
    this.title = title;
    this.description = description;
    this.ingredients = new ArrayList<Ingredient>();
  }

  public Pizza(String title, String description, ArrayList<Ingredient> listParam) {
    this(title, description);
    for (Ingredient i : ingredients) {
      if (this.ingredients.contains(i))
        throw new IllegalArgumentException("Il ne peut pas y avoir deux fois le même ingrédient dans une pizza.");
      this.ingredients.add(i);
    }
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public boolean ajouter(Ingredient ingredient) {
    if(ingredients.contains(ingredient)) {
      return false;
    }
    ingredients.add(ingredient);
    return true;
  }

  public boolean supprimer(Ingredient ingredient) {
    if(ingredient == null)
      throw new IllegalArgumentException();
    if(!this.ingredients.contains(ingredient)) {
      return false;
    }
    ingredients.remove(ingredient);
    return true;
  }

  public double calculerPrix() {
    double prix = PRIX_BASE;
    for (Ingredient i: ingredients) {
      prix += i.getPrix();
    }
    return prix;
  }

  @Override
  public String toString() {
    String infos = title + "\n" + description + "\nIngrédients : ";
    for (Ingredient ingredient : ingredients){
      infos +="\n" + ingredient.getNom();
    }
    infos +="\nprix : " + calculerPrix() + " euros";
    return infos;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Pizza that = (Pizza) o;
    return Objects.equals(title, that.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title);
  }
}
