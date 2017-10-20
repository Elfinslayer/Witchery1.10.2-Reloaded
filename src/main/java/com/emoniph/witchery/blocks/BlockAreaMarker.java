package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.ritual.Rite;
import com.emoniph.witchery.ritual.rites.RiteTeleportEntity;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.Log;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class BlockAreaMarker extends BlockBaseContainer
{
  public static class TileEntityAreaCurseProtect extends BlockAreaMarker.TileEntityAreaMarker
  {
    public TileEntityAreaCurseProtect() {}
    
    public boolean activateBlock(World world, int x, int y, int z, EntityPlayer player, int side)
    {
      return false;
    }
    
    protected boolean isProtected(EntityLivingBase entity, boolean killer, Rite rite)
    {
      return (!killer) && (instanceallowDecurseDirected) && ((rite == null) || ((rite instanceof com.emoniph.witchery.ritual.rites.RiteCurseCreature)));
    }
    
    protected boolean isNear(EntityLivingBase entity)
    {
      int RADIUS = instancedecurseDirectedRadius;
      int RADIUS_SQ = RADIUS * RADIUS;
      boolean inRange = Coord.distanceSq(field_70165_t, 1.0D, field_70161_v, field_145851_c, 1.0D, field_145849_e) <= RADIUS_SQ;
      return (inRange) && (field_145850_b.field_73011_w.field_76574_g == field_71093_bK);
    }
    
    protected Block getExpectedBlockType()
    {
      return BlocksDECURSE_DIRECTED;
    }
  }
  
  public static class TileEntityAreaTeleportPullProtect extends BlockAreaMarker.TileEntityAreaMarker {
    public TileEntityAreaTeleportPullProtect() {}
    
    public boolean activateBlock(World world, int x, int y, int z, EntityPlayer player, int side) { return false; }
    

    protected boolean isProtected(EntityLivingBase entity, boolean killer, Rite rite)
    {
      return (!killer) && (instanceallowDecurseTeleport) && ((rite == null) || ((rite instanceof RiteTeleportEntity)));
    }
    
    protected boolean isNear(EntityLivingBase entity)
    {
      int RADIUS = instancedecurseTeleportPullRadius;
      int RADIUS_SQ = RADIUS * RADIUS;
      boolean inRange = Coord.distanceSq(field_70165_t, 1.0D, field_70161_v, field_145851_c, 1.0D, field_145849_e) <= RADIUS_SQ;
      return (inRange) && (field_145850_b.field_73011_w.field_76574_g == field_71093_bK);
    }
    
    protected Block getExpectedBlockType()
    {
      return BlocksDECURSE_TELEPORT;
    }
  }
  
  public BlockAreaMarker(Class<? extends TileEntityAreaMarker> clazzTile) {
    super(net.minecraft.block.material.Material.field_151576_e, clazzTile);
    
    func_149722_s();
    func_149752_b(9999.0F);
    func_149711_c(2.5F);
    func_149672_a(field_149769_e);
    func_149676_a(0.15F, 0.0F, 0.15F, 0.85F, 0.5F, 0.85F);
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack)
  {
    int l = MathHelper.func_76128_c(field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3;
    
    if (l == 0) {
      world.func_72921_c(x, y, z, 2, 2);
    }
    
    if (l == 1) {
      world.func_72921_c(x, y, z, 5, 2);
    }
    
    if (l == 2) {
      world.func_72921_c(x, y, z, 3, 2);
    }
    
    if (l == 3) {
      world.func_72921_c(x, y, z, 4, 2);
    }
    

    if ((!field_72995_K) && ((entity instanceof EntityPlayer))) {
      EntityPlayer player = (EntityPlayer)entity;
      TileEntity tile = world.func_147438_o(x, y, z);
      if ((tile != null) && ((tile instanceof TileEntityAreaMarker))) {
        TileEntityAreaMarker marker = (TileEntityAreaMarker)tile;
        marker.setOwner(player.func_70005_c_());
      }
    }
  }
  
  public void func_149699_a(World world, int x, int y, int z, EntityPlayer player)
  {
    if (((!field_72995_K ? 1 : 0) & (player != null ? 1 : 0)) != 0) {
      TileEntity tile = world.func_147438_o(x, y, z);
      if ((tile != null) && ((tile instanceof TileEntityAreaMarker))) {
        TileEntityAreaMarker marker = (TileEntityAreaMarker)tile;
        if ((field_71075_bZ.field_75098_d) || ((player.func_70005_c_().equals(marker.getOwner())) && (player.func_70093_af()))) {
          int dy = y;
          while (world.func_147439_a(x, dy, z) == this) {
            world.func_147468_f(x, dy, z);
            world.func_72838_d(new EntityItem(world, 0.5D + x, 0.5D + dy, 0.5D + z, new ItemStack(this)));
            dy++;
          }
        }
      }
    }
  }
  

  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
  {
    TileEntity tile = world.func_147438_o(x, y, z);
    if ((tile != null) && ((tile instanceof TileEntityAreaMarker))) {
      TileEntityAreaMarker marker = (TileEntityAreaMarker)tile;
      return marker.activateBlock(world, x, y, z, player, side);
    }
    return false;
  }
  

  public boolean func_149662_c()
  {
    return false;
  }
  
  public boolean func_149686_d()
  {
    return false;
  }
  
  public int func_149645_b() {
    return 0;
  }
  
  public int func_149745_a(Random rand)
  {
    return 1;
  }
  
  @SideOnly(Side.CLIENT)
  public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
  {
    return false;
  }
  


  @SideOnly(Side.CLIENT)
  public void func_149734_b(World world, int x, int y, int z, Random rand) {}
  


  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister par1IconRegister) { field_149761_L = par1IconRegister.func_94245_a("stone"); }
  
  public static class AreaMarkerRegistry {
    public AreaMarkerRegistry() {}
    
    public static AreaMarkerRegistry instance() { if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
        return INSTANCE_SERVER;
      }
      
      return INSTANCE_CLIENT;
    }
    
    public static void serverStart() {
      INSTANCE_CLIENT = new AreaMarkerRegistry();
      INSTANCE_SERVER = new AreaMarkerRegistry();
    }
    

    private static AreaMarkerRegistry INSTANCE_CLIENT;
    private static AreaMarkerRegistry INSTANCE_SERVER;
    private final ArrayList<BlockAreaMarker.TileEntityAreaMarker> tiles = new ArrayList();
    
    private void add(BlockAreaMarker.TileEntityAreaMarker tile) {
      if (!tiles.contains(tile)) {
        try {
          Iterator<BlockAreaMarker.TileEntityAreaMarker> it = tiles.iterator();
          while (it.hasNext()) {
            BlockAreaMarker.TileEntityAreaMarker source = (BlockAreaMarker.TileEntityAreaMarker)it.next();
            
            if ((source == null) || (source.func_145837_r()) || ((field_145851_c == field_145851_c) && (field_145848_d == field_145848_d) && (field_145849_e == field_145849_e))) {
              it.remove();
            }
          }
        } catch (Throwable e) {
          Log.instance().warning(e, "Exception occured validating existing power source entries");
        }
        tiles.add(tile);
      }
    }
    
    private void remove(BlockAreaMarker.TileEntityAreaMarker tile) {
      if (tiles.contains(tile)) {
        tiles.remove(tile);
      }
      try
      {
        Iterator<BlockAreaMarker.TileEntityAreaMarker> it = tiles.iterator();
        while (it.hasNext()) {
          BlockAreaMarker.TileEntityAreaMarker source = (BlockAreaMarker.TileEntityAreaMarker)it.next();
          
          if ((source == null) || (source.func_145837_r())) {
            it.remove();
          } else if (source.func_145831_w().func_147438_o(field_145851_c, field_145848_d, field_145849_e) != source) {
            it.remove();
          }
        }
      } catch (Throwable e) {
        Log.instance().warning(e, "Exception occured removing existing power source entries");
      }
    }
    
    public boolean isProtectionActive(EntityLivingBase entity, Rite rite) {
      for (BlockAreaMarker.TileEntityAreaMarker tile : tiles) {
        if (tile.checkIsProtected(entity, rite)) {
          return true;
        }
      }
      return false;
    }
  }
  
  public static abstract class TileEntityAreaMarker extends TileEntityBase {
    private static final String OWNER_KEY = "WITCPlacer";
    private String owner;
    
    public TileEntityAreaMarker() {}
    
    protected void initiate() {
      super.initiate();
      if (!field_145850_b.field_72995_K) {
        if (field_145850_b.func_147439_a(field_145851_c, field_145848_d, field_145849_e) == getExpectedBlockType()) {
          BlockAreaMarker.AreaMarkerRegistry.access$000(BlockAreaMarker.AreaMarkerRegistry.instance(), this);
        } else {
          Log.instance().warning("Area Marker tile entity exists without a corresponding block at: " + field_145851_c + ", " + field_145848_d + ", " + field_145849_e);
        }
      }
    }
    
    public void func_145843_s()
    {
      super.func_145843_s();
      if (!field_145850_b.field_72995_K) {
        BlockAreaMarker.AreaMarkerRegistry.access$100(BlockAreaMarker.AreaMarkerRegistry.instance(), this);
      }
    }
    
    public void setOwner(String username) {
      owner = username;
    }
    
    public String getOwner() {
      return owner != null ? owner : "";
    }
    
    private ArrayList<String> killers = new ArrayList();
    
    public void setKiller(EntityPlayer player) {
      String username = player.func_70005_c_();
      if (!killers.contains(username)) {
        killers.add(username);
      }
    }
    
    public boolean checkIsProtected(EntityLivingBase entity, Rite rite) {
      if (isNear(entity)) {
        boolean killer = ((entity instanceof EntityPlayer)) && (killers.contains(entity.func_70005_c_()));
        return isProtected(entity, killer, rite);
      }
      return false;
    }
    

    public void func_145841_b(NBTTagCompound nbtTag)
    {
      super.func_145841_b(nbtTag);
      nbtTag.func_74778_a("WITCPlacer", getOwner());
      
      NBTTagList nbtKillers = new NBTTagList();
      for (String killer : killers) {
        nbtKillers.func_74742_a(new NBTTagString(killer));
      }
      nbtTag.func_74782_a("Killers", nbtKillers);
    }
    
    public void func_145839_a(NBTTagCompound nbtTag)
    {
      super.func_145839_a(nbtTag);
      if (nbtTag.func_74764_b("WITCPlacer")) {
        owner = nbtTag.func_74779_i("WITCPlacer");
      } else {
        owner = "";
      }
      NBTTagList nbtKillers = nbtTag.func_150295_c("Killers", 8);
      int i = 0; for (int count = nbtKillers.func_74745_c(); i < count; i++)
        killers.add(nbtKillers.func_150307_f(i)); }
    
    public abstract boolean activateBlock(World paramWorld, int paramInt1, int paramInt2, int paramInt3, EntityPlayer paramEntityPlayer, int paramInt4);
    
    protected abstract boolean isNear(EntityLivingBase paramEntityLivingBase);
    
    protected abstract boolean isProtected(EntityLivingBase paramEntityLivingBase, boolean paramBoolean, Rite paramRite);
    
    protected abstract Block getExpectedBlockType(); }
  
  public static class AreaMarkerEventHooks { public AreaMarkerEventHooks() {}
    
    @cpw.mods.fml.common.eventhandler.SubscribeEvent(priority=EventPriority.NORMAL)
    public void onLivingDeath(LivingDeathEvent event) { EntityPlayer attacker;
      if ((!event.isCanceled()) && (!entityLiving.field_70170_p.field_72995_K) && ((entityLiving instanceof EntityPlayer)) && 
        (source.func_76364_f() != null) && ((source.func_76364_f() instanceof EntityPlayer)) && (source.func_76364_f() != entityLiving))
      {

        attacker = (EntityPlayer)source.func_76364_f();
        for (BlockAreaMarker.TileEntityAreaMarker tile : BlockAreaMarker.AreaMarkerRegistry.access$200(BlockAreaMarker.AreaMarkerRegistry.instance())) {
          if (tile.isNear(attacker)) {
            tile.setKiller(attacker);
          }
        }
      }
    }
  }
}
