#ifndef _PATHCOMPRESSQU_H
#define _PATHCOMPRESSQU_H

#include <vector>
using std::vector;

class PathCompressQU{
 public:
    PathCompressQU(){}
    PathCompressQU(int size);
    void Union(int a, int b);
    bool Connected(int a, int b);
 private:
    int root(int a);
    vector<int> id;
};

#endif