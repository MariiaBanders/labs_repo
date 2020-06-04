#include <iostream>
#include <fstream>
#include <string>
#include <set>

int main() {
    const std::string PATH = "../text.txt";
    std::ifstream in(PATH);

    const std::set<char> vowel = {'e', 'y', 'u', 'i', 'o', 'a'};

    const std::set<char> consonants = {'q', 'w', 'r', 't', 'p',
                                       's', 'd', 'f', 'g', 'h', 'j', 'k', 'l',
                                       'z', 'x', 'c', 'v', 'b', 'n', 'm'};

    char curr;
    bool doubleLetter = false;
    std::string fromFile;
    std::set<std::string> res;

    while(in) {
        in.get(curr);
        bool isConsonant = consonants.count(curr) != 0;
        if (isConsonant && !fromFile.empty() && curr == fromFile[fromFile.size() - 1]) {
            doubleLetter = true;
        }

        if (isConsonant || vowel.count(curr) != 0) {
            fromFile.push_back(curr);
        } else {
            if (doubleLetter) {
                res.insert(fromFile);
                doubleLetter = false;
            }
            fromFile = {};
        }
    }

    for (const auto& item : res) {
        std::cout << item << "\n";
    }

    return 0;
}
