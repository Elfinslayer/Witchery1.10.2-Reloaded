package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.blocks.BlockCircle;
import com.emoniph.witchery.blocks.BlockCircleGlyph;
import com.emoniph.witchery.brewing.potions.PotionEnslaved;
import com.emoniph.witchery.familiar.Familiar;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemGeneral.Brew;
import com.emoniph.witchery.item.ItemGeneral.Brew.BrewResult;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import com.emoniph.witchery.network.PacketPipeline;
import com.emoniph.witchery.network.PacketPushTarget;
import com.emoniph.witchery.util.BlockProtect;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.EarthItems;
import com.emoniph.witchery.util.EntityUtil;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.FoodStats;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityWitchProjectile extends EntityThrowable
{
  private int damageValue;
  
  public EntityWitchProjectile(World world)
  {
    super(world);
  }
  
  public EntityWitchProjectile(World world, EntityLivingBase entityLiving, ItemGeneral.SubItem generalSubItem) {
    super(world, entityLiving);
    setDamageValue(damageValue);
  }
  
  public EntityWitchProjectile(World world, double posX, double posY, double posZ, ItemGeneral.SubItem generalSubItem) {
    super(world, posX, posY, posZ);
    setDamageValue(damageValue);
  }
  
  protected void func_70088_a()
  {
    field_70180_af.func_75682_a(6, Integer.valueOf(0));
    super.func_70088_a();
  }
  
  public void setDamageValue(int damageValue) {
    this.damageValue = damageValue;
    func_70096_w().func_75692_b(6, Integer.valueOf(damageValue));
  }
  
  public int getDamageValue() {
    return func_70096_w().func_75679_c(6);
  }
  
  public boolean isPotion() {
    return ((ItemsGENERIC.subItems.get(damageValue) instanceof ItemGeneral.Brew)) || (ItemsGENERIC.itemQuicklime.damageValue == damageValue);
  }
  
  protected float func_70185_h()
  {
    if (isPotion()) {
      return 0.05F;
    }
    return super.func_70185_h();
  }
  

  protected float func_70182_d()
  {
    if (isPotion()) {
      return 0.5F;
    }
    return super.func_70182_d();
  }
  

  protected float func_70183_g()
  {
    if (isPotion()) {
      return -20.0F;
    }
    return super.func_70183_g();
  }
  

  private boolean skipFX = false;
  private static final String DAMAGE_VALUE_KEY = "damageValue";
  
  protected void func_70184_a(MovingObjectPosition mop) {
    if (!field_70170_p.field_72995_K)
    {
      if (mop != null) {
        boolean enhanced = false;
        EntityLivingBase thrower = func_85052_h();
        
        if ((thrower != null) && ((thrower instanceof EntityPlayer))) {
          enhanced = Familiar.hasActiveBrewMasteryFamiliar((EntityPlayer)thrower);
        }
        
        skipFX = false;
        if (ItemsGENERIC.itemBrewOfVines.damageValue == damageValue) {
          impactVines(mop, enhanced);
        } else if (ItemsGENERIC.itemBrewOfThorns.damageValue == damageValue) {
          impactThorns(mop, enhanced);
        } else if (ItemsGENERIC.itemBrewOfWebs.damageValue == damageValue) {
          impactWebBig(mop, enhanced);
        } else if (ItemsGENERIC.itemBrewOfInk.damageValue == damageValue) {
          impactInk(mop, enhanced);
        } else if (ItemsGENERIC.itemBrewOfWasting.damageValue == damageValue) {
          impactWasting(mop, enhanced);
        } else if (ItemsGENERIC.itemBrewOfSprouting.damageValue == damageValue) {
          impactSprout(mop, enhanced);
        } else if (ItemsGENERIC.itemBrewOfErosion.damageValue == damageValue) {
          impactErosion(mop, enhanced);
        } else if (ItemsGENERIC.itemBrewOfLove.damageValue == damageValue) {
          impactLove(mop, enhanced);
        } else if (ItemsGENERIC.itemWeb.damageValue == damageValue) {
          impactWebSmall(mop);
          skipFX = true;
        } else if (ItemsGENERIC.itemRock.damageValue == damageValue) {
          impactRock(mop);
          skipFX = true;
        } else if (ItemsGENERIC.itemBrewOfRaising.damageValue == damageValue) {
          impactRaising(mop);
        } else if (ItemsGENERIC.itemQuicklime.damageValue == damageValue) {
          impactQuicklime(mop);
        } else if (ItemsGENERIC.itemBrewOfIce.damageValue == damageValue) {
          impactIce(mop);
        } else if (ItemsGENERIC.itemBrewOfFrogsTongue.damageValue == damageValue) {
          impactFrogsTongue(mop, false);
        } else if (ItemsGENERIC.itemBrewOfCursedLeaping.damageValue == damageValue) {
          impactLeaping(mop, false);
        } else if (ItemsGENERIC.itemBrewOfHitchcock.damageValue == damageValue) {
          impactHitchcock(mop);
        } else if (ItemsGENERIC.itemBrewOfInfection.damageValue == damageValue) {
          impactInfection(mop, enhanced);
        } else if (ItemsGENERIC.itemBrewOfBats.damageValue == damageValue) {
          impactBats(mop, enhanced);
        } else {
          ItemGeneral.SubItem item = (ItemGeneral.SubItem)ItemsGENERIC.subItems.get(damageValue);
          if ((item instanceof ItemGeneral.Brew)) {
            ItemGeneral.Brew brew = (ItemGeneral.Brew)item;
            ItemGeneral.Brew.BrewResult result = brew.onImpact(field_70170_p, thrower, mop, enhanced, field_70165_t, field_70163_u, field_70161_v, field_70121_D);
            
            if (result == ItemGeneral.Brew.BrewResult.DROP_ITEM) {
              EntityItem itemEntity = null;
              if (mop != null) {
                ItemStack newBrewStack = brew.createStack();
                switch (1.$SwitchMap$net$minecraft$util$MovingObjectPosition$MovingObjectType[field_72313_a.ordinal()]) {
                case 1: 
                  itemEntity = new EntityItem(field_70170_p, field_72311_b + 0.5D, field_72312_c + (field_72310_e == 0 ? -1 : 1) + 0.5D, field_72309_d + 0.5D, newBrewStack);
                  break;
                case 2: 
                  itemEntity = new EntityItem(field_70170_p, field_72308_g.field_70165_t, field_72308_g.field_70163_u, field_72308_g.field_70161_v, newBrewStack);
                  break;
                }
                
              }
              
              skipFX = true;
              if (itemEntity != null) {
                field_70170_p.func_72838_d(itemEntity);
              }
            } else {
              skipFX = (result == ItemGeneral.Brew.BrewResult.HIDE_EFFECT);
            }
          }
        }
        
        if (!skipFX) {
          field_70170_p.func_72926_e(2002, (int)Math.round(field_70165_t), (int)Math.round(field_70163_u), (int)Math.round(field_70161_v), 2);
        }
      }
    }
    func_70106_y();
  }
  
  private void impactBats(MovingObjectPosition mop, boolean enhanced) {
    switch (1.$SwitchMap$net$minecraft$util$MovingObjectPosition$MovingObjectType[field_72313_a.ordinal()]) {
    case 1: 
      explodeBats(field_70170_p, field_72311_b, field_72312_c, field_72309_d, field_72310_e, enhanced);
      break;
    case 2: 
      int x = MathHelper.func_76128_c(field_72308_g.field_70165_t);
      int y = MathHelper.func_76128_c(field_72308_g.field_70163_u);
      int z = MathHelper.func_76128_c(field_72308_g.field_70161_v);
      explodeBats(field_70170_p, x, y, z, -1, enhanced);
      break;
    }
    
    

    double RADIUS = enhanced ? 4.0D : 3.0D;
    
    AxisAlignedBB axisalignedbb = field_70121_D.func_72314_b(RADIUS, 2.0D, RADIUS);
    List list1 = field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
    
    if ((list1 != null) && (!list1.isEmpty())) {
      Iterator iterator = list1.iterator();
      
      while (iterator.hasNext()) {
        EntityLivingBase entitylivingbase = (EntityLivingBase)iterator.next();
        double d0 = entitylivingbase.func_70092_e(field_70165_t, field_70163_u, field_70161_v);
        
        if (d0 < RADIUS * RADIUS) {
          double d1 = 1.0D - Math.sqrt(d0) / RADIUS;
          
          if (entitylivingbase == field_72308_g) {
            d1 = 1.0D;
          }
          
          int j = (int)(d1 * 100.0D + 0.5D);
          
          entitylivingbase.func_70690_d(new PotionEffect(field_76421_dfield_76415_H, j, 5));
          if ((entitylivingbase instanceof EntityLiving)) {
            EntityUtil.dropAttackTarget((EntityLiving)entitylivingbase);
          }
        }
      }
    }
  }
  
  private void explodeBats(World world, int posX, int posY, int posZ, int side, boolean enhanced) {
    int x = posX + (side == 5 ? 1 : side == 4 ? -1 : 0);
    int z = posZ + (side == 3 ? 1 : side == 2 ? -1 : 0);
    int y = posY + (side == 1 ? 1 : side == 0 ? -1 : 0);
    if ((side == 1) && (!world.func_147439_a(x, posY, z).func_149688_o().func_76220_a())) {
      y--;
    }
    int NUM_BATS = enhanced ? 14 : 10;
    for (int i = 0; i < NUM_BATS; i++) {
      EntityBat bat = new EntityBat(world);
      NBTTagCompound nbtBat = bat.getEntityData();
      nbtBat.func_74757_a("WITCNoDrops", true);
      bat.func_70012_b(x, y, z, 0.0F, 0.0F);
      field_70170_p.func_72838_d(bat);
    }
    ParticleEffect.LARGE_EXPLODE.send(SoundEffect.MOB_ENDERMEN_PORTAL, world, 0.5D + x, 0.5D + y, 0.5D + z, 3.0D, 3.0D, 16);
  }
  























  private void impactInfection(MovingObjectPosition mop, boolean enhanced)
  {
    if (field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
      Block blockID = field_70170_p.func_147439_a(field_72311_b, field_72312_c, field_72309_d);
      int blockMeta = field_70170_p.func_72805_g(field_72311_b, field_72312_c, field_72309_d);
      if (((blockID == Blocks.field_150348_b) || (blockID == Blocks.field_150347_e) || ((blockID == Blocks.field_150417_aV) && (blockMeta == 0))) && (BlockProtect.canBreak(field_72311_b, field_72309_d, field_72312_c, field_70170_p))) {
        if (blockID == Blocks.field_150348_b) {
          field_70170_p.func_147465_d(field_72311_b, field_72312_c, field_72309_d, Blocks.field_150418_aU, 0, 3);
        } else if (blockID == Blocks.field_150347_e) {
          field_70170_p.func_147465_d(field_72311_b, field_72312_c, field_72309_d, Blocks.field_150418_aU, 1, 3);
        } else if (blockID == Blocks.field_150417_aV) {
          field_70170_p.func_147465_d(field_72311_b, field_72312_c, field_72309_d, Blocks.field_150418_aU, 2, 3);
        }
        return;
      }
    } else if ((field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY) && 
      ((field_72308_g instanceof EntityLivingBase))) {
      EntityLivingBase entity = (EntityLivingBase)field_72308_g;
      
      if ((entity instanceof EntityVillager)) {
        EntityZombie entityzombie = new EntityZombie(field_70170_p);
        entityzombie.func_82149_j(entity);
        field_70170_p.func_72900_e(entity);
        entityzombie.func_110161_a((IEntityLivingData)null);
        entityzombie.func_82229_g(true);
        if (entity.func_70631_g_()) {
          entityzombie.func_82227_f(true);
        }
        field_70170_p.func_72838_d(entityzombie);
        field_70170_p.func_72889_a((EntityPlayer)null, 1016, (int)field_70165_t, (int)field_70163_u, (int)field_70161_v, 0);
      } else {
        float WORM_DAMAGE = enhanced ? 4.0F : 1.0F;
        
        entity.func_70097_a(DamageSource.func_76356_a(entity, func_85052_h()), WORM_DAMAGE);
        entity.func_70690_d(new PotionEffect(field_76421_dfield_76415_H, 100, 8));
      }
      return;
    }
    


    EntityItem itemEntity = null;
    if (mop != null) {
      ItemStack newBrewStack = ItemsGENERIC.itemBrewOfInfection.createStack();
      switch (1.$SwitchMap$net$minecraft$util$MovingObjectPosition$MovingObjectType[field_72313_a.ordinal()]) {
      case 1: 
        itemEntity = new EntityItem(field_70170_p, field_72311_b + 0.5D, field_72312_c + (field_72310_e == 0 ? -1 : 1) + 0.5D, field_72309_d + 0.5D, newBrewStack);
        break;
      case 2: 
        itemEntity = new EntityItem(field_70170_p, field_72308_g.field_70165_t, field_72308_g.field_70163_u, field_72308_g.field_70161_v, newBrewStack);
        break;
      }
      
    }
    
    skipFX = true;
    if (itemEntity != null) {
      field_70170_p.func_72838_d(itemEntity);
    }
  }
  
  private void impactHitchcock(MovingObjectPosition mop) {
    if ((field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY) && ((field_72308_g instanceof EntityLivingBase))) {
      EntityLivingBase victim = (EntityLivingBase)field_72308_g;
      int BIRDS = field_70170_p.field_73012_v.nextInt(2) + 3;
      
      for (int i = 0; i < BIRDS; i++) {
        EntityOwl owl = new EntityOwl(field_70170_p);
        owl.func_70012_b(field_70165_t - 2.0D + field_70170_p.field_73012_v.nextInt(5), field_70163_u + field_70131_O + 1.0D + field_70170_p.field_73012_v.nextInt(2), field_70161_v - 2.0D + field_70170_p.field_73012_v.nextInt(5), 0.0F, 0.0F);
        owl.func_70624_b(victim);
        owl.setTimeToLive(400);
        field_70170_p.func_72838_d(owl);
        
        ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, owl, 1.0D, 1.0D, 16);
      }
    }
    else {
      EntityItem itemEntity = null;
      if (mop != null) {
        ItemStack newBrewStack = ItemsGENERIC.itemBrewOfHitchcock.createStack();
        switch (1.$SwitchMap$net$minecraft$util$MovingObjectPosition$MovingObjectType[field_72313_a.ordinal()]) {
        case 1: 
          itemEntity = new EntityItem(field_70170_p, field_72311_b + 0.5D, field_72312_c + (field_72310_e == 0 ? -1 : 1) + 0.5D, field_72309_d + 0.5D, newBrewStack);
          break;
        case 2: 
          itemEntity = new EntityItem(field_70170_p, field_72308_g.field_70165_t, field_72308_g.field_70163_u, field_72308_g.field_70161_v, newBrewStack);
          break;
        }
        
      }
      
      skipFX = true;
      if (itemEntity != null) {
        field_70170_p.func_72838_d(itemEntity);
      }
    }
  }
  
  private void impactLeaping(MovingObjectPosition mop, boolean enhanced) {
    Entity livingEntity = field_72308_g;
    
    double RADIUS = enhanced ? 6.0D : 5.0D;
    
    AxisAlignedBB axisalignedbb = field_70121_D.func_72314_b(RADIUS, 2.0D, RADIUS);
    List list1 = field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
    
    if ((list1 != null) && (!list1.isEmpty())) {
      Iterator iterator = list1.iterator();
      
      while (iterator.hasNext()) {
        EntityLivingBase entitylivingbase = (EntityLivingBase)iterator.next();
        double d0 = entitylivingbase.func_70092_e(field_70165_t, field_70163_u, field_70161_v);
        
        if (d0 < RADIUS * RADIUS) {
          double d1 = 1.0D - 0.5D * (Math.sqrt(d0) / RADIUS / 2.0D);
          
          if (entitylivingbase == livingEntity) {
            d1 = 1.0D;
          }
          
          int j = (int)(d1 * 400.0D + 0.5D);
          
          double LEAP = d1 * 1.6D;
          
          entitylivingbase.func_70690_d(new PotionEffect(field_76430_jfield_76415_H, 200, 3));
          if ((entitylivingbase instanceof EntityPlayer)) {
            Witchery.packetPipeline.sendTo(new PacketPushTarget(field_70159_w, field_70181_x + LEAP, field_70179_y), (EntityPlayer)entitylivingbase);
          } else {
            field_70181_x += LEAP;
          }
        }
      }
    }
  }
  
  private void impactFrogsTongue(MovingObjectPosition mop, boolean enhanced) {
    if ((!field_70170_p.field_72995_K) && (func_85052_h() != null)) {
      double RADIUS = enhanced ? 5.0D : 4.0D;
      double RADIUS_SQ = RADIUS * RADIUS;
      EntityLivingBase thrower = func_85052_h();
      
      boolean pulled = false;
      
      AxisAlignedBB axisalignedbb = field_70121_D.func_72314_b(RADIUS, 2.0D, RADIUS);
      List entityLivingList = field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
      if ((entityLivingList != null) && (!entityLivingList.isEmpty())) {
        Iterator iterator = entityLivingList.iterator();
        while (iterator.hasNext()) {
          EntityLivingBase livingEntity = (EntityLivingBase)iterator.next();
          double distanceSq = livingEntity.func_70092_e(field_70165_t, field_70163_u, field_70161_v);
          
          if ((distanceSq < RADIUS_SQ) && (livingEntity != func_85052_h())) {
            pull(field_70170_p, livingEntity, field_70165_t, field_70163_u, field_70161_v, 0.05D, 0.0D);
          }
        }
      }
    }
  }
  











































  private void pull(World world, Entity entity, double posX, double posY, double posZ, double dy, double yy)
  {
    if (((entity instanceof EntityDragon)) || ((entity instanceof EntityHornedHuntsman))) {
      return;
    }
    
    double d = posX - field_70165_t;
    double d1 = posY - field_70163_u;
    double d2 = posZ - field_70161_v;
    

    float distance = MathHelper.func_76133_a(d * d + d1 * d1 + d2 * d2);
    float f2 = 0.1F + (float)dy;
    
    double mx = d / distance * f2 * distance;
    
    double my = yy == 0.0D ? 0.4D : d1 / distance * distance * 0.2D + 0.2D + yy;
    double mz = d2 / distance * f2 * distance;
    
    if ((entity instanceof EntityLivingBase)) {
      ((EntityLivingBase)entity).func_70690_d(new PotionEffect(field_76430_jfield_76415_H, 20, 1));
    }
    
    if ((entity instanceof EntityPlayer)) {
      Witchery.packetPipeline.sendTo(new PacketPushTarget(mx, my, mz), (EntityPlayer)entity);
    } else {
      field_70159_w = mx;
      field_70181_x = my;
      field_70179_y = mz;
    }
  }
  
  private void impactIce(MovingObjectPosition mop) {
    switch (1.$SwitchMap$net$minecraft$util$MovingObjectPosition$MovingObjectType[field_72313_a.ordinal()]) {
    case 1: 
      int FREEZE_RANGE = 3;
      if ((field_70170_p.func_147439_a(field_72311_b + 1, field_72312_c, field_72309_d).func_149688_o() == Material.field_151586_h) || (field_70170_p.func_147439_a(field_72311_b - 1, field_72312_c, field_72309_d).func_149688_o() == Material.field_151586_h) || (field_70170_p.func_147439_a(field_72311_b, field_72312_c + 1, field_72309_d).func_149688_o() == Material.field_151586_h) || (field_70170_p.func_147439_a(field_72311_b, field_72312_c - 1, field_72309_d).func_149688_o() == Material.field_151586_h) || (field_70170_p.func_147439_a(field_72311_b, field_72312_c, field_72309_d + 1).func_149688_o() == Material.field_151586_h) || (field_70170_p.func_147439_a(field_72311_b, field_72312_c, field_72309_d - 1).func_149688_o() == Material.field_151586_h))
      {




        freezeSurroundingWater(field_70170_p, field_72311_b, field_72312_c, field_72309_d, field_72311_b, field_72312_c, field_72309_d, 3);
        return;
      }
      
      int SHIELD_HEIGHT = 3;
      if (field_72310_e == 1) {
        explodeIceShield(field_70170_p, func_85052_h(), field_72311_b, field_72312_c, field_72309_d, 3);
        return; }
      if (field_72310_e != 0) {
        int b0 = 0;
        
        switch (field_72310_e) {
        case 0: 
        case 1: 
          b0 = 0;
          break;
        case 2: 
        case 3: 
          b0 = 8;
          break;
        case 4: 
        case 5: 
          b0 = 4;
        }
        
        int dx = field_72310_e == 4 ? -1 : field_72310_e == 5 ? 1 : 0;
        int dy = field_72310_e == 1 ? 1 : field_72310_e == 0 ? -1 : 0;
        int dz = field_72310_e == 2 ? -1 : field_72310_e == 3 ? 1 : 0;
        
        explodeIceShield(field_70170_p, func_85052_h(), field_72311_b + dx, field_72312_c + dy, field_72309_d + dz, 3);
        return;
      }
      
      EntityItem itemEntity = null;
      if (mop != null) {
        ItemStack newBrewStack = ItemsGENERIC.itemBrewOfIce.createStack();
        switch (1.$SwitchMap$net$minecraft$util$MovingObjectPosition$MovingObjectType[field_72313_a.ordinal()]) {
        case 1: 
          itemEntity = new EntityItem(field_70170_p, field_72311_b + 0.5D, field_72312_c + (field_72310_e == 0 ? -1 : 1) + 0.5D, field_72309_d + 0.5D, newBrewStack);
          break;
        case 2: 
          itemEntity = new EntityItem(field_70170_p, field_72308_g.field_70165_t, field_72308_g.field_70163_u, field_72308_g.field_70161_v, newBrewStack);
          break;
        }
        
      }
      
      skipFX = true;
      if (itemEntity != null) {
        field_70170_p.func_72838_d(itemEntity);
      }
      break;
    case 2: 
      int x = (int)Math.round(field_72308_g.field_70165_t);
      int y = MathHelper.func_76128_c(field_72308_g.field_70163_u);
      int z = (int)Math.round(field_72308_g.field_70161_v);
      explodeIceBlock(field_70170_p, x, y, z, -1, field_72308_g);
      break;
    }
    
  }
  
  private void freezeSurroundingWater(World world, int x, int y, int z, int x0, int y0, int z0, int range)
  {
    if ((Math.abs(x0 - x) >= range) || (Math.abs(y0 - y) >= range) || (Math.abs(z0 - z) >= range)) {
      return;
    }
    if (freezeWater(world, x + 1, y, z)) {
      freezeSurroundingWater(world, x + 1, y, z, x0, y0, z0, range);
    }
    
    if (freezeWater(world, x - 1, y, z)) {
      freezeSurroundingWater(world, x - 1, y, z, x0, y0, z0, range);
    }
    
    if (freezeWater(world, x, y, z + 1)) {
      freezeSurroundingWater(world, x, y, z + 1, x0, y0, z0, range);
    }
    
    if (freezeWater(world, x, y, z - 1)) {
      freezeSurroundingWater(world, x, y, z - 1, x0, y0, z0, range);
    }
    
    if (freezeWater(world, x, y + 1, z)) {
      freezeSurroundingWater(world, x, y + 1, z, x0, y0, z0, range);
    }
    
    if (freezeWater(world, x, y - 1, z)) {
      freezeSurroundingWater(world, x, y - 1, z, x0, y0, z0, range);
    }
  }
  
  private boolean freezeWater(World world, int x, int y, int z) {
    if (world.func_147439_a(x, y, z).func_149688_o() == Material.field_151586_h) {
      world.func_147449_b(x, y, z, Blocks.field_150432_aD);
      return true;
    }
    return false;
  }
  
  public static void explodeIceBlock(World world, int posX, int posY, int posZ, int side, Entity entity) {
    int x = posX + (side == 5 ? 1 : side == 4 ? -1 : 0);
    int z = posZ + (side == 3 ? 1 : side == 2 ? -1 : 0);
    int y = posY + (side == 1 ? 1 : side == 0 ? -1 : 0) - 1;
    if ((side == 1) && (!world.func_147439_a(x, posY, z).func_149688_o().func_76220_a())) {
      y--;
    }
    
    Block block = Blocks.field_150432_aD;
    boolean resistent = ((entity instanceof EntityDemon)) || ((entity instanceof net.minecraft.entity.monster.EntityBlaze)) || ((entity instanceof EntityDragon)) || ((entity instanceof EntityHornedHuntsman)) || ((entity instanceof EntityEnt)) || ((entity instanceof net.minecraft.entity.boss.EntityWither)) || ((entity instanceof net.minecraft.entity.monster.EntityIronGolem));
    
    if (resistent) {
      setBlockIfNotSolid(world, x, y + 1, z, Blocks.field_150358_i);
    } else {
      int HEIGHT = resistent ? 2 : 4;
      for (int i = 0; i < HEIGHT; i++) {
        setBlockIfNotSolid(world, x - 2, y + i, z - 1, block);
        setBlockIfNotSolid(world, x - 2, y + i, z, block);
        setBlockIfNotSolid(world, x - 1, y + i, z + 1, block);
        setBlockIfNotSolid(world, x, y + i, z + 1, block);
        setBlockIfNotSolid(world, x + 1, y + i, z, block);
        setBlockIfNotSolid(world, x + 1, y + i, z - 1, block);
        
        setBlockIfNotSolid(world, x, y + i, z - 2, block);
        setBlockIfNotSolid(world, x - 1, y + i, z - 2, block);
        
        setBlockIfNotSolid(world, x - 2, y + i, z - 2, block);
        setBlockIfNotSolid(world, x - 2, y + i, z + 1, block);
        setBlockIfNotSolid(world, x + 1, y + i, z + 1, block);
        setBlockIfNotSolid(world, x + 1, y + i, z - 2, block);
      }
      









      setBlockIfNotSolid(world, x, y, z, block);
      if (!resistent) {
        setBlockIfNotSolid(world, x, y + HEIGHT - 1, z, block);
        setBlockIfNotSolid(world, x - 1, y + HEIGHT - 1, z - 1, block);
        setBlockIfNotSolid(world, x - 1, y + HEIGHT - 1, z, block);
        setBlockIfNotSolid(world, x, y + HEIGHT - 1, z - 1, block);
      }
      
      if ((entity instanceof EntityCreeper)) {
        EntityCreeper creeper = (EntityCreeper)entity;
        boolean flag = world.func_82736_K().func_82766_b("mobGriefing");
        
        if (creeper.func_70830_n())
        {
          world.func_72876_a(creeper, field_70165_t, field_70163_u, field_70161_v, 6.0F, flag);
        }
        else
        {
          world.func_72876_a(creeper, field_70165_t, field_70163_u, field_70161_v, 3.0F, flag);
        }
        
        creeper.func_70106_y();
      }
    }
  }
  
  public static void explodeIceShield(World world, EntityLivingBase player, int posX, int posY, int posZ, int height)
  {
    double f1 = player != null ? MathHelper.func_76134_b(-field_70177_z * 0.017453292F - 3.1415927F) : 0.0D;
    double f2 = player != null ? MathHelper.func_76126_a(-field_70177_z * 0.017453292F - 3.1415927F) : 0.0D;
    Vec3 loc = Vec3.func_72443_a(f2, 0.0D, f1);
    
    if (!world.func_147439_a(posX, posY, posZ).func_149688_o().func_76220_a()) {
      posY--;
    }
    
    explodeIceColumn(world, posX, posY + 1, posZ, height);
    
    loc.func_72442_b((float)Math.toRadians(90.0D));
    int newX = MathHelper.func_76128_c(posX + 0.5D + field_72450_a * 1.0D);
    int newZ = MathHelper.func_76128_c(posZ + 0.5D + field_72449_c * 1.0D);
    explodeIceColumn(world, newX, posY + 1, newZ, height);
    
    loc.func_72442_b((float)Math.toRadians(180.0D));
    newX = MathHelper.func_76128_c(posX + 0.5D + field_72450_a * 1.0D);
    newZ = MathHelper.func_76128_c(posZ + 0.5D + field_72449_c * 1.0D);
    explodeIceColumn(world, newX, posY + 1, newZ, height);
  }
  
  public static void explodeIceColumn(World world, int posX, int posY, int posZ, int height) {
    for (int offsetPosY = posY; offsetPosY < posY + height; offsetPosY++) {
      setBlockIfNotSolid(world, posX, offsetPosY, posZ, Blocks.field_150432_aD);
    }
  }
  
  private void impactLove(MovingObjectPosition mop, boolean enhanced) {
    double RADIUS = enhanced ? 5.0D : 4.0D;
    AxisAlignedBB axisalignedbb = field_70121_D.func_72314_b(RADIUS, 2.0D, RADIUS);
    List list1 = field_70170_p.func_72872_a(EntityLiving.class, axisalignedbb);
    
    if ((list1 != null) && (!list1.isEmpty()) && (!field_70170_p.field_72995_K))
    {
      EntityLivingBase entityThrower = func_85052_h();
      EntityPlayer thrower = (entityThrower != null) && ((entityThrower instanceof EntityPlayer)) ? (EntityPlayer)entityThrower : null;
      
      Iterator iterator = list1.iterator();
      
      ArrayList<EntityVillager> villagers = new ArrayList();
      ArrayList<EntityZombie> zombies = new ArrayList();
      while (iterator.hasNext()) {
        EntityLiving entitylivingbase = (EntityLiving)iterator.next();
        double d0 = entitylivingbase.func_70092_e(field_70165_t, field_70163_u, field_70161_v);
        
        if (d0 < RADIUS * RADIUS) {
          double d1 = 1.0D - Math.sqrt(d0) / RADIUS;
          
          if (entitylivingbase == field_72308_g) {
            d1 = 1.0D;
          }
          
          int j = (int)(d1 * 400.0D + 0.5D);
          
          if ((entitylivingbase instanceof EntityAnimal)) {
            EntityAnimal animal = (EntityAnimal)entitylivingbase;
            if (animal.func_70874_b() >= 0) {
              animal.func_70873_a(0);
              animal.func_146082_f(null);
            }
          } else if ((entitylivingbase instanceof EntityVillager)) {
            EntityVillager villager = (EntityVillager)entitylivingbase;
            if (villager.func_70874_b() >= 0) {
              villagers.add(villager);
            }
          } else if ((entitylivingbase instanceof EntityZombie))
          {
            EntityZombie zombie = (EntityZombie)entitylivingbase;
            if ((!zombie.func_70631_g_()) && (thrower != null)) {
              NBTTagCompound nbt = zombie.getEntityData();
              if (PotionEnslaved.isMobEnslavedBy(zombie, thrower)) {
                zombies.add(zombie);
              }
            }
          }
        }
      }
      
      int limit = 10;
      while ((villagers.size() > 1) && (limit-- > 0)) {
        EntityVillager villager = (EntityVillager)villagers.get(0);
        EntityVillager mate = (EntityVillager)villagers.get(1);
        villager.func_70107_b(field_70165_t, field_70163_u, field_70161_v);
        ParticleEffect.HEART.send(SoundEffect.NONE, mate, 1.0D, 2.0D, 8);
        
        giveBirth(villager, mate);
        villagers.remove(0);
        villagers.remove(0);
      }
      
      limit = 10;
      while ((zombies.size() > 1) && (limit-- > 0)) {
        EntityZombie zombie = (EntityZombie)zombies.get(0);
        EntityZombie mate = (EntityZombie)zombies.get(1);
        zombie.func_70107_b(field_70165_t, field_70163_u, field_70161_v);
        ParticleEffect.HEART.send(SoundEffect.NONE, mate, 1.0D, 2.0D, 8);
        zombie.func_82229_g(true);
        mate.func_82229_g(true);
        EntityZombie baby = new EntityZombie(field_70170_p);
        baby.func_70012_b(field_70165_t, field_70163_u, field_70161_v, 0.0F, 0.0F);
        baby.func_82227_f(true);
        field_70170_p.func_72838_d(baby);
        
        zombies.remove(0);
        zombies.remove(0);
      }
    }
  }
  
  private void giveBirth(EntityVillager villagerObj, EntityVillager mate) {
    EntityVillager entityvillager = villagerObj.func_90011_a(mate);
    mate.func_70873_a(6000);
    villagerObj.func_70873_a(6000);
    entityvillager.func_70873_a(41536);
    entityvillager.func_70012_b(field_70165_t, field_70163_u, field_70161_v, 0.0F, 0.0F);
    field_70170_p.func_72838_d(entityvillager);
    field_70170_p.func_72960_a(entityvillager, (byte)12);
  }
  
  private void impactQuicklime(MovingObjectPosition mop) {
    if (field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY)
    {
      if ((field_72308_g instanceof EntityLivingBase)) {
        EntityLivingBase livingEntity = (EntityLivingBase)field_72308_g;
        if (!livingEntity.func_70644_a(Potion.field_76440_q)) {
          livingEntity.func_70690_d(new PotionEffect(field_76440_qfield_76415_H, 60, 0));
        }
        float DAMAGE = livingEntity.func_110143_aJ() == livingEntity.func_110138_aP() ? 0.5F : (field_72308_g instanceof net.minecraft.entity.monster.EntitySlime) ? 4.0F : 0.1F;
        livingEntity.func_70097_a(DamageSource.func_76356_a(this, func_85052_h()), DAMAGE);
      }
      

      skipFX = true;
      return;
    }
    

    EntityItem itemEntity = null;
    if (mop != null) {
      ItemStack newBrewStack = ItemsGENERIC.itemQuicklime.createStack();
      switch (1.$SwitchMap$net$minecraft$util$MovingObjectPosition$MovingObjectType[field_72313_a.ordinal()]) {
      case 1: 
        itemEntity = new EntityItem(field_70170_p, field_72311_b + 0.5D, field_72312_c + (field_72310_e == 0 ? -1 : 1) + 0.5D, field_72309_d + 0.5D, newBrewStack);
        break;
      case 2: 
        itemEntity = new EntityItem(field_70170_p, field_72308_g.field_70165_t, field_72308_g.field_70163_u, field_72308_g.field_70161_v, newBrewStack);
        break;
      }
      
    }
    
    skipFX = true;
    if (itemEntity != null) {
      field_70170_p.func_72838_d(itemEntity);
    }
  }
  
  private void impactRaising(MovingObjectPosition mop) {
    if ((field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) && (field_72310_e == 1))
    {
      int posX = field_72311_b;
      int posY = field_72312_c;
      int posZ = field_72309_d;
      World world = field_70170_p;
      
      raiseDead(posX, posY, posZ, world, func_85052_h());
      
      return;
    }
    

    EntityItem itemEntity = null;
    if (mop != null) {
      ItemStack newBrewStack = ItemsGENERIC.itemBrewOfRaising.createStack();
      switch (1.$SwitchMap$net$minecraft$util$MovingObjectPosition$MovingObjectType[field_72313_a.ordinal()]) {
      case 1: 
        itemEntity = new EntityItem(field_70170_p, field_72311_b + 0.5D, field_72312_c + (field_72310_e == 0 ? -1 : 1) + 0.5D, field_72309_d + 0.5D, newBrewStack);
        break;
      case 2: 
        itemEntity = new EntityItem(field_70170_p, field_72308_g.field_70165_t, field_72308_g.field_70163_u, field_72308_g.field_70161_v, newBrewStack);
        break;
      }
      
    }
    
    skipFX = true;
    if (itemEntity != null) {
      field_70170_p.func_72838_d(itemEntity);
    }
  }
  
  public static void raiseDead(int posX, int posY, int posZ, World world, EntityLivingBase raiser) {
    int y0 = world.func_147439_a(posX, posY, posZ).func_149688_o().func_76220_a() ? posY : posY - 1;
    
    int MAX_SPAWNS = 3;
    int MAX_DISTANCE = 3;
    int MAX_DROP = 6;
    
    EntityPlayer playerThrower = (EntityPlayer)((raiser instanceof EntityPlayer) ? raiser : null);
    
    raiseUndead(world, posX, y0, posZ, playerThrower);
    
    int extraCount = 0;
    double chance = field_73012_v.nextDouble();
    if (chance < 0.1D) {
      extraCount = 2;
    } else if (chance < 0.4D) {
      extraCount = 1;
    }
    
    for (int i = 0; i < extraCount; i++) {
      int x = posX - 3 + field_73012_v.nextInt(6) + 1;
      int z = posZ - 3 + field_73012_v.nextInt(6) + 1;
      int y = -1;
      for (int dy = -6; dy < 6; dy++) {
        if (world.func_147439_a(x, y0 - dy, z).func_149688_o().func_76220_a()) {
          y = y0 - dy;
          break;
        }
      }
      if (y != -1) {
        raiseUndead(world, x, y, z, playerThrower);
      }
    }
  }
  
  private static void raiseUndead(World world, int posX, int posY, int posZ, EntityPlayer thrower) {
    if (!field_72995_K) {
      Block blockID = world.func_147439_a(posX, posY, posZ);
      if ((blockID != Blocks.field_150346_d) && (blockID != Blocks.field_150348_b) && (blockID != Blocks.field_150349_c) && (blockID != Blocks.field_150424_aL) && (blockID != Blocks.field_150391_bh) && (blockID != Blocks.field_150425_aM) && (blockID != Blocks.field_150347_e) && (blockID != Blocks.field_150351_n) && (blockID != Blocks.field_150354_m))
      {

        posY++;
      }
      spawnParticles(world, ParticleEffect.SMOKE, 0.5D + posX, 0.5D + posY, 0.5D + posZ);
      world.func_147468_f(posX, posY, posZ);
      world.func_147468_f(posX, posY + 1, posZ);
      EntityLiving undeadEntity = createUndeadCreature(world);
      undeadEntity.func_70012_b(0.5D + posX, 0.5D + posY, 0.5D + posZ, 1.0F, 0.0F);
      IEntityLivingData entitylivingData = null;
      entitylivingData = undeadEntity.func_110161_a(entitylivingData);
      undeadEntity.func_110163_bv();
      
      if (thrower != null) {
        try {
          PotionEnslaved.setEnslaverForMob(undeadEntity, thrower);
        } catch (Exception e) {
          Log.instance().warning(e, "Unhandled exception occurred setting enslaver from raiseUnded potion.");
        }
      }
      
      world.func_72838_d(undeadEntity);
    }
  }
  
  private static EntityLiving createUndeadCreature(World world) {
    double value = field_73012_v.nextDouble();
    if (value < 0.6D)
      return new EntityZombie(world);
    if (value < 0.97D) {
      return new net.minecraft.entity.monster.EntitySkeleton(world);
    }
    return new EntityPigZombie(world);
  }
  
  private void impactErosion(MovingObjectPosition mop, boolean enhanced)
  {
    if (field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
      if (BlockProtect.checkModsForBreakOK(field_70170_p, field_72311_b, field_72309_d, field_72312_c, func_85052_h())) {
        int RADIUS = 2;
        int obsidianMetled = 0;
        obsidianMetled += drawFilledCircle(field_70170_p, field_72311_b, field_72309_d, field_72312_c, 2);
        for (int i = 0; i < 2; i++) {
          int dy = i + 1;
          obsidianMetled += drawFilledCircle(field_70170_p, field_72311_b, field_72309_d, field_72312_c + dy, 2 - dy);
          obsidianMetled += drawFilledCircle(field_70170_p, field_72311_b, field_72309_d, field_72312_c - dy, 2 - dy);
        }
        if (obsidianMetled > 0) {
          field_70170_p.func_72838_d(new EntityItem(field_70170_p, 0.5D + field_72311_b, 0.5D + field_72312_c, 0.5D + field_72309_d, new ItemStack(Blocks.field_150343_Z, obsidianMetled, 0)));
        }
      }
    }
    else if (field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY) {
      if ((field_72308_g instanceof EntityLivingBase)) {
        EntityLivingBase entity = (EntityLivingBase)field_72308_g;
        float ACID_DAMAGE = enhanced ? 10.0F : 8.0F;
        
        entity.func_70097_a(DamageSource.func_76356_a(entity, func_85052_h()), ACID_DAMAGE);
        if ((entity instanceof EntityPlayer)) {
          EntityPlayer player = (EntityPlayer)entity;
          if (causeAcidDamage(entity.func_70694_bm())) {
            player.func_71028_bD();
          }
          for (int slot = 0; slot < field_71071_by.field_70460_b.length; slot++) {
            if (causeAcidDamage(field_71071_by.field_70460_b[slot])) {
              field_71071_by.field_70460_b[slot] = null;
            }
          }
        } else {
          for (int slot = 0; slot < 5; slot++) {
            if (causeAcidDamage(entity.func_71124_b(slot))) {
              entity.func_70062_b(slot, null);
            }
          }
        }
      } else {
        skipFX = true;
        field_70170_p.func_72838_d(new EntityItem(field_70170_p, field_72308_g.field_70165_t, field_72308_g.field_70163_u, field_72308_g.field_70161_v, ItemsGENERIC.itemBrewOfErosion.createStack()));
      }
    }
  }
  
  private boolean causeAcidDamage(ItemStack itemstack)
  {
    int ITEM_ACID_DAMAGE = 100;
    if ((itemstack != null) && (itemstack.func_77984_f()) && (EarthItems.instance().isMatch(itemstack))) {
      itemstack.func_77972_a(100, func_85052_h());
      return itemstack.func_77960_j() <= 0;
    }
    return false;
  }
  
  protected int drawFilledCircle(World world, int x0, int z0, int y, int radius) {
    int x = radius;
    int z = 0;
    int radiusError = 1 - x;
    
    int obsidianMelted = 0;
    while (x >= z) {
      obsidianMelted += drawLine(world, -x + x0, x + x0, z + z0, y);
      obsidianMelted += drawLine(world, -z + x0, z + x0, x + z0, y);
      obsidianMelted += drawLine(world, -x + x0, x + x0, -z + z0, y);
      obsidianMelted += drawLine(world, -z + x0, z + x0, -x + z0, y);
      
      z++;
      
      if (radiusError < 0) {
        radiusError += 2 * z + 1;
      } else {
        x--;
        radiusError += 2 * (z - x + 1);
      }
    }
    return obsidianMelted;
  }
  
  protected int drawLine(World world, int x1, int x2, int z, int y) {
    int obsidianMelted = 0;
    for (int x = x1; x <= x2; x++)
    {
      Block blockID = world.func_147439_a(x, y, z);
      if (blockID == Blocks.field_150343_Z) {
        obsidianMelted++;
      }
      if ((blockID != Blocks.field_150350_a) && (blockID != Blocks.field_150353_l) && (blockID != Blocks.field_150356_k) && (blockID != Blocks.field_150480_ab) && (blockID != Blocks.field_150358_i) && (blockID != Blocks.field_150355_j) && (BlockProtect.canBreak(blockID, world)))
      {

        world.func_147468_f(x, y, z);
        spawnParticles(field_70170_p, ParticleEffect.SPLASH, field_70165_t, field_70163_u, field_70161_v);
      }
    }
    return obsidianMelted;
  }
  
  private void impactSprout(MovingObjectPosition mop, boolean enhanced) {
    if ((mop != null) && (field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK))
    {
      int posX = field_72311_b;
      int posY = field_72312_c;
      int posZ = field_72309_d;
      World world = field_70170_p;
      int sideHit = field_72310_e;
      
      growBranch(posX, posY, posZ, world, sideHit, enhanced ? 20 : 15, field_70121_D);
    } else {
      EntityItem itemEntity = null;
      if (mop != null) {
        ItemStack newBrewStack = ItemsGENERIC.itemBrewOfSprouting.createStack();
        switch (1.$SwitchMap$net$minecraft$util$MovingObjectPosition$MovingObjectType[field_72313_a.ordinal()]) {
        case 1: 
          itemEntity = new EntityItem(field_70170_p, field_72311_b + 0.5D, field_72312_c + (field_72310_e == 0 ? -1 : 1) + 0.5D, field_72309_d + 0.5D, newBrewStack);
          break;
        case 2: 
          itemEntity = new EntityItem(field_70170_p, field_72308_g.field_70165_t, field_72308_g.field_70163_u, field_72308_g.field_70161_v, newBrewStack);
          break;
        }
        
      }
      
      skipFX = true;
      if (itemEntity != null) {
        field_70170_p.func_72838_d(itemEntity);
      }
    }
  }
  
  public static void growBranch(int posX, int posY, int posZ, World world, int sideHit, int extent, AxisAlignedBB boundingBox)
  {
    Block blockID = world.func_147439_a(posX, posY, posZ);
    int j1 = world.func_72805_g(posX, posY, posZ);
    Block logBlock; Block logBlock; if ((blockID == Blocks.field_150364_r) || (blockID == Blocks.field_150344_f) || (blockID == Blocks.field_150345_g) || (blockID == Blocks.field_150362_t)) {
      logBlock = Blocks.field_150364_r; } else { Block logBlock;
      if ((blockID == BlocksLOG) || (blockID == BlocksPLANKS) || (blockID == BlocksSAPLING) || (blockID == BlocksLEAVES))
      {
        logBlock = BlocksLOG;
      } else {
        logBlock = field_73012_v.nextInt(2) == 0 ? Blocks.field_150364_r : BlocksLOG;
        j1 = field_73012_v.nextInt(Blocks.field_150364_r == logBlock ? 4 : 3);
      } }
    Block leavesBlock = Blocks.field_150364_r == logBlock ? Blocks.field_150362_t : BlocksLEAVES;
    int b0 = 0;
    j1 &= 0x3;
    
    switch (sideHit) {
    case 0: 
    case 1: 
      b0 = 0;
      break;
    case 2: 
    case 3: 
      b0 = 8;
      break;
    case 4: 
    case 5: 
      b0 = 4;
    }
    
    int meta = j1 | b0;
    
    ParticleEffect particleEffect = ParticleEffect.EXPLODE;
    
    int dx = sideHit == 4 ? -1 : sideHit == 5 ? 1 : 0;
    int dy = sideHit == 1 ? 1 : sideHit == 0 ? -1 : 0;
    int dz = sideHit == 2 ? -1 : sideHit == 3 ? 1 : 0;
    
    int sproutExtent = extent;
    
    boolean isInitialBlockSolid = world.func_147439_a(posX, posY, posZ).func_149688_o().func_76220_a();
    

    for (int i = (sideHit == 1) && (!isInitialBlockSolid) ? 0 : 1; i < sproutExtent; i++) {
      int x = posX + i * dx;
      int y = posY + i * dy;
      int z = posZ + i * dz;
      if (y >= 255) {
        break;
      }
      if (!setBlockIfNotSolid(world, x, y, z, logBlock, meta)) {
        break;
      }
      int lx = (dx == 0) && (field_73012_v.nextInt(4) == 0) ? field_73012_v.nextInt(3) - 1 : 0;
      int ly = (dy == 0) && (lx == 0) && (field_73012_v.nextInt(4) == 0) ? field_73012_v.nextInt(3) - 1 : 0;
      int lz = (dz == 0) && (lx == 0) && (ly == 0) && (field_73012_v.nextInt(4) == 0) ? field_73012_v.nextInt(3) - 1 : 0;
      if ((lx != 0) || (ly != 0) || (lz != 0)) {
        setBlockIfNotSolid(world, x + lx, y + ly, z + lz, leavesBlock, meta);
      }
    }
    
    if (sideHit == 1) {
      AxisAlignedBB axisalignedbb = boundingBox.func_72314_b(0.0D, 2.0D, 0.0D);
      List list1 = world.func_72872_a(EntityLivingBase.class, axisalignedbb);
      if ((list1 != null) && (!list1.isEmpty())) {
        Iterator iterator = list1.iterator();
        int x = posX + i * dx;
        int y = Math.min(posY + i * dy, 255);
        int z = posZ + i * dz;
        while (iterator.hasNext()) {
          EntityLivingBase entitylivingbase = (EntityLivingBase)iterator.next();
          if ((!world.func_147439_a(x, y + 1, z).func_149688_o().func_76220_a()) && (!world.func_147439_a(x, y + 2, z).func_149688_o().func_76220_a())) {
            entitylivingbase.func_70107_b(0.5D + x, y + 1, 0.5D + z);
          }
        }
      }
    }
  }
  
  private void impactWasting(MovingObjectPosition mop, boolean enhanced) {
    Entity livingEntity = field_72308_g;
    double z;
    double x;
    double y; double z; if (field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY) {
      double x = field_70165_t;
      double y = field_70163_u;
      z = field_70161_v;
    } else {
      x = field_72311_b;
      y = field_72312_c;
      z = field_72309_d;
    }
    explodeWasting(field_70170_p, x, y, z, livingEntity, field_70121_D, enhanced);
  }
  
  public static void explodeWasting(World world, double posX, double posY, double posZ, Entity livingEntity, AxisAlignedBB boundingBox, boolean enhanced) {
    double RADIUS = enhanced ? 5.0D : 4.0D;
    
    AxisAlignedBB axisalignedbb = boundingBox.func_72314_b(RADIUS, 2.0D, RADIUS);
    List list1 = world.func_72872_a(EntityLivingBase.class, axisalignedbb);
    
    if ((list1 != null) && (!list1.isEmpty())) {
      Iterator iterator = list1.iterator();
      
      while (iterator.hasNext()) {
        EntityLivingBase entitylivingbase = (EntityLivingBase)iterator.next();
        double d0 = entitylivingbase.func_70092_e(posX, posY, posZ);
        
        if (d0 < RADIUS * RADIUS) {
          double d1 = 1.0D - Math.sqrt(d0) / RADIUS;
          
          if (entitylivingbase == livingEntity) {
            d1 = 1.0D;
          }
          
          int j = (int)(d1 * 400.0D + 0.5D);
          
          if ((entitylivingbase instanceof EntityPlayer)) {
            EntityPlayer victim = (EntityPlayer)entitylivingbase;
            int minLevel = enhanced ? 6 : 10;
            if (victim.func_71024_bL().func_75116_a() > minLevel) {
              victim.func_71024_bL().func_75122_a(-minLevel, 0.0F);
            }
            victim.func_70690_d(new PotionEffect(field_76438_sfield_76415_H, j * 2, enhanced ? 2 : 1));
            victim.func_70690_d(new PotionEffect(field_76436_ufield_76415_H, Math.max(j / 3, 40), 0));
          } else {
            entitylivingbase.func_70690_d(new PotionEffect(field_82731_vfield_76415_H, j * 2, enhanced ? 1 : 0));
            entitylivingbase.func_70690_d(new PotionEffect(field_76436_ufield_76415_H, Math.max(j / 3, 40), 0));
          }
        }
      }
    }
    
    int BLOCK_RADIUS = (int)RADIUS - 1;
    int BLOCK_RADIUS_SQ = BLOCK_RADIUS * BLOCK_RADIUS;
    int blockX = MathHelper.func_76128_c(posX);
    int blockY = MathHelper.func_76128_c(posY);
    int blockZ = MathHelper.func_76128_c(posZ);
    for (int y = blockY - BLOCK_RADIUS; y <= blockY + BLOCK_RADIUS; y++) {
      for (int x = blockX - BLOCK_RADIUS; x <= blockX + BLOCK_RADIUS; x++) {
        for (int z = blockZ - BLOCK_RADIUS; z <= blockZ + BLOCK_RADIUS; z++) {
          if (Coord.distanceSq(x, y, z, blockX, blockY, blockZ) <= BLOCK_RADIUS_SQ) {
            Material material = world.func_147439_a(x, y, z).func_149688_o();
            if ((material != null) && ((material == Material.field_151584_j) || (((material == Material.field_151585_k) || (material == Material.field_151582_l)) && (material.func_76222_j())))) {
              Block blockID = world.func_147439_a(x, y, z);
              if ((!(blockID instanceof BlockCircle)) && (!(blockID instanceof BlockCircleGlyph))) {
                blockID.func_149697_b(world, x, y, z, world.func_72805_g(x, y, z), 0);
                world.func_147468_f(x, y, z);
              }
            }
          }
        }
      }
    }
  }
  
  private void impactInk(MovingObjectPosition mop, boolean enhanced) {
    Entity livingEntity = field_72308_g;
    
    explodeInk(field_70170_p, field_70165_t, field_70163_u, field_70161_v, livingEntity, field_70121_D, enhanced);
  }
  
  public static void explodeInk(World world, double posX, double posY, double posZ, Entity livingEntity, AxisAlignedBB boundingBox, boolean enhanced) {
    double RADIUS = enhanced ? 5.0D : 4.0D;
    
    AxisAlignedBB axisalignedbb = boundingBox.func_72314_b(RADIUS, 2.0D, RADIUS);
    List list1 = world.func_72872_a(EntityLivingBase.class, axisalignedbb);
    
    if ((list1 != null) && (!list1.isEmpty())) {
      Iterator iterator = list1.iterator();
      
      while (iterator.hasNext()) {
        EntityLivingBase entitylivingbase = (EntityLivingBase)iterator.next();
        double d0 = entitylivingbase.func_70092_e(posX, posY, posZ);
        
        if (d0 < RADIUS * RADIUS) {
          double d1 = 1.0D - Math.sqrt(d0) / RADIUS;
          
          if (entitylivingbase == livingEntity) {
            d1 = 1.0D;
          }
          
          int j = (int)(d1 * 400.0D + 0.5D);
          
          entitylivingbase.func_70690_d(new PotionEffect(field_76440_qfield_76415_H, j, 0));
          if ((entitylivingbase instanceof EntityLiving)) {
            EntityUtil.dropAttackTarget((EntityLiving)entitylivingbase);
          }
        }
      }
    }
  }
  
  private void impactRock(MovingObjectPosition mop) {
    if (field_72308_g != null) {
      float DAMAGE = 6.0F;
      field_72308_g.func_70097_a(DamageSource.func_76356_a(this, func_85052_h()), 6.0F);
    }
    
    spawnParticles(field_70170_p, ParticleEffect.EXPLODE, field_70165_t, field_70163_u, field_70161_v);
  }
  
  private static void spawnParticles(World world, ParticleEffect effect, double posX, double posY, double posZ) {
    effect.send(SoundEffect.NONE, world, posX, posY, posZ, 1.0D, 1.0D, 8);
  }
  
  private void impactWebSmall(MovingObjectPosition mop) {
    switch (1.$SwitchMap$net$minecraft$util$MovingObjectPosition$MovingObjectType[field_72313_a.ordinal()]) {
    case 2: 
      field_70170_p.func_147449_b((int)field_72308_g.field_70165_t, (int)field_72308_g.field_70163_u, (int)field_72308_g.field_70161_v, Blocks.field_150321_G);
      break;
    case 1: 
      if (field_70170_p.func_147439_a(field_72311_b, field_72312_c, field_72309_d) == Blocks.field_150433_aE) {
        field_72312_c -= 1;
        field_72310_e = 1;
      }
      switch (field_72310_e) {
      case 0: 
        setBlockIfNotSolid(field_70170_p, field_72311_b, field_72312_c - 1, field_72309_d, Blocks.field_150321_G);
        break;
      case 1: 
        setBlockIfNotSolid(field_70170_p, field_72311_b, field_72312_c + 1, field_72309_d, Blocks.field_150321_G);
        break;
      case 2: 
        setBlockIfNotSolid(field_70170_p, field_72311_b - 1, field_72312_c, field_72309_d, Blocks.field_150321_G);
        break;
      case 3: 
        setBlockIfNotSolid(field_70170_p, field_72311_b + 1, field_72312_c, field_72309_d, Blocks.field_150321_G);
        break;
      case 4: 
        setBlockIfNotSolid(field_70170_p, field_72311_b, field_72312_c, field_72309_d - 1, Blocks.field_150321_G);
        break;
      case 5: 
        setBlockIfNotSolid(field_70170_p, field_72311_b, field_72312_c, field_72309_d + 1, Blocks.field_150321_G);
      }
      
      break;
    }
    
  }
  
  private void impactWebBig(MovingObjectPosition mop, boolean enhanced)
  {
    switch (1.$SwitchMap$net$minecraft$util$MovingObjectPosition$MovingObjectType[field_72313_a.ordinal()]) {
    case 1: 
      explodeWeb(field_70170_p, field_72311_b, field_72312_c, field_72309_d, field_72310_e, enhanced);
      break;
    case 2: 
      int x = MathHelper.func_76128_c(field_72308_g.field_70165_t);
      int y = MathHelper.func_76128_c(field_72308_g.field_70163_u);
      int z = MathHelper.func_76128_c(field_72308_g.field_70161_v);
      explodeWeb(field_70170_p, x, y, z, -1, enhanced);
      break;
    }
    
  }
  
  public static void explodeWeb(World world, int posX, int posY, int posZ, int side, boolean enhanced)
  {
    int x = posX + (side == 5 ? 1 : side == 4 ? -1 : 0);
    int z = posZ + (side == 3 ? 1 : side == 2 ? -1 : 0);
    int y = posY + (side == 1 ? 1 : side == 0 ? -1 : 0);
    if ((side == 1) && (!world.func_147439_a(x, posY, z).func_149688_o().func_76220_a())) {
      y--;
    }
    setBlockIfNotSolid(world, x, y, z, Blocks.field_150321_G);
    setBlockIfNotSolid(world, x + 1, y, z, Blocks.field_150321_G);
    setBlockIfNotSolid(world, x - 1, y, z, Blocks.field_150321_G);
    setBlockIfNotSolid(world, x, y, z + 1, Blocks.field_150321_G);
    setBlockIfNotSolid(world, x, y, z - 1, Blocks.field_150321_G);
    
    if (enhanced) {
      setBlockIfNotSolid(world, x + 1, y, z + 1, Blocks.field_150321_G);
      setBlockIfNotSolid(world, x - 1, y, z - 1, Blocks.field_150321_G);
      setBlockIfNotSolid(world, x - 1, y, z + 1, Blocks.field_150321_G);
      setBlockIfNotSolid(world, x + 1, y, z - 1, Blocks.field_150321_G);
    }
    
    setBlockIfNotSolid(world, x, y + 1, z, Blocks.field_150321_G);
    setBlockIfNotSolid(world, x, y - 1, z, Blocks.field_150321_G); }
  
  private void impactThorns(MovingObjectPosition mop, boolean enhanced) { int x;
    int z;
    int y;
    switch (1.$SwitchMap$net$minecraft$util$MovingObjectPosition$MovingObjectType[field_72313_a.ordinal()]) {
    case 1: 
      if ((field_72310_e == 1) || (field_70170_p.func_147439_a(field_72311_b, field_72312_c, field_72309_d) == Blocks.field_150434_aF)) {
        int y = field_72312_c;
        int x = field_72311_b;
        int z = field_72309_d;
        int CACTUS_HEIGHT = enhanced ? 4 : 3;
        
        if (plantCactus(field_70170_p, x, y, z, CACTUS_HEIGHT)) {
          break;
        }
      }
      else {
        ItemStack newBrewStack = ItemsGENERIC.itemBrewOfThorns.createStack();
        x = field_72311_b + (field_72310_e == 5 ? 1 : field_72310_e == 4 ? -1 : 0);
        z = field_72309_d + (field_72310_e == 3 ? 1 : field_72310_e == 2 ? -1 : 0);
        y = field_72312_c + (field_72310_e == 1 ? 1 : field_72310_e == 0 ? -1 : 0);
        skipFX = true;
        field_70170_p.func_72838_d(new EntityItem(field_70170_p, x + 0.5D, y + 0.5D, z + 0.5D, newBrewStack)); }
      break;
    case 2: 
      int CACTUS_HEIGHT = enhanced ? 2 : 1;
      x = MathHelper.func_76128_c(field_72308_g.field_70165_t);
      y = MathHelper.func_76128_c(field_72308_g.field_70163_u);
      z = MathHelper.func_76128_c(field_72308_g.field_70161_v);
      
      boolean success = plantCactus(field_70170_p, x + 1, y, z, CACTUS_HEIGHT);
      success = (success) && (plantCactus(field_70170_p, x - 1, y, z, CACTUS_HEIGHT));
      success = (success) && (plantCactus(field_70170_p, x, y, z + 1, CACTUS_HEIGHT));
      success = (success) && (plantCactus(field_70170_p, x, y, z - 1, CACTUS_HEIGHT));
      
      if (!success) {
        skipFX = true;
        field_70170_p.func_72838_d(new EntityItem(field_70170_p, field_72308_g.field_70165_t, field_72308_g.field_70163_u, field_72308_g.field_70161_v, ItemsGENERIC.itemBrewOfThorns.createStack()));
      }
      
      break;
    }
    
  }
  
  public static boolean plantCactus(World world, int x, int y, int z, int CACTUS_HEIGHT)
  {
    if (!world.func_147439_a(x, y, z).func_149688_o().func_76220_a()) {
      y--;
    }
    
    Material material = world.func_147439_a(x, y, z).func_149688_o();
    if ((material != Material.field_151571_B) && (material != Material.field_151596_z) && (material != Material.field_151577_b) && (material != Material.field_151578_c) && (material != Material.field_151576_e) && (material != Material.field_151595_p) && (material != Material.field_151597_y) && (material != Material.field_151583_m) && (material != Material.field_151570_A))
    {
      return false;
    }
    Block blockID = world.func_147439_a(x, y, z);
    if (!BlockProtect.canBreak(blockID, world)) {
      return false;
    }
    

    if (material != Material.field_151570_A) {
      world.func_147449_b(x, y, z, Blocks.field_150354_m);
    } else {
      while (world.func_147439_a(x, y, z) == Blocks.field_150434_aF) {
        y++;
      }
      y--;
    }
    for (int i = 1; i <= CACTUS_HEIGHT; i++) {
      if (y + i >= 256) {
        break;
      }
      
      if (!setBlockIfNotSolid(world, x, y + i, z, Blocks.field_150434_aF)) {
        break;
      }
    }
    
    return true;
  }
  
  private void impactVines(MovingObjectPosition mop, boolean enhanced) {
    if ((field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) && (field_72310_e != 0) && (field_72310_e != 1)) {
      int dx = field_72310_e == 5 ? 1 : field_72310_e == 4 ? -1 : 0;
      int dz = field_72310_e == 3 ? 1 : field_72310_e == 2 ? -1 : 0;
      int y0 = field_72312_c;
      
      int meta = 0;
      switch (field_72310_e) {
      case 2: 
        meta = 1;
        break;
      case 3: 
        meta = 4;
        break;
      case 4: 
        meta = 8;
        break;
      case 5: 
        meta = 2;
      }
      
      
      ParticleEffect EFFECT = ParticleEffect.EXPLODE;
      int y = y0;
      int x = field_72311_b;
      int z = field_72309_d;
      if ((!isNotSolidOrLeaves(field_70170_p.func_147439_a(x + dx, y, z + dz).func_149688_o())) || (!field_70170_p.func_147439_a(x, y, z).func_149688_o().func_76220_a())) {
        x += dx;
        z += dz;
      }
      while ((isNotSolidOrLeaves(field_70170_p.func_147439_a(x + dx, y, z + dz).func_149688_o())) && (field_70170_p.func_147439_a(x, y, z).func_149688_o().func_76220_a()) && (y > 0)) {
        field_70170_p.func_147465_d(x + dx, y, z + dz, Blocks.field_150395_bd, meta, 3);
        spawnParticles(field_70170_p, EFFECT, 0.5D + x + dx, 0.5D + y, 0.5D + z + dz);
        y--;
        if ((!isNotSolidOrLeaves(field_70170_p.func_147439_a(x + dx, y, z + dz).func_149688_o())) || (!field_70170_p.func_147439_a(x, y, z).func_149688_o().func_76220_a())) {
          x += dx;
          z += dz;
          if ((enhanced) && ((!isNotSolidOrLeaves(field_70170_p.func_147439_a(x + dx, y, z + dz).func_149688_o())) || (!field_70170_p.func_147439_a(x, y, z).func_149688_o().func_76220_a()))) {
            x += dx;
            z += dz;
          }
        }
      }
      y = y0 + 1;
      x = field_72311_b;
      z = field_72309_d;
      
      if (!field_70170_p.func_147439_a(x, y, z).func_149688_o().func_76220_a()) {
        x -= dx;
        z -= dz;
        if ((enhanced) && (!field_70170_p.func_147439_a(x, y, z).func_149688_o().func_76220_a())) {
          x -= dx;
          z -= dz;
        }
      }
      
      while ((isNotSolidOrLeaves(field_70170_p.func_147439_a(x + dx, y, z + dz).func_149688_o())) && (field_70170_p.func_147439_a(x, y, z).func_149688_o().func_76220_a()) && (y < 256)) {
        field_70170_p.func_147465_d(x + dx, y, z + dz, Blocks.field_150395_bd, meta, 3);
        spawnParticles(field_70170_p, EFFECT, 0.5D + x + dx, 0.5D + y, 0.5D + z + dz);
        y++;
        if (!field_70170_p.func_147439_a(x, y, z).func_149688_o().func_76220_a()) {
          x -= dx;
          z -= dz;
          if ((enhanced) && (!field_70170_p.func_147439_a(x, y, z).func_149688_o().func_76220_a())) {
            x -= dx;
            z -= dz;
          }
        }
      }
    } else {
      EntityItem itemEntity = null;
      ItemStack newBrewStack = ItemsGENERIC.itemBrewOfVines.createStack();
      switch (1.$SwitchMap$net$minecraft$util$MovingObjectPosition$MovingObjectType[field_72313_a.ordinal()]) {
      case 1: 
        itemEntity = new EntityItem(field_70170_p, field_72311_b + 0.5D, field_72312_c + (field_72310_e == 0 ? -1 : 1) + 0.5D, field_72309_d + 0.5D, newBrewStack);
        break;
      case 2: 
        itemEntity = new EntityItem(field_70170_p, field_72308_g.field_70165_t, field_72308_g.field_70163_u, field_72308_g.field_70161_v, newBrewStack);
        break;
      }
      
      
      skipFX = true;
      field_70170_p.func_72838_d(itemEntity);
    }
  }
  
  private boolean isNotSolidOrLeaves(Material material) {
    return (material == null) || (!material.func_76220_a()) || (material == Material.field_151584_j);
  }
  
  private static boolean setBlockIfNotSolid(World world, int x, int y, int z, Block block) {
    return setBlockIfNotSolid(world, x, y, z, block, 0);
  }
  
  private static boolean setBlockIfNotSolid(World world, int x, int y, int z, Block block, int metadata) {
    if ((!world.func_147439_a(x, y, z).func_149688_o().func_76220_a()) || ((block == Blocks.field_150321_G) && (world.func_147439_a(x, y, z) == Blocks.field_150433_aE))) {
      world.func_147465_d(x, y, z, block, metadata, 3);
      spawnParticles(world, ParticleEffect.EXPLODE, 0.5D + x, 0.5D + y, 0.5D + z);
      return true;
    }
    return false;
  }
  



  public void func_70037_a(NBTTagCompound nbtTag)
  {
    super.func_70037_a(nbtTag);
    
    if (nbtTag.func_74764_b("damageValue")) {
      damageValue = nbtTag.func_74762_e("damageValue");
      setDamageValue(damageValue);
    } else {
      func_70106_y();
    }
  }
  
  public void func_70014_b(NBTTagCompound nbtTag)
  {
    super.func_70014_b(nbtTag);
    
    nbtTag.func_74768_a("damageValue", damageValue);
  }
}
