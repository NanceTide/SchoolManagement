<script>
import axios from "axios";
import {API_URL, showError, showSuccess} from "@/utils";
import GradeManagement from "@/components/admin/GradeManagement.vue";

export default {
  components: {GradeManagement},
  created() {
    this.pageChange(1)

    axios.post(API_URL + '/admin/course/totalCount', null, {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
        'Token': localStorage.getItem('Token')
      }
    }).then(({data}) => {
      if (!data)
        showError("后端服务器存在问题")
      if (data.status) {
        this.total = data.data;
      }
    })
  },
  data() {
    return {
      dataList: [],
      gradeList: [],

      nowPage: 1,
      total: 1,

      editDialogVisible: false,
      gradeDialogVisible: false,
      insertDialogVisible: false,

      courseId: '',
      courseName: '',
      credit: '',

      searchStr: '',
    }
  },
  methods: {
    pageChange(nowPage) {
      axios.post(API_URL + '/admin/course/list', null, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Token': localStorage.getItem('Token')
        },
        params: {
          page : nowPage
        }
      }).then(({data}) => {
        if (!data)
          showError("后端服务器存在问题")
        if (data.status) {
          this.dataList = data.data
        } else
          showError(data.message)
      }).catch(showError)
    },

    openEditDialog(row) {
      this.editDialogVisible = true
      this.courseId = row.courseId
      this.courseName = row.courseName
      this.credit = row.credit
    },

    editCourse() {
      axios.post(API_URL + '/admin/course/update', {
        courseId: this.courseId,
        courseName: this.courseName,
        credit: this.credit,
      }, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Token': localStorage.getItem('Token')
        }
      }).then(({data}) => {
        if (!data)
          showError("后端服务器存在问题")
        if (data.status) {
          showSuccess("修改成功")
          this.pageChange(this.nowPage)
          this.editDialogVisible = false
        } else
          showError(data.message)
      }).catch(showError)
    },

    openGradeDialog(row) {
      this.gradeDialogVisible = true
      this.courseId = row.courseId
      axios.post(API_URL + '/admin/course/grade', {
        courseId: this.courseId
      }, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Token': localStorage.getItem('Token')
        }
      }).then(({data}) => {
        if(!data)
          showError("后端服务器存在问题")
        if(data.status) {
          this.gradeList = data.data
        } else
          showError(data.message)
      }).catch(showError)
    },

    openInsertDialog() {
      this.insertDialogVisible = true
      this.courseId = ''
      this.courseName = ''
      this.credit = null
    },

    insertCourse() {
      if(!this.courseId || !this.courseName || !this.credit) {
        showError("未填写完全")
        return
      }
      axios.post(API_URL + '/admin/course/insert', {
        courseId: this.courseId,
        courseName: this.courseName,
        credit: this.credit
      }, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Token': localStorage.getItem('Token')
        }
      }).then(({data}) => {
        if (!data)
          showError("后端服务器存在问题")
        if (data.status) {
          showSuccess("新增成功")
          this.pageChange(this.nowPage)
          this.insertDialogVisible = false
        } else
          showError(data.message)
      }).catch(showError)
    },

    deleteCourse(row) {
      axios.post(API_URL + '/admin/course/delete', {
        courseId: row.courseId
      }, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Token': localStorage.getItem('Token')
        }
      }).then(({data}) => {
        if(!data)
          showError("后端服务器存在问题")
        if(data.status) {
          showSuccess("删除成功")
          this.pageChange(this.nowPage)
        } else
          showError(data.message)
      }).catch(showError)
    },

    searchCourse() {
      if(this.searchStr == null)
        this.pageChange(this.nowPage)

      axios.post(API_URL + '/admin/course/search', {
        like: this.searchStr
      }, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Token': localStorage.getItem('Token')
        }
      }).then(({data}) => {
        if(!data)
          showError("后端服务器存在问题")
        if(data.status) {
          this.dataList = data.data
          this.total = this.dataList.length
        } else
          showError(data.message)
      }).catch(showError)
    },

    downloadFile() {
      axios.get(API_URL + "/admin/course/file", {
        responseType: 'blob',
        headers: {
          'Token': localStorage.getItem('Token')
        }
      }).then(response => {
        const blob = new Blob([response.data], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });

        const url = window.URL.createObjectURL(blob);

        const a = document.createElement('a');
        a.href = url;
        a.download = '课程信息.xls';
        document.body.appendChild(a);
        a.click();

        // 释放对象URL资源
        window.URL.revokeObjectURL(url);
      })
          .catch(error => {
            showError('下载文件失败');
          });
    },

  }
}
</script>

<template>

  <div style="margin: 5%">

    <el-card shadow="hover" style="margin-left: 15%; margin-right: 15%">
      <div style="text-align: center; font-size: 20px">课程管理</div>

      <div style="margin: 8px 0 8px 0">
        <el-button style="float: left" @click="downloadFile">导出为 Excel</el-button>
        <el-button style="float: right" @click="openInsertDialog">新增</el-button>
        <div style="text-align: -webkit-center">
          <el-input placeholder="请输入内容" v-model="searchStr" class="input-with-select" style="width: 300px">
            <el-button slot="append" icon="el-icon-search" @click="searchCourse"></el-button>
          </el-input>
        </div>
      </div>

      <el-table :data="dataList" stripe style="width: 100%" border>
        <el-table-column prop="courseId" label="课程号" />
        <el-table-column prop="courseName" label="课程名" />
        <el-table-column prop="credit" label="学分" />

        <el-table-column :width="310" label="操作">
          <template v-slot="scope">
            <el-popconfirm title="确定要删除吗？" icon="" style="margin-right: 10px" @confirm="deleteCourse(scope.row)">
              <el-button flex icon="el-icon-delete" slot="reference">删除</el-button>
            </el-popconfirm>
            <el-button icon="el-icon-edit" @click="openEditDialog(scope.row)">编辑</el-button>
            <el-button icon="el-icon-tickets" @click="openGradeDialog(scope.row)">成绩</el-button>
          </template>
        </el-table-column>
      </el-table>

      <br/>
      <div style="text-align: center; font-size: smaller; color: lightgray">共 {{total}} 条数据</div>

      <el-pagination
          style="text-align: center"
          layout="prev, pager, next"
          :hide-on-single-page="true"
          :total="total"
          :current-page="nowPage"
          @current-change="pageChange">
      </el-pagination>

    </el-card>


    <el-dialog title="编辑" :visible.sync="editDialogVisible">
      <div>修改课程信息。</div><br/>
      <el-form id="userInput" label-width="80px">

        <div style="width: 30%; min-width: 300px; margin: 0 auto">
          <el-form-item label="课程号">
            <el-input v-model="courseId" :disabled="true"/>
          </el-form-item>

          <el-form-item label="课程名">
            <el-input v-model="courseName"/>
          </el-form-item>

          <el-form-item label="学分">
            <el-input v-model="credit"/>
          </el-form-item>
        </div>

        <div style="height: 30px"/>
        <el-form-item style="text-align: center">
          <el-button icon="el-icon-check" @click="editCourse" circle/>
        </el-form-item>
      </el-form>
    </el-dialog>


    <el-dialog title="新增" :visible.sync="insertDialogVisible">
      <div>新增课程信息。</div><br/>
      <el-form id="userInput" label-width="80px">

        <div style="width: 30%; min-width: 300px; margin: 0 auto">
          <el-form-item label="课程号">
            <el-input v-model="courseId"/>
          </el-form-item>

          <el-form-item label="课程名">
            <el-input v-model="courseName"/>
          </el-form-item>

          <el-form-item label="学分">
            <el-input v-model="credit"/>
          </el-form-item>
        </div>

        <div style="height: 30px"/>
        <el-form-item style="text-align: center">
          <el-button icon="el-icon-check" @click="insertCourse" circle/>
        </el-form-item>
      </el-form>
    </el-dialog>


    <GradeManagement
        :in-course-id="courseId"
        :in-data-list="gradeList"
        :grade-dialog-visible="gradeDialogVisible"
        @closeDialog="gradeDialogVisible = false"
    />

  </div>
</template>

<style>

</style>