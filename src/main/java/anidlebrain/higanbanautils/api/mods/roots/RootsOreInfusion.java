package anidlebrain.higanbanautils.api.mods.roots;

import anidlebrain.higanbanautils.Higanbana;
import crafttweaker.IAction;
import crafttweaker.annotations.ModOnly;
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

@ModOnly("roots")
@ZenRegister
@ZenClass("mods.Higanbana.Roots.OreInfusion")
@SuppressWarnings("unused")
public class RootsOreInfusion {
    @ZenMethod
    public static void addOre(IOreDictEntry ore, int weight)
    {
        Higanbana.queuePostInitAction(new IAction() {
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
    public static void addOre(String oreName, int weight)
    {
        Higanbana.queuePostInitAction(new IAction() {
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
    public static void removeAll() {
        Higanbana.queuePostInitAction(new IAction() {
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
    public static void modifySpellChance(float chance) {
        Higanbana.queuePostInitAction(new IAction() {
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
