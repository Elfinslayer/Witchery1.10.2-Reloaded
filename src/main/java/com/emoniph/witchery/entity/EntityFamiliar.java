package com.emoniph.witchery.entity;

import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.entity.ai.EntityAIFamiliarFindDiamonds;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TameableUtil;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityFamiliar extends EntityOcelot
{
  private int searches = 0;
  private static final int WATCH_KEY_OBJ_TO_FIND = 23;
  
  public EntityFamiliar(World world) { super(world);
    func_70105_a(0.8F, 0.8F);
    func_70661_as().func_75491_a(true);
    field_70714_bg.field_75782_a.clear();
    field_70714_bg.func_75776_a(1, new net.minecraft.entity.ai.EntityAISwimming(this));
    field_70714_bg.func_75776_a(2, field_70911_d);
    field_70714_bg.func_75776_a(3, new net.minecraft.entity.ai.EntityAIFollowOwner(this, 1.0D, 10.0F, 5.0F));
    field_70714_bg.func_75776_a(4, new EntityAIFamiliarFindDiamonds(this, 1.33D));
    field_70714_bg.func_75776_a(5, new net.minecraft.entity.ai.EntityAILeapAtTarget(this, 0.3F));
    field_70714_bg.func_75776_a(6, new net.minecraft.entity.ai.EntityAIOcelotAttack(this));
    field_70714_bg.func_75776_a(7, new EntityAIWander(this, 0.8D));
    field_70714_bg.func_75776_a(8, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
  }
  


  protected void func_70088_a()
  {
    super.func_70088_a();
    func_70912_b(1);
    field_70180_af.func_75682_a(23, Integer.valueOf(-1));
  }
  
  public String func_70005_c_()
  {
    if (func_94056_bM()) {
      return func_94057_bL();
    }
    return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.familiar.name");
  }
  
  public void setItemIDToFind(int itemID)
  {
    field_70180_af.func_75692_b(23, Integer.valueOf(itemID));
  }
  
  public int getItemIDToFind() {
    return field_70180_af.func_75679_c(23);
  }
  
  public void func_70014_b(NBTTagCompound nbtTag)
  {
    super.func_70014_b(nbtTag);
    nbtTag.func_74768_a("ItemToFind", getItemIDToFind());
    nbtTag.func_74768_a("Searches", searches);
  }
  
  public void func_70037_a(NBTTagCompound nbtTag)
  {
    super.func_70037_a(nbtTag);
    setItemIDToFind(nbtTag.func_74762_e("ItemToFind"));
    searches = nbtTag.func_74762_e("Searches");
  }
  
  @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
  public float func_70053_R()
  {
    super.func_70053_R();
    return 0.0F;
  }
  
  protected void func_110147_ax()
  {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(40.0D);
  }
  
  public boolean func_70652_k(Entity par1Entity)
  {
    return par1Entity.func_70097_a(DamageSource.func_76358_a(this), 0.5F);
  }
  
  public boolean func_70085_c(EntityPlayer player)
  {
    if ((func_70909_n()) && 
      (TameableUtil.isOwner(this, player)) && (!field_70170_p.field_72995_K)) {
      ItemStack item = player.func_71045_bC();
      int itemToFind = hasOre(item);
      if (itemToFind != -1) {
        setItemIDToFind(itemToFind);
        searches += 1;
        field_77994_a -= 1;
        if (field_77994_a <= 0) {
          field_71071_by.func_70299_a(field_71071_by.field_70461_c, null);
        }
        
        double[] probs = { 0.0D, 0.6D, 0.75D, 0.85D, 0.95D };
        
        double chance = field_70170_p.field_73012_v.nextDouble();
        if ((searches > 5) || ((searches > 1) && (chance < probs[Math.max(searches - 1, 1)]))) {
          ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, this, 1.0D, 1.0D, 16);
          func_70106_y();
        } else {
          SoundEffect.RANDOM_ORB.playAtPlayer(field_70170_p, player);
        }
      }
      else {
        field_70911_d.func_75270_a(!func_70906_o());
      }
    }
    

    return true;
  }
  
  public Block getBlockIDToFind() {
    int idToFind = getItemIDToFind();
    if (idToFind != -1) {
      return ORES[idToFind];
    }
    return null;
  }
  
  public void clearItemToFind()
  {
    setItemIDToFind(-1);
  }
  
  private static final Item[] ITEMS = { Items.field_151045_i, Items.field_151166_bC, Items.field_151043_k, Items.field_151042_j, Items.field_151137_ax, Items.field_151100_aR, Items.field_151044_h };
  

  private static final Block[] ORES = { Blocks.field_150482_ag, Blocks.field_150412_bA, Blocks.field_150352_o, Blocks.field_150366_p, Blocks.field_150450_ax, Blocks.field_150369_x, Blocks.field_150365_q };
  

  private static final Integer[] ORE_DEPTH = { Integer.valueOf(14), Integer.valueOf(14), Integer.valueOf(30), Integer.valueOf(64), Integer.valueOf(16), Integer.valueOf(30), Integer.valueOf(64) };
  
  public int getDepthToFind() {
    int idToFind = getItemIDToFind();
    if (idToFind != -1) {
      return ORE_DEPTH[idToFind].intValue();
    }
    return 1;
  }
  
  private int hasOre(ItemStack item)
  {
    if (item == null) {
      return -1;
    }
    return Arrays.asList(ITEMS).indexOf(item.func_77973_b());
  }
  
  protected String func_70639_aQ()
  {
    return "mob.pig.say";
  }
  
  protected String func_70621_aR()
  {
    return "mob.pig.say";
  }
  
  protected String func_70673_aS()
  {
    return "mob.pig.death";
  }
  
  protected void func_145780_a(int par1, int par2, int par3, Block par4)
  {
    func_85030_a("mob.pig.step", 0.15F, 1.0F);
  }
  
  protected float func_70599_aP()
  {
    return 0.4F;
  }
  
  protected void func_70628_a(boolean par1, int par2)
  {
    func_70099_a(ItemsGENERIC.itemSpectralDust.createStack(), 0.0F);
  }
  





  public boolean func_70877_b(ItemStack par1ItemStack)
  {
    return false;
  }
  
  public boolean func_70878_b(EntityAnimal par1EntityAnimal)
  {
    return false;
  }
  
  public EntityOcelot func_90011_a(EntityAgeable par1EntityAgeable)
  {
    return null;
  }
  
  public IEntityLivingData func_110161_a(IEntityLivingData data)
  {
    func_110148_a(SharedMonsterAttributes.field_111265_b).func_111121_a(new AttributeModifier("Random spawn bonus", field_70146_Z.nextGaussian() * 0.05D, 1));
    
    return data;
  }
  
  public void setAISitting(boolean b) {
    func_70904_g(true);
    field_70911_d.func_75270_a(true);
  }
}
