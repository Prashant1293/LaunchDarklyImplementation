package com.knoldus;

import com.launchdarkly.client.*;

import java.io.IOException;
import java.util.Arrays;
/*
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
*/

public class LaunchDarklyImplementation {
    public static void main(String[] args) throws IOException {
        //Config conf = ConfigFactory.load("application.conf");
        
        LDClient ldClient = new LDClient("sdk-9b4accc8-7f91-4bca-9243-71ba966abd3f");
        LDUser user = new LDUser.Builder("12ps93r@gmail.com")
                .firstName("Prashant")
                .lastName("Sharma")
                .custom("groups", Arrays.asList("beta_testers"))
                .build();
        LDConfig.Builder flushVar = new LDConfig.Builder().flushInterval(1);
        //ldClient.flush();
        flushVar.build();
        boolean showFeature = ldClient.boolVariation("show-data", user, false);
        try {
            if (showFeature) {
                for (int i = 0; i < 600; i++) {
                    //flushVar.build();
                    System.out.println("ldClient is : " + ldClient);
                    ldClient.flush();
                    //Thread.sleep(1000);
                    System.out.println("ldClient is : " + ldClient);
                    System.out.println("Showing your feature " + ldClient.boolVariation("show-data", user, false));
                    ldClient.close();
                }
            } else {
                System.out.println("Not showing your feature " + ldClient.boolVariation("show-data", user, false));
            }
            ldClient.flush();
            ldClient.close();
        } catch (IOException e) {
            System.out.println("Exception thrown");
        } /*catch (InterruptedException ie) {
            System.out.println("Inside thread sleep");
        }*/
        
    }
}
