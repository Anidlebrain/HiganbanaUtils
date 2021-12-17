package anidlebrain.higanbana.mod.creative;

import anidlebrain.higanbana.Higanbana;
import anidlebrain.higanbana.RegistryHandler;
import anidlebrain.higanbana.mod.block.BlockInit;
import anidlebrain.higanbana.mod.block.render.IHasModel;
import anidlebrain.higanbana.mod.items.MHInitItems;
import de.ellpeck.actuallyadditions.api.misc.IDisableableItem;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author Andileabrain
 */
public class CreativeTab extends CreativeTabs {
    public static final CreativeTab INSTANCE = new CreativeTab();
    private NonNullList<ItemStack> list;

    public CreativeTab() {
        super(Higanbana.MODID);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(MHInitItems.itemMysticalLens);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void displayAllRelevantItems(NonNullList<ItemStack> list) {
        this.list = list;

        //items
        for (Item item : RegistryHandler.ITEMS_TO_REGISTER) {
            this.add(item);
        }

        //blocks
        for (Block block : RegistryHandler.BLOCKS_TO_REGISTER) {
            this.add(block);
        }

    }

    public void add(Item item) {
        if (item != null && (!(item instanceof IDisableableItem) || !((IDisableableItem) item).isDisabled())) {
            item.getSubItems(INSTANCE, this.list);
        }
    }

    public void add(Block block) {
        if (block != null) {
            block.getSubBlocks(INSTANCE, this.list);
        }
    }

    @Override
    public boolean hasSearchBar() {
        return false;
    }

    @Override
    public int getSearchbarWidth() {
        return 70;
    }
}
