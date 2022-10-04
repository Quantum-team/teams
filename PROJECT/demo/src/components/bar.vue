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
		name: 'BarDiv',
		setup() {
			onMounted(() => {
				// let ps = {
				// 	"year": "1998",
				// 	"month": "04"
				// }
				let day = []
				let price = []
				let data = []
				let top5x = []
				let top5y = []
				let ps = new URLSearchParams()
				ps.append('year', '1998')
				ps.append('month', '04')
				axios.post('/api/getNationTotalPrice', ps).then(function(res) {
					console.log('result')
					console.log(res)
					console.log(res.data.results)

					// day=res.data.results.name
					// price=res.data.results.totalprice
					// console.log('day price')
					// console.log(day)
					// console.log(price)
					data = res.data.results
					for (let i = 0; i < data.length; i++) {
						day[i] = data[i].name
						price[i] = data[i].totalprice
					}

					console.log(day)
					console.log(price)

					let qu = [...new Set(day)]
					console.log('去重')
					console.log(qu)

					let cun = [] //开始为空，主要作用:存不重复数据
					let num = [] //统计个数
					for (let i = 0; i < day.length; i++) {
						// ll kk jj 22

						if (cun.indexOf(day[i]) == -1) { //== -1：没有遍历到 第一次进来
							cun.push(day[i])
							let index = cun.indexOf(day[i]) //
							num[index] = Number(price[i]) //
						} else { //遍历到 重复数据
							let index = cun.indexOf(day[i]) //找第一次出现的位置，累加,找原先重复的数据的位置
							num[index] = Number(num[index]) + Number(price[i])
						}

					}

					console.log('分组价格')
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
					console.log('zuizhong')
					console.log(xx)
					console.log(yy)
					let topx = []
					let topy = []
					for (let j = 0; j < 5; j++) {
						topx[j] = xx[j]
						topy[j] = yy[j]
					}
					console.log('top')
					// console.log(topx)
					// console.log(topy)
					top5x=topx
					top5y=topy

					console.log('top5')
					console.log(top5x)
					console.log(top5y)
					// let chong=['ll','kk','jj','ll',22,88,99,99]
					// 			// ll:2
					// 			// kk:1
					// 			// for
					// 			// let one =








					// 			let qu=[...new Set(chong)]
					// 				console.log('去重')
					// 					console.log(qu)

					// set不能重复 map

					//去重 统计个数 




					let bardiv = document.getElementById('bardiv');

					let mychart = echarts.init(bardiv);
					let option = {
						xAxis: {
							type: 'category',
							axisLabel: {

								rotate: 50

							},
							data: top5x
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
							data: top5y

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
