var postsData = require('../../data/posts-data.js')
Page({
  data: {

  },

  onLoad: function (options) {
    //页面初始化options为页面跳转所带来的参数

    this.setData({
      posts_key: postsData.postList
    });
  },

  onPostTap: function (event) {
    var postId = event.currentTarget.dataset.postid;
    // console.log("onPostTap");
    wx.navigateTo({
      url: 'post-detail/post-detail?id=' + postId
    })
  }
  ,
  onSwiperTap:function(event){
    //target和currentTarget的区别
    //target指的是当前点击的组件，currentTarget指的是事件捕获的组件
    //target这里指的是image而currentTarget指的是swiper组件
        var postId = event.target.dataset.postid;//经过调试后，target中才有id号
            wx.navigateTo({
      url: 'post-detail/post-detail?id=' + postId
    })
  }
})
