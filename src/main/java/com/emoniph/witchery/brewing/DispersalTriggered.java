package com.emoniph.witchery.brewing;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.familiar.Familiar;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;

public class DispersalTriggered extends Dispersal
{
  public DispersalTriggered() {}
  
  public static class EventHooks
  {
    public EventHooks() {}
    
    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent event)
    {
      if ((!entityPlayer.field_70170_p.field_72995_K) && ((action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR) || (action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)) && (entityPlayer.func_70694_bm() != null) && (entityPlayer.func_70694_bm().func_77942_o()) && (entityPlayer.func_70694_bm().func_77978_p().func_74764_b("WITCCurse")))
      {



        NBTTagCompound root = entityPlayer.func_70694_bm().func_77978_p().func_74775_l("WITCCurse");
        
        NBTTagCompound tag = root.func_74775_l("Curse");
        int charges = root.func_74762_e("Count");
        long delay = root.func_74763_f("Delay");
        if ((delay == 0L) || (MinecraftServer.func_130071_aq() > delay)) {
          if (charges > 0) {
            WitcheryBrewRegistry.INSTANCE.applyToEntity(entityPlayer.field_70170_p, entityPlayer, tag, new ModifiersEffect(1.0D, 1.0D, false, new com.emoniph.witchery.util.EntityPosition(entityPlayer), false, 0, com.emoniph.witchery.util.EntityUtil.playerOrFake(entityPlayer.field_70170_p, (net.minecraft.entity.EntityLivingBase)null)));
            





            ParticleEffect.SPELL_COLORED.send(SoundEffect.RANDOM_POP, entityPlayer, 1.0D, 1.0D, 16);
            charges--;
          }
          
          if (charges > 0) {
            root.func_74768_a("Count", charges);
            root.func_74772_a("Delay", MinecraftServer.func_130071_aq() + 5000L);
          } else {
            entityPlayer.func_70694_bm().func_77978_p().func_82580_o("WITCCurse");
            if (entityPlayer.func_70694_bm().func_77978_p().func_82582_d()) {
              entityPlayer.func_70694_bm().func_77982_d(null);
            }
          }
        }
      }
    }
  }
  

  public void onImpactSplashPotion(World world, NBTTagCompound nbtBrew, MovingObjectPosition mop, ModifiersImpact modifiers)
  {
    Coord coord = new Coord(mop, impactPosition, true);
    boolean replaceable = com.emoniph.witchery.util.BlockProtect.checkModsForBreakOK(world, x, y, z, thrower);
    
    if (replaceable) {
      Block block = coord.getBlock(world);
      if (block == Blocks.field_150430_aB) {
        BlocksCURSED_BUTTON_STONE.replaceButton(world, x, y, z, modifiers, nbtBrew);
        
        return; }
      if (block == Blocks.field_150471_bO) {
        BlocksCURSED_BUTTON_WOOD.replaceButton(world, x, y, z, modifiers, nbtBrew);
        
        return; }
      if (block == Blocks.field_150442_at) {
        BlocksCURSED_LEVER.replaceButton(world, x, y, z, modifiers, nbtBrew);
        return; }
      if (block.hasTileEntity(coord.getBlockMetadata(world))) {
        TileEntityCursedBlock tile = (TileEntityCursedBlock)BlockUtil.getTileEntity(world, x, y, z, TileEntityCursedBlock.class);
        if (tile != null) {
          tile.updateCurse(modifiers, nbtBrew);
        }
      }
      
      coord = new Coord(mop, impactPosition, false);
      block = coord.getBlock(world);
      if (block == Blocks.field_150452_aw) {
        BlocksCURSED_WOODEN_PRESSURE_PLATE.replaceButton(world, x, y, z, modifiers, nbtBrew);
      }
      else if (block == Blocks.field_150456_au) {
        BlocksCURSED_STONE_PRESSURE_PLATE.replaceButton(world, x, y, z, modifiers, nbtBrew);
      }
      else if (block == BlocksSNOW_PRESSURE_PLATE) {
        BlocksCURSED_SNOW_PRESSURE_PLATE.replaceButton(world, x, y, z, modifiers, nbtBrew);
      }
      else if (block == Blocks.field_150466_ao) {
        BlocksCURSED_WOODEN_DOOR.replaceButton(world, x, y, z, modifiers, nbtBrew);
      }
      else if (block.hasTileEntity(coord.getBlockMetadata(world))) {
        int y = y;
        if (block == BlocksCURSED_WOODEN_DOOR) {
          int i1 = ((BlockDoor)Blocks.field_150466_ao).func_150012_g(world, x, y, z);
          if ((i1 & 0x8) != 0) {
            y--;
          }
        }
        TileEntityCursedBlock tile = (TileEntityCursedBlock)BlockUtil.getTileEntity(world, x, y, z, TileEntityCursedBlock.class);
        if (tile != null) {
          tile.updateCurse(modifiers, nbtBrew);
        }
      }
    }
  }
  
  public String getUnlocalizedName()
  {
    return "witchery:brew.dispersal.triggered";
  }
  


  public RitualStatus onUpdateRitual(World world, int x, int y, int z, NBTTagCompound nbtBrew, ModifiersRitual modifiers, ModifiersImpact impactModifiers)
  {
    AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(x, y + 1, z, x + 1, y + 2, z + 1);
    List<EntityItem> items = world.func_72872_a(EntityItem.class, bounds);
    for (EntityItem item : items) {
      ItemStack stack = item.func_92059_d();
      if (stack != null) {
        if (field_77994_a > 1) {
          stack = stack.func_77979_a(1);
          EntityItem newEntity = new EntityItem(field_70170_p, field_70165_t, field_70163_u, field_70161_v, stack);
          
          world.func_72838_d(newEntity);
        }
        if (!stack.func_77942_o()) {
          stack.func_77982_d(new NBTTagCompound());
        }
        NBTTagCompound nbtRoot = stack.func_77978_p();
        
        AxisAlignedBB playerBounds = bounds.func_72314_b(3.0D, 3.0D, 3.0D);
        List<EntityPlayer> players = world.func_72872_a(EntityPlayer.class, playerBounds);
        boolean catNear = false;
        for (EntityPlayer player : players) {
          if (Familiar.hasActiveCurseMasteryFamiliar(player)) {
            catNear = true;
            break;
          }
        }
        

        if ((nbtRoot.func_74764_b("WITCCurse")) && (((NBTTagCompound)nbtRoot.func_74775_l("WITCCurse").func_74781_a("Curse")).func_150295_c("Items", 10).equals(nbtBrew.func_150295_c("Items", 10))))
        {

          NBTTagCompound nbtCurse = nbtRoot.func_74775_l("WITCCurse");
          nbtCurse.func_74768_a("Count", nbtCurse.func_74762_e("Count") + (catNear ? 2 : 1));
        } else {
          NBTTagCompound nbtCurse = new NBTTagCompound();
          nbtCurse.func_74782_a("Curse", nbtBrew.func_74737_b());
          nbtCurse.func_74768_a("Count", catNear ? 2 : 1);
          nbtRoot.func_74782_a("WITCCurse", nbtCurse);
        }
        ParticleEffect.EXPLODE.send(SoundEffect.RANDOM_ORB, item, 0.5D, 0.5D, 16);
        
        return RitualStatus.COMPLETE;
      }
    }
    
    return RitualStatus.FAILED;
  }
}
