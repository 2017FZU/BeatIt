<template>
  <div class="container" >
      <canvas id="canvas" width="600" height="600" ></canvas>
      <textarea id="text" style="width: 100px;height: 100px;color: red; z-index: 1000;"></textarea>
      <button id="ibtn" @click="input">完成</button>
      <button id="back" @click="back">撤销{{len}}</button>
      <img id="img" src="" width="400" height="400" />
  </div>
</template>


<script>
export default {
  data() {
    return {
      canvas: "",
      ctx: "",
      imgObj: "",
      lastImg: [],
      len: 0
    };
  },
  mounted() {
    this.canvas = document.getElementById("canvas");
    //console.log(this.canvas);
    var canvas = this.canvas;
    this.ctx = canvas.getContext("2d");
    var ctx = this.ctx;
    ctx.strokeStyle = "rgba(255,0,0,0.5)";
    this.imgObj = new Image();
    var imgObj = this.imgObj;
    imgObj.src = "/src/images/test.jpg";
    imgObj.onload = function() {
      ctx.drawImage(this, 0, 0, 650, 600);
    };
    this.lastImg = new Array();
    var lastImg = this.lastImg;
    this.len = 0;
    var that = this;
    canvas.onmousedown = function(ev) {
      var container = document.getElementsByClassName("container")[0];
      console.log(container);
      var subLeft = container.clientLeft;
      var subTop = container.clientTop;
      console.log("container_left =>" + subLeft);
      console.log("container_Top =>" + subTop);

      lastImg[that.len++] = ctx.getImageData(0, 0, canvas.width, canvas.height);
      console.log("len=>",that.len);
      console.log("left = >" + this.offsetLeft);
      console.log("top = >" + this.offsetTop);
      console.log("cx = >" + ev.clientX);
      console.log("cy = >" + ev.clientY);

      var x = ev.clientX - this.offsetLeft - subLeft;
      var y = ev.clientY - this.offsetTop - subTop;
      ctx.beginPath();
      ctx.moveTo(x, y);
      canvas.onmousemove = function(ev) {
        var targetX = ev.clientX - this.offsetLeft - subLeft;
        var targetY = ev.clientY - this.offsetTop - subTop;
        ctx.lineWidth = 1;
        ctx.lineTo(targetX, targetY);
        ctx.stroke();
      };
      window.onmouseup = function(ev) {
        canvas.onmousemove = null;
        canvas.onmouseup = null;
      };
    };
  },
  methods: {
    back() {
      if (this.len == 0) {
        console.log("empty");
        return;
      }
      console.log("back");
      this.ctx.putImageData(this.lastImg[--this.len], 0, 0);
    },
    input() {
      var ibtn = document.getElementById("text");
      console.log("success", ibtn.value);
      this.draw(ibtn.value);
      return "success";
    },
    draw: function(text) {
      this.lastImg[this.len++] = this.ctx.getImageData(
        0,
        0,
        this.canvas.width,
        this.canvas.height
      );

      this.ctx.font = "italic 35px 黑体";
      this.ctx.fillStyle = "Red";
      this.ctx.fillText(text, 130, 160, 200);
      this.ctx.font = "bold 35px 宋体";
      return;
    }
  }
};
</script>



<style>
.container {
  margin-left: -100px;
  margin-top: -10px;
  padding-left: 100px;
  height: 600px;
}

#canvas {
  border: 3px solid red;
}
</style>

