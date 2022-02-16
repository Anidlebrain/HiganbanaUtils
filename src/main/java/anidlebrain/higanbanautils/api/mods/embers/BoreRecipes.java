package anidlebrain.higanbanautils.api.mods.embers;

import com.google.common.collect.Sets;
import crafttweaker.annotations.ModOnly;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import stanhebben.zenscript.annotations.*;
import teamroots.embers.compat.crafttweaker.EmberBore;
import teamroots.embers.recipe.BoreOutput;
import teamroots.embers.recipe.RecipeRegistry;
import crafttweaker.api.item.WeightedItemStack;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * @author Andileabrain
 * modify by https://github.com/DaedalusGame/EmbersRekindled/blob/rekindled/src/main/java/teamroots/embers/compat/crafttweaker/EmberBore.java
 */

@ModOnly("embers")
@ZenRegister
@ZenClass("mods.Higanbana.ember.BoreOutput")
@SuppressWarnings("unused")
public class BoreRecipes {
    BoreOutput internal;

    @ZenGetter("dimensions")
    public Integer[] getDimensions() {
        return internal.dimensionIds.toArray(new Integer[0]);
    }

    @ZenSetter("dimensions")
    public void setDimensions(Integer[] dimensions) {
        internal.dimensionIds = Sets.newHashSet(dimensions);
    }

    @ZenGetter("biomes")
    public String[] getBiomes() {
        return (String[]) internal.biomeIds.stream().map(ResourceLocation::toString).toArray();
    }

    @ZenSetter("biomes")
    public void setBiomes(String[] biomes) {
        internal.biomeIds = Arrays.stream(biomes).map(ResourceLocation::new).collect(Collectors.toCollection(HashSet::new));
    }

    @ZenGetter("stacks")
    public WeightedItemStack[] getStacks() {
        return (WeightedItemStack[]) internal.stacks.stream().map(stack -> new WeightedItemStack(CraftTweakerMC.getIItemStack(stack.getStack()),stack.itemWeight)).toArray();
    }

    @ZenMethod
    public void addOutput(WeightedItemStack stack) {
        internal.stacks.add(new teamroots.embers.util.WeightedItemStack(CraftTweakerMC.getItemStack(stack.getStack()), (int)stack.getChance()));
    }

    @ZenMethod
    public void removeOutput(IItemStack stack) {
        ItemStack toRemove = CraftTweakerMC.getItemStack(stack);
        internal.stacks.removeIf(x -> x.getStack().isItemEqual(toRemove));
    }

    @ZenMethod
    public void clear() {
        internal.stacks.clear();
    }

    public BoreRecipes(BoreOutput internal) {
        this.internal = internal;
    }

    @ZenMethod
    public static EmberBore create(Integer[] dimensions, String[] biomes) {
        EmberBore emberBore = new EmberBore(new BoreOutput());
        emberBore.setDimensions(dimensions);
        emberBore.setBiomes(biomes);
        return emberBore;
    }

    @ZenMethod
    public static EmberBore getDefault() {
        return new EmberBore(RecipeRegistry.defaultBoreOutput);
    }

    @ZenMethod
    public static void setDefault(WeightedItemStack[] items) {
        RecipeRegistry.setDefaultBoreOutput(new BoreOutput(Sets.newHashSet(), Sets.newHashSet(),
                Arrays.stream(items).map(item -> new teamroots.embers.util.WeightedItemStack(CraftTweakerMC.getItemStack(item.getStack()),
                        (int)item.getChance())).collect(Collectors.toCollection(ArrayList::new))));
    }

    @ZenMethod
    public void registry() {
        RecipeRegistry.registerBoreOutput(internal);
    }


}
