<template>
  <div>
      <br>
      <h4>{{ text }}</h4>
      <br>
  </div>
</template>

<script>
export default {
    props:{
        text:{
            type:String,
            default:''
        }
    }
}
</script>