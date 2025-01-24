package com.noctis.assembler.symboltable;

import com.noctis.assembler.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Noctis
 * @date 2025/01/24
 */
public class SymbolTable {

   public static final int SYMBOL_MISSING_DEFAULT_VALUE = -1;
   private final Map<String, Integer> symbolTable;

   /**
    * Get hack assembler's symbol table with pre-defined symbol loaded
    */
   public SymbolTable() {
      //Creates a new empty symbol table
      this.symbolTable = new HashMap<>();
      loadPreDefinedSymbol();
   }

   public void addEntry(String symbol, Integer address) {
      if (StringUtils.isNotEmpty(symbol) && address != null) {
         symbolTable.put(symbol, address);
      }
   }

   public boolean contains(String symbol) {
      if (StringUtils.isNotEmpty(symbol)) {
         return symbolTable.containsKey(symbol);
      }
      return false;
   }

   public int getAddress(String symbol) {
      if (StringUtils.isNotEmpty(symbol)) {
         symbolTable.getOrDefault(symbol, SYMBOL_MISSING_DEFAULT_VALUE);
      }
      return SYMBOL_MISSING_DEFAULT_VALUE;
   }

   private void loadPreDefinedSymbol() {
      if (symbolTable == null) {
         throw new RuntimeException("Symbol table has not been initialized yet");
      }
      //RO-R15 registers
      symbolTable.put("R0", 0);
      symbolTable.put("R1", 1);
      symbolTable.put("R2", 2);
      symbolTable.put("R3", 3);
      symbolTable.put("R4", 4);
      symbolTable.put("R5", 5);
      symbolTable.put("R6", 6);
      symbolTable.put("R7", 7);
      symbolTable.put("R8", 8);
      symbolTable.put("R9", 9);
      symbolTable.put("R10", 10);
      symbolTable.put("R11", 11);
      symbolTable.put("R12", 12);
      symbolTable.put("R13", 13);
      symbolTable.put("R14", 14);
      symbolTable.put("R15", 15);
      //SCREEN & KBD
      symbolTable.put("SCREEN", 16384);
      symbolTable.put("KBD", 24576);
      //keywords
      symbolTable.put("SP", 0);
      symbolTable.put("LCL", 1);
      symbolTable.put("ARG", 2);
      symbolTable.put("THIS", 3);
      symbolTable.put("THAT", 4);
   }
}
