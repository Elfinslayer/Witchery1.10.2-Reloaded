package com.emoniph.witchery.brewing;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryFluids;
import com.emoniph.witchery.blocks.BlockBaseContainer;
import com.emoniph.witchery.common.CommonProxy;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.EntityPosition;
import com.emoniph.witchery.util.EntityUtil;
import com.google.common.collect.Maps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;

public class BlockBrewLiquidEffect
  extends BlockBaseContainer implements IFluidBlock
{
  protected static final Map<Block, Boolean> defaultDisplacements = ;
  
  static {
    defaultDisplacements.put(Blocks.field_150466_ao, Boolean.valueOf(false));
    defaultDisplacements.put(Blocks.field_150454_av, Boolean.valueOf(false));
    defaultDisplacements.put(Blocks.field_150472_an, Boolean.valueOf(false));
    defaultDisplacements.put(Blocks.field_150444_as, Boolean.valueOf(false));
    defaultDisplacements.put(Blocks.field_150436_aH, Boolean.valueOf(false)); }
  
  protected Map<Block, Boolean> displacements = Maps.newHashMap();
  
  protected int quantaPerBlock = 6;
  protected float quantaPerBlockFloat = 8.0F;
  protected int density = 1;
  protected int densityDir = -1;
  protected int temperature = 295;
  
  protected int tickRate = 20;
  protected int renderPass = 1;
  protected int maxScaledLight = 0;
  
  protected boolean[] isOptimalFlowDirection = new boolean[4];
  protected int[] flowCost = new int[4];
  protected FluidStack stack;
  protected final String fluidName;
  @SideOnly(Side.CLIENT)
  protected IIcon[] icons;
  
  public BlockBrewLiquidEffect() {
    super(Material.field_151586_h, TileEntityBrewFluid.class);
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    func_149675_a(true);
    registerWithCreateTab = false;
    func_149649_H();
    
    Fluid fluid = FluidsBREW_LIQUID;
    fluidName = fluid.getName();
    density = fluid.getDensity();
    temperature = fluid.getTemperature();
    maxScaledLight = fluid.getLuminosity();
    tickRate = (fluid.getViscosity() / 200);
    densityDir = (fluid.getDensity() > 0 ? -1 : 1);
    fluid.setBlock(this);
    
    stack = new FluidStack(fluid, 1000);
    
    displacements.putAll(defaultDisplacements);
  }
  
  public int func_149720_d(IBlockAccess world, int x, int y, int z) {
    TileEntityBrewFluid fluid = (TileEntityBrewFluid)BlockUtil.getTileEntity(world, x, y, z, TileEntityBrewFluid.class);
    if (fluid != null) {
      return color;
    }
    return 68;
  }
  
  public BlockBrewLiquidEffect setFluidStack(FluidStack stack) {
    this.stack = stack;
    return this;
  }
  
  public BlockBrewLiquidEffect setFluidStackAmount(int amount) {
    stack.amount = amount;
    return this;
  }
  



  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int meta)
  {
    return (side != 0) && (side != 1) ? icons[1] : icons[0];
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister iconRegister)
  {
    icons = new IIcon[] { iconRegister.func_94245_a(func_149641_N() + "_still"), iconRegister.func_94245_a(func_149641_N() + "_flow") };
  }
  
  public BlockBrewLiquidEffect setQuantaPerBlock(int quantaPerBlock)
  {
    if ((quantaPerBlock > 16) || (quantaPerBlock < 1))
      quantaPerBlock = 8;
    this.quantaPerBlock = quantaPerBlock;
    quantaPerBlockFloat = quantaPerBlock;
    return this;
  }
  
  public BlockBrewLiquidEffect setDensity(int density) {
    if (density == 0)
      density = 1;
    this.density = density;
    densityDir = (density > 0 ? -1 : 1);
    return this;
  }
  
  public BlockBrewLiquidEffect setTemperature(int temperature) {
    this.temperature = temperature;
    return this;
  }
  
  public BlockBrewLiquidEffect setTickRate(int tickRate) {
    if (tickRate <= 0)
      tickRate = 20;
    this.tickRate = tickRate;
    return this;
  }
  
  public BlockBrewLiquidEffect setRenderPass(int renderPass) {
    this.renderPass = renderPass;
    return this;
  }
  
  public BlockBrewLiquidEffect setMaxScaledLight(int maxScaledLight) {
    this.maxScaledLight = maxScaledLight;
    return this;
  }
  
  public boolean canDisplace(IBlockAccess world, int x, int y, int z)
  {
    if (world.func_147439_a(x, y, z).isAir(world, x, y, z)) {
      return true;
    }
    if (world.func_147439_a(x, y, z).func_149688_o().func_76224_d()) {
      return false;
    }
    
    Block block = world.func_147439_a(x, y, z);
    
    if (block == this) {
      return false;
    }
    
    if (displacements.containsKey(block)) {
      return ((Boolean)displacements.get(block)).booleanValue();
    }
    
    Material material = block.func_149688_o();
    if ((material.func_76230_c()) || (material == Material.field_151567_E)) {
      return false;
    }
    
    int density = getDensity(world, x, y, z);
    if (density == Integer.MAX_VALUE) {
      return true;
    }
    
    if (this.density > density) {
      return true;
    }
    return false;
  }
  
  public boolean displaceIfPossible(World world, int x, int y, int z)
  {
    if (world.func_147439_a(x, y, z).isAir(world, x, y, z)) {
      return true;
    }
    
    if (world.func_147439_a(x, y, z).func_149688_o().func_76224_d()) {
      return false;
    }
    
    Block block = world.func_147439_a(x, y, z);
    if (block == this) {
      return false;
    }
    
    if (displacements.containsKey(block)) {
      if (((Boolean)displacements.get(block)).booleanValue()) {
        block.func_149697_b(world, x, y, z, world.func_72805_g(x, y, z), 0);
        return true;
      }
      return false;
    }
    
    Material material = block.func_149688_o();
    if ((material.func_76230_c()) || (material == Material.field_151567_E)) {
      return false;
    }
    
    int density = getDensity(world, x, y, z);
    if (density == Integer.MAX_VALUE) {
      block.func_149697_b(world, x, y, z, world.func_72805_g(x, y, z), 0);
      return true;
    }
    
    if (this.density > density) {
      return true;
    }
    return false;
  }
  

  public void func_149726_b(World world, int x, int y, int z)
  {
    world.func_147464_a(x, y, z, this, tickRate);
  }
  
  public void func_149695_a(World world, int x, int y, int z, Block block)
  {
    world.func_147464_a(x, y, z, this, tickRate);
  }
  
  public boolean func_149698_L()
  {
    return false;
  }
  
  public boolean func_149655_b(IBlockAccess world, int x, int y, int z)
  {
    return true;
  }
  
  public AxisAlignedBB func_149668_a(World world, int x, int y, int z)
  {
    return null;
  }
  
  public Item func_149650_a(int par1, Random par2Random, int par3)
  {
    return null;
  }
  
  public int func_149745_a(Random par1Random)
  {
    return 0;
  }
  
  public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
  {
    return null;
  }
  
  public int func_149738_a(World world)
  {
    return tickRate;
  }
  
  public void func_149640_a(World world, int x, int y, int z, Entity entity, Vec3 vec)
  {
    if (densityDir > 0)
      return;
    Vec3 vec_flow = getFlowVector(world, x, y, z);
    field_72450_a += field_72450_a * (quantaPerBlock * 4);
    field_72448_b += field_72448_b * (quantaPerBlock * 4);
    field_72449_c += field_72449_c * (quantaPerBlock * 4);
  }
  
  public int func_149645_b()
  {
    return Witchery.proxy.getBrewLiquidRenderId();
  }
  
  public boolean func_149662_c()
  {
    return false;
  }
  
  public boolean func_149686_d()
  {
    return false;
  }
  
  public int func_149677_c(IBlockAccess world, int x, int y, int z)
  {
    int lightThis = world.func_72802_i(x, y, z, 0);
    int lightUp = world.func_72802_i(x, y + 1, z, 0);
    int lightThisBase = lightThis & 0xFF;
    int lightUpBase = lightUp & 0xFF;
    int lightThisExt = lightThis >> 16 & 0xFF;
    int lightUpExt = lightUp >> 16 & 0xFF;
    return (lightThisBase > lightUpBase ? lightThisBase : lightUpBase) | (lightThisExt > lightUpExt ? lightThisExt : lightUpExt) << 16;
  }
  

  public int func_149701_w()
  {
    return renderPass;
  }
  
  public boolean func_149646_a(IBlockAccess world, int x, int y, int z, int side)
  {
    Block block = world.func_147439_a(x, y, z);
    if (block != this) {
      return !block.func_149662_c();
    }
    return block.func_149688_o() == func_149688_o() ? false : super.func_149646_a(world, x, y, z, side);
  }
  
  public static final int getDensity(IBlockAccess world, int x, int y, int z)
  {
    Block block = world.func_147439_a(x, y, z);
    if (!(block instanceof BlockBrewLiquidEffect)) {
      return Integer.MAX_VALUE;
    }
    return density;
  }
  
  public static final int getTemperature(IBlockAccess world, int x, int y, int z) {
    Block block = world.func_147439_a(x, y, z);
    if (!(block instanceof BlockBrewLiquidEffect)) {
      return Integer.MAX_VALUE;
    }
    return temperature;
  }
  
  public static double getFlowDirection(IBlockAccess world, int x, int y, int z) {
    Block block = world.func_147439_a(x, y, z);
    if (!block.func_149688_o().func_76224_d()) {
      return -1000.0D;
    }
    Vec3 vec = ((BlockBrewLiquidEffect)block).getFlowVector(world, x, y, z);
    return (field_72450_a == 0.0D) && (field_72449_c == 0.0D) ? -1000.0D : Math.atan2(field_72449_c, field_72450_a) - 1.5707963267948966D;
  }
  
  public final int getQuantaValueBelow(IBlockAccess world, int x, int y, int z, int belowThis)
  {
    int quantaRemaining = getQuantaValue(world, x, y, z);
    if (quantaRemaining >= belowThis) {
      return -1;
    }
    return quantaRemaining;
  }
  
  public final int getQuantaValueAbove(IBlockAccess world, int x, int y, int z, int aboveThis) {
    int quantaRemaining = getQuantaValue(world, x, y, z);
    if (quantaRemaining <= aboveThis) {
      return -1;
    }
    return quantaRemaining;
  }
  
  public final float getQuantaPercentage(IBlockAccess world, int x, int y, int z) {
    int quantaRemaining = getQuantaValue(world, x, y, z);
    return quantaRemaining / quantaPerBlockFloat;
  }
  
  public Vec3 getFlowVector(IBlockAccess world, int x, int y, int z) {
    Vec3 vec = Vec3.func_72443_a(0.0D, 0.0D, 0.0D);
    int decay = quantaPerBlock - getQuantaValue(world, x, y, z);
    
    for (int side = 0; side < 4; side++) {
      int x2 = x;
      int z2 = z;
      
      switch (side) {
      case 0: 
        x2--;
        break;
      case 1: 
        z2--;
        break;
      case 2: 
        x2++;
        break;
      case 3: 
        z2++;
      }
      
      
      int otherDecay = quantaPerBlock - getQuantaValue(world, x2, y, z2);
      if (otherDecay >= quantaPerBlock) {
        if (!world.func_147439_a(x2, y, z2).func_149688_o().func_76230_c()) {
          otherDecay = quantaPerBlock - getQuantaValue(world, x2, y - 1, z2);
          if (otherDecay >= 0) {
            int power = otherDecay - (decay - quantaPerBlock);
            vec = vec.func_72441_c((x2 - x) * power, (y - y) * power, (z2 - z) * power);
          }
        }
      } else if (otherDecay >= 0) {
        int power = otherDecay - decay;
        vec = vec.func_72441_c((x2 - x) * power, (y - y) * power, (z2 - z) * power);
      }
    }
    
    if (world.func_147439_a(x, y + 1, z) == this) {
      boolean flag = (func_149747_d(world, x, y, z - 1, 2)) || (func_149747_d(world, x, y, z + 1, 3)) || (func_149747_d(world, x - 1, y, z, 4)) || (func_149747_d(world, x + 1, y, z, 5)) || (func_149747_d(world, x, y + 1, z - 1, 2)) || (func_149747_d(world, x, y + 1, z + 1, 3)) || (func_149747_d(world, x - 1, y + 1, z, 4)) || (func_149747_d(world, x + 1, y + 1, z, 5));
      



      if (flag) {
        vec = vec.func_72432_b().func_72441_c(0.0D, -6.0D, 0.0D);
      }
    }
    vec = vec.func_72432_b();
    return vec;
  }
  
  public Fluid getFluid()
  {
    return FluidRegistry.getFluid(fluidName);
  }
  
  public float getFilledPercentage(World world, int x, int y, int z)
  {
    int quantaRemaining = getQuantaValue(world, x, y, z) + 1;
    float remaining = quantaRemaining / quantaPerBlockFloat;
    if (remaining > 1.0F)
      remaining = 1.0F;
    return remaining * (density > 0 ? 1 : -1);
  }
  
  public int getQuantaValue(IBlockAccess world, int x, int y, int z) {
    if (world.func_147439_a(x, y, z) == Blocks.field_150350_a) {
      return 0;
    }
    
    if (world.func_147439_a(x, y, z) != this) {
      return -1;
    }
    
    int quantaRemaining = quantaPerBlock - world.func_72805_g(x, y, z);
    return quantaRemaining;
  }
  
  public boolean func_149678_a(int meta, boolean fullHit)
  {
    return (fullHit) && (meta == 0);
  }
  
  public int getMaxRenderHeightMeta() {
    return 0;
  }
  
  public int getLightValue(IBlockAccess world, int x, int y, int z)
  {
    if (maxScaledLight == 0) {
      return super.getLightValue(world, x, y, z);
    }
    int data = quantaPerBlock - world.func_72805_g(x, y, z) - 1;
    return (int)(data / quantaPerBlockFloat * maxScaledLight);
  }
  
  private boolean isTargetBlock(World world, Block block, int x, int y, int z) {
    return (block != null) && ((block != Blocks.field_150350_a) || (world.func_147439_a(x, y - 1, z).func_149688_o().func_76220_a())) && (block != this);
  }
  
  public boolean isFlowingVertically(IBlockAccess world, int x, int y, int z)
  {
    return (world.func_147439_a(x, y + densityDir, z) == this) || ((world.func_147439_a(x, y, z) == this) && (canFlowInto(world, x, y + densityDir, z)));
  }
  
  public boolean isSourceBlock(IBlockAccess world, int x, int y, int z)
  {
    return (world.func_147439_a(x, y, z) == this) && (world.func_72805_g(x, y, z) == 0);
  }
  
  protected boolean[] getOptimalFlowDirections(World world, int x, int y, int z) {
    for (int side = 0; side < 4; side++) {
      flowCost[side] = 1000;
      
      int x2 = x;
      int y2 = y;
      int z2 = z;
      
      switch (side) {
      case 0: 
        x2--;
        break;
      case 1: 
        x2++;
        break;
      case 2: 
        z2--;
        break;
      case 3: 
        z2++;
      }
      
      
      if ((canFlowInto(world, x2, y2, z2)) && (!isSourceBlock(world, x2, y2, z2)))
      {


        if (canFlowInto(world, x2, y2 + densityDir, z2)) {
          flowCost[side] = 0;
        } else {
          flowCost[side] = calculateFlowCost(world, x2, y2, z2, 1, side);
        }
      }
    }
    int min = flowCost[0];
    for (int side = 1; side < 4; side++) {
      if (flowCost[side] < min) {
        min = flowCost[side];
      }
    }
    for (int side = 0; side < 4; side++) {
      isOptimalFlowDirection[side] = (flowCost[side] == min ? 1 : false);
    }
    return isOptimalFlowDirection;
  }
  
  protected int calculateFlowCost(World world, int x, int y, int z, int recurseDepth, int side) {
    int cost = 1000;
    for (int adjSide = 0; adjSide < 4; adjSide++)
      if (((adjSide != 0) || (side != 1)) && ((adjSide != 1) || (side != 0)) && ((adjSide != 2) || (side != 3)) && ((adjSide != 3) || (side != 2)))
      {



        int x2 = x;
        int y2 = y;
        int z2 = z;
        
        switch (adjSide) {
        case 0: 
          x2--;
          break;
        case 1: 
          x2++;
          break;
        case 2: 
          z2--;
          break;
        case 3: 
          z2++;
        }
        
        
        if ((canFlowInto(world, x2, y2, z2)) && (!isSourceBlock(world, x2, y2, z2)))
        {


          if (canFlowInto(world, x2, y2 + densityDir, z2)) {
            return recurseDepth;
          }
          
          if (recurseDepth < 4)
          {


            int min = calculateFlowCost(world, x2, y2, z2, recurseDepth + 1, adjSide);
            if (min < cost)
              cost = min;
          }
        } }
    return cost;
  }
  
  protected void flowIntoBlock(World world, int x, int y, int z, int meta, TileEntityBrewFluid sourceFluid) {
    if (meta < 0)
      return;
    if (displaceIfPossible(world, x, y, z)) {
      world.func_147465_d(x, y, z, this, meta, 3);
      TileEntityBrewFluid targetFluid = (TileEntityBrewFluid)BlockUtil.getTileEntity(world, x, y, z, TileEntityBrewFluid.class);
      
      if ((targetFluid != null) && (sourceFluid != null) && (nbtEffect != null)) {
        nbtEffect = ((NBTTagCompound)nbtEffect.func_74737_b());
        expansion = expansion;
        color = color;
        duration = duration;
        thrower = thrower;
      }
    }
  }
  
  protected boolean canFlowInto(IBlockAccess world, int x, int y, int z) {
    if (world.func_147439_a(x, y, z).isAir(world, x, y, z)) {
      return true;
    }
    Block block = world.func_147439_a(x, y, z);
    if (block == this) {
      return true;
    }
    
    if (displacements.containsKey(block)) {
      return ((Boolean)displacements.get(block)).booleanValue();
    }
    
    Material material = block.func_149688_o();
    if ((material.func_76230_c()) || (material == Material.field_151586_h) || (material == Material.field_151587_i) || (material == Material.field_151567_E))
    {
      return false;
    }
    
    int density = getDensity(world, x, y, z);
    if (density == Integer.MAX_VALUE) {
      return true;
    }
    
    if (this.density > density) {
      return true;
    }
    return false;
  }
  
  protected int getLargerQuanta(IBlockAccess world, int x, int y, int z, int compare)
  {
    int quantaRemaining = getQuantaValue(world, x, y, z);
    if (quantaRemaining <= 0) {
      return compare;
    }
    return quantaRemaining >= compare ? quantaRemaining : compare;
  }
  
  public FluidStack drain(World world, int x, int y, int z, boolean doDrain)
  {
    return null;
  }
  
  public boolean canDrain(World world, int x, int y, int z)
  {
    return false;
  }
  
  public void func_149674_a(World world, int x, int y, int z, Random rand)
  {
    if (field_72995_K) {
      return;
    }
    
    boolean evaporated = false;
    TileEntityBrewFluid fluid = (TileEntityBrewFluid)BlockUtil.getTileEntity(world, x, y, z, TileEntityBrewFluid.class);
    
    if ((!field_72995_K) && (fluid != null) && (isSourceBlock(world, x, y, z))) {
      if ((++updateCount > 3) && ((duration == 0) || (rand.nextInt(duration) == 0))) {
        world.func_147468_f(x, y, z);
        evaporated = true;
      } else {
        world.func_147464_a(x, y, z, this, tickRate);
      }
    }
    
    if (!evaporated) {
      int quantaRemaining = quantaPerBlock - world.func_72805_g(x, y, z);
      int expQuanta = -101;
      

      if (quantaRemaining < quantaPerBlock) {
        int y2 = y - densityDir;
        
        if ((world.func_147439_a(x, y2, z) == this) || (world.func_147439_a(x - 1, y2, z) == this) || (world.func_147439_a(x + 1, y2, z) == this) || (world.func_147439_a(x, y2, z - 1) == this) || (world.func_147439_a(x, y2, z + 1) == this))
        {

          expQuanta = quantaPerBlock - 1;
        } else {
          int maxQuanta = -100;
          maxQuanta = getLargerQuanta(world, x - 1, y, z, maxQuanta);
          maxQuanta = getLargerQuanta(world, x + 1, y, z, maxQuanta);
          maxQuanta = getLargerQuanta(world, x, y, z - 1, maxQuanta);
          maxQuanta = getLargerQuanta(world, x, y, z + 1, maxQuanta);
          
          expQuanta = maxQuanta - 1;
        }
        

        if (expQuanta != quantaRemaining) {
          quantaRemaining = expQuanta;
          
          if (expQuanta <= 0) {
            world.func_147449_b(x, y, z, Blocks.field_150350_a);
          } else {
            world.func_72921_c(x, y, z, quantaPerBlock - expQuanta, 3);
            world.func_147464_a(x, y, z, this, tickRate);
            world.func_147459_d(x, y, z, this);
          }
          
        }
        

      }
      else if (quantaRemaining >= quantaPerBlock) {
        world.func_72921_c(x, y, z, 0, 2);
      }
      

      if (canDisplace(world, x, y + densityDir, z)) {
        flowIntoBlock(world, x, y + densityDir, z, 1, fluid);
        return;
      }
      

      int flowMeta = quantaPerBlock - quantaRemaining + 1;
      if (flowMeta >= quantaPerBlock) {
        return;
      }
      
      if ((isSourceBlock(world, x, y, z)) || (!isFlowingVertically(world, x, y, z))) {
        if (world.func_147439_a(x, y - densityDir, z) == this) {
          flowMeta = 1;
        }
        boolean[] flowTo = getOptimalFlowDirections(world, x, y, z);
        
        if (flowTo[0] != 0)
          flowIntoBlock(world, x - 1, y, z, flowMeta, fluid);
        if (flowTo[1] != 0)
          flowIntoBlock(world, x + 1, y, z, flowMeta, fluid);
        if (flowTo[2] != 0)
          flowIntoBlock(world, x, y, z - 1, flowMeta, fluid);
        if (flowTo[3] != 0) {
          flowIntoBlock(world, x, y, z + 1, flowMeta, fluid);
        }
      }
      if ((fluid != null) && (nbtEffect != null)) {
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
          int x2 = x + offsetX;int y2 = y + offsetY;int z2 = z + offsetZ;
          if ((field_73012_v.nextDouble() < 0.01D) && (isTargetBlock(world, world.func_147439_a(x2, y2, z2), x2, y2, z2)))
          {
            ModifiersEffect modifiers = new ModifiersEffect(1.0D, 1.0D, false, new EntityPosition(x + 0.5D, y, z + 0.5D), false, 0, EntityUtil.playerOrFake(world, thrower));
            
            strengthPenalty += 1;
            WitcheryBrewRegistry.INSTANCE.applyToBlock(world, x2, y2, z2, direction.getOpposite(), 1, nbtEffect, modifiers);
          }
        }
        
        world.func_147464_a(x, y, z, this, tickRate);
      }
    }
  }
  
  public void func_149670_a(World world, int x, int y, int z, Entity entity) {
    if ((entity != null) && ((entity instanceof EntityLivingBase)) && 
      (!field_72995_K) && (field_73012_v.nextInt(10) == 4)) {
      TileEntityBrewFluid liquid = (TileEntityBrewFluid)BlockUtil.getTileEntity(world, x, y, z, TileEntityBrewFluid.class);
      
      if ((liquid != null) && (nbtEffect != null)) {
        EntityLivingBase living = (EntityLivingBase)entity;
        
        WitcheryBrewRegistry.INSTANCE.applyToEntity(world, living, nbtEffect, new ModifiersEffect(0.25D, 0.5D, false, new EntityPosition(x, y, z), false, 0, EntityUtil.playerOrFake(world, thrower)));
      }
    }
  }
}
