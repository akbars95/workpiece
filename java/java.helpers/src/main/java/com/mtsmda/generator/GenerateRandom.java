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

    private boolean setViaCustomChars;

    private List<Character> characters = new ArrayList<>();

    public static final int ONLY_ENGLISH_LETTER = 0;
    public static final int ENGLISH_LETTERS_AND_NUMBERS = 1;
    public static final int ONLY_RUSSIAN_LETTER = 2;
    public static final int RUSSIAN_LETTER_AND_NUMBERS = 3;
    public static final int ONLY_NUMBERS = 4;
    public static final int RUSSIAN_LETTER_AND_ENGLISH_LETTER_AND_NUMBERS = 5;

    public GenerateRandom() {
        this.setViaCustomChars = true;
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
            case ONLY_NUMBERS: {
                this.numbers = true;
            }break;
            case RUSSIAN_LETTER_AND_ENGLISH_LETTER_AND_NUMBERS:{
                this.numbers = true;
                this.englishBigLetter = true;
                this.englishSmallLetter = true;
                this.russianBigLetter = true;
                this.russianSmallLetter = true;
            }
        }
        this.setViaCustomChars = true;
        init();
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
        this.setViaCustomChars = true;
        init();
    }

    public GenerateRandom(List<Character> characters) {
        this.characters = characters;
        this.setViaCustomChars = true;
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
        return generate(length, null, null);
    }

    private String generate(int length, Integer min, Integer max) {
        checkLastElement();

        boolean isOnlyNumbers = false;

        if (min != null && max != null && min.intValue() < 0 && max.intValue() > 9) {
            if (this.numbers && !this.englishBigLetter && !this.englishSmallLetter && !this.russianBigLetter
                    && !this.russianSmallLetter && !this.standardSpecialCharacters) {
                if (this.setViaCustomChars) {
                    if (this.characters.isEmpty()) {
                        throw new RuntimeException("list of characters is null, list should not be null!");
                    } else {
                        for (Character current : this.characters) {
                            if (!current.isDigit(current)) {
                                throw new RuntimeException("custom characters should be all numbers! " + current + "isn't digit!");
                            }
                        }
                    }
                }
            }
            throw new RuntimeException("min value should be min number = 0 and max values should be 9!You are min=" + min
                    + " and max=" + max);
        }
        isOnlyNumbers = true;

        StringBuilder result = new StringBuilder();
        int currentInt = Integer.MIN_VALUE;
        for (int i = 0; i < length; ) {
            do {
                currentInt = new Double(Math.random() * this.characters.size() - 1).intValue();
            } while (isOnlyNumbers && ((min != null && max != null && currentInt <= min && currentInt >= max) ||
                    (min != null && max == null && currentInt <= min) ||
                    (min == null && max != null && currentInt >= max)));

            Character character = this.characters.get(currentInt);
            if (character.equals(" ")) {
                continue;
            }
            result.append(character);
            i++;
        }
        return result.toString();
    }

    public static <T extends Number> Double generateNumberInRange(T minValue, T maxValue) {
        double random = -0.1;
        do {
            random = Math.random() * maxValue.doubleValue();
        } while (minValue.doubleValue() > random || maxValue.doubleValue() < random);

        return random;
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

    public static int generateRandomNumberInRange(int min, int max) {
        return (int) generateRandomNumberInRange((long) min, (long)max);
    }

    public static long generateRandomNumberInRange(long min, long max) {
        if (min > max) {
            throw new RuntimeException("min should be less than max!");
        }
        long result = -1;
        do {
            result = Math.round(Math.random() * max);
        } while (result < min || result > max);
        return result;
    }

    public static String generateRandomNumberInRangeWithCount(long min, long max, int count) {
        StringBuilder result = new StringBuilder();
        if (min < 0 || max > 9) {
            throw new RuntimeException("min should be 0 and max should be 9!");
        }
        if(min > max){
            throw new RuntimeException("min cannot be more than max!");
        }
        if(count < 1){
            System.out.println("Count should be more than 1");
        }
        for (int i = 0; i < count; i++) {
            result.append(generateRandomNumberInRange(min, max));
        }
        return result.toString();
    }


}