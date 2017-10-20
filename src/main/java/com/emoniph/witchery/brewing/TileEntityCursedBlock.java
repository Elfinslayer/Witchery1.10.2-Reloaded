package com.emoniph.witchery.brewing;

import com.emoniph.witchery.blocks.TileEntityBase;
import com.emoniph.witchery.util.EntityPosition;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.world.World;

public class TileEntityCursedBlock extends TileEntityBase
{
  NBTTagCompound nbtEffect;
  int color;
  int duration;
  int expansion;
  int count;
  String thrower;
  
  public TileEntityCursedBlock() {}
  
  public boolean canUpdate()
  {
    return false;
  }
  
  public void initalise(ModifiersImpact impactModifiers, NBTTagCompound nbtBrew) {
    if (nbtBrew != null) {
      nbtEffect = ((NBTTagCompound)nbtBrew.func_74737_b());
    }
    color = WitcheryBrewRegistry.INSTANCE.getBrewColor(nbtEffect);
    duration = (lifetime >= 0 ? 5 + lifetime * lifetime * 5 : 100);
    
    expansion = Math.min(4 + extent, 10);
    if (thrower != null) {
      thrower = thrower.func_70005_c_();
    }
    count = 1;
    field_145850_b.func_147471_g(field_145851_c, field_145848_d, field_145849_e);
  }
  
  public void updateCurse(ModifiersImpact impactModifiers, NBTTagCompound nbtBrew) {
    if (nbtBrew != null) {
      if ((nbtEffect != null) && (nbtEffect.func_150295_c("Items", 10).equals(nbtBrew.func_150295_c("Items", 10))))
      {

        count += 1;
      } else {
        nbtEffect = nbtBrew;
        count = 1;
        color = WitcheryBrewRegistry.INSTANCE.getBrewColor(nbtEffect);
        duration = (lifetime >= 0 ? 5 + lifetime * lifetime * 5 : 100);
        
        expansion = Math.min(4 + extent, 10);
        if (thrower != null) {
          thrower = thrower.func_70005_c_();
        }
      }
    }
  }
  
  public Packet func_145844_m()
  {
    NBTTagCompound nbtTag = new NBTTagCompound();
    func_145841_b(nbtTag);
    return new S35PacketUpdateTileEntity(field_145851_c, field_145848_d, field_145849_e, 1, nbtTag);
  }
  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
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
    nbtRoot.func_74768_a("Count", count);
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
    count = nbtRoot.func_74762_e("Count");
  }
  
  public boolean applyToEntityAndDestroy(Entity entity) {
    if ((nbtEffect != null) && (entity != null) && ((entity instanceof EntityLivingBase))) {
      EntityLivingBase living = (EntityLivingBase)entity;
      WitcheryBrewRegistry.INSTANCE.applyToEntity(field_70170_p, living, nbtEffect, new ModifiersEffect(1.0D, 1.0D, false, new EntityPosition(living), false, 0, com.emoniph.witchery.util.EntityUtil.playerOrFake(field_70170_p, thrower)));
      




      ParticleEffect.SPELL_COLORED.send(SoundEffect.RANDOM_POP, living, 1.0D, 1.0D, 16);
    }
    return --count > 0;
  }
}
