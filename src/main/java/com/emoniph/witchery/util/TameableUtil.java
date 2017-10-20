package com.emoniph.witchery.util;

import java.util.UUID;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class TameableUtil
{
  public TameableUtil() {}
  
  public static void setOwner(EntityTameable tameable, EntityPlayer owner)
  {
    if ((tameable != null) && (owner != null))
    {


      tameable.func_152115_b(owner.func_110124_au().toString());
    }
  }
  



  public static void setOwnerByUsername(EntityTameable tameable, String ownerUsername)
  {
    EntityPlayer player = MinecraftServer.func_71276_C().func_71203_ab().func_152612_a(ownerUsername);
    setOwner(tameable, player);
  }
  
  public static boolean isOwner(EntityTameable tameable, EntityPlayer player)
  {
    return tameable.func_152114_e(player);
  }
  
  public static boolean hasOwner(EntityTameable tameable) {
    String id = tameable.func_152113_b();
    return (id != null) && (!id.isEmpty());
  }
  
  public static net.minecraft.entity.EntityLivingBase getOwnerAccrossDimensions(EntityTameable tameable) {
    String id = tameable.func_152113_b();
    UUID uuid = UUID.fromString(id);
    return getPlayerByID(uuid);
  }
  
  public static EntityPlayerMP getPlayerByID(UUID uuid) {
    java.util.Iterator iterator = func_71276_Cfunc_71203_abfield_72404_b.iterator();
    EntityPlayerMP entityplayermp;
    do
    {
      if (!iterator.hasNext()) {
        return null;
      }
      
      entityplayermp = (EntityPlayerMP)iterator.next();
    } while (!entityplayermp.func_146103_bH().getId().equals(uuid));
    
    return entityplayermp;
  }
  
  public static void cloneOwner(EntityTameable tameable, EntityTameable tameableToCopyFrom) {
    tameable.func_152115_b(tameableToCopyFrom.func_152113_b());
  }
}
