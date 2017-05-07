var postsData = require('../../../data/posts-data.js')
var app = getApp();
Page({
    data: {
        isPlayingMusic: false
    },

    onLoad: function (option) {
        var postId = option.id;
        this.data.currentPostId = postId;
        var postData = postsData.postList[postId];
        this.setData({
            postData: postData
        })

        var postsCollected = wx.getStorageSync('posts_Collected')
        if (postsCollected) {
            var postCollexted = postsCollected[postId]
            this.setData({
                collected: postCollexted
            })
        } else {
            var postsCollected = {};
            postsCollected[postId] = false;
            wx.setStorageSync('posts_Collected', postsCollected);
        }

        if (app.globalData.g_isPlayMusic && app.globalData.g_currentMisicPostId == postId) {
            this.setData({
                isPlayingMusic: true
            })
        }
        this.setMusicMonitor();

    },

    setMusicMonitor: function (event) {
        var that = this;
        //监听音乐播放事件
        wx.onBackgroundAudioPlay(function (event) {
            // callback
            that.setData({
                isPlayingMusic: true
            })
            app.globalData.g_isPlayMusic = true;
            app.globalData.g_currentMisicPostId = that.data.currentPostId;
        })
        //监听音乐暂停事件
        wx.onBackgroundAudioPause(function (event) {
            // callback
            that.setData({
                isPlayingMusic: false
            })
            app.globalData.g_isPlayMusic = false;
            app.globalData.g_currentMisicPostId = null;
        })
        //监听音乐停止事件
        wx.onBackgroundAudioStop(function (event) {
            // callback
            that.setData({
                isPlayingMusic: false
            })
            app.globalData.g_isPlayMusic = false;
            app.globalData.g_currentMisicPostId = null;
        })
    },


    onCollectionTap: function (event) {
        this.getPostsCollectedSyc();//同步方法
        // this.getPostsCollectedAsy();//异步方法

    },

    //封装的异步的方法
    getPostsCollectedAsy: function () {
        var that = this;
        var that = this;
        wx.getStorage({
            key: "posts_Collected",
            success: function (res) {
                var postsCollected = res.data;
                var postCollected = postsCollected[that.data.currentPostId];
                //取反操作。将收藏变为未收藏，未收藏变为收藏
                postCollected = !postCollected;
                postsCollected[that.data.currentPostId] = postCollected;
                that.showToast(postsCollected, postCollected);
            }
        })
    },

    //封装的同步的方法
    getPostsCollectedSyc: function () {
        var postsCollected = wx.getStorageSync('posts_Collected');
        var postCollected = postsCollected[this.data.currentPostId];
        //取反操作。将收藏变为未收藏，未收藏变为收藏
        postCollected = !postCollected;
        postsCollected[this.data.currentPostId] = postCollected;
        this.showToast(postsCollected, postCollected);
    },



    showModal: function (postsCollected, postCollected) {
        var that = this;
        wx.showModal({
            title: "收藏",
            content: postCollected ? "收藏该文章?" : "取消收藏该文章?",
            showcencel: "true",
            cancelText: "取消",
            cancelColor: "#333",
            confirmText: "确认",
            confirmColor: "red",
            success: function (res) {
                if (res.confirm) {
                    //更新文章是否收藏的缓存值
                    wx.setStorageSync('posts_Collected', postsCollected);
                    //更新数据绑定变量，从而实现切换图片
                    that.setData({
                        collected: postCollected
                    })
                }
            }
        })
    },

    showToast: function (postsCollected, postCollected) {
        //更新文章是否收藏的缓存值
        wx.setStorageSync('posts_Collected', postsCollected);
        //更新数据绑定变量，从而实现切换图片
        this.setData({
            collected: postCollected
        })
        wx.showToast({
            title: postCollected ? "收藏成功" : "取消成功",
            duration: 1000,
            icon: "success"
        })
    },

    onShareTap: function (event) {
        var itemsList = [
            "分享给微信好友",
            "分享到朋友圈",
            "分享到QQ",
            "分享到微博"
        ];
        wx.showActionSheet({
            itemList: itemsList,
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
    },

    onMusicTap: function (event) {
        var currentPostId = this.data.currentPostId;
        var postData = postsData.postList[currentPostId];
        var isPlayingMusic = this.data.isPlayingMusic;
        if (isPlayingMusic) {
            wx.pauseBackgroundAudio();     //暂停播放音乐
            this.setData({
                isPlayingMusic: false
            })
        } else {
            wx.playBackgroundAudio({        //开始播放音乐
                dataUrl: postData.music.url,
                title: postData.music.title,
                coverImgUrl: postData.music.coverImg
            })
            this.setData({
                isPlayingMusic: true
            })
        }
    }

})
