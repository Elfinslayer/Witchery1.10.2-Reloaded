package com.emoniph.witchery.integration;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.blocks.BlockWitchCrop;
import com.emoniph.witchery.entity.EntityBabaYaga;
import com.emoniph.witchery.entity.EntityBanshee;
import com.emoniph.witchery.entity.EntityCorpse;
import com.emoniph.witchery.entity.EntityCovenWitch;
import com.emoniph.witchery.entity.EntityDeath;
import com.emoniph.witchery.entity.EntityDeathsHorse;
import com.emoniph.witchery.entity.EntityDemon;
import com.emoniph.witchery.entity.EntityEnt;
import com.emoniph.witchery.entity.EntityFamiliar;
import com.emoniph.witchery.entity.EntityFollower;
import com.emoniph.witchery.entity.EntityGoblinGulg;
import com.emoniph.witchery.entity.EntityGoblinMog;
import com.emoniph.witchery.entity.EntityHornedHuntsman;
import com.emoniph.witchery.entity.EntityIllusionCreeper;
import com.emoniph.witchery.entity.EntityIllusionSpider;
import com.emoniph.witchery.entity.EntityIllusionZombie;
import com.emoniph.witchery.entity.EntityLeonard;
import com.emoniph.witchery.entity.EntityLilith;
import com.emoniph.witchery.entity.EntityLordOfTorment;
import com.emoniph.witchery.entity.EntityLostSoul;
import com.emoniph.witchery.entity.EntityMindrake;
import com.emoniph.witchery.entity.EntityNightmare;
import com.emoniph.witchery.entity.EntityPoltergeist;
import com.emoniph.witchery.entity.EntityReflection;
import com.emoniph.witchery.entity.EntitySpectre;
import com.emoniph.witchery.entity.EntitySpirit;
import com.emoniph.witchery.entity.EntityWitchHunter;
import com.emoniph.witchery.util.Log;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.RegistryNamespaced;
import powercrystals.minefactoryreloaded.api.FactoryRegistry;

public class ModHookMineFactoryReloaded extends ModHook
{
  public ModHookMineFactoryReloaded() {}
  
  public String getModID()
  {
    return "MineFactoryReloaded";
  }
  

  protected void doInit() {}
  

  protected void doPostInit() {}
  

  protected void doReduceMagicPower(EntityLivingBase entity, float factor) {}
  
  public static class IntegrateMineFactoryReloaded
  {
    public IntegrateMineFactoryReloaded() {}
    
    private static NBTTagCompound getFertilizableCrop(BlockWitchCrop crop)
    {
      NBTTagCompound nbtRoot = new NBTTagCompound();
      nbtRoot.func_74778_a("plant", Block.field_149771_c.func_148750_c(crop));
      nbtRoot.func_74768_a("meta", crop.getNumGrowthStages());
      return nbtRoot;
    }
    
    private static NBTTagCompound getPlantableCrop(BlockWitchCrop crop, Item seeds) {
      NBTTagCompound nbtRoot = new NBTTagCompound();
      nbtRoot.func_74778_a("seed", Item.field_150901_e.func_148750_c(seeds));
      nbtRoot.func_74778_a("crop", Block.field_149771_c.func_148750_c(crop));
      return nbtRoot;
    }
    
    private static NBTTagCompound getPlantableSapling(Block sapling) {
      NBTTagCompound nbtRoot = new NBTTagCompound();
      nbtRoot.func_74778_a("sapling", Block.field_149771_c.func_148750_c(sapling));
      return nbtRoot;
    }
    
    private static NBTTagCompound getFertilizableSapling(Block sapling) {
      NBTTagCompound nbtRoot = new NBTTagCompound();
      nbtRoot.func_74778_a("plant", Block.field_149771_c.func_148750_c(sapling));
      return nbtRoot;
    }
    
    public static void register()
    {
      FactoryRegistry.sendMessage("registerHarvestable_Crop", new ItemStack(BlocksCROP_ARTICHOKE, 1, BlocksCROP_ARTICHOKE.getNumGrowthStages()));
      
      FactoryRegistry.sendMessage("registerHarvestable_Crop", new ItemStack(BlocksCROP_MANDRAKE, 1, BlocksCROP_MANDRAKE.getNumGrowthStages()));
      
      FactoryRegistry.sendMessage("registerHarvestable_Crop", new ItemStack(BlocksCROP_BELLADONNA, 1, BlocksCROP_BELLADONNA.getNumGrowthStages()));
      
      FactoryRegistry.sendMessage("registerHarvestable_Crop", new ItemStack(BlocksCROP_SNOWBELL, 1, BlocksCROP_SNOWBELL.getNumGrowthStages()));
      
      FactoryRegistry.sendMessage("registerHarvestable_Crop", new ItemStack(BlocksCROP_WORMWOOD, 1, BlocksCROP_WORMWOOD.getNumGrowthStages()));
      
      FactoryRegistry.sendMessage("registerHarvestable_Crop", new ItemStack(BlocksCROP_MINDRAKE, 1, BlocksCROP_MINDRAKE.getNumGrowthStages()));
      
      FactoryRegistry.sendMessage("registerHarvestable_Crop", new ItemStack(BlocksCROP_WOLFSBANE, 1, BlocksCROP_WOLFSBANE.getNumGrowthStages()));
      
      FactoryRegistry.sendMessage("registerHarvestable_Crop", new ItemStack(BlocksCROP_GARLIC, 1, BlocksCROP_GARLIC.getNumGrowthStages()));
      

      FactoryRegistry.sendMessage("registerHarvestable_Log", Block.field_149771_c.func_148750_c(BlocksLOG));
      FactoryRegistry.sendMessage("registerHarvestable_Leaves", Block.field_149771_c.func_148750_c(BlocksLEAVES));
      
      FactoryRegistry.sendMessage("registerFertilizable_Crop", getFertilizableCrop(BlocksCROP_ARTICHOKE));
      FactoryRegistry.sendMessage("registerFertilizable_Crop", getFertilizableCrop(BlocksCROP_MANDRAKE));
      FactoryRegistry.sendMessage("registerFertilizable_Crop", getFertilizableCrop(BlocksCROP_BELLADONNA));
      FactoryRegistry.sendMessage("registerFertilizable_Crop", getFertilizableCrop(BlocksCROP_SNOWBELL));
      FactoryRegistry.sendMessage("registerFertilizable_Crop", getFertilizableCrop(BlocksCROP_WORMWOOD));
      FactoryRegistry.sendMessage("registerFertilizable_Crop", getFertilizableCrop(BlocksCROP_MINDRAKE));
      FactoryRegistry.sendMessage("registerFertilizable_Crop", getFertilizableCrop(BlocksCROP_WOLFSBANE));
      FactoryRegistry.sendMessage("registerFertilizable_Crop", getFertilizableCrop(BlocksCROP_GARLIC));
      
      FactoryRegistry.sendMessage("registerFertilizable_Standard", getFertilizableSapling(BlocksSAPLING));
      
      FactoryRegistry.sendMessage("registerPlantable_Crop", getPlantableCrop(BlocksCROP_ARTICHOKE, ItemsSEEDS_ARTICHOKE));
      
      FactoryRegistry.sendMessage("registerPlantable_Crop", getPlantableCrop(BlocksCROP_MANDRAKE, ItemsSEEDS_MANDRAKE));
      
      FactoryRegistry.sendMessage("registerPlantable_Crop", getPlantableCrop(BlocksCROP_BELLADONNA, ItemsSEEDS_BELLADONNA));
      
      FactoryRegistry.sendMessage("registerPlantable_Crop", getPlantableCrop(BlocksCROP_SNOWBELL, ItemsSEEDS_SNOWBELL));
      
      FactoryRegistry.sendMessage("registerPlantable_Crop", getPlantableCrop(BlocksCROP_WORMWOOD, ItemsSEEDS_WORMWOOD));
      
      FactoryRegistry.sendMessage("registerPlantable_Crop", getPlantableCrop(BlocksCROP_MINDRAKE, ItemsSEEDS_MINDRAKE));
      
      FactoryRegistry.sendMessage("registerPlantable_Crop", getPlantableCrop(BlocksCROP_WOLFSBANE, ItemsSEEDS_WOLFSBANE));
      
      FactoryRegistry.sendMessage("registerPlantable_Crop", getPlantableCrop(BlocksCROP_GARLIC, ItemsSEEDS_GARLIC));
      

      FactoryRegistry.sendMessage("registerPlantable_Sapling", getPlantableSapling(BlocksSAPLING));
      try
      {
        FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityCovenWitch.class);
        FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityNightmare.class);
        FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityDemon.class);
        FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityEnt.class);
        FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityBabaYaga.class);
        FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityHornedHuntsman.class);
        FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityIllusionSpider.class);
        FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityIllusionZombie.class);
        FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityIllusionCreeper.class);
        FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityFamiliar.class);
        FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityCorpse.class);
        FactoryRegistry.sendMessage("registerGrinderBlacklist", EntitySpirit.class);
        FactoryRegistry.sendMessage("registerGrinderBlacklist", EntitySpectre.class);
        FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityPoltergeist.class);
        FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityBanshee.class);
        FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityDeath.class);
        FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityWitchHunter.class);
        FactoryRegistry.sendMessage("registerGrinderBlacklist", com.emoniph.witchery.entity.EntityImp.class);
        FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityLordOfTorment.class);
        FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityGoblinMog.class);
        FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityGoblinGulg.class);
        FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityDeathsHorse.class);
        FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityLeonard.class);
        FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityLostSoul.class);
        FactoryRegistry.sendMessage("registerGrinderBlacklist", com.emoniph.witchery.entity.EntityVampire.class);
        FactoryRegistry.sendMessage("registerGrinderBlacklist", com.emoniph.witchery.entity.EntityWolfman.class);
        FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityLilith.class);
        FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityFollower.class);
        FactoryRegistry.sendMessage("registerGrinderBlacklist", EntityReflection.class);
      } catch (Throwable e) {
        Log.instance().warning(e, "Exception occurred setting up MFR grinder blacklist");
      }
      try
      {
        FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityCovenWitch.class);
        FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityNightmare.class);
        FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityDemon.class);
        FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityEnt.class);
        FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityBabaYaga.class);
        FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityHornedHuntsman.class);
        FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityIllusionCreeper.class);
        FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityIllusionSpider.class);
        FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityIllusionZombie.class);
        FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityFamiliar.class);
        FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityCorpse.class);
        FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntitySpirit.class);
        FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntitySpectre.class);
        FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityBanshee.class);
        FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityPoltergeist.class);
        FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityDeath.class);
        FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityLordOfTorment.class);
        FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityGoblinMog.class);
        FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityGoblinGulg.class);
        FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityDeathsHorse.class);
        FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityLeonard.class);
        FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityLostSoul.class);
        FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityLilith.class);
        FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityFollower.class);
        FactoryRegistry.sendMessage("registerSafariNetBlacklist", EntityReflection.class);
      } catch (Throwable e) {
        Log.instance().warning(e, "Exception occurred setting up MFR safari net blacklist");
      }
      
      try
      {
        FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityCovenWitch.class);
        FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityNightmare.class);
        FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityDemon.class);
        FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityEnt.class);
        FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityBabaYaga.class);
        FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityHornedHuntsman.class);
        FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityIllusionCreeper.class);
        FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityIllusionSpider.class);
        FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityIllusionZombie.class);
        FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityFamiliar.class);
        FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntitySpirit.class);
        FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntitySpectre.class);
        FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityBanshee.class);
        FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityPoltergeist.class);
        FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityDeath.class);
        FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityWitchHunter.class);
        FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityMindrake.class);
        FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", com.emoniph.witchery.entity.EntityImp.class);
        FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityLordOfTorment.class);
        FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityMindrake.class);
        FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityDeathsHorse.class);
        FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", com.emoniph.witchery.entity.EntityGoblin.class);
        FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityGoblinGulg.class);
        FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityGoblinMog.class);
        FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityLeonard.class);
        FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityLostSoul.class);
        FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", com.emoniph.witchery.entity.EntityWolfman.class);
        FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", com.emoniph.witchery.entity.EntityVampire.class);
        FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityLilith.class);
        FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityFollower.class);
        FactoryRegistry.sendMessage("registerAutoSpawnerBlacklist", EntityReflection.class);
      }
      catch (Throwable e) {
        Log.instance().warning(e, "Exception occurred setting up MFR autospawner blacklist");
      }
    }
  }
}
