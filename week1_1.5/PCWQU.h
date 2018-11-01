#ifndef _PCWQU_H
#define _PCWQU_H

#include <vector>
using std::vector;

class PCWQU{
 public:
    PCWQU(){}
    PCWQU(int size);
    void Union(int a, int b);
    bool Connected(int a, int b);
 private:
    int root(int a);
    vector<int> sz;
    vector<int> id;
};

#endif