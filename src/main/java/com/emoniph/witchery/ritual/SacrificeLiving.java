package com.emoniph.witchery.ritual;

import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
import com.emoniph.witchery.util.Const;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class SacrificeLiving extends Sacrifice
{
  final Class<? extends EntityLiving> entityLivingClass;
  
  public SacrificeLiving(Class<? extends EntityLiving> entityLivingClass)
  {
    this.entityLivingClass = entityLivingClass;
  }
  
  public void addDescription(StringBuffer sb)
  {
    String s = (String)EntityList.field_75626_c.get(entityLivingClass);
    
    if (s == null)
    {
      s = "generic";
    }
    
    sb.append("§8>§0 ");
    sb.append(StatCollector.func_74838_a("entity." + s + ".name"));
    sb.append(Const.BOOK_NEWLINE);
  }
  









  public boolean isMatch(World world, int posX, int posY, int posZ, int maxDistance, ArrayList<Entity> entities, ArrayList<ItemStack> grassperStacks)
  {
    return true;
  }
  
  public void addSteps(ArrayList<RitualStep> steps, AxisAlignedBB bounds, int maxDistance)
  {
    steps.add(new StepSacrificeLiving(this, bounds, maxDistance));
  }
  
  private static class StepSacrificeLiving extends RitualStep
  {
    private final SacrificeLiving sacrifice;
    private final AxisAlignedBB bounds;
    private final int maxDistance;
    
    public StepSacrificeLiving(SacrificeLiving sacrifice, AxisAlignedBB bounds, int maxDistance) {
      super();
      this.sacrifice = sacrifice;
      this.bounds = bounds;
      this.maxDistance = (maxDistance + 1);
    }
    
    public RitualStep.Result process(World worldObj, int xCoord, int yCoord, int zCoord, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
    {
      if (ticks % 20L != 0L) {
        return RitualStep.Result.STARTING;
      }
      for (Object obj : worldObj.func_72872_a(EntityLiving.class, bounds)) {
        EntityLiving entity = (EntityLiving)obj;
        if ((sacrifice.entityLivingClass.isInstance(entity)) && (Sacrifice.distance(xCoord, yCoord, zCoord, field_70165_t, field_70163_u, field_70161_v) <= maxDistance)) {
          if (!field_72995_K) {
            entity.func_70106_y();
            
            ParticleEffect.PORTAL.send(SoundEffect.RANDOM_POP, entity, 1.0D, 2.0D, 16);
          }
          
          return RitualStep.Result.COMPLETED;
        }
      }
      
      RiteRegistry.RiteError("witchery.rite.missinglivingsacrifice", ritual.getInitiatingPlayerName(), worldObj);
      return RitualStep.Result.ABORTED_REFUND;
    }
  }
}
