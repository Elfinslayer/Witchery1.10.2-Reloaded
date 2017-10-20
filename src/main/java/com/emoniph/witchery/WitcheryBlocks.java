package com.emoniph.witchery;

import com.emoniph.witchery.blocks.BlockAlluringSkull;
import com.emoniph.witchery.blocks.BlockAltar;
import com.emoniph.witchery.blocks.BlockAreaMarker;
import com.emoniph.witchery.blocks.BlockAreaMarker.TileEntityAreaCurseProtect;
import com.emoniph.witchery.blocks.BlockAreaMarker.TileEntityAreaTeleportPullProtect;
import com.emoniph.witchery.blocks.BlockBarrier;
import com.emoniph.witchery.blocks.BlockBeartrap;
import com.emoniph.witchery.blocks.BlockBloodCrucible;
import com.emoniph.witchery.blocks.BlockBloodRose;
import com.emoniph.witchery.blocks.BlockBloodedWool;
import com.emoniph.witchery.blocks.BlockBramble;
import com.emoniph.witchery.blocks.BlockBrazier;
import com.emoniph.witchery.blocks.BlockCandelabra;
import com.emoniph.witchery.blocks.BlockChalice;
import com.emoniph.witchery.blocks.BlockCircle;
import com.emoniph.witchery.blocks.BlockCircleGlyph;
import com.emoniph.witchery.blocks.BlockCoffin;
import com.emoniph.witchery.blocks.BlockCotton;
import com.emoniph.witchery.blocks.BlockCritterSnare;
import com.emoniph.witchery.blocks.BlockCrystalBall;
import com.emoniph.witchery.blocks.BlockDaylightCollector;
import com.emoniph.witchery.blocks.BlockDemonHeart;
import com.emoniph.witchery.blocks.BlockDisease;
import com.emoniph.witchery.blocks.BlockDistillery;
import com.emoniph.witchery.blocks.BlockDreamCatcher;
import com.emoniph.witchery.blocks.BlockEmberMoss;
import com.emoniph.witchery.blocks.BlockFetish;
import com.emoniph.witchery.blocks.BlockFlowingSpirit;
import com.emoniph.witchery.blocks.BlockForce;
import com.emoniph.witchery.blocks.BlockFumeFunnel;
import com.emoniph.witchery.blocks.BlockGarlicGarland;
import com.emoniph.witchery.blocks.BlockGlintWeed;
import com.emoniph.witchery.blocks.BlockGlowGlobe;
import com.emoniph.witchery.blocks.BlockGrassper;
import com.emoniph.witchery.blocks.BlockInfinityEgg;
import com.emoniph.witchery.blocks.BlockKettle;
import com.emoniph.witchery.blocks.BlockLeapingLily;
import com.emoniph.witchery.blocks.BlockLeechChest;
import com.emoniph.witchery.blocks.BlockLight;
import com.emoniph.witchery.blocks.BlockMirror;
import com.emoniph.witchery.blocks.BlockMirrorWall;
import com.emoniph.witchery.blocks.BlockPerpertualIceStairs;
import com.emoniph.witchery.blocks.BlockPerpetualIce;
import com.emoniph.witchery.blocks.BlockPerpetualIceDoor;
import com.emoniph.witchery.blocks.BlockPerpetualIceFence;
import com.emoniph.witchery.blocks.BlockPerpetualIceGate;
import com.emoniph.witchery.blocks.BlockPerpetualIcePressurePlate;
import com.emoniph.witchery.blocks.BlockPerpetualIceSlab;
import com.emoniph.witchery.blocks.BlockPitDirt;
import com.emoniph.witchery.blocks.BlockPitGrass;
import com.emoniph.witchery.blocks.BlockPlacedItem;
import com.emoniph.witchery.blocks.BlockPlantMine;
import com.emoniph.witchery.blocks.BlockPoppetShelf;
import com.emoniph.witchery.blocks.BlockRefillingChest;
import com.emoniph.witchery.blocks.BlockShadedGlass;
import com.emoniph.witchery.blocks.BlockSilverVat;
import com.emoniph.witchery.blocks.BlockSnowSlab;
import com.emoniph.witchery.blocks.BlockSnowStairs;
import com.emoniph.witchery.blocks.BlockSpanishMoss;
import com.emoniph.witchery.blocks.BlockSpinningWheel;
import com.emoniph.witchery.blocks.BlockSpiritPortal;
import com.emoniph.witchery.blocks.BlockStatueGoddess;
import com.emoniph.witchery.blocks.BlockStatueOfWorship;
import com.emoniph.witchery.blocks.BlockStatueWerewolf;
import com.emoniph.witchery.blocks.BlockStockade;
import com.emoniph.witchery.blocks.BlockTormentPortal;
import com.emoniph.witchery.blocks.BlockVoidBramble;
import com.emoniph.witchery.blocks.BlockWickerBundle;
import com.emoniph.witchery.blocks.BlockWitchCrop;
import com.emoniph.witchery.blocks.BlockWitchDoor;
import com.emoniph.witchery.blocks.BlockWitchLeaves;
import com.emoniph.witchery.blocks.BlockWitchLog;
import com.emoniph.witchery.blocks.BlockWitchSapling;
import com.emoniph.witchery.blocks.BlockWitchWood;
import com.emoniph.witchery.blocks.BlockWitchWoodSlab;
import com.emoniph.witchery.blocks.BlockWitchWoodStairs;
import com.emoniph.witchery.blocks.BlockWitchesOven;
import com.emoniph.witchery.blocks.BlockWolfHead;
import com.emoniph.witchery.brewing.BlockBrew;
import com.emoniph.witchery.brewing.BlockBrewGas;
import com.emoniph.witchery.brewing.BlockBrewLiquidEffect;
import com.emoniph.witchery.brewing.BlockButtonBase;
import com.emoniph.witchery.brewing.BlockCauldron;
import com.emoniph.witchery.brewing.BlockDoorBase;
import com.emoniph.witchery.brewing.BlockLeverBase;
import com.emoniph.witchery.brewing.BlockPressurePlateBase;
import com.emoniph.witchery.brewing.BlockSlurp;
import com.emoniph.witchery.brewing.BlockWitchCactus;
import com.emoniph.witchery.brewing.BlockWitchLilyPad;
import com.emoniph.witchery.brewing.BlockWitchVine;
import com.emoniph.witchery.brewing.BlockWitchWeb;
import com.emoniph.witchery.worldgen.WorldHandlerVillageDistrict.Wall.BlockVillageWallGen;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPressurePlate.Sensitivity;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;




public final class WitcheryBlocks
{
  public final BlockWitchCrop CROP_BELLADONNA = (BlockWitchCrop)new BlockWitchCrop(false).func_149663_c("witchery:belladonna").func_149658_d("witchery:belladonna");
  
  public final BlockWitchCrop CROP_MANDRAKE = (BlockWitchCrop)new BlockWitchCrop(false).func_149663_c("witchery:mandrake").func_149658_d("witchery:mandrake");
  
  public final BlockWitchCrop CROP_ARTICHOKE = (BlockWitchCrop)new BlockWitchCrop(true).func_149663_c("witchery:artichoke").func_149658_d("witchery:artichoke");
  
  public final BlockWitchCrop CROP_SNOWBELL = (BlockWitchCrop)new BlockWitchCrop(false).func_149663_c("witchery:snowbell").func_149658_d("witchery:snowbell");
  
  public final BlockWitchCrop CROP_WORMWOOD = (BlockWitchCrop)new BlockWitchCrop(false).func_149663_c("witchery:wormwood").func_149658_d("witchery:wormwood");
  
  public final BlockWitchCrop CROP_MINDRAKE = (BlockWitchCrop)new BlockWitchCrop(false, 4, false).func_149663_c("witchery:mindrake").func_149658_d("witchery:mindrake");
  
  public final BlockWitchCrop CROP_WOLFSBANE = (BlockWitchCrop)new BlockWitchCrop(false, 7, false).func_149663_c("witchery:wolfsbane").func_149658_d("witchery:wolfsbane");
  
  public final BlockWitchCrop CROP_GARLIC = (BlockWitchCrop)new BlockWitchCrop(false, 5, true).func_149663_c("witchery:garlicplant").func_149658_d("witchery:garlic");
  

  public final Block SAPLING = new BlockWitchSapling().func_149663_c("witchery:witchsapling").func_149658_d("witchery:sapling");
  
  public final Block LOG = new BlockWitchLog().func_149663_c("witchery:witchlog").func_149658_d("witchery:log");
  
  public final Block LEAVES = new BlockWitchLeaves().func_149663_c("witchery:witchleaves").func_149658_d("witchery:leaves");
  

  public final Block VOID_BRAMBLE = new BlockVoidBramble().func_149663_c("witchery:voidbramble").func_149658_d("witchery:voidBramble");
  
  public final Block BRAMBLE = new BlockBramble().func_149663_c("witchery:bramble").func_149658_d("witchery:bramble");
  
  public final Block GLINT_WEED = new BlockGlintWeed().func_149663_c("witchery:glintweed").func_149658_d("witchery:glintWeed");
  
  public final Block SPANISH_MOSS = new BlockSpanishMoss().func_149663_c("witchery:spanishmoss").func_149658_d("witchery:spanishMoss");
  
  public final Block LEAPING_LILY = new BlockLeapingLily().func_149663_c("witchery:leapinglily").func_149658_d("waterlily");
  
  public final Block TRAPPED_PLANT = new BlockPlantMine().func_149663_c("witchery:plantmine").func_149658_d("flower_rose");
  
  public final Block EMBER_MOSS = new BlockEmberMoss().func_149663_c("witchery:embermoss").func_149658_d("witchery:embermoss");
  
  public final Block CRITTER_SNARE = new BlockCritterSnare().func_149663_c("witchery:crittersnare").func_149658_d("witchery:critterSnare");
  
  public final Block GRASSPER = new BlockGrassper().func_149663_c("witchery:grassper").func_149658_d("witchery:grassper");
  
  public final Block BLOOD_ROSE = new BlockBloodRose().func_149663_c("witchery:bloodrose").func_149658_d("witchery:bloodrose");
  
  public final Block WISPY_COTTON = new BlockCotton().func_149663_c("witchery:somniancotton").func_149658_d("witchery:somnianCotton");
  

  public final Block WEB = new BlockWitchWeb().func_149663_c("witchery:web").func_149658_d("web");
  public final Block VINE = new BlockWitchVine().func_149663_c("witchery:vine").func_149658_d("vine");
  public final Block CACTUS = new BlockWitchCactus().func_149663_c("cactus").func_149658_d("cactus");
  public final Block LILY = new BlockWitchLilyPad().func_149663_c("lilypad").func_149658_d("waterlily");
  

  public final Block DEMON_HEART = new BlockDemonHeart().func_149663_c("witchery:demonheart").func_149658_d("witchery:demonHeart");
  

  public final Block WOLFHEAD = new BlockWolfHead().func_149663_c("witchery:wolfhead").func_149658_d("witchery:wolfhead");
  

  public final Block PLANKS = new BlockWitchWood().func_149663_c("witchery:witchwood").func_149658_d("witchery:planks");
  
  public final Block STAIRS_ROWAN = new BlockWitchWoodStairs(PLANKS, 0, 5, 20).func_149663_c("witchery:stairswoodrowan");
  
  public final Block STAIRS_ALDER = new BlockWitchWoodStairs(PLANKS, 1, 5, 20).func_149663_c("witchery:stairswoodalder");
  
  public final Block STAIRS_HAWTHORN = new BlockWitchWoodStairs(PLANKS, 2, 1, 1).func_149663_c("witchery:stairswoodhawthorn");
  
  public final Block WOOD_SLAB_SINGLE = new BlockWitchWoodSlab(false).func_149663_c("witchery:witchwoodslab");
  public final Block WOOD_SLAB_DOUBLE = new BlockWitchWoodSlab(true).func_149663_c("witchery:witchwooddoubleslab");
  

  public final Block DOOR_ROWAN = new BlockWitchDoor().func_149663_c("witchery:rowanwooddoor").func_149658_d("witchery:door_rowan").func_149711_c(5.0F);
  
  public final Block DOOR_ALDER = new BlockWitchDoor().func_149663_c("witchery:alderwooddoor").func_149658_d("door_wood").func_149711_c(3.0F);
  

  public final Block CHALICE = new BlockChalice().func_149663_c("witchery:chalice").func_149658_d("witchery:chalice");
  
  public final Block CANDELABRA = new BlockCandelabra().func_149663_c("witchery:candelabra").func_149658_d("witchery:candelabra");
  
  public final Block PLACED_ITEMSTACK = new BlockPlacedItem().func_149663_c("witchery:placeditem");
  public final Block ALLURING_SKULL = new BlockAlluringSkull().func_149663_c("witchery:alluringskull").func_149658_d("witchery:alluringSkull");
  
  public final Block WICKER_BUNDLE = new BlockWickerBundle().func_149663_c("witchery:wickerbundle").func_149658_d("witchery:wicker_block");
  
  public final Block GLOW_GLOBE = new BlockGlowGlobe().func_149663_c("witchery:glowglobe").func_149658_d("witchery:glowGlobe");
  
  public final Block STATUE_GODDESS = new BlockStatueGoddess().func_149663_c("witchery:statuegoddess").func_149658_d("witchery:statueGoddess");
  

  public final Block STOCKADE = new BlockStockade(false).func_149663_c("witchery:stockade").func_149658_d("log_oak");
  
  public final Block STOCKADE_ICE = new BlockStockade(true).func_149663_c("witchery:icestockade").func_149658_d("ice");
  
  public final Block PERPETUAL_ICE = new BlockPerpetualIce().func_149663_c("witchery:perpetualice").func_149658_d("ice");
  
  public final Block PERPETUAL_ICE_DOOR = new BlockPerpetualIceDoor().func_149663_c("witchery:icedoor").func_149658_d("witchery:icedoor");
  
  public final Block PERPETUAL_ICE_STAIRS = new BlockPerpertualIceStairs(PERPETUAL_ICE, 0).func_149663_c("witchery:icestairs").func_149658_d("ice");
  
  public final Block PERPETUAL_ICE_SLAB_SINGLE = new BlockPerpetualIceSlab(false).func_149663_c("witchery:iceslab");
  
  public final Block PERPETUAL_ICE_SLAB_DOUBLE = new BlockPerpetualIceSlab(true).func_149663_c("witchery:icedoubleslab");
  
  public final Block PERPETUAL_ICE_FENCE = new BlockPerpetualIceFence("ice").func_149663_c("witchery:icefence").func_149658_d("ice");
  
  public final Block PERPETUAL_ICE_FENCE_GATE = new BlockPerpetualIceGate().func_149663_c("witchery:icefencegate").func_149658_d("ice");
  
  public final Block PERPETUAL_ICE_PRESSURE_PLATE = new BlockPerpetualIcePressurePlate(Material.field_151588_w).func_149663_c("witchery:icepressureplate").func_149658_d("ice");
  
  public final Block SNOW_PRESSURE_PLATE = new BlockPerpetualIcePressurePlate(Material.field_151597_y).func_149663_c("witchery:snowpressureplate").func_149658_d("snow");
  

  public final Block SNOW_STAIRS = new BlockSnowStairs(Blocks.field_150433_aE, 0).func_149663_c("witchery:snowstairs").func_149658_d("snow");
  
  public final Block SNOW_SLAB_SINGLE = new BlockSnowSlab(false).func_149663_c("witchery:snowslab");
  public final Block SNOW_SLAB_DOUBLE = new BlockSnowSlab(true).func_149663_c("witchery:snowdoubleslab");
  
  public final Block INFINITY_EGG = new BlockInfinityEgg().func_149663_c("witchery:infinityegg").func_149658_d("witchery:dragon_egg");
  

  public final BlockSlurp SLURP = (BlockSlurp)new BlockSlurp().func_149663_c("witchery:slurp").func_149658_d("witchery:barrier");
  

  public final Block PIT_DIRT = new BlockPitDirt().func_149663_c("witchery:pitdirt").func_149658_d("dirt");
  public final Block PIT_GRASS = new BlockPitGrass().func_149663_c("witchery:pitgrass").func_149658_d("grass");
  
  public final Block GARLIC_GARLAND = new BlockGarlicGarland().func_149663_c("witchery:garlicgarland").func_149658_d("witchery:garlicgarlanditem");
  public final Block BLOODED_WOOL = new BlockBloodedWool().func_149663_c("witchery:bloodedwool").func_149658_d("witchery:bloodedwool");
  public final Block SHADED_GLASS = new BlockShadedGlass(false).func_149663_c("witchery:shadedglass").func_149658_d("witchery:shadedglassoff");
  public final Block SHADED_GLASS_ON = new BlockShadedGlass(true).func_149663_c("witchery:shadedglass_active").func_149658_d("witchery:shadedglass");
  
  public final Block MIRROR_WALL = new BlockMirrorWall().func_149663_c("witchery:mirrorwall").func_149658_d("witchery:brew");
  

  public final Block REFILLING_CHEST = new BlockRefillingChest().func_149663_c("witchery:refillingchest").func_149658_d("textures/entity/chest/normal.png");
  
  public final Block FORCE = new BlockForce(true).func_149663_c("witchery:force").func_149658_d("witchery:force");
  
  public final Block TORMENT_STONE = new BlockForce(false).func_149663_c("witchery:tormentstone").func_149658_d("mycelium_top");
  
  public final Block BARRIER = new BlockBarrier().func_149663_c("witchery:barrier").func_149658_d("witchery:barrier");
  
  public final Block LEECH_CHEST = new BlockLeechChest().func_149663_c("witchery:leechchest").func_149658_d("witchery:leechChest");
  
  public final Block ALTAR = new BlockAltar().func_149663_c("witchery:altar").func_149658_d("witchery:altar");
  
  public final BlockKettle KETTLE = (BlockKettle)new BlockKettle().func_149663_c("witchery:kettle").func_149658_d("witchery:kettle");
  
  public final Block POPPET_SHELF = new BlockPoppetShelf().func_149663_c("witchery:poppetshelf").func_149658_d("witchery:poppetShelf");
  
  public final BlockDreamCatcher DREAM_CATCHER = (BlockDreamCatcher)new BlockDreamCatcher().func_149663_c("witchery:dreamcatcher").func_149658_d("witchery:dreamCatcher");
  
  public final Block CRYSTAL_BALL = new BlockCrystalBall().func_149663_c("witchery:crystalball").func_149658_d("witchery:crystalball");
  
  public final BlockSpiritPortal SPIRIT_PORTAL = (BlockSpiritPortal)new BlockSpiritPortal(Blocks.field_150433_aE).func_149663_c("witchery:spiritportal").func_149658_d("portal");
  
  public final Block TORMENT_PORTAL = new BlockTormentPortal().func_149663_c("witchery:tormentportal").func_149658_d("portal");
  
  public final Block SPINNING_WHEEL = new BlockSpinningWheel().func_149663_c("witchery:spinningwheel").func_149658_d("witchery:spinningwheel");
  
  public final Block BRAZIER = new BlockBrazier().func_149663_c("witchery:brazier").func_149658_d("witchery:brazier");
  

  public final Block OVEN_IDLE = new BlockWitchesOven(false).func_149663_c("witchery:witchesovenidle").func_149658_d("witchery:witchesOven");
  
  public final Block OVEN_BURNING = new BlockWitchesOven(true).func_149663_c("witchery:witchesovenburning").func_149658_d("witchery:witchesOven");
  
  public final Block OVEN_FUMEFUNNEL = new BlockFumeFunnel(false).func_149663_c("witchery:fumefunnel").func_149658_d("witchery:fumefunnel");
  
  public final Block OVEN_FUMEFUNNEL_FILTERED = new BlockFumeFunnel(true).func_149663_c("witchery:filteredfumefunnel").func_149658_d("witchery:fumefunnel");
  

  public final Block DISTILLERY_IDLE = new BlockDistillery(false).func_149663_c("witchery:distilleryidle").func_149658_d("witchery:distillery");
  
  public final Block DISTILLERY_BURNING = new BlockDistillery(true).func_149663_c("witchery:distilleryburning").func_149658_d("witchery:distillery");
  

  public final Block FETISH_SCARECROW = new BlockFetish().func_149663_c("witchery:scarecrow").func_149658_d("witchery:scarecrow");
  
  public final Block FETISH_TREANT_IDOL = new BlockFetish().func_149663_c("witchery:trent").func_149658_d("witchery:trent");
  
  public final Block FETISH_WITCHS_LADDER = new BlockFetish().func_149663_c("witchery:witchsladder").func_149658_d("witchery:witchsladder");
  

  public final Block DECURSE_TELEPORT = new BlockAreaMarker(BlockAreaMarker.TileEntityAreaTeleportPullProtect.class).func_149663_c("witchery:decurseteleport").func_149658_d("witchery:statuemandrake");
  
  public final Block DECURSE_DIRECTED = new BlockAreaMarker(BlockAreaMarker.TileEntityAreaCurseProtect.class).func_149663_c("witchery:decursedirected").func_149658_d("witchery:statuewolf");
  

  public final Block STATUE_OF_WORSHIP = new BlockStatueOfWorship().func_149663_c("witchery:statueofworship").func_149658_d("stone");
  

  public final BlockCauldron CAULDRON = (BlockCauldron)new BlockCauldron().func_149663_c("witchery:cauldron").func_149658_d("witchery:cauldron");
  

  public final Block WOLF_ALTAR = new BlockStatueWerewolf().func_149663_c("witchery:wolfaltar").func_149658_d("stone");
  public final Block SILVER_VAT = new BlockSilverVat().func_149663_c("witchery:silvervat").func_149658_d("stone");
  public final Block BEARTRAP = new BlockBeartrap(false).func_149663_c("witchery:beartrap").func_149658_d("iron_block");
  public final Block WOLFTRAP = new BlockBeartrap(true).func_149663_c("witchery:wolftrap").func_149658_d("iron_block");
  
  public final Block WALLGEN = new WorldHandlerVillageDistrict.Wall.BlockVillageWallGen().func_149663_c("witchery:wallgen").func_149658_d("iron_block");
  public final BlockCoffin COFFIN = (BlockCoffin)new BlockCoffin().func_149663_c("witchery:coffinblock").func_149658_d("coal_block");
  public final BlockLight LIGHT = (BlockLight)new BlockLight().func_149663_c("witchery:light");
  public final Block DAYLIGHT_COLLECTOR = new BlockDaylightCollector().func_149663_c("witchery:daylightcollector").func_149658_d("witchery:daylightcollector");
  public final Block BLOOD_CRUCIBLE = new BlockBloodCrucible().func_149663_c("witchery:bloodcrucible").func_149658_d("stone");
  
  public final BlockMirror MIRROR = (BlockMirror)new BlockMirror(false).func_149663_c("witchery:mirrorblock").func_149658_d("gold_block");
  public final BlockMirror MIRROR_UNBREAKABLE = (BlockMirror)new BlockMirror(true).func_149663_c("witchery:mirrorblock2").func_149658_d("gold_block");
  

  public final Block CIRCLE = new BlockCircle().func_149663_c("witchery:circle").func_149658_d("witchery:heartGlyph");
  
  public final Block GLYPH_RITUAL = new BlockCircleGlyph(0, false).func_149663_c("witchery:circleglyphritual").func_149658_d("witchery:circleglyph");
  
  public final Block GLYPH_OTHERWHERE = new BlockCircleGlyph(1, false).func_149663_c("witchery:circleglyphotherwhere").func_149658_d("witchery:circleglyph");
  
  public final Block GLYPH_INFERNAL = new BlockCircleGlyph(2, false).func_149663_c("witchery:circleglyphinfernal").func_149658_d("witchery:circleglyph");
  


  public final Block FLOWING_SPIRIT = new BlockFlowingSpirit(FluidsFLOWING_SPIRIT, new PotionEffect(field_76428_lfield_76415_H, 100, 1), new PotionEffect(field_76437_tfield_76415_H, 300, 1), true, true).func_149663_c("witchery:spiritflowing").func_149658_d("witchery:flowspirit");
  


  public final Block HOLLOW_TEARS = new BlockFlowingSpirit(FluidsHOLLOW_TEARS, new PotionEffect(field_76437_tfield_76415_H, 100, 1), new PotionEffect(field_76428_lfield_76415_H, 100, 1), false, false).func_149663_c("witchery:hollowtears").func_149658_d("witchery:tears");
  


  public final Block DISEASE = new BlockDisease(FluidsDISEASE).func_149663_c("witchery:disease").func_149658_d("witchery:disease");
  

  public final Block BREW = new BlockBrew(FluidsBREW).func_149663_c("witchery:brew").func_149658_d("witchery:brew");
  

  public final Block BREW_GAS = new BlockBrewGas().func_149663_c("witchery:brewgas").func_149658_d("witchery:brewgas");
  
  public final Block BREW_LIQUID = new BlockBrewLiquidEffect().func_149663_c("witchery:brewliquid").func_149658_d("witchery:brew");
  


  public final BlockLeverBase CURSED_LEVER = (BlockLeverBase)new BlockLeverBase().func_149663_c("witchery:clever");
  

  public final BlockDoorBase CURSED_WOODEN_DOOR = (BlockDoorBase)new BlockDoorBase(Material.field_151575_d).func_149663_c("witchery:cwoodendoor").func_149658_d("door_wood");
  

  public final BlockPressurePlateBase CURSED_WOODEN_PRESSURE_PLATE = (BlockPressurePlateBase)new BlockPressurePlateBase(Blocks.field_150452_aw, "planks_oak", BlockPressurePlate.Sensitivity.everything).func_149663_c("witchery:cwoodpressureplate");
  


  public final BlockPressurePlateBase CURSED_STONE_PRESSURE_PLATE = (BlockPressurePlateBase)new BlockPressurePlateBase(Blocks.field_150456_au, "stone", BlockPressurePlate.Sensitivity.mobs).func_149663_c("witchery:cstonepressureplate");
  


  public final BlockPressurePlateBase CURSED_SNOW_PRESSURE_PLATE = (BlockPressurePlateBase)new BlockPressurePlateBase(SNOW_PRESSURE_PLATE, "snow", BlockPressurePlate.Sensitivity.everything).func_149663_c("witchery:csnowpressureplate");
  


  public final BlockButtonBase CURSED_BUTTON_WOOD = (BlockButtonBase)new BlockButtonBase(true) {
    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int p_149691_1_, int p_149691_) {
      return Blocks.field_150344_f.func_149733_h(1);
    }
  }.func_149663_c("witchery:cbuttonwood");
  





  public final BlockButtonBase CURSED_BUTTON_STONE = (BlockButtonBase)new BlockButtonBase(false) {
    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int p_149691_1_, int p_149691_2_) {
      return Blocks.field_150348_b.func_149733_h(1);
    }
  }.func_149663_c("witchery:cbuttonstone");
  
  public WitcheryBlocks() {}
}
