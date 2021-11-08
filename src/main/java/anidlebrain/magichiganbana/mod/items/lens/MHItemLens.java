package anidlebrain.magichiganbana.mod.items.lens;

import anidlebrain.magichiganbana.mod.items.MHInitItems;
import anidlebrain.magichiganbana.mod.items.base.MHItembase;
import de.ellpeck.actuallyadditions.api.lens.ILensItem;
import de.ellpeck.actuallyadditions.api.lens.Lens;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class MHItemLens extends MHItembase implements ILensItem {
    private final Lens type;

    public MHItemLens(String name, Lens type) {
        super(name);
        this.type = type;
        this.setMaxStackSize(1);
    }

    @Override
    public Lens getLens() {
        return this.type;
    }
}