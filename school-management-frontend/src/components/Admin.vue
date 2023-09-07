<script>

import StudentManagement from "@/components/admin/StudentManagement.vue";
import LessonManagement from "@/components/admin/LessonManagement.vue";
import DepartmentManagement from "@/components/admin/DepartmentManagement.vue";
import MajorManagement from "@/components/admin/MajorManagement.vue";
import ClassManagement from "@/components/admin/ClassManagement.vue";
import GradeManagement from "@/components/admin/GradeManagement.vue";

export default {
  components: {
    StudentManagement,
    LessonManagement,
    DepartmentManagement,
    MajorManagement,
    ClassManagement,
    GradeManagement,
  },
  data() {
    return {
      activeIndex: 'StudentManagement',
    }
  },
  methods: {
    handleSelect(index) {
      this.activeIndex = index
    }
  }

}
</script>

<template>
  <div>
    <el-header style="border: 1px solid #eee">
      <el-main>
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
          <el-menu-item disabled style="color: black">学生学籍管理系统</el-menu-item>
          <el-menu-item index="StudentManagement">学生管理</el-menu-item>
          <el-menu-item index="LessonManagement">课程管理</el-menu-item>
          <el-menu-item index="GradeManagement">成绩管理</el-menu-item>
          <el-menu-item index="DepartmentManagement">院系管理</el-menu-item>
          <el-menu-item index="MajorManagement">专业管理</el-menu-item>
          <el-menu-item index="ClassManagement">班级管理</el-menu-item>
        </el-menu>

      </el-main>
    </el-header>

    <StudentManagement v-show="activeIndex === 'StudentManagement'"/>
    <LessonManagement v-show="activeIndex === 'LessonManagement'"/>
    <GradeManagement v-show="activeIndex === 'GradeManagement'"/>
    <DepartmentManagement v-show="activeIndex === 'DepartmentManagement'"/>
    <MajorManagement v-show="activeIndex === 'MajorManagement'"/>
    <ClassManagement v-show="activeIndex === 'ClassManagement'"/>

  </div>
</template>

<style>

</style>