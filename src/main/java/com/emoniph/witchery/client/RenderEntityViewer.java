package com.emoniph.witchery.client;

import net.minecraft.client.Minecraft;

public class RenderEntityViewer extends net.minecraft.client.renderer.EntityRenderer
{
  private final Minecraft mc;
  private float offsetY;
  
  public RenderEntityViewer(Minecraft mc) {
    super(mc, mc.func_110442_L());
    this.mc = mc;
  }
  
  public void setOffset(float offset) {
    offsetY = offset;
  }
  
  public float getOffset() {
    return offsetY;
  }
  
  private boolean canShiftView() {
    return (mc.field_71439_g != null) && (!mc.field_71439_g.func_70608_bn()) && (!mc.field_71439_g.func_70115_ae());
  }
  
  public void func_78480_b(float partialTicks)
  {
    if (canShiftView()) {
      mc.field_71439_g.field_70163_u += -offsetY;
      mc.field_71439_g.field_70137_T += -offsetY;
      mc.field_71439_g.field_70167_r += -offsetY;
      
      float savedHeight = mc.field_71439_g.eyeHeight;
      mc.field_71439_g.eyeHeight = mc.field_71439_g.getDefaultEyeHeight();
      super.func_78480_b(partialTicks);
      mc.field_71439_g.eyeHeight = savedHeight;
      mc.field_71439_g.field_70163_u -= -offsetY;
      mc.field_71439_g.field_70137_T -= -offsetY;
      mc.field_71439_g.field_70167_r -= -offsetY;
    } else {
      super.func_78480_b(partialTicks);
    }
  }
  
  public void func_78473_a(float partialTicks)
  {
    if (canShiftView()) {
      mc.field_71439_g.field_70163_u += -offsetY;
      mc.field_71439_g.field_70167_r += -offsetY;
      mc.field_71439_g.field_70137_T += -offsetY;
      float savedHeight = mc.field_71439_g.eyeHeight;
      mc.field_71439_g.eyeHeight = mc.field_71439_g.getDefaultEyeHeight();
      super.func_78473_a(partialTicks);
      mc.field_71439_g.eyeHeight = savedHeight;
      mc.field_71439_g.field_70163_u -= -offsetY;
      mc.field_71439_g.field_70167_r -= -offsetY;
      mc.field_71439_g.field_70137_T -= -offsetY;
    } else {
      super.func_78473_a(partialTicks);
    }
  }
}
