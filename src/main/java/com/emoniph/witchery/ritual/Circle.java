package com.emoniph.witchery.ritual;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class Circle
{
  int numRitualGlyphs;
  int numOtherwhereGlyphs;
  int numInfernalGlyphs;
  final int requiredGlyphs;
  
  public Circle(int requiredGlyphs)
  {
    this.requiredGlyphs = requiredGlyphs;
  }
  
  public Circle(int numRitualGlyphs, int numOtherwhereGlyphs, int numInfernalGlyphs) {
    requiredGlyphs = (numRitualGlyphs + numOtherwhereGlyphs + numInfernalGlyphs);
    this.numRitualGlyphs = numRitualGlyphs;
    this.numOtherwhereGlyphs = numOtherwhereGlyphs;
    this.numInfernalGlyphs = numInfernalGlyphs;
  }
  
  public void addGlyph(World world, int posX, int posY, int posZ) {
    addGlyph(world, posX, posY, posZ, false);
  }
  
  public void addGlyph(World world, int posX, int posY, int posZ, boolean remove) {
    if (requiredGlyphs > 0) {
      Block blockID = world.func_147439_a(posX, posY, posZ);
      boolean found = false;
      if (BlocksGLYPH_RITUAL == blockID) {
        numRitualGlyphs += 1;
        found = true;
      } else if (BlocksGLYPH_OTHERWHERE == blockID) {
        numOtherwhereGlyphs += 1;
        found = true;
      } else if (BlocksGLYPH_INFERNAL == blockID) {
        numInfernalGlyphs += 1;
        found = true;
      }
      
      if ((remove) && (found)) {
        world.func_147468_f(posX, posY, posZ);
      }
    }
  }
  
  public void removeIfRequired(ArrayList<Circle> circlesToFind) {
    if (isComplete()) {
      for (int i = 0; i < circlesToFind.size(); i++) {
        if (isMatch((Circle)circlesToFind.get(i))) {
          circlesToFind.remove(i);
          return;
        }
      }
    }
  }
  
  private boolean isMatch(Circle other) {
    return (numRitualGlyphs == numRitualGlyphs) && (numOtherwhereGlyphs == numOtherwhereGlyphs) && (numInfernalGlyphs == numInfernalGlyphs);
  }
  
  public boolean isComplete()
  {
    return requiredGlyphs == getGlyphCount();
  }
  
  private int getGlyphCount() {
    return numRitualGlyphs + numOtherwhereGlyphs + numInfernalGlyphs;
  }
  
  public int getRadius() {
    return (requiredGlyphs + 2) / 6 + 1;
  }
  
  public int getExclusiveMetadataValue() {
    if (numRitualGlyphs == requiredGlyphs)
      return 1;
    if (numOtherwhereGlyphs == requiredGlyphs)
      return 2;
    if (numInfernalGlyphs == requiredGlyphs) {
      return 3;
    }
    return 0;
  }
  
  public int getTextureIndex()
  {
    int size = getGlyphCount();
    if (size == 40)
      return getExclusiveMetadataValue() - 1;
    if (size == 28) {
      return getExclusiveMetadataValue() + 3 - 1;
    }
    return getExclusiveMetadataValue() + 6 - 1;
  }
}
