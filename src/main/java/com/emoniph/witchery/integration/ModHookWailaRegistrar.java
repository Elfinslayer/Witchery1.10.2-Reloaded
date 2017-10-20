package com.emoniph.witchery.integration;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.blocks.BlockPitGrass;
import com.emoniph.witchery.blocks.BlockWitchDoor;
import com.emoniph.witchery.entity.EntityIllusionCreeper;
import com.emoniph.witchery.entity.EntityIllusionSpider;
import com.emoniph.witchery.entity.EntityIllusionZombie;
import com.emoniph.witchery.entity.EntityVillagerWere;
import java.util.List;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import mcp.mobius.waila.api.IWailaEntityProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ModHookWailaRegistrar implements IWailaDataProvider, IWailaEntityProvider
{
  public ModHookWailaRegistrar() {}
  
  public static void callbackRegister(IWailaRegistrar registrar)
  {
    ModHookWailaRegistrar provider = new ModHookWailaRegistrar();
    registrar.registerStackProvider(provider, com.emoniph.witchery.blocks.BlockPlantMine.class);
    registrar.registerStackProvider(provider, BlockWitchDoor.class);
    registrar.registerStackProvider(provider, com.emoniph.witchery.blocks.BlockPitDirt.class);
    registrar.registerStackProvider(provider, BlockPitGrass.class);
    registrar.registerOverrideEntityProvider(provider, EntityIllusionCreeper.class);
    registrar.registerOverrideEntityProvider(provider, EntityIllusionSpider.class);
    registrar.registerOverrideEntityProvider(provider, EntityIllusionZombie.class);
    registrar.registerOverrideEntityProvider(provider, EntityVillagerWere.class);
  }
  
  private static final ItemStack yellowPlant = new ItemStack(Blocks.field_150327_N);
  private static final ItemStack redPlant = new ItemStack(Blocks.field_150328_O);
  private static final ItemStack shrubPlant = new ItemStack(Blocks.field_150330_I);
  private static final ItemStack door = new ItemStack(Items.field_151135_aq);
  private static final ItemStack dirt = new ItemStack(Blocks.field_150346_d);
  private static final ItemStack grass = new ItemStack(Blocks.field_150349_c);
  private static final ItemStack rowandoor = new ItemStack(BlocksDOOR_ROWAN);
  private static Entity CREEPER;
  private static Entity ZOMBIE;
  
  public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) { if (accessor.getBlock() == BlocksTRAPPED_PLANT) {
      int foundMeta = accessor.getMetadata();
      if ((foundMeta == 5) || (foundMeta == 6) || (foundMeta == 7) || (foundMeta == 4))
      {


        return yellowPlant; }
      if ((foundMeta == 1) || (foundMeta == 2) || (foundMeta == 3) || (foundMeta == 0))
      {

        return redPlant; }
      if ((foundMeta == 9) || (foundMeta == 10) || (foundMeta == 11) || (foundMeta == 8))
      {

        return shrubPlant; }
    } else {
      if (accessor.getBlock() == BlocksDOOR_ALDER)
        return door;
      if (accessor.getBlock() == BlocksDOOR_ROWAN)
        return rowandoor;
      if (accessor.getBlock() == BlocksPIT_DIRT)
        return dirt;
      if (accessor.getBlock() == BlocksPIT_GRASS)
        return grass;
    }
    return null;
  }
  

  public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
  {
    return currenttip;
  }
  

  public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
  {
    return currenttip;
  }
  


  public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
  {
    return currenttip;
  }
  


  private static Entity SPIDER;
  
  private static EntityVillager VILLAGER;
  
  public Entity getWailaOverride(IWailaEntityAccessor accessor, IWailaConfigHandler config)
  {
    if ((accessor.getEntity() instanceof EntityIllusionCreeper)) {
      if ((CREEPER == null) || (CREEPERfield_70170_p != accessor.getWorld())) {
        CREEPER = new EntityCreeper(accessor.getWorld());
      }
      return CREEPER; }
    if ((accessor.getEntity() instanceof EntityIllusionZombie)) {
      if ((ZOMBIE == null) || (ZOMBIEfield_70170_p != accessor.getWorld())) {
        ZOMBIE = new EntityZombie(accessor.getWorld());
      }
      return ZOMBIE; }
    if ((accessor.getEntity() instanceof EntityIllusionSpider)) {
      if ((SPIDER == null) || (SPIDERfield_70170_p != accessor.getWorld())) {
        SPIDER = new net.minecraft.entity.monster.EntitySpider(accessor.getWorld());
      }
      return SPIDER; }
    if ((accessor.getEntity() instanceof EntityVillagerWere)) {
      EntityVillagerWere were = (EntityVillagerWere)accessor.getEntity();
      if ((VILLAGER == null) || (VILLAGERfield_70170_p != accessor.getWorld())) {
        VILLAGER = new EntityVillager(accessor.getWorld());
      }
      VILLAGER.func_70938_b(were.func_70946_n());
      return VILLAGER;
    }
    return null;
  }
  

  public List<String> getWailaHead(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config)
  {
    return currenttip;
  }
  

  public List<String> getWailaBody(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config)
  {
    return currenttip;
  }
  

  public List<String> getWailaTail(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config)
  {
    return currenttip;
  }
}
