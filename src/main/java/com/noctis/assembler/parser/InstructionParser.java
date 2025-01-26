package com.noctis.assembler.parser;

import com.noctis.assembler.common.HackConstants;
import com.noctis.assembler.common.InstructionType;
import com.noctis.assembler.utils.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Noctis
 * @date 2025/01/24
 */
public class InstructionParser {
   private final List<String> instructionList;
   private int index;
   private String currentInstruction;

   /**
    * Opens the input file and gets ready to parse it
    *
    * @param hackAsmFileLocation the asm file absolutely location (must not be null)
    */
   public InstructionParser(String hackAsmFileLocation) {
      index = 0;
      try {
         //read the asm code into memory, trim the whitespace when reading
         instructionList = Files.lines(Paths.get(hackAsmFileLocation))
                 .map(content -> content.replaceAll("\\s*", ""))
                 .collect(Collectors.toList());
      } catch (IOException e) {
         throw new RuntimeException("Error occurred when reading file :" + hackAsmFileLocation, e);
      }
   }


   /**
    * Checks if there is more work to do
    *
    * @return whether more instruction line remains
    */
   public boolean hasMoreLines() {
      return index < instructionList.size();
   }

   /**
    * Skips over whitespace and comments if necessary.
    * Reads the next instruction from the input and make it the current instruction.
    *
    * @return current instruction
    */
   public String advance() {
      while (hasMoreLines()) {
         String instruction = instructionList.get(index++);
         if (StringUtils.isEmpty(instruction) || instruction.contains(HackConstants.ASM_COMMENTS)) {
            continue;
         }
         currentInstruction = instruction;
         break;
      }
      return currentInstruction;
   }

   /**
    * Returns the type of the current instruction
    * A_INSTRUCTION for @xxx where xxx is either a decimal number or a symbol
    * C_INSTRUCTION for dest=comp;jump
    * L_INSTRUCTION for (xxx) where xxx is a symbol
    *
    * @return InstructionType
    */
   public InstructionType instructionType() {
      if (StringUtils.isEmpty(currentInstruction)) {
         return null;
      }
      Pattern aPattern = Pattern.compile(HackConstants.A_INSTRUCTION_PATTERN);
      Pattern cPattern = Pattern.compile(HackConstants.C_INSTRUCTION_PATTERN);
      Pattern lPattern = Pattern.compile(HackConstants.L_INSTRUCTION_PATTERN);
      if (aPattern.matcher(currentInstruction).matches()) {
         return InstructionType.A_INSTRUCTION;
      } else if (cPattern.matcher(currentInstruction).matches()) {
         return InstructionType.C_INSTRUCTION;
      } else if (lPattern.matcher(currentInstruction).matches()) {
         return InstructionType.L_INSTRUCTION;
      }
      throw new RuntimeException("Current instruction [" + currentInstruction + "] type miss match");
   }

   /**
    * Returns current instruction’s symbol
    *
    * @return Symbol for A instruction adn L instruction and null of C instruction
    */
   public String symbol() {
      //Should be called only if instruction type is A or L instruction
      InstructionType instructionType = instructionType();
      if (StringUtils.isEmpty(currentInstruction) || InstructionType.C_INSTRUCTION.equals(instructionType)) {
         return null;
      }
      if (InstructionType.A_INSTRUCTION.equals(instructionType)) {
         int begin = currentInstruction.indexOf("@");
         return currentInstruction.substring(begin + 1);
      } else {
         int begin = currentInstruction.indexOf("(");
         int end = currentInstruction.indexOf(")");
         return currentInstruction.substring(begin + 1, end);
      }
   }

   /**
    * Returns current instruction’s dest field
    *
    * @return C instruction’s dest field, if current instruction is not C type, return null
    */
   public String dest() {
      InstructionType instructionType = instructionType();
      if (StringUtils.isEmpty(currentInstruction) || !InstructionType.C_INSTRUCTION.equals(instructionType)) {
         return null;
      }
      int end = currentInstruction.indexOf("=");
      if (end == -1) {
         return "null";
      }
      return currentInstruction.substring(0, end);
   }

   /**
    * Returns current instruction’s comp field
    *
    * @return C instruction’s comp field, if current instruction is not C type, return null
    */
   public String comp() {
      InstructionType instructionType = instructionType();
      if (StringUtils.isEmpty(currentInstruction) || !InstructionType.C_INSTRUCTION.equals(instructionType)) {
         return null;
      }
      int start = currentInstruction.indexOf("=");
      int end = currentInstruction.indexOf(";");
      if (start == -1 && end == -1) {
         throw new RuntimeException("Syntax error found in current instruction [" + currentInstruction + "]");
      } else if (start == -1) {
         return currentInstruction.substring(0, end);
      } else if (end == -1) {
         return currentInstruction.substring(start + 1);
      } else {
         return currentInstruction.substring(start + 1, end);
      }
   }

   /**
    * Returns current instruction’s jmp field
    *
    * @return C instruction’s jmp field, if current instruction is not C type, return null
    */
   public String jump() {
      InstructionType instructionType = instructionType();
      if (StringUtils.isEmpty(currentInstruction) || !InstructionType.C_INSTRUCTION.equals(instructionType)) {
         return null;
      }
      int start = currentInstruction.indexOf(";");
      if (start == -1) {
         return "null";
      }
      return currentInstruction.substring(start + 1);
   }

   /**
    * Get instruction parser current row number of the whole assembly code
    * @return row number of the current instruction in asm file
    */
   public int getCurrentRowNumber() {
      return index;
   }

   /**
    * Reset the instruction parser to its default value
    */
   public void reset(){
      index = 0;
      currentInstruction = null;
   }
}
