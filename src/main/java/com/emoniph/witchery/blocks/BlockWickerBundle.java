package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.entity.EntityHornedHuntsman;
import com.emoniph.witchery.util.MultiItemBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockWickerBundle
  extends BlockBaseRotatedPillar
{
  private static final String[] bundleType = { "plain", "bloodied" };
  @SideOnly(Side.CLIENT)
  private IIcon[] block_side;
  
  public static class ClassItemBlock extends MultiItemBlock { public ClassItemBlock(Block block) { super(); }
    

    protected String[] getNames()
    {
      return BlockWickerBundle.bundleType;
    }
  }
  


  @SideOnly(Side.CLIENT)
  private IIcon[] block_top;
  
  public BlockWickerBundle()
  {
    super(Material.field_151575_d, ClassItemBlock.class);
    
    func_149711_c(0.5F);
    func_149672_a(field_149779_h);
  }
  


  public Block func_149663_c(String blockName)
  {
    super.func_149663_c(blockName);
    Blocks.field_150480_ab.setFireInfo(this, 20, 20);
    return this;
  }
  

  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float xOffset, float yOffset, float zOffset)
  {
    ItemStack heldItem = player.func_70694_bm();
    if ((heldItem != null) && (heldItem.func_77973_b() == Items.field_151033_d))
    {
      if (!tryIgniteMan(world, x, y, z, field_70177_z)) {
        return false;
      }
      
      return true;
    }
    return false;
  }
  
  public static boolean tryIgniteMan(World world, int x, int y, int z, float rotationYaw) {
    boolean xleft = world.func_147439_a(x - 1, y, z) == BlocksWICKER_BUNDLE;
    boolean xright = world.func_147439_a(x + 1, y, z) == BlocksWICKER_BUNDLE;
    boolean zleft = world.func_147439_a(x, y, z - 1) == BlocksWICKER_BUNDLE;
    boolean zright = world.func_147439_a(x, y, z + 1) == BlocksWICKER_BUNDLE;
    int dx = x;
    int dy = y;
    int dz = z;
    int fz = 0;
    int fx = 0;
    if (((!xleft) && (!xright)) || ((zleft) || (zright) || ((!xleft) && (!xright) && (!zleft) && (!zright))))
      return false;
    if ((xleft) || (xright))
    {
      if ((xleft) && (!xright)) {
        dx--;
      } else if (!xleft) {
        dx++;
      }
      while (world.func_147439_a(dx, dy - 1, dz) == BlocksWICKER_BUNDLE) {
        dy--;
      }
      
      while (world.func_147439_a(dx - 1, dy, dz) == BlocksWICKER_BUNDLE) {
        dx--;
      }
      fx = 1;
    } else {
      if ((zleft) && (!zright)) {
        dz--;
      } else if (!zleft) {
        dz++;
      }
      while (world.func_147439_a(dx, dy - 1, dz) == BlocksWICKER_BUNDLE) {
        dy--;
      }
      
      while (world.func_147439_a(dx, dy, dz - 1) == BlocksWICKER_BUNDLE) {
        dz--;
      }
      fz = 1;
    }
    
    World w = world;
    if ((!wicker(w, dx, dy + 7, dz)) && (!wicker(w, dx + 1 * fx, dy + 7, dz + 1 * fz)) && (!wicker(w, dx - 1 * fx, dy + 6, dz - 1 * fz)) && (wicker(w, dx, dy + 6, dz)) && (wicker(w, dx + 1 * fx, dy + 6, dz + 1 * fz)) && (!wicker(w, dx + 2 * fx, dy + 6, dz + 2 * fz)) && (!wicker(w, dx - 1 * fx, dy + 5, dz - 1 * fz)) && (wicker(w, dx, dy + 5, dz)) && (wicker(w, dx + 1 * fx, dy + 5, dz + 1 * fz)) && (!wicker(w, dx + 2 * fx, dy + 5, dz + 2 * fz)) && (!wicker(w, dx - 2 * fx, dy + 4, dz - 2 * fz)) && (wicker(w, dx - 1 * fx, dy + 4, dz - 1 * fz)) && (wicker(w, dx, dy + 4, dz)) && (wicker(w, dx + 1 * fx, dy + 4, dz + 1 * fz)) && (wicker(w, dx + 2 * fx, dy + 4, dz + 2 * fz)) && (!wicker(w, dx + 3 * fx, dy + 4, dz + 3 * fz)) && (!wicker(w, dx - 2 * fx, dy + 3, dz - 2 * fz)) && (wicker(w, dx - 1 * fx, dy + 3, dz - 1 * fz)) && (wicker(w, dx, dy + 3, dz)) && (wicker(w, dx + 1 * fx, dy + 3, dz + 1 * fz)) && (wicker(w, dx + 2 * fx, dy + 3, dz + 2 * fz)) && (!wicker(w, dx + 3 * fx, dy + 3, dz + 3 * fz)) && (!wicker(w, dx - 2 * fx, dy + 2, dz - 2 * fz)) && (wicker(w, dx - 1 * fx, dy + 2, dz - 1 * fz)) && (wicker(w, dx, dy + 2, dz)) && (wicker(w, dx + 1 * fx, dy + 2, dz + 1 * fz)) && (wicker(w, dx + 2 * fx, dy + 2, dz + 2 * fz)) && (!wicker(w, dx + 3 * fx, dy + 2, dz + 3 * fz)) && (!wicker(w, dx - 1 * fx, dy + 1, dz - 1 * fz)) && (wicker(w, dx, dy + 1, dz)) && (wicker(w, dx + 1 * fx, dy, dz + 1 * fz)) && (!wicker(w, dx + 2 * fx, dy + 1, dz + 2 * fz)) && (!wicker(w, dx - 1 * fx, dy, dz - 1 * fz)) && (wicker(w, dx, dy, dz)) && (wicker(w, dx + 1 * fx, dy, dz + 1 * fz)) && (!wicker(w, dx + 2 * fx, dy, dz + 2 * fz)))
    {















      world.func_147449_b(dx, dy + 6, dz, Blocks.field_150480_ab);
      world.func_147449_b(dx + 1 * fx, dy + 6, dz + 1 * fz, Blocks.field_150480_ab);
      
      world.func_147449_b(dx, dy + 3, dz, Blocks.field_150480_ab);
      world.func_147449_b(dx + 1 * fx, dy + 3, dz + 1 * fz, Blocks.field_150480_ab);
      
      world.func_147449_b(dx, dy + 2, dz, Blocks.field_150480_ab);
      world.func_147449_b(dx + 1 * fx, dy + 2, dz + 1 * fz, Blocks.field_150480_ab);
      
      world.func_147449_b(dx, dy + 1, dz, Blocks.field_150480_ab);
      world.func_147449_b(dx + 1 * fx, dy + 1, dz + 1 * fz, Blocks.field_150480_ab);
      
      world.func_147449_b(dx, dy + 0, dz, Blocks.field_150480_ab);
      world.func_147449_b(dx + 1 * fx, dy + 0, dz + 1 * fz, Blocks.field_150480_ab);
      
      world.func_147449_b(dx - 1 * fx, dy + 4, dz - 1 * fz, Blocks.field_150480_ab);
      world.func_147449_b(dx + 2 * fx, dy + 4, dz + 2 * fz, Blocks.field_150480_ab);
      
      EntityHornedHuntsman entity = new EntityHornedHuntsman(world);
      entity.func_70012_b(dx + 1.0D * fx + 0.5D * fz, dy + 0.1D, dz + 1.0D * fz + 0.5D * fx, 180.0F + rotationYaw, 0.0F);
      

      field_70759_as = field_70177_z;
      field_70761_aq = field_70177_z;
      entity.func_110163_bv();
      entity.func_82206_m();
      entity.func_70642_aH();
      if (!field_72995_K) {
        world.func_72838_d(entity);
      }
      

      for (int j1 = 0; j1 < 120; j1++) {
        world.func_72869_a("snowballpoof", dx + field_73012_v.nextDouble(), dy - 2 + field_73012_v.nextDouble() * 3.9D, dz + 1 + field_73012_v.nextDouble(), 0.0D, 0.0D, 0.0D);
      }
    }
    
    return true;
  }
  
  private static boolean wicker(World world, int x, int y, int z) {
    return (world.func_147439_a(x, y, z) == BlocksWICKER_BUNDLE) && (limitToValidMetadata(world.func_72805_g(x, y, z)) == 1);
  }
  
  public int func_149645_b()
  {
    return 31;
  }
  
  public int func_149692_a(int meta)
  {
    return limitToValidMetadata(meta);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149666_a(Item item, CreativeTabs creativeTabs, List list)
  {
    for (int i = 0; i < bundleType.length; i++) {
      list.add(new ItemStack(item, 1, i));
    }
  }
  
  public int func_149745_a(Random par1Random)
  {
    return 1;
  }
  
  public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
  {
    int metadata = world.func_72805_g(x, y, z);
    return new ItemStack(this, 1, metadata >= 0 ? limitToValidMetadata(metadata) : 0);
  }
  
  @SideOnly(Side.CLIENT)
  protected IIcon func_150163_b(int meta)
  {
    return block_side[net.minecraft.util.MathHelper.func_76125_a(meta, 0, 1)];
  }
  
  @SideOnly(Side.CLIENT)
  protected IIcon func_150161_d(int meta)
  {
    return block_top[net.minecraft.util.MathHelper.func_76125_a(meta, 0, 1)];
  }
  
  public static int limitToValidMetadata(int par0) {
    return par0 & bundleType.length - 1;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister iconRegister)
  {
    block_side = new IIcon[bundleType.length];
    block_top = new IIcon[bundleType.length];
    
    for (int i = 0; i < bundleType.length; i++) {
      block_side[i] = iconRegister.func_94245_a(func_149641_N() + "_" + bundleType[i] + "_side");
      block_top[i] = iconRegister.func_94245_a(func_149641_N() + "_" + bundleType[i] + "_top");
    }
  }
  
  public boolean isFlammable(IBlockAccess world, int x, int y, int z, ForgeDirection face)
  {
    boolean flammable = super.isFlammable(world, x, y, z, face);
    return flammable;
  }
}
