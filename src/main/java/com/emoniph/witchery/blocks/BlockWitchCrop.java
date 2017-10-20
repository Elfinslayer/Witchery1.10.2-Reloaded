package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.entity.EntityMandrake;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import com.emoniph.witchery.network.PacketParticles;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.ParticleEffect;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockWitchCrop extends BlockBaseBush implements IGrowable
{
  @SideOnly(Side.CLIENT)
  private IIcon[] iconArray;
  private ItemStack seedItemPrototype;
  private ItemStack cropItemPrototype;
  private final int growthStages;
  private final boolean waterPlant;
  private final boolean canFertilize;
  private static final int MIN_LIGHT_LEVEL = 9;
  private static final double NIGHT_MANDRAKE_SPAWN_CHANCE = 0.1D;
  private static final double DAY_MANDRAKE_SPAWN_CHANCE = 0.9D;
  
  public BlockWitchCrop(boolean waterPlant)
  {
    this(waterPlant, 4, true);
  }
  
  public BlockWitchCrop(boolean waterPlant, int growthStages, boolean canFertilize) {
    super(Material.field_151585_k);
    registerWithCreateTab = false;
    
    this.growthStages = growthStages;
    this.waterPlant = waterPlant;
    this.canFertilize = canFertilize;
    func_149675_a(true);
    
    float f = 0.5F;
    func_149676_a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
    func_149711_c(0.0F);
    func_149672_a(Block.field_149779_h);
    func_149649_H();
  }
  
  public BlockWitchCrop setSeedItem(ItemStack stack) {
    seedItemPrototype = stack;
    return this;
  }
  
  public BlockWitchCrop setCropItem(ItemStack stack) {
    cropItemPrototype = (stack != null ? stack : seedItemPrototype.func_77946_l());
    return this;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister iconRegister)
  {
    iconArray = new IIcon[getNumGrowthStages() + 1];
    
    for (int i = 0; i < iconArray.length; i++) {
      iconArray[i] = iconRegister.func_94245_a(func_149641_N() + "_stage_" + i);
    }
  }
  
  protected boolean func_149854_a(Block block)
  {
    return block == net.minecraft.init.Blocks.field_150355_j;
  }
  


  public int getNumGrowthStages()
  {
    return growthStages;
  }
  
  public void func_149674_a(World world, int posX, int posY, int posZ, Random rand)
  {
    super.func_149674_a(world, posX, posY, posZ, rand);
    
    if (world.func_72957_l(posX, posY + 1, posZ) >= 9) {
      int l = world.func_72805_g(posX, posY, posZ);
      
      if (l < getNumGrowthStages()) {
        float f = getGrowthRate(world, posX, posY, posZ);
        
        if (rand.nextInt((int)(25.0F / f) + 1) == 0) {
          l++;
          world.func_72921_c(posX, posY, posZ, l, 2);
        }
      } else if (this == BlocksCROP_WORMWOOD) {
        Block blockBelow = BlockUtil.getBlock(world, posX, posY - 1, posZ);
        if ((blockBelow != this) && (world.func_147437_c(posX, posY + 1, posZ))) {
          BlockUtil.setBlock(world, posX, posY + 1, posZ, this, 0, 3);
        }
      }
    }
  }
  
  public boolean fertilize(World world, int posX, int posY, int posZ) {
    if (!field_72995_K) {
      int stages = getNumGrowthStages();
      int current = world.func_72805_g(posX, posY, posZ);
      if (current == stages) {
        return false;
      }
      int l;
      int l;
      if (!canFertilize) {
        l = current + 1;
      } else {
        l = current + MathHelper.func_76136_a(field_73012_v, 2, stages);
      }
      
      if (l > stages) {
        l = stages;
      }
      
      world.func_72921_c(posX, posY, posZ, l, 2);
      
      return true;
    }
    
    return false;
  }
  
  public boolean func_149851_a(World world, int x, int y, int z, boolean flag)
  {
    return world.func_72805_g(x, y, z) != getNumGrowthStages();
  }
  
  public boolean func_149852_a(World world, Random rand, int x, int y, int z)
  {
    return true;
  }
  
  public void func_149853_b(World world, Random rand, int x, int y, int z)
  {
    fertilize(world, x, y, z);
  }
  
  private float getGrowthRate(World world, int posX, int posY, int posZ) {
    float f = 1.0F;
    Block l = world.func_147439_a(posX, posY, posZ - 1);
    Block i1 = world.func_147439_a(posX, posY, posZ + 1);
    Block j1 = world.func_147439_a(posX - 1, posY, posZ);
    Block k1 = world.func_147439_a(posX + 1, posY, posZ);
    Block l1 = world.func_147439_a(posX - 1, posY, posZ - 1);
    Block i2 = world.func_147439_a(posX + 1, posY, posZ - 1);
    Block j2 = world.func_147439_a(posX + 1, posY, posZ + 1);
    Block k2 = world.func_147439_a(posX - 1, posY, posZ + 1);
    boolean flag = (j1 == this) || (k1 == this);
    boolean flag1 = (l == this) || (i1 == this);
    boolean flag2 = (l1 == this) || (i2 == this) || (j2 == this) || (k2 == this);
    
    for (int l2 = posX - 1; l2 <= posX + 1; l2++) {
      for (int i3 = posZ - 1; i3 <= posZ + 1; i3++) {
        Block j3 = world.func_147439_a(l2, posY - 1, i3);
        float f1 = 0.0F;
        
        if ((j3 != null) && (j3.canSustainPlant(world, l2, posY - 1, i3, ForgeDirection.UP, this))) {
          f1 = 1.0F;
          
          if (j3.isFertile(world, l2, posY - 1, i3)) {
            f1 = 3.0F;
          }
        }
        
        if ((l2 != posX) || (i3 != posZ)) {
          f1 /= 4.0F;
        }
        
        f += f1;
      }
    }
    
    if ((flag2) || ((flag) && (flag1))) {
      f /= 2.0F;
    }
    

    if (cropItemPrototype.func_77973_b() == ItemsSEEDS_MINDRAKE) {
      f /= 1.5F;
    }
    
    return f;
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int par1, int par2)
  {
    int stages = getNumGrowthStages();
    if ((par2 < 0) || (par2 > stages)) {
      par2 = stages;
    }
    
    return iconArray != null ? iconArray[par2] : null;
  }
  
  public int func_149645_b()
  {
    return (this == BlocksCROP_SNOWBELL) || (this == BlocksCROP_WOLFSBANE) || (this == BlocksCROP_WORMWOOD) ? 1 : 6;
  }
  
  protected ItemStack getSeedItemStack()
  {
    return seedItemPrototype.func_77946_l();
  }
  
  protected ItemStack getCropItemStack() {
    return cropItemPrototype.func_77946_l();
  }
  

  public void func_149690_a(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
  {
    super.func_149690_a(par1World, par2, par3, par4, par5, par6, 0);
  }
  



  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
  {
    ArrayList<ItemStack> ret = new ArrayList();
    Block block = world.func_147439_a(x, y, z);
    if (metadata >= getNumGrowthStages()) {
      if ((!ItemsGENERIC.itemMandrakeRoot.isMatch(cropItemPrototype)) || (field_73013_u == EnumDifficulty.PEACEFUL) || ((field_73011_w.isDaytime()) && (field_73012_v.nextDouble() > 0.9D)) || ((!field_73011_w.isDaytime()) && (field_73012_v.nextDouble() > 0.1D)))
      {



        if (cropItemPrototype.func_77973_b() == ItemsSEEDS_MINDRAKE) {
          ret.add(getSeedItemStack());
          if (field_73012_v.nextInt(4) == 0) {
            ret.add(getCropItemStack());
          }
        } else {
          for (int n = 0; n < 3 + fortune; n++) {
            if (field_73012_v.nextInt(15) <= 7) {
              ret.add(getSeedItemStack());
            }
          }
          
          for (int i = 0; i < func_149745_a(field_73012_v); i++) {
            ret.add(getCropItemStack());
          }
          
          if ((seedItemPrototype.func_77973_b() == ItemsSEEDS_SNOWBELL) && (field_73012_v.nextDouble() <= 0.2D))
          {
            ret.add(ItemsGENERIC.itemIcyNeedle.createStack());
          }
        }
      }
      else if (!field_72995_K) {
        EntityMandrake mandrake = new EntityMandrake(world);
        mandrake.func_70012_b(0.5D + x, 0.05D + y, 0.5D + z, 0.0F, 0.0F);
        world.func_72838_d(mandrake);
        Witchery.packetPipeline.sendToAllAround(new PacketParticles(ParticleEffect.EXPLODE, com.emoniph.witchery.util.SoundEffect.NONE, mandrake, 0.5D, 1.0D), com.emoniph.witchery.util.TargetPointUtil.from(mandrake, 16.0D));
      }
      
    }
    else {
      for (int i = 0; i < func_149745_a(field_73012_v); i++) {
        ret.add(getSeedItemStack());
      }
    }
    
    return ret;
  }
  

  protected void func_149642_a(World p_149642_1_, int p_149642_2_, int p_149642_3_, int p_149642_4_, ItemStack p_149642_5_)
  {
    if ((!field_72995_K) && (p_149642_1_.func_82736_K().func_82766_b("doTileDrops"))) {
      float f = 0.7F;
      double d0 = field_73012_v.nextFloat() * f + (1.0F - f) * 0.5D;
      double d1 = field_73012_v.nextFloat() * f + (1.0F - f) * 0.5D;
      double d2 = field_73012_v.nextFloat() * f + (1.0F - f) * 0.5D;
      EntityItem entityitem = new EntityItem(p_149642_1_, p_149642_2_ + d0, p_149642_3_ + d1, p_149642_4_ + d2, p_149642_5_);
      
      field_145804_b = 10;
      if ((p_149642_5_ != null) && (p_149642_5_.func_77973_b() == ItemsSEEDS_MINDRAKE)) {
        lifespan = com.emoniph.witchery.util.TimeUtil.secsToTicks(3);
      }
      p_149642_1_.func_72838_d(entityitem);
    }
  }
  
  public Item func_149650_a(int par1, Random rand, int par3)
  {
    return par1 == getNumGrowthStages() ? cropItemPrototype.func_77973_b() : seedItemPrototype.func_77973_b();
  }
  
  public int func_149692_a(int par1)
  {
    return par1 == getNumGrowthStages() ? cropItemPrototype.func_77960_j() : seedItemPrototype.func_77960_j();
  }
  

  public int func_149745_a(Random rand)
  {
    return 1;
  }
  
  public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
  {
    return seedItemPrototype;
  }
  
  public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
  {
    if (waterPlant) {
      return EnumPlantType.Water;
    }
    return super.getPlantType(world, x, y, z);
  }
}
