package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.util.Dye;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderLivingEvent.Post;
import net.minecraftforge.client.event.RenderLivingEvent.Pre;
import org.lwjgl.opengl.GL11;

public class PotionColorful
  extends PotionBase
  implements IHandlePreRenderLiving, IHandleRenderLiving
{
  public PotionColorful(int id, int color)
  {
    super(id, true, color);
    setIncurable();
    hideInventoryText();
  }
  
  @SideOnly(Side.CLIENT)
  public void onLivingRender(World world, EntityLivingBase entity, RenderLivingEvent.Pre event, int amplifier)
  {
    GL11.glPushMatrix();
    Dye dye = Dye.DYES[Math.min(amplifier, Dye.DYES.length - 1)];
    
    float red = (rgb >>> 16 & 0xFF) / 256.0F;
    float green = (rgb >>> 8 & 0xFF) / 256.0F;
    float blue = (rgb & 0xFF) / 256.0F;
    
    GL11.glColor3f(red, green, blue);
  }
  


  public void onLivingRender(World world, EntityLivingBase entity, RenderLivingEvent.Post event, int amplifier) {}
  

  public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc)
  {
    Dye dye = Dye.DYES[Math.min(effect.func_76458_c(), Dye.DYES.length - 1)];
    String label = Witchery.resource("witchery:color." + unlocalizedName);
    field_71466_p.func_78261_a(label, x + 10 + 18, y + 6, 16777215);
    String duration = Potion.func_76389_a(effect);
    field_71466_p.func_78261_a(duration, x + 10 + 18, y + 6 + 10, 8355711);
  }
}
