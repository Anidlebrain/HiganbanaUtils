package anidlebrain.magichiganbana;
import anidlebrain.magichiganbana.impl.INumber;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = MagicHiganbana.MODID,
        name = MagicHiganbana.NAME,
        version = MagicHiganbana.VERSION,
        dependencies = MagicHiganbana.DESPENDENCIES
)
public class MagicHiganbana {
    public static final String MODID = "magichiganbana";
    public static final String NAME = "Magic Higanbana";
    public static final String VERSION = "1.0.0";
    public static final String DESPENDENCIES = "required-after:crafttweaker;required-after:astralsorcery;required-after:woot;";

    @Mod.Instance(MagicHiganbana.MODID)
    public static MagicHiganbana instance;

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
    }

}
