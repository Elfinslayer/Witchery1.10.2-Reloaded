package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.client.model.ModelEarmuffs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.sound.PlaySoundEvent17;

public class ItemEarmuffs extends ItemArmor
{
  @SideOnly(Side.CLIENT)
  private ModelEarmuffs modelClothesChest;
  @SideOnly(Side.CLIENT)
  private ModelEarmuffs modelClothesLegs;
  
  public ItemEarmuffs(int armorSlot)
  {
    super(ItemArmor.ArmorMaterial.CLOTH, 1, armorSlot);
    
    func_77656_e(ItemArmor.ArmorMaterial.CLOTH.func_78046_a(armorSlot));
    
    func_77637_a(com.emoniph.witchery.WitcheryCreativeTab.INSTANCE);
  }
  
  public net.minecraft.item.Item func_77655_b(String itemName)
  {
    com.emoniph.witchery.util.ItemUtil.registerItem(this, itemName);
    return super.func_77655_b(itemName);
  }
  
  public boolean func_82816_b_(ItemStack stack)
  {
    return false;
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
  
  public int func_82814_b(ItemStack stack)
  {
    if (!func_82816_b_(stack)) {
      return 16777215;
    }
    
    return super.func_82814_b(stack);
  }
  
  @SideOnly(Side.CLIENT)
  public EnumRarity func_77613_e(ItemStack itemstack)
  {
    return EnumRarity.common;
  }
  
  public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean advancedTooltips)
  {
    if (stack != null) {
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
  






  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
  {
    if (stack != null) {
      if (type == null) {
        return "witchery:textures/entities/earmuffs.png";
      }
      return "witchery:textures/entities/empty64x64_overlay.png";
    }
    
    return null;
  }
  

  @SideOnly(Side.CLIENT)
  public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack stack, int armorSlot)
  {
    if (modelClothesChest == null) {
      modelClothesChest = new ModelEarmuffs();
    }
    




    ModelBiped armorModel = null;
    if ((stack != null) && ((stack.func_77973_b() instanceof ItemArmor))) {
      int type = func_77973_bfield_77881_a;
      
      if (type != 2) {
        armorModel = modelClothesChest;
      } else {
        armorModel = modelClothesChest;
      }
      
      if (armorModel != null) {
        boolean isVisible = true;
        
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
  
  public static boolean isHelmWorn(EntityPlayer entity) {
    ItemStack currentArmor = entity.func_82169_q(3);
    return (currentArmor != null) && (currentArmor.func_77973_b() == ItemsEARMUFFS);
  }
  
  public static class ClientEventHooks { public ClientEventHooks() {}
    
    @cpw.mods.fml.common.eventhandler.SubscribeEvent
    public void onSound(PlaySoundEvent17 event) { Minecraft MC = Minecraft.func_71410_x();
      EntityPlayer player = field_71439_g;
      if ((player != null) && (ItemEarmuffs.isHelmWorn(player))) {
        result = null;
      }
    }
  }
}
