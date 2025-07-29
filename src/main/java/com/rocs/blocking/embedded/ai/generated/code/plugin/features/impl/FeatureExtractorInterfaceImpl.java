package com.rocs.blocking.embedded.ai.generated.code.plugin.features.impl;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.*;
import com.rocs.blocking.embedded.ai.generated.code.plugin.features.FeatureExtractorInterface;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public class FeatureExtractorInterfaceImpl implements FeatureExtractorInterface {

    @Override
    public int countNumberOfLines(Path path){
        String lineString;
        int countLines = 0;
        try{
            BufferedReader lineReader = new BufferedReader(new FileReader(String.valueOf(path)));
            while ((lineString = lineReader.readLine()) != null){
                countLines++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return countLines;
    }
    @Override
    public int countNumberOfChar(Path path){
        int chars = 0;
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(String.valueOf(path)));
            String lineString;
            while((lineString = bufferedReader.readLine()) != null){
                chars += lineString.length();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return chars;
    }
    @Override
    public int countNumberOfToken(Path path){
        String code;
        int token = 0;
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(String.valueOf(path)));
            while ((code = bufferedReader.readLine())!=null){
                token += code.split("\\s+").length;
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return token;
    }
    @Override
    public double countAverageTokenLength(int numChar, int numToken){
        return (double) numChar/numToken;
    }
    @Override
    public int countIfStatement(Path path){
        int statement = 0;
        try {
            CompilationUnit compilationUnit = new JavaParser().parse(path).getResult().orElse(null);
            if(compilationUnit != null){
                statement = compilationUnit.findAll(IfStmt.class).size();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return statement;
    }
    @Override
    public int countMethods(Path path){
        int method = 0;
        try {
            CompilationUnit compilationUnit = new JavaParser().parse(path).getResult().orElse(null);
            if(compilationUnit != null){
                method = compilationUnit.findAll(MethodDeclaration.class).size();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return method;
    }
    @Override
    public int averageMethodLength(Path path){
        BlockStmt body;
        int aveMethodLength = 0;
        try {
            CompilationUnit compilationUnit = new JavaParser().parse(path).getResult().orElse(null);
            for(MethodDeclaration method:compilationUnit.findAll(MethodDeclaration.class)){
                body = method.getBody().orElse(null);
                if(body != null){
                    aveMethodLength = body.findAll(Statement.class).size();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return aveMethodLength;
    }
    @Override
    public int countSwitchStmt(Path path){
        int switchStmt = 0;
        try {
            CompilationUnit compilationUnit = new JavaParser().parse(path).getResult().orElse(null);
            if(compilationUnit != null){
                switchStmt = compilationUnit.findAll(SwitchStmt.class).size();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return switchStmt;
    }
    @Override
    public int countLoops(Path path){
        int loops = 0;
        try {
            CompilationUnit compilationUnit = new JavaParser().parse(path).getResult().orElse(null);
            if(compilationUnit != null){
                loops = compilationUnit.findAll(WhileStmt.class).size()+
                        compilationUnit.findAll(DoStmt.class).size()+
                        compilationUnit.findAll(ForStmt.class).size()+
                        compilationUnit.findAll(ForEachStmt.class).size();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return loops;
    }
    @Override
    public boolean isInterface(Path path){
        boolean isInterface = false;
        String lineString;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(String.valueOf(path)));
            while((lineString = bufferedReader.readLine())!= null){
                if(lineString.startsWith("public interface")){
                    isInterface = true;
                    break;
                }
                if(lineString.startsWith("public class")){
                    isInterface = false;
                    continue;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return isInterface;
    }
}
