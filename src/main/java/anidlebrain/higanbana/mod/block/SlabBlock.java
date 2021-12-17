package anidlebrain.higanbana.mod.block;

import anidlebrain.higanbana.mod.block.base.BlockBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class SlabBlock extends BlockBase {

    private static final PropertyEnum<EnumBlockSlab> HALF = PropertyEnum.create("half", EnumBlockSlab.class);
    private static final AxisAlignedBB AABB_BOTTOM_HALF = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
    private static final AxisAlignedBB AABB_TOP_HALF = new AxisAlignedBB(0.0D, 0.5D, 0.0D, 1.0D, 1.0D, 1.0D);

    public SlabBlock(String name) {
        super(Material.ROCK, name);
        this.setHardness(1F);
        this.setResistance(1024F);
        this.setSoundType(SoundType.STONE);
        this.setDefaultState(blockState.getBaseState().withProperty(HALF, EnumBlockSlab.BOTTOM));
        this.useNeighborBrightness = true;
        this.register();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, HALF);
    }

    @Override
    protected boolean canSilkHarvest() {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return state.getValue(HALF).equals(EnumBlockSlab.FULL);
    }

    @Override
    public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
        return (state.getValue(HALF).equals(EnumBlockSlab.BOTTOM) && face == EnumFacing.DOWN) || (state.getValue(HALF).equals(EnumBlockSlab.TOP) && face == EnumFacing.UP) || state.getValue(HALF).equals(EnumBlockSlab.FULL);
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing face, float x, float y, float z, int meta, EntityLivingBase placer) {
        IBlockState state = getStateFromMeta(meta);
        return state.getValue(HALF).equals(EnumBlockSlab.FULL) ? state : (face != EnumFacing.DOWN && (face == EnumFacing.UP || (double) y <= 0.5D) ? state.withProperty(HALF, EnumBlockSlab.BOTTOM) : state.withProperty(HALF, EnumBlockSlab.TOP));
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random random) {
        return state.getValue(HALF).equals(EnumBlockSlab.FULL) ? 2 : 1;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return state.getValue(HALF).equals(EnumBlockSlab.FULL);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        switch (state.getValue(HALF)) {
            case TOP:    return AABB_TOP_HALF;
            case BOTTOM: return AABB_BOTTOM_HALF;
            default:     return FULL_BLOCK_AABB;
        }
    }

    @Override
    public boolean isTopSolid(IBlockState state) {
        return state.getValue(HALF).equals(EnumBlockSlab.FULL) || state.getValue(HALF).equals(EnumBlockSlab.TOP);
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {

        if (state.getValue(HALF) == EnumBlockSlab.FULL) {
            return BlockFaceShape.SOLID;
        }

        else if (face == EnumFacing.UP && state.getValue(HALF) == EnumBlockSlab.TOP) {
            return BlockFaceShape.SOLID;
        }

        else {
            return face == EnumFacing.DOWN && state.getValue(HALF) == EnumBlockSlab.BOTTOM ? BlockFaceShape.SOLID : BlockFaceShape.UNDEFINED;
        }
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing face, float hitX, float hitY, float hitZ) {

        ItemStack stack = player.getHeldItem(hand);

        if (!stack.isEmpty() && ((state.getValue(HALF).equals(EnumBlockSlab.TOP) && face.equals(EnumFacing.DOWN)) || (state.getValue(HALF).equals(EnumBlockSlab.BOTTOM) && face.equals(EnumFacing.UP)))) {

            if (stack.getItem() == Item.getItemFromBlock(this)) {

                world.setBlockState(pos, state.withProperty(HALF, EnumBlockSlab.FULL));
                if (!player.isCreative()) { stack.setCount(stack.getCount() - 1); }

                SoundType sound = this.getSoundType(state, world, pos, player);
                world.playSound(player, pos, sound.getPlaceSound(), SoundCategory.BLOCKS, (sound.getVolume() + 1.0F) / 2.0F, sound.getPitch() * 0.8F);
                return true;
            }
        }
        return super.onBlockActivated(world, pos, state, player, hand, face, hitX, hitY, hitZ);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(HALF, EnumBlockSlab.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(HALF).ordinal();
    }

    public enum EnumBlockSlab implements IStringSerializable {

        TOP("top"),
        BOTTOM("bottom"),
        FULL("full");
        private final String name;

        EnumBlockSlab(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }

        @Override
        public String getName() {
            return this.name;
        }

        public static EnumBlockSlab byMetadata(int metadata) {
            if (metadata < 0 || metadata >= values().length) {
                metadata = 0;
            }
            return values()[metadata];
        }
    }
}
