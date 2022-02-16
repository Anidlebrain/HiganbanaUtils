package anidlebrain.higanbanautils.api.utils;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.recipes.ICraftingRecipe;
import crafttweaker.mc1120.recipes.MCRecipeManager;
import net.minecraft.util.ResourceLocation;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.List;

@ZenRegister
@ZenClass("mods.Higanbana.recipes")
@SuppressWarnings("unused")
public class Recipes {
    static List<ICraftingRecipe> higanbanaRecipes;
    final static MCRecipeManager recipeManager = new MCRecipeManager();
    Recipes() {
        higanbanaRecipes = recipeManager.getAll();
    }

    public void removeRecipe(IIngredient output, @Optional boolean nbtMatch)
    {
        for (IItemStack  item: output.getItems()) {
            if (nbtMatch) {
                higanbanaRecipes.removeIf(recipe -> recipe.getOutput().matchesExact(item));
            }
            else {
                higanbanaRecipes.removeIf(recipe -> recipe.getOutput().matches(item));
            }

        }
        recipeManager.remove(output, nbtMatch);
    }

    public void removeRecipeByName(String recipeName, @Optional IItemStack outputFilter)
    {
        for (ICraftingRecipe recipe: higanbanaRecipes) {
            if(outputFilter != null) {
                if(outputFilter.matches(recipe.getOutput()))
                    higanbanaRecipes.remove(recipe);
            } else {
                higanbanaRecipes.remove(recipe);
            }
        }
        new ResourceLocation("1", "1");
        recipeManager.removeByRecipeName(recipeName, outputFilter);
    }

    @ZenGetter("all")
    @ZenMethod
    public List<ICraftingRecipe> getAllRecipes()
    {
        return higanbanaRecipes;
    }

}
