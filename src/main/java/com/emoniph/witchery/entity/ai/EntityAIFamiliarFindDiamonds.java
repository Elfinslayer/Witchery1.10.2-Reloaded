package com.emoniph.witchery.entity.ai;

import com.emoniph.witchery.entity.EntityFamiliar;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.SoundEffect;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityAIFamiliarFindDiamonds extends net.minecraft.entity.ai.EntityAIBase
{
  private final EntityFamiliar theFamiliar;
  private final double field_75404_b;
  private int currentTick;
  private int field_75402_d;
  private int maxSittingTicks;
  private int sitableBlockX;
  private int sitableBlockY;
  private int sitableBlockZ;
  
  public EntityAIFamiliarFindDiamonds(EntityFamiliar familiarEntity, double par2)
  {
    theFamiliar = familiarEntity;
    field_75404_b = par2;
    func_75248_a(5);
  }
  
  public boolean func_75250_a()
  {
    EntityLivingBase entitylivingbase = theFamiliar.func_70902_q();
    return (theFamiliar.func_70909_n()) && (!theFamiliar.func_70906_o()) && (theFamiliar.getBlockIDToFind() != null) && (entitylivingbase != null) && (theFamiliar.func_70068_e(entitylivingbase) < 100.0D) && (theFamiliar.func_70681_au().nextDouble() <= 0.1D) && (getNearbySitableBlockDistance());
  }
  


  public boolean func_75253_b()
  {
    EntityLivingBase entitylivingbase = theFamiliar.func_70902_q();
    return (currentTick <= maxSittingTicks) && (field_75402_d <= 60) && (entitylivingbase != null) && (theFamiliar.func_70068_e(entitylivingbase) < 100.0D) && (isSittableBlock(theFamiliar.field_70170_p, sitableBlockX, sitableBlockY, sitableBlockZ));
  }
  


  public void func_75249_e()
  {
    if (!theFamiliar.func_70661_as().func_75492_a(sitableBlockX + 0.5D, sitableBlockY + 1, sitableBlockZ + 0.5D, field_75404_b))
    {
      theFamiliar.func_70661_as().func_75492_a(sitableBlockX + 0.5D, theFamiliar.field_70163_u + 1.0D, sitableBlockZ + 0.5D, field_75404_b);
    }
    
    currentTick = 0;
    field_75402_d = 0;
    maxSittingTicks = (theFamiliar.func_70681_au().nextInt(theFamiliar.func_70681_au().nextInt(1200) + 1200) + 1200);
    theFamiliar.func_70907_r().func_75270_a(false);
  }
  
  public void func_75251_c()
  {
    theFamiliar.func_70904_g(false);
  }
  
  public void func_75246_d()
  {
    currentTick += 1;
    theFamiliar.func_70907_r().func_75270_a(false);
    
    if (theFamiliar.func_70092_e(sitableBlockX, theFamiliar.field_70163_u, sitableBlockZ) > 2.0D) {
      theFamiliar.func_70904_g(false);
      
      if (!theFamiliar.func_70661_as().func_75492_a(sitableBlockX + 0.5D, sitableBlockY + 1, sitableBlockZ + 0.5D, field_75404_b))
      {
        theFamiliar.func_70661_as().func_75492_a(sitableBlockX + 0.5D, theFamiliar.field_70163_u, sitableBlockZ + 0.5D, field_75404_b);
      }
      

      field_75402_d += 1;
    } else if (!theFamiliar.func_70906_o()) {
      EntityLivingBase living = theFamiliar.func_70902_q();
      if ((living != null) && 
        ((living instanceof EntityPlayer))) {
        EntityPlayer player = (EntityPlayer)living;
        SoundEffect.RANDOM_ORB.playAtPlayer(theFamiliar.field_70170_p, player);
        ChatUtil.sendTranslated(EnumChatFormatting.LIGHT_PURPLE, player, "witchery.familiar.foundsomething", new Object[] { Integer.valueOf(MathHelper.func_76128_c(theFamiliar.field_70165_t)).toString(), Integer.valueOf(MathHelper.func_76128_c(theFamiliar.field_70163_u)).toString(), Integer.valueOf(MathHelper.func_76128_c(theFamiliar.field_70161_v)).toString() });
      }
      



      theFamiliar.clearItemToFind();
      theFamiliar.setAISitting(true);
    } else {
      field_75402_d -= 1;
    }
  }
  

  protected boolean getNearbySitableBlockDistance()
  {
    int MAX_WIDTH = 4;
    int DEPTH = theFamiliar.getDepthToFind();
    
    for (int i = 1; i < DEPTH; i++) {
      for (int j = (int)theFamiliar.field_70165_t - 4; j < theFamiliar.field_70165_t + 4.0D; j++) {
        for (int k = (int)theFamiliar.field_70161_v - 4; k < theFamiliar.field_70161_v + 4.0D; k++) {
          if (isSittableBlock(theFamiliar.field_70170_p, j, i, k))
          {



            sitableBlockX = j;
            sitableBlockY = i;
            sitableBlockZ = k;
            

            return true;
          }
        }
      }
    }
    
    return false;
  }
  
  protected boolean isSittableBlock(World par1World, int par2, int par3, int par4) {
    Block blockID = theFamiliar.getBlockIDToFind();
    Block foundBlockID = par1World.func_147439_a(par2, par3, par4);
    if (blockID == Blocks.field_150482_ag)
      return (foundBlockID == blockID) || (foundBlockID == Blocks.field_150412_bA);
    if (blockID == Blocks.field_150412_bA) {
      return (foundBlockID == blockID) || (foundBlockID == Blocks.field_150482_ag);
    }
    return foundBlockID == blockID;
  }
}
