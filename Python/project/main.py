# pandas导入
# import pandas as pd
import numpy as np
import torch
from transformers import pipeline
import transformers

# 创建整数索引
# s = pd.Series([1,2,3,4,np.nan, 6, 8])
# print(np.nan)
# print(torch.torch_version)
generator = pipeline(task="automatic-speech-recognition")


# if __name__ == '__main__':
#      print('PyCharm')


