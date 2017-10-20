package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.common.ExtendedPlayer.VampireUltimate;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class BlockBloodCrucible extends BlockBaseContainer
{
  public BlockBloodCrucible()
  {
    super(Material.field_151576_e, TileEntityBloodCrucible.class);
    func_149752_b(1000.0F);
    func_149711_c(2.5F);
    func_149672_a(field_149769_e);
    func_149676_a(0.25F, 0.0F, 0.25F, 0.75F, 0.31F, 0.75F);
  }
  


  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
  {
    if (!field_72995_K) {
      TileEntityBloodCrucible crucible = (TileEntityBloodCrucible)com.emoniph.witchery.util.BlockUtil.getTileEntity(world, x, y, z, TileEntityBloodCrucible.class);
      
      if ((crucible != null) && 
        ((world instanceof WorldServer))) {
        ExtendedPlayer playerEx = ExtendedPlayer.get(player);
        ItemStack stack = player.func_70694_bm();
        if ((playerEx.getVampireLevel() >= 10) && ((crucible.isFull()) || (field_71075_bZ.field_75098_d)) && (stack != null)) {
          boolean success = false;
          if (ItemsGENERIC.itemArtichoke.isMatch(stack)) {
            playerEx.setVampireUltimate(ExtendedPlayer.VampireUltimate.STORM);
            success = true;
          } else if (ItemsGENERIC.itemBatWool.isMatch(stack)) {
            playerEx.setVampireUltimate(ExtendedPlayer.VampireUltimate.SWARM);
            success = true;
          } else if (stack.func_77973_b() == Items.field_151103_aS) {
            playerEx.setVampireUltimate(ExtendedPlayer.VampireUltimate.FARM);
            success = true;
          }
          
          if (success) {
            crucible.drainAll();
            field_77994_a -= 1;
            ParticleEffect.REDDUST.send(SoundEffect.RANDOM_FIZZ, world, 0.5D + x, y, 0.5D + z, 0.5D, 0.5D, 16);
          } else {
            ParticleEffect.SMOKE.send(SoundEffect.NOTE_SNARE, world, 0.5D + x, y, 0.5D + z, 0.5D, 0.5D, 16);
          }
        } else {
          ParticleEffect.SMOKE.send(SoundEffect.NOTE_SNARE, world, 0.5D + x, y, 0.5D + z, 0.5D, 0.5D, 16);
        }
      }
      
      return true;
    }
    return true;
  }
  
  public int func_149745_a(Random rand)
  {
    return 1;
  }
  
  @SideOnly(Side.CLIENT)
  public boolean func_149646_a(IBlockAccess world, int x, int y, int z, int side)
  {
    return false;
  }
  
  public boolean func_149662_c()
  {
    return false;
  }
  
  public boolean func_149686_d()
  {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149734_b(World world, int x, int y, int z, Random rand) {}
  
  public static class TileEntityBloodCrucible extends TileEntity
  {
    private static final int MAX_BLOOD_LEVEL = 20;
    private int bloodLevel;
    
    public TileEntityBloodCrucible() {}
    
    public boolean canUpdate() {
      return false;
    }
    
    public boolean isFull() {
      return bloodLevel == 20;
    }
    
    public void drainAll() {
      bloodLevel = 0;
      markBlockForUpdate(false);
    }
    
    public int getBloodLevel() {
      return bloodLevel;
    }
    
    public void increaseBloodLevel() {
      if (bloodLevel < 20) {
        bloodLevel = Math.min(5 + bloodLevel, 20);
        markBlockForUpdate(false);
      }
    }
    
    public float getPercentFilled() {
      return bloodLevel / 20.0F;
    }
    
    public void func_145841_b(NBTTagCompound nbtRoot)
    {
      super.func_145841_b(nbtRoot);
      nbtRoot.func_74768_a("BloodLevel", bloodLevel);
    }
    
    public void func_145839_a(NBTTagCompound nbtRoot)
    {
      super.func_145839_a(nbtRoot);
      bloodLevel = nbtRoot.func_74762_e("BloodLevel");
    }
    
    public void markBlockForUpdate(boolean notifyNeighbours) {
      field_145850_b.func_147471_g(field_145851_c, field_145848_d, field_145849_e);
      if ((notifyNeighbours) && (field_145850_b != null)) {
        field_145850_b.func_147444_c(field_145851_c, field_145848_d, field_145849_e, func_145838_q());
      }
    }
    
    public net.minecraft.network.Packet func_145844_m()
    {
      NBTTagCompound nbtTag = new NBTTagCompound();
      func_145841_b(nbtTag);
      return new S35PacketUpdateTileEntity(field_145851_c, field_145848_d, field_145849_e, 1, nbtTag);
    }
    
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
    {
      super.onDataPacket(net, packet);
      func_145839_a(packet.func_148857_g());
      field_145850_b.func_147479_m(field_145851_c, field_145848_d, field_145849_e);
    }
  }
}
