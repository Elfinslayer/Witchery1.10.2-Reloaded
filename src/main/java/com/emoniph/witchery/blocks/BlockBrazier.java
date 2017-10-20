package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.common.IPowerSource;
import com.emoniph.witchery.common.PowerSources;
import com.emoniph.witchery.common.PowerSources.RelativePowerSource;
import com.emoniph.witchery.crafting.BrazierRecipes;
import com.emoniph.witchery.crafting.BrazierRecipes.BrazierRecipe;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import com.emoniph.witchery.util.BlockSide;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBrazier extends BlockBaseContainer
{
  public BlockBrazier()
  {
    super(Material.field_151573_f, TileEntityBrazier.class);
    func_149711_c(3.5F);
    func_149672_a(field_149777_j);
    func_149676_a(0.2F, 0.0F, 0.2F, 0.8F, 0.95F, 0.8F);
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
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149734_b(World world, int x, int y, int z, Random rand)
  {
    TileEntity tile = world.func_147438_o(x, y, z);
    if ((tile != null) && ((tile instanceof TileEntityBrazier))) {
      TileEntityBrazier brazier = (TileEntityBrazier)tile;
      if (brazier.isBurning()) {
        double d0 = x + 0.4F + rand.nextInt(3) * 0.1F;
        double d1 = y + 1.1F + rand.nextInt(2) * 0.1F;
        double d2 = z + 0.4F + rand.nextInt(3) * 0.1F;
        world.func_72869_a(ParticleEffect.FLAME.toString(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
      }
    }
  }
  
  public void func_149749_a(World world, int x, int y, int z, Block oldBlockID, int oldBlockMetadata)
  {
    TileEntity tile = world.func_147438_o(x, y, z);
    if ((tile != null) && ((tile instanceof TileEntityBrazier))) {
      TileEntityBrazier brazier = (TileEntityBrazier)tile;
      if (!brazier.isBurning()) {
        for (int j1 = 0; j1 < brazier.func_70302_i_(); j1++) {
          ItemStack itemstack = brazier.func_70301_a(j1);
          
          dropItemFromBrokenBlock(world, x, y, z, itemstack);
          
          world.func_147453_f(x, y, z, oldBlockID);
        }
      } else {
        dropItemFromBrokenBlock(world, x, y, z, ItemsGENERIC.itemAshWood.createStack());
        
        world.func_147453_f(x, y, z, oldBlockID);
      }
    }
    
    super.func_149749_a(world, x, y, z, oldBlockID, oldBlockMetadata);
  }
  
  private void dropItemFromBrokenBlock(World world, int x, int y, int z, ItemStack itemstack) {
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
  
  public boolean func_149740_M()
  {
    return true;
  }
  
  public int func_149736_g(World world, int x, int y, int z, int side)
  {
    return Container.func_94526_b((IInventory)world.func_147438_o(x, y, z));
  }
  

  public boolean func_149727_a(World world, int posX, int posY, int posZ, EntityPlayer player, int par6, float par7, float par8, float par9)
  {
    if (field_72995_K) {
      return true;
    }
    TileEntity tile = world.func_147438_o(posX, posY, posZ);
    if ((tile != null) && ((tile instanceof TileEntityBrazier))) {
      TileEntityBrazier brazier = (TileEntityBrazier)tile;
      ItemStack stack = player.func_70694_bm();
      if (stack == null) {
        return false;
      }
      
      if ((stack.func_77973_b() == Items.field_151068_bn) && (stack.func_77960_j() == 0)) {
        if (!brazier.isEmpty()) {
          brazier.reset();
          if ((!field_71075_bZ.field_75098_d) && (field_71071_by != null)) {
            field_71071_by.func_70299_a(field_71071_by.field_70461_c, new ItemStack(Items.field_151069_bo));
            if ((player instanceof EntityPlayerMP)) {
              ((EntityPlayerMP)player).func_71120_a(field_71069_bz);
            }
          }
          SoundEffect.WATER_SPLASH.playAtPlayer(world, player);
        }
      } else if (stack.func_77973_b() == Items.field_151131_as) {
        if (!brazier.isEmpty()) {
          brazier.reset();
          if ((!field_71075_bZ.field_75098_d) && (field_71071_by != null)) {
            field_71071_by.func_70299_a(field_71071_by.field_70461_c, new ItemStack(Items.field_151133_ar));
            if ((player instanceof EntityPlayerMP)) {
              ((EntityPlayerMP)player).func_71120_a(field_71069_bz);
            }
          }
          SoundEffect.WATER_SPLASH.playAtPlayer(world, player);
        }
      } else { if (stack.func_77973_b() == Items.field_151033_d) {
          if (!brazier.isEmpty()) {
            brazier.begin();
          }
          return false;
        }
        boolean added = false;
        for (int i = 0; i < brazier.func_70302_i_() - 1; i++) {
          if (brazier.func_70301_a(i) == null) {
            if ((!field_71075_bZ.field_75098_d) && (field_71071_by != null)) {
              ItemStack newStack = stack.func_77979_a(1);
              brazier.func_70299_a(i, newStack);
              if (field_77994_a == 0) {
                field_71071_by.func_70299_a(field_71071_by.field_70461_c, null);
              }
            } else {
              brazier.func_70299_a(i, new ItemStack(stack.func_77973_b(), 1, stack.func_77960_j()));
            }
            added = true;
            break;
          }
        }
      }
    }
    
    return true;
  }
  
  public static void tryIgnite(World world, int x, int y, int z)
  {
    TileEntity tile = world.func_147438_o(x, y, z);
    if ((tile != null) && ((tile instanceof TileEntityBrazier))) {
      TileEntityBrazier brazier = (TileEntityBrazier)tile;
      if (!brazier.isEmpty()) {
        brazier.begin();
      }
    }
  }
  

  public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5)
  {
    boolean flag = par1World.func_72864_z(par2, par3, par4);
    TileEntity tile = par1World.func_147438_o(par2, par3, par4);
    if ((tile != null) && ((tile instanceof TileEntityBrazier))) {
      TileEntityBrazier brazier = (TileEntityBrazier)tile;
      if ((previousRedstoneState != flag) && 
        (flag) && (!brazier.isEmpty())) {
        brazier.begin();
      }
      

      previousRedstoneState = flag;
    }
  }
  



  public static class TileEntityBrazier
    extends TileEntityBase
    implements net.minecraft.inventory.ISidedInventory
  {
    private ItemStack[] slots = new ItemStack[4];
    private int furnaceCookTime = 0;
    public boolean previousRedstoneState;
    
    public TileEntityBrazier() {}
    
    public int func_70302_i_() { return slots.length; }
    
    public void begin()
    {
      func_70299_a(3, ItemsGENERIC.itemAshWood.createStack());
    }
    
    public ItemStack func_70301_a(int slot)
    {
      return slots[slot];
    }
    
    public boolean isBurning() {
      for (int i = 0; i < func_70302_i_(); i++) {
        if (func_70301_a(i) == null) {
          return false;
        }
      }
      return true;
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
    
    public boolean isFull()
    {
      if (func_70301_a(3) != null) {
        return true;
      }
      
      for (int slot = 0; slot < 3; slot++) {
        if (func_70301_a(slot) == null) {
          return false;
        }
      }
      return true;
    }
    
    public boolean isEmpty() {
      for (int slot = 0; slot < 3; slot++) {
        if (func_70301_a(slot) != null) {
          return false;
        }
      }
      return true;
    }
    
    public int getIngredientCount() {
      int count = 0;
      for (int slot = 0; slot < 3; slot++) {
        if (func_70301_a(slot) != null) {
          count++;
        }
      }
      return count;
    }
    
    public void reset() {
      for (int slot = 0; slot < func_70302_i_(); slot++) {
        func_70299_a(slot, null);
      }
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
      func_70296_d();
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
      storage = nbtRoot.func_74763_f("PowerStorage");
    }
    
    public void func_145841_b(NBTTagCompound nbtRoot)
    {
      super.func_145841_b(nbtRoot);
      nbtRoot.func_74777_a("CookTime", (short)furnaceCookTime);
      nbtRoot.func_74777_a("PowerLevel", (short)powerLevel);
      nbtRoot.func_74772_a("PowerStorage", storage);
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
    

    private IPowerSource getPowerSource()
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
    

    private Coord powerSourceCoord;
    private IPowerSource findNewPowerSource()
    {
      List<PowerSources.RelativePowerSource> sources = PowerSources.instance() != null ? PowerSources.instance().get(field_145850_b, new Coord(this), 16) : null;
      
      return (sources != null) && (sources.size() > 0) ? ((PowerSources.RelativePowerSource)sources.get(0)).source() : null;
    }
    

    private static final int POWER_SOURCE_RADIUS = 16;
    
    private static final float POWER_PER_TICK = 1.0F;
    public int powerLevel;
    public void func_145845_h()
    {
      super.func_145845_h();
      
      boolean update = false;
      boolean cooking = furnaceCookTime > 0;
      
      if (!field_145850_b.field_72995_K) {
        boolean powered = powerLevel > 0;
        BrazierRecipes.BrazierRecipe recipe = BrazierRecipes.instance().getRecipe(new ItemStack[] { slots[0], slots[1], slots[2] });
        
        if ((recipe != null) && (func_70301_a(3) != null)) {
          IPowerSource powerSource = getPowerSource();
          if ((powerSource != null) && (!powerSource.isLocationEqual(powerSourceCoord))) {
            powerSourceCoord = powerSource.getLocation();
          } else {
            powerSourceCoord = null;
          }
          
          boolean needsPower = recipe.getNeedsPower();
          powerLevel = ((needsPower) && (powerSource == null) ? 0 : 1);
          
          if ((!recipe.getNeedsPower()) || ((powerSource != null) && (powerSource.consumePower(1.0F)))) {
            update = furnaceCookTime == 0;
            furnaceCookTime += 1;
            if (furnaceCookTime == burnTicks + storage * 400L) {
              furnaceCookTime = 0;
              recipe.onBurnt(field_145850_b, field_145851_c, field_145848_d, field_145849_e, ticks, this);
              func_70299_a(0, null);
              func_70299_a(1, null);
              func_70299_a(2, null);
              update = true;
            } else {
              storage += recipe.onBurning(field_145850_b, field_145851_c, field_145848_d, field_145849_e, ticks, this);
              if (storage == Long.MAX_VALUE) {
                storage = 0L;
              }
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
          if (func_70301_a(3) != null) {
            reset();
            ParticleEffect.SMOKE.send(SoundEffect.RANDOM_FIZZ, field_145850_b, 0.5D + field_145851_c, 1.0D + field_145848_d, 0.5D + field_145849_e, 0.5D, 0.5D, 8);
          }
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
    
    public boolean func_70300_a(EntityPlayer par1EntityPlayer)
    {
      return field_145850_b.func_147438_o(field_145851_c, field_145848_d, field_145849_e) == this;
    }
    


    public void func_70295_k_() {}
    


    public void func_70305_f() {}
    

    public boolean func_94041_b(int slot, ItemStack itemstack)
    {
      if ((slot == 3) || (itemstack == null))
        return false;
      if (field_77994_a != 1)
        return false;
      if ((slot < 0) || (slot >= slots.length))
        return false;
      if (slots[slot] != null) {
        return false;
      }
      return true;
    }
    

    private long storage;
    private static final int SLOT_1 = 0;
    private static final int SLOT_2 = 1;
    private static final int SLOT_3 = 2;
    private static final int SLOT_RESULT = 3;
    private static final int[] slots_top = { 0, 1, 2 };
    private static final int[] slots_bottom = { 0, 1, 2 };
    private static final int[] slots_sides = { 0, 1, 2 };
    
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
      return false;
    }
  }
}
