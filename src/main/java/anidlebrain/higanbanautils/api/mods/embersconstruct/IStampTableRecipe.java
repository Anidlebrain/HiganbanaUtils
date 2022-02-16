package anidlebrain.higanbanautils.api.mods.embersconstruct;

import anidlebrain.higanbanautils.Higanbana;
import com.peatral.embersconstruct.EmbersConstructConfig;
import com.peatral.embersconstruct.registry.StampTableRecipes;
import crafttweaker.IAction;
import crafttweaker.annotations.ModOnly;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * @author Andileabrain
 */

@ModOnly("embersconstruct")
@ZenRegister
@ZenClass("mods.Higanbana.EmbersConstruct.StampTableRecipe")
@SuppressWarnings("unused")
public interface IStampTableRecipe {

    @ZenMethod
    static void addRecipe(IItemStack output, IIngredient input, boolean requiresBlank) {
        Higanbana.queuePostInitAction(new IAction() {
            @Override
            public void apply() {
                StampTableRecipes.instance().addRecipe(CraftTweakerMC.getIngredient(input), CraftTweakerMC.getItemStack(output), requiresBlank);
            }

            @Override
            public String describe() {
                return "Adds a EmbersConstruct StampTableRecipe Recipe";
            }
        });
    }

    @ZenMethod
    static void addRecipe(IItemStack output, IIngredient input) {
        Higanbana.queuePostInitAction(new IAction() {
            @Override
            public void apply() {
                boolean requiresBlank = EmbersConstructConfig.embersConstructSettings.stampTableNeedBlank;
                StampTableRecipes.instance().addRecipe(CraftTweakerMC.getIngredient(input), CraftTweakerMC.getItemStack(output), requiresBlank);
            }

            @Override
            public String describe() {
                return "Adds a EmbersConstruct StampTableRecipe Recipe";
            }
        });
    }

    @ZenMethod
    static void removeRecipe(IItemStack input) {
        Higanbana.queuePostInitAction(new IAction() {
            @Override
            public void apply() {
                StampTableRecipes.instance().getRecipeList().removeIf(recipe -> recipe.matches(CraftTweakerMC.getItemStack(input)));
            }

            @Override
            public String describe() {
                return "Removes a EmbersConstruct StampTableRecipe Recipe";
            }
        });
    }

    @ZenMethod
    static void removeAll() {
        Higanbana.queuePostInitAction(new IAction() {
            @Override
            public void apply() {
                StampTableRecipes.instance().getRecipeList().clear();
            }

            @Override
            public String describe() {
                return "Removes all recipes for the EmbersConstruct StampTableRecipe";
            }
        });
    }
}
