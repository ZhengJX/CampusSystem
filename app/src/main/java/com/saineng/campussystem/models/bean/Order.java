package com.saineng.campussystem.models.bean;

import java.util.List;

/**
 * Created by ${charles}     on 2017/6/5.
 *
 * @desc ${TODO}
 */

public class Order
{


    private List<ClassListBean> classList;

    public List<ClassListBean> getClassList() {
        return classList;
    }

    public void setClassList(List<ClassListBean> classList) {
        this.classList = classList;
    }

    public static class ClassListBean {

        private String className;
        private List<StudentsBean> students;

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public List<StudentsBean> getStudents() {
            return students;
        }

        public void setStudents(List<StudentsBean> students) {
            this.students = students;
        }

        public static class StudentsBean {
            /**
             * name : 陈坚城
             * id : 28276
             */

            private String name;
            private String id;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }
}
