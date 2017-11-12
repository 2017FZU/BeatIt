<template>
  <div class="container">
    <div class="publish">
      <p><img src="../../images/文件夹.png" alt="">高等数学</p>
      <img src="../../images/上传.png" alt="">
    </div>
    <div class="container-main">
      <div class="row">
        <div class="data">
          <img src="../../images/word.png" alt="">
          <p>习题一</p>
        </div>
        <div class="data">
          <img src="../../images/word.png" alt="">
          <p>习题一</p>
        </div>
        <div class="data">
          <img src="../../images/word.png" alt="">
          <p>习题一</p>
        </div>
      </div>
      <div class="row">
        <div class="data">
          <img src="../../images/ppt.png" alt="">
          <p>习题一</p>
        </div>
        <div class="data">
          <img src="../../images/ppt.png" alt="">
          <p>习题一</p>
        </div>
        <div class="data">
          <img src="../../images/ppt.png" alt="">
          <p>习题一</p>
        </div>
      </div>
      <div class="row">
        <div class="data">
          <img src="../../images/pdf.png" alt="">
          <p>习题一</p>
        </div>
        <div class="data">
          <img src="../../images/pdf.png" alt="">
          <p>习题一</p>
        </div>
        <div class="data">
          <img src="../../images/pdf.png" alt="">
          <p>习题一</p>
        </div>
      </div>
    </div>
  </div>
</template>
<script>

</script>

<style scoped>
.container {
  margin-left: -100px;
  margin-top: -10px;
  padding: 0;
  height: 600px;
}
.publish > p{
  font-family: PingFangSC-Medium;
  font-size: 30px;
  color: #4A4A4A;
  display: flex;
  align-items: center;
  margin-left: 440px;
}
.publish > p > img{
  position: relative;
  margin-right: 5px;
}
.publish > img{
  position: relative;
  left: 1000px;
}
.container-main{
  padding: 15px 40px;
}
.container-main .row{
  display: flex;
}
.container-main .row .data{
  margin: 30px 20px;  
}
.container-main .row .data p{
  text-align: center;
  font-family: PingFangSC-Regular;
  font-size: 25px;
  color: #4A4A4A;
}
</style>