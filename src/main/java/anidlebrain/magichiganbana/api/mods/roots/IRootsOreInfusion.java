package anidlebrain.magichiganbana.api.mods.roots;

import anidlebrain.magichiganbana.MagicHiganbana;
import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.oredict.IOreDictEntry;
import epicsquid.roots.properties.Property;
import epicsquid.roots.recipe.OreChances;
import epicsquid.roots.spell.SpellGrowthInfusion;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * @author Andileabrain
 */

@ZenRegister
@ZenClass("mods.magichiganbana.Roots.IOreInfusion")
@SuppressWarnings("unused")
public class IRootsOreInfusion {
    @ZenMethod
    static void addOre(IOreDictEntry ore, int weight)
    {
        MagicHiganbana.queuePostInitAction(new IAction() {
            @Override
            public void apply() {
                OreChances.addOreChance(new OreChances.OreDictItem(ore.getName(), weight));
            }

            @Override
            public String describe() {
                return "add root ore chances " + ore.getName();
            }
        });
    }

    @ZenMethod
    static void addOre(String oreName, int weight)
    {
        MagicHiganbana.queuePostInitAction(new IAction() {
            @Override
            public void apply() {
                OreChances.addOreChance(new OreChances.OreDictItem(oreName, weight));
            }

            @Override
            public String describe() {
                return "add root ore chances " + oreName;
            }
        });
    }

    @ZenMethod
    static void removeAll() {
        MagicHiganbana.queuePostInitAction(new IAction() {
            @Override
            public void apply() {
                OreChances.clear();
            }

            @Override
            public String describe() {
                return "Removes all roots ore infusion's ore chances";
            }
        });
    }

    @ZenMethod
    static void modifySpellChance(float chance) {
        MagicHiganbana.queuePostInitAction(new IAction() {
            @Override
            public void apply() {
                SpellGrowthInfusion.PROP_STONE_CHANCE = new Property<>("stone_chance", chance).setDescription("the chance per tick of eligible stone being converted to ore");
            }

            @Override
            public String describe() {
                return "modifySpellChance stone_chance = " + chance;
            }
        });
    }
}
