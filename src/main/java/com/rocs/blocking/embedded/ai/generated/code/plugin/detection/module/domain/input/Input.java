package com.rocs.blocking.embedded.ai.generated.code.plugin.detection.module.domain.input;

public class Input {
   private int lines;
   private int chars;
   private int token;
   private int ifStmt;
   private double tokenLength;
   private int method;
   private int methodLength;
   private int switchStmt;
   private int loop;

   /**
    * gets the total number of line
    * */
    public int getLines() {
        return lines;
    }
    /**
     * Sets the number of lines in the source file.
     *
     * @param lines the total number of lines
     */
    public void setLines(int lines) {
        this.lines = lines;
    }
    /**
     * Gets the number of characters in the source file.
     *
     * @return the total character count
     */
    public int getChars() {
        return chars;
    }
    /**
     * Sets the number of characters in the source file.
     *
     * @param chars the total character count
     */
    public void setChars(int chars) {
        this.chars = chars;
    }
    /**
     * Gets the number of tokens in the source file.
     *
     * @return the total token count
     */
    public int getToken() {
        return token;
    }
    /**
     * Sets the number of tokens in the source file.
     *
     * @param token the total token count
     */
    public void setToken(int token) {
        this.token = token;
    }
    /**
     * Gets the number of if-statements in the source file.
     *
     * @return the number of if-statements
     */
    public int getIfStmt() {
        return ifStmt;
    }
    /**
     * Sets the number of if-statements in the source file.
     *
     * @param ifStmt the number of if-statements
     */
    public void setIfStmt(int ifStmt) {
        this.ifStmt = ifStmt;
    }
    /**
     * Gets the average token length in the source file.
     *
     * @return the average token length
     */
    public double getTokenLength() {
        return tokenLength;
    }
    /**
     * Sets the average token length in the source file.
     *
     * @param tokenLength the average token length
     */
    public void setTokenLength(double tokenLength) {
        this.tokenLength = tokenLength;
    }
    /**
     * Gets the number of methods defined in the source file.
     *
     * @return the number of methods
     */
    public int getMethod() {
        return method;
    }
    /**
     * Sets the number of methods defined in the source file.
     *
     * @param method the number of methods
     */
    public void setMethod(int method) {
        this.method = method;
    }
    /**
     * Gets the average method length in the source file.
     *
     * @return the average method length
     */
    public int getMethodLength() {
        return methodLength;
    }
    /**
     * Sets the average method length in the source file.
     *
     * @param methodLength the average method length
     */
    public void setMethodLength(int methodLength) {
        this.methodLength = methodLength;
    }
    /**
     * Gets the number of switch statements in the source file.
     *
     * @return the number of switch statements
     */
    public int getSwitchStmt() {
        return switchStmt;
    }
    /**
     * Sets the number of switch statements in the source file.
     *
     * @param switchStmt the number of switch statements
     */
    public void setSwitchStmt(int switchStmt) {
        this.switchStmt = switchStmt;
    }
    /**
     * Gets the number of loops (for, while, do-while) in the source file.
     *
     * @return the number of loops
     */
    public int getLoop() {
        return loop;
    }
    /**
     * Sets the number of loops (for, while, do-while) in the source file.
     *
     * @param loop the number of loops
     */
    public void setLoop(int loop) {
        this.loop = loop;
    }
}
