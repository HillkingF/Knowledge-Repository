# conda命令

```bash
# 在指定位置创建虚拟环境
conda create --prefix /aistudio/workspace/conda_envs/py310 python=3.10


# 在指定位置创建虚拟环境后，可能出现虚拟环境没有名字的情况，使用下面的命令将自定义虚拟环境目录加入到配置文件中
conda config --append envs_dirs /aistudio/workspace/conda_envs
```

