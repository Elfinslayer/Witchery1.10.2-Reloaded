package com.emoniph.witchery.familiar;

import com.emoniph.witchery.entity.EntityOwl;
import com.emoniph.witchery.entity.EntityToad;
import com.emoniph.witchery.entity.EntityWitchCat;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TameableUtil;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;




public abstract class Familiar
{
  private static final String[] NAMES_TOAD = { "Casper", "Wart", "Langston", "Croaker", "Prince Charming", "Frog-n-stien", "Randolph", "Evileye", "Churchill", "Santa", "Dillinger", "Spuds" };
  
  private static final String[] NAMES_CAT = { "Pyewackett", "Salem", "Gobbolino", "Sabbath", "Norris", "Crookshanks", "Binx", "Voodoo", "Raven", "Simpkin", "Fishbone", "Kismet" };
  
  private static final String[] NAMES_OWL = { "Archimedes", "Dumbledornithologist", "Al Travis", "Baltimore", "Cornelius", "Hadwig", "Hoot", "Merlin", "Owl Capone", "Pigwidgeon", "Athena", "Albertine" };
  
  private static final String FAMILIAR_TAG_KEY = "WITCFamiliar";
  
  private static final String FAMILIAR_UUID_MOST = "UUIDMost";
  
  private static final String FAMILIAR_UUID_LEAST = "UUIDLeast";
  
  private static final String FAMILIAR_NAME = "FamiliarName";
  private static final String FAMILIAR_TYPE = "FamiliarType";
  private static final String FAMILIAR_COLOR = "FamiliarColor";
  private static final String FAMILIAR_SUMMONED = "FamiliarSummoned";
  
  public Familiar() {}
  
  public static void bindToPlayer(EntityPlayer player, EntityTameable familiarEntity)
  {
    if ((canBecomeFamiliar(familiarEntity)) && (TameableUtil.isOwner(familiarEntity, player))) {
      NBTTagCompound nbtTag = Infusion.getNBT(player);
      if (nbtTag != null) {
        EntityTameable currentFamiliar = getFamiliarEntity(player);
        if (currentFamiliar != null) {
          ((IFamiliar)currentFamiliar).clearFamiliar();
        }
        
        if ((familiarEntity instanceof EntityOcelot)) {
          EntityOcelot oldCat = (EntityOcelot)familiarEntity;
          EntityWitchCat newCat = new EntityWitchCat(field_70170_p);
          newCat.cloneOcelot(oldCat);
          newCat.func_70912_b(1);
          oldCat.func_70106_y();
          field_70170_p.func_72838_d(newCat);
          field_70170_p.func_72960_a(newCat, (byte)7);
          familiarEntity = newCat;
        }
        
        IFamiliar familiar = (IFamiliar)familiarEntity;
        
        NBTTagCompound nbtFamiliar = new NBTTagCompound();
        nbtFamiliar.func_74772_a("UUIDMost", familiarEntity.func_110124_au().getMostSignificantBits());
        nbtFamiliar.func_74772_a("UUIDLeast", familiarEntity.func_110124_au().getLeastSignificantBits());
        
        String name = "Familiar";
        if ((familiarEntity instanceof EntityOwl)) {
          name = NAMES_OWL[field_70170_p.field_73012_v.nextInt(NAMES_OWL.length)];
          nbtFamiliar.func_74768_a("FamiliarType", 3);
          nbtFamiliar.func_74774_a("FamiliarColor", Byte.valueOf((byte)((EntityOwl)familiar).getFeatherColor()).byteValue());
        } else if ((familiarEntity instanceof EntityToad)) {
          name = NAMES_TOAD[field_70170_p.field_73012_v.nextInt(NAMES_OWL.length)];
          nbtFamiliar.func_74768_a("FamiliarType", 2);
          nbtFamiliar.func_74774_a("FamiliarColor", Byte.valueOf((byte)((EntityToad)familiar).getSkinColor()).byteValue());
        } else if ((familiarEntity instanceof EntityOcelot)) {
          name = NAMES_CAT[field_70170_p.field_73012_v.nextInt(NAMES_OWL.length)];
          nbtFamiliar.func_74768_a("FamiliarType", 1);
          nbtFamiliar.func_74774_a("FamiliarColor", Byte.valueOf((byte)0).byteValue());
        }
        
        if ((!familiarEntity.func_94056_bM()) && (name != null) && (!name.isEmpty())) {
          familiarEntity.func_94058_c(name);
        }
        
        nbtFamiliar.func_74778_a("FamiliarName", familiarEntity.func_94057_bL());
        nbtFamiliar.func_74774_a("FamiliarSummoned", Byte.valueOf((byte)1).byteValue());
        
        nbtTag.func_74782_a("WITCFamiliar", nbtFamiliar);
        
        familiar.setMaxHealth(50.0F);
      }
    }
  }
  
  public static boolean canBecomeFamiliar(EntityTameable familiarEntity) {
    return (familiarEntity != null) && (familiarEntity.func_70909_n()) && (((familiarEntity instanceof EntityWitchCat)) || ((familiarEntity instanceof EntityOcelot)) || ((familiarEntity instanceof EntityToad)) || ((familiarEntity instanceof EntityOwl)));
  }
  

  public static EntityTameable getFamiliarEntityByID(EntityPlayer player, UUID uuidFamiliar)
  {
    if (uuidFamiliar != null) {
      List list = field_70170_p.field_72996_f;
      for (int i = 0; i < list.size(); i++) {
        Object obj = list.get(i);
        if ((obj instanceof EntityTameable)) {
          EntityTameable tameableEntity = (EntityTameable)obj;
          if (tameableEntity.func_110124_au().equals(uuidFamiliar)) {
            return tameableEntity;
          }
        }
      }
      
      if (!field_70170_p.field_72995_K) {
        MinecraftServer server = MinecraftServer.func_71276_C();
        for (WorldServer worldServer : field_71305_c) {
          List list2 = field_72996_f;
          for (int i = 0; i < list2.size(); i++) {
            Object obj = list2.get(i);
            if ((obj instanceof EntityTameable)) {
              EntityTameable tameableEntity = (EntityTameable)obj;
              if (tameableEntity.func_110124_au().equals(uuidFamiliar)) {
                return tameableEntity;
              }
            }
          }
        }
      }
    }
    

    return null;
  }
  
  public static EntityTameable getFamiliarEntity(EntityPlayer player) {
    UUID uuidFamiliar = getFamiliarEntityID(player);
    EntityTameable familiar = getFamiliarEntityByID(player, uuidFamiliar);
    return familiar;
  }
  
  public static UUID getFamiliarEntityID(EntityPlayer player) {
    if (player != null) {
      NBTTagCompound nbtTag = Infusion.getNBT(player);
      if ((nbtTag != null) && (nbtTag.func_74764_b("WITCFamiliar"))) {
        NBTTagCompound nbtFamiliar = nbtTag.func_74775_l("WITCFamiliar");
        if ((nbtFamiliar != null) && (nbtFamiliar.func_74764_b("UUIDMost")) && (nbtFamiliar.func_74764_b("UUIDLeast"))) {
          UUID uuidFamiliar = new UUID(nbtFamiliar.func_74763_f("UUIDMost"), nbtFamiliar.func_74763_f("UUIDLeast"));
          return uuidFamiliar;
        }
      }
    }
    return null;
  }
  
  public static boolean isPlayerBoundToFamiliar(EntityPlayer player, EntityTameable familiar) {
    if ((player != null) && (familiar != null)) {
      NBTTagCompound nbtTag = Infusion.getNBT(player);
      if ((nbtTag != null) && (nbtTag.func_74764_b("WITCFamiliar"))) {
        NBTTagCompound nbtFamiliar = nbtTag.func_74775_l("WITCFamiliar");
        if ((nbtFamiliar != null) && (nbtFamiliar.func_74764_b("UUIDMost")) && (nbtFamiliar.func_74764_b("UUIDLeast"))) {
          UUID uuidFamiliar = new UUID(nbtFamiliar.func_74763_f("UUIDMost"), nbtFamiliar.func_74763_f("UUIDLeast"));
          return uuidFamiliar.equals(familiar.func_110124_au());
        }
      }
    }
    return false;
  }
  
  public static class FamiliarOwner
  {
    private final EntityPlayer player;
    private final boolean owner;
    
    public FamiliarOwner(EntityPlayer player, boolean owner) {
      this.player = player;
      this.owner = owner;
    }
    
    public EntityPlayer getPlayer() {
      return player;
    }
    
    public boolean isOwner() {
      return owner;
    }
    
    public EntityPlayer getCurrentOwner() {
      return owner ? player : null;
    }
  }
  
  public static FamiliarOwner getOwnerForFamiliar(EntityTameable familiar) {
    if ((familiar != null) && (!field_70170_p.field_72995_K) && (familiar.func_70909_n())) {
      EntityLivingBase owner = familiar.func_70902_q();
      if ((owner != null) && ((owner instanceof EntityPlayer))) {
        EntityPlayer player = (EntityPlayer)owner;
        UUID uuidFamiliar = getFamiliarEntityID(player);
        if ((uuidFamiliar != null) && (uuidFamiliar.equals(familiar.func_110124_au()))) {
          return new FamiliarOwner(player, true);
        }
        return new FamiliarOwner(player, false);
      }
    }
    
    return new FamiliarOwner(null, false);
  }
  
  public static boolean hasActiveCurseMasteryFamiliar(EntityPlayer player) {
    int familiarType = getActiveFamiliarType(player);
    return familiarType == 1;
  }
  
  public static boolean hasActiveBrewMasteryFamiliar(EntityPlayer player) {
    int familiarType = getActiveFamiliarType(player);
    return familiarType == 2;
  }
  
  public static boolean hasActiveBroomMasteryFamiliar(EntityPlayer player) {
    int familiarType = getActiveFamiliarType(player);
    return familiarType == 3;
  }
  
  public static boolean hasActiveFamiliar(EntityPlayer player) {
    int familiarType = getActiveFamiliarType(player);
    return familiarType > 0;
  }
  
  public static int getActiveFamiliarType(EntityPlayer player) {
    if ((player != null) && (!field_70170_p.field_72995_K)) {
      NBTTagCompound nbtTag = Infusion.getNBT(player);
      if ((nbtTag != null) && (nbtTag.func_74764_b("WITCFamiliar"))) {
        NBTTagCompound nbtFamiliar = nbtTag.func_74775_l("WITCFamiliar");
        if ((nbtFamiliar.func_74764_b("FamiliarSummoned")) && (nbtFamiliar.func_74764_b("FamiliarType")) && (nbtFamiliar.func_74764_b("FamiliarName"))) {
          int summoned = nbtFamiliar.func_74771_c("FamiliarSummoned");
          if (summoned == 1) {
            int type = nbtFamiliar.func_74762_e("FamiliarType");
            return type;
          }
        }
      }
    }
    return 0;
  }
  




  public static void handlePlayerHurt(LivingHurtEvent event, EntityPlayer player)
  {
    World world = entityLiving.field_70170_p;
    if ((!field_72995_K) && (!event.isCanceled()))
    {
      UUID familiarID = getFamiliarEntityID(player);
      if (familiarID != null) {
        float totalDamage = ammount;
        float redirectedDamage = totalDamage * 0.01F;
        EntityTameable familiar = getFamiliarEntityByID(player, familiarID);
        if (familiar != null) {
          if (familiar.func_70068_e(player) <= 576.0D) {
            redirectedDamage = totalDamage * 0.1F;
          }
          if (redirectedDamage >= 1.0F) {
            familiar.func_70097_a(source, redirectedDamage);
          }
        }
        ammount -= redirectedDamage;
      }
    }
  }
  
  public static void handleLivingDeath(LivingDeathEvent event) {
    World world = entityLiving.field_70170_p;
    if ((!field_72995_K) && (!event.isCanceled()))
    {
      if ((entityLiving instanceof EntityTameable)) {
        EntityTameable tameableEntity = (EntityTameable)entityLiving;
        if (couldBeFamiliar(tameableEntity)) {
          FamiliarOwner owner = getOwnerForFamiliar(tameableEntity);
          if ((player != null) && (owner.isOwner())) {
            NBTTagCompound nbtTag = Infusion.getNBT(player);
            player.func_70097_a(DamageSource.field_76376_m, player.func_110138_aP() * 2.0F);
            dismissFamiliar(player, tameableEntity);
            event.setCanceled(true);
          } else if (player == null) {
            tameableEntity.func_70606_j(1.0F);
            event.setCanceled(true);
          }
        }
      } else if ((entityLiving instanceof EntityPlayer)) {
        EntityPlayer player = (EntityPlayer)entityLiving;
        EntityTameable familiar = getFamiliarEntity(player);
        if ((familiar != null) && (!field_70128_L)) {
          dismissFamiliar(player, familiar);
        }
      }
    }
  }
  
  public static void dismissFamiliar(EntityPlayer player, EntityTameable familiar) {
    if ((player != null) && (familiar != null) && (!field_70170_p.field_72995_K) && 
      (isPlayerBoundToFamiliar(player, familiar))) {
      NBTTagCompound nbtTag = Infusion.getNBT(player);
      if ((nbtTag != null) && (nbtTag.func_74764_b("WITCFamiliar"))) {
        NBTTagCompound nbtFamiliar = nbtTag.func_74775_l("WITCFamiliar");
        nbtFamiliar.func_74778_a("FamiliarName", familiar.func_94057_bL());
        nbtFamiliar.func_74774_a("FamiliarSummoned", Byte.valueOf((byte)0).byteValue());
        if ((familiar instanceof EntityOwl)) {
          nbtFamiliar.func_74774_a("FamiliarColor", Byte.valueOf((byte)((EntityOwl)familiar).getFeatherColor()).byteValue());
        } else if ((familiar instanceof EntityToad)) {
          nbtFamiliar.func_74774_a("FamiliarColor", Byte.valueOf((byte)((EntityToad)familiar).getSkinColor()).byteValue());
        }
        
        ParticleEffect.INSTANT_SPELL.send(SoundEffect.MOB_ENDERMEN_PORTAL, familiar, 1.0D, 1.0D, 16);
        
        familiar.func_70106_y();
      }
    }
  }
  
  public static String getFamiliarName(EntityPlayer player)
  {
    NBTTagCompound nbtTag = Infusion.getNBT(player);
    if ((nbtTag != null) && (nbtTag.func_74764_b("WITCFamiliar"))) {
      NBTTagCompound nbtFamiliar = nbtTag.func_74775_l("WITCFamiliar");
      if ((nbtFamiliar.func_74764_b("FamiliarSummoned")) && (nbtFamiliar.func_74764_b("FamiliarType")) && (nbtFamiliar.func_74764_b("FamiliarName"))) {
        int summoned = nbtFamiliar.func_74771_c("FamiliarSummoned");
        String name = nbtFamiliar.func_74779_i("FamiliarName");
        return name;
      }
    }
    return null;
  }
  
  public static EntityTameable summonFamiliar(EntityPlayer player, double x, double y, double z) {
    if ((player != null) && (!field_70170_p.field_72995_K)) {
      NBTTagCompound nbtTag = Infusion.getNBT(player);
      if ((nbtTag != null) && (nbtTag.func_74764_b("WITCFamiliar"))) {
        NBTTagCompound nbtFamiliar = nbtTag.func_74775_l("WITCFamiliar");
        if ((nbtFamiliar.func_74764_b("FamiliarSummoned")) && (nbtFamiliar.func_74764_b("FamiliarType")) && (nbtFamiliar.func_74764_b("FamiliarName"))) {
          int summoned = nbtFamiliar.func_74771_c("FamiliarSummoned");
          if (summoned == 0) {
            String name = nbtFamiliar.func_74779_i("FamiliarName");
            int type = nbtFamiliar.func_74762_e("FamiliarType");
            int color = nbtFamiliar.func_74771_c("FamiliarColor");
            
            EntityTameable familiar = null;
            switch (type) {
            case 1: 
              familiar = new EntityWitchCat(field_70170_p);
              break;
            case 2: 
              familiar = new EntityToad(field_70170_p);
              ((EntityToad)familiar).setSkinColor(color);
              break;
            case 3: 
              familiar = new EntityOwl(field_70170_p);
              ((EntityOwl)familiar).setFeatherColor(color);
              break;
            default: 
              return null;
            }
            
            familiar.func_70903_f(true);
            TameableUtil.setOwner(familiar, player);
            familiar.func_94058_c(name);
            ((IFamiliar)familiar).setMaxHealth(50.0F);
            familiar.func_70012_b(x, y, z, 0.0F, 0.0F);
            field_70170_p.func_72838_d(familiar);
            
            nbtFamiliar.func_74772_a("UUIDMost", familiar.func_110124_au().getMostSignificantBits());
            nbtFamiliar.func_74772_a("UUIDLeast", familiar.func_110124_au().getLeastSignificantBits());
            
            ParticleEffect.INSTANT_SPELL.send(SoundEffect.MOB_ENDERMEN_PORTAL, familiar, 1.0D, 1.0D, 16);
            
            nbtFamiliar.func_74774_a("FamiliarSummoned", Byte.valueOf((byte)1).byteValue());
            return familiar;
          }
        }
      }
    }
    return null;
  }
  





  public static final int FAMILIAR_NONE = 0;
  




  public static final int FAMILIAR_CAT = 1;
  




  public static final int FAMILIAR_TOAD = 2;
  




  public static final int FAMILIAR_OWL = 3;
  




  private static final float REDIRECTED_DAMAGE_PCT_FAR = 0.01F;
  



  private static final float REDIRECTED_DAMAGE_PCT_NEAR = 0.1F;
  



  private static final float MAX_HEALTH = 50.0F;
  



  private static final float FAMILIAR_NEAR_DISTANCE_SQ = 576.0F;
  



  public static boolean couldBeFamiliar(EntityTameable entity)
  {
    if ((entity instanceof IFamiliar)) {
      IFamiliar familiar = (IFamiliar)entity;
      return familiar.isFamiliar();
    }
    
    return false;
  }
}
