#include "PathCompressQU.h"

PathCompressQU::PathCompressQU(int size){
    id.resize(size);
    for (int i=0;i<size;i++)
        id[i] = i;
}

void PathCompressQU::Union(int a, int b){
    int ra = root(a), rb = root(b);
    id[ra] = rb;
}

bool PathCompressQU::Connected(int a, int b){
    return root(a) == root(b);
}

int PathCompressQU::root(int a){
    while (a != id[a]){
        id[a] = id[id[a]];//compress path, make tree flat
        a = id[a];
    }
    return a;
}
