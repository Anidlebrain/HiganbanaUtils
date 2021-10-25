package anidlebrain.magichiganbana;
import anidlebrain.magichiganbana.impl.INumber;
import anidlebrain.magichiganbana.impl.MCAnvilManager;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.LinkedList;
import java.util.Queue;

@Mod(
        modid = MagicHiganbana.MODID,
        name = MagicHiganbana.NAME,
        version = MagicHiganbana.VERSION,
        dependencies = MagicHiganbana.DESPENDENCIES
)

@SuppressWarnings("unused")
public class MagicHiganbana {
    public static final String MODID = "magichiganbana";
    public static final String NAME = "Magic Higanbana";
    public static final String VERSION = "1.0.1";
    public static final String DESPENDENCIES = "required-after:crafttweaker;" +
            "required-after:astralsorcery;" +
            "required-after:woot;" +
            "required-after:randomthings";

    @Mod.Instance(MagicHiganbana.MODID)
    public static MagicHiganbana instance;

    private static final Queue<IAction> postQueue = new LinkedList<>();

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        // TODO
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // TODO
        INumber.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        // TODO
        for (IAction action : postQueue) {
            CraftTweakerAPI.apply(action);
        }
        postQueue.clear();
    }

    public static void queuePostInitAction(IAction action) {
        postQueue.add(action);
    }

}
