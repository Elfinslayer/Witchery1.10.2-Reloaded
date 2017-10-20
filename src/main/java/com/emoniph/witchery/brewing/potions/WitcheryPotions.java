package com.emoniph.witchery.brewing.potions;

import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

public class WitcheryPotions
{
  private final List<PotionBase> allEffects;
  private final List<IHandleLivingUpdate> livingUpdateEventHandlers;
  private final List<IHandleLivingJump> livingJumpEventHandlers;
  private final List<IHandleLivingHurt> livingHurtEventHandlers;
  private final List<IHandleLivingDeath> livingDeathEventHandlers;
  private final List<IHandlePlayerDrops> playerDropsEventHandlers;
  private final List<IHandleRenderLiving> renderLivingEventHandlers;
  private final List<IHandlePreRenderLiving> renderLivingPreEventHandlers;
  private final List<IHandleEnderTeleport> enderTeleportEventHandlers;
  private final List<IHandleHarvestDrops> harvestDropsEventHandlers;
  private final List<IHandleLivingSetAttackTarget> livingSetAttackTargetEventHandlers;
  private final List<IHandleLivingAttack> livingAttackEventHandlers;
  public final Potion PARALYSED;
  public final Potion WRAPPED_IN_VINE;
  public final Potion SPIKED;
  public final Potion SPROUTING;
  public final Potion GROTESQUE;
  public final Potion LOVE;
  public final Potion SUN_ALLERGY;
  public final Potion CHILLED;
  public final Potion SNOW_TRAIL;
  public final Potion FLOATING;
  public final Potion NETHER_BOUND;
  public final Potion BREWING_EXPERT;
  public final Potion DOUBLE_JUMP;
  public final Potion FEATHER_FALL;
  public final Potion DARKNESS_ALLERGY;
  public final Potion REINCARNATE;
  public final Potion INSANITY;
  public final Potion KEEP_INVENTORY;
  public final Potion SINKING;
  public final Potion OVERHEATING;
  public final Potion WAKING_NIGHTMARE;
  public final Potion QUEASY;
  public final Potion SWIMMING;
  
  public WitcheryPotions()
  {
    allEffects = new java.util.ArrayList();
    livingUpdateEventHandlers = new java.util.ArrayList();
    livingJumpEventHandlers = new java.util.ArrayList();
    livingHurtEventHandlers = new java.util.ArrayList();
    livingDeathEventHandlers = new java.util.ArrayList();
    playerDropsEventHandlers = new java.util.ArrayList();
    renderLivingEventHandlers = new java.util.ArrayList();
    renderLivingPreEventHandlers = new java.util.ArrayList();
    enderTeleportEventHandlers = new java.util.ArrayList();
    harvestDropsEventHandlers = new java.util.ArrayList();
    livingSetAttackTargetEventHandlers = new java.util.ArrayList();
    livingAttackEventHandlers = new java.util.ArrayList();
    
    PARALYSED = register("witchery:potion.paralysed", PotionParalysis.class);
    WRAPPED_IN_VINE = register("witchery:potion.wrappedinvine", PotionWrappedInVine.class);
    SPIKED = register("witchery:potion.spiked", PotionSpiked.class);
    SPROUTING = register("witchery:potion.sprouting", PotionSprouting.class);
    GROTESQUE = register("witchery:potion.grotesque", PotionGrotesque.class);
    LOVE = register("witchery:potion.love", PotionLove.class);
    SUN_ALLERGY = register("witchery:potion.allergysun", PotionSunAllergy.class);
    CHILLED = register("witchery:potion.chilled", PotionChilled.class);
    SNOW_TRAIL = register("witchery:potion.snowtrail", PotionSnowTrail.class);
    FLOATING = register("witchery:potion.floating", PotionFloating.class);
    NETHER_BOUND = register("witchery:potion.hellishaura", PotionHellishAura.class);
    BREWING_EXPERT = register("witchery:potion.brewingexpertise", PotionBrewingExpertise.class);
    DOUBLE_JUMP = register("witchery:potion.doublejump", PotionBase.class);
    FEATHER_FALL = register("witchery:potion.featherfall", PotionFeatherFall.class);
    DARKNESS_ALLERGY = register("witchery:potion.allergydark", PotionDarknessAllergy.class);
    REINCARNATE = register("witchery:potion.reincarnate", PotionReincarnate.class);
    INSANITY = register("witchery:potion.insane", PotionInsanity.class);
    KEEP_INVENTORY = register("witchery:potion.keepinventory", PotionKeepInventory.class);
    SINKING = register("witchery:potion.sinking", PotionSinking.class);
    OVERHEATING = register("witchery:potion.overheating", PotionOverheating.class);
    WAKING_NIGHTMARE = register("witchery:potion.wakingnightmare", PotionWakingNightmare.class);
    QUEASY = register("witchery:potion.queasy", PotionQueasy.class);
    SWIMMING = register("witchery:potion.swimming", PotionSwimming.class);
    RESIZING = register("witchery:potion.resizing", PotionResizing.class);
    COLORFUL = register("witchery:potion.colorful", PotionColorful.class);
    ENDER_INHIBITION = register("witchery:potion.enderinhibition", PotionEnderInhibition.class);
    ILL_FITTING = register("witchery:potion.illfitting", PotionIllFitting.class);
    VOLATILITY = register("witchery:potion.volatility", PotionVolatility.class);
    ENSLAVED = register("witchery:potion.enslaved", PotionEnslaved.class);
    MORTAL_COIL = register("witchery:potion.mortalcoil", PotionMortalCoil.class);
    ABSORB_MAGIC = register("witchery:potion.absorbmagic", PotionAbsorbMagic.class);
    POISON_WEAPONS = register("witchery:potion.poisonweapons", PotionPoisonWeapons.class);
    REFLECT_PROJECTILES = register("witchery:potion.reflectprojectiles", PotionReflectProjectiles.class);
    
    REFLECT_DAMAGE = register("witchery:potion.reflectdamage", PotionReflectDamage.class);
    ATTRACT_PROJECTILES = register("witchery:potion.attractprojectiles", PotionAttractProjectiles.class);
    
    REPELL_ATTACKER = register("witchery:potion.repellattacker", PotionRepellAttacker.class);
    STOUT_BELLY = register("witchery:potion.stoutbelly", PotionStoutBelly.class);
    FEEL_NO_PAIN = register("witchery:potion.feelnopain", PotionFeelNoPain.class);
    GAS_MASK = register("witchery:potion.gasmask", PotionGasMask.class);
    
    DISEASED = register("witchery:potion.diseased", PotionDiseased.class);
    FORTUNE = register("witchery:potion.fortune", PotionFortune.class);
    WORSHIP = register("witchery:potion.worship", PotionWorship.class);
    KEEP_EFFECTS = register("witchery:potion.keepeffects", PotionKeepEffectsOnDeath.class);
    WOLFSBANE = register("witchery:potion.wolfsbane", PotionBase.class);
  }
  
  private Potion register(String unlocalisedName, Class<? extends PotionBase> clazz) { int potionID = -1;
    
    PotionArrayExtender.access$000();
    
    if (potionID < 1) {
      for (int i = instancepotionStartID; i < Potion.field_76425_a.length; i++) {
        if (Potion.field_76425_a[i] == null) {
          potionID = instanceconfiguration.get("potions", unlocalisedName, i).getInt();
          break;
        }
      }
    }
    
    if ((potionID > 31) && (potionID < Potion.field_76425_a.length)) {
      try {
        if (Potion.field_76425_a[potionID] != null) {
          com.emoniph.witchery.util.Log.instance().warning(String.format("Potion slot %d already occupided by %s is being overwriting with %s, you may want to change potion ids in the config file!", new Object[] { Integer.valueOf(potionID), Potion.field_76425_a[potionID].func_76393_a(), unlocalisedName }));
        }
        




        if (potionID > 127) {
          com.emoniph.witchery.util.Log.instance().warning(String.format("Using potion slot %d (for potion %s), can lead to problems, since there is a client/server syncing restriction of max 128 potion IDs. Use the PotionStartID configuration setting to lower the range witchery uses.", new Object[] { Integer.valueOf(potionID), unlocalisedName }));
        }
        




        java.lang.reflect.Constructor<? extends PotionBase> ctor = clazz.getConstructor(new Class[] { Integer.TYPE, Integer.TYPE });
        PotionBase potion = (PotionBase)ctor.newInstance(new Object[] { Integer.valueOf(potionID), Integer.valueOf(unlocalisedName.hashCode()) });
        potion.func_76390_b(unlocalisedName);
        allEffects.add(potion);
        if ((potion instanceof IHandleLivingHurt)) {
          livingHurtEventHandlers.add((IHandleLivingHurt)potion);
        }
        if ((potion instanceof IHandleLivingDeath)) {
          livingDeathEventHandlers.add((IHandleLivingDeath)potion);
        }
        if ((potion instanceof IHandleLivingUpdate)) {
          livingUpdateEventHandlers.add((IHandleLivingUpdate)potion);
        }
        if ((potion instanceof IHandleRenderLiving)) {
          renderLivingEventHandlers.add((IHandleRenderLiving)potion);
        }
        
        if ((potion instanceof IHandlePreRenderLiving)) {
          renderLivingPreEventHandlers.add((IHandlePreRenderLiving)potion);
        }
        if ((potion instanceof IHandlePlayerDrops)) {
          playerDropsEventHandlers.add((IHandlePlayerDrops)potion);
        }
        
        if ((potion instanceof IHandleLivingJump)) {
          livingJumpEventHandlers.add((IHandleLivingJump)potion);
        }
        
        if ((potion instanceof IHandleEnderTeleport)) {
          enderTeleportEventHandlers.add((IHandleEnderTeleport)potion);
        }
        
        if ((potion instanceof IHandleLivingSetAttackTarget)) {
          livingSetAttackTargetEventHandlers.add((IHandleLivingSetAttackTarget)potion);
        }
        
        if ((potion instanceof IHandleHarvestDrops)) {
          harvestDropsEventHandlers.add((IHandleHarvestDrops)potion);
        }
        
        if ((potion instanceof IHandleLivingAttack)) {
          livingAttackEventHandlers.add((IHandleLivingAttack)potion);
        }
        
        potion.postContructInitialize();
        return potion;
      } catch (NoSuchMethodException ex) {
        return null;
      } catch (java.lang.reflect.InvocationTargetException ex) {
        return null;
      } catch (InstantiationException ex) {
        return null;
      } catch (IllegalAccessException ex) {
        return null;
      }
    }
    com.emoniph.witchery.util.Log.instance().warning(String.format("Failed to assign potion %s to slot %d, max slot id is %d, you may want to change the potion ids in the config file!", new Object[] { unlocalisedName, Integer.valueOf(potionID), Integer.valueOf(Potion.field_76425_a.length - 1) }));
    



    return null; }
  
  public final Potion RESIZING;
  public final Potion COLORFUL;
  
  public void preInit() { com.emoniph.witchery.util.Config.instance().saveIfChanged(); }
  
  public final Potion ENDER_INHIBITION;
  
  public void init() { for (Potion potion : allEffects)
      if ((field_76415_H > 0) && (field_76415_H < Potion.field_76425_a.length)) {
        if (Potion.field_76425_a[field_76415_H] != potion) {
          com.emoniph.witchery.util.Log.instance().warning(String.format("Another mod has overwritten Witchery potion %s in slot %d! offender: %s.", new Object[] { potion.func_76393_a(), Integer.valueOf(field_76415_H), Potion.field_76425_a[field_76415_H].func_76393_a() }));
        }
        

      }
      else
        com.emoniph.witchery.util.Log.instance().warning(String.format("Witchery potion has not been registered: %s!", new Object[] { potion.func_76393_a() }));
  }
  
  public final Potion ILL_FITTING;
  public final Potion VOLATILITY;
  public final Potion ENSLAVED;
  public final Potion MORTAL_COIL;
  
  private static class PotionArrayExtender { private PotionArrayExtender() {}
    
    private static void extendPotionArray() { if (!potionArrayExtended) {
        int RESERVED = 32;
        int MAX_EXTRA = Math.min(instancepotionStartID - 32, 0) + 96;
        com.emoniph.witchery.util.Log.instance().debug("Extending the vanilla potions array");
        int existingArrayLength = Potion.field_76425_a.length;
        Potion[] newPotionArray = new Potion[existingArrayLength + MAX_EXTRA];
        System.arraycopy(Potion.field_76425_a, 0, newPotionArray, 0, existingArrayLength);
        setPrivateFinalValue(Potion.class, null, newPotionArray, new String[] { "potionTypes", "field_76425_a" });
        potionArrayExtended = true;
      }
    }
    
    private static boolean potionArrayExtended;
    private static <T, E> void setPrivateFinalValue(Class<? super T> classToAccess, T instance, E value, String... fieldNames) {
      java.lang.reflect.Field field = cpw.mods.fml.relauncher.ReflectionHelper.findField(classToAccess, cpw.mods.fml.common.ObfuscationReflectionHelper.remapFieldNames(classToAccess.getName(), fieldNames));
      try
      {
        java.lang.reflect.Field modifiersField = java.lang.reflect.Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & 0xFFFFFFEF);
        field.set(instance, value);
      } catch (Throwable e) {
        e.printStackTrace(); } } }
  
  public final Potion ABSORB_MAGIC;
  public final Potion POISON_WEAPONS;
  
  public static class EventHooks { public EventHooks() {}
    
    @cpw.mods.fml.common.eventhandler.SubscribeEvent(priority=cpw.mods.fml.common.eventhandler.EventPriority.HIGHEST)
    public void onPlayerDrops(net.minecraftforge.event.entity.player.PlayerDropsEvent event) { for (IHandlePlayerDrops handler : PotionsplayerDropsEventHandlers) {
        if (event.isCanceled()) {
          break;
        }
        if (entityLiving.func_70644_a(handler.getPotion())) {
          PotionEffect effect = entityLiving.func_70660_b(handler.getPotion());
          handler.onPlayerDrops(entityPlayer.func_130014_f_(), entityPlayer, event, effect.func_76458_c());
        }
      }
    }
    
    @cpw.mods.fml.common.eventhandler.SubscribeEvent(priority=cpw.mods.fml.common.eventhandler.EventPriority.HIGH)
    public void onBlockHarvest(net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent event)
    {
      for (IHandleHarvestDrops handler : PotionsharvestDropsEventHandlers) {
        if (event.isCanceled()) {
          break;
        }
        if ((harvester != null) && (harvester.func_70644_a(handler.getPotion()))) {
          PotionEffect effect = harvester.func_70660_b(handler.getPotion());
          handler.onHarvestDrops(world, harvester, event, effect.func_76458_c());
        }
      }
    }
    
    @cpw.mods.fml.common.eventhandler.SubscribeEvent
    public void onLivingHurt(net.minecraftforge.event.entity.living.LivingHurtEvent event) {
      for (IHandleLivingHurt handler : PotionslivingHurtEventHandlers) {
        if (event.isCanceled()) {
          break;
        }
        if ((handler.handleAllHurtEvents()) || (entityLiving.func_70644_a(handler.getPotion()))) {
          PotionEffect effect = entityLiving.func_70660_b(handler.getPotion());
          handler.onLivingHurt(entityLiving.field_70170_p, entityLiving, event, effect != null ? effect.func_76458_c() : -1);
        }
      }
    }
    
    @cpw.mods.fml.common.eventhandler.SubscribeEvent
    public void onLivingUpdate(net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent event)
    {
      for (IHandleLivingUpdate handler : PotionslivingUpdateEventHandlers) {
        if (event.isCanceled()) {
          break;
        }
        if (entityLiving.func_70644_a(handler.getPotion())) {
          PotionEffect effect = entityLiving.func_70660_b(handler.getPotion());
          handler.onLivingUpdate(entityLiving.field_70170_p, entityLiving, event, effect.func_76458_c(), effect.func_76459_b());
        }
      }
    }
    
    @cpw.mods.fml.common.eventhandler.SubscribeEvent
    public void onLivingAttack(LivingAttackEvent event)
    {
      for (IHandleLivingAttack handler : PotionslivingAttackEventHandlers) {
        if (event.isCanceled()) {
          break;
        }
        if (entityLiving.func_70644_a(handler.getPotion())) {
          PotionEffect effect = entityLiving.func_70660_b(handler.getPotion());
          handler.onLivingAttack(entityLiving.field_70170_p, entityLiving, event, effect.func_76458_c());
        }
      }
      
      if ((modHooksisAM2Present) && (!event.isCanceled()) && (!entity.field_70170_p.field_72995_K) && (source == net.minecraft.util.DamageSource.field_76368_d) && ((entity instanceof net.minecraft.entity.player.EntityPlayer)) && ((com.emoniph.witchery.common.ExtendedPlayer.get((net.minecraft.entity.player.EntityPlayer)entity).getCreatureType() == com.emoniph.witchery.util.TransformCreature.WOLF) || (com.emoniph.witchery.common.ExtendedPlayer.get((net.minecraft.entity.player.EntityPlayer)entity).getCreatureType() == com.emoniph.witchery.util.TransformCreature.BAT)))
      {


        if (!entity.field_70170_p.func_147439_a(net.minecraft.util.MathHelper.func_76128_c(entity.field_70165_t), net.minecraft.util.MathHelper.func_76128_c(entity.field_70163_u), net.minecraft.util.MathHelper.func_76128_c(entity.field_70161_v)).func_149721_r())
        {

          event.setCanceled(true);
        }
      }
    }
    
    @cpw.mods.fml.common.eventhandler.SubscribeEvent
    public void onLivingJump(net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent event) {
      for (IHandleLivingJump handler : PotionslivingJumpEventHandlers) {
        if (event.isCanceled()) {
          break;
        }
        if (entityLiving.func_70644_a(handler.getPotion())) {
          PotionEffect effect = entityLiving.func_70660_b(handler.getPotion());
          handler.onLivingJump(entityLiving.field_70170_p, entityLiving, event, effect.func_76458_c());
        }
      }
    }
    
    @cpw.mods.fml.common.eventhandler.SubscribeEvent
    public void onEnderTeleport(net.minecraftforge.event.entity.living.EnderTeleportEvent event)
    {
      if ((entityLiving != null) && ((entityLiving.field_70170_p.field_73011_w.field_76574_g == instancedimensionTormentID) || (entityLiving.field_70170_p.field_73011_w.field_76574_g == instancedimensionMirrorID)))
      {

        event.setCanceled(true);
        return;
      }
      for (IHandleEnderTeleport handler : PotionsenderTeleportEventHandlers) {
        if (event.isCanceled()) {
          break;
        }
        if (entityLiving.func_70644_a(handler.getPotion())) {
          PotionEffect effect = entityLiving.func_70660_b(handler.getPotion());
          handler.onEnderTeleport(entityLiving.field_70170_p, entityLiving, event, effect.func_76458_c());
        }
      }
    }
    
    @cpw.mods.fml.common.eventhandler.SubscribeEvent
    public void onLivingSetAttackTarget(net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent event) {
      net.minecraft.entity.EntityLiving livingEntity;
      if ((entityLiving instanceof net.minecraft.entity.EntityLiving)) {
        livingEntity = (net.minecraft.entity.EntityLiving)entityLiving;
        if ((livingEntity != null) && (PotionsENSLAVED != null) && (target != null) && ((target instanceof net.minecraft.entity.player.EntityPlayer))) {
          net.minecraft.entity.player.EntityPlayer player = (net.minecraft.entity.player.EntityPlayer)target;
          if ((!livingEntity.func_70644_a(PotionsENSLAVED)) && (PotionEnslaved.isMobEnslavedBy(livingEntity, player)))
          {
            livingEntity.func_70624_b(null);
          }
        }
        
        for (IHandleLivingSetAttackTarget handler : PotionslivingSetAttackTargetEventHandlers) {
          if (event.isCanceled()) {
            break;
          }
          if (entityLiving.func_70644_a(handler.getPotion())) {
            PotionEffect effect = entityLiving.func_70660_b(handler.getPotion());
            handler.onLivingSetAttackTarget(entityLiving.field_70170_p, livingEntity, event, effect.func_76458_c());
          }
        }
      }
    }
    
    @cpw.mods.fml.common.eventhandler.SubscribeEvent(priority=cpw.mods.fml.common.eventhandler.EventPriority.LOW)
    public void onLivingDeath(net.minecraftforge.event.entity.living.LivingDeathEvent event)
    {
      for (IHandleLivingDeath handler : PotionslivingDeathEventHandlers) {
        if (event.isCanceled()) {
          break;
        }
        if (entityLiving.func_70644_a(handler.getPotion())) {
          PotionEffect effect = entityLiving.func_70660_b(handler.getPotion());
          handler.onLivingDeath(entityLiving.field_70170_p, entityLiving, event, effect.func_76458_c());
        }
      }
      

      if ((!entityLiving.field_70170_p.field_72995_K) && ((entityLiving instanceof net.minecraft.entity.player.EntityPlayer))) {
        net.minecraft.entity.player.EntityPlayer player = (net.minecraft.entity.player.EntityPlayer)entityLiving;
        java.util.Collection<PotionEffect> activeEffects = player.func_70651_bq();
        if (activeEffects.size() > 0) {
          java.util.ArrayList<PotionEffect> permenantEffectList = new java.util.ArrayList();
          int allPermentantLevel = -1;
          if (player.func_70644_a(PotionsKEEP_EFFECTS)) {
            PotionEffect permAll = player.func_70660_b(PotionsKEEP_EFFECTS);
            allPermentantLevel = permAll.func_76458_c();
          }
          for (PotionEffect effect : activeEffects) {
            int potionID = effect.func_76456_a();
            if ((potionID >= 0) && (potionID < Potion.field_76425_a.length) && (Potion.field_76425_a[potionID] != null))
            {

              if ((Potion.field_76425_a[potionID] instanceof PotionBase)) {
                PotionBase potion = (PotionBase)Potion.field_76425_a[potionID];
                if (potion.isPermenant()) {
                  permenantEffectList.add(effect);
                  continue;
                }
              }
              Potion potion = Potion.field_76425_a[potionID];
              if ((!PotionBase.isDebuff(potion)) && (allPermentantLevel >= effect.func_76458_c())) {
                permenantEffectList.add(effect);
              }
            }
          }
          
          if (permenantEffectList.size() > 0) {
            net.minecraft.nbt.NBTTagList nbtEffectList = new net.minecraft.nbt.NBTTagList();
            for (PotionEffect permenantEffect : permenantEffectList) {
              net.minecraft.nbt.NBTTagCompound nbtEffect = new net.minecraft.nbt.NBTTagCompound();
              permenantEffect.func_82719_a(nbtEffect);
              nbtEffectList.func_74742_a(nbtEffect);
            }
            net.minecraft.nbt.NBTTagCompound nbtPlayer = com.emoniph.witchery.infusion.Infusion.getNBT(player);
            nbtPlayer.func_74782_a("WITCPoSpawn", nbtEffectList); } } } } }
  
  public final Potion REFLECT_PROJECTILES;
  public final Potion REFLECT_DAMAGE;
  public final Potion ATTRACT_PROJECTILES;
  public final Potion REPELL_ATTACKER;
  public final Potion STOUT_BELLY;
  public final Potion FEEL_NO_PAIN; public final Potion GAS_MASK;
  public static class ClientEventHooks { public ClientEventHooks() {}
    @cpw.mods.fml.common.eventhandler.SubscribeEvent(priority=cpw.mods.fml.common.eventhandler.EventPriority.NORMAL)
    public void onRenderLiving(net.minecraftforge.client.event.RenderLivingEvent.Pre event) { if ((entity != null) && (entity.field_70170_p != null) && (entity.field_70170_p.field_72995_K)) {
        for (IHandlePreRenderLiving handler : PotionsrenderLivingPreEventHandlers) {
          if (event.isCanceled()) {
            break;
          }
          if (entity.func_82165_m(getPotionfield_76415_H)) {
            PotionEffect effect = entity.func_70660_b(handler.getPotion());
            handler.onLivingRender(entity.field_70170_p, entity, event, effect.func_76458_c());
          }
        }
      }
    }
    
    @cpw.mods.fml.common.eventhandler.SubscribeEvent(priority=cpw.mods.fml.common.eventhandler.EventPriority.NORMAL)
    public void onRenderLiving(net.minecraftforge.client.event.RenderLivingEvent.Post event) {
      if ((entity != null) && (entity.field_70170_p != null) && (entity.field_70170_p.field_72995_K)) {
        for (IHandleRenderLiving handler : PotionsrenderLivingEventHandlers) {
          if (event.isCanceled()) {
            break;
          }
          if (entity.func_82165_m(getPotionfield_76415_H)) {
            PotionEffect effect = entity.func_70660_b(handler.getPotion());
            handler.onLivingRender(entity.field_70170_p, entity, event, effect.func_76458_c());
          }
        }
      }
    }
    
    @cpw.mods.fml.common.eventhandler.SubscribeEvent
    public void onDrawBlockHighlight(net.minecraftforge.client.event.DrawBlockHighlightEvent event) {
      if ((event != null) && (!event.isCanceled()) && (player != null)) {
        if ((player.func_70644_a(PotionsRESIZING)) || (!com.emoniph.witchery.util.EntitySizeInfoplayer).isDefault))
        {
          double reach = func_71410_xfield_71442_b.func_78757_d();
          net.minecraft.util.MovingObjectPosition mop = player.func_70614_a(reach, partialTicks);
          if ((mop != null) && (!com.emoniph.witchery.blocks.BlockBeartrap.checkForHiddenTrap(player, mop))) {
            context.func_72731_b(player, mop, 0, partialTicks);
          }
          event.setCanceled(true);
        }
        else if (com.emoniph.witchery.blocks.BlockBeartrap.checkForHiddenTrap(player, target)) {
          event.setCanceled(true);
        }
      }
    }
  }
  
  public final Potion DISEASED;
  public final Potion FORTUNE;
  public final Potion WORSHIP;
  public final Potion KEEP_EFFECTS;
  public final Potion WOLFSBANE;
}
