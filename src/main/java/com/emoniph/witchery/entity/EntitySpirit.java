package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.entity.ai.EntityAIFlyerFlyToWaypoint;
import com.emoniph.witchery.entity.ai.EntityAIFlyerFlyToWaypoint.CarryRequirement;
import com.emoniph.witchery.entity.ai.EntityAIFlyerFollowOwner;
import com.emoniph.witchery.entity.ai.EntityAIFlyerLand;
import com.emoniph.witchery.entity.ai.EntityAIFlyingTempt;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.ParticleEffect;
import cpw.mods.fml.relauncher.ReflectionHelper;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderFlat;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraft.world.gen.ChunkProviderHell;
import net.minecraft.world.gen.ChunkProviderServer;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenVillage;

public class EntitySpirit extends EntityFlyingTameable
{
  public EntityAIFlyingTempt aiTempt;
  private int timeToLive = -1;
  private int spiritType = 0;
  
  private static final ItemStack[] TEMPTATIONS = { ItemsGENERIC.itemFocusedWill.createStack() };
  private static Field fieldStructureGenerators;
  private static Field fieldVillageGenerator;
  
  public EntitySpirit(World world) { super(world);
    func_70105_a(0.25F, 0.25F);
    func_70661_as().func_75495_e(true);
    field_70714_bg.func_75776_a(1, new com.emoniph.witchery.entity.ai.EntityAISitAndStay(this));
    


    field_70714_bg.func_75776_a(3, this.aiTempt = new EntityAIFlyingTempt(this, 0.6D, TEMPTATIONS, true));
    field_70714_bg.func_75776_a(5, new EntityAIFlyerFollowOwner(this, 1.0D, 14.0F, 5.0F));
    
    field_70714_bg.func_75776_a(8, new EntityAIFlyerFlyToWaypoint(this, EntityAIFlyerFlyToWaypoint.CarryRequirement.NONE));
    field_70714_bg.func_75776_a(9, new EntityAIFlyerLand(this, 0.8D, true));
    field_70714_bg.func_75776_a(10, new com.emoniph.witchery.entity.ai.EntityAIFlyerWander(this, 0.8D, 10.0D));
    field_70714_bg.func_75776_a(11, new net.minecraft.entity.ai.EntityAIWatchClosest(this, EntityPlayer.class, 10.0F, 0.2F));
  }
  



  protected boolean func_70692_ba()
  {
    return true;
  }
  


  public void setTarget(String target, int type)
  {
    timeToLive = com.emoniph.witchery.util.TimeUtil.secsToTicks(10);
    spiritType = type;
    try {
      if (target.equals("Village")) {
        IChunkProvider cp = field_70170_p.func_72863_F();
        while ((cp != null) && ((cp instanceof ChunkProviderServer))) {
          cp = field_73246_d;
        }
        
        if (cp != null) {
          if ((cp instanceof ChunkProviderFlat)) {
            if (fieldStructureGenerators == null) {
              fieldStructureGenerators = ReflectionHelper.findField(ChunkProviderFlat.class, new String[] { "structureGenerators", "field_82696_f", "f" });
            }
            
            Iterator iterator = ((List)fieldStructureGenerators.get((ChunkProviderFlat)cp)).iterator();
            while (iterator.hasNext()) {
              if (setWaypointTo(iterator.next(), MapGenVillage.class)) {
                return;
              }
            }
          } else if ((cp instanceof ChunkProviderGenerate)) {
            if (fieldVillageGenerator == null) {
              fieldVillageGenerator = ReflectionHelper.findField(ChunkProviderGenerate.class, new String[] { "villageGenerator", "field_73224_v", "v" });
            }
            
            if (fieldVillageGenerator != null) {
              setWaypointTo(fieldVillageGenerator.get((ChunkProviderGenerate)cp), MapGenVillage.class);
            }
          } else if ((cp instanceof ChunkProviderHell)) {
            setWaypointTo(field_73172_c);
          }
        }
      }
    }
    catch (IllegalAccessException ex) {}
  }
  
  private boolean setWaypointTo(Object objStructure, Class<? extends MapGenStructure> clazz)
  {
    if ((objStructure != null) && (clazz.isAssignableFrom(objStructure.getClass()))) {
      setWaypointTo((MapGenStructure)objStructure);
      return true;
    }
    return false;
  }
  
  private void setWaypointTo(MapGenStructure mapStructure) { if (mapStructure != null) {
      ChunkPosition pos = mapStructure.func_151545_a(field_70170_p, (int)field_70165_t, (int)field_70163_u, (int)field_70161_v);
      
      if (pos != null) {
        homeX = field_151329_a;
        homeY = field_151327_b;
        homeZ = field_151328_c;
        waypoint = ItemsGENERIC.itemWaystone.createStack();
      }
    }
  }
  
  public void func_70014_b(NBTTagCompound nbtRoot)
  {
    super.func_70014_b(nbtRoot);
    nbtRoot.func_74768_a("SuicideIn", timeToLive);
    nbtRoot.func_74768_a("SpiritType", spiritType);
  }
  
  public void func_70037_a(NBTTagCompound nbtRoot)
  {
    super.func_70037_a(nbtRoot);
    if (nbtRoot.func_74764_b("SuicideIn")) {
      timeToLive = nbtRoot.func_74762_e("SuicideIn");
    } else {
      timeToLive = -1;
    }
    
    if (nbtRoot.func_74764_b("SpiritType")) {
      spiritType = nbtRoot.func_74762_e("SpiritType");
    } else {
      spiritType = 0;
    }
  }
  
  protected void func_70629_bd()
  {
    func_70661_as().func_75499_g();
    super.func_70629_bd();
    if ((field_70170_p != null) && (!field_70128_L) && (!field_70170_p.field_72995_K) && (timeToLive != -1) && (--timeToLive == 0)) {
      ParticleEffect.EXPLODE.send(com.emoniph.witchery.util.SoundEffect.NONE, this, 1.0D, 1.0D, 16);
      func_70106_y();
      if (!field_70170_p.field_72995_K) {
        func_70628_a(false, 0);
      }
    }
  }
  
  protected void func_70088_a()
  {
    super.func_70088_a();
    field_70180_af.func_75682_a(21, Integer.valueOf(0));
  }
  
  protected int func_70682_h(int par1)
  {
    return par1;
  }
  
  protected void func_110147_ax()
  {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(4.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4D);
    func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111264_e);
    func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
  }
  
  public boolean func_70650_aV()
  {
    return true;
  }
  

  protected void func_70628_a(boolean par1, int par2)
  {
    if (spiritType == 2) return;
    ItemStack stack;
    ItemStack stack; if (spiritType == 1) {
      stack = ItemsGENERIC.itemSubduedSpiritVillage.createStack();
    } else {
      stack = ItemsGENERIC.itemSubduedSpirit.createStack();
    }
    
    func_70099_a(stack, 0.0F);
  }
  
  public void func_70636_d()
  {
    super.func_70636_d();
  }
  

  public void func_70071_h_()
  {
    super.func_70071_h_();
    if (field_70170_p.field_72995_K) {
      int color = getFeatherColor();
      float red = 1.0F;
      float green = 0.8F;
      float blue = 0.0F;
      if (color > 0) {
        red = (color >> 16 & 0xFF) / 255.0F;
        green = (color >> 8 & 0xFF) / 255.0F;
        blue = (color & 0xFF) / 255.0F;
      }
      Witchery.proxy.generateParticle(field_70170_p, field_70165_t - field_70130_N * 0.5D + field_70170_p.field_73012_v.nextDouble() * field_70130_N, 0.1D + field_70163_u + field_70170_p.field_73012_v.nextDouble() * 0.2D, field_70161_v - field_70130_N * 0.5D + field_70170_p.field_73012_v.nextDouble() * field_70130_N, red, green, blue, 10, -0.1F);
    }
  }
  


  public int func_70627_aG()
  {
    return super.func_70627_aG() * 2;
  }
  
  protected String func_70639_aQ()
  {
    return null;
  }
  
  protected String func_70621_aR()
  {
    return null;
  }
  
  protected String func_70673_aS()
  {
    return null;
  }
  
  public boolean func_70085_c(EntityPlayer par1EntityPlayer)
  {
    return false;
  }
  
  public EntitySpirit spawnBabyAnimal(EntityAgeable par1EntityAgeable) {
    return null;
  }
  
  public boolean func_70877_b(ItemStack itemstack)
  {
    return (itemstack != null) && (itemstack.func_77973_b() == net.minecraft.init.Items.field_151103_aS);
  }
  
  public boolean func_70878_b(EntityAnimal par1EntityAnimal)
  {
    return false;
  }
  
  public int getFeatherColor() {
    return field_70180_af.func_75679_c(21);
  }
  
  public void setFeatherColor(int par1) {
    field_70180_af.func_75692_b(21, Integer.valueOf(par1));
  }
  
  public boolean func_70601_bi()
  {
    if (field_70170_p.field_73011_w.field_76574_g == instancedimensionDreamID) {
      boolean superGetCanSpawnHere = (field_70170_p.func_72855_b(field_70121_D)) && (field_70170_p.func_72945_a(this, field_70121_D).isEmpty()) && (!field_70170_p.func_72953_d(field_70121_D));
      


      int i = MathHelper.func_76128_c(field_70165_t);
      int j = MathHelper.func_76128_c(field_70121_D.field_72338_b);
      int k = MathHelper.func_76128_c(field_70161_v);
      superGetCanSpawnHere = (superGetCanSpawnHere) && (func_70783_a(i, j, k) >= 0.0F) && (j >= 60);
      
      net.minecraft.block.Block blockID = field_70170_p.func_147439_a(i, j - 1, k);
      
      return (superGetCanSpawnHere) && (field_70170_p.field_73012_v.nextInt(10) == 0) && ((blockID == Blocks.field_150349_c) || (blockID == Blocks.field_150354_m)) && (field_70170_p.func_72883_k(i, j, k) > 8);
    }
    


    return false;
  }
  

  public String func_70005_c_()
  {
    if (func_94056_bM()) {
      return func_94057_bL();
    }
    return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.spirit.name");
  }
  

  public IEntityLivingData func_110161_a(IEntityLivingData par1EntityLivingData)
  {
    par1EntityLivingData = super.func_110161_a(par1EntityLivingData);
    
    return par1EntityLivingData;
  }
  
  public EntityAgeable func_90011_a(EntityAgeable par1EntityAgeable)
  {
    return null;
  }
}
