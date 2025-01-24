package com.noctis.assembler.code;

import com.noctis.assembler.utils.StringUtils;

import java.util.*;

/**
 * @author Noctis
 * @date 2025/01/24
 */
public class CInstructionCodeGenerator {

   private static final Map<String, String> DESTINATION_MAP = initializeDestinationMap();
   private static final Map<String, String> COMPUTATION_MAP = initializeComputationMap();
   private static final Map<String, String> JUMP_MAP = initializeJumpMap();

   private static Map<String, String> initializeDestinationMap() {
      Map<String, String> map = new HashMap<>(8);
      map.put("null", "000");
      map.put("M", "001");
      map.put("D", "010");
      map.put("DM", "011");
      map.put("A", "100");
      map.put("AM", "101");
      map.put("AD", "110");
      map.put("ADM", "111");
      return Collections.unmodifiableMap(map);
   }

   private static Map<String, String> initializeComputationMap() {
      Map<String, String> map = new HashMap<>(28);
      map.put("0", "0101010");
      map.put("1", "0111111");
      map.put("-1", "0111010");
      map.put("D", "0001100");
      map.put("A", "0110000");
      map.put("!D", "0001101");
      map.put("!A", "0110001");
      map.put("!M", "1110001");
      map.put("-D", "0001111");
      map.put("-A", "0110011");
      map.put("-M", "1110011");
      map.put("D+1", "0011111");
      map.put("A+1", "0110111");
      map.put("M+1", "1110111");
      map.put("D-1", "0001110");
      map.put("A-1", "0110010");
      map.put("M-1", "1110010");
      map.put("D+A", "0000010");
      map.put("D+M", "1000010");
      map.put("D-A", "0010011");
      map.put("D-M", "1010011");
      map.put("A-D", "0000111");
      map.put("M-D", "1000111");
      map.put("D&A", "0000000");
      map.put("D&M", "1000000");
      map.put("D|A", "0010101");
      map.put("D|M", "1010101");
      return Collections.unmodifiableMap(map);
   }

   private static Map<String, String> initializeJumpMap() {
      Map<String, String> map = new HashMap<>(8);
      map.put("null", "000");
      map.put("JGT", "001");
      map.put("JEQ", "010");
      map.put("JGE", "011");
      map.put("JLT", "100");
      map.put("JNE", "101");
      map.put("JLE", "110");
      map.put("JMP", "111");
      return Collections.unmodifiableMap(map);
   }

   public String dest(String destField) {
      if (StringUtils.isEmpty(destField)) {
         return null;
      }
      return DESTINATION_MAP.getOrDefault(destField, null);
   }

   public String comp(String compField) {
      if (StringUtils.isEmpty(compField)) {
         return null;
      }
      return COMPUTATION_MAP.getOrDefault(compField, null);
   }

   public String jump(String jumpField) {
      if (StringUtils.isEmpty(jumpField)) {
         return null;
      }
      return JUMP_MAP.getOrDefault(jumpField, null);
   }
}
