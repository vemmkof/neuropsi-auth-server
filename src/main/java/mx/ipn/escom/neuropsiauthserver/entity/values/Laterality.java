package mx.ipn.escom.neuropsiauthserver.entity.values;

public enum Laterality {
  LEFT_HANDED("LEFT_HANDED"), RIGHT_HANDED("RIGHT_HANDED"), AMBIDEXTROUS("AMBIDEXTROUS");

  String lateralityValue;

  Laterality(String laterality) {
    lateralityValue = laterality;
  }


}
