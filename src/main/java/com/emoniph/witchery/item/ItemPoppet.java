package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.blocks.BlockPoppetShelf.TileEntityPoppetShelf;
import com.emoniph.witchery.entity.EntityWitchHunter;
import com.emoniph.witchery.infusion.infusions.spirit.InfusedSpiritEffect;
import com.emoniph.witchery.network.PacketPipeline;
import com.emoniph.witchery.network.PacketPushTarget;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.SoundEffect;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
















public class ItemPoppet
  extends ItemBase
{
  private final ArrayList<PoppetType> poppetTypes = new ArrayList();
  
  public final PoppetType unboundPoppet = PoppetType.register(new PoppetType(0, "poppet", "Poppet", 0, null), poppetTypes);
  public final PoppetType earthPoppet = PoppetType.register(new PoppetType(1, "protectEarth", "Earth Protection Poppet", null).setDestroyOnUse(true), poppetTypes);
  
  public final PoppetType waterPoppet = PoppetType.register(new PoppetType(2, "protectWater", "Water Protection Poppet", null).setDestroyOnUse(true), poppetTypes);
  
  public final PoppetType firePoppet = PoppetType.register(new PoppetType(3, "protectFire", "Fire Protection Poppet", null).setDestroyOnUse(true), poppetTypes);
  
  public final PoppetType foodPoppet = PoppetType.register(new PoppetType(4, "protectStarvation", "Hunger Protection Poppet", null).setDestroyOnUse(true), poppetTypes);
  
  public final PoppetType toolPoppet = PoppetType.register(new PoppetType(5, "protectTool", "Tool Protection Poppet", null).setDestroyOnUse(true), poppetTypes);
  
  public final PoppetType deathPoppet = PoppetType.register(new PoppetType(6, "protectDeath", "Death Protection Poppet", 2, null).setDestroyOnUse(true), poppetTypes);
  
  public final PoppetType antiVoodooPoppet = PoppetType.register(new PoppetType(7, "protectVoodoo", "Voodoo Protection Poppet", null), poppetTypes);
  public final PoppetType voodooPoppet = PoppetType.register(new PoppetType(8, "voodoo", "Voodoo Poppet", null), poppetTypes);
  public final PoppetType vampiricPoppet = PoppetType.register(new PoppetType(9, "vampiric", "Vampiric Poppet", 2, null), poppetTypes);
  public final PoppetType poppetProtectionPoppet = PoppetType.register(new PoppetType(10, "protectPoppet", "Poppet Protection", 2, null), poppetTypes);
  public final PoppetType armorPoppet = PoppetType.register(new PoppetType(11, "protectArmor", "Armor Protection Poppet", null).setDestroyOnUse(true), poppetTypes);
  private static final String KEY_DAMAGE = "WITCDamage";
  
  public ItemPoppet() {
    setNoRepair();
    func_77625_d(1);
    func_77656_e(0);
    func_77627_a(true);
  }
  
  @SideOnly(Side.CLIENT)
  public EnumRarity func_77613_e(ItemStack itemstack)
  {
    return EnumRarity.values()[poppetTypes.get(itemstack.func_77960_j())).rarity];
  }
  
  public String func_77667_c(ItemStack itemStack)
  {
    int damage = itemStack.func_77960_j();
    assert ((damage >= 0) && (damage < poppetTypes.size())) : "damage value is too large";
    if ((damage >= 0) && (damage < poppetTypes.size())) {
      return ((PoppetType)poppetTypes.get(damage)).getUnlocalizedName();
    }
    return "";
  }
  

  public String func_77653_i(ItemStack itemstack)
  {
    if (vampiricPoppet.isMatch(itemstack)) {
      String sourceID = ItemsTAGLOCK_KIT.getBoundEntityDisplayName(itemstack, Integer.valueOf(1));
      String targetID = ItemsTAGLOCK_KIT.getBoundEntityDisplayName(itemstack, Integer.valueOf(2));
      String localizedName = super.func_77653_i(itemstack);
      if ((!sourceID.isEmpty()) && (!targetID.isEmpty()))
        return String.format("%s (%s -> %s)", new Object[] { localizedName, sourceID, targetID });
      if (!sourceID.isEmpty())
        return String.format("%s (%s -> ??)", new Object[] { localizedName, sourceID });
      if (!targetID.isEmpty()) {
        return String.format("%s (?? -> %s)", new Object[] { localizedName, targetID });
      }
      return localizedName;
    }
    
    String entityID = ItemsTAGLOCK_KIT.getBoundEntityDisplayName(itemstack, Integer.valueOf(1));
    String localizedName = super.func_77653_i(itemstack);
    return !entityID.isEmpty() ? String.format("%s (%s)", new Object[] { localizedName, entityID }) : localizedName;
  }
  

  public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean advTooltips)
  {
    if (vampiricPoppet.isMatch(stack)) {
      String sourceID = ItemsTAGLOCK_KIT.getBoundEntityDisplayName(stack, Integer.valueOf(1));
      String targetID = ItemsTAGLOCK_KIT.getBoundEntityDisplayName(stack, Integer.valueOf(2));
      String localizedName = super.func_77653_i(stack);
      if ((!sourceID.isEmpty()) && (!targetID.isEmpty())) {
        list.add(String.format(Witchery.resource("item.witcheryTaglockKit.boundto"), new Object[] { String.format("%s -> %s", new Object[] { sourceID, targetID }) }));
      } else if (!sourceID.isEmpty()) {
        list.add(String.format(Witchery.resource("item.witcheryTaglockKit.boundto"), new Object[] { String.format("%s -> ??", new Object[] { sourceID }) }));
      } else if (!targetID.isEmpty()) {
        list.add(String.format(Witchery.resource("item.witcheryTaglockKit.boundto"), new Object[] { String.format("?? -> %s", new Object[] { targetID }) }));
      } else {
        list.add(Witchery.resource("item.witcheryTaglockKit.unbound"));
      }
    } else {
      String entityID = ItemsTAGLOCK_KIT.getBoundEntityDisplayName(stack, Integer.valueOf(1));
      String localizedName = super.func_77653_i(stack);
      if ((entityID != null) && (!entityID.isEmpty())) {
        list.add(String.format(Witchery.resource("item.witcheryTaglockKit.boundto"), new Object[] { entityID }));
      } else {
        list.add(Witchery.resource("item.witcheryTaglockKit.unbound"));
      }
    }
  }
  
  public void func_94581_a(IIconRegister iconRegister)
  {
    for (PoppetType poppetType : poppetTypes) {
      poppetType.registerIcon(iconRegister, this);
    }
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_77617_a(int damage)
  {
    if ((damage < 0) || (damage >= poppetTypes.size())) {
      damage = 0;
    }
    return poppetTypes.get(damage)).icon;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_150895_a(Item item, CreativeTabs tab, List itemList)
  {
    for (PoppetType poppetType : poppetTypes) {
      itemList.add(new ItemStack(item, 1, damageValue));
    }
  }
  

  private static final int MAX_DAMAGE = 1000;
  private static final float AUTO_REPAIR_THRESHOLD = 0.1F;
  public void func_77622_d(ItemStack stack, World world, EntityPlayer player)
  {
    ensureNBT(stack);
    super.func_77622_d(stack, world, player);
  }
  
  public boolean func_77645_m()
  {
    return true;
  }
  
  public boolean isDamaged(ItemStack stack)
  {
    ensureNBT(stack);
    return getDamageNBT(stack) > 0;
  }
  


  public void setDamage(ItemStack stack, int damage) {}
  

  public int getDisplayDamage(ItemStack stack)
  {
    ensureNBT(stack);
    return getDamageNBT(stack);
  }
  
  public int func_77612_l()
  {
    return 1000;
  }
  
  private int getDamageNBT(ItemStack stack) {
    ensureNBT(stack);
    return stack.func_77978_p().func_74762_e("WITCDamage");
  }
  
  private void setDamageNBT(IInventory inventory, ItemStack stack, int damage) {
    ensureNBT(stack);
    damage = Math.min(damage, 1000);
    stack.func_77978_p().func_74768_a("WITCDamage", damage);
    if (damage == 1000) {
      field_77994_a = 0;
      if (inventory != null) {
        for (int i = 0; i < inventory.func_70302_i_(); i++) {
          if (inventory.func_70301_a(i) == stack) {
            inventory.func_70299_a(i, null);
            break;
          }
        }
      }
    }
  }
  
  private void ensureNBT(ItemStack stack) {
    if (!stack.func_77942_o()) {
      stack.func_77982_d(new NBTTagCompound());
    }
    
    if (!stack.func_77978_p().func_74764_b("WITCDamage")) {
      stack.func_77978_p().func_74768_a("WITCDamage", 0);
    }
  }
  
  public void func_77663_a(ItemStack stack, World world, Entity entity, int par4, boolean par5)
  {
    if ((!field_72995_K) && (voodooPoppet.isMatch(stack)))
    {
      if ((entity.func_70055_a(Material.field_151586_h)) && (entity.func_70086_ai() <= 0)) {
        EntityLivingBase boundEntity = ItemsTAGLOCK_KIT.getBoundEntity(world, entity, stack, Integer.valueOf(1));
        if ((boundEntity != null) && (boundEntity.func_70089_S())) {
          if (!voodooProtectionActivated((entity instanceof EntityPlayer) ? (EntityPlayer)entity : null, stack, boundEntity, true, false)) {
            if ((entity instanceof EntityPlayer)) {
              EntityWitchHunter.blackMagicPerformed((EntityPlayer)entity);
            }
            boolean damageDisabled = ((boundEntity instanceof EntityPlayer)) && (field_71075_bZ.field_75102_a);
            if ((!ItemHunterClothes.isMagicalProtectionActive(boundEntity)) && (!boundEntity.func_70648_aU()) && (!boundEntity.func_82165_m(field_76427_ofield_76415_H)) && (!damageDisabled))
            {


              for (int i = 0; i < 8; i++) {
                float f = field_73012_v.nextFloat() - field_73012_v.nextFloat();
                float f1 = field_73012_v.nextFloat() - field_73012_v.nextFloat();
                float f2 = field_73012_v.nextFloat() - field_73012_v.nextFloat();
                world.func_72869_a("bubble", field_70165_t + f, field_70163_u + f1, field_70161_v + f2, field_70159_w, field_70181_x, field_70179_y);
              }
              

              boundEntity.func_70097_a(DamageSource.field_76369_e, 1.0F);
            }
            boundEntity.func_70066_B();
          }
        }
      }
    }
    
    super.func_77663_a(stack, world, entity, par4, par5);
  }
  
  public ItemStack func_77659_a(ItemStack itemstack, World world, EntityPlayer player)
  {
    if (voodooPoppet.isMatch(itemstack)) {
      player.func_71008_a(itemstack, func_77626_a(itemstack));
    }
    
    return super.func_77659_a(itemstack, world, player);
  }
  
  public int func_77626_a(ItemStack par1ItemStack)
  {
    return 80;
  }
  
  public EnumAction func_77661_b(ItemStack par1ItemStack)
  {
    return EnumAction.bow;
  }
  
  private static class PoppetDamageSource extends EntityDamageSource {
    private PoppetDamageSource(DamageSource damageType, Entity source) {
      super(source);
      func_76348_h();
      func_82726_p();
    }
  }
  
  public void func_77615_a(ItemStack itemstack, World world, EntityPlayer player, int ticks)
  {
    if (!field_72995_K) {
      if (voodooPoppet.isMatch(itemstack)) {
        EntityLivingBase entity = ItemsTAGLOCK_KIT.getBoundEntity(world, player, itemstack, Integer.valueOf(1));
        if (entity != null) {
          EntityWitchHunter.blackMagicPerformed(player);
          

          MovingObjectPosition hitObject = func_77621_a(world, player, true);
          if ((hitObject != null) && (field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK)) {
            Material hitMaterial = world.func_147439_a(field_72311_b, field_72312_c, field_72309_d).func_149688_o();
            Block hitBlock = world.func_147439_a(field_72311_b, field_72312_c, field_72309_d);
            if (hitMaterial == Material.field_151587_i) {
              if ((!voodooProtectionActivated(player, itemstack, entity, true, false)) && (!ItemHunterClothes.isMagicalProtectionActive(player))) {
                entity.func_70015_d(10);
              }
              setDamageNBT(field_71071_by, itemstack, 1000);
              world.func_72956_a(player, "random.fizz", 0.4F, 2.0F + field_73012_v.nextFloat() * 0.4F);
              return;
            }
          }
          
          if (player.func_70093_af()) {
            if ((field_71075_bZ.field_75098_d) || (ItemsGENERIC.itemBoneNeedle.isItemInInventory(field_71071_by))) {
              DamageSource damageSource = new PoppetDamageSource(DamageSource.field_76376_m, player, null);
              if (!voodooProtectionActivated(player, itemstack, entity, true, false)) {
                entity.func_70097_a(damageSource, 0.5F);
                if (!field_71075_bZ.field_75098_d) {
                  ItemsGENERIC.itemBoneNeedle.consumeItemFromInventory(field_71071_by);
                  setDamageNBT(field_71071_by, itemstack, getDamageNBT(itemstack) + 10);
                }
              }
            }
            
            return;
          }
          if ((!voodooProtectionActivated(player, itemstack, entity, true, false)) && (!ItemHunterClothes.isMagicalProtectionActive(player))) {
            Vec3 look = player.func_70040_Z();
            float scaling = (func_77626_a(itemstack) - ticks) / 20;
            double motionX = field_72450_a * 0.9D * scaling;
            double motionY = field_72448_b * 0.3D * scaling;
            double motionZ = field_72449_c * 0.9D * scaling;
            if ((entity instanceof EntityPlayer)) {
              EntityPlayer targetPlayer = (EntityPlayer)entity;
              
              Witchery.packetPipeline.sendTo(new PacketPushTarget(motionX, motionY, motionZ), targetPlayer);
            } else {
              field_70159_w = motionX;
              field_70181_x = motionY;
              field_70179_y = motionZ;
            }
            setDamageNBT(field_71071_by, itemstack, getDamageNBT(itemstack) + 10);
          }
          return;
        }
      }
      

      super.func_77615_a(itemstack, world, player, ticks);
    }
  }
  
  public boolean voodooProtectionActivated(EntityPlayer attackingEntity, ItemStack voodooStack, EntityLivingBase targetEntity, int strength) {
    if ((strength > 1) && ((!(targetEntity instanceof EntityPlayer)) || (!InfusedSpiritEffect.POPPET_ENHANCEMENT.isNearTo((EntityPlayer)targetEntity)))) {
      for (int i = 1; i <= strength; i++) {
        boolean allowLightning = i == strength;
        if (!voodooProtectionActivated(attackingEntity, voodooStack, targetEntity, allowLightning, false)) {
          return false;
        }
      }
    } else {
      return voodooProtectionActivated(attackingEntity, voodooStack, targetEntity, true, false);
    }
    return true;
  }
  
  public boolean voodooProtectionActivated(EntityPlayer attackingEntity, ItemStack voodooStack, EntityLivingBase targetEntity, boolean allowLightning, boolean onlyBoosted) {
    int ITEM_DAMAGE = 350;
    if ((targetEntity instanceof EntityPlayer)) {
      EntityPlayer targetPlayer = (EntityPlayer)targetEntity;
      ItemStack defenseStack = findBoundPoppetInWorld(antiVoodooPoppet, targetPlayer, 350, false, onlyBoosted);
      if ((defenseStack != null) && (!field_70170_p.field_72995_K)) {
        if ((attackingEntity != null) && (voodooStack != null)) {
          setDamageNBT(field_71071_by, voodooStack, getDamageNBT(voodooStack) + 350);
        }
        
        if ((attackingEntity != null) && (allowLightning)) {
          EntityLightningBolt lightning = new EntityLightningBolt(field_70170_p, field_70165_t, field_70163_u, field_70161_v);
          
          field_70170_p.func_72942_c(lightning);
        }
        
        return true;
      }
    }
    return false;
  }
  
  public boolean poppetProtectionActivated(EntityPlayer attackingEntity, ItemStack voodooStack, EntityLivingBase targetEntity, boolean allowLightning) {
    int ITEM_DAMAGE = 350;
    if ((targetEntity instanceof EntityPlayer)) {
      EntityPlayer targetPlayer = (EntityPlayer)targetEntity;
      ItemStack defenseStack = findBoundPoppetInWorld(poppetProtectionPoppet, targetPlayer, 350);
      if ((defenseStack != null) && (!field_70170_p.field_72995_K)) {
        if (voodooStack != null) {
          setDamageNBT(field_71071_by, voodooStack, getDamageNBT(voodooStack) + 350);
        }
        
        if ((attackingEntity != null) && (allowLightning)) {
          EntityLightningBolt lightning = new EntityLightningBolt(field_70170_p, field_70165_t, field_70163_u, field_70161_v);
          
          field_70170_p.func_72942_c(lightning);
        }
        
        return true;
      }
    }
    return false;
  }
  
  public void destroyAntiVoodooPoppets(EntityPlayer attackingEntity, EntityLivingBase targetEntity, int poppetsToDestroy) {
    int ITEM_DAMAGE = 1000;
    int MAX = 1000;
    if ((targetEntity instanceof EntityPlayer)) {
      EntityPlayer targetPlayer = (EntityPlayer)targetEntity;
      
      for (int i = 0; i < poppetsToDestroy; i++) {
        ItemStack defenseStack = findBoundPoppetInWorld(antiVoodooPoppet, targetPlayer, 1000);
        if (defenseStack == null) {
          return;
        }
      }
    }
  }
  
  public static ItemStack findBoundPoppetInWorld(PoppetType poppetType, EntityPlayer player, int foundItemDamage)
  {
    return findBoundPoppetInWorld(poppetType, player, foundItemDamage, false, false);
  }
  
  public static ItemStack findBoundPoppetInWorld(PoppetType poppetType, EntityPlayer player, int foundItemDamage, boolean allIndices, boolean onlyBoosted)
  {
    if (ItemHunterClothes.isFullSetWorn(player, false)) {
      return null;
    }
    
    int damageValue = poppetType.damageValue;
    
    ItemStack poppetStack = findBoundPoppetInInventory(ItemsPOPPET, damageValue, player, field_71071_by, foundItemDamage, allIndices, onlyBoosted);
    if (poppetStack != null) {
      return poppetStack;
    }
    
    if ((!field_70170_p.field_72995_K) && (!onlyBoosted)) {
      MinecraftServer server = MinecraftServer.func_71276_C();
      for (WorldServer world : field_71305_c) {
        if ((!instancerestrictPoppetShelvesToVanillaAndSpiritDimensions) || (field_73011_w.field_76574_g == 0) || (field_73011_w.field_76574_g == -1) || (field_73011_w.field_76574_g == 1) || (field_73011_w.field_76574_g == instancedimensionDreamID))
        {



          for (Object obj : field_147482_g) {
            if ((obj instanceof BlockPoppetShelf.TileEntityPoppetShelf)) {
              poppetStack = findBoundPoppetInInventory(ItemsPOPPET, damageValue, player, (IInventory)obj, foundItemDamage, allIndices, false);
              if (poppetStack != null) {
                return poppetStack;
              }
            }
          }
        }
      }
    }
    return null;
  }
  
  private static ItemStack findBoundPoppetInInventory(Item item, int damage, EntityPlayer player, IInventory inventory, int foundItemDamage, boolean allIndices, boolean onlyBoosted) {
    for (int i = 0; i < inventory.func_70302_i_(); i++) {
      ItemStack itemstack = inventory.func_70301_a(i);
      if ((itemstack != null) && (itemstack.func_77973_b() == item) && (itemstack.func_77960_j() == damage) && (ItemsTAGLOCK_KIT.containsTaglockForEntity(itemstack, player, Integer.valueOf(1))) && ((!allIndices) || (ItemsTAGLOCK_KIT.isTaglockPresent(itemstack, Integer.valueOf(2)))))
      {

        if (onlyBoosted) {
          if (InfusedSpiritEffect.POPPET_ENHANCEMENT.isNearTo(player)) {
            ((PoppetType)ItemsPOPPET.poppetTypes.get(damage)).applyDamageOnUse(inventory, i, itemstack, foundItemDamage);
            return itemstack;
          }
          return null;
        }
        
        ((PoppetType)ItemsPOPPET.poppetTypes.get(damage)).applyDamageOnUse(inventory, i, itemstack, foundItemDamage);
        return itemstack;
      }
    }
    
    return null;
  }
  
  public void addDamageToPoppet(ItemStack sourcePoppet, ItemStack destPoppet) {
    setDamageNBT(null, destPoppet, getDamageNBT(sourcePoppet));
  }
  
  public void cancelEventIfPoppetFound(EntityPlayer player, PoppetType poppetType, LivingHurtEvent event, boolean heal) {
    cancelEventIfPoppetFound(player, poppetType, event, heal, false);
  }
  
  public void cancelEventIfPoppetFound(EntityPlayer player, PoppetType poppetType, LivingHurtEvent event, boolean heal, boolean onlyHandheld) {
    ItemStack stack = findBoundPoppetInWorld(poppetType, player, 1000, false, onlyHandheld);
    if (stack != null) {
      event.setCanceled(true);
      if ((heal) && (player.func_110143_aJ() < 10.0F)) {
        player.func_70606_j(10.0F);
      }
      
      SoundEffect.RANDOM_ORB.playAtPlayer(field_70170_p, player);
    }
  }
  

  public void checkForArmorProtection(EntityPlayer player)
  {
    for (int i = 0; i < field_71071_by.field_70460_b.length; i++) {
      ItemStack armorPiece = field_71071_by.field_70460_b[i];
      if ((armorPiece != null) && (armorPiece.func_77984_f())) {
        int itemDamage = armorPiece.func_77960_j();
        int maxDamage = armorPiece.func_77958_k();
        int repairThreshold = (int)(maxDamage * 0.9F);
        if (itemDamage >= repairThreshold) {
          ItemStack protectStack = findBoundPoppetInWorld(ItemsPOPPET.armorPoppet, player, 1000);
          if (protectStack != null) {
            armorPiece.func_77964_b(0);
            field_70170_p.func_72956_a(player, "random.orb", 0.5F, 0.4F / ((float)field_70170_p.field_73012_v.nextDouble() * 0.4F + 0.8F));
          }
        }
      }
    }
  }
  
  public static class PoppetEventHooks { public PoppetEventHooks() {}
    
    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent event) { if (!entityPlayer.field_70170_p.field_72995_K) {
        EntityPlayer player = entityPlayer;
        ItemStack heldItem = player.func_70694_bm();
        if ((heldItem != null) && (heldItem.func_77984_f())) {
          int itemDamage = heldItem.func_77960_j();
          int maxDamage = heldItem.func_77958_k();
          int repairThreshold = (int)(maxDamage * 0.9F);
          if (itemDamage >= repairThreshold) {
            ItemStack protectStack = ItemPoppet.findBoundPoppetInWorld(ItemsPOPPET.toolPoppet, player, 1000);
            if (protectStack != null) {
              heldItem.func_77964_b(0);
              
              field_70170_p.func_72956_a(player, "random.orb", 0.5F, 0.4F / ((float)field_70170_p.field_73012_v.nextDouble() * 0.4F + 0.8F)); } } } } } }
  
  public static class PoppetType { public final int damageValue;
    private final String unlocalizedName;
    private final String localizedName;
    private final int rarity;
    @SideOnly(Side.CLIENT)
    private IIcon icon;
    private boolean destroyOnUse;
    
    private static PoppetType register(PoppetType poppetType, ArrayList<PoppetType> poppetTypes) { poppetTypes.add(poppetType);
      return poppetType;
    }
    









    private PoppetType(int damageValue, String unlocalizedName, String localizedName)
    {
      this(damageValue, unlocalizedName, localizedName, 1);
    }
    
    private PoppetType(int damageValue, String unlocalizedName, String localizedName, int rarity) {
      this.damageValue = damageValue;
      this.unlocalizedName = unlocalizedName;
      this.localizedName = localizedName;
      this.rarity = rarity;
    }
    
    public ItemStack createStack() {
      return createStack(1);
    }
    
    public ItemStack createStack(int quantity) {
      return new ItemStack(ItemsPOPPET, quantity, damageValue);
    }
    
    public boolean isMatch(ItemStack itemstack) {
      return (itemstack != null) && (itemstack.func_77960_j() == damageValue);
    }
    
    private String getUnlocalizedName() {
      if (damageValue > 0) {
        return String.format("%s.%s", new Object[] { ItemsPOPPET.func_77658_a(), unlocalizedName });
      }
      return ItemsPOPPET.func_77658_a();
    }
    
    @SideOnly(Side.CLIENT)
    private PoppetType registerIcon(IIconRegister iconRegister, ItemPoppet item)
    {
      if (unlocalizedName.equals("poppet")) {
        icon = iconRegister.func_94245_a(item.func_111208_A());
      } else {
        icon = iconRegister.func_94245_a(item.func_111208_A() + "." + unlocalizedName);
      }
      return this;
    }
    
    public PoppetType setDestroyOnUse(boolean destroyOnUse) {
      this.destroyOnUse = destroyOnUse;
      return this;
    }
    



    private boolean applyDamageOnUse(IInventory inventory, int i, ItemStack itemstack, int itemDamage)
    {
      if (destroyOnUse) {
        inventory.func_70299_a(i, null);
        field_77994_a = 0;
      } else {
        ItemsPOPPET.setDamageNBT(inventory, itemstack, ItemPoppet.access$1100(ItemsPOPPET, itemstack) + itemDamage);
      }
      return false;
    }
  }
}
