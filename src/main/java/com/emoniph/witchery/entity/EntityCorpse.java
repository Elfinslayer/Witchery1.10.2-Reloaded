package com.emoniph.witchery.entity;

import com.emoniph.witchery.dimension.WorldProviderDreamWorld;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityCorpse extends EntityLiving
{
  private net.minecraft.client.renderer.ThreadDownloadImageData downloadImageSkin;
  private net.minecraft.util.ResourceLocation locationSkin;
  
  public EntityCorpse(World world)
  {
    super(world);
    func_70105_a(1.2F, 0.5F);
  }
  

  public boolean func_70104_M()
  {
    return false;
  }
  
  public boolean func_70067_L()
  {
    return super.func_70067_L();
  }
  
  protected void func_110147_ax()
  {
    super.func_110147_ax();
    func_110148_a(net.minecraft.entity.SharedMonsterAttributes.field_111267_a).func_111128_a(20.0D);
    func_110148_a(net.minecraft.entity.SharedMonsterAttributes.field_111266_c).func_111128_a(1.0D);
  }
  


  public void func_70091_d(double par1, double par3, double par5) {}
  


  protected void func_70088_a()
  {
    super.func_70088_a();
    field_70180_af.func_75682_a(17, "");
  }
  
  protected boolean func_70085_c(EntityPlayer par1EntityPlayer)
  {
    return true;
  }
  
  public boolean func_70097_a(DamageSource par1DamageSource, float par2)
  {
    if (!field_70170_p.field_72995_K) {
      if ((par1DamageSource.func_76364_f() != null) && ((par1DamageSource.func_76364_f() instanceof EntityPlayer)) && (func_76364_ffield_71075_bZ.field_75098_d)) {
        return super.func_70097_a(par1DamageSource, par2);
      }
      
      String username = getOwnerName();
      for (WorldServer world : func_71276_Cfield_71305_c) {
        EntityPlayer player = world.func_72924_a(username);
        if (player != null) {
          return super.func_70097_a(par1DamageSource, par2);
        }
      }
      return false;
    }
    return super.func_70097_a(par1DamageSource, par2);
  }
  

  public String func_70005_c_()
  {
    if (func_94056_bM()) {
      return func_94057_bL();
    }
    return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.body.name");
  }
  

  public boolean func_70650_aV()
  {
    return true;
  }
  
  public void func_70014_b(NBTTagCompound nbtRoot)
  {
    super.func_70014_b(nbtRoot);
    
    if (getOwnerName() == null) {
      nbtRoot.func_74778_a("Owner", "");
    } else {
      nbtRoot.func_74778_a("Owner", getOwnerName());
    }
  }
  
  public void func_70037_a(NBTTagCompound nbtRoot)
  {
    super.func_70037_a(nbtRoot);
    
    String s = nbtRoot.func_74779_i("Owner");
    
    if (s.length() > 0) {
      setOwner(s);
    }
  }
  
  public String getOwnerName() {
    return field_70180_af.func_75681_e(17);
  }
  
  public void setOwner(String username) {
    func_110163_bv();
    field_70180_af.func_75692_b(17, username);
  }
  


  protected void setupCustomSkin()
  {
    String username = getOwnerName();
    locationSkin = AbstractClientPlayer.func_110311_f(username);
    downloadImageSkin = AbstractClientPlayer.func_110304_a(locationSkin, username);
  }
  
  public EntityPlayer getOwnerEntity() {
    return field_70170_p.func_72924_a(getOwnerName());
  }
  
  public void func_70645_a(DamageSource par1DamageSource)
  {
    super.func_70645_a(par1DamageSource);
    if (!field_70170_p.field_72995_K) {
      String username = getOwnerName();
      for (WorldServer world : func_71276_Cfield_71305_c) {
        EntityPlayer player = world.func_72924_a(username);
        if (player != null) {
          if (field_71093_bK == instancedimensionDreamID) {
            WorldProviderDreamWorld.returnPlayerToOverworld(player); break; }
          if (!WorldProviderDreamWorld.getPlayerIsGhost(player)) break;
          WorldProviderDreamWorld.returnGhostPlayerToSpiritWorld(player);
          WorldProviderDreamWorld.returnPlayerToOverworld(player); break;
        }
      }
    }
  }
  


  public void func_70636_d()
  {
    super.func_70636_d();
  }
  
  public net.minecraft.util.ResourceLocation getLocationSkin() {
    if (locationSkin == null) {
      setupCustomSkin();
    }
    if (locationSkin != null) {
      return locationSkin;
    }
    return AbstractClientPlayer.field_110314_b;
  }
}
