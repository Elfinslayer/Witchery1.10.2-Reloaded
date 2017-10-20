package com.emoniph.witchery;

import com.emoniph.witchery.brewing.ItemBrew;
import com.emoniph.witchery.brewing.ItemBrewBucket;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.item.ItemArthana;
import com.emoniph.witchery.item.ItemBiomeNote;
import com.emoniph.witchery.item.ItemBoline;
import com.emoniph.witchery.item.ItemBook;
import com.emoniph.witchery.item.ItemBrewBag;
import com.emoniph.witchery.item.ItemBrewEndlessWater;
import com.emoniph.witchery.item.ItemBrewFuel;
import com.emoniph.witchery.item.ItemBucketSpirit;
import com.emoniph.witchery.item.ItemCaneSword;
import com.emoniph.witchery.item.ItemChalk;
import com.emoniph.witchery.item.ItemCircleTalisman;
import com.emoniph.witchery.item.ItemCoffin;
import com.emoniph.witchery.item.ItemDeathsClothes;
import com.emoniph.witchery.item.ItemDeathsHand;
import com.emoniph.witchery.item.ItemDiviner;
import com.emoniph.witchery.item.ItemDuplicationStaff;
import com.emoniph.witchery.item.ItemEarmuffs;
import com.emoniph.witchery.item.ItemEntityLocator;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import com.emoniph.witchery.item.ItemGlassGoblet;
import com.emoniph.witchery.item.ItemGoblinClothes;
import com.emoniph.witchery.item.ItemHandBow;
import com.emoniph.witchery.item.ItemHornOfTheHunt;
import com.emoniph.witchery.item.ItemHunterClothes;
import com.emoniph.witchery.item.ItemHuntsmanSpear;
import com.emoniph.witchery.item.ItemKobolditePickaxe;
import com.emoniph.witchery.item.ItemLeonardsUrn;
import com.emoniph.witchery.item.ItemMarkupBook;
import com.emoniph.witchery.item.ItemMirror;
import com.emoniph.witchery.item.ItemMoonCharm;
import com.emoniph.witchery.item.ItemMutator;
import com.emoniph.witchery.item.ItemMysticBranch;
import com.emoniph.witchery.item.ItemParasyticLouse;
import com.emoniph.witchery.item.ItemPolynesiaCharm;
import com.emoniph.witchery.item.ItemPoppet;
import com.emoniph.witchery.item.ItemPoppetShelfCompass;
import com.emoniph.witchery.item.ItemSilverSword;
import com.emoniph.witchery.item.ItemSpectralStone;
import com.emoniph.witchery.item.ItemStew;
import com.emoniph.witchery.item.ItemSunGrenade;
import com.emoniph.witchery.item.ItemTaglockKit;
import com.emoniph.witchery.item.ItemVampireClothes;
import com.emoniph.witchery.item.ItemVanillaPotion;
import com.emoniph.witchery.item.ItemWitchHand;
import com.emoniph.witchery.item.ItemWitchSeeds;
import com.emoniph.witchery.item.ItemWitchSlab;
import com.emoniph.witchery.item.ItemWitchesClothes;
import com.emoniph.witchery.item.ItemWolfToken;
import net.minecraft.block.BlockSlab;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public final class WitcheryItems
{
  public final ItemGeneral GENERIC = (ItemGeneral)new ItemGeneral().func_77655_b("witchery:ingredient").func_111206_d("witchery:ingredient");
  public final ItemTaglockKit TAGLOCK_KIT = (ItemTaglockKit)new ItemTaglockKit().func_77655_b("witchery:taglockkit").func_111206_d("witchery:taglockKit");
  public final ItemPoppet POPPET = (ItemPoppet)new ItemPoppet().func_77655_b("witchery:poppet").func_111206_d("witchery:poppet");
  public final ItemVanillaPotion POTIONS = (ItemVanillaPotion)new ItemVanillaPotion().func_77655_b("witchery:potion").func_111206_d("potion");
  public final Item SLAB_SINGLE = new ItemWitchSlab((BlockSlab)BlocksWOOD_SLAB_SINGLE, (BlockSlab)BlocksWOOD_SLAB_SINGLE, (BlockSlab)BlocksWOOD_SLAB_DOUBLE).func_77655_b("witchery:witchwoodslab");
  public final Item SLAB_DOUBLE = new ItemWitchSlab((BlockSlab)BlocksWOOD_SLAB_DOUBLE, (BlockSlab)BlocksWOOD_SLAB_SINGLE, (BlockSlab)BlocksWOOD_SLAB_DOUBLE).func_77655_b("witchery:witchwooddoubleslab");
  
  public final Item PERPERTUAL_ICE_SLAB_SINGLE = new ItemWitchSlab((BlockSlab)BlocksPERPETUAL_ICE_SLAB_SINGLE, (BlockSlab)BlocksPERPETUAL_ICE_SLAB_SINGLE, (BlockSlab)BlocksPERPETUAL_ICE_SLAB_DOUBLE).func_77655_b("witchery:iceslab");
  public final Item PERPERTUAL_ICE_SLAB_DOUBLE = new ItemWitchSlab((BlockSlab)BlocksPERPETUAL_ICE_SLAB_DOUBLE, (BlockSlab)BlocksPERPETUAL_ICE_SLAB_SINGLE, (BlockSlab)BlocksPERPETUAL_ICE_SLAB_DOUBLE).func_77655_b("witchery:icedoubleslab");
  
  public final Item SNOW_SLAB_SINGLE = new ItemWitchSlab((BlockSlab)BlocksSNOW_SLAB_SINGLE, (BlockSlab)BlocksSNOW_SLAB_SINGLE, (BlockSlab)BlocksSNOW_SLAB_DOUBLE).func_77655_b("witchery:snowslab");
  public final Item SNOW_SLAB_DOUBLE = new ItemWitchSlab((BlockSlab)BlocksSNOW_SLAB_DOUBLE, (BlockSlab)BlocksSNOW_SLAB_SINGLE, (BlockSlab)BlocksSNOW_SLAB_DOUBLE).func_77655_b("witchery:snowdoubleslab");
  
  public final Item COFFIN = new ItemCoffin().func_77655_b("witchery:coffin").func_111206_d("witchery:coffin");
  
  public final Item STEW = new ItemStew(12, 1.0F).func_77655_b("witchery:stew").func_111206_d("witchery:stew");
  public final Item STEW_RAW = new ItemStew(1, 0.1F).func_77655_b("witchery:stewraw").func_111206_d("witchery:stewraw");
  
  public final Item MIRROR = new ItemMirror().func_77655_b("witchery:mirror").func_111206_d("witchery:mirror");
  

  public final Item CHALK_RITUAL = new ItemChalk(BlocksGLYPH_RITUAL).func_77655_b("witchery:chalkritual").func_111206_d("witchery:chalkRitual");
  public final Item CHALK_OTHERWHERE = new ItemChalk(BlocksGLYPH_OTHERWHERE).func_77655_b("witchery:chalkotherwhere").func_111206_d("witchery:chalkOtherwhere");
  public final Item CHALK_INFERNAL = new ItemChalk(BlocksGLYPH_INFERNAL).func_77655_b("witchery:chalkinfernal").func_111206_d("witchery:chalkInfernal");
  public final Item CHALK_GOLDEN = new ItemChalk(BlocksCIRCLE).func_77655_b("witchery:chalkheart").func_111206_d("witchery:chalkHeart");
  

  public final Item BOLINE = new ItemBoline().func_77655_b("witchery:boline").func_111206_d("witchery:boline");
  public final Item ARTHANA = new ItemArthana().func_77655_b("witchery:arthana").func_111206_d("witchery:arthana");
  public final Item HUNTSMANS_SPEAR = new ItemHuntsmanSpear().func_77655_b("witchery:huntsmanspear").func_111206_d("witchery:huntsmanspear");
  public final Item CROSSBOW_PISTOL = new ItemHandBow().func_77655_b("witchery:handbow").func_111206_d("witchery:handbow");
  public final ItemDeathsHand DEATH_HAND = (ItemDeathsHand)new ItemDeathsHand().func_77655_b("witchery:deathshand").func_111206_d("witchery:deathshand");
  
  public final Item SILVER_SWORD = new ItemSilverSword().func_77655_b("witchery:silversword").func_111206_d("witchery:silversword");
  public final ItemCaneSword CANE_SWORD = (ItemCaneSword)new ItemCaneSword().func_77655_b("witchery:canesword").func_111206_d("witchery:canesword");
  

  public final Item WITCH_HAND = new ItemWitchHand().func_77655_b("witchery:witchhand").func_111206_d("witchery:witchHand");
  public final Item CIRCLE_TALISMAN = new ItemCircleTalisman().func_77655_b("witchery:circletalisman").func_111206_d("witchery:circleTalisman");
  public final Item MYSTIC_BRANCH = new ItemMysticBranch().func_77655_b("witchery:mysticbranch").func_111206_d("witchery:mysticbranch");
  public final Item DIVINER_WATER = new ItemDiviner(Blocks.field_150355_j).func_77655_b("witchery:divinerwater").func_111206_d("witchery:divinerWater");
  public final Item DIVINER_LAVA = new ItemDiviner(Blocks.field_150353_l).func_77655_b("witchery:divinerlava").func_111206_d("witchery:divinerLava");
  public final Item POLYNESIA_CHARM = new ItemPolynesiaCharm(false).func_77655_b("witchery:polynesiacharm").func_111206_d("witchery:polynesiaCharm");
  public final Item DEVILS_TONGUE_CHARM = new ItemPolynesiaCharm(true).func_77655_b("witchery:devilstonguecharm").func_111206_d("witchery:devilsTongueCharm");
  public final Item MUTATING_SPRIG = new ItemMutator().func_77655_b("witchery:mutator").func_111206_d("witchery:mutator");
  public final Item PARASYTIC_LOUSE = new ItemParasyticLouse().func_77655_b("witchery:louse").func_111206_d("witchery:louse");
  public final Item BREW_BAG = new ItemBrewBag().func_77655_b("witchery:brewbag").func_111206_d("witchery:brewbag");
  public final Item SPECTRAL_STONE = new ItemSpectralStone().func_77655_b("witchery:spectralstone").func_111206_d("witchery:witchery.spectralstone");
  public final Item SHELF_COMPASS = new ItemPoppetShelfCompass().func_77655_b("witchery:shelfcompass").func_111206_d("witchery:shelfcompass");
  public final Item KOBOLDITE_PICKAXE = new ItemKobolditePickaxe().func_77655_b("witchery:kobolditepickaxe").func_111206_d("witchery:kobolditepickaxe");
  public final Item DUP_STAFF = new ItemDuplicationStaff().func_77655_b("witchery:dupstaff").func_111206_d("witchery:dupstaff");
  
  public final Item BREW = new ItemBrew().func_77655_b("witchery:brewbottle").func_111206_d("witchery:brew_drinkable");
  public final Item BREW_FUEL = new ItemBrewFuel().func_77655_b("witchery:brew.fuel").func_111206_d("witchery:brew_drinkable");
  public final Item BREW_ENDLESS_WATER = new ItemBrewEndlessWater().func_77655_b("witchery:brew.water").func_111206_d("witchery:brew_drinkable");
  
  public final Item BIOME_BOOK = new ItemBook().func_77655_b("witchery:bookbiomes2").func_111206_d("witchery:biomebook2");
  public final Item BIOME_NOTE = new ItemBiomeNote().func_77655_b("witchery:biomenote").func_111206_d("witchery:biomenote");
  
  public final Item CAULDRON_BOOK = new ItemMarkupBook(7).func_77655_b("witchery:cauldronbook").func_111206_d("witchery:bookcauldron");
  
  public final Item LEONARDS_URN = new ItemLeonardsUrn().func_77655_b("witchery:leonardsurn").func_111206_d("witchery:leonardsurn");
  public final Item PLAYER_COMPASS = new ItemEntityLocator().func_77655_b("witchery:playercompass").func_111206_d("witchery:playercompass");
  
  public final Item MOON_CHARM = new ItemMoonCharm().func_77655_b("witchery:mooncharm").func_111206_d("witchery:mooncharm");
  public final Item HORN_OF_THE_HUNT = new ItemHornOfTheHunt().func_77655_b("witchery:hornofthehunt").func_111206_d("witchery:hornofthehunt");
  public final Item CREATIVE_WOLF_TOKEN = new ItemWolfToken().func_77655_b("witchery:wolftoken").func_111206_d("witchery:wolftoken");
  
  public final ItemGlassGoblet BLOOD_GOBLET = (ItemGlassGoblet)new ItemGlassGoblet().func_77655_b("witchery:glassgoblet").func_111206_d("witchery:glassgoblet");
  public final Item SUN_GRENADE = new ItemSunGrenade(0).func_77655_b("witchery:sungrenade").func_111206_d("witchery:sungrenade");
  
  public final Item VAMPIRE_BOOK = new ItemMarkupBook(7, new int[] { 0, 9 })
  {
    public void onBookRead(ItemStack stack, World world, EntityPlayer player) {
      ExtendedPlayer.get(player).increaseVampireLevelCap(stack.func_77960_j() + 1);
    }
  }.func_77655_b("witchery:vampirebook").func_111206_d("witchery:vbook");
  





  public final Item DUP_GRENADE = new ItemSunGrenade(1).func_77655_b("witchery:dupgrenade").func_111206_d("witchery:dupgrenade");
  

  public final ItemWitchesClothes WITCH_HAT = (ItemWitchesClothes)new ItemWitchesClothes(0).func_77655_b("witchery:witchhat").func_111206_d("witchery:witchesHat");
  public final ItemWitchesClothes BABAS_HAT = (ItemWitchesClothes)new ItemWitchesClothes(0).func_77655_b("witchery:babashat").func_111206_d("witchery:babasHat");
  public final ItemWitchesClothes WITCH_ROBES = (ItemWitchesClothes)new ItemWitchesClothes(1).func_77655_b("witchery:witchrobe").func_111206_d("witchery:witchesRobes");
  public final ItemWitchesClothes NECROMANCERS_ROBES = (ItemWitchesClothes)new ItemWitchesClothes(1).func_77655_b("witchery:necromancerrobe").func_111206_d("witchery:necromancerRobes");
  public final ItemWitchesClothes BITING_BELT = (ItemWitchesClothes)new ItemWitchesClothes(2).func_77655_b("witchery:bitingbelt").func_111206_d("witchery:bitingBelt");
  public final ItemWitchesClothes BARK_BELT = (ItemWitchesClothes)new ItemWitchesClothes(2).func_77655_b("witchery:barkbelt").func_111206_d("witchery:barkBelt");
  public final ItemWitchesClothes ICY_SLIPPERS = (ItemWitchesClothes)new ItemWitchesClothes(3).func_77655_b("witchery:iceslippers").func_111206_d("witchery:iceSlippers");
  public final ItemWitchesClothes RUBY_SLIPPERS = (ItemWitchesClothes)new ItemWitchesClothes(3).func_77655_b("witchery:rubyslippers").func_111206_d("witchery:rubySlippers");
  public final ItemWitchesClothes SEEPING_SHOES = (ItemWitchesClothes)new ItemWitchesClothes(3).func_77655_b("witchery:seepingshoes").func_111206_d("witchery:seepingShoes");
  
  public final ItemHunterClothes HUNTER_HAT = (ItemHunterClothes)new ItemHunterClothes(0, false, false).func_77655_b("witchery:hunterhat").func_111206_d("witchery:hunterhat");
  public final ItemHunterClothes HUNTER_COAT = (ItemHunterClothes)new ItemHunterClothes(1, false, false).func_77655_b("witchery:huntercoat").func_111206_d("witchery:huntercoat");
  public final ItemHunterClothes HUNTER_LEGS = (ItemHunterClothes)new ItemHunterClothes(2, false, false).func_77655_b("witchery:hunterlegs").func_111206_d("witchery:hunterlegs");
  public final ItemHunterClothes HUNTER_BOOTS = (ItemHunterClothes)new ItemHunterClothes(3, false, false).func_77655_b("witchery:hunterboots").func_111206_d("witchery:hunterboots");
  
  public final ItemHunterClothes HUNTER_HAT_SILVERED = (ItemHunterClothes)new ItemHunterClothes(0, true, false).func_77655_b("witchery:hunterhatsilvered").func_111206_d("witchery:hunterhat");
  public final ItemHunterClothes HUNTER_COAT_SILVERED = (ItemHunterClothes)new ItemHunterClothes(1, true, false).func_77655_b("witchery:huntercoatsilvered").func_111206_d("witchery:huntercoat");
  public final ItemHunterClothes HUNTER_LEGS_SILVERED = (ItemHunterClothes)new ItemHunterClothes(2, true, false).func_77655_b("witchery:hunterlegssilvered").func_111206_d("witchery:hunterlegs");
  public final ItemHunterClothes HUNTER_BOOTS_SILVERED = (ItemHunterClothes)new ItemHunterClothes(3, true, false).func_77655_b("witchery:hunterbootssilvered").func_111206_d("witchery:hunterboots");
  
  public final ItemHunterClothes HUNTER_HAT_GARLICKED = (ItemHunterClothes)new ItemHunterClothes(0, true, true).func_77655_b("witchery:hunterhatgarlicked").func_111206_d("witchery:hunterhat");
  public final ItemHunterClothes HUNTER_COAT_GARLICKED = (ItemHunterClothes)new ItemHunterClothes(1, true, true).func_77655_b("witchery:huntercoatgarlicked").func_111206_d("witchery:huntercoat");
  public final ItemHunterClothes HUNTER_LEGS_GARLICKED = (ItemHunterClothes)new ItemHunterClothes(2, true, true).func_77655_b("witchery:hunterlegsgarlicked").func_111206_d("witchery:hunterlegs");
  public final ItemHunterClothes HUNTER_BOOTS_GARLICKED = (ItemHunterClothes)new ItemHunterClothes(3, true, true).func_77655_b("witchery:hunterbootsgarlicked").func_111206_d("witchery:hunterboots");
  
  public final ItemDeathsClothes DEATH_HOOD = (ItemDeathsClothes)new ItemDeathsClothes(0).func_77655_b("witchery:deathscowl").func_111206_d("witchery:deathscowl");
  public final ItemDeathsClothes DEATH_ROBE = (ItemDeathsClothes)new ItemDeathsClothes(1).func_77655_b("witchery:deathsrobe").func_111206_d("witchery:deathsrobe");
  public final ItemDeathsClothes DEATH_FEET = (ItemDeathsClothes)new ItemDeathsClothes(3).func_77655_b("witchery:deathsfeet").func_111206_d("witchery:deathsfeet");
  
  public final Item MOGS_QUIVER = new ItemGoblinClothes(1).func_77655_b("witchery:quiverofmog").func_111206_d("witchery:mogquiver");
  public final Item GULGS_GURDLE = new ItemGoblinClothes(2).func_77655_b("witchery:gurdleofgulg").func_111206_d("witchery:gulggurdle");
  public final Item KOBOLDITE_HELM = new ItemGoblinClothes(0).func_77655_b("witchery:kobolditehelm").func_111206_d("witchery:kobolditehelm");
  
  public final Item EARMUFFS = new ItemEarmuffs(0).func_77655_b("witchery:earmuffs").func_111206_d("witchery:earmuffs");
  
  public final ItemVampireClothes VAMPIRE_HAT = (ItemVampireClothes)new ItemVampireClothes(0, false, false).func_77655_b("witchery:vampirehat").func_111206_d("witchery:vampirehat");
  public final ItemVampireClothes VAMPIRE_HELMET = (ItemVampireClothes)new ItemVampireClothes(0, false, true).func_77655_b("witchery:vampirehelmet").func_111206_d("witchery:vampirehelmet");
  public final ItemVampireClothes VAMPIRE_COAT = (ItemVampireClothes)new ItemVampireClothes(1, false, false).func_77655_b("witchery:vampirecoat").func_111206_d("witchery:vampirecoat");
  public final ItemVampireClothes VAMPIRE_COAT_FEMALE = (ItemVampireClothes)new ItemVampireClothes(1, true, false).func_77655_b("witchery:vampirecoat_female").func_111206_d("witchery:vampirecoat");
  
  public final ItemVampireClothes VAMPIRE_COAT_CHAIN = (ItemVampireClothes)new ItemVampireClothes(1, false, true).func_77655_b("witchery:vampirechaincoat").func_111206_d("witchery:vampirechaincoat");
  public final ItemVampireClothes VAMPIRE_COAT_FEMALE_CHAIN = (ItemVampireClothes)new ItemVampireClothes(1, true, true).func_77655_b("witchery:vampirechaincoat_female").func_111206_d("witchery:vampirechaincoat");
  
  public final ItemVampireClothes VAMPIRE_LEGS = (ItemVampireClothes)new ItemVampireClothes(2, false, false).func_77655_b("witchery:vampirelegs").func_111206_d("witchery:vampirelegs");
  public final ItemVampireClothes VAMPIRE_LEGS_KILT = (ItemVampireClothes)new ItemVampireClothes(2, true, false).func_77655_b("witchery:vampirelegs_kilt").func_111206_d("witchery:vampirelegs_kilt");
  public final ItemVampireClothes VAMPIRE_BOOTS = (ItemVampireClothes)new ItemVampireClothes(3, false, false).func_77655_b("witchery:vampireboots").func_111206_d("witchery:vampireboots");
  

  public final Item SEEDS_BELLADONNA = new ItemWitchSeeds(BlocksCROP_BELLADONNA, new ItemStack(GENERIC, 1, GENERIC.itemBelladonnaFlower.damageValue), Blocks.field_150458_ak, false).func_77655_b("witchery:seedsbelladonna").func_111206_d("witchery:ingredient.seedsBelladonna");
  public final Item SEEDS_MANDRAKE = new ItemWitchSeeds(BlocksCROP_MANDRAKE, new ItemStack(GENERIC, 1, GENERIC.itemMandrakeRoot.damageValue), Blocks.field_150458_ak, false).func_77655_b("witchery:seedsmandrake").func_111206_d("witchery:ingredient.seedsMandrake");
  public final Item SEEDS_ARTICHOKE = new ItemWitchSeeds(BlocksCROP_ARTICHOKE, new ItemStack(GENERIC, 1, GENERIC.itemArtichoke.damageValue), Blocks.field_150458_ak, true).func_77655_b("witchery:seedsartichoke").func_111206_d("witchery:ingredient.seedsArtichoke");
  public final Item SEEDS_SNOWBELL = new ItemWitchSeeds(BlocksCROP_SNOWBELL, new ItemStack(Items.field_151126_ay), Blocks.field_150458_ak, false).func_77655_b("witchery:seedssnowbell").func_111206_d("witchery:ingredient.seedsSnowbell");
  public final Item SEEDS_WORMWOOD = new ItemWitchSeeds(BlocksCROP_WORMWOOD, new ItemStack(GENERIC, 1, GENERIC.itemWormwood.damageValue), Blocks.field_150458_ak, false).func_77655_b("witchery:seedswormwood").func_111206_d("witchery:ingredient.seedswormwood");
  public final Item SEEDS_MINDRAKE = new ItemWitchSeeds(BlocksCROP_MINDRAKE, null, Blocks.field_150458_ak, false).func_77655_b("witchery:seedsmindrake").func_111206_d("witchery:ingredient.mindrakebulb");
  public final Item SEEDS_WOLFSBANE = new ItemWitchSeeds(BlocksCROP_WOLFSBANE, new ItemStack(GENERIC, 1, GENERIC.itemWolfsbane.damageValue), Blocks.field_150458_ak, false).func_77655_b("witchery:seedswolfsbane").func_111206_d("witchery:ingredient.seedswolfsbane");
  public final Item SEEDS_GARLIC = new ItemWitchSeeds(BlocksCROP_GARLIC, null, Blocks.field_150458_ak, false).func_77655_b("witchery:garlic").func_111206_d("witchery:garlic");
  

  public final Item BUCKET_FLOWINGSPIRIT = WitcheryFluids.bind((ItemBucketSpirit)new ItemBucketSpirit().func_77655_b("witchery:bucketspirit").func_111206_d("witchery:bucket_spirit").func_77642_a(Items.field_151133_ar), FluidsFLOWING_SPIRIT, BlocksFLOWING_SPIRIT);
  

  public final Item BUCKET_HOLLOWTEARS = WitcheryFluids.bind((ItemBucketSpirit)new ItemBucketSpirit().func_77655_b("witchery:buckethollowtears").func_111206_d("witchery:bucket_hollowtears").func_77642_a(Items.field_151133_ar), FluidsHOLLOW_TEARS, BlocksHOLLOW_TEARS);
  


  public final Item BUCKET_BREW = WitcheryFluids.bind(new ItemBrewBucket().func_77655_b("witchery:bucketbrew").func_111206_d("witchery:bucket_brew").func_77642_a(Items.field_151133_ar), FluidsBREW, 1000);
  
  public WitcheryItems() {}
}
