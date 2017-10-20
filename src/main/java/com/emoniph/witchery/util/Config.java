package com.emoniph.witchery.util;

import com.emoniph.witchery.worldgen.ComponentVillageWatchTower;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.gen.structure.StructureVillagePieces.Church;
import net.minecraft.world.gen.structure.StructureVillagePieces.Field1;
import net.minecraft.world.gen.structure.StructureVillagePieces.Field2;
import net.minecraft.world.gen.structure.StructureVillagePieces.Hall;
import net.minecraft.world.gen.structure.StructureVillagePieces.House1;
import net.minecraft.world.gen.structure.StructureVillagePieces.House2;
import net.minecraft.world.gen.structure.StructureVillagePieces.House3;
import net.minecraft.world.gen.structure.StructureVillagePieces.House4Garden;
import net.minecraft.world.gen.structure.StructureVillagePieces.Village;
import net.minecraft.world.gen.structure.StructureVillagePieces.WoodHut;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;























































































public class Config
{
  private static final Config INSTANCE = new Config();
  public Configuration configuration;
  
  public static Config instance() { return INSTANCE; }
  

  public boolean smeltAllSaplingsToWoodAsh;
  
  public boolean guiOnLeft;
  
  public boolean doubleFumeFilterChance;
  
  public boolean allowModIntegration;
  
  public boolean allowThaumcraft;
  
  public boolean allowMineFactoryReloaded;
  
  public boolean allowArsMagica2;
  
  public boolean allowForestry;
  
  public boolean allowTreecapitator;
  
  public boolean allowNotEnoughItems;
  
  public boolean generateApothecaries;
  
  public boolean generateWitchHuts;
  
  public boolean generateBookShops;
  
  public int apothecaryID;
  
  public boolean generateCovens;
  
  public boolean generateWickerMen;
  
  public boolean generateShacks;
  
  public boolean generateGoblinHuts;
  
  public boolean allowDeathItemRecoveryRite;
  
  public boolean respectOtherDeathChestMods;
  
  public int worldGenFrequency;
  
  public boolean worldGenTwilightForest;
  
  public boolean allowStatueGoddessRecipe;
  
  public String[] strawmanSpawnerRules;
  
  public boolean allowHellOnEarthFires;
  
  public boolean allowVoidBrambleRecipe;
  
  public boolean allowBiomeChanging;
  
  public int covenWitchSpawnWeight;
  
  public int goblinSpawnWeight;
  public int branchIconSet;
  public int dimensionDreamID;
  public int dimensionTormentID;
  public int dimensionMirrorID;
  public int percentageOfPlayersSleepingForBuff;
  public boolean render3dGlintEffect;
  public boolean renderHuntsmanGlintEffect;
  public boolean allowMystcraft;
  public boolean restrictPoppetShelvesToVanillaAndSpiritDimensions;
  public boolean allowBlockBreakEvents;
  public boolean allowDeathsHoodToFreezeVictims;
  public int decurseTeleportPullRadius;
  public int decurseDirectedRadius;
  public boolean allowDecurseTeleport;
  public boolean allowDecurseDirected;
  public boolean restrictTaglockCollectionOnNonPVPServers;
  public boolean restrictTaglockCollectionForStaffMembers;
  public int goblinSpawnRate;
  public String[] mutandisExtras;
  
  private Config() {}
  
  public void init(Configuration configuration, Configuration configuration_debug)
  {
    this.configuration = configuration;
    this.configuration.load();
    sync();
    
    configuration_debug.load();
    traceRitesEnabled = configuration_debug.get("Debug", "TraceRites", false).getBoolean(false);
    debugging = configuration_debug.get("Debug", "Debugging", false).getBoolean(false);
    dupStaffSaveTemplate = configuration_debug.get("Debug", "SaveDupStaffTemplate", false).getBoolean(false);
  }
  
  public void sync()
  {
    smeltAllSaplingsToWoodAsh = configuration.get("general", "AddSmeltingForAllSaplingsToWoodAsh", true).getBoolean(true);
    
    guiOnLeft = configuration.get("general", "GUIOnLeft", true).getBoolean(true);
    
    doubleFumeFilterChance = configuration.get("general", "DoubleFumeFilterChance", false).getBoolean(false);
    
    allowModIntegration = configuration.get("general", "AllowModIntegration", true).getBoolean(true);
    
    allowThaumcraft = configuration.get("general", "AllowModThaumcraft", true).getBoolean(true);
    
    allowMineFactoryReloaded = configuration.get("general", "AllowModMineFactoryReloaded", true).getBoolean(true);
    
    allowForestry = configuration.get("general", "AllowModForestry", true).getBoolean(true);
    
    allowTreecapitator = configuration.get("general", "AllowModTreecapitator", true).getBoolean(true);
    
    allowNotEnoughItems = configuration.get("general", "AllowModNEI", true).getBoolean(true);
    
    generateApothecaries = configuration.get("general", "GenerateApothecaries", true).getBoolean(true);
    
    apothecaryID = configuration.get("general", "ApothecaryVillagerID", 2435).getInt();
    
    generateCovens = configuration.get("general", "GenerateCovens", true).getBoolean(true);
    
    generateWickerMen = configuration.get("general", "GenerateWickerMen", true).getBoolean(true);
    
    generateShacks = configuration.get("general", "GenerateShacks", true).getBoolean(true);
    
    generateGoblinHuts = configuration.get("general", "GenerateHobgoblinHuts", true).getBoolean(true);
    
    allowDeathItemRecoveryRite = configuration.get("general", "AllowDeathItemRecoveryRite", true).getBoolean(true);
    
    respectOtherDeathChestMods = configuration.get("general", "RespectOtherDeathChestMods", true).getBoolean(true);
    
    worldGenTwilightForest = configuration.get("general", "WorldGenInTwilightForest", true).getBoolean(true);
    
    worldGenFrequency = configuration.get("general", "WorldGenFrequency", 12).getInt();
    
    allowStatueGoddessRecipe = configuration.get("general", "AllowGoddessStatueRecipe", false).getBoolean(false);
    
    allowHellOnEarthFires = configuration.get("general", "AllowHellOnEarthFires", true).getBoolean(true);
    
    allowVoidBrambleRecipe = configuration.get("general", "AllowVoidBrambleRecipe", false).getBoolean(false);
    
    allowBiomeChanging = configuration.get("general", "AllowBiomeModificationRituals", true).getBoolean(true);
    
    covenWitchSpawnWeight = configuration.get("general", "CovenWitchSpawnWeight", 2).getInt();
    
    goblinSpawnWeight = configuration.get("general", "HobgoblinSpawnChance", 2).getInt();
    
    goblinSpawnRate = configuration.get("general", "HobgoblinSpawnWeight", 4).getInt();
    
    goblinDespawnBlock = configuration.get("general", "HobgoblinDespawnBlock", true).getBoolean(true);
    

    branchIconSet = configuration.get("general", "BranchGlyphSet", 0).getInt();
    dimensionDreamID = configuration.get("general", "DreamDimensionID", -37).getInt();
    
    dimensionTormentID = configuration.get("general", "TormentDimensionID", -38).getInt();
    
    dimensionMirrorID = configuration.get("general", "MirrorDimensionID", -39).getInt();
    
    percentageOfPlayersSleepingForBuff = Math.max(Math.min(configuration.get("general", "PercentageOfPlayersSleepingForBuff", 100).getInt(), 100), 1);
    


    render3dGlintEffect = configuration.get("general", "Render3dGlintEffect", true).getBoolean(true);
    
    renderHuntsmanGlintEffect = configuration.get("general", "RenderHuntsmanGlintEffect", true).getBoolean(true);
    
    allowMystcraft = configuration.get("general", "AllowModMystcraft", true).getBoolean(true);
    
    allowArsMagica2 = configuration.get("general", "AllowModArsMagica2", true).getBoolean(true);
    
    restrictPoppetShelvesToVanillaAndSpiritDimensions = configuration.get("general", "RestrictPoppetShelvesToVanillaAndSpiritDimensions", true).getBoolean(true);
    

    allowBlockBreakEvents = configuration.get("general", "AllowInterModBlockBreakEvents", true).getBoolean(true);
    
    allowDeathsHoodToFreezeVictims = configuration.get("general", "AllowDeathsHoodToFreezeVictims", true).getBoolean(true);
    
    strawmanSpawnerRules = configuration.get("general", "StrawmanSpawnerRules", new String[] { "Zombie", "Zombie", "Skeleton" }).getStringList();
    
    generateWitchHuts = configuration.get("general", "GenerateWitchHuts", true).getBoolean(true);
    

    generateBookShops = configuration.get("general", "GenerateBookShops", true).getBoolean(true);
    

    decurseTeleportPullRadius = Math.min(Math.max(configuration.get("general", "DecurseTeleportPullProtectRadius", 32).getInt(), 0), 128);
    


    decurseDirectedRadius = Math.min(Math.max(configuration.get("general", "DecurseDirectedProtectRadius", 32).getInt(), 0), 128);
    

    allowDecurseDirected = configuration.get("general", "DecurseDirectedEnabled", false).getBoolean(false);
    
    allowDecurseTeleport = configuration.get("general", "DecurseTeleportPullEnabled", false).getBoolean(false);
    

    restrictTaglockCollectionOnNonPVPServers = configuration.get("general", "RestrictTaglockCollectionOnNonPVPServers", false).getBoolean(false);
    
    restrictTaglockCollectionForStaffMembers = configuration.get("general", "RestrictTaglockCollectionForOPs", false).getBoolean(false);
    

    potionStartID = Math.max(configuration.get("general", "PotionStartID", 32).getInt(), 32);
    

    mutandisExtras = configuration.get("general", "MutandisAdditionalBlocks", new String[] { "witchery:glintweed,0", "tallgrass,2" }).getStringList();
    
    hobgoblinGodSpawnChance = Math.max(Math.min(configuration.get("general", "HobgoblinGodSpawnChance", 10).getInt(), 100), 0);
    

    hellhoundSpawnRate = configuration.get("general", "HellhoundSpawnWeight", 25).getInt();
    

    spawnWeightSpirit = MathHelper.func_76125_a(configuration.get("general", "SpiritSpawnWeight", 1).getInt(), 1, 1000);
    

    allowExplodingCreeperHearts = configuration.get("general", "CreeperHeartsExplodeWithDamageWhenEaten", true).getBoolean(true);
    
    mantrapAlpha = ((float)Math.min(1.0D, Math.max(0.1D, configuration.get("general", "MantrapOpacity", 0.3D).getDouble(0.3D))));
    

    allowVolatilityPotionBlockDamage = configuration.get("general", "AllowVolatilityPotionBlockDamage", true).getBoolean(true);
    
    diseaseRemovalChance = configuration.get("general", "DiseaseBlockRemovalChance", 10).getInt();
    

    vampireDeathItemKeepAliveMins = configuration.get("general", "VampireDeathItemKeepAliveMins", 12).getInt();
    

    hudShowVampireTargetBloodText = configuration.get("general", "HUDShowVampireTargetBloodText", false).getBoolean(false);
    

    vampireHunterSpawnChance = ((float)Math.min(1.0D, Math.max(0.0D, configuration.get("general", "VampireHunterSpawnChance", 0.01D).getDouble(0.01D))));
    

    fairestSpawnChance = MathHelper.func_151237_a(configuration.get("general", "NewFairestOfThemAllSpawnChance", 0.01D).getDouble(0.01D), 0.01D, 1.0D);
    


    allowPlayerToPlayerWolfInfection = configuration.get("general", "AllowPlayerToPlayerWolfInfection", true).getBoolean(true);
    

    riteOfEclipseCooldownInSecs = MathHelper.func_76125_a(configuration.get("general", "RiteOfEclipseCooldownInSecs", 0).getInt(), 0, 3600);
    


    allowChatMasquerading = configuration.get("general", "AllowChatMasquerading", true).getBoolean(true);
    

    allowNameplateMasquerading = configuration.get("general", "AllowNameplateMasquerading", true).getBoolean(true);
    

    shrinkMirrorWorld = configuration.get("general", "ShrinkMirrorWorld", false).getBoolean(false);
    

    townZombieMode = Math.min(Math.max(configuration.get("general", "TownZombieAttackReductionMode", 1).getInt(), 0), 2);
    

    townParts = new ArrayList();
    new Building(StructureVillagePieces.House4Garden.class, "GardenHouse", 3, 20, 3, 5, this);
    new Building(StructureVillagePieces.House1.class, "House", 3, 20, 3, 5, this);
    new Building(StructureVillagePieces.WoodHut.class, "WoodHut", 3, 20, 3, 5, this);
    new Building(StructureVillagePieces.Hall.class, "Hall", 3, 20, 3, 5, this);
    new Building(StructureVillagePieces.House3.class, "House3", 3, 20, 3, 5, this);
    new Building(StructureVillagePieces.Field1.class, "SingleField", 3, 20, 3, 5, this);
    new Building(StructureVillagePieces.Field2.class, "DoubleField", 3, 20, 3, 5, this);
    new Building(StructureVillagePieces.House2.class, "Blacksmith", 1, 5, 0, 1, this);
    new Building(StructureVillagePieces.Church.class, "Church", 0, 10, 0, 1, this);
    new Building(ComponentVillageWatchTower.class, "GuardTower", 4, 20, 0, 1, this);
    
    townWallChance = Math.min(Math.max(configuration.get("general", "TownWallMode", 1).getInt(), 0), 2);
    
    townWallWeight = Math.min(Math.max(configuration.get("general", "TownWallWeight", 100).getInt(), 0), 1000);
    

    townKeepChance = Math.min(Math.max(configuration.get("general", "TownKeepMode", 1).getInt(), 0), 2);
    
    townKeepWeight = Math.min(Math.max(configuration.get("general", "TownKeepWeight", 100).getInt(), 0), 1000);
    


    townAllowSandy = configuration.get("general", "TownBiomeSandyAllowed", true).getBoolean(true);
    
    townAllowPlains = configuration.get("general", "TownBiomePlainsAllowed", true).getBoolean(true);
    
    townAllowMountain = configuration.get("general", "TownBiomeMountainAllowed", true).getBoolean(true);
    
    townAllowHills = configuration.get("general", "TownBiomeHillsAllowed", true).getBoolean(true);
    
    townAllowForest = configuration.get("general", "TownBiomeForestAllowed", true).getBoolean(true);
    
    townAllowSnowy = configuration.get("general", "TownBiomeSnowyAllowed", true).getBoolean(true);
    
    townAllowWasteland = configuration.get("general", "TownBiomeWastelandAllowed", true).getBoolean(true);
    
    townAllowMesa = configuration.get("general", "TownBiomeMesaAllowed", true).getBoolean(true);
    
    townAllowJungle = configuration.get("general", "TownBiomeJungleAllowed", false).getBoolean(false);
    

    townBooks = configuration.get("general", "TownBookshopAllowedBooks", new String[] { "book", "witchery:ingredient,46", "witchery:ingredient,47", "witchery:ingredient,48", "witchery:ingredient,49", "witchery:ingredient,81", "witchery:ingredient,106", "witchery:ingredient,107", "witchery:ingredient,127", "witchery:bookbiomes2", "witchery:cauldronbook", "Thaumcraft:ItemThaumonomicon", "TConstruct:manualBook", "TConstruct:manualBook,1", "TConstruct:manualBook,2", "TConstruct:manualBook,3" }).getStringList();
    


















    String[] replaceableBlocks = configuration.get("general", "NaturesPowerReplaceableBlocks", new String[] { "mycelium" }).getStringList();
    



    naturePowerReplaceableBlocks = new ArrayList();
    
    for (String extra : replaceableBlocks) {
      try {
        naturePowerReplaceableBlocks.add(new BlockMeta(extra));
      }
      catch (Throwable ex) {}
    }
    
    allowVampireWolfHybrids = configuration.get("general", "AllowVampireWerewolfHybrids", true).getBoolean(true);
    allowStakingVampires = configuration.get("general", "AllowStakingVampires", true).getBoolean(true);
    allowCovenWitchVisits = configuration.get("general", "AllowCovenWitchVisits", true).getBoolean(true);
    allowVampireQuests = configuration.get("general", "AllowVampireQuests", true).getBoolean(true);
    allowVampireRitual = configuration.get("general", "AllowVampireRitual", true).getBoolean(true);
    
    saveIfChanged();
  }
  
  private static class BlockMeta {
    private final Block block;
    private final int metadata;
    
    public BlockMeta(String extra) { String name = extra;
      int meta = 32767;
      int comma = extra.lastIndexOf(',');
      if (comma >= 0) {
        name = extra.substring(0, comma);
        
        String metaString = extra.substring(comma + 1);
        meta = Integer.parseInt(metaString);
      }
      

      block = Block.func_149684_b(name);
      metadata = meta;
    }
    
    public boolean isMatch(Block b, int m) { return (b == block) && ((metadata == 32767) || (metadata == m)); } }
  
  public int hobgoblinGodSpawnChance;
  public boolean hudShowVampireTargetBloodText;
  public String[] townBooks;
  public double vampireHunterSpawnChance;
  public int potionStartID;
  public boolean dupStaffSaveTemplate;
  private boolean traceRitesEnabled;
  private boolean debugging;
  public int hellhoundSpawnRate;
  public boolean allowExplodingCreeperHearts;
  public float mantrapAlpha;
  public int townZombieMode;
  public boolean allowVolatilityPotionBlockDamage;
  public boolean allowStakingVampires;
  public boolean allowCovenWitchVisits;
  public boolean allowVampireRitual;
  public boolean allowVampireQuests;
  public boolean shrinkMirrorWorld;
  private static final String CategoryEntity = "Entity";
  private List<BlockMeta> naturePowerReplaceableBlocks;
  public boolean allowVampireWolfHybrids;
  public int townWallChance;
  public int townWallWeight;
  public int townKeepChance;
  public int townKeepWeight;
  public boolean townAllowSandy;
  public boolean townAllowPlains;
  public boolean townAllowMountain;
  public boolean townAllowHills;
  public boolean townAllowForest;
  public boolean townAllowSnowy;
  public boolean townAllowWasteland;
  public boolean townAllowJungle;
  public boolean townAllowMesa;
  public List<Building> townParts;
  public boolean goblinDespawnBlock;
  public int diseaseRemovalChance;
  public int vampireDeathItemKeepAliveMins;
  public int spawnWeightSpirit;
  public double fairestSpawnChance;
  public boolean allowPlayerToPlayerWolfInfection;
  public int riteOfEclipseCooldownInSecs;
  public boolean allowChatMasquerading;
  public boolean allowNameplateMasquerading;
  public static class Building { private static final String TOWN = "Town";
    public final int groups;
    public final int weight;
    public final int min;
    public final int max;
    public Building(Class<? extends StructureVillagePieces.Village> clazz, String name, int groups, int weight, int min, int max, Config config) { this.clazz = clazz;
      this.groups = configuration.get("general", "Town" + name + "ClusterGroups", groups).getInt();
      
      this.weight = configuration.get("general", "Town" + name + "ClusterWeight", weight).getInt();
      
      this.min = configuration.get("general", "Town" + name + "ClusterMin", min).getInt();
      
      this.max = configuration.get("general", "Town" + name + "ClusterMax", max).getInt();
      
      townParts.add(this);
    }
    
    public final Class<? extends StructureVillagePieces.Village> clazz; }
  
  public boolean isDebugging() { return debugging; }
  

  public boolean traceRites() {
    return traceRitesEnabled;
  }
  
  public void saveIfChanged() {
    if (configuration.hasChanged()) {
      configuration.save();
    }
  }
  
  public boolean isReduceZombeVillagerDamageActive() {
    return townZombieMode >= 1;
  }
  
  public boolean isZombeIgnoreVillagerActive() {
    return townZombieMode >= 2;
  }
  
  public boolean canReplaceNaturalBlock(Block block, int meta) {
    for (BlockMeta bm : naturePowerReplaceableBlocks) {
      if (bm.isMatch(block, meta)) {
        return true;
      }
    }
    return false;
  }
}
