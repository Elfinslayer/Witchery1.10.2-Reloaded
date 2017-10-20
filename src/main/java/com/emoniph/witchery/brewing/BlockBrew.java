package com.emoniph.witchery.brewing;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockBrew extends BlockFluidClassic
{
  @SideOnly(Side.CLIENT)
  protected IIcon[] icons;
  
  public BlockBrew(Fluid fluid)
  {
    super(fluid, Material.field_151586_h);
    quantaPerBlock = 2;
  }
  
  public Block func_149663_c(String blockName)
  {
    com.emoniph.witchery.util.BlockUtil.registerBlock(this, blockName);
    

    return super.func_149663_c(blockName);
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
    


    FluidsBREW.setIcons(icons[0], icons[1]);
  }
  

  public boolean canDisplace(net.minecraft.world.IBlockAccess world, int x, int y, int z)
  {
    if (world.func_147439_a(x, y, z).func_149688_o().func_76224_d()) {
      return false;
    }
    return super.canDisplace(world, x, y, z);
  }
  
  public boolean displaceIfPossible(World world, int x, int y, int z)
  {
    if (world.func_147439_a(x, y, z).func_149688_o().func_76224_d()) {
      return false;
    }
    return super.displaceIfPossible(world, x, y, z);
  }
}
