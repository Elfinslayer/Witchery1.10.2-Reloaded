package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import com.emoniph.witchery.util.BlockSide;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.util.SlotClayJar;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWitchesOven extends BlockBaseContainer
{
  private final Random furnaceRand = new Random();
  
  private final boolean isActive;
  
  private static boolean keepFurnaceInventory;
  

  public BlockWitchesOven(boolean burning)
  {
    super(Material.field_151573_f, TileEntityWitchesOven.class);
    registerTileEntity = (!burning);
    registerWithCreateTab = (!burning);
    
    isActive = burning;
    
    func_149711_c(3.5F);
    func_149672_a(field_149777_j);
    if (isActive) {
      func_149715_a(0.875F);
    }
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
  
  public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
  {
    return Item.func_150898_a(BlocksOVEN_IDLE);
  }
  
  public void func_149726_b(World par1World, int par2, int par3, int par4)
  {
    super.func_149726_b(par1World, par2, par3, par4);
    BlockUtil.setBlockDefaultDirection(par1World, par2, par3, par4);
  }
  
  public static boolean isOven(Block block) {
    return (block == BlocksOVEN_IDLE) || (block == BlocksOVEN_BURNING);
  }
  

  public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
  {
    if (field_72995_K) {
      return true;
    }
    TileEntity tileentityfurnace = par1World.func_147438_o(par2, par3, par4);
    
    if (tileentityfurnace != null) {
      par5EntityPlayer.openGui(Witchery.instance, 2, par1World, par2, par3, par4);
    }
    
    return true;
  }
  
  public static void updateWitchesOvenBlockState(boolean par0, World par1World, int par2, int par3, int par4)
  {
    int l = par1World.func_72805_g(par2, par3, par4);
    TileEntity tileentity = par1World.func_147438_o(par2, par3, par4);
    keepFurnaceInventory = true;
    
    if (par0) {
      par1World.func_147449_b(par2, par3, par4, BlocksOVEN_BURNING);
    } else {
      par1World.func_147449_b(par2, par3, par4, BlocksOVEN_IDLE);
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
      int l = par1World.func_72805_g(par2, par3, par4);
      float f = par2 + 0.5F;
      float f1 = par3 + 0.2F + par5Random.nextFloat() * 6.0F / 16.0F;
      float f2 = par4 + 0.5F;
      float f3 = 0.52F;
      float f4 = par5Random.nextFloat() * 0.6F - 0.3F;
      
      if (l == 4) {
        par1World.func_72869_a("smoke", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
        par1World.func_72869_a("flame", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
      } else if (l == 5) {
        par1World.func_72869_a("smoke", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
        par1World.func_72869_a("flame", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
      } else if (l == 2) {
        par1World.func_72869_a("smoke", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
        par1World.func_72869_a("flame", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
      } else if (l == 3) {
        par1World.func_72869_a("smoke", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
        par1World.func_72869_a("flame", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
      }
    }
  }
  

  public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
  {
    int l = MathHelper.func_76128_c(field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3;
    
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
      TileEntity tile = par1World.func_147438_o(par2, par3, par4);
      TileEntityWitchesOven tileentityfurnace = (TileEntityWitchesOven)BlockUtil.getTileEntity(par1World, par2, par3, par4, TileEntityWitchesOven.class);
      
      if (tileentityfurnace != null)
      {
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
    TileEntity te = par1World.func_147438_o(par2, par3, par4);
    return (te != null) && ((te instanceof IInventory)) ? Container.func_94526_b((IInventory)te) : 0;
  }
  
  public Item func_149694_d(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
  {
    return Item.func_150898_a(BlocksOVEN_IDLE);
  }
  







  public static class TileEntityWitchesOven
    extends TileEntity
    implements ISidedInventory
  {
    private ItemStack[] furnaceItemStacks = new ItemStack[5];
    public int furnaceBurnTime;
    public int currentItemBurnTime;
    public int furnaceCookTime;
    static final int COOK_TIME = 180;
    private static final double FUNNEL_CHANCE = 0.25D;
    private static final double FILTERED_FUNNEL_CHANCE = 0.3D;
    private static final double DOUBLED_FILTERED_FUNNEL_CHANCE = 0.8D;
    private static final int SLOT_TO_COOK = 0;
    private static final int SLOT_FUEL = 1;
    private static final int SLOT_COOKED = 2;
    private static final int SLOT_BY_PRODUCT = 3;
    private static final int SLOT_JARS = 4;
    
    public TileEntityWitchesOven() {}
    
    public int func_70302_i_() { return furnaceItemStacks.length; }
    

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
      
      furnaceBurnTime = par1NBTTagCompound.func_74765_d("BurnTime");
      furnaceCookTime = par1NBTTagCompound.func_74765_d("CookTime");
      currentItemBurnTime = TileEntityFurnace.func_145952_a(furnaceItemStacks[1]);
    }
    
    public void func_145841_b(NBTTagCompound par1NBTTagCompound)
    {
      super.func_145841_b(par1NBTTagCompound);
      par1NBTTagCompound.func_74777_a("BurnTime", (short)furnaceBurnTime);
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
      return furnaceCookTime * par1 / getCookTime();
    }
    
    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int par1) {
      if (currentItemBurnTime == 0) {
        currentItemBurnTime = 200;
      }
      
      return furnaceBurnTime * par1 / currentItemBurnTime;
    }
    
    public boolean isBurning() {
      return furnaceBurnTime > 0;
    }
    


    public void func_145845_h()
    {
      boolean flag = furnaceBurnTime > 0;
      boolean flag1 = false;
      
      if (furnaceBurnTime > 0) {
        furnaceBurnTime -= 1;
      }
      
      if (!field_145850_b.field_72995_K) {
        if ((furnaceBurnTime == 0) && (canSmelt())) {
          currentItemBurnTime = (this.furnaceBurnTime = TileEntityFurnace.func_145952_a(furnaceItemStacks[1]));
          

          if (furnaceBurnTime > 0) {
            flag1 = true;
            
            if (furnaceItemStacks[1] != null) {
              furnaceItemStacks[1].field_77994_a -= 1;
              
              if (furnaceItemStacks[1].field_77994_a == 0) {
                furnaceItemStacks[1] = furnaceItemStacks[1].func_77973_b().getContainerItem(furnaceItemStacks[1]);
              }
            }
          }
        }
        

        if ((isBurning()) && (canSmelt())) {
          furnaceCookTime += 1;
          
          if (furnaceCookTime >= getCookTime()) {
            furnaceCookTime = 0;
            smeltItem();
            flag1 = true;
          }
        } else {
          furnaceCookTime = 0;
        }
        
        if (flag != furnaceBurnTime > 0) {
          flag1 = true;
          BlockWitchesOven.updateWitchesOvenBlockState(furnaceBurnTime > 0, field_145850_b, field_145851_c, field_145848_d, field_145849_e);
        }
      }
      

      if (flag1) {
        field_145850_b.func_147471_g(field_145851_c, field_145848_d, field_145849_e);
      }
    }
    
    private boolean canSmelt() {
      if (furnaceItemStacks[0] == null) {
        return false;
      }
      ItemStack itemstack = FurnaceRecipes.func_77602_a().func_151395_a(furnaceItemStacks[0]);
      
      if (itemstack == null) {
        return false;
      }
      Item item = itemstack.func_77973_b();
      
      if ((item != Items.field_151044_h) && (!(item instanceof ItemFood)) && (!ItemsGENERIC.itemAshWood.isMatch(itemstack)))
      {
        return false;
      }
      if (furnaceItemStacks[2] == null) {
        return true;
      }
      if (!furnaceItemStacks[2].func_77969_a(itemstack)) {
        return false;
      }
      int result = furnaceItemStacks[2].field_77994_a + field_77994_a;
      return (result <= func_70297_j_()) && (result <= itemstack.func_77976_d());
    }
    
    public void smeltItem()
    {
      if (canSmelt()) {
        ItemStack itemstack = FurnaceRecipes.func_77602_a().func_151395_a(furnaceItemStacks[0]);
        
        if (furnaceItemStacks[2] == null) {
          furnaceItemStacks[2] = itemstack.func_77946_l();
        } else if (furnaceItemStacks[2].func_77969_a(itemstack)) {
          furnaceItemStacks[2].field_77994_a += field_77994_a;
        }
        
        generateByProduct(itemstack);
        
        furnaceItemStacks[0].field_77994_a -= 1;
        
        if (furnaceItemStacks[0].field_77994_a <= 0) {
          furnaceItemStacks[0] = null;
        }
      }
    }
    
    private int getFumeFunnels() {
      int funnels = 0;
      int meta = field_145850_b.func_72805_g(field_145851_c, field_145848_d, field_145849_e);
      switch (meta) {
      case 2: 
      case 3: 
        funnels += (isFumeFunnel(field_145851_c - 1, field_145848_d, field_145849_e, meta) ? 1 : 0);
        funnels += (isFumeFunnel(field_145851_c + 1, field_145848_d, field_145849_e, meta) ? 1 : 0);
        break;
      case 4: 
      case 5: 
        funnels += (isFumeFunnel(field_145851_c, field_145848_d, field_145849_e - 1, meta) ? 1 : 0);
        funnels += (isFumeFunnel(field_145851_c, field_145848_d, field_145849_e + 1, meta) ? 1 : 0);
      }
      
      funnels += (isFumeFunnel(field_145851_c, field_145848_d + 1, field_145849_e, meta) ? 1 : 0);
      return funnels;
    }
    
    private boolean isFumeFunnel(int xCoord, int yCoord, int zCoord, int meta) {
      Block block = field_145850_b.func_147439_a(xCoord, yCoord, zCoord);
      return ((block == BlocksOVEN_FUMEFUNNEL) || (block == BlocksOVEN_FUMEFUNNEL_FILTERED)) && (field_145850_b.func_72805_g(xCoord, yCoord, zCoord) == meta);
    }
    




    private double getFumeFunnelsChance()
    {
      double funnels = 0.0D;
      switch (field_145850_b.func_72805_g(field_145851_c, field_145848_d, field_145849_e)) {
      case 2: 
        funnels += getFumeFunnelChance(field_145851_c + 1, field_145848_d, field_145849_e, 2);
        funnels += getFumeFunnelChance(field_145851_c - 1, field_145848_d, field_145849_e, 2);
        break;
      case 3: 
        funnels += getFumeFunnelChance(field_145851_c + 1, field_145848_d, field_145849_e, 3);
        funnels += getFumeFunnelChance(field_145851_c - 1, field_145848_d, field_145849_e, 3);
        break;
      case 4: 
        funnels += getFumeFunnelChance(field_145851_c, field_145848_d, field_145849_e + 1, 4);
        funnels += getFumeFunnelChance(field_145851_c, field_145848_d, field_145849_e - 1, 4);
        break;
      case 5: 
        funnels += getFumeFunnelChance(field_145851_c, field_145848_d, field_145849_e + 1, 5);
        funnels += getFumeFunnelChance(field_145851_c, field_145848_d, field_145849_e - 1, 5);
      }
      
      return funnels;
    }
    
    private double getFumeFunnelChance(int x, int y, int z, int meta) {
      Block block = field_145850_b.func_147439_a(x, y, z);
      if (block == BlocksOVEN_FUMEFUNNEL) {
        if (field_145850_b.func_72805_g(x, y, z) == meta) {
          return 0.25D;
        }
      } else if ((block == BlocksOVEN_FUMEFUNNEL_FILTERED) && 
        (field_145850_b.func_72805_g(x, y, z) == meta)) {
        return instancedoubleFumeFilterChance ? 0.8D : 0.3D;
      }
      

      return 0.0D;
    }
    
    private int getCookTime() {
      int time = 180 - 20 * getFumeFunnels();
      return time;
    }
    
    private void generateByProduct(ItemStack itemstack) {
      try {
        double BASE_CHANCE = 0.3D;
        double funnels = getFumeFunnelsChance();
        
        Log.instance().debug("" + furnaceItemStacks[0] + ": " + furnaceItemStacks[0].func_77973_b().func_77658_a());
        


        if ((field_145850_b.field_73012_v.nextDouble() <= Math.min(0.3D + funnels, 1.0D)) && (furnaceItemStacks[4] != null))
        {
          if ((furnaceItemStacks[0].func_77973_b() == Item.func_150898_a(net.minecraft.init.Blocks.field_150345_g)) && (furnaceItemStacks[0].func_77960_j() != 3))
          {
            switch (furnaceItemStacks[0].func_77960_j()) {
            case 0: 
              createByProduct(ItemsGENERIC.itemExhaleOfTheHornedOne.createStack(1));
              break;
            case 1: 
              createByProduct(ItemsGENERIC.itemHintOfRebirth.createStack(1));
              break;
            case 2: 
              createByProduct(ItemsGENERIC.itemBreathOfTheGoddess.createStack(1));
            }
            
          }
          else if (furnaceItemStacks[0].func_77973_b() == Item.func_150898_a(BlocksSAPLING))
          {
            switch (furnaceItemStacks[0].func_77960_j()) {
            case 0: 
              createByProduct(ItemsGENERIC.itemWhiffOfMagic.createStack(1));
              break;
            case 1: 
              createByProduct(ItemsGENERIC.itemReekOfMisfortune.createStack(1));
              break;
            case 2: 
              createByProduct(ItemsGENERIC.itemOdourOfPurity.createStack(1));
            }
          }
          else if ((furnaceItemStacks[0].func_77977_a().equals("tile.bop.saplings")) && (furnaceItemStacks[0].func_77960_j() == 6))
          {

            createByProduct(ItemsGENERIC.itemHintOfRebirth.createStack(1));
          } else if ((furnaceItemStacks[0].func_77942_o()) && (furnaceItemStacks[0].func_77978_p().func_74764_b("Genome")))
          {
            NBTBase tag = furnaceItemStacks[0].func_77978_p().func_74781_a("Genome");
            if ((tag != null) && ((tag instanceof NBTTagCompound))) {
              NBTTagCompound compound = (NBTTagCompound)tag;
              if ((compound.func_74764_b("Chromosomes")) && ((compound.func_74781_a("Chromosomes") instanceof NBTTagList)))
              {
                NBTTagList list = compound.func_150295_c("Chromosomes", 10);
                
                if ((list != null) && (list.func_74745_c() > 0)) {
                  NBTBase chromoBase = list.func_150305_b(0);
                  if ((chromoBase != null) && ((chromoBase instanceof NBTTagCompound))) {
                    NBTTagCompound chromosome = (NBTTagCompound)chromoBase;
                    if (chromosome.func_74764_b("UID0")) {
                      String treeType = chromosome.func_74779_i("UID0");
                      if (treeType != null) {
                        Log.instance().debug("Forestry tree: " + treeType);
                        if (treeType.equals("forestry.treeOak")) {
                          createByProduct(ItemsGENERIC.itemExhaleOfTheHornedOne.createStack(1));
                        }
                        else if (treeType.equals("forestry.treeSpruce")) {
                          createByProduct(ItemsGENERIC.itemHintOfRebirth.createStack(1));
                        }
                        else if (treeType.equals("forestry.treeBirch")) {
                          createByProduct(ItemsGENERIC.itemBreathOfTheGoddess.createStack(1));
                        }
                      }
                    }
                  }
                }
              }
            }
          }
          else
          {
            createByProduct(ItemsGENERIC.itemFoulFume.createStack(1));
          }
        }
      } catch (Throwable e) {
        Log.instance().warning(e, "Exception occured while generating a by product from a witches oven");
      }
    }
    
    private void createByProduct(ItemStack byProduct) {
      int BY_PRODUCT_INDEX = 3;
      if (furnaceItemStacks[3] == null) {
        furnaceItemStacks[3] = byProduct;
        
        if (--furnaceItemStacks[4].field_77994_a <= 0) {
          furnaceItemStacks[4] = null;
        }
      } else if ((furnaceItemStacks[3].func_77969_a(byProduct)) && (furnaceItemStacks[3].field_77994_a + field_77994_a < furnaceItemStacks[3].func_77976_d()))
      {

        furnaceItemStacks[3].field_77994_a += field_77994_a;
        
        if (--furnaceItemStacks[4].field_77994_a <= 0) {
          furnaceItemStacks[4] = null;
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
      if ((slot == 2) || (slot == 3))
        return false;
      if (slot == 1)
        return TileEntityFurnace.func_145954_b(itemstack);
      if (slot == 4)
        return ItemsGENERIC.itemEmptyClayJar.isMatch(itemstack);
      if ((slot == 0) && (ItemsGENERIC.itemEmptyClayJar.isMatch(itemstack))) {
        return false;
      }
      return true;
    }
    







    private static final int[] slots_top = { 0, 4 };
    private static final int[] slots_bottom = { 4, 1 };
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
        return (slot == 1) && (stack.func_77973_b() == Items.field_151133_ar);
      }
      
      return (slot == 3) || (slot == 2);
    }
  }
  

  public static class ContainerWitchesOven
    extends Container
  {
    private BlockWitchesOven.TileEntityWitchesOven furnace;
    
    private int lastCookTime;
    
    private int lastBurnTime;
    
    private int lastItemBurnTime;
    
    public ContainerWitchesOven(InventoryPlayer par1InventoryPlayer, BlockWitchesOven.TileEntityWitchesOven par2TileEntityFurnace)
    {
      furnace = par2TileEntityFurnace;
      func_75146_a(new Slot(par2TileEntityFurnace, 0, 56, 17));
      func_75146_a(new Slot(par2TileEntityFurnace, 1, 56, 53));
      func_75146_a(new SlotFurnace(field_70458_d, par2TileEntityFurnace, 2, 118, 21));
      func_75146_a(new SlotFurnace(field_70458_d, par2TileEntityFurnace, 3, 118, 53));
      func_75146_a(new SlotClayJar(par2TileEntityFurnace, 4, 83, 53));
      

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
      par1ICrafting.func_71112_a(this, 1, furnace.furnaceBurnTime);
      par1ICrafting.func_71112_a(this, 2, furnace.currentItemBurnTime);
    }
    
    public void func_75142_b()
    {
      super.func_75142_b();
      
      for (int i = 0; i < field_75149_d.size(); i++) {
        ICrafting icrafting = (ICrafting)field_75149_d.get(i);
        
        if (lastCookTime != furnace.furnaceCookTime) {
          icrafting.func_71112_a(this, 0, furnace.furnaceCookTime);
        }
        
        if (lastBurnTime != furnace.furnaceBurnTime) {
          icrafting.func_71112_a(this, 1, furnace.furnaceBurnTime);
        }
        
        if (lastItemBurnTime != furnace.currentItemBurnTime) {
          icrafting.func_71112_a(this, 2, furnace.currentItemBurnTime);
        }
      }
      
      lastCookTime = furnace.furnaceCookTime;
      lastBurnTime = furnace.furnaceBurnTime;
      lastItemBurnTime = furnace.currentItemBurnTime;
    }
    
    @SideOnly(Side.CLIENT)
    public void func_75137_b(int par1, int par2)
    {
      if (par1 == 0) {
        furnace.furnaceCookTime = par2;
      }
      
      if (par1 == 1) {
        furnace.furnaceBurnTime = par2;
      }
      
      if (par1 == 2) {
        furnace.currentItemBurnTime = par2;
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
        
        if ((slotIndex == 2) || (slotIndex == 3)) {
          if (!func_75135_a(itemstack1, 5, 41, true)) {
            return null;
          }
          
          slot.func_75220_a(itemstack1, itemstack);
        } else if ((slotIndex != 1) && (slotIndex != 0) && (slotIndex != 4)) {
          if (FurnaceRecipes.func_77602_a().func_151395_a(itemstack1) != null) {
            if (!func_75135_a(itemstack1, 0, 1, false)) {
              return null;
            }
          } else if (TileEntityFurnace.func_145954_b(itemstack1)) {
            if (!func_75135_a(itemstack1, 1, 2, false)) {
              return null;
            }
          } else if (ItemsGENERIC.itemEmptyClayJar.isMatch(itemstack1)) {
            if (!func_75135_a(itemstack1, 4, 5, false)) {
              return null;
            }
          } else if ((slotIndex >= 5) && (slotIndex < 32)) {
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
