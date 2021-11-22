package anidlebrain.magichiganbana.mod.items.lens;

import anidlebrain.magichiganbana.MagicHiganbana;
import anidlebrain.magichiganbana.impl.WeightedOre;
import de.ellpeck.actuallyadditions.api.internal.IAtomicReconstructor;
import de.ellpeck.actuallyadditions.api.lens.Lens;
import de.ellpeck.actuallyadditions.mod.util.StackUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Andileabrain
 */
public class LensMystical extends Lens {

    public static final Map<IBlockState, Map<String, Integer>> map = new HashMap<>();
    public static void init() {

    }

    @Override
    public boolean invoke(IBlockState iBlockState, BlockPos blockPos, IAtomicReconstructor iAtomicReconstructor) {
        if (!iAtomicReconstructor.getWorldObject().isAirBlock((BlockPos) iBlockState)) {
            if (iAtomicReconstructor.getEnergy() > 3000) {
                int adaptedUse = 3000;
                List<WeightedOre> ores = getOreToPut(iBlockState);
                if (ores != null) {
                    int totalWeight = WeightedRandom.getTotalWeight(ores);
                    ItemStack stack = null;

                    boolean found = false;
                    while (!found) {
                        WeightedOre ore = WeightedRandom.getRandomItem(iAtomicReconstructor.getWorldObject().rand, ores, totalWeight);
                        if (ore != null) {
                            List<ItemStack> stacks = OreDictionary.getOres(ore.name, false);
                            if (stacks != null && !stacks.isEmpty()) {
                                for (ItemStack aStack : stacks) {
                                    if (StackUtil.isValid(aStack) &&
                                            aStack.getItem() instanceof ItemBlock) {
                                        stack = aStack;
                                        found = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (iAtomicReconstructor.getEnergy() >= adaptedUse) {
                        Block block = Block.getBlockFromItem(stack.getItem());
                        if (block != Blocks.AIR) {
                            IBlockState state = block.getStateForPlacement(iAtomicReconstructor.getWorldObject(), blockPos, EnumFacing.UP, 0, 0, 0, stack.getMetadata(),
                                    FakePlayerFactory.getMinecraft((WorldServer) iAtomicReconstructor.getWorldObject()), EnumHand.MAIN_HAND);
                            iAtomicReconstructor.getWorldObject().setBlockState(blockPos, state, 2);

                            iAtomicReconstructor.getWorldObject().playEvent(2001, blockPos, Block.getStateId(state));

                            iAtomicReconstructor.extractEnergy(adaptedUse);
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public float[] getColor() {
        return new float[] { 76F / 255F, 76F / 255F, 76F / 255F };
    }

    @Override
    public int getDistance() {
        return 5;
    }

    private List<WeightedOre> getOreToPut(IBlockState state)
    {
        List<WeightedOre> values = new ArrayList<>();
        map.get(state).forEach((k, v) -> values.add(new WeightedOre(k, map.get(state).get(k))));
        return values;
    }

}
