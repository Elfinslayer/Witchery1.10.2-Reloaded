package com.emoniph.witchery.brewing;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.common.CommonProxy;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;





public class RenderWitchVine
  implements ISimpleBlockRenderingHandler
{
  public RenderWitchVine() {}
  
  public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {}
  
  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
  {
    Tessellator tessellator = Tessellator.field_78398_a;
    IIcon iicon = renderer.func_147777_a(block, 0);
    
    if (renderer.func_147744_b()) {
      iicon = field_147840_d;
    }
    
    tessellator.func_78380_c(block.func_149677_c(field_147845_a, x, y, z));
    int color = block.func_149720_d(field_147845_a, x, y, z);
    float f = (color >> 16 & 0xFF) / 255.0F;
    float f1 = (color >> 8 & 0xFF) / 255.0F;
    float f2 = (color & 0xFF) / 255.0F;
    tessellator.func_78386_a(f, f1, f2);
    
    double d0 = iicon.func_94209_e();
    double d1 = iicon.func_94206_g();
    double d2 = iicon.func_94212_f();
    double d3 = iicon.func_94210_h();
    int l = field_147845_a.func_72805_g(x, y, z);
    double d4 = 0.0D;
    double d5 = 0.05000000074505806D;
    
    if (l == 5) {
      tessellator.func_78374_a(x + d5, y + 1 + d4, z + 1 + d4, d0, d1);
      tessellator.func_78374_a(x + d5, y + 0 - d4, z + 1 + d4, d0, d3);
      tessellator.func_78374_a(x + d5, y + 0 - d4, z + 0 - d4, d2, d3);
      tessellator.func_78374_a(x + d5, y + 1 + d4, z + 0 - d4, d2, d1);
      tessellator.func_78381_a();
      
      tessellator.func_78382_b();
      tessellator.func_78380_c(block.func_149677_c(field_147845_a, x, y, z));
      tessellator.func_78386_a(f, f1, f2);
      tessellator.func_78374_a(x - d5, y + 0 - d4, z + 1 + d4, d2, d3);
      tessellator.func_78374_a(x - d5, y + 1 + d4, z + 1 + d4, d2, d1);
      tessellator.func_78374_a(x - d5, y + 1 + d4, z + 0 - d4, d0, d1);
      tessellator.func_78374_a(x - d5, y + 0 - d4, z + 0 - d4, d0, d3);
    }
    


    if (l == 4) {
      tessellator.func_78374_a(x + 1 - d5, y + 0 - d4, z + 1 + d4, d2, d3);
      tessellator.func_78374_a(x + 1 - d5, y + 1 + d4, z + 1 + d4, d2, d1);
      tessellator.func_78374_a(x + 1 - d5, y + 1 + d4, z + 0 - d4, d0, d1);
      tessellator.func_78374_a(x + 1 - d5, y + 0 - d4, z + 0 - d4, d0, d3);
      
      tessellator.func_78381_a();
      
      tessellator.func_78382_b();
      tessellator.func_78380_c(block.func_149677_c(field_147845_a, x, y, z));
      tessellator.func_78386_a(f, f1, f2);
      tessellator.func_78374_a(x + 1.0D + d5, y + 1 + d4, z + 1 + d4, d0, d1);
      tessellator.func_78374_a(x + 1.0D + d5, y + 0 - d4, z + 1 + d4, d0, d3);
      tessellator.func_78374_a(x + 1.0D + d5, y + 0 - d4, z + 0 - d4, d2, d3);
      tessellator.func_78374_a(x + 1.0D + d5, y + 1 + d4, z + 0 - d4, d2, d1);
    }
    
    if (l == 3) {
      tessellator.func_78374_a(x + 1 + d4, y + 0 - d4, z + d5, d2, d3);
      tessellator.func_78374_a(x + 1 + d4, y + 1 + d4, z + d5, d2, d1);
      tessellator.func_78374_a(x + 0 - d4, y + 1 + d4, z + d5, d0, d1);
      tessellator.func_78374_a(x + 0 - d4, y + 0 - d4, z + d5, d0, d3);
      tessellator.func_78381_a();
      
      tessellator.func_78382_b();
      tessellator.func_78380_c(block.func_149677_c(field_147845_a, x, y, z));
      tessellator.func_78386_a(f, f1, f2);
      tessellator.func_78374_a(x + 1 + d4, y + 1 + d4, z - d5, d0, d1);
      tessellator.func_78374_a(x + 1 + d4, y + 0 - d4, z - d5, d0, d3);
      tessellator.func_78374_a(x + 0 - d4, y + 0 - d4, z - d5, d2, d3);
      tessellator.func_78374_a(x + 0 - d4, y + 1 + d4, z - d5, d2, d1);
    }
    
    if (l == 2) {
      tessellator.func_78374_a(x + 1 + d4, y + 1 + d4, z + 1 - d5, d0, d1);
      tessellator.func_78374_a(x + 1 + d4, y + 0 - d4, z + 1 - d5, d0, d3);
      tessellator.func_78374_a(x + 0 - d4, y + 0 - d4, z + 1 - d5, d2, d3);
      tessellator.func_78374_a(x + 0 - d4, y + 1 + d4, z + 1 - d5, d2, d1);
      tessellator.func_78381_a();
      
      tessellator.func_78382_b();
      tessellator.func_78380_c(block.func_149677_c(field_147845_a, x, y, z));
      tessellator.func_78386_a(f, f1, f2);
      tessellator.func_78374_a(x + 1 + d4, y + 0 - d4, z + 1.0D + d5, d2, d3);
      tessellator.func_78374_a(x + 1 + d4, y + 1 + d4, z + 1.0D + d5, d2, d1);
      tessellator.func_78374_a(x + 0 - d4, y + 1 + d4, z + 1.0D + d5, d0, d1);
      tessellator.func_78374_a(x + 0 - d4, y + 0 - d4, z + 1.0D + d5, d0, d3);
    }
    return true;
  }
  
  public boolean shouldRender3DInInventory(int modelId)
  {
    return false;
  }
  
  public int getRenderId()
  {
    return Witchery.proxy.getVineRenderId();
  }
}
