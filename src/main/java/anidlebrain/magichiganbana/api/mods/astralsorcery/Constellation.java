package anidlebrain.magichiganbana.api.mods.astralsorcery;

import anidlebrain.magichiganbana.MagicHiganbana;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import hellfirepvp.astralsorcery.common.constellation.ConstellationBase;
import hellfirepvp.astralsorcery.common.constellation.ConstellationRegistry;
import hellfirepvp.astralsorcery.common.constellation.star.StarLocation;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Andileabrain
 */

@ZenRegister
@ZenClass("mods.magichiganbana.AstralSorcery.Constellation")
@SuppressWarnings("unused")
public class Constellation {

    private final ConstellationBase starBase;
    private final List<StarLocation> starList = new ArrayList<>();
    private String name;

    public Constellation(String name, int level, int color) {
        if (level == 1) {
            this.starBase = new ConstellationBase.Major(name, new Color(color));
        }
        else if (level == 2) {
            this.starBase = new ConstellationBase.Weak(name, new Color(color));
        }
        else if (level == 3) {
            this.starBase = new ConstellationBase.Minor(name, new Color(color));
        }
        else {
            this.starBase = null;
        }
        this.name = name;
    }

    @ZenMethod
    public static Constellation create(String name, int level, int color) {
        return new Constellation(name, level, color);
    }

    @ZenMethod
    public int addStar(int x, int y) {
        if (starBase == null) {
            CraftTweakerAPI.logError("This starBase was empty!");
            return -1;
        }
        starList.add(starBase.addStar(x, y));
        return starList.size();
    }

    @ZenMethod
    public void addConnection(int x, int y) {
        if (starBase != null && x > 0 && y > 0 && x <= starList.size() && y <= starList.size()) {
            starBase.addConnection(starList.get(x - 1), starList.get(y - 1));
        }
    }

    @ZenMethod
    public void register() {
        if (starBase != null) {
            ConstellationRegistry.registerConstellation(starBase);
        }
    }

}
