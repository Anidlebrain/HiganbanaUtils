package anidlebrain.higanbana.render;

import anidlebrain.higanbana.Higanbana;
import anidlebrain.higanbana.tile.TileEntityDisplay;
import de.ellpeck.actuallyadditions.mod.util.AssetUtil;
import de.ellpeck.actuallyadditions.mod.util.StackUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class RenderDisplay extends TileEntitySpecialRenderer<TileEntityDisplay> {

    @Override
    public void render(TileEntityDisplay tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {

        ItemStack stack = tile.inv.getStackInSlot(0);
        if (StackUtil.isValid(stack)) {
            GlStateManager.pushMatrix();
            GlStateManager.translate((float) x + 0.5F, (float) y + 1F, (float) z + 0.5F);

            double boop = Minecraft.getSystemTime() / 800D;
            GlStateManager.translate(0D, Math.sin(boop % (2 * Math.PI)) * 0.065, 0D);
            GlStateManager.rotate((float) (boop * 40D % 360), 0, 1, 0);

            float scale = stack.getItem() instanceof ItemBlock ? 0.85F : 0.65F;
            GlStateManager.scale(scale, scale, scale);
            try {
                AssetUtil.renderItemInWorld(stack);
            } catch (Exception e) {
                Higanbana.LOGGER.error("Something went wrong trying to render an item in a display stand! The item is " + stack.getItem().getRegistryName() + "!", e);
            }

            GlStateManager.popMatrix();
        }
    }
}
