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

- 通过这些代码来接受数据
```var postsData = require('../../data/posts-data.js')```（只能用相对路径），```module.exports={
    postList:local_database
}```
来封装数据

- 用```<import src="posts-item/post-item-template.wxml"/>```（即能用相对路径，也能使用绝对路径）来导入template模板，用```      <template is="postItem" data="{{...item}}" />```来使用模板,  ```...```的作用是将数据展开

- 用```@import "post-Item/post-item-template.wxss";```在总体css文件中导入模板中的wxss样式文件

- 用```<view catchtap="onPostTap" data-postId="{{item.postId}}">```来接收postId值，用```var postId = event.currentTarget.dataset.postid;```来设置postId

- 跨页面传递postId值:
这是主页面
```
    wx.navigateTo({
      url: 'post-detail/post-detail?这里=' + postId
    })
```
这是要传递过去的子页面
```
    onLoad:function(option){
    var postId=option.这里;
```

- 这是赋值
```
        var postData=postsData.postList[postId];
        this.setData({
            postData=postData
        })
```

这是接受赋值{{postData.headImgSrc}}

- 设置缓存，这是同步方法

```
        wx.setStorageSync('key',"看门狗")
和
        wx.setStorageSync('key',{
            game:"看门狗",
            developer:"育碧"
        })
都可以
```

- 获取缓存的基本方法
```
    onCollectionTap: function (event) {
        var game = wx.getStorageSync('key')
    }
```
onCollectionTap函数绑定在你需要点击的部件处

- 清除缓存的方法
```
       //缓存的上限最大不能超过10MB
    onShareTap:function(event){
       wx.removeStorageSync('key')  //清除目标缓存
       wx.clearStorageSync()  //清除所有缓存
    }
```

- 判断是否收藏
```
    onCollectionTap: function (event) {
        var postdCollected = wx.getStorageSync('posts_Collected');
        var postCollexted = postsCollected[this.data.currentPostId];
        postCollexted = !postCollexted;//取反操作
    }
```

- 显示消息提示栏的方法(自动消失)
```
        wx.showToast({
            title:postCollexted?"收藏成功":"取消成功"
        })
```

- 显示消息提示栏的方法(不自动消失)
```
        wx.showModal({
            title:"收藏",
            content:"是否收藏该文章",
            showcencel:"true",
            cancelText:"不收藏",
            cancelColor:"#333",
            confirmText:"收藏",
            confirmColor:"405f80"
        })

```

- 在API方法中的success等方法中this的指代已经发生了改变