package com.emoniph.witchery.brewing;

import com.emoniph.witchery.Witchery;

public class BrewNamePart {
  public static enum Position { NONE,  PREFIX,  POSTFIX;
    
    private Position() {} }
  
  protected final String text;
  protected final String invertedText;
  protected final Position position;
  protected long baseDuration;
  protected long invertedDuration;
  public BrewNamePart(String resourceId) { this(resourceId, resourceId, Position.NONE); }
  
  public BrewNamePart(String resourceId, Position position)
  {
    this(resourceId, resourceId, position);
  }
  
  public BrewNamePart(String resourceId, String invertedResourceId) {
    this(resourceId, invertedResourceId, Position.NONE);
  }
  
  public BrewNamePart(String resourceId, String invertedResourceId, Position position) {
    text = Witchery.resource(resourceId);
    invertedText = Witchery.resource(invertedResourceId);
    this.position = position;
  }
  
  public void applyTo(BrewNameBuilder nameBuilder) {
    switch (1.$SwitchMap$com$emoniph$witchery$brewing$BrewNamePart$Position[position.ordinal()]) {
    case 1: 
      nameBuilder.append(text, invertedText, baseDuration, invertedDuration);
      break;
    case 2: 
      nameBuilder.appendPrefix(text);
      break;
    case 3: 
      nameBuilder.appendPostfix(text);
    }
  }
  
  public BrewNamePart setBaseDuration(long baseDuration, long invertedDuration)
  {
    this.baseDuration = baseDuration;
    this.invertedDuration = invertedDuration;
    return this;
  }
  
  public BrewNamePart setBaseDuration(int baseDuration) {
    return setBaseDuration(baseDuration, baseDuration);
  }
}
