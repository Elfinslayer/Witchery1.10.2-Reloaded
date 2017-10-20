package com.emoniph.witchery.brewing;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class TileEntityBrewFluid extends net.minecraft.tileentity.TileEntity
{
  NBTTagCompound nbtEffect;
  int color;
  int duration;
  int expansion;
  int updateCount;
  String thrower;
  
  public TileEntityBrewFluid() {}
  
  public void initalise(ModifiersImpact impactModifiers, NBTTagCompound nbtBrew)
  {
    if (nbtBrew != null) {
      nbtEffect = ((NBTTagCompound)nbtBrew.func_74737_b());
    }
    color = WitcheryBrewRegistry.INSTANCE.getBrewColor(nbtEffect);
    duration = (lifetime >= 0 ? 5 + lifetime * lifetime * 5 : 100);
    expansion = Math.min(4 + extent, 10);
    if (thrower != null) {
      thrower = thrower.func_70005_c_();
    }
    field_145850_b.func_147471_g(field_145851_c, field_145848_d, field_145849_e);
  }
  
  public net.minecraft.network.Packet func_145844_m()
  {
    NBTTagCompound nbtTag = new NBTTagCompound();
    func_145841_b(nbtTag);
    return new S35PacketUpdateTileEntity(field_145851_c, field_145848_d, field_145849_e, 1, nbtTag);
  }
  
  public void onDataPacket(net.minecraft.network.NetworkManager net, S35PacketUpdateTileEntity packet)
  {
    super.onDataPacket(net, packet);
    func_145839_a(packet.func_148857_g());
    field_145850_b.func_147479_m(field_145851_c, field_145848_d, field_145849_e);
  }
  
  public void func_145841_b(NBTTagCompound nbtRoot)
  {
    super.func_145841_b(nbtRoot);
    if (nbtEffect != null) {
      nbtRoot.func_74782_a("Effect", nbtEffect);
    }
    nbtRoot.func_74768_a("Color", color);
    nbtRoot.func_74768_a("Duration", duration);
    nbtRoot.func_74768_a("Expansion", expansion);
    if (thrower != null) {
      nbtRoot.func_74778_a("Thrower", thrower);
    }
  }
  
  public void func_145839_a(NBTTagCompound nbtRoot)
  {
    super.func_145839_a(nbtRoot);
    if (nbtRoot.func_74764_b("Effect")) {
      nbtEffect = nbtRoot.func_74775_l("Effect");
    }
    color = nbtRoot.func_74762_e("Color");
    duration = nbtRoot.func_74762_e("Duration");
    expansion = nbtRoot.func_74762_e("Expansion");
    thrower = nbtRoot.func_74779_i("Thrower");
  }
  
  public boolean canUpdate()
  {
    return false;
  }
  
  private int runTicks = 0;
  
  public int incRunTicks() {
    return ++runTicks;
  }
}
