package com.emoniph.witchery.infusion.infusions.spirit;

import java.util.ArrayList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.tileentity.TileEntity;

public class InfusedSpiritTwisterEffect extends InfusedSpiritEffect
{
  private static final double RANDOM_SPIN_RADIUS = 3.0D;
  private static final double RANDOM_SPIN_RADIUS_SQ = 9.0D;
  
  public InfusedSpiritTwisterEffect(int id, int spirits, int spectres, int banshees, int poltergeists)
  {
    super(id, "twister", spirits, spectres, banshees, poltergeists);
  }
  
  public int getCooldownTicks()
  {
    return 10;
  }
  

  public double getRadius()
  {
    return 8.0D;
  }
  



  public boolean doUpdateEffect(TileEntity tile, boolean triggered, ArrayList<EntityLivingBase> foundEntities)
  {
    if (triggered) {
      for (EntityLivingBase entity : foundEntities) {
        if ((entity instanceof EntityPlayer)) {
          EntityPlayer player = (EntityPlayer)entity;
          if ((field_71071_by.func_70440_f(0) != null) || (field_71071_by.func_70440_f(1) != null) || (field_71071_by.func_70440_f(2) != null) || (field_71071_by.func_70440_f(3) != null) || (player.func_70694_bm() != null))
          {


            double yawRadians = Math.atan2(field_70161_v - (0.5D + field_145849_e), field_70165_t - (0.5D + field_145851_c));
            double yaw = Math.toDegrees(yawRadians) + 180.0D;
            double playerYaw = (field_70177_z + 90.0F) % 360.0F;
            if (playerYaw < 0.0D) {
              playerYaw += 360.0D;
            }
            float rev = ((float)yaw + 90.0F) % 360.0F;
            double ARC = 45.0D;
            double diff = Math.abs(yaw - playerYaw);
            if ((360.0D - diff % 360.0D < 45.0D) || (diff % 360.0D < 45.0D))
            {







              if ((player instanceof net.minecraft.entity.player.EntityPlayerMP)) {
                S08PacketPlayerPosLook packet = new S08PacketPlayerPosLook(field_70165_t, field_70163_u, field_70161_v, rev, field_70125_A, false);
                com.emoniph.witchery.Witchery.packetPipeline.sendTo(packet, player);
              }
              
            }
            
          }
          
        }
        else if ((entity instanceof EntityLiving)) {
          EntityLiving creature = (EntityLiving)entity;
          if (creature.func_110138_aP() < 50.0F) {
            com.emoniph.witchery.util.EntityUtil.dropAttackTarget(creature);
            if (foundEntities.size() > 1) {
              com.emoniph.witchery.util.EntityUtil.setTarget(creature, (EntityLivingBase)foundEntities.get(func_145831_wfield_73012_v.nextInt(foundEntities.size())));
            }
          }
        }
      }
    }
    return triggered;
  }
}
