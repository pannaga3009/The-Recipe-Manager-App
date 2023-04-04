package application;

import java.util.ArrayList;

public class RecipeStorage {

	private static ArrayList<Recipe> recipes;
    
	RecipeStorage(){
		recipes = new ArrayList<Recipe>();
	}
	
    public static ArrayList<Recipe> getRecipes() {
        return recipes;
    }
    
    public static void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }
    
    public static void removeRecipe(Recipe recipe) {
        recipes.remove(recipe);
    }
	
}
