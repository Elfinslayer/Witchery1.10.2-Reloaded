package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.brewing.EntityThrowableBase;
import com.emoniph.witchery.client.particle.NaturePowerFX;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.item.ItemSunGrenade;
import com.emoniph.witchery.util.CreatureUtil;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityGrenade extends EntityThrowableBase
{
  String owner;
  boolean blockPlaced;
  int blockX;
  int blockY;
  int blockZ;
  
  public EntityGrenade(World world)
  {
    super(world);
    func_70105_a(0.25F, 0.25F);
    field_70145_X = false;
  }
  
  public EntityGrenade(World world, EntityLivingBase thrower, ItemStack stack) {
    super(world, thrower, -20.0F);
    func_70105_a(0.25F, 0.25F);
    field_70145_X = false;
    if ((stack != null) && (stack.func_77973_b() == ItemsDUP_GRENADE)) {
      setMode(1);
      setOwner(ItemSunGrenade.getOwnerName(stack));
    } else {
      setMode(0);
    }
  }
  
  public EntityGrenade(World world, double x, double y, double z, ItemStack stack) {
    super(world, x, y, z, -20.0F);
    func_70105_a(0.25F, 0.25F);
    field_70145_X = false;
    
    if ((stack != null) && (stack.func_77973_b() == ItemsDUP_GRENADE)) {
      setMode(1);
      setOwner(ItemSunGrenade.getOwnerName(stack));
    } else {
      setMode(0);
    }
  }
  
  protected float getGravityVelocity()
  {
    return getImpact() ? 0.0F : 0.05F;
  }
  
  protected float func_70182_d()
  {
    return 0.75F;
  }
  
  protected float func_70183_g()
  {
    return -20.0F;
  }
  
  public void setOwner(String owner) {
    this.owner = owner;
  }
  
  protected void func_70088_a()
  {
    super.func_70088_a();
    field_70180_af.func_75682_a(6, Byte.valueOf((byte)0));
    field_70180_af.func_75682_a(16, Byte.valueOf((byte)0));
  }
  
  public int getMode() {
    return field_70180_af.func_75683_a(16);
  }
  
  public void setMode(int mode) {
    field_70180_af.func_75692_b(16, Byte.valueOf((byte)mode));
  }
  
  protected void setImpact(boolean impact) {
    func_70096_w().func_75692_b(6, Byte.valueOf((byte)(impact ? 1 : 0)));
  }
  
  public boolean getImpact() {
    return func_70096_w().func_75683_a(6) == 1;
  }
  
  protected int getMaxGroundTicks()
  {
    return super.getMaxGroundTicks();
  }
  
  protected int getMaxAirTicks()
  {
    return super.getMaxAirTicks();
  }
  
  protected void onImpact(net.minecraft.util.MovingObjectPosition mop)
  {
    if (!field_70170_p.field_72995_K) {
      if (getMode() == 0) {
        setImpact(true);
      } else {
        if (!field_70170_p.field_72995_K) {
          onSetDead();
        } else {
          onClientSetDead();
        }
        
        func_70106_y();
      }
    }
    field_70159_w = 0.0D;
    field_70181_x = 0.0D;
    field_70179_y = 0.0D;
  }
  
  public void func_70030_z()
  {
    super.func_70030_z();
  }
  
  public void func_70071_h_()
  {
    super.func_70071_h_();
    if (getMode() == 0) {
      if ((field_70170_p.field_72995_K) && (getImpact()) && (field_70170_p.field_73012_v.nextInt(4) == 0)) {
        float red = 1.0F;
        float green = 1.0F;
        float blue = 0.0F;
        Witchery.proxy.generateParticle(field_70170_p, field_70165_t - 0.1D + field_70170_p.field_73012_v.nextDouble() * 0.2D, field_70163_u + 0.3D * field_70131_O - 0.1D + field_70170_p.field_73012_v.nextDouble() * 0.2D, field_70161_v - 0.1D + field_70170_p.field_73012_v.nextDouble() * 0.2D, red, green, blue, 10, -0.3F);

      }
      else if ((!field_70170_p.field_72995_K) && (!field_70128_L)) {
        if ((!blockPlaced) && (field_70173_aa % 5 == 4)) {
          blockPlaced = true;
          blockX = MathHelper.func_76128_c(field_70165_t);
          blockY = MathHelper.func_76128_c(field_70163_u);
          blockZ = MathHelper.func_76128_c(field_70161_v);
          if (field_70170_p.func_147437_c(blockX, blockY, blockZ)) {
            field_70170_p.func_147449_b(blockX, blockY, blockZ, BlocksLIGHT);
          } else {
            blockY += 1;
            if (field_70170_p.func_147437_c(blockX, blockY, blockZ)) {
              field_70170_p.func_147449_b(blockX, blockY, blockZ, BlocksLIGHT);
            }
          }
        } else if ((blockPlaced) && ((field_70173_aa % 5 == 2) || (getImpact()))) {
          int x = MathHelper.func_76128_c(field_70165_t);
          int y = MathHelper.func_76128_c(field_70163_u);
          int z = MathHelper.func_76128_c(field_70161_v);
          if ((blockX != x) || (blockY != y) || (blockZ != z) || ((field_70173_aa % 30 == 4) && (field_70170_p.func_147437_c(x, y, z))))
          {
            if (field_70170_p.func_147439_a(blockX, blockY, blockZ) == BlocksLIGHT) {
              field_70170_p.func_147468_f(blockX, blockY, blockZ);
            }
            blockX = x;
            blockY = y;
            blockZ = z;
            
            if (field_70170_p.func_147437_c(blockX, blockY, blockZ)) {
              field_70170_p.func_147449_b(blockX, blockY, blockZ, BlocksLIGHT);
            } else {
              blockY += 1;
              if (field_70170_p.func_147437_c(blockX, blockY, blockZ)) {
                field_70170_p.func_147449_b(blockX, blockY, blockZ, BlocksLIGHT);
              }
            }
          }
        }
        if (getImpact()) {
          net.minecraft.entity.Entity entity = null;
          List<EntityLivingBase> list = field_70170_p.func_72872_a(EntityLivingBase.class, field_70121_D.func_72321_a(field_70159_w, field_70181_x, field_70179_y).func_72314_b(1.0D, 1.0D, 1.0D));
          


          double d0 = 0.0D;
          for (int j = 0; j < list.size(); j++) {
            EntityLivingBase entity1 = (EntityLivingBase)list.get(j);
            
            if ((entity1.func_70067_L()) && 
              (CreatureUtil.isUndead(entity1))) {
              entity1.func_70015_d(3);
            }
          }
        }
      }
    }
  }
  



  protected void onSetDead()
  {
    if (!field_70170_p.field_72995_K) {
      func_70099_a(ItemsGENERIC.itemQuartzSphere.createStack(), 0.5F);
      
      int mode = getMode();
      if (mode == 0) {
        if (blockPlaced) {
          blockPlaced = false;
          if (field_70170_p.func_147439_a(blockX, blockY, blockZ) == BlocksLIGHT) {
            field_70170_p.func_147468_f(blockX, blockY, blockZ);
          }
        }
        
        net.minecraft.entity.Entity entity = null;
        List<EntityLivingBase> list = field_70170_p.func_72872_a(EntityLivingBase.class, field_70121_D.func_72321_a(field_70159_w, field_70181_x, field_70179_y).func_72314_b(3.0D, 2.0D, 3.0D));
        
        double d0 = 0.0D;
        for (int j = 0; j < list.size(); j++) {
          EntityLivingBase entity1 = (EntityLivingBase)list.get(j);
          
          if (entity1.func_70067_L()) {
            if (CreatureUtil.isUndead(entity1)) {
              entity1.func_70015_d(5);
              

              if ((entity1 instanceof EntityPlayer)) {
                EntityPlayer player = (EntityPlayer)entity1;
                ExtendedPlayer playerEx = ExtendedPlayer.get(player);
                if ((playerEx.getVampireLevel() == 4) && (playerEx.canIncreaseVampireLevel())) {
                  if (playerEx.getVampireQuestCounter() >= 9) {
                    playerEx.increaseVampireLevel();
                  } else {
                    playerEx.increaseVampireQuestCounter();
                  }
                }
              }
            }
            
            entity1.func_70690_d(new net.minecraft.potion.PotionEffect(field_76440_qfield_76415_H, com.emoniph.witchery.util.TimeUtil.secsToTicks(field_70170_p.field_73012_v.nextInt(3) + 10), 0, true));
          }
        }
      }
      else if (mode == 1) {
        EntityFollower entity = new EntityFollower(field_70170_p);
        entity.setFollowerType(5);
        entity.setSkin(owner != null ? owner : "");
        entity.func_94058_c(owner != null ? owner : "Steve");
        entity.func_70012_b(field_70165_t, field_70163_u, field_70161_v, 0.0F, 0.0F);
        entity.setTTL(com.emoniph.witchery.util.TimeUtil.secsToTicks(10));
        field_70170_p.func_72838_d(entity);
        entity.attractAttention();
      }
    }
  }
  
  @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
  protected void onClientSetDead()
  {
    if (getMode() == 0) {
      for (int i = 0; i < 20; i++) {
        double width = 0.4D;
        double xPos = 0.3D + field_70146_Z.nextDouble() * 0.4D;
        double zPos = 0.3D + field_70146_Z.nextDouble() * 0.4D;
        double d0 = field_70165_t;
        double d1 = field_70163_u;
        double d2 = field_70161_v;
        
        NaturePowerFX sparkle = new NaturePowerFX(field_70170_p, d0, d1, d2);
        sparkle.setScale(1.0F);
        sparkle.setGravity(0.2F);
        sparkle.setCanMove(true);
        field_70145_X = true;
        double maxSpeed = 0.08D;
        double doubleSpeed = 0.16D;
        sparkle.func_70016_h(field_70146_Z.nextDouble() * 0.16D - 0.08D, field_70146_Z.nextDouble() * 0.05D + 0.12D, field_70146_Z.nextDouble() * 0.16D - 0.08D);
        
        sparkle.setMaxAge(25 + field_70146_Z.nextInt(10));
        float red = 1.0F;
        float green = 1.0F;
        float blue = 0.0F;
        float maxColorShift = 0.2F;
        float doubleColorShift = maxColorShift * 2.0F;
        float colorshiftR = field_70146_Z.nextFloat() * doubleColorShift - maxColorShift;
        float colorshiftG = field_70146_Z.nextFloat() * doubleColorShift - maxColorShift;
        float colorshiftB = field_70146_Z.nextFloat() * doubleColorShift - maxColorShift;
        sparkle.func_70538_b(red + colorshiftR, green + colorshiftG, blue + colorshiftB);
        sparkle.func_82338_g(0.1F);
        
        func_71410_xfield_71452_i.func_78873_a(sparkle);
      }
    }
  }
  





  public void func_70037_a(NBTTagCompound nbtRoot)
  {
    super.func_70037_a(nbtRoot);
    setImpact(nbtRoot.func_74767_n("Impacted"));
    blockPlaced = nbtRoot.func_74767_n("BlockPlaced");
    if (blockPlaced) {
      blockX = nbtRoot.func_74762_e("BlockPlacedX");
      blockY = nbtRoot.func_74762_e("BlockPlacedY");
      blockZ = nbtRoot.func_74762_e("BlockPlacedZ");
    }
    if (nbtRoot.func_74764_b("Mode")) {
      setMode(nbtRoot.func_74762_e("Mode"));
    }
    
    if (nbtRoot.func_74764_b("Owner")) {
      owner = nbtRoot.func_74779_i("Owner");
    } else {
      owner = null;
    }
  }
  
  public void func_70014_b(NBTTagCompound nbtRoot)
  {
    super.func_70014_b(nbtRoot);
    nbtRoot.func_74757_a("Impacted", getImpact());
    if (blockPlaced) {
      nbtRoot.func_74757_a("BlockPlaced", blockPlaced);
      nbtRoot.func_74768_a("BlockPlacedX", blockX);
      nbtRoot.func_74768_a("BlockPlacedY", blockY);
      nbtRoot.func_74768_a("BlockPlacedZ", blockZ);
    }
    nbtRoot.func_74768_a("Mode", getMode());
    if (owner != null) {
      nbtRoot.func_74778_a("Owner", owner);
    }
  }
}
