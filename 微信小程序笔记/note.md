# <center>微信小程序笔记

- 相关文件：wxml、wxss、json、js  
- 最多只能五级层级页面
- 文件夹下的文件的文件名称一定要一样
- 创建新的文件，需要现在app.json中注册
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

- 这样会使Hel和lo换行,这是text的一个特性  
```
<text style="color:red;">Hel \n lo</text>
```  

- pages数组下的第一项元素代表了小程序要启动的第一个页面
- 在swiper上设置css才有效，在swiper-item上设置无效
- indicator-dots="true"一定需要引号
- 水平间距建议使用rpx，垂直间距建议使用px
- letter-spacing: 2rpx;文本间距
- 数据绑定{{name}},在text中间不需要引号，在src后面需要加入用引号包含
- boolean型的false要将false加上{{}}如vertical="{{false}}"这样才能使滑动水平
- text中可以绑定多个{{}}  如{{name1}}{{name2}}
- 控制显隐!wx:if="{{false}}"   这样设置会使绑定的数据元素不显示wx:if="这里有内容就行"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;这样会显示
wx:if="{{这里的内容也可以绑定，如(wxname)}}"  在绑定位置使用wxname:true或者wxname:false

- 多层嵌套时，比如
```
 var post_content1={
        date: "Sep 18 2016",
        title: "正是虾肥蟹壮时",
        img:{
          imgSrc: "/images/post/crab.png",
          avatar: "/images/avatar/1.png"
        },
        content: "菊黄蟹正肥，品尝秋之味。徐志摩把,“看初花的荻芦”和“到楼外楼吃蟹”,并列为秋天来杭州不能错过的风雅之事；用林妹妹的话讲是“螯封嫩玉双双满，",
        reading: "112",
        collection: "96",
    }
```
前面的数据绑定位置想要显示img里的内容，需要用src="{{img.avatar}}"的方法显示，嵌套多层使用多个img.img1.img2等等依次增加
- this.setData(posts_content);这段代码的作用是将posts_content中的内容绑定到data中
-   <block wx:for="{{posts_key}}" wx:for-item="item">中间是要显示的内容，被block包住<block>，使用方法{{item.avatar}}开连接

-   使用```<view class="moto-container" bindtap="onTap">```进行页面跳转，js文件中用  
``` onTap:function(){
        wx.navigateTo({
          url: '../posts/post',
          success: function(res){
            // success
          },
          fail: function(res) {
            // fail
          },
          complete: function(res) {
            // complete
          }
        })
    }
``` 
配合跳转

- bind事件绑定不会阻止冒泡事件向上冒泡，catch事件绑定可以阻止冒泡事件向上冒泡。
- redirectTo跳转后不能返回，navigateTo跳转后可以返回
- 按住alt + shift + F 可以格式化代码样式

- 通过这些代码来接受数据```var postsData = require('../../data/posts-data.js')```（只能用相对路径），
```module.exports={
    postList:local_database
}```
来封装数据

- 用```<import src="posts-item/post-item-template.wxml"/>```（即能用相对路径，也能使用绝对路径）来导入template模板，用```      <template is="postItem" data="{{...item}}" />```来使用模板,  ```...```的作用是将数据展开

- 用```@import "post-Item/post-item-template.wxss";```在总体css文件中导入模板中的wxss样式文件

- 用```<view catchtap="onPostTap" data-postId="{{item.postId}}">```来接收postId值，用```var postId = event.currentTarget.dataset.postid;```来设置postId