package anidlebrain.magichiganbana;
import anidlebrain.magichiganbana.impl.INumber;
import anidlebrain.magichiganbana.mod.proxy.IProxy;
import anidlebrain.magichiganbana.tile.TileEntityDisplay;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Andileabrain
 */


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
    public static final String VERSION = "1.0.7";
    public static final String DESPENDENCIES = "required-after:crafttweaker;" +
            "required-after:astralsorcery;" +
            "required-after:woot;" +
            "required-after:jeresources;" +
            "required-after:randomthings;" +
            "required-after:actuallyadditions;" +
            "required-after:roots";

    @Mod.Instance(MagicHiganbana.MODID)
    public static MagicHiganbana instance;

    private static final Queue<IAction> postQueue = new LinkedList<>();

    public static final Logger LOGGER = LogManager.getLogger(NAME);

    @SidedProxy(clientSide = "anidlebrain.magichiganbana.mod.proxy.ClientProxy", serverSide = "anidlebrain.magichiganbana.mod.proxy.ServerProxy")
    public static IProxy PROXY;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // TODO
        MagicHiganbana.LOGGER.info("preInit MagicHiganbana");

        MinecraftForge.EVENT_BUS.register(new RegistryHandler());

        PROXY.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        // TODO
        MagicHiganbana.LOGGER.info("init MagicHiganbana");
        INumber.init();
        TileEntityDisplay.register();
        PROXY.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        // TODO
        MagicHiganbana.LOGGER.info("postInit MagicHiganbana");
        for (IAction action : postQueue) {
            CraftTweakerAPI.apply(action);
        }
        postQueue.clear();

        PROXY.postInit(event);
    }

    public static void queuePostInitAction(IAction action) {
        postQueue.add(action);
    }

    @EventHandler
    public void onConstruct(FMLConstructionEvent event) {

    }

}
