#include "PCWQU.h"

PCWQU::PCWQU(int size){
    id.resize(size);
    sz.resize(size);
    for (int i=0;i<size;i++){
        id[i] = i;
        sz[i] = 1;
    }
}

void PCWQU::Union(int a, int b){
    int ra = root(a), rb = root(b);
    if (sz[ra] >= sz[rb]){
        id[rb] = ra;
        sz[ra] += sz[rb];
    }else {
        id[ra] = rb;
        sz[rb] += sz[ra];
    }
}

bool PCWQU::Connected(int a, int b){
    return root(a) == root(b);
}

int PCWQU::root(int a){
    while (a != id[a]){
        id[a] = id[id[a]];//compress path, make tree flat
        a = id[a];
    }
    return a;
}