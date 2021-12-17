package anidlebrain.higanbana;

import anidlebrain.higanbana.mod.block.render.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class ClientRegistryHandler {
    public static final Map<ItemStack, ModelResourceLocation> MODEL_LOCATIONS_FOR_REGISTERING = new HashMap<>();

    @SubscribeEvent
    public void onModelRegistry(ModelRegistryEvent event) {

        Higanbana.LOGGER.info("onModelRegistry");

        for (Block block : RegistryHandler.BLOCKS_TO_REGISTER) {
            if (block instanceof IHasModel) {
                ((IHasModel) block).registerRendering();
            }
        }

        for (Map.Entry<ItemStack, ModelResourceLocation> entry : MODEL_LOCATIONS_FOR_REGISTERING.entrySet()) {
            Higanbana.LOGGER.info("onModelRegistry " + entry.getKey().getUnlocalizedName() + " " + entry.getKey().getItemDamage());
            ModelLoader.setCustomModelResourceLocation(entry.getKey().getItem(), entry.getKey().getItemDamage(), entry.getValue());
        }

    }

    @SubscribeEvent
    public void onModelBake(ModelBakeEvent event) {

    }
}
