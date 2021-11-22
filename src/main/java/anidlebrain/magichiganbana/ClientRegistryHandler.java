package anidlebrain.magichiganbana;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;

public class ClientRegistryHandler {
    public static final Map<ItemStack, ModelResourceLocation> MODEL_LOCATIONS_FOR_REGISTERING = new HashMap<>();

    @SubscribeEvent
    public void onModelRegistry(ModelRegistryEvent event) {

        MagicHiganbana.LOGGER.info("onModelRegistry");

        for (Map.Entry<ItemStack, ModelResourceLocation> entry : MODEL_LOCATIONS_FOR_REGISTERING.entrySet()) {
            ModelLoader.setCustomModelResourceLocation(entry.getKey().getItem(), entry.getKey().getItemDamage(), entry.getValue());
        }

    }

    @SubscribeEvent
    public void onModelBake(ModelBakeEvent e) {

    }
}
