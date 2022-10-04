<template>

	<Linediv></Linediv>
	<Bardiv1></Bardiv1>
    <RadarDiv></RadarDiv>
	<Referer></Referer>
</template>

<script>
import Linediv from './components/linechart.vue'
import Bardiv1 from './components/bar1.vue'
import RadarDiv from './components/Radar.vue'
import Referer from './components/Referer.vue'

export default {
  name: 'App',
  components: {
   Linediv,
   Bardiv1,
   RadarDiv,
   Referer
  }
}
</script>

