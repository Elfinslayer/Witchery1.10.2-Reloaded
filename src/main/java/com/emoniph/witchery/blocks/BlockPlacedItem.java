package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPlacedItem extends BlockBaseContainer
{
  public static void placeItemInWorld(ItemStack stack, EntityPlayer player, World world, int x, int y, int z)
  {
    int meta = 0;
    
    if (player != null) {
      int l = MathHelper.func_76128_c(field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3;
      
      if (l == 0) {
        meta = 2;
      }
      
      if (l == 1) {
        meta = 5;
      }
      
      if (l == 2) {
        meta = 3;
      }
      
      if (l == 3) {
        meta = 4;
      }
    }
    
    world.func_147465_d(x, y, z, BlocksPLACED_ITEMSTACK, meta, 3);
    TileEntity tile = world.func_147438_o(x, y, z);
    if ((tile != null) && ((tile instanceof TileEntityPlacedItem))) {
      ((TileEntityPlacedItem)tile).setStack(stack);
    }
  }
  
  public BlockPlacedItem() {
    super(Material.field_151578_c, TileEntityPlacedItem.class);
    registerWithCreateTab = false;
    
    func_149711_c(0.0F);
    func_149672_a(field_149777_j);
    
    func_149676_a(0.2F, 0.0F, 0.2F, 0.8F, 0.05F, 0.8F);
  }
  

  public void func_149651_a(IIconRegister p_149651_1_) {}
  

  protected String func_149641_N()
  {
    return null;
  }
  
  public boolean func_149662_c()
  {
    return false;
  }
  
  public boolean func_149686_d()
  {
    return false;
  }
  
  public void func_149681_a(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer)
  {
    if (field_71075_bZ.field_75098_d) {
      par5 |= 0x8;
      par1World.func_72921_c(par2, par3, par4, par5, 4);
    }
    
    func_149697_b(par1World, par2, par3, par4, par5, 0);
    
    super.func_149681_a(par1World, par2, par3, par4, par5, par6EntityPlayer);
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
  {
    ArrayList<ItemStack> drops = new ArrayList();
    if ((metadata & 0x8) == 0) {
      TileEntity tile = world.func_147438_o(x, y, z);
      if ((tile != null) && ((tile instanceof TileEntityPlacedItem)) && (((TileEntityPlacedItem)tile).getStack() != null)) {
        drops.add(((TileEntityPlacedItem)tile).getStack());
      }
    }
    return drops;
  }
  
  public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5)
  {
    func_111046_k(par1World, par2, par3, par4);
  }
  
  private boolean func_111046_k(World par1World, int par2, int par3, int par4) {
    if (!func_149718_j(par1World, par2, par3, par4)) {
      if (!field_72995_K) {
        func_149697_b(par1World, par2, par3, par4, par1World.func_72805_g(par2, par3, par4), 0);
        par1World.func_147468_f(par2, par3, par4);
      }
      return false;
    }
    return true;
  }
  

  public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
  {
    TileEntity tile = world.func_147438_o(x, y, z);
    if ((tile != null) && ((tile instanceof TileEntityPlacedItem)) && (((TileEntityPlacedItem)tile).getStack() != null)) {
      return ((TileEntityPlacedItem)tile).getStack().func_77946_l();
    }
    return new ItemStack(ItemsARTHANA);
  }
  

  public boolean func_149718_j(World world, int x, int y, int z)
  {
    Material material = world.func_147439_a(x, y - 1, z).func_149688_o();
    return (!world.func_147437_c(x, y - 1, z)) && (material != null) && (material.func_76218_k()) && (material.func_76220_a());
  }
  
  @SideOnly(Side.CLIENT)
  public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
  {
    return false;
  }
  

  @SideOnly(Side.CLIENT)
  public void func_149734_b(World world, int x, int y, int z, Random rand) {}
  

  @SideOnly(Side.CLIENT)
  public String func_149702_O()
  {
    return func_149641_N();
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int par1, int par2)
  {
    return net.minecraft.init.Blocks.field_150339_S.func_149733_h(0);
  }
  
  public static class TileEntityPlacedItem extends TileEntity {
    private static final String ITEM_KEY = "WITCPlacedItem";
    private ItemStack stack;
    
    public TileEntityPlacedItem() {}
    
    public boolean canUpdate() { return false; }
    

    public void func_145841_b(NBTTagCompound nbtRoot)
    {
      super.func_145841_b(nbtRoot);
      if (stack != null) {
        NBTTagCompound nbtItem = new NBTTagCompound();
        stack.func_77955_b(nbtItem);
        nbtRoot.func_74782_a("WITCPlacedItem", nbtItem);
      }
    }
    
    public void func_145839_a(NBTTagCompound nbtRoot)
    {
      super.func_145839_a(nbtRoot);
      if (nbtRoot.func_74764_b("WITCPlacedItem")) {
        NBTTagCompound nbtItem = nbtRoot.func_74775_l("WITCPlacedItem");
        ItemStack stack = ItemStack.func_77949_a(nbtItem);
        this.stack = stack;
      }
    }
    
    public void setStack(ItemStack stack) {
      this.stack = stack;
      if (!field_145850_b.field_72995_K) {
        field_145850_b.func_147471_g(field_145851_c, field_145848_d, field_145849_e);
      }
    }
    
    public ItemStack getStack() {
      return stack;
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
  }
}
