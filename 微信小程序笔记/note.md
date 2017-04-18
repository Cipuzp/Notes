# <center>微信小程序笔记

- 相关文件：wxml、wxss、json、js  
- 最多只能五级层级页面
- 文件夹下的文件的文件名称一定要一样
- wxml是编写小程序骨架的文件，view相当于div
- 只有被text包围的文件才能在手机上长按选中
- 使用iphone6的格式，用rpx替换px
- 弹性盒子布局  
```
.yourClassName{
    display: flex;
    flex-direction: column;
    align-items: center;
}
```
- 全局配置样式应该放在app.wxss样式中、
- 如何设置垂直居中？使文字的line-height等于容器高度