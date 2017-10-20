package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.common.ExtendedPlayer;

public class RenderOtherPlayer extends net.minecraft.client.renderer.entity.RenderPlayer
{
  public RenderOtherPlayer() {}
  
  protected net.minecraft.util.ResourceLocation func_110775_a(net.minecraft.client.entity.AbstractClientPlayer entity)
  {
    net.minecraft.entity.player.EntityPlayer player = entity;
    ExtendedPlayer playerEx = ExtendedPlayer.get(player);
    return playerEx.getOtherPlayerSkinLocation();
  }
}
