var app = new Vue({
    el: "#app",
    data: {
        state: false,

        name: "",
        authority: "",
        userId: "170405102",

        Cno: "1",
        courseList: [],
        collections: [],
        current: 0,
    },
    mounted: function() {
        this.getCourse();
    },
    methods: {
        // 获取用户权限和姓名 保存再authority和name
        getAuothority: function() {
            this.name = localStorage.name;
            this.authority = localStorage.authority;
            this.userId = localStorage.id;
            console.log('用户：' + this.name + '\n' + '权限：' + this.authority);
        },
        // 获取所有课程
        getCourse: function() {
            var that = this;
            axios.get('http://47.97.206.40/course').then(function(response) {
                that.courseList = response.data.courses;
                console.log(response);
                console.log('成功获取课程');
                that.getCollections();
                that.state = true;
                // that.getPaper();
                // that.getQuestions();
            }).catch(function(err) {
                console.log(err);
                alert('无法加载课程，请检查你的网络！');
            });
        },
        getCollections: function() {
            let that = this;
            this.userId = localStorage.id;
            axios.get("http://47.97.206.40/course/courseCollections?" +
                    "Cno=" + that.Cno + "&stuId=" + that.userId)
                .then(res => {
                    console.log(res)
                    console.log("成功获取收藏");
                    that.collections = res.data.testQuestions;
                })
                .catch(err => {
                    console.error(err);
                    that.collections = [];
                    // alert("无已收藏的内容");
                })
        },
        changeCourse:function (index) {
            this.current = index;
            this.Cno=this.courseList[this.current].cno;
            this.getCollections();
        },
        cancelCollect:function (tqNo) {
            if(!confirm("确定取消收藏？"))
                return;
            axios.get("http://47.97.206.40/course/deleteCollection?TqNo="+tqNo+"&stuId="+this.userId)
                .then(res => {
                console.log(res)
                alert("取消收藏成功");
                this.getCollections();
            })
            .catch(err => {
                    console.error(err);
                    alert("取消收藏失败");
            })

        }
    }
})