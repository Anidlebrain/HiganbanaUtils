package anidlebrain.magichiganbana.mod.block.base;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

/**
 * @author Andileabrain
 */
public class MHItemBlockBase extends ItemBlock {

    public MHItemBlockBase(Block block) {
        super(block);
        this.setHasSubtypes(false);
        this.setMaxDamage(0);
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

}
