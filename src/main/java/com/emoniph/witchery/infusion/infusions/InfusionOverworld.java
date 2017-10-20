package com.emoniph.witchery.infusion.infusions;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.common.ServerTickEvents.ServerTickTask;
import com.emoniph.witchery.entity.EntityWitchProjectile;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.network.PacketPipeline;
import com.emoniph.witchery.network.PacketPushTarget;
import com.emoniph.witchery.ritual.rites.RiteProtectionCircleRepulsive;
import com.emoniph.witchery.util.BlockProtect;
import com.emoniph.witchery.util.BlockSide;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.EarthItems;
import com.emoniph.witchery.util.ParticleEffect;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingFallEvent;

public class InfusionOverworld extends Infusion
{
  public InfusionOverworld(int infusionID)
  {
    super(infusionID);
  }
  
  public IIcon getPowerBarIcon(EntityPlayer player, int index)
  {
    return Blocks.field_150346_d.func_149691_a(0, 0);
  }
  
  public void onFalling(World world, EntityPlayer player, LivingFallEvent event)
  {
    if (distance > 3.0F) {
      int blockX = MathHelper.func_76128_c(field_70165_t);
      int blockY = MathHelper.func_76128_c(field_70163_u) - 1;
      int blockZ = MathHelper.func_76128_c(field_70161_v);
      
      Block blockID = world.func_147439_a(blockX, blockY, blockZ);
      if ((blockID == Blocks.field_150349_c) || (blockID == Blocks.field_150349_c) || (blockID == Blocks.field_150391_bh) || (blockID == Blocks.field_150351_n) || (blockID == Blocks.field_150354_m) || (blockID == Blocks.field_150433_aE))
      {
        if (player.func_70093_af()) {
          if (consumeCharges(world, player, 10, true)) {
            distance = 0.0F;
            int EXPLOSION_STRENGTH = 3;
            world.func_72876_a(player, field_70165_t, blockY + 0.5D, field_70161_v, 3.0F, true);
          }
        }
        else if (consumeCharges(world, player, 5, true)) {
          distance = 0.0F;
          world.func_147468_f(blockX, blockY, blockZ);
          ItemStack itemstack = new ItemStack(blockID, 1, 0);
          EntityItem blockEntity = new EntityItem(world, blockX, blockY, blockZ, itemstack);
          world.func_72838_d(blockEntity);
        }
      }
    }
  }
  


  public void onLeftClickEntity(ItemStack itemstack, World world, EntityPlayer player, Entity otherEntity)
  {
    if (field_72995_K) {
      return;
    }
    
    if ((otherEntity instanceof EntityLivingBase)) {
      EntityLivingBase otherLivingEntity = (EntityLivingBase)otherEntity;
      int posX = MathHelper.func_76128_c(field_70165_t);
      int posY = MathHelper.func_76128_c(field_70163_u);
      int posZ = MathHelper.func_76128_c(field_70161_v);
      boolean isWearingMetalArmour = false;
      for (int i = 0; i < 5; i++) {
        ItemStack heldStack = otherLivingEntity.func_71124_b(i);
        if (EarthItems.instance().isMatch(heldStack)) {
          isWearingMetalArmour = true;
          break;
        }
      }
      
      if (isWearingMetalArmour) {
        double ACCELERATION = 3.0D;
        if (player.func_70093_af()) {
          if (consumeCharges(world, player, 4, true)) {
            Vec3 look = player.func_70040_Z();
            double motionX = field_72450_a * 0.8D * 3.0D;
            double motionY = 1.5D;
            double motionZ = field_72449_c * 0.8D * 3.0D;
            if ((otherLivingEntity instanceof EntityPlayer)) {
              EntityPlayer targetPlayer = (EntityPlayer)otherLivingEntity;
              Witchery.packetPipeline.sendTo(new PacketPushTarget(motionX, 1.5D, motionZ), targetPlayer);
            } else {
              field_70159_w = motionX;
              field_70181_x = 1.5D;
              field_70179_y = motionZ;
            }
          }
        }
        else if (consumeCharges(world, player, 2, true)) {
          Vec3 look = player.func_70040_Z();
          double motionX = field_72450_a * 0.8D * 3.0D;
          double motionY = 0.30000000000000004D;
          double motionZ = field_72449_c * 0.8D * 3.0D;
          if ((otherLivingEntity instanceof EntityPlayer)) {
            EntityPlayer targetPlayer = (EntityPlayer)otherLivingEntity;
            Witchery.packetPipeline.sendTo(new PacketPushTarget(motionX, 0.30000000000000004D, motionZ), targetPlayer);
          } else {
            field_70159_w = motionX;
            field_70181_x = 0.30000000000000004D;
            field_70179_y = motionZ;
          }
        }
      }
    }
  }
  

  public void onUsingItemTick(ItemStack itemstack, World world, EntityPlayer player, int countdown)
  {
    if (!field_72995_K) {
      int elapsedTicks = getMaxItemUseDuration(itemstack) - countdown;
      int seconds = elapsedTicks / 20;
      
      if (player.func_70093_af()) {
        if ((seconds >= 2) && (elapsedTicks % 4 == 0) && (consumeCharges(world, player, 1, true)))
        {
          int AreaOfEffect = 6;
          
          List entities = world.func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a(field_70165_t - 6.0D, field_70163_u - 6.0D, field_70161_v - 6.0D, field_70165_t + 6.0D, field_70163_u + 6.0D, field_70161_v + 6.0D));
          




          for (int i = 0; i < entities.size(); i++) {
            EntityItem entity = (EntityItem)entities.get(i);
            if (EarthItems.instance().isMatch(entity.func_92059_d())) {
              double d0 = 8.0D;
              double motionX = 0.0D;double motionY = 0.0D;double motionZ = 0.0D;
              double d1 = (field_70165_t - field_70165_t) / d0;
              double d2 = (field_70163_u + player.func_70047_e() - field_70163_u) / d0;
              double d3 = (field_70161_v - field_70161_v) / d0;
              double d4 = Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
              double d5 = 2.0D;
              
              if (d5 > 0.0D) {
                d5 *= d5;
                motionX += d1 / Math.max(Math.abs(d1), 0.0D) * 1.0D;
                motionY += d2 / Math.max(Math.abs(d1), 0.0D) * 1.0D;
                motionZ += d3 / Math.max(Math.abs(d1), 0.0D) * 1.0D;
              }
              
              boolean oldClip = field_70145_X;
              field_70145_X = true;
              entity.func_70091_d(motionX, motionY, motionZ);
              field_70145_X = oldClip;
            }
          }
          
          int AreaOfEffect2 = 6;
          
          for (int x = (int)field_70165_t - 6; x <= (int)field_70165_t + 6; x++) {
            for (int y = (int)field_70163_u - 3; y <= (int)field_70163_u + 3; y++) {
              for (int z = (int)field_70161_v - 6; z <= (int)field_70161_v + 6; z++) {
                Block id = world.func_147439_a(x, y, z);
                if (id != Blocks.field_150350_a) {
                  Item ingot = EarthItems.instance().oreToIngot(id);
                  if ((ingot != null) && (!field_72995_K) && (consumeCharges(world, player, 2, true))) {
                    world.func_147465_d(x, y, z, Blocks.field_150348_b, 0, 3);
                    world.func_72838_d(new EntityItem(world, x, y, z, new ItemStack(ingot)));
                  }
                }
              }
            }
          }
        }
      }
      else if ((seconds >= 2) && (elapsedTicks % 20 == 0)) {
        playSound(world, player, "random.orb");
      }
    }
  }
  

  public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer player, int countdown)
  {
    if (field_72995_K) {
      return;
    }
    
    int elapsedTicks = getMaxItemUseDuration(itemstack) - countdown;
    
    MovingObjectPosition hit = InfusionOtherwhere.doCustomRayTrace(world, player, true, 4.0D);
    if (hit != null) {
      switch (1.$SwitchMap$net$minecraft$util$MovingObjectPosition$MovingObjectType[field_72313_a.ordinal()]) {
      case 1: 
        if ((!player.func_70093_af()) && 
          ((field_72308_g instanceof EntityLiving)) && (consumeCharges(world, player, 2, true))) {
          EntityLiving entity = (EntityLiving)field_72308_g;
          ItemStack heldItem = entity.func_70694_bm();
          
          if ((heldItem != null) && (EarthItems.instance().isMatch(heldItem)) && 
            (!field_72995_K)) {
            entity.func_70099_a(heldItem, 2.0F);
            entity.func_70062_b(0, null);
          }
          

          return;
        }
        
        break;
      case 2: 
        int DEPTH = 3;
        if ((!player.func_70093_af()) && (BlockSide.TOP.isEqual(field_72310_e)) && (world.func_147439_a(field_72311_b, field_72312_c - 9 - 1, field_72309_d).func_149688_o().func_76220_a()) && (consumeCharges(world, player, 2, true)))
        {

          for (int h = 0; h < 6; h++) {
            int originY = field_72312_c - h;
            Block blockID = world.func_147439_a(field_72311_b, originY, field_72309_d);
            if (BlockProtect.canBreak(blockID, world)) {
              int blockMetadata = world.func_72805_g(field_72311_b, originY, field_72309_d);
              world.func_147468_f(field_72311_b, originY, field_72309_d);
              if (BlockProtect.canBreak(field_72311_b, originY + 3, field_72309_d, world)) {
                world.func_147465_d(field_72311_b, originY + 3, field_72309_d, blockID, blockMetadata, 3);
              }
              
              AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(field_72311_b, field_72312_c, field_72309_d, field_72311_b + 1, field_72312_c + 2, field_72309_d + 1);
              for (Object obj : world.func_72872_a(Entity.class, bounds)) {
                Entity entity = (Entity)obj;
                if ((entity instanceof EntityLivingBase)) {
                  ((EntityLivingBase)entity).func_70634_a(field_70165_t, field_70163_u + 3.0D, field_70161_v);
                } else {
                  entity.func_70107_b(field_70165_t, field_70163_u + 3.0D, field_70161_v);
                }
              }
            }
          }
        } else if ((!player.func_70093_af()) && (!BlockSide.BOTTOM.isEqual(field_72310_e)) && (!BlockSide.TOP.isEqual(field_72310_e)))
        {
          if ((isThrowableRock(world, field_72311_b, field_72312_c, field_72309_d, field_72310_e)) && (consumeCharges(world, player, 3, true)))
          {
            world.func_147468_f(field_72311_b, field_72312_c, field_72309_d);
            
            ParticleEffect.EXPLODE.send(com.emoniph.witchery.util.SoundEffect.RANDOM_EXPLODE, world, field_72311_b, field_72312_c, field_72309_d, 0.5D, 0.5D, 8);
            
            EntityWitchProjectile rockEntity = new EntityWitchProjectile(world, player, ItemsGENERIC.itemRock);
            rockEntity.func_70107_b(field_72311_b + 0.5D, field_72312_c + 0.5D, field_72309_d + 0.5D);
            world.func_72838_d(rockEntity);
          }
        } else if (player.func_70093_af())
        {
          if (consumeCharges(world, player, 2, true)) {
            Block blockID = world.func_147439_a(field_72311_b, field_72312_c, field_72309_d);
            Item ingot = EarthItems.instance().oreToIngot(blockID);
            if (ingot != null) {
              world.func_147465_d(field_72311_b, field_72312_c, field_72309_d, Blocks.field_150348_b, 0, 3);
              if (!field_72995_K)
              {
                world.func_72838_d(new EntityItem(world, field_72311_b, field_72312_c, field_72309_d, new ItemStack(ingot, 2, 0)));
              }
            }
          }
        }
        
        return;
      }
      
    }
    

    int seconds = elapsedTicks / 20;
    
    if ((seconds >= 2) && (!player.func_70093_af()) && (consumeCharges(world, player, 6 * seconds, true))) {
      com.emoniph.witchery.common.ServerTickEvents.TASKS.add(new ShockwaveTask(player, 2 * seconds));
    } else {
      playFailSound(world, player);
    }
  }
  
  private boolean isThrowableRock(World world, int blockX, int blockY, int blockZ, int sideHit) {
    Block[] blocks = { Blocks.field_150346_d, Blocks.field_150349_c, Blocks.field_150348_b, Blocks.field_150347_e, Blocks.field_150354_m, Blocks.field_150351_n, Blocks.field_150322_A, Blocks.field_150333_U, Blocks.field_150336_V, Blocks.field_150341_Y, Blocks.field_150349_c, Blocks.field_150446_ar, Blocks.field_150435_aG, Blocks.field_150425_aM, Blocks.field_150417_aV, Blocks.field_150389_bf, Blocks.field_150390_bg, Blocks.field_150391_bh, Blocks.field_150385_bj, Blocks.field_150387_bl, Blocks.field_150372_bz, Blocks.field_150405_ch, Blocks.field_150402_ci, Blocks.field_150424_aL };
    




    Block blockID = world.func_147439_a(blockX, blockY, blockZ);
    if (!Arrays.asList(blocks).contains(blockID)) {
      return false;
    }
    
    boolean northValid = (BlockSide.NORTH.isEqual(sideHit)) && (!world.func_147439_a(blockX + 1, blockY, blockZ).func_149688_o().func_76220_a());
    boolean southValid = (BlockSide.SOUTH.isEqual(sideHit)) && (!world.func_147439_a(blockX - 1, blockY, blockZ).func_149688_o().func_76220_a());
    boolean eastValid = (BlockSide.EAST.isEqual(sideHit)) && (!world.func_147439_a(blockX, blockY, blockZ + 1).func_149688_o().func_76220_a());
    boolean westValid = (BlockSide.WEST.isEqual(sideHit)) && (!world.func_147439_a(blockX, blockY, blockZ - 1).func_149688_o().func_76220_a());
    
    return (northValid) || (southValid) || (eastValid) || (westValid);
  }
  
  private static class ShockwaveTask
    extends ServerTickEvents.ServerTickTask
  {
    final Coord center;
    final EntityPlayer creator;
    final int maxRadius;
    final int MIN_RADIUS = 2;
    
    int stage = 0;
    
    public ShockwaveTask(EntityPlayer creator, int maxRadius) {
      super();
      center = new Coord((int)field_70165_t, (int)field_70163_u - 1, (int)field_70161_v);
      this.creator = creator;
      this.maxRadius = (maxRadius + 2);
    }
    
    public boolean process()
    {
      stage += 1;
      
      Block centerBlock = center.getBlock(world);
      
      if (stage == 1) {
        drawCircle(world, center.x, center.y, center.z, stage + 2, 2, 1);
      } else {
        drawCircle(world, center.x, center.y + 2, center.z, stage + 2, 2, -1);
        drawCircle(world, center.x, center.y + 1, center.z, stage + 2 - 1, 2, -1);
      }
      
      if (stage < maxRadius) {
        drawCircle(world, center.x, center.y, center.z, stage + 2 + 1, 2, 2);
      } else {
        drawCircle(world, center.x, center.y + 1, center.z, stage + 2, 2, -1);
      }
      
      int r = stage + 2;
      
      AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(center.x - r, center.y + 1, center.z - r, center.x + r, center.y + 3, center.z + r);
      for (Object obj : world.func_72872_a(EntityLivingBase.class, bounds)) {
        EntityLivingBase entity = (EntityLivingBase)obj;
        Coord position = new Coord(entity);
        double dist = center.distanceTo(position);
        if ((dist <= r + 1) && (dist >= r)) {
          entity.func_70097_a(DamageSource.func_76365_a(creator), 8.0F);
          RiteProtectionCircleRepulsive.push(world, entity, center.x, center.y, center.z);
        }
      }
      
      return stage == maxRadius;
    }
    
    protected void drawCircle(World world, int x0, int y0, int z0, int radius, int blocksToMove, int direction) {
      int x = radius;
      int z = 0;
      int radiusError = 1 - x;
      
      while (x >= z) {
        drawPixel(world, x + x0, y0, z + z0, blocksToMove, direction);
        drawPixel(world, z + x0, y0, x + z0, blocksToMove, direction);
        drawPixel(world, -x + x0, y0, z + z0, blocksToMove, direction);
        drawPixel(world, -z + x0, y0, x + z0, blocksToMove, direction);
        drawPixel(world, -x + x0, y0, -z + z0, blocksToMove, direction);
        drawPixel(world, -z + x0, y0, -x + z0, blocksToMove, direction);
        drawPixel(world, x + x0, y0, -z + z0, blocksToMove, direction);
        drawPixel(world, z + x0, y0, -x + z0, blocksToMove, direction);
        
        z++;
        if (radiusError < 0) {
          radiusError += 2 * z + 1;
        } else {
          x--;
          radiusError += 2 * (z - x + 1);
        }
      }
    }
    
    protected void drawPixel(World world, int x, int y, int z, int blocksToMove, int direction) {
      if (direction > 0) {
        if ((world.func_147437_c(x, y - blocksToMove + 1, z)) || (world.func_147439_a(x, y + 1, z).func_149688_o().func_76220_a())) {
          return;
        }
        for (int i = 0; i < blocksToMove; i++) {
          Block blockID = world.func_147439_a(x, y - i, z);
          
          int blockMetadata = world.func_72805_g(x, y - i, z);
          
          if (BlockProtect.canBreak(blockID, world)) {
            world.func_147468_f(x, y - i, z);
          }
          if (BlockProtect.canBreak(x, y - i + direction, z, world)) {
            world.func_147465_d(x, y - i + direction, z, blockID, blockMetadata, 3);
          }
        }
      } else {
        if ((world.func_147437_c(x, y, z)) || (world.func_147439_a(x, y + direction - 1, z).func_149688_o().func_76220_a())) {
          return;
        }
        for (int i = blocksToMove - 1; i >= 0; i--) {
          Block blockID = world.func_147439_a(x, y - i, z);
          
          int blockMetadata = world.func_72805_g(x, y - i, z);
          if (BlockProtect.canBreak(blockID, world)) {
            world.func_147468_f(x, y - i, z);
          }
          if (BlockProtect.canBreak(x, y - i + direction, z, world)) {
            world.func_147465_d(x, y - i + direction, z, blockID, blockMetadata, 3);
          }
        }
      }
    }
  }
}
