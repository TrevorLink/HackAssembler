package com.noctis.assembler.common;

/**
 * @author Noctis
 * @date 2025/01/24
 */
public enum InstructionType {
   /**
    * for {@code @xxx}, where xxx is either a decimal number or a symbol
    */
   A_INSTRUCTION,
   /**
    * for {@code dest=comp;jump}
    */
   C_INSTRUCTION,
   /**
    * for label
    */
   L_INSTRUCTION
}
