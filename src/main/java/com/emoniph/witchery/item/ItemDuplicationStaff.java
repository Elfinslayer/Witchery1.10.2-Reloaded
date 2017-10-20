package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.infusion.infusions.InfusionOtherwhere;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.BlockStairs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.RegistryNamespaced;
import net.minecraft.world.World;

public class ItemDuplicationStaff
  extends ItemBase
{
  public ItemDuplicationStaff()
  {
    func_77625_d(1);
    func_77664_n();
  }
  
  public static enum Rotation {
    NONE,  DEGREES_90,  DEGREES_180,  DEGREES_270;
    
    private Rotation() {}
  }
  
  @SideOnly(Side.CLIENT)
  public EnumRarity func_77613_e(ItemStack itemstack) { return EnumRarity.epic; }
  

  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player)
  {
    if ((!field_72995_K) && (player.func_70093_af())) {
      MovingObjectPosition pickedBlock = InfusionOtherwhere.doCustomRayTrace(world, player, true, 6.0D);
      if (((pickedBlock == null) || (field_72313_a != MovingObjectPosition.MovingObjectType.BLOCK)) && (stack.func_77942_o())) {
        NBTTagCompound nbtRoot = stack.func_77978_p();
        nbtRoot.func_82580_o("SavedSchematic");
        nbtRoot.func_82580_o("Marker");
        SoundEffect.RANDOM_FIZZ.playAtPlayer(world, player);
      }
    }
    return stack;
  }
  
  public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
  {
    if ((stack.func_77942_o()) && (stack.func_77978_p().func_74764_b("SavedSchematic"))) {
      if (!player.func_70093_af()) {
        int rotation = stack.func_77978_p().func_74762_e("Rotation");
        rotation++;
        if (rotation > 3) {
          rotation = 0;
        }
        stack.func_77978_p().func_74768_a("Rotation", rotation);
        placeSchematic(stack, world, x, y + 1, z, player, Rotation.values()[rotation], true);
      } else {
        placeSchematic(stack, world, x, y + 1, z, player, Rotation.NONE, true);
        stack.func_77978_p().func_74768_a("Rotation", 0);
      }
    } else if ((stack.func_77942_o()) && (stack.func_77978_p().func_74764_b("Marker"))) {
      saveSchematic(stack, world, x, y, z, player);
    } else {
      setMarker(stack, world, x, y, z, player);
    }
    
    return !field_72995_K;
  }
  
  private void setMarker(ItemStack stack, World world, int x, int y, int z, EntityPlayer player) {
    if (!field_72995_K) {
      if (!stack.func_77942_o()) {
        stack.func_77982_d(new NBTTagCompound());
      }
      NBTTagCompound nbtRoot = stack.func_77978_p();
      Coord marker = new Coord(x, y, z);
      nbtRoot.func_74782_a("Marker", marker.toTagNBT());
      ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_POP, world, 0.5D + x, y, 0.5D + z, 1.0D, 1.0D, 16);
    }
  }
  
  private void saveSchematic(ItemStack stack, World world, int x, int y, int z, EntityPlayer player) {
    if (!field_72995_K) {
      NBTTagCompound nbtRoot = stack.func_77978_p();
      if (nbtRoot != null) {
        PrintWriter writer = null;
        PrintWriter writer2 = null;
        try {
          Coord marker = Coord.fromTagNBT(nbtRoot.func_74775_l("Marker"));
          NBTTagList nbtList = new NBTTagList();
          ArrayList<Byte> bytes = new ArrayList();
          
          int width = Math.max(x, x) - Math.min(x, x) - 1;
          int height = Math.max(y, y) - Math.min(y, y) - 1;
          int depth = Math.max(z, z) - Math.min(z, z) - 1;
          
          File file = instancedupStaffSaveTemplate ? new File(String.format("%s/schematic.txt", new Object[] { Witchery.configDirectoryPath })) : null;
          File file2 = instancedupStaffSaveTemplate ? new File(String.format("%s/schematic2.txt", new Object[] { Witchery.configDirectoryPath })) : null;
          if ((file != null) && (!file.exists())) {
            file.createNewFile();
          }
          

          writer = new PrintWriter(file);
          if (writer != null) {
            writer.println(String.format("final NBTTagCompound nbtSchematic = new NBTTagCompound();", new Object[0]));
            writer.println(String.format("final NBTTagList nbtList = new NBTTagList();", new Object[0]));
            writer.println(String.format("NBTTagCompound nbtBlock;", new Object[0]));
          }
          
          if ((file2 != null) && (!file2.exists())) {
            file2.createNewFile();
          }
          

          writer2 = new PrintWriter(file2);
          if ((writer2 == null) || (
          

            (x != x) && (y != y) && (z != z))) {
            int minX = Math.min(x, x) + 1;
            int minZ = Math.min(z, z) + 1;
            int minY = Math.min(y, y) + 1;
            for (int dx = minX; dx < Math.max(x, x); dx++) {
              for (int dz = minZ; dz < Math.max(z, z); dz++) {
                for (int dy = minY; dy < Math.max(y, y); dy++)
                {
                  Block block = BlockUtil.getBlock(world, dx, dy, dz);
                  int meta = world.func_72805_g(dx, dy, dz);
                  NBTTagCompound nbtBlock = new NBTTagCompound();
                  String blockName = Block.field_149771_c.func_148750_c(block);
                  nbtBlock.func_74778_a("n", blockName);
                  if (meta != 0) {
                    nbtBlock.func_74768_a("m", meta);
                  }
                  nbtList.func_74742_a(nbtBlock);
                  
                  if (writer != null) {
                    writer.println(String.format("nbtBlock = new NBTTagCompound();", new Object[0]));
                    writer.println(String.format("nbtBlock.setString(\"n\", \"%s\");", new Object[] { blockName }));
                    if (meta != 0) {
                      writer.println(String.format("nbtBlock.setInteger(\"m\", %d);", new Object[] { Integer.valueOf(meta) }));
                    }
                    writer.println(String.format("nbtList.appendTag(nbtBlock);", new Object[0]));
                  }
                  
                  if ((writer2 != null) && (block != Blocks.field_150350_a)) {
                    String blockNameStripped = blockName.substring(blockName.indexOf(':') + 1);
                    writer2.println(String.format("placeBlockAtCurrentPosition(world, Blocks.%s, %s, %d, %d, %d, bounds);", new Object[] { blockNameStripped, blockNameStripped, Integer.valueOf(meta), Integer.valueOf(dx - minX), Integer.valueOf(dy - minY), Integer.valueOf(dz - minZ) }));
                  }
                }
              }
            }
          }
          







          if (nbtList.func_74745_c() > 0) {
            NBTTagCompound nbtSchematic = new NBTTagCompound();
            nbtSchematic.func_74782_a("blocks", nbtList);
            nbtSchematic.func_74768_a("xMax", width);
            nbtSchematic.func_74768_a("yMax", height);
            nbtSchematic.func_74768_a("zMax", depth);
            
            if (writer != null) {
              writer.println(String.format("nbtSchematic.setTag(\"blocks\", nbtList);", new Object[0]));
              writer.println(String.format("nbtSchematic.setInteger(\"xMax\", %d);", new Object[] { Integer.valueOf(width) }));
              writer.println(String.format("nbtSchematic.setInteger(\"yMax\", %d);", new Object[] { Integer.valueOf(height) }));
              writer.println(String.format("nbtSchematic.setInteger(\"zMax\", %d);", new Object[] { Integer.valueOf(depth) }));
            }
            
            nbtRoot.func_74782_a("SavedSchematic", nbtSchematic);
            nbtRoot.func_82580_o("Marker");
            

            ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_POP, world, 0.5D + x, y, 0.5D + z, 1.0D, 1.0D, 16);
          } else {
            ParticleEffect.SMOKE.send(SoundEffect.NOTE_SNARE, world, 0.5D + x, y, 0.5D + z, 1.0D, 1.0D, 16);
          }
        }
        catch (IOException ex) {}finally
        {
          if (writer != null) {
            writer.close();
          }
          
          if (writer2 != null) {
            writer2.close();
          }
        }
      }
    }
  }
  
  private void placeSchematic(ItemStack stack, World world, int x, int y, int z, EntityPlayer player, Rotation rotation, boolean clearAir) {
    if (!field_72995_K) {
      NBTTagCompound nbtRoot = stack.func_77978_p();
      if (nbtRoot != null) {
        NBTTagCompound nbtSchematic = nbtRoot.func_74775_l("SavedSchematic");
        drawSchematicInWorld(world, x, y, z, rotation, clearAir, nbtSchematic);
      }
    }
  }
  
  public static void drawSchematicInWorld(World world, int x, int y, int z, Rotation rotation, boolean clearAir, NBTTagCompound nbtSchematic) {
    if (nbtSchematic != null) {
      NBTTagList nbtBlocks = nbtSchematic.func_150295_c("blocks", 10);
      int width = nbtSchematic.func_74762_e("xMax");
      int height = nbtSchematic.func_74762_e("yMax");
      int depth = nbtSchematic.func_74762_e("zMax");
      if ((nbtBlocks != null) && (width > 0) && (height > 0) && (depth > 0)) {
        int blockIndex = 0;
        if (rotation == Rotation.DEGREES_180) {
          for (int dx = width - 1; dx >= 0; dx--) {
            for (int dz = depth - 1; dz >= 0; dz--) {
              blockIndex = drawBlocks(world, dx + x, y, dz + z, nbtBlocks, height, blockIndex, rotation, clearAir);
            }
          }
        } else if (rotation == Rotation.DEGREES_90) {
          for (int dx = width - 1; dx >= 0; dx--) {
            for (int dz = 0; dz < depth; dz++) {
              blockIndex = drawBlocks(world, dz + x, y, dx + z, nbtBlocks, height, blockIndex, rotation, clearAir);
            }
            
          }
        } else if (rotation == Rotation.DEGREES_270) {
          for (int dx = 0; dx < width; dx++) {
            for (int dz = depth - 1; dz >= 0; dz--) {
              blockIndex = drawBlocks(world, dz + x, y, dx + z, nbtBlocks, height, blockIndex, rotation, clearAir);
            }
          }
        } else {
          for (int dx = 0; dx < width; dx++) {
            for (int dz = 0; dz < depth; dz++) {
              blockIndex = drawBlocks(world, dx + x, y, dz + z, nbtBlocks, height, blockIndex, rotation, clearAir);
            }
          }
        }
      }
    }
  }
  
  private static int drawBlocks(World world, int x, int y, int z, NBTTagList nbtBlocks, int height, int blockIndex, Rotation rotation, boolean clearAir) {
    for (int dy = 0; dy < height; dy++) {
      NBTTagCompound nbtBlock = nbtBlocks.func_150305_b(blockIndex++);
      String blockName = nbtBlock.func_74779_i("n");
      int blockMeta = nbtBlock.func_74762_e("m");
      
      Block block = Block.func_149684_b(blockName);
      if ((block instanceof BlockStairs)) {
        int direction = blockMeta & 0x3;
        int updown = blockMeta >> 2 & 0x1;
        
        if (rotation == Rotation.DEGREES_180) {
          direction = new int[] { 1, 0, 3, 2 }[direction];
        } else if (rotation == Rotation.DEGREES_90) {
          direction = new int[] { 3, 2, 0, 1 }[direction];
        } else if (rotation == Rotation.DEGREES_270) {
          direction = new int[] { 2, 3, 1, 0 }[direction];
        }
        blockMeta = direction | updown << 2;
      } else if ((block instanceof BlockRotatedPillar)) {
        int type = blockMeta & 0x3;
        int direction = blockMeta >> 2 & 0x3;
        int other = blockMeta >> 4;
        if ((rotation == Rotation.DEGREES_90) || (rotation == Rotation.DEGREES_270)) {
          direction = new int[] { 0, 2, 1, 3 }[direction];
          blockMeta = type | direction << 2 | other << 4;
        }
      } else if (((block instanceof BlockDoor)) && 
        ((blockMeta >> 4 & 0x1) == 0)) {
        int direction = blockMeta & 0x3;
        int other = blockMeta >> 2;
        if (rotation == Rotation.DEGREES_180) {
          direction = new int[] { 2, 3, 0, 1 }[direction];
        } else if (rotation == Rotation.DEGREES_90) {
          direction = new int[] { 3, 0, 1, 2 }[direction];
        } else if (rotation == Rotation.DEGREES_270) {
          direction = new int[] { 1, 2, 3, 0 }[direction];
        }
        blockMeta = direction | other << 2;
      }
      

      if ((block != null) && ((clearAir) || (block != Blocks.field_150350_a))) {
        world.func_147465_d(x, dy + y, z, block, blockMeta, 2);
      }
    }
    return blockIndex;
  }
}
