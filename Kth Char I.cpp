class Solution {
public:
    char kthCharacter(int k) {
       int len = 1;
       while(len < k)
       {
           len *= 2;
       }
       int shift = 0;
       while(len > 1)
       {
        if(k > len/2)
        {
            shift++;
            k -= len/2;
        }
        len /= 2;
       }
       return 'a'+(shift%26);
    }
};