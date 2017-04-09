# Demo2


## 链接 demo

### 内嵌连接

- 外部链接:[百度](https://www.baidu.com)  
- 内部链接1:链接仓库的其他文件: [Demo1](Demo1.md)
- 内部链接2:链接本文档的其他部分: [代码块 demo](Demo2.md#代码块-demo)

### 引用式链接

- 外部链接:[百度]
- 外部链接:[百度][baidu]
- 内部链接1:链接仓库的其他文件:[Demo1]
- 内部链接2:链接本文档的其他部分：[代码块 demo]

## 图片 demo

### 图片的内嵌连接

- 图片的Markdown语法  
    ![alt](url txt)
    
- 外部图片
![google](http://jbcdn2.b0.upaiyun.com/2015/11/a569940a91db90f903ccdac9ae137e45.jpg "谷歌网站")  
- 仓库内的图片  
![GoogleChina](images/google.png)

### 图片的引用式链接

- 外部图片
![google][GoogleLogo] 
- 仓库内的图片  
![GoogleChina][GoogleChinaLogo]

## 引用 demo

>这是一个引文。  

—出自《出处》

- 多次引用
>>>这是多重引文

## 代码块 demo  

- 行内代码

这个代码中用来声明变量是`var a = 10`,打印变量`console.log`函数的调用

- 块式代码

<!-- 此处的JavaScript表示 下面的代码关键字会遵从该语言的关键字高亮显示 -->

```javascript
var a = 10;
console.log(a);
```

- 加四个空格，这也是块式代码，但是不能使代码高亮！
    var a = 10;
    console.log(a);

<!-- 本文中的所有链接  -->
[百度]: http://www.baidu.com
[baidu]: http://www.baidu.com
[Demo1]: Demo1.md
[代码块 demo]: Demo2.md#代码块-demo
[GoogleLogo]:http://jbcdn2.b0.upaiyun.com/2015/11/a569940a91db90f903ccdac9ae137e45.jpg
[GoogleChinaLogo]:images/google.png
