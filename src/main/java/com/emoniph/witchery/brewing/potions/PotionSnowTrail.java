package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.util.BlockActionCircle;
import com.emoniph.witchery.util.Coord;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class PotionSnowTrail extends PotionBase
{
  public PotionSnowTrail(int id, int color)
  {
    super(id, color);
  }
  
  public boolean func_76397_a(int duration, int amplifier)
  {
    return duration % 10 == 0;
  }
  
  public void func_76394_a(EntityLivingBase entity, int amplifier)
  {
    if (!field_70170_p.field_72995_K) {
      for (int l = 0; l < 4; l++) {
        int i = MathHelper.func_76128_c(field_70165_t + (l % 2 * 2 - 1) * 0.25F);
        int j = MathHelper.func_76128_c(field_70163_u);
        int k = MathHelper.func_76128_c(field_70161_v + (l / 2 % 2 * 2 - 1) * 0.25F);
        
        if (field_70170_p.func_147439_a(i, j, k).func_149688_o() == Material.field_151579_a) {
          float temp = field_70170_p.func_72807_a(i, k).func_150564_a(i, j, k);
          if ((temp < 1.6F) && (Blocks.field_150431_aC.func_149742_c(field_70170_p, i, j, k))) {
            field_70170_p.func_147449_b(i, j, k, Blocks.field_150431_aC);
          }
        }
      }
      
      if (((entity instanceof net.minecraft.entity.monster.EntitySnowman)) && (field_70170_p.field_73012_v.nextInt(20) == 0)) {
        field_70170_p.func_72876_a(entity, field_70165_t, field_70163_u, field_70161_v, 3.0F, false);
        Coord coord = new Coord(entity);
        createSnowCovering(field_70170_p, x, y, z, 8, null);
        entity.func_70106_y();
      }
    }
  }
  
  public static void createSnowCovering(World world, int x, int y, int z, int radius, EntityPlayer source) {
    if (com.emoniph.witchery.util.BlockProtect.checkModsForBreakOK(world, x, y, z, source)) {
      new BlockActionCircle()
      {
        public void onBlock(World world, int x, int y, int z) {
          int maxSearch = 8;
          if (world.func_147437_c(x, y, z)) {
            for (int i = 1; i < 8; i++) {
              int dy = y - i;
              Block block = world.func_147439_a(x, dy, z);
              if (block.func_149688_o() != Material.field_151579_a) {
                setBlockToSnow(world, x, dy + 1, z, block);
                break;
              }
            }
          } else {
            for (int i = 1; i < 8; i++) {
              int dy = y + i;
              Block block = world.func_147439_a(x, dy, z);
              if (block.func_149688_o() == Material.field_151579_a) {
                Block blockBelow = world.func_147439_a(x, dy - 1, z);
                setBlockToSnow(world, x, dy, z, blockBelow);
                break;
              }
            }
          }
        }
        
        private void setBlockToSnow(World world, int x, int y, int z, Block blockBelow) {
          if ((blockBelow.func_149662_c()) || (blockBelow.func_149688_o() == Material.field_151584_j))
            world.func_147449_b(x, y, z, Blocks.field_150431_aC); } }.processFilledCircle(world, x, y, z, radius);
    }
  }
}
