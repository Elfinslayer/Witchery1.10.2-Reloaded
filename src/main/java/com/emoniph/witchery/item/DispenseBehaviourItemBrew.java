package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.blocks.BlockKettle;
import com.emoniph.witchery.brewing.BlockCauldron;
import com.emoniph.witchery.brewing.EntityBrew;
import com.emoniph.witchery.brewing.WitcheryBrewRegistry;
import com.emoniph.witchery.entity.EntityGrenade;
import com.emoniph.witchery.util.SoundEffect;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;


public class DispenseBehaviourItemBrew
  implements IBehaviorDispenseItem
{
  private final BehaviorDefaultDispenseItem defaultDispenserItemBehavior;
  
  public DispenseBehaviourItemBrew() { defaultDispenserItemBehavior = new BehaviorDefaultDispenseItem(); }
  
  public ItemStack func_82482_a(IBlockSource block, ItemStack stack) {
    if ((stack.func_77973_b() == ItemsBREW) && (WitcheryBrewRegistry.INSTANCE.isSplash(stack.func_77978_p())))
      return new DispenserBehaviorBrew(this, stack).func_82482_a(block, stack);
    if (stack.func_77973_b() == Items.field_151069_bo) {
      EnumFacing facing = BlockDispenser.func_149937_b(block.func_82620_h());
      EnumFacing[] FACINGS = { EnumFacing.DOWN, EnumFacing.UP, EnumFacing.NORTH, EnumFacing.SOUTH, EnumFacing.EAST, EnumFacing.WEST };
      for (EnumFacing cauldronFacing : FACINGS) {
        if (cauldronFacing != facing) {
          int x = block.func_82623_d() + cauldronFacing.func_82601_c();
          int y = block.func_82622_e() + cauldronFacing.func_96559_d();
          int z = block.func_82621_f() + cauldronFacing.func_82599_e();
          Block replaceBlock = block.func_82618_k().func_147439_a(x, y, z);
          if (replaceBlock == BlocksCAULDRON) {
            ItemStack brew = BlocksCAULDRON.fillBottleFromCauldron(block.func_82618_k(), x, y, z, 3000);
            if (brew != null) {
              IPosition position = BlockDispenser.func_149939_a(block);
              BehaviorDefaultDispenseItem.func_82486_a(block.func_82618_k(), brew, 6, facing, position);
              stack.func_77979_a(1);
              block.func_82618_k().func_72926_e(1000, block.func_82623_d(), block.func_82622_e(), block.func_82621_f(), 0);
            }
            return stack;
          }
        }
      }
      
      return defaultDispenserItemBehavior.func_82482_a(block, stack); }
    if (stack.func_77973_b() == ItemsBREW_ENDLESS_WATER) {
      if (!func_82618_kfield_72995_K)
      {
        EnumFacing facing = BlockDispenser.func_149937_b(block.func_82620_h());
        int x = block.func_82623_d() + facing.func_82601_c();
        int y = block.func_82622_e() + facing.func_96559_d();
        int z = block.func_82621_f() + facing.func_82599_e();
        
        Block replaceBlock = block.func_82618_k().func_147439_a(x, y, z);
        net.minecraft.entity.player.EntityPlayer fakePlayer = net.minecraftforge.common.util.FakePlayerFactory.getMinecraft((WorldServer)block.func_82618_k());
        
        if (stack.func_77960_j() <= stack.func_77958_k()) {
          if ((com.emoniph.witchery.util.BlockUtil.isReplaceableBlock(block.func_82618_k(), x, y, z, fakePlayer)) && (replaceBlock.func_149688_o() != net.minecraft.block.material.Material.field_151586_h)) {
            stack.func_77972_a(1, fakePlayer);
            block.func_82618_k().func_147449_b(x, y, z, net.minecraft.init.Blocks.field_150358_i);
            block.func_82618_k().func_147471_g(x, y, z);
            SoundEffect.WATER_SPLASH.playAt(block.func_82618_k(), x, y, z);
          } else if (replaceBlock == BlocksCAULDRON) {
            if (BlocksCAULDRON.tryFillWith(block.func_82618_k(), x, y, z, new FluidStack(FluidRegistry.WATER, 3000))) {
              stack.func_77972_a(1, fakePlayer);
            }
          } else if ((replaceBlock == BlocksKETTLE) && 
            (BlocksKETTLE.tryFillWith(block.func_82618_k(), x, y, z, new FluidStack(FluidRegistry.WATER, 1000)))) {
            stack.func_77972_a(1, fakePlayer);
          }
        }
      }
      
      return stack; }
    if (stack.func_77973_b() == ItemsSUN_GRENADE)
      return new DispenserGrenade(this, stack).func_82482_a(block, stack);
    if (stack.func_77973_b() == ItemsDUP_GRENADE) {
      return new DispenserGrenade(this, stack).func_82482_a(block, stack);
    }
    return defaultDispenserItemBehavior.func_82482_a(block, stack);
  }
  
  public static class DispenserBehaviorBrew extends BehaviorDefaultDispenseItem
  {
    final ItemStack potionItemStack;
    final DispenseBehaviourItemBrew dispenserPotionBehavior;
    
    DispenserBehaviorBrew(DispenseBehaviourItemBrew behavior, ItemStack brewStack) {
      dispenserPotionBehavior = behavior;
      potionItemStack = brewStack;
    }
    
    public ItemStack func_82487_b(IBlockSource dispenserBlock, ItemStack stack) {
      World world = dispenserBlock.func_82618_k();
      IPosition iposition = BlockDispenser.func_149939_a(dispenserBlock);
      EnumFacing enumfacing = BlockDispenser.func_149937_b(dispenserBlock.func_82620_h());
      EntityBrew iprojectile = getProjectileEntity(world, iposition);
      iprojectile.setThrowableHeading(enumfacing.func_82601_c(), enumfacing.func_96559_d() + 0.1F, enumfacing.func_82599_e(), func_82500_b(), func_82498_a());
      

      world.func_72838_d(iprojectile);
      stack.func_77979_a(1);
      return stack;
    }
    
    protected void func_82485_a(IBlockSource dispenserBlock) {
      dispenserBlock.func_82618_k().func_72926_e(1002, dispenserBlock.func_82623_d(), dispenserBlock.func_82622_e(), dispenserBlock.func_82621_f(), 0);
    }
    
    protected EntityBrew getProjectileEntity(World world, IPosition position)
    {
      return new EntityBrew(world, position.func_82615_a(), position.func_82617_b(), position.func_82616_c(), potionItemStack, false);
    }
    
    protected float func_82498_a() {
      return 3.0F;
    }
    
    protected float func_82500_b() {
      return 1.375F;
    }
  }
  
  public static class DispenserGrenade extends BehaviorDefaultDispenseItem {
    final ItemStack potionItemStack;
    final DispenseBehaviourItemBrew dispenserPotionBehavior;
    
    DispenserGrenade(DispenseBehaviourItemBrew behavior, ItemStack brewStack) {
      dispenserPotionBehavior = behavior;
      potionItemStack = brewStack;
    }
    
    public ItemStack func_82487_b(IBlockSource dispenserBlock, ItemStack stack) {
      World world = dispenserBlock.func_82618_k();
      IPosition iposition = BlockDispenser.func_149939_a(dispenserBlock);
      EnumFacing enumfacing = BlockDispenser.func_149937_b(dispenserBlock.func_82620_h());
      EntityGrenade iprojectile = getProjectileEntity(world, iposition);
      iprojectile.setThrowableHeading(enumfacing.func_82601_c(), enumfacing.func_96559_d() + 0.1F, enumfacing.func_82599_e(), func_82500_b(), func_82498_a());
      

      world.func_72838_d(iprojectile);
      stack.func_77979_a(1);
      return stack;
    }
    
    protected void func_82485_a(IBlockSource dispenserBlock) {
      dispenserBlock.func_82618_k().func_72926_e(1002, dispenserBlock.func_82623_d(), dispenserBlock.func_82622_e(), dispenserBlock.func_82621_f(), 0);
    }
    
    protected EntityGrenade getProjectileEntity(World world, IPosition position)
    {
      return new EntityGrenade(world, position.func_82615_a(), position.func_82617_b(), position.func_82616_c(), potionItemStack);
    }
    
    protected float func_82498_a() {
      return 3.0F;
    }
    
    protected float func_82500_b() {
      return 1.375F;
    }
  }
}
