package tests;

import java.util.Iterator;

import cuisine.Ingredient;
import cuisine.IngredientQuantifie;
import cuisine.Instruction;
import cuisine.Plat;
import cuisine.Plat.Cout;
import cuisine.Plat.Difficulte;
import cuisine.Unite;

public class Main {

	public static void main(String[] args) {

		Plat plat = null;
		plat = new Plat("Waterzooi", 4, Difficulte.XX, Cout.$$$);

		Instruction instruction = new Instruction("Couper les l�gumes", 15);
		try {
			plat.insererInstruction(0,instruction);
		} catch(IllegalArgumentException iae){}
		plat.ajouterInstruction(instruction);
		instruction = new Instruction("Faire revenir les l�gumes", 5);
		plat.ajouterInstruction(instruction);
		instruction = new Instruction("Laisser mijoter jusqu'� cuisson du poulet",50);
		try {
			plat.insererInstruction(4,instruction);
		} catch(IllegalArgumentException iae){}
		plat.ajouterInstruction(instruction);
		instruction = new Instruction("Laisser l�g�rement refroidir", 3);
		plat.ajouterInstruction(instruction);
		instruction = new Instruction("Ajouter la cr�me et servir", 0);
		plat.ajouterInstruction(instruction);
		instruction = new Instruction("Laisser mijoter jusqu'� cuisson du poulet", 67);
		plat.remplacerInstruction(3,instruction);
		instruction = new Instruction("Ajouter le poulet", 0);
		plat.insererInstruction(3,instruction);
		plat.supprimerInstruction(5);

		Iterator<Instruction> instructionIterator = plat.instructions();
		while(instructionIterator.hasNext()){
			instruction = instructionIterator.next();
		}
		try{
			instructionIterator.remove();
		} catch (UnsupportedOperationException uoe){}

		Ingredient ing = new Ingredient("Blanc de poulet");
		plat.ajouterIngredient(ing,400, Unite.GRAMME);
		ing = new Ingredient("C�leri");
		plat.ajouterIngredient(ing,200, Unite.GRAMME);
		ing = new Ingredient("Carottes");
		plat.ajouterIngredient(ing, 2);
		ing = new Ingredient("jus de citron");
		plat.ajouterIngredient(ing,10,Unite.MILLILITRE);
		ing = new Ingredient("Sel");
		plat.ajouterIngredient(ing,1, Unite.PINCEE);
		ing = new Ingredient("Cr�me fraiche");
		plat.ajouterIngredient(ing,10, Unite.CENTILITRE);
		plat.modifierIngredient(new Ingredient("Blanc de poulet"), 600,Unite.GRAMME);
		plat.supprimerIngredient(new Ingredient("jus de citron"));
		IngredientQuantifie ingQuantifie = plat.trouverIngredientQuantifie(new Ingredient("Blanc de poulet"));
		System.out.println("Quantit� de blanc de poulet n�cessaire : " + ingQuantifie.getQuantite() + " " + ingQuantifie.getUnite()+"\n");
		System.out.println(plat);
	}

}
