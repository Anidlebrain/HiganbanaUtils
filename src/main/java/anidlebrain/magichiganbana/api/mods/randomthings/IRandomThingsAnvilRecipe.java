package anidlebrain.magichiganbana.api.mods.randomthings;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import lumien.randomthings.recipes.anvil.AnvilRecipeHandler;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static anidlebrain.magichiganbana.MagicHiganbana.queuePostInitAction;

@ZenRegister
@ZenClass("mods.magichiganbana.RandomThings.AnvilRecipe")
@SuppressWarnings("unused")
public interface IRandomThingsAnvilRecipe {
    @ZenMethod
    static void addRecipe(IItemStack output, IItemStack ingredient1, IItemStack ingredient2, int cost)
    {
        queuePostInitAction(new IAction() {
            @Override
            public void apply() {
                AnvilRecipeHandler.addAnvilRecipe(CraftTweakerMC.getItemStack(ingredient1),
                        CraftTweakerMC.getItemStack(ingredient2),
                        CraftTweakerMC.getItemStack(output),
                        cost);
            }

            @Override
            public String describe() {
                return "Adds a RandomThings AnvilRecipe Recipe";
            }
        });

    }

    @ZenMethod
    static void removeRecipe(IItemStack output)
    {
        queuePostInitAction(new IAction() {
            @Override
            public void apply() {
                AnvilRecipeHandler.getAllRecipes().removeIf(ir -> ir.getOutput().isItemEqual(CraftTweakerMC.getItemStack(output)));
            }

            @Override
            public String describe() {
                return "Removes a RandomThings AnvilRecipe Recipe";
            }
        });
    }

    @ZenMethod
    static void removeAll() {
        queuePostInitAction(new IAction() {
            @Override
            public void apply() {
                AnvilRecipeHandler.getAllRecipes().clear();
            }

            @Override
            public String describe() {
                return "Removes all recipes for the RandomThings Anvil";
            }
        });
    }
}
