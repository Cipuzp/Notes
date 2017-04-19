Page({
  data:{
      data:"Sep 18 2016",
      title:"正是虾肥蟹壮时"
  },
  imgPath:"/images/...",
  process:function(){
      var date = "Nov 18 2016";
      var daate_ele = document.getElementById('Id');
  },
  onLoad:function(options){
    //页面初始化options为页面跳转所带来的参数
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
    this.setData(post_content1);

    
    // 生命周期函数--监听页面加载
    console.log("onLoad");
  },
  onReady:function(){
    // 生命周期函数--监听页面初次渲染完成
    console.log("onReady");
  },
  onShow:function(){
    // 生命周期函数--监听页面显示
    console.log("onShow");
  },
  onHide:function(){
    // 生命周期函数--监听页面隐藏
    console.log("onHide");
  },
  onUnload:function(){
    // 生命周期函数--监听页面卸载
    console.log("onUnload");
  },
    onPullDownRefresh: function() {
    // 页面相关事件处理函数--监听用户下拉动作
    String7
  },
  onReachBottom: function() {
    // 页面上拉触底事件的处理函数
    String8
  },
  onShareAppMessage: function() {
    // 用户点击右上角分享
    return {
      title: 'title', // 分享标题
      desc: 'desc', // 分享描述
      path: 'path' // 分享路径
    }
  }


})
