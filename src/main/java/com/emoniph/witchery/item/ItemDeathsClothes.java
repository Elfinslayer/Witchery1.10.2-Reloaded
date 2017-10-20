package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.client.model.ModelClothesDeath;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.TimeUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemDeathsClothes extends ItemArmor
{
  @SideOnly(Side.CLIENT)
  private ModelClothesDeath modelClothesChest;
  private static final String BIBLIOCRAFT_ARMOR_STAND_ENTITY_NAME = "AbstractSteve";
  
  public ItemDeathsClothes(int armorSlot)
  {
    super(ItemArmor.ArmorMaterial.IRON, 1, armorSlot);
    
    func_77656_e(ItemArmor.ArmorMaterial.DIAMOND.func_78046_a(armorSlot));
    
    func_77637_a(com.emoniph.witchery.WitcheryCreativeTab.INSTANCE);
  }
  
  public Item func_77655_b(String itemName)
  {
    com.emoniph.witchery.util.ItemUtil.registerItem(this, itemName);
    return super.func_77655_b(itemName);
  }
  
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
  {
    if (stack != null) {
      return "witchery:textures/entities/deathsclothes" + (type == null ? "" : "_overlay") + ".png";
    }
    return null;
  }
  

  public void onArmorTick(World world, EntityPlayer player, ItemStack stack)
  {
    if ((!field_72995_K) && 
      (player.func_82169_q(3) == stack)) {
      int offset = (int)(world.func_82737_E() % 10L);
      if ((instanceallowDeathsHoodToFreezeVictims) && (offset == 1)) {
        MovingObjectPosition mop = com.emoniph.witchery.infusion.infusions.InfusionOtherwhere.raytraceEntities(world, player, true, 16.0D);
        if ((mop != null) && (field_72313_a == net.minecraft.util.MovingObjectPosition.MovingObjectType.ENTITY) && ((field_72308_g instanceof EntityLivingBase))) {
          EntityLivingBase victim = (EntityLivingBase)field_72308_g;
          if ((victim.func_70685_l(player)) && 
            (!victim.func_70644_a(Potion.field_76421_d))) {
            victim.func_70690_d(new PotionEffect(field_76421_dfield_76415_H, 60, 2));
          }
        }
      }
      else if (offset == 2) {
        int x = MathHelper.func_76128_c(field_70165_t);
        int y = MathHelper.func_76128_c(field_70163_u);
        int z = MathHelper.func_76128_c(field_70161_v);
        if (world.func_72957_l(x, y, z) < 8) {
          PotionEffect potion = player.func_70660_b(Potion.field_76439_r);
          if ((potion == null) || (potion.func_76459_b() <= TimeUtil.secsToTicks(15))) {
            player.func_82170_o(field_76439_rfield_76415_H);
            player.func_70690_d(new PotionEffect(field_76439_rfield_76415_H, TimeUtil.secsToTicks(20), 0, true));
          }
        }
      }
    }
  }
  






  @SideOnly(Side.CLIENT)
  public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack stack, int armorSlot)
  {
    if (modelClothesChest == null) {
      modelClothesChest = new ModelClothesDeath(0.4F);
    }
    
    ModelBiped armorModel = null;
    if ((stack != null) && ((stack.func_77973_b() instanceof ItemDeathsClothes))) {
      int type = func_77973_bfield_77881_a;
      
      armorModel = modelClothesChest;
      
      if (armorModel != null) {
        boolean isVisible = true;
        if ((entityLiving != null) && (entityLiving.func_82150_aj())) {
          String entityTypeName = entityLiving.getClass().getSimpleName();
          isVisible = (entityTypeName == null) || (entityTypeName.isEmpty()) || (entityTypeName.equals("AbstractSteve"));
        }
        

        field_78116_c.field_78806_j = ((isVisible) && (armorSlot == 0));
        field_78114_d.field_78806_j = ((isVisible) && (armorSlot == 0));
        field_78115_e.field_78806_j = ((isVisible) && (armorSlot == 1));
        field_78112_f.field_78806_j = ((isVisible) && (armorSlot == 1));
        field_78113_g.field_78806_j = ((isVisible) && (armorSlot == 1));
        field_78123_h.field_78806_j = ((isVisible) && ((armorSlot == 3) || (armorSlot == 2)));
        field_78124_i.field_78806_j = ((isVisible) && ((armorSlot == 3) || (armorSlot == 2)));
        
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
    return EnumRarity.epic;
  }
  
  public String func_77653_i(ItemStack stack)
  {
    String baseName = super.func_77653_i(stack);
    return baseName;
  }
  
  public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean advancedTooltips)
  {
    if ((stack != null) && ((stack.func_77973_b() != ItemsDEATH_HOOD) || (instanceallowDeathsHoodToFreezeVictims))) {
      String localText = Witchery.resource(func_77658_a() + ".tip");
      if (localText != null) {
        for (String s : localText.split("\n")) {
          if (!s.isEmpty()) {
            list.add(s);
          }
        }
      }
    }
  }
  
  public static boolean isFullSetWorn(EntityLivingBase entity) {
    int count = 0;
    for (int i = 1; i <= 4; i++) {
      ItemStack item = entity.func_71124_b(i);
      if ((item != null) && ((item.func_77973_b() instanceof ItemDeathsClothes))) {
        count++;
      }
    }
    return count >= 3;
  }
}
