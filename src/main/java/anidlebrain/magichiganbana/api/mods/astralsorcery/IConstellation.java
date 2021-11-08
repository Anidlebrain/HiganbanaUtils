package anidlebrain.magichiganbana.api.mods.astralsorcery;

import anidlebrain.magichiganbana.impl.MCIConstellation;
import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * @author Andileabrain
 */
@ZenRegister
@ZenClass("mods.magichiganbana.AstralSorcery.constellation")
@SuppressWarnings("unused")
public interface IConstellation {
    @ZenMethod
    static MCIConstellation create(String name, int level, int color) {
        return new MCIConstellation(name, level, color);
    }

    @ZenMethod
    int addStar(int x, int y);

    @ZenMethod
    void addConnection(int x, int y);

    @ZenMethod
    void register();

}
