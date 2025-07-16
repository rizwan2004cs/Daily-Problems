class KthcharII {
public:
    char kthCharacter(long long k, vector<int>& operations) {
        int shifts = 0;
        long long current_k = k - 1; 
        for (int i = operations.size() - 1; i >= 0; i--) {
                        if (i >= 63) {
                continue; 
            }
            long long len = 1LL << i; 
            if (current_k >= len) {
                current_k -= len; 
                if (operations[i] == 1) {
                    shifts++; 
                }
            }
        }
        
        return 'a' + (shifts % 26);
    }
};