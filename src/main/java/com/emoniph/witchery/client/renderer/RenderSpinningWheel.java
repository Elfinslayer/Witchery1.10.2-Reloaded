package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.blocks.BlockSpinningWheel.TileEntitySpinningWheel;
import com.emoniph.witchery.client.model.ModelSpinningWheel;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderSpinningWheel extends TileEntitySpecialRenderer
{
  private final ModelSpinningWheel model;
  
  public RenderSpinningWheel()
  {
    model = new ModelSpinningWheel();
  }
  
  public void func_147500_a(TileEntity tileEntity, double d, double d1, double d2, float f)
  {
    GL11.glPushMatrix();
    


    GL11.glTranslatef((float)d, (float)d1, (float)d2);
    
    BlockSpinningWheel.TileEntitySpinningWheel spinningWheel = (BlockSpinningWheel.TileEntitySpinningWheel)tileEntity;
    




    renderSpinningWheel(spinningWheel, tileEntity.func_145831_w(), field_145851_c, field_145848_d, field_145849_e, BlocksSPINNING_WHEEL);
    

    GL11.glPopMatrix();
  }
  
  private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/blocks/spinningwheel.png");
  
  public void renderSpinningWheel(BlockSpinningWheel.TileEntitySpinningWheel te, World world, int x, int y, int z, Block par1BlockBrewingStand) {
    GL11.glPushMatrix();
    
    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    


    func_147499_a(TEXTURE_URL);
    


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
    
    model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, te);
    
    GL11.glPopMatrix();
  }
}
