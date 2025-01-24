package com.noctis.assembler.parser;

import com.noctis.assembler.common.InstructionType;

/**
 * @author Noctis
 * @date 2025/01/24
 */
public class InstructionParser {

   private final String hackAsmFileLocation;

   protected InstructionParser(String hackAsmFileLocation) {
      this.hackAsmFileLocation = hackAsmFileLocation;
   }


   /**
    * Checks if there is more work to do
    *
    * @return whether more instruction line remains
    */
   protected boolean hasMoreLines() {
      return false;
   }

   /**
    * Gets thr next instruction and makes it the current instruction
    *
    * @return current instruction
    */
   protected String advance() {
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
