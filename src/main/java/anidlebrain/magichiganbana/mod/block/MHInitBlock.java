package anidlebrain.magichiganbana.mod.block;

import anidlebrain.magichiganbana.MagicHiganbana;
import net.minecraft.block.Block;
/**
 * @author Andileabrain
 */
public class MHInitBlock {
    public static Block blockDisplayQian;
    public static Block blockDisplayKun;
    public static Block blockDisplayZhen;
    public static Block blockDisplayXun;
    public static Block blockDisplayKan;
    public static Block blockDisplayLi;
    public static Block blockDisplayGen;
    public static Block blockDisplayDui;

    public static void init() {
        MagicHiganbana.LOGGER.info("MHInitBlocks");

        blockDisplayQian = new MHBlockDisplayStand("block_display_qian");
        blockDisplayKun = new MHBlockDisplayStand("block_display_kun");
        blockDisplayZhen = new MHBlockDisplayStand("block_display_zhen");
        blockDisplayXun = new MHBlockDisplayStand("block_display_xun");
        blockDisplayKan = new MHBlockDisplayStand("block_display_kan");
        blockDisplayLi = new MHBlockDisplayStand("block_display_li");
        blockDisplayGen = new MHBlockDisplayStand("block_display_gen");
        blockDisplayDui = new MHBlockDisplayStand("block_display_dui");


    }



}
