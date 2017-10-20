package com.emoniph.witchery.blocks;

import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class BlockStatueGoddess
  extends BlockBaseContainer
{
  public BlockStatueGoddess()
  {
    super(Material.field_151576_e, TileEntityStatueGoddess.class);
    func_149722_s();
    func_149752_b(1000.0F);
    func_149711_c(2.5F);
    func_149672_a(field_149769_e);
    func_149676_a(0.0F, 0.0F, 0.1F, 1.0F, 2.0F, 0.9F);
  }
  

  public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
  {
    int l = MathHelper.func_76128_c(field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3;
    
    if (l == 0) {
      par1World.func_72921_c(par2, par3, par4, 2, 2);
    }
    
    if (l == 1) {
      par1World.func_72921_c(par2, par3, par4, 5, 2);
    }
    
    if (l == 2) {
      par1World.func_72921_c(par2, par3, par4, 3, 2);
    }
    
    if (l == 3) {
      par1World.func_72921_c(par2, par3, par4, 4, 2);
    }
    
    if ((!field_72995_K) && ((par5EntityLivingBase instanceof EntityPlayer))) {
      EntityPlayer player = (EntityPlayer)par5EntityLivingBase;
      TileEntityStatueGoddess tile = (TileEntityStatueGoddess)BlockUtil.getTileEntity(par1World, par2, par3, par4, TileEntityStatueGoddess.class);
      if (tile != null) {
        tile.setOwner(player.func_70005_c_());
      }
    }
  }
  





  public void func_149699_a(World world, int x, int y, int z, EntityPlayer player)
  {
    if (!field_72995_K) {
      TileEntityStatueGoddess tile = (TileEntityStatueGoddess)BlockUtil.getTileEntity(world, x, y, z, TileEntityStatueGoddess.class);
      if ((tile != null) && (
        (field_71075_bZ.field_75098_d) || ((player.func_70005_c_().equals(tile.getOwner())) && (player.func_70093_af())))) {
        int dy = y;
        while (world.func_147439_a(x, dy, z) == this) {
          world.func_147468_f(x, dy, z);
          world.func_72838_d(new EntityItem(world, 0.5D + x, 0.5D + dy, 0.5D + z, new ItemStack(this)));
          dy++;
        }
      }
    }
  }
  

  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
  {
    if (!field_72995_K)
    {

      NBTTagCompound nbtTag = Infusion.getNBT(player);
      if ((nbtTag != null) && ((nbtTag.func_74764_b("witcheryCursed")) || (nbtTag.func_74764_b("witcheryInsanity")) || (nbtTag.func_74764_b("witcherySinking")) || (nbtTag.func_74764_b("witcheryOverheating")) || (nbtTag.func_74764_b("witcheryWakingNightmare"))))
      {



        if (nbtTag.func_74764_b("witcheryCursed")) {
          nbtTag.func_82580_o("witcheryCursed");
          ChatUtil.sendTranslated(EnumChatFormatting.BLUE, player, "tile.witcheryStatusGoddess.curemisfortune", new Object[0]);
        }
        
        if (nbtTag.func_74764_b("witcheryInsanity")) {
          nbtTag.func_82580_o("witcheryInsanity");
          ChatUtil.sendTranslated(EnumChatFormatting.BLUE, player, "tile.witcheryStatusGoddess.cureinsanity", new Object[0]);
        }
        
        if (nbtTag.func_74764_b("witcherySinking")) {
          nbtTag.func_82580_o("witcherySinking");
          ChatUtil.sendTranslated(EnumChatFormatting.BLUE, player, "tile.witcheryStatusGoddess.curesinking", new Object[0]);
          Infusion.syncPlayer(world, player);
        }
        
        if (nbtTag.func_74764_b("witcheryOverheating")) {
          nbtTag.func_82580_o("witcheryOverheating");
          ChatUtil.sendTranslated(EnumChatFormatting.BLUE, player, "tile.witcheryStatusGoddess.cureoverheating", new Object[0]);
        }
        
        if (nbtTag.func_74764_b("witcheryWakingNightmare")) {
          nbtTag.func_82580_o("witcheryWakingNightmare");
          ChatUtil.sendTranslated(EnumChatFormatting.BLUE, player, "tile.witcheryStatusGoddess.curenightmare", new Object[0]);
        }
        
        if (player.func_70644_a(Potion.field_76436_u)) {
          player.func_82170_o(field_76436_ufield_76415_H);
        }
        if (player.func_70644_a(Potion.field_76437_t)) {
          player.func_82170_o(field_76437_tfield_76415_H);
        }
        if (player.func_70644_a(Potion.field_76440_q)) {
          player.func_82170_o(field_76440_qfield_76415_H);
        }
        if (player.func_70644_a(Potion.field_76419_f)) {
          player.func_82170_o(field_76419_ffield_76415_H);
        }
        if (player.func_70644_a(Potion.field_76421_d)) {
          player.func_82170_o(field_76421_dfield_76415_H);
        }
        
        ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_FIZZ, player, 1.0D, 2.0D, 8);
      } else {
        SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
      }
      
      return true;
    }
    return super.func_149727_a(world, x, y, z, player, par6, par7, par8, par9);
  }
  
  public boolean func_149662_c()
  {
    return false;
  }
  
  public boolean func_149686_d()
  {
    return false;
  }
  
  public int func_149745_a(Random rand)
  {
    return 1;
  }
  
  @SideOnly(Side.CLIENT)
  public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
  {
    return false;
  }
  

  @SideOnly(Side.CLIENT)
  public void func_149734_b(World world, int x, int y, int z, Random rand) {}
  

  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister par1IconRegister)
  {
    field_149761_L = par1IconRegister.func_94245_a("stone");
  }
  
  public static class TileEntityStatueGoddess extends TileEntity {
    private static final String OWNER_KEY = "WITCPlacer";
    private String owner;
    
    public TileEntityStatueGoddess() {}
    
    public boolean canUpdate() {
      return false;
    }
    
    public void setOwner(String username) {
      owner = username;
    }
    
    public String getOwner() {
      return owner != null ? owner : "";
    }
    
    public void func_145841_b(NBTTagCompound nbtTag)
    {
      super.func_145841_b(nbtTag);
      nbtTag.func_74778_a("WITCPlacer", getOwner());
    }
    
    public void func_145839_a(NBTTagCompound nbtTag)
    {
      super.func_145839_a(nbtTag);
      if (nbtTag.func_74764_b("WITCPlacer")) {
        owner = nbtTag.func_74779_i("WITCPlacer");
      } else {
        owner = "";
      }
    }
  }
}
