package com.noctis.assembler;

import com.noctis.assembler.code.CInstructionCodeGenerator;
import com.noctis.assembler.common.HackConstants;
import com.noctis.assembler.common.InstructionType;
import com.noctis.assembler.parser.InstructionParser;
import com.noctis.assembler.symboltable.SymbolTable;
import com.noctis.assembler.utils.StringUtils;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Noctis
 * @date 2025/01/24
 */
public class HackAssembler {
   public static void main(String[] args) throws IOException {
      //0-Arguments validation
      if (args.length == 0) {
         throw new RuntimeException("Assembly file location not specified");
      }
      String hackAsmFileLocation = args[0];
      if (StringUtils.isEmpty(hackAsmFileLocation)) {
         throw new RuntimeException("Empty assembly file location");
      }

      //1-Assembler components initialization
      InstructionParser instructionParser = new InstructionParser(hackAsmFileLocation);
      SymbolTable symbolTable = new SymbolTable();
      CInstructionCodeGenerator cInstructionCodeGenerator = new CInstructionCodeGenerator();
      FileWriter fw = new FileWriter(HackConstants.TRANSLATION_OUT_FILE_NAME);

      //2-First pass, focusing on labels, fill the symbol table according to the code architecture
      while (instructionParser.hasMoreLines()) {
         instructionParser.advance();
         String symbol = instructionParser.symbol();
         InstructionType instructionType = instructionParser.instructionType();
         if (StringUtils.isNotEmpty(symbol) && !symbolTable.contains(symbol)
                 && InstructionType.L_INSTRUCTION.equals(instructionType)) {
            //Label's symbol value is its row number
            symbolTable.addEntry(symbol, instructionParser.getCurrentRowNumber());
         }
      }

      //3-Second pass, translate the assembly code
      instructionParser.reset();
      while (instructionParser.hasMoreLines()) {
         instructionParser.advance();
         InstructionType instructionType = instructionParser.instructionType();
         String translateResult;
         if (InstructionType.A_INSTRUCTION.equals(instructionType)) {
            String symbol = instructionParser.symbol();
            if (StringUtils.isNotEmpty(symbol)) {
               //symbol not in the table, add it first
               if (!symbolTable.contains(symbol)) {
                  symbolTable.addEntry(symbol);
               }
               //translate its value to binary value
               int address = symbolTable.getAddress(symbol);
               if (address != HackConstants.SYMBOL_MISSING_DEFAULT_ADDRESS) {
                  translateResult = String.format("%16s", Integer.toBinaryString(address)).replace(' ', '0');
                  fw.write(translateResult + System.lineSeparator());
               }
            }
         } else if (InstructionType.C_INSTRUCTION.equals(instructionType)) {
            //translate each three field of the c instruction into its binary value and concat
            translateResult = HackConstants.C_INSTRUCTION_PREFIX + cInstructionCodeGenerator.comp(instructionParser.comp()) +
                    cInstructionCodeGenerator.dest(instructionParser.dest()) +
                    cInstructionCodeGenerator.jump(instructionParser.jump());
            fw.write(translateResult + System.lineSeparator());
         }
      }
      fw.close();
   }
}
