# 算法分析

## 科学方法

观察-假设-预测

*e.g. 3-Sum Problem*

#### 1. 观察

* 不同规模的数据输入

    | 1K | 2K | 4K | 8K |
    |:---:|:---:|:---:|:---:|
    |0.228|1.848|14.316|117.567|

* 画图  
  

#### 2. 假设

* 假设运行时间函数表达式为: $T(t)=aN^b$

* 作log-log图，求出$b=直线斜率$

* 求出$a$

#### 3. 预测 c

* 对16K, 32K, 1M的数据进行预测  

## 简化方法

| N | time | ratio | $\log \mathrm{ratio}$ |
| :--: | :--: | :--: | :--: | 
| 1K | 0.228 | 0 | 0 |
| 2K | 1.848 | 8.11 | 3 |
| 4K | 14.316 | 7.75 | 3 |
| 8K | 117.567| 8.21 | 3 |

根据 $\log \rm{ratio}$ 判断$b = 3$，再求出$a$

## 分析程序性能

* 简化1: 使用基本指令操作消耗时间作为运行时间的估计

* 简化2: 忽略低阶项，保留最高阶项

$$Ex1.\quad 1+2+\ldots+N = \sum^N_{i=1}i \sim \int^N_{x=1}x\, dx \sim \frac1 2 N^2 \\
Ex2.\quad 1+\frac1 2+\ldots+\frac1 N = \sum^N_{i=1}\frac 1 i \sim \int^N_{x=1}\frac1 x\,dx \sim \ln N \\
Ex3.\quad 3-sum \space triple \space loop:\sum^N_{i=1}\sum^N_{j=i}\sum^N_{k=j}1 \sim \int^N_{x=1}\int^N_{y=x}\int^N_{z=y}1\,dx \sim \frac1 6 N^3$$

|$1$|$\log N$|$N$|$N\log N$|$N^2$|$N^3$|$2^N$|
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|-|Binary search|Find max|Merge sort|Check pairs|Check triples|Exhaustive search|
|-|while(N>1){N/=2;}|for(int i=0;i<N;i++)|Divide&conquer|double loop|triple loop|check all subsets|

*e.g. 二分查找*

命题: 二分查找在一个排好序的序列里最多需要比较$1+\log N$次可以找到需要的元素。
已知: 
$$T(N) \leq T(\frac N 2) + 1 for N \gt 1,\space with T(1) = 1$$
证明: 
$$T(N) \leq T(\frac N 2) + 1\leq T(\frac N 4) + 1 + 1\leq \ldots\\\leq T(1) + 1 + \ldots = 1 + \log N$$

#### 3-Sum Problem改进1

1. 序列排序
2. 对每对二元数，二分查找`-(a[i]+a[j])`

* 时间复杂度: $N^2\log N$

#### 3-Sum Problem改进2

1. 第一个数i从0到a.length-3遍历数组
2. j, k分别从头尾开始查找
3. 若三个数之和太大，k--;太小，j++

* 时间复杂度: $N^2$

## 算法理论

* $\Theta(N)$ - 算法类型
* $O(N)$ - 上界（最坏情况）
* $\Omega(N)$ - 下界（最好情况）    

#### 算法设计的目标
1. 降低算法的上界
2. 使算法适合输入


## 习题: Search in a bitonic array

#### 3次二分查找

1. 查找最大值
2. 查找最大值左边
3. 查找最大值右边

时间复杂度 $ \sim 3\log N$

注：
1. 二分法易错点
    * (正序)if key > a[mid], then lo = mid + 1;
    else if key < a[mid], then hi = mid - 1;
    *  循环条件: lo <= hi (注意等号!)
2. 二分法找最值
```java
private static int binarySearchMax(int[] a) {
    int n = a.length;
    int lo = 1, hi = n - 2;
    if (a[lo] < a[0]) return 0;
    if (a[hi] < a[n - 1]) return n - 1;
    while (lo <= hi) {
        int mid = (lo + hi) / 2;
        if (a[mid] > a[mid - 1] && a[mid] > a[mid + 1]) return mid;
        else if (a[mid] > a[mid - 1] && a[mid] < a[mid + 1]) lo = mid + 1;
        else hi = mid - 1;
    }
    return -1;//不应该运行到这里
}
```