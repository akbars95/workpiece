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
    public void test1000CheckLowerEnglishLetters(){
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
    public void test1001CheckUpperEnglishLetters(){
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
    public void test1002CheckNumbers(){
        generateRandom = new GenerateRandom(false, false, true, false, false, false);
        List<Character> characters = generateRandom.getCharacters();
        assertNotNull(characters);
        assertEquals(characters.size(), 10);
        assertEquals(characters.get(0), new Character('0'));
        assertEquals(characters.get(characters.size() - 1), new Character('9'));

        String generate = generateRandom.generate(10);
        assertNotNull(generate);
        for(int i = 0; i < generate.length(); i++){
            assertTrue(Character.isDigit(generate.charAt(i)));
        }
        assertTrue(generate.length() == 10);
    }

    @Test
    public void test1003CheckLowerRussianLetters(){
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
    public void test1004CheckUpperRussianLetters(){
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
    public void test1005CheckSpecialCharacters(){
        generateRandom = new GenerateRandom(false, false, false, false, false, true);
        List<Character> characters = generateRandom.getCharacters();
        assertNotNull(characters);
        assertEquals(characters.size(), 16);

        String generate = generateRandom.generate(10);
        assertNotNull(generate);
        for (int i = 0; i < generate.length();i++){
            assertFalse(Character.isLetterOrDigit(generate.charAt(i)));
        }
        assertTrue(generate.length() == 10);
    }

    @Test
    public void test1006CheckAllCharacters(){
        generateRandom = new GenerateRandom(true, true, true, true, true, true);
        List<Character> characters = generateRandom.getCharacters();
        assertNotNull(characters);
        assertEquals(characters.size(), 144);

        String generate = generateRandom.generate(255);
        assertNotNull(generate);
        for (int i = 0; i < generate.length();i++){
            assertTrue(Character.isLetterOrDigit(generate.charAt(i)) || Character.isDigit(generate.charAt(i)) || !Character.isLetterOrDigit(generate.charAt(i)));
        }
        assertTrue(generate.length() == 255);
    }

    @Test
    public void test1007CheckCustomCharacters(){
        List<Character> listWithData = ListHelper.getListWithData('c', '#', '5', '2', '-');
        generateRandom = new GenerateRandom(listWithData);
        List<Character> characters = generateRandom.getCharacters();
        assertNotNull(characters);
        assertEquals(characters.size(), 5);

        String generate = generateRandom.generate(255);
        assertNotNull(generate);
        for (int i = 0; i < generate.length();i++){
            assertTrue(listWithData.contains(generate.charAt(i)));
        }
        assertTrue(generate.length() == 255);
    }

}