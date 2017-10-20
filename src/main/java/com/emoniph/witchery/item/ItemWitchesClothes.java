package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.client.model.ModelWitchesClothes;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.util.Config;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemWitchesClothes extends ItemArmor
{
  private static final int CHARGE_PER_PIECE = 2;
  @SideOnly(Side.CLIENT)
  private ModelWitchesClothes modelClothesChest;
  @SideOnly(Side.CLIENT)
  private ModelWitchesClothes modelNecroChest;
  @SideOnly(Side.CLIENT)
  private ModelWitchesClothes modelClothesLegs;
  private static final String BIBLIOCRAFT_ARMOR_STAND_ENTITY_NAME = "AbstractSteve";
  
  public ItemWitchesClothes(int armorSlot)
  {
    super(net.minecraft.item.ItemArmor.ArmorMaterial.CLOTH, 1, armorSlot);
    func_77637_a(com.emoniph.witchery.WitcheryCreativeTab.INSTANCE);
  }
  
  public net.minecraft.item.Item func_77655_b(String itemName)
  {
    com.emoniph.witchery.util.ItemUtil.registerItem(this, itemName);
    return super.func_77655_b(itemName);
  }
  
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
  {
    if ((stack != null) && ((stack.func_77973_b() == ItemsWITCH_HAT) || (stack.func_77973_b() == ItemsWITCH_ROBES) || (stack.func_77973_b() == ItemsNECROMANCERS_ROBES) || (stack.func_77973_b() == ItemsICY_SLIPPERS) || (stack.func_77973_b() == ItemsRUBY_SLIPPERS) || (stack.func_77973_b() == ItemsSEEPING_SHOES) || (stack.func_77973_b() == ItemsBABAS_HAT)))
    {



      return "witchery:textures/entities/witchclothes" + (type == null ? "" : "_overlay") + ".png"; }
    if ((stack != null) && (stack.func_77973_b() == ItemsBITING_BELT))
      return "witchery:textures/entities/witchclothes_legs" + (type == null ? "" : "_overlay") + ".png";
    if ((stack != null) && (stack.func_77973_b() == ItemsBARK_BELT)) {
      return "witchery:textures/entities/witchclothes" + (type == null ? "2_legs" : "_legs_overlay") + ".png";
    }
    return null;
  }
  


  public int getMaxChargeLevel(EntityLivingBase entity)
  {
    int level = 0;
    for (int i = 1; i <= 4; i++) {
      ItemStack stack = entity.func_71124_b(i);
      if ((stack != null) && ((stack.func_77973_b() instanceof ItemWitchesClothes))) {
        level += 2;
      }
    }
    
    return level;
  }
  
  public void setChargeLevel(ItemStack stack, int level) {
    if (!stack.func_77942_o()) {
      stack.func_77982_d(new NBTTagCompound());
    }
    NBTTagCompound nbtRoot = stack.func_77978_p();
    nbtRoot.func_74768_a("WITCBarkPieces", level);
  }
  
  public int getChargeLevel(ItemStack stack) {
    if (stack.func_77942_o()) {
      NBTTagCompound nbtRoot = stack.func_77978_p();
      if (nbtRoot.func_74764_b("WITCBarkPieces")) {
        return nbtRoot.func_74762_e("WITCBarkPieces");
      }
    }
    
    return 0;
  }
  
  public boolean func_82816_b_(ItemStack stack)
  {
    return (stack == null) || (stack.func_77973_b() != ItemsBABAS_HAT);
  }
  
  public int func_82814_b(ItemStack stack)
  {
    if (!func_82816_b_(stack)) {
      return super.func_82814_b(stack);
    }
    if (stack.func_77973_b() == ItemsRUBY_SLIPPERS) {
      return 14483456;
    }
    int color = super.func_82814_b(stack);
    if (color == 10511680) {
      if (this == ItemsICY_SLIPPERS) {
        color = 7842303;
      } else if (this == ItemsSEEPING_SHOES) {
        color = 2254387;
      } else if (this == ItemsBARK_BELT) {
        color = 6968628;
      } else {
        color = 2628115;
      }
    }
    return color;
  }
  

  @SideOnly(Side.CLIENT)
  public int func_82790_a(ItemStack stack, int par2)
  {
    return super.func_82790_a(stack, par2);
  }
  
  @SideOnly(Side.CLIENT)
  public boolean func_77623_v()
  {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public net.minecraft.util.IIcon func_77618_c(int par1, int par2)
  {
    return func_77617_a(par1);
  }
  
  public boolean isHatWorn(EntityPlayer player) {
    return (field_71071_by.func_70440_f(3) != null) && (field_71071_by.func_70440_f(3).func_77973_b() == this);
  }
  
  public boolean isRobeWorn(EntityPlayer player) {
    return (field_71071_by.func_70440_f(2) != null) && (field_71071_by.func_70440_f(2).func_77973_b() == this);
  }
  
  public boolean isBeltWorn(EntityPlayer player) {
    return (field_71071_by.func_70440_f(1) != null) && (field_71071_by.func_70440_f(1).func_77973_b() == this);
  }
  











  @SideOnly(Side.CLIENT)
  public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack stack, int armorSlot)
  {
    if (modelClothesChest == null) {
      modelClothesChest = new ModelWitchesClothes(0.61F, false);
    }
    
    if (modelNecroChest == null) {
      modelNecroChest = new ModelWitchesClothes(0.61F, true);
    }
    
    if (modelClothesLegs == null) {
      modelClothesLegs = new ModelWitchesClothes(0.45F, false);
    }
    
    ModelBiped armorModel = null;
    if ((stack != null) && ((stack.func_77973_b() instanceof ItemWitchesClothes))) {
      int type = func_77973_bfield_77881_a;
      
      if ((type == 1) || (type == 3)) {
        armorModel = stack.func_77973_b() == ItemsNECROMANCERS_ROBES ? modelNecroChest : modelClothesChest;
      } else {
        armorModel = modelClothesLegs;
      }
      
      if (armorModel != null) {
        boolean isVisible = true;
        if ((entityLiving != null) && (entityLiving.func_82150_aj())) {
          String entityTypeName = entityLiving.getClass().getSimpleName();
          isVisible = (entityTypeName == null) || (entityTypeName.isEmpty()) || (entityTypeName.equals("AbstractSteve"));
        }
        

        field_78116_c.field_78806_j = ((isVisible) && (armorSlot == 0));
        field_78114_d.field_78806_j = ((isVisible) && (armorSlot == 0));
        field_78115_e.field_78806_j = ((isVisible) && ((armorSlot == 1) || (armorSlot == 2)));
        field_78112_f.field_78806_j = ((isVisible) && (armorSlot == 1));
        field_78113_g.field_78806_j = ((isVisible) && (armorSlot == 1));
        field_78123_h.field_78806_j = ((isVisible) && (armorSlot == 3));
        field_78124_i.field_78806_j = ((isVisible) && (armorSlot == 3));
        
        field_78117_n = entityLiving.func_70093_af();
        field_78093_q = entityLiving.func_70115_ae();
        field_78091_s = entityLiving.func_70631_g_();
        
        ItemStack heldStack = entityLiving.func_71124_b(0);
        field_78120_m = (heldStack != null ? 1 : 0);
        field_78118_o = false;
        
        if (((entityLiving instanceof EntityPlayer)) && (heldStack != null) && (((EntityPlayer)entityLiving).func_71057_bx() > 0)) {
          EnumAction enumaction = heldStack.func_77975_n();
          
          if (enumaction == EnumAction.block) {
            field_78120_m = 3;
          }
          
          field_78118_o = (enumaction == EnumAction.bow);
        }
        return armorModel;
      }
    }
    return null;
  }
  
  @SideOnly(Side.CLIENT)
  public EnumRarity func_77613_e(ItemStack stack)
  {
    if (stack == null)
      return EnumRarity.common;
    if (stack.func_77973_b() == ItemsBABAS_HAT)
      return EnumRarity.epic;
    if (stack.func_77973_b() == ItemsBARK_BELT) {
      return EnumRarity.rare;
    }
    return EnumRarity.uncommon;
  }
  

  public String func_77653_i(ItemStack stack)
  {
    String baseName = super.func_77653_i(stack);
    return baseName;
  }
  
  public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean advancedTooltips)
  {
    String localText = Witchery.resource(func_77658_a() + ".tip");
    if (localText != null) {
      for (String s : localText.split("\n")) {
        if (!s.isEmpty()) {
          list.add(s);
        }
      }
    }
    if ((stack != null) && (stack.func_77942_o()) && (stack.func_77978_p().func_74764_b("WITCPotion"))) {
      int potion = stack.func_77978_p().func_74762_e("WITCPotion");
      List effects = net.minecraft.init.Items.field_151068_bn.func_77834_f(potion);
      if ((effects != null) && (!effects.isEmpty())) {
        PotionEffect effect = (PotionEffect)effects.get(0);
        String s1 = effect.func_76453_d();
        s1 = s1 + ".postfix";
        String s2 = "§6" + StatCollector.func_74838_a(s1).trim() + "§r";
        
        if (effect.func_76458_c() > 0) {
          s2 = s2 + " " + StatCollector.func_74838_a(new StringBuilder().append("potion.potency.").append(effect.func_76458_c()).toString()).trim();
        }
        
        if (effect.func_76459_b() > 20) {
          s2 = s2 + " [" + net.minecraft.potion.Potion.func_76389_a(effect) + "]";
        }
        
        list.add(s2);
      }
    }
    
    if ((stack != null) && (stack.func_77942_o()) && (stack.func_77978_p().func_74764_b("WITCPotion2"))) {
      int potion = stack.func_77978_p().func_74762_e("WITCPotion2");
      List effects = net.minecraft.init.Items.field_151068_bn.func_77834_f(potion);
      if ((effects != null) && (!effects.isEmpty())) {
        PotionEffect effect = (PotionEffect)effects.get(0);
        String s1 = effect.func_76453_d();
        s1 = s1 + ".postfix";
        String s2 = "§6" + StatCollector.func_74838_a(s1).trim() + "§r";
        
        if (effect.func_76458_c() > 0) {
          s2 = s2 + " " + StatCollector.func_74838_a(new StringBuilder().append("potion.potency.").append(effect.func_76458_c()).toString()).trim();
        }
        
        if (effect.func_76459_b() > 20) {
          s2 = s2 + " [" + net.minecraft.potion.Potion.func_76389_a(effect) + "]";
        }
        
        list.add(s2);
      }
    }
  }
  
  private static String noPlaceLikeHome = null;
  private static final double WILD_EFFECT_CHANCE = 0.01D;
  
  public boolean trySayTheresNoPlaceLikeHome(EntityPlayer player, String message) {
    if ((player == null) || (field_70170_p.field_72995_K)) {
      return false;
    }
    if (noPlaceLikeHome == null) {
      noPlaceLikeHome = Witchery.resource("witchery.rite.noplacelikehome").toLowerCase().replace("'", "");
    }
    if (message.toLowerCase().replace("'", "").startsWith(noPlaceLikeHome)) {
      ItemStack stack = player.func_71124_b(1);
      if ((stack != null) && (stack.func_77973_b() == ItemsRUBY_SLIPPERS) && (field_71093_bK != instancedimensionDreamID)) {
        NBTTagCompound nbtPlayer = Infusion.getNBT(player);
        if (nbtPlayer != null) {
          int R = 3;
          AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(field_70165_t - 3.0D, field_70163_u - 3.0D, field_70161_v - 3.0D, field_70165_t + 3.0D, field_70163_u + 3.0D, field_70161_v + 3.0D);
          
          List list = field_70170_p.func_72872_a(EntityItem.class, bounds);
          for (Object obj : list) {
            EntityItem waystoneEntity = (EntityItem)obj;
            ItemStack waystoneStack = waystoneEntity.func_92059_d();
            if ((waystoneStack != null) && (ItemsGENERIC.itemWaystoneBound.isMatch(waystoneStack))) {
              if (nbtPlayer.func_74764_b("WITCLastRubySlipperWayTime")) {
                long lastTime = nbtPlayer.func_74763_f("WITCLastRubySlipperWayTime");
                long currentTime = MinecraftServer.func_130071_aq();
                long timeSince = currentTime - lastTime;
                long COOLDOWN = 60000L;
                if (timeSince < 60000L) {
                  int cooldownRemaining = Math.max(1, (int)(60000L - timeSince) / 60000);
                  com.emoniph.witchery.util.ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.rite.slippersoncooldown", new Object[] { Integer.valueOf(cooldownRemaining).toString() });
                  return true;
                }
              }
              
              int waystoneDimension = ItemGeneral.getWaystoneDimension(waystoneStack);
              if (Infusion.aquireEnergy(field_70170_p, player, nbtPlayer, waystoneDimension != field_71093_bK ? 80 : 40, true)) {
                waystoneEntity.func_70106_y();
                nbtPlayer.func_74772_a("WITCLastRubySlipperWayTime", MinecraftServer.func_130071_aq());
                if (field_70170_p.field_73012_v.nextDouble() < 0.01D) {
                  com.emoniph.witchery.blocks.BlockVoidBramble.teleportRandomly(field_70170_p, MathHelper.func_76128_c(field_70165_t), MathHelper.func_76128_c(field_70163_u), MathHelper.func_76128_c(field_70161_v), player, 500);
                }
                else {
                  ItemsGENERIC.teleportToLocation(field_70170_p, waystoneStack, player, 2, true);
                }
              }
              return true;
            }
          }
          
          if (nbtPlayer.func_74764_b("WITCLastRubySlipperTime")) {
            long lastTime = nbtPlayer.func_74763_f("WITCLastRubySlipperTime");
            long currentTime = MinecraftServer.func_130071_aq();
            long timeSince = currentTime - lastTime;
            long COOLDOWN = 1800000L;
            if ((timeSince < 1800000L) && (!field_71075_bZ.field_75098_d)) {
              int cooldownRemaining = Math.max(1, (int)(1800000L - timeSince) / 60000);
              com.emoniph.witchery.util.ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.rite.slippersoncooldown", new Object[] { Integer.valueOf(cooldownRemaining).toString() });
              return true;
            }
          }
          ChunkCoordinates coords = player.getBedLocation(field_71093_bK);
          int dimension = field_71093_bK;
          World world = field_70170_p;
          if (coords == null) {
            coords = player.getBedLocation(0);
            dimension = 0;
            world = MinecraftServer.func_71276_C().func_71218_a(0);
            if (coords == null) {
              coords = world.func_72861_E();
              while ((world.func_147439_a(field_71574_a, field_71572_b, field_71573_c).func_149721_r()) && (field_71572_b < 255)) {
                field_71572_b += 1;
              }
            }
          }
          if (coords != null) {
            nbtPlayer.func_74772_a("WITCLastRubySlipperTime", MinecraftServer.func_130071_aq());
            coords = net.minecraft.init.Blocks.field_150324_C.getBedSpawnPosition(world, field_71574_a, field_71572_b, field_71573_c, null);
            if (coords != null) {
              if (Infusion.aquireEnergy(field_70170_p, player, nbtPlayer, dimension != field_71093_bK ? 120 : 80, true)) {
                ItemGeneral.teleportToLocation(field_70170_p, field_71574_a, field_71572_b + 1, field_71573_c, dimension, player, true);
              }
              return true;
            }
          }
        }
      }
    }
    return false;
  }
}
