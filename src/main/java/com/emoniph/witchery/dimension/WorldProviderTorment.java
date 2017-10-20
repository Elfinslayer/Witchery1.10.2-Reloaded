package com.emoniph.witchery.dimension;

import com.emoniph.witchery.entity.EntityLordOfTorment;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.Chunk;

public class WorldProviderTorment extends WorldProvider
{
  public static final String SPIRIT_WORLD_TORMENT_PLAYER_KEY = "WITCForceTorment";
  public static final String SPIRIT_WORLD_TORMENT_LEVEL_KEY = "WITCForceTormentLevel";
  public static final int TORMENT_NONE = 0;
  public static final int TORMENT_BEGIN = 1;
  public static final int TORMENT_BEGIN_WITH_BOSS = 2;
  public static final int TORMENT_END = 3;
  
  public WorldProviderTorment() {}
  
  public String func_80007_l()
  {
    return "Torment";
  }
  
  public net.minecraft.world.chunk.IChunkProvider func_76555_c()
  {
    return new WorldChunkManagerTorment(field_76579_a);
  }
  
  public boolean func_76567_e()
  {
    return false;
  }
  
  public boolean func_76569_d()
  {
    return false;
  }
  
  public boolean canDoLightning(Chunk chunk)
  {
    return false;
  }
  
  public boolean isBlockHighHumidity(int x, int y, int z)
  {
    return false;
  }
  
  public boolean isDaytime()
  {
    return false;
  }
  
  public ChunkCoordinates getSpawnPoint()
  {
    return new ChunkCoordinates(8, 14, 8);
  }
  
  @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
  public boolean func_76561_g()
  {
    return true;
  }
  
  public float func_76563_a(long par1, float par3)
  {
    return 1.0F;
  }
  



  public Vec3 getSkyColor(Entity cameraEntity, float partialTicks)
  {
    float f1 = field_76579_a.func_72826_c(partialTicks);
    float f2 = MathHelper.func_76134_b(f1 * 3.1415927F * 2.0F) * 2.0F + 0.5F;
    
    if (f2 < 0.0F)
    {
      f2 = 0.0F;
    }
    
    if (f2 > 1.0F)
    {
      f2 = 1.0F;
    }
    


    int i = MathHelper.func_76128_c(field_70165_t);
    int j = MathHelper.func_76128_c(field_70163_u);
    int k = MathHelper.func_76128_c(field_70161_v);
    








    GameSettings settings = func_71410_xfield_71474_y;
    int[] ranges = net.minecraftforge.common.ForgeModContainer.blendRanges;
    int distance = 0;
    if ((field_74347_j) && (field_151451_c >= 0) && (field_151451_c < ranges.length))
    {
      distance = ranges[field_151451_c];
    }
    
    int r = 0;
    int g = 0;
    int b = 0;
    
    int divider = 0;
    for (int x = -distance; x <= distance; x++)
    {
      for (int z = -distance; z <= distance; z++)
      {
        net.minecraft.world.biome.BiomeGenBase biome = field_76579_a.func_72807_a(i + x, k + z);
        
        int colour = 16711680;
        r += ((colour & 0xFF0000) >> 16);
        g += ((colour & 0xFF00) >> 8);
        b += (colour & 0xFF);
        divider++;
      }
    }
    
    int multiplier = (r / divider & 0xFF) << 16 | (g / divider & 0xFF) << 8 | b / divider & 0xFF;
    





    int l = multiplier;
    
    float f4 = (l >> 16 & 0xFF) / 255.0F;
    float f5 = (l >> 8 & 0xFF) / 255.0F;
    float f6 = (l & 0xFF) / 255.0F;
    



    return Vec3.func_72443_a(f4, f5, f6);
  }
  








  public static void setPlayerMustTorment(EntityPlayer player, int torment, int presetLevel)
  {
    NBTTagCompound nbtPlayer = Infusion.getNBT(player);
    setPlayerMustTorment(nbtPlayer, torment, presetLevel);
  }
  
  public static void setPlayerMustTorment(NBTTagCompound nbtPlayer, int torment, int presetLevel)
  {
    nbtPlayer.func_74768_a("WITCForceTorment", torment);
    if (presetLevel > -1) {
      nbtPlayer.func_74768_a("WITCForceTormentLevel", presetLevel);
    } else if ((presetLevel == -2) && (nbtPlayer.func_74764_b("WITCForceTormentLevel"))) {
      nbtPlayer.func_82580_o("WITCForceTormentLevel");
    }
  }
  
  public static int getRandomTormentLevel(World world) {
    return field_73012_v.nextInt(6);
  }
  
  public static int getPlayerMustTorment(EntityPlayer player) {
    NBTTagCompound nbtPlayer = Infusion.getNBT(player);
    return getPlayerMustTorment(nbtPlayer);
  }
  
  public static int getPlayerMustTorment(NBTTagCompound nbtPlayer) {
    return nbtPlayer.func_74762_e("WITCForceTorment");
  }
  
  public static void updatePlayerEffects(World world, EntityPlayer player, NBTTagCompound nbtPlayer, long time, long counter) {
    if (!field_72995_K) {
      boolean done = false;
      if (counter % 20L == 0L) {
        int mustTorment = getPlayerMustTorment(nbtPlayer);
        if ((mustTorment == 1) || (mustTorment == 2)) {
          int level = mustTorment == 2 ? nbtPlayer.func_74762_e("WITCForceTormentLevel") : getRandomTormentLevel(world);
          setPlayerMustTorment(nbtPlayer, 0, -1);
          if (player.func_70115_ae()) {
            player.func_70078_a(null);
          }
          
          ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, player, 1.0D, 2.0D, 16);
          
          int yPos = 12 + level * 15;
          player.func_70634_a(8.0D, yPos, 8.0D);
          ItemGeneral.travelToDimension(player, instancedimensionTormentID);
          player.func_70634_a(8.0D, yPos, 8.0D);
          ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, player, 1.0D, 2.0D, 16);
          World tormentWorld = com.emoniph.witchery.util.ServerUtil.getWorld(instancedimensionTormentID);
          int midX = 8;
          int midZ = 8;
          for (int x = midX - 1; x <= midX + 1; x++) {
            for (int z = midZ - 1; z <= midZ + 1; z++) {
              if (!tormentWorld.func_147437_c(x, yPos, z)) {
                tormentWorld.func_147468_f(x, yPos, z);
              }
              if (!tormentWorld.func_147437_c(x, yPos + 1, z)) {
                tormentWorld.func_147468_f(x, yPos + 1, z);
              }
            }
          }
          if (mustTorment == 2) {
            boolean found = false;
            if (field_70170_p.field_73011_w.field_76574_g == instancedimensionTormentID) {
              for (Object obj : field_70170_p.field_72996_f) {
                if ((obj instanceof EntityLordOfTorment)) {
                  EntityLordOfTorment lot = (EntityLordOfTorment)obj;
                  if ((field_70163_u >= yPos - 2) && (field_70163_u <= yPos + 6 - 2)) {
                    found = true;
                    break;
                  }
                }
              }
            }
            if (!found)
            {
              if (tormentWorld != null) {
                EntityLordOfTorment lot = new EntityLordOfTorment(tormentWorld);
                lot.func_70080_a(9.0D, yPos - 1, 36.0D, 0.0F, 0.0F);
                lot.func_110163_bv();
                lot.func_70606_j(lot.func_110138_aP() * 0.5F);
                tormentWorld.func_72838_d(lot);
              }
            }
          }
        }
        else if (mustTorment == 3) {
          setPlayerMustTorment(nbtPlayer, 0, -2);
          if (player.func_70115_ae()) {
            player.func_70078_a(null);
          }
          
          World overworld = func_71276_Cfield_71305_c[0];
          ChunkCoordinates coords = player.getBedLocation(0);
          int dimension = 0;
          if (coords == null) {
            coords = overworld.func_72861_E();
          }
          if (coords != null) {
            int mod = 0;
            int origY = field_71572_b;
            while ((!isSafeBlock(overworld, field_71574_a, field_71572_b, field_71573_c)) && (field_71572_b > 1) && (field_71572_b < 255)) {
              field_71572_b = (origY + mod);
              if (origY - mod > 1) {
                mod = -mod;
              }
              if (mod >= 0) {
                mod++;
              }
            }
            
            ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, player, 1.0D, 2.0D, 16);
            ItemGeneral.teleportToLocation(field_70170_p, field_71574_a, field_71572_b + 1, field_71573_c, dimension, player, true);
            ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, player, 1.0D, 2.0D, 16);
          }
        }
      }
    }
  }
  
  private static boolean isSafeBlock(World world, int posX, int posY, int posZ) {
    boolean base = BlockUtil.isSolid(world, posX, posY, posZ);
    boolean air1 = !BlockUtil.isSolid(world, posX, posY + 1, posZ);
    boolean air2 = !BlockUtil.isSolid(world, posX, posY + 2, posZ);
    boolean isSafe = (base) && (air1) && (air2);
    return isSafe;
  }
}
