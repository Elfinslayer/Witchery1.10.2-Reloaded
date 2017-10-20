package com.emoniph.witchery.dimension;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.blocks.BlockFetish.ClassItemBlock;
import com.emoniph.witchery.entity.EntityCorpse;
import com.emoniph.witchery.entity.EntityDemon;
import com.emoniph.witchery.entity.EntityNightmare;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import com.emoniph.witchery.network.PacketPipeline;
import com.emoniph.witchery.network.PacketPlayerStyle;
import com.emoniph.witchery.network.PacketPushTarget;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.Log;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.FoodStats;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldProviderDreamWorld extends WorldProvider
{
  public WorldProviderDreamWorld() {}
  
  public void setDimension(int dim)
  {
    field_76574_g = dim;
    super.setDimension(dim);
  }
  
  public long getSeed()
  {
    Long seed = Long.valueOf(super.getSeed());
    return seed.longValue();
  }
  
  public IChunkProvider func_76555_c()
  {
    WorldProvider overworldProvider = net.minecraftforge.common.DimensionManager.getProvider(0);
    return field_76577_b.getChunkGenerator(field_76579_a, field_76579_a.func_72912_H().func_82571_y());
  }
  
  public void func_76572_b()
  {
    super.func_76572_b();
    field_76574_g = instancedimensionDreamID;
  }
  
  public String getWelcomeMessage()
  {
    if ((this instanceof WorldProviderDreamWorld)) {
      return "Entering the " + func_80007_l();
    }
    return null;
  }
  
  public String getDepartMessage()
  {
    if ((this instanceof WorldProviderDreamWorld)) {
      return "Leaving the " + func_80007_l();
    }
    return null;
  }
  
  public String func_80007_l()
  {
    return "Spirit World";
  }
  
  public float getStarBrightness(float par1)
  {
    return 0.0F;
  }
  
  public boolean func_76567_e()
  {
    return false;
  }
  

  public double getMovementFactor()
  {
    return 1.0D;
  }
  
  int nightmare = 0;
  private static final String SPIRIT_WORLD_KEY = "WITCSpiritWorld";
  private static final String SPIRIT_WORLD_WALKING_KEY = "WITCSpiritWalking";
  
  public float func_76563_a(long par1, float par3) { return nightmare > 0 ? 0.5F : 1.0F; }
  

  public float func_76571_f()
  {
    return 0.0F;
  }
  
  public boolean func_76566_a(int par1, int par2)
  {
    int var3 = field_76579_a.func_72825_h(par1, par2);
    return var3 != -1;
  }
  
  public ChunkCoordinates func_76554_h()
  {
    return new ChunkCoordinates(100, 50, 0);
  }
  
  public int func_76557_i()
  {
    return 64;
  }
  
  public double getHorizon()
  {
    return 0.0D;
  }
  
  @SideOnly(Side.CLIENT)
  public boolean hasVoidParticles(boolean var1) {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public boolean func_76561_g()
  {
    return true;
  }
  
  public double func_76565_k()
  {
    return 1.0D;
  }
  
  @SideOnly(Side.CLIENT)
  public Vec3 func_76562_b(float par1, float par2)
  {
    float var3 = MathHelper.func_76134_b(par1 * 3.1415927F * 2.0F) * 2.0F + 0.5F;
    
    if (var3 < 0.0F) {
      var3 = 0.0F;
    }
    
    if (var3 > 1.0F)
      var3 = 1.0F;
    float var6;
    float var4;
    float var5;
    float var6; if (nightmare == 0) {
      float var4 = 0.8F;
      float var5 = 0.2F;
      var6 = 0.6F; } else { float var6;
      if (nightmare == 1) {
        float var4 = 0.0F;
        float var5 = 1.0F;
        var6 = 0.0F;
      } else {
        var4 = 1.0F;
        var5 = 0.0F;
        var6 = 0.0F;
      } }
    var4 *= (var3 * 0.94F + 0.06F);
    var5 *= (var3 * 0.94F + 0.06F);
    var6 *= (var3 * 0.91F + 0.09F);
    return Vec3.func_72443_a(var4, var5, var6);
  }
  
  public void setAllowedSpawnTypes(boolean allowHostile, boolean allowPeaceful)
  {
    allowPeaceful = true;
  }
  

  private static final String SPIRIT_WORLD_NIGHTMARE_KEY = "Nightmare";
  private static final String SPIRIT_WORLD_DEMONIC_KEY = "Demonic";
  private static final String SPIRIT_WORLD_OVERWORLD_BODY_KEY = "OverworldBody";
  private static final String SPIRIT_WORLD_OVERWORLD_HEALTH_KEY = "OverworldHealth";
  private static final String SPIRIT_WORLD_SPIRIT_HEALTH_KEY = "SpiritHealth";
  private static final String SPIRIT_WORLD_OVERWORLD_HUNGER_FOOD_KEY = "OverworldHunger";
  private static final String SPIRIT_WORLD_SPIRIT_HUNGER_FOOD_KEY = "SpiritHunger";
  private static final String SPIRIT_WORLD_OVERWORLD_INVENTORY_KEY = "OverworldInventory";
  private static final String SPIRIT_WORLD_SPIRIT_INVENTORY_KEY = "SpiritInventory";
  private static final String SPIRIT_WORLD_MANIFEST_GHOST_KEY = "WITCManifested";
  public static final String SPIRIT_WORLD_MANIFEST_TIME_KEY = "WITCManifestDuration";
  public static final String SPIRIT_WORLD_AWAKEN_PLAYER_KEY = "WITCForceAwaken";
  private static final String SPIRIT_WORLD_LAST_NIGHTMARE_KILL_KEY = "LastNightmareKillTime";
  public static final String SPIRIT_WORLD_MANIFEST_SKIP_TIME_TICK_KEY = "WITCManifestSkipTick";
  public void updateWeather()
  {
    if ((field_76579_a != null) && (field_76579_a.field_73012_v.nextInt(20) == 0)) {
      int playerHasNightmare = 0;
      for (Object obj : field_76579_a.field_73010_i) {
        EntityPlayer player = (EntityPlayer)obj;
        int level = getPlayerHasNightmare(player);
        if (level > playerHasNightmare) {
          playerHasNightmare = level;
          break;
        }
      }
      if (nightmare != playerHasNightmare) {
        nightmare = playerHasNightmare;
      }
    }
    super.updateWeather();
  }
  
  public boolean isNightmare() {
    return nightmare > 0;
  }
  
  public boolean isDemonicNightmare() {
    return nightmare > 1;
  }
  
  public static int getPlayerHasNightmare(EntityPlayer player) {
    NBTTagCompound nbtPlayer = Infusion.getNBT(player);
    return getPlayerHasNightmare(nbtPlayer);
  }
  
  public static int getPlayerHasNightmare(NBTTagCompound nbtPlayer) {
    if (!nbtPlayer.func_74764_b("WITCSpiritWorld")) {
      return 0;
    }
    NBTTagCompound nbtSpirit = nbtPlayer.func_74775_l("WITCSpiritWorld");
    boolean nightmare = nbtSpirit.func_74767_n("Nightmare");
    boolean demonic = nbtSpirit.func_74767_n("Demonic");
    return nightmare ? 1 : (nightmare) && (demonic) ? 2 : 0;
  }
  
  public static void setPlayerHasNightmare(EntityPlayer player, boolean nightmare, boolean demonic) {
    NBTTagCompound nbtPlayer = Infusion.getNBT(player);
    setPlayerHasNightmare(nbtPlayer, nightmare, demonic);
  }
  
  public static void setPlayerHasNightmare(NBTTagCompound nbtPlayer, boolean nightmare, boolean demonic) {
    if (!nbtPlayer.func_74764_b("WITCSpiritWorld")) {
      nbtPlayer.func_74782_a("WITCSpiritWorld", new NBTTagCompound());
    }
    NBTTagCompound nbtSpirit = nbtPlayer.func_74775_l("WITCSpiritWorld");
    nbtSpirit.func_74757_a("Nightmare", nightmare);
    nbtSpirit.func_74757_a("Demonic", demonic);
  }
  

  public static void setPlayerLastNightmareKillNow(EntityPlayer player)
  {
    if (player != null) {
      NBTTagCompound nbtPlayer = Infusion.getNBT(player);
      long time = MinecraftServer.func_130071_aq();
      setPlayerLastNightmareKill(nbtPlayer, time);
    }
  }
  
  public static void setPlayerLastNightmareKill(NBTTagCompound nbtPlayer, long time) {
    if (!nbtPlayer.func_74764_b("WITCSpiritWorld")) {
      nbtPlayer.func_74782_a("WITCSpiritWorld", new NBTTagCompound());
    }
    NBTTagCompound nbtSpirit = nbtPlayer.func_74775_l("WITCSpiritWorld");
    nbtSpirit.func_74772_a("LastNightmareKillTime", time);
  }
  
  public static long getPlayerLastNightmareKill(NBTTagCompound nbtPlayer) {
    if (!nbtPlayer.func_74764_b("WITCSpiritWorld")) {
      return 0L;
    }
    NBTTagCompound nbtSpirit = nbtPlayer.func_74775_l("WITCSpiritWorld");
    if (!nbtSpirit.func_74764_b("LastNightmareKillTime")) {
      return 0L;
    }
    long time = nbtSpirit.func_74763_f("LastNightmareKillTime");
    return time;
  }
  
  public static boolean getPlayerIsSpiritWalking(EntityPlayer player) {
    NBTTagCompound nbtPlayer = Infusion.getNBT(player);
    return getPlayerIsSpiritWalking(nbtPlayer);
  }
  
  public static boolean getPlayerIsSpiritWalking(NBTTagCompound nbtPlayer) {
    boolean walking = nbtPlayer.func_74767_n("WITCSpiritWalking");
    return walking;
  }
  
  public static void setPlayerIsSpiritWalking(EntityPlayer player, boolean walking) {
    NBTTagCompound nbtPlayer = Infusion.getNBT(player);
    setPlayerIsSpiritWalking(nbtPlayer, walking);
  }
  
  public static void setPlayerIsSpiritWalking(NBTTagCompound nbtPlayer, boolean walking) {
    nbtPlayer.func_74757_a("WITCSpiritWalking", walking);
  }
  
  private static void addItemToInventory(EntityPlayer player, ItemStack protoStack, int totalQuantity) {
    if (totalQuantity > 0) {
      int itemsRemaining = totalQuantity;
      int maxStack = protoStack.func_77976_d();
      while (itemsRemaining > 0) {
        int quantity = itemsRemaining > maxStack ? maxStack : itemsRemaining;
        itemsRemaining -= quantity;
        ItemStack newStack = new ItemStack(protoStack.func_77973_b(), quantity, protoStack.func_77960_j());
        field_71071_by.func_70441_a(newStack);
      }
    }
  }
  
  private static void addItemToInventory(EntityPlayer player, ArrayList<ItemStack> stacks) {
    for (ItemStack stack : stacks) {
      if (!field_71071_by.func_70441_a(stack)) {
        field_70170_p.func_72838_d(new EntityItem(field_70170_p, field_70165_t, 0.5D + field_70163_u, field_70161_v, stack));
      }
    }
  }
  
  public static void sendPlayerToSpiritWorld(EntityPlayer player, double nightmareChance) {
    if ((player != null) && (!field_70170_p.field_72995_K)) {
      NBTTagCompound nbtPlayer = Infusion.getNBT(player);
      if (!nbtPlayer.func_74764_b("WITCSpiritWorld")) {
        nbtPlayer.func_74782_a("WITCSpiritWorld", new NBTTagCompound());
      }
      NBTTagCompound nbtSpirit = nbtPlayer.func_74775_l("WITCSpiritWorld");
      

      Coord posBody = new Coord(player);
      posBody.setNBT(nbtSpirit, "OverworldBody");
      
      int fireFound = 0;
      int heartsFound = 0;
      int spiritPoolFound = 0;
      int cottonFound = 0;
      boolean nightmareCatcherFound = false;
      
      double modifiedNightmareChance = nightmareChance;
      if ((nightmareChance > 0.0D) && (nightmareChance < 1.0D))
      {
        int R = 8;
        int posX = MathHelper.func_76128_c(field_70165_t);
        int posY = MathHelper.func_76128_c(field_70163_u);
        int posZ = MathHelper.func_76128_c(field_70161_v);
        for (int x = posX - 8; x <= posX + 8; x++) {
          for (int z = posZ - 8; z <= posZ + 8; z++) {
            for (int y = posY - 8; y <= posY + 8; y++) {
              Block block = field_70170_p.func_147439_a(x, y, z);
              if ((!nightmareCatcherFound) && (block == BlocksDREAM_CATCHER) && (com.emoniph.witchery.blocks.BlockDreamCatcher.causesNightmares(field_70170_p, x, y, z)))
              {
                modifiedNightmareChance -= 0.5D;
                nightmareCatcherFound = true;
              }
              
              if ((spiritPoolFound < 3) && (block == BlocksFLOWING_SPIRIT) && (field_70170_p.func_72805_g(x, y, z) == 0)) {
                spiritPoolFound++;
                modifiedNightmareChance -= 0.1D;
              }
              
              if ((cottonFound < 2) && (block == BlocksWISPY_COTTON)) {
                cottonFound++;
                modifiedNightmareChance -= 0.1D;
              }
              

              if ((heartsFound < 2) && (block == BlocksDEMON_HEART)) {
                heartsFound++;
                modifiedNightmareChance += 0.35D;
              }
              if ((fireFound < 3) && (block == Blocks.field_150480_ab)) {
                fireFound++;
                modifiedNightmareChance += 0.1D;
              }
            }
          }
        }
        modifiedNightmareChance = nightmareCatcherFound ? Math.min(Math.max(modifiedNightmareChance, 0.0D), 1.0D) : nightmareChance;
      }
      

      boolean nightmare = (modifiedNightmareChance != 0.0D) && ((modifiedNightmareChance == 1.0D) || (field_70170_p.field_73012_v.nextDouble() < modifiedNightmareChance));
      boolean demonic = (nightmare) && (nightmareCatcherFound) && (spiritPoolFound > 0) && (heartsFound > 0) && (field_70170_p.field_73012_v.nextDouble() < heartsFound * 0.35D + fireFound * 0.1D);
      setPlayerHasNightmare(nbtPlayer, nightmare, demonic);
      setPlayerIsSpiritWalking(nbtPlayer, true);
      

      EntityCorpse corpse = new EntityCorpse(field_70170_p);
      corpse.func_70606_j(player.func_110143_aJ());
      corpse.func_94058_c(player.func_70005_c_());
      corpse.setOwner(player.func_70005_c_());
      corpse.func_70012_b(0.5D + MathHelper.func_76128_c(field_70165_t), field_70163_u, 0.5D + MathHelper.func_76128_c(field_70161_v), 0.0F, 0.0F);
      field_70170_p.func_72838_d(corpse);
      

      int boneNeedles = field_71071_by.func_146027_a(ItemsGENERIC, ItemsGENERIC.itemIcyNeedle.damageValue);
      int mutandis = field_71071_by.func_146027_a(ItemsGENERIC, ItemsGENERIC.itemMutandis.damageValue);
      

      dropBetterBackpacks(player);
      

      NBTTagList nbtOverworldInventory = new NBTTagList();
      field_71071_by.func_70442_a(nbtOverworldInventory);
      nbtSpirit.func_74782_a("OverworldInventory", nbtOverworldInventory);
      

      if (nbtSpirit.func_74764_b("SpiritInventory")) {
        NBTTagList nbtSpiritInventory = nbtSpirit.func_150295_c("SpiritInventory", 10);
        field_71071_by.func_70443_b(nbtSpiritInventory);
        

        nbtSpirit.func_82580_o("SpiritInventory");
      } else {
        field_71071_by.func_146027_a(null, -1);
      }
      

      addItemToInventory(player, ItemsGENERIC.itemIcyNeedle.createStack(), boneNeedles);
      addItemToInventory(player, ItemsGENERIC.itemMutandis.createStack(), mutandis);
      

      nbtSpirit.func_74776_a("OverworldHealth", Math.max(player.func_110143_aJ(), 1.0F));
      

      if (nbtSpirit.func_74764_b("SpiritHealth")) {
        float health = Math.max(nbtSpirit.func_74760_g("SpiritHealth"), 10.0F);
        player.func_70606_j(health);
        

        nbtSpirit.func_82580_o("SpiritHealth");
      }
      

      NBTTagCompound nbtOverworldFood = new NBTTagCompound();
      player.func_71024_bL().func_75117_b(nbtOverworldFood);
      nbtSpirit.func_74782_a("OverworldHunger", nbtOverworldFood);
      

      if (nbtSpirit.func_74764_b("SpiritHunger")) {
        NBTTagCompound nbtSpiritFood = nbtSpirit.func_74775_l("SpiritHunger");
        player.func_71024_bL().func_75112_a(nbtSpiritFood);
        player.func_71024_bL().func_75122_a(16, 0.8F);
        

        nbtSpirit.func_82580_o("SpiritHunger");
      }
      

      changeDimension(player, instancedimensionDreamID);
      findTopAndSetPosition(field_70170_p, player);
      
      Witchery.packetPipeline.sendToAll(new PacketPlayerStyle(player));
      Witchery.packetPipeline.sendTo(new PacketPushTarget(0.0D, 0.1D, 0.0D), player);
    }
  }
  
  private static void dropBetterBackpacks(EntityPlayer player) {
    try {
      Class classItemBackpack = Class.forName("net.mcft.copy.betterstorage.item.ItemBackpack");
      Method[] methods = classItemBackpack.getDeclaredMethods();
      if (Config.instance().isDebugging()) {
        for (Method method : methods) {
          Log.instance().debug(method.toString());
        }
      }
      
      Method methodGetPackpack = classItemBackpack.getMethod("getBackpack", new Class[] { EntityLivingBase.class });
      if (methodGetPackpack == null) {
        Log.instance().debug("No getBackpack method found");
      } else {
        Log.instance().debug("using method: " + methodGetPackpack.toString());
      }
      ItemStack stackBackpack = (ItemStack)methodGetPackpack.invoke(null, new Object[] { player });
      if (stackBackpack == null) {
        Log.instance().debug("No backpack stack found");
      } else {
        Log.instance().debug("got backpack stack: " + stackBackpack.toString());
      }
      
      Method methodPlaceBackpack = classItemBackpack.getDeclaredMethod("placeBackpack", new Class[] { EntityLivingBase.class, EntityPlayer.class, ItemStack.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, ForgeDirection.class, Boolean.TYPE, Boolean.TYPE });
      if (methodPlaceBackpack == null) {
        Log.instance().debug("No placebackpack method found");
      } else {
        Log.instance().debug("using method: " + methodPlaceBackpack.toString());
      }
      
      World w = field_70170_p;
      int x = MathHelper.func_76128_c(field_70165_t);
      int y = MathHelper.func_76128_c(field_70163_u);
      int z = MathHelper.func_76128_c(field_70161_v);
      boolean found = true;
      if (isReplaceable(w, x + 1, y, z)) {
        x++;
      } else if (isReplaceable(w, x - 1, y, z)) {
        x--;
      } else if (isReplaceable(w, x, y, z + 1)) {
        z++;
      } else if (isReplaceable(w, x - 1, y, z - 1)) {
        z--;
      } else if (isReplaceable(w, x + 1, y, z + 1)) {
        x++;
        z++;
      } else if (isReplaceable(w, x - 1, y, z + 1)) {
        x--;
        z++;
      } else if (isReplaceable(w, x + 1, y, z - 1)) {
        x++;
        z--;
      } else if (isReplaceable(w, x - 1, y, z - 1)) {
        x--;
        z--;
      } else {
        found = false;
      }
      if (found) {
        if (!w.func_147439_a(x, y - 1, z).func_149662_c()) {
          w.func_147449_b(x, y - 1, z, Blocks.field_150348_b);
        }
      } else {
        found = true;
        y++;
        if (isReplaceable(w, x + 1, y, z)) {
          x++;
        } else if (isReplaceable(w, x - 1, y, z)) {
          x--;
        } else if (isReplaceable(w, x, y, z + 1)) {
          z++;
        } else if (isReplaceable(w, x - 1, y, z - 1)) {
          z--;
        } else if (isReplaceable(w, x + 1, y, z + 1)) {
          x++;
          z++;
        } else if (isReplaceable(w, x - 1, y, z + 1)) {
          x--;
          z++;
        } else if (isReplaceable(w, x + 1, y, z - 1)) {
          x++;
          z--;
        } else if (isReplaceable(w, x - 1, y, z - 1)) {
          x--;
          z--;
        } else {
          found = false;
        }
        if (!found) {
          x++;
          y++;
          w.func_147468_f(x, y, z);
          if (!w.func_147439_a(x, y - 1, z).func_149662_c()) {
            w.func_147449_b(x, y - 1, z, Blocks.field_150348_b);
          }
        }
      }
      
      Boolean result = (Boolean)methodPlaceBackpack.invoke(null, new Object[] { player, player, stackBackpack, Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(z), Integer.valueOf(1), ForgeDirection.NORTH, Boolean.valueOf(false), Boolean.valueOf(false) });
      if (result.equals(Boolean.FALSE)) {
        Log.instance().debug("Backpack could not be placed");
      } else {
        Method methodSetBackpack = classItemBackpack.getDeclaredMethod("setBackpack", new Class[] { EntityLivingBase.class, ItemStack.class, [Lnet.minecraft.item.ItemStack.class });
        if (methodSetBackpack == null) {
          Log.instance().debug("No setBackpack method found");
        } else {
          Log.instance().debug("using method: " + methodPlaceBackpack.toString());
        }
        methodSetBackpack.invoke(null, new Object[] { player, null, null });
      }
    } catch (ClassNotFoundException ex) {
      Log.instance().debug("No class found for ItemBackpack");
    } catch (NoSuchMethodException ex) {
      Log.instance().debug("No onPlaceBackpack method found: " + ex.toString());
    } catch (InvocationTargetException ex) {
      Log.instance().debug("No onPlaceBackpack target found");
    } catch (IllegalAccessException ex) {
      Log.instance().debug("No onPlaceBackpack method access allowed");
    } catch (IndexOutOfBoundsException ex) {
      Log.instance().debug("No onPlaceBackpack method index");
    } catch (Throwable ex) {
      Log.instance().debug("Unexpected onPlaceBackpack error: " + ex.toString());
    }
  }
  
  private static boolean isReplaceable(World world, int x, int y, int z) {
    Material m = world.func_147439_a(x, y, z).func_149688_o();
    if (m == null) {
      return false;
    }
    return m.func_76222_j();
  }
  
  public static void changeDimension(EntityPlayer player, int dimension) {
    dismountEntity(player);
    ItemGeneral.travelToDimension(player, dimension);
  }
  
  private static void dismountEntity(EntityPlayer player) {
    if (player.func_70115_ae()) {
      player.func_70078_a(null);
    }
  }
  
  public static void findTopAndSetPosition(World world, EntityPlayer player)
  {
    findTopAndSetPosition(world, player, field_70165_t, field_70163_u, field_70161_v);
  }
  
  private static void findTopAndSetPosition(World world, EntityPlayer player, double posX, double posY, double posZ) {
    int x = MathHelper.func_76128_c(posX);
    int y = MathHelper.func_76128_c(posY);
    int z = MathHelper.func_76128_c(posZ);
    if (!isValidSpawnPoint(world, x, y, z)) {
      for (int i = 1; i <= 256; i++) {
        int yPlus = y + i;
        int yMinus = y - i;
        if ((yPlus < 256) && (isValidSpawnPoint(world, x, yPlus, z))) {
          y = yPlus;


        }
        else if ((yMinus > 2) && (isValidSpawnPoint(world, x, yMinus, z))) {
          y = yMinus;
        }
        else
        {
          if ((yMinus <= 2) && (yPlus >= 255))
            break;
        }
      }
    }
    player.func_70634_a(0.5D + x, 0.1D + y, 0.5D + z);
  }
  
  private static boolean isValidSpawnPoint(World world, int x, int y, int z) {
    Material materialBelow = world.func_147439_a(x, y - 1, z).func_149688_o();
    return (!world.func_147437_c(x, y - 1, z)) && (materialBelow != Material.field_151587_i) && (world.func_147437_c(x, y, z)) && (world.func_147437_c(x, y + 1, z));
  }
  
  public static void returnPlayerToOverworld(EntityPlayer player) {
    if ((player != null) && (!field_70170_p.field_72995_K)) {
      if (field_71093_bK != instancedimensionDreamID) {
        Log.instance().warning("Player " + player.getDisplayName() + " is in incorrect dimension when returning frmo spirit world, dimension=" + field_71093_bK);
      }
      NBTTagCompound nbtPlayer = Infusion.getNBT(player);
      if (!nbtPlayer.func_74764_b("WITCSpiritWorld")) {
        nbtPlayer.func_74782_a("WITCSpiritWorld", new NBTTagCompound());
      }
      NBTTagCompound nbtSpirit = nbtPlayer.func_74775_l("WITCSpiritWorld");
      

      boolean isSpiritWorld = field_71093_bK == instancedimensionDreamID;
      int cottonRemoved = isSpiritWorld ? field_71071_by.func_146027_a(net.minecraft.item.Item.func_150898_a(BlocksWISPY_COTTON), 0) : 0;
      int disturbedCottonRemoved = isSpiritWorld ? field_71071_by.func_146027_a(ItemsGENERIC, ItemsGENERIC.itemDisturbedCotton.damageValue) : 0;
      int hunger = isSpiritWorld ? field_71071_by.func_146027_a(ItemsGENERIC, ItemsGENERIC.itemMellifluousHunger.damageValue) : 0;
      int spirit = isSpiritWorld ? field_71071_by.func_146027_a(ItemsGENERIC, ItemsGENERIC.itemBrewOfFlowingSpirit.damageValue) : 0;
      int subduedSpirits = field_71071_by.func_146027_a(ItemsGENERIC, ItemsGENERIC.itemSubduedSpirit.damageValue);
      int boneNeedles = field_71071_by.func_146027_a(ItemsGENERIC, ItemsGENERIC.itemIcyNeedle.damageValue);
      


      dropBetterBackpacks(player);
      
      if (field_71093_bK == instancedimensionDreamID)
      {
        NBTTagList nbtSpiritInventory = new NBTTagList();
        field_71071_by.func_70442_a(nbtSpiritInventory);
        nbtSpirit.func_74782_a("SpiritInventory", nbtSpiritInventory);
      }
      

      if (nbtSpirit.func_74764_b("OverworldInventory")) {
        NBTTagList nbtOverworldInventory = nbtSpirit.func_150295_c("OverworldInventory", 10);
        field_71071_by.func_70443_b(nbtOverworldInventory);
        

        nbtSpirit.func_82580_o("OverworldInventory");
      } else {
        field_71071_by.func_146027_a(null, -1);
      }
      

      addItemToInventory(player, new ItemStack(BlocksWISPY_COTTON, 1, 0), cottonRemoved);
      addItemToInventory(player, ItemsGENERIC.itemDisturbedCotton.createStack(), disturbedCottonRemoved);
      addItemToInventory(player, ItemsGENERIC.itemMellifluousHunger.createStack(), hunger);
      addItemToInventory(player, ItemsGENERIC.itemIcyNeedle.createStack(), boneNeedles);
      addItemToInventory(player, ItemsGENERIC.itemBrewOfFlowingSpirit.createStack(), spirit);
      addItemToInventory(player, ItemsGENERIC.itemSubduedSpirit.createStack(), subduedSpirits);
      

      nbtSpirit.func_74776_a("SpiritHealth", Math.max(player.func_110143_aJ(), 10.0F));
      

      if (nbtSpirit.func_74764_b("OverworldHealth")) {
        float health = nbtSpirit.func_74760_g("OverworldHealth");
        player.func_70606_j(health);
        

        nbtSpirit.func_82580_o("OverworldHealth");
      }
      

      NBTTagCompound nbtSpiritFood = new NBTTagCompound();
      player.func_71024_bL().func_75117_b(nbtSpiritFood);
      nbtSpirit.func_74782_a("SpiritHunger", nbtSpiritFood);
      

      if (nbtSpirit.func_74764_b("OverworldHunger")) {
        NBTTagCompound nbtOverworldFood = nbtSpirit.func_74775_l("OverworldHunger");
        player.func_71024_bL().func_75112_a(nbtOverworldFood);
        

        nbtSpirit.func_82580_o("OverworldHunger");
      }
      

      setPlayerHasNightmare(nbtPlayer, false, false);
      setPlayerIsGhost(nbtPlayer, false);
      setPlayerIsSpiritWalking(nbtPlayer, false);
      
      player.func_70066_B();
      
      Coord posBody = Coord.createFrom(nbtSpirit, "OverworldBody");
      

      if (field_71093_bK != 0) {
        if (posBody != null) {
          dismountEntity(player);
          player.func_70634_a(x, y, z);
        }
        
        changeDimension(player, 0);
      }
      
      World world = field_70170_p;
      if (posBody != null) {
        findTopAndSetPosition(field_70170_p, player, x, y, z);
        nbtSpirit.func_82580_o("OverworldBody");
      } else {
        findTopAndSetPosition(field_70170_p, player);
      }
      

      for (Object obj : field_70170_p.field_72996_f) {
        if ((obj instanceof EntityCorpse)) {
          EntityCorpse corpse = (EntityCorpse)obj;
          String owner = corpse.getOwnerName();
          if ((owner != null) && (owner.equalsIgnoreCase(player.func_70005_c_()))) {
            field_70170_p.func_72900_e(corpse);
          }
        }
      }
      
      Witchery.packetPipeline.sendToAll(new PacketPlayerStyle(player));
      Witchery.packetPipeline.sendTo(new PacketPushTarget(0.0D, 0.1D, 0.0D), player);
    }
  }
  
  public static void manifestPlayerInOverworldAsGhost(EntityPlayer player) {
    if ((player != null) && (!field_70170_p.field_72995_K)) {
      NBTTagCompound nbtPlayer = Infusion.getNBT(player);
      if (!nbtPlayer.func_74764_b("WITCSpiritWorld")) {
        nbtPlayer.func_74782_a("WITCSpiritWorld", new NBTTagCompound());
      }
      NBTTagCompound nbtSpirit = nbtPlayer.func_74775_l("WITCSpiritWorld");
      

      int boneNeedles = field_71071_by.func_146027_a(ItemsGENERIC, ItemsGENERIC.itemIcyNeedle.damageValue);
      

      dropBetterBackpacks(player);
      

      NBTTagList nbtSpiritInventory = new NBTTagList();
      field_71071_by.func_70442_a(nbtSpiritInventory);
      nbtSpirit.func_74782_a("SpiritInventory", nbtSpiritInventory);
      

      field_71071_by.func_146027_a(null, -1);
      

      addItemToInventory(player, ItemsGENERIC.itemIcyNeedle.createStack(), boneNeedles);
      

      nbtSpirit.func_74776_a("SpiritHealth", Math.max(player.func_110143_aJ(), 1.0F));
      

      setPlayerIsGhost(nbtPlayer, true);
      

      changeDimension(player, 0);
      findTopAndSetPosition(field_70170_p, player);
      
      Witchery.packetPipeline.sendToAll(new PacketPlayerStyle(player));
    }
  }
  
  public static void returnGhostPlayerToSpiritWorld(EntityPlayer player) {
    if ((player != null) && (!field_70170_p.field_72995_K)) {
      NBTTagCompound nbtPlayer = Infusion.getNBT(player);
      if (!nbtPlayer.func_74764_b("WITCSpiritWorld")) {
        nbtPlayer.func_74782_a("WITCSpiritWorld", new NBTTagCompound());
      }
      NBTTagCompound nbtSpirit = nbtPlayer.func_74775_l("WITCSpiritWorld");
      

      int boneNeedles = field_71071_by.func_146027_a(ItemsGENERIC, ItemsGENERIC.itemIcyNeedle.damageValue);
      ArrayList<ItemStack> fetishes = getBoundFetishes(field_71071_by);
      

      field_71071_by.func_70436_m();
      

      dropBetterBackpacks(player);
      

      if (nbtSpirit.func_74764_b("SpiritInventory")) {
        NBTTagList nbtSpiritInventory = nbtSpirit.func_150295_c("SpiritInventory", 10);
        field_71071_by.func_70443_b(nbtSpiritInventory);
        

        nbtSpirit.func_82580_o("SpiritInventory");
      }
      

      addItemToInventory(player, ItemsGENERIC.itemIcyNeedle.createStack(), boneNeedles);
      addItemToInventory(player, fetishes);
      

      setPlayerIsGhost(nbtPlayer, false);
      

      changeDimension(player, instancedimensionDreamID);
      
      findTopAndSetPosition(field_70170_p, player);
      
      Witchery.packetPipeline.sendToAll(new PacketPlayerStyle(player));
    }
  }
  
  private static ArrayList<ItemStack> getBoundFetishes(InventoryPlayer inventory) {
    ArrayList<ItemStack> stacks = new ArrayList();
    for (int i = 0; i < inventory.func_70302_i_(); i++) {
      ItemStack stack = inventory.func_70301_a(i);
      if ((stack != null) && ((stack.func_77973_b() instanceof BlockFetish.ClassItemBlock)) && 
        (com.emoniph.witchery.infusion.infusions.spirit.InfusedSpiritEffect.getEffectID(stack) > 0)) {
        stacks.add(stack);
      }
    }
    
    return stacks;
  }
  
  public static void updatePlayerEffects(World world, EntityPlayer player, NBTTagCompound nbtPlayer, long time, long counter) {
    if (!field_72995_K) {
      boolean done = false;
      if (counter % 20L == 0L) {
        boolean mustAwaken = getPlayerMustAwaken(nbtPlayer);
        if (mustAwaken) {
          setPlayerMustAwaken(nbtPlayer, false);
          if ((field_71093_bK != instancedimensionDreamID) && (getPlayerIsSpiritWalking(player)) && (!getPlayerIsGhost(player)))
          {
            returnPlayerToOverworld(player);
          } else if (field_71093_bK == instancedimensionDreamID) {
            returnPlayerToOverworld(player);
          }
        }
      }
      if ((!done) && (counter % 100L == 0L)) {
        int nightmareLevel = getPlayerHasNightmare(nbtPlayer);
        if ((field_71093_bK == instancedimensionDreamID) && (nightmareLevel > 0)) {
          double R = 18.0D;
          double H = 18.0D;
          AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(field_70165_t - 18.0D, field_70163_u - 18.0D, field_70161_v - 18.0D, field_70165_t + 18.0D, field_70163_u + 18.0D, field_70161_v + 18.0D);
          

          if (nightmareLevel > 1) {
            double chance = field_73012_v.nextDouble();
            if (chance < 0.5D) {
              EntitySmallFireball fireball = new EntitySmallFireball(world, field_70165_t - 2.0D + field_73012_v.nextInt(5), field_70163_u + 15.0D, field_70161_v - 2.0D + field_73012_v.nextInt(5), 0.0D, -0.2D, 0.0D);
              world.func_72838_d(fireball);
            } else if (chance < 0.65D) {
              EntityLargeFireball fireball = new EntityLargeFireball(world);
              
              double par2 = field_70165_t - 2.0D + field_73012_v.nextInt(5);
              double par4 = field_70163_u + 15.0D;
              double par6 = field_70161_v - 2.0D + field_73012_v.nextInt(5);
              double par8 = 0.0D;
              double par10 = -0.2D;
              double par12 = 0.0D;
              fireball.func_70012_b(par2, par4, par6, field_70177_z, field_70125_A);
              fireball.func_70107_b(par2, par4, par6);
              double d6 = MathHelper.func_76133_a(par8 * par8 + par10 * par10 + par12 * par12);
              field_70232_b = (par8 / d6 * 0.1D);
              field_70233_c = (par10 / d6 * 0.1D);
              field_70230_d = (par12 / d6 * 0.1D);
              
              world.func_72838_d(fireball);
            } else if (chance < 0.75D) {
              List entities = world.func_72872_a(net.minecraft.entity.monster.EntityMob.class, bounds);
              if ((entities.size() < 10) && (!containsDemons(entities, 2))) {
                EntityDemon blaze = new EntityDemon(world);
                Infusion.spawnCreature(world, EntityDemon.class, MathHelper.func_76128_c(field_70165_t), MathHelper.func_76128_c(field_70163_u), MathHelper.func_76128_c(field_70161_v), player, 4, 8, com.emoniph.witchery.util.ParticleEffect.SMOKE, com.emoniph.witchery.util.SoundEffect.MOB_WITHER_DEATH);
              }
            }
          }
          


          List entities = world.func_72872_a(EntityNightmare.class, bounds);
          for (Object obj : entities) {
            EntityNightmare nightmare = (EntityNightmare)obj;
            if (nightmare.getVictimName().equalsIgnoreCase(player.func_70005_c_())) {
              return;
            }
          }
          

          long currentTime = MinecraftServer.func_130071_aq();
          long lastKillTime = getPlayerLastNightmareKill(nbtPlayer);
          if (lastKillTime < currentTime - 30000L) {
            Infusion.spawnCreature(world, EntityNightmare.class, MathHelper.func_76128_c(field_70165_t), MathHelper.func_76128_c(field_70163_u), MathHelper.func_76128_c(field_70161_v), player, 2, 6);
          }
        }
        else if ((field_71093_bK != instancedimensionDreamID) && (getPlayerIsGhost(nbtPlayer))) {
          int timeRemaining = 0;
          boolean skipNext = getPlayerSkipNextManifestTick(nbtPlayer);
          if (nbtPlayer.func_74764_b("WITCManifestDuration")) {
            timeRemaining = nbtPlayer.func_74762_e("WITCManifestDuration");
            timeRemaining = Math.max(0, timeRemaining - 5);
            if (((timeRemaining >= 60) && (timeRemaining <= 64)) || ((timeRemaining >= 30) && (timeRemaining <= 34)) || ((timeRemaining >= 15) && (timeRemaining <= 19) && 
              (!skipNext))) {
              com.emoniph.witchery.util.ChatUtil.sendTranslated(EnumChatFormatting.LIGHT_PURPLE, player, "witchery.rite.manifestation.countdown", new Object[] { Integer.valueOf(timeRemaining).toString() });
            }
          }
          

          if (timeRemaining == 0) {
            if (nbtPlayer.func_74764_b("WITCManifestDuration")) {
              nbtPlayer.func_82580_o("WITCManifestDuration");
            }
            returnGhostPlayerToSpiritWorld(player);
          }
          else if (!skipNext) {
            nbtPlayer.func_74768_a("WITCManifestDuration", timeRemaining);
          } else {
            setPlayerSkipNextManifestationReduction(nbtPlayer, false);
          }
        }
      }
    }
  }
  


  public static void setPlayerSkipNextManifestationReduction(EntityPlayer player)
  {
    NBTTagCompound nbtPlayer = Infusion.getNBT(player);
    setPlayerSkipNextManifestationReduction(nbtPlayer, true);
  }
  
  public static void setPlayerSkipNextManifestationReduction(NBTTagCompound nbtPlayer, boolean skip) {
    nbtPlayer.func_74757_a("WITCManifestSkipTick", skip);
  }
  
  public static boolean getPlayerSkipNextManifestTick(NBTTagCompound nbtPlayer) {
    return nbtPlayer.func_74767_n("WITCManifestSkipTick");
  }
  
  private static boolean containsDemons(List entities, int max) {
    int count = 0;
    for (Object obj : entities) {
      if ((obj instanceof EntityDemon)) {
        count++; if (count >= max) {
          return true;
        }
      }
    }
    return false;
  }
  
  public static void setPlayerIsGhost(NBTTagCompound nbtPlayer, boolean ghost) {
    nbtPlayer.func_74757_a("WITCManifested", ghost);
  }
  
  public static boolean getPlayerIsGhost(EntityPlayer player) {
    NBTTagCompound nbtPlayer = Infusion.getNBT(player);
    return getPlayerIsGhost(nbtPlayer);
  }
  
  public static boolean getPlayerIsGhost(NBTTagCompound nbtPlayer) {
    return nbtPlayer.func_74767_n("WITCManifested");
  }
  
  public static void setPlayerMustAwaken(EntityPlayer player, boolean awaken) {
    NBTTagCompound nbtPlayer = Infusion.getNBT(player);
    setPlayerMustAwaken(nbtPlayer, awaken);
  }
  
  public static void setPlayerMustAwaken(NBTTagCompound nbtPlayer, boolean ghost) {
    nbtPlayer.func_74757_a("WITCForceAwaken", ghost);
  }
  
  public static boolean getPlayerMustAwaken(EntityPlayer player) {
    NBTTagCompound nbtPlayer = Infusion.getNBT(player);
    return getPlayerMustAwaken(nbtPlayer);
  }
  
  public static boolean getPlayerMustAwaken(NBTTagCompound nbtPlayer) {
    return nbtPlayer.func_74767_n("WITCForceAwaken");
  }
  
  public static boolean canPlayerManifest(EntityPlayer player) {
    NBTTagCompound nbtPlayer = Infusion.getNBT(player);
    int timeRemaining = 0;
    if ((nbtPlayer != null) && (nbtPlayer.func_74764_b("WITCManifestDuration"))) {
      timeRemaining = nbtPlayer.func_74762_e("WITCManifestDuration");
    }
    return timeRemaining >= 5;
  }
}
