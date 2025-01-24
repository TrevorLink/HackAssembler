package com.noctis.assembler.utils;

/**
 * @author Noctis
 * @date 2025/01/24
 */
public class StringUtils {

   public static boolean isNotEmpty(String str) {
      return str != null && str.isEmpty();
   }

   public static boolean isEmpty(String str) {
      return !isNotEmpty(str);
   }

}
