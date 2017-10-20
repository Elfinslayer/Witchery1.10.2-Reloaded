package com.emoniph.witchery.infusion;

import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;

public class PlayerEffects
{
  private static final ArrayList<PlayerEffect> effects = new ArrayList();
  public static final String KEY_EFFECTS = "witchery.effects";
  public static final PlayerEffect IMP_FIRE_TOUCH = new PlayerEffect("witchery.imp.firetouch", effects) { protected void doUpdate(EntityPlayer player, int worldTicks) {}
    
    protected void doHarvest(EntityPlayer player, BlockEvent.HarvestDropsEvent event) {}
    
    protected void doInteract(EntityPlayer player, PlayerInteractEvent event) { World world = field_70170_p;
      if (field_73012_v.nextDouble() < 0.2D) {
        Block block = BlockUtil.getBlock(world, x, y, z);
        if ((block != null) && (block != Blocks.field_150350_a)) {
          int par4 = x;
          int par5 = y;
          int par6 = z;
          int par7 = face;
          if (par7 == 0)
          {
            par5--;
          }
          
          if (par7 == 1)
          {
            par5++;
          }
          
          if (par7 == 2)
          {
            par6--;
          }
          
          if (par7 == 3)
          {
            par6++;
          }
          
          if (par7 == 4)
          {
            par4--;
          }
          
          if (par7 == 5)
          {
            par4++;
          }
          if (action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK) {
            par4 = par4 - 1 + field_73012_v.nextInt(3);
            par6 = par6 - 1 + field_73012_v.nextInt(3);
          }
          
          if ((world.func_147437_c(par4, par5, par6)) && (!world.func_147437_c(par4, par5 - 1, par6)))
          {
            world.func_72908_a(par4 + 0.5D, par5 + 0.5D, par6 + 0.5D, SoundEffect.FIRE_FIRE.toString(), 1.0F, field_73012_v.nextFloat() * 0.4F + 0.8F);
            world.func_147449_b(par4, par5, par6, Blocks.field_150480_ab);
          }
        }
      }
    }
  };
  
  public static final PlayerEffect IMP_EVAPORATION = new PlayerEffect("witchery.imp.evaporation", effects) {
    protected void doUpdate(EntityPlayer player, int worldTicks) {
      if (field_70170_p.field_73012_v.nextInt(5) == 0) {
        int midX = MathHelper.func_76128_c(field_70165_t);
        int midY = MathHelper.func_76128_c(field_70163_u);
        int midZ = MathHelper.func_76128_c(field_70161_v);
        int R = 3;
        int RSq = 9;
        boolean found = false;
        for (int x = midX - 3; x <= midX + 3; x++) {
          for (int z = midZ - 3; z <= midZ + 3; z++) {
            for (int y = midY + 2; y >= midY - 1; y--) {
              if (player.func_70092_e(x, y, z) <= 9.0D) {
                Block block = BlockUtil.getBlock(field_70170_p, x, y, z);
                
                if (((block == Blocks.field_150355_j) || (block == Blocks.field_150358_i)) && (field_70170_p.func_147437_c(x, y + 1, z))) {
                  field_70170_p.func_147468_f(x, y, z);
                  ParticleEffect.EXPLODE.send(SoundEffect.NONE, field_70170_p, x, y + 1, z, 1.0D, 1.0D, 16);
                  found = true;
                }
              }
            }
          }
        }
        
        if (found)
          SoundEffect.RANDOM_FIZZ.playAt(field_70170_p, field_70165_t, field_70163_u, field_70161_v, 1.0F, 2.6F + (field_70170_p.field_73012_v.nextFloat() - field_70170_p.field_73012_v.nextFloat()) * 0.8F);
      }
    }
    
    protected void doHarvest(EntityPlayer player, BlockEvent.HarvestDropsEvent event) {}
    
    protected void doInteract(EntityPlayer player, PlayerInteractEvent event) {}
  };
  public static final PlayerEffect IMP_METLING_TOUCH = new PlayerEffect("witchery.im.meltingtouch", effects) {
    protected void doUpdate(EntityPlayer player, int worldTicks) {}
    
    protected void doHarvest(EntityPlayer player, BlockEvent.HarvestDropsEvent event) { ArrayList<ItemStack> newDrops = new ArrayList();
      for (ItemStack drop : drops) {
        ItemStack smeltedDrop = FurnaceRecipes.func_77602_a().func_151395_a(drop);
        
        if (smeltedDrop != null) {
          Log.instance().debug("Smelting Touch: " + drop.toString() + " -> " + smeltedDrop.toString());
          ItemStack smelted = smeltedDrop.func_77946_l();
          if (field_70170_p.field_73012_v.nextDouble() < 0.25D) {
            field_77994_a += 1;
          }
          newDrops.add(smelted);
        } else {
          Log.instance().debug("Smelting Touch: " + drop.toString() + " -> none");
          newDrops.add(drop);
        }
      }
      drops.clear();
      for (ItemStack newDrop : newDrops)
        drops.add(newDrop); }
    
    protected void doInteract(EntityPlayer player, PlayerInteractEvent event) {} };
  private static final int TICKS_PER_UPDATE = 20;
  
  public PlayerEffects() {}
  
  public static void onDeath(EntityPlayer player) { NBTTagCompound nbtPlayer = Infusion.getNBT(player);
    if ((nbtPlayer != null) && (nbtPlayer.func_74764_b("witchery.effects"))) {
      NBTTagCompound nbtEffects = nbtPlayer.func_74775_l("witchery.effects");
      for (PlayerEffect effect : effects) {
        effect.removeFrom(nbtEffects);
      }
      if (nbtEffects.func_82582_d()) {
        nbtPlayer.func_82580_o("witchery.effects");
      }
    }
  }
  

  public static void onUpdate(EntityPlayer player, long ticks)
  {
    if (ticks % 20L == 3L) {
      NBTTagCompound nbtPlayer = Infusion.getNBT(player);
      if ((nbtPlayer != null) && (nbtPlayer.func_74764_b("witchery.effects"))) {
        NBTTagCompound nbtEffects = nbtPlayer.func_74775_l("witchery.effects");
        for (PlayerEffect effect : effects) {
          effect.update(nbtEffects, 20, player);
        }
        if (nbtEffects.func_82582_d()) {
          nbtPlayer.func_82580_o("witchery.effects");
        }
      }
    }
  }
  
  public static void onHarvestDrops(EntityPlayer player, BlockEvent.HarvestDropsEvent event) {
    NBTTagCompound nbtPlayer = Infusion.getNBT(player);
    if ((nbtPlayer != null) && (nbtPlayer.func_74764_b("witchery.effects"))) {
      NBTTagCompound nbtEffects = nbtPlayer.func_74775_l("witchery.effects");
      for (PlayerEffect effect : effects) {
        effect.harvest(nbtEffects, event, player);
      }
      if (nbtEffects.func_82582_d()) {
        nbtPlayer.func_82580_o("witchery.effects");
      }
    }
  }
  
  public static void onInteract(EntityPlayer player, PlayerInteractEvent event) {
    NBTTagCompound nbtPlayer = Infusion.getNBT(player);
    if ((nbtPlayer != null) && (nbtPlayer.func_74764_b("witchery.effects"))) {
      NBTTagCompound nbtEffects = nbtPlayer.func_74775_l("witchery.effects");
      for (PlayerEffect effect : effects) {
        effect.interact(nbtEffects, event, player);
      }
      if (nbtEffects.func_82582_d()) {
        nbtPlayer.func_82580_o("witchery.effects");
      }
    }
  }
  
  public static abstract class PlayerEffect {
    protected final String unlocalizedName;
    
    protected PlayerEffect(String unlocalizedName, ArrayList<PlayerEffect> effects) {
      this.unlocalizedName = unlocalizedName;
      effects.add(this);
    }
    
    public void interact(NBTTagCompound nbtEffects, PlayerInteractEvent event, EntityPlayer player) {
      if (nbtEffects.func_74764_b(unlocalizedName)) {
        doInteract(player, event);
      }
    }
    
    protected abstract void doInteract(EntityPlayer paramEntityPlayer, PlayerInteractEvent paramPlayerInteractEvent);
    
    public void harvest(NBTTagCompound nbtEffects, BlockEvent.HarvestDropsEvent event, EntityPlayer player) {
      if (nbtEffects.func_74764_b(unlocalizedName)) {
        doHarvest(player, event);
      }
    }
    
    protected abstract void doHarvest(EntityPlayer paramEntityPlayer, BlockEvent.HarvestDropsEvent paramHarvestDropsEvent);
    
    public void applyTo(EntityPlayer player, int durationTicks) {
      NBTTagCompound nbtPlayer = Infusion.getNBT(player);
      if (nbtPlayer != null) {
        if (!nbtPlayer.func_74764_b("witchery.effects")) {
          nbtPlayer.func_74782_a("witchery.effects", new NBTTagCompound());
        }
        NBTTagCompound nbtEffects = nbtPlayer.func_74775_l("witchery.effects");
        nbtEffects.func_74768_a(unlocalizedName, durationTicks);
      }
    }
    
    private void removeFrom(NBTTagCompound nbtEffects) {
      if (nbtEffects.func_74764_b(unlocalizedName)) {
        nbtEffects.func_82580_o(unlocalizedName);
      }
    }
    
    private void update(NBTTagCompound nbtEffects, int ticks, EntityPlayer player) {
      if (nbtEffects.func_74764_b(unlocalizedName)) {
        int remainingTicks = nbtEffects.func_74762_e(unlocalizedName);
        int newTicks = Math.max(remainingTicks - ticks, 0);
        if (newTicks == 0) {
          removeFrom(nbtEffects);
        } else {
          nbtEffects.func_74768_a(unlocalizedName, newTicks);
          doUpdate(player, ticks);
        }
      }
    }
    
    protected abstract void doUpdate(EntityPlayer paramEntityPlayer, int paramInt);
  }
}
