package com.emoniph.witchery.integration;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import com.emoniph.witchery.item.ItemPoppet.PoppetType;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftApi.EntityTagsNBT;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class ModHookThaumcraft4 extends ModHook
{
  public ModHookThaumcraft4() {}
  
  public String getModID()
  {
    return "Thaumcraft";
  }
  
  protected void doInit()
  {
    cpw.mods.fml.common.event.FMLInterModComms.sendMessage(getModID(), "harvestStandardCrop", new ItemStack(BlocksCROP_ARTICHOKE, 1, BlocksCROP_ARTICHOKE.getNumGrowthStages()));
    
    cpw.mods.fml.common.event.FMLInterModComms.sendMessage(getModID(), "harvestStandardCrop", new ItemStack(BlocksCROP_BELLADONNA, 1, BlocksCROP_BELLADONNA.getNumGrowthStages()));
    
    cpw.mods.fml.common.event.FMLInterModComms.sendMessage(getModID(), "harvestStandardCrop", new ItemStack(BlocksCROP_MANDRAKE, 1, BlocksCROP_MANDRAKE.getNumGrowthStages()));
    
    cpw.mods.fml.common.event.FMLInterModComms.sendMessage(getModID(), "harvestStandardCrop", new ItemStack(BlocksCROP_SNOWBELL, 1, BlocksCROP_SNOWBELL.getNumGrowthStages()));
    
    cpw.mods.fml.common.event.FMLInterModComms.sendMessage(getModID(), "harvestStandardCrop", new ItemStack(BlocksCROP_WORMWOOD, 1, BlocksCROP_WORMWOOD.getNumGrowthStages()));
    
    cpw.mods.fml.common.event.FMLInterModComms.sendMessage(getModID(), "harvestStandardCrop", new ItemStack(BlocksCROP_MINDRAKE, 1, BlocksCROP_MINDRAKE.getNumGrowthStages()));
    

    cpw.mods.fml.common.event.FMLInterModComms.sendMessage(getModID(), "harvestStandardCrop", new ItemStack(BlocksCROP_WOLFSBANE, 1, BlocksCROP_WOLFSBANE.getNumGrowthStages()));
    

    cpw.mods.fml.common.event.FMLInterModComms.sendMessage("Thaumcraft", "dimensionBlacklist", "" + instancedimensionDreamID + ":0");
    
    cpw.mods.fml.common.event.FMLInterModComms.sendMessage("Thaumcraft", "dimensionBlacklist", "" + instancedimensionTormentID + ":0");
  }
  



  protected void doPostInit() {}
  



  protected void doReduceMagicPower(net.minecraft.entity.EntityLivingBase entity, float factor) { IntegrateThaumcraft.reduceMagicPower(entity, factor); }
  
  private static class IntegrateThaumcraft {
    private IntegrateThaumcraft() {}
    
    public static void reduceMagicPower(net.minecraft.entity.EntityLivingBase entity, float percentage) { if ((entity instanceof net.minecraft.entity.player.EntityPlayer)) {
        net.minecraft.entity.player.EntityPlayer player = (net.minecraft.entity.player.EntityPlayer)entity;
        for (java.util.Iterator i$ = Aspect.aspects.values().iterator(); i$.hasNext(); 
            


            goto 83)
        {
          Aspect aspect = (Aspect)i$.next();
          AspectList list = new AspectList().add(aspect, 100);
          int countdown = percentage == 1.0F ? 1000 : (int)Math.max(150.0F * percentage, 1.0F);
          if ((thaumcraft.api.ThaumcraftApiHelper.consumeVisFromInventory(player, list)) && (countdown > 0)) {
            countdown--;
          }
        }
      }
    }
    

    public static void registerAspects()
    {
      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksWICKER_BUNDLE), new int[] { 0, 4, 8 }, new AspectList().add(Aspect.TREE, 2));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksWICKER_BUNDLE), new int[] { 1, 5, 9 }, new AspectList().add(Aspect.TREE, 2).add(Aspect.FLESH, 2).add(Aspect.DEATH, 3).add(Aspect.FIRE, 1));
      




      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksSTOCKADE), new int[] { -1 }, new AspectList().add(Aspect.TREE, 2));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksSTATUE_OF_WORSHIP), new int[] { -1 }, new AspectList().add(Aspect.EARTH, 3).add(Aspect.MIND, 3).add(Aspect.SOUL, 2).add(Aspect.MAGIC, 2));
      




      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksLOG), new int[] { -1 }, new AspectList().add(Aspect.TREE, 2).add(Aspect.MAGIC, 1));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksPLANKS), new int[] { -1 }, new AspectList().add(Aspect.TREE, 1));
      







      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksSTAIRS_ALDER), new int[] { -1 }, new AspectList().add(Aspect.TREE, 1));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksSTAIRS_HAWTHORN), new int[] { -1 }, new AspectList().add(Aspect.TREE, 1));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksSTAIRS_ROWAN), new int[] { -1 }, new AspectList().add(Aspect.TREE, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksSAPLING), new int[] { 0 }, new AspectList().add(Aspect.PLANT, 1).add(Aspect.MAGIC, 1).add(Aspect.PLANT, 1));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksSAPLING), new int[] { 1 }, new AspectList().add(Aspect.PLANT, 1).add(Aspect.ENTROPY, 1).add(Aspect.PLANT, 1));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksSAPLING), new int[] { 2 }, new AspectList().add(Aspect.PLANT, 1).add(Aspect.ORDER, 1).add(Aspect.PLANT, 1));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksLEAVES), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksDOOR_ALDER), new int[] { -1 }, new AspectList().add(Aspect.TREE, 2).add(Aspect.MECHANISM, 2).add(Aspect.MOTION, 1).add(Aspect.TRAP, 1));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksDOOR_ROWAN), new int[] { -1 }, new AspectList().add(Aspect.TREE, 2).add(Aspect.MECHANISM, 2).add(Aspect.MOTION, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemRowanBerries.damageValue }, new AspectList().add(Aspect.PLANT, 1).add(Aspect.HUNGER, 1));
      


      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksALTAR), new int[] { -1 }, new AspectList().add(Aspect.MAGIC, 3).add(Aspect.EARTH, 4).add(Aspect.MECHANISM, 3).add(Aspect.ENERGY, 3));
      




      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksDISTILLERY_IDLE), new int[] { -1 }, new AspectList().add(Aspect.MAGIC, 2).add(Aspect.METAL, 8).add(Aspect.MECHANISM, 3).add(Aspect.CRYSTAL, 4).add(Aspect.GREED, 4).add(Aspect.ENERGY, 4).add(Aspect.AIR, 2).add(Aspect.WATER, 2).add(Aspect.FIRE, 2));
      




      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksDISTILLERY_BURNING), new int[] { -1 }, new AspectList().add(Aspect.MAGIC, 2).add(Aspect.METAL, 8).add(Aspect.MECHANISM, 3).add(Aspect.CRYSTAL, 4).add(Aspect.GREED, 4).add(Aspect.ENERGY, 4).add(Aspect.AIR, 2).add(Aspect.WATER, 2).add(Aspect.FIRE, 2));
      





      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksOVEN_IDLE), new int[] { -1 }, new AspectList().add(Aspect.METAL, 14).add(Aspect.MECHANISM, 3).add(Aspect.FIRE, 1).add(Aspect.AIR, 2));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksOVEN_BURNING), new int[] { -1 }, new AspectList().add(Aspect.METAL, 14).add(Aspect.MECHANISM, 3).add(Aspect.FIRE, 3).add(Aspect.AIR, 2));
      




      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksSPINNING_WHEEL), new int[] { -1 }, new AspectList().add(Aspect.TREE, 2).add(Aspect.MECHANISM, 3).add(Aspect.CLOTH, 3));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksCROP_BELLADONNA), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 2).add(Aspect.POISON, 4).add(Aspect.DEATH, 4).add(Aspect.CROP, 1));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksCROP_MANDRAKE), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 2).add(Aspect.MAN, 1).add(Aspect.CROP, 1));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksCROP_ARTICHOKE), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 2).add(Aspect.WATER, 2).add(Aspect.CROP, 1));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksCROP_SNOWBELL), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 2).add(Aspect.COLD, 2).add(Aspect.CROP, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemSeedsTreefyd.damageValue }, new AspectList().add(Aspect.PLANT, 4).add(Aspect.MAGIC, 1));
      


      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsSEEDS_BELLADONNA), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 1).add(Aspect.POISON, 1));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsSEEDS_MANDRAKE), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 1).add(Aspect.EARTH, 1));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsSEEDS_ARTICHOKE), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 1).add(Aspect.WATER, 1));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsSEEDS_SNOWBELL), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 1).add(Aspect.COLD, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksKETTLE), new int[] { -1 }, new AspectList().add(Aspect.METAL, 6).add(Aspect.CRYSTAL, 4).add(Aspect.GREED, 4).add(Aspect.ENERGY, 4).add(Aspect.MAGIC, 2).add(Aspect.WATER, 4).add(Aspect.CRAFT, 8));
      




      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksCAULDRON), new int[] { -1 }, new AspectList().add(Aspect.METAL, 6).add(Aspect.CRYSTAL, 4).add(Aspect.GREED, 4).add(Aspect.ENERGY, 4).add(Aspect.MAGIC, 2).add(Aspect.WATER, 4).add(Aspect.CRAFT, 8));
      




      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksPOPPET_SHELF), new int[] { -1 }, new AspectList().add(Aspect.CRYSTAL, 6).add(Aspect.GREED, 6).add(Aspect.ENERGY, 4).add(Aspect.MAGIC, 4).add(Aspect.SOUL, 4));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksSPANISH_MOSS), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 1).add(Aspect.SOUL, 1));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksLEAPING_LILY), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 2).add(Aspect.WATER, 1).add(Aspect.MOTION, 3).add(Aspect.FLIGHT, 3));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksEMBER_MOSS), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 1).add(Aspect.FIRE, 2));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksGLINT_WEED), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 1).add(Aspect.FIRE, 1));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksGLOW_GLOBE), new int[] { -1 }, new AspectList().add(Aspect.FIRE, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksALLURING_SKULL), new int[] { -1 }, new AspectList().add(Aspect.DEATH, 4).add(Aspect.SOUL, 4).add(Aspect.UNDEAD, 6).add(Aspect.GREED, 4));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsWITCH_HAND), new int[] { -1 }, new AspectList().add(Aspect.TOOL, 1).add(Aspect.FLESH, 1).add(Aspect.MAGIC, 2));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsARTHANA), new int[] { -1 }, new AspectList().add(Aspect.GREED, 6).add(Aspect.METAL, 2).add(Aspect.CRYSTAL, 2).add(Aspect.WEAPON, 1));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsHUNTSMANS_SPEAR), new int[] { -1 }, new AspectList().add(Aspect.TREE, 3).add(Aspect.MAGIC, 2).add(Aspect.WEAPON, 2));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemMysticUnguent.damageValue }, new AspectList().add(Aspect.WATER, 2).add(Aspect.ENERGY, 2).add(Aspect.MAGIC, 2).add(Aspect.ENTROPY, 10));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBranchEnt.damageValue }, new AspectList().add(Aspect.TREE, 2).add(Aspect.MAGIC, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsMYSTIC_BRANCH), new int[] { -1 }, new AspectList().add(Aspect.TOOL, 1).add(Aspect.TREE, 2).add(Aspect.MAGIC, 3).add(Aspect.ENTROPY, 10));
      




      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksCIRCLE), new int[] { -1 }, new AspectList().add(Aspect.MECHANISM, 1).add(Aspect.MAGIC, 3).add(Aspect.ORDER, 1));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksGLYPH_RITUAL), new int[] { -1 }, new AspectList().add(Aspect.MECHANISM, 1).add(Aspect.MAGIC, 1).add(Aspect.ORDER, 1));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksGLYPH_OTHERWHERE), new int[] { -1 }, new AspectList().add(Aspect.MECHANISM, 1).add(Aspect.MAGIC, 1).add(Aspect.ORDER, 1).add(Aspect.ELDRITCH, 4).add(Aspect.TRAVEL, 4));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksGLYPH_INFERNAL), new int[] { -1 }, new AspectList().add(Aspect.MECHANISM, 1).add(Aspect.MAGIC, 1).add(Aspect.ORDER, 1).add(Aspect.FIRE, 2).add(Aspect.TRAVEL, 4));
      




      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsDIVINER_WATER), new int[] { -1 }, new AspectList().add(Aspect.TOOL, 2).add(Aspect.SENSES, 2).add(Aspect.WATER, 4).add(Aspect.MAGIC, 1));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsDIVINER_LAVA), new int[] { -1 }, new AspectList().add(Aspect.TOOL, 2).add(Aspect.SENSES, 2).add(Aspect.FIRE, 2).add(Aspect.EARTH, 2).add(Aspect.MAGIC, 1));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsTAGLOCK_KIT), new int[] { -1 }, new AspectList().add(Aspect.TOOL, 1).add(Aspect.BEAST, 1).add(Aspect.MAN, 1).add(Aspect.LIFE, 1).add(Aspect.ORDER, 1));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsPOPPET), new int[] { ItemsPOPPET.unboundPoppet.damageValue }, new AspectList().add(Aspect.CLOTH, 4).add(Aspect.PLANT, 2).add(Aspect.SOUL, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsPOPPET), new int[] { ItemsPOPPET.earthPoppet.damageValue }, new AspectList().add(Aspect.CLOTH, 4).add(Aspect.PLANT, 2).add(Aspect.SOUL, 1).add(Aspect.ARMOR, 1).add(Aspect.EARTH, 1).add(Aspect.FLIGHT, 1));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsPOPPET), new int[] { ItemsPOPPET.waterPoppet.damageValue }, new AspectList().add(Aspect.CLOTH, 4).add(Aspect.PLANT, 2).add(Aspect.SOUL, 1).add(Aspect.ARMOR, 1).add(Aspect.WATER, 1).add(Aspect.AIR, 1));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsPOPPET), new int[] { ItemsPOPPET.firePoppet.damageValue }, new AspectList().add(Aspect.CLOTH, 4).add(Aspect.PLANT, 2).add(Aspect.SOUL, 1).add(Aspect.ARMOR, 1).add(Aspect.FIRE, 1).add(Aspect.WATER, 1));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsPOPPET), new int[] { ItemsPOPPET.foodPoppet.damageValue }, new AspectList().add(Aspect.CLOTH, 4).add(Aspect.PLANT, 2).add(Aspect.SOUL, 1).add(Aspect.ARMOR, 1).add(Aspect.HUNGER, 1).add(Aspect.LIFE, 1));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsPOPPET), new int[] { ItemsPOPPET.toolPoppet.damageValue }, new AspectList().add(Aspect.CLOTH, 4).add(Aspect.PLANT, 2).add(Aspect.SOUL, 1).add(Aspect.ARMOR, 1).add(Aspect.TOOL, 1).add(Aspect.CRAFT, 1));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsPOPPET), new int[] { ItemsPOPPET.armorPoppet.damageValue }, new AspectList().add(Aspect.CLOTH, 4).add(Aspect.PLANT, 2).add(Aspect.SOUL, 1).add(Aspect.ARMOR, 2).add(Aspect.CRAFT, 1));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsPOPPET), new int[] { ItemsPOPPET.deathPoppet.damageValue }, new AspectList().add(Aspect.CLOTH, 4).add(Aspect.PLANT, 2).add(Aspect.SOUL, 1).add(Aspect.ARMOR, 1).add(Aspect.DEATH, 1).add(Aspect.HEAL, 1));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsPOPPET), new int[] { ItemsPOPPET.antiVoodooPoppet.damageValue }, new AspectList().add(Aspect.CLOTH, 4).add(Aspect.PLANT, 2).add(Aspect.SOUL, 3).add(Aspect.ARMOR, 1));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsPOPPET), new int[] { ItemsPOPPET.voodooPoppet.damageValue }, new AspectList().add(Aspect.CLOTH, 4).add(Aspect.PLANT, 2).add(Aspect.SOUL, 4).add(Aspect.MOTION, 1));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsPOPPET), new int[] { ItemsPOPPET.vampiricPoppet.damageValue }, new AspectList().add(Aspect.CLOTH, 4).add(Aspect.PLANT, 2).add(Aspect.SOUL, 1).add(Aspect.LIFE, 1).add(Aspect.DEATH, 1).add(Aspect.EXCHANGE, 1));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemCandelabra.damageValue }, new AspectList().add(Aspect.METAL, 6).add(Aspect.CRYSTAL, 4).add(Aspect.GREED, 6).add(Aspect.ENERGY, 4).add(Aspect.MAGIC, 2).add(Aspect.LIGHT, 4));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksCANDELABRA), new int[] { -1 }, new AspectList().add(Aspect.METAL, 6).add(Aspect.CRYSTAL, 4).add(Aspect.GREED, 6).add(Aspect.ENERGY, 4).add(Aspect.MAGIC, 2).add(Aspect.LIGHT, 4));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemChaliceEmpty.damageValue }, new AspectList().add(Aspect.METAL, 6).add(Aspect.CRYSTAL, 4).add(Aspect.GREED, 6).add(Aspect.ENERGY, 4).add(Aspect.MAGIC, 2).add(Aspect.VOID, 2));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksCHALICE), new int[] { -1 }, new AspectList().add(Aspect.METAL, 6).add(Aspect.CRYSTAL, 4).add(Aspect.GREED, 6).add(Aspect.ENERGY, 4).add(Aspect.MAGIC, 2).add(Aspect.VOID, 2));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemChaliceFull.damageValue }, new AspectList().add(Aspect.METAL, 6).add(Aspect.CRYSTAL, 4).add(Aspect.GREED, 6).add(Aspect.ENERGY, 6).add(Aspect.MAGIC, 4).add(Aspect.VOID, 2).add(Aspect.WATER, 2));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemDreamMove.damageValue }, new AspectList().add(Aspect.AIR, 1).add(Aspect.SENSES, 1).add(Aspect.SOUL, 2).add(Aspect.DARKNESS, 1).add(Aspect.MOTION, 2));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemDreamDig.damageValue }, new AspectList().add(Aspect.AIR, 1).add(Aspect.SENSES, 1).add(Aspect.SOUL, 2).add(Aspect.DARKNESS, 1).add(Aspect.ENERGY, 2));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemDreamEat.damageValue }, new AspectList().add(Aspect.AIR, 1).add(Aspect.SENSES, 1).add(Aspect.SOUL, 2).add(Aspect.DARKNESS, 1).add(Aspect.HUNGER, 2));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemDreamIntensity.damageValue }, new AspectList().add(Aspect.AIR, 1).add(Aspect.SENSES, 1).add(Aspect.SOUL, 2).add(Aspect.DARKNESS, 1).add(Aspect.MIND, 1));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemDreamNightmare.damageValue }, new AspectList().add(Aspect.AIR, 1).add(Aspect.SENSES, 1).add(Aspect.SOUL, 2).add(Aspect.DARKNESS, 4).add(Aspect.EXCHANGE, 1));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksDREAM_CATCHER), new int[] { -1 }, new AspectList().add(Aspect.AIR, 1).add(Aspect.SENSES, 1).add(Aspect.SOUL, 2).add(Aspect.DARKNESS, 1).add(Aspect.ENERGY, 2));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBoneNeedle.damageValue }, new AspectList().add(Aspect.WEAPON, 1).add(Aspect.FLESH, 1).add(Aspect.DEATH, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBroom.damageValue }, new AspectList().add(Aspect.TOOL, 1).add(Aspect.TREE, 2));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBroomEnchanted.damageValue }, new AspectList().add(Aspect.TOOL, 1).add(Aspect.TREE, 2).add(Aspect.MAGIC, 2).add(Aspect.FLIGHT, 2));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemAttunedStone.damageValue }, new AspectList().add(Aspect.CRYSTAL, 4).add(Aspect.GREED, 4).add(Aspect.ENERGY, 4).add(Aspect.MAGIC, 2));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemAttunedStoneCharged.damageValue }, new AspectList().add(Aspect.CRYSTAL, 4).add(Aspect.GREED, 4).add(Aspect.ENERGY, 6).add(Aspect.MAGIC, 2));
      


      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemWaystone.damageValue }, new AspectList().add(Aspect.EARTH, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemWaystoneBound.damageValue }, new AspectList().add(Aspect.EARTH, 1).add(Aspect.ELDRITCH, 2).add(Aspect.MAGIC, 2));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemWaystonePlayerBound.damageValue }, new AspectList().add(Aspect.EARTH, 1).add(Aspect.ELDRITCH, 2).add(Aspect.MAGIC, 2));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemMutandis.damageValue }, new AspectList().add(Aspect.EXCHANGE, 4).add(Aspect.PLANT, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemMutandisExtremis.damageValue }, new AspectList().add(Aspect.EXCHANGE, 8).add(Aspect.PLANT, 1).add(Aspect.MAGIC, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemQuicklime.damageValue }, new AspectList().add(Aspect.WEAPON, 1).add(Aspect.ENTROPY, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemGypsum.damageValue }, new AspectList().add(Aspect.EARTH, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemAshWood.damageValue }, new AspectList().add(Aspect.TREE, 1).add(Aspect.FIRE, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBelladonnaFlower.damageValue }, new AspectList().add(Aspect.PLANT, 2).add(Aspect.POISON, 4).add(Aspect.DEATH, 4));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemMandrakeRoot.damageValue }, new AspectList().add(Aspect.PLANT, 2).add(Aspect.MAN, 1).add(Aspect.EARTH, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemIcyNeedle.damageValue }, new AspectList().add(Aspect.PLANT, 1).add(Aspect.COLD, 4));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemDemonHeart.damageValue }, new AspectList().add(Aspect.FIRE, 4).add(Aspect.VOID, 2).add(Aspect.FLESH, 2).add(Aspect.SOUL, 4));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemCreeperHeart.damageValue }, new AspectList().add(Aspect.BEAST, 4).add(Aspect.FIRE, 1).add(Aspect.FLESH, 2).add(Aspect.SOUL, 4));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemFrozenHeart.damageValue }, new AspectList().add(Aspect.BEAST, 4).add(Aspect.FIRE, 1).add(Aspect.FLESH, 2).add(Aspect.SOUL, 4).add(Aspect.COLD, 4));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBatWool.damageValue }, new AspectList().add(Aspect.FLESH, 1).add(Aspect.FLIGHT, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemDogTongue.damageValue }, new AspectList().add(Aspect.FLESH, 1).add(Aspect.BEAST, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemSoftClayJar.damageValue }, new AspectList().add(Aspect.EARTH, 1).add(Aspect.WATER, 1).add(Aspect.VOID, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemEmptyClayJar.damageValue }, new AspectList().add(Aspect.EARTH, 1).add(Aspect.FIRE, 1).add(Aspect.VOID, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemFoulFume.damageValue }, new AspectList().add(Aspect.AIR, 3).add(Aspect.EARTH, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemDiamondVapour.damageValue }, new AspectList().add(Aspect.AIR, 3).add(Aspect.CRYSTAL, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemExhaleOfTheHornedOne.damageValue }, new AspectList().add(Aspect.AIR, 3).add(Aspect.FIRE, 1).add(Aspect.UNDEAD, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBreathOfTheGoddess.damageValue }, new AspectList().add(Aspect.AIR, 3).add(Aspect.ORDER, 1).add(Aspect.SOUL, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemHintOfRebirth.damageValue }, new AspectList().add(Aspect.AIR, 3).add(Aspect.LIFE, 1).add(Aspect.EXCHANGE, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemReekOfMisfortune.damageValue }, new AspectList().add(Aspect.AIR, 3).add(Aspect.ENTROPY, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemWhiffOfMagic.damageValue }, new AspectList().add(Aspect.AIR, 3).add(Aspect.MAGIC, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemReekOfMisfortune.damageValue }, new AspectList().add(Aspect.AIR, 3).add(Aspect.ENTROPY, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemOdourOfPurity.damageValue }, new AspectList().add(Aspect.AIR, 3).add(Aspect.ORDER, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemEnderDew.damageValue }, new AspectList().add(Aspect.WATER, 2).add(Aspect.ELDRITCH, 2));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemOilOfVitriol.damageValue }, new AspectList().add(Aspect.WATER, 2).add(Aspect.ENTROPY, 4));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemTearOfTheGoddess.damageValue }, new AspectList().add(Aspect.WATER, 2).add(Aspect.ORDER, 1).add(Aspect.SOUL, 2));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemRefinedEvil.damageValue }, new AspectList().add(Aspect.WATER, 2).add(Aspect.MIND, 2).add(Aspect.ENTROPY, 2));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemDropOfLuck.damageValue }, new AspectList().add(Aspect.WATER, 2).add(Aspect.ENTROPY, 1).add(Aspect.ORDER, 1).add(Aspect.VOID, 1));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemRedstoneSoup.damageValue }, new AspectList().add(Aspect.WATER, 2).add(Aspect.ENERGY, 2).add(Aspect.MAGIC, 2));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemFlyingOintment.damageValue }, new AspectList().add(Aspect.WATER, 2).add(Aspect.ENERGY, 2).add(Aspect.MAGIC, 2).add(Aspect.FLIGHT, 2));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemGhostOfTheLight.damageValue }, new AspectList().add(Aspect.WATER, 2).add(Aspect.ENERGY, 2).add(Aspect.MAGIC, 6).add(Aspect.LIGHT, 15).add(Aspect.ARMOR, 5));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemSoulOfTheWorld.damageValue }, new AspectList().add(Aspect.WATER, 2).add(Aspect.ENERGY, 2).add(Aspect.MAGIC, 2).add(Aspect.EARTH, 10).add(Aspect.METAL, 10));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemSpiritOfOtherwhere.damageValue }, new AspectList().add(Aspect.WATER, 2).add(Aspect.ENERGY, 2).add(Aspect.MAGIC, 2).add(Aspect.ELDRITCH, 20));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemInfernalAnimus.damageValue }, new AspectList().add(Aspect.WATER, 2).add(Aspect.ENERGY, 2).add(Aspect.MAGIC, 2).add(Aspect.DEATH, 5).add(Aspect.UNDEAD, 5).add(Aspect.BEAST, 5).add(Aspect.HUNGER, 5));
      





      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBookOven.damageValue }, new AspectList().add(Aspect.MIND, 5).add(Aspect.MAGIC, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBookDistilling.damageValue }, new AspectList().add(Aspect.MIND, 5).add(Aspect.MAGIC, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBookCircleMagic.damageValue }, new AspectList().add(Aspect.MIND, 5).add(Aspect.MAGIC, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBookWands.damageValue }, new AspectList().add(Aspect.MIND, 4).add(Aspect.MAGIC, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBookBiomes.damageValue }, new AspectList().add(Aspect.MIND, 4).add(Aspect.MAGIC, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBookInfusions.damageValue }, new AspectList().add(Aspect.MIND, 5).add(Aspect.MAGIC, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBookHerbology.damageValue }, new AspectList().add(Aspect.MIND, 5).add(Aspect.MAGIC, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemOddPorkRaw.damageValue }, new AspectList().add(Aspect.MAN, 1).add(Aspect.HUNGER, 2));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemOddPorkCooked.damageValue }, new AspectList().add(Aspect.MAN, 1).add(Aspect.HUNGER, 2).add(Aspect.FIRE, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemRock.damageValue }, new AspectList().add(Aspect.EARTH, 2));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemWeb.damageValue }, new AspectList().add(Aspect.CLOTH, 2).add(Aspect.TRAP, 4));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBrewOfVines.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.PLANT, 2));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBrewOfIce.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.COLD, 2));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBrewOfLove.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.LIFE, 2).add(Aspect.SOUL, 1));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBrewOfWebs.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.TRAP, 2).add(Aspect.CLOTH, 1));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBrewOfFlowingSpirit.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.SOUL, 2));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBrewOfWasting.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.HUNGER, 2));
      


      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBrewOfCursedLeaping.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.MOTION, 2));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBrewOfFrogsTongue.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.MOTION, 2));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBrewOfHitchcock.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.BEAST, 2));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBrewOfInfection.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.POISON, 2));
      


      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemOwletsWing.damageValue }, new AspectList().add(Aspect.BEAST, 1).add(Aspect.AIR, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemToeOfFrog.damageValue }, new AspectList().add(Aspect.BEAST, 1).add(Aspect.WATER, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemInfernalBlood.damageValue }, new AspectList().add(Aspect.FIRE, 1).add(Aspect.FLESH, 1).add(Aspect.SOUL, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemWormyApple.damageValue }, new AspectList().add(Aspect.PLANT, 1).add(Aspect.POISON, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemSleepingApple.damageValue }, new AspectList().add(Aspect.PLANT, 1).add(Aspect.HUNGER, 1).add(Aspect.MIND, 2));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBrewOfSleeping.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.MIND, 2));
      


      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemTormentedTwine.damageValue }, new AspectList().add(Aspect.CLOTH, 1).add(Aspect.DARKNESS, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemFancifulThread.damageValue }, new AspectList().add(Aspect.CLOTH, 1).add(Aspect.LIGHT, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemGoldenThread.damageValue }, new AspectList().add(Aspect.METAL, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemDisturbedCotton.damageValue }, new AspectList().add(Aspect.PLANT, 1).add(Aspect.DARKNESS, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemMellifluousHunger.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.HUNGER, 4));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemCharmOfDisruptedDreams.damageValue }, new AspectList().add(Aspect.CLOTH, 1).add(Aspect.ENTROPY, 1));
      


      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemKobolditeDust.damageValue }, new AspectList().add(Aspect.METAL, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemKobolditeNugget.damageValue }, new AspectList().add(Aspect.METAL, 2));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemKobolditeIngot.damageValue }, new AspectList().add(Aspect.METAL, 3));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemKobolditePentacle.damageValue }, new AspectList().add(Aspect.METAL, 4).add(Aspect.MAGIC, 3));
      


      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsKOBOLDITE_PICKAXE), new int[] { -1 }, new AspectList().add(Aspect.METAL, 3).add(Aspect.MAGIC, 1).add(Aspect.MINE, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksWISPY_COTTON), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 1).add(Aspect.LIGHT, 1));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksFLOWING_SPIRIT), new int[] { -1 }, new AspectList().add(Aspect.WATER, 1).add(Aspect.SOUL, 2));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsBUCKET_FLOWINGSPIRIT), new int[] { -1 }, new AspectList().add(Aspect.WATER, 1).add(Aspect.SOUL, 2).add(Aspect.METAL, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsPARASYTIC_LOUSE), new int[] { -1 }, new AspectList().add(Aspect.EXCHANGE, 1).add(Aspect.MOTION, 1).add(Aspect.BEAST, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTRAPPED_PLANT), new int[] { 0 }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.TRAP, 2).add(Aspect.CLOTH, 1).add(Aspect.MECHANISM, 1).add(Aspect.SENSES, 1));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTRAPPED_PLANT), new int[] { 4 }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.TRAP, 2).add(Aspect.CLOTH, 1).add(Aspect.MECHANISM, 1).add(Aspect.SENSES, 1));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTRAPPED_PLANT), new int[] { 8 }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.TRAP, 2).add(Aspect.CLOTH, 1).add(Aspect.MECHANISM, 1).add(Aspect.SENSES, 1));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBrewOfInk.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.DARKNESS, 2));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTRAPPED_PLANT), new int[] { 1 }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.DARKNESS, 2).add(Aspect.MECHANISM, 1).add(Aspect.SENSES, 1));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTRAPPED_PLANT), new int[] { 5 }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.DARKNESS, 2).add(Aspect.MECHANISM, 1).add(Aspect.SENSES, 1));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTRAPPED_PLANT), new int[] { 9 }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.DARKNESS, 2).add(Aspect.MECHANISM, 1).add(Aspect.SENSES, 1));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBrewOfSprouting.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.TREE, 4));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTRAPPED_PLANT), new int[] { 3 }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.TREE, 4).add(Aspect.MECHANISM, 1).add(Aspect.SENSES, 1));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTRAPPED_PLANT), new int[] { 7 }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.TREE, 4).add(Aspect.MECHANISM, 1).add(Aspect.SENSES, 1));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTRAPPED_PLANT), new int[] { 11 }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.TREE, 4).add(Aspect.MECHANISM, 1).add(Aspect.SENSES, 1));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBrewOfErosion.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 5).add(Aspect.MINE, 4));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBrewOfThorns.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 2).add(Aspect.PLANT, 1).add(Aspect.WEAPON, 1));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTRAPPED_PLANT), new int[] { 2 }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 2).add(Aspect.PLANT, 1).add(Aspect.WEAPON, 1).add(Aspect.MECHANISM, 1).add(Aspect.SENSES, 1));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTRAPPED_PLANT), new int[] { 6 }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 2).add(Aspect.PLANT, 1).add(Aspect.WEAPON, 1).add(Aspect.MECHANISM, 1).add(Aspect.SENSES, 1));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTRAPPED_PLANT), new int[] { 10 }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 2).add(Aspect.PLANT, 1).add(Aspect.WEAPON, 1).add(Aspect.MECHANISM, 1).add(Aspect.SENSES, 1));
      




      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemSpectralDust.damageValue }, new AspectList().add(Aspect.SOUL, 2).add(Aspect.UNDEAD, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBrewOfRaising.damageValue }, new AspectList().add(Aspect.WATER, 2).add(Aspect.ENTROPY, 1).add(Aspect.UNDEAD, 2));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemNecroStone.damageValue }, new AspectList().add(Aspect.CRYSTAL, 4).add(Aspect.GREED, 4).add(Aspect.ENERGY, 2).add(Aspect.MAGIC, 5).add(Aspect.UNDEAD, 5));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemArtichoke.damageValue }, new AspectList().add(Aspect.PLANT, 2).add(Aspect.WATER, 2));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBrewGrotesque.damageValue }, new AspectList().add(Aspect.WATER, 2).add(Aspect.SENSES, 2).add(Aspect.EXCHANGE, 2).add(Aspect.MAGIC, 1));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemImpregnatedLeather.damageValue }, new AspectList().add(Aspect.CLOTH, 2).add(Aspect.BEAST, 1).add(Aspect.ARMOR, 1).add(Aspect.MAGIC, 1));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksOVEN_FUMEFUNNEL), new int[] { -1 }, new AspectList().add(Aspect.VOID, 4).add(Aspect.METAL, 20).add(Aspect.SENSES, 3).add(Aspect.LIGHT, 5).add(Aspect.ORDER, 4).add(Aspect.FIRE, 4));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemFumeFilter.damageValue }, new AspectList().add(Aspect.METAL, 4).add(Aspect.CRYSTAL, 10).add(Aspect.GREED, 4).add(Aspect.ENERGY, 6).add(Aspect.MAGIC, 10).add(Aspect.ORDER, 4));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksOVEN_FUMEFUNNEL_FILTERED), new int[] { -1 }, new AspectList().add(Aspect.METAL, 24).add(Aspect.CRYSTAL, 10).add(Aspect.GREED, 4).add(Aspect.ENERGY, 6).add(Aspect.MAGIC, 10).add(Aspect.ORDER, 8).add(Aspect.VOID, 4).add(Aspect.SENSES, 3).add(Aspect.LIGHT, 5).add(Aspect.FIRE, 4));
      




      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsPOLYNESIA_CHARM), new int[] { -1 }, new AspectList().add(Aspect.MIND, 2).add(Aspect.SENSES, 2).add(Aspect.BEAST, 2));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsDEVILS_TONGUE_CHARM), new int[] { -1 }, new AspectList().add(Aspect.MIND, 4).add(Aspect.SENSES, 3).add(Aspect.FIRE, 6).add(Aspect.SOUL, 4).add(Aspect.FLESH, 5));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsWITCH_HAT), new int[] { -1 }, new AspectList().add(Aspect.CLOTH, 6).add(Aspect.BEAST, 5).add(Aspect.ARMOR, 5).add(Aspect.MAGIC, 5));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsBABAS_HAT), new int[] { -1 }, new AspectList().add(Aspect.CLOTH, 4).add(Aspect.BEAST, 2).add(Aspect.ARMOR, 5).add(Aspect.MAGIC, 5).add(Aspect.ELDRITCH, 3));
      




      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsMOGS_QUIVER), new int[] { -1 }, new AspectList().add(Aspect.CLOTH, 3).add(Aspect.BEAST, 2).add(Aspect.ARMOR, 1).add(Aspect.MAGIC, 3).add(Aspect.MECHANISM, 2));
      




      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGULGS_GURDLE), new int[] { -1 }, new AspectList().add(Aspect.CLOTH, 3).add(Aspect.BEAST, 2).add(Aspect.ARMOR, 1).add(Aspect.MAGIC, 3).add(Aspect.WEAPON, 4));
      




      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsWITCH_ROBES), new int[] { -1 }, new AspectList().add(Aspect.CLOTH, 6).add(Aspect.BEAST, 4).add(Aspect.ARMOR, 5).add(Aspect.MAGIC, 4));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsNECROMANCERS_ROBES), new int[] { -1 }, new AspectList().add(Aspect.CLOTH, 6).add(Aspect.BEAST, 4).add(Aspect.ARMOR, 5).add(Aspect.MAGIC, 3).add(Aspect.UNDEAD, 2));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsICY_SLIPPERS), new int[] { -1 }, new AspectList().add(Aspect.CLOTH, 2).add(Aspect.BEAST, 2).add(Aspect.ARMOR, 3).add(Aspect.MAGIC, 2).add(Aspect.COLD, 4));
      




      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsRUBY_SLIPPERS), new int[] { -1 }, new AspectList().add(Aspect.CLOTH, 2).add(Aspect.BEAST, 2).add(Aspect.ARMOR, 3).add(Aspect.MAGIC, 2).add(Aspect.ELDRITCH, 3));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsSEEPING_SHOES), new int[] { -1 }, new AspectList().add(Aspect.CLOTH, 2).add(Aspect.BEAST, 2).add(Aspect.ARMOR, 3).add(Aspect.MAGIC, 2).add(Aspect.POISON, 2));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsBARK_BELT), new int[] { -1 }, new AspectList().add(Aspect.CLOTH, 3).add(Aspect.BEAST, 2).add(Aspect.ARMOR, 6).add(Aspect.MAGIC, 2).add(Aspect.TREE, 4));
      




      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemPurifiedMilk.damageValue }, new AspectList().add(Aspect.HEAL, 1).add(Aspect.WATER, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBatBall.damageValue }, new AspectList().add(Aspect.BEAST, 1).add(Aspect.MOTION, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBrewOfBats.damageValue }, new AspectList().add(Aspect.WATER, 2).add(Aspect.MOTION, 1).add(Aspect.BEAST, 1).add(Aspect.MAGIC, 1));
      




      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsBITING_BELT), new int[] { -1 }, new AspectList().add(Aspect.CLOTH, 3).add(Aspect.BEAST, 2).add(Aspect.ARMOR, 4).add(Aspect.MAGIC, 1).add(Aspect.HUNGER, 2));
      




      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsBREW_BAG), new int[] { -1 }, new AspectList().add(Aspect.CLOTH, 2).add(Aspect.BEAST, 2).add(Aspect.VOID, 2).add(Aspect.MECHANISM, 1));
      




      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsMUTATING_SPRIG), new int[] { -1 }, new AspectList().add(Aspect.TOOL, 2).add(Aspect.EXCHANGE, 8).add(Aspect.MAGIC, 1).add(Aspect.TREE, 1));
      




      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemDoorKey.damageValue }, new AspectList().add(Aspect.MECHANISM, 1).add(Aspect.TOOL, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemDoorKeyring.damageValue }, new AspectList().add(Aspect.MECHANISM, 1).add(Aspect.TOOL, 1));
      


      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksBARRIER), new int[] { -1 }, new AspectList().add(Aspect.MAGIC, 2).add(Aspect.ARMOR, 3));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksVOID_BRAMBLE), new int[] { -1 }, new AspectList().add(Aspect.ORDER, 4).add(Aspect.ELDRITCH, 3).add(Aspect.ARMOR, 4).add(Aspect.MAGIC, 4).add(Aspect.WATER, 1).add(Aspect.PLANT, 1));
      




      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsCHALK_GOLDEN), new int[] { -1 }, new AspectList().add(Aspect.TOOL, 1).add(Aspect.MAGIC, 3).add(Aspect.ORDER, 1));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsCHALK_RITUAL), new int[] { -1 }, new AspectList().add(Aspect.TOOL, 1).add(Aspect.MAGIC, 1).add(Aspect.ORDER, 1));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsCHALK_OTHERWHERE), new int[] { -1 }, new AspectList().add(Aspect.TOOL, 1).add(Aspect.MAGIC, 1).add(Aspect.ORDER, 1).add(Aspect.ELDRITCH, 4).add(Aspect.TRAVEL, 4));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsCHALK_INFERNAL), new int[] { -1 }, new AspectList().add(Aspect.TOOL, 1).add(Aspect.MAGIC, 1).add(Aspect.ENTROPY, 1).add(Aspect.FIRE, 4).add(Aspect.BEAST, 1));
      




      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksCIRCLE), new int[] { -1 }, new AspectList().add(Aspect.MECHANISM, 1).add(Aspect.MAGIC, 3).add(Aspect.ORDER, 1));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksGLYPH_RITUAL), new int[] { -1 }, new AspectList().add(Aspect.MECHANISM, 1).add(Aspect.MAGIC, 1).add(Aspect.ORDER, 1));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksGLYPH_OTHERWHERE), new int[] { -1 }, new AspectList().add(Aspect.MECHANISM, 1).add(Aspect.MAGIC, 1).add(Aspect.ORDER, 1).add(Aspect.ELDRITCH, 4).add(Aspect.TRAVEL, 4));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksGLYPH_INFERNAL), new int[] { -1 }, new AspectList().add(Aspect.MECHANISM, 1).add(Aspect.MAGIC, 1).add(Aspect.ENTROPY, 1).add(Aspect.FIRE, 4).add(Aspect.BEAST, 1));
      




      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsCIRCLE_TALISMAN), new int[] { 0 }, new AspectList().add(Aspect.TOOL, 2).add(Aspect.CRYSTAL, 4).add(Aspect.METAL, 8).add(Aspect.MAGIC, 3));
      




      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksLEECH_CHEST), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 2).add(Aspect.VOID, 3).add(Aspect.HUNGER, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksSTATUE_GODDESS), new int[] { -1 }, new AspectList().add(Aspect.EARTH, 10).add(Aspect.MAGIC, 10).add(Aspect.HEAL, 4));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksCRITTER_SNARE), new int[] { -1 }, new AspectList().add(Aspect.WATER, 1).add(Aspect.PLANT, 1).add(Aspect.TRAP, 2));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksBRAMBLE), new int[] { 0 }, new AspectList().add(Aspect.ORDER, 2).add(Aspect.ELDRITCH, 3).add(Aspect.WATER, 1).add(Aspect.PLANT, 1));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksBRAMBLE), new int[] { 1 }, new AspectList().add(Aspect.WEAPON, 1).add(Aspect.WATER, 1).add(Aspect.PLANT, 1));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksGRASSPER), new int[] { -1 }, new AspectList().add(Aspect.WATER, 1).add(Aspect.PLANT, 1).add(Aspect.VOID, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemQuartzSphere.damageValue }, new AspectList().add(Aspect.CRYSTAL, 1).add(Aspect.EARTH, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemHappenstanceOil.damageValue }, new AspectList().add(Aspect.WATER, 2).add(Aspect.ENERGY, 2).add(Aspect.MAGIC, 2).add(Aspect.SENSES, 2));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemSeerStone.damageValue }, new AspectList().add(Aspect.CRYSTAL, 1).add(Aspect.EARTH, 1).add(Aspect.SENSES, 3));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksCRYSTAL_BALL), new int[] { -1 }, new AspectList().add(Aspect.CRYSTAL, 2).add(Aspect.MAGIC, 2).add(Aspect.SENSES, 4));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksBLOOD_ROSE), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 1).add(Aspect.HUNGER, 1).add(Aspect.MECHANISM, 1));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksDEMON_HEART), new int[] { -1 }, new AspectList().add(Aspect.FIRE, 4).add(Aspect.VOID, 2).add(Aspect.FLESH, 2).add(Aspect.SOUL, 4));
      




      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemGraveyardDust.damageValue }, new AspectList().add(Aspect.SOUL, 2).add(Aspect.UNDEAD, 1));
      


      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBookBurning.damageValue }, new AspectList().add(Aspect.MIND, 4).add(Aspect.MAGIC, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksFETISH_WITCHS_LADDER), new int[] { -1 }, new AspectList().add(Aspect.TRAP, 1).add(Aspect.AIR, 1).add(Aspect.MECHANISM, 2));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksFETISH_TREANT_IDOL), new int[] { -1 }, new AspectList().add(Aspect.TRAP, 1).add(Aspect.TREE, 2).add(Aspect.MECHANISM, 2));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksFETISH_SCARECROW), new int[] { -1 }, new AspectList().add(Aspect.TRAP, 1).add(Aspect.CLOTH, 2).add(Aspect.MECHANISM, 2));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsBOLINE), new int[] { -1 }, new AspectList().add(Aspect.WEAPON, 1).add(Aspect.METAL, 1).add(Aspect.CRYSTAL, 1));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsSPECTRAL_STONE), new int[] { -1 }, new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.TRAP, 4));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksBRAZIER), new int[] { -1 }, new AspectList().add(Aspect.MECHANISM, 2).add(Aspect.FIRE, 2).add(Aspect.METAL, 2));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemCongealedSpirit.damageValue }, new AspectList().add(Aspect.SOUL, 6));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsSEEDS_WORMWOOD), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 1));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksCROP_WORMWOOD), new int[] { -1 }, new AspectList().add(Aspect.UNDEAD, 2).add(Aspect.PLANT, 1));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemWormwood.damageValue }, new AspectList().add(Aspect.UNDEAD, 2).add(Aspect.PLANT, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemSubduedSpirit.damageValue }, new AspectList().add(Aspect.SOUL, 2));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemFocusedWill.damageValue }, new AspectList().add(Aspect.SOUL, 2));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemCondensedFear.damageValue }, new AspectList().add(Aspect.DEATH, 2));
      


      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBrewOfSolidRock.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.EXCHANGE, 1).add(Aspect.EARTH, 1));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBrewOfSolidDirt.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.EXCHANGE, 1).add(Aspect.EARTH, 1));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBrewOfSolidSand.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.EXCHANGE, 1).add(Aspect.EARTH, 1));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBrewOfSolidSandstone.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.EXCHANGE, 1).add(Aspect.EARTH, 1));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBrewOfSolidErosion.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 3).add(Aspect.EXCHANGE, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemInfusionBase.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ORDER, 3));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBrewOfSoaring.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.AIR, 3));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBrewGrave.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.UNDEAD, 3));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBrewRevealing.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.SENSES, 2));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBrewSubstitution.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.EXCHANGE, 3));
      


      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBrewOfHollowTears.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1).add(Aspect.VOID, 2));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksHOLLOW_TEARS), new int[] { -1 }, new AspectList().add(Aspect.WATER, 1).add(Aspect.VOID, 2));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsBUCKET_HOLLOWTEARS), new int[] { -1 }, new AspectList().add(Aspect.WATER, 1).add(Aspect.VOID, 2).add(Aspect.METAL, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemNullCatalyst.damageValue }, new AspectList().add(Aspect.ENTROPY, 1).add(Aspect.VOID, 2));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemNullifiedLeather.damageValue }, new AspectList().add(Aspect.VOID, 2).add(Aspect.CLOTH, 1));
      


      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBoltStake.damageValue }, new AspectList().add(Aspect.WEAPON, 1).add(Aspect.TREE, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBoltSplitting.damageValue }, new AspectList().add(Aspect.WEAPON, 1).add(Aspect.TREE, 1).add(Aspect.ENTROPY, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBoltHoly.damageValue }, new AspectList().add(Aspect.WEAPON, 1).add(Aspect.DEATH, 2));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBoltAntiMagic.damageValue }, new AspectList().add(Aspect.WEAPON, 1).add(Aspect.DEATH, 1).add(Aspect.MAGIC, 2).add(Aspect.VOID, 2));
      




      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsDEATH_HOOD), new int[] { -1 }, new AspectList().add(Aspect.CLOTH, 1).add(Aspect.DEATH, 4).add(Aspect.ARMOR, 2).add(Aspect.TRAP, 2));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsDEATH_ROBE), new int[] { -1 }, new AspectList().add(Aspect.CLOTH, 1).add(Aspect.DEATH, 4).add(Aspect.ARMOR, 2).add(Aspect.FIRE, 2));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsDEATH_FEET), new int[] { -1 }, new AspectList().add(Aspect.CLOTH, 1).add(Aspect.DEATH, 4).add(Aspect.ARMOR, 2).add(Aspect.WATER, 2));
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsDEATH_HAND), new int[] { -1 }, new AspectList().add(Aspect.WEAPON, 3).add(Aspect.DEATH, 4).add(Aspect.SOUL, 3));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBinkyHead.damageValue }, new AspectList().add(Aspect.DEATH, 3).add(Aspect.BEAST, 2).add(Aspect.EXCHANGE, 2));
      


      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsHUNTER_HAT), new int[] { -1 }, new AspectList().add(Aspect.ARMOR, 1).add(Aspect.CLOTH, 1).add(Aspect.VOID, 1));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsHUNTER_BOOTS), new int[] { -1 }, new AspectList().add(Aspect.ARMOR, 1).add(Aspect.CLOTH, 1).add(Aspect.VOID, 1));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsHUNTER_LEGS), new int[] { -1 }, new AspectList().add(Aspect.ARMOR, 1).add(Aspect.CLOTH, 1).add(Aspect.VOID, 1));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsHUNTER_COAT), new int[] { -1 }, new AspectList().add(Aspect.ARMOR, 1).add(Aspect.CLOTH, 1).add(Aspect.VOID, 1));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsCROSSBOW_PISTOL), new int[] { -1 }, new AspectList().add(Aspect.WEAPON, 2).add(Aspect.MOTION, 1).add(Aspect.MECHANISM, 2));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsSHELF_COMPASS), new int[] { -1 }, new AspectList().add(Aspect.SENSES, 2).add(Aspect.CRYSTAL, 3).add(Aspect.MAGIC, 2));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsPOTIONS), new int[] { ItemsPOTIONS.potionAntidote.damageValue }, new AspectList().add(Aspect.WATER, 1).add(Aspect.HEAL, 2));
      


      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsSEEDS_MINDRAKE), new int[] { -1 }, new AspectList().add(Aspect.WATER, 1).add(Aspect.EXCHANGE, 1));
      
      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksCROP_MINDRAKE), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 2).add(Aspect.MAN, 1).add(Aspect.CROP, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsPLAYER_COMPASS), new int[] { -1 }, new AspectList().add(Aspect.SENSES, 2).add(Aspect.MAN, 1).add(Aspect.MAGIC, 2));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsEARMUFFS), new int[] { -1 }, new AspectList().add(Aspect.SENSES, 1).add(Aspect.CLOTH, 1).add(Aspect.BEAST, 1).add(Aspect.ARMOR, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksPIT_DIRT), new int[] { -1 }, new AspectList().add(Aspect.EARTH, 1).add(Aspect.TRAP, 2).add(Aspect.MAGIC, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksPIT_GRASS), new int[] { -1 }, new AspectList().add(Aspect.EARTH, 1).add(Aspect.TRAP, 2).add(Aspect.MAGIC, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksVINE), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksLILY), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksCACTUS), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 1).add(Aspect.TRAP, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksWEB), new int[] { -1 }, new AspectList().add(Aspect.TRAP, 2));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksSNOW_STAIRS), new int[] { -1 }, new AspectList().add(Aspect.COLD, 1).add(Aspect.WATER, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksSNOW_SLAB_SINGLE), new int[] { -1 }, new AspectList().add(Aspect.COLD, 1).add(Aspect.WATER, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksSNOW_SLAB_DOUBLE), new int[] { -1 }, new AspectList().add(Aspect.COLD, 1).add(Aspect.WATER, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksSNOW_PRESSURE_PLATE), new int[] { -1 }, new AspectList().add(Aspect.COLD, 1).add(Aspect.WATER, 1).add(Aspect.MECHANISM, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksPERPETUAL_ICE), new int[] { -1 }, new AspectList().add(Aspect.COLD, 1).add(Aspect.WATER, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksPERPETUAL_ICE_STAIRS), new int[] { -1 }, new AspectList().add(Aspect.COLD, 1).add(Aspect.WATER, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksPERPETUAL_ICE_SLAB_SINGLE), new int[] { -1 }, new AspectList().add(Aspect.COLD, 1).add(Aspect.WATER, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksPERPETUAL_ICE_SLAB_DOUBLE), new int[] { -1 }, new AspectList().add(Aspect.COLD, 1).add(Aspect.WATER, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksPERPETUAL_ICE_FENCE), new int[] { -1 }, new AspectList().add(Aspect.COLD, 1).add(Aspect.WATER, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksPERPETUAL_ICE_FENCE_GATE), new int[] { -1 }, new AspectList().add(Aspect.COLD, 1).add(Aspect.WATER, 1).add(Aspect.MECHANISM, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksPERPETUAL_ICE_DOOR), new int[] { -1 }, new AspectList().add(Aspect.COLD, 1).add(Aspect.WATER, 1).add(Aspect.MECHANISM, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksSTOCKADE_ICE), new int[] { -1 }, new AspectList().add(Aspect.COLD, 1).add(Aspect.WATER, 1).add(Aspect.ARMOR, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksPERPETUAL_ICE_PRESSURE_PLATE), new int[] { -1 }, new AspectList().add(Aspect.COLD, 1).add(Aspect.WATER, 1).add(Aspect.MECHANISM, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(BlocksVINE), new int[] { -1 }, new AspectList().add(Aspect.PLANT, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsBIOME_BOOK), new int[] { -1 }, new AspectList().add(Aspect.MIND, 5).add(Aspect.MAGIC, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsBIOME_NOTE), new int[] { -1 }, new AspectList().add(Aspect.MIND, 2).add(Aspect.MAGIC, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsCAULDRON_BOOK), new int[] { -1 }, new AspectList().add(Aspect.MIND, 5).add(Aspect.MAGIC, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsBREW_FUEL), new int[] { -1 }, new AspectList().add(Aspect.WATER, 2).add(Aspect.ENERGY, 2).add(Aspect.MAGIC, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsBREW_ENDLESS_WATER), new int[] { -1 }, new AspectList().add(Aspect.WATER, 8).add(Aspect.MAGIC, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemAnnointingPaste.damageValue }, new AspectList().add(Aspect.PLANT, 1).add(Aspect.EXCHANGE, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsBREW), new int[] { -1 }, new AspectList().add(Aspect.WATER, 2).add(Aspect.MAGIC, 3));
      




      ThaumcraftApi.registerEntityTag("witchery.mindrake", new AspectList().add(Aspect.PLANT, 2).add(Aspect.MAN, 1).add(Aspect.SENSES, 1).add(Aspect.MOTION, 1).add(Aspect.FIRE, 1), new ThaumcraftApi.EntityTagsNBT[0]);
      

      ThaumcraftApi.registerEntityTag("witchery.demon", new AspectList().add(Aspect.DEATH, 5).add(Aspect.VOID, 2).add(Aspect.FIRE, 4).add(Aspect.MAGIC, 6).add(Aspect.MOTION, 2).add(Aspect.SENSES, 2).add(Aspect.GREED, 8).add(Aspect.BEAST, 2).add(Aspect.ENTROPY, 1), new ThaumcraftApi.EntityTagsNBT[0]);
      



      ThaumcraftApi.registerEntityTag("witchery.familiar", new AspectList().add(Aspect.AIR, 5).add(Aspect.SOUL, 6).add(Aspect.MAGIC, 2).add(Aspect.VOID, 2).add(Aspect.MOTION, 1).add(Aspect.BEAST, 1), new ThaumcraftApi.EntityTagsNBT[0]);
      


      ThaumcraftApi.registerEntityTag("witchery.mandrake", new AspectList().add(Aspect.PLANT, 2).add(Aspect.MAN, 1).add(Aspect.AIR, 2).add(Aspect.SENSES, 2).add(Aspect.MOTION, 1), new ThaumcraftApi.EntityTagsNBT[0]);
      
      ThaumcraftApi.registerEntityTag("witchery.treefyd", new AspectList().add(Aspect.PLANT, 4).add(Aspect.MAGIC, 2).add(Aspect.MOTION, 2).add(Aspect.MIND, 2), new ThaumcraftApi.EntityTagsNBT[0]);
      
      ThaumcraftApi.registerEntityTag("witchery.hornedHuntsman", new AspectList().add(Aspect.PLANT, 2).add(Aspect.MAGIC, 4).add(Aspect.MOTION, 2).add(Aspect.MIND, 4).add(Aspect.BEAST, 8).add(Aspect.EARTH, 2).add(Aspect.SOUL, 4), new ThaumcraftApi.EntityTagsNBT[0]);
      


      ThaumcraftApi.registerEntityTag("witchery.ent", new AspectList().add(Aspect.PLANT, 2).add(Aspect.TREE, 8).add(Aspect.MAGIC, 2).add(Aspect.MOTION, 2).add(Aspect.MIND, 2), new ThaumcraftApi.EntityTagsNBT[0]);
      



      ThaumcraftApi.registerEntityTag("witchery.babayaga", new AspectList().add(Aspect.MAN, 2).add(Aspect.MAGIC, 6).add(Aspect.MOTION, 2).add(Aspect.MIND, 4).add(Aspect.SOUL, 8), new ThaumcraftApi.EntityTagsNBT[0]);
      

      ThaumcraftApi.registerEntityTag("witchery.owl", new AspectList().add(Aspect.FLIGHT, 2).add(Aspect.MOTION, 2).add(Aspect.MIND, 1).add(Aspect.BEAST, 1), new ThaumcraftApi.EntityTagsNBT[0]);
      


      ThaumcraftApi.registerEntityTag("witchery.toad", new AspectList().add(Aspect.SLIME, 1).add(Aspect.WATER, 1).add(Aspect.MOTION, 2).add(Aspect.BEAST, 1), new ThaumcraftApi.EntityTagsNBT[0]);
      


      ThaumcraftApi.registerEntityTag("witchery.cat", new AspectList().add(Aspect.SENSES, 2).add(Aspect.MIND, 1).add(Aspect.MOTION, 2).add(Aspect.BEAST, 1), new ThaumcraftApi.EntityTagsNBT[0]);
      


      ThaumcraftApi.registerEntityTag("witchery.louse", new AspectList().add(Aspect.EXCHANGE, 1).add(Aspect.MOTION, 1).add(Aspect.BEAST, 1), new ThaumcraftApi.EntityTagsNBT[0]);
      

      ThaumcraftApi.registerEntityTag("witchery.illusionSpider", new AspectList().add(Aspect.AIR, 2).add(Aspect.SENSES, 1).add(Aspect.MOTION, 2), new ThaumcraftApi.EntityTagsNBT[0]);
      
      ThaumcraftApi.registerEntityTag("witchery.illusionCreeper", new AspectList().add(Aspect.AIR, 2).add(Aspect.SENSES, 1).add(Aspect.MOTION, 2), new ThaumcraftApi.EntityTagsNBT[0]);
      
      ThaumcraftApi.registerEntityTag("witchery.illusionZombie", new AspectList().add(Aspect.AIR, 2).add(Aspect.SENSES, 1).add(Aspect.MOTION, 2), new ThaumcraftApi.EntityTagsNBT[0]);
      

      ThaumcraftApi.registerEntityTag("witchery.covenwitch", new AspectList().add(Aspect.MAN, 2).add(Aspect.MAGIC, 3).add(Aspect.MIND, 2).add(Aspect.SOUL, 1).add(Aspect.MOTION, 2), new ThaumcraftApi.EntityTagsNBT[0]);
      
      ThaumcraftApi.registerEntityTag("witchery.witchhunter", new AspectList().add(Aspect.MAN, 2).add(Aspect.MIND, 2).add(Aspect.SOUL, 1).add(Aspect.MOTION, 2), new ThaumcraftApi.EntityTagsNBT[0]);
      

      ThaumcraftApi.registerEntityTag("witchery.corpse", new AspectList().add(Aspect.MAN, 2).add(Aspect.MIND, 2).add(Aspect.SOUL, 1), new ThaumcraftApi.EntityTagsNBT[0]);
      
      ThaumcraftApi.registerEntityTag("witchery.nightmare", new AspectList().add(Aspect.VOID, 3).add(Aspect.MAGIC, 2).add(Aspect.SOUL, 2).add(Aspect.HUNGER, 4), new ThaumcraftApi.EntityTagsNBT[0]);
      

      ThaumcraftApi.registerEntityTag("witchery.spirit", new AspectList().add(Aspect.SOUL, 3).add(Aspect.AIR, 1), new ThaumcraftApi.EntityTagsNBT[0]);
      
      ThaumcraftApi.registerEntityTag("witchery.lostsoul", new AspectList().add(Aspect.SOUL, 3).add(Aspect.AIR, 1), new ThaumcraftApi.EntityTagsNBT[0]);
      
      ThaumcraftApi.registerEntityTag("witchery.spectre", new AspectList().add(Aspect.DEATH, 2).add(Aspect.UNDEAD, 1), new ThaumcraftApi.EntityTagsNBT[0]);
      
      ThaumcraftApi.registerEntityTag("witchery.banshee", new AspectList().add(Aspect.DEATH, 2).add(Aspect.UNDEAD, 1).add(Aspect.SENSES, 2), new ThaumcraftApi.EntityTagsNBT[0]);
      
      ThaumcraftApi.registerEntityTag("witchery.poltergeist", new AspectList().add(Aspect.DEATH, 2).add(Aspect.UNDEAD, 1).add(Aspect.MOTION, 2), new ThaumcraftApi.EntityTagsNBT[0]);
      
      ThaumcraftApi.registerEntityTag("witchery.death", new AspectList().add(Aspect.DEATH, 8).add(Aspect.SOUL, 8), new ThaumcraftApi.EntityTagsNBT[0]);
      

      ThaumcraftApi.registerEntityTag("witchery.imp", new AspectList().add(Aspect.VOID, 2).add(Aspect.FIRE, 3).add(Aspect.MAGIC, 7).add(Aspect.MOTION, 2).add(Aspect.SENSES, 2).add(Aspect.GREED, 6).add(Aspect.BEAST, 2).add(Aspect.ENTROPY, 1), new ThaumcraftApi.EntityTagsNBT[0]);
      




      ThaumcraftApi.registerEntityTag("witchery.lordoftorment", new AspectList().add(Aspect.DEATH, 2).add(Aspect.VOID, 2).add(Aspect.FIRE, 5).add(Aspect.MAGIC, 8).add(Aspect.MOTION, 3).add(Aspect.SENSES, 2).add(Aspect.BEAST, 2).add(Aspect.ENTROPY, 2).add(Aspect.TRAP, 2), new ThaumcraftApi.EntityTagsNBT[0]);
      




      ThaumcraftApi.registerEntityTag("witchery.goblin", new AspectList().add(Aspect.SENSES, 2).add(Aspect.MIND, 1).add(Aspect.MOTION, 2).add(Aspect.MAN, 1), new ThaumcraftApi.EntityTagsNBT[0]);
      


      ThaumcraftApi.registerEntityTag("witchery.goblingulg", new AspectList().add(Aspect.SENSES, 2).add(Aspect.MIND, 1).add(Aspect.MOTION, 2).add(Aspect.MAN, 2).add(Aspect.MAGIC, 3).add(Aspect.ARMOR, 2), new ThaumcraftApi.EntityTagsNBT[0]);
      


      ThaumcraftApi.registerEntityTag("witchery.goblinmog", new AspectList().add(Aspect.SENSES, 2).add(Aspect.MIND, 1).add(Aspect.MOTION, 2).add(Aspect.MAN, 2).add(Aspect.MAGIC, 3).add(Aspect.ARMOR, 2), new ThaumcraftApi.EntityTagsNBT[0]);
      



      ThaumcraftApi.registerEntityTag("witchery.leonard", new AspectList().add(Aspect.SENSES, 2).add(Aspect.MIND, 1).add(Aspect.MOTION, 2).add(Aspect.MAN, 2).add(Aspect.MAGIC, 8), new ThaumcraftApi.EntityTagsNBT[0]);
      



      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBrewSoulAnguish.damageValue }, new AspectList().add(Aspect.SOUL, 2).add(Aspect.MIND, 2));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBrewSoulFear.damageValue }, new AspectList().add(Aspect.SOUL, 2).add(Aspect.SENSES, 2));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBrewSoulHunger.damageValue }, new AspectList().add(Aspect.SOUL, 2).add(Aspect.HUNGER, 2));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemBrewSoulTorment.damageValue }, new AspectList().add(Aspect.SOUL, 2).add(Aspect.TRAP, 2));
      


      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemContractOwnership.damageValue }, new AspectList().add(Aspect.EXCHANGE, 2).add(Aspect.SOUL, 2));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemContractBlaze.damageValue }, new AspectList().add(Aspect.EXCHANGE, 2).add(Aspect.FIRE, 1).add(Aspect.BEAST, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemContractEvaporate.damageValue }, new AspectList().add(Aspect.EXCHANGE, 2).add(Aspect.FIRE, 1).add(Aspect.WATER, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemContractFieryTouch.damageValue }, new AspectList().add(Aspect.EXCHANGE, 2).add(Aspect.FIRE, 1).add(Aspect.MECHANISM, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemContractResistFire.damageValue }, new AspectList().add(Aspect.EXCHANGE, 2).add(Aspect.FIRE, 1).add(Aspect.ARMOR, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemContractSmelting.damageValue }, new AspectList().add(Aspect.EXCHANGE, 2).add(Aspect.FIRE, 1).add(Aspect.MINE, 1));
      

      ThaumcraftApi.registerObjectTag(new ItemStack(ItemsGENERIC), new int[] { ItemsGENERIC.itemContractTorment.damageValue }, new AspectList().add(Aspect.EXCHANGE, 2).add(Aspect.TRAP, 3));
    }
  }
}
