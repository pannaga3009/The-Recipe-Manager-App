package application;

import java.util.LinkedList;
import java.util.Queue;

public class MealPlan {
    private Queue<Recipe> lowCarbRecipes = new LinkedList<>();
    private Queue<Recipe> ketoRecipes = new LinkedList<>();
    private Queue<Recipe> weightLossRecipes = new LinkedList<>();
    
    public void addRecipe(String mealPlan, Recipe recipe) {
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
        }
    }
    
    public Queue<Recipe> getRecipes(String mealPlan) {
        switch(mealPlan) {
            case "Low Carb":
                return lowCarbRecipes;
            case "Keto":
                return ketoRecipes;
            case "Weight Loss":
                return weightLossRecipes;
            default:
                return null;
        }
    }
}

