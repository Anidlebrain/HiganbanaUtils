package anidlebrain.magichiganbana.mod.items.base;

import anidlebrain.magichiganbana.MagicHiganbana;
import anidlebrain.magichiganbana.mod.RegistryHandler;
import anidlebrain.magichiganbana.mod.creative.MHCreativeTab;
import net.minecraft.item.Item;

public class MHItembase extends Item {
    private final String name;

    public MHItembase(String name) {
        super();
        this.name = name;
        this.setUnlocalizedName(MagicHiganbana.MODID + "." + name);
        this.register();
    }

    private void register() {
        //setTranslationKey(MagicHiganbana.MODID + "." + name);
        this.setRegistryName(MagicHiganbana.MODID, name);
        RegistryHandler.ITEMS_TO_REGISTER.add(this);
        this.setCreativeTab(MHCreativeTab.INSTANCE);

    }

}
