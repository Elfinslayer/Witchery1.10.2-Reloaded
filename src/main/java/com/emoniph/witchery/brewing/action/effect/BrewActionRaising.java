package com.emoniph.witchery.brewing.action.effect;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.brewing.AltarPower;
import com.emoniph.witchery.brewing.BrewItemKey;
import com.emoniph.witchery.brewing.BrewNamePart;
import com.emoniph.witchery.brewing.EffectLevel;
import com.emoniph.witchery.brewing.ModifiersEffect;
import com.emoniph.witchery.brewing.Probability;
import com.emoniph.witchery.brewing.action.BrewActionEffect;
import com.emoniph.witchery.brewing.potions.WitcheryPotions;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.EntityUtil;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.util.ParticleEffect;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BrewActionRaising extends BrewActionEffect
{
  public BrewActionRaising(Item axe, AltarPower powerCost, EffectLevel effectLevel)
  {
    super(new BrewItemKey(axe, 32767), new BrewNamePart("witchery:brew.raising"), powerCost, new Probability(1.0D), effectLevel);
  }
  


  protected void doApplyToBlock(World world, int posX, int posY, int posZ, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack stack)
  {
    raiseDead(world, new Coord(posX, posY, posZ, side), ritualised ? 0 : modifiers.getStrength(), caster, ritualised ? com.emoniph.witchery.util.TimeUtil.secsToTicks(10 * (modifiers.getStrength() + 1)) : 0);
  }
  


  public static void raiseDead(World world, Coord coord, int strength, EntityPlayer raiser, int lifetime)
  {
    int MAX_DISTANCE = 3;
    int MAX_DROP = 6;
    
    raiseUndead(world, coord, raiser, lifetime);
    
    int extraCount = 0;
    double chance = field_73012_v.nextDouble();
    if ((strength >= 1) && (field_73012_v.nextDouble() < strength * 0.5D)) {
      extraCount++;
    }
    
    if ((strength >= 2) && (field_73012_v.nextDouble() < strength * 0.25D)) {
      extraCount++;
    }
    
    if ((strength >= 3) && (field_73012_v.nextDouble() < strength * 0.25D)) {
      extraCount++;
    }
    
    for (int i = 0; i < extraCount; i++) {
      int x = x - 3 + field_73012_v.nextInt(6) + 1;
      int z = z - 3 + field_73012_v.nextInt(6) + 1;
      int dy = y + 6; for (int minY = y - 6; dy >= minY; dy--) {
        if ((world.func_147439_a(x, dy - 1, z).func_149688_o().func_76220_a()) && (world.func_147437_c(x, dy, z))) {
          raiseUndead(world, new Coord(x, dy, z), raiser, lifetime);
          break;
        }
      }
    }
  }
  
  private static void raiseUndead(World world, Coord coord, EntityPlayer thrower, int lifetime) {
    if (!field_72995_K) {
      EntityLiving undeadEntity = createUndeadCreature(world);
      undeadEntity.func_70012_b(0.5D + x, 0.1D + y, 0.5D + z, 0.0F, 0.0F);
      IEntityLivingData entitylivingData = null;
      entitylivingData = undeadEntity.func_110161_a(entitylivingData);
      EntityUtil.persistanceRequired(undeadEntity);
      EntityUtil.setNoDrops(undeadEntity);
      if (lifetime > 0) {
        undeadEntity.func_70690_d(new PotionEffect(PotionsMORTAL_COIL.field_76415_H, lifetime));
      }
      
      if (thrower != null) {
        try {
          com.emoniph.witchery.brewing.potions.PotionEnslaved.setEnslaverForMob(undeadEntity, thrower);
        } catch (Exception e) {
          Log.instance().warning(e, "Unhandled exception occurred setting enslaver from raiseUnded potion.");
        }
      }
      

      world.func_72838_d(undeadEntity);
      ParticleEffect.LARGE_SMOKE.send(com.emoniph.witchery.util.SoundEffect.NONE, undeadEntity, 0.5D, 2.0D, 16);
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
}
