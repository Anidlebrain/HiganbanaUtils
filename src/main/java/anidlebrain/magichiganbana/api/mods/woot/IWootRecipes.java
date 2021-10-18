package anidlebrain.magichiganbana.api.mods.woot;

import crafttweaker.annotations.ZenRegister;

import crafttweaker.api.minecraft.CraftTweakerMC;
import ipsis.Woot;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import crafttweaker.api.item.IItemStack;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ZenRegister
@ZenClass("mods.magichiganbana.Woot.AnvilRecipe")
@SuppressWarnings("unused")
public interface IWootRecipes {

    @ZenMethod
    static void addRecipe(IItemStack output, IItemStack base, boolean preserveBase, IItemStack[] inputs)
    {
        Woot.anvilManager.addRecipe(CraftTweakerMC.getItemStack(output),
                CraftTweakerMC.getItemStack(base),
                preserveBase,
                (Object[])CraftTweakerMC.getItemStacks(inputs));
    }

    @ZenMethod
    static void removeRecipe(IItemStack output)
    {
        Woot.anvilManager.getRecipes().remove(Woot.anvilManager.getRecipe(CraftTweakerMC.getItemStack(output)));
    }

}
