package anidlebrain.magichiganbana.api.mods.jeresources;

import anidlebrain.magichiganbana.MagicHiganbana;
import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import jeresources.api.drop.PlantDrop;
import jeresources.entry.PlantEntry;
import jeresources.registry.PlantRegistry;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andileabrain
 */

@ZenRegister
@ZenClass("mods.magichiganbana.jeresources.JEIPlantEntry")
@SuppressWarnings("unused")
public class JEIPlantEntry {

    private final ItemStack itemStack;
    List<PlantDrop> drops = new ArrayList<>();

    private JEIPlantEntry(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    @ZenMethod
    public static void removeAll() {
        MagicHiganbana.queuePostInitAction(new IAction() {
            @Override
            public void apply() {
                PlantRegistry.getInstance().clear();
            }

            @Override
            public String describe() {
                return "removeAll Plant";
            }
        });
    }

    @ZenMethod
    public static void removePlant(IItemStack itemStack) {
        MagicHiganbana.queuePostInitAction(new IAction() {
            @Override
            public void apply() {
                PlantRegistry.getInstance().getAllPlants().removeIf(plantEntry -> CraftTweakerMC.matches(itemStack, plantEntry.getPlantItemStack()));
            }

            @Override
            public String describe() {
                return "remove Plant " + itemStack.getDisplayName();
            }
        });

    }

    @ZenMethod
    public static JEIPlantEntry addPlant(IItemStack itemStack) {
        return new JEIPlantEntry(CraftTweakerMC.getItemStack(itemStack));
    }

    @ZenMethod
    public JEIPlantEntry addDrop(IItemStack itemStack, float chance) {
        drops.add(new PlantDrop(CraftTweakerMC.getItemStack(itemStack), chance));
        return this;
    }

    @ZenMethod
    public JEIPlantEntry addDrop(IItemStack itemStack, int minDrop, int maxDrop) {
        drops.add(new PlantDrop(CraftTweakerMC.getItemStack(itemStack), minDrop, maxDrop));
        return this;
    }

    @ZenMethod
    public void registry() {
        MagicHiganbana.queuePostInitAction(new IAction() {
            @Override
            public void apply() {
                PlantRegistry.getInstance().registerPlant(new PlantEntry(itemStack, (PlantDrop[]) drops.toArray()));
            }

            @Override
            public String describe() {
                return "register " + itemStack.getDisplayName() + " Plant";
            }
        });
    }
}
