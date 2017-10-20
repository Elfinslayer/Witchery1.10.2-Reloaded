package com.emoniph.witchery.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class ModelLouse
  extends ModelBase
{
  private ModelRenderer[] silverfishBodyParts = new ModelRenderer[7];
  
  private ModelRenderer[] silverfishWings;
  
  private float[] field_78170_c = new float[7];
  

  private static final int[][] silverfishBoxLength = { { 3, 2, 2 }, { 4, 3, 2 }, { 4, 3, 2 }, { 3, 3, 3 }, { 2, 2, 3 }, { 2, 1, 2 }, { 1, 1, 2 } };
  

  private static final int[][] silverfishTexturePositions = { { 0, 0 }, { 0, 4 }, { 0, 9 }, { 0, 16 }, { 0, 22 }, { 11, 0 }, { 13, 4 } };
  
  public ModelLouse()
  {
    float f = -3.5F;
    
    for (int i = 0; i < silverfishBodyParts.length; i++)
    {
      silverfishBodyParts[i] = new ModelRenderer(this, silverfishTexturePositions[i][0], silverfishTexturePositions[i][1]);
      silverfishBodyParts[i].func_78789_a(silverfishBoxLength[i][0] * -0.5F, 0.0F, silverfishBoxLength[i][2] * -0.5F, silverfishBoxLength[i][0], silverfishBoxLength[i][1], silverfishBoxLength[i][2]);
      silverfishBodyParts[i].func_78793_a(0.0F, 24 - silverfishBoxLength[i][1], f);
      field_78170_c[i] = f;
      
      if (i < silverfishBodyParts.length - 1)
      {
        f += (silverfishBoxLength[i][2] + silverfishBoxLength[(i + 1)][2]) * 0.5F;
      }
    }
    
    silverfishWings = new ModelRenderer[3];
    silverfishWings[0] = new ModelRenderer(this, 20, 0);
    silverfishWings[0].func_78789_a(-5.0F, 0.0F, silverfishBoxLength[2][2] * -0.5F, 10, 8, silverfishBoxLength[2][2]);
    silverfishWings[0].func_78793_a(0.0F, 16.0F, field_78170_c[2]);
    silverfishWings[1] = new ModelRenderer(this, 20, 11);
    silverfishWings[1].func_78789_a(-3.0F, 0.0F, silverfishBoxLength[4][2] * -0.5F, 6, 4, silverfishBoxLength[4][2]);
    silverfishWings[1].func_78793_a(0.0F, 20.0F, field_78170_c[4]);
    silverfishWings[2] = new ModelRenderer(this, 20, 18);
    silverfishWings[2].func_78789_a(-3.0F, 0.0F, silverfishBoxLength[4][2] * -0.5F, 6, 5, silverfishBoxLength[1][2]);
    silverfishWings[2].func_78793_a(0.0F, 19.0F, field_78170_c[1]);
  }
  



  public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
  {
    func_78087_a(par2, par3, par4, par5, par6, par7, par1Entity);
    

    for (int i = 0; i < silverfishBodyParts.length; i++)
    {
      silverfishBodyParts[i].func_78785_a(par7);
    }
    
    for (i = 0; i < silverfishWings.length; i++)
    {
      silverfishWings[i].func_78785_a(par7);
    }
  }
  





  public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
  {
    for (int i = 0; i < silverfishBodyParts.length; i++)
    {
      silverfishBodyParts[i].field_78796_g = (MathHelper.func_76134_b(par3 * 0.9F + i * 0.15F * 3.1415927F) * 3.1415927F * 0.05F * (1 + Math.abs(i - 2)));
      silverfishBodyParts[i].field_78800_c = (MathHelper.func_76126_a(par3 * 0.9F + i * 0.15F * 3.1415927F) * 3.1415927F * 0.2F * Math.abs(i - 2));
    }
    
    silverfishWings[0].field_78796_g = silverfishBodyParts[2].field_78796_g;
    silverfishWings[1].field_78796_g = silverfishBodyParts[4].field_78796_g;
    silverfishWings[1].field_78800_c = silverfishBodyParts[4].field_78800_c;
    silverfishWings[2].field_78796_g = silverfishBodyParts[1].field_78796_g;
    silverfishWings[2].field_78800_c = silverfishBodyParts[1].field_78800_c;
  }
}
