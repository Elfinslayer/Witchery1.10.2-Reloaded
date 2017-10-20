package com.emoniph.witchery.blocks;

import com.emoniph.witchery.util.BlockUtil;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGrassper extends BlockBaseContainer
{
  public BlockGrassper()
  {
    super(Material.field_151585_k, TileEntityGrassper.class);
    
    func_149672_a(field_149779_h);
    
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.51F, 1.0F);
  }
  
  public void func_149726_b(World world, int posX, int posY, int posZ)
  {
    super.func_149726_b(world, posX, posY, posZ);
    BlockUtil.setBlockDefaultDirection(world, posX, posY, posZ);
  }
  
  public boolean func_149662_c()
  {
    return false;
  }
  
  public boolean func_149646_a(IBlockAccess iblockaccess, int i, int j, int k, int l)
  {
    return false;
  }
  
  public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4)
  {
    return null;
  }
  
  public boolean func_149727_a(World world, int posX, int posY, int posZ, EntityPlayer player, int par6, float par7, float par8, float par9)
  {
    if (field_72995_K) {
      return true;
    }
    TileEntityGrassper tileEntity = (TileEntityGrassper)world.func_147438_o(posX, posY, posZ);
    if (tileEntity != null) {
      ItemStack stack = tileEntity.func_70301_a(0);
      if (stack != null) {
        tileEntity.func_70299_a(0, null);
        world.func_72838_d(new EntityItem(world, 0.5D + posX, 0.8D + posY, 0.5D + posZ, stack));
      } else {
        stack = player.func_70694_bm();
        if (stack != null) {
          tileEntity.func_70299_a(0, stack.func_77979_a(1));
          if (field_77994_a == 0) {
            field_71071_by.func_70299_a(field_71071_by.field_70461_c, null);
          }
        }
      }
    }
    
    return true;
  }
  

  public void func_149695_a(World world, int posX, int posY, int posZ, Block par5)
  {
    boolean flag = (world.func_72864_z(posX, posY, posZ)) || (world.func_72864_z(posX, posY + 1, posZ));
    int i1 = world.func_72805_g(posX, posY, posZ);
    boolean flag1 = (i1 & 0x8) != 0;
    
    if ((flag) && (!flag1)) {
      world.func_147464_a(posX, posY, posZ, this, func_149738_a(world));
      world.func_72921_c(posX, posY, posZ, i1 | 0x8, 4);
    } else if ((!flag) && (flag1)) {
      world.func_72921_c(posX, posY, posZ, i1 & 0xFFFFFFF7, 4);
    }
  }
  
  public void func_149689_a(World world, int posX, int posY, int posZ, EntityLivingBase entityLiving, ItemStack itemstack)
  {
    int l = BlockPistonBase.func_150071_a(world, posX, posY, posZ, entityLiving);
    world.func_72921_c(posX, posY, posZ, l, 2);
  }
  
  public void func_149749_a(World world, int posX, int posY, int posZ, Block par5, int par6)
  {
    TileEntityGrassper tileEntity = (TileEntityGrassper)world.func_147438_o(posX, posY, posZ);
    if (tileEntity != null) {
      for (int j1 = 0; j1 < tileEntity.func_70302_i_(); j1++) {
        ItemStack itemstack = tileEntity.func_70301_a(j1);
        
        if (itemstack != null) {
          float f = field_73012_v.nextFloat() * 0.8F + 0.1F;
          float f1 = field_73012_v.nextFloat() * 0.8F + 0.1F;
          float f2 = field_73012_v.nextFloat() * 0.8F + 0.1F;
          
          while (field_77994_a > 0) {
            int k1 = field_73012_v.nextInt(21) + 10;
            
            if (k1 > field_77994_a) {
              k1 = field_77994_a;
            }
            
            field_77994_a -= k1;
            EntityItem entityitem = new EntityItem(world, posX + f, posY + f1, posZ + f2, new ItemStack(itemstack.func_77973_b(), k1, itemstack.func_77960_j()));
            

            if (itemstack.func_77942_o()) {
              entityitem.func_92059_d().func_77982_d((NBTTagCompound)itemstack.func_77978_p().func_74737_b());
            }
            
            float f3 = 0.05F;
            field_70159_w = ((float)field_73012_v.nextGaussian() * 0.05F);
            field_70181_x = ((float)field_73012_v.nextGaussian() * 0.05F + 0.2F);
            field_70179_y = ((float)field_73012_v.nextGaussian() * 0.05F);
            world.func_72838_d(entityitem);
          }
        }
      }
      
      world.func_147453_f(posX, posY, posZ, par5);
    }
    
    super.func_149749_a(world, posX, posY, posZ, par5, par6);
  }
  
  public boolean func_149740_M()
  {
    return true;
  }
  
  public int func_149736_g(World world, int posX, int posY, int posZ, int par5)
  {
    return Container.func_94526_b((IInventory)world.func_147438_o(posX, posY, posZ));
  }
  
  public static class TileEntityGrassper extends TileEntityBase implements IInventory {
    private ItemStack[] contents = new ItemStack[1];
    protected String customName;
    
    public TileEntityGrassper() {}
    
    public boolean canUpdate() { return false; }
    

    public int func_70302_i_()
    {
      return contents.length;
    }
    
    public ItemStack func_70301_a(int slot)
    {
      return contents[slot];
    }
    
    public ItemStack func_70298_a(int slot, int size)
    {
      if (contents[slot] != null)
      {

        if (contents[slot].field_77994_a <= size) {
          ItemStack itemstack = contents[slot];
          contents[slot] = null;
          field_145850_b.func_147471_g(field_145851_c, field_145848_d, field_145849_e);
          return itemstack;
        }
        ItemStack itemstack = contents[slot].func_77979_a(size);
        
        if (contents[slot].field_77994_a == 0) {
          contents[slot] = null;
        }
        
        field_145850_b.func_147471_g(field_145851_c, field_145848_d, field_145849_e);
        return itemstack;
      }
      
      return null;
    }
    

    public ItemStack func_70304_b(int slot)
    {
      if (contents[slot] != null) {
        ItemStack itemstack = contents[slot];
        contents[slot] = null;
        return itemstack;
      }
      return null;
    }
    
    public int getRandomStackFromInventory()
    {
      int i = -1;
      int j = 1;
      
      for (int k = 0; k < contents.length; k++) {
        if ((contents[k] != null) && (field_145850_b.field_73012_v.nextInt(j++) == 0)) {
          i = k;
        }
      }
      
      return i;
    }
    
    public void func_70299_a(int slot, ItemStack itemstack)
    {
      contents[slot] = itemstack;
      
      if ((itemstack != null) && (field_77994_a > func_70297_j_())) {
        field_77994_a = func_70297_j_();
      }
      
      field_145850_b.func_147471_g(field_145851_c, field_145848_d, field_145849_e);
    }
    
    public int addItem(ItemStack itemstack) {
      for (int i = 0; i < contents.length; i++) {
        if (contents[i] == null) {
          func_70299_a(i, itemstack);
          return i;
        }
      }
      
      return -1;
    }
    






    public String func_145825_b()
    {
      return "tile.witcheryGrassper.name";
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
    
    public boolean func_145818_k_()
    {
      return false;
    }
    
    public void func_145839_a(NBTTagCompound nbtTag)
    {
      super.func_145839_a(nbtTag);
      NBTTagList nbttaglist = nbtTag.func_150295_c("Items", 10);
      contents = new ItemStack[func_70302_i_()];
      
      for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
        NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
        int j = nbttagcompound1.func_74771_c("Slot") & 0xFF;
        
        if ((j >= 0) && (j < contents.length)) {
          contents[j] = ItemStack.func_77949_a(nbttagcompound1);
        }
      }
      
      if (nbtTag.func_74764_b("CustomName")) {
        customName = nbtTag.func_74779_i("CustomName");
      }
    }
    
    public void func_145841_b(NBTTagCompound nbtTag)
    {
      super.func_145841_b(nbtTag);
      NBTTagList nbttaglist = new NBTTagList();
      
      for (int i = 0; i < contents.length; i++) {
        if (contents[i] != null) {
          NBTTagCompound nbttagcompound1 = new NBTTagCompound();
          nbttagcompound1.func_74774_a("Slot", (byte)i);
          contents[i].func_77955_b(nbttagcompound1);
          nbttaglist.func_74742_a(nbttagcompound1);
        }
      }
      
      nbtTag.func_74782_a("Items", nbttaglist);
      
      if (func_145818_k_()) {
        nbtTag.func_74778_a("CustomName", customName);
      }
    }
    
    public int func_70297_j_()
    {
      return 1;
    }
    
    public boolean func_70300_a(EntityPlayer player)
    {
      return field_145850_b.func_147438_o(field_145851_c, field_145848_d, field_145849_e) == this;
    }
    


    public void func_70295_k_() {}
    


    public void func_70305_f() {}
    

    public boolean func_94041_b(int slot, ItemStack itemstack)
    {
      return (slot == 0) && (contents[0] == null);
    }
  }
}
