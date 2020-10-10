var app = new Vue({
    el: "#app",
    data: {
        Cno: "36",
        course: {},
        professions: [],
        teachers: [],
        oldTID: "",
        state: false,
    },
    mounted: function() {
        this.Cno=localStorage.cno;
        console.log(this.Cno);
        this.getProfessions();
        this.getCourse();
        this.getTeachers();
    },
    methods: {
        getCourse: function() {
            let that = this;
            axios.get("http://47.97.206.40/admin/course/getOneCourse/" + that.Cno)
                .then(res => {
                    console.log(res)
                    that.course = res.data.course;
                    that.oldTID = that.course.tID;
                })
                .catch(err => {
                    console.log(err);
                })
        },
        getProfessions: function() {
            let that = this;
            axios.get("http://47.97.206.40/admin/allProfession")
                .then(res => {
                    // console.log(res);
                    that.professions = res.data.professions;
                    that.state = true;
                })
                .catch(err => {
                    console.error(err);
                })
        },
        getTeachers: function() {
            let that = this;
            axios.get("http://47.97.206.40/admin/allTeachers")
                .then(res => {
                    // console.log(res)
                    that.teachers = res.data;
                    // console.log(this.teachers);
                })
                .catch(err => {
                    console.error(err);
                })
        },
        modCourse: function() {
            let that = this;
            let data = {
                name: that.course.name,
                profession: that.course.profession,
                year_admission: that.course.year_admission,
                ifopen: that.course.ifopen,
                Cno: that.course.cno
            }
            let TID = $("input[name=teacher]:checked").val();
            console.log(data, that.oldTID, TID);
            if (that.course.name == "") {
                alert("请填写课程名称");
                return;
            } else if (that.course.profession == "") {
                alert("请选择上课的专业");
                return;
            }
            if (TID == undefined) {
                alert("请选择任课教师");
                return;
            }
            if (!confirm("确定提交？"))
                return;
            $.ajax({
                type: "GET",
                url: "http://47.97.206.40/admin/course/updateCourse",
                data: data,
                dataType: "String",
                success: function(result) {
                    console.log(result);
                },
                complete: function(result) {
                    console.log(result);
                    that.modCourseTeacher(that.course.cno, TID)
                    alert("修改成功");
                }
            })
        },
        modCourseTeacher: function(Cno, TID) {
            let that = this;
            axios.get("http://47.97.206.40/admin/course/updateCourseTeacher/" + that.oldTID + "?" +
                    "Cno=" + Cno + "&TID=" + TID)
                .then(res => {
                    console.log(res)
                })
                .catch(err => {
                    console.error(err);
                })
        },
    }
})