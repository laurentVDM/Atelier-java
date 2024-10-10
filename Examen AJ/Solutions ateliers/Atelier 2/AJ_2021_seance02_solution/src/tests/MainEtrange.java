package tests;

import java.time.Duration;
import java.util.Iterator;

import cuisine.Ingredient;
import cuisine.IngredientQuantifie;
import cuisine.Instruction;
import cuisine.Plat;
import cuisine.Plat.Cout;
import cuisine.Plat.Difficulte;
import cuisine.Unite;

public class MainEtrange {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Plat plat = null;
		plat = new Plat("Waterzooi", 4, Difficulte.XX, Cout.$$$);
		// Mauvaise technique mais qui doit fonctionner ici vu les clones
		Instruction instruction = new Instruction("Couper les légumes", 15);
		plat.ajouterInstruction(instruction);
		instruction.setDescription("Faire revenir les légumes");
		instruction.setDureeEnMinutes(Duration.ofMinutes(5));
		plat.insererInstruction(2,instruction);
		instruction.setDescription("Ajouter le poulet");
		instruction.setDureeEnMinutes(Duration.ofMinutes(5));
		plat.ajouterInstruction(instruction);
		instruction.setDescription("Laisser mijoter jusqu'à cuisson du poulet");
		instruction.setDureeEnMinutes(Duration.ofMinutes(67));
		plat.ajouterInstruction(instruction);
		instruction.setDescription("Ajouter le poulet");
		instruction.setDureeEnMinutes(Duration.ofMinutes(0));
		plat.remplacerInstruction(3,instruction);
		instruction.setDescription("Ajouter la crème et servir");
		instruction.setDureeEnMinutes(Duration.ofMinutes(0));
		plat.ajouterInstruction(instruction);
		instruction.setDescription("Danser la macarena");
		instruction.setDureeEnMinutes(Duration.ofMinutes(66));

		Iterator<Instruction> instructionIterator = plat.instructions();
		while(instructionIterator.hasNext()){
			instruction = instructionIterator.next();
			instruction.setDescription("Danser la macarena");
			instruction.setDureeEnMinutes(Duration.ofMinutes(66));
		}
		try{
			instructionIterator.remove();
		} catch (UnsupportedOperationException uoe){}

		Ingredient ing = new Ingredient("Blanc de poulet");
		plat.ajouterIngredient(ing,400, Unite.GRAMME);
		ing = new Ingredient("Céleri");
		plat.ajouterIngredient(ing,200, Unite.GRAMME);
		ing = new Ingredient("Carottes");
		plat.ajouterIngredient(ing, 2);
		ing = new Ingredient("jus de citron");
		plat.ajouterIngredient(ing,10,Unite.MILLILITRE);
		ing = new Ingredient("Sel");
		plat.ajouterIngredient(ing,1, Unite.PINCEE);
		ing = new Ingredient("Crème fraiche");
		plat.ajouterIngredient(ing,10, Unite.CENTILITRE);
		plat.modifierIngredient(new Ingredient("Blanc de poulet"), 600,Unite.GRAMME);
		plat.supprimerIngredient(new Ingredient("jus de citron"));
		IngredientQuantifie ingQuantifie = plat.trouverIngredientQuantifie(new Ingredient("Blanc de poulet"));
		ingQuantifie.setUnite(Unite.PINCEE);
		ingQuantifie.setQuantite(4);
		ingQuantifie = plat.trouverIngredientQuantifie(new Ingredient("Blanc de poulet"));
		System.out.println("Quantité de blanc de poulet nécessaire : " + ingQuantifie.getQuantite() + " " + ingQuantifie.getUnite()+"\n");
		System.out.println(plat);

	}

}
