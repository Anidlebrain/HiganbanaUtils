package anidlebrain.magichiganbana.api.mods.roots;

import anidlebrain.magichiganbana.MagicHiganbana;
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
@ZenClass("mods.magichiganbana.Roots.ISpiritDrops")
@SuppressWarnings("unused")
public class IRootsSpiritDrops {
    @ZenMethod
    static void removeAllPouch() {
        MagicHiganbana.queuePostInitAction(new IAction() {
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
    static void removeAllReliquary() {
        MagicHiganbana.queuePostInitAction(new IAction() {
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
    static void addReliquaryDrop(IItemStack item, int weight) {
        MagicHiganbana.queuePostInitAction(new IAction() {
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
    static void addReliquaryDrop(IOreDictEntry ore, int weight) {
        MagicHiganbana.queuePostInitAction(new IAction() {
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
    static void addPouchDrop(IItemStack item, int weight) {
        MagicHiganbana.queuePostInitAction(new IAction() {
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
    static void addPouchDrop(IOreDictEntry ore, int weight) {
        MagicHiganbana.queuePostInitAction(new IAction() {
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
    static void removeReliquaryDrop(IItemStack item) {
        MagicHiganbana.queuePostInitAction(new IAction() {
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
    static void removePouchDrop(IItemStack item) {
        MagicHiganbana.queuePostInitAction(new IAction() {
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
