package com.emoniph.witchery.util;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.entity.EntityHornedHuntsman;
import com.emoniph.witchery.entity.ai.EntityAIAttackOnCollide2;
import com.emoniph.witchery.network.PacketPipeline;
import com.emoniph.witchery.network.PacketPushTarget;
import cpw.mods.fml.relauncher.ReflectionHelper;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.EntityTrackerEntry;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class EntityUtil
{
  private EntityUtil() {}
  
  public static EntityPlayer playerOrFake(World world, String thrower)
  {
    return playerOrFake(world, world != null ? world.func_72924_a(thrower) : null);
  }
  
  public static EntityPlayer playerOrFake(World world, EntityLivingBase entity) {
    if ((entity != null) && ((entity instanceof EntityPlayer)))
      return (EntityPlayer)entity;
    if ((world == null) || (!(world instanceof WorldServer))) {
      return null;
    }
    return FakePlayerFactory.getMinecraft((WorldServer)world);
  }
  

  public static <T extends Entity> T findNearestEntityWithinAABB(World world, Class<T> clazz, AxisAlignedBB bounds, Entity entity)
  {
    Entity foundEntity = world.func_72857_a(clazz, bounds, entity);
    if (foundEntity != null) {
      return foundEntity;
    }
    return null;
  }
  
  public static void spawnEntityInWorld(World world, Entity entity)
  {
    if ((entity != null) && (world != null) && (!field_72995_K)) {
      world.func_72838_d(entity);
    }
  }
  
  private static Field fieldTrackedEntities = null;
  public static Field fieldGhastTargetedEntity;
  
  public static void correctProjectileTrackerSync(World world, Entity projectile) { if ((!field_72995_K) && ((world instanceof WorldServer))) {
      try {
        if (fieldTrackedEntities == null) {
          fieldTrackedEntities = ReflectionHelper.findField(EntityTracker.class, new String[] { "trackedEntities", "field_72793_b", "b" });
        }
        

        if (fieldTrackedEntities != null) {
          EntityTracker tracker = ((WorldServer)world).func_73039_n();
          Set trackedEntities = (Set)fieldTrackedEntities.get(tracker);
          Iterator iterator = trackedEntities.iterator();
          while (iterator.hasNext()) {
            EntityTrackerEntry next = (EntityTrackerEntry)iterator.next();
            if (field_73132_a == projectile) {
              field_73136_m = 1;
              break;
            }
          }
        }
      } catch (IllegalAccessException e) {
        Log.instance().warning(e, "Exception occurred setting entity tracking for bolt.");
      } catch (Exception e) {
        Log.instance().debug(String.format("Exception occurred setting entity tracking for bolt. %s", new Object[] { e.toString() }));
      }
    }
  }
  
  public static void push(World world, Entity entity, EntityPosition position, double power)
  {
    Entity entity2 = entity;
    double d = x - field_70165_t;
    double d1 = y - field_70163_u;
    double d2 = z - field_70161_v;
    double d4 = d * d + d1 * d1 + d2 * d2;
    d4 *= d4;
    if (d4 <= Math.pow(6.0D, 4.0D)) {
      double d5 = -(d * 0.01999999955296516D / d4) * Math.pow(6.0D, 3.0D);
      double d6 = -(d1 * 0.01999999955296516D / d4) * Math.pow(6.0D, 3.0D);
      double d7 = -(d2 * 0.01999999955296516D / d4) * Math.pow(6.0D, 3.0D);
      if (d5 > 0.0D) {
        d5 = 0.22D;
      } else if (d5 < 0.0D) {
        d5 = -0.22D;
      }
      if (d6 > 0.2D) {
        d6 = 0.12D;
      } else if (d6 < -0.1D) {
        d6 = 0.12D;
      }
      if (d7 > 0.0D) {
        d7 = 0.22D;
      } else if (d7 < 0.0D) {
        d7 = -0.22D;
      }
      field_70159_w += d5 * power;
      field_70181_x += d6 * (power / 3.0D);
      field_70179_y += d7 * power;
    }
  }
  












  public static Field fieldGhastAggroCooldown;
  











  public static void pullTowards(World world, Entity entity, EntityPosition target, double dy, double yy)
  {
    if (((entity instanceof EntityDragon)) || ((entity instanceof EntityHornedHuntsman)) || (target.occupiedBy(entity))) {
      return;
    }
    
    double d = x - field_70165_t;
    double d1 = y - field_70163_u;
    double d2 = z - field_70161_v;
    

    float distance = MathHelper.func_76133_a(d * d + d1 * d1 + d2 * d2);
    if (distance < 0.01D) {
      return;
    }
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
  
  public static void pushback(World world, Entity entity, EntityPosition hit, double xyScale, double ySpeed) {
    double d = x - field_70165_t;
    double d1 = y - field_70163_u;
    double d2 = z - field_70161_v;
    
    Vec3 vec = Vec3.func_72443_a(d, d1, d2).func_72432_b();
    double dx = -field_72450_a * xyScale;
    double dy = Math.max(-field_72448_b, ySpeed);
    double dz = -field_72449_c * xyScale;
    
    if ((entity instanceof EntityPlayer)) {
      Witchery.packetPipeline.sendTo(new PacketPushTarget(dx, dy, dz), (EntityPlayer)entity);
    } else {
      field_70159_w = dx;
      field_70181_x = dy;
      field_70179_y = dz;
    }
  }
  
  public static <T extends Entity> List<T> getEntitiesInRadius(Class<T> clazz, TileEntity tile, double radius) {
    return getEntitiesInRadius(clazz, tile.func_145831_w(), 0.5D + field_145851_c, 0.5D + field_145848_d, 0.5D + field_145849_e, radius);
  }
  

  public static <T extends Entity> List<T> getEntitiesInRadius(Class<T> clazz, World world, double x, double y, double z, double radius)
  {
    AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(x - radius, y - radius, z - radius, x + radius, y + radius, z + radius);
    
    List<T> entities = world.func_72872_a(clazz, bounds);
    ArrayList<T> nearbyEntities = new ArrayList();
    double radiusSq = radius * radius;
    for (T entity : entities) {
      if (entity.func_70092_e(x, field_70163_u, z) <= radiusSq) {
        nearbyEntities.add(entity);
      }
    }
    return nearbyEntities;
  }
  


  public static void setTarget(EntityLiving attacker, EntityLivingBase victim)
  {
    attacker.func_70624_b(victim);
    if ((attacker instanceof EntityGhast)) {
      try {
        EntityGhast ghastEntity = (EntityGhast)attacker;
        
        if (fieldGhastTargetedEntity == null) {
          fieldGhastTargetedEntity = ReflectionHelper.findField(EntityGhast.class, new String[] { "targetedEntity", "field_70792_g", "g" });
        }
        
        fieldGhastTargetedEntity.set(ghastEntity, victim);
        
        if (fieldGhastAggroCooldown == null) {
          fieldGhastAggroCooldown = ReflectionHelper.findField(EntityGhast.class, new String[] { "aggroCooldown", "field_70798_h", "h" });
        }
        
        fieldGhastAggroCooldown.set(ghastEntity, Integer.valueOf(20000));
      } catch (IllegalAccessException e) {
        Log.instance().warning(e, "Exception occurred setting ghast target.");
      } catch (Exception e) {
        Log.instance().debug(String.format("Exception occurred setting ghast target. %s", new Object[] { e.toString() }));
      }
    }
    if ((attacker instanceof EntityCreature)) {
      EntityCreature attackerCreature = (EntityCreature)attacker;
      attackerCreature.func_70784_b(victim);
      attackerCreature.func_70604_c(victim);
      if (((attackerCreature instanceof EntityZombie)) || ((attackerCreature instanceof EntityCreeper))) {
        boolean found = false;
        Class victimClass = victim.getClass();
        for (Object obj : field_70715_bh.field_75782_a) {
          EntityAITasks.EntityAITaskEntry task = (EntityAITasks.EntityAITaskEntry)obj;
          if ((field_75733_a instanceof EntityAIAttackOnCollide2)) {
            EntityAIAttackOnCollide2 ai = (EntityAIAttackOnCollide2)field_75733_a;
            if ((ai == null) || (!ai.appliesToClass(victimClass))) break;
            found = true; break;
          }
        }
        


        if (!found) {
          field_70714_bg.func_75776_a(2, new EntityAIAttackOnCollide2(attackerCreature, victimClass, 1.0D, false));
        }
      }
    }
  }
  
  public static void dropAttackTarget(EntityLiving entity)
  {
    entity.func_70624_b(null);
    if ((entity instanceof EntityCreature)) {
      EntityCreature creatureEntity = (EntityCreature)entity;
      creatureEntity.func_70784_b(null);
      creatureEntity.func_70604_c(null);
    }
  }
  
  public static void syncInventory(EntityPlayer player) {
    if ((player instanceof EntityPlayerMP)) {
      ((EntityPlayerMP)player).func_71120_a(field_71069_bz);
    }
  }
  
  public static void persistanceRequired(EntityLiving entity) {
    entity.func_110163_bv();
  }
  
  public static void setNoDrops(EntityLiving entity) {
    if (entity != null) {
      NBTTagCompound nbtEntity = entity.getEntityData();
      nbtEntity.func_74757_a("WITCNoDrops", true);
    }
  }
  
  public static boolean isNoDrops(EntityLivingBase entity) {
    if ((entity == null) || ((entity instanceof EntityPlayer))) {
      return false;
    }
    NBTTagCompound nbtEntity = entity.getEntityData();
    return nbtEntity.func_74767_n("WITCNoDrops");
  }
  

  public static float getHealthAfterDamage(LivingHurtEvent event, float currentHealth, EntityLivingBase entity)
  {
    if (source.func_76363_c()) {
      return currentHealth - ammount;
    }
    
    float damage = ammount;
    int i = 25 - entity.func_70658_aO();
    float f1 = damage * i;
    damage = f1 / 25.0F;
    


    if ((entity.func_70644_a(Potion.field_76429_m)) && (source != DamageSource.field_76380_i)) {
      i = (entity.func_70660_b(Potion.field_76429_m).func_76458_c() + 1) * 5;
      float j = 25 - i;
      f1 = damage * j;
      damage = f1 / 25.0F;
    }
    
    if (damage <= 0.0F) {
      damage = 0.0F;
    } else {
      i = EnchantmentHelper.func_77508_a(entity.func_70035_c(), source);
      
      if (i > 20) {
        i = 20;
      }
      
      if ((i > 0) && (i <= 20)) {
        float j = 25 - i;
        f1 = damage * j;
        damage = f1 / 25.0F;
      }
    }
    
    return currentHealth - damage;
  }
  
  public static class DamageSourceSunlight extends EntityDamageSource
  {
    public static final DamageSourceSunlight SUN = new DamageSourceSunlight(null);
    
    public DamageSourceSunlight(Entity attacker) { super(attacker);
      func_76348_h();
      func_82726_p();
    }
    
    public IChatComponent func_151519_b(EntityLivingBase p_151519_1_) { EntityLivingBase entitylivingbase1 = p_151519_1_.func_94060_bK();
      String s = "witchery:death.attack." + field_76373_n;
      String s1 = s + ".player";
      return (entitylivingbase1 != null) && (net.minecraft.util.StatCollector.func_94522_b(s1)) ? new ChatComponentTranslation(s1, new Object[] { p_151519_1_.func_145748_c_(), entitylivingbase1.func_145748_c_() }) : new ChatComponentTranslation(s, new Object[] { p_151519_1_.func_145748_c_() });
    }
  }
  
  public static class DamageSourceVampireFire
    extends DamageSource
  {
    public static final DamageSourceVampireFire SOURCE = new DamageSourceVampireFire();
    
    public DamageSourceVampireFire() { super();
      func_76348_h();
      func_82726_p();
    }
  }
  
  public static void instantDeath(EntityLivingBase entity, EntityLivingBase attacker) {
    if ((entity != null) && (field_70170_p != null) && (!field_70170_p.field_72995_K)) {
      if ((entity instanceof EntityLiving)) {
        entity.func_70606_j(0.0F);
        if (attacker == null) {
          entity.func_70645_a(DamageSource.field_76376_m);
        } else {
          entity.func_70645_a(new EntityDamageSource(DamageSource.field_76376_m.func_76355_l(), attacker));
        }
        entity.func_70106_y();
      } else if ((entity instanceof EntityPlayer)) {
        EntityPlayer player = (EntityPlayer)entity;
        if (!field_71075_bZ.field_75098_d) {
          if (player.func_70608_bn()) {
            player.func_70999_a(true, true, false);
          }
          entity.func_70606_j(0.0F);
          if (ExtendedPlayer.get(player).isVampire()) {
            entity.func_70645_a(attacker == null ? DamageSourceSunlight.SUN : new DamageSourceSunlight(attacker));
          } else {
            entity.func_70645_a(new EntityDamageSource(DamageSource.field_76376_m.func_76355_l(), attacker));
          }
        }
      }
    }
  }
  
  public static boolean touchOfDeath(Entity victim, EntityLivingBase attacker, float damage) {
    if ((victim != null) && (victim.func_85032_ar())) {
      return false;
    }
    
    if ((victim != null) && (field_70170_p != null) && (!field_70170_p.field_72995_K)) {
      if ((victim instanceof EntityLiving)) {
        DamageSource source = new EntityDamageSource(DamageSource.field_76376_m.func_76355_l(), attacker);
        EntityLiving creature = (EntityLiving)victim;
        float cap = 10000.0F;
        if ((victim instanceof IHandleDT)) {
          cap = ((IHandleDT)victim).getCapDT(source, damage);
          if (cap <= 0.0F)
            return false;
          if ((attacker instanceof EntityLiving)) {
            cap = Math.min(6.0F, cap);
          }
        }
        

        creature.func_70097_a(source, 0.0F);
        creature.func_70606_j(Math.max(creature.func_110143_aJ() - Math.min(damage, cap), 0.0F));
        









        creature.func_70097_a(source, 0.0F);
      }
      else if ((victim instanceof EntityPlayer)) {
        EntityPlayer player = (EntityPlayer)victim;
        if (!field_71075_bZ.field_75098_d) {
          player.func_70606_j(Math.max(player.func_110143_aJ() - damage, 0.0F));
          


          if (player.func_110143_aJ() <= 0.0F) {
            if (attacker == null) {
              player.func_70645_a(DamageSource.field_76376_m);
            } else {
              player.func_70645_a(new EntityDamageSource(DamageSource.field_76376_m.func_76355_l(), attacker));
            }
          } else {
            player.func_70097_a(new EntityDamageSource(DamageSource.field_76376_m.func_76355_l(), attacker), 0.0F);
          }
        }
        else {
          return false;
        }
      }
    }
    return true;
  }
  
  public static boolean moveToBlockPositionAndUpdate(EntityLiving entity, int x, int y, int z, int maxDY) {
    World world = field_70170_p;
    boolean done = false;
    int mod = 0;
    int sign = -1;
    while ((!done) && (mod <= 2 * maxDY) && (y < 250) && (y > 2)) {
      if ((BlockUtil.isNormalCube(world.func_147439_a(x, y, z))) && (world.func_147437_c(x, y + 1, z)) && (world.func_147437_c(x, y + 2, z)))
      {

        done = true;
      } else {
        mod++;
        sign *= -1;
        y += mod * sign;
      }
    }
    
    if (done) {
      entity.func_70634_a(0.5D + x, 1.05D + y, 0.5D + z);
    }
    
    return done;
  }
}
