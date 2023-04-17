package application;

import java.util.LinkedList;
import java.util.Queue;

public class MealPlan {
    private static Queue<Recipe> lowCarbRecipes = new LinkedList<>();
    private static Queue<Recipe> ketoRecipes = new LinkedList<>();
    private static Queue<Recipe> weightLossRecipes = new LinkedList<>();
    private static Queue<Recipe> customRecipes = new LinkedList<>();
    
    public static void addRecipe(String mealPlan, Recipe recipe) {
        switch(mealPlan) {
            case "Low Carb":
                lowCarbRecipes.add(recipe);
                break;
            case "Keto":
                ketoRecipes.add(recipe);
                break;
            case "Weight Loss":
                weightLossRecipes.add(recipe);
                break;
            case "Custom Meal Plan":
            	customRecipes.add(recipe);
                break;  
        }
    }
    
    public static Queue<Recipe> getRecipes(String mealPlan) {
        switch(mealPlan) {
            case "Low Carb":
                return lowCarbRecipes;
            case "Keto":
                return ketoRecipes;
            case "Weight Loss":
                return weightLossRecipes;
            case "Custom Meal Plan":
            	return customRecipes;
            default:
                return null;
        }
    }
}

