package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.common.IPowerSource;
import com.emoniph.witchery.common.PowerSources;
import com.emoniph.witchery.common.PowerSources.RelativePowerSource;
import com.emoniph.witchery.crafting.SpinningRecipes;
import com.emoniph.witchery.crafting.SpinningRecipes.SpinningRecipe;
import com.emoniph.witchery.util.BlockSide;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.Coord;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;



public class BlockSpinningWheel
  extends BlockBaseContainer
{
  public BlockSpinningWheel()
  {
    super(Material.field_151575_d, TileEntitySpinningWheel.class);
    func_149711_c(3.5F);
    func_149672_a(field_149766_f);
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
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
  
  public void func_149726_b(World world, int x, int y, int z)
  {
    super.func_149726_b(world, x, y, z);
    BlockUtil.setBlockDefaultDirection(world, x, y, z);
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
  {
    if (field_72995_K) {
      return true;
    }
    TileEntity tile = world.func_147438_o(x, y, z);
    if ((tile != null) && ((tile instanceof TileEntitySpinningWheel))) {
      TileEntitySpinningWheel spinningWheel = (TileEntitySpinningWheel)tile;
      player.openGui(Witchery.instance, 4, world, x, y, z);
    }
    
    return true;
  }
  


  @SideOnly(Side.CLIENT)
  public void func_149734_b(World world, int x, int y, int z, Random rand) {}
  

  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack)
  {
    int l = MathHelper.func_76128_c(field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3;
    switch (l) {
    case 0: 
    default: 
      world.func_72921_c(x, y, z, 2, 2);
      break;
    case 1: 
      world.func_72921_c(x, y, z, 5, 2);
      break;
    case 2: 
      world.func_72921_c(x, y, z, 3, 2);
      break;
    case 3: 
      world.func_72921_c(x, y, z, 4, 2);
    }
    
  }
  
  public void func_149749_a(World world, int x, int y, int z, Block oldBlockID, int oldBlockMetadata)
  {
    TileEntity tile = world.func_147438_o(x, y, z);
    if ((tile != null) && ((tile instanceof TileEntitySpinningWheel))) {
      TileEntitySpinningWheel tileentityfurnace = (TileEntitySpinningWheel)tile;
      
      if (tileentityfurnace != null) {
        for (int j1 = 0; j1 < tileentityfurnace.func_70302_i_(); j1++) {
          ItemStack itemstack = tileentityfurnace.func_70301_a(j1);
          
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
              EntityItem entityitem = new EntityItem(world, x + f, y + f1, z + f2, new ItemStack(itemstack.func_77973_b(), k1, itemstack.func_77960_j()));
              

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
        
        world.func_147453_f(x, y, z, oldBlockID);
      }
    }
    
    super.func_149749_a(world, x, y, z, oldBlockID, oldBlockMetadata);
  }
  
  public boolean func_149740_M()
  {
    return true;
  }
  
  public int func_149736_g(World world, int x, int y, int z, int side)
  {
    return Container.func_94526_b((IInventory)world.func_147438_o(x, y, z));
  }
  







  public static class TileEntitySpinningWheel
    extends TileEntityBase
    implements ISidedInventory
  {
    private ItemStack[] slots = new ItemStack[5];
    

    public int furnaceCookTime = 0;
    
    public TileEntitySpinningWheel() {}
    
    public int func_70302_i_() { return slots.length; }
    

    public ItemStack func_70301_a(int slot)
    {
      return slots[slot];
    }
    
    public ItemStack func_70298_a(int slot, int quantity)
    {
      if (slots[slot] != null)
      {

        if (slots[slot].field_77994_a <= quantity) {
          ItemStack itemstack = slots[slot];
          slots[slot] = null;
          return itemstack;
        }
        ItemStack itemstack = slots[slot].func_77979_a(quantity);
        
        if (slots[slot].field_77994_a == 0) {
          slots[slot] = null;
        }
        
        return itemstack;
      }
      
      return null;
    }
    

    public ItemStack func_70304_b(int slot)
    {
      if (slots[slot] != null) {
        ItemStack itemstack = slots[slot];
        slots[slot] = null;
        return itemstack;
      }
      return null;
    }
    

    public void func_70299_a(int slot, ItemStack stack)
    {
      slots[slot] = stack;
      
      if ((stack != null) && (field_77994_a > func_70297_j_())) {
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
    
    public void func_145839_a(NBTTagCompound nbtRoot)
    {
      super.func_145839_a(nbtRoot);
      NBTTagList nbtSlotList = nbtRoot.func_150295_c("Items", 10);
      slots = new ItemStack[func_70302_i_()];
      
      for (int i = 0; i < nbtSlotList.func_74745_c(); i++) {
        NBTTagCompound nbtSlot = nbtSlotList.func_150305_b(i);
        byte b0 = nbtSlot.func_74771_c("Slot");
        
        if ((b0 >= 0) && (b0 < slots.length)) {
          slots[b0] = ItemStack.func_77949_a(nbtSlot);
        }
      }
      
      furnaceCookTime = nbtRoot.func_74765_d("CookTime");
      powerLevel = nbtRoot.func_74765_d("PowerLevel");
    }
    
    public void func_145841_b(NBTTagCompound nbtRoot)
    {
      super.func_145841_b(nbtRoot);
      nbtRoot.func_74777_a("CookTime", (short)furnaceCookTime);
      nbtRoot.func_74777_a("PowerLevel", (short)powerLevel);
      NBTTagList nbtSlotList = new NBTTagList();
      
      for (int i = 0; i < slots.length; i++) {
        if (slots[i] != null) {
          NBTTagCompound nbtSlot = new NBTTagCompound();
          nbtSlot.func_74774_a("Slot", (byte)i);
          slots[i].func_77955_b(nbtSlot);
          nbtSlotList.func_74742_a(nbtSlot);
        }
      }
      
      nbtRoot.func_74782_a("Items", nbtSlotList);
    }
    
    public int func_70297_j_()
    {
      return 64;
    }
    
    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int par1) {
      return furnaceCookTime * par1 / getTotalCookTime();
    }
    
    private final int TICKS_PER_SPIN = 20;
    private final int SPINS_PER_STEP = 3;
    private final int STEPS_TO_COMPLETE = 5;
    Coord powerSourceCoord;
    
    public int getTotalCookTime() { int time = 300;
      return 300;
    }
    
    public int getCookTime() {
      return furnaceCookTime;
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
    

    static final int POWER_SOURCE_RADIUS = 16;
    private IPowerSource findNewPowerSource()
    {
      List<PowerSources.RelativePowerSource> sources = PowerSources.instance() != null ? PowerSources.instance().get(field_145850_b, new Coord(this), 16) : null;
      
      return (sources != null) && (sources.size() > 0) ? ((PowerSources.RelativePowerSource)sources.get(0)).source() : null;
    }
    

    static final float POWER_PER_TICK = 0.6F;
    
    public int powerLevel;
    public void func_145845_h()
    {
      super.func_145845_h();
      
      boolean update = false;
      boolean cooking = furnaceCookTime > 0;
      
      if (!field_145850_b.field_72995_K) {
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
            update = furnaceCookTime == 0;
            furnaceCookTime += 1;
            if (furnaceCookTime == getTotalCookTime()) {
              furnaceCookTime = 0;
              smeltItem();
              update = true;
            }
            if (powered != powerLevel > 0) {
              update = true;
            }
          }
          else {
            powerLevel = 0;
            if (powered != powerLevel > 0) {
              update = true;
            }
          }
        } else {
          if (ticks % 40L == 0L) {
            IPowerSource powerSource = getPowerSource();
            if ((powerSource != null) && (!powerSource.isLocationEqual(powerSourceCoord))) {
              powerSourceCoord = powerSource.getLocation();
            }
            
            powerLevel = (powerSource == null ? 0 : 1);
          }
          
          if (furnaceCookTime <= 0) {} update = powered != powerLevel > 0;
          furnaceCookTime = 0;
        }
      }
      
      if (update) {
        func_70296_d();
      }
    }
    
    public void func_70296_d()
    {
      super.func_70296_d();
      if (!field_145850_b.field_72995_K) {
        field_145850_b.func_147471_g(field_145851_c, field_145848_d, field_145849_e);
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
    
    private boolean canSmelt() {
      if (slots[0] == null) {
        return false;
      }
      SpinningRecipes.SpinningRecipe recipe = SpinningRecipes.instance().getRecipe(slots[0], new ItemStack[] { slots[1], slots[3], slots[4] });
      

      if (recipe == null) {
        return false;
      }
      if (slots[2] == null) {
        return true;
      }
      ItemStack itemstack = recipe.getResult();
      
      if (!slots[2].func_77969_a(itemstack)) {
        return false;
      }
      int result = slots[2].field_77994_a + field_77994_a;
      return (result <= func_70297_j_()) && (result <= itemstack.func_77976_d());
    }
    
    public void smeltItem()
    {
      if (canSmelt()) {
        SpinningRecipes.SpinningRecipe recipe = SpinningRecipes.instance().getRecipe(slots[0], new ItemStack[] { slots[1], slots[3], slots[4] });
        

        ItemStack itemstack = recipe.getResult();
        
        if (slots[2] == null) {
          slots[2] = itemstack.func_77946_l();
        } else if (slots[2].func_77969_a(itemstack)) {
          slots[2].field_77994_a += field_77994_a;
        }
        
        slots[0].field_77994_a -= fibre.field_77994_a;
        
        if (slots[0].field_77994_a <= 0) {
          slots[0] = null;
        }
        
        ArrayList<ItemStack> available = recipe.getMutableModifiersList();
        
        updateIfContained(available, 1);
        updateIfContained(available, 3);
        updateIfContained(available, 4);
      }
    }
    
    private void updateIfContained(ArrayList<ItemStack> available, int slot) {
      if (slots[slot] != null) {
        for (int i = 0; i < available.size(); i++) {
          if (((ItemStack)available.get(i)).func_77969_a(slots[slot])) {
            slots[slot].field_77994_a -= 1;
            
            if (slots[slot].field_77994_a <= 0) {
              slots[slot] = null;
            }
            
            available.remove(i);
            return;
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
      if (slot == 2) {
        return false;
      }
      return true;
    }
    

    private static final int SLOT_TO_SPIN = 0;
    
    private static final int SLOT_SPUN = 2;
    private static final int SLOT_FUEL = 1;
    private static final int SLOT_BY_PRODUCT = 3;
    private static final int SLOT_JARS = 4;
    private static final int[] slots_top = { 0 };
    private static final int[] slots_bottom = { 4, 1, 3 };
    private static final int[] slots_sides = { 3, 2, 4, 1 };
    
    public int[] func_94128_d(int side)
    {
      return BlockSide.TOP.isEqual(side) ? slots_top : BlockSide.BOTTOM.isEqual(side) ? slots_bottom : slots_sides;
    }
    
    public boolean func_102007_a(int slot, ItemStack itemstack, int par3)
    {
      return func_94041_b(slot, itemstack);
    }
    
    public boolean func_102008_b(int slot, ItemStack stack, int side)
    {
      if (BlockSide.TOP.isEqual(side)) {
        return false;
      }
      
      if (BlockSide.BOTTOM.isEqual(side)) {
        return false;
      }
      
      return slot == 2;
    }
  }
  

  public static class ContainerSpinningWheel
    extends Container
  {
    private BlockSpinningWheel.TileEntitySpinningWheel furnace;
    
    private int lastCookTime;
    
    private int lastPowerLevel;
    
    public ContainerSpinningWheel(InventoryPlayer par1InventoryPlayer, BlockSpinningWheel.TileEntitySpinningWheel par2TileEntityFurnace)
    {
      furnace = par2TileEntityFurnace;
      func_75146_a(new Slot(par2TileEntityFurnace, 0, 56, 20));
      func_75146_a(new Slot(par2TileEntityFurnace, 1, 56, 53));
      func_75146_a(new SlotFurnace(field_70458_d, par2TileEntityFurnace, 2, 118, 21));
      func_75146_a(new Slot(par2TileEntityFurnace, 3, 74, 53));
      func_75146_a(new Slot(par2TileEntityFurnace, 4, 92, 53));
      

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
        
        if (slotIndex == 2) {
          if (!func_75135_a(itemstack1, 5, 41, true)) {
            return null;
          }
          
          slot.func_75220_a(itemstack1, itemstack);
        } else if ((slotIndex != 1) && (slotIndex != 0) && (slotIndex != 4) && (slotIndex != 3))
        {
          if ((SpinningRecipes.instance().findRecipeUsingFibre(itemstack1) != null) && ((furnace.func_70301_a(0) == null) || (furnace.func_70301_a(0).func_77969_a(itemstack1))))
          {
            if (!func_75135_a(itemstack1, 0, 1, false)) {
              return null;
            }
          } else if (SpinningRecipes.instance().findRecipeUsing(itemstack1) != null) {
            if ((!func_75135_a(itemstack1, 1, 2, false)) && 
              (!func_75135_a(itemstack1, 3, 4, false)) && 
              (!func_75135_a(itemstack1, 4, 5, false))) {
              return null;
            }
            
          }
          else if ((slotIndex >= 5) && (slotIndex < 32)) {
            if (!func_75135_a(itemstack1, 32, 41, false)) {
              return null;
            }
          } else if ((slotIndex >= 32) && (slotIndex < 41) && (!func_75135_a(itemstack1, 5, 32, false))) {
            return null;
          }
        } else if (!func_75135_a(itemstack1, 5, 41, false)) {
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
