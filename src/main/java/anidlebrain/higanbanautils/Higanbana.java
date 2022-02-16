package anidlebrain.higanbanautils;

import anidlebrain.higanbanautils.impl.INumber;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
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
    public static final String VERSION = "1.0.13";
    public static final String DESPENDENCIES = "required-after:crafttweaker;" +
            "required-after:astralsorcery;" +
            "required-after:woot;" +
            "required-after:jeresources;" +
            "required-after:randomthings;" +
            "required-after:actuallyadditions;" +
            "required-after:roots;" +
            "required-after:bloodmagic;" +
            "required-after:embers;";

    @Mod.Instance(Higanbana.MODID)
    public static Higanbana instance;

    private static final Queue<IAction> postQueue = new LinkedList<>();

    public static final Logger LOGGER = LogManager.getLogger(NAME);


    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // TODO
        Higanbana.LOGGER.info("preInit " + NAME);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        // TODO
        Higanbana.LOGGER.info("init " + NAME);
        INumber.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        // TODO
        Higanbana.LOGGER.info("postInit " + NAME);
        for (IAction action : postQueue) {
            CraftTweakerAPI.apply(action);
        }
        postQueue.clear();
    }

    public static void queuePostInitAction(IAction action) {
        postQueue.add(action);
    }

    @EventHandler
    public void onConstruct(FMLConstructionEvent event) {

    }

}