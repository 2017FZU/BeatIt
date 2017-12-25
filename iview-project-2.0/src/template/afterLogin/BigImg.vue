<template>
    <!-- 过渡动画 -->
    <transition name="fade" >
        
        <div class="img-view" @click="bigImg" ref="view" >
            <!-- 遮罩层 -->
            <div class="img-layer" ></div>
              <canvas 
                id="canvas"
                @mousedown="canvasMousedown($event)"
              >
              您的浏览器不支持Canvas画布，无法进行作业批改，请更换Firfox或Chrome浏览器.
              </canvas>
            <div id="tool" >
	              <a  id="getPaint" @click="getPaint">画笔</a>
	              <a id="write" @click="toolwrite">文本</a>
	              <a id="back" @click="back">撤销</a>
                <a id="cancel" @click="cancelShow">结束批改</a>
            </div>
            <textarea id="text"></textarea>
      </div>
    </transition> 
</template>
<script>
export default {
  props: ["imgSrc"],
  data() {
    return {
      canvas: "",
      ctx: "",
      imgObj: "",
      lastImg: [],
      len: 0,
      tool: 0,
      textarea:null,
      inX:0,
      inY:0,
      url: this.imgSrc
     
    };
  }, 
  mounted() {
    
    var clientWidth = document.body.clientWidth; //获取浏览器可见区域宽度
    var clientHeight = document.body.clientHeight; //获取浏览器可见区域高度
    console.log('clientHeight',clientHeight)
    var img_view = document.getElementsByClassName('img-view')[0];
    var img_layer = document.getElementsByClassName('img-layer')[0];
    console.log("view",img_view);
    console.log("layer",img_layer);
    
    img_view.width = clientWidth +'px';
    img_view.height = clientHeight*2+'px';
    img_layer.style.width = clientWidth+'px';
    img_layer.style.height = clientHeight*10+'px';

    this.canvas = document.getElementById("canvas");
    var canvas = this.canvas;
    canvas.width = clientWidth/2; //设置canvas画布宽度
    canvas.height = clientHeight*1.4; //设置canvas画布高度
    this.ctx = canvas.getContext("2d");
   
    var ctx = this.ctx;
    ctx.strokeStyle = 'rgba(255,0,0,0.5)'; 
    ctx.strokeRect(0,0,0,0); 
    
    console.log("top => ", canvas.offsetTop);
    this.imgObj = new Image();
    var imgObj = this.imgObj;
    imgObj.crossOrigin = "Anonymous";
    imgObj.onload = function() {
      ctx.drawImage(this, 0, 0, clientWidth/2, clientHeight*1.2);       //图片与两边的边距
      localStorage.setItem("savedImageData", canvas.toDataURL("image/png"));
    };
    //imgObj.src = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1933053708,660974614&fm=27&gp=0.jpg";
    imgObj.src = this.url;
    var p = document.getElementById('getPaint');
    p.style.backgroundColor = 'skyblue';
    this.lastImg = new Array();
    var lastImg = this.lastImg;
    this.len = 0;
    var that = this;
    this.inX = 0,
    this.inY = 0;
    this.textarea = document.getElementById("text"); 
  },
  methods: {
    bigImg() {
      // 发送事件
      this.$emit("clickit");
    },
    cancelShow: function() {
      this.$emit("cancelShow", false);
      this.save();
    },
    getPaint: function() {
      var p = document.getElementById('getPaint');
      var w = document.getElementById('write');
      p.style.backgroundColor = 'skyblue';
      w.style.backgroundColor = '';
      
      var  textarea = this.textarea;
      if (textarea.value != "") {
        this.draw(this.inX, this.inY);
      }
      textarea.style.left = -1000 + "px";
      textarea.value = "";
      this.tool = 0;
      return;
    },
    toolwrite:function() {
      this.canvas.onmousemove = null;
      this.canvas.onmouseup = null;
      this.tool = 1;
      var p = document.getElementById('getPaint');
      var w = document.getElementById('write');
      p.style.backgroundColor = '';
      w.style.backgroundColor = 'skyblue';
      return;
    },
    // 文本编辑功能
    write:function (ev) {
      console.log("writing...")
      var canvas = this.canvas;
      var textarea = this.textarea;
      textarea.value = "";
      console.log(ev.clientX, ev.clientY);
      
      var scrollTop = document.getElementsByClassName('img-view')[0].scrollTop
      this.inX = ev.clientX-canvas.offsetLeft;
      this.inY = ev.clientY-canvas.offsetTop + scrollTop;
    
      textarea.style.left = ev.clientX + "px"; // 文本框left信息
      textarea.style.top = ev.clientY + "px"; // 文本框top信息
      console.log("textarea=>",textarea.style.left, textarea.style.top);
      this.tool = 2;
      return;
    },
    draw: function (x, y) {
      var textarea = this.textarea;
      var ctx = this.ctx;
      var text = textarea.value;
      var that = this;
      if (text == "") {
        that.tool = 1;
        textarea.style.left = -1000 + "px";
        return;
      }
      that.canvasSave();
      ctx.font = "bold 18px 黑体";
      ctx.fillStyle = "red";
      var len1 = text.length;
      var lineWidth = 0;
      var initHeight = 0;
      var lastSubStrIndex = 0;
      for (var i = 0; i < len1; ++i) {
        lineWidth += ctx.measureText(text[i]).width;
        if (lineWidth > 250) {
          console.log(y + initHeight);
          ctx.fillText(text.substring(lastSubStrIndex, i), x, y + initHeight);
          initHeight += 24;
          lineWidth = 0;
          lastSubStrIndex = i;
        }
        if (i == len1 - 1) {
          ctx.fillText(
            text.substring(lastSubStrIndex, i + 1),
            x,
            y + initHeight
          );
        }
      }
      textarea.style.left = -1000 + "px";
      textarea.value = "";
      return;
    },
    canvasMousedown:function(ev){
      console.log("mouse=>",ev.clientX,ev.clientY);
      switch (this.tool) {
        case 0:
          this.paint(ev);
          console.log("lenxxx",this.len);
          break;
        case 1:
          this.write(ev);
          console.log("wlen",this.len);
          break;
        case 2:
          this.draw(this.inX, this.inY);
          if (this.tool == 1) this.write(ev);
          console.log("dlen",this.len);
          break;
      }
    },
    paint:function (ev) { // onmousedown
      var canvas = this.canvas;
      var ctx = this.ctx;
      console.log("(x, y)=>", ev.clientX,ev.clientY);
      console.log('canvas.offsetLeft=>'+canvas.offsetLeft);
      console.log('canvas.offsetTop=>'+canvas.offsetTop);
      this.canvasSave();
      var scrollTop = document.getElementsByClassName('img-view')[0].scrollTop
      console.log("scrollTop",scrollTop); 
      var x = ev.clientX - canvas.offsetLeft;
      var y = ev.clientY - canvas.offsetTop + scrollTop;

      ctx.beginPath(); // 路径
      ctx.moveTo(x, y); // 移动
      var that = this;
      canvas.onmousemove = function(ev) {
        // 监听鼠标移动
        
        var scrollTop = document.getElementsByClassName('img-view')[0].scrollTop
        var targetX = ev.clientX - canvas.offsetLeft;
        var targetY = ev.clientY - canvas.offsetTop + scrollTop;
        console.log("(tx, ty)", targetX, targetY);
        ctx.lineWidth = 1;
        ctx.lineTo(targetX,targetY);
        ctx.stroke();
      };

      canvas.onmouseup = function(ev) {
        //监听鼠标放开
        canvas.onmousemove = null;
        canvas.onmouseup = null;
      };
    },
    back() {
      console.log("back =>",this.len);
      if (this.len == 0) {
        console.log("empty");
        return;
      }
      console.log("back");
      this.ctx.putImageData(this.lastImg[--this.len], 0, 0);
    },
    save() {
      var img = this.canvas.toDataURL("image/png");
      this.$store.commit("setBasedata",img);
      return;
    },
    canvasSave() {
      this.lastImg[this.len++] = this.ctx.getImageData(
        0,
        0,
        this.canvas.width,
        this.canvas.height
      ); //
      return;
    },
  }
};
</script>
<style scoped>
/*动画*/
.fade-enter-active,
.fade-leave-active {
  transition: all 0.2s linear;
  transform: translate3D(0, 0, 0);
}

.fade-enter,
.fade-leave-active {
  transform: translate3D(100%, 0, 0);
}

/* bigimg */


.img-view{
  position: fixed;
  left: 0;
  top: 0;
  height: 2000px;
  width: 100%;
  background: rgba(0, 0, 0, 0.7);
  top: 0;  
  bottom:0;  
  overflow-y:scroll;  
  overflow-x:hidden;
}


/*遮罩层样式*/
.img-layer{
  position: absolute;
  z-index: 999;
  left: 0;
  top: 0;
  overflow-y:scroll;
  overflow-x:hidden;  
  background: rgba(0, 0, 0, 0.7);
}


#canvas {
  top: 40px;
  left: 26%;
  position: absolute;
  cursor: crosshair;
  z-index: 1111;
  border: none;
}
#tool {
  left: 26%;
  color: #fff;
  height: auto;
  position: fixed;
  z-index: 1222; 
  top:0;
  background: transparent;
}

#getPaint,
#write,
#back,
#cancel {
  cursor: pointer;
  float: left;
  width: 60px;
  height: auto;
  border: 2px solid skyblue;
  text-align: center;
  margin: 4px;
  color: #fff;
  border-radius: 3px;
}

a:hover{
  background: skyblue;
  color: aqua;
}

#text {
  top: -10000px;
  border: 1px solid #fff;
  position: fixed;
  left: 20px;
  background: transparent;
  overflow: hidden;
  resize: none;
  color: red;
  z-index: 3500;
}

#text:focus {
  border-color: skyblue;
}
</style>