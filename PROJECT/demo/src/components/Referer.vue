<template>
	<div id="Referer" style="width: 600px;height: 500px;">
	</div>
</template>

<script>
	import {
		onMounted
	} from "@vue/runtime-core"
	import * as echarts from 'echarts'
	import * as axios from 'axios'
	export default ({
			name: 'RefererName',
			setup() {
				onMounted(() => {
						// let ps = {
						// 	"year": "1998"

						// }
						let ps = new URLSearchParams()
						ps.append('year', '1998')

						axios.post('/api/getRegionTotalPrice', ps).then(function(res) {
								console.log('result饼图')
								console.log(res)
								console.log(res.data.results)
								let Referer = document.getElementById('Referer');

								let mychart = echarts.init(Referer);
								let option = {
									title: {
										text: 'Referer of a Website',
										subtext: 'Fake Data',
										left: 'center'
									},
									tooltip: {
										trigger: 'item'
									},
									legend: {
										orient: 'vertical',
										left: 'left'
									},
									series: [{
										name: 'Access From',
										type: 'pie',
										radius: '50%',
										data: [{
												value: 1048,
												name: 'Search Engine'
											},
											{
												value: 735,
												name: 'Direct'
											},
											{
												value: 580,
												name: 'Email'
											},
											{
												value: 484,
												name: 'Union Ads'
											},
											{
												value: 300,
												name: 'Video Ads'
											}
										],
										emphasis: {
											itemStyle: {
												shadowBlur: 10,
												shadowOffsetX: 0,
												shadowColor: 'rgba(0, 0, 0, 0.5)'
											}
										}
									}]
								}
								mychart.setOption(option);
							})



						})
				}
			})
</script>

<style>
</style>
