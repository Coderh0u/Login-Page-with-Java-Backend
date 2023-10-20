import java.util.HashMap;
import java.util.Map;

public class EncoderDecoder {
  private String referenceTable; // the 44 characters
  private Map<Character, Integer> encoderMap; // dictionary

  public EncoderDecoder(String referenceTable) {
    this.referenceTable = referenceTable;
    initializeEncoderMap();
  }

  // building the dictionary
  private void initializeEncoderMap() {
    encoderMap = new HashMap<>();
    for (int i = 0; i < referenceTable.length(); i++) {
      encoderMap.put(referenceTable.charAt(i), i);
    }
  }

  // encoding function
  public String encode(String text, char offsetChar) {
    StringBuilder encodedText = new StringBuilder();
    encodedText.append(offsetChar);
    int offset = encoderMap.get(offsetChar);
    char[] textArray = text.toCharArray();
    for (int i = 0; i < textArray.length; i++) {
      if (encoderMap.containsKey(textArray[i])) {
        int index = (encoderMap.get(textArray[i]) - offset + referenceTable.length()) % referenceTable.length();
        encodedText.append(referenceTable.charAt(index));
      } else {
        encodedText.append(textArray[i]);
      }
    }
    return encodedText.toString();
  }

  public String decode(String encodedText) {
    StringBuilder text = new StringBuilder();
    int offset = encoderMap.get(encodedText.charAt(0));
    char[] encodedArray = encodedText.substring(1).toCharArray();
    for (int i = 0; i < encodedArray.length; i++) {
      if (encoderMap.containsKey(encodedArray[i])) {
        int index = (encoderMap.get(encodedArray[i]) + offset) % referenceTable.length();
        text.append(referenceTable.charAt(index));
      } else {
        text.append(encodedArray[i]);
      }
    }
    return text.toString();
  }

  public static void main(String[] args) {
    String referenceTable = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()*+,-./";
    EncoderDecoder encoderDecoder = new EncoderDecoder(referenceTable);

    char offsetChar = 'F';
    String text = "HELLO WORLD";
    String encodedText = encoderDecoder.encode(text, offsetChar);
    System.out.println(encodedText);

    String decodedText = encoderDecoder.decode(encodedText);
    System.out.println(decodedText);
  }

}
