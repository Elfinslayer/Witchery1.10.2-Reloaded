package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.CreatureUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class BlockFlowingSpirit extends BlockFluidClassic
{
  protected final boolean nightmareBane;
  protected final boolean igniteSpiritPortals;
  protected final PotionEffect goodyEffect;
  protected final PotionEffect baddyEffect;
  @SideOnly(Side.CLIENT)
  protected IIcon[] icons;
  
  public BlockFlowingSpirit(Fluid fluid, PotionEffect goodyEffect, PotionEffect baddyEffect, boolean nightmareBane, boolean igniteSpiritPortals)
  {
    super(fluid, Material.field_151586_h);
    quantaPerBlock = 5;
    func_149711_c(100.0F);
    func_149713_g(3);
    this.goodyEffect = goodyEffect;
    this.baddyEffect = baddyEffect;
    this.nightmareBane = nightmareBane;
    this.igniteSpiritPortals = igniteSpiritPortals;
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
  
  public void func_149726_b(World world, int x, int y, int z)
  {
    if ((!igniteSpiritPortals) || (field_73011_w.field_76574_g != instancedimensionDreamID) || (world.func_147439_a(x, y - 1, z) != net.minecraft.init.Blocks.field_150433_aE) || (world.func_72805_g(x, y, z) != 0) || (!BlocksSPIRIT_PORTAL.tryToCreatePortal(world, x, y, z)))
    {



      super.func_149726_b(world, x, y, z);
    }
  }
  
  public void func_149670_a(World world, int x, int y, int z, Entity entity)
  {
    if ((!field_72995_K) && (entity != null)) {
      if ((entity instanceof EntityLivingBase)) {
        EntityLivingBase livingEntity = (EntityLivingBase)entity;
        if ((CreatureUtil.isUndead(livingEntity)) || (CreatureUtil.isDemonic(livingEntity)) || ((nightmareBane) && ((livingEntity instanceof com.emoniph.witchery.entity.EntityNightmare))))
        {
          if (!livingEntity.func_82165_m(baddyEffect.func_76456_a())) {
            livingEntity.func_70690_d(new PotionEffect(baddyEffect));
          }
        }
        else if (!livingEntity.func_82165_m(goodyEffect.func_76456_a())) {
          livingEntity.func_70690_d(new PotionEffect(goodyEffect));
        }
      }
      else if ((nightmareBane) && ((entity instanceof EntityItem))) {
        EntityItem item = (EntityItem)entity;
        ItemStack stack = item.func_92059_d();
        if (ItemsGENERIC.itemDisturbedCotton.isMatch(stack)) {
          ItemStack newStack = new ItemStack(BlocksWISPY_COTTON, field_77994_a);
          item.func_92058_a(newStack);
        }
      }
    }
  }
  
  public boolean canDisplace(IBlockAccess world, int x, int y, int z)
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
