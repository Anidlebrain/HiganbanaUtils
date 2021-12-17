package anidlebrain.higanbana.mod.items.base;

import anidlebrain.higanbana.Higanbana;
import anidlebrain.higanbana.RegistryHandler;
import anidlebrain.higanbana.mod.creative.CreativeTab;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public abstract class MHItembase extends Item {
    private final String name;

    public MHItembase(String name) {
        super();
        this.name = name;
        this.setUnlocalizedName(Higanbana.MODID + "." + name);
        this.register();
    }

    protected String getBaseName() {
        return this.name;
    }

    private void register() {
        //setTranslationKey(MagicHiganbana.MODID + "." + name);
        this.setRegistryName(Higanbana.MODID, name);
        RegistryHandler.ITEMS_TO_REGISTER.add(this);
        this.setCreativeTab(CreativeTab.INSTANCE);

        registerRendering();
    }

    protected void registerRendering() {
        Higanbana.PROXY.addRenderRegister(new ItemStack(this), this.getRegistryName(), "inventory");
    }

}
