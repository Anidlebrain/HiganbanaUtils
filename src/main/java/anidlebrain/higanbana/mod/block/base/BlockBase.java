package anidlebrain.higanbana.mod.block.base;

import anidlebrain.higanbana.Higanbana;
import anidlebrain.higanbana.RegistryHandler;
import anidlebrain.higanbana.mod.block.render.IHasModel;
import anidlebrain.higanbana.mod.creative.CreativeTab;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.Objects;

public class BlockBase extends Block implements IHasModel {
    private final String name;

    public BlockBase(Material materialIn, String name) {
        super(materialIn);
        this.name = name;
    }

    protected void register() {
        this.setRegistryName(Higanbana.MODID, name);
        this.setUnlocalizedName(Objects.requireNonNull(this.getRegistryName()).toString());
        this.setCreativeTab(CreativeTab.INSTANCE);

        //reg block
        RegistryHandler.BLOCKS_TO_REGISTER.add(this);

        //reg item
        ItemBlock itemBlock = this.getItemBlock();
        itemBlock.setRegistryName(Higanbana.MODID, name);
        RegistryHandler.ITEMS_TO_REGISTER.add(itemBlock);

    }

    @Override
    public void registerRendering() {
        Higanbana.PROXY.addRenderRegister(new ItemStack(this), this.getRegistryName(), "inventory");
    }

    private ItemBlock getItemBlock() {
        return new ItemBlock(this);
    }

    protected String getBaseName() {
        return this.name;
    }
}
