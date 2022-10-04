<template>
	<div id="line" style="width: 800px;height: 600px;"></div>
	<h1>111</h1>
</template>

<script>
	import {
		onMounted
	} from "@vue/runtime-core"
	import * as echarts from 'echarts'
	
	export default ({
		name: 'LineDiv',
		setup() {
			onMounted(() => {
				let x=['one','t','y','p','o']
				let y=[2,6,9,1,6]
				
				
		
				let map=new Map();
			for(let i=0;i<x.length;i++){
				map.set(x[i],y[i])
			}
			console.log('map'+map)
			console.log(map)
			// let c=Array.from(map)
			// [...map]
			let c=[...map]
			console.log('数组'+c)
			
				console.log(c)

			let last=c.sort(test)
			
 // [[100, 1],[24, 2],[65, 6],[6, 6],[96, 9]]

			function test(a,b){
				return a[1]-b[1]
			}
			console.log('排序后')
			console.log(last)
			let xx=[]
			let yy=[]
			for(let i=0;i<last.length;i++){
				xx[i]=last[i][0]
				yy[i]=last[i][1]
			}
			console.log('zuizhong')
			console.log(xx)
			console.log(yy)
			
			
			
			// ll:2
			// kk:1
			// for
			// let one =
			
			
			
			
			
			
			
		
		
			// set不能重复 map
			
			//去重 统计个数 
			let chong=['ll','kk','jj','ll',22,88,99,99]
			
			// let qu=[...new Set(chong)]
			// 	console.log('去重')
			// 		console.log(qu)
			console.log(chong.indexOf('ll'))//
			let cun=[]//开始为空，主要作用:存不重复数据
			let num=[] //统计个数
			for(let i=0;i<chong.length;i++){
				// ll kk jj 22
				
				if(cun.indexOf(chong[i])==-1){//== -1：没有遍历到 第一次进来
					cun.push(chong[i])
					let index=cun.indexOf(chong[i])//
					num[index]=1;//
				}
				else{//遍历到 重复数据
					let index=cun.indexOf(chong[i]) //找第一次出现的位置，累加,找原先重复的数据的位置
					num[index]++;
				}
				
			}
			
			console.log('去重统计个数')
			console.log(cun)
			console.log(num)
			// for(let i=0;i<arr.length;i++){
			// 	console.log(a[i][0]+'----'+a[i][1])
			// }
			// let a=[['name1','age1',10],['name2','age2',6],['name3','age3',13]]
			// 	let b=[...a]
			// 	console.log(b)
			// 	a.forEach((item,i)=>{
					
			// 	})
				let linediv = document.getElementById('line');

				let mychart = echarts.init(linediv);
				let option = {
					title: {
						text: 'Stacked Line'
					},
					tooltip: {
						trigger: 'axis'
					},
					legend: {
						data: ['Email', 'Union Ads', 'Video Ads', 'Direct', 'Search Engine']
					},
					grid: {
						left: '3%',
						right: '4%',
						bottom: '3%',
						containLabel: true
					},
					toolbox: {
						feature: {
							saveAsImage: {}
						}
					},
					xAxis: {
						type: 'category',
						axisLabel: {
						
						rotate:50
						
						},
						boundaryGap: false,
						data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
					},
					yAxis: {
						type: 'value'
					},
					series: [{
							name: 'Email',
							type: 'line',
                            itemStyle : { normal: {label : {show: true}}}, 
							data: [120, 132, 101, 134, 90, 230, 210]
						},
						{
							name: 'Union Ads',
							type: 'line',

							data: [0, 182, 191, 234, 290, 330, 310]
						},
						{
							name: 'Video Ads',
							type: 'line',

							data: [0, 232, 201, 154, 190, 330, 410]
						},
						{
							name: 'Direct',
							type: 'line',

							data: [0, 332, 301, 334, 390, 330, 320]
						},
						{
							name: 'Search Engine',
							type: 'line',

							data: [0, 932, 901, 934, 1290, 1330, 1320]
						}
					]
				};
				mychart.setOption(option);
			})
		}
	})
</script>
