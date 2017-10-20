package com.emoniph.witchery.worldgen;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;




public class WorldGenLargeWitchTree
  extends WorldGenerator
{
  static final byte[] otherCoordPairs = { 2, 0, 0, 1, 2, 1 };
  

  Random rand = new Random();
  
  World worldObj;
  
  int[] basePos = { 0, 0, 0 };
  int heightLimit;
  int height;
  double heightAttenuation = 0.618D;
  double branchDensity = 1.0D;
  double branchSlope = 0.381D;
  double scaleWidth = 1.0D;
  double leafDensity = 1.0D;
  




  int trunkSize = 1;
  



  int heightLimitLimit = 12;
  




  int leafDistanceLimit = 4;
  
  int[][] leafNodes;
  
  final int leafMeta;
  final int logMeta;
  
  public WorldGenLargeWitchTree(boolean par1, int logMeta, int leafMeta)
  {
    this(par1, logMeta, leafMeta, 0.618D, 0.381D);
  }
  
  public WorldGenLargeWitchTree(boolean par1, int logMeta, int leafMeta, double branchSlope) {
    this(par1, logMeta, leafMeta, 0.618D, branchSlope);
  }
  
  public WorldGenLargeWitchTree(boolean par1, int logMeta, int leafMeta, double attenuation, double branchSlope) {
    super(par1);
    this.logMeta = logMeta;
    this.leafMeta = leafMeta;
    heightAttenuation = attenuation;
    this.branchSlope = branchSlope;
  }
  



  void generateLeafNodeList()
  {
    height = ((int)(heightLimit * heightAttenuation));
    
    if (height >= heightLimit) {
      height = (heightLimit - 1);
    }
    
    int i = (int)(1.382D + Math.pow(leafDensity * heightLimit / 13.0D, 2.0D));
    
    if (i < 1) {
      i = 1;
    }
    
    int[][] aint = new int[i * heightLimit][4];
    int j = basePos[1] + heightLimit - leafDistanceLimit;
    int k = 1;
    int l = basePos[1] + height;
    int i1 = j - basePos[1];
    aint[0][0] = basePos[0];
    aint[0][1] = j;
    aint[0][2] = basePos[2];
    aint[0][3] = l;
    j--;
    
    while (i1 >= 0) {
      int j1 = 0;
      float f = layerSize(i1);
      
      if (f < 0.0F) {
        j--;
        i1--;
      } else {
        for (double d0 = 0.5D; j1 < i; j1++) {
          double d1 = scaleWidth * f * (rand.nextFloat() + 0.328D);
          double d2 = rand.nextFloat() * 2.0D * 3.141592653589793D;
          int k1 = MathHelper.func_76128_c(d1 * Math.sin(d2) + basePos[0] + d0);
          int l1 = MathHelper.func_76128_c(d1 * Math.cos(d2) + basePos[2] + d0);
          int[] aint1 = { k1, j, l1 };
          int[] aint2 = { k1, j + leafDistanceLimit, l1 };
          
          if (checkBlockLine(aint1, aint2) == -1) {
            int[] aint3 = { basePos[0], basePos[1], basePos[2] };
            double d3 = Math.sqrt(Math.pow(Math.abs(basePos[0] - aint1[0]), 2.0D) + Math.pow(Math.abs(basePos[2] - aint1[2]), 2.0D));
            
            double d4 = d3 * branchSlope;
            
            if (aint1[1] - d4 > l) {
              aint3[1] = l;
            } else {
              aint3[1] = ((int)(aint1[1] - d4));
            }
            
            if (checkBlockLine(aint3, aint1) == -1) {
              aint[k][0] = k1;
              aint[k][1] = j;
              aint[k][2] = l1;
              aint[k][3] = aint3[1];
              k++;
            }
          }
        }
        
        j--;
        i1--;
      }
    }
    
    leafNodes = new int[k][4];
    System.arraycopy(aint, 0, leafNodes, 0, k);
  }
  
  void genTreeLayer(int par1, int par2, int par3, float par4, byte par5, Block blockID, int meta) {
    int i1 = (int)(par4 + 0.618D);
    byte b1 = otherCoordPairs[par5];
    byte b2 = otherCoordPairs[(par5 + 3)];
    int[] aint = { par1, par2, par3 };
    int[] aint1 = { 0, 0, 0 };
    int j1 = -i1;
    int k1 = -i1;
    
    for (aint1[par5] = aint[par5]; j1 <= i1; j1++) {
      aint[b1] += j1;
      k1 = -i1;
      
      while (k1 <= i1) {
        double d0 = Math.pow(Math.abs(j1) + 0.5D, 2.0D) + Math.pow(Math.abs(k1) + 0.5D, 2.0D);
        
        if (d0 > par4 * par4) {
          k1++;
        } else {
          aint[b2] += k1;
          Block l1 = worldObj.func_147439_a(aint1[0], aint1[1], aint1[2]);
          
          if ((l1 != Blocks.field_150350_a) && (l1 != BlocksLEAVES)) {
            k1++;
          } else {
            func_150516_a(worldObj, aint1[0], aint1[1], aint1[2], blockID, meta);
            k1++;
          }
        }
      }
    }
  }
  


  float layerSize(int par1)
  {
    if (par1 < heightLimit * 0.3D) {
      return -1.618F;
    }
    float f = heightLimit / 2.0F;
    float f1 = heightLimit / 2.0F - par1;
    float f2;
    float f2;
    if (f1 == 0.0F) {
      f2 = f; } else { float f2;
      if (Math.abs(f1) >= f) {
        f2 = 0.0F;
      } else {
        f2 = (float)Math.sqrt(Math.pow(Math.abs(f), 2.0D) - Math.pow(Math.abs(f1), 2.0D));
      }
    }
    f2 *= 0.5F;
    return f2;
  }
  
  float leafSize(int par1)
  {
    return (par1 >= 0) && (par1 < leafDistanceLimit) ? 2.0F : (par1 != 0) && (par1 != leafDistanceLimit - 1) ? 3.0F : -1.0F;
  }
  



  void generateLeafNode(int par1, int par2, int par3)
  {
    int l = par2;
    
    for (int i1 = par2 + leafDistanceLimit; l < i1; l++) {
      float f = leafSize(l - par2);
      genTreeLayer(par1, l, par3, f, (byte)1, BlocksLEAVES, leafMeta);
    }
  }
  



  void placeBlockLine(int[] par1ArrayOfInteger, int[] par2ArrayOfInteger, Block blockId, int meta)
  {
    int[] aint2 = { 0, 0, 0 };
    byte b0 = 0;
    

    for (byte b1 = 0; b0 < 3; b0 = (byte)(b0 + 1)) {
      par2ArrayOfInteger[b0] -= par1ArrayOfInteger[b0];
      
      if (Math.abs(aint2[b0]) > Math.abs(aint2[b1])) {
        b1 = b0;
      }
    }
    
    if (aint2[b1] != 0) {
      byte b2 = otherCoordPairs[b1];
      byte b3 = otherCoordPairs[(b1 + 3)];
      byte b4;
      byte b4;
      if (aint2[b1] > 0) {
        b4 = 1;
      } else {
        b4 = -1;
      }
      
      double d0 = aint2[b2] / aint2[b1];
      double d1 = aint2[b3] / aint2[b1];
      int[] aint3 = { 0, 0, 0 };
      int j = 0;
      
      for (int k = aint2[b1] + b4; j != k; j += b4) {
        aint3[b1] = MathHelper.func_76128_c(par1ArrayOfInteger[b1] + j + 0.5D);
        aint3[b2] = MathHelper.func_76128_c(par1ArrayOfInteger[b2] + j * d0 + 0.5D);
        aint3[b3] = MathHelper.func_76128_c(par1ArrayOfInteger[b3] + j * d1 + 0.5D);
        byte b5 = 0;
        int l = Math.abs(aint3[0] - par1ArrayOfInteger[0]);
        int i1 = Math.abs(aint3[2] - par1ArrayOfInteger[2]);
        int j1 = Math.max(l, i1);
        
        if (j1 > 0) {
          if (l == j1) {
            b5 = 4;
          } else if (i1 == j1) {
            b5 = 8;
          }
        }
        
        func_150516_a(worldObj, aint3[0], aint3[1], aint3[2], blockId, meta);
      }
    }
  }
  



  void generateLeaves()
  {
    int i = 0;
    
    for (int j = leafNodes.length; i < j; i++) {
      int k = leafNodes[i][0];
      int l = leafNodes[i][1];
      int i1 = leafNodes[i][2];
      generateLeafNode(k, l, i1);
    }
  }
  



  boolean leafNodeNeedsBase(int par1)
  {
    return par1 >= heightLimit * 0.2D;
  }
  



  void generateTrunk()
  {
    int i = basePos[0];
    int j = basePos[1];
    int k = basePos[1] + height;
    int l = basePos[2];
    int[] aint = { i, j, l };
    int[] aint1 = { i, k, l };
    placeBlockLine(aint, aint1, BlocksLOG, logMeta);
    
    if (trunkSize == 2) {
      aint[0] += 1;
      aint1[0] += 1;
      placeBlockLine(aint, aint1, BlocksLOG, logMeta);
      aint[2] += 1;
      aint1[2] += 1;
      placeBlockLine(aint, aint1, BlocksLOG, logMeta);
      aint[0] += -1;
      aint1[0] += -1;
      placeBlockLine(aint, aint1, BlocksLOG, logMeta);
    }
  }
  



  void generateLeafNodeBases()
  {
    int i = 0;
    int j = leafNodes.length;
    
    for (int[] aint = { basePos[0], basePos[1], basePos[2] }; i < j; i++) {
      int[] aint1 = leafNodes[i];
      int[] aint2 = { aint1[0], aint1[1], aint1[2] };
      aint[1] = aint1[3];
      int k = aint[1] - basePos[1];
      
      if (leafNodeNeedsBase(k)) {
        placeBlockLine(aint, aint2, BlocksLOG, logMeta);
      }
    }
  }
  




  int checkBlockLine(int[] par1ArrayOfInteger, int[] par2ArrayOfInteger)
  {
    int[] aint2 = { 0, 0, 0 };
    byte b0 = 0;
    

    for (byte b1 = 0; b0 < 3; b0 = (byte)(b0 + 1)) {
      par2ArrayOfInteger[b0] -= par1ArrayOfInteger[b0];
      
      if (Math.abs(aint2[b0]) > Math.abs(aint2[b1])) {
        b1 = b0;
      }
    }
    
    if (aint2[b1] == 0) {
      return -1;
    }
    byte b2 = otherCoordPairs[b1];
    byte b3 = otherCoordPairs[(b1 + 3)];
    byte b4;
    byte b4;
    if (aint2[b1] > 0) {
      b4 = 1;
    } else {
      b4 = -1;
    }
    
    double d0 = aint2[b2] / aint2[b1];
    double d1 = aint2[b3] / aint2[b1];
    int[] aint3 = { 0, 0, 0 };
    int i = 0;
    

    for (int j = aint2[b1] + b4; i != j; i += b4) {
      par1ArrayOfInteger[b1] += i;
      aint3[b2] = MathHelper.func_76128_c(par1ArrayOfInteger[b2] + i * d0);
      aint3[b3] = MathHelper.func_76128_c(par1ArrayOfInteger[b3] + i * d1);
      Block k = worldObj.func_147439_a(aint3[0], aint3[1], aint3[2]);
      
      if ((k != Blocks.field_150350_a) && (k != BlocksLEAVES)) {
        break;
      }
    }
    
    return i == j ? -1 : Math.abs(i);
  }
  




  boolean validTreeLocation()
  {
    int[] aint = { basePos[0], basePos[1], basePos[2] };
    int[] aint1 = { basePos[0], basePos[1] + heightLimit - 1, basePos[2] };
    Block i = worldObj.func_147439_a(basePos[0], basePos[1] - 1, basePos[2]);
    
    Block soil = i;
    boolean isValidSoil = (soil != null) && (soil.canSustainPlant(worldObj, basePos[0], basePos[1] - 1, basePos[2], ForgeDirection.UP, (IPlantable)BlocksSAPLING));
    
    if (!isValidSoil) {
      return false;
    }
    int j = checkBlockLine(aint, aint1);
    
    if (j == -1)
      return true;
    if (j < 6) {
      return false;
    }
    heightLimit = j;
    return true;
  }
  





  public void func_76487_a(double heightFactor, double widthFactor, double leafDensity)
  {
    heightLimitLimit = ((int)(heightFactor * 12.0D));
    
    if (height > 0.5D) {
      leafDistanceLimit = 5;
    }
    
    scaleWidth = widthFactor;
    this.leafDensity = leafDensity;
  }
  
  public boolean func_76484_a(World par1World, Random par2Random, int par3, int par4, int par5)
  {
    worldObj = par1World;
    long l = par2Random.nextLong();
    rand.setSeed(l);
    basePos[0] = par3;
    basePos[1] = par4;
    basePos[2] = par5;
    
    if (heightLimit == 0) {
      heightLimit = (5 + rand.nextInt(heightLimitLimit));
    }
    
    if (!validTreeLocation()) {
      return false;
    }
    generateLeafNodeList();
    generateLeaves();
    generateTrunk();
    generateLeafNodeBases();
    return true;
  }
}
