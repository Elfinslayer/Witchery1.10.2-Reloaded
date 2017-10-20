package com.emoniph.witchery.util;

import com.emoniph.witchery.common.ExtendedPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class EntitySizeInfo
{
  public final float defaultWidth;
  public final float defaultHeight;
  public final float eyeHeight;
  public final float stepSize;
  public final boolean isDefault;
  public final TransformCreature creature;
  
  public EntitySizeInfo(EntityLivingBase entity)
  {
    if ((entity instanceof EntityPlayer)) {
      EntityPlayer player = (EntityPlayer)entity;
      creature = ExtendedPlayer.get(player).getCreatureType();
      NBTTagCompound nbtEntity = entity.getEntityData();
      switch (1.$SwitchMap$com$emoniph$witchery$util$TransformCreature[creature.ordinal()]) {
      case 1: 
      case 2: 
      default: 
        isDefault = true;
        defaultWidth = 0.6F;
        defaultHeight = 1.8F;
        stepSize = 0.5F;
        eyeHeight = player.getDefaultEyeHeight();
        break;
      case 3: 
        isDefault = false;
        defaultWidth = 0.6F;
        defaultHeight = 0.8F;
        eyeHeight = (defaultHeight * 0.92F);
        stepSize = 1.0F;
        break;
      case 4: 
        isDefault = true;
        defaultWidth = 0.6F;
        defaultHeight = 1.8F;
        eyeHeight = player.getDefaultEyeHeight();
        stepSize = 1.0F;
        break;
      case 5: 
        isDefault = false;
        defaultWidth = 0.3F;
        defaultHeight = 0.6F;
        eyeHeight = (defaultHeight * 0.8F);
        stepSize = 0.5F;
        break;
      case 6: 
        isDefault = false;
        defaultWidth = 0.3F;
        defaultHeight = 0.5F;
        eyeHeight = (defaultHeight * 0.92F);
        stepSize = 0.5F;
      }
    }
    else
    {
      NBTTagCompound nbtEntity = entity.getEntityData();
      defaultWidth = nbtEntity.func_74760_g("WITCInitialWidth");
      defaultHeight = nbtEntity.func_74760_g("WITCInitialHeight");
      stepSize = (((entity instanceof EntityHorse)) || ((entity instanceof EntityEnderman)) ? 1.0F : 0.5F);
      eyeHeight = 0.12F;
      isDefault = true;
      creature = TransformCreature.NONE;
    }
  }
}
