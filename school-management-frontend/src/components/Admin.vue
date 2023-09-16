<script>

import StudentManagement from "@/components/admin/StudentManagement.vue";
import CourseManagement from "@/components/admin/CourseManagement.vue";
import DepartmentManagement from "@/components/admin/DepartmentManagement.vue";
import ApplicationConfirm from "@/components/admin/ApplicationConfirm.vue";

export default {
  components: {
    StudentManagement,
    CourseManagement,
    DepartmentManagement,
    ApplicationConfirm,
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
          <el-menu-item index="CourseManagement">课程管理</el-menu-item>
          <el-menu-item index="DepartmentManagement">公共管理</el-menu-item>
          <el-menu-item index="ApplicationConfirm">申请审批</el-menu-item>
        </el-menu>

      </el-main>
    </el-header>

    <StudentManagement v-if="activeIndex === 'StudentManagement'"/>
    <CourseManagement v-if="activeIndex === 'CourseManagement'"/>
    <DepartmentManagement v-if="activeIndex === 'DepartmentManagement'"/>
    <ApplicationConfirm v-if="activeIndex === 'ApplicationConfirm'"/>

  </div>
</template>

<style>

</style>