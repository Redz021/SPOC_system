var app = new Vue({
    el: "#app",
    data: {
        // mode: "exam",
        mode: "exercise", //"exam" or "exercise"
        // 卷子号
        TpNo: 80,
        // 名字
        name:'',
        // 课程号
        Cno: 1,
        // id不一定是学生
        stuId: "170405108",
        // 权限
        authority:'student',
        // 只读还是做题
        option:'read',
        answer: [],
        tqNo: [],
        questions: [],
        SingleSelection: [],
        MultipleSelection: [],
        ShortAnswerQuestion: [],
        paperName:''

    },
    mounted: function() {
        this.name = localStorage.name;
        this.TpNo = localStorage.tpNo;
        this.stuId = localStorage.id;
        this.authority = localStorage.authority;
        this.paperName= localStorage.paperName;
        this.Cno=localStorage.cno;
        console.log('初始化权限：'+this.authority);
        if (this.authority=='teacher'){
            this.option = 'read';
            console.log('操作'+this.option);
            $('.jianda').each(function (index, element) {
                element.setAttribute('readonly',true);
            })
        }else{
            this.option = 'write';
        }
        axios.get("http://47.97.206.40/course/testPaper/" + this.TpNo)
            .then(res => {
                console.log(res);
                this.questions = res.data.testQuestions;
                for (index in this.questions) {
                    this.tqNo.push(this.questions[index].tqNo);
                    if (this.questions[index].type == "SingleSelection")
                        this.SingleSelection.push(this.questions[index]);
                    else if (this.questions[index].type == "MultipleSelection")
                        this.MultipleSelection.push(this.questions[index]);
                    else this.ShortAnswerQuestion.push(this.questions[index]);
                }
            })
            .catch(err => {
                console.error(err);
            })
    },
    methods: {
        // 退出登录
        logout:function(){
            axios.get('http://47.97.206.40/logout');
            $(location).attr('href', 'http://47.97.206.40');
        },
        submit: function() {
            this.answer.splice(0);
            for (index in this.questions) {
                let ans = "";
                let tqNo = this.questions[index].tqNo.toString();
                if (this.questions[index].type != "ShortAnswerQuestion") {
                    $("input[name='" + tqNo + "']:checked").each(function() {
                        ans += $(this).val();
                    });
                } else
                    ans = $("textarea[name='" + tqNo + "']").val();
                this.answer.push({ TqNo: tqNo, answer: ans })
            }
            let data = {
                "TpNo": this.TpNo.toString(),
                "stuId": this.stuId.toString(),
                "answers": this.answer
            };
            console.log(data);
            axios.post("http://47.97.206.40/course/student/doTestPaper", data)
                .then(res => {
                    console.log(res)
                    alert("提交成功");
                    window.location.href="/course/mainPage";//做完跳转
                })
                .catch(err => {
                    console.error(err);
                })
        },
        collect: function(val) {
            console.log(this.Cno, val, this.stuId);
            let data = {
                "Cno": this.Cno.toString(),
                "TqNo": val.toString(),
                "stuId": this.stuId
            };
            console.log(data);
            $.ajax({
                url: "http://47.97.206.40/course/addNewCollection",
                dataType: "String",
                async: true,
                data: data,
                type: "GET",
                success: function(res) {
                    console.log(res);
                    alert("收藏成功");
                },
                complete: function(res) {
                    console.log(res.responseText);
                    alert("收藏成功");
                }

            })
        }
        // cancelCollect: function(val) {
        //     let data = {
        //         "TqNo": val.toString(),
        //         "stuId": this.stuId
        //     }
        //     console.log(data);
        //     $.ajax({
        //         url: "http://47.97.206.40/course/deleteCollection",
        //         dataType: "String",
        //         async: true,
        //         data: data,
        //         type: "GET",
        //         success: function(res) {
        //             console.log(res);
        //             alert("取消收藏成功");
        //         },
        //         complete: function(res) {
        //             console.log(res);
        //             alert("取消收藏成功");
        //         }

        //     })
        // },
    }
})