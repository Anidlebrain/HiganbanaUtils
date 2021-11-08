package anidlebrain.magichiganbana.mod.items;

import anidlebrain.magichiganbana.MagicHiganbana;
import anidlebrain.magichiganbana.mod.items.lens.MHItemLens;
import anidlebrain.magichiganbana.mod.items.lens.MHLen;
import net.minecraft.item.Item;

/**
 * @author Andileabrain
 */
public class MHInitItems {
    public static Item itemMysticalLens;


    public static void init() {
        MagicHiganbana.LOGGER.info("MHInitItems");
        itemMysticalLens = new MHItemLens("item_mystical_lens", MHLen.lensMystical);
    }
}