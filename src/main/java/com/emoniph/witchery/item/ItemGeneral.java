package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.WitcheryFluids;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.blocks.BlockChalice.TileEntityChalice;
import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
import com.emoniph.witchery.blocks.BlockCrystalBall;
import com.emoniph.witchery.blocks.BlockDreamCatcher.TileEntityDreamCatcher;
import com.emoniph.witchery.blocks.BlockPlacedItem;
import com.emoniph.witchery.blocks.BlockWickerBundle;
import com.emoniph.witchery.blocks.BlockWitchCrop;
import com.emoniph.witchery.brewing.BrewItemKey;
import com.emoniph.witchery.brewing.potions.PotionEnslaved;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.common.Shapeshift;
import com.emoniph.witchery.dimension.WorldProviderDreamWorld;
import com.emoniph.witchery.entity.EntityBroom;
import com.emoniph.witchery.entity.EntityCovenWitch;
import com.emoniph.witchery.entity.EntityDeathsHorse;
import com.emoniph.witchery.entity.EntityEye;
import com.emoniph.witchery.entity.EntityLordOfTorment;
import com.emoniph.witchery.entity.EntitySpirit;
import com.emoniph.witchery.entity.EntitySummonedUndead;
import com.emoniph.witchery.entity.EntityTreefyd;
import com.emoniph.witchery.infusion.InfusedBrew;
import com.emoniph.witchery.infusion.InfusedBrewEffect;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.infusion.PlayerEffects;
import com.emoniph.witchery.infusion.PlayerEffects.PlayerEffect;
import com.emoniph.witchery.infusion.infusions.InfusionInfernal;
import com.emoniph.witchery.infusion.infusions.InfusionOtherwhere;
import com.emoniph.witchery.infusion.infusions.symbols.EffectRegistry;
import com.emoniph.witchery.infusion.infusions.symbols.SymbolEffect;
import com.emoniph.witchery.item.brew.BrewFluid;
import com.emoniph.witchery.item.brew.BrewSolidifySpirit;
import com.emoniph.witchery.item.brew.BrewSoul;
import com.emoniph.witchery.network.PacketCamPos;
import com.emoniph.witchery.network.PacketParticles;
import com.emoniph.witchery.network.PacketPipeline;
import com.emoniph.witchery.util.BlockProtect;
import com.emoniph.witchery.util.BlockSide;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.CreatureUtil;
import com.emoniph.witchery.util.EffectSpiral;
import com.emoniph.witchery.util.MutableBlock;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TargetPointUtil;
import com.emoniph.witchery.util.TimeUtil;
import com.emoniph.witchery.util.TransformCreature;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.server.S14PacketEntity.S17PacketEntityLookMove;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.profiler.Profiler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.FoodStats;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.GameRules;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;

public class ItemGeneral extends ItemBase
{
  public final ArrayList<SubItem> subItems = new ArrayList();
  public final ArrayList<DreamWeave> weaves = new ArrayList();
  
  public final SubItem itemCandelabra = SubItem.register(new SubItem(0, "candelabra"), subItems);
  public final SubItem itemChaliceEmpty = SubItem.register(new SubItem(1, "chalice"), subItems);
  public final SubItem itemChaliceFull = SubItem.register(new SubItem(2, "chaliceFull"), subItems);
  
  public final DreamWeave itemDreamMove = DreamWeave.register(new DreamWeave(3, 0, "weaveMoveFast", Potion.field_76424_c, Potion.field_76421_d, 7200, 0, 17, 10, null), subItems, weaves);
  
  public final DreamWeave itemDreamDig = DreamWeave.register(new DreamWeave(4, 1, "weaveDigFast", Potion.field_76422_e, Potion.field_76419_f, 7200, 0, 17, 4, null), subItems, weaves);
  
  public final DreamWeave itemDreamEat = DreamWeave.register(new DreamWeave(5, 2, "weaveSaturation", Potion.field_76443_y, Potion.field_76438_s, 4800, 0, 17, 16, null), subItems, weaves);
  
  public final DreamWeave itemDreamNightmare = DreamWeave.register(new DreamWeave(6, 3, "weaveNightmares", Potion.field_76437_t, Potion.field_76440_q, 1200, 0, 4, 4, null), subItems, weaves);
  

  public final SubItem itemBoneNeedle = SubItem.register(new SubItem(7, "boneNeedle"), subItems);
  
  public final SubItem itemBroom = SubItem.register(new SubItem(8, "broom"), subItems);
  public final SubItem itemBroomEnchanted = SubItem.register(new SubItem(9, "broomEnchanted", 3, null).setEnchanted(true), subItems);
  
  public final SubItem itemAttunedStone = SubItem.register(new SubItem(10, "attunedStone"), subItems);
  public final SubItem itemAttunedStoneCharged = SubItem.register(new SubItem(11, "attunedStoneCharged").setEnchanted(true), subItems);
  
  public final SubItem itemWaystone = SubItem.register(new SubItem(12, "waystone"), subItems);
  public final SubItem itemWaystoneBound = SubItem.register(new SubItem(13, "waystoneBound", 1, false, null), subItems);
  
  public final SubItem itemMutandis = SubItem.register(new SubItem(14, "mutandis"), subItems);
  public final SubItem itemMutandisExtremis = SubItem.register(new SubItem(15, "mutandisExtremis"), subItems);
  
  public final SubItem itemQuicklime = SubItem.register(new SubItem(16, "quicklime"), subItems);
  public final SubItem itemGypsum = SubItem.register(new SubItem(17, "gypsum"), subItems);
  public final SubItem itemAshWood = SubItem.register(new SubItem(18, "ashWood"), subItems);
  
  public final SubItem itemBelladonnaFlower = SubItem.register(new SubItem(21, "belladonna"), subItems);
  public final SubItem itemMandrakeRoot = SubItem.register(new SubItem(22, "mandrakeRoot"), subItems);
  
  private static final int DEMON_FOOD_DURATION = 2400;
  public final SubItem itemDemonHeart = SubItem.register(new Drinkable(23, "demonHeart", 2, EnumAction.eat, new PotionEffect[] { new PotionEffect(field_76434_wfield_76415_H, 2400, 4), new PotionEffect(field_76428_lfield_76415_H, 2400, 1), new PotionEffect(field_76420_gfield_76415_H, 2400, 2), new PotionEffect(field_76424_cfield_76415_H, 2400, 2), new PotionEffect(field_76426_nfield_76415_H, 2400, 2), new PotionEffect(field_76431_kfield_76415_H, 2400), new PotionEffect(field_76438_sfield_76415_H, 3600, 1) }), subItems);
  



  public final SubItem itemBatWool = SubItem.register(new SubItem(24, "batWool"), subItems);
  public final SubItem itemDogTongue = SubItem.register(new SubItem(25, "dogTongue"), subItems);
  
  public final SubItem itemSoftClayJar = SubItem.register(new SubItem(26, "clayJarSoft"), subItems);
  public final SubItem itemEmptyClayJar = SubItem.register(new SubItem(27, "clayJar"), subItems);
  
  public final SubItem itemFoulFume = SubItem.register(new SubItem(28, "foulFume"), subItems);
  public final SubItem itemDiamondVapour = SubItem.register(new SubItem(29, "diamondVapour"), subItems);
  
  public final SubItem itemOilOfVitriol = SubItem.register(new SubItem(30, "oilOfVitriol"), subItems);
  
  public final SubItem itemExhaleOfTheHornedOne = SubItem.register(new SubItem(31, "exhaleOfTheHornedOne"), subItems);
  public final SubItem itemBreathOfTheGoddess = SubItem.register(new SubItem(32, "breathOfTheGoddess"), subItems);
  public final SubItem itemHintOfRebirth = SubItem.register(new SubItem(33, "hintOfRebirth"), subItems);
  public final SubItem itemWhiffOfMagic = SubItem.register(new SubItem(34, "whiffOfMagic"), subItems);
  public final SubItem itemReekOfMisfortune = SubItem.register(new SubItem(35, "reekOfMisfortune"), subItems);
  public final SubItem itemOdourOfPurity = SubItem.register(new SubItem(36, "odourOfPurity"), subItems);
  public final SubItem itemTearOfTheGoddess = SubItem.register(new SubItem(37, "tearOfTheGoddess"), subItems);
  public final SubItem itemRefinedEvil = SubItem.register(new SubItem(38, "refinedEvil"), subItems);
  public final SubItem itemDropOfLuck = SubItem.register(new SubItem(39, "dropOfLuck"), subItems);
  
  public final SubItem itemRedstoneSoup = SubItem.register(new Drinkable(40, "redstoneSoup", 1, new PotionEffect[] { new PotionEffect(field_76434_wfield_76415_H, 2400, 1) }), subItems);
  

  public final SubItem itemFlyingOintment = SubItem.register(new Drinkable(41, "flyingOintment", 2, new PotionEffect[] { new PotionEffect(field_76436_ufield_76415_H, 1200, 2) }), subItems);
  
  public final SubItem itemGhostOfTheLight = SubItem.register(new Drinkable(42, "ghostOfTheLight", 2, new PotionEffect[] { new PotionEffect(field_76436_ufield_76415_H, 1200, 1) }), subItems);
  
  public final SubItem itemSoulOfTheWorld = SubItem.register(new Drinkable(43, "soulOfTheWorld", 2, new PotionEffect[] { new PotionEffect(field_76436_ufield_76415_H, 1200, 1) }), subItems);
  
  public final SubItem itemSpiritOfOtherwhere = SubItem.register(new Drinkable(44, "spiritOfOtherwhere", 2, new PotionEffect[] { new PotionEffect(field_76436_ufield_76415_H, 1200, 1) }), subItems);
  
  public final SubItem itemInfernalAnimus = SubItem.register(new Drinkable(45, "infernalAnimus", 2, new PotionEffect[] { new PotionEffect(field_76436_ufield_76415_H, 1200, 1), new PotionEffect(field_82731_vfield_76415_H, 3600, 2) }), subItems);
  

  public final SubItem itemBookOven = SubItem.register(new SubItem(46, "bookOven"), subItems);
  public final SubItem itemBookDistilling = SubItem.register(new SubItem(47, "bookDistilling"), subItems);
  public final SubItem itemBookCircleMagic = SubItem.register(new SubItem(48, "bookCircleMagic"), subItems);
  public final SubItem itemBookInfusions = SubItem.register(new SubItem(49, "bookInfusions"), subItems);
  
  public final SubItem itemOddPorkRaw = SubItem.register(new Edible(50, "oddPorkchopRaw", 3, 0.3F, true, null), subItems);
  public final SubItem itemOddPorkCooked = SubItem.register(new Edible(51, "oddPorkchopCooked", 8, 0.8F, true, null), subItems);
  
  public final SubItem itemDoorRowan = SubItem.register(new SubItem(52, "doorRowan"), subItems);
  public final SubItem itemDoorAlder = SubItem.register(new SubItem(53, "doorAlder"), subItems);
  
  public final SubItem itemDoorKey = SubItem.register(new SubItem(54, "doorKey"), subItems);
  
  public final SubItem itemRock = SubItem.register(new SubItem(55, "rock"), subItems);
  public final SubItem itemWeb = SubItem.register(new SubItem(56, "web"), subItems);
  
  public final SubItem itemBrewOfVines = SubItem.register(new Brew(57, "brewVines"), subItems);
  public final SubItem itemBrewOfWebs = SubItem.register(new Brew(58, "brewWeb"), subItems);
  public final SubItem itemBrewOfThorns = SubItem.register(new Brew(59, "brewThorns"), subItems);
  public final SubItem itemBrewOfInk = SubItem.register(new Brew(60, "brewInk"), subItems);
  public final SubItem itemBrewOfSprouting = SubItem.register(new Brew(61, "brewSprouting"), subItems);
  public final SubItem itemBrewOfErosion = SubItem.register(new Brew(62, "brewErosion"), subItems);
  
  public final SubItem itemRowanBerries = SubItem.register(new Edible(63, "berriesRowan", 1, 6.0F, false, null), subItems);
  
  public final SubItem itemNecroStone = SubItem.register(new SubItem(64, "necroStone", 1, null).setEnchanted(true), subItems);
  public final SubItem itemBrewOfRaising = SubItem.register(new Brew(65, "brewRaising"), subItems);
  
  public final SubItem itemSpectralDust = SubItem.register(new SubItem(66, "spectralDust"), subItems);
  public final SubItem itemEnderDew = SubItem.register(new SubItem(67, "enderDew"), subItems);
  
  public final SubItem itemArtichoke = SubItem.register(new Edible(69, "artichoke", 20, 0.0F, false, null), subItems);
  
  public final SubItem itemSeedsTreefyd = SubItem.register(new SubItem(70, "seedsTreefyd"), subItems);
  
  public final SubItem itemBrewGrotesque = SubItem.register(new Drinkable(71, "brewGrotesque", 1, new PotionEffect[0]).setPotion(true), subItems);
  public final SubItem itemImpregnatedLeather = SubItem.register(new SubItem(72, "impregnatedLeather"), subItems);
  public final SubItem itemFumeFilter = SubItem.register(new SubItem(73, "fumeFilter"), subItems);
  
  public final SubItem itemCreeperHeart = SubItem.register(new Drinkable(74, "creeperHeart", 1, EnumAction.eat, new PotionEffect[] { new PotionEffect(field_76426_nfield_76415_H, 20, 0) }), subItems);
  
  public final SubItem itemBrewOfLove = SubItem.register(new Brew(75, "brewLove"), subItems);
  public final SubItem itemBrewOfIce = SubItem.register(new Brew(76, "brewIce"), subItems);
  public final SubItem itemBrewOfTheDepths = SubItem.register(new Drinkable(77, "brewDepths", 1, new PotionEffect[0]).setPotion(true), subItems);
  public final SubItem itemIcyNeedle = SubItem.register(new SubItem(78, "icyNeedle"), subItems);
  public final SubItem itemFrozenHeart = SubItem.register(new Drinkable(79, "frozenHeart", 1, EnumAction.eat, new PotionEffect[] { new PotionEffect(field_76426_nfield_76415_H, 20, 0) }), subItems);
  
  public final SubItem itemInfernalBlood = SubItem.register(new SubItem(80, "infernalBlood"), subItems);
  
  public final SubItem itemBookHerbology = SubItem.register(new SubItem(81, "bookHerbology"), subItems);
  
  public final SubItem itemBranchEnt = SubItem.register(new SubItem(82, "entbranch"), subItems);
  public final SubItem itemMysticUnguent = SubItem.register(new Drinkable(83, "mysticunguent", 2, new PotionEffect[] { new PotionEffect(field_76437_tfield_76415_H, 1200, 1) }), subItems);
  

  public final SubItem itemDoorKeyring = SubItem.register(new SubItem(84, "doorKeyring"), subItems);
  
  public final SubItem itemBrewOfFrogsTongue = SubItem.register(new Brew(85, "brewFrogsTongue"), subItems);
  public final SubItem itemBrewOfCursedLeaping = SubItem.register(new Brew(86, "brewCursedLeaping"), subItems);
  public final SubItem itemBrewOfHitchcock = SubItem.register(new Brew(87, "brewHitchcock"), subItems);
  public final SubItem itemBrewOfInfection = SubItem.register(new Brew(88, "brewInfection"), subItems);
  public final SubItem itemOwletsWing = SubItem.register(new SubItem(89, "owletsWing"), subItems);
  public final SubItem itemToeOfFrog = SubItem.register(new SubItem(90, "toeOfFrog"), subItems);
  public final SubItem itemWormyApple = SubItem.register(new Drinkable(91, "appleWormy", 0, EnumAction.eat, new PotionEffect[] { new PotionEffect(field_76436_ufield_76415_H, 60) }), subItems);
  public final SubItem itemQuartzSphere = SubItem.register(new SubItem(92, "quartzSphere"), subItems);
  public final SubItem itemHappenstanceOil = SubItem.register(new Drinkable(93, "happenstanceOil", 1, new PotionEffect[] { new PotionEffect(field_76439_rfield_76415_H, 1200) }), subItems);
  public final SubItem itemSeerStone = SubItem.register(new SubItem(94, "seerStone", 1, null).setEnchanted(true), subItems);
  public final SubItem itemBrewOfSleeping = SubItem.register(new Drinkable(95, "brewSleep", 1, new PotionEffect[0]).setPotion(true), subItems);
  public final SubItem itemBrewOfFlowingSpirit = SubItem.register(new BrewFluid(96, "brewFlowingSpirit", FluidsFLOWING_SPIRIT), subItems);
  public final SubItem itemBrewOfWasting = SubItem.register(new Brew(97, "brewWasting"), subItems);
  public final SubItem itemSleepingApple = SubItem.register(new Edible(98, "sleepingApple", 3, 3.0F, false, true, null), subItems);
  public final SubItem itemDisturbedCotton = SubItem.register(new SubItem(99, "disturbedCotton"), subItems);
  public final SubItem itemFancifulThread = SubItem.register(new SubItem(100, "fancifulThread"), subItems);
  public final SubItem itemTormentedTwine = SubItem.register(new SubItem(101, "tormentedTwine"), subItems);
  public final SubItem itemGoldenThread = SubItem.register(new SubItem(102, "goldenThread"), subItems);
  public final SubItem itemMellifluousHunger = SubItem.register(new SubItem(103, "mellifluousHunger"), subItems);
  
  public final DreamWeave itemDreamIntensity = DreamWeave.register(new DreamWeave(104, 4, "weaveIntensity", Potion.field_76439_r, Potion.field_76440_q, 300, 0, 17, 22, null), subItems, weaves);
  

  public final SubItem itemPurifiedMilk = SubItem.register(new Drinkable(105, "purifiedMilk", 1, new PotionEffect[0]), subItems);
  public final SubItem itemBookBiomes = SubItem.register(new SubItem(106, "bookBiomes"), subItems);
  public final SubItem itemBookWands = SubItem.register(new SubItem(107, "bookWands"), subItems);
  public final SubItem itemBatBall = SubItem.register(new SubItem(108, "batBall"), subItems);
  public final SubItem itemBrewOfBats = SubItem.register(new Brew(109, "brewBats"), subItems);
  public final SubItem itemCharmOfDisruptedDreams = SubItem.register(new SubItem(110, "charmDisruptedDreams"), subItems);
  public final SubItem itemWormwood = SubItem.register(new SubItem(111, "wormwood"), subItems);
  public final SubItem itemSubduedSpirit = SubItem.register(new SubItem(112, "subduedSpirit"), subItems);
  public final SubItem itemFocusedWill = SubItem.register(new SubItem(113, "focusedWill"), subItems);
  public final SubItem itemCondensedFear = SubItem.register(new SubItem(114, "condensedFear"), subItems);
  public final SubItem itemBrewOfHollowTears = SubItem.register(new BrewFluid(115, "brewHollowTears", FluidsHOLLOW_TEARS), subItems);
  public final SubItem itemBrewOfSolidRock = SubItem.register(new BrewSolidifySpirit(116, "brewSolidStone", Blocks.field_150348_b), subItems);
  public final SubItem itemBrewOfSolidDirt = SubItem.register(new BrewSolidifySpirit(117, "brewSolidDirt", Blocks.field_150346_d), subItems);
  public final SubItem itemBrewOfSolidSand = SubItem.register(new BrewSolidifySpirit(118, "brewSolidSand", Blocks.field_150354_m), subItems);
  public final SubItem itemBrewOfSolidSandstone = SubItem.register(new BrewSolidifySpirit(119, "brewSolidSandstone", Blocks.field_150322_A), subItems);
  public final SubItem itemBrewOfSolidErosion = SubItem.register(new BrewSolidifySpirit(120, "brewSolidErosion", null), subItems);
  
  public final SubItem itemInfusionBase = SubItem.register(new Drinkable(121, "infusionBase", 2, new PotionEffect[] { new PotionEffect(field_82731_vfield_76415_H, 200, 3) }), subItems);
  public final SubItem itemBrewOfSoaring = SubItem.register(new InfusedBrew(122, "brewSoaring", InfusedBrewEffect.Soaring), subItems);
  public final SubItem itemBrewGrave = SubItem.register(new InfusedBrew(123, "brewGrave", InfusedBrewEffect.Grave), subItems);
  public final SubItem itemBrewRevealing = SubItem.register(new Brew(124, "brewRevealing") {
    public ItemGeneral.Brew.BrewResult onImpact(World world, EntityLivingBase thrower, MovingObjectPosition mop, boolean enhanced, double brewX, double brewY, double brewZ, AxisAlignedBB brewBounds) {
      double RADIUS = enhanced ? 8.0D : 5.0D;
      double RADIUS_SQ = RADIUS * RADIUS;
      AxisAlignedBB areaOfEffect = brewBounds.func_72314_b(RADIUS, RADIUS, RADIUS);
      List entities = world.func_72872_a(EntityLivingBase.class, areaOfEffect);
      if ((entities != null) && (!entities.isEmpty())) {
        Iterator entityIterator = entities.iterator();
        while (entityIterator.hasNext()) {
          EntityLivingBase entityLiving = (EntityLivingBase)entityIterator.next();
          double distanceSq = entityLiving.func_70092_e(brewX, brewY, brewZ);
          if (distanceSq <= RADIUS_SQ) {
            double scalingFactor = 1.0D - Math.sqrt(distanceSq) / RADIUS;
            if (entityLiving == field_72308_g) {
              scalingFactor = 1.0D;
            }
            
            if (entityLiving.func_70644_a(Potion.field_76441_p)) {
              entityLiving.func_82170_o(field_76441_pfield_76415_H);
            }
            
            if (((entityLiving instanceof EntityPlayerMP)) && (entityLiving.func_82150_aj())) {
              entityLiving.func_82142_c(false);
            }
            
            if ((entityLiving instanceof EntityPlayer)) {
              EntityPlayer player = (EntityPlayer)entityLiving;
              ExtendedPlayer playerEx = ExtendedPlayer.get(player);
              if ((playerEx != null) && (playerEx.getCreatureType() == TransformCreature.PLAYER)) {
                ParticleEffect.SMOKE.send(SoundEffect.WITCHERY_RANDOM_POOF, player, 0.5D, 2.0D, 16);
                Shapeshift.INSTANCE.shiftTo(player, TransformCreature.NONE);
              }
            }
            
            if (((entityLiving instanceof EntitySummonedUndead)) && (((EntitySummonedUndead)entityLiving).isObscured())) {
              ((EntitySummonedUndead)entityLiving).setObscured(false);
            }
          }
        }
      }
      return ItemGeneral.Brew.BrewResult.SHOW_EFFECT;
    }
  }, subItems);
  









































  public final SubItem itemBrewSubstitution = SubItem.register(new Brew(125, "brewSubstitution") {
    public ItemGeneral.Brew.BrewResult onImpact(World world, EntityLivingBase thrower, final MovingObjectPosition mop, boolean enhanced, double brewX, double brewY, double brewZ, AxisAlignedBB brewBounds) {
      if ((mop == null) || (field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY)) {
        return ItemGeneral.Brew.BrewResult.DROP_ITEM;
      }
      
      int RADIUS = enhanced ? 6 : 4;
      final double RADIUS_SQ = RADIUS * RADIUS;
      AxisAlignedBB areaOfEffect = brewBounds.func_72314_b(RADIUS, RADIUS, RADIUS);
      List entities = world.func_72872_a(EntityItem.class, areaOfEffect);
      if ((entities != null) && (!entities.isEmpty())) {
        final ArrayList<EntityItem> items = new ArrayList();
        Iterator entityIterator = entities.iterator();
        while (entityIterator.hasNext()) {
          EntityItem item = (EntityItem)entityIterator.next();
          double distanceSq = item.func_70092_e(brewX, brewY, brewZ);
          if (distanceSq <= RADIUS_SQ) {
            ItemStack stack = item.func_92059_d();
            if ((stack.func_77973_b() instanceof ItemBlock)) {
              items.add(item);
            }
          }
        }
        
        Block refBlock = BlockUtil.getBlock(world, field_72311_b, field_72312_c, field_72309_d);
        final int refMeta = BlockUtil.getBlockMetadata(world, field_72311_b, field_72312_c, field_72309_d);
        
        if ((items.size() > 0) && (refBlock != null) && (BlockProtect.canBreak(refBlock, world))) {
          new EffectSpiral(new com.emoniph.witchery.util.ISpiralBlockAction() {
            int stackIndex = 0;
            int subCount = 0;
            

            public void onSpiralActionStart(World world, int posX, int posY, int posZ) {}
            

            public boolean onSpiralBlockAction(World world, int posX, int posY, int posZ)
            {
              if (Coord.distanceSq(mopfield_72311_b, mopfield_72312_c, mopfield_72309_d, posX, posY, posZ) < RADIUS_SQ) {
                boolean found = false;
                if ((BlockUtil.getBlock(world, posX, posY, posZ) == items) && (BlockUtil.isReplaceableBlock(world, posX, posY + 1, posZ))) {
                  found = true;
                } else if ((BlockUtil.getBlock(world, posX, posY + 1, posZ) == items) && (BlockUtil.isReplaceableBlock(world, posX, posY + 2, posZ))) {
                  posY++;
                  found = true;
                } else if ((BlockUtil.getBlock(world, posX, posY - 1, posZ) == items) && (BlockUtil.isReplaceableBlock(world, posX, posY, posZ))) {
                  posY--;
                  found = true;
                } else if ((BlockUtil.getBlock(world, posX, posY + 2, posZ) == items) && (BlockUtil.isReplaceableBlock(world, posX, posY + 3, posZ))) {
                  posY += 2;
                  found = true;
                } else if ((BlockUtil.getBlock(world, posX, posY - 2, posZ) == items) && (BlockUtil.isReplaceableBlock(world, posX, posY - 1, posZ))) {
                  posY -= 2;
                  found = true;
                }
                
                if (found) {
                  subCount += 1;
                  ItemStack stack = ((EntityItem)refMeta.get(stackIndex)).func_92059_d();
                  BlockUtil.setBlock(world, posX, posY, posZ, (ItemBlock)stack.func_77973_b(), stack.func_77960_j(), 3);
                  ParticleEffect.INSTANT_SPELL.send(SoundEffect.NONE, world, posX, posY, posZ, 1.0D, 1.0D, 16);
                  
                  if (--field_77994_a == 0) {
                    ((EntityItem)refMeta.get(stackIndex)).func_70106_y();
                    stackIndex += 1;
                  }
                }
              }
              return stackIndex < refMeta.size();
            }
            
            public void onSpiralActionStop(World world, int posX, int posY, int posZ)
            {
              while (subCount > 0) {
                int quantity = subCount > 64 ? 64 : subCount;
                subCount -= quantity;
                world.func_72838_d(new EntityItem(world, 0.5D + posX, 1.5D + posY, 0.5D + posZ, new ItemStack(items, quantity, val$refMeta))); } } }).apply(world, field_72311_b, field_72312_c, field_72309_d, RADIUS * 2, RADIUS * 2);
          



          return ItemGeneral.Brew.BrewResult.SHOW_EFFECT;
        }
      }
      return ItemGeneral.Brew.BrewResult.DROP_ITEM;
    }
  }, subItems);
  























































































  public final SubItem itemCongealedSpirit = SubItem.register(new Drinkable(126, "brewCongealedSpirit", 1, new PotionEffect[] { new PotionEffect(field_76439_rfield_76415_H, TimeUtil.secsToTicks(30), 1) }).setPotion(true), subItems);
  public final SubItem itemBookBurning = SubItem.register(new SubItem(127, "bookBurning"), subItems);
  public final SubItem itemGraveyardDust = SubItem.register(new SubItem(128, "graveyardDust"), subItems);
  public final SubItem itemBinkyHead = SubItem.register(new BoltType(129, "binkyhead", null), subItems);
  public final SubItem itemNullCatalyst = SubItem.register(new BoltType(130, "nullcatalyst", null), subItems);
  public final SubItem itemNullifiedLeather = SubItem.register(new BoltType(131, "nullifiedleather", null), subItems);
  public final SubItem itemBoltStake = SubItem.register(new BoltType(132, "boltStake", null), subItems);
  public final SubItem itemBoltAntiMagic = SubItem.register(new BoltType(133, "boltAntiMagic", null), subItems);
  public final SubItem itemBoltHoly = SubItem.register(new BoltType(134, "boltHoly", null), subItems);
  public final SubItem itemBoltSplitting = SubItem.register(new BoltType(135, "boltSplitting", null), subItems);
  public final SubItem itemBrewSoulHunger = SubItem.register(new BrewSoul(136, "brewSoulHunger", EffectRegistry.CarnosaDiem), subItems);
  public final SubItem itemBrewSoulAnguish = SubItem.register(new BrewSoul(137, "brewSoulAnguish", EffectRegistry.Ignianima), subItems);
  public final SubItem itemBrewSoulFear = SubItem.register(new BrewSoul(138, "brewSoulFear", EffectRegistry.MORSMORDRE), subItems);
  public final SubItem itemBrewSoulTorment = SubItem.register(new BrewSoul(139, "brewSoulTorment", EffectRegistry.Tormentum), subItems);
  public final SubItem itemContractOwnership = SubItem.register(new SubItem(140, "contract"), subItems);
  public final SubItem itemContractTorment = SubItem.register(new SubItem(141, "contractTorment"), subItems);
  public final SubItem itemContractBlaze = SubItem.register(new ItemGeneralContract(142, "contractBlaze") {
    public boolean activate(ItemStack stack, EntityLivingBase targetEntity) {
      EntityCreature blaze = InfusionInfernal.spawnCreature(field_70170_p, net.minecraft.entity.monster.EntityBlaze.class, targetEntity, 1, 2, ParticleEffect.FLAME, SoundEffect.RANDOM_FIZZ);
      if (blaze != null) {
        blaze.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(50.0D);
        blaze.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(7.0D);
        return true;
      }
      return false;
    }
  }, subItems);
  










  public final SubItem itemContractResistFire = SubItem.register(new ItemGeneralContract(143, "contractResistFire") {
    public boolean activate(ItemStack stack, EntityLivingBase targetEntity) {
      targetEntity.func_70690_d(new PotionEffect(field_76426_nfield_76415_H, TimeUtil.minsToTicks(15)));
      return true;
    }
  }, subItems);
  




  public final SubItem itemContractEvaporate = SubItem.register(new ItemGeneralContract(144, "contractEvaporate") {
    public boolean activate(ItemStack stack, EntityLivingBase targetEntity) {
      if ((targetEntity instanceof EntityPlayer)) {
        PlayerEffects.IMP_EVAPORATION.applyTo((EntityPlayer)targetEntity, TimeUtil.minsToTicks(10));
        return true;
      }
      return false;
    }
  }, subItems);
  








  public final SubItem itemContractFieryTouch = SubItem.register(new ItemGeneralContract(145, "contractFieryTouch") {
    public boolean activate(ItemStack stack, EntityLivingBase targetEntity) {
      if ((targetEntity instanceof EntityPlayer)) {
        PlayerEffects.IMP_FIRE_TOUCH.applyTo((EntityPlayer)targetEntity, TimeUtil.minsToTicks(10));
        return true;
      }
      return false;
    }
  }, subItems);
  








  public final SubItem itemContractSmelting = SubItem.register(new ItemGeneralContract(146, "contractSmelting") {
    public boolean activate(ItemStack stack, EntityLivingBase targetEntity) {
      if ((targetEntity instanceof EntityPlayer)) {
        PlayerEffects.IMP_METLING_TOUCH.applyTo((EntityPlayer)targetEntity, TimeUtil.minsToTicks(10));
        return true;
      }
      return false;
    }
  }, subItems);
  








  public final SubItem itemWaystonePlayerBound = SubItem.register(new SubItem(147, "waystoneCreatureBound", 1, false, null), subItems);
  public final SubItem itemKobolditeDust = SubItem.register(new SubItem(148, "kobolditedust"), subItems);
  public final SubItem itemKobolditeNugget = SubItem.register(new SubItem(149, "kobolditenugget"), subItems);
  public final SubItem itemKobolditeIngot = SubItem.register(new SubItem(150, "kobolditeingot"), subItems);
  public final SubItem itemKobolditePentacle = SubItem.register(new SubItem(151, "pentacle"), subItems);
  public final SubItem itemDoorIce = SubItem.register(new SubItem(152, "doorIce"), subItems);
  public final SubItem itemAnnointingPaste = SubItem.register(new SubItem(153, "annointingPaste"), subItems);
  public final SubItem itemSubduedSpiritVillage = SubItem.register(new SubItem(154, "subduedSpiritVillage"), subItems);
  public final SubItem itemBoltSilver = SubItem.register(new BoltType(155, "boltSilver", null), subItems);
  public final SubItem itemWolfsbane = SubItem.register(new SubItem(156, "wolfsbane"), subItems);
  public final SubItem itemSilverDust = SubItem.register(new SubItem(157, "silverdust"), subItems);
  public final SubItem itemMuttonRaw = SubItem.register(new Edible(158, "muttonraw", 2, 0.2F, true, null), subItems);
  public final SubItem itemMuttonCooked = SubItem.register(new Edible(159, "muttoncooked", 6, 0.8F, true, null), subItems);
  public final SubItem itemVampireBookPage = SubItem.register(new SubItem(160, "vbookPage"), subItems);
  public final SubItem itemDarkCloth = SubItem.register(new SubItem(161, "darkCloth"), subItems);
  public final SubItem itemWoodenStake = SubItem.register(new SubItem(162, "stake"), subItems);
  public final SubItem itemBloodWarm = SubItem.register(new Drinkable(163, "warmBlood", 1, new PotionEffect[0]) {
    public void onDrunk(World world, EntityPlayer player, ItemStack itemstack) {
      if (!field_72995_K) {
        ExtendedPlayer playerEx = ExtendedPlayer.get(player);
        if (playerEx.isVampire()) {
          playerEx.increaseBloodPower(500);
        } else {
          player.func_70690_d(new PotionEffect(field_76438_sfield_76415_H, TimeUtil.secsToTicks(6)));
        }
      }
    }
  }, subItems);
  










  public final SubItem itemBloodLiliths = SubItem.register(new Drinkable(164, "lilithsBlood", 2, new PotionEffect[0]) {
    public void onDrunk(World world, EntityPlayer player, ItemStack itemstack) {
      if (!field_72995_K) {
        ExtendedPlayer playerEx = ExtendedPlayer.get(player);
        int level = playerEx.getVampireLevel();
        if (level == 10) {
          playerEx.increaseBloodPower(2000);
        } else {
          playerEx.increaseVampireLevel();
        }
      }
    }
  }, subItems);
  











  public final SubItem itemHeartOfGold = SubItem.register(new SubItem(165, "heartofgold"), subItems);
  @SideOnly(Side.CLIENT)
  private IIcon overlayGenericIcon;
  
  public ItemGeneral() { func_77656_e(0);
    func_77625_d(64);
    func_77627_a(true);
  }
  
  public boolean isBrew(int itemDamage) {
    return subItems.get(itemDamage) instanceof Brew;
  }
  
  public boolean isBrew(ItemStack stack) {
    return (stack != null) && (stack.func_77973_b() == this) && (isBrew(stack.func_77960_j()));
  }
  
  public String func_77667_c(ItemStack itemStack)
  {
    return func_77658_a() + "." + subItems.get(itemStack.func_77960_j())).unlocalizedName;
  }
  


  @SideOnly(Side.CLIENT)
  private IIcon overlayBroomIcon;
  

  @SideOnly(Side.CLIENT)
  private IIcon overlaySolidifierIcon;
  
  @SideOnly(Side.CLIENT)
  private IIcon overlayInfusedBrewIcon;
  
  @SideOnly(Side.CLIENT)
  public void func_94581_a(IIconRegister iconRegister)
  {
    String defaultIconName = func_111208_A();
    for (SubItem subItem : subItems) {
      if (subItem != null) {
        subItem.registerIcon(iconRegister, this);
      }
    }
    overlayGenericIcon = iconRegister.func_94245_a(defaultIconName + ".genericoverlay");
    overlayBroomIcon = iconRegister.func_94245_a(defaultIconName + ".broomOverlay");
    overlaySolidifierIcon = iconRegister.func_94245_a(defaultIconName + ".brewSolidOverlay");
    overlayInfusedBrewIcon = iconRegister.func_94245_a(defaultIconName + ".brewInfusedOverlay");
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_77617_a(int damage)
  {
    return subItems.get(Math.max(damage, 0))).icon;
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_77618_c(int damage, int pass)
  {
    if (pass == 0)
      return super.func_77618_c(damage, pass);
    if (((SubItem)subItems.get(damage)).isSolidifier())
      return overlaySolidifierIcon;
    if (((SubItem)subItems.get(damage)).isInfused())
      return overlayInfusedBrewIcon;
    if (((SubItem)subItems.get(damage)).isPotion())
      return Items.field_151068_bn.func_77618_c((subItems.get(damage) instanceof Brew) ? 16384 : 0, pass);
    if (itemBroomEnchanted.damageValue == damage) {
      return overlayBroomIcon;
    }
    return overlayGenericIcon;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_82790_a(ItemStack stack, int pass)
  {
    if ((!itemBroomEnchanted.isMatch(stack)) || (pass == 0)) {
      return super.func_82790_a(stack, pass);
    }
    
    int j = getBroomItemColor(stack);
    
    if (j > 15)
      return super.func_82790_a(stack, pass);
    if (j < 0) {
      j = 12;
    }
    
    return net.minecraft.item.ItemDye.field_150922_c[net.minecraft.block.BlockColored.func_150031_c(j)];
  }
  
  @SideOnly(Side.CLIENT)
  public boolean func_77623_v()
  {
    return true;
  }
  
  public boolean hasEffect(ItemStack stack, int pass)
  {
    return ((pass == 0) && (((SubItem)subItems.get(stack.func_77960_j())).isEnchanted())) || (itemBroomEnchanted.isMatch(stack)) || (itemSubduedSpirit.isMatch(stack)) || (itemSubduedSpiritVillage.isMatch(stack));
  }
  
  @SideOnly(Side.CLIENT)
  public void func_150895_a(Item item, CreativeTabs tab, List itemList)
  {
    for (SubItem subItem : subItems) {
      if ((subItem != null) && (showInCreativeTab)) {
        itemList.add(subItem.createStack());
      }
    }
  }
  
  @SideOnly(Side.CLIENT)
  public net.minecraft.item.EnumRarity func_77613_e(ItemStack itemstack)
  {
    return net.minecraft.item.EnumRarity.values()[subItems.get(itemstack.func_77960_j())).rarity];
  }
  
  @SideOnly(Side.CLIENT)
  public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean includeHandlers)
  {
    String location = getBoundDisplayName(stack);
    if ((location != null) && (!location.isEmpty())) {
      list.add(location);
    }
    
    String taglock = ItemsTAGLOCK_KIT.getBoundEntityDisplayName(stack, Integer.valueOf(1));
    if (!taglock.isEmpty()) {
      list.add(String.format(Witchery.resource("item.witcheryTaglockKit.boundto"), new Object[] { taglock }));
    }
    
    if (itemDoorKey.isMatch(stack)) {
      NBTTagCompound nbtTag = stack.func_77978_p();
      if ((nbtTag != null) && (nbtTag.func_74764_b("doorX")) && (nbtTag.func_74764_b("doorY")) && (nbtTag.func_74764_b("doorZ"))) {
        int doorX = nbtTag.func_74762_e("doorX");
        int doorY = nbtTag.func_74762_e("doorY");
        int doorZ = nbtTag.func_74762_e("doorZ");
        if ((nbtTag.func_74764_b("doorD")) && (nbtTag.func_74764_b("doorDN"))) {
          list.add(String.format("%s: %d, %d, %d", new Object[] { nbtTag.func_74779_i("doorDN"), Integer.valueOf(doorX), Integer.valueOf(doorY), Integer.valueOf(doorZ) }));
        } else
          list.add(String.format("%d, %d, %d", new Object[] { Integer.valueOf(doorX), Integer.valueOf(doorY), Integer.valueOf(doorZ) }));
      }
    } else if (itemDoorKeyring.isMatch(stack)) {
      NBTTagCompound nbtTag = stack.func_77978_p();
      if ((nbtTag != null) && (nbtTag.func_74764_b("doorKeys"))) {
        NBTTagList keyList = nbtTag.func_150295_c("doorKeys", 10);
        if (keyList != null) {
          for (int i = 0; i < keyList.func_74745_c(); i++) {
            NBTTagCompound keyTag = keyList.func_150305_b(i);
            if ((keyTag != null) && (keyTag.func_74764_b("doorX")) && (keyTag.func_74764_b("doorY")) && (keyTag.func_74764_b("doorZ"))) {
              int doorX = keyTag.func_74762_e("doorX");
              int doorY = keyTag.func_74762_e("doorY");
              int doorZ = keyTag.func_74762_e("doorZ");
              if ((keyTag.func_74764_b("doorD")) && (keyTag.func_74764_b("doorDN"))) {
                list.add(String.format("%s: %d, %d, %d", new Object[] { keyTag.func_74779_i("doorDN"), Integer.valueOf(doorX), Integer.valueOf(doorY), Integer.valueOf(doorZ) }));
              } else {
                list.add(String.format("%d, %d, %d", new Object[] { Integer.valueOf(doorX), Integer.valueOf(doorY), Integer.valueOf(doorZ) }));
              }
            }
          }
        }
      }
    }
    
    if (stack.func_77960_j() == itemContractTorment.damageValue) {
      String localText = Witchery.resource("item.witchery:ingredient.contractTorment.tip");
      if (localText != null) {
        for (String s : localText.split("\n")) {
          if (!s.isEmpty()) {
            list.add(s);
          }
        }
      }
    }
  }
  
  public String getBoundDisplayName(ItemStack itemstack) {
    NBTTagCompound tag = itemstack.func_77978_p();
    if ((tag != null) && (tag.func_74764_b("PosX")) && (tag.func_74764_b("PosY")) && (tag.func_74764_b("PosZ")) && (tag.func_74764_b("NameD"))) {
      return String.format("%s: %d, %d, %d", new Object[] { tag.func_74779_i("NameD"), Integer.valueOf(tag.func_74762_e("PosX")), Integer.valueOf(tag.func_74762_e("PosY")), Integer.valueOf(tag.func_74762_e("PosZ")) });
    }
    return "";
  }
  
  public void bindToLocation(World world, int posX, int posY, int posZ, int dimension, String dimensionName, ItemStack itemstack)
  {
    if (!itemstack.func_77942_o()) {
      itemstack.func_77982_d(new NBTTagCompound());
    }
    
    itemstack.func_77978_p().func_74768_a("PosX", posX);
    itemstack.func_77978_p().func_74768_a("PosY", posY);
    itemstack.func_77978_p().func_74768_a("PosZ", posZ);
    itemstack.func_77978_p().func_74768_a("PosD", dimension);
    itemstack.func_77978_p().func_74778_a("NameD", dimensionName);
  }
  
  public boolean hasLocationBinding(ItemStack itemstack) {
    if (itemstack.func_77942_o()) {
      NBTTagCompound nbtTag = itemstack.func_77978_p();
      return (nbtTag.func_74764_b("PosX")) && (nbtTag.func_74764_b("PosY")) && (nbtTag.func_74764_b("PosZ")) && (nbtTag.func_74764_b("PosD")) && (nbtTag.func_74764_b("NameD"));
    }
    return false;
  }
  
  public void copyLocationBinding(ItemStack from, ItemStack to)
  {
    if (hasLocationBinding(from)) {
      NBTTagCompound nbtTagFrom = from.func_77978_p();
      if (!to.func_77942_o()) {
        to.func_77982_d(new NBTTagCompound());
      }
      NBTTagCompound nbtTagTo = to.func_77978_p();
      nbtTagTo.func_74768_a("PosX", nbtTagFrom.func_74762_e("PosX"));
      nbtTagTo.func_74768_a("PosY", nbtTagFrom.func_74762_e("PosY"));
      nbtTagTo.func_74768_a("PosZ", nbtTagFrom.func_74762_e("PosZ"));
      nbtTagTo.func_74768_a("PosD", nbtTagFrom.func_74762_e("PosD"));
      nbtTagTo.func_74778_a("NameD", nbtTagFrom.func_74779_i("NameD"));
      if (from.func_82837_s()) {
        to.func_151001_c(from.func_82833_r());
      }
    }
  }
  
  public boolean copyLocationBinding(World world, ItemStack from, BlockCircle.TileEntityCircle.ActivatedRitual to) {
    if (!hasLocationBinding(from)) {
      return false;
    }
    
    NBTTagCompound nbtTagFrom = from.func_77978_p();
    if (nbtTagFrom.func_74762_e("PosD") != field_73011_w.field_76574_g) {
      return false;
    }
    

    Coord coord = new Coord(nbtTagFrom.func_74762_e("PosX"), nbtTagFrom.func_74762_e("PosY"), nbtTagFrom.func_74762_e("PosZ"));
    to.setLocation(coord);
    return true;
  }
  
  public ItemStack func_77654_b(ItemStack itemstack, World world, EntityPlayer player)
  {
    SubItem subItem = (SubItem)subItems.get(itemstack.func_77960_j());
    
    if (itemWaystoneBound.isMatch(itemstack)) {
      if ((!field_72995_K) && ((player instanceof EntityPlayerMP))) {
        Witchery.packetPipeline.sendTo(new PacketCamPos(false, false, null), (EntityPlayerMP)player);
      }
      return itemstack;
    }
    
    if ((subItem instanceof Edible)) {
      if (!field_71075_bZ.field_75098_d) {
        field_77994_a -= 1;
        if (field_77994_a <= 0) {
          field_77994_a = 0;
          field_71071_by.func_70299_a(field_71071_by.field_70461_c, null);
        }
      }
      Edible edible = (Edible)subItem;
      
      if (subItem == itemArtichoke) {
        int foodLevel = player.func_71024_bL().func_75116_a();
        player.func_71024_bL().func_75122_a(healAmount, saturationModifier);
        int healed = player.func_71024_bL().func_75116_a() - foodLevel;
        player.func_70690_d(new PotionEffect(field_76438_sfield_76415_H, 3 * healed * 20, 2));
      } else if (subItem == itemSleepingApple) {
        player.func_71024_bL().func_75122_a(healAmount, saturationModifier);
        if ((field_71093_bK == 0) && (!field_72995_K) && (!WorldProviderDreamWorld.getPlayerIsGhost(player))) {
          WorldProviderDreamWorld.sendPlayerToSpiritWorld(player, 1.0D);
          field_77994_a = 0;
          world.func_72956_a(player, "random.burp", 0.5F, field_73012_v.nextFloat() * 0.1F + 0.9F);
          return player.func_71045_bC() != null ? player.func_71045_bC() : itemstack;
        }
      } else {
        player.func_71024_bL().func_75122_a(healAmount, saturationModifier);
      }
      world.func_72956_a(player, "random.burp", 0.5F, field_73012_v.nextFloat() * 0.1F + 0.9F);
    } else if ((subItem instanceof Drinkable)) {
      if ((itemDemonHeart.isMatch(itemstack)) && (player.func_70093_af()))
      {
        return itemstack;
      }
      if (!field_71075_bZ.field_75098_d) {
        field_77994_a -= 1;
        if (field_77994_a <= 0) {
          field_77994_a = 0;
          field_71071_by.func_70299_a(field_71071_by.field_70461_c, null);
        }
      }
      Drinkable drinkable = (Drinkable)subItem;
      for (PotionEffect effect : effects) {
        player.func_70690_d(new PotionEffect(effect));
      }
      if (itemDemonHeart.isMatch(itemstack)) {
        player.func_70015_d(2640);
      } else if (itemBrewGrotesque.isMatch(itemstack)) {
        if (!field_72995_K) {
          Infusion.getNBT(player).func_74768_a("witcheryGrotesque", 1200);
          Witchery.packetPipeline.sendToDimension(new com.emoniph.witchery.network.PacketPlayerStyle(player), field_71093_bK);
        }
      } else if (itemBrewOfSleeping.isMatch(itemstack)) {
        if ((field_71093_bK == 0) && (!field_72995_K) && (!WorldProviderDreamWorld.getPlayerIsGhost(player))) {
          WorldProviderDreamWorld.sendPlayerToSpiritWorld(player, 0.998D);
          field_77994_a = 0;
          world.func_72956_a(player, "random.burp", 0.5F, field_73012_v.nextFloat() * 0.1F + 0.9F);
          return player.func_71045_bC() != null ? player.func_71045_bC() : itemstack;
        }
      } else if (itemPurifiedMilk.isMatch(itemstack)) {
        if ((!field_72995_K) && 
          (field_73012_v.nextInt(2) == 0)) {
          Collection c = player.func_70651_bq();
          if ((c != null) && (!c.isEmpty())) {
            Object[] objs = c.toArray();
            
            int itemIndex = field_73012_v.nextInt(c.size());
            PotionEffect effect = (PotionEffect)objs[itemIndex];
            player.func_82170_o(effect.func_76456_a());
          }
        }
      }
      else if (itemBrewOfTheDepths.isMatch(itemstack)) {
        if (!field_72995_K) {
          Infusion.getNBT(player).func_74768_a("witcheryDepths", 300);
        }
      }
      else if (itemCreeperHeart.isMatch(itemstack)) {
        if (!field_72995_K) {
          if (instanceallowExplodingCreeperHearts) {
            world.func_72876_a(player, field_70165_t, field_70163_u, field_70161_v, 3.0F, true);
          } else {
            world.func_72876_a(player, field_70165_t, field_70163_u, field_70161_v, 1.0F, false);
          }
        }
      } else if (itemFrozenHeart.isMatch(itemstack)) {
        if (!field_72995_K) {
          PlayerEffects.onDeath(player);
        }
      } else {
        drinkable.onDrunk(world, player, itemstack);
      }
      world.func_72956_a(player, "random.burp", 0.5F, field_73012_v.nextFloat() * 0.1F + 0.9F);
    }
    return itemstack;
  }
  
  public int func_77626_a(ItemStack itemstack)
  {
    SubItem subItem = (SubItem)subItems.get(itemstack.func_77960_j());
    if (((subItem instanceof Edible)) || ((subItem instanceof Drinkable)))
      return 32;
    if (itemWaystoneBound.isMatch(itemstack))
      return 1200;
    if (itemContractTorment.isMatch(itemstack))
      return 1200;
    if (itemSeerStone.isMatch(itemstack)) {
      return 1200;
    }
    return super.func_77626_a(itemstack);
  }
  

  public EnumAction func_77661_b(ItemStack itemstack)
  {
    SubItem subItem = (SubItem)subItems.get(itemstack.func_77960_j());
    if ((subItem instanceof Edible))
      return EnumAction.eat;
    if ((subItem instanceof Drinkable))
      return useAction;
    if (itemContractTorment.isMatch(itemstack))
      return EnumAction.bow;
    if (itemSeerStone.isMatch(itemstack)) {
      return EnumAction.bow;
    }
    return super.func_77661_b(itemstack);
  }
  

  public void onUsingTick(ItemStack stack, EntityPlayer player, int countdown)
  {
    World world = field_70170_p;
    int elapsedTicks = func_77626_a(stack) - countdown;
    if ((!field_72995_K) && ((player instanceof EntityPlayerMP))) {
      if (itemWaystoneBound.isMatch(stack)) {
        if (elapsedTicks % 20 == 0) {
          if (elapsedTicks == 0) {
            NBTTagCompound tag = stack.func_77978_p();
            if ((tag != null) && (tag.func_74764_b("PosX")) && (tag.func_74764_b("PosY")) && (tag.func_74764_b("PosZ")) && (tag.func_74764_b("PosD"))) {
              int newX = tag.func_74762_e("PosX");
              int newY = tag.func_74762_e("PosY");
              int newZ = tag.func_74762_e("PosZ");
              int newD = tag.func_74762_e("PosD");
              
              EntityEye eye = new EntityEye(world);
              eye.func_70012_b(newX, newY, newZ, field_70177_z, 90.0F);
              world.func_72838_d(eye);
              
              Witchery.packetPipeline.sendTo(new PacketCamPos(true, elapsedTicks == 0, eye), (EntityPlayerMP)player);
            }
          } else {
            Witchery.packetPipeline.sendTo(new PacketCamPos(true, false, null), (EntityPlayerMP)player);
          }
        }
      } else if (itemContractTorment.isMatch(stack)) {
        if (!field_72995_K) {
          if ((elapsedTicks == 0) || (elapsedTicks == 40)) {
            if (Infusion.aquireEnergy(world, player, 10, true)) {
              if ((elapsedTicks > 0) || (circleNear(world, player))) {
                SoundEffect.MOB_BLAZE_DEATH.playAtPlayer(world, player);
              } else {
                SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
                ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "item.witchery:ingredient.contractTorment.nostones", new Object[0]);
                player.func_71041_bz();
              }
            }
          } else if ((elapsedTicks == 80) || (elapsedTicks == 120)) {
            if (Infusion.aquireEnergy(world, player, 10, true))
            {
              ParticleEffect.MOB_SPELL.send(SoundEffect.MOB_BLAZE_DEATH, player, 1.0D, 2.0D, 16);

            }
            

          }
          else if ((elapsedTicks == 160) || (elapsedTicks == 200) || (elapsedTicks == 240)) {
            if (Infusion.aquireEnergy(world, player, 10, true))
            {
              ParticleEffect.MOB_SPELL.send(SoundEffect.MOB_BLAZE_DEATH, player, 1.0D, 2.0D, 16);
              ParticleEffect.FLAME.send(SoundEffect.NONE, player, 1.0D, 2.0D, 16);

            }
            

          }
          else if ((elapsedTicks == 280) && 
            (Infusion.aquireEnergy(world, player, 10, true))) {
            if (circleNear(world, player)) {
              ParticleEffect.MOB_SPELL.send(SoundEffect.NONE, player, 1.0D, 2.0D, 16);
              ParticleEffect.FLAME.send(SoundEffect.NONE, player, 1.0D, 2.0D, 16);
              ParticleEffect.FLAME.send(SoundEffect.NONE, player, 1.0D, 2.0D, 16);
              player.func_71041_bz();
              EntityLiving living = InfusionInfernal.spawnCreature(world, EntityLordOfTorment.class, (int)field_70165_t, (int)field_70163_u, (int)field_70161_v, null, 2, 4, ParticleEffect.FLAME, SoundEffect.MOB_ENDERDRAGON_GROWL);
              if (living != null) {
                if (!field_71075_bZ.field_75098_d) {
                  field_77994_a -= 1;
                  if (field_77994_a <= 0) {
                    field_71071_by.func_70299_a(field_71071_by.field_70461_c, null);
                  }
                }
                living.func_110163_bv();
                world.func_72885_a(living, field_70165_t, field_70163_u + living.func_70047_e(), field_70161_v, 7.0F, false, world.func_82736_K().func_82766_b("mobGriefing"));
              } else {
                SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
              }
            }
            else {
              SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
              player.func_71041_bz();
            }
          }
        }
      }
      else if (itemSeerStone.isMatch(stack))
      {
        EntityCovenWitch.summonCoven(world, player, new Coord(player), elapsedTicks);
      }
    }
  }
  
  private boolean circleNear(World world, EntityPlayer player) {
    int midX = MathHelper.func_76128_c(field_70165_t);
    int midY = MathHelper.func_76128_c(field_70163_u);
    int midZ = MathHelper.func_76128_c(field_70161_v);
    
    int[][] PATTERN = { { 0, 0, 0, 0, 4, 3, 4, 0, 0, 0, 0 }, { 0, 0, 4, 3, 1, 1, 1, 3, 4, 0, 0 }, { 0, 4, 1, 1, 1, 1, 1, 1, 1, 4, 0 }, { 0, 3, 1, 1, 1, 1, 1, 1, 1, 3, 0 }, { 4, 1, 1, 1, 2, 2, 2, 1, 1, 1, 4 }, { 3, 1, 1, 1, 2, 1, 2, 1, 1, 1, 3 }, { 4, 1, 1, 1, 2, 2, 2, 1, 1, 1, 4 }, { 0, 3, 1, 1, 1, 1, 1, 1, 1, 3, 0 }, { 0, 4, 1, 1, 1, 1, 1, 1, 1, 4, 0 }, { 0, 0, 4, 3, 1, 1, 1, 3, 4, 0, 0 }, { 0, 0, 0, 0, 4, 3, 4, 0, 0, 0, 0 } };
    












    int offsetZ = (PATTERN.length - 1) / 2;
    for (int z = 0; z < PATTERN.length - 1; z++) {
      int worldZ = midZ - offsetZ + z;
      int offsetX = (PATTERN[z].length - 1) / 2;
      for (int x = 0; x < PATTERN[z].length; x++) {
        int worldX = midX - offsetX + x;
        int value = PATTERN[(PATTERN.length - 1 - z)][x];
        if (value != 0) {
          if (!isPost(world, worldX, midY, worldZ, (value == 2) || (value == 4), value == 4, (value == 3) || (value == 4))) {
            return false;
          }
        }
      }
    }
    
    return true;
  }
  
  private boolean isPost(World world, int x, int y, int z, boolean bottomSolid, boolean midSolid, boolean topSolid) {
    Block blockBelow = BlockUtil.getBlock(world, x, y - 1, z);
    Block blockBottom = BlockUtil.getBlock(world, x, y, z);
    Block blockMid = BlockUtil.getBlock(world, x, y + 1, z);
    Block blockTop = BlockUtil.getBlock(world, x, y + 2, z);
    Block blockAbove = BlockUtil.getBlock(world, x, y + 3, z);
    
    if ((blockBelow == null) || (!blockBelow.func_149688_o().func_76220_a())) {
      return false;
    }
    
    if (bottomSolid) {
      if ((blockBottom == null) || (!blockBottom.func_149688_o().func_76220_a())) {
        return false;
      }
      
    }
    else if ((blockBottom != null) && (blockBottom.func_149688_o().func_76220_a())) {
      return false;
    }
    

    if (midSolid) {
      if ((blockMid == null) || (!blockMid.func_149688_o().func_76220_a())) {
        return false;
      }
      
    }
    else if ((blockMid != null) && (blockMid.func_149688_o().func_76220_a())) {
      return false;
    }
    

    if (topSolid) {
      if ((blockTop == null) || (!blockTop.func_149688_o().func_76220_a())) {
        return false;
      }
      
    }
    else if ((blockTop != null) && (blockTop.func_149688_o().func_76220_a())) {
      return false;
    }
    

    return (blockAbove == null) || (!blockAbove.func_149688_o().func_76220_a());
  }
  
  public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
  {
    Block block = BlockUtil.getBlock(world, x, y, z);
    if ((itemWaystoneBound.isMatch(stack)) && (block == BlocksCRYSTAL_BALL)) {
      if ((!field_72995_K) && (BlockCrystalBall.tryConsumePower(world, player, x, y, z))) {
        NBTTagCompound tag = stack.func_77978_p();
        if ((tag != null) && (tag.func_74764_b("PosX")) && (tag.func_74764_b("PosY")) && (tag.func_74764_b("PosZ")) && (tag.func_74764_b("PosD"))) {
          int newX = tag.func_74762_e("PosX");
          int newY = tag.func_74762_e("PosY");
          int newZ = tag.func_74762_e("PosZ");
          int newD = tag.func_74762_e("PosD");
          double MAX_DISTANCE = 22500.0D;
          if ((newD == field_71093_bK) && (player.func_70092_e(newX, newY, newZ) <= 22500.0D)) {
            player.func_71008_a(stack, func_77626_a(stack));
          } else {
            SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
          }
        } else {
          SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
        }
      } else if (field_72995_K) {
        player.func_71008_a(stack, func_77626_a(stack));
      }
      return !field_72995_K;
    }
    return super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
  }
  
  public void func_77615_a(ItemStack stack, World world, EntityPlayer player, int countdown)
  {
    if ((!field_72995_K) && (itemWaystoneBound.isMatch(stack)) && ((player instanceof EntityPlayerMP))) {
      Witchery.packetPipeline.sendTo(new PacketCamPos(false, false, null), (EntityPlayerMP)player);
    }
  }
  
  public boolean isBook(ItemStack itemstack) {
    return (itemBookOven.isMatch(itemstack)) || (itemBookDistilling.isMatch(itemstack)) || (itemBookCircleMagic.isMatch(itemstack)) || (itemBookInfusions.isMatch(itemstack)) || (itemBookHerbology.isMatch(itemstack)) || (itemBookBiomes.isMatch(itemstack)) || (itemBookWands.isMatch(itemstack)) || (itemBookBurning.isMatch(itemstack));
  }
  


  public ItemStack func_77659_a(ItemStack itemstack, World world, EntityPlayer player)
  {
    SubItem subItem = (SubItem)subItems.get(itemstack.func_77960_j());
    if (isBook(itemstack)) {
      openWitchcraftBook(world, player, itemstack);
    } else if (itemWolfsbane.isMatch(itemstack)) {
      if (!field_72995_K) {
        MovingObjectPosition mop = InfusionOtherwhere.raytraceEntities(world, player, true, 2.0D);
        if ((mop != null) && (field_72308_g != null)) {
          if (CreatureUtil.isWerewolf(field_72308_g, true)) {
            ParticleEffect.FLAME.send(SoundEffect.WITCHERY_MOB_WOLFMAN_HOWL, field_72308_g, 0.5D, 1.5D, 16);
          } else {
            SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
          }
          field_77994_a -= 1;
          if (field_77994_a <= 0) {
            field_77994_a = 0;
            field_71071_by.func_70299_a(field_71071_by.field_70461_c, null);
          }
        }
      }
    } else if ((subItem instanceof Edible)) {
      if ((player.func_71043_e(false)) || (eatAnyTime)) {
        player.func_71008_a(itemstack, func_77626_a(itemstack));
      }
    } else if ((subItem instanceof Drinkable)) {
      player.func_71008_a(itemstack, func_77626_a(itemstack));
    } else if (itemContractTorment.isMatch(itemstack)) {
      player.func_71008_a(itemstack, func_77626_a(itemstack));
    } else if ((subItem instanceof Brew)) {
      throwBrew(itemstack, world, player, subItem);
    } else if (itemQuicklime.isMatch(itemstack)) {
      throwBrew(itemstack, world, player, itemQuicklime);
    } else if (itemNecroStone.isMatch(itemstack)) {
      useNecroStone(world, player, itemstack);
    }
    else if (itemBatBall.isMatch(itemstack)) {
      field_77994_a -= 1;
      if (field_77994_a <= 0) {
        field_71071_by.func_70299_a(field_71071_by.field_70461_c, null);
      }
      if (!field_72995_K) {
        EntityItem item = new EntityItem(world, field_70165_t, field_70163_u + 1.3D, field_70161_v, itemBatBall.createStack());
        field_145804_b = 5;
        item.func_70012_b(field_70165_t, field_70163_u + player.func_70047_e(), field_70161_v, field_70177_z, field_70125_A);
        field_70165_t -= MathHelper.func_76134_b(field_70177_z / 180.0F * 3.1415927F) * 0.16F;
        field_70163_u -= 0.10000000149011612D;
        field_70161_v -= MathHelper.func_76126_a(field_70177_z / 180.0F * 3.1415927F) * 0.16F;
        item.func_70107_b(field_70165_t, field_70163_u, field_70161_v);
        field_70129_M = 0.0F;
        float f = 0.4F;
        field_70159_w = (-MathHelper.func_76126_a(field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(field_70125_A / 180.0F * 3.1415927F) * f);
        field_70179_y = (MathHelper.func_76134_b(field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(field_70125_A / 180.0F * 3.1415927F) * f);
        field_70181_x = (-MathHelper.func_76126_a((field_70125_A + 0.0F) / 180.0F * 3.1415927F) * f);
        setThrowableHeading(item, field_70159_w, field_70181_x, field_70179_y, 1.0F, 1.0F);
        world.func_72838_d(item);
      }
    } else if (itemSeerStone.isMatch(itemstack)) {
      useSeerStone(world, player, itemstack);
    } else if (itemIcyNeedle.isMatch(itemstack)) {
      useIcyNeedle(world, player, itemstack);
      return player.func_71045_bC() != null ? player.func_71045_bC() : itemstack;
    }
    
    return super.func_77659_a(itemstack, world, player);
  }
  
  public void setThrowableHeading(EntityItem item, double par1, double par3, double par5, float par7, float par8)
  {
    float f2 = MathHelper.func_76133_a(par1 * par1 + par3 * par3 + par5 * par5);
    par1 /= f2;
    par3 /= f2;
    par5 /= f2;
    par1 += field_70170_p.field_73012_v.nextGaussian() * 0.007499999832361937D * par8;
    par3 += field_70170_p.field_73012_v.nextGaussian() * 0.007499999832361937D * par8;
    par5 += field_70170_p.field_73012_v.nextGaussian() * 0.007499999832361937D * par8;
    par1 *= par7;
    par3 *= par7;
    par5 *= par7;
    field_70159_w = par1;
    field_70181_x = par3;
    field_70179_y = par5;
    float f3 = MathHelper.func_76133_a(par1 * par1 + par5 * par5);
    field_70126_B = (item.field_70177_z = (float)(Math.atan2(par1, par5) * 180.0D / 3.141592653589793D));
    field_70127_C = (item.field_70125_A = (float)(Math.atan2(par3, f3) * 180.0D / 3.141592653589793D));
  }
  
  private void useIcyNeedle(World world, EntityPlayer player, ItemStack itemstack)
  {
    if (!field_71075_bZ.field_75098_d) {
      field_77994_a -= 1;
      if (field_77994_a <= 0) {
        field_77994_a = 0;
        field_71071_by.func_70299_a(field_71071_by.field_70461_c, null);
      }
    }
    
    if (field_73011_w.field_76574_g == instancedimensionDreamID) {
      WorldProviderDreamWorld.returnPlayerToOverworld(player);
      field_77994_a = 0;
    } else if (WorldProviderDreamWorld.getPlayerIsGhost(player)) {
      WorldProviderDreamWorld.returnGhostPlayerToSpiritWorld(player);
      field_77994_a = 0;
    } else {
      player.func_70097_a(DamageSource.func_76365_a(player), 1.0F);
    }
  }
  
  public void throwBrew(ItemStack itemstack, World world, EntityPlayer player) {
    if ((itemstack != null) && (itemstack.func_77973_b() == this)) {
      SubItem subItem = (SubItem)subItems.get(itemstack.func_77960_j());
      throwBrew(itemstack, world, player, subItem);
    }
  }
  
  private void throwBrew(ItemStack itemstack, World world, EntityPlayer player, SubItem item) {
    if (!field_71075_bZ.field_75098_d) {
      field_77994_a -= 1;
    }
    
    world.func_72956_a(player, "random.bow", 0.5F, 0.4F / (field_77697_d.nextFloat() * 0.4F + 0.8F));
    
    if (!field_72995_K) {
      world.func_72838_d(new com.emoniph.witchery.entity.EntityWitchProjectile(world, player, item));
    }
  }
  
  private void openWitchcraftBook(World world, EntityPlayer player, ItemStack itemstack) {
    int posX = MathHelper.func_76128_c(field_70165_t);
    int posY = MathHelper.func_76128_c(field_70163_u);
    int posZ = MathHelper.func_76128_c(field_70161_v);
    player.openGui(Witchery.instance, 1, world, posX, posY, posZ);
  }
  
  public boolean func_77648_a(ItemStack itemstack, EntityPlayer player, World world, int posX, int posY, int posZ, int side, float par8, float par9, float par10)
  {
    if (itemMutandis.isMatch(itemstack))
      return useMutandis(false, itemstack, player, world, posX, posY, posZ);
    if (itemAnnointingPaste.isMatch(itemstack))
      return useAnnointingPaste(itemstack, player, world, posX, posY, posZ);
    if (itemKobolditePentacle.isMatch(itemstack)) {
      if ((world.func_147439_a(posX, posY, posZ) == BlocksALTAR) && (side == 1) && (world.func_147439_a(posX, posY + 1, posZ) == Blocks.field_150350_a)) {
        BlockPlacedItem.placeItemInWorld(itemstack, player, world, posX, posY + 1, posZ);
        field_71071_by.func_70299_a(field_71071_by.field_70461_c, null);
        return !field_72995_K;
      }
      return super.func_77648_a(itemstack, player, world, posX, posY, posZ, side, par8, par9, par10); }
    if (itemMutandisExtremis.isMatch(itemstack))
      return useMutandis(true, itemstack, player, world, posX, posY, posZ);
    if ((itemChaliceEmpty.isMatch(itemstack)) || (itemChaliceFull.isMatch(itemstack)))
      return placeBlock(BlocksCHALICE, itemstack, player, world, posX, posY, posZ, side, par8, par9, par10);
    if (itemCandelabra.isMatch(itemstack))
      return placeBlock(BlocksCANDELABRA, itemstack, player, world, posX, posY, posZ, side, par8, par9, par10);
    if (itemBroomEnchanted.isMatch(itemstack))
      return placeBroom(itemstack, player, world, posX, posY, posZ, side, par8, par9, par10);
    if ((subItems.get(itemstack.func_77960_j()) instanceof DreamWeave))
      return placeDreamCatcher(world, player, itemstack, posX, posY, posZ, side);
    if (itemDoorRowan.isMatch(itemstack))
      return placeDoor(world, player, itemstack, posX, posY, posZ, side, BlocksDOOR_ROWAN);
    if (itemDoorAlder.isMatch(itemstack))
      return placeDoor(world, player, itemstack, posX, posY, posZ, side, BlocksDOOR_ALDER);
    if (itemDoorIce.isMatch(itemstack))
      return placeDoor(world, player, itemstack, posX, posY, posZ, side, BlocksPERPETUAL_ICE_DOOR);
    if ((itemSubduedSpirit.isMatch(itemstack)) || (itemSubduedSpiritVillage.isMatch(itemstack)))
      return useSubduedSpirit(world, player, itemstack, posX, posY, posZ, side);
    if (itemSeedsTreefyd.isMatch(itemstack))
      return placeTreefyd(world, player, itemstack, posX, posY, posZ, side);
    if (itemBinkyHead.isMatch(itemstack))
      return placeBinky(world, player, itemstack, posX, posY, posZ, side);
    if (itemInfernalBlood.isMatch(itemstack))
      return placeInfernalBlood(world, player, itemstack, posX, posY, posZ, side);
    if ((itemDemonHeart.isMatch(itemstack)) && (player.func_70093_af()))
      return placeBlock(BlocksDEMON_HEART, itemstack, player, world, posX, posY, posZ, side, par8, par9, par10);
    if ((itemBoneNeedle.isMatch(itemstack)) && (ExtendedPlayer.get(player).isVampire())) {
      Block block = world.func_147439_a(posX, posY, posZ);
      if ((block == Blocks.field_150325_L) && (world.func_72805_g(posX, posY, posZ) == 0)) {
        ExtendedPlayer playerEx = ExtendedPlayer.get(player);
        if ((playerEx.getVampireLevel() >= 4) && 
          (playerEx.decreaseBloodPower(125, true))) {
          world.func_147449_b(posX, posY, posZ, BlocksBLOODED_WOOL);
          ParticleEffect.REDDUST.send(SoundEffect.WITCHERY_RANDOM_DRINK, world, posX + 0.5D, posY + 0.5D, posZ + 0.5D, 1.0D, 1.0D, 16);
          
          return true;
        }
      }
      

      SoundEffect.NOTE_SNARE.playOnlyTo(player);
      return true;
    }
    return super.func_77648_a(itemstack, player, world, posX, posY, posZ, side, par8, par9, par10);
  }
  
  private boolean placeBinky(World world, EntityPlayer player, ItemStack itemstack, int posX, int posY, int posZ, int side)
  {
    if (side != 1) {
      return false;
    }
    posY++;
    Material material = world.func_147439_a(posX, posY, posZ).func_149688_o();
    if ((material == null) || (!material.func_76220_a())) {
      if (!field_72995_K) {
        EntityDeathsHorse horse = new EntityDeathsHorse(world);
        horse.func_110234_j(true);
        horse.func_110214_p(4);
        horse.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3D);
        horse.func_110163_bv();
        horse.func_94058_c(Witchery.resource("item.witchery.horseofdeath.customname"));
        horse.func_70012_b(0.5D + posX, 0.01D + posY, 0.5D + posZ, 0.0F, 0.0F);
        NBTTagCompound nbtHorse = horse.getEntityData();
        if (nbtHorse != null) {
          nbtHorse.func_74757_a("WITCIsBinky", true);
        }
        
        ParticleEffect.INSTANT_SPELL.send(SoundEffect.NONE, world, 0.5D + posX, posY, 0.5D + posZ, 1.0D, 1.0D, 16);
        

        world.func_72838_d(horse);
      }
      field_77994_a -= 1;
    }
    return true;
  }
  
  private boolean placeInfernalBlood(World world, EntityPlayer player, ItemStack itemstack, int posX, int posY, int posZ, int side)
  {
    Block block = world.func_147439_a(posX, posY, posZ);
    int meta = world.func_72805_g(posX, posY, posZ);
    if ((block == BlocksWICKER_BUNDLE) && (BlockWickerBundle.limitToValidMetadata(meta) == 0)) {
      if (!field_72995_K) {
        int uses = 5;
        for (int y = posY - 1; (y <= posY + 1) && (uses > 0); y++) {
          for (int x = posX - 1; (x <= posX + 1) && (uses > 0); x++) {
            for (int z = posZ - 1; (z <= posZ + 1) && (uses > 0); z++) {
              Block b = world.func_147439_a(x, y, z);
              int m = world.func_72805_g(x, y, z);
              if ((b == BlocksWICKER_BUNDLE) && (BlockWickerBundle.limitToValidMetadata(m) == 0)) {
                world.func_147465_d(x, y, z, b, m | 0x1, 3);
                uses--;
              }
            }
          }
        }
      }
      
      field_77994_a -= 1;
      return true;
    }
    return false;
  }
  
  private boolean placeTreefyd(World world, EntityPlayer player, ItemStack itemstack, int posX, int posY, int posZ, int side)
  {
    if (side != 1) {
      return false;
    }
    posY++;
    Material material = world.func_147439_a(posX, posY, posZ).func_149688_o();
    if ((Blocks.field_150329_H.func_149718_j(world, posX, posY, posZ)) && ((material == null) || (!material.func_76220_a()))) {
      if (!field_72995_K) {
        world.func_147465_d(posX, posY, posZ, Blocks.field_150329_H, 1, 3);
        EntityTreefyd entity = new EntityTreefyd(world);
        entity.func_70012_b(0.5D + posX, posY, 0.5D + posZ, 0.0F, 0.0F);
        entity.func_110161_a(null);
        entity.func_110163_bv();
        entity.setOwner(player.func_70005_c_());
        world.func_72838_d(entity);
        
        ParticleEffect.SLIME.send(SoundEffect.MOB_SILVERFISH_KILL, entity, 1.0D, 2.0D, 16);
        ParticleEffect.EXPLODE.send(SoundEffect.NONE, entity, 1.0D, 2.0D, 16);
      }
      field_77994_a -= 1;
    }
    return true;
  }
  
  private boolean useSeerStone(World world, EntityPlayer player, ItemStack stack)
  {
    if (player.func_70093_af()) {
      if (!field_72995_K) {
        double MAX_TARGET_RANGE = 3.0D;
        MovingObjectPosition mop = InfusionOtherwhere.doCustomRayTrace(world, player, true, 3.0D);
        if (mop != null) {
          switch (10.$SwitchMap$net$minecraft$util$MovingObjectPosition$MovingObjectType[field_72313_a.ordinal()]) {
          case 1: 
            if (!(field_72308_g instanceof EntityPlayer)) break;
            readPlayer(player, (EntityPlayer)field_72308_g);
            return true;
          

          default: 
            readPlayer(player, player);
            break;
          }
        } else {
          readPlayer(player, player);
        }
      }
    } else {
      player.func_71008_a(stack, func_77626_a(stack));
    }
    
    return true;
  }
  
  private void readPlayer(EntityPlayer player, EntityPlayer targetPlayer) {
    String reading = "";
    NBTTagCompound nbtPlayer = Infusion.getNBT(targetPlayer);
    
    if ((nbtPlayer != null) && (nbtPlayer.func_74764_b("WITCManifestDuration"))) {
      int timeRemaining = nbtPlayer.func_74762_e("WITCManifestDuration");
      if (timeRemaining > 0) {
        reading = reading + String.format(Witchery.resource("item.witchery:ingredient.seerstone.manifestationtime"), new Object[] { Integer.valueOf(timeRemaining).toString() }) + " ";
      } else {
        reading = reading + Witchery.resource("item.witchery:ingredient.seerstone.nomanifestationtime") + " ";
      }
    } else {
      reading = reading + Witchery.resource("item.witchery:ingredient.seerstone.nomanifestationtime") + " ";
    }
    
    String familiarName = com.emoniph.witchery.familiar.Familiar.getFamiliarName(targetPlayer);
    if ((familiarName != null) && (!familiarName.isEmpty())) {
      reading = reading + String.format(Witchery.resource("item.witchery:ingredient.seerstone.familiar"), new Object[] { familiarName }) + " ";
    } else {
      reading = reading + Witchery.resource("item.witchery:ingredient.seerstone.nofamiliar") + " ";
    }
    
    int covenSize = EntityCovenWitch.getCovenSize(targetPlayer);
    if (covenSize > 0) {
      reading = reading + String.format(Witchery.resource("item.witchery:ingredient.seerstone.covensize"), new Object[] { Integer.valueOf(covenSize).toString() }) + " ";
    } else {
      reading = reading + Witchery.resource("item.witchery:ingredient.seerstone.nocoven") + " ";
    }
    
    String spellKnowledge = SymbolEffect.getKnowledge(targetPlayer);
    if (!spellKnowledge.isEmpty()) {
      reading = reading + String.format(Witchery.resource("item.witchery:ingredient.seerstone.knownspells"), new Object[] { spellKnowledge }) + " ";
    } else {
      reading = reading + Witchery.resource("item.witchery:ingredient.seerstone.nospells") + " ";
    }
    
    ExtendedPlayer playerEx = ExtendedPlayer.get(targetPlayer);
    if (playerEx != null) {
      int bottlingSkill = playerEx.getSkillPotionBottling();
      reading = reading + String.format(Witchery.resource("item.witchery:ingredient.seerstone.bottlingskill"), new Object[] { Integer.valueOf(bottlingSkill).toString() }) + " ";
    }
    
    if ((nbtPlayer != null) && ((nbtPlayer.func_74764_b("witcheryCursed")) || (nbtPlayer.func_74764_b("witcheryInsanity")) || (nbtPlayer.func_74764_b("witcherySinking")) || (nbtPlayer.func_74764_b("witcheryOverheating")) || (nbtPlayer.func_74764_b("witcheryWakingNightmare"))))
    {
      if (nbtPlayer.func_74764_b("witcheryCursed")) {
        int level = nbtPlayer.func_74762_e("witcheryCursed");
        if (!reading.isEmpty()) {
          reading = reading + ", ";
        }
        reading = reading + String.format(Witchery.resource("witchery.item.seerstone.misfortune"), new Object[] { Integer.valueOf(level) });
      }
      
      if (nbtPlayer.func_74764_b("witcheryInsanity")) {
        int level = nbtPlayer.func_74762_e("witcheryInsanity");
        if (!reading.isEmpty()) {
          reading = reading + ", ";
        }
        reading = reading + String.format(Witchery.resource("witchery.item.seerstone.insanity"), new Object[] { Integer.valueOf(level) });
      }
      
      if (nbtPlayer.func_74764_b("witcherySinking")) {
        int level = nbtPlayer.func_74762_e("witcherySinking");
        if (!reading.isEmpty()) {
          reading = reading + ", ";
        }
        reading = reading + String.format(Witchery.resource("witchery.item.seerstone.sinking"), new Object[] { Integer.valueOf(level) });
      }
      
      if (nbtPlayer.func_74764_b("witcheryOverheating")) {
        int level = nbtPlayer.func_74762_e("witcheryOverheating");
        if (!reading.isEmpty()) {
          reading = reading + ", ";
        }
        reading = reading + String.format(Witchery.resource("witchery.item.seerstone.overheating"), new Object[] { Integer.valueOf(level) });
      }
      
      if (nbtPlayer.func_74764_b("witcheryWakingNightmare")) {
        int level = nbtPlayer.func_74762_e("witcheryWakingNightmare");
        if (!reading.isEmpty()) {
          reading = reading + ", ";
        }
        reading = reading + String.format(Witchery.resource("witchery.item.seerstone.nightmare"), new Object[] { Integer.valueOf(level) });
      }
    }
    else {
      reading = reading + Witchery.resource("witchery.item.seerstone.notcursed");
    }
    
    ChatUtil.sendPlain(EnumChatFormatting.BLUE, player, reading);
  }
  
  private boolean useSubduedSpirit(World world, EntityPlayer player, ItemStack itemstack, int x, int y, int z, int side) {
    if (!field_72995_K) {
      EntityCreature creature = Infusion.spawnCreature(world, EntitySpirit.class, x, y, z, null, 0, 0, ParticleEffect.INSTANT_SPELL, null);
      if (creature != null) {
        EntitySpirit spirit = (EntitySpirit)creature;
        creature.func_110163_bv();
        
        if (itemSubduedSpiritVillage.isMatch(itemstack)) {
          spirit.setTarget("Village", 1);
        }
        
        if (!field_71075_bZ.field_75098_d) {
          if (--field_77994_a == 0) {
            field_71071_by.func_70299_a(field_71071_by.field_70461_c, null);
            if ((player instanceof EntityPlayerMP)) {
              ((EntityPlayerMP)player).func_71120_a(field_71069_bz);
            }
          }
        }
      }
      return true;
    }
    return false;
  }
  
  private boolean useNecroStone(World world, EntityPlayer player, ItemStack itemstack)
  {
    if (field_72995_K) {
      return false;
    }
    
    double MAX_TARGET_RANGE = 15.0D;
    MovingObjectPosition mop = InfusionOtherwhere.doCustomRayTrace(world, player, true, 15.0D);
    
    if (mop != null) {
      switch (10.$SwitchMap$net$minecraft$util$MovingObjectPosition$MovingObjectType[field_72313_a.ordinal()]) {
      case 1: 
        if ((field_72308_g instanceof EntityLivingBase)) {
          if (!player.func_70093_af()) {
            EntityLivingBase targetEntityLivingBase = (EntityLivingBase)field_72308_g;
            int r = 50;
            int minionCount = 0;
            AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(field_70165_t - 50.0D, field_70163_u - 15.0D, field_70161_v - 50.0D, field_70165_t + 50.0D, field_70163_u + 15.0D, field_70161_v + 50.0D);
            
            for (Object obj : world.func_72872_a(EntityLiving.class, bounds)) {
              EntityLiving nearbyLivingEntity = (EntityLiving)obj;
              if ((nearbyLivingEntity.func_70668_bt() == EnumCreatureAttribute.UNDEAD) && 
                (PotionEnslaved.isMobEnslavedBy(nearbyLivingEntity, player))) {
                minionCount++;
                
                com.emoniph.witchery.util.EntityUtil.setTarget(nearbyLivingEntity, targetEntityLivingBase);
              }
            }
            











            if (minionCount > 0) {
              Witchery.packetPipeline.sendToAllAround(new PacketParticles(ParticleEffect.CRIT, SoundEffect.MOB_ZOMBIE_DEATH, targetEntityLivingBase, 0.5D, 2.0D), TargetPointUtil.from(targetEntityLivingBase, 16.0D));
              
              return true;
            }
          } else {
            if ((InfusedBrewEffect.Grave.isActive(player)) && 
              (InfusedBrewEffect.Grave.tryUseEffect(player, mop))) {
              Witchery.packetPipeline.sendToAllAround(new PacketParticles(ParticleEffect.MOB_SPELL, SoundEffect.MOB_ZOMBIE_INFECT, field_72308_g, 1.0D, 1.0D), TargetPointUtil.from(field_72308_g, 16.0D));
              
              return true;
            }
            

            Witchery.packetPipeline.sendToAllAround(new PacketParticles(ParticleEffect.SMOKE, SoundEffect.NOTE_SNARE, field_72308_g, 1.0D, 1.0D), TargetPointUtil.from(field_72308_g, 16.0D));
          }
        }
        
        break;
      case 2: 
        if (world.func_147439_a(field_72311_b, field_72312_c, field_72309_d) == BlocksALLURING_SKULL) {
          return false;
        }
        if (BlockSide.TOP.isEqual(field_72310_e)) {
          int minionCount = 0;
          int r = 50;
          AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(field_70165_t - 50.0D, field_70163_u - 15.0D, field_70161_v - 50.0D, field_70165_t + 50.0D, field_70163_u + 15.0D, field_70161_v + 50.0D);
          
          for (Object obj : world.func_72872_a(EntityLiving.class, bounds)) {
            EntityLiving creature = (EntityLiving)obj;
            EntityCreature creature2 = (creature instanceof EntityCreature) ? (EntityCreature)creature : null;
            if ((creature.func_70668_bt() == EnumCreatureAttribute.UNDEAD) && 
              (PotionEnslaved.isMobEnslavedBy(creature, player))) {
              minionCount++;
              creature.func_70624_b(null);
              creature.func_70604_c(null);
              if (creature2 != null)
                creature2.func_70784_b(null);
              if ((((creature instanceof EntitySpider)) || (!creature.func_70661_as().func_75492_a(field_72311_b, field_72312_c + 1, field_72309_d, 1.0D))) && 
                (creature2 != null)) {
                creature2.func_70778_a(world.func_72844_a(creature, field_72311_b, field_72312_c + 1, field_72309_d, 10.0F, true, false, false, true));
              }
            }
          }
          


          if (minionCount > 0) {
            ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_POP, world, 0.5D + field_72311_b, field_72312_c + 1, 0.5D + field_72309_d, 1.0D, 1.0D, 16);
            
            return true;
          } }
        break;
      }
      
    }
    




    SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
    
    return false;
  }
  
  private boolean placeDoor(World world, EntityPlayer player, ItemStack itemstack, int posX, int posY, int posZ, int side, Block block) {
    if (side != 1) {
      return false;
    }
    posY++;
    
    if ((player.func_82247_a(posX, posY, posZ, side, itemstack)) && (player.func_82247_a(posX, posY + 1, posZ, side, itemstack))) {
      if (!block.func_149742_c(world, posX, posY, posZ)) {
        return false;
      }
      int i1 = MathHelper.func_76128_c((field_70177_z + 180.0F) * 4.0F / 360.0F - 0.5D) & 0x3;
      net.minecraft.item.ItemDoor.func_150924_a(world, posX, posY, posZ, i1, block);
      
      if ((!field_72995_K) && (itemDoorRowan.isMatch(itemstack))) {
        ItemStack keyStack = ItemsGENERIC.itemDoorKey.createStack();
        if (!keyStack.func_77942_o()) {
          keyStack.func_77982_d(new NBTTagCompound());
        }
        NBTTagCompound nbtTag = keyStack.func_77978_p();
        nbtTag.func_74768_a("doorX", posX);
        nbtTag.func_74768_a("doorY", posY);
        nbtTag.func_74768_a("doorZ", posZ);
        nbtTag.func_74768_a("doorD", field_73011_w.field_76574_g);
        nbtTag.func_74778_a("doorDN", field_73011_w.func_80007_l());
        world.func_72838_d(new EntityItem(world, field_70165_t, field_70163_u + 0.5D, field_70161_v, keyStack));
      }
      
      field_77994_a -= 1;
      
      return true;
    }
    
    return false;
  }
  

  public static void setBlockToClay(World world, int x, int y, int z)
  {
    Block block = world.func_147439_a(x, y, z);
    Block blockAbove = world.func_147439_a(x, y + 1, z);
    if ((block == Blocks.field_150346_d) && ((blockAbove == Blocks.field_150355_j) || (blockAbove == Blocks.field_150358_i))) {
      world.func_147449_b(x, y, z, Blocks.field_150435_aG);
      if (!field_72995_K) {
        ParticleEffect.INSTANT_SPELL.send(SoundEffect.MOB_SLIME_BIG, world, 0.5D + x, 1.5D + y, 0.5D + z, 1.0D, 1.0D, 16);
      }
    }
  }
  
  private boolean useAnnointingPaste(ItemStack stack, EntityPlayer player, World world, int x, int y, int z)
  {
    if (!field_72995_K) {
      Block block = world.func_147439_a(x, y, z);
      int meta = world.func_72805_g(x, y, z);
      








      if (block == Blocks.field_150383_bp)
      {


        world.func_147449_b(x, y, z, BlocksCAULDRON);
        field_77994_a -= 1;
        ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_FIZZ, world, x, y, z, 1.0D, 1.0D, 16);
        ParticleEffect.LARGE_EXPLODE.send(SoundEffect.RANDOM_LEVELUP, world, x, y, z, 1.0D, 1.0D, 16);
      }
    }
    return true;
  }
  
  private boolean useMutandis(boolean extremis, ItemStack itemstack, EntityPlayer player, World world, int posX, int posY, int posZ) {
    if (!field_72995_K)
    {
      Block block = world.func_147439_a(posX, posY, posZ);
      Block blockAbove = world.func_147439_a(posX, posY + 1, posZ);
      
      if ((extremis) && ((block == Blocks.field_150349_c) || (block == Blocks.field_150391_bh))) {
        if (field_73012_v.nextInt(2) == 0) {
          world.func_147449_b(posX, posY, posZ, block == Blocks.field_150349_c ? Blocks.field_150391_bh : Blocks.field_150349_c);
        }
        
        ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_FIZZ, world, posX, posY + 1, posZ, 1.0D, 1.0D, 16);
        
        field_77994_a -= 1;
      } else if ((extremis) && (block == Blocks.field_150346_d) && ((blockAbove == Blocks.field_150355_j) || (blockAbove == Blocks.field_150358_i))) {
        if (field_73012_v.nextInt(2) == 0) {
          setBlockToClay(world, posX, posY, posZ);
          setBlockToClay(world, posX + 1, posY, posZ);
          setBlockToClay(world, posX - 1, posY, posZ);
          setBlockToClay(world, posX, posY, posZ + 1);
          setBlockToClay(world, posX, posY, posZ - 1);
        } else {
          ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_FIZZ, world, posX, posY + 1, posZ, 1.0D, 1.0D, 16);
        }
        field_77994_a -= 1;
      } else {
        int metadata = world.func_72805_g(posX, posY, posZ);
        
        ArrayList<MutableBlock> list;
        ArrayList<MutableBlock> list;
        if ((block == Blocks.field_150457_bL) && (metadata > 0)) {
          MutableBlock[] blocks = { new MutableBlock(Blocks.field_150457_bL, 1), new MutableBlock(Blocks.field_150457_bL, 2), new MutableBlock(Blocks.field_150457_bL, 3), new MutableBlock(Blocks.field_150457_bL, 4), new MutableBlock(Blocks.field_150457_bL, 5), new MutableBlock(Blocks.field_150457_bL, 6), new MutableBlock(Blocks.field_150457_bL, 7), new MutableBlock(Blocks.field_150457_bL, 8), new MutableBlock(Blocks.field_150457_bL, 9), new MutableBlock(Blocks.field_150457_bL, 10), new MutableBlock(Blocks.field_150457_bL, 11) };
          


          list = new ArrayList(Arrays.asList(blocks));
        } else {
          MutableBlock[] blocks = { new MutableBlock(Blocks.field_150345_g, 0), new MutableBlock(Blocks.field_150345_g, 1), new MutableBlock(Blocks.field_150345_g, 2), new MutableBlock(Blocks.field_150345_g, 3), new MutableBlock(Blocks.field_150345_g, 4), new MutableBlock(Blocks.field_150345_g, 5), new MutableBlock(BlocksSAPLING, 0), new MutableBlock(BlocksSAPLING, 1), new MutableBlock(BlocksSAPLING, 2), new MutableBlock(BlocksEMBER_MOSS, 0), new MutableBlock(Blocks.field_150329_H, 1), new MutableBlock(Blocks.field_150392_bi), new MutableBlock(Blocks.field_150338_P), new MutableBlock(Blocks.field_150337_Q), new MutableBlock(Blocks.field_150328_O, 0), new MutableBlock(Blocks.field_150327_N), new MutableBlock(BlocksSPANISH_MOSS, 1) };
          





          list = new ArrayList(Arrays.asList(blocks));
          
          for (String extra : instancemutandisExtras) {
            try {
              list.add(new MutableBlock(extra));
            }
            catch (Throwable ex) {}
          }
          
          if (extremis) {
            MutableBlock[] extremisBlocks = { new MutableBlock(Blocks.field_150459_bM, -1, Math.min(metadata, 7)), new MutableBlock(Blocks.field_150469_bN, -1, Math.min(metadata, 7)), new MutableBlock(Blocks.field_150464_aj, -1, Math.min(metadata, 7)), new MutableBlock(Blocks.field_150436_aH, -1, Math.min(metadata, 7)), new MutableBlock(BlocksCROP_BELLADONNA, -1, Math.min(metadata, BlocksCROP_BELLADONNA.getNumGrowthStages())), new MutableBlock(BlocksCROP_MANDRAKE, -1, Math.min(metadata, BlocksCROP_MANDRAKE.getNumGrowthStages())), new MutableBlock(BlocksCROP_ARTICHOKE, -1, Math.min(metadata, BlocksCROP_ARTICHOKE.getNumGrowthStages())), new MutableBlock(Blocks.field_150393_bb, -1, Math.min(metadata, 7)), new MutableBlock(Blocks.field_150434_aF), new MutableBlock(Blocks.field_150394_bc, -1, Math.min(metadata, 7)), new MutableBlock(Blocks.field_150388_bm, -1, Math.min(metadata, 3)) };
            






            list.addAll(Arrays.asList(extremisBlocks));
          } else if (field_71093_bK == instancedimensionDreamID) {
            MutableBlock[] spiritBlocks = { new MutableBlock(Blocks.field_150388_bm, -1, 3) };
            list.addAll(Arrays.asList(spiritBlocks));
          }
        }
        
        MutableBlock mutableBlock = new MutableBlock(block, metadata, 0);
        int index = list.indexOf(mutableBlock);
        if (index != -1) {
          list.remove(index);
          ((MutableBlock)list.get(field_73012_v.nextInt(list.size()))).mutate(world, posX, posY, posZ);
          ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_FIZZ, world, posX, posY, posZ, 1.0D, 1.0D, 16);
          field_77994_a -= 1;
        }
      }
    }
    
    return true;
  }
  
  private boolean placeBroom(ItemStack itemstack, EntityPlayer player, World world, int posX, int posY, int posZ, int side, float par8, float par9, float par10)
  {
    float f = 1.0F;
    float f1 = field_70127_C + (field_70125_A - field_70127_C) * 1.0F;
    float f2 = field_70126_B + (field_70177_z - field_70126_B) * 1.0F;
    double d0 = field_70169_q + (field_70165_t - field_70169_q) * 1.0D;
    double d1 = field_70167_r + (field_70163_u - field_70167_r) * 1.0D + 1.62D - field_70129_M;
    double d2 = field_70166_s + (field_70161_v - field_70166_s) * 1.0D;
    Vec3 vec3 = Vec3.func_72443_a(d0, d1, d2);
    float f3 = MathHelper.func_76134_b(-f2 * 0.017453292F - 3.1415927F);
    float f4 = MathHelper.func_76126_a(-f2 * 0.017453292F - 3.1415927F);
    float f5 = -MathHelper.func_76134_b(-f1 * 0.017453292F);
    float f6 = MathHelper.func_76126_a(-f1 * 0.017453292F);
    float f7 = f4 * f5;
    float f8 = f3 * f5;
    double d3 = 5.0D;
    Vec3 vec31 = vec3.func_72441_c(f7 * 5.0D, f6 * 5.0D, f8 * 5.0D);
    MovingObjectPosition movingobjectposition = world.func_72901_a(vec3, vec31, true);
    
    if (movingobjectposition == null) {
      return super.func_77648_a(itemstack, player, world, posX, posY, posZ, side, par8, par9, par10);
    }
    Vec3 vec32 = player.func_70676_i(1.0F);
    boolean flag = false;
    float f9 = 1.0F;
    List list = world.func_72839_b(player, field_70121_D.func_72321_a(field_72450_a * 5.0D, field_72448_b * 5.0D, field_72449_c * 5.0D).func_72314_b(1.0D, 1.0D, 1.0D));
    


    for (int i = 0; i < list.size(); i++) {
      Entity entity = (Entity)list.get(i);
      
      if (entity.func_70067_L()) {
        float f10 = entity.func_70111_Y();
        AxisAlignedBB axisalignedbb = field_70121_D.func_72314_b(f10, f10, f10);
        
        if (axisalignedbb.func_72318_a(vec3)) {
          flag = true;
        }
      }
    }
    
    if (flag) {
      return super.func_77648_a(itemstack, player, world, posX, posY, posZ, side, par8, par9, par10);
    }
    if (field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
      i = field_72311_b;
      int j = field_72312_c;
      int k = field_72309_d;
      
      if (world.func_147439_a(i, j, k) == Blocks.field_150433_aE) {
        j--;
      }
      
      EntityBroom broomEntity = new EntityBroom(world, i + 0.5F, j + 1.0F, k + 0.5F);
      if (itemstack.func_82837_s()) {
        broomEntity.setCustomNameTag(itemstack.func_82833_r());
      }
      setBroomEntityColor(broomEntity, itemstack);
      
      field_70177_z = field_70177_z;
      
      if (!world.func_72945_a(broomEntity, field_70121_D.func_72314_b(-0.1D, -0.1D, -0.1D)).isEmpty()) {
        super.func_77648_a(itemstack, player, world, posX, posY, posZ, side, par8, par9, par10);
      }
      
      field_70177_z = (((MathHelper.func_76128_c(field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3) - 1) * 90);
      
      if (!field_72995_K) {
        world.func_72838_d(broomEntity);
        int l = MathHelper.func_76141_d(field_70177_z * 256.0F / 360.0F);
        

        Witchery.packetPipeline.sendToAllAround(new S14PacketEntity.S17PacketEntityLookMove(broomEntity.func_145782_y(), (byte)0, (byte)0, (byte)0, (byte)Math.max(Math.min(l, 255), 0), (byte)0), world, TargetPointUtil.from(broomEntity, 128.0D));
      }
      







      if (!field_71075_bZ.field_75098_d) {
        field_77994_a -= 1;
      }
    }
    
    return super.func_77648_a(itemstack, player, world, posX, posY, posZ, side, par8, par9, par10);
  }
  

  private void setBroomEntityColor(EntityBroom broomEntity, ItemStack itemstack)
  {
    broomEntity.setBrushColor(getBroomItemColor(itemstack));
  }
  
  public void setBroomItemColor(ItemStack itemstack, int brushColor) {
    if ((brushColor >= 0) && (brushColor <= 15)) {
      if (!itemstack.func_77942_o()) {
        itemstack.func_77982_d(new NBTTagCompound());
      }
      NBTTagCompound nbtTag = itemstack.func_77978_p();
      nbtTag.func_74774_a("BrushColor", Byte.valueOf((byte)brushColor).byteValue());
    }
  }
  
  public int getBroomItemColor(ItemStack stack) {
    NBTTagCompound nbtTag = stack.func_77978_p();
    if ((nbtTag != null) && (nbtTag.func_74764_b("BrushColor"))) {
      return nbtTag.func_74771_c("BrushColor") & 0xF;
    }
    return -1;
  }
  
  private boolean placeBlock(Block spawnBlock, ItemStack itemstack, EntityPlayer player, World world, int posX, int posY, int posZ, int side, float par8, float par9, float par10)
  {
    Block block = world.func_147439_a(posX, posY, posZ);
    
    if ((block == Blocks.field_150433_aE) && ((world.func_72805_g(posX, posY, posZ) & 0x7) < 1)) {
      side = 1;
    } else if ((block != Blocks.field_150395_bd) && (block != Blocks.field_150329_H) && (block != Blocks.field_150330_I)) {
      if (side == 0) {
        posY--;
      } else if (side == 1) {
        posY++;
      } else if (side == 2) {
        posZ--;
      } else if (side == 3) {
        posZ++;
      } else if (side == 4) {
        posX--;
      } else if (side == 5) {
        posX++;
      }
    }
    
    if (!player.func_82247_a(posX, posY, posZ, side, itemstack))
      return false;
    if (field_77994_a == 0) {
      return false;
    }
    if (world.func_147472_a(spawnBlock, posX, posY, posZ, false, side, (Entity)null, itemstack)) {
      int j1 = spawnBlock.func_149660_a(world, posX, posY, posZ, side, par8, par9, par10, 0);
      
      if (world.func_147465_d(posX, posY, posZ, spawnBlock, j1, 3)) {
        if (world.func_147439_a(posX, posY, posZ) == spawnBlock) {
          spawnBlock.func_149689_a(world, posX, posY, posZ, player, itemstack);
          spawnBlock.func_149714_e(world, posX, posY, posZ, j1);
          if (spawnBlock == BlocksCHALICE) {
            BlockChalice.TileEntityChalice tileEntity = (BlockChalice.TileEntityChalice)world.func_147438_o(posX, posY, posZ);
            if (tileEntity != null) {
              tileEntity.setFilled(itemChaliceFull.isMatch(itemstack));
            }
          }
        }
        
        world.func_72908_a(posX + 0.5F, posY + 0.5F, posZ + 0.5F, field_149762_H.func_150496_b(), (field_149762_H.func_150497_c() + 1.0F) / 2.0F, field_149762_H.func_150494_d() * 0.8F);
        
        field_77994_a -= 1;
      }
    }
    
    return true;
  }
  
  private boolean placeDreamCatcher(World world, EntityPlayer player, ItemStack itemstack, int posX, int posY, int posZ, int side)
  {
    if (side == 0)
      return false;
    if (!world.func_147439_a(posX, posY, posZ).func_149688_o().func_76220_a()) {
      return false;
    }
    if (side == 1) {
      posY++;
    } else if (side == 2) {
      posZ--;
    } else if (side == 3) {
      posZ++;
    } else if (side == 4) {
      posX--;
    } else if (side == 5) {
      posX++;
    }
    
    if (!player.func_82247_a(posX, posY, posZ, side, itemstack))
      return false;
    if (!BlocksDREAM_CATCHER.func_149742_c(world, posX, posY, posZ))
      return false;
    if (field_72995_K) {
      return true;
    }
    if (side != 1) {
      world.func_147465_d(posX, posY, posZ, BlocksDREAM_CATCHER, side, 3);
      field_77994_a -= 1;
      BlockDreamCatcher.TileEntityDreamCatcher tileEntity = (BlockDreamCatcher.TileEntityDreamCatcher)world.func_147438_o(posX, posY, posZ);
      if (tileEntity != null) {
        DreamWeave weave = (DreamWeave)subItems.get(itemstack.func_77960_j());
        weave.setEffect(tileEntity);
      }
    }
    

    return true;
  }
  
  public static boolean isWaystoneBound(ItemStack stack)
  {
    NBTTagCompound tag = stack.func_77978_p();
    return (tag != null) && (tag.func_74764_b("PosX")) && (tag.func_74764_b("PosY")) && (tag.func_74764_b("PosZ")) && (tag.func_74764_b("PosD"));
  }
  
  public static int getWaystoneDimension(ItemStack stack) { NBTTagCompound tag = stack.func_77978_p();
    if ((tag != null) && (tag.func_74764_b("PosX")) && (tag.func_74764_b("PosY")) && (tag.func_74764_b("PosZ")) && (tag.func_74764_b("PosD"))) {
      int newX = tag.func_74762_e("PosX");
      int newY = tag.func_74762_e("PosY");
      int newZ = tag.func_74762_e("PosZ");
      int newD = tag.func_74762_e("PosD");
      
      return newD;
    }
    return 0;
  }
  
  private boolean isRestrictedTeleportTarget(int source, int target) {
    if (source == target) {
      return false;
    }
    
    return (source == instancedimensionDreamID) || (source == instancedimensionMirrorID) || (target == instancedimensionDreamID) || (target == instancedimensionMirrorID);
  }
  
  public boolean teleportToLocation(World world, ItemStack itemstack, Entity entity, int radius, boolean presetPosition)
  {
    NBTTagCompound tag = itemstack.func_77978_p();
    if ((tag != null) && (tag.func_74764_b("PosX")) && (tag.func_74764_b("PosY")) && (tag.func_74764_b("PosZ")) && (tag.func_74764_b("PosD"))) {
      int newX = tag.func_74762_e("PosX") - radius + field_73012_v.nextInt(radius * 2 + 1);
      int newY = tag.func_74762_e("PosY");
      int newZ = tag.func_74762_e("PosZ") - radius + field_73012_v.nextInt(radius * 2 + 1);
      int newD = tag.func_74762_e("PosD");
      
      if (!isRestrictedTeleportTarget(field_71093_bK, newD)) {
        teleportToLocation(world, newX, newY, newZ, newD, entity, presetPosition);
        return true;
      }
    } else if (tag != null) {
      EntityLivingBase target = ItemsTAGLOCK_KIT.getBoundEntity(world, null, itemstack, Integer.valueOf(1));
      if ((entity != null) && (target != null) && 
        (!isRestrictedTeleportTarget(field_71093_bK, field_71093_bK))) {
        teleportToLocation(world, field_70165_t, field_70163_u, field_70161_v, field_71093_bK, entity, presetPosition);
        return true;
      }
    }
    
    return false;
  }
  
  public static boolean teleportToLocationSafely(World world, double posX, double posY, double posZ, int dimension, Entity entity, boolean presetPosition) {
    World targetWorld = MinecraftServer.func_71276_C().func_71218_a(dimension);
    int x = MathHelper.func_76128_c(posX);
    int y = MathHelper.func_76128_c(posY);
    int z = MathHelper.func_76128_c(posZ);
    for (int i = 0; i < 16; i++) {
      int dy = y + i;
      if ((dy < 250) && (!BlockUtil.isReplaceableBlock(targetWorld, x, dy, z)) && (BlockUtil.isReplaceableBlock(targetWorld, x, dy + 1, z)) && (BlockUtil.isReplaceableBlock(targetWorld, x, dy + 2, z)))
      {

        teleportToLocation(world, x, dy + 1, z, dimension, entity, presetPosition);
        return true;
      }
      
      dy = y - i;
      if ((i > 0) && (dy > 1) && (!BlockUtil.isReplaceableBlock(targetWorld, x, dy, z)) && (BlockUtil.isReplaceableBlock(targetWorld, x, dy + 1, z)) && (BlockUtil.isReplaceableBlock(targetWorld, x, dy + 2, z)))
      {

        teleportToLocation(world, x, dy + 1, z, dimension, entity, presetPosition);
        return true;
      }
    }
    
    return false;
  }
  
  public static void teleportToLocation(World world, double posX, double posY, double posZ, int dimension, Entity entity, boolean presetPosition) {
    teleportToLocation(world, posX, posY, posZ, dimension, entity, presetPosition, ParticleEffect.PORTAL, SoundEffect.MOB_ENDERMEN_PORTAL);
  }
  
  public static void teleportToLocation(World world, double posX, double posY, double posZ, int dimension, Entity entity, boolean presetPosition, ParticleEffect particle, SoundEffect sound) {
    boolean isVampire = CreatureUtil.isVampire(entity);
    if (isVampire) {
      Witchery.packetPipeline.sendToAllAround(new PacketParticles(ParticleEffect.SMOKE, SoundEffect.WITCHERY_RANDOM_POOF, entity, 0.5D, 2.0D), TargetPointUtil.from(entity, 16.0D));
    }
    else {
      Witchery.packetPipeline.sendToAllAround(new PacketParticles(particle, sound, entity, 0.5D, 2.0D), TargetPointUtil.from(entity, 16.0D));
    }
    

    if ((entity instanceof EntityPlayer)) {
      EntityPlayer player = (EntityPlayer)entity;
      if (field_71093_bK != dimension)
      {
        if (presetPosition) {
          player.func_70107_b(posX, posY, posZ);
        }
        travelToDimension(player, dimension);
      }
      player.func_70634_a(posX, posY, posZ);
    } else if ((entity instanceof EntityLiving)) {
      if (field_71093_bK != dimension) {
        travelToDimension(entity, dimension, posX, posY, posZ);
      } else {
        entity.func_70012_b(posX, posY, posZ, field_70177_z, field_70125_A);
      }
    }
    else if (field_71093_bK != dimension) {
      travelToDimension(entity, dimension, posX, posY, posZ);
    } else {
      entity.func_70012_b(posX, posY, posZ, field_70177_z, field_70125_A);
    }
    

    if (isVampire) {
      Witchery.packetPipeline.sendToAllAround(new PacketParticles(ParticleEffect.SMOKE, SoundEffect.WITCHERY_RANDOM_POOF, entity, 0.5D, 2.0D), TargetPointUtil.from(entity, 16.0D));
    }
    else {
      Witchery.packetPipeline.sendToAllAround(new PacketParticles(particle, sound, entity, 0.5D, 2.0D), TargetPointUtil.from(entity, 16.0D));
    }
  }
  

  public static void travelToDimension(EntityPlayer player, int dimension)
  {
    if ((!field_70170_p.field_72995_K & player instanceof EntityPlayerMP)) {
      MinecraftServer server = MinecraftServer.func_71276_C();
      WorldServer newWorldServer = server.func_71218_a(dimension);
      


      server.func_71203_ab().transferPlayerToDimension((EntityPlayerMP)player, dimension, new Teleporter2(newWorldServer));
    }
  }
  
  private static Entity travelToDimension(Entity thisE, int par1, double posX, double posY, double posZ) {
    if ((!field_70170_p.field_72995_K) && (!field_70128_L)) {
      field_70170_p.field_72984_F.func_76320_a("changeDimension");
      MinecraftServer minecraftserver = MinecraftServer.func_71276_C();
      int j = field_71093_bK;
      WorldServer worldserver = minecraftserver.func_71218_a(j);
      WorldServer worldserver1 = minecraftserver.func_71218_a(par1);
      field_71093_bK = par1;
      
      if ((j == 1) && (par1 == 1)) {
        worldserver1 = minecraftserver.func_71218_a(0);
        field_71093_bK = 0;
      }
      
      field_70170_p.func_72900_e(thisE);
      field_70128_L = false;
      field_70170_p.field_72984_F.func_76320_a("reposition");
      minecraftserver.func_71203_ab().transferEntityToWorld(thisE, j, worldserver, worldserver1, new Teleporter2(worldserver1));
      field_70170_p.field_72984_F.func_76318_c("reloading");
      Entity entity = EntityList.func_75620_a(EntityList.func_75621_b(thisE), worldserver1);
      
      if (entity != null) {
        entity.func_82141_a(thisE, true);
        










        entity.func_70012_b(posX, posY, posZ, field_70177_z, field_70125_A);
        

        worldserver1.func_72838_d(entity);
      }
      
      field_70128_L = true;
      field_70170_p.field_72984_F.func_76319_b();
      worldserver.func_82742_i();
      worldserver1.func_82742_i();
      field_70170_p.field_72984_F.func_76319_b();
      
      return entity;
    }
    
    return null;
  }
  
  private static class Teleporter2 extends Teleporter {
    public Teleporter2(WorldServer server) {
      super();
    }
    

    public boolean func_85188_a(Entity par1Entity)
    {
      return false;
    }
    
    public boolean func_77184_b(Entity par1Entity, double par2, double par4, double par6, float par8)
    {
      return false;
    }
    
    public void func_77185_a(Entity par1Entity, double par2, double par4, double par6, float par8) {}
    
    public void func_85189_a(long par1) {}
  }
  
  public static class SubItem {
    public final int damageValue;
    private final String unlocalizedName;
    private final int rarity;
    
    private static <T extends SubItem> T register(T subItem, ArrayList<SubItem> subItems) {
      assert (subItems.size() == damageValue) : "Misalignement with subItem registration";
      while (subItems.size() <= damageValue) {
        subItems.add(null);
      }
      subItems.set(damageValue, subItem);
      return subItem;
    }
    
    public boolean isSolidifier() {
      return false;
    }
    
    public boolean isInfused() {
      return false;
    }
    

    private final boolean showInCreativeTab;
    
    protected boolean enchanted;
    
    protected boolean potion;
    
    @SideOnly(Side.CLIENT)
    private IIcon icon;
    
    public SubItem(int damageValue, String unlocalizedName)
    {
      this(damageValue, unlocalizedName, 0, true);
    }
    
    private SubItem(int damageValue, String unlocalizedName, int rarity) {
      this(damageValue, unlocalizedName, rarity, true);
    }
    
    private SubItem(int damageValue, String unlocalizedName, int rarity, boolean showInCreativeTab) {
      this.damageValue = damageValue;
      this.unlocalizedName = unlocalizedName;
      this.rarity = rarity;
      this.showInCreativeTab = showInCreativeTab;
      enchanted = false;
      potion = false;
    }
    
    @SideOnly(Side.CLIENT)
    private void registerIcon(IIconRegister iconRegister, ItemGeneral itemIngredient) {
      icon = iconRegister.func_94245_a(itemIngredient.func_111208_A() + "." + unlocalizedName);
    }
    
    public boolean isMatch(ItemStack itemstack) {
      return (itemstack != null) && (ItemsGENERIC == itemstack.func_77973_b()) && (itemstack.func_77960_j() == damageValue);
    }
    
    public ItemStack createStack(int stackSize) {
      return new ItemStack(ItemsGENERIC, stackSize, damageValue);
    }
    
    public ItemStack createStack() {
      return createStack(1);
    }
    
    public boolean isItemInInventory(InventoryPlayer inventory) {
      return getItemSlotFromInventory(inventory) != -1;
    }
    
    public int getItemSlotFromInventory(InventoryPlayer inventory) {
      for (int k = 0; k < field_70462_a.length; k++) {
        if ((field_70462_a[k] != null) && (field_70462_a[k].func_77973_b() == ItemsGENERIC) && (field_70462_a[k].func_77960_j() == damageValue))
        {
          return k;
        }
      }
      
      return -1;
    }
    
    public boolean consumeItemFromInventory(InventoryPlayer inventory) {
      int j = getItemSlotFromInventory(inventory);
      if (j < 0) {
        return false;
      }
      if (--field_70462_a[j].field_77994_a <= 0) {
        field_70462_a[j] = null;
      }
      
      return true;
    }
    
    public boolean isEnchanted()
    {
      return (enchanted) || (potion);
    }
    
    public SubItem setEnchanted(boolean enchanted) {
      this.enchanted = enchanted;
      return this;
    }
    
    public SubItem setPotion(boolean potion) {
      this.potion = potion;
      return this;
    }
    
    public boolean isPotion() {
      return potion;
    }
    
    public BrewItemKey getBrewItemKey() {
      return new BrewItemKey(ItemsGENERIC, damageValue);
    }
  }
  
  public static class BoltType extends ItemGeneral.SubItem {
    private BoltType(int damageValue, String unlocalizedName) {
      super(unlocalizedName);
    }
    
    public static BoltType getBolt(ItemStack stack) {
      if ((stack != null) && (stack.func_77973_b() == ItemsGENERIC)) {
        ItemGeneral.SubItem item = (ItemGeneral.SubItem)ItemsGENERIC.subItems.get(stack.func_77960_j());
        if ((item instanceof BoltType)) {
          return (BoltType)item;
        }
      }
      return null;
    }
  }
  
  public static class Edible extends ItemGeneral.SubItem {
    public final boolean eatAnyTime;
    private final int healAmount;
    private final float saturationModifier;
    private final boolean wolfsFavorite;
    
    private Edible(int damageValue, String unlocalizedName, int healAmount, float saturationModifier, boolean wolfsFavorite) {
      this(damageValue, unlocalizedName, healAmount, saturationModifier, wolfsFavorite, false);
    }
    
    private Edible(int damageValue, String unlocalizedName, int healAmount, float saturationModifier, boolean wolfsFavorite, boolean eatAnyTime) {
      super(unlocalizedName);
      this.healAmount = healAmount;
      this.saturationModifier = saturationModifier;
      this.wolfsFavorite = wolfsFavorite;
      this.eatAnyTime = eatAnyTime;
    }
  }
  
  public static class Brew extends ItemGeneral.SubItem {
    public static enum BrewResult {
      DROP_ITEM, 
      SHOW_EFFECT, 
      HIDE_EFFECT;
      
      private BrewResult() {} }
    
    public Brew(int damageValue, String unlocalizedName) { super(unlocalizedName);
      setPotion(true);
    }
    
    public BrewResult onImpact(World world, EntityLivingBase thrower, MovingObjectPosition mop, boolean enhanced, double brewX, double brewY, double brewZ, AxisAlignedBB brewBounds) {
      return BrewResult.SHOW_EFFECT;
    }
    
    protected static boolean setBlockIfNotSolid(World world, int x, int y, int z, Block block) {
      return setBlockIfNotSolid(world, x, y, z, block, 0);
    }
    
    protected static boolean setBlockIfNotSolid(World world, int x, int y, int z, Block block, int metadata) {
      if ((!world.func_147439_a(x, y, z).func_149688_o().func_76220_a()) || ((block == Blocks.field_150321_G) && (BlockUtil.getBlock(world, x, y, z) == Blocks.field_150433_aE))) {
        BlockUtil.setBlock(world, x, y, z, block, metadata, 3);
        
        ParticleEffect.EXPLODE.send(SoundEffect.NONE, world, 0.5D + x, 0.5D + y, 0.5D + z, 1.0D, 1.0D, 16);
        
        return true;
      }
      return false;
    }
  }
  
  public static class Drinkable extends ItemGeneral.SubItem
  {
    protected PotionEffect[] effects;
    protected EnumAction useAction;
    
    protected Drinkable(int damageValue, String unlocalizedName, int rarity, PotionEffect... effects) {
      this(damageValue, unlocalizedName, rarity, EnumAction.drink, effects);
    }
    
    protected Drinkable(int damageValue, String unlocalizedName, int rarity, EnumAction useAction, PotionEffect... effects) {
      super(unlocalizedName, rarity, null);
      this.effects = effects;
      this.useAction = useAction;
    }
    
    public void onDrunk(World world, EntityPlayer player, ItemStack itemstack) {}
  }
  
  public static class DreamWeave extends ItemGeneral.SubItem { public final int weaveID;
    public final int textureOffsetX;
    public final int textureOffsetY;
    
    private static DreamWeave register(DreamWeave subItem, ArrayList<ItemGeneral.SubItem> subItems, ArrayList<DreamWeave> weaves) { weaves.add(ItemGeneral.SubItem.access$000(subItem, subItems));
      return subItem;
    }
    

    private final Potion potionDream;
    
    private final Potion potionNightmare;
    
    private final int duration;
    
    private final int amplifier;
    
    private DreamWeave(int damageValue, int weaveID, String unlocalizedName, Potion potionDream, Potion potionNightmare, int duration, int amplifier, int textureX, int textureY)
    {
      super(unlocalizedName, 1, null);
      this.potionDream = potionDream;
      this.potionNightmare = potionNightmare;
      this.duration = duration;
      this.amplifier = amplifier;
      textureOffsetX = textureX;
      textureOffsetY = textureY;
      this.weaveID = weaveID;
    }
    
    public void setEffect(BlockDreamCatcher.TileEntityDreamCatcher dreamCatcherEntity) {
      dreamCatcherEntity.setEffect(this);
    }
    
    public void applyEffect(EntityPlayer player, boolean isDream, boolean isEnhanced) {
      if (isDream) {
        player.func_70690_d(new PotionEffect(potionDream.func_76396_c(), isEnhanced ? duration - 2400 : (isEnhanced) && (potionDream.field_76415_H == field_76443_yfield_76415_H) ? duration + 2400 : duration, (isEnhanced) && (potionDream.field_76415_H != field_76443_yfield_76415_H) ? amplifier + 1 : amplifier));
      } else {
        player.func_70690_d(new PotionEffect(potionNightmare.func_76396_c(), duration, isEnhanced ? amplifier + 1 : amplifier));
      }
    }
  }
}
