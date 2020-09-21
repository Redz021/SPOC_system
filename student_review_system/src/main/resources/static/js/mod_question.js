var app = new Vue({
    el: "#app",
    data: {
        TqNo: 1,
        question: {}
    },
    mounted: function() {
        this.getQuestionInfo();
    },
    methods: {
        addOption: function() {
            if (this.question.options.length < 8) {
                let option = String.fromCharCode(65 + this.question.options.length);
                this.question.options.push({ option: option, optionDescription: "选项内容" });
            } else
                alert("数量已达上限");
        },
        delOption: function() {
            if (this.question.options.length > 2)
                this.question.options.pop();
            else
                alert("数量已达下限");
        },
        isAnswer: function(val) {
            return (this.question.answer.includes(val));
        },
        isEmpty: function(val) {
            console.log(val);
            if (typeof val == "undefined" || val == null || val == "")
                return true;
            else
                return false;
        },
        getQuestionInfo: function() {
            this.TqNo = localStorage.tqNo;
            var that = this;
            axios.get("http://127.0.0.1/course/QuestionDetail/" + that.TqNo)
                .then(res => {
                    this.question = res.data.question;
                    console.log(this.question)
                }).catch(err => {
                    console.error(err);
                })
        },
        collectAndSend: function() {
            if (this.type != "ShortAnswerQuestion") {
                let answers = [];
                $("input[name='selection']:checked").each(function() {
                    answers.push($(this).val());
                });
                this.question.answer = answers.toString();
            }
            console.log(this.question.tqNo, this.question.type, this.question.describe, this.question.answer);
            let TqNo = this.question.tqNo;
            let type = this.question.type;
            let describe = this.question.describe;
            let describe_pic = "";
            let parsing = this.question.parsing;
            let parsing_pic = "";
            let answer = this.question.answer;
            let answer_pic = "";
            let options = this.question.options;

            if (this.isEmpty(describe) || this.isEmpty(answer)) {
                alert("请填写内容");
                return;
            }

            let data = {
                "type": type,
                "describe": describe,
                "describe_pic": describe_pic,
                "parsing": parsing,
                "parsing_pic": parsing_pic,
                "answer": answer,
                "answer_pic": answer_pic,
                "options": options
            }
            axios.post("http://www.stydehome.com/teacher/course/updateQuestion/" + TqNo, data)
                .then(res => {
                    console.log(res);
                    alert("修改成功");
                    window.location.reload();
                })
                .catch(err => {
                    console.error(err);
                    alert("修改失败");
                })
        }
    }
})