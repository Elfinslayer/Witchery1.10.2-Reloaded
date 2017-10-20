package com.emoniph.witchery.blocks;

import com.emoniph.witchery.WitcheryCreativeTab;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.TimeUtil;
import cpw.mods.fml.common.registry.GameRegistry;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.common.ChestGenHooks;

public class BlockRefillingChest extends BlockChest
{
  public BlockRefillingChest()
  {
    super(0);
    func_149752_b(9999.0F);
    func_149722_s();
  }
  
  public Block func_149663_c(String blockName)
  {
    func_149647_a(WitcheryCreativeTab.INSTANCE);
    
    BlockUtil.registerBlock(this, blockName);
    GameRegistry.registerTileEntity(TileEntityRefillingChest.class, blockName);
    
    return super.func_149663_c(blockName);
  }
  
  public TileEntity func_149915_a(World world, int metadata)
  {
    return new TileEntityRefillingChest();
  }
  
  public static class TileEntityRefillingChest extends TileEntityChest {
    protected long ticks = 0L;
    
    public TileEntityRefillingChest() {}
    
    public void func_145845_h() { super.func_145845_h();
      
      if (ticks == 0L) {
        initiate();
      } else if (ticks >= Long.MAX_VALUE) {
        ticks = 1L;
      }
      
      ticks += 1L;
      
      doUpdate();
    }
    
    protected void initiate() {
      doUpdate();
    }
    
    private static final int MAX_ITEMS_FOR_REFILL = 0;
    protected void doUpdate()
    {
      if ((!field_145850_b.field_72995_K) && (field_145850_b.field_73011_w.field_76574_g == instancedimensionTormentID) && (TimeUtil.secondsElapsed(3600, ticks)) && (com.emoniph.witchery.util.InvUtil.getItemStackCount(this) <= 0))
      {
        int numItems = 2 + field_145850_b.field_73012_v.nextInt(4);
        ChestGenHooks gen = ChestGenHooks.getInfo("dungeonChest");
        WeightedRandomChestContent.func_76293_a(field_145850_b.field_73012_v, gen.getItems(field_145850_b.field_73012_v), this, numItems);
      }
    }
    
    public void func_145841_b(NBTTagCompound nbtChest)
    {
      super.func_145841_b(nbtChest);
      nbtChest.func_74772_a("WITCLifeTicks", ticks);
    }
    
    public void func_145839_a(NBTTagCompound nbtChest)
    {
      super.func_145839_a(nbtChest);
      ticks = nbtChest.func_74763_f("WITCLifeTicks");
    }
  }
}
