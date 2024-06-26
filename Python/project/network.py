# import torch
# from torch import nn
# from d2l import torch as d2l
#
#
# class MultiHeadAttention(nn.Module):
#     """多头注意力"""
#
#     def __init__(self, key_size, query_size, value_size, num_hiddens, num_heads, dropout, bias=False, **kwargs):
#         super(MultiHeadAttention, self).__init__(**kwargs)
#         self.num_heads = num_heads
#         # 此处使用缩放点积注意力作为每一个注意力头
#         self.attention = d2l.DotProductAttention(dropout)
#         self.W_q = nn.Linear(query_size, num_hiddens, bias=bias)
#         self.W_k = nn.Linear(key_size, num_hiddens, bias=bias)
#         self.W_v = nn.Linear(value_size, num_hiddens, bias=bias)
#         self.W_o = nn.Linear(num_hiddens, num_hiddens, bias=bias)
#
#     def forward(self, queries, keys, values, valid_lens):   # 参数((2,4,100),(2,6,100),(2,6,100),torch.tensor([3, 2]))
#         # queries，keys，values的形状: (batch_size，查询或者“键－值”对的个数，num_hiddens)
#         # 经过变换后，输出的queries，keys，values　的形状: (batch_size * num_heads，查询或者“键－值”对的个数， num_hiddens/num_heads)
#         queries = transpose_qkv(self.W_q(queries), self.num_heads)
#         keys = transpose_qkv(self.W_k(keys), self.num_heads)
#         values = transpose_qkv(self.W_v(values), self.num_heads)
#
#         # valid_lens　的形状: (batch_size，)或(batch_size，查询的个数)
#         if valid_lens is not None:
#             # 在轴0，将第一项（标量或者矢量）复制num_heads次，然后如此复制第二项，然后诸如此类。
#             valid_lens = torch.repeat_interleave(valid_lens, repeats=self.num_heads, dim=0)
#
#         # output的形状:(batch_size*num_heads，查询的个数，num_hiddens/num_heads)
#         output = self.attention(queries, keys, values, valid_lens)
#
#         # output_concat的形状:(batch_size，查询的个数，num_hiddens)
#         output_concat = transpose_output(output, self.num_heads)
#         return self.W_o(output_concat)
#
# # ------------------------两个函数--------------------------
# def transpose_qkv(X, num_heads):
#     """为了多注意力头的并行计算而变换形状"""
#     # 输入X的形状:(batch_size，查询或者“键－值”对的个数，num_hiddens)
#     # 输出X的形状:(batch_size，查询或者“键－值”对的个数，num_heads，num_hiddens/num_heads)
#     X = X.reshape(X.shape[0], X.shape[1], num_heads, -1)
#     # 输出X的形状:(batch_size，num_heads，查询或者“键－值”对的个数, num_hiddens/num_heads)
#     X = X.permute(0, 2, 1, 3)
#     # 最终输出的形状:(batch_size * num_heads,查询或者“键－值”对的个数, num_hiddens/num_heads)
#     return X.reshape(-1, X.shape[2], X.shape[3])
#
#
# def transpose_output(X, num_heads):
#     """逆转transpose_qkv函数的操作"""
#     X = X.reshape(-1, num_heads, X.shape[1], X.shape[2])
#     X = X.permute(0, 2, 1, 3)
#     return X.reshape(X.shape[0], X.shape[1], -1)
#
#
# #--------------------------测试案例-----------------------
# #### 下面我们使用键和值相同的小例子来测试我们编写的MultiHeadAttention类。 多头注意力输出的形状是（batch_size，num_queries，num_hiddens）
# num_hiddens, num_heads = 100, 5
# # key_size, query_size, value_size,与num_hiddens相同
# attention = MultiHeadAttention(num_hiddens, num_hiddens, num_hiddens, num_hiddens, num_heads, 0.5)
# print(attention.eval())
#
# batch_size, num_queries = 2, 4
# num_kvpairs, valid_lens = 6, torch.tensor([3, 2])   # 3行2列
# X = torch.ones((batch_size, num_queries, num_hiddens))  # (2,4,100)
# Y = torch.ones((batch_size, num_kvpairs, num_hiddens))  # (2,6,100)
# print(attention(X, Y, Y, valid_lens).shape)  # 参数((2,4,100),(2,6,100),(2,6,100),torch.tensor([3, 2]))
