package anidlebrain.higanbana.mod.items.lens;

import anidlebrain.higanbana.mod.items.base.MHItembase;
import de.ellpeck.actuallyadditions.api.lens.ILensItem;
import de.ellpeck.actuallyadditions.api.lens.Lens;

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
