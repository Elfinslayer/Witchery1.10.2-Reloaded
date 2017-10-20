package com.emoniph.witchery.util;

import net.minecraft.client.settings.KeyBinding;

public class KeyBindHelper
{
  public KeyBindHelper() {}
  
  public static boolean isKeyBindDown(KeyBinding keyBinding) {
    return keyBinding.func_151463_i() >= 0 ? org.lwjgl.input.Keyboard.isKeyDown(keyBinding.func_151463_i()) : org.lwjgl.input.Mouse.isButtonDown(keyBinding.func_151463_i() + 100);
  }
}
