package com.emoniph.witchery;

import com.emoniph.witchery.crafting.DistilleryRecipes;
import com.emoniph.witchery.crafting.KettleRecipes;
import com.emoniph.witchery.crafting.KettleRecipes.KettleRecipe;
import com.emoniph.witchery.crafting.RecipeAttachTaglock;
import com.emoniph.witchery.crafting.RecipeShapelessAddColor;
import com.emoniph.witchery.crafting.RecipeShapelessAddKeys;
import com.emoniph.witchery.crafting.RecipeShapelessAddPotion;
import com.emoniph.witchery.crafting.RecipeShapelessBiomeCopy;
import com.emoniph.witchery.crafting.RecipeShapelessPoppet;
import com.emoniph.witchery.crafting.RecipeShapelessRepair;
import com.emoniph.witchery.crafting.SpinningRecipes;
import com.emoniph.witchery.entity.EntityBabaYaga;
import com.emoniph.witchery.entity.EntityDemon;
import com.emoniph.witchery.entity.EntityEnt;
import com.emoniph.witchery.entity.EntityFamiliar;
import com.emoniph.witchery.entity.EntityImp;
import com.emoniph.witchery.entity.EntityOwl;
import com.emoniph.witchery.entity.EntityReflection;
import com.emoniph.witchery.entity.EntityToad;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.infusion.Infusion.Registry;
import com.emoniph.witchery.infusion.infusions.InfusionInfernal;
import com.emoniph.witchery.infusion.infusions.InfusionLight;
import com.emoniph.witchery.infusion.infusions.InfusionOtherwhere;
import com.emoniph.witchery.infusion.infusions.InfusionOverworld;
import com.emoniph.witchery.infusion.infusions.creature.CreaturePower.Registry;
import com.emoniph.witchery.infusion.infusions.creature.CreaturePowerBat;
import com.emoniph.witchery.infusion.infusions.creature.CreaturePowerBlaze;
import com.emoniph.witchery.infusion.infusions.creature.CreaturePowerCreeper;
import com.emoniph.witchery.infusion.infusions.creature.CreaturePowerEnderman;
import com.emoniph.witchery.infusion.infusions.creature.CreaturePowerHeal;
import com.emoniph.witchery.infusion.infusions.creature.CreaturePowerJump;
import com.emoniph.witchery.infusion.infusions.creature.CreaturePowerPigMan;
import com.emoniph.witchery.infusion.infusions.creature.CreaturePowerSkeleton;
import com.emoniph.witchery.infusion.infusions.creature.CreaturePowerSpeed;
import com.emoniph.witchery.infusion.infusions.creature.CreaturePowerSpider;
import com.emoniph.witchery.infusion.infusions.creature.CreaturePowerSquid;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemGeneral.DreamWeave;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import com.emoniph.witchery.item.ItemPoppet;
import com.emoniph.witchery.item.ItemPoppet.PoppetType;
import com.emoniph.witchery.item.ItemVanillaPotion;
import com.emoniph.witchery.item.ItemVanillaPotion.SubItem;
import com.emoniph.witchery.predictions.PredictionArrow;
import com.emoniph.witchery.predictions.PredictionBuriedTreasure;
import com.emoniph.witchery.predictions.PredictionFall;
import com.emoniph.witchery.predictions.PredictionFallInLove;
import com.emoniph.witchery.predictions.PredictionFight;
import com.emoniph.witchery.predictions.PredictionManager;
import com.emoniph.witchery.predictions.PredictionMultiMine;
import com.emoniph.witchery.predictions.PredictionNetherTrip;
import com.emoniph.witchery.predictions.PredictionRescue;
import com.emoniph.witchery.predictions.PredictionWet;
import com.emoniph.witchery.ritual.Circle;
import com.emoniph.witchery.ritual.RiteRegistry;
import com.emoniph.witchery.ritual.RiteRegistry.Ritual;
import com.emoniph.witchery.ritual.RitualTraits;
import com.emoniph.witchery.ritual.Sacrifice;
import com.emoniph.witchery.ritual.SacrificeItem;
import com.emoniph.witchery.ritual.SacrificeLiving;
import com.emoniph.witchery.ritual.SacrificeMultiple;
import com.emoniph.witchery.ritual.SacrificeOptionalItem;
import com.emoniph.witchery.ritual.SacrificePower;
import com.emoniph.witchery.ritual.rites.RiteBanishDemon;
import com.emoniph.witchery.ritual.rites.RiteBindCircleToTalisman;
import com.emoniph.witchery.ritual.rites.RiteBindFamiliar;
import com.emoniph.witchery.ritual.rites.RiteBindSpiritsToFetish;
import com.emoniph.witchery.ritual.rites.RiteBlindness;
import com.emoniph.witchery.ritual.rites.RiteCallCreatures;
import com.emoniph.witchery.ritual.rites.RiteClimateChange;
import com.emoniph.witchery.ritual.rites.RiteCookItem;
import com.emoniph.witchery.ritual.rites.RiteCurseCreature;
import com.emoniph.witchery.ritual.rites.RiteCurseOfTheWolf;
import com.emoniph.witchery.ritual.rites.RiteCursePoppets;
import com.emoniph.witchery.ritual.rites.RiteEclipse;
import com.emoniph.witchery.ritual.rites.RiteFertility;
import com.emoniph.witchery.ritual.rites.RiteForestation;
import com.emoniph.witchery.ritual.rites.RiteGlyphicTransformation;
import com.emoniph.witchery.ritual.rites.RiteHellOnEarth;
import com.emoniph.witchery.ritual.rites.RiteInfusePlayers;
import com.emoniph.witchery.ritual.rites.RiteInfusionRecharge;
import com.emoniph.witchery.ritual.rites.RiteNaturesPower;
import com.emoniph.witchery.ritual.rites.RitePartEarth;
import com.emoniph.witchery.ritual.rites.RitePriorIncarnation;
import com.emoniph.witchery.ritual.rites.RiteProtectionCircleAttractive;
import com.emoniph.witchery.ritual.rites.RiteProtectionCircleBarrier;
import com.emoniph.witchery.ritual.rites.RiteRainOfToads;
import com.emoniph.witchery.ritual.rites.RiteRaiseColumn;
import com.emoniph.witchery.ritual.rites.RiteRaiseVolcano;
import com.emoniph.witchery.ritual.rites.RiteSetNBT;
import com.emoniph.witchery.ritual.rites.RiteSphereEffect;
import com.emoniph.witchery.ritual.rites.RiteSummonCreature;
import com.emoniph.witchery.ritual.rites.RiteSummonItem;
import com.emoniph.witchery.ritual.rites.RiteSummonItem.Binding;
import com.emoniph.witchery.ritual.rites.RiteSummonSpectralStone;
import com.emoniph.witchery.ritual.rites.RiteTeleportEntity;
import com.emoniph.witchery.ritual.rites.RiteTeleportToWaystone;
import com.emoniph.witchery.ritual.rites.RiteTransposeOres;
import com.emoniph.witchery.ritual.rites.RiteWeatherCallStorm;
import com.emoniph.witchery.util.ClothColor;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.Dye;
import cpw.mods.fml.common.registry.GameRegistry;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class WitcheryRecipes
{
  public Infusion infusionEnder;
  public Infusion infusionLight;
  public Infusion infusionWorld;
  public Infusion infusionBeast;
  
  public WitcheryRecipes() {}
  
  public void preInit()
  {
    RecipeSorter.register("witchery:bindpoppet", RecipeShapelessPoppet.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
    RecipeSorter.register("witchery:addpotion", RecipeShapelessAddPotion.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
    RecipeSorter.register("witchery:repair", RecipeShapelessRepair.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
    RecipeSorter.register("witchery:addcolor", RecipeShapelessAddColor.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
    RecipeSorter.register("witchery:addkeys", RecipeShapelessAddKeys.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
    RecipeSorter.register("witchery:attachtaglock", RecipeAttachTaglock.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
    RecipeSorter.register("witchery:biomecopy", RecipeShapelessBiomeCopy.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
    

    if (instanceallowStatueGoddessRecipe) {
      GameRegistry.addRecipe(new ItemStack(BlocksSTATUE_GODDESS), new Object[] { "s#s", "shs", "###", Character.valueOf('h'), ItemsGENERIC.itemDemonHeart.createStack(), Character.valueOf('#'), new ItemStack(Blocks.field_150348_b), Character.valueOf('s'), new ItemStack(Items.field_151156_bN) });
    }
    






    ItemStack ash = ItemsGENERIC.itemAshWood.createStack();
    ItemStack bone = new ItemStack(Items.field_151103_aS);
    GameRegistry.addShapelessRecipe(Dye.BONE_MEAL.createStack(4), new Object[] { bone, ash, ash });
    GameRegistry.addShapelessRecipe(Dye.BONE_MEAL.createStack(5), new Object[] { bone, ash, ash, ash, ash });
    GameRegistry.addShapelessRecipe(Dye.BONE_MEAL.createStack(6), new Object[] { bone, ash, ash, ash, ash, ash, ash });
    GameRegistry.addShapelessRecipe(Dye.BONE_MEAL.createStack(7), new Object[] { bone, ash, ash, ash, ash, ash, ash, ash, ash });
    
    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlocksWICKER_BUNDLE, 1, 0), new Object[] { "###", "###", "###", Character.valueOf('#'), "treeSapling" }));
    




    GameRegistry.addRecipe(new ItemStack(BlocksWICKER_BUNDLE, 5, 1), new Object[] { "#b#", "###", Character.valueOf('#'), new ItemStack(BlocksWICKER_BUNDLE), Character.valueOf('b'), ItemsGENERIC.itemInfernalBlood.createStack() });
    






    addPlantMineRecipe(0, new ItemStack(Blocks.field_150328_O), ItemsGENERIC.itemBrewOfWebs.createStack());
    addPlantMineRecipe(1, new ItemStack(Blocks.field_150328_O), ItemsGENERIC.itemBrewOfInk.createStack());
    addPlantMineRecipe(2, new ItemStack(Blocks.field_150328_O), ItemsGENERIC.itemBrewOfThorns.createStack());
    addPlantMineRecipe(3, new ItemStack(Blocks.field_150328_O), ItemsGENERIC.itemBrewOfSprouting.createStack());
    addPlantMineRecipe(4, new ItemStack(Blocks.field_150327_N), ItemsGENERIC.itemBrewOfWebs.createStack());
    addPlantMineRecipe(5, new ItemStack(Blocks.field_150327_N), ItemsGENERIC.itemBrewOfInk.createStack());
    addPlantMineRecipe(6, new ItemStack(Blocks.field_150327_N), ItemsGENERIC.itemBrewOfThorns.createStack());
    addPlantMineRecipe(7, new ItemStack(Blocks.field_150327_N), ItemsGENERIC.itemBrewOfSprouting.createStack());
    addPlantMineRecipe(8, new ItemStack(Blocks.field_150330_I), ItemsGENERIC.itemBrewOfWebs.createStack());
    addPlantMineRecipe(9, new ItemStack(Blocks.field_150330_I), ItemsGENERIC.itemBrewOfInk.createStack());
    addPlantMineRecipe(10, new ItemStack(Blocks.field_150330_I), ItemsGENERIC.itemBrewOfThorns.createStack());
    addPlantMineRecipe(11, new ItemStack(Blocks.field_150330_I), ItemsGENERIC.itemBrewOfSprouting.createStack());
    
    GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151170_bI, 2), new Object[] { new ItemStack(Items.field_151170_bI), new ItemStack(Items.field_151174_bG), new ItemStack(Items.field_151070_bp) });
    












    GameRegistry.addRecipe(new ItemStack(BlocksLEAPING_LILY, 5), new Object[] { "#p#", "c#c", "#b#", Character.valueOf('#'), new ItemStack(Blocks.field_150392_bi), Character.valueOf('p'), new ItemStack(Items.field_151068_bn, 1, 8194), Character.valueOf('b'), ItemsGENERIC.itemBreathOfTheGoddess.createStack(), Character.valueOf('c'), new ItemStack(Items.field_151114_aO) });
    








    GameRegistry.addRecipe(ItemsGENERIC.itemBoneNeedle.createStack(8), new Object[] { "ab", Character.valueOf('a'), new ItemStack(Items.field_151103_aS), Character.valueOf('b'), new ItemStack(Items.field_151145_ak) });
    




    GameRegistry.addRecipe(new ItemStack(ItemsTAGLOCK_KIT), new Object[] { "ab", Character.valueOf('b'), ItemsGENERIC.itemBoneNeedle.createStack(), Character.valueOf('a'), new ItemStack(Items.field_151069_bo) });
    




    ItemStack taglocks = new ItemStack(ItemsTAGLOCK_KIT, 1, 1);
    ItemStack unboundPoppet = ItemsPOPPET.unboundPoppet.createStack();
    
    GameRegistry.addRecipe(unboundPoppet, new Object[] { "xyx", "ayb", "x x", Character.valueOf('x'), new ItemStack(Blocks.field_150325_L), Character.valueOf('y'), new ItemStack(BlocksSPANISH_MOSS), Character.valueOf('a'), ItemsGENERIC.itemBoneNeedle.createStack(), Character.valueOf('b'), new ItemStack(Items.field_151007_F) });
    








    ItemStack earthPoppet = ItemsPOPPET.earthPoppet.createStack();
    GameRegistry.addRecipe(ItemsPOPPET.earthPoppet.createStack(), new Object[] { " a ", "b#b", " c ", Character.valueOf('#'), ItemsPOPPET.unboundPoppet.createStack(), Character.valueOf('b'), new ItemStack(Items.field_151008_G), Character.valueOf('a'), new ItemStack(Items.field_151119_aD), Character.valueOf('c'), new ItemStack(Blocks.field_150346_d) });
    








    CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessPoppet(earthPoppet, new ItemStack[] { taglocks, earthPoppet }));
    



    ItemStack waterPoppet = ItemsPOPPET.waterPoppet.createStack();
    GameRegistry.addRecipe(waterPoppet, new Object[] { " a ", "b#b", " a ", Character.valueOf('#'), ItemsPOPPET.unboundPoppet.createStack(), Character.valueOf('a'), ItemsGENERIC.itemArtichoke.createStack(), Character.valueOf('b'), Dye.INK_SAC.createStack() });
    







    CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessPoppet(waterPoppet, new ItemStack[] { taglocks, waterPoppet }));
    



    ItemStack foodPoppet = ItemsPOPPET.foodPoppet.createStack();
    GameRegistry.addRecipe(foodPoppet, new Object[] { " a ", "b#b", " a ", Character.valueOf('#'), unboundPoppet, Character.valueOf('b'), new ItemStack(Items.field_151060_bw), Character.valueOf('a'), new ItemStack(Items.field_151078_bh) });
    







    CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessPoppet(foodPoppet, new ItemStack[] { taglocks, foodPoppet }));
    



    ItemStack firePoppet = ItemsPOPPET.firePoppet.createStack();
    GameRegistry.addRecipe(firePoppet, new Object[] { " a ", "b#b", " a ", Character.valueOf('#'), unboundPoppet, Character.valueOf('b'), ItemsGENERIC.itemBatWool.createStack(), Character.valueOf('a'), new ItemStack(BlocksEMBER_MOSS) });
    







    CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessPoppet(firePoppet, new ItemStack[] { taglocks, firePoppet }));
    



    ItemStack antiVoodooPoppet = ItemsPOPPET.antiVoodooPoppet.createStack();
    GameRegistry.addRecipe(antiVoodooPoppet, new Object[] { "ced", "a#b", "dfc", Character.valueOf('#'), unboundPoppet, Character.valueOf('a'), ItemsGENERIC.itemBelladonnaFlower.createStack(), Character.valueOf('b'), ItemsGENERIC.itemMandrakeRoot.createStack(), Character.valueOf('c'), new ItemStack(Blocks.field_150327_N), Character.valueOf('d'), new ItemStack(Blocks.field_150328_O), Character.valueOf('e'), new ItemStack(Blocks.field_150337_Q), Character.valueOf('f'), new ItemStack(Blocks.field_150338_P) });
    











    CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessPoppet(antiVoodooPoppet, new ItemStack[] { taglocks, antiVoodooPoppet }));
    



    ItemStack poppetProectionPoppet = ItemsPOPPET.poppetProtectionPoppet.createStack();
    GameRegistry.addRecipe(poppetProectionPoppet, new Object[] { "gfg", "e#e", "glg", Character.valueOf('#'), antiVoodooPoppet, Character.valueOf('l'), ItemsGENERIC.itemDropOfLuck.createStack(), Character.valueOf('e'), ItemsGENERIC.itemEnderDew.createStack(), Character.valueOf('g'), new ItemStack(Items.field_151074_bl), Character.valueOf('f'), ItemsGENERIC.itemToeOfFrog.createStack() });
    









    CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessPoppet(poppetProectionPoppet, new ItemStack[] { taglocks, poppetProectionPoppet }));
    



    ItemStack voodooPoppet = ItemsPOPPET.voodooPoppet.createStack();
    GameRegistry.addRecipe(voodooPoppet, new Object[] { " d ", "a#b", " c ", Character.valueOf('#'), unboundPoppet, Character.valueOf('a'), ItemsGENERIC.itemBelladonnaFlower.createStack(), Character.valueOf('b'), ItemsGENERIC.itemMandrakeRoot.createStack(), Character.valueOf('c'), ItemsGENERIC.itemExhaleOfTheHornedOne.createStack(), Character.valueOf('d'), new ItemStack(Items.field_151071_bq) });
    









    CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessPoppet(voodooPoppet, new ItemStack[] { taglocks, voodooPoppet }));
    



    ItemStack toolPoppet = ItemsPOPPET.toolPoppet.createStack();
    GameRegistry.addRecipe(toolPoppet, new Object[] { " a ", "b#b", " a ", Character.valueOf('#'), unboundPoppet, Character.valueOf('a'), ItemsGENERIC.itemReekOfMisfortune.createStack(), Character.valueOf('b'), ItemsGENERIC.itemDropOfLuck.createStack() });
    







    CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessPoppet(toolPoppet, new ItemStack[] { taglocks, toolPoppet }));
    




    ItemStack armorPoppet = ItemsPOPPET.armorPoppet.createStack();
    GameRegistry.addRecipe(armorPoppet, new Object[] { " a ", "b#b", " d ", Character.valueOf('#'), unboundPoppet, Character.valueOf('a'), ItemsGENERIC.itemReekOfMisfortune.createStack(), Character.valueOf('b'), ItemsGENERIC.itemDropOfLuck.createStack(), Character.valueOf('d'), ItemsGENERIC.itemDiamondVapour.createStack() });
    








    CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessPoppet(armorPoppet, new ItemStack[] { taglocks, armorPoppet }));
    





    ItemStack avoidDeathPoppet = ItemsPOPPET.deathPoppet.createStack();
    GameRegistry.addRecipe(avoidDeathPoppet, new Object[] { "axb", "x#x", " x ", Character.valueOf('#'), unboundPoppet, Character.valueOf('a'), ItemsGENERIC.itemDropOfLuck.createStack(), Character.valueOf('b'), ItemsGENERIC.itemDiamondVapour.createStack(), Character.valueOf('x'), new ItemStack(Items.field_151074_bl) });
    








    CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessPoppet(avoidDeathPoppet, new ItemStack[] { taglocks, avoidDeathPoppet }));
    



    ItemStack vampiricPoppet = ItemsPOPPET.vampiricPoppet.createStack();
    GameRegistry.addRecipe(vampiricPoppet, new Object[] { " b ", "c#c", " a ", Character.valueOf('#'), unboundPoppet, Character.valueOf('a'), ItemsGENERIC.itemExhaleOfTheHornedOne.createStack(), Character.valueOf('b'), ItemsGENERIC.itemDiamondVapour.createStack(), Character.valueOf('c'), ItemsGENERIC.itemBatWool.createStack() });
    








    CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessPoppet(vampiricPoppet, new ItemStack[] { taglocks, taglocks, vampiricPoppet }));
    




    GameRegistry.addRecipe(new ItemStack(BlocksPOPPET_SHELF), new Object[] { "yzy", "zxz", "yzy", Character.valueOf('x'), ClothColor.GREEN.createStack(), Character.valueOf('y'), ItemsGENERIC.itemAttunedStone.createStack(), Character.valueOf('z'), new ItemStack(Blocks.field_150385_bj) });
    







    GameRegistry.addRecipe(new ItemStack(BlocksOVEN_IDLE), new Object[] { " z ", "xxx", "xzx", Character.valueOf('x'), new ItemStack(Items.field_151042_j), Character.valueOf('z'), new ItemStack(Blocks.field_150411_aY) });
    














    GameRegistry.addRecipe(ItemsGENERIC.itemSoftClayJar.createStack(4), new Object[] { " # ", "###", Character.valueOf('#'), new ItemStack(Items.field_151119_aD) });
    





    GameRegistry.addRecipe(new ItemStack(BlocksPLANKS, 4, 0), new Object[] { "#", Character.valueOf('#'), new ItemStack(BlocksLOG, 1, 0) });
    



    GameRegistry.addRecipe(new ItemStack(BlocksPLANKS, 4, 1), new Object[] { "#", Character.valueOf('#'), new ItemStack(BlocksLOG, 1, 1) });
    



    GameRegistry.addRecipe(new ItemStack(BlocksPLANKS, 4, 2), new Object[] { "#", Character.valueOf('#'), new ItemStack(BlocksLOG, 1, 2) });
    



    CraftingManager.func_77594_a().func_77592_b().add(0, getShapedRecipe(ItemsGENERIC.itemDoorRowan.createStack(), new Object[] { "##", "##", "##", Character.valueOf('#'), new ItemStack(BlocksPLANKS, 1, 0) }));
    





    CraftingManager.func_77594_a().func_77592_b().add(0, getShapedRecipe(ItemsGENERIC.itemDoorAlder.createStack(), new Object[] { "##", "##", "##", Character.valueOf('#'), new ItemStack(BlocksPLANKS, 1, 1) }));
    





    GameRegistry.addRecipe(new ItemStack(BlocksSTAIRS_ALDER, 4, 0), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), new ItemStack(BlocksPLANKS, 1, 1) });
    





    GameRegistry.addRecipe(new ItemStack(BlocksSTAIRS_HAWTHORN, 4, 0), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), new ItemStack(BlocksPLANKS, 1, 2) });
    





    GameRegistry.addRecipe(new ItemStack(BlocksSTAIRS_ROWAN, 4, 0), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), new ItemStack(BlocksPLANKS, 1, 0) });
    






    GameRegistry.addRecipe(new ItemStack(BlocksSNOW_STAIRS, 4, 0), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), new ItemStack(Blocks.field_150433_aE, 1, 0) });
    





    CraftingManager.func_77594_a().func_77592_b().add(0, getShapedRecipe(new ItemStack(BlocksSNOW_SLAB_SINGLE, 6, 0), new Object[] { "###", "###", Character.valueOf('#'), new ItemStack(Blocks.field_150431_aC, 1, 0) }));
    




    GameRegistry.addRecipe(new ItemStack(BlocksSNOW_PRESSURE_PLATE, 1, 0), new Object[] { "##", Character.valueOf('#'), new ItemStack(Blocks.field_150433_aE, 1, 0) });
    



















    GameRegistry.addRecipe(ItemsGENERIC.itemQuicklime.createStack(), new Object[] { "#", Character.valueOf('#'), ItemsGENERIC.itemAshWood.createStack() });
    



    GameRegistry.addRecipe(new ItemStack(BlocksALTAR, 3), new Object[] { "abc", "xyx", "xyx", Character.valueOf('a'), ItemsGENERIC.itemBreathOfTheGoddess.createStack(), Character.valueOf('b'), new ItemStack(Items.field_151068_bn), Character.valueOf('c'), ItemsGENERIC.itemExhaleOfTheHornedOne.createStack(), Character.valueOf('x'), new ItemStack(Blocks.field_150417_aV, 1, 0), Character.valueOf('y'), new ItemStack(BlocksLOG, 1, 0) });
    









    GameRegistry.addRecipe(ItemsGENERIC.itemAttunedStone.createStack(), new Object[] { "a", "b", "c", Character.valueOf('a'), ItemsGENERIC.itemWhiffOfMagic.createStack(), Character.valueOf('b'), new ItemStack(Items.field_151045_i), Character.valueOf('c'), new ItemStack(Items.field_151129_at) });
    







    GameRegistry.addRecipe(new ItemStack(BlocksDISTILLERY_IDLE), new Object[] { "bxb", "xxx", "yay", Character.valueOf('a'), ItemsGENERIC.itemAttunedStone.createStack(), Character.valueOf('b'), ItemsGENERIC.itemEmptyClayJar.createStack(), Character.valueOf('y'), new ItemStack(Items.field_151043_k), Character.valueOf('x'), new ItemStack(Items.field_151042_j) });
    








    GameRegistry.addRecipe(new ItemStack(BlocksKETTLE), new Object[] { "bxb", "xax", " y ", Character.valueOf('a'), ItemsGENERIC.itemAttunedStone.createStack(), Character.valueOf('b'), new ItemStack(Items.field_151055_y), Character.valueOf('x'), new ItemStack(Items.field_151007_F), Character.valueOf('y'), new ItemStack(Items.field_151066_bu) });
    








    GameRegistry.addRecipe(new ItemStack(BlocksBRAZIER), new Object[] { "#a#", " w ", "www", Character.valueOf('a'), ItemsGENERIC.itemNecroStone.createStack(), Character.valueOf('w'), new ItemStack(Items.field_151055_y), Character.valueOf('#'), new ItemStack(Items.field_151042_j) });
    







    GameRegistry.addRecipe(new ItemStack(ItemsCHALK_RITUAL, 2, 0), new Object[] { "xax", "xyx", "xyx", Character.valueOf('a'), ItemsGENERIC.itemTearOfTheGoddess.createStack(), Character.valueOf('x'), ItemsGENERIC.itemAshWood.createStack(), Character.valueOf('y'), ItemsGENERIC.itemGypsum.createStack() });
    





































    GameRegistry.addRecipe(ItemsGENERIC.itemWaystone.createStack(), new Object[] { "ab", Character.valueOf('a'), new ItemStack(Items.field_151145_ak), Character.valueOf('b'), ItemsGENERIC.itemBoneNeedle.createStack() });
    




    GameRegistry.addRecipe(new ItemStack(ItemsARTHANA), new Object[] { " y ", "xbx", " a ", Character.valueOf('a'), new ItemStack(Items.field_151055_y), Character.valueOf('b'), new ItemStack(Items.field_151166_bC), Character.valueOf('y'), new ItemStack(Items.field_151043_k), Character.valueOf('x'), new ItemStack(Items.field_151074_bl) });
    







    GameRegistry.addRecipe(new ItemStack(ItemsBOLINE), new Object[] { "y", "a", "b", Character.valueOf('a'), new ItemStack(Items.field_151103_aS), Character.valueOf('b'), new ItemStack(Items.field_151166_bC), Character.valueOf('y'), new ItemStack(Items.field_151042_j) });
    






    GameRegistry.addRecipe(new ItemStack(ItemsCIRCLE_TALISMAN), new Object[] { "yxy", "xax", "yxy", Character.valueOf('a'), new ItemStack(Items.field_151045_i), Character.valueOf('x'), new ItemStack(Items.field_151043_k), Character.valueOf('y'), new ItemStack(Items.field_151074_bl) });
    






    GameRegistry.addRecipe(ItemsGENERIC.itemBroom.createStack(), new Object[] { " x ", " x ", "yyy", Character.valueOf('x'), new ItemStack(Items.field_151055_y), Character.valueOf('y'), new ItemStack(BlocksSAPLING, 1, 2) });
    




























    GameRegistry.addShapelessRecipe(ItemsGENERIC.itemOddPorkRaw.createStack(), new Object[] { ItemsGENERIC.itemMutandis.createStack(), new ItemStack(Items.field_151078_bh) });
    GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151147_al), new Object[] { ItemsGENERIC.itemMutandis.createStack(), ItemsGENERIC.itemOddPorkRaw.createStack() });
    GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151147_al), new Object[] { ItemsGENERIC.itemMutandis.createStack(), new ItemStack(Items.field_151076_bf) });
    GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151076_bf), new Object[] { ItemsGENERIC.itemMutandis.createStack(), new ItemStack(Items.field_151082_bd) });
    GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151082_bd), new Object[] { ItemsGENERIC.itemMutandis.createStack(), new ItemStack(Items.field_151147_al) });
    

    GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151157_am), new Object[] { ItemsGENERIC.itemMutandis.createStack(), ItemsGENERIC.itemOddPorkCooked.createStack() });
    GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151157_am), new Object[] { ItemsGENERIC.itemMutandis.createStack(), new ItemStack(Items.field_151077_bg) });
    GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151077_bg), new Object[] { ItemsGENERIC.itemMutandis.createStack(), new ItemStack(Items.field_151083_be) });
    GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151083_be), new Object[] { ItemsGENERIC.itemMutandis.createStack(), new ItemStack(Items.field_151157_am) });
    

    GameRegistry.addShapelessRecipe(ItemsGENERIC.itemOddPorkCooked.createStack(), new Object[] { ItemsGENERIC.itemMutandisExtremis.createStack(), new ItemStack(Items.field_151078_bh) });
    GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151157_am), new Object[] { ItemsGENERIC.itemMutandisExtremis.createStack(), ItemsGENERIC.itemOddPorkRaw.createStack() });
    GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151157_am), new Object[] { ItemsGENERIC.itemMutandisExtremis.createStack(), new ItemStack(Items.field_151076_bf) });
    GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151077_bg), new Object[] { ItemsGENERIC.itemMutandisExtremis.createStack(), new ItemStack(Items.field_151082_bd) });
    GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151083_be), new Object[] { ItemsGENERIC.itemMutandisExtremis.createStack(), new ItemStack(Items.field_151147_al) });
    

    GameRegistry.addShapelessRecipe(ItemsGENERIC.itemOddPorkRaw.createStack(), new Object[] { ItemsGENERIC.itemMutandisExtremis.createStack(), new ItemStack(Items.field_151078_bh) });
    GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151147_al), new Object[] { ItemsGENERIC.itemMutandisExtremis.createStack(), ItemsGENERIC.itemOddPorkRaw.createStack() });
    GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151147_al), new Object[] { ItemsGENERIC.itemMutandisExtremis.createStack(), new ItemStack(Items.field_151076_bf) });
    GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151076_bf), new Object[] { ItemsGENERIC.itemMutandisExtremis.createStack(), new ItemStack(Items.field_151082_bd) });
    GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151082_bd), new Object[] { ItemsGENERIC.itemMutandisExtremis.createStack(), new ItemStack(Items.field_151147_al) });
    

    GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151157_am), new Object[] { ItemsGENERIC.itemMutandisExtremis.createStack(), ItemsGENERIC.itemOddPorkCooked.createStack() });
    GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151157_am), new Object[] { ItemsGENERIC.itemMutandisExtremis.createStack(), new ItemStack(Items.field_151077_bg) });
    GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151077_bg), new Object[] { ItemsGENERIC.itemMutandisExtremis.createStack(), new ItemStack(Items.field_151083_be) });
    GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151083_be), new Object[] { ItemsGENERIC.itemMutandisExtremis.createStack(), new ItemStack(Items.field_151157_am) });
    









    GameRegistry.addRecipe(ItemsGENERIC.itemCandelabra.createStack(), new Object[] { "xxx", "yay", " y ", Character.valueOf('x'), new ItemStack(Blocks.field_150478_aa), Character.valueOf('y'), new ItemStack(Items.field_151042_j), Character.valueOf('a'), ItemsGENERIC.itemAttunedStone.createStack() });
    






    GameRegistry.addRecipe(ItemsGENERIC.itemChaliceEmpty.createStack(), new Object[] { "yay", "yxy", " x ", Character.valueOf('x'), new ItemStack(Items.field_151043_k), Character.valueOf('y'), new ItemStack(Items.field_151074_bl), Character.valueOf('a'), ItemsGENERIC.itemAttunedStone.createStack() });
    






    GameRegistry.addRecipe(ItemsGENERIC.itemChaliceFull.createStack(), new Object[] { "b", "a", Character.valueOf('a'), ItemsGENERIC.itemChaliceEmpty.createStack(), Character.valueOf('b'), ItemsGENERIC.itemRedstoneSoup.createStack() });
    





    GameRegistry.addRecipe(new ItemStack(ItemsDIVINER_WATER), new Object[] { "yay", "yay", "axa", Character.valueOf('a'), new ItemStack(Items.field_151055_y), Character.valueOf('y'), new ItemStack(Items.field_151068_bn), Character.valueOf('x'), ItemsGENERIC.itemTearOfTheGoddess.createStack() });
    






    GameRegistry.addRecipe(new ItemStack(ItemsDIVINER_LAVA), new Object[] { " a ", " x ", "a a", Character.valueOf('x'), new ItemStack(ItemsDIVINER_WATER), Character.valueOf('a'), new ItemStack(Items.field_151072_bj) });
    





    GameRegistry.addRecipe(ItemsGENERIC.itemDreamMove.createStack(), new Object[] { "dxe", "bab", "cfc", Character.valueOf('a'), new ItemStack(Items.field_151160_bD), Character.valueOf('b'), ItemsGENERIC.itemFancifulThread.createStack(), Character.valueOf('f'), ItemsGENERIC.itemTormentedTwine.createStack(), Character.valueOf('c'), new ItemStack(Items.field_151008_G), Character.valueOf('d'), new ItemStack(Items.field_151068_bn, 1, 16450), Character.valueOf('e'), new ItemStack(Items.field_151068_bn, 1, 16458), Character.valueOf('x'), ItemsGENERIC.itemDiamondVapour.createStack() });
    











    GameRegistry.addRecipe(ItemsGENERIC.itemDreamMove.createStack(), new Object[] { "dxe", "bab", "cfc", Character.valueOf('a'), new ItemStack(Items.field_151160_bD), Character.valueOf('b'), ItemsGENERIC.itemFancifulThread.createStack(), Character.valueOf('f'), ItemsGENERIC.itemTormentedTwine.createStack(), Character.valueOf('c'), new ItemStack(Items.field_151008_G), Character.valueOf('d'), new ItemStack(Items.field_151068_bn, 1, 16450), Character.valueOf('e'), new ItemStack(Items.field_151068_bn, 1, 24650), Character.valueOf('x'), ItemsGENERIC.itemDiamondVapour.createStack() });
    











    GameRegistry.addRecipe(ItemsGENERIC.itemDreamDig.createStack(), new Object[] { "dxe", "bab", "cfc", Character.valueOf('a'), new ItemStack(Items.field_151160_bD), Character.valueOf('b'), ItemsGENERIC.itemFancifulThread.createStack(), Character.valueOf('f'), ItemsGENERIC.itemTormentedTwine.createStack(), Character.valueOf('c'), new ItemStack(Items.field_151008_G), Character.valueOf('d'), new ItemStack(Items.field_151068_bn, 1, 16457), Character.valueOf('e'), new ItemStack(Items.field_151068_bn, 1, 16456), Character.valueOf('x'), ItemsGENERIC.itemDiamondVapour.createStack() });
    











    GameRegistry.addRecipe(ItemsGENERIC.itemDreamDig.createStack(), new Object[] { "dxe", "bab", "cfc", Character.valueOf('a'), new ItemStack(Items.field_151160_bD), Character.valueOf('b'), ItemsGENERIC.itemFancifulThread.createStack(), Character.valueOf('f'), ItemsGENERIC.itemTormentedTwine.createStack(), Character.valueOf('c'), new ItemStack(Items.field_151008_G), Character.valueOf('d'), new ItemStack(Items.field_151068_bn, 1, 16457), Character.valueOf('e'), new ItemStack(Items.field_151068_bn, 1, 24648), Character.valueOf('x'), ItemsGENERIC.itemDiamondVapour.createStack() });
    











    GameRegistry.addRecipe(ItemsGENERIC.itemDreamEat.createStack(), new Object[] { "dxe", "bab", "cfc", Character.valueOf('a'), new ItemStack(Items.field_151160_bD), Character.valueOf('b'), ItemsGENERIC.itemFancifulThread.createStack(), Character.valueOf('f'), ItemsGENERIC.itemTormentedTwine.createStack(), Character.valueOf('c'), new ItemStack(Items.field_151008_G), Character.valueOf('d'), new ItemStack(Items.field_151068_bn, 1, 16421), Character.valueOf('e'), ItemsGENERIC.itemMellifluousHunger.createStack(), Character.valueOf('x'), ItemsGENERIC.itemDiamondVapour.createStack() });
    











    GameRegistry.addRecipe(ItemsGENERIC.itemDreamEat.createStack(), new Object[] { "dxe", "bab", "cfc", Character.valueOf('a'), new ItemStack(Items.field_151160_bD), Character.valueOf('b'), ItemsGENERIC.itemFancifulThread.createStack(), Character.valueOf('f'), ItemsGENERIC.itemTormentedTwine.createStack(), Character.valueOf('c'), new ItemStack(Items.field_151008_G), Character.valueOf('d'), new ItemStack(Items.field_151068_bn, 1, 16421), Character.valueOf('e'), ItemsGENERIC.itemMellifluousHunger.createStack(), Character.valueOf('x'), ItemsGENERIC.itemDiamondVapour.createStack() });
    











    GameRegistry.addRecipe(ItemsGENERIC.itemDreamNightmare.createStack(), new Object[] { "dxe", "bab", "cbc", Character.valueOf('a'), new ItemStack(Items.field_151160_bD), Character.valueOf('b'), ItemsGENERIC.itemTormentedTwine.createStack(), Character.valueOf('c'), new ItemStack(Items.field_151008_G), Character.valueOf('d'), new ItemStack(Items.field_151068_bn, 1, 16452), Character.valueOf('e'), new ItemStack(Items.field_151068_bn, 1, 16454), Character.valueOf('x'), ItemsGENERIC.itemDiamondVapour.createStack() });
    










    GameRegistry.addRecipe(ItemsGENERIC.itemDreamIntensity.createStack(), new Object[] { "dxe", "bab", "cfc", Character.valueOf('a'), new ItemStack(Items.field_151160_bD), Character.valueOf('b'), ItemsGENERIC.itemFancifulThread.createStack(), Character.valueOf('f'), ItemsGENERIC.itemTormentedTwine.createStack(), Character.valueOf('c'), new ItemStack(Items.field_151008_G), Character.valueOf('d'), ItemsGENERIC.itemBrewOfFlowingSpirit.createStack(), Character.valueOf('e'), ItemsGENERIC.itemBrewOfSleeping.createStack(), Character.valueOf('x'), ItemsGENERIC.itemDiamondVapour.createStack() });
    











    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemsCAULDRON_BOOK), new Object[] { " c ", "a#b", Character.valueOf('#'), new ItemStack(Items.field_151122_aG), Character.valueOf('a'), "dyeBlack", Character.valueOf('b'), new ItemStack(Items.field_151008_G), Character.valueOf('c'), new ItemStack(Blocks.field_150346_d) }));
    







    GameRegistry.addRecipe(new ShapedOreRecipe(ItemsGENERIC.itemBookHerbology.createStack(), new Object[] { " c ", "a#b", " d ", Character.valueOf('#'), new ItemStack(Items.field_151122_aG), Character.valueOf('a'), "dyeBlack", Character.valueOf('b'), new ItemStack(Items.field_151008_G), Character.valueOf('c'), new ItemStack(Blocks.field_150328_O), Character.valueOf('d'), new ItemStack(Blocks.field_150327_N) }));
    









    GameRegistry.addRecipe(new ShapedOreRecipe(ItemsGENERIC.itemBookWands.createStack(), new Object[] { " c ", "a#b", "   ", Character.valueOf('#'), new ItemStack(Items.field_151122_aG), Character.valueOf('a'), "dyeBlack", Character.valueOf('b'), new ItemStack(Items.field_151008_G), Character.valueOf('c'), ItemsGENERIC.itemBranchEnt.createStack() }));
    








    GameRegistry.addRecipe(new ShapedOreRecipe(ItemsGENERIC.itemBookBiomes.createStack(), new Object[] { " c ", "a#b", " d ", Character.valueOf('#'), new ItemStack(Items.field_151122_aG), Character.valueOf('a'), "dyeBlack", Character.valueOf('b'), new ItemStack(Items.field_151008_G), Character.valueOf('c'), new ItemStack(Blocks.field_150345_g), Character.valueOf('d'), new ItemStack(Blocks.field_150348_b) }));
    









    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemsBIOME_BOOK), new Object[] { " d ", "d#d", " d ", Character.valueOf('#'), ItemsGENERIC.itemBookBiomes.createStack(), Character.valueOf('d'), new ItemStack(Blocks.field_150348_b) }));
    






    GameRegistry.addRecipe(new ShapedOreRecipe(ItemsGENERIC.itemBookBurning.createStack(), new Object[] { " c ", "a#b", " d ", Character.valueOf('#'), new ItemStack(Items.field_151122_aG), Character.valueOf('a'), "dyeBlack", Character.valueOf('b'), new ItemStack(Items.field_151008_G), Character.valueOf('c'), ItemsGENERIC.itemAshWood.createStack(), Character.valueOf('d'), new ItemStack(Items.field_151033_d) }));
    









    GameRegistry.addRecipe(new ShapedOreRecipe(ItemsGENERIC.itemBookOven.createStack(), new Object[] { " c ", "a#b", " d ", Character.valueOf('#'), new ItemStack(Items.field_151122_aG), Character.valueOf('a'), "dyeBlack", Character.valueOf('b'), new ItemStack(Items.field_151008_G), Character.valueOf('c'), ItemsGENERIC.itemBelladonnaFlower.createStack(), Character.valueOf('d'), new ItemStack(Items.field_151044_h, 1, 1) }));
    









    GameRegistry.addRecipe(new ShapedOreRecipe(ItemsGENERIC.itemBookDistilling.createStack(), new Object[] { " c ", "a#b", " d ", Character.valueOf('#'), new ItemStack(Items.field_151122_aG), Character.valueOf('a'), "dyeBlack", Character.valueOf('b'), new ItemStack(Items.field_151008_G), Character.valueOf('c'), ItemsGENERIC.itemBelladonnaFlower.createStack(), Character.valueOf('d'), ItemsGENERIC.itemBreathOfTheGoddess.createStack() }));
    









    GameRegistry.addRecipe(new ShapedOreRecipe(ItemsGENERIC.itemBookCircleMagic.createStack(), new Object[] { " c ", "a#b", " d ", Character.valueOf('#'), new ItemStack(Items.field_151122_aG), Character.valueOf('a'), "dyeBlack", Character.valueOf('b'), new ItemStack(Items.field_151008_G), Character.valueOf('c'), ItemsGENERIC.itemBelladonnaFlower.createStack(), Character.valueOf('d'), ItemsGENERIC.itemWhiffOfMagic.createStack() }));
    









    GameRegistry.addRecipe(new ShapedOreRecipe(ItemsGENERIC.itemBookInfusions.createStack(), new Object[] { " c ", "a#b", " d ", Character.valueOf('#'), new ItemStack(Items.field_151122_aG), Character.valueOf('a'), "dyeBlack", Character.valueOf('b'), new ItemStack(Items.field_151008_G), Character.valueOf('c'), ItemsGENERIC.itemBelladonnaFlower.createStack(), Character.valueOf('d'), ItemsGENERIC.itemOdourOfPurity.createStack() }));
    









    GameRegistry.addRecipe(ItemsGENERIC.itemWeb.createStack(), new Object[] { " s ", "sws", " s ", Character.valueOf('s'), new ItemStack(Items.field_151007_F), Character.valueOf('w'), new ItemStack(Blocks.field_150321_G) });
    






    GameRegistry.addRecipe(new ItemStack(BlocksALLURING_SKULL), new Object[] { " a ", "bcb", " d ", Character.valueOf('a'), ItemsGENERIC.itemNecroStone.createStack(), Character.valueOf('d'), ItemsPOPPET.voodooPoppet.createStack(), Character.valueOf('c'), new ItemStack(Items.field_151144_bL), Character.valueOf('b'), new ItemStack(Items.field_151114_aO) });
    








    GameRegistry.addRecipe(new ItemStack(BlocksALLURING_SKULL), new Object[] { " a ", "bcb", " d ", Character.valueOf('a'), ItemsGENERIC.itemNecroStone.createStack(), Character.valueOf('d'), ItemsPOPPET.voodooPoppet.createStack(), Character.valueOf('c'), new ItemStack(Items.field_151144_bL, 1, 1), Character.valueOf('b'), new ItemStack(Items.field_151114_aO) });
    








    GameRegistry.addRecipe(ItemsGENERIC.itemSeedsTreefyd.createStack(2), new Object[] { "xax", "cyd", "xbx", Character.valueOf('x'), ItemsGENERIC.itemMutandisExtremis.createStack(), Character.valueOf('y'), ItemsGENERIC.itemArtichoke.createStack(), Character.valueOf('c'), new ItemStack(BlocksEMBER_MOSS), Character.valueOf('d'), ItemsGENERIC.itemMandrakeRoot.createStack(), Character.valueOf('a'), ItemsGENERIC.itemReekOfMisfortune.createStack(), Character.valueOf('b'), ItemsGENERIC.itemTearOfTheGoddess.createStack() });
    










    GameRegistry.addRecipe(new ItemStack(ItemsPOLYNESIA_CHARM, 1), new Object[] { "nin", "p#p", "nwn", Character.valueOf('#'), new ItemStack(Items.field_151115_aP), Character.valueOf('i'), new ItemStack(Items.field_151042_j), Character.valueOf('p'), ItemsGENERIC.itemOdourOfPurity.createStack(), Character.valueOf('w'), ItemsGENERIC.itemWhiffOfMagic.createStack(), Character.valueOf('n'), new ItemStack(Items.field_151075_bm) });
    









    GameRegistry.addRecipe(new ItemStack(ItemsDEVILS_TONGUE_CHARM, 1), new Object[] { "b#b", "dse", "btb", Character.valueOf('#'), new ItemStack(ItemsPOLYNESIA_CHARM), Character.valueOf('d'), ItemsGENERIC.itemDemonHeart.createStack(), Character.valueOf('t'), ItemsGENERIC.itemDogTongue.createStack(), Character.valueOf('e'), ItemsGENERIC.itemRefinedEvil.createStack(), Character.valueOf('s'), new ItemStack(Items.field_151144_bL), Character.valueOf('b'), new ItemStack(Items.field_151065_br) });
    










    GameRegistry.addRecipe(new ItemStack(BlocksOVEN_FUMEFUNNEL), new Object[] { "ele", "ege", "bib", Character.valueOf('e'), new ItemStack(Items.field_151133_ar), Character.valueOf('l'), new ItemStack(Items.field_151129_at), Character.valueOf('b'), new ItemStack(Blocks.field_150339_S), Character.valueOf('g'), new ItemStack(Blocks.field_150426_aN), Character.valueOf('i'), new ItemStack(Blocks.field_150411_aY) });
    









    GameRegistry.addRecipe(ItemsGENERIC.itemFumeFilter.createStack(), new Object[] { "ggg", "sas", "ggg", Character.valueOf('g'), new ItemStack(Blocks.field_150359_w), Character.valueOf('s'), new ItemStack(Items.field_151042_j), Character.valueOf('a'), ItemsGENERIC.itemAttunedStoneCharged.createStack() });
    







    GameRegistry.addRecipe(new ItemStack(BlocksOVEN_FUMEFUNNEL_FILTERED), new Object[] { "b", "f", Character.valueOf('b'), new ItemStack(BlocksOVEN_FUMEFUNNEL), Character.valueOf('f'), ItemsGENERIC.itemFumeFilter.createStack() });
    





    GameRegistry.addShapelessRecipe(ItemsGENERIC.itemPurifiedMilk.createStack(3), new Object[] { new ItemStack(Items.field_151117_aB), ItemsGENERIC.itemOdourOfPurity.createStack(), ItemsGENERIC.itemEmptyClayJar.createStack(), ItemsGENERIC.itemEmptyClayJar.createStack(), ItemsGENERIC.itemEmptyClayJar.createStack() });
    






    GameRegistry.addShapelessRecipe(ItemsGENERIC.itemPurifiedMilk.createStack(3), new Object[] { new ItemStack(Items.field_151105_aU), ItemsGENERIC.itemOdourOfPurity.createStack(), ItemsGENERIC.itemOdourOfPurity.createStack(), ItemsGENERIC.itemEmptyClayJar.createStack(), ItemsGENERIC.itemEmptyClayJar.createStack(), ItemsGENERIC.itemEmptyClayJar.createStack() });
    








    GameRegistry.addRecipe(ItemsGENERIC.itemImpregnatedLeather.createStack(4), new Object[] { "mlm", "ldl", "mlm", Character.valueOf('l'), new ItemStack(Items.field_151116_aA), Character.valueOf('d'), ItemsGENERIC.itemDiamondVapour.createStack(), Character.valueOf('m'), ItemsGENERIC.itemWhiffOfMagic.createStack() });
    







    GameRegistry.addRecipe(new ItemStack(ItemsWITCH_HAT), new Object[] { " l ", "sls", "lgl", Character.valueOf('s'), ItemsGENERIC.itemGoldenThread.createStack(), Character.valueOf('l'), ItemsGENERIC.itemImpregnatedLeather.createStack(), Character.valueOf('g'), new ItemStack(Items.field_151114_aO) });
    







    if (instanceallowVoidBrambleRecipe) {
      GameRegistry.addRecipe(new ItemStack(BlocksVOID_BRAMBLE, 4), new Object[] { "lml", "r#r", "lml", Character.valueOf('#'), new ItemStack(BlocksBRAMBLE), Character.valueOf('r'), new ItemStack(Items.field_151156_bN), Character.valueOf('l'), ItemsGENERIC.itemDropOfLuck.createStack(), Character.valueOf('m'), ItemsGENERIC.itemMutandisExtremis.createStack() });
    }
    









    GameRegistry.addRecipe(new ItemStack(Items.field_151016_H, 5), new Object[] { "#", Character.valueOf('#'), ItemsGENERIC.itemCreeperHeart.createStack() });
    



    GameRegistry.addShapelessRecipe(new ItemStack(Blocks.field_150424_aL), new Object[] { ItemsGENERIC.itemInfernalBlood.createStack(), new ItemStack(Blocks.field_150351_n) });
    
    ItemStack impregLeather = ItemsGENERIC.itemImpregnatedLeather.createStack();
    
    GameRegistry.addRecipe(new ItemStack(ItemsWITCH_ROBES), new Object[] { "lsl", "l#l", "lll", Character.valueOf('s'), ItemsGENERIC.itemGoldenThread.createStack(), Character.valueOf('l'), impregLeather, Character.valueOf('#'), ItemsGENERIC.itemCreeperHeart.createStack() });
    







    GameRegistry.addRecipe(new ItemStack(ItemsNECROMANCERS_ROBES), new Object[] { "lsl", "l#l", "lll", Character.valueOf('s'), ItemsGENERIC.itemGoldenThread.createStack(), Character.valueOf('l'), impregLeather, Character.valueOf('#'), ItemsGENERIC.itemNecroStone.createStack() });
    







    GameRegistry.addRecipe(ItemsGENERIC.itemFrozenHeart.createStack(), new Object[] { "n", "h", "t", Character.valueOf('h'), ItemsGENERIC.itemCreeperHeart.createStack(), Character.valueOf('n'), ItemsGENERIC.itemIcyNeedle.createStack(), Character.valueOf('t'), new ItemStack(Items.field_151073_bk) });
    







    GameRegistry.addRecipe(new ItemStack(ItemsICY_SLIPPERS), new Object[] { "lsl", "l#l", "dod", Character.valueOf('l'), impregLeather, Character.valueOf('s'), ItemsGENERIC.itemGoldenThread.createStack(), Character.valueOf('#'), ItemsGENERIC.itemFrozenHeart.createStack(), Character.valueOf('d'), ItemsGENERIC.itemDiamondVapour.createStack(), Character.valueOf('o'), ItemsGENERIC.itemOdourOfPurity.createStack() });
    









    GameRegistry.addRecipe(new ItemStack(ItemsBITING_BELT), new Object[] { "#lh", "lsl", "l l", Character.valueOf('l'), impregLeather, Character.valueOf('s'), ItemsGENERIC.itemGoldenThread.createStack(), Character.valueOf('h'), ItemsGENERIC.itemMellifluousHunger.createStack(), Character.valueOf('#'), new ItemStack(ItemsPARASYTIC_LOUSE) });
    








    GameRegistry.addRecipe(new ItemStack(ItemsSEEPING_SHOES), new Object[] { "lsl", "hrh", "mmm", Character.valueOf('l'), impregLeather, Character.valueOf('s'), ItemsGENERIC.itemGoldenThread.createStack(), Character.valueOf('h'), new ItemStack(ItemsWITCH_HAND), Character.valueOf('r'), ItemsGENERIC.itemRedstoneSoup.createStack(), Character.valueOf('m'), new ItemStack(Items.field_151117_aB) });
    









    GameRegistry.addRecipe(new ItemStack(ItemsRUBY_SLIPPERS), new Object[] { "aba", "tst", "aba", Character.valueOf('s'), new ItemStack(ItemsSEEPING_SHOES), Character.valueOf('t'), ItemsGENERIC.itemGoldenThread.createStack(), Character.valueOf('a'), ItemsGENERIC.itemAttunedStone.createStack(), Character.valueOf('b'), ItemsGENERIC.itemInfernalBlood.createStack() });
    








    GameRegistry.addRecipe(new ItemStack(ItemsBARK_BELT), new Object[] { "ses", "gbg", "shs", Character.valueOf('b'), new ItemStack(ItemsBITING_BELT), Character.valueOf('s'), ItemsGENERIC.itemBrewOfFlowingSpirit.createStack(), Character.valueOf('g'), ItemsGENERIC.itemBranchEnt.createStack(), Character.valueOf('h'), ItemsGENERIC.itemCreeperHeart.createStack(), Character.valueOf('e'), new ItemStack(Items.field_151166_bC) });
    









    GameRegistry.addShapelessRecipe(ItemsGENERIC.itemWormyApple.createStack(), new Object[] { new ItemStack(Items.field_151034_e), new ItemStack(Items.field_151078_bh), new ItemStack(Items.field_151102_aT) });
    
    ItemStack louse = new ItemStack(ItemsPARASYTIC_LOUSE, 1, 32767);
    ItemStack belt = new ItemStack(ItemsBITING_BELT, 1, 32767);
    int[] lousePotions = { 8200, 8202, 8264, 8266, 8193, 8194, 8196, 8225, 8226, 8227, 8228, 8229, 8230, 8232, 8233, 8234, 8236, 8238, 8257, 8258, 8259, 8260, 8261, 8262, 8264, 8265, 8266, 8268, 8270, 8201, 8206 };
    for (int dv : lousePotions) {
      GameRegistry.addShapelessRecipe(new ItemStack(ItemsPARASYTIC_LOUSE, 1, dv), new Object[] { louse, new ItemStack(Items.field_151068_bn, 1, dv) });
      CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessAddPotion(new ItemStack(ItemsBITING_BELT, 1, dv), new ItemStack[] { belt, new ItemStack(Items.field_151068_bn, 1, dv) }));
    }
    
    CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessRepair(new ItemStack(ItemsWITCH_ROBES), new ItemStack[] { new ItemStack(ItemsWITCH_ROBES), impregLeather, impregLeather, impregLeather, impregLeather }));
    CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessRepair(new ItemStack(ItemsNECROMANCERS_ROBES), new ItemStack[] { new ItemStack(ItemsNECROMANCERS_ROBES), impregLeather, impregLeather, impregLeather, impregLeather }));
    CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessRepair(new ItemStack(ItemsWITCH_HAT), new ItemStack[] { new ItemStack(ItemsWITCH_HAT), impregLeather, impregLeather, impregLeather }));
    CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessRepair(new ItemStack(ItemsICY_SLIPPERS), new ItemStack[] { new ItemStack(ItemsICY_SLIPPERS), impregLeather, impregLeather, impregLeather }));
    CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessRepair(new ItemStack(ItemsRUBY_SLIPPERS), new ItemStack[] { new ItemStack(ItemsRUBY_SLIPPERS), impregLeather, impregLeather, impregLeather }));
    CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessRepair(new ItemStack(ItemsSEEPING_SHOES), new ItemStack[] { new ItemStack(ItemsSEEPING_SHOES), impregLeather, impregLeather, impregLeather }));
    CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessRepair(new ItemStack(ItemsBITING_BELT), new ItemStack[] { new ItemStack(ItemsBITING_BELT), impregLeather, impregLeather, impregLeather, impregLeather }));
    CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessRepair(new ItemStack(ItemsBARK_BELT), new ItemStack[] { new ItemStack(ItemsBARK_BELT), impregLeather, impregLeather, impregLeather, impregLeather }));
    CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessRepair(new ItemStack(ItemsBABAS_HAT), new ItemStack[] { new ItemStack(ItemsBABAS_HAT), impregLeather, impregLeather, impregLeather }));
    
    for (Dye dye : Dye.DYES) {
      CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessAddColor(new ItemStack(ItemsBREW_BAG), new ItemStack[] { new ItemStack(ItemsBREW_BAG), dye.createStack() }));
    }
    
    GameRegistry.addRecipe(new ItemStack(ItemsBREW_BAG), new Object[] { "lll", "lsl", "lll", Character.valueOf('l'), impregLeather, Character.valueOf('s'), ItemsGENERIC.itemGoldenThread.createStack() });
    






    GameRegistry.addRecipe(ItemsGENERIC.itemCharmOfDisruptedDreams.createStack(), new Object[] { "lll", "lsl", "lll", Character.valueOf('l'), new ItemStack(Items.field_151055_y), Character.valueOf('s'), ItemsGENERIC.itemFancifulThread.createStack() });
    






    CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessAddKeys(ItemsGENERIC.itemDoorKeyring.createStack(), new ItemStack[] { ItemsGENERIC.itemDoorKey.createStack(), ItemsGENERIC.itemDoorKey.createStack() }));
    



    CraftingManager.func_77594_a().func_77592_b().add(new RecipeShapelessAddKeys(ItemsGENERIC.itemDoorKeyring.createStack(), new ItemStack[] { ItemsGENERIC.itemDoorKeyring.createStack(), ItemsGENERIC.itemDoorKey.createStack() }));
    



    GameRegistry.addRecipe(ItemsGENERIC.itemQuartzSphere.createStack(), new Object[] { "qbq", "bgb", "qbq", Character.valueOf('q'), new ItemStack(Items.field_151128_bU), Character.valueOf('b'), new ItemStack(Blocks.field_150371_ca), Character.valueOf('g'), new ItemStack(Blocks.field_150359_w) });
    







    GameRegistry.addRecipe(ItemsGENERIC.itemSleepingApple.createStack(), new Object[] { " g ", "mam", "gsg", Character.valueOf('a'), ItemsGENERIC.itemWormyApple.createStack(), Character.valueOf('g'), ItemsGENERIC.itemMutandis.createStack(), Character.valueOf('m'), ItemsGENERIC.itemReekOfMisfortune.createStack(), Character.valueOf('s'), ItemsGENERIC.itemBrewOfSleeping.createStack() });
    







    GameRegistry.addRecipe(ItemsGENERIC.itemBatBall.createStack(), new Object[] { "sbs", "b b", "sbs", Character.valueOf('s'), new ItemStack(Items.field_151123_aH), Character.valueOf('b'), new ItemStack(BlocksCRITTER_SNARE, 1, 1) });
    





    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlocksSPINNING_WHEEL), new Object[] { "aab", "aac", "wsw", Character.valueOf('a'), new ItemStack(Items.field_151160_bD), Character.valueOf('b'), new ItemStack(Blocks.field_150325_L), Character.valueOf('c'), "stickWood", Character.valueOf('w'), "plankWood", Character.valueOf('s'), ItemsGENERIC.itemAttunedStone.createStack() }));
    








    GameRegistry.addShapelessRecipe(ItemsGENERIC.itemGraveyardDust.createStack(), new Object[] { ItemsGENERIC.itemSpectralDust.createStack(), Dye.BONE_MEAL.createStack(), ItemsGENERIC.itemMutandis.createStack() });
    



    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlocksFETISH_SCARECROW), new Object[] { "w#w", "sls", "wsw", Character.valueOf('#'), new ItemStack(Blocks.field_150428_aP), Character.valueOf('w'), new ItemStack(Blocks.field_150325_L), Character.valueOf('s'), "stickWood", Character.valueOf('l'), ItemsGENERIC.itemTormentedTwine.createStack() }));
    







    GameRegistry.addShapedRecipe(new ItemStack(BlocksFETISH_WITCHS_LADDER), new Object[] { "fsf", "ftf", "fsf", Character.valueOf('f'), new ItemStack(Items.field_151008_G), Character.valueOf('s'), new ItemStack(Items.field_151007_F), Character.valueOf('t'), ItemsGENERIC.itemFancifulThread.createStack() });
    






    GameRegistry.addShapedRecipe(new ItemStack(BlocksFETISH_TREANT_IDOL), new Object[] { "o#o", "srs", "o o", Character.valueOf('#'), new ItemStack(Blocks.field_150428_aP), Character.valueOf('o'), new ItemStack(Blocks.field_150364_r, 1, 0), Character.valueOf('r'), new ItemStack(BlocksLOG, 1, 0), Character.valueOf('s'), ItemsGENERIC.itemTormentedTwine.createStack() });
    







    SpinningRecipes.instance().addRecipe(ItemsGENERIC.itemFancifulThread.createStack(), new ItemStack(BlocksWISPY_COTTON, 4), new ItemStack[] { new ItemStack(Items.field_151007_F), ItemsGENERIC.itemOdourOfPurity.createStack() });
    SpinningRecipes.instance().addRecipe(ItemsGENERIC.itemTormentedTwine.createStack(), ItemsGENERIC.itemDisturbedCotton.createStack(4), new ItemStack[] { new ItemStack(Items.field_151007_F), ItemsGENERIC.itemReekOfMisfortune.createStack() });
    SpinningRecipes.instance().addRecipe(new ItemStack(Blocks.field_150321_G), new ItemStack(Items.field_151007_F, 8), new ItemStack[0]);
    SpinningRecipes.instance().addRecipe(ItemsGENERIC.itemGoldenThread.createStack(3), new ItemStack(Blocks.field_150407_cf), new ItemStack[] { ItemsGENERIC.itemWhiffOfMagic.createStack() });
    

    GameRegistry.addShapelessRecipe(ItemsGENERIC.itemNullCatalyst.createStack(2), new Object[] { new ItemStack(Items.field_151156_bN), new ItemStack(Items.field_151045_i), new ItemStack(Items.field_151145_ak), new ItemStack(Items.field_151064_bs), new ItemStack(Items.field_151064_bs), new ItemStack(Items.field_151064_bs), new ItemStack(Items.field_151064_bs), new ItemStack(Items.field_151064_bs), new ItemStack(Items.field_151064_bs) });
    





    GameRegistry.addShapelessRecipe(ItemsGENERIC.itemNullCatalyst.createStack(2), new Object[] { ItemsGENERIC.itemNullCatalyst.createStack(), new ItemStack(Items.field_151064_bs), new ItemStack(Items.field_151065_br) });
    


    GameRegistry.addShapedRecipe(ItemsGENERIC.itemNullifiedLeather.createStack(3), new Object[] { "lll", "lcl", "lll", Character.valueOf('l'), new ItemStack(Items.field_151116_aA), Character.valueOf('c'), ItemsGENERIC.itemNullCatalyst.createStack() });
    





    GameRegistry.addShapedRecipe(new ItemStack(ItemsHUNTER_HAT), new Object[] { "lll", "l l", Character.valueOf('l'), ItemsGENERIC.itemNullifiedLeather.createStack() });
    



    GameRegistry.addShapedRecipe(new ItemStack(ItemsHUNTER_COAT), new Object[] { "l l", "lll", "lll", Character.valueOf('l'), ItemsGENERIC.itemNullifiedLeather.createStack() });
    




    GameRegistry.addShapedRecipe(new ItemStack(ItemsHUNTER_LEGS), new Object[] { "lll", "l l", "l l", Character.valueOf('l'), ItemsGENERIC.itemNullifiedLeather.createStack() });
    




    GameRegistry.addShapedRecipe(new ItemStack(ItemsHUNTER_BOOTS), new Object[] { "l l", "l l", Character.valueOf('l'), ItemsGENERIC.itemNullifiedLeather.createStack() });
    



    GameRegistry.addShapedRecipe(new ItemStack(ItemsSHELF_COMPASS), new Object[] { "gdg", "d#d", "gcg", Character.valueOf('g'), new ItemStack(Items.field_151043_k), Character.valueOf('d'), new ItemStack(Items.field_151045_i), Character.valueOf('#'), new ItemStack(Items.field_151113_aN), Character.valueOf('c'), ItemsGENERIC.itemNullCatalyst.createStack() });
    







    GameRegistry.addRecipe(new ShapedOreRecipe(ItemsGENERIC.itemBoltStake.createStack(9), new Object[] { " s ", "www", "fff", Character.valueOf('f'), new ItemStack(Items.field_151008_G), Character.valueOf('s'), new ItemStack(Items.field_151007_F), Character.valueOf('w'), "stickWood" }));
    






    GameRegistry.addShapedRecipe(ItemsGENERIC.itemBoltSplitting.createStack(), new Object[] { " s ", "bbb", " f ", Character.valueOf('f'), new ItemStack(Items.field_151008_G), Character.valueOf('s'), new ItemStack(Items.field_151007_F), Character.valueOf('b'), ItemsGENERIC.itemBoltStake.createStack() });
    






    GameRegistry.addShapedRecipe(ItemsGENERIC.itemBoltHoly.createStack(12), new Object[] { "aba", "ata", "aba", Character.valueOf('t'), new ItemStack(Items.field_151073_bk), Character.valueOf('a'), ItemsGENERIC.itemBoltStake.createStack(), Character.valueOf('b'), new ItemStack(Items.field_151103_aS) });
    






    GameRegistry.addShapelessRecipe(ItemsGENERIC.itemBoltAntiMagic.createStack(3), new Object[] { ItemsGENERIC.itemNullCatalyst.createStack(), ItemsGENERIC.itemBelladonnaFlower.createStack(), ItemsGENERIC.itemBelladonnaFlower.createStack(), ItemsGENERIC.itemBoltHoly.createStack(), ItemsGENERIC.itemBoltHoly.createStack(), ItemsGENERIC.itemBoltHoly.createStack() });
    






    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemsCROSSBOW_PISTOL), new Object[] { "mbm", "swn", " m ", Character.valueOf('m'), new ItemStack(Items.field_151042_j), Character.valueOf('b'), new ItemStack(Items.field_151031_f), Character.valueOf('n'), ItemsGENERIC.itemBoneNeedle.createStack(), Character.valueOf('s'), new ItemStack(Items.field_151007_F), Character.valueOf('w'), "stickWood" }));
    








    GameRegistry.addShapelessRecipe(ItemsPOTIONS.potionAntidote.createStack(2), new Object[] { ItemsGENERIC.itemNullCatalyst.createStack(), new ItemStack(Items.field_151068_bn, 1, 8196), new ItemStack(Items.field_151068_bn, 1, 8196) });
    





    GameRegistry.addShapedRecipe(ItemsGENERIC.itemContractOwnership.createStack(), new Object[] { "ppp", "pfp", "pps", Character.valueOf('f'), ItemsGENERIC.itemOddPorkRaw.createStack(), Character.valueOf('p'), new ItemStack(Items.field_151121_aF), Character.valueOf('s'), new ItemStack(Items.field_151007_F) });
    






    GameRegistry.addRecipe(new RecipeAttachTaglock(ItemsGENERIC.itemContractOwnership.createStack(), new ItemStack[] { ItemsGENERIC.itemContractOwnership.createStack(), new ItemStack(ItemsTAGLOCK_KIT, 1, 1) }));
    


    GameRegistry.addShapelessRecipe(ItemsGENERIC.itemContractBlaze.createStack(), new Object[] { ItemsGENERIC.itemContractOwnership.createStack(), new ItemStack(Items.field_151072_bj), ItemsGENERIC.itemHintOfRebirth.createStack() });
    


    GameRegistry.addRecipe(new RecipeAttachTaglock(ItemsGENERIC.itemContractBlaze.createStack(), new ItemStack[] { ItemsGENERIC.itemContractBlaze.createStack(), new ItemStack(ItemsTAGLOCK_KIT, 1, 1) }));
    


    GameRegistry.addShapelessRecipe(ItemsGENERIC.itemContractResistFire.createStack(), new Object[] { ItemsGENERIC.itemContractOwnership.createStack(), new ItemStack(Items.field_151065_br) });
    


    GameRegistry.addRecipe(new RecipeAttachTaglock(ItemsGENERIC.itemContractResistFire.createStack(), new ItemStack[] { ItemsGENERIC.itemContractResistFire.createStack(), new ItemStack(ItemsTAGLOCK_KIT, 1, 1) }));
    


    GameRegistry.addShapelessRecipe(ItemsGENERIC.itemContractEvaporate.createStack(), new Object[] { ItemsGENERIC.itemContractOwnership.createStack(), new ItemStack(Items.field_151064_bs), new ItemStack(Items.field_151072_bj) });
    



    GameRegistry.addRecipe(new RecipeAttachTaglock(ItemsGENERIC.itemContractEvaporate.createStack(), new ItemStack[] { ItemsGENERIC.itemContractEvaporate.createStack(), new ItemStack(ItemsTAGLOCK_KIT, 1, 1) }));
    


    GameRegistry.addShapelessRecipe(ItemsGENERIC.itemContractFieryTouch.createStack(), new Object[] { ItemsGENERIC.itemContractOwnership.createStack(), new ItemStack(BlocksEMBER_MOSS), new ItemStack(Items.field_151072_bj) });
    



    GameRegistry.addRecipe(new RecipeAttachTaglock(ItemsGENERIC.itemContractFieryTouch.createStack(), new ItemStack[] { ItemsGENERIC.itemContractFieryTouch.createStack(), new ItemStack(ItemsTAGLOCK_KIT, 1, 1) }));
    


    GameRegistry.addShapelessRecipe(ItemsGENERIC.itemContractSmelting.createStack(), new Object[] { ItemsGENERIC.itemContractOwnership.createStack(), new ItemStack(Items.field_151129_at) });
    


    GameRegistry.addRecipe(new RecipeAttachTaglock(ItemsGENERIC.itemContractSmelting.createStack(), new ItemStack[] { ItemsGENERIC.itemContractSmelting.createStack(), new ItemStack(ItemsTAGLOCK_KIT, 1, 1) }));
    


    GameRegistry.addShapelessRecipe(new ItemStack(ItemsLEONARDS_URN, 1, 1), new Object[] { new ItemStack(ItemsLEONARDS_URN, 1, 0), new ItemStack(ItemsLEONARDS_URN, 1, 0) });
    

    GameRegistry.addShapelessRecipe(new ItemStack(ItemsLEONARDS_URN, 1, 2), new Object[] { new ItemStack(ItemsLEONARDS_URN, 1, 1), new ItemStack(ItemsLEONARDS_URN, 1, 0) });
    

    GameRegistry.addShapelessRecipe(new ItemStack(ItemsLEONARDS_URN, 1, 3), new Object[] { new ItemStack(ItemsLEONARDS_URN, 1, 2), new ItemStack(ItemsLEONARDS_URN, 1, 0) });
    


    GameRegistry.addRecipe(new RecipeAttachTaglock(new ItemStack(ItemsPLAYER_COMPASS), new ItemStack[] { new ItemStack(ItemsPLAYER_COMPASS, 1, 32767), new ItemStack(ItemsTAGLOCK_KIT, 1, 1) }));
    





    ItemStack[] logs = { new ItemStack(Blocks.field_150364_r, 1, 0), new ItemStack(Blocks.field_150364_r, 1, 1), new ItemStack(Blocks.field_150364_r, 1, 2), new ItemStack(Blocks.field_150364_r, 1, 3), new ItemStack(BlocksLOG, 1, 0), new ItemStack(BlocksLOG, 1, 1), new ItemStack(BlocksLOG, 1, 2), new ItemStack(Blocks.field_150363_s, 1, 0), new ItemStack(Blocks.field_150363_s, 1, 1) };
    









    for (int i = 0; i < logs.length; i++) {
      GameRegistry.addShapedRecipe(new ItemStack(BlocksSTOCKADE, 9, i), new Object[] { " w ", "wfw", "www", Character.valueOf('f'), ItemsGENERIC.itemExhaleOfTheHornedOne.createStack(), Character.valueOf('w'), logs[i] });
    }
    





    ItemStack kobolditeIngot = ItemsGENERIC.itemKobolditeIngot.createStack();
    
    GameRegistry.addShapedRecipe(new ItemStack(ItemsKOBOLDITE_PICKAXE), new Object[] { "bab", "iii", " s ", Character.valueOf('i'), kobolditeIngot, Character.valueOf('a'), ItemsGENERIC.itemAttunedStoneCharged.createStack(), Character.valueOf('b'), new ItemStack(Items.field_151129_at), Character.valueOf('s'), new ItemStack(Items.field_151055_y) });
    







    GameRegistry.addShapedRecipe(new ItemStack(BlocksSTATUE_OF_WORSHIP), new Object[] { "sks", " s ", "s s", Character.valueOf('k'), kobolditeIngot, Character.valueOf('s'), new ItemStack(Blocks.field_150348_b) });
    





    GameRegistry.addShapedRecipe(ItemsGENERIC.itemKobolditePentacle.createStack(), new Object[] { "sks", "kdk", "sks", Character.valueOf('k'), kobolditeIngot, Character.valueOf('s'), ItemsGENERIC.itemKobolditeNugget.createStack(), Character.valueOf('d'), new ItemStack(Items.field_151045_i) });
    






    GameRegistry.addShapedRecipe(new ItemStack(ItemsKOBOLDITE_HELM), new Object[] { "iii", "iai", Character.valueOf('i'), kobolditeIngot, Character.valueOf('a'), ItemsGENERIC.itemAttunedStoneCharged.createStack() });
    




    GameRegistry.addShapedRecipe(new ItemStack(ItemsEARMUFFS), new Object[] { "iii", "i i", "w w", Character.valueOf('i'), new ItemStack(Items.field_151116_aA), Character.valueOf('w'), new ItemStack(Blocks.field_150325_L) });
    







    GameRegistry.addRecipe(new RecipeShapelessBiomeCopy(new ItemStack(ItemsBIOME_NOTE), new ItemStack[] { new ItemStack(ItemsBIOME_BOOK.func_77642_a(ItemsBIOME_BOOK)), new ItemStack(Items.field_151121_aF) }));
    


    GameRegistry.addShapelessRecipe(ItemsGENERIC.itemAnnointingPaste.createStack(), new Object[] { new ItemStack(ItemsSEEDS_ARTICHOKE), new ItemStack(ItemsSEEDS_MANDRAKE), new ItemStack(ItemsSEEDS_BELLADONNA), new ItemStack(ItemsSEEDS_SNOWBELL) });
    




    GameRegistry.addShapedRecipe(new ItemStack(ItemsSILVER_SWORD), new Object[] { "ddd", "dsd", "ddd", Character.valueOf('s'), new ItemStack(Items.field_151010_B), Character.valueOf('d'), ItemsGENERIC.itemSilverDust.createStack() });
    





    Item[][] hunterItems = { { ItemsHUNTER_BOOTS, ItemsHUNTER_BOOTS_SILVERED }, { ItemsHUNTER_LEGS, ItemsHUNTER_LEGS_SILVERED }, { ItemsHUNTER_COAT, ItemsHUNTER_COAT_SILVERED }, { ItemsHUNTER_HAT, ItemsHUNTER_HAT_SILVERED } };
    




    for (int i = 0; i < hunterItems.length; i++) {
      CraftingManager.func_77594_a().func_92103_a(new ItemStack(hunterItems[i][1]), new Object[] { "dwd", "w#w", "dsd", Character.valueOf('#'), new ItemStack(hunterItems[i][0]), Character.valueOf('s'), new ItemStack(Items.field_151007_F), Character.valueOf('w'), ItemsGENERIC.itemWolfsbane.createStack(), Character.valueOf('d'), ItemsGENERIC.itemSilverDust.createStack() }).func_92100_c();
    }
    







    GameRegistry.addShapedRecipe(ItemsGENERIC.itemBoltSilver.createStack(3), new Object[] { " s ", "bbb", Character.valueOf('b'), ItemsGENERIC.itemBoltStake.createStack(), Character.valueOf('s'), ItemsGENERIC.itemSilverDust.createStack() });
    




    GameRegistry.addShapedRecipe(new ItemStack(BlocksWOLF_ALTAR), new Object[] { " w ", "w#w", "#d#", Character.valueOf('w'), new ItemStack(BlocksWOLFHEAD, 1, 32767), Character.valueOf('#'), new ItemStack(Blocks.field_150348_b), Character.valueOf('d'), ItemsGENERIC.itemWolfsbane.createStack() });
    






    GameRegistry.addShapedRecipe(new ItemStack(BlocksSILVER_VAT), new Object[] { "ibi", "ifi", Character.valueOf('i'), new ItemStack(Items.field_151042_j), Character.valueOf('b'), new ItemStack(Items.field_151131_as), Character.valueOf('f'), new ItemStack(Blocks.field_150460_al) });
    





    GameRegistry.addShapedRecipe(new ItemStack(BlocksBEARTRAP), new Object[] { "iii", "bpb", "iii", Character.valueOf('p'), new ItemStack(Blocks.field_150443_bT), Character.valueOf('i'), new ItemStack(Items.field_151042_j), Character.valueOf('b'), new ItemStack(Items.field_151097_aZ) });
    






    GameRegistry.addShapedRecipe(new ItemStack(BlocksWOLFTRAP), new Object[] { "sns", "w#w", "sns", Character.valueOf('#'), new ItemStack(BlocksBEARTRAP), Character.valueOf('s'), ItemsGENERIC.itemSilverDust.createStack(), Character.valueOf('n'), ItemsGENERIC.itemNullCatalyst.createStack(), Character.valueOf('w'), ItemsGENERIC.itemWolfsbane.createStack() });
    







    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlocksGARLIC_GARLAND), new Object[] { "s s", "GsG", "GGG", Character.valueOf('G'), "cropGarlic", Character.valueOf('s'), new ItemStack(Items.field_151007_F) }));
    






    ItemStack[] meats = { new ItemStack(Items.field_151082_bd), new ItemStack(Items.field_151076_bf), new ItemStack(Items.field_151147_al), new ItemStack(Items.field_151115_aP), new ItemStack(Items.field_151115_aP, 1), ItemsGENERIC.itemMuttonRaw.createStack() };
    






    for (ItemStack meat : meats) {
      GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemsSTEW_RAW), new Object[] { "cropGarlic", meat, new ItemStack(Items.field_151174_bG), new ItemStack(Items.field_151172_bF), new ItemStack(Items.field_151054_z), new ItemStack(Blocks.field_150338_P) }));
    }
    







    Item[][] hunterItemsSilvered = { { ItemsHUNTER_BOOTS_SILVERED, ItemsHUNTER_BOOTS_GARLICKED }, { ItemsHUNTER_LEGS_SILVERED, ItemsHUNTER_LEGS_GARLICKED }, { ItemsHUNTER_COAT_SILVERED, ItemsHUNTER_COAT_GARLICKED }, { ItemsHUNTER_HAT_SILVERED, ItemsHUNTER_HAT_GARLICKED } };
    




    for (int i = 0; i < hunterItemsSilvered.length; i++) {
      CraftingManager.func_77594_a().func_92103_a(new ItemStack(hunterItemsSilvered[i][1]), new Object[] { " g ", "g#g", " s ", Character.valueOf('#'), new ItemStack(hunterItemsSilvered[i][0]), Character.valueOf('s'), new ItemStack(Items.field_151007_F), Character.valueOf('g'), new ItemStack(ItemsSEEDS_GARLIC) }).func_92100_c();
    }
    







    for (int i = 0; i < 9; i++) {
      GameRegistry.addShapelessRecipe(new ItemStack(ItemsVAMPIRE_BOOK, 1, i + 1), new Object[] { new ItemStack(ItemsVAMPIRE_BOOK, 1, i), ItemsGENERIC.itemVampireBookPage.createStack() });
    }
    



    GameRegistry.addShapedRecipe(new ItemStack(ItemsBLOOD_GOBLET), new Object[] { "b b", " b ", " g ", Character.valueOf('g'), new ItemStack(Blocks.field_150359_w), Character.valueOf('b'), new ItemStack(Items.field_151069_bo) });
    





    GameRegistry.addShapedRecipe(new ItemStack(BlocksBLOOD_CRUCIBLE), new Object[] { "s s", "blb", Character.valueOf('s'), new ItemStack(Blocks.field_150390_bg), Character.valueOf('b'), new ItemStack(Blocks.field_150417_aV), Character.valueOf('l'), new ItemStack(Blocks.field_150333_U, 1, 5) });
    





    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemsCOFFIN), new Object[] { "ppp", "lbl", "lll", Character.valueOf('b'), new ItemStack(Items.field_151104_aV), Character.valueOf('p'), "plankWood", Character.valueOf('l'), "logWood" }));
    







    GameRegistry.addShapedRecipe(new ItemStack(BlocksDAYLIGHT_COLLECTOR), new Object[] { "g g", " r ", "ici", Character.valueOf('g'), new ItemStack(Items.field_151043_k), Character.valueOf('r'), new ItemStack(Items.field_151107_aW), Character.valueOf('i'), new ItemStack(Items.field_151042_j), Character.valueOf('c'), new ItemStack(Blocks.field_150453_bW) });
    







    GameRegistry.addShapedRecipe(new ItemStack(ItemsVAMPIRE_HELMET), new Object[] { " i ", "i#i", " i ", Character.valueOf('i'), new ItemStack(Items.field_151042_j), Character.valueOf('#'), new ItemStack(ItemsVAMPIRE_HAT) });
    





    GameRegistry.addShapedRecipe(new ItemStack(ItemsVAMPIRE_COAT_CHAIN), new Object[] { " i ", "i#i", " i ", Character.valueOf('i'), new ItemStack(Items.field_151042_j), Character.valueOf('#'), new ItemStack(ItemsVAMPIRE_COAT) });
    





    GameRegistry.addShapedRecipe(new ItemStack(ItemsVAMPIRE_COAT_FEMALE_CHAIN), new Object[] { " i ", "i#i", " i ", Character.valueOf('i'), new ItemStack(Items.field_151042_j), Character.valueOf('#'), new ItemStack(ItemsVAMPIRE_COAT_FEMALE) });
    





    ItemStack cloth = ItemsGENERIC.itemDarkCloth.createStack();
    GameRegistry.addShapedRecipe(new ItemStack(ItemsVAMPIRE_HAT), new Object[] { "###", "# #", Character.valueOf('#'), cloth });
    



    GameRegistry.addShapedRecipe(new ItemStack(ItemsVAMPIRE_COAT), new Object[] { "# #", "###", "###", Character.valueOf('#'), cloth });
    




    GameRegistry.addShapedRecipe(new ItemStack(ItemsVAMPIRE_COAT_FEMALE), new Object[] { "# #", "#l#", "###", Character.valueOf('l'), new ItemStack(Items.field_151116_aA), Character.valueOf('#'), cloth });
    





    GameRegistry.addShapedRecipe(new ItemStack(ItemsVAMPIRE_LEGS), new Object[] { "###", "# #", "# #", Character.valueOf('#'), cloth });
    




    GameRegistry.addShapedRecipe(new ItemStack(ItemsVAMPIRE_LEGS_KILT), new Object[] { "###", "###", "# #", Character.valueOf('#'), cloth });
    




    GameRegistry.addShapedRecipe(new ItemStack(ItemsVAMPIRE_BOOTS), new Object[] { "# #", "# #", Character.valueOf('#'), cloth });
    



    GameRegistry.addShapedRecipe(new ItemStack(ItemsCANE_SWORD), new Object[] { " #g", "#d#", "## ", Character.valueOf('g'), new ItemStack(Items.field_151043_k), Character.valueOf('d'), new ItemStack(Items.field_151048_u), Character.valueOf('#'), cloth });
    






    GameRegistry.addShapedRecipe(new ItemStack(ItemsVAMPIRE_BOOK), new Object[] { "#s#", "#b#", "#g#", Character.valueOf('s'), new ItemStack(Items.field_151156_bN), Character.valueOf('b'), new ItemStack(Items.field_151122_aG), Character.valueOf('g'), new ItemStack(ItemsSEEDS_GARLIC), Character.valueOf('#'), new ItemStack(Items.field_151075_bm) });
    







    for (int i = 0; i < 16; i++) {
      GameRegistry.addShapedRecipe(new ItemStack(BlocksSHADED_GLASS, 8, i), new Object[] { "###", "#r#", "###", Character.valueOf('r'), new ItemStack(Items.field_151137_ax), Character.valueOf('#'), new ItemStack(Blocks.field_150399_cn, 1, i) });
    }
    






    GameRegistry.addRecipe(new ShapedOreRecipe(ItemsGENERIC.itemWoodenStake.createStack(), new Object[] { "GGG", "GsG", "GGG", Character.valueOf('G'), "cropGarlic", Character.valueOf('s'), new ItemStack(Items.field_151055_y) }));
    











    OreDictionary.registerOre("plankWood", new ItemStack(BlocksPLANKS, 1, 32767));
    OreDictionary.registerOre("treeSapling", new ItemStack(BlocksSAPLING, 1, 32767));
    OreDictionary.registerOre("logWood", new ItemStack(BlocksLOG, 1, 32767));
    OreDictionary.registerOre("treeLeaves", new ItemStack(BlocksLEAVES, 1, 32767));
    
    OreDictionary.registerOre("stairWood", new ItemStack(BlocksSTAIRS_ALDER, 1, 32767));
    OreDictionary.registerOre("stairWood", new ItemStack(BlocksSTAIRS_HAWTHORN, 1, 32767));
    OreDictionary.registerOre("stairWood", new ItemStack(BlocksSTAIRS_ROWAN, 1, 32767));
    
    OreDictionary.registerOre("cropGarlic", new ItemStack(ItemsSEEDS_GARLIC, 1, 32767));
    
    GameRegistry.addSmelting(ItemsGENERIC.itemSoftClayJar.createStack(), ItemsGENERIC.itemEmptyClayJar.createStack(), 0.0F);
    GameRegistry.addSmelting(ItemsGENERIC.itemOddPorkRaw.createStack(), ItemsGENERIC.itemOddPorkCooked.createStack(), 0.0F);
    GameRegistry.addSmelting(ItemsGENERIC.itemGoldenThread.createStack(), new ItemStack(Items.field_151074_bl), 0.0F);
    GameRegistry.addSmelting(ItemsGENERIC.itemMuttonRaw.createStack(), ItemsGENERIC.itemMuttonCooked.createStack(), 0.0F);
    GameRegistry.addSmelting(new ItemStack(BlocksBLOODED_WOOL), ItemsGENERIC.itemDarkCloth.createStack(), 0.0F);
    
    GameRegistry.addSmelting(new ItemStack(ItemsSTEW_RAW), new ItemStack(ItemsSTEW), 1.0F);
    
    if (!instancesmeltAllSaplingsToWoodAsh) {
      GameRegistry.addSmelting(Blocks.field_150345_g, ItemsGENERIC.itemAshWood.createStack(), 0.0F);
      GameRegistry.addSmelting(new ItemStack(BlocksSAPLING), ItemsGENERIC.itemAshWood.createStack(), 0.0F);
    }
    
    GameRegistry.addSmelting(new ItemStack(BlocksLOG, 1, 0), new ItemStack(Items.field_151044_h, 1, 1), 0.0F);
    GameRegistry.addSmelting(new ItemStack(BlocksLOG, 1, 1), new ItemStack(Items.field_151044_h, 1, 1), 0.0F);
    GameRegistry.addSmelting(new ItemStack(BlocksLOG, 1, 2), new ItemStack(Items.field_151044_h, 1, 1), 0.0F);
    





    DistilleryRecipes.instance().addRecipe(ItemsGENERIC.itemFoulFume.createStack(), ItemsGENERIC.itemQuicklime.createStack(), 1, ItemsGENERIC.itemGypsum.createStack(), ItemsGENERIC.itemOilOfVitriol.createStack(), new ItemStack(Items.field_151123_aH), null);
    







    DistilleryRecipes.instance().addRecipe(ItemsGENERIC.itemBreathOfTheGoddess.createStack(), Dye.LAPIS_LAZULI.createStack(), 3, ItemsGENERIC.itemTearOfTheGoddess.createStack(), ItemsGENERIC.itemWhiffOfMagic.createStack(), new ItemStack(Items.field_151123_aH), ItemsGENERIC.itemFoulFume.createStack());
    







    DistilleryRecipes.instance().addRecipe(new ItemStack(Items.field_151045_i), ItemsGENERIC.itemOilOfVitriol.createStack(), 3, ItemsGENERIC.itemDiamondVapour.createStack(), ItemsGENERIC.itemDiamondVapour.createStack(), ItemsGENERIC.itemOdourOfPurity.createStack(), null);
    







    DistilleryRecipes.instance().addRecipe(ItemsGENERIC.itemDiamondVapour.createStack(), new ItemStack(Items.field_151073_bk), 3, ItemsGENERIC.itemOdourOfPurity.createStack(), ItemsGENERIC.itemReekOfMisfortune.createStack(), ItemsGENERIC.itemFoulFume.createStack(), ItemsGENERIC.itemRefinedEvil.createStack());
    







    DistilleryRecipes.instance().addRecipe(new ItemStack(Items.field_151079_bi), null, 6, ItemsGENERIC.itemEnderDew.createStack(2), ItemsGENERIC.itemEnderDew.createStack(2), ItemsGENERIC.itemEnderDew.createStack(), ItemsGENERIC.itemWhiffOfMagic.createStack());
    







    DistilleryRecipes.instance().addRecipe(new ItemStack(Items.field_151065_br), new ItemStack(Items.field_151016_H), 1, new ItemStack(Items.field_151114_aO), new ItemStack(Items.field_151114_aO), ItemsGENERIC.itemReekOfMisfortune.createStack(), null);
    







    DistilleryRecipes.instance().addRecipe(ItemsGENERIC.itemDemonHeart.createStack(), ItemsGENERIC.itemDiamondVapour.createStack(), 4, ItemsGENERIC.itemInfernalBlood.createStack(2), ItemsGENERIC.itemInfernalBlood.createStack(2), ItemsGENERIC.itemRefinedEvil.createStack(), null);
    







    DistilleryRecipes.instance().addRecipe(ItemsGENERIC.itemDemonHeart.createStack(), new ItemStack(Blocks.field_150424_aL), 2, new ItemStack(Blocks.field_150425_aM), ItemsGENERIC.itemInfernalBlood.createStack(), ItemsGENERIC.itemInfernalBlood.createStack(), null);
    







    DistilleryRecipes.instance().addRecipe(ItemsGENERIC.itemBrewOfFlowingSpirit.createStack(), ItemsGENERIC.itemOilOfVitriol.createStack(), 2, ItemsGENERIC.itemFocusedWill.createStack(), ItemsGENERIC.itemCondensedFear.createStack(), ItemsGENERIC.itemBrewOfHollowTears.createStack(4), ItemsGENERIC.itemBrewOfHollowTears.createStack(4));
    











    KettleRecipes.instance().addRecipe(ItemsGENERIC.itemBrewOfVines.createStack(3), 1, 0, 0.0F, -16753913, 0, new ItemStack[] { new ItemStack(Blocks.field_150395_bd), new ItemStack(Blocks.field_150337_Q), new ItemStack(Blocks.field_150338_P), ItemsGENERIC.itemDogTongue.createStack(), new ItemStack(Items.field_151015_O), ItemsGENERIC.itemReekOfMisfortune.createStack() });
    









    KettleRecipes.instance().addRecipe(ItemsGENERIC.itemBrewOfWebs.createStack(3), 1, 0, 0.0F, -1, 0, new ItemStack[] { ItemsGENERIC.itemWeb.createStack(), new ItemStack(Blocks.field_150337_Q), ItemsGENERIC.itemBatWool.createStack(), new ItemStack(Blocks.field_150327_N), ItemsGENERIC.itemWhiffOfMagic.createStack(), ItemsGENERIC.itemBelladonnaFlower.createStack() });
    









    KettleRecipes.instance().addRecipe(ItemsGENERIC.itemBrewOfThorns.createStack(3), 1, 0, 0.0F, -10027232, 0, new ItemStack[] { Dye.CACTUS_GREEN.createStack(), new ItemStack(Blocks.field_150338_P), ItemsGENERIC.itemOilOfVitriol.createStack(), ItemsGENERIC.itemOdourOfPurity.createStack(), new ItemStack(Blocks.field_150328_O), ItemsGENERIC.itemMandrakeRoot.createStack() });
    









    KettleRecipes.instance().addRecipe(ItemsGENERIC.itemBrewOfInk.createStack(3), 1, 0, 0.0F, -13421773, 0, new ItemStack[] { Dye.INK_SAC.createStack(), ItemsGENERIC.itemQuicklime.createStack(), ItemsGENERIC.itemOilOfVitriol.createStack(), new ItemStack(Items.field_151123_aH), ItemsGENERIC.itemBelladonnaFlower.createStack(), ItemsGENERIC.itemRowanBerries.createStack() });
    









    KettleRecipes.instance().addRecipe(ItemsGENERIC.itemBrewOfSprouting.createStack(3), 1, 0, 0.0F, -11258073, 0, new ItemStack[] { new ItemStack(BlocksSAPLING, 1, 0), new ItemStack(BlocksSAPLING, 1, 1), new ItemStack(BlocksSAPLING, 1, 2), ItemsGENERIC.itemDogTongue.createStack(), ItemsGENERIC.itemMandrakeRoot.createStack(), new ItemStack(Blocks.field_150328_O) });
    









    KettleRecipes.instance().addRecipe(ItemsGENERIC.itemBrewOfErosion.createStack(3), 1, 0, 0.0F, -4456656, 0, new ItemStack[] { ItemsGENERIC.itemOilOfVitriol.createStack(), ItemsGENERIC.itemOilOfVitriol.createStack(), ItemsGENERIC.itemQuicklime.createStack(), ItemsGENERIC.itemBelladonnaFlower.createStack(), new ItemStack(Blocks.field_150327_N), new ItemStack(Items.field_151064_bs) });
    









    KettleRecipes.instance().addRecipe(ItemsGENERIC.itemBrewOfRaising.createStack(3), 1, 0, 500.0F, -12120505, 0, new ItemStack[] { ItemsGENERIC.itemBatWool.createStack(), ItemsGENERIC.itemMutandis.createStack(), new ItemStack(Items.field_151137_ax), ItemsGENERIC.itemOilOfVitriol.createStack(), new ItemStack(Items.field_151103_aS), new ItemStack(Items.field_151078_bh) });
    









    KettleRecipes.instance().addRecipe(ItemsGENERIC.itemBrewGrotesque.createStack(3), 1, 0, 500.0F, -13491946, 0, new ItemStack[] { ItemsGENERIC.itemMutandisExtremis.createStack(), ItemsGENERIC.itemMandrakeRoot.createStack(), ItemsGENERIC.itemArtichoke.createStack(), ItemsGENERIC.itemDogTongue.createStack(), new ItemStack(Items.field_151153_ao), new ItemStack(Items.field_151170_bI) });
    









    KettleRecipes.instance().addRecipe(ItemsGENERIC.itemBrewOfLove.createStack(3), 1, 0, 0.0F, 42492, 0, new ItemStack[] { new ItemStack(Blocks.field_150328_O), ItemsGENERIC.itemWhiffOfMagic.createStack(), ItemsGENERIC.itemArtichoke.createStack(), new ItemStack(Items.field_151150_bK), new ItemStack(Blocks.field_150392_bi), Dye.COCOA_BEANS.createStack() });
    









    KettleRecipes.instance().addRecipe(ItemsGENERIC.itemBrewOfIce.createStack(3), 1, 0, 1000.0F, -13565953, 0, new ItemStack[] { ItemsGENERIC.itemIcyNeedle.createStack(), new ItemStack(Items.field_151126_ay), ItemsGENERIC.itemArtichoke.createStack(), new ItemStack(Items.field_151060_bw), new ItemStack(Blocks.field_150337_Q), ItemsGENERIC.itemOdourOfPurity.createStack() });
    









    KettleRecipes.instance().addRecipe(ItemsGENERIC.itemBrewOfTheDepths.createStack(3), 1, 0, 0.0F, -15260093, 0, new ItemStack[] { ItemsGENERIC.itemMandrakeRoot.createStack(), ItemsGENERIC.itemArtichoke.createStack(), ItemsGENERIC.itemOdourOfPurity.createStack(), ItemsGENERIC.itemTearOfTheGoddess.createStack(), new ItemStack(Blocks.field_150392_bi), Dye.INK_SAC.createStack() });
    









    KettleRecipes.instance().addRecipe(ItemsGENERIC.itemBrewOfInfection.createStack(3), 0, 0, 0.0F, -11112850, 0, new ItemStack[] { ItemsGENERIC.itemToeOfFrog.createStack(), ItemsGENERIC.itemCreeperHeart.createStack(), ItemsGENERIC.itemWormyApple.createStack(), ItemsGENERIC.itemBelladonnaFlower.createStack(), new ItemStack(Items.field_151078_bh), ItemsGENERIC.itemMutandis.createStack() });
    









    KettleRecipes.instance().addRecipe(ItemsGENERIC.itemBrewOfSleeping.createStack(3), 1, 0, 0.0F, -7710856, 0, new ItemStack[] { ItemsGENERIC.itemPurifiedMilk.createStack(), new ItemStack(Items.field_151106_aX), ItemsGENERIC.itemBrewOfLove.createStack(), ItemsGENERIC.itemWhiffOfMagic.createStack(), ItemsGENERIC.itemIcyNeedle.createStack(), ItemsGENERIC.itemArtichoke.createStack() });
    









    KettleRecipes.instance().addRecipe(ItemsGENERIC.itemBrewOfFlowingSpirit.createStack(3), 0, 0, 0.0F, -16711834, instancedimensionDreamID, new ItemStack[] { ItemsGENERIC.itemFancifulThread.createStack(), ItemsGENERIC.itemArtichoke.createStack(), ItemsGENERIC.itemMandrakeRoot.createStack(), new ItemStack(BlocksSPANISH_MOSS), new ItemStack(BlocksGLINT_WEED), ItemsGENERIC.itemBatWool.createStack() }).setUnlocalizedName("witchery.brew.flowingspirit");
    










    KettleRecipes.instance().addRecipe(ItemsGENERIC.itemBrewOfWasting.createStack(3), 1, 0, 0.0F, -12440546, 0, new ItemStack[] { ItemsGENERIC.itemMellifluousHunger.createStack(), new ItemStack(Items.field_151078_bh), ItemsGENERIC.itemBelladonnaFlower.createStack(), new ItemStack(BlocksEMBER_MOSS), new ItemStack(Items.field_151170_bI), new ItemStack(Items.field_151070_bp) });
    









    KettleRecipes.instance().addRecipe(ItemsGENERIC.itemBrewOfBats.createStack(3), 1, 0, 0.0F, -9809858, 0, new ItemStack[] { ItemsGENERIC.itemBatBall.createStack(), ItemsGENERIC.itemBatWool.createStack(), new ItemStack(Items.field_151034_e), new ItemStack(Items.field_151102_aT), new ItemStack(Items.field_151071_bq), new ItemStack(Items.field_151016_H) });
    









    KettleRecipes.instance().addRecipe(ItemsGENERIC.itemBrewSubstitution.createStack(3), 1, 0, 0.0F, -7010720, 0, new ItemStack[] { ItemsGENERIC.itemEnderDew.createStack(), ItemsGENERIC.itemEnderDew.createStack(), ItemsGENERIC.itemMutandisExtremis.createStack(), new ItemStack(Items.field_151110_aK), new ItemStack(Items.field_151064_bs), ItemsGENERIC.itemBranchEnt.createStack() });
    









    KettleRecipes.instance().addRecipe(ItemsGENERIC.itemBrewRevealing.createStack(3), 1, 0, 0.0F, -4079167, 0, new ItemStack[] { new ItemStack(Items.field_151172_bF), new ItemStack(Items.field_151070_bp), new ItemStack(Items.field_151070_bp), new ItemStack(Items.field_151068_bn, 1, 8198), new ItemStack(Blocks.field_150338_P), ItemsGENERIC.itemOdourOfPurity.createStack() });
    










    KettleRecipes.instance().addRecipe(ItemsGENERIC.itemBrewOfSolidDirt.createStack(3), 1, 0, 2000.0F, -11720688, 0, true, new ItemStack[] { new ItemStack(Blocks.field_150346_d), ItemsGENERIC.itemFoulFume.createStack(), ItemsGENERIC.itemOdourOfPurity.createStack(), ItemsGENERIC.itemMutandis.createStack(), ItemsGENERIC.itemAshWood.createStack(), new ItemStack(BlocksSPANISH_MOSS) }).setUnlocalizedName("witchery.brew.solidification");
    










    KettleRecipes.instance().addRecipe(ItemsGENERIC.itemBrewOfSolidRock.createStack(3), 1, 0, 2000.0F, -8355712, 0, false, new ItemStack[] { new ItemStack(Blocks.field_150348_b), ItemsGENERIC.itemFoulFume.createStack(), ItemsGENERIC.itemOdourOfPurity.createStack(), ItemsGENERIC.itemMutandis.createStack(), ItemsGENERIC.itemAshWood.createStack(), new ItemStack(BlocksSPANISH_MOSS) });
    










    KettleRecipes.instance().addRecipe(ItemsGENERIC.itemBrewOfSolidSand.createStack(3), 1, 0, 2000.0F, -3495323, 0, false, new ItemStack[] { new ItemStack(Blocks.field_150354_m), ItemsGENERIC.itemFoulFume.createStack(), ItemsGENERIC.itemOdourOfPurity.createStack(), ItemsGENERIC.itemMutandis.createStack(), ItemsGENERIC.itemAshWood.createStack(), new ItemStack(BlocksSPANISH_MOSS) });
    










    KettleRecipes.instance().addRecipe(ItemsGENERIC.itemBrewOfSolidSandstone.createStack(3), 1, 0, 2000.0F, -8427008, 0, false, new ItemStack[] { new ItemStack(Blocks.field_150322_A), ItemsGENERIC.itemFoulFume.createStack(), ItemsGENERIC.itemOdourOfPurity.createStack(), ItemsGENERIC.itemMutandis.createStack(), ItemsGENERIC.itemAshWood.createStack(), new ItemStack(BlocksSPANISH_MOSS) });
    










    KettleRecipes.instance().addRecipe(ItemsGENERIC.itemBrewOfSolidErosion.createStack(3), 1, 0, 2000.0F, 62236, 0, false, new ItemStack[] { ItemsGENERIC.itemBrewOfErosion.createStack(), ItemsGENERIC.itemFoulFume.createStack(), ItemsGENERIC.itemOdourOfPurity.createStack(), ItemsGENERIC.itemMutandis.createStack(), ItemsGENERIC.itemAshWood.createStack(), new ItemStack(BlocksSPANISH_MOSS) });
    











    KettleRecipes.instance().addRecipe(ItemsGENERIC.itemBrewOfCursedLeaping.createStack(3), 1, 1, 0.0F, -16758145, 0, new ItemStack[] { new ItemStack(Items.field_151103_aS), new ItemStack(Items.field_151034_e), ItemsGENERIC.itemBrewOfSprouting.createStack(), ItemsGENERIC.itemBelladonnaFlower.createStack(), new ItemStack(Items.field_151008_G), new ItemStack(Items.field_151115_aP) });
    









    KettleRecipes.instance().addRecipe(ItemsGENERIC.itemBrewOfFrogsTongue.createStack(3), 1, 2, 0.0F, -12938226, 0, new ItemStack[] { new ItemStack(Blocks.field_150337_Q), new ItemStack(Items.field_151015_O), ItemsGENERIC.itemBrewOfWebs.createStack(), ItemsGENERIC.itemArtichoke.createStack(), new ItemStack(Items.field_151123_aH), ItemsGENERIC.itemToeOfFrog.createStack() });
    









    KettleRecipes.instance().addRecipe(ItemsGENERIC.itemBrewOfHitchcock.createStack(3), 1, 3, 0.0F, -3908582, 0, new ItemStack[] { new ItemStack(Blocks.field_150338_P), new ItemStack(Items.field_151014_N), ItemsGENERIC.itemBrewOfThorns.createStack(), ItemsGENERIC.itemBatWool.createStack(), new ItemStack(Items.field_151008_G), ItemsGENERIC.itemOwletsWing.createStack() });
    










    KettleRecipes.instance().addRecipe(ItemsGENERIC.itemCongealedSpirit.createStack(), 0, 0, 2000.0F, -3096310, 0, new ItemStack[] { ItemsGENERIC.itemBrewOfHollowTears.createStack(), ItemsGENERIC.itemSubduedSpirit.createStack(), ItemsGENERIC.itemSubduedSpirit.createStack(), ItemsGENERIC.itemSubduedSpirit.createStack(), ItemsGENERIC.itemSubduedSpirit.createStack(), ItemsGENERIC.itemSubduedSpirit.createStack() });
    










    KettleRecipes.instance().addRecipe(ItemsGENERIC.itemRedstoneSoup.createStack(), 0, 0, 1000.0F, -59882, 0, new ItemStack[] { new ItemStack(Items.field_151137_ax), ItemsGENERIC.itemDropOfLuck.createStack(), ItemsGENERIC.itemBatWool.createStack(), ItemsGENERIC.itemDogTongue.createStack(), ItemsGENERIC.itemBelladonnaFlower.createStack(), ItemsGENERIC.itemMandrakeRoot.createStack() });
    









    KettleRecipes.instance().addRecipe(ItemsGENERIC.itemFlyingOintment.createStack(), 0, 0, 3000.0F, 47916, 0, new ItemStack[] { ItemsGENERIC.itemRedstoneSoup.createStack(), new ItemStack(Items.field_151068_bn, 1, 8258), new ItemStack(Items.field_151045_i), new ItemStack(Items.field_151008_G), ItemsGENERIC.itemBatWool.createStack(), ItemsGENERIC.itemBelladonnaFlower.createStack() });
    









    KettleRecipes.instance().addRecipe(ItemsGENERIC.itemMysticUnguent.createStack(), 0, 0, 3000.0F, -14333109, 0, new ItemStack[] { ItemsGENERIC.itemRedstoneSoup.createStack(), new ItemStack(Items.field_151068_bn, 1, 8265), new ItemStack(Items.field_151045_i), new ItemStack(BlocksSAPLING, 1, 0), ItemsGENERIC.itemCreeperHeart.createStack(), ItemsGENERIC.itemInfernalBlood.createStack() });
    









    KettleRecipes.instance().addRecipe(ItemsGENERIC.itemHappenstanceOil.createStack(), 0, 0, 2000.0F, 8534058, 0, new ItemStack[] { ItemsGENERIC.itemRedstoneSoup.createStack(), new ItemStack(Items.field_151068_bn, 1, 8262), new ItemStack(Items.field_151061_bv), new ItemStack(Items.field_151150_bK), new ItemStack(Items.field_151070_bp), ItemsGENERIC.itemMandrakeRoot.createStack() });
    









    KettleRecipes.instance().addRecipe(ItemsGENERIC.itemGhostOfTheLight.createStack(2), 0, 0, 4000.0F, -5584658, 0, new ItemStack[] { ItemsGENERIC.itemRedstoneSoup.createStack(), new ItemStack(Items.field_151068_bn, 1, 8270), new ItemStack(Items.field_151068_bn, 1, 8259), ItemsPOPPET.firePoppet.createStack(), new ItemStack(Blocks.field_150478_aa), ItemsGENERIC.itemDogTongue.createStack() });
    









    KettleRecipes.instance().addRecipe(ItemsGENERIC.itemSoulOfTheWorld.createStack(2), 0, 0, 4000.0F, -16003328, 0, new ItemStack[] { ItemsGENERIC.itemRedstoneSoup.createStack(), new ItemStack(Items.field_151068_bn, 1, 8257), new ItemStack(Items.field_151153_ao, 1, 1), ItemsGENERIC.itemAttunedStone.createStack(), ItemsGENERIC.itemMandrakeRoot.createStack(), new ItemStack(BlocksSAPLING, 1, 0) });
    









    KettleRecipes.instance().addRecipe(ItemsGENERIC.itemSpiritOfOtherwhere.createStack(2), 0, 0, 4000.0F, -7128833, 0, new ItemStack[] { ItemsGENERIC.itemRedstoneSoup.createStack(), new ItemStack(Items.field_151068_bn, 1, 8258), new ItemStack(Items.field_151061_bv), new ItemStack(Items.field_151061_bv), ItemsGENERIC.itemDropOfLuck.createStack(), ItemsGENERIC.itemBatWool.createStack() });
    









    KettleRecipes.instance().addRecipe(ItemsGENERIC.itemSpiritOfOtherwhere.createStack(2), 0, 0, 4000.0F, -7128833, 0, false, new ItemStack[] { ItemsGENERIC.itemRedstoneSoup.createStack(), new ItemStack(Items.field_151068_bn, 1, 16210), new ItemStack(Items.field_151061_bv), new ItemStack(Items.field_151061_bv), ItemsGENERIC.itemDropOfLuck.createStack(), ItemsGENERIC.itemBatWool.createStack() });
    










    KettleRecipes.instance().addRecipe(ItemsGENERIC.itemInfernalAnimus.createStack(2), 0, 0, 4000.0F, -7598080, 0, new ItemStack[] { ItemsGENERIC.itemRedstoneSoup.createStack(), new ItemStack(Items.field_151068_bn, 1, 8236), ItemsPOPPET.voodooPoppet.createStack(), ItemsGENERIC.itemDemonHeart.createStack(), ItemsGENERIC.itemRefinedEvil.createStack(), new ItemStack(Items.field_151072_bj) });
    









    KettleRecipes.instance().addRecipe(ItemsGENERIC.itemInfernalAnimus.createStack(2), 0, 0, 4000.0F, -7598080, 0, false, new ItemStack[] { ItemsGENERIC.itemRedstoneSoup.createStack(), new ItemStack(Items.field_151068_bn, 1, 16172), ItemsPOPPET.voodooPoppet.createStack(), ItemsGENERIC.itemDemonHeart.createStack(), ItemsGENERIC.itemRefinedEvil.createStack(), new ItemStack(Items.field_151072_bj) });
    










    KettleRecipes.instance().addRecipe(ItemsGENERIC.itemInfusionBase.createStack(), 1, 0, 3000.0F, -10520657, 0, new ItemStack[] { ItemsGENERIC.itemRedstoneSoup.createStack(), ItemsGENERIC.itemBrewOfFlowingSpirit.createStack(), ItemsGENERIC.itemCreeperHeart.createStack(), ItemsGENERIC.itemToeOfFrog.createStack(), ItemsGENERIC.itemOwletsWing.createStack(), ItemsGENERIC.itemDogTongue.createStack() });
    









    KettleRecipes.instance().addRecipe(ItemsGENERIC.itemInfusionBase.createStack(2), 0, 0, 3000.0F, -10520657, 0, new ItemStack[] { ItemsGENERIC.itemInfusionBase.createStack(), ItemsGENERIC.itemBrewOfFlowingSpirit.createStack(), ItemsGENERIC.itemHintOfRebirth.createStack(), ItemsGENERIC.itemMandrakeRoot.createStack(), ItemsGENERIC.itemBatWool.createStack(), new ItemStack(BlocksBRAMBLE, 1, 1) });
    













    CreaturePower.Registry.instance().add(new CreaturePowerSpider(1, EntityCaveSpider.class));
    CreaturePower.Registry.instance().add(new CreaturePowerSpider(2, EntitySpider.class));
    CreaturePower.Registry.instance().add(new CreaturePowerCreeper(3));
    CreaturePower.Registry.instance().add(new CreaturePowerBat(4, EntityBat.class));
    CreaturePower.Registry.instance().add(new CreaturePowerSquid(5));
    CreaturePower.Registry.instance().add(new com.emoniph.witchery.infusion.infusions.creature.CreaturePowerGhast(6));
    CreaturePower.Registry.instance().add(new CreaturePowerBlaze(7));
    CreaturePower.Registry.instance().add(new CreaturePowerPigMan(8));
    CreaturePower.Registry.instance().add(new com.emoniph.witchery.infusion.infusions.creature.CreaturePowerZombie(9));
    CreaturePower.Registry.instance().add(new CreaturePowerSkeleton(10));
    CreaturePower.Registry.instance().add(new CreaturePowerJump(11, EntityMagmaCube.class));
    CreaturePower.Registry.instance().add(new CreaturePowerJump(12, EntitySlime.class));
    CreaturePower.Registry.instance().add(new CreaturePowerSpeed(13, EntitySilverfish.class));
    CreaturePower.Registry.instance().add(new CreaturePowerSpeed(14, EntityOcelot.class));
    CreaturePower.Registry.instance().add(new CreaturePowerSpeed(15, EntityWolf.class));
    CreaturePower.Registry.instance().add(new CreaturePowerSpeed(16, EntityHorse.class));
    CreaturePower.Registry.instance().add(new CreaturePowerEnderman(17));
    CreaturePower.Registry.instance().add(new CreaturePowerHeal(18, EntitySheep.class, 1));
    CreaturePower.Registry.instance().add(new CreaturePowerHeal(19, EntityCow.class, 1));
    CreaturePower.Registry.instance().add(new CreaturePowerHeal(20, EntityChicken.class, 1));
    CreaturePower.Registry.instance().add(new CreaturePowerHeal(21, EntityPig.class, 1));
    CreaturePower.Registry.instance().add(new CreaturePowerHeal(22, EntityVillager.class, 2));
    CreaturePower.Registry.instance().add(new CreaturePowerHeal(23, EntityMooshroom.class, 2));
    CreaturePower.Registry.instance().add(new CreaturePowerBat(24, EntityOwl.class));
    CreaturePower.Registry.instance().add(new CreaturePowerJump(25, EntityToad.class));
    





    RiteRegistry.addRecipe(1, 0, new RiteBindCircleToTalisman(), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(ItemsCIRCLE_TALISMAN), new ItemStack(Items.field_151137_ax) }), new SacrificePower(1000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[0]).setUnlocalizedName("witchery.rite.bindcircle");
    







    RiteRegistry.addRecipe(2, 1, new RiteSummonItem(ItemsGENERIC.itemWaystoneBound.createStack(), RiteSummonItem.Binding.LOCATION), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemWaystone.createStack(), ItemsGENERIC.itemEnderDew.createStack(), new ItemStack(Items.field_151114_aO) }), new SacrificePower(500.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.bindwaystone");
    









    RiteRegistry.addRecipe(3, 3, new RiteSummonItem(ItemsGENERIC.itemAttunedStoneCharged.createStack(), RiteSummonItem.Binding.NONE), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemAttunedStone.createStack(), new ItemStack(Items.field_151114_aO), new ItemStack(Items.field_151137_ax), ItemsGENERIC.itemAshWood.createStack(), ItemsGENERIC.itemQuicklime.createStack() }), new SacrificePower(2000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0), new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.chargestone");
    












    RiteRegistry.addRecipe(4, 4, new RiteInfusionRecharge(10, 4, 40.0F, 0), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Items.field_151068_bn, 1, 8193) }), new SacrificePower(4000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0), new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.infusionrecharge");
    













    RiteRegistry.addRecipe(5, 5, new RiteTeleportToWaystone(3), new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemWaystoneBound.createStack() }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 16, 0) }).setUnlocalizedName("witchery.rite.teleporttowaystone");
    





    RiteRegistry.addRecipe(6, 6, new RiteTeleportEntity(3), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemWaystone.createStack(), new ItemStack(ItemsTAGLOCK_KIT, 1, 1), ItemsGENERIC.itemEnderDew.createStack(), new ItemStack(Items.field_151036_c) }), new SacrificePower(3000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 28, 0) }).setUnlocalizedName("witchery.rite.teleportentity");
    










    RiteRegistry.addRecipe(7, 7, new RiteTransposeOres(8, 30, new Block[] { Blocks.field_150366_p, Blocks.field_150352_o }), new SacrificeItem(new ItemStack[] { new ItemStack(Items.field_151079_bi), new ItemStack(Items.field_151042_j), new ItemStack(Items.field_151065_br), ItemsGENERIC.itemDiamondVapour.createStack(), ItemsGENERIC.itemAttunedStoneCharged.createStack() }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 40, 0) }).setUnlocalizedName("witchery.rite.teleportironore");
    













    RiteRegistry.addRecipe(8, 8, new com.emoniph.witchery.ritual.rites.RiteProtectionCircleRepulsive(4, 0.8F, 0), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Items.field_151008_G), new ItemStack(Items.field_151137_ax) }), new SacrificePower(500.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.protection");
    








    RiteRegistry.addRecipe(9, 9, new RiteProtectionCircleAttractive(4, 0.8F, 0), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Items.field_151123_aH), new ItemStack(Items.field_151137_ax) }), new SacrificePower(500.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.imprisonment");
    








    RiteRegistry.addRecipe(10, 10, new RiteProtectionCircleBarrier(4, 5, 1.2F, false, 0), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Blocks.field_150343_Z), new ItemStack(Items.field_151137_ax) }), new SacrificeOptionalItem(ItemsGENERIC.itemWaystoneBound.createStack()), new SacrificePower(500.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.barrier");
    









    RiteRegistry.addRecipe(11, 11, new RiteProtectionCircleBarrier(6, 6, 1.4F, true, 0), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Blocks.field_150343_Z), new ItemStack(Items.field_151114_aO) }), new SacrificeOptionalItem(ItemsGENERIC.itemWaystoneBound.createStack()), new SacrificePower(1000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.barrierlarge");
    









    RiteRegistry.addRecipe(12, 12, new RiteProtectionCircleBarrier(6, 4, 0.0F, true, 60), new SacrificeItem(new ItemStack[] { new ItemStack(Blocks.field_150343_Z), ItemsGENERIC.itemAttunedStoneCharged.createStack() }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.barrierportable");
    










    RiteRegistry.addRecipe(13, 13, new RiteRaiseVolcano(8, 8), new SacrificeItem(new ItemStack[] { new ItemStack(Blocks.field_150348_b), new ItemStack(Items.field_151064_bs), new ItemStack(Items.field_151010_B), ItemsGENERIC.itemAttunedStoneCharged.createStack() }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 0, 16) }).setUnlocalizedName("witchery.rite.volcano");
    








    RiteRegistry.addRecipe(14, 14, new RiteWeatherCallStorm(0, 3, 8), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Items.field_151041_m), ItemsGENERIC.itemAshWood.createStack() }), new SacrificeOptionalItem(ItemsGENERIC.itemWaystoneBound.createStack()), new SacrificePower(1000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.storm");
    









    RiteRegistry.addRecipe(15, 15, new RiteWeatherCallStorm(3, 7, 18), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Items.field_151052_q), ItemsGENERIC.itemAshWood.createStack() }), new SacrificeOptionalItem(ItemsGENERIC.itemWaystoneBound.createStack()), new SacrificePower(2000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.stormlarge");
    









    RiteRegistry.addRecipe(16, 16, new RiteWeatherCallStorm(3, 7, 18), new SacrificeItem(new ItemStack[] { new ItemStack(Items.field_151040_l), ItemsGENERIC.itemAshWood.createStack(), ItemsGENERIC.itemAttunedStoneCharged.createStack() }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.stormportable");
    







    RiteRegistry.addRecipe(17, 17, new RiteEclipse(), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Items.field_151049_t), ItemsGENERIC.itemQuicklime.createStack() }), new SacrificePower(3000.0F, 20) }), EnumSet.of(RitualTraits.ONLY_AT_DAY), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.eclipse");
    








    RiteRegistry.addRecipe(18, 18, new RiteEclipse(), new SacrificeItem(new ItemStack[] { new ItemStack(Items.field_151036_c), ItemsGENERIC.itemQuicklime.createStack(), ItemsGENERIC.itemAttunedStoneCharged.createStack() }), EnumSet.of(RitualTraits.ONLY_AT_DAY), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.eclipseportable");
    







    RiteRegistry.addRecipe(19, 19, new RitePartEarth(60, 1, 10), new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemBrewOfErosion.createStack() }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.partearth");
    





    RiteRegistry.addRecipe(20, 20, new RiteRaiseColumn(4, 8), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemBrewOfSprouting.createStack(), new ItemStack(Blocks.field_150434_aF), new ItemStack(Items.field_151016_H) }), new SacrificeOptionalItem(ItemsGENERIC.itemWaystoneBound.createStack()) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.raiseearth");
    















    RiteRegistry.addRecipe(21, 23, new RiteBanishDemon(9), new SacrificeItem(new ItemStack[] { new ItemStack(Items.field_151065_br), ItemsGENERIC.itemWaystone.createStack(), ItemsGENERIC.itemAttunedStoneCharged.createStack() }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.banishdemonportable");
    







    RiteRegistry.addRecipe(22, 24, new RiteBanishDemon(9), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Items.field_151065_br), ItemsGENERIC.itemWaystone.createStack() }), new SacrificePower(2000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.banishdemon");
    









    RiteRegistry.addRecipe(23, 25, new RiteSummonCreature(EntityDemon.class, false), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemRefinedEvil.createStack(), new ItemStack(Items.field_151065_br), new ItemStack(Items.field_151079_bi) }), new SacrificeLiving(EntityVillager.class), new SacrificePower(3000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 0, 40) }).setUnlocalizedName("witchery.rite.summondemon");
    










    RiteRegistry.addRecipe(24, 26, new RiteSummonCreature(EntityDemon.class, false), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemRefinedEvil.createStack(), new ItemStack(Items.field_151072_bj), new ItemStack(Items.field_151079_bi), ItemsGENERIC.itemAttunedStone.createStack(), ItemsGENERIC.itemAttunedStoneCharged.createStack() }), new SacrificePower(3000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 0, 40) }).setUnlocalizedName("witchery.rite.summondemonexpensive");
    











    RiteRegistry.addRecipe(25, 27, new RiteSummonCreature(EntityWither.class, false), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Items.field_151144_bL, 1, 1), ItemsGENERIC.itemDiamondVapour.createStack(), new ItemStack(Items.field_151079_bi) }), new SacrificeLiving(EntityVillager.class), new SacrificePower(4000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 0, 28), new Circle(0, 0, 40) }).setUnlocalizedName("witchery.rite.summonwither");
    











    RiteRegistry.addRecipe(26, 28, new RiteSummonCreature(EntityWither.class, false), new SacrificeItem(new ItemStack[] { new ItemStack(Items.field_151144_bL, 1, 1), new ItemStack(Items.field_151045_i), new ItemStack(Items.field_151079_bi), ItemsGENERIC.itemAttunedStone.createStack(), ItemsGENERIC.itemAttunedStoneCharged.createStack() }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 0, 28), new Circle(0, 0, 40) }).setUnlocalizedName("witchery.rite.summonwitherexpensive");
    














    infusionLight = new InfusionLight(1);
    Infusion.Registry.instance().add(infusionLight);
    
    RiteRegistry.addRecipe(27, 31, new RiteInfusePlayers(infusionLight, 200, 4), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemGhostOfTheLight.createStack() }), new SacrificePower(2000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0), new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.infusionlight");
    








    infusionWorld = new InfusionOverworld(2);
    Infusion.Registry.instance().add(infusionWorld);
    
    RiteRegistry.addRecipe(28, 32, new RiteInfusePlayers(infusionWorld, 200, 4), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemSoulOfTheWorld.createStack() }), new SacrificePower(4000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0), new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.infusionearth");
    








    infusionEnder = new InfusionOtherwhere(3);
    Infusion.Registry.instance().add(infusionEnder);
    
    RiteRegistry.addRecipe(29, 33, new RiteInfusePlayers(infusionEnder, 200, 4), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemSpiritOfOtherwhere.createStack() }), new SacrificePower(4000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 16, 0), new Circle(0, 28, 0) }).setUnlocalizedName("witchery.rite.infusionender");
    








    infusionBeast = new InfusionInfernal(4);
    Infusion.Registry.instance().add(infusionBeast);
    
    RiteRegistry.addRecipe(30, 34, new RiteInfusePlayers(infusionBeast, 200, 4), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemInfernalAnimus.createStack() }), new SacrificePower(4000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 0, 16), new Circle(0, 0, 28) }).setUnlocalizedName("witchery.rite.infusionhell");
    








    RiteRegistry.addRecipe(31, 35, new RiteSummonItem(ItemsGENERIC.itemBroomEnchanted.createStack(), RiteSummonItem.Binding.NONE), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemBroom.createStack(), ItemsGENERIC.itemFlyingOintment.createStack() }), new SacrificePower(3000.0F, 20) }), EnumSet.of(RitualTraits.ONLY_AT_NIGHT), new Circle[] { new Circle(16, 0, 0), new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.infusionsky");
    










    RiteRegistry.addRecipe(32, 36, new RiteSummonItem(ItemsGENERIC.itemNecroStone.createStack(), RiteSummonItem.Binding.NONE), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemAttunedStone.createStack(), new ItemStack(Items.field_151103_aS), new ItemStack(Items.field_151078_bh), ItemsGENERIC.itemAshWood.createStack(), new ItemStack(Items.field_151040_l), ItemsGENERIC.itemSpectralDust.createStack() }), new SacrificePower(1000.0F, 20) }), EnumSet.of(RitualTraits.ONLY_AT_NIGHT), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.necrostone");
    












    RiteRegistry.addRecipe(33, 30, new RiteSummonCreature(EntityFamiliar.class, true), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemDropOfLuck.createStack(), new ItemStack(Items.field_151147_al), new ItemStack(Items.field_151043_k), new ItemStack(ItemsARTHANA) }), new SacrificePower(2000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.summonfamiliar");
    










    RiteRegistry.addRecipe(34, 2, new RiteSummonItem(ItemsGENERIC.itemWaystoneBound.createStack(2), RiteSummonItem.Binding.COPY_LOCATION), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemWaystoneBound.createStack(), ItemsGENERIC.itemWaystone.createStack(), ItemsGENERIC.itemEnderDew.createStack(), new ItemStack(Items.field_151137_ax) }), new SacrificePower(500.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.bindwaystonecopy");
    










    RiteRegistry.addRecipe(35, 21, new RiteFertility(50, 15), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { Dye.BONE_MEAL.createStack(), ItemsGENERIC.itemHintOfRebirth.createStack(), ItemsGENERIC.itemDiamondVapour.createStack(), ItemsGENERIC.itemQuicklime.createStack(), ItemsGENERIC.itemGypsum.createStack(), ItemsGENERIC.itemMutandis.createStack() }), new SacrificePower(3000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.fertility");
    












    RiteRegistry.addRecipe(36, 22, new RiteFertility(50, 15), new SacrificeItem(new ItemStack[] { Dye.BONE_MEAL.createStack(), ItemsGENERIC.itemHintOfRebirth.createStack(), ItemsGENERIC.itemDiamondVapour.createStack(), ItemsGENERIC.itemQuicklime.createStack(), ItemsGENERIC.itemGypsum.createStack(), ItemsGENERIC.itemMutandisExtremis.createStack(), ItemsGENERIC.itemAttunedStoneCharged.createStack() }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.fertilityportable");
    











    RiteRegistry.addRecipe(37, 37, new com.emoniph.witchery.ritual.rites.RiteBlight(80, 15), new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemAttunedStoneCharged.createStack(), ItemsGENERIC.itemRedstoneSoup.createStack(), ItemsGENERIC.itemReekOfMisfortune.createStack(), new ItemStack(Items.field_151071_bq), new ItemStack(Items.field_151060_bw), new ItemStack(Items.field_151078_bh), new ItemStack(Items.field_151045_i) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 0, 28) }).setUnlocalizedName("witchery.rite.curseblight");
    











    RiteRegistry.addRecipe(38, 38, new RiteBlindness(80, 15), new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemAttunedStoneCharged.createStack(), ItemsGENERIC.itemRedstoneSoup.createStack(), ItemsGENERIC.itemReekOfMisfortune.createStack(), ItemsGENERIC.itemExhaleOfTheHornedOne.createStack(), ItemsGENERIC.itemBrewOfInk.createStack(), new ItemStack(Items.field_151170_bI), new ItemStack(Items.field_151070_bp), new ItemStack(Items.field_151045_i) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 0, 16) }).setUnlocalizedName("witchery.rite.curseblindness");
    












    RiteRegistry.addRecipe(39, 39, new RiteHellOnEarth(20, 15, 200.0F), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemRedstoneSoup.createStack(), ItemsGENERIC.itemDemonHeart.createStack(), ItemsGENERIC.itemWaystone.createStack(), new ItemStack(Items.field_151156_bN) }), new SacrificeLiving(EntityVillager.class), new SacrificeOptionalItem(ItemsGENERIC.itemWaystoneBound.createStack()), new SacrificePower(5000.0F, 20) }), EnumSet.of(RitualTraits.ONLY_OVERWORLD, RitualTraits.ONLY_AT_NIGHT), new Circle[] { new Circle(0, 0, 16), new Circle(0, 28, 0), new Circle(0, 0, 40) }).setUnlocalizedName("witchery.rite.hellonearth");
    














    RiteRegistry.addRecipe(40, 29, new RiteSummonCreature(EntityWitch.class, false), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemDiamondVapour.createStack(), ItemsGENERIC.itemExhaleOfTheHornedOne.createStack(), new ItemStack(Items.field_151064_bs), new ItemStack(ItemsARTHANA), new ItemStack(Items.field_151071_bq) }), new SacrificePower(2000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 0, 16) }).setUnlocalizedName("witchery.rite.summonwitch");
    











    RiteRegistry.addRecipe(41, 1, new RiteSummonItem(ItemsGENERIC.itemWaystoneBound.createStack(), RiteSummonItem.Binding.LOCATION), new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemWaystone.createStack(), ItemsGENERIC.itemEnderDew.createStack(), ItemsGENERIC.itemAshWood.createStack(), ItemsGENERIC.itemAttunedStoneCharged.createStack() }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.bindwaystoneportable");
    








    RiteRegistry.addRecipe(42, 2, new RiteSummonItem(ItemsGENERIC.itemWaystoneBound.createStack(2), RiteSummonItem.Binding.COPY_LOCATION), new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemWaystoneBound.createStack(), ItemsGENERIC.itemWaystone.createStack(), ItemsGENERIC.itemEnderDew.createStack(), ItemsGENERIC.itemQuicklime.createStack(), ItemsGENERIC.itemAttunedStoneCharged.createStack() }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.bindwaystonecopyportable");
    









    RiteRegistry.addRecipe(43, 22, new RiteNaturesPower(14, 8, 150, 2), new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemBrewOfSprouting.createStack(), new ItemStack(BlocksSAPLING, 1, 0), new ItemStack(BlocksSAPLING, 1, 1), new ItemStack(BlocksSAPLING, 1, 2), new ItemStack(Blocks.field_150345_g, 1, 0), new ItemStack(Blocks.field_150345_g, 1, 1), new ItemStack(Blocks.field_150345_g, 1, 2), ItemsGENERIC.itemAttunedStoneCharged.createStack() }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.naturespower");
    












    RiteRegistry.addRecipe(44, 36, new RitePriorIncarnation(5, 16), new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemNecroStone.createStack(), ItemsGENERIC.itemDogTongue.createStack(), new ItemStack(Items.field_151103_aS), ItemsGENERIC.itemSpectralDust.createStack(), ItemsGENERIC.itemAttunedStoneCharged.createStack() }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 0, 16) }).setUnlocalizedName("witchery.rite.priorincarnation");
    









    RiteRegistry.addRecipe(45, 0, new RiteBindCircleToTalisman(), new SacrificeItem(new ItemStack[] { new ItemStack(ItemsCIRCLE_TALISMAN), new ItemStack(Items.field_151114_aO), ItemsGENERIC.itemAttunedStoneCharged.createStack() }), EnumSet.noneOf(RitualTraits.class), new Circle[0]).setUnlocalizedName("witchery.rite.bindcircleportable");
    






    RiteRegistry.addRecipe(46, 20, new RiteRaiseColumn(6, 8), new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemBrewOfSprouting.createStack(), new ItemStack(Blocks.field_150434_aF), new ItemStack(Items.field_151137_ax) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.raiseearth");
    







    RiteRegistry.addRecipe(47, 20, new RiteRaiseColumn(9, 8), new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemBrewOfSprouting.createStack(), new ItemStack(Blocks.field_150434_aF), new ItemStack(Items.field_151114_aO) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(40, 0, 0) }).setUnlocalizedName("witchery.rite.raiseearth");
    







    RiteRegistry.addRecipe(48, 48, new RiteCurseCreature(true, "witcheryCursed", 1), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(ItemsTAGLOCK_KIT, 1, 1), ItemsGENERIC.itemExhaleOfTheHornedOne.createStack(), new ItemStack(Items.field_151071_bq), new ItemStack(Items.field_151016_H), ItemsGENERIC.itemBrewGrotesque.createStack() }), new SacrificePower(2000.0F, 20) }), EnumSet.of(RitualTraits.ONLY_IN_STROM), new Circle[] { new Circle(0, 0, 28) }).setUnlocalizedName("witchery.rite.cursecreature1");
    











    RiteRegistry.addRecipe(49, 49, new RiteCurseCreature(false, "witcheryCursed", 1), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(ItemsTAGLOCK_KIT, 1, 1), ItemsGENERIC.itemBreathOfTheGoddess.createStack(), new ItemStack(Items.field_151070_bp), new ItemStack(Items.field_151016_H), ItemsGENERIC.itemBrewOfLove.createStack() }), new SacrificePower(2000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.removecurse1");
    











    RiteRegistry.addRecipe(50, 35, new RiteSummonItem(new ItemStack(ItemsMYSTIC_BRANCH), RiteSummonItem.Binding.NONE), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemBranchEnt.createStack(), ItemsGENERIC.itemMysticUnguent.createStack() }), new SacrificePower(3000.0F, 20) }), EnumSet.of(RitualTraits.ONLY_AT_NIGHT), new Circle[] { new Circle(16, 0, 0), new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.infusiontree");
    









    RiteRegistry.addRecipe(51, 20, new RiteCookItem(5.0F, 0.08D), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Items.field_151072_bj), ItemsGENERIC.itemAshWood.createStack(), new ItemStack(Items.field_151044_h) }), new SacrificePower(1000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 0, 16) }).setUnlocalizedName("witchery.rite.cookfood");
    










    RiteRegistry.addRecipe(52, 48, new RiteCurseCreature(true, "witcheryInsanity", 1), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(ItemsTAGLOCK_KIT, 1, 1), ItemsGENERIC.itemExhaleOfTheHornedOne.createStack(), new ItemStack(Items.field_151170_bI), new ItemStack(Items.field_151102_aT), ItemsGENERIC.itemBrewGrotesque.createStack() }), new SacrificePower(2000.0F, 20) }), EnumSet.of(RitualTraits.ONLY_IN_STROM), new Circle[] { new Circle(0, 0, 28) }).setUnlocalizedName("witchery.rite.curseinsanity1");
    











    RiteRegistry.addRecipe(53, 49, new RiteCurseCreature(false, "witcheryInsanity", 1), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(ItemsTAGLOCK_KIT, 1, 1), ItemsGENERIC.itemBreathOfTheGoddess.createStack(), new ItemStack(Items.field_151174_bG), new ItemStack(Items.field_151102_aT), ItemsGENERIC.itemBrewOfLove.createStack() }), new SacrificePower(2000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.removeinsanity1");
    











    RiteRegistry.addRecipe(54, 1, new RiteBindFamiliar(7), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemTearOfTheGoddess.createStack(), ItemsGENERIC.itemOdourOfPurity.createStack(), ItemsGENERIC.itemWhiffOfMagic.createStack(), new ItemStack(Items.field_151045_i), ItemsGENERIC.itemInfernalBlood.createStack() }), new SacrificePower(8000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.bindfamiliar");
    











    RiteRegistry.addRecipe(55, 30, new com.emoniph.witchery.ritual.rites.RiteCallFamiliar(7), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemBreathOfTheGoddess.createStack(), ItemsGENERIC.itemHintOfRebirth.createStack(), ItemsGENERIC.itemWhiffOfMagic.createStack() }), new SacrificePower(1000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.callfamiliar");
    









    RiteRegistry.addRecipe(56, 50, new RiteCursePoppets(1), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(ItemsTAGLOCK_KIT, 1, 1), ItemsGENERIC.itemExhaleOfTheHornedOne.createStack(), ItemsPOPPET.antiVoodooPoppet.createStack(), new ItemStack(Items.field_151065_br), ItemsGENERIC.itemSpectralDust.createStack() }), new SacrificePower(7000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 0, 28) }).setUnlocalizedName("witchery.rite.corruptvoodooprotection");
    











    RiteRegistry.addRecipe(57, 35, new RiteSummonItem(new ItemStack(BlocksCRYSTAL_BALL), RiteSummonItem.Binding.NONE), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemQuartzSphere.createStack(), new ItemStack(Items.field_151043_k), ItemsGENERIC.itemHappenstanceOil.createStack() }), new SacrificePower(2000.0F, 20) }), EnumSet.of(RitualTraits.ONLY_AT_NIGHT), new Circle[] { new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.infusionfuture");
    









    RiteRegistry.addRecipe(58, 20, new RiteCookItem(5.0F, 0.16D), new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemAttunedStoneCharged.createStack(), new ItemStack(Items.field_151072_bj), ItemsGENERIC.itemAshWood.createStack(), new ItemStack(Items.field_151065_br) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 0, 16) }).setUnlocalizedName("witchery.rite.cookfood");
    









    RiteRegistry.addRecipe(59, 48, new RiteCurseCreature(true, "witcherySinking", 1), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(ItemsTAGLOCK_KIT, 1, 1), ItemsGENERIC.itemExhaleOfTheHornedOne.createStack(), Dye.INK_SAC.createStack(), new ItemStack(Items.field_151075_bm), ItemsGENERIC.itemBrewGrotesque.createStack() }), new SacrificePower(2000.0F, 20) }), EnumSet.of(RitualTraits.ONLY_IN_STROM), new Circle[] { new Circle(0, 0, 28) }).setUnlocalizedName("witchery.rite.cursesinking1");
    











    RiteRegistry.addRecipe(60, 49, new RiteCurseCreature(false, "witcherySinking", 1), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(ItemsTAGLOCK_KIT, 1, 1), ItemsGENERIC.itemBreathOfTheGoddess.createStack(), Dye.BONE_MEAL.createStack(), new ItemStack(Items.field_151075_bm), ItemsGENERIC.itemBrewOfTheDepths.createStack() }), new SacrificePower(2000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.removesinking1");
    











    RiteRegistry.addRecipe(61, 35, new RiteSummonItem(ItemsGENERIC.itemSeerStone.createStack(), RiteSummonItem.Binding.NONE), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemQuartzSphere.createStack(), new ItemStack(Blocks.field_150343_Z), ItemsGENERIC.itemHappenstanceOil.createStack() }), new SacrificePower(2000.0F, 20) }), EnumSet.of(RitualTraits.ONLY_AT_NIGHT), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.infusionseerstone");
    









    RiteRegistry.addRecipe(62, 48, new RiteCurseCreature(true, "witcheryOverheating", 1), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(ItemsTAGLOCK_KIT, 1, 1), ItemsGENERIC.itemExhaleOfTheHornedOne.createStack(), ItemsGENERIC.itemInfernalBlood.createStack(), new ItemStack(Items.field_151072_bj), ItemsGENERIC.itemBrewGrotesque.createStack() }), new SacrificePower(2000.0F, 20) }), EnumSet.of(RitualTraits.ONLY_IN_STROM), new Circle[] { new Circle(0, 0, 28) }).setUnlocalizedName("witchery.rite.curseoverheating");
    











    RiteRegistry.addRecipe(63, 49, new RiteCurseCreature(false, "witcheryOverheating", 1), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(ItemsTAGLOCK_KIT, 1, 1), ItemsGENERIC.itemBreathOfTheGoddess.createStack(), ItemsGENERIC.itemIcyNeedle.createStack(), new ItemStack(Items.field_151072_bj), ItemsGENERIC.itemBrewOfTheDepths.createStack() }), new SacrificePower(2000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.cureoverheating");
    











    RiteRegistry.addRecipe(64, 22, new RiteClimateChange(16), new SacrificeItem(new ItemStack[] { new ItemStack(Items.field_151070_bp), ItemsGENERIC.itemToeOfFrog.createStack(), ItemsGENERIC.itemBatWool.createStack(), ItemsGENERIC.itemDogTongue.createStack(), ItemsGENERIC.itemOwletsWing.createStack(), ItemsGENERIC.itemAttunedStone.createStack(), ItemsGENERIC.itemAttunedStoneCharged.createStack() }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(40, 0, 0) }).setUnlocalizedName("witchery.rite.climatechange");
    











    RiteRegistry.addRecipe(65, 12, new RiteSphereEffect(8, BlocksPERPETUAL_ICE), new SacrificeItem(new ItemStack[] { new ItemStack(Items.field_151048_u), ItemsGENERIC.itemFrozenHeart.createStack(), ItemsGENERIC.itemAttunedStoneCharged.createStack() }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.iceshell");
    







    RiteRegistry.addRecipe(66, 38, new RiteRainOfToads(5, 16, 10), new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemAttunedStoneCharged.createStack(), ItemsGENERIC.itemRedstoneSoup.createStack(), ItemsGENERIC.itemReekOfMisfortune.createStack(), ItemsGENERIC.itemToeOfFrog.createStack(), new ItemStack(Items.field_151131_as), ItemsGENERIC.itemBelladonnaFlower.createStack() }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 0, 28) }).setUnlocalizedName("witchery.rite.rainoffrogs");
    











    RiteRegistry.addRecipe(67, 4, new RiteGlyphicTransformation(), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemGypsum.createStack(), new ItemStack(ItemsARTHANA) }), new SacrificePower(1000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[0]).setUnlocalizedName("witchery.rite.glyphictransform");
    







    RiteRegistry.addRecipe(68, 7, new RiteCallCreatures(64.0F, new Class[] { EntityPig.class, EntityChicken.class, EntityCow.class, EntitySheep.class, EntityMooshroom.class, EntityWolf.class, EntityOcelot.class }), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Items.field_151117_aB), new ItemStack(Blocks.field_150407_cf), new ItemStack(Items.field_151034_e), new ItemStack(Items.field_151082_bd), new ItemStack(Items.field_151115_aP), new ItemStack(Blocks.field_150337_Q), new ItemStack(Items.field_151172_bF), new ItemStack(Items.field_151014_N) }), new SacrificePower(6000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 40, 0) }).setUnlocalizedName("witchery.rite.callbeasts");
    















    RiteRegistry.addRecipe(69, 7, new RiteSetNBT(5, "WITCManifestDuration", 150, 25), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemSpectralDust.createStack(), ItemsGENERIC.itemMellifluousHunger.createStack(), ItemsGENERIC.itemNecroStone.createStack(), new ItemStack(Items.field_151005_D), new ItemStack(ItemsARTHANA), new ItemStack(Items.field_151016_H) }), new SacrificePower(5000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 16, 0) }).setUnlocalizedName("witchery.rite.manifest");
    












    RiteRegistry.addRecipe(70, 22, new RiteForestation(20, 8, 60, Blocks.field_150345_g, 0), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Blocks.field_150345_g, 1, 0), new ItemStack(BlocksWICKER_BUNDLE), ItemsGENERIC.itemBrewOfSprouting.createStack(), ItemsGENERIC.itemBranchEnt.createStack() }), new SacrificeOptionalItem(ItemsGENERIC.itemWaystoneBound.createStack()), new SacrificePower(4000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.forestation");
    











    RiteRegistry.addRecipe(71, 22, new RiteForestation(20, 8, 60, Blocks.field_150345_g, 1), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Blocks.field_150345_g, 1, 1), new ItemStack(BlocksWICKER_BUNDLE), ItemsGENERIC.itemBrewOfSprouting.createStack(), ItemsGENERIC.itemBranchEnt.createStack() }), new SacrificeOptionalItem(ItemsGENERIC.itemWaystoneBound.createStack()), new SacrificePower(4000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.forestation").setShowInBook(false);
    











    RiteRegistry.addRecipe(72, 22, new RiteForestation(20, 8, 60, Blocks.field_150345_g, 2), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Blocks.field_150345_g, 1, 2), new ItemStack(BlocksWICKER_BUNDLE), ItemsGENERIC.itemBrewOfSprouting.createStack(), ItemsGENERIC.itemBranchEnt.createStack() }), new SacrificeOptionalItem(ItemsGENERIC.itemWaystoneBound.createStack()), new SacrificePower(4000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.forestation").setShowInBook(false);
    











    RiteRegistry.addRecipe(73, 22, new RiteForestation(20, 8, 60, Blocks.field_150345_g, 3), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Blocks.field_150345_g, 1, 3), new ItemStack(BlocksWICKER_BUNDLE), ItemsGENERIC.itemBrewOfSprouting.createStack(), ItemsGENERIC.itemBranchEnt.createStack() }), new SacrificeOptionalItem(ItemsGENERIC.itemWaystoneBound.createStack()), new SacrificePower(4000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.forestation").setShowInBook(false);
    











    RiteRegistry.addRecipe(74, 22, new RiteForestation(20, 8, 60, BlocksSAPLING, 0), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(BlocksSAPLING, 1, 0), new ItemStack(BlocksWICKER_BUNDLE), ItemsGENERIC.itemBrewOfSprouting.createStack(), ItemsGENERIC.itemBranchEnt.createStack() }), new SacrificeOptionalItem(ItemsGENERIC.itemWaystoneBound.createStack()), new SacrificePower(4000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.forestation").setShowInBook(false);
    











    RiteRegistry.addRecipe(75, 22, new RiteForestation(20, 8, 60, BlocksSAPLING, 1), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(BlocksSAPLING, 1, 1), new ItemStack(BlocksWICKER_BUNDLE), ItemsGENERIC.itemBrewOfSprouting.createStack(), ItemsGENERIC.itemBranchEnt.createStack() }), new SacrificeOptionalItem(ItemsGENERIC.itemWaystoneBound.createStack()), new SacrificePower(4000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.forestation").setShowInBook(false);
    











    RiteRegistry.addRecipe(76, 22, new RiteForestation(20, 8, 60, BlocksSAPLING, 2), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(BlocksSAPLING, 1, 2), new ItemStack(BlocksWICKER_BUNDLE), ItemsGENERIC.itemBrewOfSprouting.createStack(), ItemsGENERIC.itemBranchEnt.createStack() }), new SacrificeOptionalItem(ItemsGENERIC.itemWaystoneBound.createStack()), new SacrificePower(4000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.forestation").setShowInBook(false);
    











    RiteRegistry.addRecipe(77, 13, new RiteRaiseVolcano(8, 8), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(Blocks.field_150347_e), new ItemStack(Items.field_151064_bs), new ItemStack(Items.field_151010_B) }), new SacrificeOptionalItem(ItemsGENERIC.itemWaystoneBound.createStack()), new SacrificePower(2000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 0, 16) }).setUnlocalizedName("witchery.rite.volcano");
    










    RiteRegistry.addRecipe(78, 48, new RiteCurseCreature(true, "witcheryWakingNightmare", 1), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(ItemsTAGLOCK_KIT, 1, 1), ItemsGENERIC.itemExhaleOfTheHornedOne.createStack(), ItemsGENERIC.itemMellifluousHunger.createStack(), ItemsGENERIC.itemTormentedTwine.createStack(), new ItemStack(Items.field_151045_i), ItemsGENERIC.itemBrewGrotesque.createStack() }), new SacrificePower(10000.0F, 20) }), EnumSet.of(RitualTraits.ONLY_IN_STROM), new Circle[] { new Circle(0, 0, 28) }).setUnlocalizedName("witchery.rite.cursenightmare");
    












    RiteRegistry.addRecipe(79, 49, new RiteCurseCreature(false, "witcheryWakingNightmare", 1), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(ItemsTAGLOCK_KIT, 1, 1), ItemsGENERIC.itemBreathOfTheGoddess.createStack(), new ItemStack(Items.field_151150_bK), ItemsGENERIC.itemTormentedTwine.createStack(), ItemsGENERIC.itemBrewOfLove.createStack() }), new SacrificePower(2000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.curenightmare");
    











    RiteRegistry.addRecipe(80, 35, new RiteSummonItem(ItemsGENERIC.itemBrewOfSoaring.createStack(), RiteSummonItem.Binding.NONE), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemInfusionBase.createStack(), ItemsGENERIC.itemBroom.createStack(), new ItemStack(Items.field_151008_G), new ItemStack(ItemsARTHANA) }), new SacrificeLiving(EntityOwl.class), new SacrificePower(3000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0), new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.infusebrewsoaring");
    













    RiteRegistry.addRecipe(81, 35, new RiteSummonItem(ItemsGENERIC.itemBrewGrave.createStack(), RiteSummonItem.Binding.NONE), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemInfusionBase.createStack(), new ItemStack(Items.field_151103_aS), ItemsGENERIC.itemWeb.createStack(), ItemsGENERIC.itemNecroStone.createStack() }), new SacrificeLiving(EntityZombie.class), new SacrificePower(3000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0), new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.infusebrewgrave");
    













    RiteRegistry.addRecipe(82, 36, new RiteSummonItem(new ItemStack(ItemsSPECTRAL_STONE), RiteSummonItem.Binding.NONE), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemNecroStone.createStack(), ItemsGENERIC.itemCongealedSpirit.createStack(), ItemsGENERIC.itemCondensedFear.createStack(), ItemsGENERIC.itemSpectralDust.createStack(), new ItemStack(ItemsBOLINE) }), new SacrificePower(6000.0F, 20) }), EnumSet.of(RitualTraits.ONLY_AT_NIGHT), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.spectralstone").setConsumeNecroStone();
    












    RiteRegistry.addRecipe(83, 1, new RiteSummonSpectralStone(5), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(ItemsSPECTRAL_STONE), ItemsGENERIC.itemSpectralDust.createStack(), new ItemStack(ItemsBOLINE) }), new SacrificePower(5000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.bindspectral");
    









    RiteRegistry.addRecipe(84, 1, new RiteBindSpiritsToFetish(5), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(BlocksFETISH_SCARECROW), ItemsGENERIC.itemAttunedStone.createStack(), ItemsGENERIC.itemNecroStone.createStack(), new ItemStack(ItemsBOLINE) }), new SacrificePower(6000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.bindfetish");
    










    RiteRegistry.addRecipe(85, 1, new RiteBindSpiritsToFetish(5), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(BlocksFETISH_TREANT_IDOL), ItemsGENERIC.itemAttunedStone.createStack(), ItemsGENERIC.itemNecroStone.createStack(), new ItemStack(ItemsBOLINE) }), new SacrificePower(6000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.bindfetish").setShowInBook(false);
    










    RiteRegistry.addRecipe(86, 1, new RiteBindSpiritsToFetish(5), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(BlocksFETISH_WITCHS_LADDER), ItemsGENERIC.itemAttunedStone.createStack(), ItemsGENERIC.itemNecroStone.createStack(), new ItemStack(ItemsBOLINE) }), new SacrificePower(6000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.bindfetish").setShowInBook(false);
    










    RiteRegistry.addRecipe(87, 26, new RiteSummonCreature(EntityImp.class, false), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemRefinedEvil.createStack(), ItemsGENERIC.itemInfernalBlood.createStack(), new ItemStack(Items.field_151079_bi), ItemsGENERIC.itemAttunedStone.createStack() }), new SacrificePower(5000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 0, 28) }).setUnlocalizedName("witchery.rite.summonimp");
    










    RiteRegistry.addRecipe(88, 1, new RiteSummonItem(ItemsGENERIC.itemWaystonePlayerBound.createStack(), RiteSummonItem.Binding.ENTITY), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemWaystone.createStack(), ItemsGENERIC.itemEnderDew.createStack(), new ItemStack(Items.field_151123_aH), new ItemStack(Items.field_151126_ay) }), new SacrificePower(500.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.bindwaystonetoplayer");
    










    RiteRegistry.addRecipe(89, 1, new RiteSummonItem(ItemsGENERIC.itemWaystonePlayerBound.createStack(), RiteSummonItem.Binding.ENTITY), new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemWaystone.createStack(), ItemsGENERIC.itemEnderDew.createStack(), new ItemStack(Items.field_151123_aH), ItemsGENERIC.itemIcyNeedle.createStack(), ItemsGENERIC.itemAttunedStoneCharged.createStack() }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.bindwaystonetoplayer");
    









    RiteRegistry.addRecipe(90, 1, new RiteSummonItem(new ItemStack(BlocksSTATUE_OF_WORSHIP), RiteSummonItem.Binding.PLAYER), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(BlocksSTATUE_OF_WORSHIP), ItemsGENERIC.itemBelladonnaFlower.createStack(), new ItemStack(Blocks.field_150328_O), new ItemStack(Blocks.field_150327_N) }), new SacrificePower(4000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.bindstatuetoplayer");
    










    RiteRegistry.addRecipe(91, 5, new RiteTeleportToWaystone(3), new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemWaystonePlayerBound.createStack() }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 16, 0) }).setUnlocalizedName("witchery.rite.teleporttowaystone");
    





    RiteRegistry.addRecipe(92, 48, new RiteCurseOfTheWolf(true), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(ItemsTAGLOCK_KIT, 1, 1), ItemsGENERIC.itemExhaleOfTheHornedOne.createStack(), new ItemStack(BlocksWOLFHEAD, 1, 1), ItemsGENERIC.itemWolfsbane.createStack(), new ItemStack(Items.field_151045_i), ItemsGENERIC.itemBrewGrotesque.createStack() }), new SacrificePower(10000.0F, 20) }), EnumSet.of(RitualTraits.ONLY_AT_NIGHT), new Circle[] { new Circle(0, 0, 28) }).setUnlocalizedName("witchery.rite.wolfcurse.book");
    












    RiteRegistry.addRecipe(93, 49, new RiteCurseOfTheWolf(false), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(ItemsTAGLOCK_KIT, 1, 1), ItemsGENERIC.itemBreathOfTheGoddess.createStack(), new ItemStack(ItemsSILVER_SWORD), ItemsGENERIC.itemWolfsbane.createStack(), new ItemStack(Items.field_151045_i), ItemsGENERIC.itemBrewOfLove.createStack() }), new SacrificePower(10000.0F, 20) }), EnumSet.of(RitualTraits.ONLY_AT_NIGHT), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.wolfcure.book");
    












    RiteRegistry.addRecipe(94, 49, new com.emoniph.witchery.ritual.rites.RiteRemoveVampirism(), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(ItemsTAGLOCK_KIT, 1, 1), ItemsGENERIC.itemBreathOfTheGoddess.createStack(), new ItemStack(ItemsSILVER_SWORD), new ItemStack(ItemsSEEDS_GARLIC), new ItemStack(Items.field_151045_i), ItemsGENERIC.itemBrewOfLove.createStack() }), new SacrificePower(10000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(16, 0, 0) }).setUnlocalizedName("witchery.rite.vampirecure.book");
    












    RiteRegistry.addRecipe(95, 35, new RiteSummonItem(new ItemStack(ItemsMIRROR), RiteSummonItem.Binding.NONE), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { ItemsGENERIC.itemBrewOfHollowTears.createStack(), new ItemStack(Items.field_151043_k), new ItemStack(Blocks.field_150410_aZ) }), new SacrificePower(2000.0F, 20), new SacrificeLiving(EntityDemon.class) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(28, 0, 0) }).setUnlocalizedName("witchery.rite.infusionmirror");
    










    RiteRegistry.addRecipe(96, 28, new RiteSummonCreature(EntityReflection.class, false), new SacrificeMultiple(new Sacrifice[] { new SacrificeItem(new ItemStack[] { new ItemStack(ItemsMIRROR), ItemsGENERIC.itemEnderDew.createStack(), new ItemStack(Items.field_151065_br), ItemsGENERIC.itemQuartzSphere.createStack() }), new SacrificePower(5000.0F, 20) }), EnumSet.noneOf(RitualTraits.class), new Circle[] { new Circle(0, 0, 40) }).setUnlocalizedName("witchery.rite.summonreflection");
    
















    double DEFAULT_FORCE_CHANCE = 0.05D;
    
    PredictionManager.instance().addPrediction(new PredictionFight(1, 13, 0.05D, "witchery.prediction.zombie", EntityZombie.class, false));
    PredictionManager.instance().addPrediction(new PredictionArrow(2, 13, 0.05D, "witchery.prediction.arrowhit"));
    PredictionManager.instance().addPrediction(new PredictionFight(3, 3, 0.05D, "witchery.prediction.ent", EntityEnt.class, false));
    PredictionManager.instance().addPrediction(new PredictionFall(4, 13, 0.05D, "witchery.prediction.fall"));
    PredictionManager.instance().addPrediction(new PredictionMultiMine(5, 8, 0.05D, "witchery.prediction.iron", 1212, 0.01D, Blocks.field_150366_p, new ItemStack(Blocks.field_150366_p), 8, 20));
    PredictionManager.instance().addPrediction(new PredictionMultiMine(6, 3, 0.05D, "witchery.prediction.diamond", 1208, 0.01D, Blocks.field_150348_b, new ItemStack(Items.field_151045_i), 1, 1));
    PredictionManager.instance().addPrediction(new PredictionMultiMine(7, 3, 0.05D, "witchery.prediction.emerald", 1208, 0.01D, Blocks.field_150348_b, new ItemStack(Items.field_151166_bC), 1, 1));
    PredictionManager.instance().addPrediction(new PredictionBuriedTreasure(8, 2, 0.05D, "witchery.prediction.treasure", 1210, 0.01D, "mineshaftCorridor"));
    PredictionManager.instance().addPrediction(new PredictionFallInLove(9, 2, 0.05D, "witchery.prediction.love", 1210, 0.01D));
    PredictionManager.instance().addPrediction(new PredictionFight(10, 2, 0.05D, "witchery.prediction.bababad", EntityBabaYaga.class, false));
    PredictionManager.instance().addPrediction(new PredictionFight(11, 2, 0.05D, "witchery.prediction.babagood", EntityBabaYaga.class, true));
    PredictionManager.instance().addPrediction(new PredictionFight(12, 3, 0.05D, "witchery.prediction.friend", EntityWolf.class, true));
    PredictionManager.instance().addPrediction(new PredictionRescue(13, 13, 0.05D, "witchery.prediction.rescued", 1208, 0.01D, EntityOwl.class));
    PredictionManager.instance().addPrediction(new PredictionRescue(14, 13, 0.05D, "witchery.prediction.rescued", 1208, 0.01D, EntityWolf.class));
    PredictionManager.instance().addPrediction(new PredictionWet(15, 13, 0.05D, "witchery.prediction.wet"));
    PredictionManager.instance().addPrediction(new PredictionNetherTrip(16, 3, 0.05D, "witchery.prediction.tothenether"));
    PredictionManager.instance().addPrediction(new PredictionMultiMine(17, 13, 0.05D, "witchery.prediction.coal", 1208, 0.01D, Blocks.field_150365_q, new ItemStack(Items.field_151044_h), 10, 20));
  }
  
  public void init() {
    ItemStack dust = ItemsGENERIC.itemSilverDust.createStack();
    
    List<ItemStack> silverDust = OreDictionary.getOres("dustSilver");
    if ((silverDust != null) && (!silverDust.isEmpty())) {
      GameRegistry.addShapelessRecipe(((ItemStack)silverDust.get(0)).func_77946_l(), new Object[] { dust, dust, dust, dust, dust, dust, dust, dust, dust });
    }
    

    List<ItemStack> silverIngots = OreDictionary.getOres("ingotSilver");
    if ((silverIngots != null) && (!silverIngots.isEmpty()))
    {
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemsSILVER_SWORD), new Object[] { "s", "s", "b", Character.valueOf('s'), "ingotSilver", Character.valueOf('b'), new ItemStack(Items.field_151010_B) }));
      






      GameRegistry.addRecipe(new ShapedOreRecipe(ItemsGENERIC.itemBoltSilver.createStack(6), new Object[] { " s ", "bbb", "bbb", Character.valueOf('s'), "ingotSilver", Character.valueOf('b'), ItemsGENERIC.itemBoltStake.createStack() }));
      






      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlocksWOLFTRAP), new Object[] { "sns", "w#w", "sns", Character.valueOf('#'), new ItemStack(BlocksBEARTRAP), Character.valueOf('s'), "ingotSilver", Character.valueOf('n'), ItemsGENERIC.itemNullCatalyst.createStack(), Character.valueOf('w'), ItemsGENERIC.itemWolfsbane.createStack() }));
      








      Item[][] hunterItems = { { ItemsHUNTER_BOOTS, ItemsHUNTER_BOOTS_SILVERED }, { ItemsHUNTER_LEGS, ItemsHUNTER_LEGS_SILVERED }, { ItemsHUNTER_COAT, ItemsHUNTER_COAT_SILVERED }, { ItemsHUNTER_HAT, ItemsHUNTER_HAT_SILVERED } };
      



      for (Item[] hunterItem : hunterItems) {
        ShapedOreRecipe recipe = new ShapedOreRecipe(new ItemStack(hunterItem[1]), new Object[] { "dwd", "w#w", "dsd", Character.valueOf('#'), new ItemStack(hunterItem[0]), Character.valueOf('s'), new ItemStack(Items.field_151007_F), Character.valueOf('w'), ItemsGENERIC.itemWolfsbane.createStack(), Character.valueOf('d'), "ingotSilver" })
        {



          public ItemStack func_77572_b(InventoryCrafting inv)
          {


            ItemStack result = func_77571_b().func_77946_l();
            for (int i = 0; i < inv.func_70302_i_(); i++) {
              ItemStack material = inv.func_70301_a(i);
              if ((material != null) && (material.func_77942_o())) {
                result.func_77982_d((NBTTagCompound)field_77990_d.func_74737_b());
              }
            }
            return result;
          }
        };
        GameRegistry.addRecipe(recipe);
      }
    }
  }
  
  public void postInit() {
    if (instancesmeltAllSaplingsToWoodAsh) {
      ArrayList<ItemStack> saplingTypes = OreDictionary.getOres("treeSapling");
      for (ItemStack stack : saplingTypes) {
        GameRegistry.addSmelting(stack, ItemsGENERIC.itemAshWood.createStack(), 0.0F);
      }
    }
  }
  
  private void addPlantMineRecipe(int damageValue, ItemStack plant, ItemStack brew) {
    GameRegistry.addRecipe(new ItemStack(BlocksTRAPPED_PLANT, 4, damageValue), new Object[] { "ccc", "bab", Character.valueOf('a'), plant, Character.valueOf('b'), new ItemStack(Blocks.field_150456_au), Character.valueOf('c'), brew });
  }
  






  private static ShapedRecipes getShapedRecipe(ItemStack par1ItemStack, Object... par2ArrayOfObj)
  {
    String s = "";
    int i = 0;
    int j = 0;
    int k = 0;
    
    if ((par2ArrayOfObj[i] instanceof String[]))
    {
      String[] astring = (String[])(String[])par2ArrayOfObj[(i++)];
      
      for (int l = 0; l < astring.length; l++)
      {
        String s1 = astring[l];
        k++;
        j = s1.length();
        s = s + s1;
      }
    }
    else
    {
      while ((par2ArrayOfObj[i] instanceof String))
      {
        String s2 = (String)par2ArrayOfObj[(i++)];
        k++;
        j = s2.length();
        s = s + s2;
      }
    }
    


    for (HashMap hashmap = new HashMap(); i < par2ArrayOfObj.length; i += 2)
    {
      Character character = (Character)par2ArrayOfObj[i];
      ItemStack itemstack1 = null;
      
      if ((par2ArrayOfObj[(i + 1)] instanceof Item))
      {
        itemstack1 = new ItemStack((Item)par2ArrayOfObj[(i + 1)]);
      }
      else if ((par2ArrayOfObj[(i + 1)] instanceof Block))
      {
        itemstack1 = new ItemStack((Block)par2ArrayOfObj[(i + 1)], 1, 32767);
      }
      else if ((par2ArrayOfObj[(i + 1)] instanceof ItemStack))
      {
        itemstack1 = (ItemStack)par2ArrayOfObj[(i + 1)];
      }
      
      hashmap.put(character, itemstack1);
    }
    
    ItemStack[] aitemstack = new ItemStack[j * k];
    
    for (int i1 = 0; i1 < j * k; i1++)
    {
      char c0 = s.charAt(i1);
      
      if (hashmap.containsKey(Character.valueOf(c0)))
      {
        aitemstack[i1] = ((ItemStack)hashmap.get(Character.valueOf(c0))).func_77946_l();
      }
      else
      {
        aitemstack[i1] = null;
      }
    }
    
    ShapedRecipes shapedrecipes = new ShapedRecipes(j, k, aitemstack, par1ItemStack);
    
    return shapedrecipes;
  }
}
