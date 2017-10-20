package com.emoniph.witchery.entity.ai;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.world.World;







public class EntityAIToadSit
  extends EntityAIBase
{
  private final EntityTameable theOcelot;
  private final double field_75404_b;
  private int currentTick;
  private int field_75402_d;
  private int maxSittingTicks;
  private int sitableBlockX;
  private int sitableBlockY;
  private int sitableBlockZ;
  
  public EntityAIToadSit(EntityTameable par1EntityOcelot, double par2)
  {
    theOcelot = par1EntityOcelot;
    field_75404_b = par2;
    func_75248_a(5);
  }
  



  public boolean func_75250_a()
  {
    return (!theOcelot.func_70906_o()) && (theOcelot.func_70681_au().nextDouble() <= 0.006500000134110451D) && (getNearbySitableBlockDistance());
  }
  



  public boolean func_75253_b()
  {
    return (currentTick <= maxSittingTicks) && (field_75402_d <= 60) && (isSittableBlock(theOcelot.field_70170_p, sitableBlockX, sitableBlockY, sitableBlockZ));
  }
  



  public void func_75249_e()
  {
    theOcelot.func_70661_as().func_75492_a(sitableBlockX + 0.5D, sitableBlockY + 1, sitableBlockZ + 0.5D, field_75404_b);
    currentTick = 0;
    field_75402_d = 0;
    maxSittingTicks = (theOcelot.func_70681_au().nextInt(theOcelot.func_70681_au().nextInt(1200) + 1200) + 1200);
  }
  




  public void func_75251_c()
  {
    theOcelot.func_70904_g(false);
  }
  



  public void func_75246_d()
  {
    currentTick += 1;
    theOcelot.func_70907_r().func_75270_a(false);
    
    if (theOcelot.func_70092_e(sitableBlockX, sitableBlockY + 1, sitableBlockZ) > 1.0D)
    {
      theOcelot.func_70904_g(false);
      theOcelot.func_70661_as().func_75492_a(sitableBlockX + 0.5D, sitableBlockY + 1, sitableBlockZ + 0.5D, field_75404_b);
      field_75402_d += 1;
    }
    else if (!theOcelot.func_70906_o())
    {
      theOcelot.func_70904_g(true);
    }
    else
    {
      field_75402_d -= 1;
    }
  }
  



  protected boolean getNearbySitableBlockDistance()
  {
    int i = (int)theOcelot.field_70163_u;
    double d0 = 4.147483647E9D;
    
    for (int j = (int)theOcelot.field_70165_t - 8; j < theOcelot.field_70165_t + 8.0D; j++)
    {
      for (int k = (int)theOcelot.field_70161_v - 8; k < theOcelot.field_70161_v + 8.0D; k++)
      {
        for (int y = (int)theOcelot.field_70163_u - 2; y < theOcelot.field_70163_u + 3.0D; y++)
        {
          if ((isSittableBlock(theOcelot.field_70170_p, j, y, k)) && (theOcelot.field_70170_p.func_147437_c(j, y + 1, k)))
          {
            double d1 = theOcelot.func_70092_e(j, y, k);
            
            if (d1 < d0)
            {
              sitableBlockX = j;
              sitableBlockY = y;
              sitableBlockZ = k;
              d0 = d1;
            }
          }
        }
      }
    }
    
    return d0 < 2.147483647E9D;
  }
  



  protected boolean isSittableBlock(World par1World, int par2, int par3, int par4)
  {
    Block l = par1World.func_147439_a(par2, par3, par4);
    int i1 = par1World.func_72805_g(par2, par3, par4);
    
    if (l == Blocks.field_150392_bi)
    {

      return true;
    }
    
    return false;
  }
}
