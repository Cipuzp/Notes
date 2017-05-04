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
-   <block wx:for="{{posts_key}}" wx:for-item="item">中间是要显示的内容，被block包住<block>，使用方法{{item.avatar}}来连接

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

- 用```@import "post-Item/post-item-template.wxss";```在总体css文件中导入模板中的wxss样式文件结尾处需要加上分号

- 在template写图片URL地址时，最好使用绝对路径，因为template模板可能给多个页面使用，如过给不同层级的文件使用，这时使用相对路径就会出错

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

- 显示操作菜单方法

```
 var itemsList = [
     "分享给微信好友",
     "分享到朋友圈",
     "分享到QQ",
     "分享到微博"
        ]
 wx.showActionSheet({
            itemList: itemsList, //这里的itemList不能随便修改名称
            itemColor: "#405f80",
            success: function (res) {
                //res.cancel用户是不是点击了取消按钮
                //res.tapIndex数组元素的序号，从0开始
                wx.showModal({
                    title: "用户" + itemsList[res.tapIndex],
                    content: "用户是否取消" + res.cancel + "现在无法实现分享功能，什么时候能支持呢"
                })
            }
        })
```

- 播放暂停音乐的方法:

```
 onMusicTap: function () {
        var isPlayingMusic = this.data.isPlayingMusic;
        if (isPlayingMusic) {
            wx.pauseBackgroundAudio;     //暂停播放音乐
            this.setData({
                isPlayingMusic: false
            })
        } else {
            wx.playBackgroundAudio({        //开始播放音乐
                dataUrl: 'http://ws.stream.qqmusic.qq.com/C100003507bR0gDKBm.m4a?fromtag=38',
                title: '夜夜夜夜-齐秦',
                coverImgUrl: 'http://y.gtimg.cn/music/photo_new/T002R150x150M000002xOmp62kqSic.jpg?max_age=2592000'
            })
            this.setData({
                isPlayingMusic: true
            })
        }
    }
```

- 切换图片的两种方法:  
第一种   

```
 <image wx:if="{{collected}}" catchtap="onCollectionTap" src="/images/icon/collection.png"></image>
 <image wx:elif="{{collected}}" catchtap="onCollectionTap" src="/images/icon/collection.png"></image>
      <image wx:else catchtap="onCollectionTap" src="/images/icon/collection-anti.png"></image>
```

第二种    

```
 <image catchtap="onMusicTap" class="audio" src="{{isPlayingMusic?'/images/music/music-start.png':'/images/music/music-stop.png'}}"></image>

图片URL地址一定要加上''包围
```
 
- 监听后台音乐播放情况方法

```
        var that = this;
        //监听音乐播放事件
        wx.onBackgroundAudioPlay(function () {
            // callback
            that.setData({
                isPlayingMusic: true
            })
        })
        //监听音乐暂停事件
        wx.onBackgroundAudioPause(function () {
            // callback
            that.setData({
                isPlayingMusic: false
            })
        })
```

- target和
- Target的区别

```
  //target指的是当前点击的组件，currentTarget指的是事件捕获的组件
    //target这里指的是image而currentTarget指的是swiper组件
        var postId = event.target.dataset.postid;//经过调试后，target中才有id号


 <swiper catchtap="onSwiperTap" class="swiper" indicator-dots="true" autoplay="true" interval="5000">
    <swiper-item>
      <image  src="/images/wx.png" data-postId="3"></image>
    </swiper-item>
    <swiper-item>
      <image  src="/images/vr.png" data-postId="4"></image>
    </swiper-item>
    <swiper-item>
      <image  src="/images/iqiyi.png" data-postId="5"></image>
    </swiper-item>
  </swiper>
```

- template模板多层套用时，css也需要多层套用


```
  justify-content:space-around;   //使flex中的部件平均分布
  justify-content:space-between;  //使flex中的部件两端分布
  justify-content:center;  	      //使flex中的部件居中
```



- 使用flex盒子模型后，vertical-align样式不能使用 

- http状态码

```
400参数错误
404没有找到资源
500服务器未知错误
502网关错误
301重定向
```

- 用
```
var app =getApp();
```
来获取全局属性

- 获取api方法
```
 wx.request({
            url: url,
            // data: {},
            method: 'GET', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT
            header: {
                "Content-Type": "json"
            }, // 设置请求的 header
            success: function (res) {
                console.log(res)
            },
            fail: function (error) {
                console.log(error)
            }
        })
```

- 请求三条数据的方法
```
        var inTheatersUrl = app.globalData.doubanBase + "/v2/movie/in_theaters"+"?start=0&count=3";
```

- 编写模板时，先小后大，传递数据时，先大后小

- 多页面传参数

```
    onMoreTap: function (event) {
        var category = event.currentTarget.dataset.category;
        wx.navigateTo({
            url: 'more-movie/more-movie?category=' + category
        })
    },
下面接收
    var category=options.category;
```

- 设置导航条方法
```
 onReady: function (event) {
    wx.setNavigationBarTitle({
      title: this.data.navigateTitle,
      // title:this.setData(navigateTitle)
    })
  }
只能在onReady里面设置
```