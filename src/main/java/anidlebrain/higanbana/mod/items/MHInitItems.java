package anidlebrain.higanbana.mod.items;

import anidlebrain.higanbana.Higanbana;
import anidlebrain.higanbana.mod.items.lens.MHItemLens;
import anidlebrain.higanbana.mod.items.lens.MHLen;
import net.minecraft.item.Item;

/**
 * @author Andileabrain
 */
public class MHInitItems {
    public static Item itemMysticalLens;


    public static void init() {
        Higanbana.LOGGER.info("MHInitItems");
        MHLen.init();

        itemMysticalLens = new MHItemLens("item_mystical_lens", MHLen.lensMystical);
    }
}