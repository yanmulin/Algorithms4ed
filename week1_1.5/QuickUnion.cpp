#include "QuickUnion.h"

QuickUnion::QuickUnion(int sz){
    id.resize(sz);
    for (int i=0;i<sz;i++)
        id[i] = i;
}

void QuickUnion::Union(int a, int b){
    int ra = root(a), rb = root(b);
    id[ra] = rb;
}

bool QuickUnion::Connected(int a, int b){
    return root(a) == root(b);
}

int QuickUnion::root(int a){
    while (id[a] != a) a = id[a];
    return a;
}
