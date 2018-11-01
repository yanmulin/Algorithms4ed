#ifndef _WEIGHTEDQU_H
#define _WEIGHTEDQU_H

#include <vector>
using std::vector;

class WeightedQU{
 public:
    WeightedQU(){}
    WeightedQU(int size);
    void Union(int a, int b);
    bool Connected(int a, int b);
 private:
    int root(int a);
    vector<int> id;
    vector<int> sz;
};

#endif