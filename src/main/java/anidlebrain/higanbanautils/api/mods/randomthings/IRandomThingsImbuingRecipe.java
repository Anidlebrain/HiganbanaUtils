package anidlebrain.higanbanautils.api.mods.randomthings;

import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import lumien.randomthings.recipes.imbuing.ImbuingRecipeHandler;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import anidlebrain.higanbanautils.Higanbana;

/**
 * @author Andileabrain
 */
@ZenRegister
@ZenClass("mods.Higanbana.RandomThings.ImbuingRecipe")
@SuppressWarnings("unused")
public interface IRandomThingsImbuingRecipe {

    @ZenMethod
    static void addRecipe(IItemStack output, IItemStack ingredient1, IItemStack ingredient2, IItemStack ingredient3, IItemStack toImbue) {
        Higanbana.queuePostInitAction(new IAction() {
            @Override
            public void apply() {
                ImbuingRecipeHandler.addRecipe(CraftTweakerMC.getItemStack(ingredient1),
                        CraftTweakerMC.getItemStack(ingredient2),
                        CraftTweakerMC.getItemStack(ingredient3),
                        CraftTweakerMC.getItemStack(toImbue),
                        CraftTweakerMC.getItemStack(output));
            }

            @Override
            public String describe() {
                return "Adds a RandomThings ImbuingRecipe Recipe";
            }
        });

    }

    @ZenMethod
    static void removeRecipe(IItemStack output) {
        Higanbana.queuePostInitAction(new IAction() {
            @Override
            public void apply() {
                ImbuingRecipeHandler.imbuingRecipes.removeIf(recipe -> recipe.getResult().isItemEqual(CraftTweakerMC.getItemStack(output)));
            }

            @Override
            public String describe() {
                return "Removes a RandomThings ImbuingRecipe Recipe";
            }
        });
    }

    @ZenMethod
    static void removeAll() {
        Higanbana.queuePostInitAction(new IAction() {
            @Override
            public void apply() {
                ImbuingRecipeHandler.imbuingRecipes.clear();
            }

            @Override
            public String describe() {
                return "Removes all recipes for the RandomThings Imbuing";
            }
        });
    }

}
