# 动态连通性问题: 并查集

Dynamic Connectivity Problem: Union-Find

由编号为0..N-1的N个对象组成，执行Union和connected操作

## 操作 

* Union(a, b): 将编号为a, b的对象连接起来
* connected(a, b): 返回一个布尔量，判断a, b是否连通
这两个

## 连通分量

连通的对象组成一个集合，该集合就是一个连通分量

## Quick Find算法(贪心算法)

### 数据结构

id[]: 索引对应对象的编号，元素保存对象所在集合的编号

### 实现

* Union(a, b): id[a] <- id[b]
* connected(a, b): return id[a] == id[b]

### 时间复杂度

| Union | connected |
| :-----: | :----: |
| N | 1 |

## Quick Union算法

### 数据结构

一棵树表示一个集合，多棵树组成一个森林

id[]: 保存对象的父节点

### 实现

* Union(a, b): 若root(a)!=root(b), 将root(b)作为root(a)的子树
* connected(a, b): return root(a)==root(b)

### 时间复杂度

| Union | connected | 
| :---: | :--: |
| $N$ | $N$ |

P.S. 最坏情况: 在树较高的情况下

## 改进1: 带权QU

1. 避免将大树添加到小树上
2. 记录每颗树的大小
3. 总是将小树添加到大树

=> 新增数据结构：sz[]记录子树的大小

### 时间复杂度

| Union | connected |
| :---: | :--: |
| $log N$ | $log N$ |

P.S. 最坏情况，Union/connected操作与树的深度有关


~ 证明 “总是将小树添加到大树”的策略使得树的深度为 $log N$

1. 将小树添加到大树，小树的大小至少翻倍，而深度至少+1
2. 则小树达到N的大小，需要翻$log N$倍
3. 所以小树的深度为$log N$

## 改进2: 压缩路径QU

1. 将每次经过节点的父节点设置为上一级父节点
=> 树被展平

| Union | connected |
| :---: | :--: |
| $log N$ | $log N$ |


## 改进3: 带权+压缩路劲QU

| Union | connected |
| :---: | :--: |
| $log* N$ | $log* N$ |

P.S. 接近线性

## 应用: [渗漏系统](https://introcs.cs.princeton.edu/java/assignments/percolation.html)

判断从顶端到底端是否存在可通行区域（白色方块）


* trick：

*virtual top / bottom site*

## 练习1: 社交网络

给定N个人和他们建立好友关系的log，log按照时间顺序记录好友关系建立的时间，判断所有人都被连接起来的最早时间。

1. 使用带权重QU，用一个变量maxsz记录最大连通分量的大小
2. 进行Union操作时更新maxsz
3. 当新的好友在每个当最大连通分量的大小等于N时，说明所有人都被连接起来了

## 练习2: 

添加find(i)操作，返回i所在的连通分量的最大节点

1. 新增数据结构：max[]记录子树最大值
1. union操作更新max

## 练习3:

给定n个整数的集合$S=\{0,1, \ldots, n-1\}$，有下面两种操作：  
* delete(x): 从集合中删除x  
* successor(x):从集合中找到$\min(y) \quad y \geq x$  
实现这种数据类型。


1. 删除的节点w维护成1个连通分量
2. 从不在删除集合的节点中查找符合条件的值

