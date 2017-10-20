package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import com.emoniph.witchery.util.ParticleEffect;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockChalice extends BlockBaseContainer
{
  public BlockChalice()
  {
    super(Material.field_151574_g, TileEntityChalice.class);
    registerWithCreateTab = false;
    
    func_149711_c(3.0F);
    func_149672_a(field_149777_j);
    
    func_149676_a(0.3F, 0.0F, 0.37F, 0.63F, 0.46F, 0.695F);
  }
  
  public boolean func_149662_c()
  {
    return false;
  }
  
  public boolean func_149686_d()
  {
    return false;
  }
  
  public int func_149745_a(Random rand)
  {
    return 1;
  }
  
  public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
  {
    return ItemsGENERIC;
  }
  
  public int func_149692_a(int metadata)
  {
    if (metadata == 1) {
      return ItemsGENERIC.itemChaliceFull.damageValue;
    }
    return ItemsGENERIC.itemChaliceEmpty.damageValue;
  }
  

  public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5)
  {
    func_111046_k(par1World, par2, par3, par4);
  }
  
  private boolean func_111046_k(World par1World, int par2, int par3, int par4) {
    if (!func_149718_j(par1World, par2, par3, par4)) {
      func_149697_b(par1World, par2, par3, par4, par1World.func_72805_g(par2, par3, par4), 0);
      par1World.func_147468_f(par2, par3, par4);
      return false;
    }
    return true;
  }
  

  public boolean func_149718_j(World world, int x, int y, int z)
  {
    Material material = world.func_147439_a(x, y - 1, z).func_149688_o();
    return (!world.func_147437_c(x, y - 1, z)) && (material != null) && (material.func_76218_k()) && (material.func_76220_a());
  }
  
  public net.minecraft.item.ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
  {
    TileEntity tileEntity = world.func_147438_o(x, y, z);
    if ((tileEntity != null) && ((tileEntity instanceof TileEntityChalice)) && (((TileEntityChalice)tileEntity).isFilled())) {
      return ItemsGENERIC.itemChaliceFull.createStack();
    }
    return ItemsGENERIC.itemChaliceEmpty.createStack();
  }
  

  @SideOnly(Side.CLIENT)
  public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
  {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149734_b(World world, int x, int y, int z, Random rand)
  {
    int metadata = world.func_72805_g(x, y, z);
    if (metadata == 1) {
      double d0 = x + 0.45F;
      double d1 = y + 0.4F;
      double d2 = z + 0.5F;
      world.func_72869_a(ParticleEffect.REDDUST.toString(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
    } }
  
  public static class TileEntityChalice extends TileEntity { private boolean filled;
    private boolean checkState;
    private static final String FILLED_KEY = "WITCFilled";
    
    public TileEntityChalice() {}
    
    public boolean isFilled() { return filled; }
    



    public void func_145845_h()
    {
      if (!checkState) {
        checkState = true;
        if ((filled) && (!field_145850_b.field_72995_K) && (field_145850_b.func_147439_a(field_145851_c, field_145848_d, field_145849_e) == BlocksCHALICE)) {
          field_145850_b.func_72921_c(field_145851_c, field_145848_d, field_145849_e, 1, 3);
        }
      }
      super.func_145845_h();
    }
    
    public void func_145841_b(NBTTagCompound nbtTag)
    {
      super.func_145841_b(nbtTag);
      nbtTag.func_74757_a("WITCFilled", filled);
    }
    
    public void func_145839_a(NBTTagCompound nbtTag)
    {
      super.func_145839_a(nbtTag);
      if (nbtTag.func_74764_b("WITCFilled")) {
        filled = nbtTag.func_74767_n("WITCFilled");
      }
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
    
    public void setFilled(boolean filled) {
      if (!field_145850_b.field_72995_K) {
        this.filled = filled;
        
        field_145850_b.func_147471_g(field_145851_c, field_145848_d, field_145849_e);
        
        if (field_145850_b.func_147439_a(field_145851_c, field_145848_d, field_145849_e) == BlocksCHALICE) {
          field_145850_b.func_72921_c(field_145851_c, field_145848_d, field_145849_e, filled ? 1 : 0, 3);
        }
      }
    }
  }
}
