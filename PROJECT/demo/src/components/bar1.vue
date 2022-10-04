<template>
	<div id="bardiv" style="width: 600px;height: 500px;">


	</div>
</template>

<script>
	import {
		onMounted
	} from "@vue/runtime-core"
	import * as echarts from 'echarts'
	import * as axios from 'axios'
	export default ({
		name: 'BarDiv1',
		setup() {
			onMounted(() => {
				let ps = new URLSearchParams()
				ps.append('year', '1998')
				ps.append('month', '04')
				axios.post('/api/getNationTotalPrice', ps).then(function(res) {
					console.log('柱状图数据')
					console.log(res)
					console.log(res.data.results)
					let data = []
					data = res.data.results //原始数据
					console.log('柱状图原始数据')
					console.log(data)

					let yuanshix = []
					let yuanshiy = []
					for (let i = 0; i < data.length; i++) {
						yuanshix[i] = data[i].name
						yuanshiy[i] = data[i].totalprice
					}
					console.log('柱状图原始数据XY')
					console.log(yuanshix)
					console.log(yuanshiy)

					let chong = yuanshix

					// let qu=[...new Set(chong)]
					// 	console.log('去重')
					// 		console.log(qu)

					let cun = [] //开始为空，主要作用:存不重复数据
					let num = [] //统计总价
					for (let i = 0; i < chong.length; i++) {
						// ll kk jj 22

						if (cun.indexOf(chong[i]) == -1) { //== -1：没有遍历到 第一次进来
							cun.push(chong[i])
							let index = cun.indexOf(chong[i]) //复位0
							num[index] = Number(yuanshiy[i])
						} else { //遍历到 重复数据
							let index = cun.indexOf(chong[i]) //找第一次出现的位置，累加,找原先重复的数据的位置
							num[index] = Number(num[index]) + Number(yuanshiy[i])
						}

					}

					console.log('去重统计柱状图总价')
					console.log(cun)
					console.log(num)




					let x = cun
					let y = num



					let map = new Map();
					for (let i = 0; i < x.length; i++) {
						map.set(x[i], y[i])
					}
					console.log('map' + map)
					console.log(map)
					// let c=Array.from(map)
					// [...map]
					let c = [...map]
					console.log('数组' + c)

					console.log(c)

					let last = c.sort(test)

					// [[100, 1],[24, 2],[65, 6],[6, 6],[96, 9]]

					function test(a, b) {
						return b[1] - a[1]
					}
					console.log('排序后')
					console.log(last)
					let xx = []
					let yy = []
					for (let i = 0; i < last.length; i++) {
						xx[i] = last[i][0]
						yy[i] = last[i][1]
					}


					let lastxx = []
					let lastyy = []
					for (let i = 0; i < 5; i++) {
						lastxx[i] = xx[i]
						lastyy[i] = yy[i]
					}

					console.log('zuizhong')
					console.log(lastxx)
					console.log(lastyy)







					let kk = '2017-02-12 14:25:36'
					let ll = kk.slice(5, 7)
					console.log('2017-02-12 14:25:36')
					console.log(ll)


					let bardiv = document.getElementById('bardiv');

					let mychart = echarts.init(bardiv);
					let option = {
						xAxis: {
							type: 'category',
							axisLabel: {

								rotate: 50

							},
							data: lastxx
						},
						tooltip: {
							trigger: 'axis'
						},
						yAxis: {
							type: 'value'
						},
						series: [{
							type: 'bar',
							itemStyle: {
								normal: {
									label: {
										show: true,
										position: 'top'

									}
								}
							},
							data: lastyy

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
