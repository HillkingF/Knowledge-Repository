'''
@Author: 冯文霓
@Date: 2023/6/6
@Purpose: prompt格式化用户输入，使用.format()传入用户参数
'''

from langchain.prompts import PromptTemplate  # 格式化提示模板


# 1.创建格式化接收用户输入的prompt提示（将用户输入格式化为特定的prompt）
prompt = PromptTemplate(
    input_variables=["product"],
    template="what is a good name for a company that makes {product}?"
)


# 打印格式化后的结果
print(prompt.format(product="colorful socks"))
print(prompt.format(product="milks"))