package com.emoniph.witchery.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class EntityBroom extends Entity
{
  private boolean field_70279_a;
  private double speedMultiplier;
  private int broomPosRotationIncrements;
  private double broomX;
  private double broomY;
  private double broomZ;
  private double broomYaw;
  private double broomPitch;
  @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
  private double velocityX;
  @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
  private double velocityY;
  @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
  private double velocityZ;
  
  public static class EventHooks
  {
    public EventHooks() {}
    
    @cpw.mods.fml.common.eventhandler.SubscribeEvent
    public void onLivingFall(net.minecraftforge.event.entity.living.LivingFallEvent event)
    {
      if ((entityLiving instanceof EntityPlayer)) {
        EntityPlayer player = (EntityPlayer)entityLiving;
        
        if ((player.func_70115_ae()) && ((field_70154_o instanceof EntityBroom))) {
          distance = 0.0F;
        }
      }
    }
  }
  














  public EntityBroom(net.minecraft.world.World world)
  {
    super(world);
    field_70279_a = true;
    speedMultiplier = 0.07D;
    field_70156_m = true;
    func_70105_a(1.2F, 0.5F);
    field_70129_M = (field_70131_O / 2.0F);
  }
  


  public EntityBroom(net.minecraft.world.World world, double x, double y, double z)
  {
    this(world);
    func_70107_b(x, y + field_70129_M, z);
    field_70159_w = 0.0D;
    field_70181_x = 0.0D;
    field_70179_y = 0.0D;
    field_70169_q = x;
    field_70167_r = y;
    field_70166_s = z;
  }
  


  protected boolean func_70041_e_()
  {
    return false;
  }
  
  protected void func_70088_a()
  {
    field_70180_af.func_75682_a(10, "");
    field_70180_af.func_75682_a(16, Byte.valueOf((byte)-1));
    field_70180_af.func_75682_a(17, new Integer(0));
    field_70180_af.func_75682_a(18, new Integer(1));
    field_70180_af.func_75682_a(19, new Float(0.0F));
  }
  

  protected void func_70081_e(int par1) {}
  
  public void setBrushColor(int color)
  {
    field_70180_af.func_75692_b(16, Byte.valueOf((byte)color));
  }
  
  public int getBrushColor() {
    return field_70180_af.func_75683_a(16);
  }
  
  public void setCustomNameTag(String par1Str)
  {
    field_70180_af.func_75692_b(10, par1Str);
  }
  
  public String getCustomNameTag()
  {
    return field_70180_af.func_75681_e(10);
  }
  
  public boolean hasCustomNameTag()
  {
    return field_70180_af.func_75681_e(10).length() > 0;
  }
  
  public net.minecraft.util.AxisAlignedBB func_70114_g(Entity par1Entity)
  {
    return field_70121_D;
  }
  
  public net.minecraft.util.AxisAlignedBB func_70046_E()
  {
    return field_70121_D;
  }
  
  public boolean func_70104_M()
  {
    return true;
  }
  
  public double func_70042_X()
  {
    return field_70131_O * 0.55D;
  }
  
  public boolean func_70097_a(net.minecraft.util.DamageSource par1DamageSource, float par2)
  {
    if (func_85032_ar())
      return false;
    if ((!field_70170_p.field_72995_K) && (!field_70128_L)) {
      setForwardDirection(-getForwardDirection());
      setTimeSinceHit(10);
      setDamageTaken(getDamageTaken() + par2 * 10.0F);
      func_70018_K();
      boolean flag = ((par1DamageSource.func_76346_g() instanceof EntityPlayer)) && (func_76346_gfield_71075_bZ.field_75098_d);
      
      if ((flag) || (getDamageTaken() > 40.0F)) {
        if (field_70153_n != null) {
          field_70153_n.func_70078_a(this);
        }
        
        if (!flag) {
          net.minecraft.item.ItemStack broomStack = ItemsGENERIC.itemBroomEnchanted.createStack();
          if (hasCustomNameTag()) {
            broomStack.func_151001_c(getCustomNameTag());
          }
          int brushColor = getBrushColor();
          if ((brushColor >= 0) && (brushColor <= 15)) {
            ItemsGENERIC.setBroomItemColor(broomStack, brushColor);
          }
          func_70099_a(broomStack, 0.0F);
        }
        
        func_70106_y();
      }
      
      return true;
    }
    return true;
  }
  

  @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
  public void func_70057_ab()
  {
    setForwardDirection(-getForwardDirection());
    setTimeSinceHit(10);
    setDamageTaken(getDamageTaken() * 11.0F);
  }
  
  public boolean func_70067_L()
  {
    return (!field_70128_L) && (field_70153_n == null);
  }
  
  @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
  public void func_70056_a(double x, double y, double z, float yaw, float pitch, int par9)
  {
    if (field_70279_a) {
      broomPosRotationIncrements = (par9 + 5);
    } else {
      double d3 = x - field_70165_t;
      double d4 = y - field_70163_u;
      double d5 = z - field_70161_v;
      double d6 = d3 * d3 + d4 * d4 + d5 * d5;
      
      if (d6 <= 1.0D) {
        return;
      }
      
      broomPosRotationIncrements = 3;
    }
    
    broomX = x;
    broomY = y;
    broomZ = z;
    broomYaw = yaw;
    broomPitch = pitch;
    field_70159_w = velocityX;
    field_70181_x = velocityY;
    field_70179_y = velocityZ;
  }
  
  @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
  public void func_70016_h(double x, double y, double z)
  {
    velocityX = (this.field_70159_w = x);
    velocityY = (this.field_70181_x = y);
    velocityZ = (this.field_70179_y = z);
  }
  
  public void func_70071_h_()
  {
    super.func_70071_h_();
    
    if ((field_70173_aa % 100 == 0) && 
      (field_70153_n != null) && ((field_70153_n instanceof EntityPlayer))) {
      riderHasSoaringBrew = com.emoniph.witchery.infusion.InfusedBrewEffect.Soaring.isActive((EntityPlayer)field_70153_n);
    }
    


    if (getTimeSinceHit() > 0) {
      setTimeSinceHit(getTimeSinceHit() - 1);
    }
    
    if (getDamageTaken() > 0.0F) {
      setDamageTaken(getDamageTaken() - 1.0F);
    }
    
    field_70169_q = field_70165_t;
    field_70167_r = field_70163_u;
    field_70166_s = field_70161_v;
    byte b0 = 5;
    double d0 = 0.0D;
    double initialHorzVelocity = Math.sqrt(field_70159_w * field_70159_w + field_70179_y * field_70179_y);
    


    if (initialHorzVelocity > 0.26249999999999996D) {
      double newHorzVelocity = Math.cos(field_70177_z * 3.141592653589793D / 180.0D);
      double d1 = Math.sin(field_70177_z * 3.141592653589793D / 180.0D);
    }
    



    if ((field_70170_p.field_72995_K) && (field_70279_a)) {
      if (broomPosRotationIncrements > 0) {
        double newHorzVelocity = field_70165_t + (broomX - field_70165_t) / broomPosRotationIncrements;
        double d5 = field_70163_u + (broomY - field_70163_u) / broomPosRotationIncrements;
        double d11 = field_70161_v + (broomZ - field_70161_v) / broomPosRotationIncrements;
        double d10 = net.minecraft.util.MathHelper.func_76138_g(broomYaw - field_70177_z);
        field_70177_z = ((float)(field_70177_z + d10 / broomPosRotationIncrements));
        field_70125_A = ((float)(field_70125_A + (broomPitch - field_70125_A) / broomPosRotationIncrements));
        
        broomPosRotationIncrements -= 1;
        func_70107_b(newHorzVelocity, d5, d11);
        func_70101_b(field_70177_z, field_70125_A);
      } else {
        double newHorzVelocity = field_70165_t + field_70159_w;
        double d5 = field_70163_u + field_70181_x;
        double d11 = field_70161_v + field_70179_y;
        func_70101_b((float)(field_70177_z + (broomYaw - field_70177_z)), (float)(field_70125_A + (broomPitch - field_70125_A)));
        
        func_70107_b(newHorzVelocity, d5, d11);
        
        field_70159_w *= 0.9900000095367432D;
        field_70179_y *= 0.9900000095367432D;
      }
    } else {
      if ((field_70153_n != null) && ((field_70153_n instanceof net.minecraft.entity.EntityLivingBase)))
      {
        double newHorzVelocity = field_70153_n).field_70701_bs;
        
        if (newHorzVelocity > 0.0D) {
          double d5 = -Math.sin(field_70153_n.field_70177_z * 3.1415927F / 180.0F);
          double d11 = Math.cos(field_70153_n.field_70177_z * 3.1415927F / 180.0F);
          
          field_70159_w += d5 * speedMultiplier * (0.1D + (riderHasSoaringBrew ? 0.1D : 0.0D) + (riderHasOwlFamiliar ? 0.2D : 0.0D));
          field_70179_y += d11 * speedMultiplier * (0.1D + (riderHasSoaringBrew ? 0.1D : 0.0D) + (riderHasOwlFamiliar ? 0.2D : 0.0D));
          
          double pitch = -Math.sin(field_70153_n.field_70125_A * 3.1415927F / 180.0F);
          if ((pitch > -0.5D) && (pitch < 0.2D)) {
            pitch = 0.0D;
          } else if (pitch < 0.0D) {
            pitch *= 0.5D;
          }
          
          field_70181_x = (pitch * speedMultiplier * 2.0D);







        }
        else if ((newHorzVelocity == 0.0D) && ((riderHasOwlFamiliar) || (riderHasSoaringBrew))) {
          field_70159_w *= 0.9D;
          field_70179_y *= 0.9D;
        }
      } else if (field_70153_n == null) {
        riderHasOwlFamiliar = false;
        double moX = field_70159_w * 0.9D;
        double moZ = field_70179_y * 0.9D;
        field_70159_w = (Math.abs(moX) < 0.01D ? 0.0D : moX);
        field_70179_y = (Math.abs(moZ) < 0.01D ? 0.0D : moZ);
        if (!field_70122_E) {
          field_70181_x = -0.2D;
        }
      }
      
      double newHorzVelocity = Math.sqrt(field_70159_w * field_70159_w + field_70179_y * field_70179_y);
      
      double SPEED_LIMIT = 0.9D + (riderHasOwlFamiliar ? 0.3D : 0.0D) + (riderHasSoaringBrew ? 0.3D : 0.0D);
      if (newHorzVelocity > SPEED_LIMIT) {
        double d5 = SPEED_LIMIT / newHorzVelocity;
        field_70159_w *= d5;
        field_70179_y *= d5;
        field_70181_x *= d5;
        newHorzVelocity = SPEED_LIMIT;
      }
      
      double MAX_ACCELERATION = (riderHasSoaringBrew) || (riderHasOwlFamiliar) ? 0.35D : 0.35D;
      double MAX_ACCELERATION_FACTOR = MAX_ACCELERATION * 100.0D;
      
      if ((newHorzVelocity > initialHorzVelocity) && (speedMultiplier < MAX_ACCELERATION)) {
        speedMultiplier += (MAX_ACCELERATION - speedMultiplier) / MAX_ACCELERATION_FACTOR;
        
        if (speedMultiplier > MAX_ACCELERATION) {
          speedMultiplier = MAX_ACCELERATION;
        }
      } else {
        speedMultiplier -= (speedMultiplier - 0.07D) / MAX_ACCELERATION_FACTOR;
        
        if (speedMultiplier < 0.07D) {
          speedMultiplier = 0.07D;
        }
      }
      
      func_70091_d(field_70159_w, field_70181_x, field_70179_y);
      
      field_70159_w *= 0.9900000095367432D;
      field_70181_x *= 0.9900000095367432D;
      field_70179_y *= 0.9900000095367432D;
      
      field_70125_A = 0.0F;
      double d5 = field_70177_z;
      double d11 = field_70169_q - field_70165_t;
      double d10 = field_70166_s - field_70161_v;
      
      if (d11 * d11 + d10 * d10 > 0.001D) {
        d5 = (float)(Math.atan2(d10, d11) * 180.0D / 3.141592653589793D);
      }
      
      double d12 = net.minecraft.util.MathHelper.func_76138_g(d5 - field_70177_z);
      
      field_70177_z = ((float)(field_70177_z + d12));
      func_70101_b(field_70177_z, field_70125_A);
      
      if (!field_70170_p.field_72995_K) {
        java.util.List list = field_70170_p.func_72839_b(this, field_70121_D.func_72314_b(0.20000000298023224D, 0.0D, 0.20000000298023224D));
        

        if ((list != null) && (!list.isEmpty())) {
          for (int l = 0; l < list.size(); l++) {
            Entity entity = (Entity)list.get(l);
            
            if ((entity != field_70153_n) && (entity.func_70104_M()) && ((entity instanceof EntityBroom))) {
              entity.func_70108_f(this);
            }
          }
        }
        
        if ((field_70153_n != null) && 
          (field_70153_n.field_70128_L)) {
          field_70153_n = null;
        }
      }
    }
  }
  




  public void func_70043_V()
  {
    super.func_70043_V();
  }
  
  protected void func_70014_b(net.minecraft.nbt.NBTTagCompound par1NBTTagCompound)
  {
    par1NBTTagCompound.func_74778_a("CustomName", getCustomNameTag());
    int brushColor = getBrushColor();
    if (brushColor >= 0) {
      par1NBTTagCompound.func_74774_a("BrushColor", Byte.valueOf((byte)brushColor).byteValue());
    }
  }
  
  protected void func_70037_a(net.minecraft.nbt.NBTTagCompound par1NBTTagCompound)
  {
    if ((par1NBTTagCompound.func_74764_b("CustomName")) && (par1NBTTagCompound.func_74779_i("CustomName").length() > 0))
    {
      setCustomNameTag(par1NBTTagCompound.func_74779_i("CustomName"));
    }
    
    if ((par1NBTTagCompound.func_74764_b("BrushColor")) && (par1NBTTagCompound.func_74771_c("BrushColor") >= 0)) {
      setBrushColor(par1NBTTagCompound.func_74771_c("BrushColor"));
    }
  }
  
  @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
  public float func_70053_R()
  {
    return 0.0F;
  }
  
  boolean riderHasOwlFamiliar = false;
  boolean riderHasSoaringBrew = false;
  
  public boolean func_130002_c(EntityPlayer player)
  {
    if ((field_70153_n != null) && ((field_70153_n instanceof EntityPlayer)) && (field_70153_n != player))
      return true;
    if ((!field_70170_p.field_72995_K) && (player.func_70694_bm() != null) && (player.func_70694_bm().func_77973_b() == net.minecraft.init.Items.field_151100_aR)) {
      net.minecraft.item.ItemStack itemstack = player.func_70694_bm();
      int i = net.minecraft.block.BlockColored.func_150032_b(itemstack.func_77960_j());
      setBrushColor(i);
      
      if (!field_71075_bZ.field_75098_d) {
        field_77994_a -= 1;
      }
      
      if (field_77994_a <= 0) {
        field_71071_by.func_70299_a(field_71071_by.field_70461_c, (net.minecraft.item.ItemStack)null);
      }
      return true;
    }
    if (!field_70170_p.field_72995_K) {
      riderHasOwlFamiliar = com.emoniph.witchery.familiar.Familiar.hasActiveBroomMasteryFamiliar(player);
      riderHasSoaringBrew = com.emoniph.witchery.infusion.InfusedBrewEffect.Soaring.isActive(player);
      player.func_70078_a(this);
    }
    
    return true;
  }
  
  public void setDamageTaken(float par1)
  {
    field_70180_af.func_75692_b(19, Float.valueOf(par1));
  }
  
  public float getDamageTaken() {
    return field_70180_af.func_111145_d(19);
  }
  
  public void setTimeSinceHit(int par1) {
    field_70180_af.func_75692_b(17, Integer.valueOf(par1));
  }
  
  public int getTimeSinceHit() {
    return field_70180_af.func_75679_c(17);
  }
  
  public void setForwardDirection(int par1) {
    field_70180_af.func_75692_b(18, Integer.valueOf(par1));
  }
  
  public int getForwardDirection() {
    return field_70180_af.func_75679_c(18);
  }
  
  @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
  public void func_70270_d(boolean par1) {
    field_70279_a = par1;
  }
}
