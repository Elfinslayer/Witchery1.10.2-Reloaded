package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.blocks.BlockStatueOfWorship.TileEntityStatueOfWorship;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;




@SideOnly(Side.CLIENT)
public class RenderStatueOfWorship
  extends TileEntitySpecialRenderer
{
  private final ModelBiped model = new ModelBiped(0.0F);
  

  private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/blocks/statueofworship.png");
  
  public RenderStatueOfWorship() {}
  
  public void func_147500_a(TileEntity tile, double x, double y, double z, float var8) { GL11.glPushMatrix();
    GL11.glTranslated(x, y, z);
    
    model.field_78091_s = true;
    model.field_78119_l = 1;
    model.field_78120_m = 1;
    
    BlockStatueOfWorship.TileEntityStatueOfWorship statue = tile != null ? (BlockStatueOfWorship.TileEntityStatueOfWorship)tile : null;
    World world = statue != null ? statue.func_145831_w() : null;
    
    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    GL11.glTranslatef(0.0F, -1.0F, 0.0F);
    
    if ((world != null) && (statue != null)) {
      int meta = world.func_72805_g(field_145851_c, field_145848_d, field_145849_e);
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
    
    func_147499_a(statue.getLocationSkin());
    GL11.glColor3f(0.7F, 0.7F, 0.7F);
    model.func_78088_a(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
    


    GL11.glPushAttrib(16448);
    GL11.glShadeModel(7424);
    GL11.glDisable(3008);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    

    func_147499_a(TEXTURE_URL);
    GL11.glColor3f(0.8F, 0.8F, 0.8F);
    model.func_78088_a(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
    









    GL11.glPopAttrib();
    
    GL11.glPopMatrix();
  }
}
