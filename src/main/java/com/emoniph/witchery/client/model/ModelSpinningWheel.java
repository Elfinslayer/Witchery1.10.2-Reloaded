package com.emoniph.witchery.client.model;

import com.emoniph.witchery.blocks.BlockSpinningWheel.TileEntitySpinningWheel;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelSpinningWheel extends ModelBase
{
  private ModelRenderer seat;
  private ModelRenderer legBackRight;
  private ModelRenderer legBackLeft;
  private ModelRenderer legFrontRight;
  private ModelRenderer legFrontLeft;
  private ModelRenderer thread;
  private ModelRenderer threadPole;
  private ModelRenderer armRight;
  private ModelRenderer armLeft;
  private ModelRenderer wheel;
  
  public ModelSpinningWheel()
  {
    field_78090_t = 64;
    field_78089_u = 64;
    func_78085_a("wheel.spokes", 0, -6);
    func_78085_a("wheel.top", 0, 7);
    func_78085_a("wheel.bottom", 0, 7);
    func_78085_a("wheel.back", 23, 5);
    func_78085_a("wheel.front", 23, 5);
    
    seat = new ModelRenderer(this, 0, 0);
    seat.func_78789_a(-2.0F, -1.0F, -7.0F, 4, 1, 14);
    seat.func_78793_a(0.0F, 18.0F, 0.0F);
    seat.func_78787_b(64, 64);
    seat.field_78809_i = true;
    setRotation(seat, 0.2602503F, 0.0F, 0.0F);
    legBackRight = new ModelRenderer(this, 32, 0);
    legBackRight.func_78789_a(-1.0F, 0.0F, 0.0F, 1, 9, 1);
    legBackRight.func_78793_a(-1.0F, 16.0F, 5.0F);
    legBackRight.func_78787_b(64, 64);
    legBackRight.field_78809_i = true;
    setRotation(legBackRight, 0.1745329F, 0.0F, 0.1745329F);
    legBackLeft = new ModelRenderer(this, 32, 0);
    legBackLeft.func_78789_a(0.0F, 0.0F, 0.0F, 1, 9, 1);
    legBackLeft.func_78793_a(1.0F, 16.0F, 5.0F);
    legBackLeft.func_78787_b(64, 64);
    legBackLeft.field_78809_i = true;
    setRotation(legBackLeft, 0.1745329F, 0.0F, -0.1745329F);
    legFrontRight = new ModelRenderer(this, 0, 6);
    legFrontRight.func_78789_a(-1.0F, 0.0F, 0.0F, 1, 6, 1);
    legFrontRight.func_78793_a(-1.0F, 19.0F, -6.0F);
    legFrontRight.func_78787_b(64, 64);
    legFrontRight.field_78809_i = true;
    setRotation(legFrontRight, -0.1745329F, 0.0F, 0.1745329F);
    legFrontLeft = new ModelRenderer(this, 0, 6);
    legFrontLeft.func_78789_a(0.0F, 0.0F, 0.0F, 1, 6, 1);
    legFrontLeft.func_78793_a(1.0F, 19.0F, -6.0F);
    legFrontLeft.func_78787_b(64, 64);
    legFrontLeft.field_78809_i = true;
    setRotation(legFrontLeft, -0.1745329F, 0.0F, -0.1745329F);
    thread = new ModelRenderer(this, 23, 0);
    thread.func_78789_a(-1.0F, -3.0F, -1.0F, 2, 3, 2);
    thread.func_78793_a(0.0F, 12.0F, 5.0F);
    thread.func_78787_b(64, 64);
    thread.field_78809_i = true;
    setRotation(thread, 0.0F, 0.0F, 0.0F);
    threadPole = new ModelRenderer(this, 9, 7);
    threadPole.func_78789_a(-0.5F, 0.0F, -0.5F, 1, 4, 1);
    threadPole.func_78793_a(0.0F, 12.0F, 5.0F);
    threadPole.func_78787_b(64, 64);
    threadPole.field_78809_i = true;
    setRotation(threadPole, 0.0F, 0.0F, 0.0F);
    armRight = new ModelRenderer(this, 28, 6);
    armRight.func_78789_a(-0.5F, -7.0F, -0.5F, 1, 7, 1);
    armRight.func_78793_a(-1.0F, 18.0F, -2.0F);
    armRight.func_78787_b(64, 64);
    armRight.field_78809_i = true;
    setRotation(armRight, 0.2268928F, 0.0F, 0.0F);
    armLeft = new ModelRenderer(this, 28, 6);
    armLeft.func_78789_a(-0.5F, -7.0F, -0.5F, 1, 7, 1);
    armLeft.func_78793_a(1.0F, 18.0F, -2.0F);
    armLeft.func_78787_b(64, 64);
    armLeft.field_78809_i = true;
    setRotation(armLeft, 0.2268928F, 0.0F, 0.0F);
    wheel = new ModelRenderer(this, "wheel");
    wheel.func_78793_a(0.0F, 12.0F, -3.5F);
    setRotation(wheel, 0.0F, 0.0F, 0.0F);
    wheel.field_78809_i = true;
    wheel.func_78786_a("spokes", 0.0F, -3.0F, -3.0F, 0, 6, 6);
    wheel.func_78786_a("top", -0.5F, -4.0F, -3.0F, 1, 1, 6);
    wheel.func_78786_a("bottom", -0.5F, 3.0F, -3.0F, 1, 1, 6);
    wheel.func_78786_a("back", -0.5F, -4.0F, 3.0F, 1, 8, 1);
    wheel.func_78786_a("front", -0.5F, -4.0F, -4.0F, 1, 8, 1);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    render(entity, f, f1, f2, f3, f4, f5, null);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, BlockSpinningWheel.TileEntitySpinningWheel spinningWheel) {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity, spinningWheel);
    
    seat.func_78785_a(f5);
    legBackRight.func_78785_a(f5);
    legBackLeft.func_78785_a(f5);
    legFrontRight.func_78785_a(f5);
    legFrontLeft.func_78785_a(f5);
    thread.func_78785_a(f5);
    threadPole.func_78785_a(f5);
    armRight.func_78785_a(f5);
    armLeft.func_78785_a(f5);
    wheel.func_78785_a(f5);
  }
  
  public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    setRotationAngles(f, f1, f2, f3, f4, f5, entity, null);
  }
  
  private void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity, BlockSpinningWheel.TileEntitySpinningWheel spinningWheel) {
    super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
    
    if (spinningWheel != null) {
      boolean spinning = (spinningWheel.getCookTime() > 0) && (spinningWheel.getCookTime() < spinningWheel.getTotalCookTime()) && (powerLevel > 0);
      Minecraft.func_71410_x();long ticks = Minecraft.func_71386_F() / 25L;
      
      wheel.field_78795_f = (spinning ? (float)(-(ticks / 3L) % 360L) : 0.0F);
      thread.field_78796_g = (spinning ? (float)(ticks / 2L % 360L) : 0.0F);
    }
  }
}
