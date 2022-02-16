package anidlebrain.higanbanautils.api.mods.roots;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ModOnly;
import crafttweaker.annotations.ZenRegister;
import epicsquid.roots.integration.crafttweaker.Herbs;
import epicsquid.roots.properties.Property;
import epicsquid.roots.properties.PropertyTable;
import epicsquid.roots.spell.SpellBase;
import epicsquid.roots.spell.SpellRegistry;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * @author Andileabrain
 */

@ModOnly("roots")
@ZenRegister
@ZenClass("mods.Higanbana.Roots.RootSpell")
@SuppressWarnings("unused")
public class RootSpell {
    private final SpellBase original;

    public RootSpell(SpellBase base) {
        this.original = base;
    }

    @ZenMethod
    public static RootSpell getSpell(String spellName) {
        if (!spellName.startsWith("spell_")) {
            spellName = "spell_" + spellName;
        }
        SpellBase spell = SpellRegistry.getSpell(spellName);
        if (spell == null) {
            return null;
        }

        return new RootSpell(spell);
    }

    @ZenMethod
    public RootSpell setModifierCost(Herbs.Herb herb, double amount) {
        PropertyTable props = original.getProperties();
        try {
            Property<SpellBase.SpellCost> prop = props.get("cost_" + herb.getHerbName());
            if (prop == null) {
                CraftTweakerAPI.logError("Invalid spell cost " + herb.getHerbName() + " for Spell " + original.getName() + ": this is not an existing cost.");
                return this;
            }
            if (amount > 0) {
                SpellBase.SpellCost newCost = new SpellBase.SpellCost(herb.getHerbName(), amount);
                props.set(prop, newCost);
            }
            else {
                props.getProperties().remove(prop);
            }

        } catch (ClassCastException error) {
            CraftTweakerAPI.logError("Invalid spell cost " + herb.getHerbName() + " for Spell " + original.getName() + ". Additional costs cannot be added at this time.", error);
        }
        return this;
    }
    
}
