<template>
  <div class="container">
    <big-img class="box" @cancelShow="cancelShow" v-if="showImg" :imgSrc="imgSrc" :showImg="showImg" @onmousewheel="bigimg(this)"></big-img>
    <div class="select">
      <img src="../../images/选择作业图标.png" alt="">
      <Select @on-change="change" v-model="homework" style="width:200px">
        <Option  v-for="item in homeworkList" :value="item.value" :key="item.value">{{ item.label}}</Option>
      </Select>
      <Modal v-model="modal1" title="作业批改" @on-ok="ok" @on-cancel="cancel" >
        
          <p id="correctInform">
            <label for="">姓名</label>
            <Input disabled class="inputFirst" v-model="Sname"></Input>
            <label for="">学号</label>
            <Input disabled class="inputFirst" v-model="Sno"></Input>
          </p>
          <!-- <p style="height:200px; width:100%; display:block;"> -->
              <Carousel v-model="value1" style="height:600px;width:488px;margin-top:20px">
                  <CarouselItem v-for="item in homeworkImg" :value="item.value" :key="item.value">
                    {{item.value}}
                    <img v-bind:id="item.value" class="demo-carousel" v-bind:src="item.label" @click="clickImg($event)">
                  </CarouselItem>
              </Carousel>
          <p class="correctInform">
            <label for="">得星数</label>
            <Rate v-model="value" style="margin-left:130px;" @on-change="rateChange"></Rate>
          </p>
          <p class="correctInform">
            <Checkbox v-model="single">优秀作业</Checkbox>

          </p>
          <p class="correctInform">
            <label for="">作业评语</label>
            <Input type="textarea" :rows="4" v-model="Comment"></Input>
          </p>
      </Modal>

      <Modal v-model="modal2" title="作业批改查看" >
          <p id="correctInform">
            <label for="">姓名</label>
            <Input disabled class="inputFirst" v-model="Sname"></Input>
            <label for="">学号</label>
            <Input disabled class="inputFirst" v-model="Sno"></Input>
          </p>
          <!-- <p style="height:200px; width:100%; display:block;"> -->
              <Carousel v-model="value1" style="height:600px;width:488px;margin-top:20px">
                  <CarouselItem v-for="item in homeworkImgAfter" :value="item.value" :key="item.value">
                    <img class="demo-carousel" v-bind:src="item.label">
                  </CarouselItem>
              </Carousel>
              <big-img  @cancelShow="cancelShow" v-if="showImg"  :imgSrc="imgSrc" :showImg="showImg"></big-img>
          <p class="correctInform">
            <label for="">得星数</label>
            <Rate disabled v-model="valueAfter" style="margin-left:130px;"></Rate>
            <p class="correctInform">
            <Checkbox  disabled v-model="double">优秀作业</Checkbox>
          </p>
      </Modal>

    </div>
    <div class="container-main">
      <Table  @on-row-click="click" highlight-row ref="currentRowTable" height="500" width="950"  size="large" 
      stripe align="center" :columns="columns1" :data="data1" no-data-text="当前作业没有提交记录"></Table>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import BigImg from "../afterLogin/BigImg";
export default {
  data() {
    return {
      sign:2,
      double: false,
      Sid: 0,
      inx: 0,
      showImg: false,
      imgSrc: "",
      Wno: "",
      single: false,
      Comment: "",
      Sname: "",
      Sno: 0,
      value: 1,
      valueAfter: 0,
      value1: 0,
      modal1: false,
      modal2: false,
      homework: 0,
      passWid: -1,
      student: 0,
      testHomework: [],
      homeworkImg: [],
      homeworkImgAfter: [],
      testStu: [],
      homeworkList: [],
      columns1: [
        {
          title: "作业",
          key: "title",
          align: "center",
          width: 200
        },
        {
          title: "姓名",
          key: "sname",
          align: "center",
          width: 250
        },
        {
          title: "学号",
          key: "sno",
          align: "center",
          width: 250
        },
        {
          title: "批改情况",
          key: "score",
          align: "center",
          width: 248,
          render: (h, params) => {
            if (params.row.score == 0) {
              return h(
                "Button",
                {
                  props: {
                    type: "primary"
                  },
                  on: {
                    click: () => {
                      this.modal1 = true;
                    }
                  }
                },
                "未批改"
              );
            } else {
              return h(
                "Button",
                {
                  props: {
                    type: "success"
                  },
                  on: {
                    click: () => {
                      this.modal2 = true;
                    }
                  }
                },
                "已批改"
              );
            }
          }
        }
      ],
      data1: []
    };
  },
  components: {
    "big-img": BigImg
  },
  mounted() {
    console.log("begin");
    if (module.hot) {
      module.hot.accept();
    }
    var passIndex;
    const that = this;
    that.testHomework = [];
    that.testStu = [];
    that.data1 = [];
    console.log("begin1");
    if (that.$route.query.index >= 0) {
      passIndex = that.$route.query.index;
    } else {
      passIndex = that.homework;
    }

    console.log("begin2");
    console.log(passIndex);
    axios
      .post(
        "http://111.231.190.23/web/getHomeWorkList?cid=" +
          that.$store.getters.getCid
      )
      .then(function(res) {
        that.testHomework = res.data.data.homeWorkList;
        for (var i = 0; i < that.testHomework.length; ++i) {
          var tmp = { wid: 0, value: 0, label: "", content: "" };
          tmp.wid = that.testHomework[i].wid;
          tmp.value = i;
          tmp.label = that.testHomework[i].title;
          tmp.content = that.testHomework[i].content;
          that.homeworkList.push(tmp);
        }
        console.log("middle", passIndex);
        axios
          .post(
            "http://111.231.190.23/web/getStudentHomeWork?wid=" +
              that.homeworkList[passIndex].wid
          )
          .then(function(res) {
            console.log("success");
            that.testStu = res.data.data.work;
            console.log("stu ", that.testStu);
            for (var i = 0; i < that.testStu.length; ++i) {
              var tmp = {
                title: "",
                sno: 0,
                sid: 0,
                isview: 0,
                sname: "",
                score: 0,
                working: []
              };
              tmp.title = that.testStu[i].title;
              tmp.sno = that.testStu[i].sno;
              tmp.sid = that.testStu[i].sid;
              tmp.isview = that.testStu[i].isview;
              tmp.score = that.testStu[i].score;
              tmp.sname = that.testStu[i].sname;
              tmp.working = that.testStu[i].workimg;
              that.data1.push(tmp);
            }
            console.log("begin3");
            that.homework = passIndex;
            console.log("end");
          });
      });
  },
  methods: {
    bigimg: function(i) {
      console.log('bigimg已执行---');
      var zoom = parseInt(i.style.zoom, 10) || 100;
      zoom += event.wheelDelta / 12;
      if (zoom > 0) i.style.zoom = zoom + "%";
      return false;
    },
    show() {
      modal1 = true;
    },
    change: function(res) {
      this.inx = res;
      var that = this;
      console.log("res=>", res);
      console.log("w => ", that.homework);
      that.testStu = [];
      that.data1 = [];
      if (that.homeworkList.length == 0) return;
      console.log("list", that.homeworkList);
      var ary = that.homeworkList;
      console.log("show => ", ary.length);

      console.log("index =>" + that.homework);
      console.log("now =>" + that.homeworkList[that.homework].wid);
      return axios
        .post(
          "http://111.231.190.23/web/getStudentHomeWork?wid=" +
            that.homeworkList[that.homework].wid
        )
        .then(function(res) {
          console.log(that.homeworkList[that.homework].wid);
          that.testStu = res.data.data.work;
          for (var i = 0; i < that.testStu.length; ++i) {
            var tmp = {
              title: "",
              sno: 0,
              sid: 0,
              isview: 0,
              sname: "",
              score: 0,
              working: []
            };
            tmp.title = that.testStu[i].title;
            tmp.sno = that.testStu[i].sno;
            tmp.sid = that.testStu[i].sid;
            tmp.isview = that.testStu[i].isview;
            tmp.score = that.testStu[i].score;
            tmp.sname = that.testStu[i].sname;
            tmp.working = that.testStu[i].workimg;
            that.data1.push(tmp);
          }
        });
    },
    rateChange: function(res) {
      this.value = res;
    },
    ok() {
      var that = this;
      that.Wno = that.homeworkList[that.homework].wid;
      axios.get(
        "http://111.231.190.23/web/CommentHomeWork?sid=" +
          that.Sid +
          "&wid=" +
          that.Wno +
          "&score=" +
          that.value +
          "&comment=" +
          that.Comment +
          "&url=" +
          that.imgSrc +
          "&Base64Data=" +
          that.$store.getters.getBasedata+
          "&isshow="+
          that.single
          
      );
      this.$Message.success("批改成功!");
      location.reload();
    },
    cancel() {},
    click: function(res) {
      this.value = 1;
      this.single = false;
      this.Comment = "";
      this.homeworkImg = [];
      this.homeworkImgAfter = [];
      this.Sid = res.sid;
      this.Sname = res.sname;
      this.Sno = res.sno;
      this.double = res.isview;
      this.valueAfter = res.score;
      for (var k = 0; k < res.working.length; k++) {
        var tmp = { value: 0, label: "" };
        tmp.value = k;
        tmp.label = res.working[k].url;
        this.homeworkImg.push(tmp);
        this.homeworkImgAfter.push(tmp);
      }
    },
    clickImg(e) {
      this.showImg = true;
      // 获取当前图片地址
      this.sign = e.currentTarget.id; //获取数组索引值
      this.imgSrc = e.currentTarget.src;
    },
    cancelShow(res) {
      // this.homeworkImg[]
      this.homeworkImgAfter[this.sign].label = this.$store.getters.getBasedata;
      alert(this.homeworkImgAfter[this.sign].label);
      this.showImg = res;
    },
    viewImg() {
      this.showImg = false;
    }
  }
};
</script>

<style scoped>
.container {
  margin-left: -100px;
  margin-top: -10px;
  padding-left: 100px;
  height: 600px;
}
.demo-carousel {
  width: 488px;
  height: 600px;
  display: block;
}
.select {
  padding: 15px 40px;
  margin-left: 360px;
  display: flex;
  align-items: center;
}

.select img {
  margin-left: 15px;
  margin-right: 15px;
}

.container-main {
  padding: 15px 40px;
}

#correctInform {
  display: flex;
  align-items: center;
}
#correctInform label {
  width: 90px;
  font-size: 16px;
  font-weight: 600;
  margin: 0 5px 0 5px;
}
.correctInform label {
  width: 180px;
  font-size: 16px;
  font-weight: 600;
  margin-left: 5px;
}

.correctInform inputfirst {
  color: black;
}
.box{
  z-index: 1200;
  width: 100%;
  height: 1000px;
  top: 0;  
  bottom:0;  
  position:absolute;  
  overflow-y:scroll;  
  overflow-x:hidden;
}
</style>