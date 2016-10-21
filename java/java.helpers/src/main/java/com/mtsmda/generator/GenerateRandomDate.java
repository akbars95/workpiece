package com.mtsmda.generator;

import java.time.LocalDateTime;

/**
 * Created by MTSMDA on 20.10.2016.
 */
public class GenerateRandomDate {

    public static LocalDateTime getRandomLocalDateTime(){
        GenerateRandom generateRandom = new GenerateRandom(GenerateRandom.ONLY_NUMBERS);
//        generateRandom.generate();
        return LocalDateTime.MAX;
    }

}