package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.brewing.potions.WitcheryPotions;
import com.emoniph.witchery.util.CreatureUtil;
import com.emoniph.witchery.util.SoundEffect;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityVillagerWere extends EntityVillager
{
  private boolean infectious;
  
  public EntityVillagerWere(World world)
  {
    this(world, 0, false);
  }
  
  public EntityVillagerWere(World world, int profession, boolean infectious) {
    super(world, profession);
    this.infectious = infectious;
  }
  
  public void func_70014_b(NBTTagCompound nbtRoot)
  {
    super.func_70014_b(nbtRoot);
    nbtRoot.func_74757_a("Infectious", infectious);
  }
  
  public void func_70037_a(NBTTagCompound nbtRoot)
  {
    super.func_70037_a(nbtRoot);
    infectious = nbtRoot.func_74767_n("Infectious");
  }
  
  protected void func_70619_bc()
  {
    super.func_70619_bc();
    if ((!field_70170_p.field_72995_K) && 
      (field_70173_aa % 100 == 3) && (!func_70631_g_()) && (CreatureUtil.isFullMoon(field_70170_p)) && (!func_70644_a(PotionsWOLFSBANE)))
    {
      convertToWolfman();
    }
  }
  
  protected void convertToWolfman()
  {
    EntityWolfman entity = new EntityWolfman(field_70170_p);
    if (infectious) {
      entity.setInfectious();
    }
    
    entity.setFormerProfession(func_70946_n(), field_70956_bz, field_70963_i);
    
    entity.func_110163_bv();
    entity.func_82149_j(this);
    entity.func_110161_a(null);
    field_70170_p.func_72900_e(this);
    field_70170_p.func_72838_d(entity);
    field_70170_p.func_72889_a(null, 1017, (int)field_70165_t, (int)field_70163_u, (int)field_70161_v, 0);
    SoundEffect.WITCHERY_MOB_WOLFMAN_HOWL.playAt(field_70170_p, field_70165_t, field_70163_u, field_70161_v);
  }
}
