package anidlebrain.magichiganbana.api.mods.woot;

import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import anidlebrain.magichiganbana.MagicHiganbana;
import ipsis.Woot;

/**
 * @author Andileabrain
 */
@ZenRegister
@ZenClass("mods.magichiganbana.Woot.AnvilRecipe")
@SuppressWarnings("unused")
public interface IWootAnvilRecipe {

    @ZenMethod
    static void addRecipe(IItemStack output, IItemStack base, boolean preserveBase, IItemStack[] inputs)
    {
        MagicHiganbana.queuePostInitAction(new IAction() {
              @Override
              public void apply() {
                  Woot.anvilManager.addRecipe(CraftTweakerMC.getItemStack(output),
                          CraftTweakerMC.getItemStack(base),
                          preserveBase,
                          (Object[])CraftTweakerMC.getItemStacks(inputs));
              }

              @Override
              public String describe() {
                  return "Adds a Woot AnvilRecipe Recipe";
              }
        });
    }

    @ZenMethod
    static void removeRecipe(IItemStack output)
    {
        MagicHiganbana.queuePostInitAction(new IAction() {
            @Override
            public void apply() {
                //CraftTweakerAPI.logInfo("Cleaning up recipes");
                Woot.anvilManager.getRecipes().removeIf(recipe -> recipe.isOutput(CraftTweakerMC.getItemStack(output)));
            }

            @Override
            public String describe() {
                return "Removes a Woot AnvilRecipe Recipe";
            }
        });
    }

    @ZenMethod
    static void removeAll() {
        MagicHiganbana.queuePostInitAction(new IAction() {
            @Override
            public void apply() {
                Woot.anvilManager.getRecipes().clear();
            }

            @Override
            public String describe() {
                return "Removes all recipes for the Woot Anvil";
            }
        });
    }

}
