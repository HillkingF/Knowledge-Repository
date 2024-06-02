"""
-*- coding: utf-8 -*-
Time     : 2024/4/29
Author   : fengwenni001
File     : a1_02_dataframe.py
Function : 
"""

import pandas as pd
"""
【1、创建对象】
1.1 基于字典创建
"""
d1 = {
    'a': [1, 2, 3],
    'b': [1, 2, 3]
}
df1 = pd.DataFrame(d1, index=['x','y','z'], columns=['a','b'])  # 指定索引名和列名。若不指定则key为列名，索引名自增创建
print(df1)
"""
   a  b
x  1  1
y  2  2
z  3  3
"""

"""
1.2 基于列表创建
"""
l1 = [1,2,3,4,5,6]
df2 = pd.DataFrame(l1)
l2 = [(1, 2.0, "Hello"), (2, 3.0, "World")]
df3 = pd.DataFrame(l2, index=['a', 'b'], columns=['X','Y','Z'])
df4 = df3[['Y','Z','X']]   # 改变列的顺序
print(df2)
print(df3)
print(df4)
"""
df2:
   0
0  1
1  2
2  3
3  4
4  5
5  6

df3:
   X    Y      Z
a  1  2.0  Hello
b  2  3.0  World

df4:
     Y      Z  X
a  2.0  Hello  1
b  3.0  World  2
"""

"""
1.3 基于字典列表创建
"""
data2 = [{"a": 1, "b": 2}, {"a": 5, "b": 10, "c": 20}]
df5 = pd.DataFrame(data2, index=['A','B'])
df6 = pd.DataFrame(data2, index=['A','B'], columns=['a'])
print(df5)
print(df6)
"""
df5:
   a   b     c
A  1   2   NaN
B  5  10  20.0

df6:
   a
A  1
B  5
"""




"""
【2、查看数据】
"""
print(df1.index)
print(df1.columns)
print(df1.index.tolist())
print(df1.columns.tolist())
"""
Index(['x', 'y', 'z'], dtype='object')
Index(['a', 'b'], dtype='object')
['x', 'y', 'z']
['a', 'b']
"""





"""
请根据以下代码，生成对应的中文说明文档，markdown格式。注意：保留""""""中的数据执行结果，标题下面创建一个目录，点击目录项可以跳转到对应的区域，每一层级标题需要编号。
"""