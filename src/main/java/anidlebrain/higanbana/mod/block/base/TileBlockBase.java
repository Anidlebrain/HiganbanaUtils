package anidlebrain.higanbana.mod.block.base;

import anidlebrain.higanbana.Higanbana;
import anidlebrain.higanbana.RegistryHandler;
import anidlebrain.higanbana.mod.block.render.IHasModel;
import anidlebrain.higanbana.mod.creative.CreativeTab;
import de.ellpeck.actuallyadditions.mod.tile.TileEntityBase;
import de.ellpeck.actuallyadditions.mod.tile.TileEntityInventoryBase;
import de.ellpeck.actuallyadditions.mod.util.StackUtil;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * @author Andileabrain
 */
public abstract class TileBlockBase extends BlockContainer implements IHasModel {
    public final String name;

    protected TileBlockBase(Material materialIn, String name) {
        super(materialIn);
        this.name = name;
        this.register();
    }

    private void register() {
        this.setRegistryName(Higanbana.MODID, name);
        this.setUnlocalizedName(Objects.requireNonNull(this.getRegistryName()).toString());
        RegistryHandler.BLOCKS_TO_REGISTER.add(this);

        ItemBlock itemBlock = this.getItemBlock();

        itemBlock.setRegistryName(Higanbana.MODID, name);
        RegistryHandler.ITEMS_TO_REGISTER.add(itemBlock);

        this.setCreativeTab(CreativeTab.INSTANCE);
    }

    @Override
    public void registerRendering() {
        Higanbana.PROXY.addRenderRegister(new ItemStack(this), this.getRegistryName(), "inventory");
    }

    private ItemBlock getItemBlock() {
        return new ItemBlock(this);
    }

    protected String getBaseName() {
        return this.name;
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random random) {
        if (!world.isRemote) {
            TileEntity tile = world.getTileEntity(pos);
            if (tile instanceof TileEntityBase) {
                TileEntityBase base = (TileEntityBase) tile;
                if (base.respondsToPulses()) {
                    base.activateOnPulse();
                }
            }
        }
    }


    public void neighborsChangedCustom(World world, BlockPos pos) {
        TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof TileEntityBase) {
            TileEntityBase base = (TileEntityBase) tile;
            if (base.shouldSaveDataOnChangeOrWorldStart()) {
                base.saveDataOnChangeOrWorldStart();
            }
        }
    }

    @Override
    public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
        super.onNeighborChange(world, pos, neighbor);
        if (world instanceof World) {
            this.neighborsChangedCustom((World) world, pos);
        }
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {

    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entity, ItemStack stack) {
        if (stack.hasTagCompound()) {
            TileEntity tile = world.getTileEntity(pos);
            if (tile instanceof TileEntityBase) {
                TileEntityBase base = (TileEntityBase) tile;
                NBTTagCompound compound = stack.getTagCompound().getCompoundTag("Data");
                if (compound != null) {
                    base.readSyncableNBT(compound, TileEntityBase.NBTType.SAVE_BLOCK);
                }
            }
        }
    }

    @Override
    public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        if (!player.capabilities.isCreativeMode) {
            TileEntity tile = world.getTileEntity(pos);
            if (tile instanceof TileEntityBase && ((TileEntityBase) tile).stopFromDropping) {

            }
        }
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof TileEntityBase) {
            TileEntityBase base = (TileEntityBase) tile;
            if (!base.stopFromDropping) {
                NBTTagCompound data = new NBTTagCompound();
                base.writeSyncableNBT(data, TileEntityBase.NBTType.SAVE_BLOCK);

                //Remove unnecessarily saved default values to avoid unstackability
                List<String> keysToRemove = new ArrayList<>();
                for (String key : data.getKeySet()) {
                    NBTBase tag = data.getTag(key);
                    //Remove only ints because they are the most common ones
                    //Add else if below here to remove more types
                    if (tag instanceof NBTTagInt) {
                        if (((NBTTagInt) tag).getInt() == 0) {
                            keysToRemove.add(key);
                        }
                    }
                }
                for (String key : keysToRemove) {
                    data.removeTag(key);
                }

                ItemStack stack = new ItemStack(this.getItemDropped(state, tile.getWorld().rand, fortune), 1, this.damageDropped(state));
                if (data.getSize() == 0) {
                    stack.setTagCompound(new NBTTagCompound());
                    stack.getTagCompound().setTag("Data", data);
                }

                drops.add(stack);
            }
        } else {
            super.getDrops(drops, world, pos, state, fortune);
        }
    }

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
        return willHarvest || super.removedByPlayer(state, world, pos, player, false);
    }

    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack) {
        super.harvestBlock(worldIn, player, pos, state, te, stack);
        worldIn.setBlockToAir(pos);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        if (this.shouldDropInventory(world, pos)) {
            this.dropInventory(world, pos);
        }

        super.breakBlock(world, pos, state);
    }

    public boolean shouldDropInventory(World world, BlockPos pos) {
        return true;
    }

    private void dropInventory(World world, BlockPos position) {
        if (!world.isRemote) {
            TileEntity aTile = world.getTileEntity(position);
            if (aTile instanceof TileEntityInventoryBase) {
                TileEntityInventoryBase tile = (TileEntityInventoryBase) aTile;
                if (tile.inv.getSlots() > 0) {
                    for (int i = 0; i < tile.inv.getSlots(); i++) {
                        this.dropSlotFromInventory(i, tile, world, position);
                    }
                }
            }
        }
    }

    private void dropSlotFromInventory(int i, TileEntityInventoryBase tile, World world, BlockPos pos) {
        ItemStack stack = tile.inv.getStackInSlot(i);
        if (StackUtil.isValid(stack)) {
            float dX = world.rand.nextFloat() * 0.8F + 0.1F;
            float dY = world.rand.nextFloat() * 0.8F + 0.1F;
            float dZ = world.rand.nextFloat() * 0.8F + 0.1F;
            EntityItem entityItem = new EntityItem(world, pos.getX() + dX, pos.getY() + dY, pos.getZ() + dZ, stack.copy());
            float factor = 0.05F;
            entityItem.motionX = world.rand.nextGaussian() * factor;
            entityItem.motionY = world.rand.nextGaussian() * factor + 0.2F;
            entityItem.motionZ = world.rand.nextGaussian() * factor;
            world.spawnEntity(entityItem);
        }
    }
}
