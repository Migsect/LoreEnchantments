package net.samongi.LoreEnchantments.Utilities;

public class StringUtilities
{
  /**Returns the int value of the input numeral.
   *    
   * @param numeral The string in the format of a roman numeral
   * @return The value of the numeral as an int.  Will return 0 if containing illegal digit.
   */
  public static int numeralToInt(String numeral)
  {
    int sum = 0;
    // looping through the string.
    int max = 0;
    for(int c = numeral.length() -1; c >= 0; c--)
    {
      int cur_num = numeralVal(numeral.charAt(c));
      if(cur_num == 0) return 0; // If we find an illegal character.
      if(cur_num > max)
      {
        max = cur_num;
        sum += cur_num;
      }
      else if(cur_num < max) sum -= cur_num;
      else sum += cur_num;
    }
    return sum;
  }
  private static int numeralVal(char numeral)
  {
    switch(Character.toUpperCase(numeral))
    {
      case 'I':
        return 1;
      case 'V':
        return 5;
      case 'X':
        return 10;
      case 'L':
        return 50;
      case 'C':
        return 100;
      case 'D':
        return 500;
      case 'M':
        return 1000;
    }
    return 0;
  }
  
  /**Converts an int into a roman numeral.
   * @param number Number to be converted into roman numerals
   * @return String representation of the roman numeral.
   */
  public static String intToNumeral(int number)
  {
    String numeral = "";
    while(number > 0)
    {
      if(number >= 1000)
      {
        number -= 1000;
        numeral = numeral + "M";
      }
      else if(number >= 900)
      {
        number -= 900;
        numeral = numeral + "CM";
      }
      else if(number >= 500)
      {
        number -= 500;
        numeral = numeral + "D";
      }
      else if(number >= 400)
      {
        number -= 400;
        numeral = numeral + "DC";
      }
      else if(number >= 100)
      {
        number -= 100;
        numeral = numeral + "C";
      }
      else if(number >= 90)
      {
        number -= 90;
        numeral = numeral + "XC";
      }
      else if(number >= 50)
      {
        number -= 50;
        numeral = numeral + "L";
      }
      else if(number >= 40)
      {
        number -= 40;
        numeral = numeral + "XL";
      }
      else if(number >= 10)
      {
        number -= 10;
        numeral = numeral + "X";
      }
      else if(number >= 9)
      {
        number -= 9;
        numeral = numeral + "IX";
      }
      else if(number >= 5)
      {
        number -= 5;
        numeral = numeral + "V";
      }
      else if(number >= 4)
      {
        number -= 4;
        numeral = numeral + "IV";
      }
      else if(number >= 1)
      {
        number -= 1;
        numeral = numeral + "I";
      }
      if(number < 0) return "ERROR";
    }
    return numeral;
  }
  
  public static int getSeconds(String time)
  {
    int seconds = 0;
    String[] split_str = time.split(":"); // Splits along colons.
    int[] times = new int[split_str.length]; 
    for(int i = 0 ; i < split_str.length;
        i++)
    {  
      times[i] = 0;
      try{times[i] = Integer.parseInt(split_str[i]);}catch(NumberFormatException e){}
      //if(times[i] == 0) return 0;
    }
    if(times.length > 0) seconds += times[times.length - 1];
    if(times.length > 1) seconds += 60 * times[times.length - 2];
    if(times.length > 2) seconds += 60 * times[times.length - 3];
    return seconds;
  }
}
