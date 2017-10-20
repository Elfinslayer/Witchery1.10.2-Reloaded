package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityMandrake extends EntityMob
{
  public EntityMandrake(World world)
  {
    super(world);
    func_70661_as().func_75491_a(true);
    func_70661_as().func_75495_e(true);
    field_70714_bg.func_75776_a(1, new net.minecraft.entity.ai.EntityAISwimming(this));
    field_70714_bg.func_75776_a(2, new EntityAIAttackOnCollide(this, 1.0D, false));
    field_70714_bg.func_75776_a(3, new EntityAIWander(this, 0.8D));
    field_70714_bg.func_75776_a(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
    field_70714_bg.func_75776_a(5, new EntityAILookIdle(this));
    field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    field_70715_bh.func_75776_a(2, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false));
    field_70728_aV = 0;
    func_70105_a(0.6F, 0.9F);
  }
  
  public String func_70005_c_()
  {
    if (func_94056_bM()) {
      return func_94057_bL();
    }
    return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.mandrake.name");
  }
  

  protected void func_110147_ax()
  {
    super.func_110147_ax();
    func_110148_a(net.minecraft.entity.SharedMonsterAttributes.field_111263_d).func_111128_a(0.65D);
  }
  
  public boolean func_70650_aV()
  {
    return true;
  }
  
  public int func_82143_as()
  {
    return func_70638_az() == null ? 3 : 3 + (int)(func_110143_aJ() - 1.0F);
  }
  
  protected void func_70088_a()
  {
    super.func_70088_a();
  }
  
  public void func_70014_b(NBTTagCompound par1NBTTagCompound)
  {
    super.func_70014_b(par1NBTTagCompound);
  }
  
  public void func_70037_a(NBTTagCompound par1NBTTagCompound)
  {
    super.func_70037_a(par1NBTTagCompound);
  }
  
  protected String func_70639_aQ()
  {
    return "mob.ghast.scream";
  }
  
  protected String func_70621_aR()
  {
    return "mob.ghast.scream";
  }
  
  protected String func_70673_aS()
  {
    return "mob.ghast.death";
  }
  
  public boolean func_70652_k(Entity entity)
  {
    if ((!field_70170_p.field_72995_K) && 
      ((entity instanceof EntityPlayer))) {
      EntityPlayer player = (EntityPlayer)entity;
      if ((!player.func_70644_a(Potion.field_76431_k)) && (!com.emoniph.witchery.item.ItemEarmuffs.isHelmWorn(player))) {
        player.func_70690_d(new PotionEffect(field_76431_kfield_76415_H, 300, 1));
      }
    }
    return true;
  }
  
  protected void func_70628_a(boolean par1, int par2)
  {
    func_70099_a(ItemsGENERIC.itemMandrakeRoot.createStack(), 0.0F);
    func_70099_a(new ItemStack(ItemsSEEDS_MANDRAKE, field_70170_p.field_73012_v.nextDouble() <= 0.25D ? 2 : 1), 0.0F);
  }
}
