package com.emoniph.witchery.entity;

import com.emoniph.witchery.blocks.BlockBrazier.TileEntityBrazier;
import com.emoniph.witchery.util.TimeUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityPoltergeist extends EntitySummonedUndead
{
  private int attackTimer;
  
  public EntityPoltergeist(World par1World)
  {
    super(par1World);
    func_70661_as().func_75491_a(true);
    func_70661_as().func_75498_b(true);
    field_70714_bg.func_75776_a(1, new net.minecraft.entity.ai.EntityAISwimming(this));
    field_70714_bg.func_75776_a(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
    field_70714_bg.func_75776_a(3, new EntityAIOpenDoor(this, true));
    field_70714_bg.func_75776_a(4, new EntityAIWander(this, 1.0D));
    field_70714_bg.func_75776_a(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
    field_70714_bg.func_75776_a(6, new EntityAILookIdle(this));
    field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true));
  }
  


  protected void func_110147_ax()
  {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(20.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3D);
    func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(3.0D);
  }
  
  protected void func_70088_a()
  {
    super.func_70088_a();
  }
  
  protected boolean func_70650_aV()
  {
    return true;
  }
  
  @SideOnly(Side.CLIENT)
  public int getAttackTimer() {
    return attackTimer;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_70103_a(byte par1)
  {
    if (par1 == 4) {
      attackTimer = 15;
    } else {
      super.func_70103_a(par1);
    }
  }
  
  public void func_70636_d()
  {
    super.func_70636_d();
    
    if (attackTimer > 0) {
      attackTimer -= 1;
    }
    
    if (TimeUtil.secondsElapsed(5, field_70173_aa)) {
      double RADIUS = 16.0D;
      double RADIUS_SQ = 256.0D;
      double THROW_RANGE = 3.0D;
      double THROW_RANGE_SQ = 9.0D;
      double EVIL_RANGE = 8.0D;
      double EVIL_RANGE_SQ = 64.0D;
      double MAX_SPEED = 0.6D;
      

      AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(field_70165_t - 16.0D, field_70163_u - 16.0D, field_70161_v - 16.0D, field_70165_t + 16.0D, field_70163_u + 16.0D, field_70161_v + 16.0D);
      
      List hangingItems = field_70170_p.func_72872_a(EntityHanging.class, bounds);
      for (Object obj : hangingItems) {
        EntityHanging hanging = (EntityHanging)obj;
        if (func_70068_e(hanging) <= 256.0D) {
          if (func_70068_e(hanging) <= 9.0D) {
            if (!field_70170_p.field_72995_K) {
              hanging.func_70097_a(DamageSource.func_76358_a(this), 3.0F);
            }
            attackTimer = 15;
            field_70170_p.func_72960_a(this, (byte)4);
          } else {
            func_70661_as().func_75492_a(field_70165_t, field_70163_u, field_70161_v, 1.0D);
          }
          return;
        }
      }
      


      EntityPlayer summoner = getSummoner();
      if ((summoner != null) && (func_70068_e(summoner) <= 64.0D)) {
        TileEntity closest = null;
        double closestDist = -1.0D;
        for (Object obj : field_70170_p.field_147482_g) {
          if (((obj instanceof IInventory)) && (!(obj instanceof com.emoniph.witchery.blocks.BlockKettle.TileEntityKettle)) && (!(obj instanceof BlockBrazier.TileEntityBrazier))) {
            TileEntity tile = (TileEntity)obj;
            double distSq = func_70092_e(0.5D + field_145851_c, 0.5D + field_145848_d, 0.5D + field_145849_e);
            if (distSq <= 256.0D) {
              IInventory inventory = (IInventory)tile;
              ArrayList<Integer> indices = new ArrayList();
              for (int i = 0; i < inventory.func_70302_i_(); i++) {
                if (inventory.func_70301_a(i) != null) {
                  indices.add(Integer.valueOf(i));
                }
              }
              if ((indices.size() > 0) && ((closest == null) || (distSq < closestDist))) {
                closest = tile;
                closestDist = distSq;
              }
            }
          }
        }
        
        if (closest != null) {
          IInventory inventory = (IInventory)closest;
          ArrayList<Integer> indices = new ArrayList();
          for (int i = 0; i < inventory.func_70302_i_(); i++) {
            if (inventory.func_70301_a(i) != null) {
              indices.add(Integer.valueOf(i));
            }
          }
          
          if (indices.size() > 0) {
            if (func_70092_e(0.5D + field_145851_c, 0.5D + field_145848_d, 0.5D + field_145849_e) <= 9.0D) {
              if (!field_70170_p.field_72995_K) {
                int slot = ((Integer)indices.get(field_70170_p.field_73012_v.nextInt(indices.size()))).intValue();
                ItemStack stack = inventory.func_70301_a(slot);
                if (field_77994_a > 1) {
                  field_77994_a -= 1;
                  stack = stack.func_77946_l();
                  field_77994_a = 1;
                } else {
                  inventory.func_70299_a(slot, null);
                }
                EntityItem itemEntity = new EntityItem(field_70170_p, 0.5D + field_145851_c, 0.5D + field_145848_d, 0.5D + field_145849_e, stack);
                
                field_70170_p.func_72838_d(itemEntity);
                lifespan = TimeUtil.minsToTicks(15);
                field_70159_w = (-0.3D + field_70170_p.field_73012_v.nextDouble() * 0.6D);
                field_70181_x = (0.1D + field_70170_p.field_73012_v.nextDouble() * 0.2D);
                field_70179_y = (-0.3D + field_70170_p.field_73012_v.nextDouble() * 0.6D);
              }
              attackTimer = 15;
              field_70170_p.func_72960_a(this, (byte)4);
            } else {
              func_70661_as().func_75492_a(field_145851_c, field_145848_d, field_145849_e, 1.0D);
            }
            return;
          }
        }
      }
      

      List droppedItems = field_70170_p.func_72872_a(EntityItem.class, bounds);
      for (Object obj : droppedItems) {
        EntityItem dropped = (EntityItem)obj;
        if (func_70068_e(dropped) <= 256.0D) {
          if (func_70068_e(dropped) <= 9.0D) {
            if (!field_70170_p.field_72995_K) {
              field_70159_w = (-0.3D + field_70170_p.field_73012_v.nextDouble() * 0.6D);
              field_70181_x = (0.1D + field_70170_p.field_73012_v.nextDouble() * 0.2D);
              field_70179_y = (-0.3D + field_70170_p.field_73012_v.nextDouble() * 0.6D);
            }
            attackTimer = 15;
            field_70170_p.func_72960_a(this, (byte)4);
          } else {
            func_70661_as().func_75492_a(field_70165_t, field_70163_u, field_70161_v, 1.0D);
          }
          return;
        }
      }
    }
  }
  


  public void func_70071_h_()
  {
    super.func_70071_h_();
  }
  
  public boolean func_70652_k(Entity par1Entity)
  {
    boolean flag = super.func_70652_k(par1Entity);
    
    return flag;
  }
  
  protected String func_70639_aQ()
  {
    return null;
  }
  
  protected String func_70621_aR()
  {
    return "witchery:mob.spectre.spectre_die";
  }
  
  protected String func_70673_aS()
  {
    return "witchery:mob.spectre.spectre_die";
  }
  
  public String func_70005_c_()
  {
    if (func_94056_bM()) {
      return func_94057_bL();
    }
    return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.poltergeist.name");
  }
  

  public void func_70014_b(NBTTagCompound par1NBTTagCompound)
  {
    super.func_70014_b(par1NBTTagCompound);
  }
  

  public void func_70037_a(NBTTagCompound par1NBTTagCompound)
  {
    super.func_70037_a(par1NBTTagCompound);
  }
  
  public IEntityLivingData func_110161_a(IEntityLivingData par1EntityLivingData)
  {
    Object par1EntityLivingData1 = super.func_110161_a(par1EntityLivingData);
    
    func_70690_d(new PotionEffect(field_76441_pfield_76415_H, Integer.MAX_VALUE));
    
    return (IEntityLivingData)par1EntityLivingData1;
  }
}
