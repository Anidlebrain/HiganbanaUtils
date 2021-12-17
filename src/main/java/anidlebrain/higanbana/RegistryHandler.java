package anidlebrain.higanbana;

import anidlebrain.higanbana.mod.block.BlockInit;
import anidlebrain.higanbana.mod.items.MHInitItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andileabrain
 */
@SuppressWarnings("unused")
public class RegistryHandler {
    public static final List<Block> BLOCKS_TO_REGISTER = new ArrayList<>();
    public static final List<Item> ITEMS_TO_REGISTER = new ArrayList<>();
    public static final List<SoundEvent> SOUNDS_TO_REGISTER = new ArrayList<>();
    public static final List<IRecipe> RECIPES_TO_REGISTER = new ArrayList<>();

    @SubscribeEvent
    public void onBlockRegistry(Register<Block> event) {
        Higanbana.LOGGER.info("onBlockRegistry");
        BlockInit.init();

        for (Block block : BLOCKS_TO_REGISTER) {
            event.getRegistry().register(block);
        }
        //BLOCKS_TO_REGISTER.clear();
    }

    @SubscribeEvent
    public void onItemRegistry(Register<Item> event) {
        Higanbana.LOGGER.info("onItemRegistry");
        MHInitItems.init();

        for (Item item : ITEMS_TO_REGISTER) {
            event.getRegistry().register(item);
        }
        ITEMS_TO_REGISTER.clear();

        //Hack to make this register before recipes :>
        //InitOreDict.init();
    }

    @SubscribeEvent
    public void onVillagerRegistry(Register<VillagerRegistry.VillagerProfession> event) {
        //InitVillager.init();

        //if (ConfigBoolValues.JAM_VILLAGER_EXISTS.isEnabled()) event.getRegistry().register(InitVillager.jamProfession);
        //if (ConfigBoolValues.ENGINEER_VILLAGER_EXISTS.isEnabled()) event.getRegistry().register(InitVillager.engineerProfession);
    }

    @SubscribeEvent
    public void onCraftingRegistry(Register<IRecipe> event) {
        //InitCrafting.init();

        for (IRecipe recipe : RECIPES_TO_REGISTER) {
            event.getRegistry().register(recipe);
        }
        RECIPES_TO_REGISTER.clear();
    }

    @SubscribeEvent
    public void onSoundRegistry(Register<SoundEvent> event) {
        //SoundHandler.init();

        for (SoundEvent sound : SOUNDS_TO_REGISTER) {
            event.getRegistry().register(sound);
        }
        SOUNDS_TO_REGISTER.clear();
    }
}