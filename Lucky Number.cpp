class Solution {
public:
    int findLucky(vector<int>& arr) {
        unordered_map<int, int> freq;
        for (int num : arr) {
            freq[num]++;
        }

        int lucky = -1;
        for (const auto& pair : freq) {
            if (pair.first == pair.second) {
                lucky = max(lucky, pair.first);
            }
        }
        
        return lucky;
    }
};