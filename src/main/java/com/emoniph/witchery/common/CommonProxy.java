package com.emoniph.witchery.common;

import com.emoniph.witchery.blocks.BlockAreaMarker.AreaMarkerEventHooks;
import com.emoniph.witchery.blocks.BlockDistillery.ContainerDistillery;
import com.emoniph.witchery.blocks.BlockDistillery.TileEntityDistillery;
import com.emoniph.witchery.blocks.BlockSpinningWheel.ContainerSpinningWheel;
import com.emoniph.witchery.blocks.BlockSpinningWheel.TileEntitySpinningWheel;
import com.emoniph.witchery.blocks.BlockWitchesOven.ContainerWitchesOven;
import com.emoniph.witchery.blocks.BlockWitchesOven.TileEntityWitchesOven;
import com.emoniph.witchery.brewing.DispersalTriggered.EventHooks;
import com.emoniph.witchery.brewing.potions.WitcheryPotions.EventHooks;
import com.emoniph.witchery.entity.EntityBroom.EventHooks;
import com.emoniph.witchery.infusion.Infusion.EventHooks;
import com.emoniph.witchery.item.ItemBrewBag.ContainerBrewBag;
import com.emoniph.witchery.item.ItemGoblinClothes.EventHooks;
import com.emoniph.witchery.item.ItemLeonardsUrn.ContainerLeonardsUrn;
import com.emoniph.witchery.item.ItemLeonardsUrn.InventoryLeonardsUrn;
import com.emoniph.witchery.item.ItemPoppet.PoppetEventHooks;
import com.emoniph.witchery.item.ItemWitchHand.EventHooks;
import com.emoniph.witchery.ritual.rites.RitePriorIncarnation.EventHooks;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.worldgen.WorldHandlerVillageDistrict.EventHooks;
import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy implements IGuiHandler
{
  public CommonProxy() {}
  
  private static final Map<String, NBTTagCompound> extendedEntityData = new HashMap();
  
  public static void storeEntityData(String name, NBTTagCompound compound) {
    extendedEntityData.put(name, compound);
  }
  
  public static NBTTagCompound getEntityData(String name) {
    return (NBTTagCompound)extendedEntityData.remove(name);
  }
  

  public void preInit() {}
  
  public void registerEvents()
  {
    MinecraftForge.EVENT_BUS.register(new ItemPoppet.PoppetEventHooks());
    MinecraftForge.EVENT_BUS.register(new Infusion.EventHooks());
    MinecraftForge.EVENT_BUS.register(new ItemWitchHand.EventHooks());
    MinecraftForge.EVENT_BUS.register(new EntityBroom.EventHooks());
    MinecraftForge.EVENT_BUS.register(new RitePriorIncarnation.EventHooks());
    MinecraftForge.EVENT_BUS.register(new BlockAreaMarker.AreaMarkerEventHooks());
    MinecraftForge.EVENT_BUS.register(new GenericEvents());
    MinecraftForge.EVENT_BUS.register(new ItemGoblinClothes.EventHooks());
    MinecraftForge.EVENT_BUS.register(new WitcheryPotions.EventHooks());
    MinecraftForge.EVENT_BUS.register(new DispersalTriggered.EventHooks());
    MinecraftForge.TERRAIN_GEN_BUS.register(new WorldHandlerVillageDistrict.EventHooks());
  }
  


  public void registerRenderers() {}
  


  public void registerServerHandlers() {}
  

  public void registerHandlers() {}
  

  public void postInit() {}
  

  public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
  {
    switch (ID) {
    case 0: 
      return null;
    

    case 2: 
      return new BlockWitchesOven.ContainerWitchesOven(field_71071_by, (BlockWitchesOven.TileEntityWitchesOven)world.func_147438_o(x, y, z));
    case 3: 
      return new BlockDistillery.ContainerDistillery(field_71071_by, (BlockDistillery.TileEntityDistillery)world.func_147438_o(x, y, z));
    case 4: 
      return new BlockSpinningWheel.ContainerSpinningWheel(field_71071_by, (BlockSpinningWheel.TileEntitySpinningWheel)world.func_147438_o(x, y, z));
    
    case 5: 
      return new ItemBrewBag.ContainerBrewBag(field_71071_by, new com.emoniph.witchery.item.ItemBrewBag.InventoryBrewBag(player), player.func_70694_bm());
    case 8: 
      return new ItemLeonardsUrn.ContainerLeonardsUrn(field_71071_by, new ItemLeonardsUrn.InventoryLeonardsUrn(player), player.func_70694_bm());
    }
    return null;
  }
  
  public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
  {
    return null;
  }
  
  public boolean getGraphicsLevel() {
    return false;
  }
  
  public int getStockageRenderId()
  {
    return 0;
  }
  
  public int getGasRenderId() {
    return 0;
  }
  
  public int getPitGrassRenderId() {
    return 0;
  }
  
  public int getBrewLiquidRenderId() {
    return 0;
  }
  

  public void registerVillagers() {}
  

  public void generateParticle(World worldObj, double posX, double posY, double posZ, float f, float g, float h, int i, float j) {}
  
  public EntityPlayer getPlayer(MessageContext ctx)
  {
    if (side == Side.SERVER) {
      return getServerHandlerfield_147369_b;
    }
    return null;
  }
  
  public int getVineRenderId()
  {
    return 0;
  }
  
  public void showParticleEffect(World world, double x, double y, double z, double width, double height, SoundEffect sound, int color, ParticleEffect particle) {}
}
