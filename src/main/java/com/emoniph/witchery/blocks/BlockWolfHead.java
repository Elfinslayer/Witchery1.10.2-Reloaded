package com.emoniph.witchery.blocks;

import com.emoniph.witchery.item.ItemWolfHead;
import com.emoniph.witchery.util.BlockUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWolfHead
  extends BlockBaseContainer
{
  public BlockWolfHead()
  {
    super(Material.field_151594_q, TileEntityWolfHead.class, ItemWolfHead.class);
    func_149676_a(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
  }
  
  public int func_149645_b()
  {
    return -1;
  }
  
  public boolean func_149662_c()
  {
    return false;
  }
  
  public boolean func_149686_d()
  {
    return false;
  }
  

  public void func_149719_a(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_)
  {
    int l = p_149719_1_.func_72805_g(p_149719_2_, p_149719_3_, p_149719_4_) & 0x7;
    
    switch (l) {
    case 1: 
    default: 
      func_149676_a(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
      break;
    case 2: 
      func_149676_a(0.25F, 0.25F, 0.5F, 0.75F, 0.75F, 1.0F);
      break;
    case 3: 
      func_149676_a(0.25F, 0.25F, 0.0F, 0.75F, 0.75F, 0.5F);
      break;
    case 4: 
      func_149676_a(0.5F, 0.25F, 0.25F, 1.0F, 0.75F, 0.75F);
      break;
    case 5: 
      func_149676_a(0.0F, 0.25F, 0.25F, 0.5F, 0.75F, 0.75F);
    }
  }
  
  public AxisAlignedBB func_149668_a(World world, int x, int y, int z)
  {
    func_149719_a(world, x, y, z);
    return super.func_149668_a(world, x, y, z);
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack)
  {
    int l = MathHelper.func_76128_c(field_70177_z * 4.0F / 360.0F + 2.5D) & 0x3;
    world.func_72921_c(x, y, z, l, 2);
  }
  
  @SideOnly(Side.CLIENT)
  public Item func_149694_d(World world, int x, int y, int z)
  {
    return Item.func_150898_a(this);
  }
  
  public int func_149643_k(World world, int x, int y, int z)
  {
    TileEntity tileentity = world.func_147438_o(x, y, z);
    return (tileentity != null) && ((tileentity instanceof TileEntityWolfHead)) ? ((TileEntityWolfHead)tileentity).getSkullType() : super.func_149643_k(world, x, y, z);
  }
  


  public void func_149666_a(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_)
  {
    super.func_149666_a(p_149666_1_, p_149666_2_, p_149666_3_);
  }
  
  public int func_149692_a(int blockMetadata)
  {
    return blockMetadata;
  }
  
  public void func_149681_a(World world, int x, int y, int z, int metadata, EntityPlayer player)
  {
    if (field_71075_bZ.field_75098_d) {
      metadata |= 0x8;
      world.func_72921_c(x, y, z, metadata, 4);
    }
    func_149697_b(world, x, y, z, metadata, 0);
    
    super.func_149681_a(world, x, y, z, metadata, player);
  }
  
  public void func_149749_a(World world, int x, int y, int z, Block block, int metadata)
  {
    super.func_149749_a(world, x, y, z, block, metadata);
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
  {
    ArrayList<ItemStack> ret = new ArrayList();
    
    if ((metadata & 0x8) == 0) {
      ItemStack itemstack = new ItemStack(this, 1, func_149643_k(world, x, y, z));
      TileEntityWolfHead head = (TileEntityWolfHead)BlockUtil.getTileEntity(world, x, y, z, TileEntityWolfHead.class);
      
      if (head == null) {
        return ret;
      }
      ret.add(itemstack);
    }
    
    return ret;
  }
  
  public Item func_149650_a(int p_149650_1_, Random rand, int p_149650_3_)
  {
    return Item.func_150898_a(this);
  }
  

  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister iconRegister) {}
  

  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int p_149691_1_, int p_149691_2_)
  {
    return Blocks.field_150425_aM.func_149733_h(p_149691_1_);
  }
  
  @SideOnly(Side.CLIENT)
  public String func_149702_O()
  {
    return func_149641_N() + "_" + ItemWolfHead.field_94587_a[0];
  }
  
  public static class TileEntityWolfHead extends TileEntity {
    private int skullType;
    private int rotation;
    
    public TileEntityWolfHead() {}
    
    public boolean canUpdate() {
      return false;
    }
    
    public void func_145841_b(NBTTagCompound nbtRoot)
    {
      super.func_145841_b(nbtRoot);
      nbtRoot.func_74774_a("SkullType", (byte)(skullType & 0xFF));
      nbtRoot.func_74774_a("Rot", (byte)(rotation & 0xFF));
    }
    
    public void func_145839_a(NBTTagCompound nbtRoot)
    {
      super.func_145839_a(nbtRoot);
      skullType = nbtRoot.func_74771_c("SkullType");
      rotation = nbtRoot.func_74771_c("Rot");
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
    
    public void setSkullType(int skullType) {
      this.skullType = skullType;
      field_145850_b.func_147471_g(field_145851_c, field_145848_d, field_145849_e);
    }
    
    public int getSkullType()
    {
      return skullType;
    }
    
    public void setRotation(int p_145903_1_) {
      rotation = p_145903_1_;
      field_145850_b.func_147471_g(field_145851_c, field_145848_d, field_145849_e);
    }
    
    @SideOnly(Side.CLIENT)
    public int getRotation() {
      return rotation;
    }
  }
}
