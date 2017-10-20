package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.common.INullSource;
import com.emoniph.witchery.common.PowerSources;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.util.ParticleEffect;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;

public class BlockVoidBramble extends BlockBaseContainer
{
  public BlockVoidBramble()
  {
    super(Material.field_151585_k, TileEntityVoidBramble.class);
    
    func_149722_s();
    func_149752_b(1000.0F);
    func_149715_a(0.125F);
    func_149672_a(Block.field_149779_h);
    
    float f = 0.45F;
    func_149676_a(0.050000012F, 0.0F, 0.050000012F, 0.95F, 1.0F, 0.95F);
  }
  
  public int func_149645_b()
  {
    return 6;
  }
  
  public boolean func_149662_c()
  {
    return false;
  }
  
  public boolean func_149686_d()
  {
    return false;
  }
  
  public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4)
  {
    return null;
  }
  
  public void func_149670_a(World world, int posX, int posY, int posZ, Entity entity)
  {
    if ((!field_72995_K) && 
      ((entity instanceof EntityLivingBase)) && 
      ((entity instanceof EntityLivingBase))) {
      teleportRandomly(world, posX, posY, posZ, entity, 500);
    }
  }
  

  public static void teleportRandomly(World world, int posX, int posY, int posZ, Entity entity, int distance)
  {
    int doubleDistance = distance * 2;
    

    posX += field_73012_v.nextInt(doubleDistance) - distance;
    posZ += field_73012_v.nextInt(doubleDistance) - distance;
    int maxY = Math.min(posY + 64, 250);
    while ((!world.func_147439_a(posX, posY, posZ).func_149688_o().func_76220_a()) && (posY >= 0)) {
      posY--;
    }
    



    while (((!world.func_147439_a(posX, posY, posZ).func_149688_o().func_76220_a()) || (world.func_147439_a(posX, posY, posZ) == net.minecraft.init.Blocks.field_150357_h) || (!world.func_147437_c(posX, posY + 1, posZ)) || (!world.func_147437_c(posX, posY + 2, posZ)) || (!world.func_147437_c(posX, posY + 3, posZ))) && (posY < maxY)) {
      posY++;
    }
    if ((posY > 0) && (posY < maxY)) {
      ItemGeneral.teleportToLocation(world, 0.5D + posX, 1.0D + posY, 0.5D + posZ, field_73011_w.field_76574_g, entity, true);
    }
  }
  


  @SideOnly(Side.CLIENT)
  public void func_149734_b(World world, int x, int y, int z, Random rand)
  {
    if (rand.nextInt(2) == 0) {
      double d0 = x + rand.nextFloat();
      double d1 = y + 0.15F + rand.nextFloat() * 0.3F + 0.5D;
      double d2 = z + rand.nextFloat();
      world.func_72869_a(ParticleEffect.PORTAL.toString(), d0, d1, d2, 0.0D, -1.2D, 0.0D);
    }
  }
  
  public void func_149699_a(World world, int x, int y, int z, EntityPlayer player)
  {
    if (!field_72995_K) {
      TileEntityVoidBramble tile = (TileEntityVoidBramble)world.func_147438_o(x, y, z);
      if ((tile != null) && (
        (field_71075_bZ.field_75098_d) || (player.func_70005_c_().equals(tile.getOwner())))) {
        int dy = y;
        while (world.func_147439_a(x, dy, z) == this) {
          world.func_147468_f(x, dy, z);
          world.func_72838_d(new EntityItem(world, 0.5D + x, 0.5D + dy, 0.5D + z, new ItemStack(this)));
          dy++;
        }
      }
    }
  }
  

  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack)
  {
    if ((!field_72995_K) && ((entity instanceof EntityPlayer))) {
      EntityPlayer player = (EntityPlayer)entity;
      TileEntityVoidBramble tile = (TileEntityVoidBramble)world.func_147438_o(x, y, z);
      if (tile != null) {
        tile.setOwner(player.func_70005_c_());
      }
    }
  }
  


  public TileEntity func_149915_a(World world, int metadata) { return new TileEntityVoidBramble(); }
  
  public static class TileEntityVoidBramble extends TileEntityBase implements INullSource { private String owner;
    private static final String OWNER_KEY = "WITCPlacer";
    
    public TileEntityVoidBramble() {}
    
    public boolean isPowerInvalid() { return func_145837_r(); }
    

    protected void initiate()
    {
      super.initiate();
      if (!field_145850_b.field_72995_K) {
        if (field_145850_b.func_147439_a(field_145851_c, field_145848_d, field_145849_e) == BlocksVOID_BRAMBLE) {
          Log.instance().debug("Initiating void bramble tile at: " + field_145851_c + ", " + field_145848_d + ", " + field_145849_e);
          PowerSources.instance().registerNullSource(this);
        } else {
          Log.instance().warning("Void bramble tile entity exists without a corresponding block at: " + field_145851_c + ", " + field_145848_d + ", " + field_145849_e);
        }
      }
    }
    

    public void func_145843_s()
    {
      super.func_145843_s();
      if (!field_145850_b.field_72995_K) {
        Log.instance().debug("Invalidating void bramble tile at: " + field_145851_c + ", " + field_145848_d + ", " + field_145849_e);
        PowerSources.instance().removeNullSource(this);
      }
    }
    

    public void setOwner(String username)
    {
      owner = username;
    }
    
    public String getOwner() {
      return owner != null ? owner : "";
    }
    


    public void func_145841_b(NBTTagCompound nbtTag)
    {
      super.func_145841_b(nbtTag);
      nbtTag.func_74778_a("WITCPlacer", getOwner());
    }
    
    public void func_145839_a(NBTTagCompound nbtTag)
    {
      super.func_145839_a(nbtTag);
      if (nbtTag.func_74764_b("WITCPlacer")) {
        owner = nbtTag.func_74779_i("WITCPlacer");
      } else {
        owner = "";
      }
    }
    
    public Packet func_145844_m()
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
    
    public World getWorld()
    {
      return field_145850_b;
    }
    
    public int getPosX()
    {
      return field_145851_c;
    }
    
    public int getPosY()
    {
      return field_145848_d;
    }
    
    public int getPosZ()
    {
      return field_145849_e;
    }
    
    public float getRange()
    {
      return 32.0F;
    }
  }
}
