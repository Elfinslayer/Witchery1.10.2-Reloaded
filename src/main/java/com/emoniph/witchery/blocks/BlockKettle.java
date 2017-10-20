package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.common.IPowerSource;
import com.emoniph.witchery.common.PowerSources;
import com.emoniph.witchery.common.PowerSources.RelativePowerSource;
import com.emoniph.witchery.crafting.KettleRecipes;
import com.emoniph.witchery.crafting.KettleRecipes.KettleRecipe;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import com.emoniph.witchery.item.ItemWitchesClothes;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;

public class BlockKettle extends BlockBaseContainer
{
  static final int POWER_SOURCE_RADIUS = 16;
  
  public BlockKettle()
  {
    super(Material.field_151574_g, TileEntityKettle.class);
    
    func_149711_c(2.0F);
    func_149672_a(field_149777_j);
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
  }
  
  public AxisAlignedBB func_149668_a(World world, int x, int y, int z)
  {
    float f = 0.0625F;
    return AxisAlignedBB.func_72330_a(x + f, y, z + f, x + 1 - f, y + 1 - f, z + 1 - f);
  }
  

  @SideOnly(Side.CLIENT)
  public AxisAlignedBB func_149633_g(World par1World, int par2, int par3, int par4)
  {
    return super.func_149633_g(par1World, par2, par3, par4);
  }
  
  public boolean func_149662_c()
  {
    return false;
  }
  
  public boolean func_149686_d()
  {
    return false;
  }
  
  public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5)
  {
    func_111046_k(par1World, par2, par3, par4);
  }
  
  private boolean func_111046_k(World par1World, int par2, int par3, int par4) {
    if (!func_149718_j(par1World, par2, par3, par4)) {
      par1World.func_147468_f(par2, par3, par4);
      return false;
    }
    return true;
  }
  

  public boolean func_149718_j(World world, int x, int y, int z)
  {
    return true;
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
  

  @SideOnly(Side.CLIENT)
  public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
  {
    return false;
  }
  



  private static IPowerSource findNewPowerSource(World world, int posX, int posY, int posZ)
  {
    List<PowerSources.RelativePowerSource> sources = PowerSources.instance() != null ? PowerSources.instance().get(world, new com.emoniph.witchery.util.Coord(posX, posY, posZ), 16) : null;
    
    return (sources != null) && (sources.size() > 0) ? ((PowerSources.RelativePowerSource)sources.get(0)).source() : null;
  }
  
  private static ItemStack consumeItem(ItemStack stack) {
    if (field_77994_a == 1) {
      if (stack.func_77973_b().hasContainerItem(stack)) {
        return stack.func_77973_b().getContainerItem(stack);
      }
      return null;
    }
    
    stack.func_77979_a(1);
    
    return stack;
  }
  

  @SideOnly(Side.CLIENT)
  public void func_149734_b(World world, int x, int y, int z, Random rand)
  {
    TileEntityKettle tileEntity = (TileEntityKettle)world.func_147438_o(x, y, z);
    if (tileEntity != null) {
      double d0 = x + 0.45F;
      double d1 = y + 0.4F;
      double d2 = z + 0.5F;
      if (tileEntity.isRuined()) {
        world.func_72869_a(ParticleEffect.LARGE_SMOKE.toString(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
      } else if (tileEntity.isReady()) {
        world.func_72869_a(ParticleEffect.SLIME.toString(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
        if (isPowered) {
          world.func_72869_a(ParticleEffect.SPELL.toString(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
        } else {
          world.func_72869_a(ParticleEffect.MOB_SPELL.toString(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
      }
      else if (tileEntity.isBrewing()) {
        world.func_72869_a(ParticleEffect.MOB_SPELL.toString(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
      }
    }
  }
  
  public boolean tryFillWith(World world, int x, int y, int z, FluidStack fluidStack) {
    if (field_72995_K) {
      return true;
    }
    TileEntity tile = world.func_147438_o(x, y, z);
    if ((tile == null) || (!(tile instanceof TileEntityKettle))) {
      return false;
    }
    
    TileEntityKettle tank = (TileEntityKettle)tile;
    
    if ((tank != null) && (tank.canFill(ForgeDirection.UNKNOWN, fluidStack.getFluid()))) {
      int qty = tank.fill(ForgeDirection.UNKNOWN, fluidStack, true);
      amount -= qty;
      if (amount < 0) {
        amount = 0;
      }
      if (qty > 0) {
        world.func_147471_g(x, y, z);
        SoundEffect.WATER_SWIM.playAt(world, x, y, z);
      }
      return qty > 0;
    }
    return false;
  }
  
  public boolean func_149727_a(World world, int posX, int posY, int posZ, EntityPlayer player, int par6, float par7, float par8, float par9)
  {
    ItemStack current = field_71071_by.func_70448_g();
    if (current != null) {
      TileEntity tile = world.func_147438_o(posX, posY, posZ);
      if ((tile == null) || (!(tile instanceof TileEntityKettle))) {
        return false;
      }
      
      TileEntityKettle tank = (TileEntityKettle)tile;
      
      if ((current.func_77973_b() == Items.field_151069_bo) && (tank.isReady()))
      {
        if (KettleRecipes.instance().isBrewableBy(furnaceItemStacks[6], player))
        {
          ItemStack itemstack1 = null;
          try {
            tank.setConsumeBottle(false);
            itemstack1 = tank.func_70298_a(6, 1);
          } finally {
            tank.setConsumeBottle(true);
          }
          double bonusChance = 0.0D;
          double bonusChance2 = 0.0D;
          if ((field_71071_by.func_70440_f(3) != null) && (field_71071_by.func_70440_f(3).func_77973_b() == ItemsWITCH_HAT)) {
            bonusChance += 0.35D;
          } else if ((field_71071_by.func_70440_f(3) != null) && (field_71071_by.func_70440_f(3).func_77973_b() == ItemsBABAS_HAT)) {
            bonusChance += 0.25D;
            bonusChance2 += 0.25D;
          }
          
          if ((!ItemsGENERIC.itemBrewOfRaising.isMatch(itemstack1)) && (ItemsWITCH_ROBES.isRobeWorn(player))) {
            bonusChance += 0.35D;
          } else if ((ItemsGENERIC.itemBrewOfRaising.isMatch(itemstack1)) && (ItemsNECROMANCERS_ROBES.isRobeWorn(player))) {
            bonusChance += 0.35D;
          }
          
          if (com.emoniph.witchery.familiar.Familiar.hasActiveBrewMasteryFamiliar(player)) {
            bonusChance += 0.05D;
            if ((field_71071_by.func_70440_f(3) != null) && (field_71071_by.func_70440_f(3).func_77973_b() == ItemsBABAS_HAT)) {
              bonusChance2 += 0.05D;
            }
          }
          
          if ((bonusChance > 0.0D) && (field_73012_v.nextDouble() <= bonusChance)) {
            field_77994_a += KettleRecipes.instance().getHatBonus(itemstack1);
          }
          
          if ((bonusChance2 > 0.0D) && (field_73012_v.nextDouble() <= bonusChance2)) {
            field_77994_a += KettleRecipes.instance().getHatBonus(itemstack1);
          }
          
          if (!field_72995_K) {
            if (field_77994_a == 1) {
              field_71071_by.func_70299_a(field_71071_by.field_70461_c, itemstack1);
              if ((player instanceof EntityPlayerMP)) {
                ((EntityPlayerMP)player).func_71120_a(field_71069_bz);
              }
            } else {
              if (!field_71071_by.func_70441_a(itemstack1)) {
                world.func_72838_d(new EntityItem(world, posX + 0.5D, posY + 1.5D, posZ + 0.5D, itemstack1));
              } else if ((player instanceof EntityPlayerMP)) {
                ((EntityPlayerMP)player).func_71120_a(field_71069_bz);
              }
              
              field_77994_a -= 1;
              
              if (field_77994_a <= 0) {
                field_71071_by.func_70299_a(field_71071_by.field_70461_c, (ItemStack)null);
              }
            }
          }
          

          SoundEffect.WATER_SWIM.playAtPlayer(world, player);
        }
        
        return true;
      }
      
      FluidStack liquid = FluidContainerRegistry.getFluidForFilledItem(current);
      
      if (liquid != null) {
        if (tank.canFill(ForgeDirection.UNKNOWN, liquid.getFluid())) {
          int qty = tank.fill(ForgeDirection.UNKNOWN, liquid, true);
          
          if ((qty != 0) && (!field_71075_bZ.field_75098_d)) {
            field_71071_by.func_70299_a(field_71071_by.field_70461_c, consumeItem(current));
          }
          tank.reset(false);
          SoundEffect.WATER_SWIM.playAtPlayer(world, player);
        }
        
        return true; }
      if (current.func_77973_b() == ItemsBREW_ENDLESS_WATER) {
        if (tryFillWith(world, posX, posY, posZ, new FluidStack(FluidRegistry.WATER, 1000))) {
          current.func_77972_a(1, player);
        }
        return true;
      }
      FluidStack available = getTankInfoUNKNOWN0fluid;
      if (available != null) {
        ItemStack filled = FluidContainerRegistry.fillFluidContainer(available, current);
        
        liquid = FluidContainerRegistry.getFluidForFilledItem(filled);
        
        if (liquid != null) {
          if (!field_71075_bZ.field_75098_d) {
            if (field_77994_a > 1) {
              if (!field_71071_by.func_70441_a(filled)) {
                return false;
              }
              field_71071_by.func_70299_a(field_71071_by.field_70461_c, consumeItem(current));
            }
            else {
              field_71071_by.func_70299_a(field_71071_by.field_70461_c, consumeItem(current));
              field_71071_by.func_70299_a(field_71071_by.field_70461_c, filled);
            }
          }
          tank.drain(ForgeDirection.UNKNOWN, amount, true);
          tank.reset(false);
          SoundEffect.WATER_SWIM.playAtPlayer(world, player);
          return true;
        }
      }
    }
    


    return false;
  }
  
  public void func_149670_a(World world, int posX, int posY, int posZ, Entity entity)
  {
    if ((!field_72995_K) && ((entity instanceof EntityItem))) {
      TileEntityKettle tileEntity = (TileEntityKettle)world.func_147438_o(posX, posY, posZ);
      if (tileEntity != null) {
        EntityItem itemEntity = (EntityItem)entity;
        if (itemEntity.func_92059_d().func_77973_b() == Items.field_151069_bo)
        {
          ItemStack stack = tileEntity.func_70301_a(7);
          if (stack == null) {
            tileEntity.func_70299_a(7, itemEntity.func_92059_d());
            itemEntity.func_70106_y();
          } else if (field_77994_a + func_92059_dfield_77994_a <= tileEntity.func_70297_j_()) {
            field_77994_a += func_92059_dfield_77994_a;
            tileEntity.func_70299_a(7, stack);
            itemEntity.func_70106_y();
          }
        } else if (tileEntity.isFilled())
        {
          boolean spaceFound = false;
          for (int i = 0; i < tileEntity.func_70302_i_() - 2; i++) {
            if (tileEntity.func_70301_a(i) == null) {
              tileEntity.func_70299_a(i, itemEntity.func_92059_d());
              spaceFound = true;
              break;
            }
          }
          
          if ((!spaceFound) && (!tileEntity.isRuined())) {
            tileEntity.setRuined();
          }
          
          itemEntity.func_70106_y();
          ParticleEffect.SPLASH.send(SoundEffect.WATER_SPLASH, world, posX + 0.5D, posY + 0.2D, posZ + 0.5D, 0.5D, 0.5D, 5);
        }
      }
    }
  }
  
  public static class TileEntityKettle extends TileEntityBase implements net.minecraft.inventory.ISidedInventory, net.minecraftforge.fluids.IFluidHandler {
    private static final int RESULT_SLOT = 6;
    private static final int BOTTLE_SLOT = 7;
    private ItemStack[] furnaceItemStacks = new ItemStack[8];
    private boolean isRuined = false;
    private boolean isPowered = false;
    private int color;
    
    public TileEntityKettle() {}
    
    public void func_145845_h() { super.func_145845_h();
      
      if ((!field_145850_b.field_72995_K) && 
        (!isRuined) && (ticks % 20L == 0L) && (isFilled()) && ((someFilled()) || (furnaceItemStacks[6] != null))) {
        boolean sendPacket = false;
        if (field_145850_b.func_147439_a(field_145851_c, field_145848_d - 1, field_145849_e).func_149688_o() != Material.field_151581_o) {
          isRuined = true;
          color = 0;
          furnaceItemStacks[6] = null;
        } else if (furnaceItemStacks[6] == null) {
          if (allFilled()) {
            KettleRecipes.KettleRecipe recipe = KettleRecipes.instance().getResult(furnaceItemStacks, furnaceItemStacks.length - 2, false, field_145850_b);
            
            if (recipe == null) {
              color = 0;
              isRuined = true;
              furnaceItemStacks[6] = null;
            } else {
              color = recipe.getColor();
              boolean wasPowered = isPowered;
              float powerNeeded = recipe.getRequiredPower();
              if (powerNeeded == 0.0F) {
                isPowered = true;
              } else {
                IPowerSource powerSource = BlockKettle.findNewPowerSource(field_145850_b, field_145851_c, field_145848_d, field_145849_e);
                isPowered = ((powerSource != null) && (powerSource.consumePower(powerNeeded)));
              }
              
              if (isPowered) {
                furnaceItemStacks[6] = recipe.getOutput(null, true);
                for (int i = 0; i < furnaceItemStacks.length - 2; i++) {
                  furnaceItemStacks[i] = null;
                }
              }
              
              sendPacket = (isPowered) || (wasPowered != isPowered);
            }
          } else {
            KettleRecipes.KettleRecipe recipe = KettleRecipes.instance().getResult(furnaceItemStacks, furnaceItemStacks.length - 2, true, field_145850_b);
            if ((recipe == null) || (recipe.getColor() == 0)) {
              color = 0;
              isRuined = true;
              furnaceItemStacks[6] = null;
            } else if (recipe.getColor() != color) {
              color = recipe.getColor();
              sendPacket = true;
            }
            
            if (!isRuined) {
              boolean wasPowered = isPowered;
              float powerNeeded = recipe.getRequiredPower();
              if (powerNeeded == 0.0F) {
                isPowered = true;
              } else {
                IPowerSource powerSource = BlockKettle.findNewPowerSource(field_145850_b, field_145851_c, field_145848_d, field_145849_e);
                isPowered = ((powerSource != null) && (powerSource.getCurrentPower() >= powerNeeded));
              }
              sendPacket = wasPowered != isPowered;
            }
          }
        }
        
        if ((isRuined) || (sendPacket)) {
          field_145850_b.func_147471_g(field_145851_c, field_145848_d, field_145849_e);
        }
      }
    }
    
    public void reset(boolean flushWater)
    {
      if (!field_145850_b.field_72995_K) {
        Log.instance().debug(String.format("Reset kettle %s", new Object[] { flushWater ? "Flush" : "No Flush" }));
        if (flushWater) {
          FluidStack drained = tank.drain(tank.getFluidAmount(), true);
          Log.instance().debug(String.format("Drained %d remaining %d of  %d", new Object[] { Integer.valueOf(amount), Integer.valueOf(tank.getFluidAmount()), Integer.valueOf(tank.getCapacity()) }));
        }
        isRuined = false;
        isPowered = false;
        for (int i = 0; i < furnaceItemStacks.length - 1; i++) {
          furnaceItemStacks[i] = null;
        }
        field_145850_b.func_147471_g(field_145851_c, field_145848_d, field_145849_e);
      }
    }
    
    public boolean allFilled() {
      for (int i = 0; i < furnaceItemStacks.length - 2; i++) {
        if (furnaceItemStacks[i] == null) {
          return false;
        }
      }
      return true;
    }
    
    public boolean someFilled() {
      for (int i = 0; i < furnaceItemStacks.length - 2; i++) {
        if (furnaceItemStacks[i] != null) {
          return true;
        }
      }
      return false;
    }
    
    public int func_70302_i_()
    {
      return furnaceItemStacks.length;
    }
    
    public boolean func_94041_b(int slot, ItemStack itemstack)
    {
      Log.instance().debug(String.format("isItemValidForSlot(%d, %s)", new Object[] { Integer.valueOf(slot), itemstack.toString() }));
      ItemStack stackInSlot = func_70301_a(slot);
      if (slot == 6)
        return true;
      if (slot == 7) {
        if (itemstack.func_77973_b() == Items.field_151069_bo) {} return (stackInSlot != null ? field_77994_a : 0) + field_77994_a <= func_70297_j_();
      }
      if (func_70301_a(6) == null) {} return (stackInSlot != null ? field_77994_a : 0) + field_77994_a <= func_70297_j_();
    }
    

    private static final int[] side_slots = { 0, 1, 2, 3, 4, 5, 6, 7 };
    
    public int[] func_94128_d(int var1)
    {
      return side_slots;
    }
    
    public boolean func_102007_a(int slot, ItemStack stack, int side)
    {
      ItemStack stackInSlot = func_70301_a(slot);
      if (slot == 6)
        return false;
      if (slot == 7) {
        if (stack.func_77973_b() == Items.field_151069_bo) {} return (stackInSlot != null ? field_77994_a : 0) + field_77994_a <= func_70297_j_();
      }
      return (stack.func_77973_b() != Items.field_151069_bo) && (func_70301_a(6) == null) && (isFilled());
    }
    

    private int lastExtractionQuantity = 0;
    
    public boolean func_102008_b(int slot, ItemStack stack, int side)
    {
      Log.instance().debug(String.format("canExtract(%d, %s, %d)", new Object[] { Integer.valueOf(slot), stack.toString(), Integer.valueOf(side) }));
      ItemStack bottles = func_70301_a(7);
      
      boolean canExtract = (slot == 6) && (isFilled()) && (isReady()) && (bottles != null) && (field_77994_a >= field_77994_a);
      
      if (canExtract) {
        if (!KettleRecipes.instance().isBrewableBy(stack, null)) {
          return false;
        }
        lastExtractionQuantity = field_77994_a;
      }
      return canExtract;
    }
    
    public int getLiquidColor() {
      return color;
    }
    
    public ItemStack func_70301_a(int par1)
    {
      return furnaceItemStacks[par1];
    }
    
    public void func_70299_a(int slot, ItemStack stack)
    {
      Log.instance().debug("setInventorySlotContents");
      
      if ((slot == 6) && 
        (consumeBottles)) {
        ItemStack resultStack = func_70301_a(6);
        ItemStack bottleStack = func_70301_a(7);
        
        if ((stack == null) && (resultStack != null) && (bottleStack != null)) {
          field_77994_a -= field_77994_a;
          if (field_77994_a <= 0) {
            furnaceItemStacks[7] = null;
          }
        } else if ((stack != null) && (resultStack != null) && (bottleStack != null)) {
          int reduction = field_77994_a - field_77994_a;
          if (reduction == 0) {
            reduction = lastExtractionQuantity;
          }
          lastExtractionQuantity = 0;
          Log.instance().debug(String.format("bottles; %d %s %s", new Object[] { Integer.valueOf(reduction), stack.toString(), resultStack.toString() }));
          field_77994_a -= reduction;
          if (field_77994_a <= 0) {
            furnaceItemStacks[7] = null;
          }
        }
      }
      

      furnaceItemStacks[slot] = stack;
      if ((stack != null) && (field_77994_a > func_70297_j_())) {
        field_77994_a = func_70297_j_();
      }
      else if ((stack == null) && (slot == 6)) {
        reset(true);
        return;
      }
      if (!field_145850_b.field_72995_K) {
        field_145850_b.func_147471_g(field_145851_c, field_145848_d, field_145849_e);
      }
    }
    
    private boolean consumeBottles = true;
    
    public void setConsumeBottle(boolean consume) { consumeBottles = consume; }
    

    public ItemStack func_70298_a(int slot, int quantity)
    {
      Log.instance().debug("decrStackSize");
      if (furnaceItemStacks[slot] != null)
      {

        ItemStack bottles = func_70301_a(7);
        if ((consumeBottles) && 
          (bottles != null)) {
          field_77994_a -= quantity;
        }
        
        if ((bottles != null) && (field_77994_a <= 0)) {
          furnaceItemStacks[7] = null;
        }
        
        if (furnaceItemStacks[slot].field_77994_a <= quantity) {
          ItemStack itemstack = furnaceItemStacks[slot];
          furnaceItemStacks[slot] = null;
          if (slot == 6) {
            reset(true);
          }
          else if (!field_145850_b.field_72995_K) {
            field_145850_b.func_147471_g(field_145851_c, field_145848_d, field_145849_e);
          }
          
          return itemstack;
        }
        ItemStack itemstack = furnaceItemStacks[slot].func_77979_a(quantity);
        
        if (furnaceItemStacks[slot].field_77994_a == 0) {
          furnaceItemStacks[slot] = null;
          if (slot == 6) {
            reset(true);
          }
          else if (!field_145850_b.field_72995_K) {
            field_145850_b.func_147471_g(field_145851_c, field_145848_d, field_145849_e);
          }
          
        }
        else if (!field_145850_b.field_72995_K) {
          field_145850_b.func_147471_g(field_145851_c, field_145848_d, field_145849_e);
        }
        

        return itemstack;
      }
      
      return null;
    }
    

    public ItemStack func_70304_b(int par1)
    {
      Log.instance().debug("getStackInSlotOnClosing");
      if (furnaceItemStacks[par1] != null) {
        ItemStack itemstack = furnaceItemStacks[par1];
        furnaceItemStacks[par1] = null;
        if (par1 == 6) {
          reset(true);
        }
        else if (!field_145850_b.field_72995_K) {
          field_145850_b.func_147471_g(field_145851_c, field_145848_d, field_145849_e);
        }
        
        return itemstack;
      }
      return null;
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
      if (tank.getFluidAmount() > 0) {
        tank.drain(tank.getFluidAmount(), true);
      }
      tank.readFromNBT(par1NBTTagCompound);
      
      NBTTagList nbttaglist = par1NBTTagCompound.func_150295_c("Items", 10);
      furnaceItemStacks = new ItemStack[func_70302_i_()];
      
      for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
        NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
        byte b0 = nbttagcompound1.func_74771_c("Slot");
        
        if ((b0 >= 0) && (b0 < furnaceItemStacks.length)) {
          furnaceItemStacks[b0] = ItemStack.func_77949_a(nbttagcompound1);
        }
      }
      
      isRuined = par1NBTTagCompound.func_74767_n("Ruined");
      isPowered = par1NBTTagCompound.func_74767_n("Powered");
      color = par1NBTTagCompound.func_74762_e("LiquidColor");
    }
    
    public void func_145841_b(NBTTagCompound par1NBTTagCompound)
    {
      super.func_145841_b(par1NBTTagCompound);
      par1NBTTagCompound.func_74757_a("Ruined", isRuined);
      par1NBTTagCompound.func_74757_a("Powered", isPowered);
      par1NBTTagCompound.func_74768_a("LiquidColor", color);
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
      tank.writeToNBT(par1NBTTagCompound);
    }
    
    public int func_70297_j_()
    {
      return 64;
    }
    


    public void func_70295_k_() {}
    

    public void func_70305_f() {}
    

    public boolean func_70300_a(EntityPlayer par1EntityPlayer)
    {
      return field_145850_b.func_147438_o(field_145851_c, field_145848_d, field_145849_e) == this;
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
    
    public boolean isFilled() {
      return tank.getFluidAmount() == tank.getCapacity();
    }
    
    public boolean isBrewing() {
      return (isFilled()) && (someFilled()) && (!isRuined());
    }
    
    public boolean isReady() {
      return (!isRuined()) && (furnaceItemStacks[6] != null);
    }
    
    public boolean isRuined() {
      return isRuined;
    }
    
    public void setRuined() {
      isRuined = true;
      field_145850_b.func_147471_g(field_145851_c, field_145848_d, field_145849_e);
    }
    
    private FluidTank tank = new FluidTank(1000);
    
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
    {
      int result = tank.fill(resource, doFill);
      
      return result;
    }
    
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
    {
      if ((resource == null) || (!resource.isFluidEqual(tank.getFluid()))) {
        return null;
      }
      return tank.drain(amount, doDrain);
    }
    
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
    {
      return tank.drain(maxDrain, doDrain);
    }
    
    public boolean canFill(ForgeDirection from, Fluid fluid)
    {
      if (fluid == null) {
        return false;
      }
      return fluid.getName().equals(FluidRegistry.WATER.getName());
    }
    
    public boolean canDrain(ForgeDirection from, Fluid fluid)
    {
      if (fluid == null) {
        return false;
      }
      return fluid.getName().equals(FluidRegistry.WATER.getName());
    }
    
    public FluidTankInfo[] getTankInfo(ForgeDirection from)
    {
      return new FluidTankInfo[] { tank.getInfo() };
    }
    
    public int bottleCount() {
      ItemStack stack = func_70301_a(7);
      return stack != null ? field_77994_a : 0;
    }
  }
}
