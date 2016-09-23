package com.mtsmda.generator;

import com.mtsmda.helper.ListHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dminzat on 9/21/2016.
 */
public class GenerateRandom {

    private boolean englishSmallLetter;
    private boolean englishBigLetter;
    private boolean numbers;
    private boolean russianSmallLetter;
    private boolean russianBigLetter;
    private boolean standardSpecialCharacters;

    private List<Character> characters = new ArrayList<>();

    public static final int ONLY_ENGLISH_LETTER = 0;
    public static final int ENGLISH_LETTERS_AND_NUMBERS = 1;
    public static final int ONLY_RUSSIAN_LETTER = 2;
    public static final int RUSSIAN_LETTER_AND_NUMBERS = 3;

    public GenerateRandom() {

    }

    public GenerateRandom(Integer type) {
        switch (type) {
            case ENGLISH_LETTERS_AND_NUMBERS: {
                this.numbers = true;
            }
            case ONLY_ENGLISH_LETTER: {
                this.englishBigLetter = true;
                this.englishSmallLetter = true;
            }
            break;
            case RUSSIAN_LETTER_AND_NUMBERS: {
                this.numbers = true;
            }
            case ONLY_RUSSIAN_LETTER: {
                this.russianBigLetter = true;
                this.russianSmallLetter = true;
            }
            break;
        }
        init();
    }

    private void typeResolver() {
    }

    public GenerateRandom(Boolean all) {
        this(all, all, all, all, all, all);
    }

    public GenerateRandom(boolean englishSmallLetter, boolean englishBigLetter, boolean numbers, boolean russianSmallLetter, boolean russianBigLetter, boolean standardSpecialCharacters) {
        this.englishSmallLetter = englishSmallLetter;
        this.englishBigLetter = englishBigLetter;
        this.numbers = numbers;
        this.russianSmallLetter = russianSmallLetter;
        this.russianBigLetter = russianBigLetter;
        this.standardSpecialCharacters = standardSpecialCharacters;
        init();
    }

    public GenerateRandom(List<Character> characters) {
        this.characters = characters;
    }

    private void init() {
        if (this.englishSmallLetter) {
            addCharsToList(97, 123);
        }
        if (this.englishBigLetter) {
            addCharsToList(65, 91);
        }
        if (this.numbers) {
            addCharsToList(48, 58);
        }
        if (this.russianSmallLetter) {
            addCharsToList(1072, 1104);
            addCharsToList(1105, 1106);
        }
        if (this.russianBigLetter) {
            addCharsToList(1040, 1072);
            addCharsToList(1025, 1026);
        }
        if (this.standardSpecialCharacters) {
            addCharsToList(33, 34);
            addCharsToList(35, 39);
            addCharsToList(42, 47);
            addCharsToList(58, 60);
            addCharsToList(63, 65);
            addCharsToList(94, 95);
            addCharsToList(124, 125);
        }
    }

    /*
    * @param startIndex include
    * @param endIndex exclude
    * */
    private void addCharsToList(int startIndex, int endIndex) {
        for (int i = startIndex; i < endIndex; i++) {
            this.characters.add(new Character((char) i));
        }
    }

    public String generate(int length) {
        checkLastElement();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; ) {
            Character character = this.characters.get((new Double(Math.random() * this.characters.size() - 1).intValue()));
            if (character.equals(" ")) {
                continue;
            }
            result.append(character);
            i++;
        }
        return result.toString();
    }

    private void checkLastElement() {
        if (ListHelper.listIsNotNullAndNotEmpty(characters)) {
            if (characters.get(characters.size() - 1) != ' ') {
                characters.add(new Character(' '));
            }
        }
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }
}