package com.emoniph.witchery.brewing.potions;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.world.World;

public class PotionSinking extends PotionBase implements IHandleLivingUpdate
{
  public PotionSinking(int id, int color)
  {
    super(id, true, color);
  }
  
  public void postContructInitialize()
  {
    setPermenant();
    setIncurable();
  }
  
  public void onLivingUpdate(World world, EntityLivingBase entity, net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent event, int amplifier, int duration)
  {
    // Byte code:
    //   0: aload_2
    //   1: instanceof 34
    //   4: ifeq +150 -> 154
    //   7: aload_2
    //   8: checkcast 34	net/minecraft/entity/player/EntityPlayer
    //   11: astore 6
    //   13: aload_1
    //   14: getfield 40	net/minecraft/world/World:field_72995_K	Z
    //   17: ifeq +134 -> 151
    //   20: aload 6
    //   22: invokevirtual 44	net/minecraft/entity/player/EntityPlayer:func_70090_H	()Z
    //   25: ifeq +85 -> 110
    //   28: aload 6
    //   30: getfield 48	net/minecraft/entity/player/EntityPlayer:field_70181_x	D
    //   33: ldc2_w 49
    //   36: dcmpg
    //   37: ifge +41 -> 78
    //   40: aload 6
    //   42: getfield 53	net/minecraft/entity/player/EntityPlayer:field_70122_E	Z
    //   45: ifne +33 -> 78
    //   48: aload 6
    //   50: dup
    //   51: getfield 48	net/minecraft/entity/player/EntityPlayer:field_70181_x	D
    //   54: ldc2_w 54
    //   57: ldc2_w 56
    //   60: iload 4
    //   62: i2d
    //   63: dmul
    //   64: ldc2_w 58
    //   67: invokestatic 65	java/lang/Math:min	(DD)D
    //   70: dadd
    //   71: dmul
    //   72: putfield 48	net/minecraft/entity/player/EntityPlayer:field_70181_x	D
    //   75: goto +76 -> 151
    //   78: aload 6
    //   80: getfield 53	net/minecraft/entity/player/EntityPlayer:field_70122_E	Z
    //   83: ifne +68 -> 151
    //   86: aload 6
    //   88: getstatic 71	net/minecraft/block/material/Material:field_151586_h	Lnet/minecraft/block/material/Material;
    //   91: invokevirtual 75	net/minecraft/entity/player/EntityPlayer:func_70055_a	(Lnet/minecraft/block/material/Material;)Z
    //   94: ifeq +57 -> 151
    //   97: aload 6
    //   99: getfield 48	net/minecraft/entity/player/EntityPlayer:field_70181_x	D
    //   102: dconst_0
    //   103: dcmpl
    //   104: ifle +47 -> 151
    //   107: goto +44 -> 151
    //   110: aload 6
    //   112: getfield 79	net/minecraft/entity/player/EntityPlayer:field_71075_bZ	Lnet/minecraft/entity/player/PlayerCapabilities;
    //   115: getfield 84	net/minecraft/entity/player/PlayerCapabilities:field_75098_d	Z
    //   118: ifne +33 -> 151
    //   121: aload 6
    //   123: getfield 79	net/minecraft/entity/player/EntityPlayer:field_71075_bZ	Lnet/minecraft/entity/player/PlayerCapabilities;
    //   126: getfield 87	net/minecraft/entity/player/PlayerCapabilities:field_75101_c	Z
    //   129: ifeq +22 -> 151
    //   132: aload 6
    //   134: getfield 79	net/minecraft/entity/player/EntityPlayer:field_71075_bZ	Lnet/minecraft/entity/player/PlayerCapabilities;
    //   137: getfield 90	net/minecraft/entity/player/PlayerCapabilities:field_75100_b	Z
    //   140: ifeq +11 -> 151
    //   143: aload 6
    //   145: ldc2_w 91
    //   148: putfield 48	net/minecraft/entity/player/EntityPlayer:field_70181_x	D
    //   151: goto +105 -> 256
    //   154: aload_1
    //   155: getfield 40	net/minecraft/world/World:field_72995_K	Z
    //   158: ifeq +98 -> 256
    //   161: aload_1
    //   162: invokevirtual 96	net/minecraft/world/World:func_82737_E	()J
    //   165: ldc2_w 97
    //   168: lrem
    //   169: ldc2_w 99
    //   172: lcmp
    //   173: ifne +83 -> 256
    //   176: aload_2
    //   177: invokevirtual 103	net/minecraft/entity/EntityLivingBase:func_70090_H	()Z
    //   180: ifeq +76 -> 256
    //   183: aload_2
    //   184: getfield 104	net/minecraft/entity/EntityLivingBase:field_70181_x	D
    //   187: dconst_0
    //   188: dcmpg
    //   189: ifge +32 -> 221
    //   192: aload_2
    //   193: dup
    //   194: getfield 104	net/minecraft/entity/EntityLivingBase:field_70181_x	D
    //   197: dconst_1
    //   198: ldc2_w 105
    //   201: iload 4
    //   203: iconst_2
    //   204: iadd
    //   205: i2d
    //   206: dmul
    //   207: ldc2_w 107
    //   210: invokestatic 65	java/lang/Math:min	(DD)D
    //   213: dadd
    //   214: dmul
    //   215: putfield 104	net/minecraft/entity/EntityLivingBase:field_70181_x	D
    //   218: goto +38 -> 256
    //   221: aload_2
    //   222: getfield 104	net/minecraft/entity/EntityLivingBase:field_70181_x	D
    //   225: dconst_0
    //   226: dcmpl
    //   227: ifle +29 -> 256
    //   230: aload_2
    //   231: dup
    //   232: getfield 104	net/minecraft/entity/EntityLivingBase:field_70181_x	D
    //   235: dconst_1
    //   236: ldc2_w 105
    //   239: iload 4
    //   241: iconst_2
    //   242: iadd
    //   243: i2d
    //   244: dmul
    //   245: ldc2_w 107
    //   248: invokestatic 65	java/lang/Math:min	(DD)D
    //   251: dsub
    //   252: dmul
    //   253: putfield 104	net/minecraft/entity/EntityLivingBase:field_70181_x	D
    //   256: return
    // Line number table:
    //   Java source line #23	-> byte code offset #0
    //   Java source line #24	-> byte code offset #7
    //   Java source line #25	-> byte code offset #13
    //   Java source line #26	-> byte code offset #20
    //   Java source line #27	-> byte code offset #28
    //   Java source line #28	-> byte code offset #48
    //   Java source line #29	-> byte code offset #78
    //   Java source line #33	-> byte code offset #110
    //   Java source line #35	-> byte code offset #143
    //   Java source line #39	-> byte code offset #151
    //   Java source line #40	-> byte code offset #154
    //   Java source line #41	-> byte code offset #176
    //   Java source line #42	-> byte code offset #183
    //   Java source line #43	-> byte code offset #192
    //   Java source line #44	-> byte code offset #221
    //   Java source line #45	-> byte code offset #230
    //   Java source line #50	-> byte code offset #256
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	257	0	this	PotionSinking
    //   0	257	1	world	World
    //   0	257	2	entity	EntityLivingBase
    //   0	257	3	event	net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent
    //   0	257	4	amplifier	int
    //   0	257	5	duration	int
    //   11	133	6	player	EntityPlayer
  }
}
