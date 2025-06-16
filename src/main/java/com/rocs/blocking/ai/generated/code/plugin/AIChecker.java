package com.rocs.blocking.ai.generated.code.plugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AIChecker {
    public void check() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long money = 100000;
        while(n!=0){
            money *= 1.05;
            if(money%1000>0){
                money+=1000;
            }
            money /= 1000;
            money *= 1000;
            --n;
        }
        System.out.println(money);
    }
}
