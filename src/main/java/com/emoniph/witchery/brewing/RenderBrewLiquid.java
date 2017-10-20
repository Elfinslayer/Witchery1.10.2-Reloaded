package com.emoniph.witchery.brewing;

import com.emoniph.witchery.Witchery;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

public class RenderBrewLiquid implements ISimpleBlockRenderingHandler
{
  public static final RenderBrewLiquid instance = new RenderBrewLiquid();
  static final float LIGHT_Y_NEG = 0.5F;
  static final float LIGHT_Y_POS = 1.0F;
  static final float LIGHT_XZ_NEG = 0.8F;
  static final float LIGHT_XZ_POS = 0.6F;
  static final double RENDER_OFFSET = 0.0010000000474974513D;
  
  public RenderBrewLiquid() {}
  
  public float getFluidHeightAverage(float[] flow) { float total = 0.0F;
    int count = 0;
    
    float end = 0.0F;
    
    for (int i = 0; i < flow.length; i++) {
      if ((flow[i] >= 0.875F) && (end != 1.0F)) {
        end = flow[i];
      }
      
      if (flow[i] >= 0.0F) {
        total += flow[i];
        count++;
      }
    }
    
    if (end == 0.0F) {
      end = total / count;
    }
    return end;
  }
  
  public float getFluidHeightForRender(IBlockAccess world, int x, int y, int z, BlockBrewLiquidEffect block) {
    if (world.func_147439_a(x, y, z) == block) {
      if (world.func_147439_a(x, y - densityDir, z).func_149688_o().func_76224_d()) {
        return 1.0F;
      }
      
      if (world.func_72805_g(x, y, z) == block.getMaxRenderHeightMeta()) {
        return 0.875F;
      }
    }
    return (!world.func_147439_a(x, y, z).func_149688_o().func_76220_a()) && (world.func_147439_a(x, y - densityDir, z) == block) ? 1.0F : block.getQuantaPercentage(world, x, y, z) * 0.875F;
  }
  


  public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {}
  

  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
  {
    if (!(block instanceof BlockBrewLiquidEffect)) {
      return false;
    }
    
    Tessellator tessellator = Tessellator.field_78398_a;
    int color = block.func_149720_d(world, x, y, z);
    float red = (color >> 16 & 0xFF) / 255.0F;
    float green = (color >> 8 & 0xFF) / 255.0F;
    float blue = (color & 0xFF) / 255.0F;
    
    BlockBrewLiquidEffect theFluid = (BlockBrewLiquidEffect)block;
    int bMeta = world.func_72805_g(x, y, z);
    
    boolean renderTop = world.func_147439_a(x, y - densityDir, z) != theFluid;
    
    boolean renderBottom = (block.func_149646_a(world, x, y + densityDir, z, 0)) && (world.func_147439_a(x, y + densityDir, z) != theFluid);
    

    boolean[] renderSides = { block.func_149646_a(world, x, y, z - 1, 2), block.func_149646_a(world, x, y, z + 1, 3), block.func_149646_a(world, x - 1, y, z, 4), block.func_149646_a(world, x + 1, y, z, 5) };
    


    if ((!renderTop) && (!renderBottom) && (renderSides[0] == 0) && (renderSides[1] == 0) && (renderSides[2] == 0) && (renderSides[3] == 0)) {
      return false;
    }
    boolean rendered = false;
    
    float flow11 = getFluidHeightForRender(world, x, y, z, theFluid);
    double heightNE;
    double heightNW; double heightSW; double heightSE; double heightNE; if (flow11 != 1.0F) {
      float flow00 = getFluidHeightForRender(world, x - 1, y, z - 1, theFluid);
      float flow01 = getFluidHeightForRender(world, x - 1, y, z, theFluid);
      float flow02 = getFluidHeightForRender(world, x - 1, y, z + 1, theFluid);
      float flow10 = getFluidHeightForRender(world, x, y, z - 1, theFluid);
      float flow12 = getFluidHeightForRender(world, x, y, z + 1, theFluid);
      float flow20 = getFluidHeightForRender(world, x + 1, y, z - 1, theFluid);
      float flow21 = getFluidHeightForRender(world, x + 1, y, z, theFluid);
      float flow22 = getFluidHeightForRender(world, x + 1, y, z + 1, theFluid);
      
      double heightNW = getFluidHeightAverage(new float[] { flow00, flow01, flow10, flow11 });
      double heightSW = getFluidHeightAverage(new float[] { flow01, flow02, flow12, flow11 });
      double heightSE = getFluidHeightAverage(new float[] { flow12, flow21, flow22, flow11 });
      heightNE = getFluidHeightAverage(new float[] { flow10, flow20, flow21, flow11 });
    } else {
      heightNW = flow11;
      heightSW = flow11;
      heightSE = flow11;
      heightNE = flow11;
    }
    
    boolean rises = densityDir == 1;
    if ((field_147837_f) || (renderTop)) {
      rendered = true;
      IIcon iconStill = getIcon(block.func_149691_a(1, bMeta));
      float flowDir = (float)BlockBrewLiquidEffect.getFlowDirection(world, x, y, z);
      
      if (flowDir > -999.0F) {
        iconStill = getIcon(block.func_149691_a(2, bMeta));
      }
      
      heightNW -= 0.0010000000474974513D;
      heightSW -= 0.0010000000474974513D;
      heightSE -= 0.0010000000474974513D;
      heightNE -= 0.0010000000474974513D;
      double v3;
      double u2;
      double v2;
      double u1; double v1; double u4; double v4; double u3; double v3; if (flowDir < -999.0F) {
        double u2 = iconStill.func_94214_a(0.0D);
        double v2 = iconStill.func_94207_b(0.0D);
        double u1 = u2;
        double v1 = iconStill.func_94207_b(16.0D);
        double u4 = iconStill.func_94214_a(16.0D);
        double v4 = v1;
        double u3 = u4;
        v3 = v2;
      } else {
        float xFlow = MathHelper.func_76126_a(flowDir) * 0.25F;
        float zFlow = MathHelper.func_76134_b(flowDir) * 0.25F;
        u2 = iconStill.func_94214_a(8.0F + (-zFlow - xFlow) * 16.0F);
        v2 = iconStill.func_94207_b(8.0F + (-zFlow + xFlow) * 16.0F);
        u1 = iconStill.func_94214_a(8.0F + (-zFlow + xFlow) * 16.0F);
        v1 = iconStill.func_94207_b(8.0F + (zFlow + xFlow) * 16.0F);
        u4 = iconStill.func_94214_a(8.0F + (zFlow + xFlow) * 16.0F);
        v4 = iconStill.func_94207_b(8.0F + (zFlow - xFlow) * 16.0F);
        u3 = iconStill.func_94214_a(8.0F + (zFlow - xFlow) * 16.0F);
        v3 = iconStill.func_94207_b(8.0F + (-zFlow - xFlow) * 16.0F);
      }
      
      tessellator.func_78380_c(block.func_149677_c(world, x, y, z));
      tessellator.func_78386_a(1.0F * red, 1.0F * green, 1.0F * blue);
      
      if (!rises) {
        tessellator.func_78374_a(x + 0, y + heightNW, z + 0, u2, v2);
        tessellator.func_78374_a(x + 0, y + heightSW, z + 1, u1, v1);
        tessellator.func_78374_a(x + 1, y + heightSE, z + 1, u4, v4);
        tessellator.func_78374_a(x + 1, y + heightNE, z + 0, u3, v3);
      } else {
        tessellator.func_78374_a(x + 1, y + 1 - heightNE, z + 0, u3, v3);
        tessellator.func_78374_a(x + 1, y + 1 - heightSE, z + 1, u4, v4);
        tessellator.func_78374_a(x + 0, y + 1 - heightSW, z + 1, u1, v1);
        tessellator.func_78374_a(x + 0, y + 1 - heightNW, z + 0, u2, v2);
      }
    }
    
    if ((field_147837_f) || (renderBottom)) {
      rendered = true;
      tessellator.func_78380_c(block.func_149677_c(world, x, y - 1, z));
      if (!rises) {
        tessellator.func_78386_a(0.5F * red, 0.5F * green, 0.5F * blue);
        renderer.func_147768_a(block, x, y + 0.0010000000474974513D, z, getIcon(block.func_149691_a(0, bMeta)));
      } else {
        tessellator.func_78386_a(1.0F * red, 1.0F * green, 1.0F * blue);
        renderer.func_147806_b(block, x, y + 0.0010000000474974513D, z, getIcon(block.func_149691_a(1, bMeta)));
      }
    }
    
    for (int side = 0; side < 4; side++) {
      int x2 = x;
      int z2 = z;
      
      switch (side) {
      case 0: 
        z2--;
        break;
      case 1: 
        z2++;
        break;
      case 2: 
        x2--;
        break;
      case 3: 
        x2++;
      }
      
      
      IIcon iconFlow = getIcon(block.func_149691_a(side + 2, bMeta));
      if ((field_147837_f) || (renderSides[side] != 0)) {
        rendered = true;
        
        double tz2;
        double ty1;
        double ty2;
        double tx1;
        double tx2;
        double tz1;
        double tz2;
        if (side == 0) {
          double ty1 = heightNW;
          double ty2 = heightNE;
          double tx1 = x;
          double tx2 = x + 1;
          double tz1 = z + 0.0010000000474974513D;
          tz2 = z + 0.0010000000474974513D; } else { double tz2;
          if (side == 1) {
            double ty1 = heightSE;
            double ty2 = heightSW;
            double tx1 = x + 1;
            double tx2 = x;
            double tz1 = z + 1 - 0.0010000000474974513D;
            tz2 = z + 1 - 0.0010000000474974513D; } else { double tz2;
            if (side == 2) {
              double ty1 = heightSW;
              double ty2 = heightNW;
              double tx1 = x + 0.0010000000474974513D;
              double tx2 = x + 0.0010000000474974513D;
              double tz1 = z + 1;
              tz2 = z;
            } else {
              ty1 = heightNE;
              ty2 = heightSE;
              tx1 = x + 1 - 0.0010000000474974513D;
              tx2 = x + 1 - 0.0010000000474974513D;
              tz1 = z;
              tz2 = z + 1;
            }
          } }
        float u1Flow = iconFlow.func_94214_a(0.0D);
        float u2Flow = iconFlow.func_94214_a(8.0D);
        float v1Flow = iconFlow.func_94207_b((1.0D - ty1) * 16.0D * 0.5D);
        float v2Flow = iconFlow.func_94207_b((1.0D - ty2) * 16.0D * 0.5D);
        float v3Flow = iconFlow.func_94207_b(8.0D);
        tessellator.func_78380_c(block.func_149677_c(world, x2, y, z2));
        float sideLighting = 1.0F;
        
        if (side < 2) {
          sideLighting = 0.8F;
        } else {
          sideLighting = 0.6F;
        }
        
        tessellator.func_78386_a(1.0F * sideLighting * red, 1.0F * sideLighting * green, 1.0F * sideLighting * blue);
        

        if (!rises) {
          tessellator.func_78374_a(tx1, y + ty1, tz1, u1Flow, v1Flow);
          tessellator.func_78374_a(tx2, y + ty2, tz2, u2Flow, v2Flow);
          tessellator.func_78374_a(tx2, y + 0, tz2, u2Flow, v3Flow);
          tessellator.func_78374_a(tx1, y + 0, tz1, u1Flow, v3Flow);
        } else {
          tessellator.func_78374_a(tx1, y + 1 - 0, tz1, u1Flow, v3Flow);
          tessellator.func_78374_a(tx2, y + 1 - 0, tz2, u2Flow, v3Flow);
          tessellator.func_78374_a(tx2, y + 1 - ty2, tz2, u2Flow, v2Flow);
          tessellator.func_78374_a(tx1, y + 1 - ty1, tz1, u1Flow, v1Flow);
        }
      }
    }
    field_147855_j = 0.0D;
    field_147857_k = 1.0D;
    return rendered;
  }
  

  public boolean shouldRender3DInInventory(int modelId)
  {
    return false;
  }
  
  public int getRenderId()
  {
    return Witchery.proxy.getBrewLiquidRenderId();
  }
  
  private IIcon getIcon(IIcon icon) {
    if (icon != null)
      return icon;
    return ((TextureMap)Minecraft.func_71410_x().func_110434_K().func_110581_b(TextureMap.field_110575_b)).func_110572_b("missingno");
  }
}
