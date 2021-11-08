package anidlebrain.magichiganbana.mod.creative;

import anidlebrain.magichiganbana.MagicHiganbana;
import anidlebrain.magichiganbana.mod.items.MHInitItems;
import de.ellpeck.actuallyadditions.api.misc.IDisableableItem;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MHCreativeTab extends CreativeTabs {
    public static final MHCreativeTab INSTANCE = new MHCreativeTab();
    private NonNullList<ItemStack> list;

    public MHCreativeTab() {
        super(MagicHiganbana.MODID);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(MHInitItems.itemMysticalLens);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void displayAllRelevantItems(NonNullList<ItemStack> list) {
        this.list = list;

        this.add(MHInitItems.itemMysticalLens);
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
        return true;
    }

    @Override
    public int getSearchbarWidth() {
        return 70;
    }
}