package com.emoniph.witchery.integration;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class ReflectManager
{
  public static HashMap<Class<?>, Class<?>> primitiveWrappers = new HashMap();
  
  static
  {
    primitiveWrappers.put(Integer.TYPE, Integer.class);
    primitiveWrappers.put(Short.TYPE, Short.class);
    primitiveWrappers.put(Byte.TYPE, Byte.class);
    primitiveWrappers.put(Long.TYPE, Long.class);
    primitiveWrappers.put(Double.TYPE, Double.class);
    primitiveWrappers.put(Float.TYPE, Float.class);
    primitiveWrappers.put(Boolean.TYPE, Boolean.class);
    primitiveWrappers.put(Character.TYPE, Character.class);
  }
  
  public static boolean isInstance(Class<?> class1, Object obj)
  {
    Class<?> primitive = (Class)primitiveWrappers.get(class1);
    if (primitive != null)
    {
      if ((primitive == Long.class) && (Long.class.isInstance(obj)))
        return true;
      if (((primitive == Long.class) || (primitive == Integer.class)) && (Integer.class.isInstance(obj)))
        return true;
      if (((primitive == Long.class) || (primitive == Integer.class) || (primitive == Short.class)) && (Short.class.isInstance(obj)))
        return true;
      if (((primitive == Long.class) || (primitive == Integer.class) || (primitive == Short.class) || (primitive == Byte.class)) && (Integer.class.isInstance(obj))) {
        return true;
      }
      if ((primitive == Double.class) && (Double.class.isInstance(obj)))
        return true;
      if (((primitive == Double.class) || (primitive == Float.class)) && (Float.class.isInstance(obj))) {
        return true;
      }
      return primitive.isInstance(obj);
    }
    return class1.isInstance(obj);
  }
  
  public static Class<?> findClass(String name)
  {
    return findClass(name, true);
  }
  
  public static boolean classExists(String name)
  {
    return findClass(name, false) != null;
  }
  
  public static Class<?> findClass(String name, boolean init)
  {
    try
    {
      return Class.forName(name, init, ReflectManager.class.getClassLoader());
    }
    catch (ClassNotFoundException cnfe)
    {
      try
      {
        return Class.forName("net.minecraft.src." + name, init, ReflectManager.class.getClassLoader());
      }
      catch (ClassNotFoundException cnfe2) {}
    }
    return null;
  }
  

  public static void setField(Class<?> class1, Object instance, String name, Object value)
    throws IllegalArgumentException, IllegalAccessException
  {
    setField(class1, instance, new String[] { name }, value);
  }
  
  public static void setField(Class<?> class1, Object instance, String[] names, Object value) throws IllegalArgumentException, IllegalAccessException
  {
    for (Field field : class1.getDeclaredFields())
    {
      boolean match = false;
      for (String name : names)
      {
        if (field.getName().equals(name))
        {
          match = true;
          break;
        }
      }
      if (match)
      {



        field.setAccessible(true);
        field.set(instance, value);
        return;
      }
    }
  }
  
  public static void setField(Class<?> class1, Object instance, int fieldindex, Object value) throws IllegalArgumentException, IllegalAccessException {
    Field field = class1.getDeclaredFields()[fieldindex];
    field.setAccessible(true);
    field.set(instance, value);
  }
  




  public static void callMethod(Class<?> class1, String name, Object... params)
    throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
  {
    callMethod(class1, null, new String[] { name }, params);
  }
  




  public static void callMethod(Class<?> class1, String[] names, Object... params)
    throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
  {
    callMethod(class1, null, names, params);
  }
  



  public static void callMethod(Class<?> class1, Object instance, String name, Object... params)
    throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
  {
    callMethod(class1, null, instance, new String[] { name }, params);
  }
  


  public static void callMethod(Class<?> class1, Object instance, String[] names, Object... params)
    throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
  {
    callMethod(class1, null, instance, names, params);
  }
  



  public static <R> R callMethod(Class<?> class1, Class<R> returntype, String name, Object... params)
    throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
  {
    return callMethod(class1, returntype, null, new String[] { name }, params);
  }
  


  public static <R> R callMethod(Class<?> class1, Class<R> returntype, String[] names, Object... params)
    throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
  {
    return callMethod(class1, returntype, null, names, params);
  }
  


  public static <R> R callMethod(Class<?> class1, Class<R> returntype, Object instance, String name, Object... params)
    throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
  {
    return callMethod(class1, returntype, instance, new String[] { name }, params);
  }
  
  public static <R> R callMethod(Class<?> class1, Class<R> returntype, Object instance, String[] names, Object... params) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
    label159:
    for (Method method : class1.getDeclaredMethods())
    {
      boolean match = false;
      for (String name : names)
      {
        if (method.getName().equals(name))
        {
          match = true;
          break;
        }
      }
      if (match)
      {



        Class<?>[] paramtypes = method.getParameterTypes();
        if (paramtypes.length == params.length)
        {

          for (int i = 0; i < params.length; i++)
          {
            if (!isInstance(paramtypes[i], params[i])) {
              break label159;
            }
          }
          method.setAccessible(true);
          return method.invoke(instance, params);
        } } }
    return null;
  }
  
  public static <T> T getField(Class<?> class1, Class<T> fieldType, Object instance, int fieldIndex) throws IllegalArgumentException, IllegalAccessException
  {
    Field field = class1.getDeclaredFields()[fieldIndex];
    field.setAccessible(true);
    return field.get(instance);
  }
  
  public static <T> T getField(Class<?> class1, Class<T> fieldType, Object instance, String fieldName)
  {
    try
    {
      Field field = class1.getDeclaredField(fieldName);
      field.setAccessible(true);
      return field.get(instance);
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }
  }
  
  public static <T> T newInstance(Class<T> class1, Object... params) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
    label88:
    for (Constructor<?> constructor : class1.getDeclaredConstructors())
    {
      Class<?>[] paramtypes = constructor.getParameterTypes();
      if (paramtypes.length == params.length)
      {

        for (int i = 0; i < params.length; i++)
        {
          if (!isInstance(paramtypes[i], params[i])) {
            break label88;
          }
        }
        constructor.setAccessible(true);
        return constructor.newInstance(params);
      } }
    return null;
  }
  
  public static boolean hasField(Class<?> class1, String fieldName)
  {
    try
    {
      class1.getDeclaredField(fieldName);
      return true;
    }
    catch (NoSuchFieldException nfe) {}
    
    return false;
  }
  

  public static <T> T get(Field field, Class<T> class1)
  {
    return get(field, class1, null);
  }
  
  public static <T> T get(Field field, Class<T> class1, Object instance)
  {
    try
    {
      return field.get(instance);
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }
  }
  
  public static void set(Field field, Object value)
  {
    set(field, null, value);
  }
  
  public static void set(Field field, Object instance, Object value)
  {
    try
    {
      field.set(instance, value);
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }
  }
  
  public ReflectManager() {}
}
