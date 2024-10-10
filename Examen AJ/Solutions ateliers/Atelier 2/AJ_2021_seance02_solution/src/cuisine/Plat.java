package cuisine;

import util.Util;

import java.time.Duration;
import java.util.*;

public class Plat {

    public enum Difficulte{
        X,XX,XXX,XXXX,XXXXX;
        @Override
        public String toString() {
            return super.toString().replace("X","*");
        }
    }

    public enum Cout{
        $,$$,$$$,$$$$,$$$$$;
        @Override
        public String toString() {
            return super.toString().replace("$","�");
        }
    }

    private final String nom;
    private int nbPersonnes;
    private Difficulte niveauDeDifficulte;
    private Cout cout;
    private Duration dureeEnMinutes = Duration.ofMinutes(0);
    private List<Instruction> recette = new ArrayList<Instruction>();
    private Set<IngredientQuantifie> ingredients = new HashSet<IngredientQuantifie>();

    public Plat(String nom, int nbPersonnes, Difficulte niveauDeDifficulte, Cout cout) {
        Util.checkString(nom);
        Util.checkStrictlyPositive(nbPersonnes);
        Util.checkObject(niveauDeDifficulte);
        Util.checkObject(cout);
        this.nom = nom;
        this.nbPersonnes = nbPersonnes;
        this.niveauDeDifficulte = niveauDeDifficulte;
        this.cout = cout;
    }

    public String getNom() {
        return nom;
    }

    public int getNbPersonnes() {
        return nbPersonnes;
    }

    public Difficulte getNiveauDeDifficulte() {
        return niveauDeDifficulte;
    }

    public Cout getCout() {
        return cout;
    }

    public Duration getDureeEnMinutes() {
        return dureeEnMinutes;
    }

    // gestion de la recette et de la dureeEnMinutes

    /** Cette m�thode ins�re l'instruction � la position pr�cis�e (la position commence � 1)
     * @param position la position � laquelle l'instruction doit �tre ins�r�e
     * @param instruction l'instruction � ins�rer
     * @throws IllegalArgumentException en cas de position invalide ou d'instruction null
     */
    public void insererInstruction(int position, Instruction instruction){
        Util.checkStrictlyPositive(position);
        Util.checkObject(instruction);
        if (position > recette.size() + 1) throw new IllegalArgumentException();
        recette.add(position-1,instruction.clone());
        dureeEnMinutes = dureeEnMinutes.plus(instruction.getDureeEnMinutes());
    }

    /** Cette m�thode ajoute l'instruction en fin de la liste
     * @param instruction l'instruction � ajouter
     * @throws IllegalArgumentException en cas d'instruction null
     */
    public void ajouterInstruction (Instruction instruction){
        Util.checkObject(instruction);
        recette.add(instruction.clone());
        dureeEnMinutes = dureeEnMinutes.plus(instruction.getDureeEnMinutes());
    }

    /**
     * Cette m�thode remplace l�instruction de la position pr�cis�e par celle en param�tre (la position commence � 1).
     * @param position la position de l'instruction � remplacer
     * @param instruction la nouvelle instruction
     * @return l'instruction remplac�e
     * @throws IllegalArgumentException en cas de position invalide ou d'instruction null
     */
    public Instruction remplacerInstruction (int position, Instruction instruction){
        Util.checkStrictlyPositive(position);
        Util.checkObject(instruction);
        if (position > recette.size()) throw new IllegalArgumentException();
        Instruction instructionRemplacee = recette.set(position-1,instruction.clone());
        dureeEnMinutes = dureeEnMinutes.minus(instructionRemplacee.getDureeEnMinutes());
        dureeEnMinutes = dureeEnMinutes.plus(instruction.getDureeEnMinutes());
        return instructionRemplacee;
    }

    /**
     * Cette m�thode supprime l�instruction qui se trouve � la position pr�cis�e en param�tre (la position commence � 1).
     * @param position la position de l'instruction � supprimer
     * @return l'instuction supprim�e
     * @throws IllegalArgumentException en cas de position invalide
     */
    public Instruction supprimerInstruction (int position){
        Util.checkStrictlyPositive(position);
        if (position > recette.size() ) throw new IllegalArgumentException();
        Instruction instructionSupprimee = recette.remove(position-1);
        dureeEnMinutes = dureeEnMinutes.minus(instructionSupprimee.getDureeEnMinutes());
        return instructionSupprimee;
    }

    public Iterator<Instruction> instructions(){
        return new InstructionIterator();
    }

    private class InstructionIterator implements Iterator<Instruction>{
        private Iterator<Instruction> instructionIterator = recette.iterator();

        @Override
        public boolean hasNext() {
            return instructionIterator.hasNext();
        }

        @Override
        public Instruction next() {
            return instructionIterator.next().clone();
        }
    }

    //gestion des ingr�dients

    /**
     * Cette m�thode recherche et renvoie l'ingredient quantifi� correspondant � l'ingr�dient pass� en param�tre
     * @param ingredient l'ingr�dient recherch�
     * @return l'ingr�dient quantifi� correspondant s'il existe, null sinon
     * @throws IllegalArgumentException en cas de param�tre null
     */
    private IngredientQuantifie getIngredientQuantifie(Ingredient ingredient) {
        Util.checkObject(ingredient);
        for (IngredientQuantifie ingredientQuantifie : ingredients) {
            if (ingredientQuantifie.getIngredient().equals(ingredient))
                return ingredientQuantifie;
        }
        return null;
    }

    /**
     * Dans le cas o� l'ingr�dient n'est pas encore pr�sent, cette m�thode cr�e et ajoute un ingr�dient quantifi� avec comme quantit� et comme unit�
     * les valeurs pass�es en param�tre.
     * @param ingredient
     * @param quantite la quantit� d�sir�e
     * @param unite l'unit� de mesure
     * @return true si un ingr�dient quantifi� a �t� ajout�, false sinon.
     * @throws IllegalArgumentException en cas de param�tres invalides
     */
    public boolean ajouterIngredient(Ingredient ingredient, int quantite, Unite unite){
        Util.checkObject(unite);
        Util.checkStrictlyPositive(quantite);
        if (getIngredientQuantifie(ingredient) != null) return false;
        ingredients.add(new IngredientQuantifie(ingredient,quantite,unite));
        return true;
    }

    /**
     * Dans le cas o� l'ingr�dient n'est pas encore pr�sent, cette m�thode cr�e et ajoute un ingr�dient quantifi� avec la quantit� pass�e en param�tre
     * et l'unit� NEANT.
     * @param ingredient
     * @param quantite la quantit� d�sir�e
     * @return true si un ingr�dient quantifi� a �t� ajout�, false sinon.
     * @throws IllegalArgumentException en cas de param�tres invalides
     */
    public boolean  ajouterIngredient(Ingredient ingredient, int quantite){
        return ajouterIngredient(ingredient,quantite,Unite.NEANT);
    }

    /**
     * Cette m�thode modifie la quantit� et l'unit� de l'ingr�dient pass� en param�tre si celui-ci est d�j� pr�sent
     * @param ingredient l'ingr�dient dont il faut modifier la quantit� et l'unit�
     * @param quantite la nouvelle quantit�
     * @param unite la nouvelle unit�
     * @return true si l'ingr�dient est pr�sent, false sinon
     * @throws IllegalArgumentException en cas de param�tres invalides
     */
    public boolean modifierIngredient(Ingredient ingredient, int quantite, Unite unite){
        Util.checkObject(unite);
        Util.checkStrictlyPositive(quantite);
        IngredientQuantifie ingredientQuantifie = getIngredientQuantifie(ingredient);
        if (ingredientQuantifie == null) return false;
        ingredientQuantifie.setQuantite(quantite);
        ingredientQuantifie.setUnite(unite);
        return true;
    }

    /**
     * Cette m�thode supprime, s'il existe, l'ingr�dient quantifi� correspondant � l'ingr�dient pass� en param�tre.
     * @param ingredient l'ingr�dient � supprimer
     * @return true si une suppression a �t� effectu�e, false sinon
     * @throws IllegalArgumentException en cas de param�tre invalide
     */
    public boolean supprimerIngredient(Ingredient ingredient){
        IngredientQuantifie ingredientQuantifie = getIngredientQuantifie(ingredient);
        if (ingredientQuantifie == null) return false;
        return ingredients.remove(ingredientQuantifie);
    }

    /**
     * Cette m�thode recherche et renvoie une copie de l'ingredient quantifi� correspondant � l'ingr�dient pass� en param�tre
     * @param ingredient l'ingr�dient recherch�
     * @return une copie de l'ingr�dient quantifi� correspondant s'il existe, null sinon
     * @throws IllegalArgumentException en cas de param�tre null
     */
    public IngredientQuantifie trouverIngredientQuantifie(Ingredient ingredient){
        IngredientQuantifie ingredientQuantifie = getIngredientQuantifie(ingredient);
        if (ingredientQuantifie == null) return null;
        return ingredientQuantifie.clone();
    }

    @Override
    public String toString() {
        String hms = String.format("%d h %02d m", dureeEnMinutes.toHours(), dureeEnMinutes.toMinutesPart());
        String res = this.nom + "\n\n";
        res += "Pour " + this.nbPersonnes + " personnes\n";
        res += "Difficult� : " + this.niveauDeDifficulte + "\n";
        res += "Co�t : " + this.cout + "\n";
        res += "Dur�e : " + hms + " \n\n";
        res += "Ingr�dients :\n";
        for (IngredientQuantifie ing : this.ingredients) {
            res += ing + "\n";
        }
        int i = 1;
        res += "\n";
        for (Instruction instruction : this.recette) {
            res += i++ + ". " + instruction + "\n";
        }
        return res;
    }


}
