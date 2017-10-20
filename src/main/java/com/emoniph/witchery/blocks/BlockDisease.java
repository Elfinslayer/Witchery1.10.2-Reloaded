package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.brewing.potions.WitcheryPotions;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.TimeUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class BlockDisease extends BlockFluidClassic
{
  @SideOnly(Side.CLIENT)
  protected IIcon[] icons;
  
  public BlockDisease(Fluid fluid)
  {
    super(fluid, net.minecraft.block.material.Material.field_151597_y);
    quantaPerBlock = 1;
    func_149711_c(100.0F);
    func_149713_g(1);
  }
  
  public int getMaxRenderHeightMeta()
  {
    return 16;
  }
  
  public void func_149674_a(World world, int x, int y, int z, Random rand)
  {
    super.func_149674_a(world, x, y, z, rand);
    int chance = instancediseaseRemovalChance;
    if (chance > 0) {
      if (field_73012_v.nextInt(chance) == 0) {
        world.func_147468_f(x, y, z);
      }
      world.func_147464_a(x, y, z, this, tickRate);
    }
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
    
    if ((stack != null) && (stack.getFluid() != null)) {
      stack.getFluid().setIcons(icons[0], icons[1]);
    }
  }
  
  public void func_149670_a(World world, int x, int y, int z, Entity entity)
  {
    if ((!field_72995_K) && (entity != null) && 
      ((entity instanceof EntityLivingBase))) {
      EntityLivingBase livingEntity = (EntityLivingBase)entity;
      if ((!com.emoniph.witchery.util.CreatureUtil.isImmuneToDisease(livingEntity)) && 
        (!livingEntity.func_70644_a(PotionsDISEASED)) && (field_73012_v.nextInt(3) == 0)) {
        livingEntity.func_70690_d(new PotionEffect(PotionsDISEASED.field_76415_H, TimeUtil.minsToTicks(1 + field_73012_v.nextInt(4))));
      }
    }
  }
  







  public boolean canDisplace(IBlockAccess world, int x, int y, int z)
  {
    return super.canDisplace(world, x, y, z);
  }
  




  public boolean displaceIfPossible(World world, int x, int y, int z)
  {
    return super.displaceIfPossible(world, x, y, z);
  }
}
