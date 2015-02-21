package org.dudu.gcode;

public class CompilerException extends RuntimeException {
    public final int lineNumber;
    public final String lineContent;
    public final String message;

    public CompilerException(int lineNumber, String lineContent, Exception e) {
        super(e);
        this.lineNumber = lineNumber;
        this.lineContent = lineContent;
        this.message = e.getMessage();
    }

    public CompilerException(int lineNumber, String lineContent, String message) {
        super(message);
        this.lineNumber = lineNumber;
        this.lineContent = lineContent;
        this.message = message;
    }
    
    public void printError() {
        System.out.println("ERROR on line " + lineNumber + ": '" + lineContent + "'");
        System.out.println("    " + message);
        if (DEBUG) {
            System.out.println();
            printStackTrace();
        }
    }
    
    static boolean DEBUG = Boolean.valueOf(System.getProperty(CompilerException.class.getName() + ".debug"));
}
