package com.emoniph.witchery.integration;

import cpw.mods.fml.common.registry.FMLControlledNamespacedRegistry;
import cpw.mods.fml.common.registry.GameData;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class ModHookTreecapitator extends ModHook
{
  public ModHookTreecapitator() {}
  
  public String getModID()
  {
    return "TreeCapitator";
  }
  
  protected void doInit()
  {
    NBTTagCompound tpModCfg = new NBTTagCompound();
    tpModCfg.func_74778_a("modID", "witchery");
    
    NBTTagList treeList = new NBTTagList();
    
    NBTTagCompound treeDef = new NBTTagCompound();
    treeDef.func_74778_a("treeName", "rowan");
    String logName = GameData.getBlockRegistry().func_148750_c(BlocksLOG);
    String leafName = GameData.getBlockRegistry().func_148750_c(BlocksLEAVES);
    treeDef.func_74778_a("logs", String.format("%s,0; %s,4; %s,8", new Object[] { logName, logName, logName }));
    treeDef.func_74778_a("leaves", String.format("%s,0", new Object[] { leafName }));
    treeList.func_74742_a(treeDef);
    
    treeDef = new NBTTagCompound();
    treeDef.func_74778_a("treeName", "alder");
    treeDef.func_74778_a("logs", String.format("%s,1; %s,5; %s,9", new Object[] { logName, logName, logName }));
    treeDef.func_74778_a("leaves", String.format("%s,1", new Object[] { leafName }));
    treeList.func_74742_a(treeDef);
    
    treeDef = new NBTTagCompound();
    treeDef.func_74778_a("treeName", "hawthorn");
    treeDef.func_74778_a("logs", String.format("%s,2; %s,6; %s,10", new Object[] { logName, logName, logName }));
    treeDef.func_74778_a("leaves", String.format("%s,2", new Object[] { leafName }));
    treeList.func_74742_a(treeDef);
    
    tpModCfg.func_74782_a("trees", treeList);
    
    cpw.mods.fml.common.event.FMLInterModComms.sendMessage(getModID(), "ThirdPartyModConfig", tpModCfg);
  }
  
  protected void doPostInit() {}
  
  protected void doReduceMagicPower(EntityLivingBase entity, float factor) {}
}
