package com.emoniph.witchery.brewing;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.WitcheryFluids;
import com.emoniph.witchery.blocks.TileEntityBase;
import com.emoniph.witchery.brewing.action.BrewAction;
import com.emoniph.witchery.brewing.potions.PotionBase;
import com.emoniph.witchery.brewing.potions.WitcheryPotions;
import com.emoniph.witchery.common.IPowerSource;
import com.emoniph.witchery.common.PowerSources;
import com.emoniph.witchery.entity.EntityCovenWitch;
import com.emoniph.witchery.entity.EntityLeonard;
import com.emoniph.witchery.util.CircleUtil;
import com.emoniph.witchery.util.EntityUtil;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TimeUtil;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;

public class TileEntityCauldron extends TileEntityBase implements net.minecraftforge.fluids.IFluidHandler
{
  private int ticksHeated;
  private boolean powered;
  private int ritualTicks;
  
  public TileEntityCauldron() {}
  
  private static final int TICKS_TO_BOIL = TimeUtil.secsToTicks(5);
  
  public boolean isBoiling() {
    return ticksHeated == TICKS_TO_BOIL; }
  
  public boolean isPowered()
  {
    return powered;
  }
  
  public int getRedstoneSignalStrength()
  {
    if (!isFilled()) {
      return 0;
    }
    
    if (!isBoiling()) {
      return 3;
    }
    
    NBTTagCompound nbtRoot = tank.getFluid().tag;
    
    if ((nbtRoot == null) || (nbtRoot.func_74762_e("EffectCount") == 0)) {
      return 6;
    }
    
    if (!isPowered()) {
      return 9;
    }
    
    if (nbtRoot.func_74762_e("RemainingCapacity") > 0) {
      return 12;
    }
    
    return 15;
  }
  
  public void func_145845_h()
  {
    super.func_145845_h();
    if (!field_145850_b.field_72995_K) {
      boolean sync = false;
      
      Block blockBelow = field_145850_b.func_147439_a(field_145851_c, field_145848_d - 1, field_145849_e);
      if ((blockBelow == net.minecraft.init.Blocks.field_150480_ab) && (isFilled())) {
        if ((ticksHeated < TICKS_TO_BOIL) && 
          (++ticksHeated == TICKS_TO_BOIL)) {
          sync = true;
        }
        
      }
      else if (ticksHeated > 0) {
        ticksHeated = 0;
        sync = true;
      }
      

      if ((isBoiling()) && (ticks % 20L == 7L)) {
        boolean wasPowered = powered;
        int power = getPower();
        if (power == 0) {
          powered = true;
        } else if (power > 0) {
          IPowerSource source = PowerSources.findClosestPowerSource(this);
          double powerNeeded;
          double powerNeeded; if ((tank.getFluid() != null) && (tank.getFluid().tag != null) && (tank.getFluidAmount() == 3000) && (tank.getFluid().tag.func_74767_n("RitualTriggered")))
          {

            boolean small = CircleUtil.isSmallCircle(field_145850_b, field_145851_c, field_145848_d, field_145849_e, BlocksGLYPH_RITUAL);
            
            boolean smallPower = CircleUtil.isSmallCircle(field_145850_b, field_145851_c, field_145848_d, field_145849_e, BlocksGLYPH_INFERNAL);
            
            boolean medium = CircleUtil.isMediumCircle(field_145850_b, field_145851_c, field_145848_d, field_145849_e, BlocksGLYPH_RITUAL);
            
            boolean mediumPower = CircleUtil.isMediumCircle(field_145850_b, field_145851_c, field_145848_d, field_145849_e, BlocksGLYPH_INFERNAL);
            

            double powerScale = 1.4D;
            if (small) {
              powerScale -= 0.2D;
            }
            
            if (medium) {
              powerScale -= 0.2D;
            }
            
            if (smallPower) {
              powerScale -= 0.37D;
            }
            
            if (mediumPower) {
              powerScale -= 0.37D;
            }
            powerNeeded = power * powerScale;
          } else {
            powerNeeded = power;
          }
          
          powered = ((power == 0) || ((source != null) && (powerNeeded <= source.getCurrentPower())));
        } else {
          powered = false;
        }
        
        if (wasPowered != powered) {
          sync = true;
        }
      }
      
      if (ticks % 10L == 8L) {
        int oldRitualTicks = ritualTicks;
        int UPDATES_TO_ACTIVATE = 20;
        if ((isBoiling()) && (isPowered()) && (tank.getFluid() != null) && (tank.getFluid().tag != null) && (tank.getFluidAmount() == 3000))
        {
          NBTTagCompound nbtRoot = tank.getFluid().tag;
          if (nbtRoot.func_74767_n("RitualTriggered")) {
            ritualTicks += 1;
            
            int witchCount = 0;
            List<EntityCovenWitch> covenWitches = EntityUtil.getEntitiesInRadius(EntityCovenWitch.class, this, 6.0D);
            
            for (EntityCovenWitch witch : covenWitches) {
              if (witch.func_70902_q() != null) {
                witchCount++;
              }
            }
            
            List<EntityPlayer> playerWitches = EntityUtil.getEntitiesInRadius(EntityPlayer.class, this, 6.0D);
            
            boolean playerCoven = false;
            for (EntityPlayer player : playerWitches) {
              if (EntityCovenWitch.getCovenSize(player) > 0) {
                if (playerCoven) {
                  witchCount++;
                } else {
                  playerCoven = true;
                }
              }
            }
            
            if (ritualTicks > 20) {
              IPowerSource powerSource = PowerSources.findClosestPowerSource(this);
              
              int neededPower = getPower();
              
              boolean small = CircleUtil.isSmallCircle(field_145850_b, field_145851_c, field_145848_d, field_145849_e, BlocksGLYPH_RITUAL);
              
              boolean smallPower = CircleUtil.isSmallCircle(field_145850_b, field_145851_c, field_145848_d, field_145849_e, BlocksGLYPH_INFERNAL);
              
              boolean medium = CircleUtil.isMediumCircle(field_145850_b, field_145851_c, field_145848_d, field_145849_e, BlocksGLYPH_RITUAL);
              
              boolean mediumPower = CircleUtil.isMediumCircle(field_145850_b, field_145851_c, field_145848_d, field_145849_e, BlocksGLYPH_INFERNAL);
              

              double powerScale = 1.4D;
              if (small) {
                powerScale -= 0.2D;
              }
              
              if (medium) {
                powerScale -= 0.2D;
              }
              
              int risk = 0;
              if ((!small) && (!medium)) {
                risk++;
              }
              
              if (smallPower) {
                risk++;
                powerScale -= 0.37D;
              }
              
              if (mediumPower) {
                risk++;
                powerScale -= 0.37D;
              }
              
              if ((neededPower == 0) || ((powerSource != null) && (powerSource.consumePower((int)Math.floor(neededPower * powerScale)))))
              {


                double R = 16.0D;
                AxisAlignedBB bb = AxisAlignedBB.func_72330_a(field_145851_c - R, field_145848_d - R, field_145849_e - R, field_145851_c + R, field_145848_d + R, field_145849_e + R);
                
                List<EntityLeonard> leonards = field_145850_b.func_72872_a(EntityLeonard.class, bb);
                
                boolean lenny = false;
                for (EntityLeonard leonard : leonards) {
                  if ((!field_70128_L) && (leonard.func_110143_aJ() > 1.0F)) {
                    lenny = true;
                    break;
                  }
                }
                
                RitualStatus status = WitcheryBrewRegistry.INSTANCE.updateRitual(MinecraftServer.func_71276_C(), field_145850_b, field_145851_c, field_145848_d, field_145849_e, nbtRoot, witchCount, ritualTicks - 20, lenny);
                

                boolean failed = false;
                switch (1.$SwitchMap$com$emoniph$witchery$brewing$RitualStatus[status.ordinal()])
                {
                case 1: 
                  checkForMisfortune(risk + (lenny ? 1 : 0), neededPower);
                  
                  break;
                case 2: 
                  ParticleEffect.SPELL.send(SoundEffect.RANDOM_FIZZ, field_145850_b, field_145851_c + 0.5D, field_145848_d + 0.6D, field_145849_e + 0.5D, 0.5D, 1.0D, 8);
                  
                  drain(ForgeDirection.UNKNOWN, getLiquidQuantity(), true);
                  ritualTicks = 0;
                  powered = false;
                  ticksHeated = 0;
                  sync = true;
                  
                  checkForMisfortune(risk + (lenny ? 1 : 0), neededPower);
                  
                  break;
                
                case 3: 
                  ParticleEffect.SPELL_COLORED.send(SoundEffect.RANDOM_POP, field_145850_b, field_145851_c + 0.5D, field_145848_d + 0.6D, field_145849_e + 0.5D, 0.5D, 1.0D, 8, -16742400);
                  
                  failed = true;
                  break;
                case 4: 
                  ParticleEffect.SPELL_COLORED.send(SoundEffect.RANDOM_POP, field_145850_b, field_145851_c + 0.5D, field_145848_d + 0.6D, field_145849_e + 0.5D, 0.5D, 1.0D, 8, -16777080);
                  
                  failed = true;
                  break;
                case 5: 
                  ParticleEffect.SPELL_COLORED.send(SoundEffect.RANDOM_POP, field_145850_b, field_145851_c + 0.5D, field_145848_d + 0.6D, field_145849_e + 0.5D, 0.5D, 1.0D, 8, -7864320);
                  
                  failed = true;
                  break;
                case 6: 
                  ParticleEffect.SPELL_COLORED.send(SoundEffect.RANDOM_POP, field_145850_b, field_145851_c + 0.5D, field_145848_d + 0.6D, field_145849_e + 0.5D, 0.5D, 1.0D, 8, -7864184);
                  
                  failed = true;
                }
                
                
                if (failed) {
                  NBTTagList nbtItems = nbtRoot.func_150295_c("Items", 10);
                  ItemStack stack = ItemStack.func_77949_a(nbtItems.func_150305_b(nbtItems.func_74745_c() - 1));
                  
                  nbtItems.func_74744_a(nbtItems.func_74745_c() - 1);
                  EntityUtil.spawnEntityInWorld(field_145850_b, new EntityItem(field_145850_b, field_145851_c, field_145848_d, field_145849_e, stack));
                  
                  nbtRoot.func_74757_a("RitualTriggered", false);
                  ritualTicks = 0;
                  sync = true;
                }
              }
              else if (ritualTicks > 21) {
                drain(ForgeDirection.UNKNOWN, getLiquidQuantity(), true);
                ritualTicks = 0;
                powered = false;
                ticksHeated = 0;
                ParticleEffect.SPELL_COLORED.send(SoundEffect.RANDOM_POP, field_145850_b, field_145851_c + 0.5D, field_145848_d + 0.6D, field_145849_e + 0.5D, 0.5D, 1.0D, 8, -7829504);
                
                sync = true;
              }
            }
          }
        }
        else
        {
          if (ritualTicks > 20) {
            drain(ForgeDirection.UNKNOWN, getLiquidQuantity(), true);
            ritualTicks = 0;
            powered = false;
            ticksHeated = 0;
            ParticleEffect.SPELL_COLORED.send(SoundEffect.RANDOM_POP, field_145850_b, field_145851_c + 0.5D, field_145848_d + 0.6D, field_145849_e + 0.5D, 0.5D, 1.0D, 8, -7829504);
            
            sync = true;
          }
          ritualTicks = 0;
        }
        if (ritualTicks != oldRitualTicks) {
          sync = true;
        }
      }
      
      if (sync) {
        markBlockForUpdate(true);
      }
    }
  }
  
  private void checkForMisfortune(int risk, int power) {
    if ((risk > 0) && (power > 0)) {
      double roll = field_145850_b.field_73012_v.nextDouble() * (1.0D + (risk - 1) * 0.2D);
      if (roll < 0.5D)
        return;
      if (roll < 0.75D) {
        applyToAllNear(new PotionEffect(field_76421_dfield_76415_H, TimeUtil.secsToTicks(60), 1));
      } else if (roll < 0.9D) {
        applyToAllNear(new PotionEffect(PotionsPARALYSED.field_76415_H, TimeUtil.secsToTicks(20), 2));
      } else if (roll < 0.98D) {
        applyToAllNear(new PotionEffect(PotionsINSANITY.field_76415_H, TimeUtil.minsToTicks(3), 2));
      } else {
        applyToAllNear(new PotionEffect(PotionsPARALYSED.field_76415_H, TimeUtil.secsToTicks(10), 2));
        for (int i = 0; i < field_145850_b.field_73012_v.nextInt(3) + 2; i++) {
          spawnBolt(field_145850_b, field_145851_c, field_145848_d, field_145849_e, 1, 4);
        }
      }
    }
  }
  
  private void applyToAllNear(PotionEffect effect) {
    double R = 16.0D;
    double RSq = 256.0D;
    AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(0.5D + field_145851_c - 16.0D, field_145848_d - 2, 0.5D + field_145849_e - 16.0D, 0.5D + field_145851_c + 16.0D, field_145848_d + 4, 0.5D + field_145849_e + 16.0D);
    
    List<EntityLivingBase> entities = field_145850_b.func_72872_a(EntityLivingBase.class, bounds);
    for (EntityLivingBase entity : entities) {
      if ((entity.func_70092_e(0.5D + field_145851_c, field_70163_u, 0.5D + field_145849_e) < 256.0D) && (!(entity instanceof IMob)) && (!(entity instanceof IBossDisplayData)))
      {
        List<Potion> effectsToRemove = new ArrayList();
        Collection<PotionEffect> effects = entity.func_70651_bq();
        for (PotionEffect buffs : effects) {
          Potion potion = Potion.field_76425_a[effect.func_76456_a()];
          if (!PotionBase.isDebuff(potion)) {
            effectsToRemove.add(potion);
          }
        }
        for (Potion potion : effectsToRemove) {
          entity.func_82170_o(field_76415_H);
        }
        entity.func_70690_d(new PotionEffect(effect));
      }
    }
  }
  
  private void spawnBolt(World world, int posX, int posY, int posZ, int min, int max) {
    int activeRadius = max - min;
    int ax = field_73012_v.nextInt(activeRadius * 2 + 1);
    if (ax > activeRadius) {
      ax += min * 2;
    }
    int x = posX - max + ax;
    
    int az = field_73012_v.nextInt(activeRadius * 2 + 1);
    if (az > activeRadius) {
      az += min * 2;
    }
    
    int z = posZ - max + az;
    
    EntityLightningBolt bolt = new EntityLightningBolt(world, x, posY, z);
    world.func_72942_c(bolt);
  }
  
  public boolean isRitualInProgress() {
    return ritualTicks > 0;
  }
  
  public boolean addItem(BrewAction brewAction, ItemStack entityItem) {
    if (field_145850_b.field_72995_K) {
      return false;
    }
    
    if (tank.getFluid().getFluid().getName().equals(FluidRegistry.WATER.getName())) {
      NBTTagCompound nbtTest = new NBTTagCompound();
      nbtTest.func_74782_a("Items", new NBTTagList());
      if (!WitcheryBrewRegistry.INSTANCE.canAdd(nbtTest, brewAction, tank.getFluidAmount() == tank.getCapacity()))
      {
        return false;
      }
      tank.setFluid(new FluidStack(FluidsBREW, tank.getFluid().amount));
      tank.getFluid().tag = nbtTest;
    }
    
    if (tank.getFluid().tag == null) {
      tank.getFluid().tag = new NBTTagCompound();
    }
    
    NBTTagCompound nbtRoot = tank.getFluid().tag;
    if (!nbtRoot.func_74764_b("Items")) {
      nbtRoot.func_74782_a("Items", new NBTTagList());
    }
    
    if (!WitcheryBrewRegistry.INSTANCE.canAdd(nbtRoot, brewAction, tank.getFluidAmount() == tank.getCapacity()))
    {
      return false;
    }
    
    if (!brewAction.removeWhenAddedToCauldron(field_145850_b)) {
      NBTTagList nbtItems = nbtRoot.func_150295_c("Items", 10);
      NBTTagCompound nbtItem = new NBTTagCompound();
      WitcheryBrewRegistry.INSTANCE.nullifyItems(nbtRoot, nbtItems, brewAction);
      entityItem.func_77955_b(nbtItem);
      nbtItems.func_74742_a(nbtItem);
    }
    
    int color = brewAction.augmentColor(nbtRoot.func_74762_e("Color"));
    nbtRoot.func_74768_a("Color", color);
    
    AltarPower powerNeeded = WitcheryBrewRegistry.INSTANCE.getPowerRequired(nbtRoot);
    nbtRoot.func_74768_a("Power", powerNeeded.getPower());
    
    nbtRoot.func_74778_a("BrewName", WitcheryBrewRegistry.INSTANCE.getBrewName(nbtRoot));
    WitcheryBrewRegistry.INSTANCE.updateBrewInformation(nbtRoot);
    nbtRoot.func_74768_a("BrewDrinkSpeed", WitcheryBrewRegistry.INSTANCE.getBrewDrinkSpeed(nbtRoot));
    
    if (brewAction.createsSplash()) {
      nbtRoot.func_74757_a("Splash", true);
    }
    
    if (brewAction.triggersRitual()) {
      nbtRoot.func_74757_a("RitualTriggered", true);
      ritualTicks = 0;
    }
    
    markBlockForUpdate(true);
    return true;
  }
  
  public boolean explodeBrew(EntityPlayer nearestPlayer) {
    if ((field_145850_b.field_72995_K) || (nearestPlayer == null)) {
      return false;
    }
    
    if (tank.getFluid() == null) {
      return false;
    }
    
    if (tank.getFluid().getFluid().getName().equals(FluidRegistry.WATER.getName())) {
      return false;
    }
    
    if (tank.getFluid().tag == null) {
      return false;
    }
    
    NBTTagCompound nbtRoot = tank.getFluid().tag;
    if (!nbtRoot.func_74764_b("Items")) {
      return false;
    }
    
    WitcheryBrewRegistry.INSTANCE.explodeBrew(field_145850_b, nbtRoot, nearestPlayer, 0.5D + field_145851_c, 1.5D + field_145848_d, 0.5D + field_145849_e);
    
    return true;
  }
  
  @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
  public int getColor() {
    NBTTagCompound nbtRoot = tank.getFluid() != null ? tank.getFluid().tag : null;
    if (nbtRoot != null) {
      int color = nbtRoot.func_74762_e("Color");
      return color;
    }
    return -1;
  }
  
  public int getPower()
  {
    NBTTagCompound nbtRoot = tank.getFluid() != null ? tank.getFluid().tag : null;
    if (nbtRoot != null) {
      int power = nbtRoot.func_74762_e("Power");
      return power;
    }
    return -1;
  }
  

  private FluidTank tank = new FluidTank(3000);
  
  public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
  {
    if (tank.getFluid() == null) {
      int filled = tank.fill(resource, doFill);
      FluidStack newStack = tank.getFluid();
      if (newStack != null) {
        tag = (tag != null ? (NBTTagCompound)tag.func_74737_b() : null);
        markBlockForUpdate(false);
      }
      return filled; }
    if ((resource.isFluidEqual(tank.getFluid())) && ((tank.getFluid().tag == null) || ((tag != null) && (tank.getFluid().tag.func_150295_c("Items", 10).equals(tag.func_150295_c("Items", 10))))))
    {

      int filled = tank.fill(resource, doFill);
      FluidStack newStack = tank.getFluid();
      if (newStack != null) {
        tag = (tag != null ? (NBTTagCompound)tag.func_74737_b() : null);
      }
      markBlockForUpdate(false);
      return filled;
    }
    return 0;
  }
  


  public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
  {
    if ((resource == null) || (!resource.isFluidEqual(tank.getFluid()))) {
      return null;
    }
    
    NBTTagCompound oldTag = (tank.getFluid() != null) && (tank.getFluid().tag != null) ? tank.getFluid().tag : null;
    
    FluidStack drained = tank.drain(amount, doDrain);
    tag = (oldTag != null ? (NBTTagCompound)oldTag.func_74737_b() : null);
    if (tank.getFluidAmount() == 0) {
      powered = false;
    }
    markBlockForUpdate(false);
    return drained;
  }
  
  public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
  {
    NBTTagCompound oldTag = (tank.getFluid() != null) && (tank.getFluid().tag != null) ? tank.getFluid().tag : null;
    
    FluidStack fluid = tank.drain(maxDrain, doDrain);
    if (fluid != null) {
      tag = (oldTag != null ? (NBTTagCompound)oldTag.func_74737_b() : null);
    }
    if (tank.getFluidAmount() == 0) {
      powered = false;
    }
    markBlockForUpdate(false);
    return fluid;
  }
  
  public boolean canFill(ForgeDirection from, Fluid fluid)
  {
    if (fluid == null) {
      return false;
    }
    return (fluid.getName().equals(FluidRegistry.WATER.getName())) || (fluid == FluidsBREW);
  }
  
  public boolean canDrain(ForgeDirection from, Fluid fluid)
  {
    if (fluid == null) {
      return false;
    }
    return (fluid.getName().equals(FluidRegistry.WATER.getName())) || (fluid == FluidsBREW);
  }
  
  public FluidTankInfo[] getTankInfo(ForgeDirection from)
  {
    return new FluidTankInfo[] { tank.getInfo() };
  }
  
  public boolean isFilled() {
    return tank.getFluid() != null;
  }
  
  public int getMaxLiquidQuantity() {
    return tank.getCapacity();
  }
  
  public int getLiquidQuantity() {
    return tank.getFluidAmount();
  }
  
  public double getPercentFilled() {
    return tank.getFluidAmount() / tank.getCapacity();
  }
  
  public Packet func_145844_m()
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
  
  public void func_145839_a(NBTTagCompound nbtRoot)
  {
    super.func_145839_a(nbtRoot);
    if (tank.getFluidAmount() > 0) {
      tank.drain(tank.getFluidAmount(), true);
    }
    tank.readFromNBT(nbtRoot);
    
    ticksHeated = nbtRoot.func_74762_e("TicksHeated");
    powered = nbtRoot.func_74767_n("Powered");
    ritualTicks = nbtRoot.func_74762_e("RitualTicks");
  }
  
  public void func_145841_b(NBTTagCompound nbtRoot)
  {
    super.func_145841_b(nbtRoot);
    tank.writeToNBT(nbtRoot);
    nbtRoot.func_74768_a("TicksHeated", ticksHeated);
    nbtRoot.func_74757_a("Powered", powered);
    nbtRoot.func_74768_a("RitualTicks", ritualTicks);
  }
  
  public int getRitualSeconds() {
    return ritualTicks;
  }
}
