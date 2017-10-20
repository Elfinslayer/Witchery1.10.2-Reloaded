package com.emoniph.witchery.brewing.potions;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderLivingEvent.Post;

public abstract interface IHandleRenderLiving
{
  public abstract PotionBase getPotion();
  
  @SideOnly(Side.CLIENT)
  public abstract void onLivingRender(World paramWorld, EntityLivingBase paramEntityLivingBase, RenderLivingEvent.Post paramPost, int paramInt);
}
