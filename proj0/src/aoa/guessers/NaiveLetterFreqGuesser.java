package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class NaiveLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public NaiveLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Makes a guess which ignores the given pattern. */
    public char getGuess(String pattern, List<Character> guesses) {
        return getGuess(guesses);
    }

    /** Returns a map from a given letter to its frequency across all words.
     *  This task is similar to something you did in hw0b! */
    public Map<Character, Integer> getFrequencyMap() {
        // TODO: Fill in this method.
        Map<Character, Integer> result = new HashMap<>();
        for(String element : words){
            for(int i = 0; i < element.length(); i++){
                char letter = element.charAt(i);
                if(result.containsKey(letter)){
                    int valueToUpdate = result.get(letter);
                    result.put(letter, valueToUpdate+1);
                }
                else{
                    result.put(letter, 1);
                }
            }
        }
        return result;
    }

    /** Returns the most common letter in WORDS that has not yet been guessed
     *  (and therefore isn't present in GUESSES). */
    public char getGuess(List<Character> guesses) {
        // TODO: Fill in this method.
        Map<Character, Integer> letterRemain = new TreeMap<>();

        for (char key : getFrequencyMap().keySet()){
            if(!guesses.contains(key)){
                letterRemain.put(key, getFrequencyMap().get(key));
            }
        }

        char charGuess = 'a';
        int highestFreq = 0;
        for(Map.Entry<Character, Integer> entry: letterRemain.entrySet()){
            if(entry.getValue() > highestFreq){
                highestFreq = entry.getValue();
                charGuess = entry.getKey();
            }
        }

        if(!letterRemain.isEmpty()){
            return charGuess;
        }
        else{
            return '?';
        }
    }

    public static void main(String[] args) {
        NaiveLetterFreqGuesser nlfg = new NaiveLetterFreqGuesser("data/example.txt");
        System.out.println("list of words: " + nlfg.words);
        System.out.println("frequency map: " + nlfg.getFrequencyMap());

        List<Character> guesses = List.of('e', 'l');
        System.out.println("guess: " + nlfg.getGuess(guesses));
    }
}
