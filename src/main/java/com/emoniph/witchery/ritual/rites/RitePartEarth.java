package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.ritual.RitualStep.Result;
import com.emoniph.witchery.ritual.RitualStep.SacrificedItem;
import com.emoniph.witchery.util.Coord;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.world.World;

public class RitePartEarth extends com.emoniph.witchery.ritual.Rite
{
  private final int length;
  private final int width;
  private final int depth;
  
  public RitePartEarth(int length, int width, int depth)
  {
    this.length = length;
    this.width = width;
    this.depth = depth;
  }
  
  public void addSteps(ArrayList<RitualStep> steps, int initialStage)
  {
    steps.add(new StepPartEarth(this, initialStage));
  }
  
  private static class StepPartEarth extends RitualStep
  {
    private final RitePartEarth rite;
    private int stage = 0;
    Coord coord;
    
    public StepPartEarth(RitePartEarth rite, int initialStage) { super();
      this.rite = rite;
      stage = initialStage;
    }
    



    public int getCurrentStage()
    {
      return stage;
    }
    
    ArrayList<Coord> coords = new ArrayList();
    
    public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
    {
      if ((stage == 0) && (ticks % 20L != 0L)) {
        return RitualStep.Result.STARTING;
      }
      
      int length = rite.length;
      int width = rite.width + (covenSize > 2 ? 2 : 0);
      int depth = rite.depth;
      
      if ((++stage == 1) || (coords.isEmpty())) {
        coords.clear();
        coord = new Coord(posX, posY - 1, posZ);
        coords.add(coord);
        int last = (sacrificedItems != null) && (!sacrificedItems.isEmpty()) ? coord.getHeading(sacrificedItems.get(0)).location) : 0;
        int probability = 20;
        for (int l = 0; l < length - 1; l++)
        {
          last = move(world, last, coord, Math.max(probability - l / 2, 6));
          coords.add(coord);
        }
      }
      
      int DELAY = 4;
      if (!field_72995_K) {
        Coord c = (Coord)coords.get(stage + 4);
        drawFilledCircle(world, x, z, y, width + (field_73012_v.nextInt(3) == 0 ? 1 : 0), depth - 2 + field_73012_v.nextInt(5));
      }
      
      return stage >= coords.size() - 4 - 1 ? RitualStep.Result.COMPLETED : RitualStep.Result.UPKEEP;
    }
    
    protected void drawFilledCircle(World world, int x0, int z0, int y, int radius, int depth) {
      int x = radius;
      int z = 0;
      int radiusError = 1 - x;
      
      while (x >= z) {
        drawLine(world, -x + x0, x + x0, z + z0, y, depth);
        drawLine(world, -z + x0, z + x0, x + z0, y, depth);
        drawLine(world, -x + x0, x + x0, -z + z0, y, depth);
        drawLine(world, -z + x0, z + x0, -x + z0, y, depth);
        
        z++;
        
        if (radiusError < 0) {
          radiusError += 2 * z + 1;
        } else {
          x--;
          radiusError += 2 * (z - x + 1);
        }
      }
    }
    
    protected void drawLine(World world, int x1, int x2, int z, int y, int depth) {
      for (int x = x1; x <= x2; x++) {
        drawPixel(world, x, z, y, depth);
      }
    }
    
    protected void drawPixel(World world, int x, int z, int y, int depth) {
      for (int d = 0; d < depth; d++) {
        if (com.emoniph.witchery.util.BlockProtect.canBreak(x, y - d, z, world)) {
          world.func_147468_f(x, y - d, z);
        }
      }
    }
    
    private int move(World world, int last, Coord coord, int probability) {
      int val = field_73012_v.nextInt(probability);
      switch (last) {
      case 0: 
        if (val == 0) {
          this.coord = coord.northEast();
          return 1; }
        if (val == 1) {
          this.coord = coord.northWest();
          return 7;
        }
        this.coord = coord.north();
        return 0;
      
      case 1: 
        if (val == 0) {
          this.coord = coord.north();
          return 0; }
        if (val == 1) {
          this.coord = coord.east();
          return 2;
        }
        this.coord = coord.northEast();
        return 1;
      
      case 2: 
        if (val == 0) {
          this.coord = coord.northEast();
          return 1; }
        if (val == 1) {
          this.coord = coord.southEast();
          return 3;
        }
        this.coord = coord.east();
        return 2;
      
      case 3: 
        if (val == 0) {
          this.coord = coord.east();
          return 2; }
        if (val == 1) {
          this.coord = coord.south();
          return 4;
        }
        this.coord = coord.southEast();
        return 3;
      
      case 4: 
        if (val == 0) {
          this.coord = coord.southEast();
          return 3; }
        if (val == 1) {
          this.coord = coord.southWest();
          return 5;
        }
        this.coord = coord.south();
        return 4;
      
      case 5: 
        if (val == 0) {
          this.coord = coord.south();
          return 4; }
        if (val == 1) {
          this.coord = coord.west();
          return 6;
        }
        this.coord = coord.southWest();
        return 5;
      
      case 6: 
        if (val == 0) {
          this.coord = coord.southWest();
          return 5; }
        if (val == 1) {
          this.coord = coord.northWest();
          return 7;
        }
        this.coord = coord.west();
        return 6;
      }
      
      
      if (val == 0) {
        this.coord = coord.west();
        return 6; }
      if (val == 1) {
        this.coord = coord.north();
        return 0;
      }
      this.coord = coord.northWest();
      return 7;
    }
  }
}
