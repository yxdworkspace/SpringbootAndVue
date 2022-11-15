<template>
<!--头部-->
  <nav>
  <Header/>
  </nav>
<!--主体-->
  <div style="display:flex;">
<!--侧边栏-->
    <Aside/>
<!--内容区域-->
    <router-view style="flex: 1"/>
  </div>
</template>


<script>
import Header from "@/components/Header";
import Aside from "@/components/Aside";
  export default {
    name: 'App',
    components: {
        Header,
        Aside
    }
  }
</script>

<style lang="scss">

</style>
