package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.network.PacketPipeline;
import cpw.mods.fml.relauncher.ReflectionHelper;
import java.lang.reflect.Field;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class PotionBase extends Potion
{
  private boolean inventoryTextHidden;
  private boolean incurable;
  private boolean permenant;
  private static Field fieldPotionIsBadEffect;
  
  public PotionBase(int id, int color)
  {
    this(id, false, color);
  }
  
  protected PotionBase(int id, boolean debuff, int color) {
    super(id, debuff, color);
  }
  

  public void postContructInitialize() {}
  
  public static boolean isDebuff(Potion potion)
  {
    try
    {
      if (fieldPotionIsBadEffect == null) {
        fieldPotionIsBadEffect = ReflectionHelper.findField(Potion.class, new String[] { "isBadEffect", "field_76418_K", "K" });
      }
      return ((Boolean)fieldPotionIsBadEffect.get(potion)).booleanValue();
    }
    catch (IllegalAccessException ex) {}
    return false;
  }
  
  protected boolean isDebuff()
  {
    return false;
  }
  
  public PotionBase getPotion() {
    return this;
  }
  
  public static boolean isCurable(Potion potion) {
    return (!(potion instanceof PotionBase)) || (((PotionBase)potion).isCurable());
  }
  
  public static boolean isPermenant(Potion potion) {
    return ((potion instanceof PotionBase)) && (((PotionBase)potion).isPermenant());
  }
  
  public boolean isCurable() {
    return !incurable;
  }
  
  public boolean isPermenant() {
    return permenant;
  }
  
  protected void setIncurable() {
    incurable = true;
  }
  
  protected void setPermenant() {
    permenant = true;
  }
  
  protected void hideInventoryText() {
    inventoryTextHidden = true;
  }
  

  public void func_111185_a(EntityLivingBase entity, BaseAttributeMap attributes, int amplifier)
  {
    super.func_111185_a(entity, attributes, amplifier);
    if ((this instanceof IHandleRenderLiving)) {
      PotionEffect effect = entity.func_70660_b(this);
      Witchery.packetPipeline.sendToAll(new S1DPacketEntityEffect(entity.func_145782_y(), effect));
    }
  }
  

  public void func_111187_a(EntityLivingBase entity, BaseAttributeMap attributes, int amplifier)
  {
    super.func_111187_a(entity, attributes, amplifier);
    if ((this instanceof IHandleRenderLiving)) {
      Witchery.packetPipeline.sendToAll(new net.minecraft.network.play.server.S1EPacketRemoveEntityEffect(entity.func_145782_y(), new PotionEffect(field_76415_H, 1)));
    }
  }
  

  public boolean shouldRenderInvText(PotionEffect effect)
  {
    return !inventoryTextHidden;
  }
  
  public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc)
  {
    if (inventoryTextHidden) {
      field_71466_p.func_78261_a(Witchery.resource("witchery:potion.unknown"), x + 10 + 18, y + 6, 16777215);
    }
  }
}
