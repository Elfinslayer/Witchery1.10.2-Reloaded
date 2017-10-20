package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.common.IPowerSource;
import com.emoniph.witchery.common.PowerSources;
import com.emoniph.witchery.common.PowerSources.RelativePowerSource;
import com.emoniph.witchery.crafting.DistilleryRecipes;
import com.emoniph.witchery.crafting.DistilleryRecipes.DistilleryRecipe;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import com.emoniph.witchery.util.BlockSide;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SlotClayJar;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDistillery extends BlockBaseContainer
{
  private final Random furnaceRand = new Random();
  
  private final boolean isActive;
  
  private static boolean keepFurnaceInventory;
  

  public BlockDistillery(boolean burning)
  {
    super(Material.field_151573_f, TileEntityDistillery.class);
    registerTileEntity = (!burning);
    registerWithCreateTab = (!burning);
    
    isActive = burning;
    
    func_149711_c(3.5F);
    func_149672_a(field_149777_j);
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    
    if (burning) {
      func_149715_a(0.4F);
    }
  }
  
  public boolean func_149662_c()
  {
    return false;
  }
  
  public boolean func_149646_a(IBlockAccess iblockaccess, int i, int j, int k, int l)
  {
    return false;
  }
  
  public boolean func_149686_d()
  {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister par1IconRegister)
  {
    field_149761_L = par1IconRegister.func_94245_a(func_149641_N());
  }
  
  public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
  {
    return Item.func_150898_a(BlocksDISTILLERY_IDLE);
  }
  
  public void func_149726_b(World par1World, int par2, int par3, int par4)
  {
    super.func_149726_b(par1World, par2, par3, par4);
    BlockUtil.setBlockDefaultDirection(par1World, par2, par3, par4);
  }
  
  public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
  {
    if (field_72995_K) {
      return true;
    }
    TileEntityDistillery tileentityfurnace = (TileEntityDistillery)par1World.func_147438_o(par2, par3, par4);
    
    if (tileentityfurnace != null) {
      par5EntityPlayer.openGui(Witchery.instance, 3, par1World, par2, par3, par4);
    }
    
    return true;
  }
  
  public static void updateDistilleryBlockState(boolean par0, World par1World, int par2, int par3, int par4)
  {
    int l = par1World.func_72805_g(par2, par3, par4);
    TileEntity tileentity = par1World.func_147438_o(par2, par3, par4);
    keepFurnaceInventory = true;
    
    if (par0) {
      par1World.func_147449_b(par2, par3, par4, BlocksDISTILLERY_BURNING);
    } else {
      par1World.func_147449_b(par2, par3, par4, BlocksDISTILLERY_IDLE);
    }
    
    keepFurnaceInventory = false;
    par1World.func_72921_c(par2, par3, par4, l, 2);
    
    if (tileentity != null) {
      tileentity.func_145829_t();
      par1World.func_147455_a(par2, par3, par4, tileentity);
    }
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149734_b(World par1World, int par2, int par3, int par4, Random par5Random)
  {
    if (isActive) {
      double d0 = par2 + 0.4F + par5Random.nextFloat() * 0.2F;
      double d1 = par3 + 1.0F + par5Random.nextFloat() * 0.3F;
      double d2 = par4 + 0.4F + par5Random.nextFloat() * 0.2F;
      par1World.func_72869_a(ParticleEffect.INSTANT_SPELL.toString(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }
  }
  
  public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
  {
    int l = net.minecraft.util.MathHelper.func_76128_c(field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3;
    
    if (l == 0) {
      par1World.func_72921_c(par2, par3, par4, 2, 2);
    }
    
    if (l == 1) {
      par1World.func_72921_c(par2, par3, par4, 5, 2);
    }
    
    if (l == 2) {
      par1World.func_72921_c(par2, par3, par4, 3, 2);
    }
    
    if (l == 3) {
      par1World.func_72921_c(par2, par3, par4, 4, 2);
    }
  }
  




  public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6)
  {
    if (!keepFurnaceInventory) {
      TileEntityDistillery tileentityfurnace = (TileEntityDistillery)par1World.func_147438_o(par2, par3, par4);
      
      if (tileentityfurnace != null) {
        for (int j1 = 0; j1 < tileentityfurnace.func_70302_i_(); j1++) {
          ItemStack itemstack = tileentityfurnace.func_70301_a(j1);
          
          if (itemstack != null) {
            float f = furnaceRand.nextFloat() * 0.8F + 0.1F;
            float f1 = furnaceRand.nextFloat() * 0.8F + 0.1F;
            float f2 = furnaceRand.nextFloat() * 0.8F + 0.1F;
            
            while (field_77994_a > 0) {
              int k1 = furnaceRand.nextInt(21) + 10;
              
              if (k1 > field_77994_a) {
                k1 = field_77994_a;
              }
              
              field_77994_a -= k1;
              EntityItem entityitem = new EntityItem(par1World, par2 + f, par3 + f1, par4 + f2, new ItemStack(itemstack.func_77973_b(), k1, itemstack.func_77960_j()));
              

              if (itemstack.func_77942_o()) {
                entityitem.func_92059_d().func_77982_d((NBTTagCompound)itemstack.func_77978_p().func_74737_b());
              }
              
              float f3 = 0.05F;
              field_70159_w = ((float)furnaceRand.nextGaussian() * f3);
              field_70181_x = ((float)furnaceRand.nextGaussian() * f3 + 0.2F);
              field_70179_y = ((float)furnaceRand.nextGaussian() * f3);
              par1World.func_72838_d(entityitem);
            }
          }
        }
        
        par1World.func_147453_f(par2, par3, par4, par5);
      }
    }
    
    super.func_149749_a(par1World, par2, par3, par4, par5, par6);
  }
  
  public boolean func_149740_M()
  {
    return true;
  }
  
  public int func_149736_g(World par1World, int par2, int par3, int par4, int par5)
  {
    return Container.func_94526_b((IInventory)par1World.func_147438_o(par2, par3, par4));
  }
  
  public Item func_149694_d(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
  {
    return Item.func_150898_a(BlocksDISTILLERY_IDLE);
  }
  









  public static class TileEntityDistillery
    extends TileEntityBase
    implements net.minecraft.inventory.ISidedInventory
  {
    private ItemStack[] furnaceItemStacks = new ItemStack[7];
    
    public int currentItemBurnTime;
    
    public int furnaceCookTime;
    
    public int powerLevel;
    
    static final int COOK_TIME = 800;
    
    Coord powerSourceCoord;
    static final int POWER_SOURCE_RADIUS = 16;
    static final float POWER_PER_TICK = 0.6F;
    
    public TileEntityDistillery() {}
    
    public int func_70302_i_()
    {
      return furnaceItemStacks.length;
    }
    
    public ItemStack func_70301_a(int par1)
    {
      return furnaceItemStacks[par1];
    }
    
    public ItemStack func_70298_a(int par1, int par2)
    {
      if (furnaceItemStacks[par1] != null)
      {

        if (furnaceItemStacks[par1].field_77994_a <= par2) {
          ItemStack itemstack = furnaceItemStacks[par1];
          furnaceItemStacks[par1] = null;
          return itemstack;
        }
        ItemStack itemstack = furnaceItemStacks[par1].func_77979_a(par2);
        
        if (furnaceItemStacks[par1].field_77994_a == 0) {
          furnaceItemStacks[par1] = null;
        }
        
        return itemstack;
      }
      
      return null;
    }
    

    public ItemStack func_70304_b(int par1)
    {
      if (furnaceItemStacks[par1] != null) {
        ItemStack itemstack = furnaceItemStacks[par1];
        furnaceItemStacks[par1] = null;
        return itemstack;
      }
      return null;
    }
    

    public void func_70299_a(int par1, ItemStack par2ItemStack)
    {
      furnaceItemStacks[par1] = par2ItemStack;
      
      if ((par2ItemStack != null) && (field_77994_a > func_70297_j_())) {
        field_77994_a = func_70297_j_();
      }
    }
    
    public String func_145825_b()
    {
      return func_145838_q().func_149732_F();
    }
    
    public boolean func_145818_k_()
    {
      return true;
    }
    
    public void func_145839_a(NBTTagCompound par1NBTTagCompound)
    {
      super.func_145839_a(par1NBTTagCompound);
      NBTTagList nbttaglist = par1NBTTagCompound.func_150295_c("Items", 10);
      furnaceItemStacks = new ItemStack[func_70302_i_()];
      
      for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
        NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
        byte b0 = nbttagcompound1.func_74771_c("Slot");
        
        if ((b0 >= 0) && (b0 < furnaceItemStacks.length)) {
          furnaceItemStacks[b0] = ItemStack.func_77949_a(nbttagcompound1);
        }
      }
      

      furnaceCookTime = par1NBTTagCompound.func_74765_d("CookTime");
    }
    


    public void func_145841_b(NBTTagCompound par1NBTTagCompound)
    {
      super.func_145841_b(par1NBTTagCompound);
      

      par1NBTTagCompound.func_74777_a("CookTime", (short)furnaceCookTime);
      NBTTagList nbttaglist = new NBTTagList();
      
      for (int i = 0; i < furnaceItemStacks.length; i++) {
        if (furnaceItemStacks[i] != null) {
          NBTTagCompound nbttagcompound1 = new NBTTagCompound();
          nbttagcompound1.func_74774_a("Slot", (byte)i);
          furnaceItemStacks[i].func_77955_b(nbttagcompound1);
          nbttaglist.func_74742_a(nbttagcompound1);
        }
      }
      
      par1NBTTagCompound.func_74782_a("Items", nbttaglist);
    }
    
    public int func_70297_j_()
    {
      return 64;
    }
    
    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int par1) {
      return furnaceCookTime * par1 / 800;
    }
    
















    IPowerSource getPowerSource()
    {
      if ((powerSourceCoord == null) || (ticks % 100L == 0L)) {
        return findNewPowerSource();
      }
      TileEntity tileEntity = powerSourceCoord.getBlockTileEntity(field_145850_b);
      if (!(tileEntity instanceof BlockAltar.TileEntityAltar)) {
        return findNewPowerSource();
      }
      BlockAltar.TileEntityAltar altarTileEntity = (BlockAltar.TileEntityAltar)tileEntity;
      if (!altarTileEntity.isValid()) {
        return findNewPowerSource();
      }
      return altarTileEntity;
    }
    




    private IPowerSource findNewPowerSource()
    {
      List<PowerSources.RelativePowerSource> sources = PowerSources.instance() != null ? PowerSources.instance().get(field_145850_b, new Coord(this), 16) : null;
      return (sources != null) && (sources.size() > 0) ? ((PowerSources.RelativePowerSource)sources.get(0)).source() : null;
    }
    


    public void func_145845_h()
    {
      super.func_145845_h();
      
      boolean flag1 = false;
      
      if (!field_145850_b.field_72995_K) {
        boolean cooking = furnaceCookTime > 0;
        boolean powered = powerLevel > 0;
        if (canSmelt()) {
          IPowerSource powerSource = getPowerSource();
          if ((powerSource != null) && (!powerSource.isLocationEqual(powerSourceCoord))) {
            powerSourceCoord = powerSource.getLocation();
          } else {
            powerSourceCoord = null;
          }
          
          powerLevel = (powerSource == null ? 0 : 1);
          
          if ((powerSource != null) && (powerSource.consumePower(0.6F))) {
            furnaceCookTime += 1;
            
            if (furnaceCookTime == 800) {
              furnaceCookTime = 0;
              smeltItem();
              flag1 = true;
            }
          } else {
            powerLevel = 0;
          }
        } else {
          if (ticks % 40L == 0L) {
            IPowerSource powerSource = getPowerSource();
            if ((powerSource != null) && (!powerSource.isLocationEqual(powerSourceCoord))) {
              powerSourceCoord = powerSource.getLocation();
            }
            
            powerLevel = (powerSource == null ? 0 : 1);
          }
          
          furnaceCookTime = 0;
        }
        
        if (cooking != furnaceCookTime > 0) {
          BlockDistillery.updateDistilleryBlockState((furnaceCookTime > 0) && (powerLevel > 0), field_145850_b, field_145851_c, field_145848_d, field_145849_e);
          lastUpdate = ticks;
          needUpdate = false;
        } else if (powered != powerLevel > 0) {
          if (ticks - lastUpdate > 20L) {
            BlockDistillery.updateDistilleryBlockState((furnaceCookTime > 0) && (powerLevel > 0), field_145850_b, field_145851_c, field_145848_d, field_145849_e);
            lastUpdate = ticks;
            needUpdate = false;
          } else {
            needUpdate = true;
          }
        } else if ((needUpdate) && (ticks - lastUpdate > 20L)) {
          BlockDistillery.updateDistilleryBlockState((furnaceCookTime > 0) && (powerLevel > 0), field_145850_b, field_145851_c, field_145848_d, field_145849_e);
          lastUpdate = ticks;
          needUpdate = false;
        }
        
        if (flag1) {
          field_145850_b.func_147471_g(field_145851_c, field_145848_d, field_145849_e);
        }
      }
    }
    
    private long lastUpdate = 0L;
    private boolean needUpdate = false;
    private static final int THROTTLE = 20;
    
    private boolean canSmelt() {
      DistilleryRecipes.DistilleryRecipe recipe = getActiveRecipe();
      
      if (recipe == null) {
        return false;
      }
      
      ItemStack[] itemstacks = recipe.getOutputs();
      
      for (int i = 0; i < itemstacks.length; i++) {
        ItemStack current = furnaceItemStacks[(i + 3)];
        if ((itemstacks[i] != null) && (current != null) && (current.func_77969_a(itemstacks[i]))) {
          int newSize = field_77994_a + field_77994_a;
          if ((newSize > func_70297_j_()) || (newSize > current.func_77976_d())) {
            return false;
          }
        }
      }
      
      return true;
    }
    
    public DistilleryRecipes.DistilleryRecipe getActiveRecipe() {
      if ((furnaceItemStacks[0] == null) && (furnaceItemStacks[1] == null)) {
        return null;
      }
      
      DistilleryRecipes.DistilleryRecipe recipe = DistilleryRecipes.instance().getDistillingResult(furnaceItemStacks[0], furnaceItemStacks[1], furnaceItemStacks[2]);
      

      return recipe;
    }
    
    public void smeltItem() {
      if (canSmelt()) {
        DistilleryRecipes.DistilleryRecipe recipe = DistilleryRecipes.instance().getDistillingResult(furnaceItemStacks[0], furnaceItemStacks[1], furnaceItemStacks[2]);
        
        ItemStack[] itemstacks = recipe.getOutputs();
        
        for (int i = 0; i < itemstacks.length; i++) {
          int furnaceIndex = i + 3;
          if (itemstacks[i] != null) {
            if (furnaceItemStacks[furnaceIndex] == null) {
              furnaceItemStacks[furnaceIndex] = itemstacks[i].func_77946_l();
            } else if (furnaceItemStacks[furnaceIndex].func_77969_a(itemstacks[i])) {
              furnaceItemStacks[furnaceIndex].field_77994_a += field_77994_a;
            }
          }
        }
        
        if (furnaceItemStacks[0] != null) {
          furnaceItemStacks[0].field_77994_a -= 1;
          
          if (furnaceItemStacks[0].field_77994_a <= 0) {
            furnaceItemStacks[0] = null;
          }
        }
        
        if (furnaceItemStacks[1] != null) {
          furnaceItemStacks[1].field_77994_a -= 1;
          
          if (furnaceItemStacks[1].field_77994_a <= 0) {
            furnaceItemStacks[1] = null;
          }
        }
        
        if (furnaceItemStacks[2] != null) {
          furnaceItemStacks[2].field_77994_a -= recipe.getJars();
          
          if (furnaceItemStacks[2].field_77994_a <= 0) {
            furnaceItemStacks[2] = null;
          }
        }
      }
    }
    

    public boolean func_70300_a(EntityPlayer par1EntityPlayer)
    {
      return field_145850_b.func_147438_o(field_145851_c, field_145848_d, field_145849_e) == this;
    }
    


    public void func_70295_k_() {}
    


    public void func_70305_f() {}
    

    public boolean func_94041_b(int slot, ItemStack itemstack)
    {
      if (slot > 3)
        return false;
      if (slot == 2) {
        return ItemsGENERIC.itemEmptyClayJar.isMatch(itemstack);
      }
      return !ItemsGENERIC.itemEmptyClayJar.isMatch(itemstack);
    }
    

    private static final int[] slots_top = { 0, 1, 2 };
    private static final int[] slots_bottom = { 0, 1, 2 };
    private static final int[] slots_sides = { 0, 1, 2, 3, 4, 5, 6 };
    
    public int[] func_94128_d(int side)
    {
      return BlockSide.TOP.isEqual(side) ? slots_top : BlockSide.BOTTOM.isEqual(side) ? slots_bottom : slots_sides;
    }
    
    public boolean func_102007_a(int slot, ItemStack itemstack, int par3)
    {
      return func_94041_b(slot, itemstack);
    }
    
    public boolean func_102008_b(int slot, ItemStack itemstack, int side)
    {
      return (side != 0) && (side != 1) && ((slot == 3) || (slot == 4) || (slot == 5) || (slot == 6));
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
  

  public static class ContainerDistillery
    extends Container
  {
    private BlockDistillery.TileEntityDistillery furnace;
    
    private int lastCookTime;
    
    private int lastPowerLevel;
    
    public ContainerDistillery(InventoryPlayer par1InventoryPlayer, BlockDistillery.TileEntityDistillery par2TileEntityFurnace)
    {
      furnace = par2TileEntityFurnace;
      func_75146_a(new Slot(par2TileEntityFurnace, 0, 48, 16));
      func_75146_a(new Slot(par2TileEntityFurnace, 1, 48, 34));
      func_75146_a(new SlotClayJar(par2TileEntityFurnace, 2, 48, 54));
      func_75146_a(new SlotFurnace(field_70458_d, par2TileEntityFurnace, 3, 110, 16));
      func_75146_a(new SlotFurnace(field_70458_d, par2TileEntityFurnace, 4, 128, 16));
      func_75146_a(new SlotFurnace(field_70458_d, par2TileEntityFurnace, 5, 110, 34));
      func_75146_a(new SlotFurnace(field_70458_d, par2TileEntityFurnace, 6, 128, 34));
      


      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 9; j++) {
          func_75146_a(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
        }
      }
      
      for (i = 0; i < 9; i++) {
        func_75146_a(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
      }
    }
    
    public void func_75132_a(ICrafting par1ICrafting)
    {
      super.func_75132_a(par1ICrafting);
      par1ICrafting.func_71112_a(this, 0, furnace.furnaceCookTime);
      par1ICrafting.func_71112_a(this, 1, furnace.powerLevel);
    }
    
    public void func_75142_b()
    {
      super.func_75142_b();
      
      for (int i = 0; i < field_75149_d.size(); i++) {
        ICrafting icrafting = (ICrafting)field_75149_d.get(i);
        
        if (lastCookTime != furnace.furnaceCookTime) {
          icrafting.func_71112_a(this, 0, furnace.furnaceCookTime);
        }
        
        if (lastPowerLevel != furnace.powerLevel) {
          icrafting.func_71112_a(this, 1, furnace.powerLevel);
        }
      }
      
      lastCookTime = furnace.furnaceCookTime;
      lastPowerLevel = furnace.powerLevel;
    }
    
    @SideOnly(Side.CLIENT)
    public void func_75137_b(int par1, int par2)
    {
      if (par1 == 0) {
        furnace.furnaceCookTime = par2;
      }
      
      if (par1 == 1) {
        furnace.powerLevel = par2;
      }
    }
    
    public boolean func_75145_c(EntityPlayer par1EntityPlayer)
    {
      return furnace.func_70300_a(par1EntityPlayer);
    }
    
    public ItemStack func_82846_b(EntityPlayer player, int slotIndex)
    {
      ItemStack itemstack = null;
      Slot slot = (Slot)field_75151_b.get(slotIndex);
      
      if ((slot != null) && (slot.func_75216_d())) {
        ItemStack itemstack1 = slot.func_75211_c();
        itemstack = itemstack1.func_77946_l();
        
        if ((slotIndex >= 3) && (slotIndex <= 6)) {
          if (!func_75135_a(itemstack1, 7, 43, true)) {
            return null;
          }
          
          slot.func_75220_a(itemstack1, itemstack);
        } else if ((slotIndex != 1) && (slotIndex != 0) && (slotIndex != 2)) {
          if (FurnaceRecipes.func_77602_a().func_151395_a(itemstack1) != null) {
            if (!func_75135_a(itemstack1, 0, 2, false)) {
              return null;
            }
          } else if (ItemsGENERIC.itemEmptyClayJar.isMatch(itemstack1)) {
            if (!func_75135_a(itemstack1, 2, 3, false)) {
              return null;
            }
          } else if ((slotIndex >= 7) && (slotIndex < 34)) {
            if (!func_75135_a(itemstack1, 34, 43, false)) {
              return null;
            }
          } else if ((slotIndex >= 34) && (slotIndex < 43) && (!func_75135_a(itemstack1, 7, 34, false))) {
            return null;
          }
        } else if (!func_75135_a(itemstack1, 7, 43, false)) {
          return null;
        }
        
        if (field_77994_a == 0) {
          slot.func_75215_d((ItemStack)null);
        } else {
          slot.func_75218_e();
        }
        
        if (field_77994_a == field_77994_a) {
          return null;
        }
        
        slot.func_82870_a(player, itemstack1);
      }
      
      return itemstack;
    }
  }
}
