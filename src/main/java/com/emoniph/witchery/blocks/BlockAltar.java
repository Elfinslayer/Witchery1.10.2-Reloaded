package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.client.particle.NaturePowerFX;
import com.emoniph.witchery.common.PowerSources;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.Log;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class BlockAltar extends BlockBaseContainer
{
  @SideOnly(Side.CLIENT)
  private IIcon blockIconTop;
  @SideOnly(Side.CLIENT)
  private IIcon blockIconJoined;
  @SideOnly(Side.CLIENT)
  private IIcon blockIconTopJoined;
  private static final int ELEMENTS_IN_COMPLETE_ALTAR = 6;
  
  public BlockAltar()
  {
    super(net.minecraft.block.material.Material.field_151576_e, TileEntityAltar.class);
    func_149711_c(2.0F);
  }
  
  public TileEntity func_149915_a(World world, int metadata)
  {
    return new TileEntityAltar();
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int metadata)
  {
    switch (1.$SwitchMap$com$emoniph$witchery$util$BlockSide[com.emoniph.witchery.util.BlockSide.fromInteger(side).ordinal()]) {
    case 1: 
      return metadata == 0 ? blockIconTop : blockIconTopJoined;
    case 2: 
      return blockIconTop;
    }
    return metadata == 0 ? field_149761_L : blockIconJoined;
  }
  

  @SideOnly(Side.CLIENT)
  public void func_149734_b(World world, int x, int y, int z, Random rand)
  {
    int i = func_71410_xfield_71474_y.field_74362_aa;
    
    if ((i == 2) || ((i == 1) && (rand.nextInt(3) == 0)))
    {
      return;
    }
    
    if (world.func_72805_g(x, y, z) == 1)
    {
      int RADIUS = 16;
      int VERT = 4;
      int plantX = x - 16 + rand.nextInt(32) + 1;
      int plantY = y - 4 + rand.nextInt(8) + 1;
      int plantZ = z - 16 + rand.nextInt(32) + 1;
      Block block = world.func_147439_a(plantX, plantY, plantZ);
      
      if ((block != null) && (
        ((block instanceof BlockFlower)) || ((block instanceof BlockLeaves)) || ((block instanceof BlockCrops)) || ((block instanceof net.minecraftforge.common.IPlantable)))) {
        int dir_x = x - plantX;
        int dir_y = y - plantY;
        int dir_z = z - plantZ;
        double distance = Math.sqrt(dir_x * dir_x + dir_y * dir_y + dir_z * dir_z);
        double speed = 0.25D;
        double factor = speed / distance;
        double vel_x = dir_x * factor;
        double vel_y = dir_y * factor;
        double vel_z = dir_z * factor;
        
        NaturePowerFX sparkle = new NaturePowerFX(world, 0.5D + plantX, 0.5D + plantY, 0.5D + plantZ);
        sparkle.setMaxAge((int)(distance / speed)).setGravity(0.0F).setScale(1.0F).func_70016_h(vel_x, vel_y, vel_z);
        sparkle.setCanMove(true);
        sparkle.func_70538_b(0.2F, 0.8F, 0.0F);
        func_71410_xfield_71452_i.func_78873_a(sparkle);
      }
    }
  }
  








  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister iconRegister)
  {
    field_149761_L = iconRegister.func_94245_a(func_149641_N());
    blockIconTop = iconRegister.func_94245_a(func_149641_N() + "_top");
    blockIconJoined = iconRegister.func_94245_a(func_149641_N() + "_joined");
    blockIconTopJoined = iconRegister.func_94245_a(func_149641_N() + "_joined_top");
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
  {
    TileEntityAltar tileEntity = (TileEntityAltar)world.func_147438_o(x, y, z);
    if (tileEntity.isValidAndUpdate()) {
      player.openGui(Witchery.instance, 0, world, x, y, z);
      return true;
    }
    return false;
  }
  

  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
  {
    super.func_149689_a(world, x, y, z, par5EntityLivingBase, par6ItemStack);
    
    updateMultiblock(world, x, y, z, null);
  }
  
  public void func_149749_a(World world, int x, int y, int z, Block block, int par6)
  {
    updateMultiblock(world, x, y, z, new Coord(x, y, z));
    super.func_149749_a(world, x, y, z, block, par6);
  }
  
  public void func_149723_a(World world, int posX, int posY, int posZ, Explosion explosion)
  {
    TileEntityAltar tileEntity = (TileEntityAltar)world.func_147438_o(posX, posY, posZ);
    updateMultiblock(world, posX, posY, posZ, null);
  }
  
  public void func_149695_a(World world, int posX, int posY, int posZ, Block block)
  {
    TileEntity tileEntity = world.func_147438_o(posX, posY, posZ);
    if ((tileEntity != null) && ((tileEntity instanceof TileEntityAltar)) && (!field_72995_K)) {
      TileEntityAltar tileEntityAltar = (TileEntityAltar)tileEntity;
      tileEntityAltar.updateCoreArtefacts();
    }
    super.func_149695_a(world, posX, posY, posZ, block);
  }
  

  private void updateMultiblock(World world, int x, int y, int z, Coord exclude)
  {
    if (!field_72995_K) {
      ArrayList<Coord> visited = new ArrayList();
      ArrayList<Coord> toVisit = new ArrayList();
      toVisit.add(new Coord(x, y, z));
      boolean valid = true;
      while (toVisit.size() > 0) {
        Coord coord = (Coord)toVisit.get(0);
        toVisit.remove(0);
        int neighbours = 0;
        for (Coord newCoord : new Coord[] { coord.north(), coord.south(), coord.east(), coord.west() }) {
          if (newCoord.getBlock(world) == this) {
            if ((!visited.contains(newCoord)) && (!toVisit.contains(newCoord))) {
              toVisit.add(newCoord);
            }
            neighbours++;
          }
        }
        
        if (!coord.equals(exclude)) {
          if ((neighbours < 2) || (neighbours > 3)) {
            valid = false;
          }
          
          visited.add(coord);
        }
      }
      
      Coord newCore = (valid) && (visited.size() == 6) ? (Coord)visited.get(0) : null;
      

      for (Coord coord : visited) {
        TileEntity te = coord.getBlockTileEntity(world);
        if ((te != null) && ((te instanceof TileEntityAltar))) {
          TileEntityAltar tile = (TileEntityAltar)te;
          tile.setCore(newCore);
        }
      }
      
      if (exclude != null) {
        TileEntity te = exclude.getBlockTileEntity(world);
        if ((te != null) && ((te instanceof TileEntityAltar))) {
          TileEntityAltar tile = (TileEntityAltar)te;
          tile.setCore(null);
        }
      }
    }
  }
  
  public static class TileEntityAltar extends TileEntityBase implements com.emoniph.witchery.common.IPowerSource
  {
    private Coord core;
    private float power;
    private float maxPower;
    private int powerScale;
    private int rechargeScale;
    private int enhancementLevel;
    private int rangeScale = 1;
    
    public TileEntityAltar() {}
    
    public boolean isPowerInvalid() { return func_145837_r(); }
    

    protected void initiate()
    {
      super.initiate();
      if ((!field_145850_b.field_72995_K) && (isCore())) {
        if (field_145850_b.func_147439_a(field_145851_c, field_145848_d, field_145849_e) == BlocksALTAR) {
          Log.instance().debug("Initiating altar tile at: " + field_145851_c + ", " + field_145848_d + ", " + field_145849_e);
          PowerSources.instance().registerPowerSource(this);
        } else {
          Log.instance().warning("Altar tile entity exists without a corresponding block at: " + field_145851_c + ", " + field_145848_d + ", " + field_145849_e);
        }
      }
    }
    
    public void func_145843_s()
    {
      super.func_145843_s();
      if (!field_145850_b.field_72995_K) {
        if (isCore()) {
          Log.instance().debug("Invalidating void bramble tile at: " + field_145851_c + ", " + field_145848_d + ", " + field_145849_e);
        }
        PowerSources.instance().removePowerSource(this);
      }
    }
    
    public void func_145845_h()
    {
      super.func_145845_h();
      
      if (!field_145850_b.field_72995_K) {
        float maxPowerScaled = maxPower * powerScale;
        if (isCore()) {
          if (power < maxPowerScaled) {
            float basePowerPerUpdate = 10.0F;
            if (ticks % 20L == 0L) {
              power = ((int)Math.min(power + 10.0F * rechargeScale, maxPowerScaled));
              
              field_145850_b.func_147471_g(field_145851_c, field_145848_d, field_145849_e);
            }
          } else if ((power > maxPowerScaled) && 
            (ticks % 20L == 0L)) {
            power = maxPowerScaled;
            field_145850_b.func_147471_g(field_145851_c, field_145848_d, field_145849_e);
          }
        }
      }
    }
    

    long lastPowerUpdate = 0L;
    private static final int SCAN_DISTANCE = 14;
    
    public float getRange() {
      return 16 * rangeScale;
    }
    
    public int getEnhancementLevel()
    {
      return enhancementLevel;
    }
    
    public boolean isValidAndUpdate() {
      if ((isValid()) && (!field_145850_b.field_72995_K)) {
        TileEntity tile = core.getBlockTileEntity(field_145850_b);
        if ((tile != null) && ((tile instanceof TileEntityAltar))) {
          TileEntityAltar tileEntity = (TileEntityAltar)tile;
          tileEntity.updateArtefacts();
          tileEntity.updatePower(true);
          return true;
        }
        return false;
      }
      if (isValid()) {
        return true;
      }
      return false;
    }
    

    public void func_145841_b(NBTTagCompound nbtTag)
    {
      if (core != null) {
        core.setNBT(nbtTag, "Core");
      }
      if (isCore()) {
        nbtTag.func_74776_a("Power", power);
        nbtTag.func_74776_a("MaxPower", maxPower);
        nbtTag.func_74768_a("PowerScale", powerScale);
        nbtTag.func_74768_a("RechargeScale", rechargeScale);
        nbtTag.func_74768_a("RangeScale", rangeScale);
        nbtTag.func_74768_a("EnhancementLevel", enhancementLevel);
      }
      super.func_145841_b(nbtTag);
    }
    
    public void func_145839_a(NBTTagCompound nbtTag)
    {
      core = Coord.createFrom(nbtTag, "Core");
      power = nbtTag.func_74760_g("Power");
      maxPower = nbtTag.func_74760_g("MaxPower");
      powerScale = nbtTag.func_74762_e("PowerScale");
      rechargeScale = nbtTag.func_74762_e("RechargeScale");
      if (nbtTag.func_74764_b("RangeScale")) {
        rangeScale = nbtTag.func_74762_e("RangeScale");
      } else {
        rangeScale = 1;
      }
      if (nbtTag.func_74764_b("EnhancementLevel")) {
        enhancementLevel = nbtTag.func_74762_e("EnhancementLevel");
      } else {
        enhancementLevel = 0;
      }
      super.func_145839_a(nbtTag);
    }
    
    private void setCore(Coord coord) {
      core = coord;
      if (isCore()) {
        updatePower(false);
        PowerSources.instance().registerPowerSource(this);
      }
      if (coord == null) {
        PowerSources.instance().removePowerSource(this);
        power = 0.0F;
        maxPower = 0.0F;
        powerScale = 1;
        rechargeScale = 1;
        rangeScale = 1;
        enhancementLevel = 0;
      }
      field_145850_b.func_72921_c(field_145851_c, field_145848_d, field_145849_e, coord != null ? 1 : 0, 3);
      field_145850_b.func_147471_g(field_145851_c, field_145848_d, field_145849_e);
    }
    
    private boolean isCore() {
      return (core != null) && (core.isAtPosition(this));
    }
    
    public net.minecraft.network.Packet func_145844_m()
    {
      NBTTagCompound nbtTag = new NBTTagCompound();
      func_145841_b(nbtTag);
      return new S35PacketUpdateTileEntity(field_145851_c, field_145848_d, field_145849_e, 1, nbtTag);
    }
    
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
    {
      super.onDataPacket(net, packet);
      func_145839_a(packet.func_148857_g());
      field_145850_b.func_147479_m(field_145851_c, field_145848_d, field_145849_e);
    }
    
    public float getCorePower() {
      if (core != null) {
        TileEntity te = core.getBlockTileEntity(field_145850_b);
        if ((te != null) && ((te instanceof TileEntityAltar))) {
          TileEntityAltar tileEntity = (TileEntityAltar)te;
          return power;
        }
      }
      
      return 0.0F;
    }
    
    private void updateCoreArtefacts() {
      if (core != null) {
        TileEntity tile = core.getBlockTileEntity(field_145850_b);
        if ((tile != null) && ((tile instanceof TileEntityAltar))) {
          TileEntityAltar tileEntity = (TileEntityAltar)tile;
          tileEntity.updateArtefacts();
        }
      }
    }
    
    public boolean consumePower(float requiredPower)
    {
      if (core != null) {
        TileEntityAltar tileEntity = (TileEntityAltar)core.getBlockTileEntity(field_145850_b);
        if (tileEntity != null) {
          return tileEntity.consumeOurPower(requiredPower);
        }
      }
      return false;
    }
    
    private boolean consumeOurPower(float requiredPower) {
      if ((!field_145850_b.field_72995_K) && (power >= requiredPower)) {
        power -= requiredPower;
        return true;
      }
      return false;
    }
    

    public float getCurrentPower()
    {
      if (core != null) {
        TileEntityAltar tileEntity = (TileEntityAltar)core.getBlockTileEntity(field_145850_b);
        if (tileEntity != null) {
          return tileEntity.getOurCurrentPower();
        }
      }
      return -1.0F;
    }
    
    private float getOurCurrentPower() {
      if (!field_145850_b.field_72995_K) {
        return power;
      }
      return -2.0F;
    }
    
    public float getCoreMaxPower()
    {
      if (core != null) {
        TileEntity tile = core.getBlockTileEntity(field_145850_b);
        if ((tile != null) && ((tile instanceof TileEntityAltar))) {
          TileEntityAltar tileEntity = (TileEntityAltar)tile;
          return maxPower * powerScale;
        }
      }
      
      return 0.0F;
    }
    
    public int getCoreSpeed() {
      if (core != null) {
        TileEntity tile = core.getBlockTileEntity(field_145850_b);
        if ((tile != null) && ((tile instanceof TileEntityAltar))) {
          TileEntityAltar tileEntity = (TileEntityAltar)tile;
          return rechargeScale;
        }
      }
      
      return 0;
    }
    
    public boolean isValid() {
      return core != null;
    }
    
    public World getWorld()
    {
      return field_145850_b;
    }
    
    public Coord getLocation()
    {
      return new Coord(this);
    }
    
    public boolean isLocationEqual(Coord location)
    {
      return (location != null) && (location.isAtPosition(this));
    }
    
    static class PowerSource {
      private final Block block;
      private final int factor;
      private final int limit;
      private int count;
      
      public PowerSource(Block block, int factor, int limit) {
        this.block = block;
        this.factor = factor;
        this.limit = limit;
        count = 0;
      }
      
      public int getPower() {
        return Math.min(count, limit) * factor;
      }
      
      static void createInMap(Map<Block, PowerSource> map, Block block, int factor, int limit) {
        PowerSource source = new PowerSource(block, factor, limit);
        map.put(block, source);
      }
    }
    


    private ArrayList<Block> extraNatureIDs = null;
    
    private void updatePower(boolean throttle)
    {
      if ((!field_145850_b.field_72995_K) && ((!throttle) || (ticks - lastPowerUpdate <= 0L) || (ticks - lastPowerUpdate > 100L))) {
        lastPowerUpdate = ticks;
        
        Hashtable<Block, PowerSource> powerObjectTable = new Hashtable();
        try
        {
          for (ItemStack blockItem : OreDictionary.getOres("treeSapling")) {
            Block block = Block.func_149634_a(blockItem.func_77973_b());
            if (block != null) {
              PowerSource.createInMap(powerObjectTable, block, 4, 20);
            }
          }
        } catch (Exception e) {
          Log.instance().warning(e, "Exception occurred while creating power source list for sapling ores");
        }
        try
        {
          for (ItemStack blockItem : OreDictionary.getOres("logWood")) {
            Block block = Block.func_149634_a(blockItem.func_77973_b());
            if (block != null) {
              PowerSource.createInMap(powerObjectTable, block, 2, 50);
            }
          }
        } catch (Exception e) {
          Log.instance().warning(e, "Exception occurred while creating power source list for log ores");
        }
        try
        {
          for (ItemStack blockItem : OreDictionary.getOres("treeLeaves")) {
            Block block = Block.func_149634_a(blockItem.func_77973_b());
            if (block != null) {
              PowerSource.createInMap(powerObjectTable, block, 3, 100);
            }
          }
        } catch (Exception e) {
          Log.instance().warning(e, "Exception occurred while creating power source list for leaf ores");
        }
        
        PowerSource.createInMap(powerObjectTable, Blocks.field_150349_c, 2, 80);
        PowerSource.createInMap(powerObjectTable, Blocks.field_150346_d, 1, 80);
        PowerSource.createInMap(powerObjectTable, Blocks.field_150458_ak, 1, 100);
        PowerSource.createInMap(powerObjectTable, Blocks.field_150329_H, 3, 50);
        PowerSource.createInMap(powerObjectTable, Blocks.field_150327_N, 4, 30);
        PowerSource.createInMap(powerObjectTable, Blocks.field_150328_O, 4, 30);
        PowerSource.createInMap(powerObjectTable, Blocks.field_150464_aj, 4, 20);
        
        PowerSource.createInMap(powerObjectTable, Blocks.field_150355_j, 1, 50);
        PowerSource.createInMap(powerObjectTable, Blocks.field_150337_Q, 3, 20);
        PowerSource.createInMap(powerObjectTable, Blocks.field_150338_P, 3, 20);
        PowerSource.createInMap(powerObjectTable, Blocks.field_150434_aF, 3, 50);
        PowerSource.createInMap(powerObjectTable, Blocks.field_150436_aH, 3, 50);
        PowerSource.createInMap(powerObjectTable, Blocks.field_150423_aK, 4, 20);
        PowerSource.createInMap(powerObjectTable, Blocks.field_150393_bb, 3, 20);
        PowerSource.createInMap(powerObjectTable, Blocks.field_150420_aW, 3, 20);
        PowerSource.createInMap(powerObjectTable, Blocks.field_150419_aX, 3, 20);
        PowerSource.createInMap(powerObjectTable, Blocks.field_150440_ba, 4, 20);
        PowerSource.createInMap(powerObjectTable, Blocks.field_150394_bc, 3, 20);
        PowerSource.createInMap(powerObjectTable, Blocks.field_150395_bd, 2, 50);
        PowerSource.createInMap(powerObjectTable, Blocks.field_150391_bh, 1, 80);
        PowerSource.createInMap(powerObjectTable, Blocks.field_150380_bt, 250, 1);
        PowerSource.createInMap(powerObjectTable, BlocksDEMON_HEART, 40, 2);
        PowerSource.createInMap(powerObjectTable, Blocks.field_150375_by, 3, 20);
        PowerSource.createInMap(powerObjectTable, Blocks.field_150459_bM, 4, 20);
        PowerSource.createInMap(powerObjectTable, Blocks.field_150469_bN, 4, 20);
        
        PowerSource.createInMap(powerObjectTable, BlocksCROP_BELLADONNA, 4, 20);
        PowerSource.createInMap(powerObjectTable, BlocksCROP_MANDRAKE, 4, 20);
        PowerSource.createInMap(powerObjectTable, BlocksCROP_ARTICHOKE, 4, 20);
        PowerSource.createInMap(powerObjectTable, BlocksCROP_SNOWBELL, 4, 20);
        PowerSource.createInMap(powerObjectTable, BlocksEMBER_MOSS, 4, 20);
        PowerSource.createInMap(powerObjectTable, BlocksLEAVES, 4, 50);
        PowerSource.createInMap(powerObjectTable, BlocksLOG, 3, 100);
        PowerSource.createInMap(powerObjectTable, BlocksSPANISH_MOSS, 3, 20);
        PowerSource.createInMap(powerObjectTable, BlocksGLINT_WEED, 2, 20);
        PowerSource.createInMap(powerObjectTable, BlocksCRITTER_SNARE, 2, 10);
        PowerSource.createInMap(powerObjectTable, BlocksBLOOD_ROSE, 2, 10);
        PowerSource.createInMap(powerObjectTable, BlocksGRASSPER, 2, 10);
        PowerSource.createInMap(powerObjectTable, BlocksWISPY_COTTON, 3, 20);
        PowerSource.createInMap(powerObjectTable, BlocksINFINITY_EGG, 1000, 1);
        
        if (extraNatureIDs == null) {
          try {
            extraNatureIDs = new ArrayList();
            Iterator iterator = Block.field_149771_c.iterator();
            while (iterator.hasNext()) {
              Block block = (Block)iterator.next();
              if ((((block instanceof BlockFlower)) || ((block instanceof BlockCrops))) && 
                (!powerObjectTable.containsKey(block))) {
                extraNatureIDs.add(block);
                Log.instance().debug(block.func_149739_a());
              }
            }
          }
          catch (Exception e) {
            Log.instance().warning(e, "Exception occurred while creating power source list for other mod flowers and crops");
          }
        }
        
        for (Block block : extraNatureIDs) {
          PowerSource.createInMap(powerObjectTable, block, 2, 4);
        }
        
        for (int y = field_145848_d - 14; y <= field_145848_d + 14; y++) {
          for (int z = field_145849_e + 14; z >= field_145849_e - 14; z--) {
            for (int x = field_145851_c - 14; x <= field_145851_c + 14; x++) {
              Block block = field_145850_b.func_147439_a(x, y, z);
              PowerSource source = (PowerSource)powerObjectTable.get(block);
              if (source != null) {
                PowerSource.access$204(source);
              }
            }
          }
        }
        
        float newMax = 0.0F;
        
        for (PowerSource source : powerObjectTable.values()) {
          newMax += source.getPower();
        }
        if (newMax != maxPower) {
          maxPower = newMax;
          field_145850_b.func_147471_g(field_145851_c, field_145848_d, field_145849_e);
        }
      }
    }
    
    private void updateArtefacts() {
      ArrayList<Coord> visited = new ArrayList();
      ArrayList<Coord> toVisit = new ArrayList();
      toVisit.add(new Coord(field_145851_c, field_145848_d, field_145849_e));
      
      boolean headfound = false;
      boolean candlefound = false;
      boolean cupfound = false;
      boolean knifeFound = false;
      boolean wandFound = false;
      boolean pentacleFound = false;
      boolean infinityFound = false;
      
      int newPowerScale = 1;
      int newRechargeScale = 1;
      int newRangeScale = 1;
      int newEnhancementLevel = 0;
      
      while (toVisit.size() > 0) {
        Coord coord = (Coord)toVisit.get(0);
        toVisit.remove(0);
        for (Coord newCoord : new Coord[] { coord.north(), coord.south(), coord.east(), coord.west() }) {
          if ((newCoord.getBlock(field_145850_b) == BlocksALTAR) && 
            (!visited.contains(newCoord)) && (!toVisit.contains(newCoord))) {
            toVisit.add(newCoord);
          }
        }
        
        visited.add(coord);
        int offsetY = 1;
        Block block = coord.getBlock(field_145850_b, 0, 1, 0);
        if ((!headfound) && (block == Blocks.field_150465_bP)) {
          TileEntity tile = coord.getBlockTileEntity(field_145850_b, 0, 1, 0);
          if ((tile != null) && ((tile instanceof TileEntitySkull))) {
            TileEntitySkull skullTileEntity = (TileEntitySkull)tile;
            switch (skullTileEntity.func_145904_a()) {
            case 0: 
              newRechargeScale++;
              newPowerScale++;
              headfound = true;
              break;
            case 1: 
              newRechargeScale += 2;
              newPowerScale += 2;
              headfound = true;
              break;
            case 3: 
              newRechargeScale += 3;
              newPowerScale += 3;
              headfound = true;
              break;
            

            }
            
          }
        }
        else if ((!candlefound) && (block == BlocksCANDELABRA)) {
          candlefound = true;
          newRechargeScale += 2;
        } else if ((!candlefound) && (block == Blocks.field_150478_aa)) {
          candlefound = true;
          newRechargeScale++;
        } else if (block == BlocksPLACED_ITEMSTACK) {
          TileEntity tile = coord.getBlockTileEntity(field_145850_b, 0, 1, 0);
          if ((tile != null) && ((tile instanceof BlockPlacedItem.TileEntityPlacedItem))) {
            BlockPlacedItem.TileEntityPlacedItem placeItem = (BlockPlacedItem.TileEntityPlacedItem)tile;
            if (placeItem.getStack() != null) {
              if ((!knifeFound) && (placeItem.getStack().func_77973_b() == ItemsARTHANA)) {
                knifeFound = true;
                newRangeScale++;
              } else if ((!wandFound) && (placeItem.getStack().func_77973_b() == ItemsMYSTIC_BRANCH)) {
                wandFound = true;
                newEnhancementLevel++;
              } else if ((!pentacleFound) && (ItemsGENERIC.itemKobolditePentacle.isMatch(placeItem.getStack()))) {
                pentacleFound = true;
              }
            }
          }
        }
        else if ((!cupfound) && (block == BlocksCHALICE)) {
          cupfound = true;
          TileEntity tile = coord.getBlockTileEntity(field_145850_b, 0, 1, 0);
          if ((tile != null) && ((tile instanceof BlockChalice.TileEntityChalice))) {
            BlockChalice.TileEntityChalice tileEntityChalice = (BlockChalice.TileEntityChalice)tile;
            newPowerScale += ((tileEntityChalice != null) && (tileEntityChalice.isFilled()) ? 2 : 1);
          }
        } else if ((!infinityFound) && (block == BlocksINFINITY_EGG)) {
          infinityFound = true;
        }
      }
      
      if (pentacleFound) {
        newRechargeScale *= 2;
      }
      
      if (infinityFound) {
        newRechargeScale *= 10;
        newPowerScale *= 10;
      }
      
      if ((newRechargeScale != rechargeScale) || (newPowerScale != powerScale) || (newRangeScale != rangeScale) || (newEnhancementLevel != enhancementLevel)) {
        rechargeScale = newRechargeScale;
        powerScale = newPowerScale;
        rangeScale = newRangeScale;
        enhancementLevel = newEnhancementLevel;
        if (!field_145850_b.field_72995_K) {
          field_145850_b.func_147471_g(field_145851_c, field_145848_d, field_145849_e);
        }
      }
    }
  }
}
