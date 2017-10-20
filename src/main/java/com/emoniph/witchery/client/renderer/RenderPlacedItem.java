package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.blocks.BlockPlacedItem.TileEntityPlacedItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class RenderPlacedItem
  extends TileEntitySpecialRenderer
{
  private final RenderItem3d renderItems;
  
  public RenderPlacedItem()
  {
    renderItems = new RenderItem3d(true) {
      public byte getMiniItemCountForItemStack(ItemStack stack) {
        return 1;
      }
      
      public byte getMiniBlockCountForItemStack(ItemStack stack) {
        return 1;
      }
      
      public boolean shouldBob()
      {
        return false;
      }
      
      public boolean shouldSpreadItems()
      {
        return false;
      }
    };
    renderItems.func_76976_a(RenderManager.field_78727_a);
  }
  
  public void func_147500_a(TileEntity tileEntity, double d0, double d1, double d2, float f)
  {
    GL11.glPushMatrix();
    GL11.glTranslatef((float)d0, (float)d1, (float)d2);
    renderPlacedItem((BlockPlacedItem.TileEntityPlacedItem)tileEntity, tileEntity.func_145831_w(), field_145851_c, field_145848_d, field_145849_e);
    GL11.glPopMatrix();
  }
  
  public void renderPlacedItem(BlockPlacedItem.TileEntityPlacedItem te, World world, int x, int y, int z) {
    if (world != null) {
      EntityItem ei = new EntityItem(world);
      field_70290_d = 0.0F;
      





      if ((te != null) && (te.getStack() != null)) {
        ei.func_92058_a(te.getStack().func_77946_l());
      } else {
        ei.func_92058_a(new ItemStack(Items.field_151103_aS));
      }
      



      GL11.glTranslatef(0.5F, 0.05F, 0.5F);
      if (world != null) {
        int meta = world.func_72805_g(x, y, z);
        

        float rotation = 0.0F;
        switch (meta) {
        case 2: 
          rotation = 0.0F;
          break;
        case 3: 
          rotation = 180.0F;
          break;
        case 4: 
          rotation = 90.0F;
          break;
        case 5: 
          rotation = 270.0F;
        }
        
        
        GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
      }
      
      GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
      GL11.glTranslatef(0.0F, -0.1F, 0.0F);
      


      renderItems.doRender(ei, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
    }
  }
}
