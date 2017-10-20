package com.emoniph.witchery.item;

import java.util.List;
import net.minecraft.world.World;

public class ItemEntityLocatorTexture extends net.minecraft.client.renderer.texture.TextureCompass
{
  public ItemEntityLocatorTexture()
  {
    super("witchery:entitylocator");
  }
  
  public void func_94241_a(World world, double playerX, double playerY, double playerZ, boolean p_94241_8_, boolean p_94241_9_)
  {
    if (!field_110976_a.isEmpty()) {
      double d3 = 0.0D;
      
      if ((world != null) && (!p_94241_8_)) {
        net.minecraft.util.ChunkCoordinates chunkcoordinates = world.func_72861_E();
        double d4 = field_71574_a - playerX;
        double d5 = field_71573_c - playerY;
        playerZ %= 360.0D;
        d3 = -((playerZ - 90.0D) * 3.141592653589793D / 180.0D - Math.atan2(d5, d4));
        
        if (!field_73011_w.func_76569_d()) {
          d3 = Math.random() * 3.141592653589793D * 2.0D;
        }
      }
      
      if (p_94241_9_) {
        field_94244_i = d3;
      }
      else
      {
        for (double d6 = d3 - field_94244_i; d6 < -3.141592653589793D; d6 += 6.283185307179586D) {}
        


        while (d6 >= 3.141592653589793D) {
          d6 -= 6.283185307179586D;
        }
        
        if (d6 < -1.0D) {
          d6 = -1.0D;
        }
        
        if (d6 > 1.0D) {
          d6 = 1.0D;
        }
        
        field_94242_j += d6 * 0.1D;
        field_94242_j *= 0.8D;
        field_94244_i += field_94242_j;
      }
      


      int i = (int)((field_94244_i / 6.283185307179586D + 1.0D) * field_110976_a.size()) % field_110976_a.size();
      while (i < 0) { i = (i + field_110976_a.size()) % field_110976_a.size();
      }
      


      if (i != field_110973_g) {
        field_110973_g = i;
        net.minecraft.client.renderer.texture.TextureUtil.func_147955_a((int[][])field_110976_a.get(field_110973_g), field_130223_c, field_130224_d, field_110975_c, field_110974_d, false, false);
      }
    }
  }
}
