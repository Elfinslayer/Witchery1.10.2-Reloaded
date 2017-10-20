package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.familiar.IFamiliar;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import com.emoniph.witchery.item.ItemTaglockKit;
import com.emoniph.witchery.item.ItemTaglockKit.BoundType;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class EntityTreefyd extends EntityMob implements IEntitySelector
{
  public EntityTreefyd(World par1World)
  {
    super(par1World);
    func_70105_a(0.4F, 1.8F);
    func_70661_as().func_75491_a(true);
    func_70661_as().func_75495_e(true);
    field_70714_bg.func_75776_a(1, new net.minecraft.entity.ai.EntityAISwimming(this));
    

    field_70714_bg.func_75776_a(4, new EntityAIAttackOnCollide(this, 1.0D, false));
    field_70714_bg.func_75776_a(5, new com.emoniph.witchery.entity.ai.EntityAITreefydWander(this, 0.8D));
    field_70714_bg.func_75776_a(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
    field_70714_bg.func_75776_a(6, new EntityAILookIdle(this));
    field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 0, false, true, this));
    

    field_70715_bh.func_75776_a(2, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false));
  }
  
  private static class CreatureID {
    UUID id;
    String name;
    
    public CreatureID(UUID id, String name) {
      this.id = id;
      this.name = name;
    }
    
    public boolean equals(Object obj)
    {
      if (obj == null) {
        return false;
      }
      
      if (obj == this) {
        return true;
      }
      
      if ((obj instanceof UUID)) {
        return id.equals((UUID)obj);
      }
      
      if (obj.getClass() == getClass()) {
        return id.equals(id);
      }
      
      return false;
    }
    
    public String toString()
    {
      return name;
    }
  }
  
  public CreatureID testID = new CreatureID(new UUID(0L, 0L), "");
  
  public boolean func_82704_a(Entity entity) {
    if ((entity.getClass() == getClass()) || ((entity instanceof EntityHornedHuntsman)) || ((entity instanceof EntityEnt)) || ((entity instanceof net.minecraft.entity.EntityFlying)) || ((entity instanceof EntityFlyingTameable)) || ((entity instanceof EntityAmbientCreature)) || ((entity instanceof net.minecraft.entity.passive.EntityWaterMob)) || (isFamiliar(entity)) || ((entity instanceof EntityCovenWitch)) || ((entity instanceof EntityCorpse)))
    {


      return false;
    }
    
    if ((entity instanceof EntityPlayer)) {
      EntityPlayer player = (EntityPlayer)entity;
      String ownerName = getOwnerName();
      if ((ownerName != null) && (!ownerName.isEmpty()) && (player.func_70005_c_().equals(ownerName))) {
        return false;
      }
      
      if ((knownPlayers != null) && (knownPlayers.contains(player.func_70005_c_()))) {
        return false;
      }
    }
    
    if ((entity instanceof EntityLiving)) {
      EntityLiving creature = (EntityLiving)entity;
      if ((knownCreatureTypes != null) && (knownCreatureTypes.contains(creature.func_70005_c_()))) {
        return false;
      }
      testID.id = entity.func_110124_au();
      if ((knownCreatures != null) && (knownCreatures.contains(testID))) {
        return false;
      }
    }
    
    return true;
  }
  
  public int func_70627_aG()
  {
    return super.func_70627_aG() * 2;
  }
  
  protected boolean func_70085_c(EntityPlayer player)
  {
    if ((!field_70170_p.field_72995_K) && (player != null) && (player.func_70005_c_().equals(getOwnerName()))) {
      ItemStack stack = player.func_70694_bm();
      if ((stack != null) && (stack.func_77973_b() == ItemsTAGLOCK_KIT)) {
        func_70624_b(null);
        ItemTaglockKit.BoundType boundEntityType = ItemsTAGLOCK_KIT.getBoundEntityType(stack, Integer.valueOf(1));
        
        switch (1.$SwitchMap$com$emoniph$witchery$item$ItemTaglockKit$BoundType[boundEntityType.ordinal()]) {
        case 1: 
          String otherUsername = ItemsTAGLOCK_KIT.getBoundUsername(stack, Integer.valueOf(1));
          if (!player.func_70005_c_().equals(otherUsername)) {
            if ((!player.func_70093_af()) && (!knownPlayers.contains(otherUsername))) {
              knownPlayers.add(otherUsername);
            } else if ((player.func_70093_af()) && (knownPlayers.contains(otherUsername))) {
              knownPlayers.remove(otherUsername);
            } else {
              showCurrentKnownEntities(player);
              
              return super.func_70085_c(player);
            }
            field_77994_a -= 1;
            if (field_77994_a <= 0) {
              field_71071_by.func_70299_a(field_71071_by.field_70461_c, null);
            }
            if ((player instanceof EntityPlayerMP)) {
              ((EntityPlayerMP)player).func_71120_a(field_71069_bz);
            }
            
            showCurrentKnownEntities(player);
            
            return true;
          }
          break;
        case 2: 
          UUID otherCreature = ItemsTAGLOCK_KIT.getBoundCreatureID(stack, Integer.valueOf(1));
          String creatureName = ItemsTAGLOCK_KIT.getBoundEntityDisplayName(stack, Integer.valueOf(1));
          if (!otherCreature.equals(func_110124_au())) {
            if (isGroupableCreature(otherCreature, creatureName)) {
              if ((!player.func_70093_af()) && (!knownCreatureTypes.contains(creatureName))) {
                knownCreatureTypes.add(creatureName);
              } else if ((player.func_70093_af()) && (knownCreatureTypes.contains(creatureName))) {
                knownCreatureTypes.remove(creatureName);
              } else {
                showCurrentKnownEntities(player);
                return super.func_70085_c(player);
              }
            } else {
              CreatureID creatureID = new CreatureID(otherCreature, creatureName);
              if ((!player.func_70093_af()) && (!knownCreatures.contains(creatureID))) {
                knownCreatures.add(creatureID);
              } else if ((player.func_70093_af()) && (knownCreatures.contains(creatureID))) {
                knownCreatures.remove(creatureID);
              } else {
                showCurrentKnownEntities(player);
                return super.func_70085_c(player);
              }
            }
            field_77994_a -= 1;
            if (field_77994_a <= 0) {
              field_71071_by.func_70299_a(field_71071_by.field_70461_c, null);
            }
            if ((player instanceof EntityPlayerMP)) {
              ((EntityPlayerMP)player).func_71120_a(field_71069_bz);
            }
          }
          showCurrentKnownEntities(player);
          return true;
        }
        
      }
      else if ((stack != null) && (ItemsGENERIC.itemSeedsTreefyd.isMatch(stack))) {
        if (!field_70170_p.field_72995_K) {
          EntityTreefyd entity = new EntityTreefyd(field_70170_p);
          entity.func_70012_b(0.5D + field_70165_t, field_70163_u, 0.5D + field_70161_v, 0.0F, 0.0F);
          entity.func_110161_a(null);
          entity.setOwner(player.func_70005_c_());
          entity.func_110163_bv();
          knownPlayers = ((ArrayList)knownPlayers.clone());
          knownCreatureTypes = ((ArrayList)knownCreatureTypes.clone());
          knownCreatures = ((ArrayList)knownCreatures.clone());
          
          field_70170_p.func_72838_d(entity);
          
          ParticleEffect.SLIME.send(SoundEffect.MOB_SILVERFISH_KILL, this, 1.0D, 2.0D, 16);
          ParticleEffect.EXPLODE.send(SoundEffect.NONE, this, 1.0D, 2.0D, 16);
        }
        if (!field_71075_bZ.field_75098_d) {
          field_77994_a -= 1;
        }
      } else if ((stack != null) && (ItemsGENERIC.itemCreeperHeart.isMatch(stack))) {
        if (!field_70170_p.field_72995_K) {
          func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(100.0D);
          func_70606_j(func_110138_aP());
          func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
          ParticleEffect.SLIME.send(SoundEffect.MOB_SILVERFISH_KILL, this, 0.5D, 2.0D, 16);
        }
        if (!field_71075_bZ.field_75098_d) {
          field_77994_a -= 1;
        }
      } else if ((stack != null) && (ItemsGENERIC.itemDemonHeart.isMatch(stack))) {
        if (!field_70170_p.field_72995_K) {
          func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(150.0D);
          func_70606_j(func_110138_aP());
          func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(5.0D);
          ParticleEffect.FLAME.send(SoundEffect.MOB_ENDERDRAGON_GROWL, this, 0.5D, 2.0D, 16);
        }
        if (!field_71075_bZ.field_75098_d) {
          field_77994_a -= 1;
        }
      } else if ((stack != null) && (stack.func_77973_b() == ItemsBOLINE) && 
        (!field_70170_p.field_72995_K)) {
        setSentinal(!isSentinal());
      }
      

      showCurrentKnownEntities(player);
    }
    return super.func_70085_c(player);
  }
  
  private static ArrayList<String> groupables = null;
  
  private boolean isGroupableCreature(UUID otherCreature, String creatureName) {
    if (groupables == null) {
      groupables = new ArrayList();
      addGroupableType(EntityVillager.class);
      addGroupableType(EntityGoblin.class);
      addGroupableType(net.minecraft.entity.passive.EntitySheep.class);
      addGroupableType(EntityCow.class);
      addGroupableType(net.minecraft.entity.passive.EntityMooshroom.class);
      addGroupableType(EntityChicken.class);
      addGroupableType(EntityPig.class);
      addGroupableType(EntityHorse.class);
    }
    return groupables.contains(creatureName);
  }
  
  private void addGroupableType(Class<? extends EntityLiving> className) {
    String name = (String)EntityList.field_75626_c.get(className);
    if (name != null) {
      String localName = StatCollector.func_74838_a("entity." + name + ".name");
      groupables.add(localName);
    }
  }
  
  private void showCurrentKnownEntities(EntityPlayer player) {
    StringBuffer sb = new StringBuffer();
    
    String ownerName = getOwnerName();
    if ((ownerName != null) && (!ownerName.isEmpty())) {
      sb.append(getOwnerName());
    }
    for (String s : knownPlayers) {
      if (sb.length() > 0) {
        sb.append(", ");
      }
      sb.append(s);
    }
    
    for (String s : knownCreatureTypes) {
      if (sb.length() > 0) {
        sb.append(", ");
      }
      sb.append("#");
      sb.append(s);
    }
    
    for (CreatureID cid : knownCreatures) {
      if (sb.length() > 0) {
        sb.append(", ");
      }
      sb.append(cid.toString());
    }
    
    String message = func_70005_c_() + " (" + sb.toString() + ")";
    ChatUtil.sendPlain(player, message);
  }
  
  private boolean isFamiliar(Entity entity) {
    if ((entity instanceof IFamiliar)) {
      IFamiliar familiar = (IFamiliar)entity;
      return familiar.isFamiliar();
    }
    return false;
  }
  
  public String func_70005_c_()
  {
    if (func_94056_bM()) {
      return func_94057_bL();
    }
    return StatCollector.func_74838_a("entity.witchery.treefyd.name");
  }
  

  protected void func_110147_ax()
  {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25D);
    func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(3.0D);
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(50.0D);
  }
  
  public boolean func_70650_aV()
  {
    return true;
  }
  
  public boolean func_70652_k(Entity entity)
  {
    if ((!field_70170_p.field_72995_K) && 
      ((entity instanceof EntityPlayer))) {
      EntityPlayer player = (EntityPlayer)entity;
      if (!player.func_70644_a(Potion.field_76440_q)) {
        player.func_70690_d(new PotionEffect(field_76440_qfield_76415_H, 100, 0));
      }
    }
    return super.func_70652_k(entity);
  }
  
  public int func_82143_as()
  {
    return func_70638_az() == null ? 3 : 3 + (int)(func_110143_aJ() - 1.0F);
  }
  
  protected void func_70088_a()
  {
    super.func_70088_a();
    field_70180_af.func_75682_a(17, "");
    field_70180_af.func_75682_a(18, Integer.valueOf(Integer.valueOf(0).intValue()));
  }
  
  public boolean isSentinal() {
    return field_70180_af.func_75679_c(18) == 1;
  }
  
  protected void setSentinal(boolean screaming) {
    field_70180_af.func_75692_b(18, Integer.valueOf(Integer.valueOf(screaming ? 1 : 0).intValue()));
  }
  
  private ArrayList<String> knownPlayers = new ArrayList();
  private ArrayList<String> knownCreatureTypes = new ArrayList();
  private ArrayList<CreatureID> knownCreatures = new ArrayList();
  
  public void func_70014_b(NBTTagCompound nbtRoot)
  {
    super.func_70014_b(nbtRoot);
    
    if (getOwnerName() == null) {
      nbtRoot.func_74778_a("Owner", "");
    } else {
      nbtRoot.func_74778_a("Owner", getOwnerName());
    }
    
    if (knownPlayers.size() > 0) {
      NBTTagList nbtPlayers = new NBTTagList();
      for (String playerName : knownPlayers) {
        NBTTagCompound nbtKnownPlayer = new NBTTagCompound();
        nbtKnownPlayer.func_74778_a("PlayerName", playerName);
        nbtPlayers.func_74742_a(nbtKnownPlayer);
      }
      nbtRoot.func_74782_a("KnownPlayers", nbtPlayers);
    }
    
    if (knownCreatureTypes.size() > 0) {
      NBTTagList nbtCreatureTypes = new NBTTagList();
      for (String typeName : knownCreatureTypes) {
        NBTTagCompound nbtKnownCreatureType = new NBTTagCompound();
        nbtKnownCreatureType.func_74778_a("CreatureTypeName", typeName);
        nbtCreatureTypes.func_74742_a(nbtKnownCreatureType);
      }
      nbtRoot.func_74782_a("KnownCreatureTypes", nbtCreatureTypes);
    }
    
    if (knownCreatures.size() > 0) {
      NBTTagList nbtCreatures = new NBTTagList();
      for (CreatureID creatureID : knownCreatures) {
        NBTTagCompound nbtKnownCreature = new NBTTagCompound();
        nbtKnownCreature.func_74772_a("CreatureMost", id.getMostSignificantBits());
        nbtKnownCreature.func_74772_a("CreatureLeast", id.getLeastSignificantBits());
        nbtKnownCreature.func_74778_a("CreatureName", name);
        nbtCreatures.func_74742_a(nbtKnownCreature);
      }
      nbtRoot.func_74782_a("KnownCreatures", nbtCreatures);
    }
    
    nbtRoot.func_74757_a("SentinalPlant", isSentinal());
  }
  
  public void func_70037_a(NBTTagCompound nbtRoot)
  {
    super.func_70037_a(nbtRoot);
    String s = nbtRoot.func_74779_i("Owner");
    
    if (s.length() > 0) {
      setOwner(s);
    }
    
    if (nbtRoot.func_74764_b("KnownPlayers")) {
      NBTTagList nbtPlayers = nbtRoot.func_150295_c("KnownPlayers", 10);
      knownPlayers = new ArrayList();
      for (int i = 0; i < nbtPlayers.func_74745_c(); i++) {
        NBTTagCompound nbtKnownPlayer = nbtPlayers.func_150305_b(i);
        String playerName = nbtKnownPlayer.func_74779_i("PlayerName");
        if ((playerName != null) && (!playerName.isEmpty())) {
          knownPlayers.add(playerName);
        }
      }
    }
    
    if (nbtRoot.func_74764_b("KnownCreatureTypes")) {
      NBTTagList nbtCreatureTypes = nbtRoot.func_150295_c("KnownCreatureTypes", 10);
      knownCreatureTypes = new ArrayList();
      for (int i = 0; i < nbtCreatureTypes.func_74745_c(); i++) {
        NBTTagCompound nbtKnownCreatureType = nbtCreatureTypes.func_150305_b(i);
        String typeName = nbtKnownCreatureType.func_74779_i("CreatureTypeName");
        if ((typeName != null) && (!typeName.isEmpty())) {
          knownCreatureTypes.add(typeName);
        }
      }
    }
    
    if (nbtRoot.func_74764_b("KnownCreatures")) {
      NBTTagList nbtCreatures = nbtRoot.func_150295_c("KnownCreatures", 10);
      knownCreatures = new ArrayList();
      for (int i = 0; i < nbtCreatures.func_74745_c(); i++) {
        NBTTagCompound nbtKnownCreature = nbtCreatures.func_150305_b(i);
        String playerName = nbtKnownCreature.func_74779_i("PlayerName");
        long uuidMost = nbtKnownCreature.func_74763_f("CreatureMost");
        long uuidLeast = nbtKnownCreature.func_74763_f("CreatureLeast");
        String cname = nbtKnownCreature.func_74779_i("CreatureName");
        if ((uuidMost != 0L) || (uuidLeast != 0L)) {
          UUID creatureID = new UUID(uuidMost, uuidLeast);
          knownCreatures.add(new CreatureID(creatureID, cname));
        }
      }
    }
    
    if (nbtRoot.func_74764_b("SentinalPlant")) {
      setSentinal(nbtRoot.func_74767_n("SentinalPlant"));
    }
  }
  
  public String getOwnerName() {
    return field_70180_af.func_75681_e(17);
  }
  
  public void setOwner(String par1Str) {
    func_110163_bv();
    field_70180_af.func_75692_b(17, par1Str);
  }
  
  public EntityPlayer getOwnerEntity() {
    return field_70170_p.func_72924_a(getOwnerName());
  }
  
  public void func_70071_h_()
  {
    super.func_70071_h_();
  }
  
  protected String func_70621_aR()
  {
    return "mob.silverfish.hit";
  }
  
  protected String func_70639_aQ()
  {
    return "witchery:mob.treefyd.treefyd_say";
  }
  
  protected String func_70673_aS()
  {
    return "mob.creeper.death";
  }
  
  protected Item func_146068_u()
  {
    return Item.func_150898_a(net.minecraft.init.Blocks.field_150328_O);
  }
  
  protected void func_70600_l(int par1) {
    func_70099_a(ItemsGENERIC.itemSeedsTreefyd.createStack(), 0.0F);
  }
  
  protected boolean func_70692_ba()
  {
    return false;
  }
}
