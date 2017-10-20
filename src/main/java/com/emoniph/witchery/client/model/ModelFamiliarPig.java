package com.emoniph.witchery.client.model;

import com.emoniph.witchery.entity.EntityFamiliar;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

@cpw.mods.fml.relauncher.SideOnly(Side.CLIENT)
public class ModelFamiliarPig extends ModelPig
{
  public ModelFamiliarPig()
  {
    this(0.0F);
  }
  
  public ModelFamiliarPig(float par1) {
    super(par1);
  }
  
  public void func_78086_a(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4) {
    EntityFamiliar entityocelot = (EntityFamiliar)par1EntityLivingBase;
    
    field_78150_a.func_78793_a(0.0F, 12.0F, -6.0F);
    field_78148_b.func_78793_a(0.0F, 11.0F, 2.0F);
    field_78149_c.func_78793_a(-3.0F, 18.0F, 7.0F);
    field_78146_d.func_78793_a(3.0F, 18.0F, 7.0F);
    field_78147_e.func_78793_a(-3.0F, 18.0F, -5.0F);
    field_78144_f.func_78793_a(3.0F, 18.0F, -5.0F);
    
    if (entityocelot.func_70906_o()) {
      field_78148_b.field_78795_f = 0.7853982F;
      field_78148_b.field_78797_d += 3.5F;
      field_78148_b.field_78798_e += 0.0F;
      

      field_78149_c.field_78795_f = (field_78146_d.field_78795_f = -0.15707964F);
      field_78149_c.field_78797_d = (field_78146_d.field_78797_d = 15.8F);
      field_78149_c.field_78798_e = (field_78146_d.field_78798_e = -7.0F);
      field_78147_e.field_78795_f = (field_78144_f.field_78795_f = -1.5707964F);
      field_78147_e.field_78797_d = (field_78144_f.field_78797_d = 21.0F);
      field_78147_e.field_78798_e = (field_78144_f.field_78798_e = 1.0F);
      field_78163_i = 3;
    } else {
      field_78163_i = 1;
    }
  }
  
  int field_78163_i = 1;
  
  public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
    field_78150_a.field_78795_f = (par5 / 57.295776F);
    field_78150_a.field_78796_g = (par4 / 57.295776F);
    
    if (field_78163_i != 3) {
      field_78148_b.field_78795_f = 1.5707964F;
      
      field_78147_e.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F) * 1.0F * par2);
      field_78144_f.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.0F * par2);
      field_78149_c.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.0F * par2);
      field_78146_d.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F) * 1.0F * par2);
    }
  }
}
