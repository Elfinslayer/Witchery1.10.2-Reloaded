package com.emoniph.witchery;

import com.emoniph.witchery.util.Config;
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public class WitcheryEntities
{
  private final ArrayList<EntityRef> entities;
  public final EntityRef DEMON;
  public final EntityRef BROOM;
  public final EntityRef BREW;
  public final EntityRef SPECTRAL_FAMILIAR;
  public final EntityRef MANDRAKE;
  public final EntityRef TREEFYD;
  public final EntityRef HUNTSMAN;
  public final EntityRef SPELL;
  public final EntityRef ENT;
  public final EntityRef ILLUSION_CREEPER;
  public final EntityRef ILLUSION_SPIDER;
  public final EntityRef ILLUSION_ZOMBIE;
  public final EntityRef OWL;
  public final EntityRef TOAD;
  public final EntityRef CAT_FAMILIAR;
  public final EntityRef LOUSE;
  public final EntityRef EYE;
  public final EntityRef BABA_YAGA;
  public final EntityRef COVEN_WITCH;
  public final EntityRef PLAYER_CORPSE;
  public final EntityRef NIGHTMARE;
  public final EntityRef SPECTRE;
  public final EntityRef POLTERGEIST;
  public final EntityRef BANSHEE;
  private static final int MIN_SPIRIT_GROUP = 2;
  private static final int MAX_SPIRIT_GROUP = 5;
  public final EntityRef SPIRIT;
  public final EntityRef DEATH;
  public final EntityRef CROSSBOW_BOLT;
  public final EntityRef WITCH_HUNTER;
  public final EntityRef BINKY_HORSE;
  public final EntityRef LORD_OF_TORMENT;
  public final EntityRef SOULFIRE;
  public final EntityRef IMP;
  public final EntityRef DARK_MARK;
  public final EntityRef MINDRAKE;
  public final EntityRef GOBLIN;
  public final EntityRef GOBLIN_MOG;
  public final EntityRef GOBLIN_GULG;
  public final EntityRef BREW2;
  public final EntityRef ITEM_WAYSTONE;
  public final EntityRef DROPLET;
  public final EntityRef SPLATTER;
  public final EntityRef LEONARD;
  public final EntityRef LOST_SOUL;
  public final EntityRef WOLFMAN;
  public final EntityRef HELLHOUND;
  public final EntityRef WERE_VILLAGER;
  public final EntityRef VILLAGE_GUARD;
  public final EntityRef VAMPIRE_VILLAGER;
  public final EntityRef GRENADE;
  public final EntityRef LILITH;
  public final EntityRef FOLLOWER;
  public final EntityRef WINGED_MONKEY;
  public final EntityRef ATTACK_BAT;
  public final EntityRef MIRROR_FACE;
  public final EntityRef REFLECTION;
  
  public WitcheryEntities()
  {
    entities = new ArrayList();
    
    DEMON = new LivingRef(92, com.emoniph.witchery.entity.EntityDemon.class, "demon", entities).setEgg(9502720, 11430927);
    
    BROOM = new EntityRef(93, com.emoniph.witchery.entity.EntityBroom.class, "broom", entities);
    BREW = new EntityRef(94, com.emoniph.witchery.entity.EntityWitchProjectile.class, "brew", 64, 3, entities);
    SPECTRAL_FAMILIAR = new LivingRef(95, com.emoniph.witchery.entity.EntityFamiliar.class, "familiar", entities);
    MANDRAKE = new LivingRef(96, com.emoniph.witchery.entity.EntityMandrake.class, "mandrake", entities).setEgg(128271104, 311408);
    
    TREEFYD = new LivingRef(97, com.emoniph.witchery.entity.EntityTreefyd.class, "treefyd", entities).setEgg(5781801, 11217964);
    
    HUNTSMAN = new LivingRef(98, com.emoniph.witchery.entity.EntityHornedHuntsman.class, "hornedHuntsman", entities).setEgg(11523, 4007964);
    
    SPELL = new EntityRef(99, com.emoniph.witchery.entity.EntitySpellEffect.class, "spellEffect", 64, 3, entities);
    ENT = new LivingRef(100, com.emoniph.witchery.entity.EntityEnt.class, "ent", entities).setEgg(5338965, 5724240);
    ILLUSION_CREEPER = new LivingRef(101, com.emoniph.witchery.entity.EntityIllusionCreeper.class, "illusionCreeper", entities);
    
    ILLUSION_SPIDER = new LivingRef(102, com.emoniph.witchery.entity.EntityIllusionSpider.class, "illusionSpider", entities);
    
    ILLUSION_ZOMBIE = new LivingRef(103, com.emoniph.witchery.entity.EntityIllusionZombie.class, "illusionZombie", entities);
    
    OWL = new LivingRef(104, com.emoniph.witchery.entity.EntityOwl.class, "owl", entities).setEgg(14869218, 6049609);
    TOAD = new LivingRef(105, com.emoniph.witchery.entity.EntityToad.class, "toad", entities).setEgg(5780254, 3090974);
    
    CAT_FAMILIAR = new LivingRef(106, com.emoniph.witchery.entity.EntityWitchCat.class, "cat", entities);
    LOUSE = new LivingRef(107, com.emoniph.witchery.entity.EntityParasyticLouse.class, "louse", entities);
    EYE = new LivingRef(108, com.emoniph.witchery.entity.EntityEye.class, "eye", 150, 1, entities);
    BABA_YAGA = new LivingRef(109, com.emoniph.witchery.entity.EntityBabaYaga.class, "babayaga", entities).setEgg(7232598, 3881787);
    
    COVEN_WITCH = new LivingRef(110, com.emoniph.witchery.entity.EntityCovenWitch.class, "covenwitch", entities).addSpawn(2, 1, 1, EnumCreatureType.creature, new net.minecraft.world.biome.BiomeGenBase[] { net.minecraft.world.biome.BiomeGenBase.field_76780_h }).addSpawn(1, 1, 1, EnumCreatureType.creature, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.FOREST)).setEgg(1118481, 11523);
    


    PLAYER_CORPSE = new LivingRef(111, com.emoniph.witchery.entity.EntityCorpse.class, "corpse", entities);
    NIGHTMARE = new LivingRef(112, com.emoniph.witchery.entity.EntityNightmare.class, "nightmare", entities).setEgg(983101, 0);
    
    SPECTRE = new LivingRef(113, com.emoniph.witchery.entity.EntitySpectre.class, "spectre", entities).setEgg(1052688, 16299031);
    
    POLTERGEIST = new LivingRef(114, com.emoniph.witchery.entity.EntityPoltergeist.class, "poltergeist", entities).setEgg(12844917, 12844917);
    
    BANSHEE = new LivingRef(115, com.emoniph.witchery.entity.EntityBanshee.class, "banshee", entities).setEgg(13683116, 10136945);
    


    SPIRIT = new LivingRef(116, com.emoniph.witchery.entity.EntitySpirit.class, "spirit", entities).addSpawn(instancespawnWeightSpirit, 2, 5, EnumCreatureType.ambient, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.MESA)).addSpawn(instancespawnWeightSpirit, 2, 5, EnumCreatureType.ambient, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.FOREST)).addSpawn(instancespawnWeightSpirit, 2, 5, EnumCreatureType.ambient, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.PLAINS)).addSpawn(instancespawnWeightSpirit, 2, 5, EnumCreatureType.ambient, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.MOUNTAIN)).addSpawn(instancespawnWeightSpirit, 2, 5, EnumCreatureType.ambient, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.HILLS)).addSpawn(instancespawnWeightSpirit, 2, 5, EnumCreatureType.ambient, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.SWAMP)).addSpawn(instancespawnWeightSpirit, 2, 5, EnumCreatureType.ambient, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.SANDY)).addSpawn(instancespawnWeightSpirit, 2, 5, EnumCreatureType.ambient, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.SNOWY)).addSpawn(instancespawnWeightSpirit, 2, 5, EnumCreatureType.ambient, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.WASTELAND)).setEgg(16753968, 15649280);
    

















    DEATH = new LivingRef(117, com.emoniph.witchery.entity.EntityDeath.class, "death", entities).setEgg(0, 0);
    
    CROSSBOW_BOLT = new EntityRef(118, com.emoniph.witchery.entity.EntityBolt.class, "bolt", 64, 10, entities);
    WITCH_HUNTER = new LivingRef(119, com.emoniph.witchery.entity.EntityWitchHunter.class, "witchhunter", entities).setEgg(7893099, 2300953);
    
    BINKY_HORSE = new LivingRef(120, com.emoniph.witchery.entity.EntityDeathsHorse.class, "deathhorse", entities);
    LORD_OF_TORMENT = new LivingRef(121, com.emoniph.witchery.entity.EntityLordOfTorment.class, "lordoftorment", entities).setEgg(9502720, 3346705);
    
    SOULFIRE = new EntityRef(122, com.emoniph.witchery.entity.EntitySoulfire.class, "soulfire", 64, 3, entities);
    IMP = new LivingRef(123, com.emoniph.witchery.entity.EntityImp.class, "imp", 64, 3, entities).setEgg(5776143, 16738816);
    
    DARK_MARK = new LivingRef(124, com.emoniph.witchery.entity.EntityDarkMark.class, "darkmark", 64, 3, entities);
    MINDRAKE = new LivingRef(125, com.emoniph.witchery.entity.EntityMindrake.class, "mindrake", 64, 3, entities).setEgg(19463, 4200704);
    
    GOBLIN = new LivingRef(126, com.emoniph.witchery.entity.EntityGoblin.class, "goblin", 64, 3, entities).addSpawn(Math.max(instancegoblinSpawnRate, 1), 1, 2, EnumCreatureType.creature, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.SWAMP)).addSpawn(Math.max(instancegoblinSpawnRate, 1), 1, 3, EnumCreatureType.creature, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.FOREST)).addSpawn(Math.max(instancegoblinSpawnRate - 1, 1), 1, 2, EnumCreatureType.creature, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.PLAINS)).setEgg(10752, 15616);
    





    GOBLIN_MOG = new LivingRef(127, com.emoniph.witchery.entity.EntityGoblinMog.class, "goblinmog", 64, 3, entities).setEgg(10752, 15616);
    
    GOBLIN_GULG = new LivingRef(128, com.emoniph.witchery.entity.EntityGoblinGulg.class, "goblingulg", 64, 3, entities).setEgg(10752, 15616);
    
    BREW2 = new EntityRef(129, com.emoniph.witchery.brewing.EntityBrew.class, "brew2", 64, 1, entities);
    ITEM_WAYSTONE = new EntityRef(130, com.emoniph.witchery.entity.EntityItemWaystone.class, "item", 64, 20, entities);
    DROPLET = new EntityRef(131, com.emoniph.witchery.brewing.EntityDroplet.class, "droplet", 64, 20, entities);
    SPLATTER = new EntityRef(132, com.emoniph.witchery.brewing.EntitySplatter.class, "splatter", 64, 20, entities);
    LEONARD = new LivingRef(133, com.emoniph.witchery.entity.EntityLeonard.class, "leonard", entities).setEgg(12152634, 2818048);
    
    LOST_SOUL = new LivingRef(134, com.emoniph.witchery.entity.EntityLostSoul.class, "lostsoul", entities).setEgg(12152634, 2818116);
    
    WOLFMAN = new LivingRef(135, com.emoniph.witchery.entity.EntityWolfman.class, "wolfman", entities).setEgg(2960685, 6316128);
    
    HELLHOUND = new LivingRef(136, com.emoniph.witchery.entity.EntityHellhound.class, "hellhound", entities).addSpawn(Math.max(instancehellhoundSpawnRate, 1), 1, 3, EnumCreatureType.monster, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.NETHER)).setEgg(14181632, 5968392);
    


    WERE_VILLAGER = new LivingRef(137, com.emoniph.witchery.entity.EntityVillagerWere.class, "werevillager", entities).setEgg(5651507, 12422002);
    

    VILLAGE_GUARD = new LivingRef(138, com.emoniph.witchery.entity.EntityVillageGuard.class, "villageguard", entities).setEgg(2236962, 5322800);
    

    VAMPIRE_VILLAGER = new LivingRef(139, com.emoniph.witchery.entity.EntityVampire.class, "vampire", entities).setEgg(5322800, 13369344);
    
    GRENADE = new EntityRef(140, com.emoniph.witchery.entity.EntityGrenade.class, "grenade", 64, 1, entities);
    
    LILITH = new LivingRef(141, com.emoniph.witchery.entity.EntityLilith.class, "lilith", entities).setEgg(0, 2818048);
    
    FOLLOWER = new LivingRef(142, com.emoniph.witchery.entity.EntityFollower.class, "follower", entities);
    
    WINGED_MONKEY = new LivingRef(143, com.emoniph.witchery.entity.EntityWingedMonkey.class, "wingedmonkey", entities).setEgg(6846848, 7574709);
    

    ATTACK_BAT = new LivingRef(144, com.emoniph.witchery.entity.EntityAttackBat.class, "attackbat", entities);
    
    MIRROR_FACE = new LivingRef(145, com.emoniph.witchery.entity.EntityMirrorFace.class, "mirrorface", entities);
    REFLECTION = new LivingRef(146, com.emoniph.witchery.entity.EntityReflection.class, "reflection", entities).setEgg(5596842, 6715391);
  }
  

  public java.util.List<EntityRef> getEntites() { return entities; }
  
  public void init() {}
  
  public static class EntityRef {
    public final Class<? extends Entity> entity_class;
    public final String entity_name;
    public boolean can_capture;
    public boolean can_spawn;
    public boolean can_grind;
    
    public EntityRef(int id, Class<? extends Entity> clazz, String name, ArrayList<EntityRef> registry) { this(id, clazz, name, 80, 3, registry); }
    

    public EntityRef(int id, Class<? extends Entity> clazz, String name, int range, int updates, ArrayList<EntityRef> registry)
    {
      entity_class = clazz;
      entity_name = name;
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(clazz, name, id, Witchery.instance, range, updates, true);
      registry.add(this);
    }
    
    public EntityRef setPropsMFR(boolean canCapture, boolean canSpawn, boolean canGrind) {
      can_capture = canCapture;
      can_spawn = canSpawn;
      can_grind = canGrind;
      return this;
    }
    
    public EntityRef setEgg(int color1, int color2) {
      int eggID = getUniqueEggId();
      net.minecraft.entity.EntityList.field_75623_d.put(Integer.valueOf(eggID), entity_class);
      net.minecraft.entity.EntityList.field_75627_a.put(Integer.valueOf(eggID), new net.minecraft.entity.EntityList.EntityEggInfo(eggID, color1, color2));
      return this;
    }
    
    private static int eggRoot = 6395;
    
    private static int getUniqueEggId() {
      do {
        eggRoot += 1;
      } while (net.minecraft.entity.EntityList.func_75617_a(eggRoot) != null);
      
      return eggRoot;
    }
  }
  
  public static class LivingRef extends WitcheryEntities.EntityRef {
    public final Class<? extends EntityLiving> living_class;
    
    public LivingRef(int id, Class<? extends EntityLiving> clazz, String name, ArrayList<WitcheryEntities.EntityRef> registry) {
      super(clazz, name, 80, 3, registry);
      living_class = clazz;
    }
    
    public LivingRef(int id, Class<? extends EntityLiving> clazz, String name, int range, int updates, ArrayList<WitcheryEntities.EntityRef> registry)
    {
      super(clazz, name, range, updates, registry);
      living_class = clazz;
    }
    
    public LivingRef addSpawn(int weight, int min, int max, EnumCreatureType type, net.minecraft.world.biome.BiomeGenBase... biomes) {
      cpw.mods.fml.common.registry.EntityRegistry.addSpawn(living_class, weight, min, max, type, biomes);
      return this;
    }
  }
}
