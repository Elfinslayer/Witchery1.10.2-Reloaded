package com.emoniph.witchery.item;

import com.emoniph.witchery.client.model.ModelHunterClothes;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.util.CreatureUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;

public class ItemHunterClothes extends ItemArmor implements net.minecraftforge.common.ISpecialArmor
{
  private boolean silvered;
  private boolean garlicked;
  @SideOnly(Side.CLIENT)
  private IIcon iconOverlaySilver;
  @SideOnly(Side.CLIENT)
  private IIcon iconOverlaySilverGarlic;
  @SideOnly(Side.CLIENT)
  private ModelHunterClothes modelClothesChest;
  @SideOnly(Side.CLIENT)
  private ModelHunterClothes modelClothesLegs;
  private static final String BIBLIOCRAFT_ARMOR_STAND_ENTITY_NAME = "AbstractSteve";
  
  public ItemHunterClothes(int armorSlot, boolean silvered, boolean garlicked)
  {
    super(ItemArmor.ArmorMaterial.CLOTH, 1, armorSlot);
    
    this.silvered = silvered;
    this.garlicked = garlicked;
    func_77656_e(ItemArmor.ArmorMaterial.IRON.func_78046_a(armorSlot));
    
    func_77637_a(com.emoniph.witchery.WitcheryCreativeTab.INSTANCE);
  }
  
  public net.minecraft.item.Item func_77655_b(String itemName)
  {
    com.emoniph.witchery.util.ItemUtil.registerItem(this, itemName);
    return super.func_77655_b(itemName);
  }
  
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
  {
    if ((stack != null) && (field_77881_a == 2)) {
      return "witchery:textures/entities/hunterclothes2" + (type == null ? "" : "_overlay") + ".png";
    }
    if (stack != null) {
      return "witchery:textures/entities/hunterclothes" + (type == null ? "" : "_overlay") + ".png";
    }
    
    return null;
  }
  

  public boolean func_82816_b_(ItemStack stack)
  {
    return true;
  }
  
  public void onArmorTick(World world, EntityPlayer player, ItemStack stack)
  {
    if ((!field_72995_K) && (field_70173_aa % 20 == 2)) {
      ExtendedPlayer playerEx = ExtendedPlayer.get(player);
      if (((silvered) && (playerEx.getWerewolfLevel() > 0)) || ((garlicked) && (playerEx.isVampire()))) {
        player.func_70097_a(DamageSource.field_76372_a, 1.0F);
      }
    }
  }
  
  public int func_82814_b(ItemStack stack)
  {
    if (!func_82816_b_(stack)) {
      return super.func_82814_b(stack);
    }
    
    int color = super.func_82814_b(stack);
    if (color == 10511680) {
      if (stack.func_77973_b() == ItemsHUNTER_BOOTS) {
        color = 1642763;
      } else if (stack.func_77973_b() == ItemsHUNTER_LEGS) {
        color = 4798251;
      } else {
        color = 4139550;
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
    return (silvered) || (garlicked);
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_77618_c(int damage, int renderPass)
  {
    if (renderPass == 1) {
      return garlicked ? iconOverlaySilverGarlic : iconOverlaySilver;
    }
    return func_77617_a(damage);
  }
  







  @SideOnly(Side.CLIENT)
  public void func_94581_a(IIconRegister iconRegister)
  {
    super.func_94581_a(iconRegister);
    iconOverlaySilver = iconRegister.func_94245_a(func_111208_A() + "_silvered");
    iconOverlaySilverGarlic = iconRegister.func_94245_a(func_111208_A() + "_garlicked");
  }
  








  @SideOnly(Side.CLIENT)
  public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack stack, int armorSlot)
  {
    if (modelClothesChest == null) {
      modelClothesChest = new ModelHunterClothes(0.4F, false);
    }
    
    if (modelClothesLegs == null) {
      modelClothesLegs = new ModelHunterClothes(0.01F, false);
    }
    
    ModelBiped armorModel = null;
    if ((stack != null) && ((stack.func_77973_b() instanceof ItemHunterClothes))) {
      int type = func_77973_bfield_77881_a;
      
      if ((type == 1) || (type == 3)) {
        armorModel = modelClothesChest;
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
        



        if (((entityLiving instanceof EntityPlayer)) && (heldStack != null) && (((EntityPlayer)entityLiving).func_71057_bx() > 0))
        {
          net.minecraft.item.EnumAction enumaction = heldStack.func_77975_n();
          
          if (enumaction == net.minecraft.item.EnumAction.block) {
            field_78120_m = 3;
          }
          
          field_78118_o = (enumaction == net.minecraft.item.EnumAction.bow);
        }
        
        return armorModel;
      }
    }
    return null;
  }
  
  @SideOnly(Side.CLIENT)
  public net.minecraft.item.EnumRarity func_77613_e(ItemStack stack)
  {
    return net.minecraft.item.EnumRarity.uncommon;
  }
  
  public String func_77653_i(ItemStack stack)
  {
    String baseName = super.func_77653_i(stack);
    return baseName;
  }
  
  public void func_77624_a(ItemStack stack, EntityPlayer player, java.util.List list, boolean advancedTooltips)
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
  
  public static boolean isFullSetWorn(EntityLivingBase entity, boolean silvered) {
    for (int i = 1; i <= 4; i++) {
      ItemStack item = entity.func_71124_b(i);
      if (item == null) {
        return false;
      }
      
      if ((item.func_77973_b() instanceof ItemHunterClothes)) {
        ItemHunterClothes clothes = (ItemHunterClothes)item.func_77973_b();
        if ((silvered) && (!silvered)) {
          return false;
        }
      } else {
        return false;
      }
    }
    return true;
  }
  
  public static boolean isMagicalProtectionActive(EntityLivingBase entity) {
    return (entity != null) && (isFullSetWorn(entity, false)) && (field_70170_p != null) && (field_70170_p.field_73012_v.nextDouble() < 0.25D);
  }
  
  public static boolean isCurseProtectionActive(EntityLivingBase entity)
  {
    return (entity != null) && (isFullSetWorn(entity, false)) && (field_70170_p != null) && (field_70170_p.field_73012_v.nextDouble() < 0.9D);
  }
  


  public ISpecialArmor.ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot)
  {
    if ((silvered) && (source != null) && (CreatureUtil.isWerewolf(source.func_76346_g()))) {
      source.func_76346_g().func_70097_a(DamageSource.field_76372_a, 1.0F);
      return new ISpecialArmor.ArmorProperties(0, field_77879_b * 2.5D / 25.0D, armor.func_77958_k() + 1 - armor.func_77960_j());
    }
    if ((garlicked) && (source != null) && (CreatureUtil.isVampire(source.func_76346_g()))) {
      return new ISpecialArmor.ArmorProperties(0, field_77879_b * 2.5D / 25.0D, armor.func_77958_k() + 1 - armor.func_77960_j());
    }
    
    return new ISpecialArmor.ArmorProperties(0, field_77879_b / 25.0D, armor.func_77958_k() + 1 - armor.func_77960_j());
  }
  


  public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot)
  {
    return field_77879_b;
  }
  
  public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot)
  {
    if ((silvered) && (source != null) && (CreatureUtil.isWerewolf(source.func_76346_g()))) {
      return;
    }
    
    if ((garlicked) && (source != null) && (CreatureUtil.isVampire(source.func_76346_g()))) {
      return;
    }
    
    stack.func_77972_a(damage, entity);
  }
  
  public static boolean isWolfProtectionActive(EntityLivingBase entity) {
    return (entity != null) && (isFullSetWorn(entity, true));
  }
}
