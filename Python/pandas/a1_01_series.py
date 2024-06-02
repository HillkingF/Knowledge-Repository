"""
-*- coding: utf-8 -*-
Time     : 2024/4/29
Author   : fengwenni001
File     : a1_base.py
Function :  基础部分
"""

import pandas as pd
import numpy as np

"""
【1、创建对象】
"""
s = pd.Series([1, 2, np.nan, np.inf, 5])   # 基于列表创建一维数值对象
s1 = pd.Series([1,2,3], index=['a','b','c'])   # 指定索引
s2 = pd.Series({'a': [1,2], 'b': 2, 'c': 3})   # 字典的键是索引，值是一维的值
s3 = pd.Series(np.random.randn(3), index=['a','b','c'])   # ndarray类型数据，索引与数据长度必须相同
s4  = pd.Series(5, index=[1,2,3,4])   # 使用单个数字的标量创建对象，必须提供索引
"""
s的索引是自动创建的，s_1的索引是自己指定的。
0    1.0
1    2.0
2    NaN
3    inf
4    5.0
dtype: float64
a    1
b    2
c    3
dtype: int64
a    [1, 2]
b         2
c         3
dtype: object
a    0.931488
b   -1.672014
c   -0.769385
dtype: float64
1    5
2    5
3    5
4    5
dtype: int64
"""

"""
【2、获取数据】
"""
x0 = s[1]
x = s.iloc[1]   # iloc的值只能是索引标签的对应的数字，不能是其他类型
x1 = s.iloc[:3]
x2 = s[s > 1]
x3 = s[[1,2,3]]
"""
- x0,x都是基于索引选取单个元素
2.0
2.0
- x1,x2,x3: 切片等操作同时也会对索引操作
0    1.0
1    2.0
2    NaN
dtype: float64
1    2.0
3    inf
4    5.0
dtype: float64
1    2.0
2    NaN
3    inf
dtype: float64
"""
"""
下面这几种获取数据的方法中，只有最后一种会报错，因为iloc的只支持整形数字索引
print(s1.get(1))
print(s1[1])
print(s1['a'])
print(s1.iloc[1])
print(s1.iloc['a'])   # 会报错，只支持整形参数
"""
