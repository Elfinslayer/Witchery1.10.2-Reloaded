package com.emoniph.witchery.infusion.infusions;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.blocks.BlockBarrier;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.util.BlockSide;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.EntityUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class InfusionLight extends Infusion
{
  private static final int BARRIER_RADIUS = 2;
  private static final int BARRIER_HEIGHT = 3;
  private static final boolean BARRIER_BLOCKS_PLAYERS = true;
  private static final int AGGRO_DROP_RADIUS = 20;
  protected static final int BARRIER_TICKS_TO_LIVE_ = 200;
  
  public InfusionLight(int infusionID)
  {
    super(infusionID);
  }
  
  public IIcon getPowerBarIcon(EntityPlayer player, int index)
  {
    return Blocks.field_150433_aE.func_149691_a(0, 0);
  }
  
  public void onLeftClickEntity(ItemStack itemstack, World world, EntityPlayer player, Entity otherEntity)
  {
    if (field_72995_K) {
      return;
    }
    
    if ((otherEntity instanceof EntityLivingBase)) {
      EntityLivingBase otherLivingEntity = (EntityLivingBase)otherEntity;
      int UPSHIFT = 4;
      int posX = (int)field_70165_t;
      int posY = (int)field_70163_u + 4;
      int posZ = (int)field_70161_v;
      
      if ((world.func_147437_c(posX, posY, posZ)) && (world.func_147437_c(posX, posY + 1, posZ)) && (world.func_147437_c(posX, posY + 2, posZ)) && (world.func_147437_c(posX + 1, posY, posZ)) && (world.func_147437_c(posX + 1, posY + 1, posZ)) && (world.func_147437_c(posX + 1, posY + 2, posZ)) && (world.func_147437_c(posX, posY, posZ + 1)) && (world.func_147437_c(posX, posY + 1, posZ + 1)) && (world.func_147437_c(posX, posY + 2, posZ + 1)) && (world.func_147437_c(posX - 1, posY, posZ)) && (world.func_147437_c(posX - 1, posY + 1, posZ)) && (world.func_147437_c(posX - 1, posY + 2, posZ)) && (world.func_147437_c(posX, posY, posZ - 1)) && (world.func_147437_c(posX, posY + 1, posZ - 1)) && (world.func_147437_c(posX, posY + 2, posZ - 1)) && (consumeCharges(world, player, 5, true)))
      {








        drawFilledCircle(world, posX, posZ, posY - 1, 2, null);
        
        for (int y = posY; y < posY + 3; y++) {
          drawCircle(world, posX, posZ, y, 2, null);
        }
        
        drawFilledCircle(world, posX, posZ, posY + 3, 2, null);
        otherLivingEntity.func_70634_a(posX, posY, posZ);
      }
    }
  }
  
  public void onUsingItemTick(ItemStack itemstack, World world, EntityPlayer player, int countdown)
  {
    if (field_72995_K) {
      if (!player.func_70115_ae()) {
        int var5 = MathHelper.func_76128_c(field_70165_t);
        int var6 = MathHelper.func_76128_c(field_70163_u - 2.0D);
        int var7 = MathHelper.func_76128_c(field_70161_v);
        if (world.func_147439_a(var5, var6, var7) != Blocks.field_150432_aD) {
          if (field_70122_E) {
            if (!player.func_70090_H()) {
              field_70159_w *= 1.6500000476837158D;
              field_70179_y *= 1.6500000476837158D;
            } else {
              field_70159_w *= 1.100000023841858D;
              field_70179_y *= 1.100000023841858D;
            }
          }
        } else {
          field_70159_w *= 1.100000023841858D;
          field_70179_y *= 1.100000023841858D;
        }
      }
      return;
    }
    
    int elapsedTicks = getMaxItemUseDuration(itemstack) - countdown;
    if ((elapsedTicks % 30 == 0) && (elapsedTicks > 19)) {
      if (consumeCharges(world, player, 1, true)) {
        bendLightAroundPlayer(world, player, true);
      } else {
        bendLightAroundPlayer(world, player, false);
      }
    }
  }
  
  protected void bendLightAroundPlayer(World world, EntityPlayer player, boolean active) {
    if (active) {
      player.func_70690_d(new PotionEffect(field_76441_pfield_76415_H, 30, 0, true));
      


      int r = 20;
      AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(field_70165_t - 20.0D, field_70163_u, field_70161_v - 20.0D, field_70165_t + 20.0D, field_70163_u + 2.0D, field_70161_v + 20.0D);
      
      for (Object obj : world.func_72872_a(EntityLiving.class, bounds)) {
        EntityLiving entity = (EntityLiving)obj;
        if ((entity.func_70638_az() == player) && (Coord.distance(field_70165_t, field_70163_u, field_70161_v, field_70165_t, field_70163_u, field_70161_v) <= 20.0D)) {
          EntityUtil.dropAttackTarget(entity);
        }
      }
    } else {
      player.func_82170_o(field_76441_pfield_76415_H);
    }
  }
  



  public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer player, int countdown)
  {
    if (field_72995_K) {
      return;
    }
    
    bendLightAroundPlayer(world, player, false);
    
    int elapsedTicks = getMaxItemUseDuration(itemstack) - countdown;
    if ((elapsedTicks < 20) && (player.func_70093_af()))
    {
      MovingObjectPosition hitMOP = InfusionOtherwhere.doCustomRayTrace(world, player, true, 16.0D);
      if (hitMOP != null) {
        switch (1.$SwitchMap$net$minecraft$util$MovingObjectPosition$MovingObjectType[field_72313_a.ordinal()]) {
        case 1: 
          if ((field_72308_g instanceof EntityLivingBase)) {
            EntityLivingBase otherLivingEntity = (EntityLivingBase)field_72308_g;
            if (consumeCharges(world, player, 3, true))
            {



              int posX = (int)field_70165_t;
              int posY = (int)field_70163_u;
              int posZ = (int)field_70161_v;
              drawFilledCircle(world, posX, posZ, posY - 1, 1, player);
              
              for (int y = posY; y < posY + 3; y++) {
                drawCircle(world, posX, posZ, y, 2, player);
              }
              
              drawFilledCircle(world, posX, posZ, posY + 3, 2, player);
            } }
          break;
        
        case 2: 
          if ((BlockSide.TOP.isEqual(field_72310_e)) && (consumeCharges(world, player, 3, true))) {
            placeBarrierShield(world, player, hitMOP);
          } else if ((!BlockSide.TOP.isEqual(field_72310_e)) && (!BlockSide.TOP.isEqual(field_72310_e)) && (consumeCharges(world, player, 3, true)))
          {
            int b0 = 0;
            
            switch (field_72310_e) {
            case 0: 
            case 1: 
              b0 = 0;
              break;
            case 2: 
            case 3: 
              b0 = 8;
              break;
            case 4: 
            case 5: 
              b0 = 4;
            }
            
            int dx = field_72310_e == 4 ? -1 : field_72310_e == 5 ? 1 : 0;
            int dy = field_72310_e == 1 ? 1 : field_72310_e == 0 ? -1 : 0;
            int dz = field_72310_e == 2 ? -1 : field_72310_e == 3 ? 1 : 0;
            
            int sproutExtent = 16;
            
            boolean isInitialBlockSolid = world.func_147439_a(field_72311_b, field_72312_c, field_72309_d).func_149688_o().func_76220_a();
            

            for (int i = (field_72310_e == 1) && (!isInitialBlockSolid) ? 0 : 1; i < 16; i++) {
              int x = field_72311_b + i * dx;
              int y = field_72312_c + i * dy;
              int z = field_72309_d + i * dz;
              if (!setBlockIfNotSolid(world, x, y, z))
                break;
            }
          }
          break;
        }
        
      }
    }
  }
  

  public static void placeBarrierShield(World world, EntityPlayer player, MovingObjectPosition hitMOP)
  {
    double f1 = MathHelper.func_76134_b(-field_70177_z * 0.017453292F - 3.1415927F);
    double f2 = MathHelper.func_76126_a(-field_70177_z * 0.017453292F - 3.1415927F);
    Vec3 loc = Vec3.func_72443_a(f2, 0.0D, f1);
    
    Material material = world.func_147439_a(field_72311_b, field_72312_c, field_72309_d).func_149688_o();
    int yPlus = 1;
    if ((material != null) && (!material.func_76220_a())) {
      yPlus = 0;
    }
    drawBarrierBlockColumn(world, player, field_72311_b, field_72312_c + yPlus, field_72309_d, 3);
    
    loc.func_72442_b((float)Math.toRadians(90.0D));
    int newX = MathHelper.func_76128_c(field_72311_b + 0.5D + field_72450_a * 1.0D);
    int newZ = MathHelper.func_76128_c(field_72309_d + 0.5D + field_72449_c * 1.0D);
    drawBarrierBlockColumn(world, player, newX, field_72312_c + yPlus, newZ, 3);
    
    loc.func_72442_b((float)Math.toRadians(180.0D));
    newX = MathHelper.func_76128_c(field_72311_b + 0.5D + field_72450_a * 1.0D);
    newZ = MathHelper.func_76128_c(field_72309_d + 0.5D + field_72449_c * 1.0D);
    drawBarrierBlockColumn(world, player, newX, field_72312_c + yPlus, newZ, 3);
  }
  
  private static boolean setBlockIfNotSolid(World world, int x, int y, int z) {
    if (world.func_147439_a(x, y, z).func_149688_o().func_76222_j()) {
      BlockBarrier.setBlock(world, x, y, z, 200, true, null);
      return true;
    }
    return false;
  }
  
  private static void drawBarrierBlockColumn(World world, EntityPlayer player, int posX, int posY, int posZ, int height)
  {
    for (int offsetPosY = posY; offsetPosY < posY + height; offsetPosY++) {
      Material material = world.func_147439_a(posX, offsetPosY, posZ).func_149688_o();
      Block blockID = world.func_147439_a(posX, offsetPosY, posZ);
      if ((material.func_76222_j()) || (blockID == BlocksBARRIER)) {
        BlockBarrier.setBlock(world, posX, offsetPosY, posZ, 200, true, player);
      }
    }
  }
  
  protected void drawCircle(World world, int x0, int z0, int y, int radius, EntityPlayer player) {
    int x = radius;
    int z = 0;
    int radiusError = 1 - x;
    
    while (x >= z) {
      drawPixel(world, x + x0, z + z0, y, player);
      drawPixel(world, z + x0, x + z0, y, player);
      drawPixel(world, -x + x0, z + z0, y, player);
      drawPixel(world, -z + x0, x + z0, y, player);
      drawPixel(world, -x + x0, -z + z0, y, player);
      drawPixel(world, -z + x0, -x + z0, y, player);
      drawPixel(world, x + x0, -z + z0, y, player);
      drawPixel(world, z + x0, -x + z0, y, player);
      
      z++;
      if (radiusError < 0) {
        radiusError += 2 * z + 1;
      } else {
        x--;
        radiusError += 2 * (z - x + 1);
      }
    }
  }
  
  protected void drawFilledCircle(World world, int x0, int z0, int y, int radius, EntityPlayer player) {
    int x = radius;
    int z = 0;
    int radiusError = 1 - x;
    
    while (x >= z) {
      drawLine(world, -x + x0, x + x0, z + z0, y, player);
      drawLine(world, -z + x0, z + x0, x + z0, y, player);
      drawLine(world, -x + x0, x + x0, -z + z0, y, player);
      drawLine(world, -z + x0, z + x0, -x + z0, y, player);
      
      z++;
      
      if (radiusError < 0) {
        radiusError += 2 * z + 1;
      } else {
        x--;
        radiusError += 2 * (z - x + 1);
      }
    }
  }
  
  protected void drawLine(World world, int x1, int x2, int z, int y, EntityPlayer player) {
    for (int x = x1; x <= x2; x++) {
      drawPixel(world, x, z, y, player);
    }
  }
  
  protected void drawPixel(World world, int x, int z, int y, EntityPlayer player) {
    Block blockID = world.func_147439_a(x, y, z);
    Material material = world.func_147439_a(x, y, z).func_149688_o();
    if ((!material.func_76220_a()) || (blockID == BlocksBARRIER) || (material == Material.field_151579_a)) {
      BlockBarrier.setBlock(world, x, y, z, 200, true, player);
    }
  }
}
