'''
@Author: 冯文霓
@Date: 2023/6/6
@Purpose:  定义一些环境变量、密钥等公共代码
'''

import os


# openai的openai-api-key密钥
os.environ["OPENAI_API_KEY"] = "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"
os.environ["http_proxy"] = "http://127.0.0.1:33210"
os.environ["https_proxy"] = "http://127.0.0.1:33210"