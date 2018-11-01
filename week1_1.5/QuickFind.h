#ifndef _QUICKFIND_H
#define _QUICKFIND_H

#include <vector>

using std::vector;

class QuickFind{
 public:
    QuickFind(){}
    explicit QuickFind(int sz);//initalize
    void Reset(int sz);
    void Union(int a, int b);
    bool Connected(int a, int b);
 private:
    vector<int> id;
};

#endif