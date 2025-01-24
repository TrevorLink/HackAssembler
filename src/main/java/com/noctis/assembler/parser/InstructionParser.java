package com.noctis.assembler.parser;

import com.noctis.assembler.common.InstructionType;
import com.noctis.assembler.utils.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Noctis
 * @date 2025/01/24
 */
public class InstructionParser {
   private final List<String> instructionList;
   private int index;
   private final String currentInstruction = null;

   protected InstructionParser(String hackAsmFileLocation) {
      index = 0;
      try {
         instructionList = Files.lines(Paths.get(hackAsmFileLocation)).collect(Collectors.toList());
      } catch (IOException e) {
         throw new RuntimeException("Error occurred when reading file :" + hackAsmFileLocation);
      }
   }


   /**
    * Checks if there is more work to do
    *
    * @return whether more instruction line remains
    */
   protected boolean hasMoreLines() {
      return index < instructionList.size();
   }

   /**
    * Gets thr next instruction and makes it the current instruction
    *
    * @return current instruction
    */
   protected String advance() {
      if (StringUtils.isEmpty(currentInstruction) || !hasMoreLines()) {
         return null;
      }
      String instruction = instructionList.get(index++);
      return null;
   }

   /**
    * Get current instruction's type
    *
    * @return InstructionType
    */
   protected InstructionType instructionType() {
      return null;
   }

   /**
    * Returns current instruction’s symbol
    *
    * @return instruction’s symbol
    */
   protected String symbol() {
      return null;
   }

   /**
    * Returns current instruction’s dest field
    *
    * @return C instruction’s dest field, if current instruction is not C type, return null
    */
   protected String dest() {
      return null;
   }

   /**
    * Returns current instruction’s comp field
    *
    * @return C instruction’s comp field, if current instruction is not C type, return null
    */
   protected String comp() {
      return null;
   }

   /**
    * Returns current instruction’s jmp field
    *
    * @return C instruction’s jmp field, if current instruction is not C type, return null
    */
   protected String jump() {
      return null;
   }
}
