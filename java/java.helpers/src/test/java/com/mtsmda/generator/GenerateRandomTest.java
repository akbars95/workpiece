package com.mtsmda.generator;

import com.mtsmda.helper.ListHelper;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by dminzat on 9/21/2016.
 */
public class GenerateRandomTest {

    private GenerateRandom generateRandom;

    @Test
    public void test1000CheckLowerEnglishLetters() {
        generateRandom = new GenerateRandom(true, false, false, false, false, false);
        List<Character> characters = generateRandom.getCharacters();
        assertNotNull(characters);
        assertEquals(characters.size(), 26);
        assertEquals(characters.get(0), new Character('a'));
        assertEquals(characters.get(characters.size() - 1), new Character('z'));

        String generate = generateRandom.generate(10);
        assertNotNull(generate);
        assertEquals(generate, generate.toLowerCase());
        assertTrue(generate.length() == 10);
    }

    @Test
    public void test1001CheckUpperEnglishLetters() {
        generateRandom = new GenerateRandom(false, true, false, false, false, false);
        List<Character> characters = generateRandom.getCharacters();
        assertNotNull(characters);
        assertEquals(characters.size(), 26);
        assertEquals(characters.get(0), new Character('A'));
        assertEquals(characters.get(characters.size() - 1), new Character('Z'));

        String generate = generateRandom.generate(10);
        assertNotNull(generate);
        assertEquals(generate, generate.toUpperCase());
        assertTrue(generate.length() == 10);
    }

    @Test
    public void test1002CheckNumbers() {
        generateRandom = new GenerateRandom(false, false, true, false, false, false);
        List<Character> characters = generateRandom.getCharacters();
        assertNotNull(characters);
        assertEquals(characters.size(), 10);
        assertEquals(characters.get(0), new Character('0'));
        assertEquals(characters.get(characters.size() - 1), new Character('9'));

        String generate = generateRandom.generate(10);
        assertNotNull(generate);
        for (int i = 0; i < generate.length(); i++) {
            assertTrue(Character.isDigit(generate.charAt(i)));
        }
        assertTrue(generate.length() == 10);
    }

    @Test
    public void test1003CheckLowerRussianLetters() {
        generateRandom = new GenerateRandom(false, false, false, true, false, false);
        List<Character> characters = generateRandom.getCharacters();
        assertNotNull(characters);
        assertEquals(characters.size(), 33);
        assertEquals(characters.get(0), new Character('а'));
        assertEquals(characters.get(characters.size() - 1), new Character('ё'));

        String generate = generateRandom.generate(10);
        assertNotNull(generate);
        assertEquals(generate, generate.toLowerCase());
        assertTrue(generate.length() == 10);
    }

    @Test
    public void test1004CheckUpperRussianLetters() {
        generateRandom = new GenerateRandom(false, false, false, false, true, false);
        List<Character> characters = generateRandom.getCharacters();
        assertNotNull(characters);
        assertEquals(characters.size(), 33);
        assertEquals(characters.get(0), new Character('А'));
        assertEquals(characters.get(characters.size() - 1), new Character('Ё'));

        String generate = generateRandom.generate(10);
        assertNotNull(generate);
        assertEquals(generate, generate.toUpperCase());
        assertTrue(generate.length() == 10);
    }

    @Test
    public void test1005CheckSpecialCharacters() {
        generateRandom = new GenerateRandom(false, false, false, false, false, true);
        List<Character> characters = generateRandom.getCharacters();
        assertNotNull(characters);
        assertEquals(characters.size(), 16);

        String generate = generateRandom.generate(10);
        assertNotNull(generate);
        for (int i = 0; i < generate.length(); i++) {
            assertFalse(Character.isLetterOrDigit(generate.charAt(i)));
        }
        assertTrue(generate.length() == 10);
    }

    @Test
    public void test1006CheckAllCharacters() {
        generateRandom = new GenerateRandom(true);
        List<Character> characters = generateRandom.getCharacters();
        assertNotNull(characters);
        assertEquals(characters.size(), 144);

        String generate = generateRandom.generate(255);
        System.out.println(generate);
        assertNotNull(generate);
        for (int i = 0; i < generate.length(); i++) {
            assertTrue(Character.isLetterOrDigit(generate.charAt(i)) || Character.isDigit(generate.charAt(i)) || !Character.isLetterOrDigit(generate.charAt(i)));
        }
        assertTrue(generate.length() == 255);
    }

    @Test
    public void test1007CheckCustomCharacters() {
        List<Character> listWithData = ListHelper.getListWithData('c', '#', '5', '2', '-');
        generateRandom = new GenerateRandom(listWithData);
        List<Character> characters = generateRandom.getCharacters();
        assertNotNull(characters);
        assertEquals(characters.size(), 5);

        String generate = generateRandom.generate(255);
        assertNotNull(generate);
        for (int i = 0; i < generate.length(); i++) {
            assertTrue(listWithData.contains(generate.charAt(i)));
        }
        assertTrue(generate.length() == 255);
    }

    @Test
    public void test1008CheckConstructorTypeONLY_ENGLISH_LETTER() {
        generateRandom = new GenerateRandom(GenerateRandom.ONLY_ENGLISH_LETTER);
        List<Character> characters = generateRandom.getCharacters();
        assertNotNull(characters);
        assertEquals(characters.size(), 52);

        String generate = generateRandom.generate(25);
        assertNotNull(generate);
        for (int i = 0; i < generate.length(); i++) {
            assertTrue(Character.isLetter(generate.charAt(i)));
            assertTrue(Character.isLowerCase(generate.charAt(i)) || Character.isUpperCase(generate.charAt(i)));
        }
        assertTrue(generate.length() == 25);
    }

    @Test
    public void test1009CheckConstructorTypeONLY_RUSSIAN_LETTER() {
        generateRandom = new GenerateRandom(GenerateRandom.ONLY_RUSSIAN_LETTER);
        List<Character> characters = generateRandom.getCharacters();
        assertNotNull(characters);
        assertEquals(characters.size(), 66);

        String generate = generateRandom.generate(25);
        assertNotNull(generate);
        for (int i = 0; i < generate.length(); i++) {
            assertTrue(Character.isLetter(generate.charAt(i)));
            assertTrue(Character.isLowerCase(generate.charAt(i)) || Character.isUpperCase(generate.charAt(i)));
        }
        assertTrue(generate.length() == 25);
    }

    @Test
    public void test1010CheckConstructorTypeENGLISH_LETTERS_AND_NUMBERS() {
        generateRandom = new GenerateRandom(GenerateRandom.ENGLISH_LETTERS_AND_NUMBERS);
        List<Character> characters = generateRandom.getCharacters();
        assertNotNull(characters);
        assertEquals(characters.size(), 62);

        String generate = generateRandom.generate(25);
        assertNotNull(generate);
        for (int i = 0; i < generate.length(); i++) {
            assertTrue(Character.isLetterOrDigit(generate.charAt(i)));
            assertTrue(Character.isLowerCase(generate.charAt(i)) || Character.isUpperCase(generate.charAt(i)) || Character.isDigit(generate.charAt(i)));
        }
        assertTrue(generate.length() == 25);
    }

    @Test
    public void test1011CheckConstructorTypeRUSSIAN_LETTER_AND_NUMBERS() {
        generateRandom = new GenerateRandom(GenerateRandom.RUSSIAN_LETTER_AND_NUMBERS);
        List<Character> characters = generateRandom.getCharacters();
        assertNotNull(characters);
        assertEquals(characters.size(), 76);

        String generate = generateRandom.generate(25);
        assertNotNull(generate);
        for (int i = 0; i < generate.length(); i++) {
            assertTrue(Character.isLetterOrDigit(generate.charAt(i)));
            assertTrue(Character.isLowerCase(generate.charAt(i)) || Character.isUpperCase(generate.charAt(i)) || Character.isDigit(generate.charAt(i)));
        }
        assertTrue(generate.length() == 25);
    }

    @Test
    public void test1012CheckConstructorTypeONLY_NUMBERS() {
        generateRandom = new GenerateRandom(GenerateRandom.ONLY_NUMBERS);
        List<Character> characters = generateRandom.getCharacters();
        assertNotNull(characters);
        assertEquals(characters.size(), 10);

        String generate = generateRandom.generate(25);
        assertNotNull(generate);
        for (int i = 0; i < generate.length(); i++) {
            assertTrue(Character.isDigit(generate.charAt(i)));
        }
        assertTrue(generate.length() == 25);
    }

    @Test
    public void test1013generateNumberInRange(){
        generateRandom = new GenerateRandom(GenerateRandom.ONLY_NUMBERS);

        Double aDouble = generateRandom.generateNumberInRange(1, 5);
        assertNotNull(aDouble);
        assertTrue(aDouble >= 1 && aDouble <= 5, "check double values");

        aDouble = generateRandom.generateNumberInRange(1.5F, 5.95F);
        assertNotNull(aDouble);
        assertTrue(aDouble >= 1.5 && aDouble <= 5.95, "check float values");

        aDouble = generateRandom.generateNumberInRange(new Byte((byte) 9), new Byte("15"));
        assertNotNull(aDouble);
        assertTrue(aDouble >= 9 && aDouble <= 15, "check byte values");

        aDouble = generateRandom.generateNumberInRange(new Short("12"), new Short("14"));
        assertNotNull(aDouble);
        assertTrue(aDouble >= 12 && aDouble <= 14, "check short values");

        aDouble = generateRandom.generateNumberInRange(100, 950);
        assertNotNull(aDouble);
        assertTrue(aDouble >= 100 && aDouble <= 950, "check int values");

        aDouble = generateRandom.generateNumberInRange(1_000_000L, 1_000_000 + 10L);
        assertNotNull(aDouble);
        assertTrue(aDouble >= 1_000_000 && aDouble <= (1_000_000 + 10), "check long values");
    }


    @Test
    public void test1014RUSSIAN_LETTER_AND_ENGLISH_LETTER_AND_NUMBERS(){
        generateRandom = new GenerateRandom(GenerateRandom.RUSSIAN_LETTER_AND_ENGLISH_LETTER_AND_NUMBERS);
        List<Character> characters = generateRandom.getCharacters();
        assertNotNull(characters);
        assertEquals(characters.size(), 128);

        String generate = generateRandom.generate(25);
        assertNotNull(generate);
        for (int i = 0; i < generate.length(); i++) {
            assertTrue(Character.isLetterOrDigit(generate.charAt(i)));
            assertTrue(Character.isLowerCase(generate.charAt(i)) || Character.isUpperCase(generate.charAt(i)) || Character.isDigit(generate.charAt(i)));
            assertFalse(Character.isSpaceChar(generate.charAt(i)));
            assertFalse(Character.isWhitespace(generate.charAt(i)));
        }
        assertTrue(generate.length() == 25);
    }

    @Test
    public void testGenerateRandomWithDateTime(){
        generateRandom = new GenerateRandom(GenerateRandom.RUSSIAN_LETTER_AND_ENGLISH_LETTER_AND_NUMBERS);
        String generateRandomWithDateTime = generateRandom.generateRandomWithDateTime(true);
        assertNotNull(generateRandomWithDateTime);
        assertTrue(generateRandomWithDateTime.length() == 15);
        generateRandomWithDateTime = generateRandom.generateRandomWithDateTime(false);
        assertNotNull(generateRandomWithDateTime);
        assertTrue(generateRandomWithDateTime.length() == 29);
    }

    @Test
    public void testGenerateRandomNumber(){
        List<Range> ranges = ListHelper.getListWithData(new Range(0, 9), new Range(9, 100), new Range(1_000, 2_000));
        ranges.forEach(range -> {
            for(int i = 0; i < 25; i++){
                long number = GenerateRandom.generateRandomNumberInRange(range.getMin(), range.getMax());
                System.out.println("current - " + number + ", min - " + range.getMin() + ",max - " + range.getMax());
                assertTrue(number >= range.getMin());
                assertTrue(number <= range.getMax());
            }
            System.out.println("-------------");
        });
    }

    @Test
    public void testGenerateRandomNumberInRangeWithCount(){
        List<Range> ranges = ListHelper.getListWithData(new Range(0, 9, 25), new Range(0, 9, 75), new Range(0, 9, 90));
        ranges.forEach(range -> {
            for(int i = 0; i < 25; i++){
                String number = GenerateRandom.generateRandomNumberInRangeWithCount(range.getMin(), range.getMax(), range.getCount());
                System.out.println("current - " + number + ", min - " + range.getMin() + ",max - " + range.getMax() + ", count - " + range.getCount());
                assertTrue(number.length() == range.getCount());
            }
            System.out.println("-------------");
        });
    }

    @Test
    public void testgenerateRandomNumberInRangeWithCountException(){
        testgenerateRandomNumberInRangeWithCountExceptionProcess(-1, 5, 5);
        testgenerateRandomNumberInRangeWithCountExceptionProcess(10, 5, 5);

//        testgenerateRandomNumberInRangeWithCountExceptionProcess(0, 9, 5);
    }

    private void testgenerateRandomNumberInRangeWithCountExceptionProcess(long min, long max, int count){
        String generateRandomNumberInRangeWithCount = null;
        try{
            generateRandomNumberInRangeWithCount = GenerateRandom.generateRandomNumberInRangeWithCount(min, max, count);
        }
        catch (RuntimeException e){
            if(min > max){
                assertEquals(e.getMessage(), "min cannot be more than max!");
            }else{
                assertEquals(e.getMessage(), "min should be 0 and max should be 9!");
            }

            return;
        }
        fail("Should be only with exception!");
    }

    private class Range{
        private long min;
        private long max;
        private int count;

        public Range(long min, long max) {
            this.min = min;
            this.max = max;
        }

        public Range(long min, long max, int count) {
            this.min = min;
            this.max = max;
            this.count = count;
        }

        public long getMin() {
            return min;
        }

        public void setMin(long min) {
            this.min = min;
        }

        public long getMax() {
            return max;
        }

        public void setMax(long max) {
            this.max = max;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }

}