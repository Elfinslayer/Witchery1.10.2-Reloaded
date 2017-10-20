package com.emoniph.witchery;

import com.emoniph.witchery.blocks.BlockAreaMarker.AreaMarkerRegistry;
import com.emoniph.witchery.blocks.BlockPoppetShelf.TileEntityPoppetShelf;
import com.emoniph.witchery.brewing.WitcheryBrewRegistry;
import com.emoniph.witchery.brewing.potions.WitcheryPotions;
import com.emoniph.witchery.client.KeyboardHandler;
import com.emoniph.witchery.client.PlayerRender;
import com.emoniph.witchery.common.ChantCommand;
import com.emoniph.witchery.common.CommonProxy;
import com.emoniph.witchery.common.PowerSources;
import com.emoniph.witchery.common.ServerTickEvents;
import com.emoniph.witchery.dimension.WorldProviderDreamWorld;
import com.emoniph.witchery.dimension.WorldProviderMirror;
import com.emoniph.witchery.dimension.WorldProviderTorment;
import com.emoniph.witchery.integration.ModHookArsMagica2;
import com.emoniph.witchery.integration.ModHookBaubles;
import com.emoniph.witchery.integration.ModHookBloodMagic;
import com.emoniph.witchery.integration.ModHookForestry;
import com.emoniph.witchery.integration.ModHookManager;
import com.emoniph.witchery.integration.ModHookMineFactoryReloaded;
import com.emoniph.witchery.integration.ModHookMorph;
import com.emoniph.witchery.integration.ModHookMystCraft;
import com.emoniph.witchery.integration.ModHookThaumcraft4;
import com.emoniph.witchery.integration.ModHookTinkersConstruct;
import com.emoniph.witchery.integration.ModHookTreecapitator;
import com.emoniph.witchery.integration.ModHookWaila;
import com.emoniph.witchery.item.DispenseBehaviourItemBrew;
import com.emoniph.witchery.item.DispenseBehaviourItemGeneral;
import com.emoniph.witchery.network.PacketPipeline;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.worldgen.BiomeManager;
import com.emoniph.witchery.worldgen.ComponentVillageApothecary;
import com.emoniph.witchery.worldgen.ComponentVillageBookShop;
import com.emoniph.witchery.worldgen.ComponentVillageWitchHut;
import com.emoniph.witchery.worldgen.WitcheryWorldGenerator;
import com.emoniph.witchery.worldgen.WorldHandlerVillageApothecary;
import com.emoniph.witchery.worldgen.WorldHandlerVillageBookShop;
import com.emoniph.witchery.worldgen.WorldHandlerVillageDistrict;
import com.emoniph.witchery.worldgen.WorldHandlerVillageWitchHut;
import com.google.common.collect.Lists;
import cpw.mods.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent.MissingMapping;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.Type;
import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.relauncher.Side;
import java.io.File;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IRegistry;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.OrderedLoadingCallback;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;





@Mod(modid="witchery", name="Witchery", version="0.24.1", guiFactory="com.emoniph.witchery.util.WitcheryGuiFactory", dependencies="required-after:Forge@[10.13.2.1277,);after:MineFactoryReloaded;after:NotEnoughItems;after:Waila;after:ForgeMultipart;after:AWWayofTime")
public class Witchery
{
  public static final String MOD_ID = "witchery";
  public static final String MOD_PREFIX = "WITC";
  @SidedProxy(clientSide="com.emoniph.witchery.client.ClientProxy", serverSide="com.emoniph.witchery.common.CommonProxy")
  public static CommonProxy proxy;
  @Mod.Instance("witchery")
  public static Witchery instance;
  public static final PacketPipeline packetPipeline = new PacketPipeline();
  public static WitcheryRecipes Recipes = new WitcheryRecipes();
  public static final ModHookManager modHooks = new ModHookManager();
  public static WitcheryPotions Potions;
  public static WitcheryFluids Fluids;
  public static WitcheryBlocks Blocks;
  public static WitcheryItems Items;
  public static WitcheryEntities Entities;
  private static WitcheryWorldGenerator worldGenerator;
  public static File configDirectoryPath;
  public static Configuration config;
  public static Configuration config_debug;
  public static boolean isDeathChestModInstalled;
  
  public Witchery() {}
  
  @Mod.EventHandler
  public void preInit(FMLPreInitializationEvent event) {
    if (instance != this) {
      Log.instance().warning("instance static not set");
    }
    


    configDirectoryPath = event.getModConfigurationDirectory();
    config = new Configuration(new File(String.format("%s/%s.cfg", new Object[] { configDirectoryPath, "witchery" })));
    config_debug = new Configuration(new File(String.format("%s/%s_debug.cfg", new Object[] { configDirectoryPath, "witchery" })));
    Config.instance().init(config, config_debug);
    
    worldGenerator = new WitcheryWorldGenerator();
    GameRegistry.registerWorldGenerator(worldGenerator, 0);
    
    if (instancegenerateApothecaries) {
      WorldHandlerVillageApothecary villageApothecaryHandler = new WorldHandlerVillageApothecary();
      VillagerRegistry.instance().registerVillagerId(instanceapothecaryID);
      proxy.registerVillagers();
      VillagerRegistry.instance().registerVillageCreationHandler(villageApothecaryHandler);
      VillagerRegistry.instance().registerVillageTradeHandler(instanceapothecaryID, villageApothecaryHandler);
      try {
        MapGenStructureIO.func_143031_a(ComponentVillageApothecary.class, "witchery:Apothecary");
      }
      catch (Throwable e) {}
    }
    if (instancegenerateWitchHuts) {
      WorldHandlerVillageWitchHut villageWitchHutHandler = new WorldHandlerVillageWitchHut();
      VillagerRegistry.instance().registerVillageCreationHandler(villageWitchHutHandler);
      try {
        MapGenStructureIO.func_143031_a(ComponentVillageWitchHut.class, "witchery:witchhut");
      }
      catch (Throwable e) {}
    }
    if (instancegenerateBookShops) {
      WorldHandlerVillageBookShop villageBookShopHandler = new WorldHandlerVillageBookShop();
      VillagerRegistry.instance().registerVillageCreationHandler(villageBookShopHandler);
      try {
        MapGenStructureIO.func_143031_a(ComponentVillageBookShop.class, "witchery:bookshop");
      }
      catch (Throwable e) {}
    }
    





    WorldHandlerVillageDistrict.preInit();
    
    proxy.preInit();
    packetPipeline.preInit();
    
    Potions = new WitcheryPotions();
    Fluids = new WitcheryFluids();
    Blocks = new WitcheryBlocks();
    Items = new WitcheryItems();
    Entities = new WitcheryEntities();
    
    Recipes.preInit();
    Potions.preInit();
    
    FMLCommonHandler.instance().bus().register(new ServerTickEvents());
    
    if (event.getSide() == Side.CLIENT) {
      FMLCommonHandler.instance().bus().register(new PlayerRender());
      FMLCommonHandler.instance().bus().register(new KeyboardHandler());
    }
  }
  
  @Mod.EventHandler
  public void onMissingMappings(FMLMissingMappingsEvent event) {
    for (FMLMissingMappingsEvent.MissingMapping missing : event.get()) {
      if (name != null) {
        int index = name.lastIndexOf(':');
        String strippedName = (index != -1) && (name.length() > index) ? name.substring(index + 1) : name;
        if (type == GameRegistry.Type.BLOCK) {
          Block replacement = GameRegistry.findBlock("witchery", strippedName);
          if (replacement != null) {
            missing.remap(replacement);
          }
        } else if (type == GameRegistry.Type.ITEM) {
          Item replacement = GameRegistry.findItem("witchery", strippedName);
          if (replacement != null) {
            missing.remap(replacement);
          }
        }
      }
    }
  }
  
  @SubscribeEvent
  public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
    if (modID.equals("witchery")) {
      Config.instance().sync();
    }
  }
  
  @Mod.EventHandler
  public void init(FMLInitializationEvent event) {
    FMLCommonHandler.instance().bus().register(instance);
    
    WorldHandlerVillageDistrict.init();
    
    packetPipeline.init();
    
    Entities.init();
    
    NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy);
    DimensionManager.registerProviderType(instancedimensionDreamID, WorldProviderDreamWorld.class, false);
    DimensionManager.registerDimension(instancedimensionDreamID, instancedimensionDreamID);
    
    DimensionManager.registerProviderType(instancedimensionTormentID, WorldProviderTorment.class, false);
    DimensionManager.registerDimension(instancedimensionTormentID, instancedimensionTormentID);
    
    DimensionManager.registerProviderType(instancedimensionMirrorID, WorldProviderMirror.class, false);
    DimensionManager.registerDimension(instancedimensionMirrorID, instancedimensionMirrorID);
    
    MinecraftForge.addGrassSeed(new ItemStack(ItemsSEEDS_ARTICHOKE), 3);
    MinecraftForge.addGrassSeed(new ItemStack(ItemsSEEDS_BELLADONNA), 4);
    MinecraftForge.addGrassSeed(new ItemStack(ItemsSEEDS_MANDRAKE), 5);
    MinecraftForge.addGrassSeed(new ItemStack(ItemsSEEDS_SNOWBELL), 2);
    MinecraftForge.addGrassSeed(new ItemStack(ItemsSEEDS_WOLFSBANE), 1);
    MinecraftForge.addGrassSeed(new ItemStack(ItemsSEEDS_GARLIC), 1);
    
    proxy.registerHandlers();
    proxy.registerServerHandlers();
    proxy.registerRenderers();
    
    isDeathChestModInstalled = (instancerespectOtherDeathChestMods) && ((Loader.isModLoaded("tombstone")) || (Loader.isModLoaded("OpenBlocks")) || (Loader.isModLoaded("Taigore_InventorySaver")) || (Loader.isModLoaded("KeepItemsOnDeath")));
    
    modHooks.register(ModHookArsMagica2.class);
    modHooks.register(ModHookBloodMagic.class);
    modHooks.register(ModHookForestry.class);
    modHooks.register(ModHookMineFactoryReloaded.class);
    modHooks.register(ModHookMystCraft.class);
    modHooks.register(ModHookThaumcraft4.class);
    modHooks.register(ModHookTinkersConstruct.class);
    modHooks.register(ModHookTreecapitator.class);
    modHooks.register(ModHookWaila.class);
    modHooks.register(ModHookMorph.class);
    modHooks.register(ModHookBaubles.class);
    
    modHooks.init();
    
    Potions.init();
    Recipes.init();
    WitcheryBrewRegistry.INSTANCE.init();
  }
  

  @Mod.EventHandler
  public void postInit(FMLPostInitializationEvent event)
  {
    Recipes.postInit();
    modHooks.postInit();
    try
    {
      BiomeManager.addModBiomes();
    } catch (Exception e) {
      Log.instance().warning(e, "Failed to add external mod biome postInit compatability");
    }
    
    proxy.postInit();
    proxy.registerEvents();
    BlockDispenser.field_149943_a.func_82595_a(ItemsGENERIC, new DispenseBehaviourItemGeneral());
    BlockDispenser.field_149943_a.func_82595_a(ItemsBREW, new DispenseBehaviourItemBrew());
    BlockDispenser.field_149943_a.func_82595_a(ItemsBREW_ENDLESS_WATER, new DispenseBehaviourItemBrew());
    BlockDispenser.field_149943_a.func_82595_a(Items.field_151069_bo, new DispenseBehaviourItemBrew());
    BlockDispenser.field_149943_a.func_82595_a(ItemsSUN_GRENADE, new DispenseBehaviourItemBrew());
    BlockDispenser.field_149943_a.func_82595_a(ItemsDUP_GRENADE, new DispenseBehaviourItemBrew());
    ForgeChunkManager.setForcedChunkLoadingCallback(instance, new ChunkloadCallback());
  }
  
  @Mod.EventHandler
  public void serverLoad(FMLServerStartingEvent event) {
    event.registerServerCommand(new ChantCommand());
    
    PowerSources.initiate();
    BlockAreaMarker.AreaMarkerRegistry.serverStart();
    worldGenerator.initiate();
  }
  
  public static class ChunkloadCallback implements ForgeChunkManager.OrderedLoadingCallback {
    public ChunkloadCallback() {}
    
    public void ticketsLoaded(List<ForgeChunkManager.Ticket> tickets, World world) { for (ForgeChunkManager.Ticket ticket : tickets) {
        int posX = ticket.getModData().func_74762_e("poppetX");
        int posY = ticket.getModData().func_74762_e("poppetY");
        int posZ = ticket.getModData().func_74762_e("poppetZ");
        BlockPoppetShelf.TileEntityPoppetShelf tileEntity = (BlockPoppetShelf.TileEntityPoppetShelf)world.func_147438_o(posX, posY, posZ);
        tileEntity.forceChunkLoading(ticket);
      }
    }
    
    public List<ForgeChunkManager.Ticket> ticketsLoaded(List<ForgeChunkManager.Ticket> tickets, World world, int maxTicketCount)
    {
      List<ForgeChunkManager.Ticket> validTickets = Lists.newArrayList();
      for (ForgeChunkManager.Ticket ticket : tickets) {
        int posX = ticket.getModData().func_74762_e("poppetX");
        int posY = ticket.getModData().func_74762_e("poppetY");
        int posZ = ticket.getModData().func_74762_e("poppetZ");
        Block block = world.func_147439_a(posX, posY, posZ);
        if (block == BlocksPOPPET_SHELF) {
          validTickets.add(ticket);
        }
      }
      return validTickets;
    }
  }
  
  public static String resource(String id) {
    String s = StatCollector.func_74838_a(id);
    return s.replace("|", "\n").replace("{", "§");
  }
}
