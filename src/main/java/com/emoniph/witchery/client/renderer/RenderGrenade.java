package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.entity.EntityGrenade;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import org.lwjgl.opengl.GL11;


@SideOnly(Side.CLIENT)
public class RenderGrenade
  extends RenderSnowball
{
  private final Item item;
  
  public RenderGrenade(Item item)
  {
    this(item, 0);
  }
  
  public RenderGrenade(Item item, int damageValue) {
    super(item, damageValue);
    this.item = item;
  }
  
  public void func_76986_a(Entity entity, double par2, double par4, double par6, float par8, float par9)
  {
    EntityGrenade brew = (EntityGrenade)entity;
    Item item = brew.getMode() == 0 ? ItemsSUN_GRENADE : ItemsDUP_GRENADE;
    IIcon icon = item.getIcon(null, 1);
    
    if (icon != null) {
      GL11.glPushMatrix();
      GL11.glTranslatef((float)par2, (float)par4, (float)par6);
      GL11.glEnable(32826);
      GL11.glScalef(0.3F, 0.3F, 0.3F);
      
      func_110777_b(entity);
      
      Tessellator tessellator = Tessellator.field_78398_a;
      












      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glPushMatrix();
      drawIcon(tessellator, item.getIcon(null, 0));
      GL11.glPopMatrix();
      

      drawIcon(tessellator, icon);
      
      GL11.glDisable(32826);
      GL11.glPopMatrix();
    }
  }
  
  private void drawIcon(Tessellator tessalator, IIcon icon) {
    float f = icon.func_94209_e();
    float f1 = icon.func_94212_f();
    float f2 = icon.func_94206_g();
    float f3 = icon.func_94210_h();
    float f4 = 1.0F;
    float f5 = 0.5F;
    float f6 = 0.25F;
    GL11.glRotatef(180.0F - field_76990_c.field_78735_i, 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(-field_76990_c.field_78732_j, 1.0F, 0.0F, 0.0F);
    tessalator.func_78382_b();
    tessalator.func_78375_b(0.0F, 1.0F, 0.0F);
    tessalator.func_78374_a(0.0F - f5, 0.0F - f6, 0.0D, f, f3);
    tessalator.func_78374_a(f4 - f5, 0.0F - f6, 0.0D, f1, f3);
    tessalator.func_78374_a(f4 - f5, f4 - f6, 0.0D, f1, f2);
    tessalator.func_78374_a(0.0F - f5, f4 - f6, 0.0D, f, f2);
    tessalator.func_78381_a();
  }
}
