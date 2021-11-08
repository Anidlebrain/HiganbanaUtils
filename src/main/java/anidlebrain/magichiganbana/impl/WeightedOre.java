package anidlebrain.magichiganbana.impl;

import net.minecraft.util.WeightedRandom;
/**
 * @author Andileabrain
 */
public class WeightedOre extends WeightedRandom.Item {

    public final String name;

    public WeightedOre(String name, int weight) {
        super(weight);
        this.name = name;
    }
}