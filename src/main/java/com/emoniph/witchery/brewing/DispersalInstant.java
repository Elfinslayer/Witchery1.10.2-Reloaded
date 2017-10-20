package com.emoniph.witchery.brewing;

import com.emoniph.witchery.util.BlockPosition;
import com.emoniph.witchery.util.EntityPosition;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class DispersalInstant extends Dispersal
{
  public DispersalInstant() {}
  
  public void onImpactSplashPotion(World world, NBTTagCompound nbtBrew, MovingObjectPosition mop, ModifiersImpact modifiers)
  {
    double R = 3 + extent;
    double R_SQ = R * R;
    

    EntityPosition position = impactPosition;
    AxisAlignedBB bb = AxisAlignedBB.func_72330_a(x - R, y - R, z - R, x + R, y + R, z + R);
    
    List<EntityLivingBase> list1 = world.func_72872_a(EntityLivingBase.class, bb);
    
    for (EntityLivingBase targetEntity : list1) {
      double distanceSq = position.getDistanceSqToEntity(targetEntity);
      
      if (distanceSq <= R_SQ) {
        boolean directHit = targetEntity == field_72308_g;
        double effectScalingFactor = directHit ? 1.0D : 1.0D - Math.sqrt(distanceSq) / R;
        WitcheryBrewRegistry.INSTANCE.applyToEntity(world, targetEntity, nbtBrew, new ModifiersEffect(effectScalingFactor, 0.5D * effectScalingFactor, !directHit, position, modifiers));
      }
    }
    


    if (field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
      WitcheryBrewRegistry.INSTANCE.applyToBlock(world, field_72311_b, field_72312_c, field_72309_d, ForgeDirection.getOrientation(field_72310_e), MathHelper.func_76143_f(R), nbtBrew, new ModifiersEffect(1.0D, 1.0D, false, position, modifiers));
    }
  }
  


  public String getUnlocalizedName()
  {
    return "witchery:brew.dispersal.splash";
  }
  

  public RitualStatus onUpdateRitual(World world, int x, int y, int z, NBTTagCompound nbtBrew, ModifiersRitual modifiers, ModifiersImpact impactModifiers)
  {
    BlockPosition target = modifiers.getTarget();
    ModifiersEffect effectModifiers = new ModifiersEffect(1.0D, 1.0D, false, new EntityPosition(target), true, covenSize, com.emoniph.witchery.util.EntityUtil.playerOrFake(world, (EntityPlayer)null));
    
    WitcheryBrewRegistry.INSTANCE.applyRitualToBlock(world, x, y, z, ForgeDirection.UP, 3 + extent, nbtBrew, modifiers, effectModifiers);
    
    return modifiers.getRitualStatus();
  }
}
