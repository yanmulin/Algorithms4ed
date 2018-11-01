#include "QuickFind.h"
#include <vector>
#include <cassert>

QuickFind::QuickFind(int sz){//initalize
    Reset(sz);
}
void QuickFind::Reset(int sz){
    //set size
    id.resize(sz);
    //set value
    for (int i=0;i<sz;i++){
        id[i] = i;
    }
}
void QuickFind::Union(int a, int b){
    assert(a >= 0 && a < id.size());
    assert(b >= 0 && b < id.size());
    int key = id[a];
    for (int i=0;i<id.size();i++){
        if (id[i] == key) id[i] = id[b];
    }
}
bool QuickFind::Connected(int a, int b){
    assert(a >= 0 && a < id.size());
    assert(b >= 0 && b < id.size());
    return id[a] == id[b];
}