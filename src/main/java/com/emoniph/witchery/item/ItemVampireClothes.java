package com.emoniph.witchery.item;

import com.emoniph.witchery.client.model.ModelVampireArmor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;

public class ItemVampireClothes extends ItemArmor implements net.minecraftforge.common.ISpecialArmor
{
  boolean female;
  boolean metal;
  private int realDamageReduction;
  @SideOnly(Side.CLIENT)
  private IIcon iconUnderlay;
  @SideOnly(Side.CLIENT)
  private ModelVampireArmor modelClothesChest;
  @SideOnly(Side.CLIENT)
  private ModelVampireArmor modelClothesLegs;
  private static final String BIBLIOCRAFT_ARMOR_STAND_ENTITY_NAME = "AbstractSteve";
  
  public ItemVampireClothes(int armorSlot, boolean female, boolean metal)
  {
    super(ItemArmor.ArmorMaterial.CLOTH, 1, armorSlot);
    this.female = female;
    this.metal = metal;
    
    func_77656_e(ItemArmor.ArmorMaterial.IRON.func_78046_a(armorSlot));
    
    realDamageReduction = (metal ? ItemArmor.ArmorMaterial.IRON.func_78044_b(armorSlot) : field_77879_b);
    
    func_77637_a(com.emoniph.witchery.WitcheryCreativeTab.INSTANCE);
  }
  
  public net.minecraft.item.Item func_77655_b(String itemName)
  {
    com.emoniph.witchery.util.ItemUtil.registerItem(this, itemName);
    return super.func_77655_b(itemName);
  }
  
  public int func_77619_b() {
    return ItemArmor.ArmorMaterial.GOLD.func_78045_a();
  }
  
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
  {
    if ((stack != null) && (field_77881_a == 2))
      return "witchery:textures/entities/vampirearmor.png";
    if (stack != null) {
      return type == null ? "witchery:textures/entities/vampirearmor_over_first.png" : "witchery:textures/entities/vampirearmor_over.png";
    }
    
    return null;
  }
  

  public boolean func_82816_b_(ItemStack stack)
  {
    return true;
  }
  


  public void onArmorTick(net.minecraft.world.World world, EntityPlayer player, ItemStack stack) {}
  

  public int func_82814_b(ItemStack stack)
  {
    if (!func_82816_b_(stack)) {
      return super.func_82814_b(stack);
    }
    
    int color = super.func_82814_b(stack);
    if (color == 10511680) {
      color = 13369344;
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
    return true;
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_77618_c(int damage, int renderPass)
  {
    if (renderPass == 0) {
      return iconUnderlay;
    }
    return func_77617_a(damage);
  }
  




  @SideOnly(Side.CLIENT)
  public void func_94581_a(IIconRegister iconRegister)
  {
    super.func_94581_a(iconRegister);
    iconUnderlay = iconRegister.func_94245_a(func_111208_A() + "_first");
  }
  








  @SideOnly(Side.CLIENT)
  public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack stack, int armorSlot)
  {
    if (modelClothesChest == null) {
      modelClothesChest = new ModelVampireArmor(0.3F, false, female, metal);
    }
    
    if (modelClothesLegs == null) {
      modelClothesLegs = new ModelVampireArmor(0.02F, true, female, metal);
    }
    
    ModelBiped armorModel = null;
    if ((stack != null) && ((stack.func_77973_b() instanceof ItemVampireClothes))) {
      int type = func_77973_bfield_77881_a;
      
      if (type == 2) {
        armorModel = modelClothesLegs;
      } else {
        armorModel = modelClothesChest;
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
        field_78123_h.field_78806_j = ((isVisible) && ((armorSlot == 3) || (armorSlot == 2)));
        field_78124_i.field_78806_j = ((isVisible) && ((armorSlot == 3) || (armorSlot == 2)));
        
        field_78117_n = entityLiving.func_70093_af();
        field_78093_q = entityLiving.func_70115_ae();
        field_78091_s = entityLiving.func_70631_g_();
        
        ItemStack heldStack = entityLiving.func_71124_b(0);
        field_78120_m = (heldStack != null ? 1 : 0);
        field_78118_o = false;
        
        if (((entityLiving instanceof EntityPlayer)) && (heldStack != null) && (((EntityPlayer)entityLiving).func_71057_bx() > 0))
        {
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
    return EnumRarity.uncommon;
  }
  
  public String func_77653_i(ItemStack stack)
  {
    String baseName = super.func_77653_i(stack);
    return baseName;
  }
  
  public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean advancedTooltips)
  {
    String localText = com.emoniph.witchery.Witchery.resource(func_77658_a() + ".tip");
    if (localText != null) {
      for (String s : localText.split("\n")) {
        if (!s.isEmpty()) {
          list.add(s);
        }
      }
    }
  }
  
  public static int numLightPiecesWorn(EntityLivingBase entity, boolean light) {
    int pieces = 0;
    for (int i = 1; i <= 4; i++) {
      ItemStack item = entity.func_71124_b(i);
      if ((item != null) && ((item.func_77973_b() instanceof ItemVampireClothes)) && 
        (!func_77973_bmetal) && (light)) {
        pieces++;
      }
    }
    
    return pieces;
  }
  
  public static boolean isFlameProtectionActive(EntityLivingBase entity) {
    return (numLightPiecesWorn(entity, true) >= 3) || (numLightPiecesWorn(entity, true) >= 2);
  }
  
  public static boolean isExtendedFlameProtectionActive(EntityLivingBase entity) {
    return numLightPiecesWorn(entity, true) >= 4;
  }
  
  public static boolean isDrinkBoostActive(EntityLivingBase entity) {
    return numLightPiecesWorn(entity, true) >= 2;
  }
  
  public static boolean isMezmeriseBoostActive(EntityLivingBase entity) {
    return numLightPiecesWorn(entity, true) >= 3;
  }
  

  public ISpecialArmor.ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot)
  {
    return new ISpecialArmor.ArmorProperties(0, realDamageReduction / 25.0D, armor.func_77958_k() + 1 - armor.func_77960_j());
  }
  
  public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot)
  {
    return realDamageReduction;
  }
  

  public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot)
  {
    stack.func_77972_a(damage, entity);
  }
}
