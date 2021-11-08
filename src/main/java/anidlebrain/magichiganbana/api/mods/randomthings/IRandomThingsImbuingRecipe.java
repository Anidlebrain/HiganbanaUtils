package anidlebrain.magichiganbana.api.mods.randomthings;

import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import lumien.randomthings.recipes.imbuing.ImbuingRecipeHandler;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static anidlebrain.magichiganbana.MagicHiganbana.queuePostInitAction;

/**
 * @author Andileabrain
 */
@ZenRegister
@ZenClass("mods.magichiganbana.RandomThings.ImbuingRecipe")
@SuppressWarnings("unused")
public interface IRandomThingsImbuingRecipe {

    @ZenMethod
    static void addRecipe(IItemStack output, IItemStack ingredient1, IItemStack ingredient2, IItemStack ingredient3, IItemStack toImbue) {
        queuePostInitAction(new IAction() {
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
        queuePostInitAction(new IAction() {
            @Override
            public void apply() {
                ImbuingRecipeHandler.imbuingRecipes.removeIf(ir -> ir.getResult().isItemEqual(CraftTweakerMC.getItemStack(output)));
            }

            @Override
            public String describe() {
                return "Removes a RandomThings ImbuingRecipe Recipe";
            }
        });
    }

    @ZenMethod
    static void removeAll() {
        queuePostInitAction(new IAction() {
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
