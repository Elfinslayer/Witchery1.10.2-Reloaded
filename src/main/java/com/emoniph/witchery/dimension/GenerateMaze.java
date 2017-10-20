package com.emoniph.witchery.dimension;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import java.util.Arrays;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class GenerateMaze
{
  private final int width;
  private final int depth;
  private final int[][] maze;
  public static final int WALL_HEIGHT = 6;
  
  public GenerateMaze(int width, int depth, Random rand)
  {
    this.width = width;
    this.depth = depth;
    maze = new int[width][depth];
    generateMaze(0, 0, rand);
  }
  
  public void display(World world, int origX, int origY, int origZ, Block walls, Block floor)
  {
    for (int i = 0; i < depth; i++)
    {

      for (int j = 0; j < width; j++) {
        if ((maze[j][i] & 0x1) == 0) {
          drawWall(world, origX + j * 2, origY, origZ + 2 * i, walls, floor);
          drawWall(world, origX + j * 2 + 1, origY, origZ + 2 * i, walls, floor);
        } else {
          drawWall(world, origX + j * 2, origY, origZ + 2 * i, walls, floor);
          drawPassage(world, origX + j * 2 + 1, origY, origZ + 2 * i, walls, floor);
        }
      }
      
      drawWall(world, origX + j * 2, origY, origZ + 2 * i, walls, floor);
      

      for (j = 0; j < width; j++) {
        if ((maze[j][i] & 0x8) == 0) {
          drawWall(world, origX + j * 2, origY, origZ + 2 * i + 1, walls, floor);
          drawPassage(world, origX + j * 2 + 1, origY, origZ + 2 * i + 1, walls, floor);
        } else {
          drawPassage(world, origX + j * 2, origY, origZ + 2 * i + 1, walls, floor);
          drawPassage(world, origX + j * 2 + 1, origY, origZ + 2 * i + 1, walls, floor);
        }
      }
      
      drawWall(world, origX + j * 2, origY, origZ + 2 * i + 1, walls, floor);
    }
    


    for (int j = 0; j < width; j++) {
      drawWall(world, origX + j * 2, origY, origZ + 2 * i, walls, floor);
      drawWall(world, origX + j * 2 + 1, origY, origZ + 2 * i, walls, floor);
    }
    
    drawWall(world, origX + j * 2, origY, origZ + 2 * i, walls, floor);
    


    int CHAMBER_WIDTH = 7;
    int CHAMBER_WIDTH_HALF = CHAMBER_WIDTH / 2;
    

    for (int x = 0; x < CHAMBER_WIDTH; x++) {
      for (int y = 0; y < CHAMBER_WIDTH; y++) {
        drawPassage(world, origX + width + x - CHAMBER_WIDTH_HALF, origY, origZ + y + 1, walls, floor);
      }
    }
    

    for (int x = 0; x < CHAMBER_WIDTH; x++) {
      for (int y = 0; y < CHAMBER_WIDTH + 2; y++) {
        drawPassage(world, origX + width + x - CHAMBER_WIDTH_HALF, origY, origZ + 2 * depth + y - CHAMBER_WIDTH, walls, floor);
      }
    }
    
    drawPortal(world, origX + width, origY, origZ + 2 * depth, walls, floor);
    
    CHAMBER_WIDTH = 5;
    CHAMBER_WIDTH_HALF = CHAMBER_WIDTH / 2;
    
    int MAX_SHIFT = 5;
    
    int shift = field_73012_v.nextInt(11) - 5;
    

    for (int x = 0; x < CHAMBER_WIDTH; x++) {
      for (int y = 0; y < CHAMBER_WIDTH; y++) {
        drawPassage(world, origX + x + 1, origY, origZ + y + depth - CHAMBER_WIDTH_HALF + shift, walls, floor);
      }
    }
    
    drawChest(world, origX + CHAMBER_WIDTH_HALF + 1, origY, origZ + depth + shift, walls, floor);
    
    shift = field_73012_v.nextInt(11) - 5;
    

    for (int x = 0; x < CHAMBER_WIDTH; x++) {
      for (int y = 0; y < CHAMBER_WIDTH; y++) {
        drawPassage(world, origX + 2 * width + x - CHAMBER_WIDTH, origY, origZ + y + depth - CHAMBER_WIDTH_HALF + shift, walls, floor);
      }
    }
    
    drawChest(world, origX + 2 * width - CHAMBER_WIDTH_HALF - 1, origY, origZ + depth + shift, walls, floor);
    
    int ROOM_WIDTH = 7;
    int ROOM_WIDTH_HALF = 3;
    

    for (int x = 0; x < 7; x++) {
      for (int y = 0; y < 7; y++) {
        drawPassage(world, origX + width + x - 3, origY, origZ + depth + y - 3, walls, floor);
      }
    }
    
    drawChest(world, origX + width, origY, origZ + depth, walls, floor);
  }
  
  private void drawPortal(World world, int x, int y, int z, Block wallBlock, Block floorBlock) {
    world.func_147449_b(x, y + 1, z, BlocksTORMENT_PORTAL);
    world.func_147449_b(x, y + 2, z, BlocksTORMENT_PORTAL);
    world.func_147449_b(x, y + 3, z, floorBlock);
    
    world.func_147449_b(x - 1, y + 1, z, floorBlock);
    world.func_147449_b(x - 1, y + 2, z, floorBlock);
    world.func_147449_b(x - 1, y + 3, z, floorBlock);
    
    world.func_147449_b(x + 1, y + 1, z, floorBlock);
    world.func_147449_b(x + 1, y + 2, z, floorBlock);
    world.func_147449_b(x + 1, y + 3, z, floorBlock);
  }
  
  private static void drawChest(World world, int x, int y, int z, Block wallBlock, Block floorBlock) {
    world.func_147449_b(x, y, z, BlocksREFILLING_CHEST);
  }
  


  private void drawWall(World world, int x, int y, int z, Block wallBlock, Block floorBlock)
  {
    for (int h = 0; h < 6; h++) {
      world.func_147449_b(x, y + h, z, wallBlock);
    }
  }
  
  private void drawPassage(World world, int x, int y, int z, Block wallBlock, Block floorBlock) {
    world.func_147449_b(x, y - 1, z, floorBlock);
    if (field_73012_v.nextInt(100) == 0) {
      world.func_147449_b(x, y, z, net.minecraft.init.Blocks.field_150391_bh);
    } else {
      world.func_147449_b(x, y, z, floorBlock);
    }
    for (int h = 1; h < 5; h++) {
      world.func_147468_f(x, y + h, z);
    }
    world.func_147449_b(x, y + 6 - 1, z, wallBlock);
  }
  
  private void generateMaze(int cx, int cy, Random rand) {
    DIR[] dirs = DIR.values();
    java.util.Collections.shuffle(Arrays.asList(dirs), rand);
    for (DIR dir : dirs) {
      int nx = cx + dx;
      int ny = cy + dy;
      if ((between(nx, width)) && (between(ny, depth)) && (maze[nx][ny] == 0)) {
        maze[cx][cy] |= bit;
        maze[nx][ny] |= access$300bit;
        generateMaze(nx, ny, rand);
      }
    }
  }
  
  private static boolean between(int v, int upper) {
    return (v >= 0) && (v < upper);
  }
  
  private static enum DIR {
    N(1, 0, -1),  S(2, 0, 1),  E(4, 1, 0),  W(8, -1, 0);
    
    private final int bit;
    private final int dx;
    private final int dy;
    private DIR opposite;
    
    static {
      Nopposite = S;
      Sopposite = N;
      Eopposite = W;
      Wopposite = E;
    }
    
    private DIR(int bit, int dx, int dy) {
      this.bit = bit;
      this.dx = dx;
      this.dy = dy;
    }
  }
}
