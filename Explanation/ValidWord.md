### Problem Statement

A word is **valid** if it satisfies all the following conditions:

- It contains at least **3 characters**.
- It consists only of **digits (0-9)** and **English letters (a-z, A-Z)**.
- It has **at least one vowel** (`a`, `e`, `i`, `o`, `u`, case-insensitive).
- It has **at least one consonant** (an English letter that is not a vowel).

You are given a string `word`. Return `true` if it is valid, otherwise return `false`.

### Approach

We need to check four rules:

1. **Length check:** The word must be at least 3 characters.
2. **Allowed characters:** All characters must be English letters (a-z, A-Z) or digits (0-9).
3. **Has at least one vowel:** There must be at least one vowel (either uppercase or lowercase).
4. **Has at least one consonant:** There must be at least one consonant (a letter that is not a vowel).

We simply traverse the string, checking for each criterion as we go, and return `false` immediately if any rule is violated.

### Java Solution

```java
class Solution {
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
```


### Example Walkthrough

| Input | Rule(s) Violated | Output | Explanation |
| :-- | :-- | :-- | :-- |
| `"234Adas"` | None | `true` | Meets all rules (length, only valid chars, has both) |
| `"b3"` | Length and vowel missing | `false` | Less than 3 chars, no vowel |
| `"a3$e"` | Invalid character \& no consonant | `false` | Contains `$`, no consonant letter |

### Key Points

- Use `Character.isLetter()` and `Character.isDigit()` for character checks.
- Check for vowels and consonants during a single pass.
- Return `false` immediately upon finding an invalid character.

This approach is efficient and passes all cases for the given constraints.

