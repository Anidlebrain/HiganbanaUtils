package anidlebrain.magichiganbana.mod.items.base;

import anidlebrain.magichiganbana.MagicHiganbana;
import anidlebrain.magichiganbana.RegistryHandler;
import anidlebrain.magichiganbana.mod.creative.MHCreativeTab;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public abstract class MHItembase extends Item {
    private final String name;

    public MHItembase(String name) {
        super();
        this.name = name;
        this.setUnlocalizedName(MagicHiganbana.MODID + "." + name);
        this.register();
    }

    protected String getBaseName() {
        return this.name;
    }

    private void register() {
        //setTranslationKey(MagicHiganbana.MODID + "." + name);
        this.setRegistryName(MagicHiganbana.MODID, name);
        RegistryHandler.ITEMS_TO_REGISTER.add(this);
        this.setCreativeTab(MHCreativeTab.INSTANCE);

        registerRendering();
    }

    protected void registerRendering() {
        MagicHiganbana.PROXY.addRenderRegister(new ItemStack(this), this.getRegistryName(), "inventory");
    }

}
