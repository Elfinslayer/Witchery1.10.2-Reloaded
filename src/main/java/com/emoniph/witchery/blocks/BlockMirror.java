package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.common.IPowerSource;
import com.emoniph.witchery.common.PowerSources;
import com.emoniph.witchery.common.Shapeshift;
import com.emoniph.witchery.entity.EntityFollower;
import com.emoniph.witchery.entity.EntityMirrorFace;
import com.emoniph.witchery.entity.EntityReflection;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemSunGrenade;
import com.emoniph.witchery.item.ItemTaglockKit;
import com.emoniph.witchery.network.PacketPipeline;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TimeUtil;
import com.emoniph.witchery.util.TransformCreature;
import com.mojang.authlib.GameProfile;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;

public class BlockMirror extends BlockBaseContainer
{
  private final boolean unbreakable;
  
  public BlockMirror(boolean unbreakable)
  {
    super(Material.field_151592_s, TileEntityMirror.class);
    this.unbreakable = unbreakable;
    registerWithCreateTab = false;
    func_149715_a(0.7F);
    func_149649_H();
    func_149672_a(field_149778_k);
    if (unbreakable) {
      func_149722_s();
      func_149752_b(9999.0F);
    } else {
      func_149711_c(1.0F);
      func_149752_b(9999.0F);
    }
  }
  
  public static int getDirection(int meta) {
    return meta & 0x3;
  }
  
  public static boolean isBlockTopOfMirror(int meta) {
    return (meta & 0x4) != 0;
  }
  
  private static String sendMeHome = null;
  private static String iGiveUp = null;
  
  public boolean trySayMirrorMirrorSendMeHome(EntityPlayer player, String message) {
    if ((player == null) || (field_70170_p.field_72995_K) || (field_71093_bK != instancedimensionMirrorID)) {
      return false;
    }
    if (sendMeHome == null) {
      sendMeHome = Witchery.resource("witchery.rite.mirrormirrorsendmehome").toLowerCase().replace("'", "").replace(",", "").trim();
    }
    
    if (iGiveUp == null) {
      iGiveUp = Witchery.resource("witchery.rite.mirrormirrorigiveup").toLowerCase().replace("'", "").replace(",", "").trim();
    }
    
    ExtendedPlayer playerEx = ExtendedPlayer.get(player);
    
    if (message.toLowerCase().replace("'", "").replace(",", "").trim().startsWith(sendMeHome)) {
      if (playerEx.canEscapeMirrorWorld(1)) {
        Coord c = playerEx.getMirrorWorldEntryPoint();
        if (c != null) {
          playerEx.escapedMirrorWorld(1);
          player.func_70080_a(field_70165_t, field_70163_u, field_70161_v, 270.0F, field_70125_A);
          ItemGeneral.teleportToLocation(field_70170_p, 0.5D + x, 0.01D + y, 0.5D + z, field_71093_bK, player, true, ParticleEffect.SPLASH, SoundEffect.RANDOM_SPLASH);
          
          return true;
        }
      } else {
        ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.rite.mirrormirror.escapecooldown", new Object[] { Long.valueOf(playerEx.getCooldownSecs(1)).toString() });
        

        return true;
      }
    } else if (message.toLowerCase().replace("'", "").replace(",", "").trim().startsWith(iGiveUp)) {
      if (playerEx.canEscapeMirrorWorld(2)) {
        ChunkCoordinates coords = player.getBedLocation(field_71093_bK);
        int dimension = field_71093_bK;
        World world = field_70170_p;
        if (coords == null) {
          coords = player.getBedLocation(0);
          dimension = 0;
          world = MinecraftServer.func_71276_C().func_71218_a(0);
          if (coords == null) {
            coords = world.func_72861_E();
            
            while ((world.func_147439_a(field_71574_a, field_71572_b, field_71573_c).func_149721_r()) && (field_71572_b < 255)) {
              field_71572_b += 1;
            }
          }
        }
        if (coords != null) {
          coords = net.minecraft.init.Blocks.field_150324_C.getBedSpawnPosition(world, field_71574_a, field_71572_b, field_71573_c, null);
          if (coords != null) {
            playerEx.escapedMirrorWorld(2);
            ItemGeneral.teleportToLocation(field_70170_p, field_71574_a, field_71572_b + 1, field_71573_c, dimension, player, true);
            
            return true;
          }
        }
      } else {
        ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.rite.mirrormirror.escapecooldown", new Object[] { Long.valueOf(playerEx.getCooldownSecs(2)).toString() });
        

        return true;
      }
    }
    return false;
  }
  
  public void func_149670_a(World world, int x, int y, int z, Entity entity)
  {
    if (!field_72995_K)
    {
      if ((field_70173_aa % 5 == 1) && (isTransportableEntity(entity))) {
        int i1 = world.func_72805_g(x, y, z);
        
        int hitZoneyShift = 0;
        if (!isBlockTopOfMirror(i1)) {
          y++;
          if (field_70131_O <= 1.0F) {
            hitZoneyShift = -1;
          }
          if (world.func_147439_a(x, y, z) != this) {
            return;
          }
        }
        
        AxisAlignedBB box = getServerSelectedBoundingBoxFromPool(world, x, y + hitZoneyShift, z);
        
        double f = field_70130_N * 0.5D;
        double f1 = field_70131_O;
        AxisAlignedBB entityBox = AxisAlignedBB.func_72330_a(field_70165_t - f, field_70163_u - field_70129_M + field_70139_V, field_70161_v - f, field_70165_t + f, field_70163_u - field_70129_M + field_70139_V + f1, field_70161_v + f);
        

        if (entityBox.func_72326_a(box))
        {
          TileEntityMirror tile = (TileEntityMirror)BlockUtil.getTileEntity(world, x, y, z, TileEntityMirror.class);
          if (tile != null)
          {
            int side = getDirection(world.func_72805_g(x, y, z));
            int facing = MathHelper.func_76128_c(field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3;
            
            int dx = 0;
            int dz = 0;
            float shift = 0.7F;
            float xShift = 0.0F;
            float zShift = 0.0F;
            
            int scale = 1;
            int requiredSide = 0;
            boolean isLiving = entity instanceof EntityLivingBase;
            
            if (side == 0) {
              dz = scale;
              zShift = -shift;
              requiredSide = 1;
              if ((!isLiving) || (facing == 0)) {}

            }
            else if (side == 1) {
              dz = -scale;
              zShift = shift;
              requiredSide = 0;
              if ((!isLiving) || (facing == 2)) {}

            }
            else if (side == 2) {
              dx = scale;
              xShift = -shift;
              requiredSide = 3;
              if ((!isLiving) || (facing == 3)) {}

            }
            else if (side == 3) {
              dx = -scale;
              requiredSide = 2;
              xShift = shift;
              if ((isLiving) && (facing != 1)) {
                return;
              }
            }
            
            boolean inMirrorWorld = field_71093_bK == instancedimensionMirrorID;
            
            if (!unbreakable)
            {
              if ((inMirrorWorld) || (demonKilled))
              {
                for (int i = 1; i < 32; i++) {
                  int nx = x + dx * i;
                  int ny = y;
                  int nz = z + dz * i;
                  Block block = world.func_147439_a(nx, ny, nz);
                  if ((block == this) && 
                    (getDirection(world.func_72805_g(nx, ny, nz)) == requiredSide)) {
                    ItemGeneral.teleportToLocation(world, 0.5D + nx - xShift, ny - 1 + 0.01D, 0.5D + nz - zShift, field_73011_w.field_76574_g, entity, true, ParticleEffect.SPLASH, SoundEffect.RANDOM_SPLASH);
                    


                    return;
                  }
                }
              }
              

              if (inMirrorWorld)
              {


                for (int i = 1; i < 10; i++) {
                  int nx = x + dx * i;
                  int ny = y;
                  int nz = z + dz * i;
                  if ((world.func_147437_c(nx, ny, nz)) && (world.func_147437_c(nx, ny - 1, nz))) {
                    boolean isPlayerEntryCell = false;
                    if ((entity instanceof EntityPlayer)) {
                      EntityPlayer player = (EntityPlayer)entity;
                      ExtendedPlayer playerEx = ExtendedPlayer.get(player);
                      isPlayerEntryCell = playerEx.isMirrorWorldEntryPoint(nx, ny, nz);
                    }
                    int cx = (nx >> 4 << 4) + 4;
                    int cy = (ny >> 4 << 4) + 8;
                    int cz = (nz >> 4 << 4) + 8;
                    boolean isEntryCell = world.func_147439_a(cx, cy, cz) == BlocksMIRROR_UNBREAKABLE;
                    if ((!isEntryCell) || (isPlayerEntryCell)) {
                      IPowerSource power = PowerSources.findClosestPowerSource(world, x, y, z);
                      
                      if ((power != null) && (power.consumePower(3000.0F))) {
                        ItemGeneral.teleportToLocation(world, 0.5D + nx - xShift, ny + 0.01D, 0.5D + nz - zShift, field_73011_w.field_76574_g, entity, true, ParticleEffect.SPLASH, SoundEffect.RANDOM_SPLASH);
                      }
                    }
                    


                    return;
                  }
                }
              } else if (demonKilled)
              {
                for (int dy = 2; dy < 16; dy++) {
                  int nx = x;
                  int ny = y + dy;
                  int nz = z;
                  Block block = world.func_147439_a(nx, ny, nz);
                  if (block == this) {
                    int meta = world.func_72805_g(nx, ny, nz);
                    if (getDirection(meta) == side) {
                      if (isBlockTopOfMirror(meta)) {
                        ny--;
                      }
                      
                      ItemGeneral.teleportToLocation(world, 0.5D + nx + xShift, ny + 0.01D, 0.5D + nz + zShift, field_73011_w.field_76574_g, entity, true, ParticleEffect.SPLASH, SoundEffect.RANDOM_SPLASH);
                      


                      if ((entity instanceof EntityPlayer)) {
                        EntityPlayer player = (EntityPlayer)entity;
                        double yaw = field_70177_z + 180.0F;
                        float rev = (float)yaw % 360.0F;
                        S08PacketPlayerPosLook packet = new S08PacketPlayerPosLook(field_70165_t, field_70163_u, field_70161_v, rev, field_70125_A, false);
                        

                        Witchery.packetPipeline.sendTo(packet, player);
                      }
                      

                      return;
                    }
                  }
                }
                

                for (int dy = 2; dy < 16; dy++) {
                  int nx = x;
                  int ny = y - dy;
                  int nz = z;
                  Block block = world.func_147439_a(nx, ny, nz);
                  if (block == this) {
                    int meta = world.func_72805_g(nx, ny, nz);
                    if (getDirection(meta) == side) {
                      if (isBlockTopOfMirror(meta)) {
                        ny--;
                      }
                      ItemGeneral.teleportToLocation(world, 0.5D + nx + xShift, ny + 0.01D, 0.5D + nz + zShift, field_73011_w.field_76574_g, entity, true, ParticleEffect.SPLASH, SoundEffect.RANDOM_SPLASH);
                      


                      if ((entity instanceof EntityPlayer)) {
                        EntityPlayer player = (EntityPlayer)entity;
                        double yaw = field_70177_z + 180.0F;
                        float rev = (float)yaw % 360.0F;
                        S08PacketPlayerPosLook packet = new S08PacketPlayerPosLook(field_70165_t, field_70163_u, field_70161_v, rev, field_70125_A, false);
                        

                        Witchery.packetPipeline.sendTo(packet, player);
                      }
                      

                      return;
                    }
                  }
                }
              }
            }
            


            if ((entity instanceof EntityPlayer)) {
              EntityPlayer player = (EntityPlayer)entity;
              ExtendedPlayer playerEx = ExtendedPlayer.get(player);
              if ((!inMirrorWorld) || (playerEx.isMirrorWorldEntryPoint(x, y, z)))
              {
                Coord dimCoords = tile.getDimCoords();
                if (dimCoords != null)
                {
                  float dimX = x + 0.5F;
                  float dimY = y + 0.01F;
                  float dimZ = z + 0.5F;
                  int targetDimension = !inMirrorWorld ? instancedimensionMirrorID : dim;
                  
                  World otherWorld = MinecraftServer.func_71276_C().func_71218_a(targetDimension);
                  
                  float face = 0.0F;
                  if (otherWorld != null) {
                    Block block = otherWorld.func_147439_a(x, y, z);
                    if ((block == BlocksMIRROR) || (block == BlocksMIRROR_UNBREAKABLE))
                    {
                      int mside = getDirection(otherWorld.func_72805_g(x, y, z));
                      

                      float distance = 1.0F;
                      if (mside == 0) {
                        face = 180.0F;
                        dimZ -= distance;
                      } else if (mside == 1) {
                        face = 0.0F;
                        dimZ += distance;
                      } else if (mside == 2) {
                        face = 90.0F;
                        dimX -= distance;
                      } else if (mside == 3) {
                        face = 270.0F;
                        dimX += distance;
                      }
                      
                      field_70177_z = face;
                      
                      TileEntityMirror otherTile = (TileEntityMirror)BlockUtil.getTileEntity(otherWorld, x, y, z, TileEntityMirror.class);
                      
                      if (otherTile != null) {
                        if (otherTile.onCooldown()) {
                          return;
                        }
                        otherTile.addCooldown(60);
                      }
                    }
                  }
                  
                  ParticleEffect.SPLASH.send(SoundEffect.RANDOM_SPLASH, entity, 0.5D, 2.0D, 16);
                  if (field_71093_bK != instancedimensionMirrorID)
                  {
                    if (!demonKilled) {
                      double R = 7.0D;
                      double RY = 6.0D;
                      float cellMidX = x + 4;
                      float cellMidY = y;
                      float cellMidZ = z;
                      AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(cellMidX - R, cellMidY - RY, cellMidZ - R, cellMidX + R, cellMidY + RY, cellMidZ + R);
                      

                      List<EntityReflection> EntityReflection = otherWorld.func_72872_a(EntityReflection.class, bounds);
                      
                      if (EntityReflection.size() == 0) {
                        EntityReflection reflection = new EntityReflection(otherWorld);
                        reflection.func_70080_a(0.5D + cellMidX, 1.1D + cellMidY, 0.5D + cellMidZ, 0.0F, 0.0F);
                        
                        reflection.func_110163_bv();
                        field_70170_p.func_72838_d(reflection);
                      }
                    }
                    
                    playerEx.setMirrorWorldEntryPoint(x, y, z);
                    player.func_70080_a(dimX, dimY - 1.0F, dimZ, face, field_70125_A);
                    
                    ItemGeneral.travelToDimension(player, instancedimensionMirrorID);
                    
                    player.func_70634_a(dimX, dimY - 1.0F, dimZ);
                  }
                  else if (isConnected) {
                    player.func_70080_a(dimX, dimY - 1.0F, dimZ, face, field_70125_A);
                    
                    ItemGeneral.travelToDimension(player, dim);
                    
                    player.func_70634_a(dimX, dimY - 1.0F, dimZ);
                  } else {
                    double targetX = dimX;
                    double targetY = dimY - 1.0F;
                    double targetZ = dimZ;
                    int targetDim = dim;
                    boolean CHECK_PLAYER_INV = true;
                    
                    MinecraftServer server = MinecraftServer.func_71276_C();
                    for (WorldServer worldServer : field_71305_c) {
                      for (Object obj : field_73010_i) {
                        EntityPlayer otherPlayer = (EntityPlayer)obj;
                        for (ItemStack stack : field_71071_by.field_70462_a) {
                          if ((stack != null) && (stack.func_77973_b() == ItemsMIRROR))
                          {
                            boolean isMirror = tile.isTargettedBy(stack);
                            if (isMirror) {
                              if (field_71093_bK == instancedimensionMirrorID) break;
                              targetX = field_70165_t;
                              targetY = field_70163_u;
                              targetZ = field_70161_v;
                              targetDim = field_71093_bK; break;
                            }
                          }
                        }
                      }
                    }
                    


                    player.func_70080_a(targetX, targetY, targetZ, face, field_70125_A);
                    
                    ItemGeneral.travelToDimension(player, targetDim);
                    player.func_70634_a(targetX, targetY, targetZ);
                  }
                  
                  ParticleEffect.SPLASH.send(SoundEffect.RANDOM_SPLASH, entity, 0.5D, 2.0D, 16);
                }
              }
            }
          }
        }
      }
    }
  }
  
  public void demonSlain(World world, double posX, double posY, double posZ) {
    if (!field_72995_K) {
      double R = 7.0D;
      double RY = 6.0D;
      int x = (MathHelper.func_76128_c(posX) >> 4 << 4) + 4;
      int xmid = x + 4;
      int y = (MathHelper.func_76128_c(posY) >> 4 << 4) + 8;
      int z = (MathHelper.func_76128_c(posZ) >> 4 << 4) + 8;
      if (world.func_147439_a(x, y, z) == BlocksMIRROR_UNBREAKABLE) {
        AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(xmid - R, y - RY, z - R, xmid + R, y + RY, z + R);
        
        List<EntityReflection> reflections = world.func_72872_a(EntityReflection.class, bounds);
        
        int livingDemons = 0;
        for (EntityReflection entity : reflections) {
          if ((entity != null) && (entity.func_70089_S())) {
            livingDemons++;
          }
        }
        if (livingDemons == 0) {
          TileEntityMirror tile = (TileEntityMirror)BlockUtil.getTileEntity(world, x, y, z, TileEntityMirror.class);
          if (tile != null) {
            Coord dimCoords = tile.getDimCoords();
            int dim = dim;
            World otherWorld = MinecraftServer.func_71276_C().func_71218_a(dim);
            if (otherWorld != null) {
              TileEntityMirror otherTile = (TileEntityMirror)BlockUtil.getTileEntity(otherWorld, x, y, z, TileEntityMirror.class);
              
              if (otherTile != null) {
                demonKilled = true;
              }
            }
          }
        }
      }
    }
  }
  
  private boolean isTransportableEntity(Entity entity) {
    return (!(entity instanceof EntityMirrorFace)) && (((entity instanceof EntityLivingBase)) || ((entity instanceof EntityItem)));
  }
  


  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
  {
    if (field_72995_K) {
      return true;
    }
    if (!unbreakable) {
      int i1 = world.func_72805_g(x, y, z);
      
      int origX = x;int origZ = z;
      if (!isBlockTopOfMirror(i1)) {
        y++;
        
        if (world.func_147439_a(x, y, z) != this) {
          return true;
        }
      }
      
      TileEntityMirror tile = (TileEntityMirror)BlockUtil.getTileEntity(world, x, y, z, TileEntityMirror.class);
      
      if (tile != null) {
        tile.depolyDemon(player);
      }
      else {
        return true;
      }
    }
    
    return true;
  }
  
  public int func_149645_b()
  {
    return -1;
  }
  
  public boolean func_149686_d()
  {
    return false;
  }
  
  public boolean func_149662_c()
  {
    return false;
  }
  
  public AxisAlignedBB func_149668_a(World world, int x, int y, int z)
  {
    int side = getDirection(world.func_72805_g(x, y, z));
    
    float w = 0.15F;
    
    if (side == 0) {
      func_149676_a(0.0F, 0.0F, 0.85F, 1.0F, 1.0F, 1.0F);
    } else if (side == 1) {
      func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.15F);
    } else if (side == 2) {
      func_149676_a(0.85F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    } else if (side == 3) {
      func_149676_a(0.0F, 0.0F, 0.0F, 0.15F, 1.0F, 1.0F);
    }
    
    AxisAlignedBB bounds = super.func_149668_a(world, x, y, z);
    
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    
    return bounds;
  }
  
  @SideOnly(Side.CLIENT)
  public AxisAlignedBB func_149633_g(World world, int x, int y, int z)
  {
    int side = getDirection(world.func_72805_g(x, y, z));
    
    float w = 0.32F;
    
    if (side == 0) {
      func_149676_a(0.0F, 0.0F, 0.68F, 1.0F, 1.0F, 1.0F);
    } else if (side == 1) {
      func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.32F);
    } else if (side == 2) {
      func_149676_a(0.68F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    } else if (side == 3) {
      func_149676_a(0.0F, 0.0F, 0.0F, 0.32F, 1.0F, 1.0F);
    }
    
    AxisAlignedBB bounds = super.func_149633_g(world, x, y, z);
    
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    return bounds;
  }
  
  public AxisAlignedBB getServerSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
    int side = getDirection(world.func_72805_g(x, y, z));
    
    float w = 0.32F;
    
    float minX = 0.0F;
    float minY = 0.0F;
    float minZ = 0.0F;
    float maxX = 1.0F;
    float maxY = 1.0F;
    float maxZ = 1.0F;
    
    if (side == 0) {
      minZ = 0.68F;
    }
    else if (side == 1) {
      maxZ = 0.32F;
    } else if (side == 2) {
      minX = 0.68F;
    } else if (side == 3) {
      maxZ = 0.32F;
    }
    
    AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(x + minX, y + minY, z + minZ, x + maxX, y + maxY, z + maxZ);
    

    return bounds;
  }
  
  public void func_149695_a(World world, int x, int y, int z, Block block)
  {
    int l = world.func_72805_g(x, y, z);
    int i1 = getDirection(l);
    
    if (isBlockTopOfMirror(l)) {
      if (world.func_147439_a(x, y - 1, z) != this) {
        if (!field_72995_K) {
          func_149697_b(world, x, y, z, l, 0);
        }
        world.func_147468_f(x, y, z);
      }
    } else if (world.func_147439_a(x, y + 1, z) != this) {
      world.func_147468_f(x, y, z);
    }
  }
  
  public Item func_149650_a(int meta, Random rand, int p_149650_3_)
  {
    return isBlockTopOfMirror(meta) ? ItemsMIRROR : Item.func_150899_d(0);
  }
  

  public void func_149690_a(World world, int x, int y, int z, int p_149690_5_, float p_149690_6_, int p_149690_7_)
  {
    if (isBlockTopOfMirror(p_149690_5_)) {
      super.func_149690_a(world, x, y, z, p_149690_5_, p_149690_6_, 0);
    }
  }
  
  public int func_149656_h()
  {
    return super.func_149656_h();
  }
  
  @SideOnly(Side.CLIENT)
  public Item func_149694_d(World world, int x, int y, int z)
  {
    return ItemsMIRROR;
  }
  

  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {}
  

  public void func_149681_a(World world, int x, int y, int z, int meta, EntityPlayer player)
  {
    if ((field_71075_bZ.field_75098_d) && 
      (isBlockTopOfMirror(meta))) {
      if (world.func_147439_a(x, y - 1, z) == this) {
        world.func_147468_f(x, y - 1, z);
      }
      
      meta |= 0x8;
      world.func_72921_c(x, y, z, meta, 4);
    }
    

    func_149697_b(world, x, y, z, meta, 0);
    
    super.func_149681_a(world, x, y, z, meta, player);
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune)
  {
    ArrayList<ItemStack> drops = new ArrayList();
    boolean brokenInCreativeMode = (meta & 0x8) != 0;
    if (!brokenInCreativeMode) {
      TileEntityMirror tile = (TileEntityMirror)BlockUtil.getTileEntity(world, x, y, z, TileEntityMirror.class);
      if (tile != null) {
        ItemStack stack = new ItemStack(ItemsMIRROR);
        
        NBTTagCompound nbtRoot = new NBTTagCompound();
        tile.writeItemDataToNBT(nbtRoot);
        stack.func_77982_d(nbtRoot);
        
        if ((field_73011_w.field_76574_g != instancedimensionMirrorID) && (tile.isDimLinked())) {
          Coord dimCoords = tile.getDimCoords();
          World otherWorld = MinecraftServer.func_71276_C().func_71218_a(instancedimensionMirrorID);
          
          if ((otherWorld != null) && (otherWorld.func_147439_a(x, y, z) == BlocksMIRROR))
          {
            TileEntityMirror otherTile = (TileEntityMirror)BlockUtil.getTileEntity(otherWorld, x, y, z, TileEntityMirror.class);
            
            if (otherTile != null) {
              isConnected = false;
              otherTile.markBlockForUpdate(false);
            }
          }
        }
        

        drops.add(stack);
      }
    }
    return drops;
  }
  
  public void loadFromItem(ItemStack stack, EntityPlayer player, World world, int x, int y, int z) {
    TileEntityMirror tile = (TileEntityMirror)BlockUtil.getTileEntity(world, x, y, z, TileEntityMirror.class);
    if (tile != null) {
      NBTTagCompound nbtRoot = stack.func_77978_p();
      if (nbtRoot != null) {
        tile.readItemDataFromNBT(nbtRoot);
        if ((field_73011_w.field_76574_g != instancedimensionMirrorID) && (tile.isDimLinked())) {
          Coord dimCoords = tile.getDimCoords();
          World otherWorld = MinecraftServer.func_71276_C().func_71218_a(instancedimensionMirrorID);
          
          if ((otherWorld != null) && (otherWorld.func_147439_a(x, y, z) == BlocksMIRROR_UNBREAKABLE))
          {
            TileEntityMirror otherTile = (TileEntityMirror)BlockUtil.getTileEntity(otherWorld, x, y, z, TileEntityMirror.class);
            
            if (otherTile != null) {
              isConnected = true;
              dimCoords = new Coord(x, y, z);
              otherTile.markBlockForUpdate(false);
            }
          }
        }
      }
    }
  }
  

  public static class TileEntityMirror
    extends TileEntityBase
  {
    public int men;
    
    private Coord dimCoords;
    
    private int dim;
    
    private boolean isConnected;
    
    private boolean demonKilled;
    
    private GameProfile favorite;
    private UUID fairest;
    private Set<String> playersSeen = new java.util.HashSet();
    
    public TileEntityMirror() {}
    
    public void func_145845_h() { super.func_145845_h();
      
      if (ticks % (field_145850_b.field_72995_K ? 10 : 40) == 1L) {
        int side = BlockMirror.getDirection(field_145850_b.func_72805_g(field_145851_c, field_145848_d, field_145849_e));
        
        int xMin = -1;int xMax = 1;int zMin = -1;int zMax = 1;
        
        int scale = 4;
        if (side == 0) {
          zMin = -4;
          zMax = 0;
        } else if (side == 1) {
          zMin = 0;
          zMax = 4;
        } else if (side == 2) {
          xMin = -4;
          xMax = 0;
        } else if (side == 3)
        {
          xMin = 0;
          xMax = 4;
        }
        
        AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(field_145851_c + xMin, field_145848_d, field_145849_e + zMin, field_145851_c + xMax + 1, field_145848_d + 1, field_145849_e + zMax + 1);
        
        List<EntityLivingBase> entities = field_145850_b.func_72872_a(EntityLivingBase.class, bounds);
        
        men = entities.size();
        if (!field_145850_b.field_72995_K) {
          for (EntityLivingBase entity : entities) {
            if ((entity instanceof EntityPlayer)) {
              playersSeen.add(entity.func_70005_c_());
            }
          }
        }
      }
    }
    

    long cooldown;
    public void addCooldown(int i)
    {
      cooldown = (ticks + i);
    }
    
    public boolean onCooldown() {
      return ticks < cooldown;
    }
    
    public boolean isTargettedBy(ItemStack stack) {
      if ((stack != null) && (stack.func_77973_b() == ItemsMIRROR) && (stack.func_77978_p() != null)) {
        NBTTagCompound nbtRoot = stack.func_77978_p();
        if ((nbtRoot.func_74764_b("DimCoords")) && (nbtRoot.func_74764_b("Dimension")))
        {
          if (field_145850_b.field_73011_w.field_76574_g == nbtRoot.func_74762_e("Dimension")) {
            Coord coords = Coord.fromTagNBT(nbtRoot.func_74775_l("DimCoords"));
            if (coords != null) {
              return coords.isMatch(field_145851_c, field_145848_d, field_145849_e);
            }
          }
        }
      }
      return false;
    }
    
    long lastFairestSpawn = 0L;
    
    private void depolyDemon(EntityPlayer player) {
      if ((!demonKilled) && (field_145850_b.field_73011_w.field_76574_g != instancedimensionMirrorID)) {
        if ((player.func_70694_bm() != null) && (player.func_70694_bm().func_77973_b() == ItemsTAGLOCK_KIT)) {
          ExtendedPlayer playerEx = ExtendedPlayer.get(player);
          TransformCreature currentTransform = playerEx.getCreatureType();
          if ((currentTransform == TransformCreature.NONE) || (currentTransform == TransformCreature.PLAYER)) {
            String username = ItemsTAGLOCK_KIT.getBoundUsername(player.func_70694_bm(), Integer.valueOf(1));
            
            if ((username == null) || (username.isEmpty()) || (username.equals(player.func_70005_c_())))
            {
              if (currentTransform == TransformCreature.PLAYER) {
                ParticleEffect.SMOKE.send(SoundEffect.WITCHERY_RANDOM_POOF, player, 0.5D, 2.0D, 16);
                Shapeshift.INSTANCE.shiftTo(player, TransformCreature.NONE);
              } else {
                ParticleEffect.SMOKE.send(SoundEffect.NOTE_SNARE, player, 0.5D, 2.0D, 16);
              }
            } else {
              IPowerSource power = PowerSources.findClosestPowerSource(this);
              if ((power != null) && (power.consumePower(4000.0F))) {
                ParticleEffect.SMOKE.send(SoundEffect.WITCHERY_RANDOM_POOF, player, 0.5D, 2.0D, 16);
                playerEx.setOtherPlayerSkin(username);
                Shapeshift.INSTANCE.shiftTo(player, TransformCreature.PLAYER);
              } else {
                ParticleEffect.SMOKE.send(SoundEffect.NOTE_SNARE, player, 0.5D, 2.0D, 16);
              }
            }
          }
        } else if ((player.func_70694_bm() != null) && (ItemsGENERIC.itemQuartzSphere.isMatch(player.func_70694_bm())))
        {

          IPowerSource power = PowerSources.findClosestPowerSource(this);
          if ((power != null) && (power.consumePower(2000.0F))) {
            ParticleEffect.SMOKE.send(SoundEffect.RANDOM_ORB, player, 0.5D, 2.0D, 16);
            ItemStack itemstack = player.func_70694_bm();
            if (field_77994_a > 1) {
              ItemStack newStack = new ItemStack(ItemsDUP_GRENADE);
              ItemSunGrenade.setOwnerName(newStack, player.func_70005_c_());
              
              field_77994_a -= 1;
              if (field_77994_a <= 0) {
                field_71071_by.func_70299_a(field_71071_by.field_70461_c, null);
              }
              if (!field_71071_by.func_70441_a(newStack)) {
                field_145850_b.func_72838_d(new EntityItem(field_145850_b, field_70165_t + 0.5D, field_70163_u + 1.5D, field_70161_v + 0.5D, newStack));
              } else if ((player instanceof EntityPlayerMP)) {
                ((EntityPlayerMP)player).func_71120_a(field_71069_bz);
              }
            } else {
              ItemStack newStack = new ItemStack(ItemsDUP_GRENADE);
              ItemSunGrenade.setOwnerName(newStack, player.func_70005_c_());
              player.func_70062_b(0, newStack);
              if ((player instanceof EntityPlayerMP)) {
                ((EntityPlayerMP)player).func_71120_a(field_71069_bz);
              }
            }
          } else {
            ParticleEffect.SMOKE.send(SoundEffect.NOTE_SNARE, player, 0.5D, 2.0D, 16);
          }
        }
        else {
          List<EntityMirrorFace> faces = field_145850_b.func_72872_a(EntityMirrorFace.class, BlocksMIRROR.func_149668_a(field_145850_b, field_145851_c, field_145848_d, field_145849_e));
          

          if (faces.size() == 0) {
            showMirrorHead(field_145850_b, field_145851_c, field_145848_d, field_145849_e);
            ParticleEffect.SPELL_COLORED.send(SoundEffect.WITCHERY_MOB_REFLECTION_SPEECH, this, 0.5D, 0.5D, 16, 7829503);
            

            double RANGE = 64.0D;
            List<EntityPlayer> players = field_145850_b.field_73010_i;
            for (EntityPlayer otherPlayer : players) {
              if (player.func_70092_e(field_145851_c, field_145848_d, field_145849_e) <= RANGE * RANGE) {
                ChatUtil.sendTranslated(otherPlayer, "witchery.rite.mirrormirror", new Object[] { player.func_70005_c_() });
              }
            }
            

            boolean fairestFound = false;
            if (fairest != null) {
              double R = 100.0D;
              double RY = 32.0D;
              AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(field_145851_c - 100.0D, field_145848_d - 32.0D, field_145849_e - 100.0D, field_145851_c + 100.0D, field_145848_d + 32.0D, field_145849_e + 100.0D);
              
              List<EntityFollower> followers = field_145850_b.func_72872_a(EntityFollower.class, bounds);
              
              for (EntityFollower follower : followers) {
                if ((follower.getPersistentID().equals(fairest)) && (follower.func_70089_S())) {
                  sayNotFairest(player, follower);
                  fairestFound = true;
                  break;
                }
              }
            }
            
            if (!fairestFound) {
              boolean isFairestAllowed = field_145850_b.func_82737_E() > lastFairestSpawn + TimeUtil.minsToTicks(2);
              

              fairest = null;
              
              if ((favorite == null) || (isFavorite(player))) {
                favorite = player.func_146103_bH();
                
                double CHANCE_OF_NEW_FAIREST = instancefairestSpawnChance;
                if ((isFairestAllowed) && (field_145850_b.field_73012_v.nextDouble() < CHANCE_OF_NEW_FAIREST)) {
                  EntityFollower follower = new EntityFollower(field_145850_b);
                  int followerType = field_145850_b.field_73012_v.nextInt(4) + 1;
                  follower.func_94058_c(EntityFollower.generateFollowerName(followerType));
                  follower.func_110163_bv();
                  follower.setFollowerType(followerType);
                  
                  Coord coord = null;
                  
                  int minRange = 50;
                  for (int i = 0; (i < 25) && (coord == null); i++) {
                    int x = field_145851_c + (field_145850_b.field_73012_v.nextBoolean() ? 1 : -1) * (50 + field_145850_b.field_73012_v.nextInt(50));
                    
                    int z = field_145849_e + (field_145850_b.field_73012_v.nextBoolean() ? 1 : -1) * (50 + field_145850_b.field_73012_v.nextInt(50));
                    
                    int y = Math.min(field_145848_d + 20, 250); for (int yMin = Math.max(field_145848_d - 20, 2); y >= yMin; y--) {
                      if ((field_145850_b.func_147439_a(x, y, z).func_149721_r()) && (field_145850_b.func_147439_a(x, y + 1, z).func_149688_o().func_76222_j()) && (field_145850_b.func_147437_c(x, y + 2, z)))
                      {

                        coord = new Coord(x, y, z);
                        break;
                      }
                    }
                  }
                  
                  if (coord != null) {
                    follower.func_70080_a(0.5D + x, 1.01D + y, 0.5D + z, 0.0F, 0.0F);
                    
                    field_145850_b.func_72838_d(follower);
                    fairest = follower.getPersistentID();
                    fairestFound = true;
                    lastFairestSpawn = field_145850_b.func_82737_E();
                    sayNotFairest(player, follower);
                  }
                }
                
                if (!fairestFound) {
                  ChatUtil.sendTranslated(EnumChatFormatting.AQUA, player, "witchery.rite.mirrormirror.you", new Object[0]);
                }
              }
              else {
                ChatUtil.sendTranslated(EnumChatFormatting.AQUA, player, "witchery.rite.mirrormirror.anotherplayer", new Object[0]);
                
                EntityPlayer otherPlayer = field_145850_b.func_72924_a(favorite.getName());
                if (otherPlayer != null) {
                  sayBearing(player, otherPlayer);
                }
              }
            }
            
            if (playersSeen.size() > 1) {
              List<String> seen = new ArrayList(playersSeen);
              java.util.Collections.sort(seen);
              StringBuffer sb = new StringBuffer();
              for (String s : seen) {
                if (!s.equals(player.func_70005_c_())) {
                  if (sb.length() > 0) {
                    sb.append(", ");
                  }
                  sb.append(s);
                }
              }
              if (sb.length() > 0) {
                ChatUtil.sendTranslated(EnumChatFormatting.AQUA, player, "witchery.rite.mirrormirror.playersseen", new Object[] { sb.toString() });
              }
              else {
                ChatUtil.sendTranslated(EnumChatFormatting.AQUA, player, "witchery.rite.mirrormirror.playersnotseen", new Object[0]);
              }
            }
            else {
              ChatUtil.sendTranslated(EnumChatFormatting.AQUA, player, "witchery.rite.mirrormirror.playersnotseen", new Object[0]);
            }
            
            if (isFavorite(player)) {
              playersSeen.clear();
            }
          }
        }
      }
    }
    

    public void sayNotFairest(EntityPlayer player, EntityFollower follower)
    {
      if (follower.getFollowerType() == 4) {
        ChatUtil.sendTranslated(EnumChatFormatting.AQUA, player, "witchery.rite.mirrormirror.anotherm", new Object[0]);
      } else {
        ChatUtil.sendTranslated(EnumChatFormatting.AQUA, player, "witchery.rite.mirrormirror.anotherf", new Object[0]);
      }
      
      sayBearing(player, follower);
    }
    
    public void sayBearing(EntityPlayer player, EntityLivingBase otherEntity) {
      double bearingRadians = Math.atan2(0.5D + field_145849_e - field_70161_v, 0.5D + field_145851_c - field_70165_t);
      
      double bearing = (Math.toDegrees(bearingRadians) + 180.0D + 90.0D) % 360.0D;
      if (bearing < 0.0D) {
        bearing += 360.0D;
      }
      
      int bearingIndex = (int)bearing / 45;
      if ((bearingIndex > 7) || (bearingIndex < 0)) {
        bearingIndex = 0;
      }
      
      ChatUtil.sendTranslated(EnumChatFormatting.AQUA, player, "witchery.rite.mirrormirror.bearing" + bearingIndex, new Object[0]);
    }
    
    private void showMirrorHead(World world, int x, int y, int z)
    {
      int side = BlockMirror.getDirection(world.func_72805_g(x, y, z));
      
      float dx = 0.0F;float dz = 0.0F;
      
      float scale = 0.4F;
      float rotation = 0.0F;
      if (side == 0) {
        dz = 0.4F;
        rotation = -90.0F;
      } else if (side == 1) {
        dz = -0.4F;
        rotation = 90.0F;
      } else if (side == 2) {
        dx = 0.4F;
        rotation = 0.0F;
      } else if (side == 3) {
        dx = -0.4F;
        rotation = -90.0F;
      }
      EntityMirrorFace face = new EntityMirrorFace(world);
      face.func_70107_b(x + 0.5D + dx, y + 0.1D, z + 0.5D + dz);
      world.func_72838_d(face);
    }
    
    private boolean isDimLinked() {
      return dimCoords != null;
    }
    
    private Coord getDimCoords() {
      if ((dimCoords == null) && (field_145850_b.field_73011_w.field_76574_g != instancedimensionMirrorID)) {
        World mworld = MinecraftServer.func_71276_C().func_71218_a(instancedimensionMirrorID);
        
        if (mworld != null) {
          int[][] map = { { 0, 1 }, { 1, 0 } };
          int cellX = 0;
          int cellZ = 0;
          int sign = 1;
          for (int i = 0; i < 256; i++) {
            for (int spin = 0; spin <= i; spin++) {
              for (int j = 0; j < map.length; j++) {
                if (i > 0) {
                  cellX += map[j][0] * sign;
                  cellZ += map[j][1] * sign;
                }
                int Y_LEVELS = instanceshrinkMirrorWorld ? 8 : 15;
                for (int cellY = 0; cellY < Y_LEVELS; cellY++) {
                  int dimX = (cellX << 4) + 4;
                  int dimY = (cellY << 4) + 8;
                  int dimZ = (cellZ << 4) + 8;
                  if ((mworld.func_147437_c(dimX, dimY, dimZ)) && (mworld.func_147437_c(dimX, dimY - 1, dimZ)))
                  {
                    boolean stop = false;
                    for (int y = dimY - 1; (y <= dimY + 6) && (!stop); y++) {
                      for (int x = dimX; (x <= dimX + 8) && (!stop); x++) {
                        for (int z = dimZ - 4; (z <= dimZ + 4) && (!stop); z++) {
                          Block block = mworld.func_147439_a(x, y, z);
                          if (!mworld.func_147437_c(x, y, z)) {
                            stop = true;
                            break;
                          }
                        }
                      }
                    }
                    
                    if (!stop) {
                      Block mirror = BlocksMIRROR_UNBREAKABLE;
                      int meta = 3;
                      mworld.func_147465_d(dimX, dimY, dimZ, mirror, meta | 0x4, 3);
                      TileEntityMirror tile = (TileEntityMirror)BlockUtil.getTileEntity(mworld, dimX, dimY, dimZ, TileEntityMirror.class);
                      
                      if (tile != null) {
                        dimCoords = new Coord(field_145851_c, field_145848_d, field_145849_e);
                        dim = field_145850_b.field_73011_w.field_76574_g;
                      }
                      if (mworld.func_147439_a(dimX, dimY, dimZ) == mirror) {
                        mworld.func_147465_d(dimX, dimY - 1, dimZ, mirror, meta, 3);
                      }
                      dimCoords = new Coord(dimX, dimY, dimZ);
                      return dimCoords;
                    }
                  }
                }
              }
            }
            sign *= -1;
          }
        }
      }
      
      return dimCoords;
    }
    
    public net.minecraft.network.Packet func_145844_m()
    {
      NBTTagCompound nbtTag = new NBTTagCompound();
      func_145841_b(nbtTag);
      return new S35PacketUpdateTileEntity(field_145851_c, field_145848_d, field_145849_e, 1, nbtTag);
    }
    
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
    {
      super.onDataPacket(net, packet);
      NBTTagCompound nbtTag = packet.func_148857_g();
      func_145839_a(nbtTag);
      field_145850_b.func_147479_m(field_145851_c, field_145848_d, field_145849_e);
    }
    
    public void func_145841_b(NBTTagCompound nbtRoot)
    {
      super.func_145841_b(nbtRoot);
      nbtRoot.func_74772_a("LastFairestSpawnTime", lastFairestSpawn);
      writeItemDataToNBT(nbtRoot);
    }
    
    public void func_145839_a(NBTTagCompound nbtRoot)
    {
      super.func_145839_a(nbtRoot);
      lastFairestSpawn = nbtRoot.func_74763_f("LastFairestSpawnTime");
      readItemDataFromNBT(nbtRoot);
    }
    
    private void writeItemDataToNBT(NBTTagCompound nbtRoot) {
      if (dimCoords != null) {
        NBTTagCompound nbtDim = dimCoords.toTagNBT();
        nbtDim.func_74768_a("Dimension", dim);
        nbtRoot.func_74782_a("DimCoords", nbtDim);
      }
      nbtRoot.func_74757_a("DemonSlain", demonKilled);
      
      if (favorite != null) {
        NBTTagCompound nbtPlayer = new NBTTagCompound();
        NBTUtil.func_152460_a(nbtPlayer, favorite);
        nbtRoot.func_74782_a("Favorite", nbtPlayer);
      }
      
      if (fairest != null) {
        nbtRoot.func_74778_a("Fairest", fairest.toString());
      }
      
      NBTTagList players = new NBTTagList();
      for (String player : playersSeen) {
        players.func_74742_a(new NBTTagString(player));
      }
      nbtRoot.func_74782_a("PlayersSeen", players);
    }
    
    public void readItemDataFromNBT(NBTTagCompound nbtRoot) {
      if (nbtRoot.func_74764_b("DimCoords")) {
        NBTTagCompound nbtDim = nbtRoot.func_74775_l("DimCoords");
        dimCoords = Coord.fromTagNBT(nbtDim);
        dim = nbtDim.func_74762_e("Dimension");
      }
      demonKilled = nbtRoot.func_74767_n("DemonSlain");
      
      if (nbtRoot.func_150297_b("Favorite", 10)) {
        favorite = NBTUtil.func_152459_a(nbtRoot.func_74775_l("Favorite"));
      } else {
        favorite = null;
      }
      
      if (nbtRoot.func_74764_b("Fairest")) {
        fairest = UUID.fromString(nbtRoot.func_74779_i("Fairest"));
      } else {
        fairest = null;
      }
      
      playersSeen.clear();
      if (nbtRoot.func_74764_b("PlayersSeen")) {
        NBTTagList players = nbtRoot.func_150295_c("PlayersSeen", 8);
        for (int i = 0; i < players.func_74745_c(); i++) {
          playersSeen.add(players.func_150307_f(i));
        }
      }
    }
    
    public boolean isFavorite(EntityPlayer player) {
      return (favorite != null) && (player != null) && (favorite.equals(player.func_146103_bH()));
    }
  }
}
