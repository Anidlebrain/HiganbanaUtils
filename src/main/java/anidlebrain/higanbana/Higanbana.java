package anidlebrain.higanbana;
import anidlebrain.higanbana.impl.INumber;
import anidlebrain.higanbana.mod.proxy.IProxy;
import anidlebrain.higanbana.tile.TileEntityDisplay;
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
        modid = Higanbana.MODID,
        name = Higanbana.NAME,
        version = Higanbana.VERSION,
        dependencies = Higanbana.DESPENDENCIES
)
@SuppressWarnings("unused")
public class Higanbana {
    public static final String MODID = "higanbanautils";
    public static final String NAME = "Higanbana Utils";
    public static final String VERSION = "1.0.10";
    public static final String DESPENDENCIES = "required-after:crafttweaker;" +
            "required-after:astralsorcery;" +
            "required-after:woot;" +
            "required-after:jeresources;" +
            "required-after:randomthings;" +
            "required-after:actuallyadditions;" +
            "required-after:roots;" +
            "required-after:bloodmagic;" +
            "required-after:embersconstruct";

    @Mod.Instance(Higanbana.MODID)
    public static Higanbana instance;

    private static final Queue<IAction> postQueue = new LinkedList<>();

    public static final Logger LOGGER = LogManager.getLogger(NAME);

    @SidedProxy(clientSide = "anidlebrain.higanbana.mod.proxy.ClientProxy", serverSide = "anidlebrain.higanbana.mod.proxy.ServerProxy")
    public static IProxy PROXY;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // TODO
        Higanbana.LOGGER.info("preInit Higanbana");

        MinecraftForge.EVENT_BUS.register(new RegistryHandler());

        PROXY.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        // TODO
        Higanbana.LOGGER.info("init Higanbana");
        INumber.init();
        TileEntityDisplay.register();
        PROXY.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        // TODO
        Higanbana.LOGGER.info("postInit Higanbana");
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
