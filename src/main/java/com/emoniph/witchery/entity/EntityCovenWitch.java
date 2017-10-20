package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
import com.emoniph.witchery.entity.ai.EntityAIMoveToRestrictionAndSit;
import com.emoniph.witchery.familiar.Familiar;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import com.emoniph.witchery.network.PacketPipeline;
import com.emoniph.witchery.network.PacketSound;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.ritual.RitualStep.Result;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TameableUtil;
import com.emoniph.witchery.util.TargetPointUtil;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class EntityCovenWitch extends EntityTameable implements IRangedAttackMob
{
  private static final UUID field_110184_bp = UUID.fromString("DA2E2747-8768-4F9A-9135-258E93B077A4");
  private static final AttributeModifier field_110185_bq = new AttributeModifier(field_110184_bp, "Drinking speed penalty", -0.25D, 0).func_111168_a(false);
  
  private static final Item[] witchDrops = { Items.field_151114_aO, Items.field_151102_aT, Items.field_151137_ax, Items.field_151070_bp, Items.field_151069_bo, Items.field_151016_H, Items.field_151055_y, Items.field_151055_y };
  

  private int witchAttackTimer;
  
  private String questOfferedTo = "";
  private boolean questAccepted = false;
  private int questType = 0;
  private int questItemsNeeded = 0;
  private int timeToLive = -1;
  public static final String COVEN_KEY = "WITCCoven";
  
  public EntityCovenWitch(World world) { super(world);
    func_70105_a(0.6F, 1.95F);
    func_70661_as().func_75491_a(true);
    func_70661_as().func_75495_e(true);
    field_70714_bg.func_75776_a(1, new net.minecraft.entity.ai.EntityAISwimming(this));
    field_70714_bg.func_75776_a(1, field_70911_d);
    field_70714_bg.func_75776_a(2, new EntityAIMoveToRestrictionAndSit(this, 0.6D));
    field_70714_bg.func_75776_a(2, new EntityAIArrowAttack(this, 1.0D, 60, 10.0F));
    

    field_70714_bg.func_75776_a(4, new EntityAIMoveIndoors(this));
    field_70714_bg.func_75776_a(5, new EntityAIRestrictOpenDoor(this));
    field_70714_bg.func_75776_a(6, new EntityAIOpenDoor(this, true));
    field_70714_bg.func_75776_a(7, new EntityAIMoveTowardsRestriction(this, 0.6D));
    field_70714_bg.func_75776_a(8, new net.minecraft.entity.ai.EntityAIWander(this, 1.0D));
    field_70714_bg.func_75776_a(9, new net.minecraft.entity.ai.EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
    field_70714_bg.func_75776_a(10, new net.minecraft.entity.ai.EntityAILookIdle(this));
    field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, false));
    field_70715_bh.func_75776_a(2, new EntityAIOwnerHurtTarget(this));
    field_70715_bh.func_75776_a(3, new EntityAIHurtByTarget(this, true));
    
    setTameSkin(field_70146_Z.nextInt(5));
    func_70903_f(false);
  }
  

  public boolean func_70601_bi()
  {
    boolean living = (field_70170_p.func_72855_b(field_70121_D)) && (field_70170_p.func_72945_a(this, field_70121_D).isEmpty()) && (!field_70170_p.func_72953_d(field_70121_D));
    

    int i = MathHelper.func_76128_c(field_70165_t);
    int j = MathHelper.func_76128_c(field_70121_D.field_72338_b);
    int k = MathHelper.func_76128_c(field_70161_v);
    boolean creature = (living) && (func_70783_a(i, j, k) >= 0.0F) && ((instancecovenWitchSpawnWeight == 1) || (field_70170_p.field_73012_v.nextInt(instancecovenWitchSpawnWeight) == 0));
    






    return creature;
  }
  

  public void func_94058_c(String par1Str) {}
  

  private void setInnerCustomNameTag(String s)
  {
    field_70180_af.func_75692_b(10, s);
  }
  
  public IEntityLivingData func_110161_a(IEntityLivingData par1EntityLivingData)
  {
    setTameSkin(field_70170_p.field_73012_v.nextInt(5));
    return super.func_110161_a(par1EntityLivingData);
  }
  
  protected void func_70088_a()
  {
    super.func_70088_a();
    func_70096_w().func_75682_a(18, Byte.valueOf((byte)0));
    func_70096_w().func_75682_a(21, Byte.valueOf((byte)0));
  }
  
  protected void func_110147_ax()
  {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(30.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25D);
  }
  
  public void func_70014_b(NBTTagCompound nbtRoot) {
    super.func_70014_b(nbtRoot);
    nbtRoot.func_74768_a("SkinType", getTameSkin());
    nbtRoot.func_74778_a("QuestOffered", questOfferedTo);
    nbtRoot.func_74757_a("QuestAccepted", questAccepted);
    nbtRoot.func_74768_a("QuestType", questType);
    nbtRoot.func_74768_a("QuestItemsNeeded", questItemsNeeded);
    nbtRoot.func_74768_a("SuicideIn", timeToLive);
  }
  
  public void func_70037_a(NBTTagCompound nbtRoot) {
    super.func_70037_a(nbtRoot);
    setTameSkin(nbtRoot.func_74762_e("SkinType"));
    questOfferedTo = nbtRoot.func_74779_i("QuestOffered");
    questAccepted = nbtRoot.func_74767_n("QuestAccepted");
    questType = nbtRoot.func_74762_e("QuestType");
    questItemsNeeded = nbtRoot.func_74762_e("QuestItemsNeeded");
    if (nbtRoot.func_74764_b("SuicideIn")) {
      timeToLive = nbtRoot.func_74762_e("SuicideIn");
    } else {
      timeToLive = -1;
    }
    if ((nbtRoot.func_74764_b("CustomName")) && (nbtRoot.func_74779_i("CustomName").length() > 0)) {
      setInnerCustomNameTag(nbtRoot.func_74779_i("CustomName"));
    }
  }
  
  public String func_70005_c_()
  {
    if (func_94056_bM()) {
      return func_94057_bL();
    }
    return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.covenwitch.name");
  }
  
  public void setTimeToLive(int i)
  {
    timeToLive = i;
  }
  

  public void func_70629_bd()
  {
    super.func_70629_bd();
    if ((field_70170_p != null) && (!field_70128_L) && (!field_70170_p.field_72995_K) && (timeToLive != -1)) {
      if (timeToLive > 0) {
        timeToLive -= 1;
      }
      if ((func_70638_az() == null) && (timeToLive == 0)) {
        ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, this, 1.0D, 1.0D, 16);
        func_70106_y();
      }
    }
  }
  


  private static final int MAX_COVEN_SIZE = 6;
  

  public void func_70645_a(DamageSource damageSource)
  {
    if ((!field_70170_p.field_72995_K) && (func_70909_n())) {
      Entity owner = func_70902_q();
      if ((owner != null) && ((owner instanceof EntityPlayer))) {
        EntityPlayer player = (EntityPlayer)owner;
        NBTTagCompound nbtPlayer = Infusion.getNBT(player);
        if (nbtPlayer.func_74764_b("WITCCoven")) {
          NBTTagList nbtCovenList = nbtPlayer.func_150295_c("WITCCoven", 10);
          for (int i = 0; i < nbtCovenList.func_74745_c(); i++) {
            NBTTagCompound nbtWitch = nbtCovenList.func_150305_b(i);
            if (nbtWitch.func_74779_i("WitchName").equalsIgnoreCase(func_94057_bL())) {
              nbtCovenList.func_74744_a(i);
              break;
            }
          }
        }
      }
    }
    super.func_70645_a(damageSource);
  }
  
  public int getTameSkin() {
    return field_70180_af.func_75683_a(18);
  }
  
  public void setTameSkin(int par1) {
    field_70180_af.func_75692_b(18, Byte.valueOf((byte)par1));
  }
  
  protected String func_70639_aQ()
  {
    return null;
  }
  
  protected String func_70621_aR()
  {
    return "mob.witch.hurt";
  }
  
  protected String func_70673_aS()
  {
    return "mob.witch.death";
  }
  
  public void setAggressive(boolean aggressive) {
    byte b0 = field_70180_af.func_75683_a(21);
    
    if (aggressive) {
      b0 = (byte)(b0 | 0x1);
    } else {
      b0 = (byte)(b0 & 0xFFFFFFFE);
    }
    
    field_70180_af.func_75692_b(21, Byte.valueOf(b0));
  }
  
  public boolean getAggressive() {
    return (field_70180_af.func_75683_a(21) & 0x1) != 0;
  }
  
  public void setQuestOffered(boolean aggressive) {
    byte b0 = field_70180_af.func_75683_a(21);
    
    if (aggressive) {
      b0 = (byte)(b0 | 0x4);
    } else {
      b0 = (byte)(b0 & 0xFFFFFFFB);
    }
    
    field_70180_af.func_75692_b(21, Byte.valueOf(b0));
  }
  
  public boolean isQuestOffered() {
    return (field_70180_af.func_75683_a(21) & 0x2) != 0;
  }
  
  public boolean func_70650_aV()
  {
    return true;
  }
  
  public void func_70636_d()
  {
    if (!field_70170_p.field_72995_K) {
      if (getAggressive()) {
        if (witchAttackTimer-- <= 0) {
          setAggressive(false);
          ItemStack itemstack = func_70694_bm();
          func_70062_b(0, (ItemStack)null);
          
          if ((itemstack != null) && (itemstack.func_77973_b() == Items.field_151068_bn)) {
            List list = Items.field_151068_bn.func_77832_l(itemstack);
            
            if (list != null) {
              Iterator iterator = list.iterator();
              
              while (iterator.hasNext()) {
                PotionEffect potioneffect = (PotionEffect)iterator.next();
                func_70690_d(new PotionEffect(potioneffect));
              }
            }
          }
          
          func_110148_a(SharedMonsterAttributes.field_111263_d).func_111124_b(field_110185_bq);
        }
      } else {
        short short1 = -1;
        
        if ((field_70146_Z.nextFloat() < 0.15F) && (func_70027_ad()) && (!func_70644_a(Potion.field_76426_n))) {
          short1 = 16307;
        } else if ((field_70146_Z.nextFloat() < 0.05F) && (func_110143_aJ() < func_110138_aP())) {
          short1 = 16341;
        } else if ((field_70146_Z.nextFloat() < 0.25F) && (func_70638_az() != null) && (!func_70644_a(Potion.field_76424_c)) && (func_70638_az().func_70068_e(this) > 121.0D))
        {
          short1 = 16274;
        } else if ((field_70146_Z.nextFloat() < 0.25F) && (func_70638_az() != null) && (!func_70644_a(Potion.field_76424_c)) && (func_70638_az().func_70068_e(this) > 121.0D))
        {
          short1 = 16274;
        }
        
        if (short1 > -1) {
          func_70062_b(0, new ItemStack(Items.field_151068_bn, 1, short1));
          witchAttackTimer = func_70694_bm().func_77988_m();
          setAggressive(true);
          IAttributeInstance attributeinstance = func_110148_a(SharedMonsterAttributes.field_111263_d);
          attributeinstance.func_111124_b(field_110185_bq);
          attributeinstance.func_111121_a(field_110185_bq);
        }
      }
      
      if (field_70146_Z.nextFloat() < 7.5E-4F) {
        field_70170_p.func_72960_a(this, (byte)15);
      }
    }
    
    super.func_70636_d();
  }
  
  protected float func_70672_c(DamageSource par1DamageSource, float par2)
  {
    par2 = super.func_70672_c(par1DamageSource, par2);
    
    if (par1DamageSource.func_76346_g() == this) {
      par2 = 0.0F;
    }
    
    if (par1DamageSource.func_82725_o()) {
      par2 = (float)(par2 * 0.15D);
    }
    
    return par2;
  }
  
  @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
  public void func_70103_a(byte par1)
  {
    if (par1 == 15) {
      for (int i = 0; i < field_70146_Z.nextInt(35) + 10; i++) {
        field_70170_p.func_72869_a("witchMagic", field_70165_t + field_70146_Z.nextGaussian() * 0.12999999523162842D, field_70121_D.field_72337_e + 0.5D + field_70146_Z.nextGaussian() * 0.12999999523162842D, field_70161_v + field_70146_Z.nextGaussian() * 0.12999999523162842D, 0.0D, 0.0D, 0.0D);
      }
      
    }
    else {
      super.func_70103_a(par1);
    }
  }
  
  public boolean func_70085_c(EntityPlayer player)
  {
    if ((!field_70170_p.field_72995_K) && (player != null)) {
      if ((!func_70909_n()) && (!getAggressive()) && (field_71093_bK != instancedimensionDreamID)) {
        if (questAccepted) {
          if (questOfferedTo.equalsIgnoreCase(player.func_70005_c_())) {
            if (isCovenFull(player)) {
              questAccepted = false;
              questType = 0;
              questOfferedTo = "";
              playWitchTalk(field_70170_p, this, 0.4F);
              
              ChatUtil.sendPlain(EnumChatFormatting.BLUE, player, String.format("<%s> %s", new Object[] { func_94057_bL(), Witchery.resource("witchery.witch.say.covenfull") }));
            }
            else if (isQuestItemForEntity(player.func_70694_bm(), this)) {
              field_71071_by.field_70462_a[field_71071_by.field_70461_c].field_77994_a -= 1;
              if (field_71071_by.field_70462_a[field_71071_by.field_70461_c].field_77994_a == 0) {
                field_71071_by.field_70462_a[field_71071_by.field_70461_c] = null;
              }
              if (--questItemsNeeded == 0) {
                if (addToPlayerCoven(player)) {
                  ChatUtil.sendPlain(EnumChatFormatting.BLUE, player, String.format("<%s> %s", new Object[] { func_94057_bL(), Witchery.resource("witchery.witch.say.joinedcoven") }));
                } else {
                  ChatUtil.sendPlain(EnumChatFormatting.BLUE, player, String.format("<%s> %s", new Object[] { func_94057_bL(), Witchery.resource("witchery.witch.say.tricked") }));
                  func_70604_c(player);
                  func_70624_b(player);
                  func_70784_b(player);
                  setAggressive(true);
                  questAccepted = false;
                  questType = 0;
                  questOfferedTo = "";
                }
              } else {
                ChatUtil.sendPlain(EnumChatFormatting.BLUE, player, String.format("<%s> %s", new Object[] { func_94057_bL(), String.format(Witchery.resource("witchery.witch.say.questitemsremaining"), new Object[] { Integer.valueOf(questItemsNeeded).toString() }) }));
              }
              playWitchTalk(field_70170_p, this, 0.8F);
            } else {
              ChatUtil.sendPlain(EnumChatFormatting.BLUE, player, String.format("<%s> %s", new Object[] { func_94057_bL(), Witchery.resource("witchery.witch.say.questnotfinished") }));
              playWitchTalk(field_70170_p, this, 0.4F);
            }
          } else {
            ChatUtil.sendPlain(EnumChatFormatting.BLUE, player, String.format("<%s> %s", new Object[] { func_94057_bL(), Witchery.resource("witchery.witch.say.begone") }));
            playWitchTalk(field_70170_p, this, 0.4F);
          }
        }
        else if (!questOfferedTo.equalsIgnoreCase(player.func_70005_c_())) {
          func_110163_bv();
          if (!func_94056_bM()) {
            setInnerCustomNameTag(generateWitchName());
          }
          if (isCovenFull(player)) {
            playWitchTalk(field_70170_p, this, 0.4F);
            ChatUtil.sendPlain(EnumChatFormatting.BLUE, player, String.format("<%s> %s", new Object[] { func_94057_bL(), Witchery.resource("witchery.witch.say.covenfull") }));
          } else if (!Familiar.hasActiveFamiliar(player))
          {
            String s;
            switch (field_70170_p.field_73012_v.nextInt(3)) {
            case 0: 
            default: 
              s = Witchery.resource("witchery.witch.say.notinterested1");
              break;
            case 1: 
              s = Witchery.resource("witchery.witch.say.notinterested2");
              break;
            case 2: 
              s = Witchery.resource("witchery.witch.say.notinterested3");
            }
            
            playWitchTalk(field_70170_p, this, 0.4F);
            ChatUtil.sendPlain(EnumChatFormatting.BLUE, player, String.format("<%s> %s", new Object[] { func_94057_bL(), s }));
          } else {
            questOfferedTo = player.func_70005_c_();
            questType = field_70170_p.field_73012_v.nextInt(QUESTS.length);
            questItemsNeeded = QUESTS[questType].getItemsNeeded();
            ChatUtil.sendPlain(EnumChatFormatting.BLUE, player, String.format("<%s> %s", new Object[] { func_94057_bL(), QUESTS[questType].getDescriptionText() }));
            playWitchTalk(field_70170_p, this, 0.4F);
          }
        } else if (isCovenFull(player)) {
          questOfferedTo = "";
          playWitchTalk(field_70170_p, this, 0.4F);
          ChatUtil.sendPlain(EnumChatFormatting.BLUE, player, String.format("<%s> %s", new Object[] { func_94057_bL(), Witchery.resource("witchery.witch.say.covenfull") }));
        } else {
          questAccepted = true;
          QUESTS[questType].activate(field_70170_p, this, player);
          if (!QUESTS[questType].getStartText().isEmpty()) {
            ChatUtil.sendPlain(EnumChatFormatting.BLUE, player, String.format("<%s> %s", new Object[] { func_94057_bL(), QUESTS[questType].getStartText() }));
          }
          playWitchTalk(field_70170_p, this, 1.0F);
        }
      }
      

      return true;
    }
    return false;
  }
  
  private boolean isCovenFull(EntityPlayer player)
  {
    return getCovenSize(player) >= 6;
  }
  
  public static int getCovenSize(EntityPlayer player) {
    if (player == null) {
      return 0;
    }
    
    NBTTagCompound nbtPlayer = Infusion.getNBT(player);
    if (!nbtPlayer.func_74764_b("WITCCoven")) {
      return 0;
    }
    
    NBTTagList nbtCovenList = nbtPlayer.func_150295_c("WITCCoven", 10);
    return nbtCovenList.func_74745_c();
  }
  
  protected boolean func_70692_ba()
  {
    if (func_70909_n()) {
      Entity player = func_70902_q();
      if (player == null)
        return true;
      if (player.func_70068_e(this) > 4096.0D) {
        return true;
      }
      return super.func_70692_ba();
    }
    
    return super.func_70692_ba();
  }
  





  private boolean addToPlayerCoven(EntityPlayer player)
  {
    NBTTagCompound nbtPlayer = Infusion.getNBT(player);
    if (!nbtPlayer.func_74764_b("WITCCoven")) {
      nbtPlayer.func_74782_a("WITCCoven", new NBTTagList());
    }
    
    NBTTagList nbtCovenList = nbtPlayer.func_150295_c("WITCCoven", 10);
    if (isWitchInList(nbtCovenList)) {
      return false;
    }
    
    func_70903_f(true);
    TameableUtil.setOwner(this, player);
    NBTTagCompound nbtWitch = new NBTTagCompound();
    writeToPlayerNBT(nbtWitch);
    nbtCovenList.func_74742_a(nbtWitch);
    ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, this, 1.0D, 2.0D, 16);
    func_70106_y();
    return true;
  }
  
  private boolean isWitchInList(NBTTagList nbtCovenList) {
    for (int i = 0; i < nbtCovenList.func_74745_c(); i++) {
      NBTTagCompound nbtWitch = nbtCovenList.func_150305_b(i);
      if (nbtWitch != null) {
        String name = nbtWitch.func_74779_i("WitchName");
        if ((name != null) && (name.equalsIgnoreCase(func_94057_bL()))) {
          return true;
        }
      }
    }
    return false;
  }
  
  private void writeToPlayerNBT(NBTTagCompound nbtWitch) {
    nbtWitch.func_74778_a("WitchName", func_94057_bL());
    nbtWitch.func_74768_a("Skin", getTameSkin());
    nbtWitch.func_74768_a("Quest", questType);
  }
  
  private void readFromPlayerNBT(NBTTagCompound nbtWitch) {
    setTameSkin(nbtWitch.func_74762_e("Skin"));
    questType = nbtWitch.func_74762_e("Quest");
    setInnerCustomNameTag(nbtWitch.func_74779_i("WitchName"));
  }
  
  public static void summonCoven(ArrayList<RitualStep> ritualSteps, World world, EntityPlayer player, int[][] pos) {
    double RADIUS_XZ = 64.0D;
    double RADIUS_Y = 16.0D;
    AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(field_70165_t - 64.0D, field_70163_u - 16.0D, field_70161_v - 64.0D, field_70165_t + 64.0D, field_70163_u + 16.0D, field_70161_v + 64.0D);
    
    List entities = world.func_72872_a(EntityCovenWitch.class, bounds);
    for (int index = 0; index < entities.size(); index++) {
      EntityCovenWitch witch = (EntityCovenWitch)entities.get(index);
      if ((witch.func_70909_n()) && (witch.func_70902_q() == player)) {
        ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, witch, 1.0D, 2.0D, 16);
        witch.func_70106_y();
      }
    }
    
    NBTTagCompound nbtPlayer = Infusion.getNBT(player);
    if (nbtPlayer.func_74764_b("WITCCoven")) {
      NBTTagList nbtCovenList = nbtPlayer.func_150295_c("WITCCoven", 10);
      for (int index = 0; (index < nbtCovenList.func_74745_c()) && (index < pos.length); index++) {
        ritualSteps.add(new StepSummonCovenMemeber(player, index, pos[index]));
      }
    }
  }
  
  private static class StepSummonCovenMemeber extends RitualStep {
    private final int index;
    private final int[] position;
    
    public StepSummonCovenMemeber(EntityPlayer player, int index, int[] position) {
      super();
      this.index = index;
      this.position = position;
    }
    
    public RitualStep.Result process(World world, int xCoord, int yCoord, int zCoord, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
    {
      if (ticks % 20L != 0L) {
        return RitualStep.Result.STARTING;
      }
      
      EntityPlayer player = ritual.getInitiatingPlayer(world);
      if (player != null) {
        NBTTagCompound nbtPlayer = Infusion.getNBT(player);
        if (nbtPlayer.func_74764_b("WITCCoven")) {
          double RADIUS_XZ = 64.0D;
          double RADIUS_Y = 16.0D;
          AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(field_70165_t - 64.0D, field_70163_u - 16.0D, field_70161_v - 64.0D, field_70165_t + 64.0D, field_70163_u + 16.0D, field_70161_v + 64.0D);
          
          List entities = world.func_72872_a(EntityCovenWitch.class, bounds);
          
          NBTTagList nbtCovenList = nbtPlayer.func_150295_c("WITCCoven", 10);
          NBTTagCompound nbtWitch = nbtCovenList.func_150305_b(this.index);
          EntityCovenWitch witch = null;
          for (int index = 0; index < entities.size(); index++) {
            EntityCovenWitch witchAround = (EntityCovenWitch)entities.get(index);
            if ((witchAround.func_70909_n()) && (witchAround.func_70902_q() == player) && (witchAround.func_94057_bL().equalsIgnoreCase(nbtWitch.func_74779_i("WitchName")))) {
              witch = witchAround;
              break;
            }
          }
          
          boolean spawn = false;
          if (witch == null) {
            witch = new EntityCovenWitch(world);
            witch.readFromPlayerNBT(nbtWitch);
            witch.func_70903_f(true);
            TameableUtil.setOwner(witch, player);
            spawn = true;
          }
          
          float[] FACING = { -45.0F, 45.0F, -135.0F, 135.0F, 180.0F, 0.0F };
          
          witch.func_70080_a(0.5D + position[0], 0.01D + position[1], 0.5D + position[2], FACING[this.index], 0.0F);
          
          field_70759_as = field_70177_z;
          field_70761_aq = field_70177_z;
          field_70126_B = field_70177_z;
          
          field_70911_d.func_75270_a(true);
          witch.setTimeToLive(300);
          
          if (spawn) {
            world.func_72838_d(witch);
          }
          
          ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, witch, 1.0D, 2.0D, 16);
        }
      }
      
      return RitualStep.Result.COMPLETED;
    }
  }
  
  public static void summonCoven(World world, EntityPlayer player, Coord target, int ticks) {
    if ((ticks % 20 == 0) && (ticks / 20 > 0)) {
      NBTTagCompound nbtPlayer = Infusion.getNBT(player);
      if (nbtPlayer.func_74764_b("WITCCoven")) {
        double RADIUS_XZ = 64.0D;
        double RADIUS_Y = 16.0D;
        AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(field_70165_t - 64.0D, field_70163_u - 16.0D, field_70161_v - 64.0D, field_70165_t + 64.0D, field_70163_u + 16.0D, field_70161_v + 64.0D);
        
        List entities = world.func_72872_a(EntityCovenWitch.class, bounds);
        
        NBTTagList nbtCovenList = nbtPlayer.func_150295_c("WITCCoven", 10);
        int witchIndex = ticks / 20 - 1;
        if (witchIndex > nbtCovenList.func_74745_c()) {
          return;
        }
        NBTTagCompound nbtWitch = nbtCovenList.func_150305_b(witchIndex);
        EntityCovenWitch witch = null;
        for (int index = 0; index < entities.size(); index++) {
          EntityCovenWitch witchAround = (EntityCovenWitch)entities.get(index);
          if ((witchAround.func_70909_n()) && (witchAround.func_70902_q() == player) && (witchAround.func_94057_bL().equalsIgnoreCase(nbtWitch.func_74779_i("WitchName")))) {
            witch = witchAround;
            break;
          }
        }
        
        boolean spawn = false;
        if (witch == null) {
          witch = new EntityCovenWitch(world);
          witch.readFromPlayerNBT(nbtWitch);
          witch.func_70903_f(true);
          TameableUtil.setOwner(witch, player);
          spawn = true;
        }
        
        TileEntity closest = null;
        double bestDistSq = 0.0D;
        for (Object obj : field_147482_g) {
          TileEntity tile = (TileEntity)obj;
          if ((tile instanceof com.emoniph.witchery.brewing.TileEntityCauldron))
          {
            double dist = player.func_70092_e(field_145851_c, field_145848_d, field_145849_e);
            if ((closest == null) || (dist < bestDistSq)) {
              closest = tile;
              bestDistSq = dist;
            }
          }
        }
        
        double cauldronRange = 9.0D;
        double cauldronRangeSq = 81.0D;
        
        if ((closest != null) && (bestDistSq <= 81.0D)) {
          witch.func_110171_b(field_145851_c, field_145848_d, field_145849_e, 3);
          int maxRange = 3;
          int minRange = 1;
          int activeRadius = maxRange - minRange;
          int ax = field_73012_v.nextInt(activeRadius * 2 + 1);
          if (ax > activeRadius) {
            ax += minRange * 2;
          }
          
          int nx = field_145851_c - maxRange + ax;
          
          int az = field_73012_v.nextInt(activeRadius * 2 + 1);
          if (az > activeRadius) {
            az += minRange * 2;
          }
          
          int nz = field_145849_e - maxRange + az;
          witch.func_70080_a(nx, 0.01D + field_145848_d, nz, 0.0F, 0.0F);
        } else {
          witch.func_70080_a(0.5D + x + field_73012_v.nextInt(3) - 1.5D, 0.01D + y, 0.5D + z + field_73012_v.nextInt(3) - 1.5D, 0.0F, 0.0F);
        }
        




        field_70911_d.func_75251_c();
        witch.setTimeToLive(1200);
        
        if (spawn) {
          world.func_72838_d(witch);
        }
        
        ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, witch, 1.0D, 2.0D, 16);
      }
    }
  }
  
  public static void summonCovenMember(World world, EntityPlayer player, int ttlSecs) {
    NBTTagCompound nbtPlayer = Infusion.getNBT(player);
    if (nbtPlayer.func_74764_b("WITCCoven")) {
      NBTTagList nbtCovenList = nbtPlayer.func_150295_c("WITCCoven", 10);
      if (nbtCovenList.func_74745_c() > 0)
      {
        double RADIUS_XZ = 64.0D;
        double RADIUS_Y = 16.0D;
        AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(field_70165_t - 64.0D, field_70163_u - 16.0D, field_70161_v - 64.0D, field_70165_t + 64.0D, field_70163_u + 16.0D, field_70161_v + 64.0D);
        
        List entities = world.func_72872_a(EntityCovenWitch.class, bounds);
        Collections.shuffle(entities);
        for (int index = 0; index < entities.size(); index++) {
          EntityCovenWitch witch = (EntityCovenWitch)entities.get(index);
          if ((witch.func_70909_n()) && (witch.func_70902_q() == player)) {
            int i = MathHelper.func_76128_c(field_70165_t) - 2;
            int j = MathHelper.func_76128_c(field_70161_v) - 2;
            int k = MathHelper.func_76128_c(field_70121_D.field_72338_b);
            for (int l = 0; l <= 4; l++) {
              for (int i1 = 0; i1 <= 4; i1++) {
                if (((l < 1) || (i1 < 1) || (l > 3) || (i1 > 3)) && (world.func_147439_a(i + l, k - 1, j + i1).isSideSolid(world, i + l, k - 1, j + i1, ForgeDirection.UP)) && (!world.func_147439_a(i + l, k, j + i1).func_149721_r()) && (!world.func_147439_a(i + l, k + 1, j + i1).func_149721_r()))
                {
                  ItemGeneral.teleportToLocation(world, i + l + 0.5F, k, j + i1 + 0.5F, field_71093_bK, witch, true);
                  
                  witch.func_70661_as().func_75499_g();
                  return;
                }
              }
            }
          }
        }
        

        NBTTagCompound nbtWitch = nbtCovenList.func_150305_b(field_73012_v.nextInt(nbtCovenList.func_74745_c()));
        EntityCovenWitch witch = new EntityCovenWitch(world);
        int i = MathHelper.func_76128_c(field_70165_t) - 2;
        int j = MathHelper.func_76128_c(field_70161_v) - 2;
        int k = MathHelper.func_76128_c(field_70121_D.field_72338_b);
        for (int l = 0; l <= 4; l++) {
          for (int i1 = 0; i1 <= 4; i1++) {
            if (((l < 1) || (i1 < 1) || (l > 3) || (i1 > 3)) && (world.func_147439_a(i + l, k - 1, j + i1).isSideSolid(world, i + l, k - 1, j + i1, ForgeDirection.UP)) && (!world.func_147439_a(i + l, k, j + i1).func_149721_r()) && (!world.func_147439_a(i + l, k + 1, j + i1).func_149721_r()))
            {
              witch.func_70012_b(i + l + 0.5F, k, j + i1 + 0.5F, 0.0F, 0.0F);
              

              break;
            }
          }
        }
        witch.func_70903_f(true);
        TameableUtil.setOwner(witch, player);
        witch.readFromPlayerNBT(nbtWitch);
        witch.setTimeToLive(ttlSecs * 20);
        world.func_72838_d(witch);
        ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, witch, 1.0D, 2.0D, 16);
      } else {
        SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
      }
    } else {
      SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
    }
  }
  
  private static void playWitchTalk(World world, Entity where, float volume) {
    Witchery.packetPipeline.sendToAllAround(new PacketSound(field_73012_v.nextBoolean() ? SoundEffect.WITCHERY_MOB_BABA_DEATH : SoundEffect.WITCHERY_MOB_BABA_LIVING, where, 1.0F, 1.0F), TargetPointUtil.from(where, 8.0D));
  }
  

  private static final Quest[] QUESTS = { new FightPetQuest(Witchery.resource("witchery.witch.quest.fightspider"), ""), new FightZombiePetQuest(Witchery.resource("witchery.witch.quest.fightzombie"), ""), new FetchQuest(Witchery.resource("witchery.witch.quest.getdemonheart"), Witchery.resource("witchery.witch.quest.go"), ItemsGENERIC.itemDemonHeart.createStack()), new FetchQuest(Witchery.resource("witchery.witch.quest.makecrystalball"), Witchery.resource("witchery.witch.quest.go"), new ItemStack(BlocksCRYSTAL_BALL)), new FetchQuest(Witchery.resource("witchery.witch.quest.getbones"), Witchery.resource("witchery.witch.quest.go"), new ItemStack(Items.field_151103_aS, 30)), new FetchQuest(Witchery.resource("witchery.witch.quest.makegrotesquebrew"), Witchery.resource("witchery.witch.quest.go"), ItemsGENERIC.itemBrewGrotesque.createStack(5)), new FetchQuest(Witchery.resource("witchery.witch.quest.makenecrostone"), Witchery.resource("witchery.witch.quest.go"), ItemsGENERIC.itemNecroStone.createStack()) };
  


  private static abstract class Quest
  {
    private final String questDescriptionText;
    

    private final String questStartText;
    
    private final int itemsNeeded;
    

    public Quest(String descriptionText, String startText, int itemsNeeded)
    {
      questStartText = startText;
      questDescriptionText = descriptionText;
      this.itemsNeeded = itemsNeeded;
    }
    
    public int getItemsNeeded() {
      return itemsNeeded;
    }
    
    public String getDescriptionText() {
      return questDescriptionText;
    }
    
    public String getStartText() {
      return questStartText;
    }
    
    public abstract void activate(World paramWorld, EntityCovenWitch paramEntityCovenWitch, EntityPlayer paramEntityPlayer);
    
    public boolean isQuestItem(ItemStack stack) {
      return false;
    }
  }
  
  private static class FetchQuest extends EntityCovenWitch.Quest {
    final ItemStack stack;
    
    public FetchQuest(String descriptionText, String startText, ItemStack stack) {
      super(startText, field_77994_a);
      this.stack = stack;
    }
    

    public void activate(World world, EntityCovenWitch witch, EntityPlayer player) {}
    

    public boolean isQuestItem(ItemStack stack)
    {
      return this.stack.func_77969_a(stack);
    }
  }
  
  private static class FightPetQuest extends EntityCovenWitch.Quest
  {
    public FightPetQuest(String descriptionText, String startText) {
      super(startText, 1);
    }
    

    public void activate(World world, EntityCovenWitch witch, EntityPlayer player)
    {
      EntitySpider spider = new EntitySpider(world);
      spider.func_70012_b(field_70165_t, field_70163_u, field_70161_v, field_70125_A, field_70177_z);
      world.func_72838_d(spider);
      spider.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(100.0D);
      spider.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(5.0D);
      spider.func_70606_j((float)spider.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111126_e());
      spider.func_70624_b(player);
      spider.func_70784_b(player);
      spider.func_70604_c(player);
      spider.func_94058_c(String.format(Witchery.resource("witchery.witch.pet"), new Object[] { witch.func_94057_bL() }));
      
      ItemStack stack = new ItemStack(Items.field_151070_bp);
      stack.func_151001_c(String.format(Witchery.resource("witchery.witch.peteye"), new Object[] { witch.func_94057_bL() }));
      if (!stack.func_77942_o()) {
        stack.func_77982_d(new NBTTagCompound());
      }
      NBTTagCompound nbtRoot = stack.func_77978_p();
      nbtRoot.func_74772_a("WITCQuestOwnerIDLeast", witch.func_110124_au().getLeastSignificantBits());
      nbtRoot.func_74772_a("WITCQuestOwnerIDMost", witch.func_110124_au().getMostSignificantBits());
      NBTTagCompound nbtExtraDrop = new NBTTagCompound();
      stack.func_77955_b(nbtExtraDrop);
      
      NBTTagCompound nbtSpider = spider.getEntityData();
      if (!nbtSpider.func_74764_b("WITCExtraDrops")) {
        nbtSpider.func_74782_a("WITCExtraDrops", new NBTTagList());
      }
      NBTTagList nbtExtraDrops = nbtSpider.func_150295_c("WITCExtraDrops", 10);
      nbtExtraDrops.func_74742_a(nbtExtraDrop);
      
      ParticleEffect.MOB_SPELL.send(SoundEffect.NONE, spider, 2.0D, 2.0D, 16);
    }
  }
  
  private static class FightZombiePetQuest extends EntityCovenWitch.Quest
  {
    public FightZombiePetQuest(String descriptionText, String startText) {
      super(startText, 1);
    }
    
    public void activate(World world, EntityCovenWitch witch, EntityPlayer player)
    {
      EntityZombie spider = new EntityZombie(world);
      spider.func_70012_b(field_70165_t, field_70163_u, field_70161_v, field_70125_A, field_70177_z);
      world.func_72838_d(spider);
      spider.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(100.0D);
      spider.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(5.0D);
      spider.func_70062_b(4, new ItemStack(Items.field_151144_bL));
      spider.func_70606_j((float)spider.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111126_e());
      spider.func_70624_b(player);
      spider.func_70784_b(player);
      spider.func_70604_c(player);
      spider.func_94058_c(String.format(Witchery.resource("witchery.witch.pet"), new Object[] { witch.func_94057_bL() }));
      
      ItemStack stack = new ItemStack(Items.field_151078_bh);
      stack.func_151001_c(String.format(Witchery.resource("witchery.witch.petflesh"), new Object[] { witch.func_94057_bL() }));
      if (!stack.func_77942_o()) {
        stack.func_77982_d(new NBTTagCompound());
      }
      NBTTagCompound nbtRoot = stack.func_77978_p();
      nbtRoot.func_74772_a("WITCQuestOwnerIDLeast", witch.func_110124_au().getLeastSignificantBits());
      nbtRoot.func_74772_a("WITCQuestOwnerIDMost", witch.func_110124_au().getMostSignificantBits());
      NBTTagCompound nbtExtraDrop = new NBTTagCompound();
      stack.func_77955_b(nbtExtraDrop);
      
      NBTTagCompound nbtSpider = spider.getEntityData();
      if (!nbtSpider.func_74764_b("WITCExtraDrops")) {
        nbtSpider.func_74782_a("WITCExtraDrops", new NBTTagList());
      }
      NBTTagList nbtExtraDrops = nbtSpider.func_150295_c("WITCExtraDrops", 10);
      nbtExtraDrops.func_74742_a(nbtExtraDrop);
      
      ParticleEffect.MOB_SPELL.send(SoundEffect.NONE, spider, 2.0D, 2.0D, 16);
    }
  }
  
  private static boolean isQuestItemForEntity(ItemStack stack, EntityCovenWitch questGiver) {
    if ((questGiver != null) && (stack != null)) {
      if (QUESTS[questType].isQuestItem(stack))
        return true;
      if (stack.func_77942_o()) {
        NBTTagCompound nbtRoot = stack.func_77978_p();
        if ((nbtRoot.func_74764_b("WITCQuestOwnerIDLeast")) && (nbtRoot.func_74764_b("WITCQuestOwnerIDMost"))) {
          UUID questGiverID = new UUID(nbtRoot.func_74763_f("WITCQuestOwnerIDMost"), nbtRoot.func_74763_f("WITCQuestOwnerIDLeast"));
          
          return questGiverID.equals(questGiver.getPersistentID());
        }
      }
    }
    return false;
  }
  
  protected void func_70628_a(boolean par1, int par2)
  {
    int j = field_70146_Z.nextInt(3) + 1;
    
    for (int k = 0; k < j; k++) {
      int l = field_70146_Z.nextInt(3);
      Item i1 = witchDrops[field_70146_Z.nextInt(witchDrops.length)];
      
      if (par2 > 0) {
        l += field_70146_Z.nextInt(par2 + 1);
      }
      
      for (int j1 = 0; j1 < l; j1++) {
        func_145779_a(i1, 1);
      }
    }
  }
  
  public void func_82196_d(EntityLivingBase par1EntityLivingBase, float par2)
  {
    if (!getAggressive()) {
      EntityPotion entitypotion = new EntityPotion(field_70170_p, this, 32732);
      field_70125_A -= -20.0F;
      double d0 = field_70165_t + field_70159_w - field_70165_t;
      double d1 = field_70163_u + par1EntityLivingBase.func_70047_e() - 1.100000023841858D - field_70163_u;
      double d2 = field_70161_v + field_70179_y - field_70161_v;
      float f1 = MathHelper.func_76133_a(d0 * d0 + d2 * d2);
      
      if ((f1 >= 8.0F) && (!par1EntityLivingBase.func_70644_a(Potion.field_76421_d))) {
        entitypotion.func_82340_a(32698);
      } else if ((par1EntityLivingBase.func_110143_aJ() >= 8.0F) && (!par1EntityLivingBase.func_70644_a(Potion.field_76436_u))) {
        entitypotion.func_82340_a(32660);
      } else if ((f1 <= 3.0F) && (!par1EntityLivingBase.func_70644_a(Potion.field_76437_t)) && (field_70146_Z.nextFloat() < 0.25F)) {
        entitypotion.func_82340_a(32696);
      }
      
      entitypotion.func_70186_c(d0, d1 + f1 * 0.2F, d2, 0.75F, 8.0F);
      field_70170_p.func_72838_d(entitypotion);
    }
  }
  
  public EntityAgeable func_90011_a(EntityAgeable entityageable)
  {
    return null;
  }
  
  public static String generateWitchName() {
    Random ra = new Random();
    return String.format("%s %s", new Object[] { FIRST_NAMES[ra.nextInt(FIRST_NAMES.length)], SURNAMES[ra.nextInt(SURNAMES.length)] });
  }
  
  private static final String[] FIRST_NAMES = { "Abigail", "Agatha", "Agony", "Alcina", "Alcyone", "Alexandra", "Alexandria", "Alvira", "Amanita", "Amaranth", "Amarantha", "Ambrosia", "Amelia", "Amethyst", "Anastasia", "Andromeda", "Angel", "Angela", "Angelica", "Angelique", "Anna", "Arachne", "Aradia", "Aria", "Arianna", "Ariadne", "Ariel", "Artemis", "Artemisia", "Astrea", "Astrid", "Astoria", "Autumn", "Aurora", "Beatrix", "Bella", "Belladonna", "Belle", "Bernadette", "Beryl", "Bianca", "Blanche", "Bliss", "Calliope", "Callypso", "Calpurnia", "Camilla", "Carlotta", "Carmilla", "Caroline", "Carrie", "Cassandra", "Cassiopeia", "Catherine", "Cathy", "Cecelia", "Celeste", "Celia", "Charlotte", "Christine", "Circe", "Clara", "Claudia", "Cleopatra", "Columbia", "Coraline", "Cordelia", "Cornelia", "Crystal", "Daphne", "Daria", "Darla", "Delia", "Delilah", "Della", "Demetria", "Demonica", "Desdemona", "Desire", "Dolores", "Dora", "Dove", "Drusilla", "Dusk", "Ebony", "Echo", "Eden", "Elanore", "Electra", "Eldora", "Elena", "Eliza", "Eloise", "Elphaba", "Elspeth", "Elsinore", "Elvira", "Ember", "Emilie", "Ephemera", "Eranthe", "Eris", "Esmerelda", "Estrella", "Esther", "Eterna", "Eternity", "Eudora", "Euphemia", "Eva", "Evalina", "Evangeline", "Eve", "Granny", "Gabriella", "Gabrielle", "Garnet", "Genevieve", "Godiva", "Hathor", "Hecate", "Hecuba", "Helena", "Hepzibah", "Hesperia", "Hippolita", "Ianthe", "Icie", "Icy", "Inez", "Infinity", "Ione", "Iris", "Isabeau", "Isabella", "Isabelle", "Isadora", "Isis", "Isolde", "Istar", "Ivy", "Izora", "Jane", "Jeanette", "Jinx", "Jocasta", "Juliet", "Katrina", "Lavinia", "Layla", "Leona", "Lenora", "Lenore", "Leona", "Libitina", "Ligeia", "Lilah", "Lilith", "Lillian", "Lily", "Lolita", "Lorraine", "Lucinda", "Lucretia", "Luna", "Lydia", "Lyra", "Madeline", "Magdalena", "Magenta", "Mara", "Marcella", "Margaret", "Marguerita", "Maria", "Marie", "Marissa", "Martha", "Matilda", "Medea", "Medusa", "Melanie", "Melantha", "Melanthe", "Melinda", "Mercedes", "Merula", "Mildred", "Mina", "Minerva", "Miranda", "Miriam", "Moira", "Mordea", "Morgan", "Morgana", "Morticia", "Nadia", "Nadine", "Nerezza", "Nora", "Nyx", "Obsidia", "Octavia", "Odessa", "Olivia", "Opal", "Ophelia", "Pandora", "Patience", "Pearl", "Penelope", "Perenelle", "Permelia", "Persephone", "Pixie", "Phoenix", "Poppy", "Priscilla", "Prudence", "Rachel", "Rain", "Raven", "Regina", "Rita", "Rosa", "Rose", "Rosemary", "Rowena", "Ruby", "Sabrina", "Salem", "Samantha", "Sangria", "Scarlet", "Selena", "Selene", "Sephora", "Seraphina", "Serena", "Serenity", "Shannon", "Silver", "Simone", "Sophia", "Sybella", "Sybil", "Sylvia", "Tabitha", "Tempest", "Theda", "Theresa", "Thora", "Threnody", "Trinity", "Twilight", "Umbra", "Vaitiare", "Valerie", "Vanessa", "Verna", "Verona", "Veronica", "Vesta", "Victoria", "Violet", "Whisper", "Willow", "Winter", "Xenobia", "Zillah", "Zinnia" };
  





















  private static final String[] SURNAMES = { "Adams", "Addams", "Argent", "Ashwood", "Balfour", "Barker", "Batby", "Bathory", "Batsford", "Batson", "Batstone", "Batt", "Baudelaire", "Black", "Blackbird", "Blackburn", "Blackcat", "Blacklock", "Blackmoore", "Blackstone", "Blackthorn", "Blackwell", "Blackwing", "Blackwolf", "Blackwood", "Blair", "Blood", "Bloodgood", "Bloodhart", "Bloodmoore", "Bloodsaw", "Bloodstone", "Bloodsworth", "Bloodwine", "Bloodworth", "Boggart", "Boggarty", "Bonebrake", "Bonehart", "Bonehill", "Bonella", "Boneman", "Bones", "Bonesmith", "Bonewits", "Borden", "Broom", "Broomwood", "Burton", "Byron", "Cackler", "Cain", "Calamity", "Castle", "Castleton", "Cemetary", "Chill", "Chillingwood", "Cobweb", "Coffin", "Coffinberry", "Coffins", "Cold", "Coldbridge", "Coldeman", "Coldstone", "Coldwell", "Cole", "Collins", "Constantine", "Corbett", "Corbin", "Corpse", "Corpseley", "Creak", "Creakey", "Creep", "Creeper", "Creeps", "Crepsley", "Crimson", "Cross", "Crossway", "Crosswicks", "Crow", "Crowden", "Crowe", "Crowley", "Darcy", "Dark", "Darke", "Darken", "Darkenwald", "Darkes", "Darkmoore", "Darkwell", "Darkwood", "Deadman", "Deadmond", "Deadmore", "Deadrick", "Deadwood", "DeAngelus", "Dearborn", "Death", "Deathridge", "Deathrow", "December", "Delambre", "DeLioncourt", "Demond", "Demonde", "Demonte", "DeMort", "DeRavin", "Devall", "Devane", "DeVille", "DeWinter", "Dracul", "Drago", "Drake", "Dread", "Drear", "Dreary", "Drelincourt", "DuLac", "Dumaine", "Dunsany", "Eldritch", "Fang", "Fanger", "Fate", "Faust", "February", "Fear", "Fearfield", "Fears", "Frankenstein", "Frost", "Fury", "Gautier", "Ghoul", "Ghoulson", "Ghost", "Ghosten", "Ghostley", "Giger", "Goblin", "Goth", "Gotham", "Gothard", "Gothberg", "Gravedigger", "Gravemaker", "Graves", "Gravesen", "Gravesgard", "Grey", "Greyson", "Greystone", "Grimmauld", "Grimm", "Grimmer", "Grimmes", "Grimmins", "Grimsbro", "Grimsby", "Grimsman", "Grimwood", "Harker", "Hart", "Haunt", "Haunter", "Haunton", "Haunty", "Hawk", "Hawke", "Havelock", "Heart", "Heartstrom", "Hemlock", "Hex", "Hexem", "Hexter", "Hexwood", "Hollow", "Holmes", "Holmwood", "Hugo", "Hunter", "Hyde", "January", "Jekyll", "Kenrick", "Kilgore", "Killar", "Killewich", "Killings", "LaCroix", "Lapidus", "LaRue", "LeFay", "LeStrange", "LeStrange", "Locke", "London", "Loveless", "Lovelock", "Lovett", "Lycan", "MacBeth", "Mandrake", "Marrow", "Masters", "Mist", "Misteri", "Moan", "Moon", "Moones", "Moonie", "Moonly", "Monet", "Monster", "Monstery", "Montague", "Montresor", "Morgan", "Morgue", "Moriarty", "Murdoc", "Murray", "Morrow", "Mort", "Mortella", "Munster", "Mysterios", "Night", "Nightchase", "Nightengale", "Nightingdale", "Nightman", "Nightwalker", "Nightwine", "Nocton", "Nox", "October", "Odd", "Odder", "Oddman", "Oddson", "Owl", "Owley", "Owlford", "Owlsey", "Pale", "Pale", "Paine", "Pains", "Payne", "Plague", "Poe", "Poe", "Poe", "Pyre", "Pyre", "Pyre", "Radcliffe", "Rain", "Raven", "Ravencraft", "Ravendale", "Ravenhorst", "Ravensloft", "Ravenway", "Rayne", "Reaper", "Redbone", "Redcross", "Redd", "Redfern", "Redgrave", "Redmond", "Redwine", "Redwolf", "Renfield", "Riven", "Rookwood", "Roth", "Ripley", "Ripper", "Salvatore", "Scar", "Scare", "Scarebrook", "Scares", "Scarey", "Scarlati", "Setzer", "Seward", "Shade", "Shademoore", "Shadow", "Shadows", "Shadowton", "Shelley", "Skeleton", "Skelinen", "Skellington", "Skelton", "Skull", "Skullman", "Specter", "Spectre", "Spellman", "Spider", "Spinner", "Spirite", "Spook", "Spook", "Spook", "Song", "Snow", "St. Claire", "St. Germaine", "Steele", "Sterling", "Stoker", "Storm", "Storme", "Stormfelt", "Stormwind", "Stormyr", "Stone", "Stonewall", "Strange", "Strangeman", "Strangeway", "Striker", "Swan", "Swann", "Teeth", "Tombs", "Tombstone", "Towers", "Trick", "Valancourt", "Valdemar", "Valentine", "Valentino", "Vamper", "Vamplers", "Vampouille", "Vamprine", "Vampyr", "Van Allen", "Van Gogh", "Van Halen", "Van Helgen", "Van Helsing", "Voorhees", "Webb", "Weird", "Weird", "West", "Westenra", "White", "Whitebone", "Whitemoon", "Whitewing", "Widdowes", "Wild", "Wildblood", "Wilde", "Winchester", "Windgate", "Windholm", "Windward", "Wing", "Wingblade", "Wingfield", "Winter", "Winterford", "Winterrose", "Winterwood", "Winters", "Witche", "Witcher", "Witchery", "Witchey", "Witchman", "Wither", "Wolf", "Wolfen", "Wolfmann", "Wolfram", "Wolfstone", "Wolftooth" };
  





























  public void standStill()
  {
    field_70911_d.func_75270_a(true);
  }
}
