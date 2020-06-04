#include <iostream>
#include <string>
#include <vector>
#include <map>
#include <algorithm>


//finite-state machine
bool FSM_check(const std::string& name, const std::vector<std::string>& enter,
               const std::vector<int>& states, const std::map<std::pair<int, std::string>, int>& rules, int enter_state) {

    if (name == " ") return true;

    int curr = enter_state;
    for (auto& item : name) {
        std::pair<int, std::string> currPair;
        auto it = rules.find(currPair);
        if (it == rules.end()) return false;
        curr = it->second;
    }

    curr = enter_state;
    for (auto& [key, value] : rules) {
        if (value == curr) return true;
    }
    return false;
}

int main() {
    return 0;
}
