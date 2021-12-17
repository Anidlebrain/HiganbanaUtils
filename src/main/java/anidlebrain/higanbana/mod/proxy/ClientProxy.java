package anidlebrain.higanbana.mod.proxy;


import anidlebrain.higanbana.ClientRegistryHandler;
import anidlebrain.higanbana.render.RenderDisplay;
import anidlebrain.higanbana.tile.TileEntityDisplay;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy implements IProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new ClientRegistryHandler());
    }

    @Override
    public void init(FMLInitializationEvent event) {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDisplay.class, new RenderDisplay());
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

    }

    @Override
    public void addRenderRegister(ItemStack stack, ResourceLocation location, String variant) {
        ClientRegistryHandler.MODEL_LOCATIONS_FOR_REGISTERING.put(stack, new ModelResourceLocation(location, variant));
    }
}
