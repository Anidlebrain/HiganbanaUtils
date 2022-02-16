package anidlebrain.higanbanautils.api.mods.roots;

import anidlebrain.higanbanautils.Higanbana;
import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.oredict.IOreDictEntry;
import epicsquid.mysticallib.types.OneTimeSupplier;
import epicsquid.roots.recipe.SpiritDrops;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;


/**
 * @author Andileabrain
 */

@ZenRegister
@ZenClass("mods.Higanbana.Roots.SpiritDrops")
@SuppressWarnings("unused")
public class RootsSpiritDrops {
    @ZenMethod
    public static void removeAllPouch() {
        Higanbana.queuePostInitAction(new IAction() {
            @Override
            public void apply() {
                SpiritDrops.clearPouch();
            }

            @Override
            public String describe() {
                return "remove All Pouch";
            }
        });
    }

    @ZenMethod
    public static void removeAllReliquary() {
        Higanbana.queuePostInitAction(new IAction() {
            @Override
            public void apply() {
                SpiritDrops.clearPouch();
            }

            @Override
            public String describe() {
                return "remove All Reliquary";
            }
        });
    }

    @ZenMethod
    public static void addReliquaryDrop(IItemStack item, int weight) {
        Higanbana.queuePostInitAction(new IAction() {
            @Override
            public void apply() {
                SpiritDrops.addReliquary(new SpiritDrops.StackItem(new OneTimeSupplier<>(() -> CraftTweakerMC.getItemStack(item)), weight));
            }

            @Override
            public String describe() {
                return "addReliquaryDrop " + item.getDisplayName();
            }
        });
    }

    @ZenMethod
    public static void addReliquaryDrop(IOreDictEntry ore, int weight) {
        Higanbana.queuePostInitAction(new IAction() {
            @Override
            public void apply() {
                SpiritDrops.addReliquary(new SpiritDrops.OreSpiritItem(ore.getName(), weight));
            }

            @Override
            public String describe() {
                return "addReliquaryDrop " + ore.getName();
            }
        });
    }

    @ZenMethod
    public static void addPouchDrop(IItemStack item, int weight) {
        Higanbana.queuePostInitAction(new IAction() {
            @Override
            public void apply() {
                SpiritDrops.addPouch(new SpiritDrops.StackItem(new OneTimeSupplier<>(() -> CraftTweakerMC.getItemStack(item)), weight));
            }

            @Override
            public String describe() {
                return "addReliquaryDrop " + item.getDisplayName();
            }
        });
    }

    @ZenMethod
    public static void addPouchDrop(IOreDictEntry ore, int weight) {
        Higanbana.queuePostInitAction(new IAction() {
            @Override
            public void apply() {
                SpiritDrops.addPouch(new SpiritDrops.OreSpiritItem(ore.getName(), weight));
            }

            @Override
            public String describe() {
                return "addReliquaryDrop " + ore.getName();
            }
        });
    }

    @ZenMethod
    public static void removeReliquaryDrop(IItemStack item) {
        Higanbana.queuePostInitAction(new IAction() {
            @Override
            public void apply() {
                SpiritDrops.getReliquary().removeIf(stackItem -> stackItem.getItem().isItemEqual(CraftTweakerMC.getItemStack(item)));
            }

            @Override
            public String describe() {
                return "addReliquaryDrop " + item.getDisplayName();
            }
        });
    }

    @ZenMethod
    public static void removePouchDrop(IItemStack item) {
        Higanbana.queuePostInitAction(new IAction() {
            @Override
            public void apply() {
                SpiritDrops.getPouch().removeIf(stackItem -> stackItem.getItem().isItemEqual(CraftTweakerMC.getItemStack(item)));
            }

            @Override
            public String describe() {
                return "addReliquaryDrop " + item.getDisplayName();
            }
        });
    }

}
