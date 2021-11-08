package anidlebrain.magichiganbana.mod.items.lens;

import de.ellpeck.actuallyadditions.api.lens.Lens;

public class MHLen {
    public static Lens lensMystical;

    public static void init(){
        lensMystical = new LensMystical();
    }
}
