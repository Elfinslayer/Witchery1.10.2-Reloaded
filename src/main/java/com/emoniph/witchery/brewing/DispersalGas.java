package com.emoniph.witchery.brewing;

import com.emoniph.witchery.util.BlockActionCircle;
import com.emoniph.witchery.util.BlockPosition;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.EntityPosition;
import com.emoniph.witchery.util.EntityUtil;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class DispersalGas extends Dispersal
{
  public DispersalGas() {}
  
  public void onImpactSplashPotion(World world, NBTTagCompound nbtBrew, MovingObjectPosition mop, ModifiersImpact modifiers)
  {
    Coord coord = new Coord(mop, impactPosition, true);
    boolean replaceable = com.emoniph.witchery.util.BlockUtil.isReplaceableBlock(world, x, y, z, thrower);
    
    if (replaceable) {
      coord.setBlock(world, BlocksBREW_GAS);
      TileEntityBrewFluid gas = (TileEntityBrewFluid)coord.getTileEntity(world, TileEntityBrewFluid.class);
      if (gas != null) {
        gas.initalise(modifiers, nbtBrew);
      }
    }
  }
  
  public String getUnlocalizedName()
  {
    return "witchery:brew.dispersal.gas";
  }
  

  public RitualStatus onUpdateRitual(World world, int x, int y, int z, final NBTTagCompound nbtBrew, final ModifiersRitual modifiers, ModifiersImpact impactModifiers)
  {
    BlockPosition target = modifiers.getTarget();
    World targetWorld = target.getWorld(MinecraftServer.func_71276_C());
    
    int height = 8;
    boolean blackMagic = false;
    
    new BlockActionCircle()
    {
      public void onBlock(World world, int x, int y, int z) {
        BlockPosition coords = null;
        for (int i = 0; i < 8; i++)
        {
          if ((world.func_147439_a(x, y + i, z).func_149688_o() != Material.field_151579_a) && (world.func_147437_c(x, y + i + 1, z)))
          {
            coords = new BlockPosition(world, x, y, z);
            break;
          }
          if ((i > 0) && (world.func_147439_a(x, y - i, z).func_149688_o() != Material.field_151579_a) && (world.func_147437_c(x, y - i + 1, z)))
          {
            coords = new BlockPosition(world, x, y, z);
            break; } }
        EntityPlayer player;
        ModifiersEffect effectModifiers;
        if (coords != null) {
          DispersalGas.showSpellParticles(world, x, y, z, false);
          player = EntityUtil.playerOrFake(world, (EntityPlayer)null);
          effectModifiers = new ModifiersEffect(0.0D, 1.0D, false, new EntityPosition(coords), true, modifierscovenSize, player);
          
          if (field_73012_v.nextDouble() < 0.01D) {
            WitcheryBrewRegistry.INSTANCE.applyToBlock(world, x, y, z, ForgeDirection.UP, 1, nbtBrew, effectModifiers);
          }
          
          java.util.List<EntityLivingBase> entities = EntityUtil.getEntitiesInRadius(EntityLivingBase.class, world, x, y, z, 1.5D);
          
          for (EntityLivingBase entity : entities) {
            effectModifiers = new ModifiersEffect(1.0D, 1.0D, false, new EntityPosition(coords), true, modifierscovenSize, player);
            
            WitcheryBrewRegistry.INSTANCE.applyToEntity(world, entity, nbtBrew, effectModifiers); } } } }.processHollowCircle(targetWorld, x, y, z, pulses);
    




    return pulses < 8 + extent * 8 ? RitualStatus.ONGOING : RitualStatus.COMPLETE;
  }
  
  private static void showSpellParticles(World world, int x, int y, int z, boolean blackMagic) {
    if (blackMagic) {
      ParticleEffect.MOB_SPELL.send(SoundEffect.NONE, world, 0.5D + x, y + 1, 0.5D + z, 1.0D, 1.0D, 16);
    } else {
      ParticleEffect.SPELL.send(SoundEffect.NONE, world, 0.5D + x, y + 1, 0.5D + z, 1.0D, 1.0D, 16);
    }
  }
}
