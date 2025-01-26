package com.noctis.assembler.common;

/**
 * @author Noctis
 * @date 2025/01/26
 */
public class HackConstants {
   /**
    * Hack assembly language comments identifier
    */
   public static final String ASM_COMMENTS = "//";

   /**
    * A_INSTRUCTION for @xxx where xxx is either a decimal number or a symbol
    */
   public static final String A_INSTRUCTION_PATTERN = "^\\@[a-zA-Z0-9_]+$";

   /**
    * C_INSTRUCTION for dest=comp;jump
    */
   public static final String C_INSTRUCTION_PATTERN = "^(?:(null|MD|DM|D|M|A|AM|AD|ADM)=)?(0|1|-1|D|A|M|!D|!A|!M|-D|-A|-M|D\\+1|A\\+1|M\\+1|D-1|A-1|M-1|D\\+A|D\\+M|D-A|D-M|A-D|M-D|D&A|D&M|D\\|A|D\\|M)(?:;(null|JGT|JEQ|JGE|JLT|JNE|JLE|JMP))?$";

   /**
    * L_INSTRUCTION for (xxx) where xxx is a symbol
    */
   public static final String L_INSTRUCTION_PATTERN = "^\\([a-zA-Z_][a-zA-Z0-9_]*\\)$";

   /**
    * The default address for symbols not in the symbol table
    */
   public static final int SYMBOL_MISSING_DEFAULT_ADDRESS = -1;

   /**
    * The prefix of binary format of C instructions
    */
   public static final String C_INSTRUCTION_PREFIX = "111";

   /**
    * Translation result file name
    */
   public static final String TRANSLATION_OUT_FILE_NAME = "Prog.hack";
}
