$(function() {
	$(".head").load("head.html");
	$(".foot").load("food.html");
	//短驳
	var chart = echarts.init(document.getElementById("circle"));
	option1 = {
		tooltip: {
			trigger: 'item',
			formatter: "{a} <br/>{b}: {c} ({d}%)"
		},
		legend: {
			orient: 'vertical',
			align: 'left',
			borderColor: '#000',
			left: '60%',
			top: '18%',
			textStyle: {
				padding: [0, 0, 0, 10],
			},
			itemWidth: 16,
			itemHeight: 16,
			data: ['等待调度', '等待发运', '在途运载', '货位引导', '等待回单', '计费确认']
		},
		color: ['#4f9ffe', '#57c6fe', '#e551a3', '#ed8d5d', '#dbdb4b', '#52e87f'],
		series: [{
			name: '火运状态',
			type: 'pie',
			startAngle: 180,
			center: ['30%', '47%'],
			radius: ['70', '101'],
			avoidLabelOverlap: false,
			label: {
				normal: {
					show: false,
					position: 'inner',
					formatter: " {c}"
				},
				emphasis: {
					show: true,
					textStyle: {
						fontSize: '20',
						fontWeight: 'bold'
					}
				}
			},
			labelLine: {
				normal: {
					show: true,
				}
			},
			label: {
				normal: {
					position: 'inner',
					formatter: " {c}"
				}
			},
			data: [{
				value: 50,
				name: '等待调度'
			}, {
				value: 39,
				name: '等待发运'
			}, {
				value: 50,
				name: '在途运载'
			}, {
				value: 80,
				name: '货位引导'
			}, {
				value: 28,
				name: '等待回单'
			}, {
				value: 28,
				name: '计费确认'
			}]
		}]
	};
	chart.setOption(option1);
	//火运
	var mchart = echarts.init(document.getElementById("circle2"));
	option2 = {
		tooltip: {
			trigger: 'item',
			formatter: "{a} <br/>{b}: {c} ({d}%)"
		},
		legend: {
			orient: 'vertical',
			align: 'left',
			borderColor: '#000',
			left: '60%',
			top: '10%',
			textStyle: {
				padding: [0, 0, 0, 10]
			},
			itemWidth: 16,
			itemHeight: 16,
			data: ['等待调度', '等待发运', '在途运载', '货位引导', '等待回单', '计费确认']
		},
		color: ['#4f9ffe', '#57c6fe', '#e551a3', '#ed8d5d', '#dbdb4b', '#52e87f'],
		series: [{
			name: '火运状态',
			type: 'pie',
			startAngle: 180,
			center: ['30%', '47%'],
			radius: ['70', '101'],
			avoidLabelOverlap: false,
			label: {
				normal: {
					show: false,
					position: 'inner',
					formatter: " {c}"
				},
				emphasis: {
					show: true,
					textStyle: {
						fontSize: '20',
						fontWeight: 'bold'
					}
				}
			},
			labelLine: {
				normal: {
					show: true,
				}
			},
			label: {
				normal: {
					position: 'inner',
					formatter: " {c}"
				}
			},
			data: [{
				value: 50,
				name: '等待调度'
			}, {
				value: 39,
				name: '等待发运'
			}, {
				value: 50,
				name: '在途运载'
			}, {
				value: 80,
				name: '货位引导'
			}, {
				value: 28,
				name: '等待回单'
			}, {
				value: 28,
				name: '计费确认'
			}]
		}]
	};
	mchart.setOption(option2);
	//折线
	var mchart = echarts.init(document.getElementById("line"));
	var option3 = {
		tooltip: {
			trigger: 'axis'
		},
		legend: {
			align: 'left',
			show: true,
			right: [150],
			data: ['短驳运单', '火运运单']
		},
		toolbox: {
			show: true
		},
		grid: {
			left: '3%',
			right: '4%',
			top: '10%',
			bottom: '3%',
			containLabel: true
		},
		xAxis: [{
			type: 'category',
			boundaryGap: false,
			data: [' ', '1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月', ' '],
			splitLine: {
				show: true
			}
		}],
		yAxis: [{
			type: 'value',
			name: '短驳运单',
			splitNumber: 14,
			interval: 2000,
			position: 'left',
			axisLabel: {
				show: false
			},
			axisTick: {
				show: false
			}
		}, {
			type: 'value',
			name: '火运运单',
			splitNumber: 14,
			splitLine: {
				show: false
			}, //去除网格线
			interval: 20,
			position: 'right',
			axisLabel: {
				show: false
			},
			axisTick: {
				show: false
			}
		}, ],
		series: [{
			name: '火运运单',
			type: 'line',
			symbol: 'circle',
			symbolSize: 15,
			//                          stack: '车次', 
			yAxisIndex: 1,
			clipOverflow: true,
			itemStyle: {
				normal: {
					color: '#04A9ED',
					borderWidth: 2,
					borderColor: '#fff'
				}
			},
			lineStyle: { //线条的样式
				normal: {
					color: '#04A9ED', //折线颜色
					width: 10,
				}
			},
			data: [null, 40, 80, 120, 100, 140, 140, 119, 160, 100, 140, 100, 120]
		}, {
			name: '短驳运单',
			type: 'line',
			symbol: 'circle',
			symbolSize: 15,
			//                        stack: '数量', 
			yAxisIndex: 0,
			clipOverflow: true,
			itemStyle: {
				normal: {
					color: '#FFAE00',
					borderWidth: 2,
					borderColor: '#fff'
				}
			},
			lineStyle: { //线条的样式
				normal: {
					color: '#FFAE00', //折线颜色
					width: 10,
				}
			},
			data: [null, 8000, 12000, 16200, 13000, 18000, 20000, 17000, 20500, 19000, 21000, 15000, 19000]
		}]
	};
	mchart.setOption(option3);
	window.onresize = mchart.resize;

})