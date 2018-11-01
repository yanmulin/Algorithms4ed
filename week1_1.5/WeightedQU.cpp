#include "WeightedQU.h"

WeightedQU::WeightedQU(int size){
    id.resize(size);
    sz.resize(size);
    for (int i=0;i<size;i++){
        id[i] = i;
        sz[i] = 1;
    }
}

void WeightedQU::Union(int a, int b){
    int ra = root(a), rb = root(b);
    if (sz[ra] >= sz[rb]){
        //append tree rb to ra
        id[rb] = ra;
        //update sz[ra]
        sz[ra] += sz[rb];
    }else{
        //append tree ra to rb
        id[ra] = rb;
        //update sz[rb]
        sz[rb] += sz[ra];
    }
}

bool WeightedQU::Connected(int a, int b){
    return root(a) == root(b);
}

int WeightedQU::root(int a){
    while(id[a] != a) a = id[a];
    return a;
}