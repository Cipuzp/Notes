

1. 错误  
```
app.json  
Expecting 'STRING','NUMBER','NULL','TRUE','FALSE','{','[', got EOF  
  1 |  
~    
```  
修改意见：需要在app.json中添加相关配置文件
2. 错误  
```
pages/welcome/welcome 出现脚本错误或者未正确调用 Page()
```  
修改意见：在出错的相关的js文件中加入Page({})即可