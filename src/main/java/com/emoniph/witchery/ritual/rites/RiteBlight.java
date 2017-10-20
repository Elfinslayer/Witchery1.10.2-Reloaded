package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.util.Log;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class RiteBlight extends RiteExpandingEffect
{
  public RiteBlight(int radius, int height)
  {
    super(radius, height, true);
  }
  
  public boolean doRadiusAction(World world, int posX, int posY, int posZ, int radius, EntityPlayer player, boolean enhanced)
  {
    double radiusSq = radius * radius;
    double minSq = Math.max(0, (radius - 1) * (radius - 1));
    for (Object obj : field_73010_i) {
      EntityPlayer victim = (EntityPlayer)obj;
      double distanceSq = victim.func_70092_e(0.5D + posX, 0.5D + posY, 0.5D + posZ);
      if ((distanceSq > minSq) && (distanceSq <= radiusSq)) {
        if (ItemsPOPPET.voodooProtectionActivated(player, null, victim, 6))
          return false;
        if (!victim.func_70644_a(Potion.field_76431_k)) {
          victim.func_70690_d(new PotionEffect(field_76431_kfield_76415_H, 2400, 1));
        }
      }
    }
    
    ArrayList<EntityVillager> villagersToZombify = new ArrayList();
    
    ArrayList<EntityCow> cowsToSchroom = new ArrayList();
    
    ArrayList<EntityAnimal> animalsToSlay = new ArrayList();
    
    for (Object obj : field_72996_f) {
      if ((obj instanceof EntityVillager)) {
        EntityVillager victim = (EntityVillager)obj;
        double distanceSq = victim.func_70092_e(0.5D + posX, 0.5D + posY, 0.5D + posZ);
        if ((distanceSq > minSq) && (distanceSq <= radiusSq)) {
          Log.instance().debug(String.format("Try Adding zombie %f %f %f", new Object[] { Double.valueOf(distanceSq), Double.valueOf(minSq), Double.valueOf(radiusSq) }));
          if (field_73012_v.nextInt(10) == 0) {
            Log.instance().debug("Added zombie");
            villagersToZombify.add(victim);
          }
          
        }
      }
      else if ((obj instanceof EntityCow)) {
        EntityCow victim = (EntityCow)obj;
        double distanceSq = victim.func_70092_e(0.5D + posX, 0.5D + posY, 0.5D + posZ);
        if ((distanceSq > minSq) && (distanceSq <= radiusSq)) {
          Log.instance().debug(String.format("Try Adding mooschroom %f %f %f", new Object[] { Double.valueOf(distanceSq), Double.valueOf(minSq), Double.valueOf(radiusSq) }));
          if (field_73012_v.nextInt(20) == 0) {
            Log.instance().debug("Added mooschroom");
            cowsToSchroom.add(victim);
          } else if (field_73012_v.nextInt(3) == 0) {
            animalsToSlay.add(victim);
          }
        }
      } else if ((obj instanceof EntityAnimal)) {
        EntityAnimal victim = (EntityAnimal)obj;
        double distanceSq = victim.func_70092_e(0.5D + posX, 0.5D + posY, 0.5D + posZ);
        if ((distanceSq > minSq) && (distanceSq <= radiusSq) && 
          (field_73012_v.nextInt(3) == 0)) {
          animalsToSlay.add(victim);
        }
      }
    }
    

    for (EntityVillager zombieWannabe : villagersToZombify) {
      EntityZombie entityzombie = new EntityZombie(world);
      entityzombie.func_82149_j(zombieWannabe);
      world.func_72900_e(zombieWannabe);
      entityzombie.func_110161_a((IEntityLivingData)null);
      entityzombie.func_82229_g(true);
      if (zombieWannabe.func_70631_g_()) {
        entityzombie.func_82227_f(true);
      }
      world.func_72838_d(entityzombie);
      world.func_72889_a((EntityPlayer)null, 1016, (int)field_70165_t, (int)field_70163_u, (int)field_70161_v, 0);
    }
    
    for (EntityCow zombieWannabe : cowsToSchroom) {
      EntityMooshroom entityzombie = new EntityMooshroom(world);
      entityzombie.func_82149_j(zombieWannabe);
      world.func_72900_e(zombieWannabe);
      entityzombie.func_110161_a((IEntityLivingData)null);
      world.func_72838_d(entityzombie);
      world.func_72889_a((EntityPlayer)null, 1016, (int)field_70165_t, (int)field_70163_u, (int)field_70161_v, 0);
    }
    
    for (EntityAnimal animal : animalsToSlay) {
      animal.func_70097_a(DamageSource.field_76376_m, 20.0F);
    }
    
    return true;
  }
  
  public void doBlockAction(World world, int posX, int posY, int posZ, int currentRadius, EntityPlayer player, boolean enhanced)
  {
    if (!field_72995_K) {
      Block blockID = world.func_147439_a(posX, posY, posZ);
      Block blockBelowID = world.func_147439_a(posX, posY - 1, posZ);
      if (blockID == Blocks.field_150329_H) {
        world.func_147468_f(posX, posY, posZ);
        blightGround(world, posX, posY - 1, posZ, blockBelowID, enhanced);
      } else if ((blockID == Blocks.field_150328_O) || (blockID == Blocks.field_150327_N) || (blockID == Blocks.field_150459_bM) || (blockID == Blocks.field_150464_aj) || (blockID == Blocks.field_150469_bN) || (blockID == Blocks.field_150393_bb) || (blockID == Blocks.field_150394_bc) || (blockID == Blocks.field_150440_ba) || (blockID == Blocks.field_150423_aK))
      {

        world.func_147449_b(posX, posY, posZ, Blocks.field_150330_I);
        blightGround(world, posX, posY - 1, posZ, blockBelowID, enhanced);
      } else if (blockID == Blocks.field_150458_ak) {
        world.func_147449_b(posX, posY, posZ, Blocks.field_150354_m);
      } else if (blockID.func_149688_o().func_76220_a()) {
        blightGround(world, posX, posY, posZ, blockID, enhanced);
      } else if (blockBelowID.func_149688_o().func_76220_a()) {
        blightGround(world, posX, posY - 1, posZ, blockBelowID, enhanced);
      }
    }
  }
  
  public void blightGround(World world, int posX, int posY, int posZ, Block blockBelowID, boolean enhanced) {
    if ((blockBelowID == Blocks.field_150346_d) || (blockBelowID == Blocks.field_150349_c) || (blockBelowID == Blocks.field_150391_bh) || (blockBelowID == Blocks.field_150458_ak))
    {
      int rand = field_73012_v.nextInt(enhanced ? 4 : 5);
      if (rand == 0) {
        world.func_147449_b(posX, posY, posZ, Blocks.field_150354_m);
      } else if (rand == 1) {
        world.func_147449_b(posX, posY, posZ, Blocks.field_150346_d);
      }
    }
  }
}
