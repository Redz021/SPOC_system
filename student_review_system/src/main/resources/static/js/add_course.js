var app = new Vue({
    el: "#app",
    data: {
        Cno: "",
        name: "",
        professions: [],
        profession: "",
        year_admission: "17",
        ifopen: "on",
        step: 1,
        teachers: [],
        state: false,
    },
    mounted: function() {
        this.getProfessions();
    },
    methods: {
        getProfessions: function() {
            axios.get("http://47.97.206.40/admin/allProfession")
                .then(res => {
                    // console.log(res);
                    this.professions = res.data.professions;
                    this.state = true;
                })
                .catch(err => {
                    console.error(err);
                })
        },
        getTeachers: function() {
            axios.get("http://47.97.206.40/admin/allTeachers")
                .then(res => {
                    // console.log(res)
                    this.teachers = res.data;
                    // console.log(this.teachers);
                })
                .catch(err => {
                    console.error(err);
                })
        },
        addCourse: function() {
            let that = this;
            let data = {
                name: that.name,
                profession: that.profession,
                year_admission: that.year_admission,
                ifopen: that.ifopen
            };
            console.log(data);
            let TID = $("input[name=teacher]:checked").val();
            if (TID == undefined) {
                alert("请选择任课教师");
                return;
            }
            if (!confirm("确定提交？"))
                return;
            $.ajax({
                type: "GET",
                url: "http://47.97.206.40/admin/course/addCourse",
                data: data,
                dataType: "String",
                success: function(result) {
                    console.log(result);
                },
                complete: function(result) {
                    console.log(result);
                    let Cno = JSON.parse(result.responseText).Cno;
                    that.Cno = Cno;
                    that.addCourseTeacher(Cno, TID)
                }
            })
        },
        addCourseTeacher: function(Cno, TID) {
            axios.get("http://47.97.206.40/admin/course/addCourseTeacher?" +
                    "Cno=" + Cno + "&TID=" + TID)
                .then(res => {
                    console.log(res)
                })
                .catch(err => {
                    console.error(err);
                })
        },
        nextStep: function() {
            if (this.name == "") {
                alert("请填写课程名称");
            } else if (this.profession == "") {
                alert("请选择上课的专业");
            } else {
                this.step = 2;
                this.getTeachers();
            }
        },
    }
})