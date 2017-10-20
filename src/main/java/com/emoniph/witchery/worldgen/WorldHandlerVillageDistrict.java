package com.emoniph.witchery.worldgen;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.blocks.BlockBaseContainer;
import com.emoniph.witchery.blocks.TileEntityBase;
import com.emoniph.witchery.entity.EntityVillageGuard;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.Config.Building;
import com.emoniph.witchery.util.Log;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageCreationHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces.Church;
import net.minecraft.world.gen.structure.StructureVillagePieces.Field1;
import net.minecraft.world.gen.structure.StructureVillagePieces.Field2;
import net.minecraft.world.gen.structure.StructureVillagePieces.Hall;
import net.minecraft.world.gen.structure.StructureVillagePieces.House1;
import net.minecraft.world.gen.structure.StructureVillagePieces.House2;
import net.minecraft.world.gen.structure.StructureVillagePieces.House3;
import net.minecraft.world.gen.structure.StructureVillagePieces.House4Garden;
import net.minecraft.world.gen.structure.StructureVillagePieces.Path;
import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
import net.minecraft.world.gen.structure.StructureVillagePieces.Village;
import net.minecraft.world.gen.structure.StructureVillagePieces.WoodHut;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.BiomeEvent.GetVillageBlockID;
import net.minecraftforge.event.terraingen.BiomeEvent.GetVillageBlockMeta;

public class WorldHandlerVillageDistrict implements VillagerRegistry.IVillageCreationHandler
{
  private final Class<? extends StructureVillagePieces.Village> pieceClazz;
  private final int weight;
  private final int quantityMin;
  private final int quantityMax;
  
  public WorldHandlerVillageDistrict(Class<? extends StructureVillagePieces.Village> clazz, int weight, int min)
  {
    this(clazz, weight, min, min);
  }
  
  public WorldHandlerVillageDistrict(Class<? extends StructureVillagePieces.Village> clazz, int weight, int min, int max)
  {
    pieceClazz = clazz;
    this.weight = weight;
    quantityMin = min;
    quantityMax = max;
  }
  
  public StructureVillagePieces.PieceWeight getVillagePieceWeight(Random rand, int size)
  {
    return new StructureVillagePieces.PieceWeight(pieceClazz, weight, quantityMax <= quantityMin ? quantityMin : quantityMin + rand.nextInt(quantityMax - quantityMin + 1));
  }
  

  public Class getComponentClass()
  {
    return pieceClazz;
  }
  

  public Object buildComponent(StructureVillagePieces.PieceWeight weight, StructureVillagePieces.Start startPiece, List pieces, Random rand, int p1, int p2, int p3, int p4, int p5)
  {
    Object object = null;
    
    if (pieceClazz == StructureVillagePieces.House4Garden.class) {
      object = StructureVillagePieces.House4Garden.func_74912_a(startPiece, pieces, rand, p1, p2, p3, p4, p5);
    } else if (pieceClazz == StructureVillagePieces.Church.class) {
      object = StructureVillagePieces.Church.func_74919_a(startPiece, pieces, rand, p1, p2, p3, p4, p5);
    } else if (pieceClazz == StructureVillagePieces.House1.class) {
      object = StructureVillagePieces.House1.func_74898_a(startPiece, pieces, rand, p1, p2, p3, p4, p5);
    } else if (pieceClazz == StructureVillagePieces.WoodHut.class) {
      object = StructureVillagePieces.WoodHut.func_74908_a(startPiece, pieces, rand, p1, p2, p3, p4, p5);
    } else if (pieceClazz == StructureVillagePieces.Hall.class) {
      object = StructureVillagePieces.Hall.func_74906_a(startPiece, pieces, rand, p1, p2, p3, p4, p5);
    } else if (pieceClazz == StructureVillagePieces.Field1.class) {
      object = StructureVillagePieces.Field1.func_74900_a(startPiece, pieces, rand, p1, p2, p3, p4, p5);
    } else if (pieceClazz == StructureVillagePieces.Field2.class) {
      object = StructureVillagePieces.Field2.func_74902_a(startPiece, pieces, rand, p1, p2, p3, p4, p5);
    } else if (pieceClazz == StructureVillagePieces.House2.class) {
      object = StructureVillagePieces.House2.func_74915_a(startPiece, pieces, rand, p1, p2, p3, p4, p5);
    } else if (pieceClazz == StructureVillagePieces.House3.class) {
      object = StructureVillagePieces.House3.func_74921_a(startPiece, pieces, rand, p1, p2, p3, p4, p5);
    } else if (pieceClazz == Wall.class) {
      object = Wall.func_74921_a(startPiece, pieces, rand, p1, p2, p3, p4, p5);
    } else if (pieceClazz == ComponentVillageWatchTower.class) {
      object = ComponentVillageWatchTower.construct(startPiece, pieces, rand, p1, p2, p3, p4, p5);
    } else if (pieceClazz == ComponentVillageKeep.class) {
      object = ComponentVillageKeep.construct(startPiece, pieces, rand, p1, p2, p3, p4, p5);
    }
    



    return object == null ? null : (StructureVillagePieces.Village)object;
  }
  
  public static void registerComponent(Class<? extends StructureVillagePieces.Village> clazz, int weight, int min, int max) {
    VillagerRegistry.instance().registerVillageCreationHandler(new WorldHandlerVillageDistrict(clazz, weight, min, max));
  }
  
  public static void preInit()
  {
    try {
      MapGenStructureIO.func_143031_a(Wall.class, "witchery:villagewall");
      MapGenStructureIO.func_143031_a(ComponentVillageKeep.class, "witchery:villagekeep");
      MapGenStructureIO.func_143031_a(ComponentVillageWatchTower.class, "witchery:villagewatchtower");
    }
    catch (Throwable e) {}
    
    if (instancetownWallChance > 0) {
      registerComponent(Wall.class, instancetownWallWeight, instancetownWallChance == 2 ? 1 : 0, 1);
    }
    

    if (instancetownKeepChance > 0) {
      registerComponent(ComponentVillageKeep.class, instancetownKeepWeight, instancetownKeepChance == 2 ? 1 : 0, 1);
    }
    

    VillagerRegistry register = VillagerRegistry.instance();
    
    for (Config.Building building : instancetownParts) {
      for (int i = 0; i < groups; i++) {
        register.registerVillageCreationHandler(new WorldHandlerVillageDistrict(clazz, weight, min, max));
      }
    }
  }
  

  public static void init()
  {
    for (BiomeGenBase biome : )
      if (biome != null)
      {


        if ((!BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.WET)) && (!BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.OCEAN)) && (!BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.BEACH)) && (!BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.END)) && (!BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.JUNGLE)) && (!BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.NETHER)) && (!BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.RIVER)) && (!BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.WATER)))
        {








          boolean disallowed = ((!instancetownAllowSandy) && (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.SANDY))) || ((!instancetownAllowPlains) && (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.PLAINS))) || ((!instancetownAllowMountain) && (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.MOUNTAIN))) || ((!instancetownAllowHills) && (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.HILLS))) || ((!instancetownAllowForest) && (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.FOREST))) || ((!instancetownAllowSnowy) && (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.SNOWY))) || ((!instancetownAllowWasteland) && (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.WASTELAND))) || ((!instancetownAllowJungle) && (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.JUNGLE))) || ((!instancetownAllowMesa) && (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.MESA)));
          











          if (!disallowed)
          {


            BiomeManager.addVillageBiome(biome, true); }
        } }
  }
  
  public static class EventHooks { public EventHooks() {}
    
    @SubscribeEvent
    public void onGetVillageBlock(BiomeEvent.GetVillageBlockID event) { if (biome == null) {
        return;
      }
      
      Block b = original;
      if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.SANDY)) {
        if ((b == Blocks.field_150364_r) || (b == Blocks.field_150363_s)) {
          replacement = Blocks.field_150322_A;
        } else if (b == Blocks.field_150347_e) {
          replacement = Blocks.field_150322_A;
        } else if (b == Blocks.field_150344_f) {
          replacement = Blocks.field_150344_f;
          event.setResult(Event.Result.DENY);
        } else if (b == Blocks.field_150476_ad) {
          replacement = Blocks.field_150487_bG;
        } else if (b == Blocks.field_150446_ar) {
          replacement = Blocks.field_150372_bz;
        } else if (b == Blocks.field_150351_n) {
          replacement = Blocks.field_150322_A;
        } else if (b == Blocks.field_150417_aV) {
          replacement = Blocks.field_150322_A;
        } else if (b == Blocks.field_150376_bx) {
          replacement = Blocks.field_150376_bx;
        } else if (b == Blocks.field_150390_bg) {
          replacement = Blocks.field_150372_bz;
        }
      } else if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.SNOWY)) {
        if ((b == Blocks.field_150364_r) || (b == Blocks.field_150363_s)) {
          replacement = Blocks.field_150403_cj;
        } else if (b == Blocks.field_150347_e) {
          replacement = Blocks.field_150433_aE;
        } else if (b == Blocks.field_150344_f) {
          replacement = Blocks.field_150433_aE;
        } else if (b == Blocks.field_150476_ad) {
          replacement = BlocksSNOW_STAIRS;
        } else if (b == Blocks.field_150446_ar) {
          replacement = BlocksSNOW_STAIRS;
        } else if (b == Blocks.field_150351_n) {
          replacement = Blocks.field_150403_cj;
        } else if (b == Blocks.field_150417_aV) {
          replacement = Blocks.field_150433_aE;
        } else if (b == Blocks.field_150333_U) {
          replacement = BlocksSNOW_SLAB_SINGLE;
        } else if (b == Blocks.field_150376_bx) {
          replacement = BlocksSNOW_SLAB_SINGLE;
        } else if (b == Blocks.field_150422_aJ) {
          replacement = BlocksPERPETUAL_ICE_FENCE;
        } else if (b == Blocks.field_150346_d) {
          replacement = Blocks.field_150433_aE;
        } else if (b == Blocks.field_150452_aw) {
          replacement = BlocksSNOW_PRESSURE_PLATE;
        } else if (b == Blocks.field_150390_bg) {
          replacement = BlocksSNOW_STAIRS;
        }
      }
      
      if ((replacement != null) && (replacement != original)) {
        event.setResult(Event.Result.DENY);
      }
    }
    
    @SubscribeEvent
    public void onGetVillageBlockMeta(BiomeEvent.GetVillageBlockMeta event) {
      Block b = original;
      if (biome == null) {
        return;
      }
      if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.SANDY)) {
        if ((b == Blocks.field_150364_r) || (b == Blocks.field_150363_s)) {
          replacement = 2;
          event.setResult(Event.Result.DENY);
        } else if (b == Blocks.field_150347_e) {
          replacement = 0;
          event.setResult(Event.Result.DENY);
        } else if (b == Blocks.field_150344_f) {
          replacement = 2;
          event.setResult(Event.Result.DENY);
        } else if (b == Blocks.field_150376_bx) {
          replacement = 2;
          event.setResult(Event.Result.DENY);
        } else if (b == Blocks.field_150333_U) {
          if ((type == 3) || (type == 0)) {
            replacement = 1;
            event.setResult(Event.Result.DENY);
          } else if ((type == 11) || (type == 8)) {
            replacement = 9;
            event.setResult(Event.Result.DENY);
          }
        } else if (b == Blocks.field_150417_aV) {
          replacement = 2;
          event.setResult(Event.Result.DENY);
        }
      } else if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.SNOWY)) {
        if ((b == Blocks.field_150364_r) || (b == Blocks.field_150363_s)) {
          replacement = 0;
          event.setResult(Event.Result.DENY);
        } else if (b == Blocks.field_150347_e) {
          replacement = 0;
          event.setResult(Event.Result.DENY);
        } else if (b == Blocks.field_150344_f) {
          replacement = 0;
          event.setResult(Event.Result.DENY);
        } else if (b == Blocks.field_150333_U) {
          if (type >= 8) {
            replacement = 8;
            event.setResult(Event.Result.DENY);
          } else {
            replacement = 0;
            event.setResult(Event.Result.DENY);
          }
        } else if (b == Blocks.field_150417_aV) {
          replacement = 0;
          event.setResult(Event.Result.DENY);
        }
      }
    }
  }
  
  public static class Wall extends StructureVillagePieces.Village
  {
    private StructureVillagePieces.Start start;
    private List pieces;
    private boolean hasMadeWallBlock;
    
    public Wall() {}
    
    public Wall(StructureVillagePieces.Start start, int componentType, Random rand, StructureBoundingBox bounds, int baseMode) {
      super(componentType);
      field_74885_f = baseMode;
      field_74887_e = bounds;
      this.start = start;
    }
    
    public static Wall func_74921_a(StructureVillagePieces.Start startPiece, List pieces, Random rand, int p1, int p2, int p3, int p4, int p5)
    {
      StructureBoundingBox bounds = StructureBoundingBox.func_78889_a(p1, p2, p3, 0, 0, 0, 2, 7, 2, p4);
      
      boolean create = (func_74895_a(bounds)) && (StructureComponent.func_74883_a(pieces, bounds) == null) && (!containsWalls(pieces));
      
      return create ? new Wall(startPiece, p5, rand, bounds, p4) : null;
    }
    




    private static boolean containsWalls(List pieces2)
    {
      return false;
    }
    
    public void func_74861_a(StructureComponent component, List pieces, Random rand)
    {
      super.func_74861_a(component, pieces, rand);
      this.pieces = pieces;
    }
    
    public boolean func_74875_a(World world, Random rand, StructureBoundingBox bounds)
    {
      if (field_143015_k < 0) {
        field_143015_k = func_74889_b(world, bounds);
        
        if (field_143015_k < 0) {
          return true;
        }
        
        field_74887_e.func_78886_a(0, field_143015_k - field_74887_e.field_78894_e + 7 - 1, 0);
      }
      
      if (!hasMadeWallBlock) {
        int x = 1;int z = 1;
        int xCoord = func_74865_a(x, z);
        int yCoord = func_74862_a(1);
        int zCoord = func_74873_b(x, z);
        
        if ((pieces != null) && (bounds.func_78890_b(xCoord, yCoord, zCoord))) {
          hasMadeWallBlock = true;
          world.func_147449_b(xCoord, yCoord, zCoord, BlocksWALLGEN);
          WorldHandlerVillageDistrict.Wall.BlockVillageWallGen.TileEntityVillageWallGen tile = (WorldHandlerVillageDistrict.Wall.BlockVillageWallGen.TileEntityVillageWallGen)BlockUtil.getTileEntity(world, xCoord, yCoord, zCoord, WorldHandlerVillageDistrict.Wall.BlockVillageWallGen.TileEntityVillageWallGen.class);
          
          if (tile != null) {
            tile.setStructure(pieces, start);
          }
        }
      }
      
      return true;
    }
    

    protected void func_143012_a(NBTTagCompound nbtRoot)
    {
      super.func_143012_a(nbtRoot);
      nbtRoot.func_74757_a("WallBlock", hasMadeWallBlock);
    }
    
    protected void func_143011_b(NBTTagCompound nbtRoot) {
      super.func_143011_b(nbtRoot);
      hasMadeWallBlock = nbtRoot.func_74767_n("WallBlock");
    }
    
    public static class StructureBounds extends StructureBoundingBox {
      public final boolean ew;
      
      public StructureBounds(StructureVillagePieces.Path path, int expansionX, int expansionZ) {
        this(path.func_74874_b(), expansionX, expansionZ);
      }
      
      public StructureBounds(StructureBoundingBox bb, int expansionX, int expansionZ) {
        this(field_78897_a, field_78895_b, field_78896_c, field_78893_d, field_78894_e, field_78892_f, expansionX, expansionZ);
      }
      
      public StructureBounds(int x, int y, int z, int x2, int y2, int z2, int expansionX, int expansionZ) {
        ew = (x2 - x > z2 - z);
        if (ew) {
          field_78897_a = (x - expansionZ);
          field_78893_d = (x2 + expansionZ);
          field_78896_c = (z - expansionX);
          field_78892_f = (z2 + expansionX);
        } else {
          field_78897_a = (x - expansionX);
          field_78893_d = (x2 + expansionX);
          field_78896_c = (z - expansionZ);
          field_78892_f = (z2 + expansionZ);
        }
        
        field_78895_b = y;
        field_78894_e = y2;
      }
    }
    
    public static void placeWalls(World world, List<StructureBounds> bb, int xCoord, int yCoord, int zCoord, BiomeGenBase biome, boolean desert)
    {
      int minX = Integer.MAX_VALUE;
      int minZ = Integer.MAX_VALUE;
      int maxX = Integer.MIN_VALUE;
      int maxZ = Integer.MIN_VALUE;
      
      Log.instance().debug(String.format("Generating town walls at [%d %d %d]", new Object[] { Integer.valueOf(xCoord), Integer.valueOf(yCoord), Integer.valueOf(zCoord) }));
      

      for (int i = 0; i < bb.size(); i++) {
        minX = Math.min(getfield_78897_a, minX);
        minZ = Math.min(getfield_78896_c, minZ);
        maxX = Math.max(getfield_78893_d, maxX);
        maxZ = Math.max(getfield_78892_f, maxZ);
      }
      
      if ((maxX != Integer.MIN_VALUE) && (minX != Integer.MAX_VALUE) && (maxZ != Integer.MIN_VALUE) && (minZ != Integer.MAX_VALUE))
      {

        byte[][] a = new byte[maxX - minX + 3][maxZ - minZ + 3];
        short[][] b = new short[maxX - minX + 3][maxZ - minZ + 3];
        for (int i = 0; i < bb.size(); i++) {
          int w = getfield_78893_d - getfield_78897_a + 1;
          int wMid = w / 2 + getfield_78897_a - 1;
          int h = getfield_78892_f - getfield_78896_c + 1;
          int hMid = h / 2 + getfield_78896_c - 1;
          for (int x = getfield_78897_a; x <= getfield_78893_d; x++) {
            for (int z = getfield_78896_c; z <= getfield_78892_f; z++) {
              int mx = x - minX + 1;
              int mz = z - minZ + 1;
              if ((!getew) && ((z == getfield_78896_c) || (z == getfield_78892_f)) && (x >= wMid - 1) && (x <= wMid + 1))
              {
                a[mx][mz] = 3;
              } else if ((getew) && ((x == getfield_78897_a) || (x == getfield_78893_d)) && (z >= hMid - 1) && (z <= hMid + 1))
              {
                a[mx][mz] = 3;
              } else {
                a[mx][mz] = 2;
              }
            }
          }
        }
        

        int range = 7;
        for (int x = 1; x < a.length - range; x++) {
          for (int z = 1; z < a[x].length - range; z++) {
            if (a[x][z] == 2) {
              for (int p = 1; p < range; p++)
              {
                if ((a[(x + p)][z] == 2) && (a[(x + p - 1)][z] == 0)) {
                  for (int p2 = p; p2 > 0; p2--) {
                    a[(x + p2)][z] = 2;
                  }
                }
                
                if ((a[x][(z + p)] == 2) && (a[x][(z + p - 1)] == 0)) {
                  for (int p2 = p; p2 > 0; p2--) {
                    a[x][(z + p2)] = 2;
                  }
                }
              }
            }
          }
        }
        

        for (int x = 1; x < a.length - 1; x++) {
          for (int z = 1; z < a[x].length - 1; z++) {
            boolean n = a[x][(z - 1)] == 0;
            boolean s = a[x][(z + 1)] == 0;
            boolean e = a[(x + 1)][z] == 0;
            boolean w = a[(x - 1)][z] == 0;
            
            boolean ne = a[(x + 1)][(z - 1)] == 0;
            boolean sw = a[(x - 1)][(z + 1)] == 0;
            boolean se = a[(x + 1)][(z + 1)] == 0;
            boolean nw = a[(x - 1)][(z - 1)] == 0;
            if ((!n) && (!s) && (!e) && (!w) && (!ne) && (!se) && (!nw) && (!sw))
            {

              a[x][z] = 1;
            }
          }
        }
        
        Block blockBase = Blocks.field_150417_aV;
        Block blockFence = Blocks.field_150422_aJ;
        Block stairsBlock = Blocks.field_150390_bg;
        int blockBaseMeta = 0;
        
        BiomeEvent.GetVillageBlockID event = new BiomeEvent.GetVillageBlockID(biome, blockBase, blockBaseMeta);
        
        MinecraftForge.TERRAIN_GEN_BUS.post(event);
        if (event.getResult() == Event.Result.DENY) {
          blockBase = replacement;
        } else if (desert) {
          blockBase = Blocks.field_150322_A;
        }
        
        event = new BiomeEvent.GetVillageBlockID(biome, blockFence, 0);
        MinecraftForge.TERRAIN_GEN_BUS.post(event);
        if (event.getResult() == Event.Result.DENY) {
          blockFence = replacement;
        }
        
        event = new BiomeEvent.GetVillageBlockID(biome, stairsBlock, 0);
        MinecraftForge.TERRAIN_GEN_BUS.post(event);
        if (event.getResult() == Event.Result.DENY) {
          stairsBlock = replacement;
        } else if (desert) {
          stairsBlock = Blocks.field_150372_bz;
        }
        
        BiomeEvent.GetVillageBlockMeta event2 = new BiomeEvent.GetVillageBlockMeta(biome, blockBase, blockBaseMeta);
        
        MinecraftForge.TERRAIN_GEN_BUS.post(event2);
        if (event2.getResult() == Event.Result.DENY) {
          blockBaseMeta = replacement;
        } else if (desert) {
          blockBaseMeta = 2;
        }
        


        int guardDist = 0;
        
        for (int x = 1; x < a.length - 1; x++) {
          for (int z = 1; z < a[x].length - 1; z++) {
            boolean n = a[x][(z - 1)] >= 2;
            boolean s = a[x][(z + 1)] >= 2;
            boolean e = a[(x + 1)][z] >= 2;
            boolean w = a[(x - 1)][z] >= 2;
            
            boolean ne = a[(x + 1)][(z - 1)] >= 2;
            boolean sw = a[(x - 1)][(z + 1)] >= 2;
            boolean se = a[(x + 1)][(z + 1)] >= 2;
            boolean nw = a[(x - 1)][(z - 1)] >= 2;
            
            if (a[x][z] >= 2) {
              int dx = minX + x;
              int dz = minZ + z;
              int solidCount = 0;
              
              for (int dy = yCoord; (dy > 1) && (solidCount < 9); dy--) {
                solidCount = 0;
                for (int ddx = dx - 1; ddx <= dx + 1; ddx++) {
                  for (int ddz = dz - 1; ddz <= dz + 1; ddz++) {
                    Block block = world.func_147439_a(ddx, dy, ddz);
                    boolean replaceable = (block.func_149688_o().func_76222_j()) || (block.func_149688_o() == Material.field_151584_j) || (block.func_149688_o() == Material.field_151575_d) || (block.func_149688_o() == Material.field_151585_k);
                    


                    if ((block.func_149721_r()) && (!replaceable)) {
                      solidCount++;
                    }
                  }
                }
              }
              
              int minHeight = 9;
              
              int startY = dy + 9;
              
              int near = Math.max(Math.max(Math.max(b[(x - 1)][z], b[(x + 1)][z]), b[x][(z + 1)]), b[x][(z - 1)]);
              
              if (near > 0) {
                if (near > startY) {
                  startY = near - 1;
                } else if (near < startY) {
                  startY = near + 1;
                }
              }
              
              int lowestY = dy;
              
              if (startY - lowestY > 0) {
                b[x][z] = ((short)Math.min(Math.max(startY, 0), 32767));
              }
              
              for (dy = startY; dy > lowestY; dy--)
              {

                if (dy == startY) {
                  if ((!ne) && (!n) && (!e)) {
                    setBlock(world, dx + 2, dy, dz - 2, blockBase, blockBaseMeta);
                    setBlock(world, dx + 2, dy, dz - 1, blockBase, blockBaseMeta);
                    setBlock(world, dx + 1, dy, dz - 2, blockBase, blockBaseMeta);
                    
                    setBlock(world, dx + 2, dy + 1, dz - 2, blockBase, blockBaseMeta, false);
                    setBlock(world, dx + 2, dy + 1, dz - 1, blockBase, blockBaseMeta, false);
                    setBlock(world, dx + 1, dy + 1, dz - 2, blockBase, blockBaseMeta, false);
                  }
                  
                  if ((!nw) && (!n) && (!w)) {
                    setBlock(world, dx - 2, dy, dz - 2, blockBase, blockBaseMeta);
                    setBlock(world, dx - 1, dy, dz - 2, blockBase, blockBaseMeta);
                    setBlock(world, dx - 2, dy, dz - 1, blockBase, blockBaseMeta);
                    
                    setBlock(world, dx - 2, dy + 1, dz - 2, blockBase, blockBaseMeta, false);
                    setBlock(world, dx - 1, dy + 1, dz - 2, blockBase, blockBaseMeta, false);
                    setBlock(world, dx - 2, dy + 1, dz - 1, blockBase, blockBaseMeta, false);
                  }
                  
                  if ((!se) && (!s) && (!e)) {
                    setBlock(world, dx + 2, dy, dz + 2, blockBase, blockBaseMeta);
                    setBlock(world, dx + 1, dy, dz + 2, blockBase, blockBaseMeta);
                    setBlock(world, dx + 2, dy, dz + 1, blockBase, blockBaseMeta);
                    
                    setBlock(world, dx + 2, dy + 1, dz + 2, blockBase, blockBaseMeta, false);
                    setBlock(world, dx + 1, dy + 1, dz + 2, blockBase, blockBaseMeta, false);
                    setBlock(world, dx + 2, dy + 1, dz + 1, blockBase, blockBaseMeta, false);
                  }
                  
                  if ((!sw) && (!s) && (!w)) {
                    setBlock(world, dx - 2, dy, dz + 2, blockBase, blockBaseMeta);
                    setBlock(world, dx - 1, dy, dz + 2, blockBase, blockBaseMeta);
                    setBlock(world, dx - 2, dy, dz + 1, blockBase, blockBaseMeta);
                    
                    setBlock(world, dx - 2, dy + 1, dz + 2, blockBase, blockBaseMeta, false);
                    setBlock(world, dx - 1, dy + 1, dz + 2, blockBase, blockBaseMeta, false);
                    setBlock(world, dx - 2, dy + 1, dz + 1, blockBase, blockBaseMeta, false);
                  }
                  
                  if ((!n) && (!ne) && (!nw)) {
                    setBlock(world, dx, dy, dz - 2, blockBase, blockBaseMeta);
                    setBlock(world, dx, dy + 1, dz - 2, stairsBlock, 0, false);
                  }
                  
                  if ((!e) && (!se) && (!ne)) {
                    setBlock(world, dx + 2, dy, dz, blockBase, blockBaseMeta);
                    setBlock(world, dx + 2, dy + 1, dz, stairsBlock, 2, false);
                  }
                  
                  if ((!s) && (!se) && (!sw)) {
                    setBlock(world, dx, dy, dz + 2, blockBase, blockBaseMeta);
                    setBlock(world, dx, dy + 1, dz + 2, stairsBlock, 0, false);
                  }
                  
                  if ((!w) && (!nw) && (!sw)) {
                    setBlock(world, dx - 2, dy, dz, blockBase, blockBaseMeta);
                    setBlock(world, dx - 2, dy + 1, dz, stairsBlock, 2, false);
                  }
                  
                  guardDist++; if (guardDist > 200) {
                    spawnGuard(world, dx, dy, dz);
                    guardDist = 0;
                  }
                }
                else {
                  int distCheck = 4;
                  boolean gate = (a[x][z] == 3) && (((x > distCheck) && (x < a.length - distCheck) && (a[(x - distCheck)][z] == 2) && (a[(x + distCheck)][z] == 2)) || ((z > distCheck) && (z < a[x].length - distCheck) && (a[x][(z - distCheck)] == 2) && (a[x][(z + distCheck)] == 2)));
                  




                  if ((gate) && 
                    (dy == startY - 3))
                  {
                    world.func_147449_b(dx, dy, dz, blockFence);
                    if ((a[(x + 1)][z] != 3) || (a[(x - 1)][z] != 3))
                    {
                      if (a[(x + 1)][z] == 3) {
                        world.func_147465_d(dx, dy, dz - 1, stairsBlock, 5, 2);
                        world.func_147465_d(dx, dy, dz + 1, stairsBlock, 5, 2);
                      } else if (a[(x - 1)][z] == 3) {
                        world.func_147465_d(dx, dy, dz - 1, stairsBlock, 4, 2);
                        world.func_147465_d(dx, dy, dz + 1, stairsBlock, 4, 2);
                      } else if ((a[x][(z + 1)] != 3) || (a[x][(z - 1)] != 3))
                      {
                        if (a[x][(z - 1)] == 3) {
                          world.func_147465_d(dx - 1, dy, dz, stairsBlock, 6, 2);
                          world.func_147465_d(dx + 1, dy, dz, stairsBlock, 6, 2);
                        } else if (a[x][(z + 1)] == 3) {
                          world.func_147465_d(dx - 1, dy, dz, stairsBlock, 7, 2);
                          world.func_147465_d(dx + 1, dy, dz, stairsBlock, 7, 2);
                        }
                      }
                    }
                  }
                  if ((!gate) || (dy > startY - 3)) {
                    setBlock(world, dx, dy, dz, blockBase, blockBaseMeta);
                    
                    boolean ng = a[x][(z - 1)] == 3;
                    boolean sg = a[x][(z + 1)] == 3;
                    boolean eg = a[(x + 1)][z] == 3;
                    boolean wg = a[(x - 1)][z] == 3;
                    
                    if (!ng) {
                      setBlock(world, dx, dy, dz - 1, blockBase, blockBaseMeta);
                    }
                    if ((!ng) && (!eg)) {
                      setBlock(world, dx + 1, dy, dz - 1, blockBase, blockBaseMeta);
                    }
                    if ((!ng) && (!wg)) {
                      setBlock(world, dx - 1, dy, dz - 1, blockBase, blockBaseMeta);
                    }
                    if (!eg) {
                      setBlock(world, dx + 1, dy, dz, blockBase, blockBaseMeta);
                    }
                    if (!sg) {
                      setBlock(world, dx, dy, dz + 1, blockBase, blockBaseMeta);
                    }
                    if ((!sg) && (!eg)) {
                      setBlock(world, dx + 1, dy, dz + 1, blockBase, blockBaseMeta);
                    }
                    if ((!sg) && (!wg)) {
                      setBlock(world, dx - 1, dy, dz + 1, blockBase, blockBaseMeta);
                    }
                    if (!wg)
                      setBlock(world, dx - 1, dy, dz, blockBase, blockBaseMeta);
                  }
                }
              }
            }
          }
        }
      }
    }
    
    private static void spawnGuard(World world, int x, int y, int z) {
      EntityVillageGuard guard = new EntityVillageGuard(world);
      guard.func_70012_b(x + 0.5D, y, z + 0.5D, 0.0F, 0.0F);
      guard.func_110163_bv();
      guard.func_110161_a(null);
      world.func_72838_d(guard);
    }
    
    private static void setBlock(World world, int x, int y, int z, Block block, int meta) {
      setBlock(world, x, y, z, block, meta, true);
    }
    
    private static void setBlock(World world, int x, int y, int z, Block block, int meta, boolean notStacked) {
      Block replaceBlock = world.func_147439_a(x, y, z);
      Material material = replaceBlock.func_149688_o();
      if ((material.func_76222_j()) || (material == Material.field_151584_j) || (material == Material.field_151575_d) || (material == Material.field_151585_k))
      {

        world.func_147465_d(x, y, z, block, meta, 2);
      }
    }
    
    public static class BlockVillageWallGen extends BlockBaseContainer {
      public BlockVillageWallGen() {
        super(TileEntityVillageWallGen.class);
        registerWithCreateTab = false;
        func_149722_s();
        func_149752_b(10000.0F);
      }
      
      public static class TileEntityVillageWallGen extends TileEntityBase {
        private List<WorldHandlerVillageDistrict.Wall.StructureBounds> bb;
        private BiomeGenBase biome;
        private boolean desert;
        
        public TileEntityVillageWallGen() {}
        
        public void func_145845_h() { super.func_145845_h();
          if ((!field_145850_b.field_72995_K) && (bb != null) && (ticks > 40L)) {
            WorldHandlerVillageDistrict.Wall.placeWalls(field_145850_b, bb, field_145851_c, field_145848_d, field_145849_e, biome, desert);
            
            bb = null;
            field_145850_b.func_147468_f(field_145851_c, field_145848_d, field_145849_e);
          }
          else if ((!field_145850_b.field_72995_K) && (ticks > 1000L)) {
            bb = null;
            field_145850_b.func_147468_f(field_145851_c, field_145848_d, field_145849_e);
          }
        }
        
        public void setStructure(List pieces, StructureVillagePieces.Start start)
        {
          biome = biome;
          desert = field_74927_b;
          bb = new ArrayList();
          for (Object obj : pieces) {
            if ((obj instanceof StructureVillagePieces.Path)) {
              bb.add(new WorldHandlerVillageDistrict.Wall.StructureBounds((StructureVillagePieces.Path)obj, 20, 7));
            }
          }
        }
      }
    }
  }
}
