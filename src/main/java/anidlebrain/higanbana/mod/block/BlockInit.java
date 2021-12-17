package anidlebrain.higanbana.mod.block;

import anidlebrain.higanbana.Higanbana;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andileabrain
 */
public class BlockInit {
    public static Block blockDisplayQian;
    public static Block blockDisplayKun;
    public static Block blockDisplayZhen;
    public static Block blockDisplayXun;
    public static Block blockDisplayKan;
    public static Block blockDisplayLi;
    public static Block blockDisplayGen;
    public static Block blockDisplayDui;
    public static Block demonExtrasSlab;
    public static Block demonExtrasSlabC;
    public static Block demonExtrasSlabD;
    public static Block demonExtrasSlabS;
    public static Block demonExtrasSlabV;

    public static void init() {
        Higanbana.LOGGER.info("MHInitBlocks");

        demonExtrasSlab = new SlabBlock("demon_extras_0_slab");
        demonExtrasSlabC = new SlabBlock("demon_extras_1_slab");
        demonExtrasSlabD = new SlabBlock("demon_extras_2_slab");
        demonExtrasSlabS = new SlabBlock("demon_extras_3_slab");
        demonExtrasSlabV = new SlabBlock("demon_extras_4_slab");


        blockDisplayQian = new BlockDisplayStand("block_display_qian");
        blockDisplayKun = new BlockDisplayStand("block_display_kun");
        blockDisplayZhen = new BlockDisplayStand("block_display_zhen");
        blockDisplayXun = new BlockDisplayStand("block_display_xun");
        blockDisplayKan = new BlockDisplayStand("block_display_kan");
        blockDisplayLi = new BlockDisplayStand("block_display_li");
        blockDisplayGen = new BlockDisplayStand("block_display_gen");
        blockDisplayDui = new BlockDisplayStand("block_display_dui");

    }

}
