package com.emoniph.witchery.brewing;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.blocks.BlockCircleGlyph;
import com.emoniph.witchery.blocks.BlockWitchCrop;
import com.emoniph.witchery.brewing.action.BrewAction;
import com.emoniph.witchery.brewing.action.BrewActionBlockCircle;
import com.emoniph.witchery.brewing.action.BrewActionDispersal;
import com.emoniph.witchery.brewing.action.BrewActionEffect;
import com.emoniph.witchery.brewing.action.BrewActionImpactModifier;
import com.emoniph.witchery.brewing.action.BrewActionList;
import com.emoniph.witchery.brewing.action.BrewActionModifier;
import com.emoniph.witchery.brewing.action.BrewActionRitual;
import com.emoniph.witchery.brewing.action.BrewActionRitualEntityTarget;
import com.emoniph.witchery.brewing.action.BrewActionRitualRecipe;
import com.emoniph.witchery.brewing.action.BrewActionRitualRecipe.Recipe;
import com.emoniph.witchery.brewing.action.BrewActionRitualSummonMob;
import com.emoniph.witchery.brewing.action.BrewActionRitualSummonMob.Recipe;
import com.emoniph.witchery.brewing.action.BrewActionSetColor;
import com.emoniph.witchery.brewing.action.BrewCurseEffect;
import com.emoniph.witchery.brewing.action.BrewPotionEffect;
import com.emoniph.witchery.brewing.action.effect.BrewActionBlight;
import com.emoniph.witchery.brewing.action.effect.BrewActionLilify;
import com.emoniph.witchery.brewing.action.effect.BrewActionRaiseLand;
import com.emoniph.witchery.brewing.action.effect.BrewActionRaising;
import com.emoniph.witchery.brewing.action.effect.BrewActionSprouting;
import com.emoniph.witchery.brewing.action.effect.BrewActionTranspose;
import com.emoniph.witchery.brewing.potions.PotionBase;
import com.emoniph.witchery.brewing.potions.PotionSnowTrail;
import com.emoniph.witchery.brewing.potions.WitcheryPotions;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.common.Shapeshift;
import com.emoniph.witchery.entity.EntityDemon;
import com.emoniph.witchery.entity.EntityLeonard;
import com.emoniph.witchery.entity.EntityOwl;
import com.emoniph.witchery.entity.EntitySummonedUndead;
import com.emoniph.witchery.entity.EntityToad;
import com.emoniph.witchery.familiar.Familiar;
import com.emoniph.witchery.infusion.infusions.InfusionInfernal;
import com.emoniph.witchery.integration.ModHookManager;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import com.emoniph.witchery.network.PacketPipeline;
import com.emoniph.witchery.network.PacketPushTarget;
import com.emoniph.witchery.util.BlockActionCircle;
import com.emoniph.witchery.util.BlockActionReplaceSphere;
import com.emoniph.witchery.util.BlockActionSphere;
import com.emoniph.witchery.util.BlockPosition;
import com.emoniph.witchery.util.BlockProtect;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.CircleUtil;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.Count;
import com.emoniph.witchery.util.CreatureUtil;
import com.emoniph.witchery.util.Dye;
import com.emoniph.witchery.util.EntityPosition;
import com.emoniph.witchery.util.EntityUtil;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.SpawnUtil;
import com.emoniph.witchery.util.TimeUtil;
import com.emoniph.witchery.util.TransformCreature;
import com.emoniph.witchery.worldgen.WorldGenLargeWitchTree;
import com.emoniph.witchery.worldgen.WorldGenWitchTree;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.FoodStats;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.IFluidBlock;

public class WitcheryBrewRegistry
{
  public static final WitcheryBrewRegistry INSTANCE = new WitcheryBrewRegistry();
  public static final int MAX_STRENGTH_BOOSTS = 7;
  public static final int MAX_DURATION_BOOSTS = 7;
  
  private WitcheryBrewRegistry()
  {
    BrewItemKey triggeredKey = new BrewItemKey(Items.field_151144_bL, 2);
    register(new BrewActionDispersal(new BrewItemKey(Items.field_151016_H), new AltarPower(0), new DispersalInstant()).addNullifier(new BrewItemKey(Items.field_151016_H), false).addNullifier(ItemsGENERIC.itemBatWool.getBrewItemKey(), false).addNullifier(ItemsGENERIC.itemArtichoke.getBrewItemKey(), false).addNullifier(ItemsGENERIC.itemWormwood.getBrewItemKey(), false).addNullifier(triggeredKey, false).addNullifier(new BrewItemKey(Items.field_151015_O), false));
    




    register(new BrewActionDispersal(ItemsGENERIC.itemArtichoke.getBrewItemKey(), new AltarPower(0), new DispersalInstant()).addNullifier(new BrewItemKey(Items.field_151016_H), false).addNullifier(ItemsGENERIC.itemBatWool.getBrewItemKey(), false).addNullifier(ItemsGENERIC.itemArtichoke.getBrewItemKey(), false).addNullifier(ItemsGENERIC.itemWormwood.getBrewItemKey(), false).addNullifier(triggeredKey, false).addNullifier(new BrewItemKey(Items.field_151015_O), false));
    




    register(new BrewActionDispersal(ItemsGENERIC.itemBatWool.getBrewItemKey(), new AltarPower(0), new DispersalGas()).addNullifier(new BrewItemKey(Items.field_151016_H), false).addNullifier(ItemsGENERIC.itemArtichoke.getBrewItemKey(), false).addNullifier(ItemsGENERIC.itemBatWool.getBrewItemKey(), false).addNullifier(ItemsGENERIC.itemWormwood.getBrewItemKey(), false).addNullifier(triggeredKey, false).addNullifier(new BrewItemKey(Items.field_151015_O), false));
    




    register(new BrewActionDispersal(ItemsGENERIC.itemWormwood.getBrewItemKey(), new AltarPower(0), new DispersalLiquid()).addNullifier(new BrewItemKey(Items.field_151016_H), false).addNullifier(ItemsGENERIC.itemBatWool.getBrewItemKey(), false).addNullifier(ItemsGENERIC.itemArtichoke.getBrewItemKey(), false).addNullifier(ItemsGENERIC.itemWormwood.getBrewItemKey(), false).addNullifier(triggeredKey, false).addNullifier(new BrewItemKey(Items.field_151015_O), false));
    




    register(new BrewActionDispersal(triggeredKey, new AltarPower(0), new DispersalTriggered()).addNullifier(new BrewItemKey(Items.field_151016_H), false).addNullifier(ItemsGENERIC.itemBatWool.getBrewItemKey(), false).addNullifier(ItemsGENERIC.itemArtichoke.getBrewItemKey(), false).addNullifier(ItemsGENERIC.itemWormwood.getBrewItemKey(), false).addNullifier(triggeredKey, false).addNullifier(new BrewItemKey(Items.field_151015_O), false));
    






    register(new BrewActionImpactModifier(ItemsGENERIC.itemAshWood.getBrewItemKey(), new BrewNamePartModifier(0, 0, false, 1, 0), new AltarPower(50))
    {
      protected void onPrepareSplashPotion(World world, ModifiersImpact modifiers)
      {
        if (extent < 1) {
          extent += 1;
        }
        
      }
    });
    register(new BrewActionImpactModifier(Dye.COCOA_BEANS.getBrewItemKey(), new BrewNamePartModifier(0, 0, false, 1, 0), new AltarPower(100))
    {
      protected void onPrepareSplashPotion(World world, ModifiersImpact modifiers)
      {
        if (extent < 2) {
          extent += 1;
        }
        
      }
    });
    register(new BrewActionImpactModifier(new BrewItemKey(BlocksWISPY_COTTON), new BrewNamePartModifier(0, 0, false, 1, 0), new AltarPower(150))
    {
      protected void onPrepareSplashPotion(World world, ModifiersImpact modifiers)
      {
        if (extent < 3) {
          extent += 1;
        }
        
      }
      
    });
    register(new BrewActionImpactModifier(ItemsGENERIC.itemBelladonnaFlower.getBrewItemKey(), new BrewNamePartModifier(0, 0, false, 0, 1), new AltarPower(50))
    {
      protected void onPrepareSplashPotion(World world, ModifiersImpact modifiers)
      {
        if (lifetime < 1) {
          lifetime += 1;
        }
        
      }
    });
    register(new BrewActionImpactModifier(Dye.LAPIS_LAZULI.getBrewItemKey(), new BrewNamePartModifier(0, 0, false, 0, 1), new AltarPower(100))
    {
      protected void onPrepareSplashPotion(World world, ModifiersImpact modifiers)
      {
        if (lifetime < 2) {
          lifetime += 1;
        }
        
      }
    });
    register(new BrewActionImpactModifier(new BrewItemKey(Blocks.field_150377_bs), new BrewNamePartModifier(0, 0, false, 0, 1), new AltarPower(150))
    {
      protected void onPrepareSplashPotion(World world, ModifiersImpact modifiers)
      {
        if (lifetime < 3) {
          lifetime += 1;
        }
      }
    });
    

    for (Dye dye : Dye.values()) {
      register(new BrewActionSetColor(new BrewItemKey(Blocks.field_150325_L, 15 - damageValue), new AltarPower(0), rgb));
    }
    


    register(new BrewActionModifier(new BrewItemKey(Items.field_151074_bl), null, new AltarPower(50))
    {
      public void augmentEffectModifiers(ModifiersEffect modifiers) {
        noParticles = true;
      }
      

    });
    register(new BrewActionModifier(ItemsGENERIC.itemRowanBerries.getBrewItemKey(), null, new AltarPower(50))
    {
      public int getDrinkSpeedModifiers()
      {
        return -8;
      }
      
    });
    register(new BrewActionModifier(ItemsGENERIC.itemExhaleOfTheHornedOne.getBrewItemKey(), null, new AltarPower(0))
    {
      public int getDrinkSpeedModifiers()
      {
        return -4;
      }
      
    });
    register(new BrewActionModifier(new BrewItemKey(BlocksSPANISH_MOSS), null, new AltarPower(50))
    {
      public int getDrinkSpeedModifiers() {
        return -4;
      }
      

    });
    register(new BrewActionModifier(ItemsGENERIC.itemMandrakeRoot.getBrewItemKey(), null, new AltarPower(0))
    {
      public boolean augmentEffectLevels(EffectLevelCounter totalEffects)
      {
        totalEffects.increaseAvailableLevelIf(new EffectLevel(1), new EffectLevel(1));
        return true;
      }
      
    });
    register(new BrewActionModifier(new BrewItemKey(Items.field_151075_bm), null, new AltarPower(50))
    {
      public boolean augmentEffectLevels(EffectLevelCounter totalEffects) {
        totalEffects.increaseAvailableLevelIf(new EffectLevel(2), new EffectLevel(2));
        return true;
      }
      
    });
    register(new BrewActionModifier(ItemsGENERIC.itemTearOfTheGoddess.getBrewItemKey(), null, new AltarPower(100))
    {
      public boolean augmentEffectLevels(EffectLevelCounter totalEffects)
      {
        totalEffects.increaseAvailableLevelIf(new EffectLevel(2), new EffectLevel(4));
        return true;
      }
      
    });
    register(new BrewActionModifier(ItemsGENERIC.itemDiamondVapour.getBrewItemKey(), null, new AltarPower(150))
    {
      public boolean augmentEffectLevels(EffectLevelCounter totalEffects)
      {
        totalEffects.increaseAvailableLevelIf(new EffectLevel(2), new EffectLevel(6));
        return true;
      }
      
    });
    register(new BrewActionModifier(new BrewItemKey(Items.field_151045_i), null, new AltarPower(150))
    {
      public boolean augmentEffectLevels(EffectLevelCounter totalEffects) {
        totalEffects.increaseAvailableLevelIf(new EffectLevel(2), new EffectLevel(8));
        return true; } }).setYieldModifier(new ModifierYield(-2));
    


    register(new BrewActionModifier(new BrewItemKey(Items.field_151156_bN), new BrewNamePartModifier(0, 0, false, 0, 0, true), new AltarPower(150))
    {
      public boolean augmentEffectLevels(EffectLevelCounter totalEffects)
      {
        totalEffects.increaseAvailableLevelIf(new EffectLevel(4), new EffectLevel(10));
        return true;
      }
      
      public void augmentEffectModifiers(ModifiersEffect modifiers)
      {
        powerhCeilingDisabled = true;
      }
      
    });
    register(new BrewActionModifier(ItemsGENERIC.itemKobolditePentacle.getBrewItemKey(), null, new AltarPower(1000))
    {
      public boolean augmentEffectLevels(EffectLevelCounter totalEffects)
      {
        totalEffects.increaseAvailableLevelIf(new EffectLevel(6), new EffectLevel(16));
        return true;
      }
      

    });
    register(new BrewActionModifier(new BrewItemKey(Items.field_151114_aO), new BrewNamePartModifier(1, 0, false, 0, 0), new AltarPower(50))
    {
      public void augmentEffectModifiers(ModifiersEffect modifiers)
      {
        if (strength < 1) {
          modifiers.increaseStrength(1);
        }
        
      }
    });
    register(new BrewActionModifier(new BrewItemKey(Items.field_151072_bj), new BrewNamePartModifier(1, 0, false, 0, 0), new AltarPower(100))
    {
      public void augmentEffectModifiers(ModifiersEffect modifiers)
      {
        if (strength < 2) {
          modifiers.increaseStrength(1);
        }
        
      }
    });
    register(new BrewActionModifier(ItemsGENERIC.itemAttunedStoneCharged.getBrewItemKey(), new BrewNamePartModifier(1, 0, false, 0, 0), new AltarPower(150))
    {
      public void augmentEffectModifiers(ModifiersEffect modifiers)
      {
        if (strength < 3) {
          modifiers.increaseStrength(1);
        }
        
      }
      
    });
    register(new BrewActionModifier(new BrewItemKey(Items.field_151137_ax), new BrewNamePartModifier(0, 1, false, 0, 0), new AltarPower(50))
    {
      public void augmentEffectModifiers(ModifiersEffect modifiers)
      {
        if (duration < 1) {
          modifiers.increaseDuration(1);
        }
        
      }
    });
    register(new BrewActionModifier(new BrewItemKey(Blocks.field_150343_Z), new BrewNamePartModifier(0, 1, false, 0, 0), new AltarPower(100))
    {
      public void augmentEffectModifiers(ModifiersEffect modifiers)
      {
        if (duration < 2) {
          modifiers.increaseDuration(1);
        }
        
      }
    });
    register(new BrewActionModifier(new BrewItemKey(ItemsSEEDS_MINDRAKE), new BrewNamePartModifier(0, 1, false, 0, 0), new AltarPower(150))
    {
      public void augmentEffectModifiers(ModifiersEffect modifiers)
      {
        if (duration < 3) {
          modifiers.increaseDuration(1);
        }
        
      }
      
    });
    register(new BrewActionModifier(new BrewItemKey(Items.field_151071_bq), new BrewNamePartModifier(0, 0, true, 0, 0), new AltarPower(25))
    {
      public void augmentEffectModifiers(ModifiersEffect modifiers)
      {
        inverted = true;
      }
      

    });
    register(new BrewActionModifier(new BrewItemKey(Items.field_151130_bT), null, new AltarPower(50))
    {
      public void augmentEffectModifiers(ModifiersEffect modifiers) {
        disableBlockTarget = true;
      }
      

    });
    register(new BrewActionModifier(new BrewItemKey(Items.field_151118_aC), null, new AltarPower(50))
    {
      public void augmentEffectModifiers(ModifiersEffect modifiers) {
        disableEntityTarget = true;
      }
      

    });
    register(new BrewActionModifier(new BrewItemKey(Items.field_151115_aP, 2), null, new AltarPower(200))
    {
      public void augmentEffectModifiers(ModifiersEffect modifiers) {
        strengthCeilingDisabled = true;


      }
      


    });
    register(new BrewActionEffect(new BrewItemKey(Items.field_151126_ay), new BrewNamePart("witchery:brew.snow").setBaseDuration(TimeUtil.minsToTicks(3)), new AltarPower(0), new Probability(1.0D), new EffectLevel(1))
    {

      protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack)
      {

        PotionSnowTrail.createSnowCovering(world, x, y, z, 2 + 2 * modifiers.getStrength(), caster);
      }
      


      protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
      {
        BrewPotionEffect.applyPotionEffect(targetEntity, modifiers, PotionsSNOW_TRAIL, TimeUtil.minsToTicks(3), noParticles, caster);
      }
      

    });
    register(new BrewPotionEffect(new BrewItemKey(Items.field_151115_aP, 0), new BrewNamePart("witchery:brew.swimming"), new AltarPower(0), new Probability(1.0D), PotionsSWIMMING, TimeUtil.minsToTicks(3), new EffectLevel(1)));
    


    for (Dye dye : new Dye[] { Dye.ROSE_RED, Dye.CACTUS_GREEN, Dye.PURPLE_DYE, Dye.CYAN_DYE, Dye.LIGHT_GRAY_DYE, Dye.GRAY_DYE, Dye.PINK_DYE, Dye.LIME_DYE, Dye.DANDELION_YELLOW, Dye.LIGHT_BLUE_DYE, Dye.MAGENTA_DYE, Dye.ORANGE_DYE })
    {

      register(new BrewCurseEffect(dye.getBrewItemKey(), new BrewNamePart("witchery:potion.colorful." + unlocalizedName), new AltarPower(0), new Probability(1.0D), PotionsCOLORFUL, TimeUtil.secsToTicks(90), new EffectLevel(1), false)
      {

        public void applyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack stack)
        {
          if (!disableEntityTarget) {
            if (!protectedFromNegativePotions) {
              targetEntity.func_70690_d(new PotionEffect(potion.field_76415_H, baseDuration, val$dye.ordinal(), true));
            }
            
            modifiers.reset();
          }
        }
      });
    }
    
    register(new BrewPotionEffect(ItemsGENERIC.itemEnderDew.getBrewItemKey(), new BrewNamePart("witchery:potion.enderinhibition"), new AltarPower(200), new Probability(1.0D), PotionsENDER_INHIBITION, TimeUtil.secsToTicks(90), new EffectLevel(1)));
    


    register(new BrewPotionEffect(new BrewItemKey(Items.field_151015_O), new BrewNamePart("witchery:brew.moonshine"), new AltarPower(0), new Probability(1.0D), PotionsFEEL_NO_PAIN, TimeUtil.secsToTicks(90), new EffectLevel(1)));
    


    register(new BrewActionEffect(new BrewItemKey(Items.field_151044_h), new BrewNamePart("witchery:brew.extinguish"), new AltarPower(0), new Probability(1.0D), new EffectLevel(1))
    {

      protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack stack)
      {
        if ((modifiers.getStrength() > 1) || (!field_73011_w.field_76575_d)) {
          if ((modifiers.getStrength() > 0) && ((targetEntity instanceof EntityBlaze))) {
            targetEntity.func_70097_a(DamageSource.func_76354_b(caster, caster), (modifiers.getStrength() + 1) * 4.0F);
          }
          

          if (targetEntity.func_70027_ad()) {
            targetEntity.func_70066_B();
          }
        }
      }
      

      protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, final int radius, final ModifiersEffect modifiers, ItemStack stack)
      {
        if ((modifiers.getStrength() > 1) || (!field_73011_w.field_76575_d)) {
          new BlockActionCircle()
          {
            public void onBlock(World world, int x, int y, int z) {
              for (int dy = y - radius; dy <= y + radius; dy++) {
                Block block = world.func_147439_a(x, dy, z);
                if ((block == Blocks.field_150480_ab) && 
                  (BlockProtect.checkModsForBreakOK(world, x, dy, z, block, world.func_72805_g(x, dy, z), modifierscaster)) && (BlockProtect.canBreak(block, world)))
                {

                  world.func_147468_f(x, dy, z);
                  SoundEffect.RANDOM_FIZZ.playAt(world, x, dy, z, 1.0F, 2.0F); } } } }.processFilledCircle(world, x, y, z, radius + (ritualised ? 5 : 0));

        }
        

      }
      


    });
    register(new BrewActionEffect(new BrewItemKey(Blocks.field_150348_b), new BrewNamePart("witchery:brew.dissipate"), new AltarPower(0), new Probability(1.0D), new EffectLevel(1))
    {

      protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack stack)
      {
        if ((targetEntity instanceof EntitySummonedUndead)) {
          targetEntity.func_70097_a(DamageSource.func_76354_b(caster, caster), (modifiers.getStrength() + 1) * 5.0F);
        }
      }
      



      protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, final int radius, final ModifiersEffect modifiers, ItemStack stack)
      {
        new BlockActionCircle()
        {
          public void onBlock(World world, int x, int y, int z) {
            for (int dy = y - radius; dy <= y + radius; dy++) {
              Block block = world.func_147439_a(x, dy, z);
              if (block == BlocksBREW_GAS) {
                if ((BlockProtect.checkModsForBreakOK(world, x, dy, z, block, world.func_72805_g(x, dy, z), modifierscaster)) && (BlockProtect.canBreak(block, world)))
                {

                  world.func_147468_f(x, dy, z);
                  SoundEffect.RANDOM_FIZZ.playAt(world, x, dy, z, 1.0F, 2.0F);
                }
              } else if (((block instanceof IFluidBlock)) && 
                (modifiers.getStrength() >= 1)) {
                IFluidBlock fluidBlock = (IFluidBlock)block;
                if ((fluidBlock.getFluid() != null) && (fluidBlock.getFluid().isGaseous()) && 
                  (BlockProtect.checkModsForBreakOK(world, x, dy, z, block, world.func_72805_g(x, dy, z), modifierscaster)) && (BlockProtect.canBreak(block, world)))
                {

                  world.func_147468_f(x, dy, z);
                  SoundEffect.RANDOM_FIZZ.playAt(world, x, dy, z, 1.0F, 2.0F); } } } } }.processFilledCircle(world, x, y, z, radius);



      }
      




    });
    register(new BrewActionBlockCircle(new BrewItemKey(Blocks.field_150327_N), new BrewNamePart("witchery:brew.flowers"), new AltarPower(200), new EffectLevel(1))
    {
      private final Block[] BLOCKS = { Blocks.field_150327_N, Blocks.field_150328_O, Blocks.field_150328_O, Blocks.field_150328_O, Blocks.field_150328_O, Blocks.field_150328_O, Blocks.field_150328_O, Blocks.field_150328_O, Blocks.field_150328_O, Blocks.field_150328_O };
      


      protected void onCircleBlock(World world, int x, int y, int z, ModifiersEffect modifiers, Count counter)
      {
        for (int dy = y - 1; dy <= y + 1; dy++) {
          if ((BlockUtil.isReplaceableBlock(world, x, dy, z, caster)) && (!world.func_147439_a(x, dy, z).func_149688_o().func_76224_d()) && (Blocks.field_150327_N.func_149742_c(world, x, dy, z)))
          {

            if (field_73012_v.nextInt(8 - modifiers.getStrength()) == 0) {
              int flowerIndex = field_73012_v.nextInt(BLOCKS.length);
              int flowerMeta = Math.max(flowerIndex - 1, 0);
              world.func_147465_d(x, dy, z, BLOCKS[flowerIndex], flowerMeta, 3);
            }
            
          }
        }
      }
    });
    register(new BrewActionBlockCircle(Dye.BONE_MEAL.getBrewItemKey(), new BrewNamePart("witchery:brew.fertilization"), new AltarPower(250), new EffectLevel(1))
    {
      private final ItemStack BONEMEAL = Dye.BONE_MEAL.createStack();
      
      protected void onCircleBlock(World world, int x, int y, int z, ModifiersEffect modifiers, Count counter)
      {
        for (int dy = y + 1; dy >= y - 1; dy--) {
          Block block = world.func_147439_a(x, dy, z);
          if (((block instanceof IGrowable)) || ((block instanceof IPlantable)) || ((block instanceof BlockWitchCrop)))
          {
            for (int i = 0; i <= modifiers.getStrength(); i++) {
              ItemDye.func_150919_a(BONEMEAL, world, x, dy, z);
            }
            break;
          }
          
        }
      }
    });
    register(new BrewActionBlockCircle(new BrewItemKey(Items.field_151034_e), new BrewNamePart("witchery:brew.harvesting"), new AltarPower(0), new EffectLevel(1))
    {
      protected void onCircleBlock(World world, int x, int y, int z, ModifiersEffect modifiers, Count counter)
      {
        for (int dy = y - 1; dy <= y + 1; dy++) {
          Block block = world.func_147439_a(x, dy, z);
          if ((block instanceof net.minecraft.block.BlockBush)) {
            int meta = world.func_72805_g(x, dy, z);
            List<ItemStack> drops = new ArrayList();
            drops.addAll(block.getDrops(world, x, dy, z, meta, Math.max(modifiers.getStrength() - 1, 0)));
            
            world.func_147468_f(x, dy, z);
            counter.increment();
            if (field_73012_v.nextInt(counter.get()) == 0) {
              world.func_72926_e(2001, x, dy, z, Block.func_149682_b(block) + (meta << 12));
            }
            for (ItemStack drop : drops) {
              world.func_72838_d(new EntityItem(world, 0.5D + x, 0.5D + dy, 0.5D + z, drop.func_77946_l()));
            }
            
          }
        }
      }
    });
    register(new BrewActionBlockCircle(new BrewItemKey(Blocks.field_150346_d), new BrewNamePart("witchery:brew.tilling"), new AltarPower(0), new EffectLevel(1))
    {
      protected void onCircleBlock(World world, int x, int y, int z, ModifiersEffect modifiers, Count counter)
      {
        for (int dy = y - 1; dy <= y + 1; dy++) {
          Block block = world.func_147439_a(x, dy, z);
          if (((block == Blocks.field_150349_c) || (block == Blocks.field_150346_d) || ((block == Blocks.field_150354_m) && (modifiers.getStrength() > 0)) || ((block == Blocks.field_150424_aL) && (modifiers.getStrength() > 1)) || ((block == Blocks.field_150425_aM) && (modifiers.getStrength() > 2))) && (world.func_147437_c(x, dy + 1, z)))
          {


            world.func_147449_b(x, dy, z, Blocks.field_150458_ak);
            counter.increment();
            if (field_73012_v.nextInt(counter.get()) == 0) {
              world.func_72908_a(x + 0.5F, dy + 0.5F, z + 0.5F, field_149762_H.func_150498_e(), (field_149762_H.func_150497_c() + 1.0F) / 2.0F, field_149762_H.func_150494_d() * 0.8F);
            }
            
          }
          
        }
        
      }
    });
    register(new com.emoniph.witchery.brewing.action.effect.BrewActionPlanting(new BrewItemKey(Items.field_151014_N), new BrewNamePart("witchery:brew.planting"), new AltarPower(0), new EffectLevel(1)));
    

    register(new BrewActionEffect(new BrewItemKey(Blocks.field_150338_P), new BrewNamePart("witchery:brew.pruning"), new AltarPower(0), new Probability(1.0D), new EffectLevel(1))
    {

      protected void doApplyToBlock(World world, int posX, int posY, int posZ, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack stack)
      {
        int BLOCK_RADIUS = radius - 1;
        int BLOCK_RADIUS_SQ = BLOCK_RADIUS * BLOCK_RADIUS;
        int blockX = MathHelper.func_76128_c(posX);
        int blockY = MathHelper.func_76128_c(posY);
        int blockZ = MathHelper.func_76128_c(posZ);
        for (int y = blockY - BLOCK_RADIUS; y <= blockY + BLOCK_RADIUS; y++) {
          for (int x = blockX - BLOCK_RADIUS; x <= blockX + BLOCK_RADIUS; x++) {
            for (int z = blockZ - BLOCK_RADIUS; z <= blockZ + BLOCK_RADIUS; z++) {
              if ((Coord.distanceSq(x, y, z, blockX, blockY, blockZ) <= BLOCK_RADIUS_SQ) && 
                (BlockProtect.checkModsForBreakOK(world, x, y, z, caster))) {
                Material material = world.func_147439_a(x, y, z).func_149688_o();
                if ((material != null) && ((material == Material.field_151584_j) || (((material == Material.field_151585_k) || (material == Material.field_151582_l)) && (material.func_76222_j()))))
                {

                  Block blockID = world.func_147439_a(x, y, z);
                  if ((!(blockID instanceof com.emoniph.witchery.blocks.BlockCircle)) && (!(blockID instanceof BlockCircleGlyph)))
                  {
                    blockID.func_149697_b(world, x, y, z, world.func_72805_g(x, y, z), modifiers.getStrength());
                    
                    world.func_147468_f(x, y, z);
                  }
                  
                }
                
              }
            }
          }
        }
      }
    });
    register(new com.emoniph.witchery.brewing.action.effect.BrewActionFelling(Items.field_151007_F, 0, new AltarPower(0), new EffectLevel(1)));
    









    register(new BrewActionEffect(new BrewItemKey(Items.field_151145_ak), new BrewNamePart("witchery:brew.pulverisation"), new AltarPower(250), new Probability(1.0D), new EffectLevel(1))
    {


      protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, final ModifiersEffect modifiers, ItemStack actionStack)
      {

        if (!field_72995_K) {
          new BlockActionSphere()
          {
            public void onBlock(World world, int x, int y, int z) {
              Block block = world.func_147439_a(x, y, z);
              if ((BlockProtect.checkModsForBreakOK(world, x, y, z, block, world.func_72805_g(x, y, z), modifierscaster)) && (BlockProtect.canBreak(block, world)))
              {

                if (block == Blocks.field_150348_b) {
                  world.func_147449_b(x, y, z, Blocks.field_150347_e);
                } else if (block == Blocks.field_150347_e) {
                  world.func_147449_b(x, y, z, Blocks.field_150351_n);
                } else if ((block == Blocks.field_150351_n) || (block == Blocks.field_150322_A)) {
                  world.func_147449_b(x, y, z, Blocks.field_150354_m);
                } else if (block == Blocks.field_150354_m) {
                  world.func_147468_f(x, y, z);
                  EntityUtil.spawnEntityInWorld(world, new EntityItem(world, x, y, z, new ItemStack(Blocks.field_150354_m)));
                }
                else {
                  return;
                }
                ParticleEffect.SMOKE.send(SoundEffect.NONE, world, x, y + 1, z, 0.25D, 0.25D, 16); } } }.drawFilledSphere(world, x, y, z, Math.max(radius - 1, 1));
          


          SoundEffect.MOB_GHAST_FIREBALL.playAt(world, x, y, z, 0.5F, 2.0F);
        }
        
      }
      
    });
    register(new BrewActionEffect(new BrewItemKey(Blocks.field_150354_m), new BrewNamePart("witchery:brew.tidehold"), new AltarPower(0), new Probability(1.0D), new EffectLevel(1))
    {

      protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, final ModifiersEffect modifiers, ItemStack actionStack)
      {

        if (!field_72995_K) {
          new BlockActionSphere()
          {
            public void onBlock(World world, int x, int y, int z) {
              Block block = world.func_147439_a(x, y, z);
              if ((block == Blocks.field_150355_j) || (block == Blocks.field_150358_i))
                BlocksSLURP.replaceBlockAt(world, x, y, z, modifiers.getModifiedDuration(TimeUtil.secsToTicks(10))); } }.drawFilledSphere(world, x, y, z, Math.max(radius + 2, 1));

        }
        

      }
      

    });
    register(new BrewActionLilify(new BrewItemKey(Blocks.field_150392_bi), new BrewNamePart("witchery:brew.lilify"), new AltarPower(200), new EffectLevel(1)));
    

    register(new BrewPotionEffect(ItemsGENERIC.itemWolfsbane.getBrewItemKey(), new BrewNamePart("witchery:potion.wolfsbane"), new AltarPower(0), new Probability(1.0D), PotionsWOLFSBANE, TimeUtil.secsToTicks(60), new EffectLevel(1)));
    




    register(new BrewActionEffect(ItemsGENERIC.itemPurifiedMilk.getBrewItemKey(), new BrewNamePart("witchery:brew.removedebuffs"), new AltarPower(200), new Probability(1.0D), new EffectLevel(2))
    {

      protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack stack)
      {
        List<Potion> effectsToRemove = new ArrayList();
        Collection<PotionEffect> effects = targetEntity.func_70651_bq();
        for (PotionEffect effect : effects) {
          Potion potion = Potion.field_76425_a[effect.func_76456_a()];
          if (PotionBase.isDebuff(potion)) {
            if ((PotionBase.isCurable(potion)) && (effect.func_76458_c() <= modifiers.getStrength())) {
              effectsToRemove.add(potion);
            }
            
            if ((potion == PotionsDISEASED) && (modifiers.getStrength() >= 2)) {
              effectsToRemove.add(potion);
              if ((targetEntity instanceof EntityPlayer)) {
                EntityPlayer player = (EntityPlayer)targetEntity;
                ExtendedPlayer playerEx = ExtendedPlayer.get(player);
                if (playerEx != null) {
                  playerEx.clearCachedIncurablePotionEffect(potion);
                }
              }
              if (modifiers.getStrength() >= 3) {
                int R = 9;
                Coord coord = new Coord(targetEntity);
                for (int x = -9; x <= 9; x++) {
                  for (int z = -9; z <= 9; z++) {
                    for (int y = -9; y <= 9; y++) {
                      Block block = world.func_147439_a(x + x, y + y, z + z);
                      if (block == BlocksDISEASE) {
                        world.func_147468_f(x + x, y + y, z + z);
                      }
                    }
                  }
                }
              }
            }
          }
        }
        
        for (Potion potion : effectsToRemove) {
          targetEntity.func_82170_o(field_76415_H);
        }
      }
      

      protected void doApplyRitualToEntity(World world, EntityLivingBase targetEntity, ModifiersRitual ritualModifiers, ModifiersEffect modifiers, ItemStack stack)
      {
        List<Potion> effectsToRemove = new ArrayList();
        List<PotionEffect> effectsToAdd = new ArrayList();
        Collection<PotionEffect> effects = targetEntity.func_70651_bq();
        int modifiedStrength = modifiers.getStrength();
        for (PotionEffect effect : effects) {
          Potion potion = Potion.field_76425_a[effect.func_76456_a()];
          if (PotionBase.isDebuff(potion)) {
            if (effect.func_76458_c() < modifiedStrength) {
              effectsToRemove.add(potion);
              if (field_73012_v.nextDouble() < 0.01D) {
                effectsToAdd.add(new PotionEffect(effect.func_76456_a(), effect.func_76459_b(), effect.func_76458_c() + 1));
              }
            }
            else if (effect.func_76458_c() == modifiedStrength) {
              effectsToRemove.add(potion);
              if (field_73012_v.nextDouble() < 0.25D) {
                effectsToAdd.add(new PotionEffect(effect.func_76456_a(), effect.func_76459_b(), effect.func_76458_c() + 1));
              }
            }
            else {
              effectsToRemove.add(potion);
              if (field_73012_v.nextDouble() < 0.75D) {
                effectsToAdd.add(new PotionEffect(effect.func_76456_a(), effect.func_76459_b(), effect.func_76458_c() + 1));
              }
            }
          }
        }
        
        for (Potion potion : effectsToRemove) {
          targetEntity.func_82170_o(field_76415_H);
          if ((!PotionBase.isCurable(potion)) && ((targetEntity instanceof EntityPlayer))) {
            EntityPlayer player = (EntityPlayer)targetEntity;
            ExtendedPlayer playerEx = ExtendedPlayer.get(player);
            if (playerEx != null) {
              playerEx.clearCachedIncurablePotionEffect(potion);
            }
          }
        }
        for (PotionEffect potionEffect : effectsToAdd) {
          targetEntity.func_70690_d(potionEffect);
        }
        
        if (effectsToAdd.isEmpty()) {
          ParticleEffect.SPELL.send(SoundEffect.RANDOM_LEVELUP, targetEntity, 0.5D, 2.0D, 16);
        } else {
          ParticleEffect.MOB_SPELL.send(SoundEffect.MOB_ENDERDRAGON_GROWL, targetEntity, 0.5D, 2.0D, 16);
        }
        
      }
    });
    register(new BrewActionEffect(ItemsGENERIC.itemReekOfMisfortune.getBrewItemKey(), new BrewNamePart("witchery:brew.removebuffs"), new AltarPower(250), new Probability(1.0D), new EffectLevel(2))
    {

      protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack stack)
      {

        List<Potion> effectsToRemove = new ArrayList();
        Collection<PotionEffect> effects = targetEntity.func_70651_bq();
        for (PotionEffect effect : effects) {
          Potion potion = Potion.field_76425_a[effect.func_76456_a()];
          if ((!PotionBase.isDebuff(potion)) && (PotionBase.isCurable(potion)) && (effect.func_76458_c() <= modifiers.getStrength()))
          {
            effectsToRemove.add(potion);
          }
        }
        for (Potion potion : effectsToRemove) {
          targetEntity.func_82170_o(field_76415_H);
        }
        
      }
      
    });
    register(new BrewActionEffect(new BrewItemKey(Blocks.field_150347_e), new BrewNamePart("witchery:brew.lavahold"), new AltarPower(100), new Probability(1.0D), new EffectLevel(2))
    {

      protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, final ModifiersEffect modifiers, ItemStack actionStack)
      {

        if (!field_72995_K) {
          new BlockActionSphere()
          {
            public void onBlock(World world, int x, int y, int z) {
              Block block = world.func_147439_a(x, y, z);
              if ((block == Blocks.field_150353_l) || (block == Blocks.field_150356_k))
                BlocksSLURP.replaceBlockAt(world, x, y, z, modifiers.getModifiedDuration(TimeUtil.secsToTicks(10))); } }.drawFilledSphere(world, x, y, z, Math.max(radius + 2, 1));

        }
        

      }
      

    });
    register(new BrewPotionEffect(new BrewItemKey(BlocksBRAMBLE, 0), new BrewNamePart("witchery:potion.repellattacker"), new AltarPower(250), new Probability(1.0D), PotionsREPELL_ATTACKER, TimeUtil.secsToTicks(90), new EffectLevel(2)));
    


    register(new BrewPotionEffect(new BrewItemKey(Blocks.field_150351_n), new BrewNamePart("witchery:potion.gasmask"), new AltarPower(100), new Probability(1.0D), PotionsGAS_MASK, TimeUtil.secsToTicks(90), new EffectLevel(2)));
    


    register(new BrewPotionEffect(new BrewItemKey(Items.field_151070_bp), new BrewNamePart("witchery:brew.poison"), new AltarPower(0), new Probability(1.0D), Potion.field_76436_u, TimeUtil.secsToTicks(45), new EffectLevel(2)));
    


    register(new BrewPotionEffect(new BrewItemKey(Items.field_151102_aT), new BrewNamePart("witchery:brew.movespeed", "witchery:brew.moveslow"), new AltarPower(100), new Probability(1.0D), Potion.field_76424_c, TimeUtil.minsToTicks(3), Potion.field_76421_d, TimeUtil.secsToTicks(90), new EffectLevel(2)));
    


    register(new BrewPotionEffect(new BrewItemKey(Items.field_151115_aP, 3), new BrewNamePart("witchery:brew.waterbreathing"), new AltarPower(100), new Probability(1.0D), Potion.field_76427_o, TimeUtil.minsToTicks(3), new EffectLevel(2)));
    


    register(new BrewPotionEffect(new BrewItemKey(Items.field_151064_bs), new BrewNamePart("witchery:brew.resistfire"), new AltarPower(100), new Probability(1.0D), Potion.field_76426_n, TimeUtil.minsToTicks(3), new EffectLevel(2)));
    


    register(new BrewPotionEffect(new BrewItemKey(Items.field_151150_bK), new BrewNamePart("witchery:brew.nightvision", "witchery:brew.invisibility"), new AltarPower(200), new Probability(1.0D), Potion.field_76439_r, TimeUtil.minsToTicks(3), Potion.field_76441_p, TimeUtil.minsToTicks(3), new EffectLevel(2)));
    



    register(new BrewPotionEffect(new BrewItemKey(Items.field_151073_bk), new BrewNamePart("witchery:brew.regeneration", "witchery:brew.poison"), new AltarPower(200), new Probability(1.0D), Potion.field_76428_l, TimeUtil.secsToTicks(45), Potion.field_76436_u, TimeUtil.secsToTicks(45), new EffectLevel(2)));
    



    register(new BrewPotionEffect(new BrewItemKey(Items.field_151065_br), new BrewNamePart("witchery:brew.damageboost", "witchery:brew.weakness"), new AltarPower(200), new Probability(1.0D), Potion.field_76420_g, TimeUtil.minsToTicks(3), Potion.field_76437_t, TimeUtil.secsToTicks(90), new EffectLevel(2)));
    



    register(new BrewPotionEffect(new BrewItemKey(Items.field_151060_bw), new BrewNamePart("witchery:brew.healing", "witchery:brew.harming"), new AltarPower(200), new Probability(1.0D), Potion.field_76432_h, 0L, Potion.field_76433_i, 0L, new EffectLevel(2)).setStrengthCeiling(1));
    


    register(new BrewPotionEffect(new BrewItemKey(Items.field_151120_aE), new BrewNamePart("witchery:brew.floating"), new AltarPower(250), new Probability(1.0D), PotionsFLOATING, TimeUtil.secsToTicks(90), new EffectLevel(2)));
    


    register(new BrewPotionEffect(new BrewItemKey(Items.field_151116_aA), new BrewNamePart("witchery:brew.jump"), new AltarPower(200), new Probability(1.0D), Potion.field_76430_j, TimeUtil.minsToTicks(3), new EffectLevel(2)));
    


    register(new BrewPotionEffect(new BrewItemKey(Items.field_151008_G), new BrewNamePart("witchery:brew.featherfall"), new AltarPower(100), new Probability(1.0D), PotionsFEATHER_FALL, TimeUtil.minsToTicks(1), new EffectLevel(2)));
    


    register(new BrewPotionEffect(new BrewItemKey(Blocks.field_150337_Q), new BrewNamePart("witchery:potion.poisonweapons"), new AltarPower(200), new Probability(1.0D), PotionsPOISON_WEAPONS, TimeUtil.secsToTicks(90), new EffectLevel(2)));
    


    register(new BrewPotionEffect(new BrewItemKey(Blocks.field_150321_G), new BrewNamePart("witchery:potion.reflectprojectiles", "witchery:potion.attractprojectiles"), new AltarPower(250), new Probability(1.0D), PotionsREFLECT_PROJECTILES, TimeUtil.secsToTicks(90), PotionsATTRACT_PROJECTILES, TimeUtil.secsToTicks(45), new EffectLevel(2)));
    



    register(new BrewActionEffect(ItemsGENERIC.itemBatBall.getBrewItemKey(), new BrewNamePart("witchery:brew.batburst"), new AltarPower(1000), new Probability(1.0D), new EffectLevel(2))
    {

      protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack)
      {
        int BAT_COUNT = ((powerScalingFactor != 1.0D) || (isGlancing) || (strengthPenalty > 0) ? 1 : 10) + modifiers.getStrength();
        

        explodeBats(world, new Coord(x, y, z), side, BAT_COUNT);
      }
      

      protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
      {
        if ((!(targetEntity instanceof EntityOwl)) && (!(targetEntity instanceof EntityBat))) {
          int BAT_COUNT = ((powerScalingFactor != 1.0D) || (isGlancing) ? 1 : 10) + modifiers.getStrength();
          
          if (((powerScalingFactor == 1.0D) && (!isGlancing)) || (field_73012_v.nextInt(20) == 0))
          {
            explodeBats(world, new Coord(targetEntity), ForgeDirection.UP, BAT_COUNT);
          }
        }
      }
      
      private void explodeBats(World world, Coord coord, ForgeDirection side, int bats) {
        int x = x + offsetX;
        int z = z + offsetZ;
        int y = y + offsetY;
        
        int NUM_BATS = bats;
        for (int i = 0; i < NUM_BATS; i++) {
          EntityBat bat = new EntityBat(world);
          EntityUtil.setNoDrops(bat);
          bat.func_70012_b(x, y, z, 0.0F, 0.0F);
          world.func_72838_d(bat);
        }
        ParticleEffect.EXPLODE.send(SoundEffect.MOB_ENDERMEN_PORTAL, world, 0.5D + x, 0.5D + y, 0.5D + z, 3.0D, 3.0D, 16);
      }
      

    });
    register(new BrewActionEffect(ItemsGENERIC.itemOwletsWing.getBrewItemKey(), new BrewNamePart("witchery:brew.bodega"), new AltarPower(1000), new Probability(1.0D), new EffectLevel(2))
    {

      protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
      {
        if ((caster != null) && (Familiar.hasActiveBroomMasteryFamiliar(caster)) && 
          (!(targetEntity instanceof EntityOwl)) && (!(targetEntity instanceof EntityBat))) {
          int BIRD_COUNT = ((powerScalingFactor != 1.0D) || (isGlancing) ? 1 : 3 + field_73012_v.nextInt(2)) + modifiers.getStrength();
          
          if (((powerScalingFactor == 1.0D) && (!isGlancing)) || (field_73012_v.nextInt(20) == 0))
          {
            explodeBirds(world, targetEntity, BIRD_COUNT);
          }
        }
      }
      
      private void explodeBirds(World world, EntityLivingBase victim, int birds)
      {
        for (int i = 0; i < birds; i++) {
          EntityOwl owl = new EntityOwl(world);
          owl.func_70012_b(field_70165_t - 2.0D + field_73012_v.nextInt(5), field_70163_u + field_70131_O + 1.0D + field_73012_v.nextInt(2), field_70161_v - 2.0D + field_73012_v.nextInt(5), 0.0F, 0.0F);
          
          owl.func_70624_b(victim);
          owl.setTimeToLive(400);
          world.func_72838_d(owl);
          
          ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, owl, 1.0D, 1.0D, 16);
        }
        
      }
    });
    register(new BrewActionEffect(ItemsGENERIC.itemBreathOfTheGoddess.getBrewItemKey(), new BrewNamePart("witchery:brew.airhike"), new AltarPower(750), new Probability(1.0D), new EffectLevel(2))
    {

      protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
      {

        double motionY = 0.6D + 0.2D * modifiers.getStrength();
        field_70143_R = 0.0F;
        if ((targetEntity instanceof EntityPlayer)) {
          Witchery.packetPipeline.sendTo(new PacketPushTarget(0.0D, motionY, 0.0D), (EntityPlayer)targetEntity);
        }
        else
        {
          field_70181_x = motionY;
        }
        
      }
    });
    register(new BrewActionEffect(new BrewItemKey(Items.field_151123_aH), new BrewNamePart("witchery:brew.frogtongue"), new AltarPower(150), new Probability(1.0D), new EffectLevel(2))
    {

      protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
      {
        if ((modifiers.getStrength() > 0) && (!(caster instanceof FakePlayer))) {
          EntityUtil.pullTowards(world, targetEntity, new EntityPosition(caster), 0.05D, 0.0D);
        } else {
          EntityUtil.pullTowards(world, targetEntity, impactLocation, 0.05D, 0.0D);
        }
      }
      

      protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack stack)
      {
        double R = radius;
        double R_SQ = R * R;
        
        EntityPosition position = impactLocation;
        AxisAlignedBB bb = AxisAlignedBB.func_72330_a(x - R, y - R, z - R, x + R, y + R, z + R);
        
        List<Entity> list1 = world.func_72872_a(Entity.class, bb);
        
        for (Entity targetEntity : list1) {
          if (!(targetEntity instanceof EntityLivingBase)) {
            double distanceSq = position.getDistanceSqToEntity(targetEntity);
            if (distanceSq <= R_SQ) {
              if ((modifiers.getStrength() > 0) && (!(caster instanceof FakePlayer))) {
                EntityUtil.pullTowards(world, targetEntity, new EntityPosition(caster), 0.05D, 0.0D);
              }
              else {
                EntityUtil.pullTowards(world, targetEntity, impactLocation, 0.05D, 0.0D);
              }
              
            }
            
          }
        }
      }
    });
    register(new BrewActionEffect(new BrewItemKey(Blocks.field_150423_aK), new BrewNamePart("witchery:brew.harmundead"), new AltarPower(200), new Probability(1.0D), new EffectLevel(2))
    {

      protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
      {

        int strength = Math.min(modifiers.getStrength(), strengthCeilingDisabled ? 3 : 1);
        if (CreatureUtil.isUndead(targetEntity)) {
          targetEntity.func_70097_a(DamageSource.func_76354_b(caster, caster), MathHelper.func_76143_f((10 << strength) * powerScalingFactor));
        }
        else
        {
          targetEntity.func_70097_a(DamageSource.func_76354_b(caster, caster), MathHelper.func_76143_f((3 << strength) * powerScalingFactor));

        }
        
      }
      

    });
    register(new BrewActionEffect(new BrewItemKey(Blocks.field_150328_O, 1), new BrewNamePart("witchery:brew.harminsects"), new AltarPower(200), new Probability(1.0D), new EffectLevel(2))
    {

      protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
      {
        int strength = Math.min(modifiers.getStrength(), strengthCeilingDisabled ? 3 : 1);
        if (CreatureUtil.isInsect(targetEntity)) {
          targetEntity.func_70097_a(DamageSource.func_76354_b(caster, caster), MathHelper.func_76143_f((10 << strength) * powerScalingFactor));
        }
        else
        {
          targetEntity.func_70097_a(DamageSource.func_76354_b(caster, caster), MathHelper.func_76143_f((3 << strength) * powerScalingFactor));

        }
        
      }
      

    });
    register(new BrewActionEffect(ItemsGENERIC.itemOilOfVitriol.getBrewItemKey(), new BrewNamePart("witchery:brew.erosion"), new AltarPower(200), new Probability(1.0D), new EffectLevel(2))
    {

      protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, final ModifiersEffect modifiers, ItemStack actionStack)
      {
        final Count obsidianCount = new Count();
        for (int r = radius; r > 0; r--) {
          int depth = radius - r;
          new BlockActionCircle()
          {
            public void onBlock(World world, int x, int y, int z) {
              Block block = world.func_147439_a(x, y, z);
              if ((BlockProtect.checkModsForBreakOK(world, x, y, z, block, world.func_72805_g(x, y, z), modifierscaster)) && (BlockProtect.canBreak(block, world)))
              {

                world.func_147468_f(x, y, z);
                ParticleEffect.SPLASH.send(SoundEffect.NONE, world, x, y, z, 0.5D, 0.5D, 16);
                obsidianCount.incrementBy(block == Blocks.field_150343_Z ? 1 : 0); } } }.processFilledCircle(world, x, y, z, r);
        }
        


        SoundEffect.RANDOM_FIZZ.playAt(world, x, y, z, 1.0F, 2.0F);
        SpawnUtil.spawnEntityItem(world, x, y, z, Blocks.field_150343_Z, obsidianCount.get());
      }
      

      protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
      {
        if (field_73012_v.nextInt(MathHelper.func_76143_f(5.0D / powerScalingFactor)) == 0) {
          targetEntity.func_70097_a(DamageSource.func_76356_a(targetEntity, caster), MathHelper.func_76143_f(8.0D * powerScalingFactor));
        }
        
        for (int slot = 0; slot < 5; slot++) {
          ItemStack stack = targetEntity.func_71124_b(slot);
          if ((stack != null) && (stack.func_77984_f())) {
            stack.func_77972_a(MathHelper.func_76143_f((50.0D + field_73012_v.nextInt(25)) * powerScalingFactor), caster);
            

            if ((stack.func_77960_j() >= stack.func_77958_k()) || (field_77994_a <= 0)) {
              targetEntity.func_70062_b(slot, null);
            }
            
          }
          
        }
      }
    });
    register(new BrewActionEffect(new BrewItemKey(Blocks.field_150424_aL), new BrewNamePart("witchery:brew.levelling"), new AltarPower(200), new Probability(1.0D), new EffectLevel(2))
    {

      protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int r, final ModifiersEffect modifiers, ItemStack actionStack)
      {

        final int y0 = ritualised ? y - 1 : y;
        int radius = ritualised ? r + (modifiers.getStrength() + 1) * 3 : r;
        
        final Count dirt = new Count();
        final Count stone = new Count();
        final Count sand = new Count();
        final Count sandstone = new Count();
        final Count netherrack = new Count();
        final Count endstone = new Count();
        
        final int s = modifiers.getStrength();
        int defaultAmount = ritualised ? 64 + 32 * modifiers.getStrength() : 16;
        Block hitBlock = world.func_147439_a(x, y0, z);
        if (hitBlock == Blocks.field_150346_d) {
          dirt.incrementBy(defaultAmount);
        } else if (hitBlock == Blocks.field_150348_b) {
          stone.incrementBy(defaultAmount);
        } else if (hitBlock == Blocks.field_150354_m) {
          sand.incrementBy(defaultAmount);
        } else if (hitBlock == Blocks.field_150322_A) {
          sandstone.incrementBy(defaultAmount);
        } else if (hitBlock == Blocks.field_150424_aL) {
          netherrack.incrementBy(defaultAmount);
        } else if (hitBlock == Blocks.field_150377_bs) {
          endstone.incrementBy(defaultAmount);
        }
        
        new BlockActionCircle()
        {
          public void onBlock(World world, int x, int y, int z) {
            for (int dy = y0 + 1; dy < y0 + 4 + s; dy++) {
              Block block = world.func_147439_a(x, dy, z);
              if ((block != Blocks.field_150350_a) && (BlockProtect.checkModsForBreakOK(world, x, dy, z, block, world.func_72805_g(x, dy, z), modifierscaster)) && (BlockProtect.canBreak(block, world)))
              {


                if (block == Blocks.field_150348_b) {
                  stone.increment();
                } else if (block == Blocks.field_150354_m) {
                  sand.increment();
                } else if (block == Blocks.field_150322_A) {
                  sandstone.increment();
                } else if (block == Blocks.field_150424_aL) {
                  netherrack.increment();
                } else if (block == Blocks.field_150377_bs) {
                  endstone.increment();
                } else {
                  dirt.increment();
                }
                world.func_147468_f(x, dy, z);
                ParticleEffect.SMOKE.send(SoundEffect.NONE, world, x, dy, z, 0.5D, 0.5D, 16); } } } }.processFilledCircle(world, x, y0, z, radius);
        




        for (int newy = y0; newy >= y0 - (4 + s); newy--) {
          final int dy = newy;
          new BlockActionCircle()
          {
            public void onBlock(World world, int x, int y, int z)
            {
              Block block = world.func_147439_a(x, dy, z);
              if (BlockUtil.isReplaceableBlock(world, x, dy, z, modifierscaster)) {
                if (endstone.get() > 0) {
                  endstone.decrement();
                  world.func_147449_b(x, dy, z, Blocks.field_150377_bs);
                } else if (netherrack.get() > 0) {
                  netherrack.decrement();
                  world.func_147449_b(x, dy, z, Blocks.field_150424_aL);
                } else if (sandstone.get() > 0) {
                  sandstone.decrement();
                  world.func_147449_b(x, dy, z, Blocks.field_150322_A);
                } else if (sand.get() > 0) {
                  sand.decrement();
                  world.func_147449_b(x, dy, z, Blocks.field_150354_m);
                } else if (stone.get() > 0) {
                  stone.decrement();
                  world.func_147449_b(x, dy, z, Blocks.field_150348_b);
                } else if (dirt.get() > 0) {
                  dirt.decrement();
                  world.func_147449_b(x, dy, z, Blocks.field_150346_d);
                } else {
                  return;
                }
                
                ParticleEffect.SMOKE.send(SoundEffect.NONE, world, x, dy, z, 0.5D, 0.5D, 16); } } }.processFilledCircle(world, x, y0, z, radius);
        }
        




        SoundEffect.RANDOM_FIZZ.playAt(world, x, y0, z, 1.0F, 2.0F);
      }
      

    });
    register(new BrewActionEffect(ItemsGENERIC.itemWeb.getBrewItemKey(), new BrewNamePart("witchery:brew.webs"), new AltarPower(200), new Probability(1.0D), new EffectLevel(2))
    {

      protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack)
      {
        placeWeb(world, new Coord(x, y, z), modifiers, side, caster);
      }
      

      protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
      {
        placeWeb(world, new Coord(targetEntity), modifiers, ForgeDirection.UNKNOWN, caster);
      }
      
      private void placeWeb(World world, Coord coord, ModifiersEffect modifiers, ForgeDirection side, EntityPlayer source)
      {
        Coord location = BlockUtil.getClosestPlantableBlock(world, x, y, z, side, source, true);
        
        if (location != null) {
          Block web = BlocksWEB;
          BlockUtil.setBlockIfReplaceable(world, x, y, z, web);
          
          if (modifiers.getStrength() > 0) {
            BlockUtil.setBlockIfReplaceable(world, x + 1, y, z, web);
            BlockUtil.setBlockIfReplaceable(world, x - 1, y, z, web);
            BlockUtil.setBlockIfReplaceable(world, x, y, z + 1, web);
            BlockUtil.setBlockIfReplaceable(world, x, y, z - 1, web);
            BlockUtil.setBlockIfReplaceable(world, x, y + 1, z, web);
            BlockUtil.setBlockIfReplaceable(world, x, y - 1, z, web);
          }
          
          if (modifiers.getStrength() > 1) {
            BlockUtil.setBlockIfReplaceable(world, x + 1, y, z + 1, web);
            BlockUtil.setBlockIfReplaceable(world, x - 1, y, z + 1, web);
            BlockUtil.setBlockIfReplaceable(world, x + 1, y, z - 1, web);
            BlockUtil.setBlockIfReplaceable(world, x - 1, y, z - 1, web);
            BlockUtil.setBlockIfReplaceable(world, x + 1, y + 1, z, web);
            BlockUtil.setBlockIfReplaceable(world, x - 1, y + 1, z, web);
            BlockUtil.setBlockIfReplaceable(world, x, y + 1, z + 1, web);
            BlockUtil.setBlockIfReplaceable(world, x, y + 1, z - 1, web);
            BlockUtil.setBlockIfReplaceable(world, x, y - 1, z + 1, web);
            BlockUtil.setBlockIfReplaceable(world, x, y - 1, z - 1, web);
            BlockUtil.setBlockIfReplaceable(world, x + 1, y - 1, z, web);
            BlockUtil.setBlockIfReplaceable(world, x - 1, y - 1, z, web);
          }
          
        }
        
      }
    });
    register(new BrewActionEffect(new BrewItemKey(Blocks.field_150395_bd), new BrewNamePart("witchery:brew.vines").setBaseDuration(TimeUtil.secsToTicks(90)), new AltarPower(150), new Probability(1.0D), new EffectLevel(2))
    {

      protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack)
      {

        int meta = 0;
        switch (WitcheryBrewRegistry.82.$SwitchMap$net$minecraftforge$common$util$ForgeDirection[side.ordinal()]) {
        case 1: 
          meta = 2;
          break;
        case 2: 
          meta = 3;
          break;
        case 3: 
          meta = 4;
          break;
        case 4: 
          meta = 5;
          break;
        default: 
          return;
        }
        Block vine = BlocksVINE;
        
        int newX = x + offsetX;
        int newZ = z + offsetZ;
        int offsetY = 0;
        

        while ((BlockUtil.isReplaceableBlock(world, newX, y + offsetY, newZ)) && (y + offsetY > 0) && ((modifiers.getStrength() > 0) || (world.func_147439_a(x, y + offsetY, z).func_149688_o().func_76220_a())))
        {
          world.func_147465_d(newX, y + offsetY, newZ, vine, meta, 3);
          offsetY--;
        }
        
        offsetY = 1;
        

        while (((BlockUtil.isReplaceableBlock(world, newX, y + offsetY, newZ)) || (world.func_147439_a(newX, y + offsetY, newZ).func_149688_o() == Material.field_151584_j)) && (world.func_147439_a(x, y + offsetY, z).func_149688_o().func_76220_a()) && (y + offsetY < 255)) {
          world.func_147465_d(newX, y + offsetY, newZ, vine, meta, 3);
          offsetY++;
        }
      }
      

      protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
      {
        BrewPotionEffect.applyPotionEffect(targetEntity, modifiers, PotionsWRAPPED_IN_VINE, TimeUtil.secsToTicks(90), noParticles, caster);

      }
      

    });
    register(new BrewActionEffect(new BrewItemKey(Blocks.field_150434_aF), new BrewNamePart("witchery:brew.thorns").setBaseDuration(TimeUtil.secsToTicks(90)), new AltarPower(150), new Probability(1.0D), new EffectLevel(2))
    {

      protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack)
      {

        Coord coord = null;
        if (world.func_147439_a(x, y, z) == BlocksCACTUS) {
          y++;
          while (world.func_147439_a(x, y, z) == BlocksCACTUS) {
            y++;
          }
          if (BlockUtil.isReplaceableBlock(world, x, y, z)) {
            coord = new Coord(x, y, z);
          }
        } else {
          coord = BlockUtil.getClosestPlantableBlock(world, x, y, z, side, caster);
        }
        
        if (coord != null) {
          int i = 0; for (int height = 3 + modifiers.getStrength(); i < height; i++) {
            if ((!BlockUtil.isReplaceableBlock(world, x, y + i, z)) || (y + i >= 255))
              break;
            world.func_147449_b(x, y + i, z, BlocksCACTUS);
          }
        }
      }
      




      protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
      {
        BrewPotionEffect.applyPotionEffect(targetEntity, modifiers, PotionsSPIKED, TimeUtil.secsToTicks(90), noParticles, caster);

      }
      

    });
    register(new BrewActionSprouting(ItemsGENERIC.itemBranchEnt.getBrewItemKey(), new BrewNamePart("witchery:brew.sprouting").setBaseDuration(TimeUtil.secsToTicks(15)), new AltarPower(350), new Probability(1.0D), new EffectLevel(2)));
    



    register(new BrewActionEffect(ItemsGENERIC.itemIcyNeedle.getBrewItemKey(), new BrewNamePart("witchery:brew.cold").setBaseDuration(TimeUtil.minsToTicks(3)), new AltarPower(200), new Probability(1.0D), new EffectLevel(2))
    {

      protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack)
      {

        if (BlockProtect.checkModsForBreakOK(world, x, y, z, caster)) {
          new BlockActionReplaceSphere()
          {
            protected boolean onShouldReplace(World world, int x, int y, int z, Block block) {
              return block.func_149688_o() == Material.field_151586_h;
            }
            


            protected void onReplaceBlock(World world, int x, int y, int z, Block block) { world.func_147449_b(x, y, z, Blocks.field_150432_aD); } }.replaceBlocks(world, x, y, z, 2 + 2 * modifiers.getStrength());
        }
      }
      



      protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
      {
        BrewPotionEffect.applyPotionEffect(targetEntity, modifiers, PotionsCHILLED, TimeUtil.minsToTicks(3), noParticles, caster);

      }
      

    });
    register(new BrewActionEffect(new BrewItemKey(Items.field_151055_y), new BrewNamePart("witchery:brew.knockback"), new AltarPower(200), new Probability(1.0D), new EffectLevel(2))
    {
      protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
      {
        double radiusSq;
        EntityPosition position;
        if (impactLocation != null) {
          EntityUtil.pushback(world, targetEntity, impactLocation, 1.0D + modifiers.getStrength() * powerScalingFactor, 0.5D + modifiers.getStrength() * 0.2D);
        }
        else
        {
          double radius = 3 + modifiers.getStrength();
          radiusSq = radius * radius;
          position = new EntityPosition(targetEntity);
          List<Entity> entities = world.func_72839_b(targetEntity, position.getBounds(radius));
          
          for (Entity entity : entities) {
            if ((((entity instanceof EntityLivingBase)) || ((entity instanceof EntityItem))) && (targetEntity.func_70068_e(entity) <= radiusSq))
            {
              EntityUtil.pushback(world, entity, position, 1.0D + modifiers.getStrength() * powerScalingFactor, 0.5D + modifiers.getStrength() * 0.2D);
            }
            
          }
          
        }
        
      }
      
    });
    register(new BrewActionEffect(new BrewItemKey(Blocks.field_150345_g, 0), new BrewNamePart("witchery:brew.treeoak"), new AltarPower(200), new Probability(1.0D), new EffectLevel(2))
    {

      protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack)
      {

        Coord location = BlockUtil.getClosestPlantableBlock(world, x, y, z, side, caster);
        if (location != null) {
          (modifiers.getStrength() > 1 ? new net.minecraft.world.gen.feature.WorldGenBigTree(true) : new WorldGenTrees(true)).func_76484_a(world, field_73012_v, x, y, z);
        }
        
      }
      
    });
    register(new BrewActionEffect(new BrewItemKey(Blocks.field_150345_g, 1), new BrewNamePart("witchery:brew.treespruce"), new AltarPower(200), new Probability(1.0D), new EffectLevel(2))
    {

      protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack)
      {
        Coord location = BlockUtil.getClosestPlantableBlock(world, x, y, z, side, caster);
        if (location != null) {
          (modifiers.getStrength() > 1 ? new net.minecraft.world.gen.feature.WorldGenMegaPineTree(false, field_73012_v.nextBoolean()) : new net.minecraft.world.gen.feature.WorldGenTaiga2(true)).func_76484_a(world, field_73012_v, x, y, z);
        }
        
      }
      

    });
    register(new BrewActionEffect(new BrewItemKey(Blocks.field_150345_g, 2), new BrewNamePart("witchery:brew.treebirch"), new AltarPower(200), new Probability(1.0D), new EffectLevel(2))
    {

      protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack)
      {
        Coord location = BlockUtil.getClosestPlantableBlock(world, x, y, z, side, caster);
        if (location != null) {
          new WorldGenForest(true, false).func_76484_a(world, field_73012_v, x, y, z);
        }
        
      }
      
    });
    register(new BrewActionEffect(new BrewItemKey(Blocks.field_150345_g, 3), new BrewNamePart("witchery:brew.treejungle"), new AltarPower(200), new Probability(1.0D), new EffectLevel(2))
    {

      protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack)
      {
        Coord location = BlockUtil.getClosestPlantableBlock(world, x, y, z, side, caster);
        if (location != null) {
          (strength > 2 ? new net.minecraft.world.gen.feature.WorldGenMegaJungle(true, 10, 20, 3, 3) : new WorldGenTrees(true, 4 + field_73012_v.nextInt(7), 3, 3, false)).func_76484_a(world, field_73012_v, x, y, z);
        }
        
      }
      

    });
    register(new BrewActionEffect(new BrewItemKey(Blocks.field_150345_g, 4), new BrewNamePart("witchery:brew.treeacacia"), new AltarPower(200), new Probability(1.0D), new EffectLevel(2))
    {

      protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack)
      {
        Coord location = BlockUtil.getClosestPlantableBlock(world, x, y, z, side, caster);
        if (location != null) {
          new WorldGenSavannaTree(true).func_76484_a(world, field_73012_v, x, y, z);
        }
        
      }
    });
    register(new BrewActionEffect(new BrewItemKey(Blocks.field_150345_g, 5), new BrewNamePart("witchery:brew.treedarkoak"), new AltarPower(200), new Probability(1.0D), new EffectLevel(2))
    {

      protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack)
      {
        Coord location = BlockUtil.getClosestPlantableBlock(world, x, y, z, side, caster);
        if (location != null) {
          new WorldGenCanopyTree(true).func_76484_a(world, field_73012_v, x, y, z);
        }
        
      }
    });
    register(new BrewActionEffect(new BrewItemKey(BlocksSAPLING, 0), new BrewNamePart("witchery:brew.treerowan"), new AltarPower(200), new Probability(1.0D), new EffectLevel(2))
    {

      protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack)
      {
        Coord location = BlockUtil.getClosestPlantableBlock(world, x, y, z, side, caster);
        if (location != null) {
          new WorldGenWitchTree(true, 5, 0, 0, 1, false).func_76484_a(world, field_73012_v, x, y, z);
        }
        
      }
      
    });
    register(new BrewActionEffect(new BrewItemKey(BlocksSAPLING, 1), new BrewNamePart("witchery:brew.treealder"), new AltarPower(200), new Probability(1.0D), new EffectLevel(2))
    {

      protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack)
      {
        Coord location = BlockUtil.getClosestPlantableBlock(world, x, y, z, side, caster);
        if (location != null) {
          WorldGenLargeWitchTree tree = new WorldGenLargeWitchTree(true, 1, 1, 0.5D);
          tree.func_76487_a(0.6D, 0.5D, 0.5D);
          tree.func_76484_a(world, field_73012_v, x, y, z);
        }
        
      }
    });
    register(new BrewActionEffect(new BrewItemKey(BlocksSAPLING, 2), new BrewNamePart("witchery:brew.treehawthorn"), new AltarPower(200), new Probability(1.0D), new EffectLevel(2))
    {

      protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack)
      {
        Coord location = BlockUtil.getClosestPlantableBlock(world, x, y, z, side, caster);
        if (location != null) {
          WorldGenLargeWitchTree tree = new WorldGenLargeWitchTree(true, 2, 2);
          tree.func_76487_a(0.8D, 1.2D, 1.0D);
          tree.func_76484_a(world, field_73012_v, x, y, z);
        }
        
      }
      
    });
    register(new BrewActionEffect(ItemsGENERIC.itemHeartOfGold.getBrewItemKey(), new BrewNamePart("witchery:brew.animalattraction", "witchery:brew.animalrepulsion"), new AltarPower(500), new Probability(1.0D), new EffectLevel(4))
    {

      protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
      {
        double radius = (modifiers.getStrength() + 1) * 16;
        double radiusSq = radius * radius;
        AxisAlignedBB bounds = field_70121_D.func_72314_b(radius, radius, radius);
        
        int maxCreatures = (int)Math.ceil((modifiers.getStrength() + 1.0D) * 2.0D * powerScalingFactor);
        

        List<EntityLivingBase> entities = world.func_72872_a(EntityLivingBase.class, bounds);
        

        for (EntityLivingBase otherEntity : entities) {
          if ((otherEntity != targetEntity) && (otherEntity.func_70068_e(targetEntity) < radiusSq)) {
            if ((otherEntity instanceof EntityTameable)) {
              EntityTameable tameable = (EntityTameable)otherEntity;
              if (Familiar.couldBeFamiliar(tameable)) {
                continue;
              }
              
              if (!inverted) {
                if (!tameable.func_70909_n()) {
                  tameable.func_70903_f(true);
                  tameable.func_70778_a((PathEntity)null);
                  tameable.func_70624_b((EntityLivingBase)null);
                  
                  tameable.func_70606_j(20.0F);
                  tameable.func_152115_b(targetEntity.func_110124_au().toString());
                  world.func_72960_a(tameable, (byte)7);
                  if ((tameable instanceof EntityOcelot)) {
                    ((EntityOcelot)tameable).func_70912_b(1 + field_73012_v.nextInt(3));
                  }
                }
              }
              else if ((tameable.func_70909_n()) && (!tameable.func_152114_e(targetEntity))) {
                tameable.func_70903_f(false);
                tameable.func_70778_a((PathEntity)null);
                tameable.func_70624_b((EntityLivingBase)null);
                
                tameable.func_152115_b("");
                world.func_72960_a(tameable, (byte)6);
                if ((tameable instanceof EntityOcelot)) {
                  ((EntityOcelot)tameable).func_70912_b(0);
                }
              }
            }
            

            if ((otherEntity instanceof EntityAnimal)) {
              EntityAnimal animal = (EntityAnimal)otherEntity;
              if (!inverted) {
                if (!animal.func_70661_as().func_75492_a(field_70165_t, field_70163_u, field_70161_v, 1.0D))
                {
                  animal.func_70778_a(world.func_72844_a(animal, MathHelper.func_76128_c(field_70165_t), MathHelper.func_76128_c(field_70163_u), MathHelper.func_76128_c(field_70161_v), 10.0F, true, false, false, true));
                }
                

              }
              else
              {

                int RANGE = 6;
                int newX = MathHelper.func_76128_c(field_70165_t) + (field_73012_v.nextBoolean() ? 1 : -1) * (RANGE + field_73012_v.nextInt(RANGE));
                

                int newZ = MathHelper.func_76128_c(field_70161_v) + (field_73012_v.nextBoolean() ? 1 : -1) * (RANGE + field_73012_v.nextInt(RANGE));
                



                for (int newY = 62; !world.func_147437_c(newX, newY + 1, newZ); newY++) {}
                

                if (!animal.func_70661_as().func_75492_a(newX, newY, newZ, 1.0D)) {
                  animal.func_70634_a(0.5D + newX, newY, 0.5D + newZ);
                }
                
              }
              
            }
          }
        }
      }
    });
    register(new BrewActionEffect(ItemsGENERIC.itemSilverDust.getBrewItemKey(), new BrewNamePart("witchery:brew.harmwerewolves"), new AltarPower(1000), new Probability(1.0D), new EffectLevel(4))
    {

      protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
      {
        int strength = Math.min(modifiers.getStrength(), strengthCeilingDisabled ? 3 : 1);
        if (CreatureUtil.isWerewolf(targetEntity)) {
          targetEntity.func_70097_a(new com.emoniph.witchery.util.EntityDamageSourceIndirectSilver(caster, caster), MathHelper.func_76143_f((3 << strength) * powerScalingFactor));
        }
        else
        {
          targetEntity.func_70097_a(DamageSource.func_76354_b(caster, caster), MathHelper.func_76143_f((1 << strength) * powerScalingFactor));
        }
      }
      



      protected void doApplyRitualToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersRitual ritualModifiers, ModifiersEffect modifiers, ItemStack stack)
      {
        if (!field_72995_K) {
          WorldInfo worldinfo = ((WorldServer)world).func_72912_H();
          int i = (300 + field_73012_v.nextInt(600)) * 20;
          if (!worldinfo.func_76059_o()) {
            worldinfo.func_76080_g(i);
            worldinfo.func_76084_b(true);
          }
          if ((modifiers.getStrength() >= 1) && (!worldinfo.func_76061_m())) {
            worldinfo.func_76090_f(i);
            worldinfo.func_76069_a(true);
          }
          
        }
        
      }
    });
    register(new BrewActionEffect(new BrewItemKey(ItemsSEEDS_GARLIC), new BrewNamePart("witchery:brew.weakenvampires"), new AltarPower(500), new Probability(1.0D), new EffectLevel(4))
    {

      protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
      {
        if (CreatureUtil.isVampire(targetEntity)) {
          if ((targetEntity instanceof EntityPlayer)) {
            ExtendedPlayer playerEx = ExtendedPlayer.get((EntityPlayer)targetEntity);
            playerEx.decreaseBloodPower(50 + 20 * modifiers.getStrength(), false);
          }
          BrewPotionEffect.applyPotionEffect(targetEntity, modifiers, Potion.field_76437_t, TimeUtil.secsToTicks(90), false, caster);
        }
        
      }
      
    });
    register(new BrewPotionEffect(new BrewItemKey(Items.field_151144_bL, 1), new BrewNamePart("witchery:brew.wither"), new AltarPower(200), new Probability(1.0D), Potion.field_82731_v, TimeUtil.secsToTicks(15), new EffectLevel(4)));
    


    register(new BrewActionEffect(new BrewItemKey(BlocksGLINT_WEED), new BrewNamePart("witchery:brew.inferno"), new AltarPower(750), new Probability(1.0D), new EffectLevel(3))
    {

      protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack stack)
      {

        if ((!field_72995_K) && (!isGlancing)) {
          EntitySplatter.splatter(world, new Coord(x, y, z, side), modifiers.getStrength());
        }
      }
      


      protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack stack)
      {
        if ((!field_72995_K) && (!isGlancing) && ((targetEntity instanceof EntityLivingBase))) {
          Coord coord = new Coord(targetEntity);
          targetEntity.func_70015_d(2 + 2 * modifiers.getStrength());
          if (powerScalingFactor == 1.0D) {
            EntitySplatter.splatter(world, new Coord(targetEntity), powerScalingFactor == 1.0D ? modifiers.getStrength() : 0);
          }
          
        }
        
      }
    });
    register(new BrewPotionEffect(ItemsGENERIC.itemInfernalBlood.getBrewItemKey(), new BrewNamePart("witchery:brew.fear"), new AltarPower(500), new Probability(1.0D), PotionsGROTESQUE, TimeUtil.minsToTicks(3), new EffectLevel(2)));
    


    register(new BrewPotionEffect(Dye.INK_SAC.getBrewItemKey(), new BrewNamePart("witchery:brew.blindness"), new AltarPower(1000), new Probability(1.0D), Potion.field_76440_q, TimeUtil.secsToTicks(15), new EffectLevel(4)));
    


    register(new BrewPotionEffect(new BrewItemKey(Blocks.field_150328_O), new BrewNamePart("witchery:brew.love"), new AltarPower(500), new Probability(1.0D), PotionsLOVE, TimeUtil.secsToTicks(10), new EffectLevel(4)));
    


    register(new BrewPotionEffect(ItemsGENERIC.itemDemonHeart.getBrewItemKey(), new BrewNamePart("witchery:brew.paralysis"), new AltarPower(750), new Probability(1.0D), PotionsPARALYSED, TimeUtil.secsToTicks(10), new EffectLevel(4)));
    


    register(new BrewCurseEffect(ItemsGENERIC.itemDropOfLuck.getBrewItemKey(), new BrewNamePart("witchery:brew.potionmaster", "witchery:brew.insanity"), new AltarPower(5000), new Probability(1.0D), PotionsBREWING_EXPERT, TimeUtil.minsToTicks(6), PotionsINSANITY, TimeUtil.minsToTicks(3), new EffectLevel(4), true));
    



    register(new BrewCurseEffect(new BrewItemKey(Items.field_151078_bh), new BrewNamePart("witchery:potion.diseased"), new AltarPower(2000), new Probability(1.0D), PotionsDISEASED, TimeUtil.minsToTicks(3), new EffectLevel(4), false));
    


    register(new BrewCurseEffect(ItemsGENERIC.itemDisturbedCotton.getBrewItemKey(), new BrewNamePart("witchery:brew.sinking"), new AltarPower(3000), new Probability(1.0D), PotionsSINKING, TimeUtil.minsToTicks(3), new EffectLevel(4), false));
    


    register(new BrewCurseEffect(new BrewItemKey(BlocksEMBER_MOSS), new BrewNamePart("witchery:brew.overheating"), new AltarPower(3000), new Probability(1.0D), PotionsOVERHEATING, TimeUtil.minsToTicks(3), new EffectLevel(4), false));
    


    register(new BrewCurseEffect(ItemsGENERIC.itemMellifluousHunger.getBrewItemKey(), new BrewNamePart("witchery:brew.wakingnightmare"), new AltarPower(10000), new Probability(1.0D), PotionsWAKING_NIGHTMARE, TimeUtil.minsToTicks(3), new EffectLevel(4), false));
    


    register(new BrewPotionEffect(ItemsGENERIC.itemToeOfFrog.getBrewItemKey(), new BrewNamePart("witchery:brew.frogsleg"), new AltarPower(500), new Probability(1.0D), PotionsDOUBLE_JUMP, TimeUtil.minsToTicks(6), new EffectLevel(4)));
    


    register(new BrewPotionEffect(new BrewItemKey(Items.field_151153_ao), new BrewNamePart("witchery:brew.absorbsion"), new AltarPower(1000), new Probability(1.0D), Potion.field_76444_x, TimeUtil.secsToTicks(30), new EffectLevel(4)));
    


    register(new BrewPotionEffect(new BrewItemKey(Items.field_151153_ao, 1), new BrewNamePart("witchery:brew.healthboost"), new AltarPower(1000), new Probability(1.0D), Potion.field_76434_w, TimeUtil.minsToTicks(2), new EffectLevel(4)));
    


    register(new BrewActionEffect(ItemsGENERIC.itemSubduedSpirit.getBrewItemKey(), new BrewNamePart("witchery:brew.wasting", "witchery:brew.fullness"), new AltarPower(500), new Probability(1.0D), new EffectLevel(4))
    {

      protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack stack)
      {

        int hungerTicks = modifiers.getModifiedDuration(TimeUtil.secsToTicks(20));
        int poisonTicks = Math.max(modifiers.getModifiedDuration(TimeUtil.secsToTicks(3)), 40);
        int strength = 1 + modifiers.getStrength() / 2;
        if ((targetEntity instanceof EntityPlayer)) {
          EntityPlayer victim = (EntityPlayer)targetEntity;
          if (inverted) {
            int minLevel = 6 + 2 * modifiers.getStrength();
            
            victim.func_71024_bL().func_75122_a(minLevel, minLevel);
          }
          else {
            int minLevel = 10 - modifiers.getStrength();
            if (victim.func_71024_bL().func_75116_a() > minLevel) {
              victim.func_71024_bL().func_75122_a(-minLevel, 2.0F);
            }
            victim.func_70690_d(new PotionEffect(field_76438_sfield_76415_H, hungerTicks, strength));
            victim.func_70690_d(new PotionEffect(field_76436_ufield_76415_H, poisonTicks, 0));
          }
        }
        else if (inverted) {
          targetEntity.func_70690_d(new PotionEffect(field_76428_lfield_76415_H, TimeUtil.secsToTicks(30), modifiers.getStrength()));
        }
        else {
          targetEntity.func_70690_d(new PotionEffect(field_82731_vfield_76415_H, hungerTicks, strength));
          targetEntity.func_70690_d(new PotionEffect(field_76436_ufield_76415_H, poisonTicks));
        }
        
      }
      
    });
    register(new BrewActionEffect(ItemsGENERIC.itemOdourOfPurity.getBrewItemKey(), new BrewNamePart("witchery:brew.revealing"), new AltarPower(100), new Probability(1.0D), new EffectLevel(4))
    {

      protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack stack)
      {
        boolean doDamage = false;
        if (targetEntity.func_70644_a(Potion.field_76441_p)) {
          targetEntity.func_82170_o(field_76441_pfield_76415_H);
        }
        
        if (((targetEntity instanceof EntityPlayer)) && (targetEntity.func_82150_aj())) {
          targetEntity.func_82142_c(false);
        }
        
        if ((targetEntity instanceof EntityPlayer)) {
          EntityPlayer player = (EntityPlayer)targetEntity;
          ExtendedPlayer playerEx = ExtendedPlayer.get(player);
          if ((playerEx != null) && (playerEx.getCreatureType() == TransformCreature.PLAYER)) {
            ParticleEffect.SMOKE.send(SoundEffect.WITCHERY_RANDOM_POOF, player, 0.5D, 2.0D, 16);
            Shapeshift.INSTANCE.shiftTo(player, TransformCreature.NONE);
          }
        }
        
        if (((targetEntity instanceof EntitySummonedUndead)) && (((EntitySummonedUndead)targetEntity).isObscured()))
        {
          ((EntitySummonedUndead)targetEntity).setObscured(false);
        }
        int strength = modifiers.getStrength();
        if ((doDamage) && (strength > 0)) {
          targetEntity.func_70097_a(DamageSource.func_76354_b(caster, caster), strength);
        }
        
      }
      
    });
    register(new BrewPotionEffect(ItemsGENERIC.itemFoulFume.getBrewItemKey(), new BrewNamePart("witchery:potion.stoutbelly"), new AltarPower(1000), new Probability(1.0D), PotionsSTOUT_BELLY, TimeUtil.secsToTicks(90), new EffectLevel(4)));
    


    register(new BrewActionBlight(new BrewItemKey(Items.field_151170_bI), new BrewNamePart("witchery:brew.blight"), new AltarPower(2000), new Probability(1.0D), new EffectLevel(4)));
    

    register(new BrewActionTranspose(new BrewItemKey(Items.field_151079_bi), new BrewNamePart("witchery:brew.transpose"), new AltarPower(1000), new Probability(1.0D), new EffectLevel(4)));
    

    register(new BrewActionEffect(new BrewItemKey(Items.field_151042_j), new BrewNamePart("witchery:brew.transposeore"), new AltarPower(2000), new Probability(1.0D), new EffectLevel(4))
    {

      protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack stack)
      {
        int depth = radius + strength;
        Block[] blockTypes = { Blocks.field_150366_p, Blocks.field_150352_o, Blocks.field_150369_x, Blocks.field_150412_bA };
        
        slurpOres(world, x, y, z, radius, depth, blockTypes, modifiers, y + 2);
      }
      

      protected void doApplyRitualToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersRitual ritualModifiers, ModifiersEffect modifiers, ItemStack stack)
      {
        int r = covenSize + radius;
        int maxDepth = 4 * covenSize * (1 + modifiers.getStrength());
        int steps = 4;
        Block[] blockTypes = { Blocks.field_150366_p, Blocks.field_150352_o, Blocks.field_150369_x, Blocks.field_150412_bA };
        

        slurpOres(world, x, y - (pulses - 1) * 4, z, r, 4, blockTypes, modifiers, y + 2);
        

        ParticleEffect.FLAME.send(SoundEffect.FIREWORKS_BLAST1, world, x, y, z, 1.0D, 1.0D, 16);
        
        ritualModifiers.setRitualStatus(pulses * 4 < maxDepth ? RitualStatus.ONGOING : RitualStatus.COMPLETE);
      }
      

      private void slurpOres(World world, int posX, int posY, int posZ, int radius, int depth, Block[] blockTypes, ModifiersEffect modifiers, int returnY)
      {
        int r = radius;
        int maxType = modifiers.getStrength();
        for (int x = posX - r; x <= posX + r; x++) {
          for (int z = posZ - r; z <= posZ + r; z++) {
            for (int y = posY - depth; y <= posY + r; y++) {
              Block blockID = world.func_147439_a(x, y, z);
              
              for (int t = 0; (t < blockTypes.length) && (t < maxType); t++) {
                if (blockID == blockTypes[t]) {
                  ItemStack newStack = new ItemStack(blockID);
                  EntityItem entity = new EntityItem(world, posX + 0.5D, posY + 0.5D, posZ + 0.5D, newStack);
                  
                  if (!field_72995_K) {
                    world.func_147468_f(x, y, z);
                    world.func_72838_d(entity);
                  }
                  
                }
              }
            }
          }
        }
      }
    });
    register(new BrewPotionEffect(new BrewItemKey(Blocks.field_150329_H, 0), new BrewNamePart("witchery:potion.volatility"), new AltarPower(1000), new Probability(1.0D), PotionsVOLATILITY, TimeUtil.secsToTicks(180), new EffectLevel(4)));
    


    register(new BrewPotionEffect(new BrewItemKey(Blocks.field_150330_I), new BrewNamePart("witchery:potion.volatility"), new AltarPower(1000), new Probability(1.0D), PotionsVOLATILITY, TimeUtil.secsToTicks(180), new EffectLevel(4)));
    


    register(new BrewPotionEffect(new BrewItemKey(Blocks.field_150425_aM), new BrewNamePart("witchery:brew.allergydark"), new AltarPower(4000), new Probability(1.0D), PotionsDARKNESS_ALLERGY, TimeUtil.minsToTicks(2), new EffectLevel(4)));
    


    register(new BrewPotionEffect(ItemsGENERIC.itemWhiffOfMagic.getBrewItemKey(), new BrewNamePart("witchery:potion.absorbmagic"), new AltarPower(2000), new Probability(1.0D), PotionsABSORB_MAGIC, TimeUtil.secsToTicks(60), new EffectLevel(4)));
    


    register(new BrewActionRaising(Items.field_151103_aS, new AltarPower(2000), new EffectLevel(4)));
    
    register(new BrewActionRaiseLand(new BrewItemKey(Items.field_151128_bU), new BrewNamePart("witchery:brew.raiseland"), new AltarPower(2000), new EffectLevel(4)));
    




    register(new BrewActionEffect(new BrewItemKey(Blocks.field_150432_aD), new BrewNamePart("witchery:brew.harmdemons"), new AltarPower(500), new Probability(1.0D), new EffectLevel(5))
    {

      protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
      {
        int strength = Math.min(modifiers.getStrength(), strengthCeilingDisabled ? 3 : 1);
        if ((targetEntity instanceof EntityLeonard)) {
          ((EntityLeonard)targetEntity).attackEntityFromWeakness(MathHelper.func_76143_f((10 << strength) * powerScalingFactor));
        }
        else if (CreatureUtil.isDemonic(targetEntity)) {
          targetEntity.func_70097_a(DamageSource.func_76354_b(caster, caster), MathHelper.func_76143_f((10 << strength) * powerScalingFactor));
        }
        else
        {
          targetEntity.func_70097_a(DamageSource.func_76354_b(caster, caster), MathHelper.func_76143_f((3 << strength) * powerScalingFactor));

        }
        
      }
      

    });
    register(new BrewActionEffect(ItemsGENERIC.itemFrozenHeart.getBrewItemKey(), new BrewNamePart("witchery:brew.iceshell"), new AltarPower(500), new Probability(1.0D), new EffectLevel(5))
    {

      protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack)
      {
        createSphere(world, modifiers, BlockUtil.getClosestPlantableBlock(world, x, y, z, side, caster));
      }
      


      protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
      {
        boolean resistent = ((targetEntity instanceof EntityDemon)) || ((targetEntity instanceof EntityBlaze)) || ((targetEntity instanceof net.minecraft.entity.boss.IBossDisplayData)) || ((targetEntity instanceof com.emoniph.witchery.entity.EntityEnt)) || ((targetEntity instanceof net.minecraft.entity.monster.EntityIronGolem));
        

        if (!resistent) {
          BrewPotionEffect.applyPotionEffect(targetEntity, modifiers, PotionsCHILLED, TimeUtil.secsToTicks(10), noParticles, caster);
          
          if (!isGlancing) {
            createSphere(world, modifiers, new Coord(targetEntity));
          }
        } else if (field_71093_bK != -1) {
          Coord coord = new Coord(targetEntity);
          BlockUtil.setBlockIfReplaceable(world, x, y, z, Blocks.field_150358_i);
        }
      }
      
      public void createSphere(final World world, ModifiersEffect modifiers, final Coord coord) {
        if (coord != null) {
          final int iceRadius = modifiers.getStrength() + (modifiers.getStrength() > 3 ? 2 : 1);
          new BlockActionSphere() {
            protected void onBlock(World world, int x, int y, int z) {
              BlockUtil.setBlockIfReplaceable(world, x, y, z, BlocksPERPETUAL_ICE);
            }
            

            protected void onComplete() { fillWith(world, coordx, coordy, coordz, iceRadius, Blocks.field_150350_a, BlocksPERPETUAL_ICE); } }.drawHollowSphere(world, x, y, z, iceRadius);

        }
        
      }
      

    });
    register(new BrewPotionEffect(ItemsGENERIC.itemSpectralDust.getBrewItemKey(), new BrewNamePart("witchery:potion.reflectdamage"), new AltarPower(2000), new Probability(1.0D), PotionsREFLECT_DAMAGE, TimeUtil.secsToTicks(90), new EffectLevel(5)));
    



    register(new BrewActionEffect(ItemsGENERIC.itemRefinedEvil.getBrewItemKey(), new BrewNamePart("witchery:brew.hellgate"), new AltarPower(3000), new Probability(1.0D), new EffectLevel(5))
    {

      public boolean isRitualTargetLocationValid(MinecraftServer server, World world, int x, int y, int z, BlockPosition target, ModifiersRitual modifiers)
      {

        return CircleUtil.isMediumCircle(target.getWorld(server), x, y, z, BlocksGLYPH_INFERNAL);
      }
      


      protected void doApplyRitualToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersRitual ritualModifiers, ModifiersEffect modifiers, ItemStack stack)
      {
        InfusionInfernal.spawnCreature(world, EntityDemon.class, x, y, z, null, 0, 2, ParticleEffect.FLAME, SoundEffect.MOB_ENDERDRAGON_GROWL);
      }
      



      protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack) {}
      


      protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
      {
        BrewPotionEffect.applyPotionEffect(targetEntity, modifiers, PotionsNETHER_BOUND, TimeUtil.minsToTicks(3), noParticles, caster);
      }
      

    });
    register(new BrewActionEffect(new BrewItemKey(Items.field_151043_k), new BrewNamePart("witchery:brew.blast"), new AltarPower(500), new Probability(1.0D), new EffectLevel(5))
    {

      protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack stack)
      {
        if ((powerScalingFactor == 1.0D) || (field_73012_v.nextDouble() < powerScalingFactor * 0.2D))
        {
          world.func_72876_a(caster, field_70165_t, field_70163_u, field_70161_v, modifiers.getStrength(), true);
        }
      }
      


      protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack stack)
      {
        world.func_72876_a(caster, x + offsetX + 0.5D, y + offsetY + 0.5D, z + offsetZ + 0.5D, modifiers.getStrength(), true);

      }
      

    });
    register(new BrewActionEffect(new BrewItemKey(Blocks.field_150398_cm), new BrewNamePart("witchery:brew.poisontoad"), new AltarPower(500), new Probability(1.0D), new EffectLevel(5))
    {

      protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack stack)
      {
        if (((!(targetEntity instanceof EntityToad)) && (powerScalingFactor == 1.0D)) || (field_73012_v.nextDouble() < powerScalingFactor * 0.2D))
        {
          EntityToad toad = new EntityToad(world);
          toad.func_70012_b(field_70165_t, field_70163_u + field_70131_O + 1.0D, field_70161_v, 0.0F, 0.0F);
          
          toad.setTimeToLive(60 + modifiers.getStrength() * 40, true);
          world.func_72838_d(toad);
        }
      }
      

      protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack stack)
      {
        EntityToad toad = new EntityToad(world);
        toad.func_70012_b(0.5D + x, 2.5D + y, 0.5D + z, 0.0F, 0.0F);
        toad.setTimeToLive(60 + modifiers.getStrength() * 40, true);
        world.func_72838_d(toad);
      }
      

    });
    register(new BrewActionEffect(new BrewItemKey(Items.field_151061_bv), new BrewNamePart("witchery:brew.iceworld"), new AltarPower(2000), new Probability(1.0D), new EffectLevel(5))
    {

      protected void doApplyToBlock(World world, int x, int y0, int z, ForgeDirection side, int radius, final ModifiersEffect modifiers, ItemStack actionStack)
      {

        if (!field_72995_K) {
          new BlockActionSphere()
          {
            public void onBlock(World world, int x, int y, int z) {
              Block block = world.func_147439_a(x, y, z);
              if ((BlockProtect.checkModsForBreakOK(world, x, y, z, block, world.func_72805_g(x, y, z), modifierscaster)) && (BlockProtect.canBreak(block, world)))
              {

                int meta = world.func_72805_g(x, y, z);
                if (block == Blocks.field_150466_ao) {
                  int i1 = ((BlockDoor)block).func_150012_g(world, x, y, z);
                  if ((i1 & 0x8) != 0) {
                    y--;
                  }
                  world.func_147468_f(x, y, z);
                  world.func_147468_f(x, y + 1, z);
                  net.minecraft.item.ItemDoor.func_150924_a(world, x, y, z, i1 & 0x3, BlocksPERPETUAL_ICE_DOOR);
                }
                else {
                  return;
                }
                ParticleEffect.SMOKE.send(SoundEffect.NONE, world, x, y + 1, z, 0.25D, 0.25D, 16); } } }.drawFilledSphere(world, x, y0, z, (int)Math.ceil(Math.max(radius * 1.5D, 1.0D)));
          



          new BlockActionSphere()
          {
            public void onBlock(World world, int x, int y, int z) {
              Block block = world.func_147439_a(x, y, z);
              if ((BlockProtect.checkModsForBreakOK(world, x, y, z, block, world.func_72805_g(x, y, z), modifierscaster)) && (BlockProtect.canBreak(block, world)))
              {

                int meta = world.func_72805_g(x, y, z);
                if ((block == Blocks.field_150346_d) || (block == Blocks.field_150349_c) || (block == Blocks.field_150391_bh) || (block == Blocks.field_150354_m))
                {
                  world.func_147449_b(x, y, z, Blocks.field_150433_aE);
                } else if ((block == Blocks.field_150347_e) || (block == Blocks.field_150341_Y) || (block == Blocks.field_150364_r) || (block == Blocks.field_150363_s) || (block == BlocksLOG))
                {

                  world.func_147449_b(x, y, z, Blocks.field_150403_cj);
                } else if ((block == Blocks.field_150348_b) || (block == Blocks.field_150417_aV) || (block == Blocks.field_150336_V) || (block == Blocks.field_150344_f) || (block == Blocks.field_150362_t) || (block == Blocks.field_150361_u) || (block == BlocksLEAVES) || (block == BlocksPLANKS) || (block == Blocks.field_150322_A))
                {



                  world.func_147449_b(x, y, z, BlocksPERPETUAL_ICE);
                } else if ((block == Blocks.field_150456_au) || (block == Blocks.field_150452_aw))
                {
                  world.func_147449_b(x, y, z, BlocksPERPETUAL_ICE_PRESSURE_PLATE);
                } else if ((block == Blocks.field_150446_ar) || (block == Blocks.field_150389_bf) || (block == Blocks.field_150390_bg) || (block == Blocks.field_150476_ad) || (block == Blocks.field_150485_bF) || (block == Blocks.field_150372_bz) || (block == Blocks.field_150487_bG) || (block == Blocks.field_150481_bH) || (block == Blocks.field_150401_cl) || (block == Blocks.field_150400_ck) || (block == BlocksSTAIRS_ALDER) || (block == BlocksSTAIRS_HAWTHORN) || (block == BlocksSTAIRS_ROWAN))
                {






                  world.func_147465_d(x, y, z, BlocksPERPETUAL_ICE_STAIRS, meta, 3);
                } else if ((block == Blocks.field_150333_U) || (block == Blocks.field_150376_bx) || (block == BlocksWOOD_SLAB_SINGLE))
                {
                  world.func_147449_b(x, y, z, BlocksPERPETUAL_ICE_SLAB_SINGLE);
                } else if ((block == Blocks.field_150334_T) || (block == Blocks.field_150373_bw) || (block == BlocksWOOD_SLAB_DOUBLE))
                {
                  world.func_147449_b(x, y, z, BlocksPERPETUAL_ICE_SLAB_DOUBLE);
                } else if ((block == Blocks.field_150422_aJ) || (block == Blocks.field_150463_bK)) {
                  world.func_147449_b(x, y, z, BlocksPERPETUAL_ICE_FENCE);
                } else if (block == Blocks.field_150396_be) {
                  world.func_147449_b(x, y, z, BlocksPERPETUAL_ICE_FENCE_GATE);
                } else if (block == BlocksSTOCKADE) {
                  world.func_147449_b(x, y, z, BlocksSTOCKADE_ICE);
                } else {
                  return;
                }
                ParticleEffect.SMOKE.send(SoundEffect.NONE, world, x, y + 1, z, 0.25D, 0.25D, 16); } } }.drawFilledSphere(world, x, y0, z, (int)Math.ceil(Math.max(radius * 1.5D, 1.0D)));
          



          SoundEffect.RANDOM_ORB.playAt(world, x, y0, z, 0.5F, 2.0F);
        }
        
      }
      

    });
    register(new BrewActionEffect(ItemsGENERIC.itemCondensedFear.getBrewItemKey(), new BrewNamePart("witchery:brew.drainmagic"), new AltarPower(1000), new Probability(1.0D), new EffectLevel(6))
    {

      protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
      {
        Witchery.modHooks.reducePowerLevels(targetEntity, 0.25F * (1.0F + modifiers.getStrength()) * (float)powerScalingFactor);
      }
      

    });
    register(new BrewPotionEffect(new BrewItemKey(Items.field_151119_aD), new BrewNamePart("witchery:potion.fortune"), new AltarPower(1000), new Probability(1.0D), PotionsFORTUNE, TimeUtil.minsToTicks(3), new EffectLevel(6)));
    


    register(new BrewPotionEffect(new BrewItemKey(Items.field_151115_aP, 1), new BrewNamePart("witchery:brew.allergysun"), new AltarPower(1000), new Probability(1.0D), PotionsSUN_ALLERGY, TimeUtil.secsToTicks(60), new EffectLevel(6)));
    


    register(new BrewPotionEffect(new BrewItemKey(BlocksBRAMBLE, 1), new BrewNamePart("witchery:potion.illfitting").setBaseDuration(TimeUtil.secsToTicks(6)), new AltarPower(8000), new Probability(1.0D), PotionsILL_FITTING, TimeUtil.secsToTicks(6), new EffectLevel(6)));
    


    register(new BrewPotionEffect(ItemsGENERIC.itemHintOfRebirth.getBrewItemKey(), new BrewNamePart("witchery:brew.reincarnate"), new AltarPower(2500), new Probability(1.0D), PotionsREINCARNATE, TimeUtil.minsToTicks(3), new EffectLevel(6)));
    


    register(new BrewActionEffect(ItemsGENERIC.itemCreeperHeart.getBrewItemKey(), new BrewNamePart("witchery:brew.durationboost"), new AltarPower(5000), new Probability(1.0D), new EffectLevel(6))
    {

      protected void doApplyToEntity(World world, EntityLivingBase target, ModifiersEffect modifiers, ItemStack actionStack)
      {
        if (target.func_70644_a(PotionsQUEASY)) {
          if (field_70170_p.field_73012_v.nextInt(3) == 0) {
            target.func_70690_d(new PotionEffect(field_76440_qfield_76415_H, TimeUtil.minsToTicks(2), 0, true));
          }
          else {
            target.func_70690_d(new PotionEffect(field_76431_kfield_76415_H, TimeUtil.minsToTicks(5), 0, true));
          }
        }
        else {
          Collection<PotionEffect> potionEffects = target.func_70651_bq();
          if (!potionEffects.isEmpty()) {
            List<PotionEffect> newEffects = new ArrayList();
            int durationBoost = MathHelper.func_76143_f(powerScalingFactor * TimeUtil.minsToTicks(3));
            
            for (PotionEffect potionEffect : potionEffects) {
              if (!Potion.field_76425_a[potionEffect.func_76456_a()].func_76403_b()) {
                int remainingTicks = potionEffect.func_76459_b();
                int newDuration = remainingTicks + Math.min(remainingTicks, durationBoost);
                newEffects.add(new PotionEffect(potionEffect.func_76456_a(), newDuration, potionEffect.func_76458_c(), potionEffect.func_82720_e()));
              }
            }
            

            target.func_70674_bp();
            for (PotionEffect potionEffect : newEffects) {
              target.func_70690_d(potionEffect);
            }
            
            int mins = 3 * Math.max(1, 4 - modifiers.getStrength());
            target.func_70690_d(new PotionEffect(PotionsQUEASY.field_76415_H, TimeUtil.minsToTicks(mins), 0, true));
          }
          
        }
        
      }
    });
    register(new BrewPotionEffect(new BrewItemKey(Items.field_151166_bC), new BrewNamePart("witchery:brew.resizing"), new AltarPower(2500), new Probability(1.0D), PotionsRESIZING, TimeUtil.secsToTicks(20), new EffectLevel(6)));
    


    register(new BrewActionEffect(new BrewItemKey(Items.field_151144_bL, 0), new BrewNamePart("witchery:brew.stealbuffs"), new AltarPower(100), new Probability(1.0D), new EffectLevel(6))
    {


      protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack stack)
      {

        double radius = (modifiers.getStrength() + 1) * 8;
        double radiusSq = radius * radius;
        AxisAlignedBB bounds = field_70121_D.func_72314_b(radius, radius, radius);
        
        int maxBuffs = (int)Math.ceil((modifiers.getStrength() + 1.0D) * 2.0D * powerScalingFactor);
        

        List<EntityLivingBase> entities = world.func_72872_a(EntityLivingBase.class, bounds);
        
        for (EntityLivingBase otherEntity : entities) {
          if ((otherEntity != targetEntity) && (otherEntity.func_70068_e(targetEntity) < radiusSq)) {
            Collection<PotionEffect> potionEffects = otherEntity.func_70651_bq();
            List<Integer> effectsToRemove = new ArrayList();
            for (PotionEffect effect : potionEffects) {
              Potion potion = Potion.field_76425_a[effect.func_76456_a()];
              if ((!PotionBase.isDebuff(potion)) && (PotionBase.isCurable(potion)) && (effect.func_76458_c() <= modifiers.getStrength()))
              {
                PotionEffect myEffect = targetEntity.func_70660_b(potion);
                if (myEffect != null) {
                  if ((myEffect.func_76459_b() <= effect.func_76459_b()) && (myEffect.func_76458_c() <= effect.func_76458_c()))
                  {
                    targetEntity.func_70690_d(new PotionEffect(effect));
                    effectsToRemove.add(Integer.valueOf(effect.func_76456_a()));
                    maxBuffs--;
                  }
                } else {
                  targetEntity.func_70690_d(new PotionEffect(effect));
                  effectsToRemove.add(Integer.valueOf(effect.func_76456_a()));
                  maxBuffs--;
                }
                if (maxBuffs <= 0) {
                  break;
                }
              }
            }
            for (Iterator i$ = effectsToRemove.iterator(); i$.hasNext();) { int id = ((Integer)i$.next()).intValue();
              otherEntity.func_82170_o(id);
            }
            if (maxBuffs <= 0) {
              break;
            }
            
          }
          
        }
        
      }
    });
    register(new BrewPotionEffect(ItemsGENERIC.itemFocusedWill.getBrewItemKey(), new BrewNamePart("witchery:brew.keepinventory"), new AltarPower(10000), new Probability(1.0D), PotionsKEEP_INVENTORY, TimeUtil.minsToTicks(6), new EffectLevel(8)));
    


    register(new BrewPotionEffect(ItemsGENERIC.itemRedstoneSoup.getBrewItemKey(), new BrewNamePart("witchery:potion.keepeffects"), new AltarPower(10000), new Probability(1.0D), PotionsKEEP_EFFECTS, TimeUtil.minsToTicks(6), new EffectLevel(8)));
    



    register(new com.emoniph.witchery.brewing.action.effect.BrewActionBiomeChange(new BrewItemKey(ItemsBIOME_NOTE, 32767), new BrewNamePart("witchery:brew.seasons"), new AltarPower(5000), new Probability(1.0D), new EffectLevel(8)));
    


    register(new BrewActionEffect(new BrewItemKey(Items.field_151144_bL, 4), new BrewNamePart("witchery:brew.spreaddebuffs"), new AltarPower(2000), new Probability(1.0D), new EffectLevel(8))
    {

      protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack stack)
      {
        int strength = modifiers.getStrength();
        double radius = (strength + 1) * 4;
        double radiusSq = radius * radius;
        AxisAlignedBB bounds = field_70121_D.func_72314_b(radius, radius, radius);
        
        int maxBuffs = (int)Math.ceil((strength + 1.0D) * powerScalingFactor);
        List<EntityLivingBase> entities = world.func_72872_a(EntityLivingBase.class, bounds);
        
        List<EntityLivingBase> others = new ArrayList();
        for (EntityLivingBase otherEntity : entities) {
          if ((otherEntity != targetEntity) && (otherEntity.func_70068_e(targetEntity) < radiusSq)) {
            others.add(otherEntity);
          }
        }
        
        Collection<PotionEffect> effects = targetEntity.func_70651_bq();
        List<Integer> effectsToRemove = new ArrayList();
        for (PotionEffect effect : effects) {
          Potion potion = Potion.field_76425_a[effect.func_76456_a()];
          if ((PotionBase.isDebuff(potion)) && (PotionBase.isCurable(potion)) && (effect.func_76458_c() <= Math.max(strength - 1, 1)))
          {
            effectsToRemove.add(Integer.valueOf(field_76415_H));
            for (EntityLivingBase other : others) {
              other.func_70690_d(new PotionEffect(effect));
            }
            maxBuffs--;
            if (maxBuffs <= 0) {
              break;
            }
          }
        }
        for (Iterator i$ = effectsToRemove.iterator(); i$.hasNext();) { int id = ((Integer)i$.next()).intValue();
          targetEntity.func_82170_o(id);
        }
        
      }
      
    });
    register(new BrewActionRitualSummonMob(new BrewItemKey(ItemsWITCH_HAT), new AltarPower(10000), new BrewActionRitualSummonMob.Recipe[] { new BrewActionRitualSummonMob.Recipe(EntityLeonard.class, new ItemStack[] { new ItemStack(Items.field_151075_bm), ItemsGENERIC.itemTearOfTheGoddess.createStack(), ItemsGENERIC.itemDiamondVapour.createStack(), new ItemStack(Items.field_151045_i), new ItemStack(Items.field_151156_bN) }) }));
    





    register(new BrewActionModifier(ItemsGENERIC.itemWaystoneBound.getBrewItemKey(), null, new AltarPower(100))
    {
      public void prepareRitual(World world, int x, int y, int z, ModifiersRitual modifiers, ItemStack stack)
      {
        modifiers.setTarget(stack);
      }
      

    });
    register(new BrewActionRitual(ItemsGENERIC.itemDogTongue.getBrewItemKey(), new AltarPower(250), true));
    


    register(new BrewActionRitualEntityTarget(new BrewItemKey(ItemsTAGLOCK_KIT, 32767), new AltarPower(1000)));
    



    register(new BrewActionRitualRecipe(new BrewItemKey(ItemsCHALK_RITUAL), new AltarPower(2000), new BrewActionRitualRecipe.Recipe[] { new BrewActionRitualRecipe.Recipe(new ItemStack(ItemsCHALK_OTHERWHERE), new ItemStack[] { new ItemStack(Items.field_151075_bm), ItemsGENERIC.itemTearOfTheGoddess.createStack(), new ItemStack(Items.field_151079_bi) }), new BrewActionRitualRecipe.Recipe(new ItemStack(ItemsCHALK_GOLDEN), new ItemStack[] { ItemsGENERIC.itemMandrakeRoot.createStack(), new ItemStack(Items.field_151074_bl) }), new BrewActionRitualRecipe.Recipe(new ItemStack(ItemsCHALK_INFERNAL), new ItemStack[] { new ItemStack(Items.field_151075_bm), new ItemStack(Items.field_151065_br) }) }));
    







    register(new BrewActionRitualRecipe(new BrewItemKey(Items.field_151110_aK), new AltarPower(0), new BrewActionRitualRecipe.Recipe[] { new BrewActionRitualRecipe.Recipe(ItemsGENERIC.itemMutandis.createStack(6), new ItemStack[] { ItemsGENERIC.itemMandrakeRoot.createStack(), ItemsGENERIC.itemExhaleOfTheHornedOne.createStack() }) }));
    



    register(new BrewActionRitualRecipe(ItemsGENERIC.itemMutandis.getBrewItemKey(), new AltarPower(0), new BrewActionRitualRecipe.Recipe[] { new BrewActionRitualRecipe.Recipe(new ItemStack(Items.field_151075_bm), new ItemStack[] { ItemsGENERIC.itemMandrakeRoot.createStack(), ItemsGENERIC.itemTearOfTheGoddess.createStack(), ItemsGENERIC.itemDiamondVapour.createStack(), new ItemStack(Items.field_151079_bi), new ItemStack(Items.field_151015_O) }), new BrewActionRitualRecipe.Recipe(ItemsGENERIC.itemMutandisExtremis.createStack(), new ItemStack[] { new ItemStack(Items.field_151075_bm) }) }));
    







    register(new BrewActionRitualRecipe(ItemsGENERIC.itemMutandisExtremis.getBrewItemKey(), new AltarPower(1800), new BrewActionRitualRecipe.Recipe[] { new BrewActionRitualRecipe.Recipe(new ItemStack(Blocks.field_150377_bs, 2), new ItemStack[] { ItemsGENERIC.itemMandrakeRoot.createStack(), new ItemStack(Blocks.field_150348_b), new ItemStack(Blocks.field_150377_bs) }), new BrewActionRitualRecipe.Recipe(new ItemStack(ItemsMUTATING_SPRIG), new ItemStack[] { new ItemStack(Items.field_151075_bm), ItemsGENERIC.itemBranchEnt.createStack() }), new BrewActionRitualRecipe.Recipe(ItemsGENERIC.itemDropOfLuck.createStack(), new ItemStack[] { ItemsGENERIC.itemMandrakeRoot.createStack(), new ItemStack(Items.field_151075_bm), ItemsGENERIC.itemTearOfTheGoddess.createStack(), ItemsGENERIC.itemRefinedEvil.createStack() }) }));
    









    register(new BrewActionRitualRecipe(new BrewItemKey(Blocks.field_150433_aE), new AltarPower(3000), new BrewActionRitualRecipe.Recipe[] { new BrewActionRitualRecipe.Recipe(new ItemStack(ItemsBREW_ENDLESS_WATER, 1, 0), new ItemStack[] { new ItemStack(Items.field_151075_bm), new ItemStack(Items.field_151114_aO), new ItemStack(Items.field_151072_bj), ItemsGENERIC.itemAttunedStoneCharged.createStack() }), new BrewActionRitualRecipe.Recipe(new ItemStack(ItemsBREW_ENDLESS_WATER, 1, 50), new ItemStack[] { new ItemStack(Items.field_151075_bm), new ItemStack(Items.field_151114_aO), new ItemStack(Items.field_151072_bj) }), new BrewActionRitualRecipe.Recipe(new ItemStack(ItemsBREW_ENDLESS_WATER, 1, 80), new ItemStack[] { new ItemStack(Items.field_151075_bm), new ItemStack(Items.field_151114_aO) }), new BrewActionRitualRecipe.Recipe(new ItemStack(ItemsBREW_ENDLESS_WATER, 1, 95), new ItemStack[] { new ItemStack(Items.field_151075_bm) }) }));
    









    register(new BrewActionRitualRecipe(new BrewItemKey(Items.field_151044_h, 1), new AltarPower(0), new BrewActionRitualRecipe.Recipe[] { new BrewActionRitualRecipe.Recipe(new ItemStack(ItemsBREW_FUEL, 1, 3), new ItemStack[] { ItemsGENERIC.itemMandrakeRoot.createStack(), new ItemStack(Items.field_151114_aO), new ItemStack(Items.field_151072_bj), ItemsGENERIC.itemAttunedStoneCharged.createStack() }), new BrewActionRitualRecipe.Recipe(new ItemStack(ItemsBREW_FUEL, 1, 2), new ItemStack[] { ItemsGENERIC.itemMandrakeRoot.createStack(), new ItemStack(Items.field_151114_aO), new ItemStack(Items.field_151072_bj) }), new BrewActionRitualRecipe.Recipe(new ItemStack(ItemsBREW_FUEL, 1, 1), new ItemStack[] { ItemsGENERIC.itemMandrakeRoot.createStack(), new ItemStack(Items.field_151114_aO) }), new BrewActionRitualRecipe.Recipe(new ItemStack(ItemsBREW_FUEL, 1, 0), new ItemStack[] { ItemsGENERIC.itemMandrakeRoot.createStack() }) }));
    













    register(new BrewActionRitualRecipe(new BrewItemKey(Items.field_151147_al), new AltarPower(0), new BrewActionRitualRecipe.Recipe[] { new BrewActionRitualRecipe.Recipe(new ItemStack(Items.field_151157_am), new ItemStack[0]) }));
    

    register(new BrewActionRitualRecipe(new BrewItemKey(Items.field_151076_bf), new AltarPower(0), new BrewActionRitualRecipe.Recipe[] { new BrewActionRitualRecipe.Recipe(new ItemStack(Items.field_151077_bg), new ItemStack[0]) }));
    

    register(new BrewActionRitualRecipe(new BrewItemKey(Items.field_151082_bd), new AltarPower(0), new BrewActionRitualRecipe.Recipe[] { new BrewActionRitualRecipe.Recipe(new ItemStack(Items.field_151083_be), new ItemStack[0]) }));
    

    register(new BrewActionRitualRecipe(ItemsGENERIC.itemOddPorkRaw.getBrewItemKey(), new AltarPower(0), new BrewActionRitualRecipe.Recipe[] { new BrewActionRitualRecipe.Recipe(ItemsGENERIC.itemOddPorkCooked.createStack(), new ItemStack[0]) }));
    


    register(new BrewActionRitualRecipe(ItemsGENERIC.itemMuttonRaw.getBrewItemKey(), new AltarPower(0), new BrewActionRitualRecipe.Recipe[] { new BrewActionRitualRecipe.Recipe(ItemsGENERIC.itemMuttonCooked.createStack(), new ItemStack[0]) }));
    

    register(new BrewActionRitualRecipe(new BrewItemKey(ItemsWITCH_HAND), new AltarPower(0), new BrewActionRitualRecipe.Recipe[] { new BrewActionRitualRecipe.Recipe(new ItemStack(Items.field_151078_bh, 6), new ItemStack[0]) }));
    

    register(new BrewActionRitualRecipe(ItemsGENERIC.itemTormentedTwine.getBrewItemKey(), new AltarPower(4000), new BrewActionRitualRecipe.Recipe[] { new BrewActionRitualRecipe.Recipe(new ItemStack(BlocksPIT_GRASS, 4), new ItemStack[] { new ItemStack(Items.field_151075_bm), new ItemStack(Blocks.field_150346_d), new ItemStack(Blocks.field_150327_N) }), new BrewActionRitualRecipe.Recipe(new ItemStack(BlocksPIT_DIRT, 4), new ItemStack[] { ItemsGENERIC.itemMandrakeRoot.createStack(), new ItemStack(Blocks.field_150346_d) }) }));
    





    register(new BrewActionRitualRecipe(new BrewItemKey(Items.field_151111_aL), new AltarPower(5000), new BrewActionRitualRecipe.Recipe[] { new BrewActionRitualRecipe.Recipe(new ItemStack(ItemsPLAYER_COMPASS), new ItemStack[] { new ItemStack(Items.field_151075_bm), ItemsGENERIC.itemTearOfTheGoddess.createStack(), new ItemStack(Blocks.field_150395_bd), new ItemStack(Items.field_151070_bp) }) }));
  }
  



  private final Hashtable<BrewItemKey, BrewAction> ingredients = new Hashtable();
  private final List<BrewActionRitualRecipe> recipes = new ArrayList();
  
  public List<BrewActionRitualRecipe> getRecipes() {
    return recipes;
  }
  
  private BrewAction register(BrewAction ingredient) {
    if (!ingredients.containsKey(ITEM_KEY)) {
      ingredients.put(ITEM_KEY, ingredient);
      
      if ((ingredient instanceof BrewActionRitualRecipe)) {
        recipes.add((BrewActionRitualRecipe)ingredient);
      }
    }
    return ingredient;
  }
  
  public int getAltarPower(ItemStack stack) {
    BrewItemKey key = BrewItemKey.fromStack(stack);
    if (key != null) {
      BrewAction action = (BrewAction)ingredients.get(key);
      if (action != null) {
        AltarPower power = new AltarPower(0);
        action.accumulatePower(power);
        return power.getPower();
      }
    }
    return -1;
  }
  
  public BrewAction getActionForItemStack(ItemStack stack) {
    return (BrewAction)ingredients.get(BrewItemKey.fromStack(stack));
  }
  
  public int getBrewColor(NBTTagCompound nbtRoot) {
    return nbtRoot != null ? nbtRoot.func_74762_e("Color") : -16744448;
  }
  
  public AltarPower getPowerRequired(NBTTagCompound nbtBrew) {
    BrewActionList actionList = new BrewActionList(nbtBrew, ingredients);
    AltarPower totalPower = new AltarPower(0);
    for (BrewAction action : actions) {
      action.accumulatePower(totalPower);
    }
    return totalPower;
  }
  
  public boolean isSplash(NBTTagCompound nbtBrew) {
    if ((nbtBrew != null) && (!nbtBrew.func_74764_b("Splash"))) {
      nbtBrew.func_74757_a("Splash", false);
      BrewActionList actionList = new BrewActionList(nbtBrew, ingredients);
      for (BrewAction action : actions) {
        if (action.createsSplash()) {
          nbtBrew.func_74757_a("Splash", true);
          break;
        }
      }
    }
    
    return (nbtBrew != null) && (nbtBrew.func_74767_n("Splash"));
  }
  
  public String getBrewName(NBTTagCompound nbtRoot) {
    BrewActionList actionList = new BrewActionList(nbtRoot, ingredients);
    BrewNameBuilder nameBuilder = new BrewNameBuilder(true);
    for (BrewAction action : actions) {
      BrewNamePart name = action.getNamePart();
      if (name != null) {
        name.applyTo(nameBuilder);
      }
    }
    return nameBuilder.toString();
  }
  
  public String getBrewInformation(NBTTagCompound nbtRoot) {
    BrewActionList actionList = new BrewActionList(nbtRoot, ingredients);
    BrewNameBuilder nameBuilder = new BrewNameBuilder(false);
    for (BrewAction action : actions) {
      BrewNamePart name = action.getNamePart();
      if (name != null) {
        name.applyTo(nameBuilder);
      }
    }
    String tooltip = nameBuilder.toString();
    int drinkSpeed = getBrewDrinkSpeed(nbtRoot);
    if (drinkSpeed != 32) {
      if (drinkSpeed > 48) {
        tooltip = tooltip + String.format(Witchery.resource("witchery:brew.drinkspeed"), new Object[] { Witchery.resource("witchery:brew.drinkspeed.veryslow") });
      }
      else if (drinkSpeed > 32) {
        tooltip = tooltip + String.format(Witchery.resource("witchery:brew.drinkspeed"), new Object[] { Witchery.resource("witchery:brew.drinkspeed.slow") });
      }
      else if (drinkSpeed < 16) {
        tooltip = tooltip + String.format(Witchery.resource("witchery:brew.drinkspeed"), new Object[] { Witchery.resource("witchery:brew.drinkspeed.veryfast") });
      }
      else if (drinkSpeed < 32) {
        tooltip = tooltip + String.format(Witchery.resource("witchery:brew.drinkspeed"), new Object[] { Witchery.resource("witchery:brew.drinkspeed.fast") });
      }
    }
    
    return tooltip;
  }
  
  public void updateBrewInformation(NBTTagCompound nbtRoot) {
    BrewActionList actionList = new BrewActionList(nbtRoot, ingredients);
    BrewNameBuilder nameBuilder = new BrewNameBuilder(false);
    for (BrewAction action : actions) {
      BrewNamePart name = action.getNamePart();
      if (name != null) {
        name.applyTo(nameBuilder);
      }
    }
    String tooltip = nameBuilder.toString();
    int drinkSpeed = getBrewDrinkSpeed(nbtRoot);
    if (drinkSpeed != 32) {
      if (drinkSpeed > 48) {
        tooltip = tooltip + String.format(Witchery.resource("witchery:brew.drinkspeed"), new Object[] { Witchery.resource("witchery:brew.drinkspeed.veryslow") });
      }
      else if (drinkSpeed > 32) {
        tooltip = tooltip + String.format(Witchery.resource("witchery:brew.drinkspeed"), new Object[] { Witchery.resource("witchery:brew.drinkspeed.slow") });
      }
      else if (drinkSpeed < 16) {
        tooltip = tooltip + String.format(Witchery.resource("witchery:brew.drinkspeed"), new Object[] { Witchery.resource("witchery:brew.drinkspeed.veryfast") });
      }
      else if (drinkSpeed < 32) {
        tooltip = tooltip + String.format(Witchery.resource("witchery:brew.drinkspeed"), new Object[] { Witchery.resource("witchery:brew.drinkspeed.fast") });
      }
    }
    
    nbtRoot.func_74778_a("BrewInfo", tooltip);
    
    EffectLevelCounter effectCounter = new EffectLevelCounter();
    boolean actionFound = false;
    for (BrewAction action : actions) {
      action.augmentEffectLevels(effectCounter);
    }
    
    nbtRoot.func_74768_a("EffectCount", effectCounter.getEffectCount());
    nbtRoot.func_74768_a("RemainingCapacity", effectCounter.remainingCapactiy());
    nbtRoot.func_74768_a("UsedCapacity", effectCounter.usedCapacity());
  }
  
  public int getUsedCapacity(NBTTagCompound nbtRoot) {
    if (nbtRoot != null) {
      return nbtRoot.func_74762_e("UsedCapacity");
    }
    return 0;
  }
  
  public int getBrewDrinkSpeed(NBTTagCompound nbtRoot) {
    BrewActionList actionList = new BrewActionList(nbtRoot, ingredients);
    int drinkSpeed = 32;
    for (BrewAction action : actions) {
      drinkSpeed += action.getDrinkSpeedModifiers();
    }
    return Math.max(drinkSpeed, 2);
  }
  
  public boolean canAdd(NBTTagCompound nbtRoot, BrewAction brewAction, boolean isCauldronFull) {
    if (nbtRoot.func_74767_n("RitualTriggered")) {
      return false;
    }
    
    BrewActionList actionList = new BrewActionList(nbtRoot, ingredients);
    EffectLevelCounter effectCounter = new EffectLevelCounter();
    boolean actionFound = false;
    for (BrewAction action : actions) {
      action.augmentEffectLevels(effectCounter);
      if (action == brewAction) {
        actionFound = true;
      } else if (((action instanceof BrewActionEffect)) || ((action instanceof BrewPotionEffect))) {
        actionFound = false;
      }
    }
    
    if (!brewAction.canAdd(actionList, isCauldronFull, effectCounter.hasEffects())) {
      return false;
    }
    
    return (!actionFound) && (brewAction.augmentEffectLevels(effectCounter));
  }
  
  public EffectLevelCounter getBrewLevel(NBTTagCompound nbtRoot)
  {
    BrewActionList actionList = new BrewActionList(nbtRoot, ingredients);
    EffectLevelCounter effectCounter = new EffectLevelCounter();
    
    for (BrewAction action : actions) {
      action.augmentEffectLevels(effectCounter);
    }
    

    return effectCounter;
  }
  
  public void nullifyItems(NBTTagCompound nbtRoot, NBTTagList nbtItems, BrewAction brewAction) {
    BrewActionList actionList = new BrewActionList(nbtRoot, ingredients);
    actionList.nullifyItems(brewAction, nbtItems);
  }
  
  public void explodeBrew(World world, NBTTagCompound nbtRoot, Entity immuneEntity, double x, double y, double z) {
    BrewActionList actionList = new BrewActionList(nbtRoot, ingredients);
    world.func_72876_a(immuneEntity, x, y, z, Math.min(1.0F + actionList.size() * 0.5F, 10.0F), false);
  }
  
  public ModifierYield getYieldModifier(NBTTagCompound nbtBrew) {
    BrewActionList actionList = new BrewActionList(nbtBrew, ingredients);
    ModifierYield counter = new ModifierYield(0);
    for (BrewAction action : actions) {
      action.prepareYield(counter);
    }
    
    return counter;
  }
  


  public RitualStatus updateRitual(MinecraftServer server, World world, int x, int y, int z, NBTTagCompound nbtBrew, int covenSize, int ritualPulses, boolean lennyPresent)
  {
    BrewActionList actionList = new BrewActionList(nbtBrew, ingredients);
    ModifiersRitual modifiers = new ModifiersRitual(new BlockPosition(world, x, y, z), covenSize, ritualPulses, lennyPresent);
    
    actionList.prepareRitual(world, x, y, z, modifiers);
    ModifiersImpact modifiersImpact = new ModifiersImpact(new EntityPosition(modifiers.getTarget()), true, covenSize, null);
    
    for (BrewAction action : actions) {
      action.prepareSplashPotion(world, actionList, modifiersImpact);
    }
    return actionList.getTopAction().updateRitual(server, actionList, world, x, y, z, modifiers, modifiersImpact);
  }
  

  public boolean impactSplashPotion(World world, ItemStack stack, MovingObjectPosition mop, ModifiersImpact modifiers)
  {
    return impactSplashPotion(world, new BrewActionList(stack.func_77978_p(), ingredients), mop, modifiers);
  }
  
  public boolean impactSplashPotion(World world, NBTTagCompound nbtBrew, MovingObjectPosition mop, ModifiersImpact modifiers)
  {
    return impactSplashPotion(world, new BrewActionList(nbtBrew, ingredients), mop, modifiers);
  }
  
  public boolean impactSplashPotion(World world, BrewActionList actionList, MovingObjectPosition mop, ModifiersImpact modifiers)
  {
    for (BrewAction action : actions) {
      action.prepareSplashPotion(world, actionList, modifiers);
    }
    modifiers.getDispersal().onImpactSplashPotion(world, actionList.getTagCompound(), mop, modifiers);
    return true;
  }
  
  public void applyToEntity(World world, EntityLivingBase targetEntity, NBTTagCompound nbtBrew, ModifiersEffect modifiers)
  {
    BrewActionList actionList = new BrewActionList(nbtBrew, ingredients);
    actionList.applyToEntity(world, targetEntity, modifiers);
  }
  
  public void applyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, NBTTagCompound nbtBrew, ModifiersEffect modifiers)
  {
    BrewActionList actionList = new BrewActionList(nbtBrew, ingredients);
    actionList.applyToBlock(world, x, y, z, side, radius, modifiers);
  }
  
  public void applyRitualToEntity(World world, EntityLivingBase target, NBTTagCompound nbtBrew, ModifiersRitual ritualModifiers, ModifiersEffect modifiers)
  {
    BrewActionList actionList = new BrewActionList(nbtBrew, ingredients);
    actionList.applyRitualToEnitity(world, target, ritualModifiers, modifiers);
  }
  
  public void applyRitualToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, NBTTagCompound nbtBrew, ModifiersRitual ritualModifiers, ModifiersEffect effectModifiers)
  {
    BrewActionList actionList = new BrewActionList(nbtBrew, ingredients);
    actionList.applyRitualToBlock(world, x, y, z, side, radius, ritualModifiers, effectModifiers);
  }
  
  public void init() {}
}
