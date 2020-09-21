new Vue({
    el: '#app',
    data: {
        Cno: "1",
        type: "SingleSelection",
        describe: "",
        parsing: "",
        answer: [],
        category: [{
                cate: "单选",
                value: "SingleSelection"
            },
            {
                cate: "多选",
                value: "MultipleSelection"
            },
            {
                cate: "简答",
                value: "ShortAnswerQuestion"
            }
        ],
        options: [{
                option: "A",
                optionDescription: "选项内容"
            },
            {
                option: "B",
                optionDescription: "选项内容"
            }
        ]
    },
    mounted: function() {
        $("#SingleSelection").attr("selected", true);
    },
    methods: {
        addOption: function() {
            if (this.options.length < 8) {
                let option = String.fromCharCode(65 + this.options.length);
                this.options.push({ option: option, optionDescription: "选项内容" });
            } else
                alert("数量已达上限");
        },
        delOption: function() {
            if (this.options.length > 2)
                this.options.pop();
            else
                alert("数量已达下限");
        },
        isEmpty: function(val) {
            console.log(val);
            if (typeof val == "undefined" || val == null || val == "")
                return true;
            else
                return false;
        },
        collectAndSend: function() {
            if (this.type != "ShortAnswerQuestion") {
                let answers = [];
                $("input[name='selection']:checked").each(function() {
                    answers.push($(this).val());
                });
                this.answer = answers.toString();
            }
            console.log(this.Cno, this.type, this.describe, this.answer);
            let Cno = this.Cno;
            let type = this.type;
            let describe = this.describe;
            let describe_pic = "";
            let parsing = this.parsing;
            let parsing_pic = "";
            let answer = this.answer;
            let answer_pic = "";
            let options = this.options;

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
            axios.post("http://www.stydehome.com/teacher/course/addNewQuestion/" + Cno, data)
                .then(res => {
                    console.log(res);
                    alert("添加成功");
                    window.location.reload();
                })
                .catch(err => {
                    console.error(err);
                    alert("添加失败");
                })
        }
    }
})