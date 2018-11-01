#include "../QuickFind.h"
#include "../QuickFind.cpp"
#include "../QuickUnion.h"
#include "../QuickUnion.cpp"
#include "../WeightedQU.h"
#include "../WeightedQU.cpp"
#include "../PathCompressQU.h"
#include "../PathCompressQU.cpp"
#include "../PCWQU.h"
#include "../PCWQU.cpp"
#include "gtest/gtest.h"
namespace {
//QuickFindTest
TEST(QuickFindTest, Union){
    QuickFind qf(5);
    qf.Union(0, 1);
    qf.Union(4, 3);
    EXPECT_TRUE(qf.Connected(0, 1));
    EXPECT_FALSE(qf.Connected(0, 2));
    EXPECT_TRUE(qf.Connected(3, 4));
}
TEST(QuickFindTest, Connected){
    QuickFind qf(5);
    qf.Union(0, 1);
    qf.Union(1, 3);
    qf.Union(2, 4);
    EXPECT_TRUE(qf.Connected(3, 0));
    EXPECT_FALSE(qf.Connected(4, 0));
}
TEST(QuickFindTest, Mixed){
    QuickFind qf(10);
    qf.Union(0, 1);
    qf.Union(3, 7);
    qf.Union(3, 2);
    EXPECT_FALSE(qf.Connected(0, 3));
    qf.Union(1, 2);
    EXPECT_TRUE(qf.Connected(0, 3));
    qf.Union(4, 5);
    qf.Union(6, 7);
    EXPECT_FALSE(qf.Connected(4, 0));
    EXPECT_TRUE(qf.Connected(6, 0));
}
//QuickUnionTest
TEST(QuickUnionTest, Union){
    QuickUnion qu(5);
    qu.Union(0, 1);
    qu.Union(4, 3);
    EXPECT_TRUE(qu.Connected(0, 1));
    EXPECT_FALSE(qu.Connected(0, 2));
    EXPECT_TRUE(qu.Connected(3, 4));
}
TEST(QuickUnionTest, Connected){
    QuickUnion qu(5);
    qu.Union(0, 1);
    qu.Union(1, 3);
    qu.Union(2, 4);
    EXPECT_TRUE(qu.Connected(3, 0));
    EXPECT_FALSE(qu.Connected(4, 0));
}
TEST(QuickUnionTest, Mixed){
    QuickUnion qu(10);
    qu.Union(0, 1);
    qu.Union(3, 7);
    qu.Union(3, 2);
    EXPECT_FALSE(qu.Connected(0, 3));
    qu.Union(1, 2);
    EXPECT_TRUE(qu.Connected(0, 3));
    qu.Union(4, 5);
    qu.Union(6, 7);
    EXPECT_FALSE(qu.Connected(4, 0));
    EXPECT_TRUE(qu.Connected(6, 0));
}
//WeightedQUTest
TEST(WeightedQUTest, Union){
    WeightedQU wqu(5);
    wqu.Union(0, 1);
    wqu.Union(4, 3);
    EXPECT_TRUE(wqu.Connected(0, 1));
    EXPECT_FALSE(wqu.Connected(0, 2));
    EXPECT_TRUE(wqu.Connected(3, 4));
}
TEST(WeightedQUTest, Connected){
    WeightedQU wqu(5);
    wqu.Union(0, 1);
    wqu.Union(1, 3);
    wqu.Union(2, 4);
    EXPECT_TRUE(wqu.Connected(3, 0));
    EXPECT_FALSE(wqu.Connected(4, 0));
}
TEST(WeightedQUTest, Mixed){
    WeightedQU wqu(10);
    wqu.Union(0, 1);
    wqu.Union(3, 7);
    wqu.Union(3, 2);
    EXPECT_FALSE(wqu.Connected(0, 3));
    wqu.Union(1, 2);
    EXPECT_TRUE(wqu.Connected(0, 3));
    wqu.Union(4, 5);
    wqu.Union(6, 7);
    EXPECT_FALSE(wqu.Connected(4, 0));
    EXPECT_TRUE(wqu.Connected(6, 0));
}
//PathCompressQUTest
TEST(PathCompressQUTest, Union){
    PathCompressQU pcqu(5);
    pcqu.Union(0, 1);
    pcqu.Union(4, 3);
    EXPECT_TRUE(pcqu.Connected(0, 1));
    EXPECT_FALSE(pcqu.Connected(0, 2));
    EXPECT_TRUE(pcqu.Connected(3, 4));
}
TEST(PathCompressQUTest, Connected){
    PathCompressQU pcqu(5);
    pcqu.Union(0, 1);
    pcqu.Union(1, 3);
    pcqu.Union(2, 4);
    EXPECT_TRUE(pcqu.Connected(3, 0));
    EXPECT_FALSE(pcqu.Connected(4, 0));
}
TEST(PathCompressQUTest, Mixed){
    PathCompressQU pcqu(10);
    pcqu.Union(0, 1);
    pcqu.Union(3, 7);
    pcqu.Union(3, 2);
    EXPECT_FALSE(pcqu.Connected(0, 3));
    pcqu.Union(1, 2);
    EXPECT_TRUE(pcqu.Connected(0, 3));
    pcqu.Union(4, 5);
    pcqu.Union(6, 7);
    EXPECT_FALSE(pcqu.Connected(4, 0));
    EXPECT_TRUE(pcqu.Connected(6, 0));
}
//PCWQUTest
TEST(PCWQUTest, Union){
    PCWQU qu(5);
    qu.Union(0, 1);
    qu.Union(4, 3);
    EXPECT_TRUE(qu.Connected(0, 1));
    EXPECT_FALSE(qu.Connected(0, 2));
    EXPECT_TRUE(qu.Connected(3, 4));
}
TEST(PCWQUTest, Connected){
    PCWQU qu(5);
    qu.Union(0, 1);
    qu.Union(1, 3);
    qu.Union(2, 4);
    EXPECT_TRUE(qu.Connected(3, 0));
    EXPECT_FALSE(qu.Connected(4, 0));
}
TEST(PCWQUTest, Mixed){
    PCWQU qu(10);
    qu.Union(0, 1);
    qu.Union(3, 7);
    qu.Union(3, 2);
    EXPECT_FALSE(qu.Connected(0, 3));
    qu.Union(1, 2);
    EXPECT_TRUE(qu.Connected(0, 3));
    qu.Union(4, 5);
    qu.Union(6, 7);
    EXPECT_FALSE(qu.Connected(4, 0));
    EXPECT_TRUE(qu.Connected(6, 0));
}
}// namespace
