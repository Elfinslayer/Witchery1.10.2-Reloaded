package com.emoniph.witchery.ritual;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
import com.emoniph.witchery.blocks.BlockGrassper.TileEntityGrassper;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.Const;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class SacrificeOptionalItem extends SacrificeItem
{
  public SacrificeOptionalItem(ItemStack optionalSacrifice)
  {
    super(new ItemStack[] { optionalSacrifice });
  }
  
  public void addDescription(StringBuffer sb)
  {
    for (ItemStack itemstack : itemstacks) {
      sb.append("§8> ");
      
      if (itemstack.func_77973_b() == Items.field_151068_bn) {
        List list = Items.field_151068_bn.func_77832_l(itemstack);
        if ((list != null) && (!list.isEmpty())) {
          PotionEffect effect = (PotionEffect)list.get(0);
          String s = itemstack.func_82833_r();
          if (effect.func_76458_c() > 0) {
            s = s + " " + net.minecraft.util.StatCollector.func_74838_a(new StringBuilder().append("potion.potency.").append(effect.func_76458_c()).toString()).trim();
          }
          
          if (effect.func_76459_b() > 20) {
            s = s + " (" + net.minecraft.potion.Potion.func_76389_a(effect) + ")";
          }
          sb.append(s);
        } else {
          sb.append(itemstack.func_82833_r());
        }
      } else {
        sb.append(itemstack.func_82833_r());
      }
      sb.append(" ");
      sb.append(Witchery.resource("witchery.rite.optional"));
      sb.append("§0");
      sb.append(Const.BOOK_NEWLINE);
    }
  }
  
  public boolean isMatch(World world, int posX, int posY, int posZ, int maxDistance, ArrayList<Entity> entities, ArrayList<ItemStack> grassperStacks)
  {
    boolean found = super.isMatch(world, posX, posY, posZ, maxDistance, entities, grassperStacks);
    return true;
  }
  
  public void addSteps(ArrayList<RitualStep> steps, AxisAlignedBB bounds, int maxDistance)
  {
    for (ItemStack itemstack : itemstacks) {
      steps.add(new StepSacrificeOptionalItem(itemstack, bounds, maxDistance));
    }
  }
  
  private static class StepSacrificeOptionalItem extends SacrificeItem.StepSacrificeItem
  {
    public StepSacrificeOptionalItem(ItemStack itemstack, AxisAlignedBB bounds, int maxDistance) {
      super(bounds, maxDistance);
      showMessages = false;
    }
    
    public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual) {
      if (ticks % 20L != 0L) {
        return RitualStep.Result.STARTING;
      }
      
      if (!field_72995_K) {
        List itemEntities = world.func_72872_a(EntityItem.class, bounds);
        
        if (Config.instance().traceRites()) {
          for (Object obj : itemEntities) {
            EntityItem entity = (EntityItem)obj;
            ItemStack foundItemstack = entity.func_92059_d();
            Log.instance().traceRite(String.format(" * found: %s, distance: %f", new Object[] { foundItemstack.toString(), Double.valueOf(Sacrifice.distance(field_70165_t, field_70163_u, field_70161_v, posX, posY, posZ)) }));
          }
        }
        
        for (Object obj : itemEntities) {
          EntityItem entity = (EntityItem)obj;
          ItemStack foundItemstack = entity.func_92059_d();
          
          if ((SacrificeItem.isItemEqual(itemstack, foundItemstack)) && (Sacrifice.distance(field_70165_t, field_70163_u, field_70161_v, posX, posY, posZ) <= maxDistance)) {
            boolean skip = false;
            if ((ItemsGENERIC.itemWaystoneBound.isMatch(foundItemstack)) && 
              (!ItemsGENERIC.copyLocationBinding(world, foundItemstack, ritual))) {
              skip = true;
            }
            

            if (!skip) {
              ItemStack sacrificedItemstack = ItemStack.func_77944_b(foundItemstack);
              field_77994_a = 1;
              sacrificedItems.add(new RitualStep.SacrificedItem(sacrificedItemstack, new Coord(entity)));
              
              if ((foundItemstack.func_77985_e()) && (field_77994_a > 1)) {
                field_77994_a -= 1;
              } else {
                world.func_72900_e(entity);
              }
            }
            
            if (!skip) {
              ParticleEffect.EXPLODE.send(SoundEffect.RANDOM_POP, entity, 0.5D, 1.0D, 16);
            } else {
              ParticleEffect.SMOKE.send(SoundEffect.NOTE_PLING, entity, 0.5D, 1.0D, 16);
            }
            
            return RitualStep.Result.COMPLETED;
          }
        }
        
        int radius = 5;
        for (int x = posX - 5; x <= posX + 5; x++) {
          for (int z = posZ - 5; z <= posZ + 5; z++) {
            Block blockID = world.func_147439_a(x, posY, z);
            if (blockID == BlocksGRASSPER) {
              net.minecraft.tileentity.TileEntity tile = world.func_147438_o(x, posY, z);
              if ((tile != null) && ((tile instanceof BlockGrassper.TileEntityGrassper))) {
                BlockGrassper.TileEntityGrassper grassper = (BlockGrassper.TileEntityGrassper)tile;
                
                ItemStack stack = grassper.func_70301_a(0);
                if ((stack != null) && (SacrificeItem.isItemEqual(itemstack, stack))) {
                  boolean skip = false;
                  if ((ItemsGENERIC.itemWaystoneBound.isMatch(stack)) && 
                    (!ItemsGENERIC.copyLocationBinding(world, stack, ritual))) {
                    skip = true;
                  }
                  

                  if (!skip) {
                    ItemStack sacrificedItemstack = ItemStack.func_77944_b(stack);
                    field_77994_a = 1;
                    sacrificedItems.add(new RitualStep.SacrificedItem(sacrificedItemstack, new Coord(tile)));
                    if ((stack.func_77985_e()) && (field_77994_a > 1)) {
                      field_77994_a -= 1;
                    } else {
                      grassper.func_70299_a(0, null);
                    }
                  }
                  if (!skip) {
                    ParticleEffect.EXPLODE.send(SoundEffect.RANDOM_POP, world, 0.5D + x, 0.8D + posY, 0.5D + z, 0.5D, 1.0D, 16);
                  } else {
                    ParticleEffect.SMOKE.send(SoundEffect.NOTE_PLING, world, 0.5D + x, 0.8D + posY, 0.5D + z, 0.5D, 1.0D, 16);
                  }
                  
                  return RitualStep.Result.COMPLETED;
                }
              }
            }
          }
        }
      }
      
      return RitualStep.Result.COMPLETED;
    }
    
    public String toString()
    {
      return String.format("StepSacrificeOptionalItem: %s, maxDistance: %d", new Object[] { itemstack.toString(), Integer.valueOf(maxDistance) });
    }
  }
}
