package anidlebrain.magichiganbana;
import anidlebrain.magichiganbana.impl.INumber;
import anidlebrain.magichiganbana.mod.RegistryHandler;
import anidlebrain.magichiganbana.mod.items.MHInitItems;
import anidlebrain.magichiganbana.mod.items.lens.MHLen;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.Queue;

@Mod(
        modid = MagicHiganbana.MODID,
        name = MagicHiganbana.NAME,
        version = MagicHiganbana.VERSION,
        dependencies = MagicHiganbana.DESPENDENCIES
)

/**
 * @author Andileabrain
 */
@SuppressWarnings("unused")
public class MagicHiganbana {
    public static final String MODID = "magichiganbana";
    public static final String NAME = "Magic Higanbana";
    public static final String VERSION = "1.0.2";
    public static final String DESPENDENCIES = "required-after:crafttweaker;" +
            "required-after:astralsorcery;" +
            "required-after:woot;" +
            "required-after:randomthings";

    @Mod.Instance(MagicHiganbana.MODID)
    public static MagicHiganbana instance;

    private static final Queue<IAction> postQueue = new LinkedList<>();

    public static final Logger LOGGER = LogManager.getLogger(NAME);

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // TODO
        MagicHiganbana.LOGGER.info("preInit MagicHiganbana");
        MHLen.init();
        MinecraftForge.EVENT_BUS.register(new RegistryHandler());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        // TODO
        MagicHiganbana.LOGGER.info("init MagicHiganbana");
        INumber.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        // TODO
        MagicHiganbana.LOGGER.info("postInit MagicHiganbana");
        for (IAction action : postQueue) {
            CraftTweakerAPI.apply(action);
        }
        postQueue.clear();
    }

    public static void queuePostInitAction(IAction action) {
        postQueue.add(action);
    }

}
