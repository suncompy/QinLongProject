$(document).ready(function() {
	var chart = echarts.init(document.getElementById("shortChart"));
	option1 = {
		tooltip: {
			trigger: 'item',
			formatter: "{a} <br/>{b}: {c} ({d}%)"
		},
		legend: {
			orient: 'vertical',
			align: 'left',
			x: 'right',
			padding: [30, 80, 20, 0],
			data: ['已完成', '等待调度', '在途运载', '等待发运', '等待回单'],
			itemWidth: 16,
			itemHeight: 16,
			textStyle: {
				padding: [0, 0, 0, 10]
			}
		},

		color: ['#FFAE00', '#43CF77', '#04A9ED', '#C286FF', '#C9D421'],
		series: [{
			name: '火运状态',
			type: 'pie',
			radius: ['60%', '90%'],
			center: ['178', '120'],
			avoidLabelOverlap: false,
			 startAngle:180,
			label: {
				normal: {
					show: false,
					position: 'center'
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
					show: false
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
				name: '已完成'
			}, {
				value: 39,
				name: '等待调度'
			}, {
				value: 50,
				name: '在途运载'
			}, {
				value: 80,
				name: '等待发运'
			}, {
				value: 28,
				name: '等待回单'
			}]
		}]
	};
	chart.setOption(option1);
	//火运
	var charts = echarts.init(document.getElementById("trainChart"));
	option2 = {
		tooltip: {
			trigger: 'item',
			formatter: "{a} <br/>{b}: {c} ({d}%)"
		},
		legend: {
			orient: 'vertical',
			align: 'left',
			x: 'right',
			padding: [30, 80, 20, 0],
			itemWidth: 16,
			itemHeight: 16,
			textStyle: {
				padding: [0, 0, 0, 10]
			},
			data: ['已完成', '等待调度', '在途运载', '等待发运', '等待回单']
			
		},
		color: ['#FFAE00', '#43CF77', '#04A9ED', '#C286FF', '#C9D421'],
		series: [{
			name: '火运状态',
			type: 'pie',
			radius: ['60%', '90%'],
			center: ['178', '120'],
			avoidLabelOverlap: false,
			 startAngle:180,
			label: {
				normal: {
					show: false,
					position: 'center'
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
					show: false
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
				name: '已完成'
			}, {
				value: 39,
				name: '等待调度'
			}, {
				value: 50,
				name: '在途运载'
			}, {
				value: 80,
				name: '等待发运'
			}, {
				value: 28,
				name: '等待回单'
			}]
		}]
	};
	charts.setOption(option2);

});