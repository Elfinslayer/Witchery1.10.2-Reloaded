package com.emoniph.witchery.brewing;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockBaseContainer;
import com.emoniph.witchery.blocks.TileEntityBase;
import com.emoniph.witchery.util.BlockProtect;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.RegistryNamespaced;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayerFactory;

public class BlockSlurp extends BlockBaseContainer
{
  public BlockSlurp()
  {
    super(net.minecraft.block.material.Material.field_151592_s, TileEntitySlurp.class);
    registerWithCreateTab = false;
  }
  
  public int func_149645_b() {
    return -1;
  }
  
  public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
    return null;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public boolean func_149678_a(int p_149678_1_, boolean p_149678_2_) {
    return false;
  }
  
  public boolean func_149646_a(IBlockAccess iblockaccess, int i, int j, int k, int l)
  {
    return false;
  }
  
  public void func_149690_a(World world, int x, int y, int z, int metadata, float chance, int fortune) {}
  
  public void replaceBlockAt(World world, int x, int y, int z, int timeoutTicks)
  {
    if (!field_72995_K) {
      Block block = world.func_147439_a(x, y, z);
      if ((BlockProtect.canBreak(block, world)) && (BlockProtect.checkModsForBreakOK(world, x, y, z, FakePlayerFactory.getMinecraft((WorldServer)world))))
      {

        int meta = world.func_72805_g(x, y, z);
        world.func_147449_b(x, y, z, BlocksSLURP);
        TileEntitySlurp tile = (TileEntitySlurp)com.emoniph.witchery.util.BlockUtil.getTileEntity(world, x, y, z, TileEntitySlurp.class);
        if (tile != null) {
          tile.saveBlock(timeoutTicks, block, meta);
        }
      }
    }
  }
  
  public static class TileEntitySlurp extends TileEntityBase {
    private Block savedBlock;
    private int savedMeta;
    private int timeout;
    
    public TileEntitySlurp() {}
    
    public void func_145845_h() {
      super.func_145845_h();
      if ((!field_145850_b.field_72995_K) && (ticks >= timeout)) {
        if (savedBlock == null) {
          field_145850_b.func_147468_f(field_145851_c, field_145848_d, field_145849_e);
        } else {
          field_145850_b.func_147465_d(field_145851_c, field_145848_d, field_145849_e, savedBlock, Math.max(savedMeta, 0), 3);
        }
      }
    }
    
    public void saveBlock(int timeoutTicks, Block block) {
      saveBlock(timeoutTicks, block, 0);
    }
    
    public void saveBlock(int timeoutTicks, Block block, int meta) {
      savedBlock = block;
      savedMeta = meta;
      timeout = timeoutTicks;
    }
    
    public void func_145841_b(NBTTagCompound nbtRoot)
    {
      super.func_145841_b(nbtRoot);
      nbtRoot.func_74768_a("Timeout", Math.max(timeout, 0));
      if (savedBlock != null) {
        nbtRoot.func_74778_a("blockName", Block.field_149771_c.func_148750_c(savedBlock));
        nbtRoot.func_74768_a("blockMeta", savedMeta);
      }
    }
    
    public void func_145839_a(NBTTagCompound nbtRoot)
    {
      super.func_145839_a(nbtRoot);
      timeout = Math.max(nbtRoot.func_74762_e("Timeout"), 0);
      savedBlock = null;
      savedMeta = 0;
      if (nbtRoot.func_74764_b("blockName")) {
        String blockName = nbtRoot.func_74779_i("blockName");
        if ((blockName != null) && (!blockName.isEmpty())) {
          savedBlock = Block.func_149684_b(blockName);
          savedMeta = nbtRoot.func_74762_e("blockMeta");
        }
      }
    }
  }
}
