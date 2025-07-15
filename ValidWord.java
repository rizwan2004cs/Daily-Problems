class ValidWord {
    public boolean isValid(String word) {
        if (word.length() < 3) return false;
        String vowels = "aeiouAEIOU";
        boolean hasVowel = false, hasConsonant = false;
        for (char c : word.toCharArray()) {
            if (Character.isDigit(c)) continue;
            if (Character.isLetter(c)) {
                if (vowels.indexOf(c) >= 0) {
                    hasVowel = true;
                } else {
                    hasConsonant = true;
                }
            } else {
                // invalid character (not a letter nor a digit)
                return false;
            }
        }
        return hasVowel && hasConsonant;
    }
}
