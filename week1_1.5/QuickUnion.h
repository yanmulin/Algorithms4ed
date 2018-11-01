#ifndef _QUICKUNION_H
#define _QUICKUNION_H

#include <vector>

using std::vector;

class QuickUnion {
 public:
    QuickUnion(){}
    QuickUnion(int sz);
    void Union(int a, int b);
    bool Connected(int a, int b);
 private:
    int root(int a);
    vector<int> id;
};

#endif