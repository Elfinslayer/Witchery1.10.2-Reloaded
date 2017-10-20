package com.emoniph.witchery.item.brew;

import com.emoniph.witchery.item.ItemGeneral.Brew.BrewResult;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;

public class BrewFluid extends com.emoniph.witchery.item.ItemGeneral.Brew
{
  protected final Fluid fluid;
  
  public BrewFluid(int damageValue, String unlocalisedName, Fluid fluid)
  {
    super(damageValue, unlocalisedName);
    this.fluid = fluid;
  }
  
  public ItemGeneral.Brew.BrewResult onImpact(World world, net.minecraft.entity.EntityLivingBase thrower, MovingObjectPosition mop, boolean enhanced, double brewX, double brewY, double brewZ, AxisAlignedBB brewBounds)
  {
    switch (1.$SwitchMap$net$minecraft$util$MovingObjectPosition$MovingObjectType[field_72313_a.ordinal()]) {
    case 1: 
      depositLiquid(world, field_72311_b, field_72312_c, field_72309_d, field_72310_e, enhanced);
      break;
    case 2: 
      int x = MathHelper.func_76128_c(field_72308_g.field_70165_t);
      int y = MathHelper.func_76128_c(field_72308_g.field_70163_u);
      int z = MathHelper.func_76128_c(field_72308_g.field_70161_v);
      depositLiquid(world, x, y, z, -1, enhanced);
      break;
    }
    
    

    return ItemGeneral.Brew.BrewResult.SHOW_EFFECT;
  }
  
  public void depositLiquid(World world, int posX, int posY, int posZ, int side, boolean enhanced) {
    int x = posX + (side == 5 ? 1 : side == 4 ? -1 : 0);
    int z = posZ + (side == 3 ? 1 : side == 2 ? -1 : 0);
    int y = posY + (side == 1 ? 1 : side == 0 ? -1 : 0);
    if ((side == 1) && (!world.func_147439_a(x, posY, z).func_149688_o().func_76220_a())) {
      y--;
    }
    setBlockIfNotSolid(world, x, y, z, fluid.getBlock());
  }
}
