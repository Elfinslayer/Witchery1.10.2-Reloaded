package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.blocks.BlockFetish.TileEntityFetish;
import com.emoniph.witchery.client.model.ModelFetishScarecrow;
import com.emoniph.witchery.client.model.ModelFetishTrent;
import com.emoniph.witchery.util.BlockUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderFetish
  extends TileEntitySpecialRenderer
{
  private static final ResourceLocation TEXTURE_URL_SCARECROW = new ResourceLocation("witchery", "textures/blocks/scarecrow.png");
  private static final ResourceLocation TEXTURE_URL_TRENT = new ResourceLocation("witchery", "textures/blocks/trent.png");
  private final ModelFetishScarecrow modelScarecrow;
  private final ModelFetishTrent modelTrent;
  
  public RenderFetish()
  {
    modelScarecrow = new ModelFetishScarecrow();
    modelTrent = new ModelFetishTrent();
  }
  
  public void func_147500_a(TileEntity te, double d, double d1, double d2, float f)
  {
    GL11.glPushMatrix();
    GL11.glTranslatef((float)d, (float)d1, (float)d2);
    BlockFetish.TileEntityFetish fetish = (te instanceof BlockFetish.TileEntityFetish) ? (BlockFetish.TileEntityFetish)te : null;
    
    if ((fetish != null) && (fetish.isSpectral())) {
      GL11.glEnable(2977);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.6F);
    }
    
    renderModel(fetish, te.func_145831_w(), field_145851_c, field_145848_d, field_145849_e);
    GL11.glPopMatrix();
  }
  
  public void renderModel(BlockFetish.TileEntityFetish te, World world, int x, int y, int z) {
    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    GL11.glTranslatef(0.0F, -1.0F, 0.0F);
    
    if (world != null) {
      int meta = world.func_72805_g(x, y, z);
      
      float rotation = 0.0F;
      switch (meta) {
      case 2: 
        rotation = 0.0F;
        break;
      case 3: 
        rotation = 180.0F;
        break;
      case 4: 
        rotation = 270.0F;
        break;
      case 5: 
        rotation = 90.0F;
      }
      
      
      GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
    }
    
    Block block = world != null ? BlockUtil.getBlock(world, x, y, z) : null;
    if (block == null) {
      block = te.getExpectedBlock();
    }
    if (block == BlocksFETISH_TREANT_IDOL) {
      func_147499_a(TEXTURE_URL_TRENT);
      modelTrent.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, te);
    } else if (block != BlocksFETISH_WITCHS_LADDER)
    {
      func_147499_a(TEXTURE_URL_SCARECROW);
      modelScarecrow.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, te);
    }
  }
  
  public static class RenderFetishBlockItem extends RenderBlockItem {
    private final Block block;
    private final BlockFetish.TileEntityFetish tileFetish;
    
    public RenderFetishBlockItem(Block block, TileEntitySpecialRenderer render, BlockFetish.TileEntityFetish dummy) { super(dummy);
      this.block = block;
      tileFetish = dummy;
    }
    
    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data)
    {
      tileFetish.setExpectedBlock(block);
      NBTTagCompound nbtRoot = item.func_77978_p();
      if ((nbtRoot != null) && (nbtRoot.func_74764_b("BlockColor"))) {
        tileFetish.setColor(nbtRoot.func_74771_c("BlockColor"));
      }
      super.renderItem(type, item, data);
    }
  }
}
