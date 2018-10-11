//# sourceURL=businessHome.js
/**
 * @title 业务首页表
 * @description 业务首页表
 * @author LiuJiefeng
 * @date 2018-01-16
 */
;
(function($) {
	'use strict';
	$(function() {
		var url = "bussinessCount/trainOrderStatusCount";
		var type = "GET";
		$.fd.ajax({
			url: url,
			type: type,
			//showMsg:true,
			success: function(d) {
				console.log(d);
				var legendData = [];
				for(var i = 0; d.data && i < d.data.length; i++) {
					legendData.push(d.data[i].name);
				}
				option1.legend.data = legendData;
				option1.series[0].data = d.data;
				chart.setOption(option1);
				window.onresize = chart.resize;

			}
		});
		var chart = echarts.init(document.getElementById("circle"));
		var option1 = {
			tooltip: {
				trigger: 'item',
				formatter: "{a} <br/>{b}: {c} ({d}%)"
			},
			legend: {
				orient: 'vertical',
				align: 'left',
				borderColor: '#000',
				left: '80%',
				textStyle: {
					padding: [0, 0, 0, 10]
				},
				itemWidth: 16,
				itemHeight: 16,
				data: ['1', '2', '3', '4', '5', '6', '7']
			},
			//color:['#FFAE00','#43CF77','#04A9ED','#C286FF','#C9D421'],
			series: [{
				name: '火运状态',
				type: 'pie',
				startAngle: 180,
				center: ['40%', '50%'],
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
				data: []
			}]
		};
		//chart.setOption(option1); 

		var url4 = "bussinessCount/bulkOrderStatusCount";
		var type4 = "GET";
		$.fd.ajax({
			url: url4,
			type: type4,
			success: function(d) {
				console.log(d);
				var legendData4 = [];
				for(var i = 0; d.data && i < d.data.length; i++) {
					legendData4.push(d.data[i].name);
				}
				option4.legend.data = legendData4;
				option4.series[0].data = d.data;
				chart4.setOption(option4);
				window.onresize = chart4.resize;

			}
		});
		var chart4 = echarts.init(document.getElementById("circleBulk"));
		var option4 = {
			tooltip: {
				trigger: 'item',
				formatter: "{a} <br/>{b}: {c} ({d}%)"
			},
			legend: {
				orient: 'vertical',
				align: 'left',
				borderColor: '#000',
				left: '80%',
				textStyle: {
					padding: [0, 0, 0, 10]
				},
				itemWidth: 16,
				itemHeight: 16,
				data: ['1', '2', '3', '4', '5', '6', '7']
			},
			//color:['#FFAE00','#43CF77','#04A9ED','#C286FF','#C9D421'],
			series: [{
				name: '短驳状态',
				type: 'pie',
				startAngle: 180,
				center: ['40%', '50%'],
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
				data: []
			}]
		};
		//chart.setOption(option1); 

		$.fd.ajax({
			url: "bussinessCount/bulkTrainSprotCount",
			type: "GET",
			success: function(d) {
				console.log(d);

				/* var dataArr=[];
      	  	    	jQuery.each(d.data, function(i, val) {  
      	  	    		dataArr.push(val);
      	  	        }); 
      	  	    	  option2.series[0].data=dataArr;
      	  	    	  mchart.setOption(option2); 
      	  	    	window.onresize = mchart.resize;*/
				var dataArrBulk = [];
				var dataArrTrain = [];
				$.each(d.data.mapBulk, function(i, val) {
					dataArrBulk.push(val);
				});
				$.each(d.data.mapTrain, function(i, val) {
					dataArrTrain.push(val);
				});
				option2.series[0].data = dataArrBulk;
				option2.series[1].data = dataArrTrain;
				mchart.setOption(option2);
				window.onresize = mchart.resize;
			}
		});
		var mchart = echarts.init(document.getElementById("line"));
		var option2 = {
			tooltip: {
				trigger: 'axis'
			},
			legend: {
				align: 'left',
				show: true,
				right: [150],
				data: ['火运订单数量', '短驳订单数量']
					//data:['订单数量']
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
				data: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19','20','21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31'],
				splitLine: {
					show: true
				}
			}],
			yAxis: [{
				type: 'value',
				name: '火运订单数量',
				splitNumber: 14,
				interval: 2000,
				position: 'left',
			}, {
				type: 'value',
				name: '短驳订单数量',
				splitNumber: 14,
				splitLine: {
					show: false
				}, //去除网格线
				interval: 20,
				position: 'right',
			}],
			series: [{
				name: '短驳订单数量',
				type: 'line',
				symbol: 'circle',
				symbolSize: 15,
				//                          stack: '数量', 
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
				//data:[null,40,80,120,100,140,140,119,160,100,140,100,120] 
				data: []
			}, {
				name: '火运订单数量',
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
				//data:[null,8000,12000,16200,13000,18000,20000,17000,20500,19000,21000,15000,19000]
				data: []
			}]
		};
		//mchart.setOption(option2);   
		$("[name=mapPointFrame]").attr("src", "mapPoint.do");

		$("#btnProjectQuery").click(function(e) {
			e.preventDefault();
			var data = $("#frmProjectDisp").serializeJson();
			projectSurvey(data);
		});

		function projectSurvey(data) {
			$.fd.ajax({
				url: "bussinessCount/projectSurvey",
				data: data,
				type: "GET",
				success: function(d) {
					console.log(d);

					var dataOrderNum = [];
					var dataNetWeight = [];
					$.each(d.data.orderNum, function(i, val) {
						dataOrderNum.push(val);
					});
					$.each(d.data.sendNetWeight, function(i, val) {
						dataNetWeight.push(val);
					});
					option3.series[0].data = dataOrderNum;
					option3.series[1].data = dataNetWeight;
					mchart3.setOption(option3);
					window.onresize = mchart.resize;

				}
			});
		}
		projectSurvey({});
		var mchart3 = echarts.init(document.getElementById("trainLine"));
		var option3 = {
			tooltip: {
				trigger: 'axis'
			},
			legend: {
				align: 'left',
				show: true,
				right: [150],
				data: ['总吨数', '订单总量']
					//data:['订单数量']
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
				data: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19','20','21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31'],
				splitLine: {
					show: true
				}
			}],
			yAxis: [{
				type: 'value',
				name: '总吨数',
				splitNumber: 14,
				interval: 2000,
				position: 'left',
			}, {
				type: 'value',
				name: '订单总量',
				splitNumber: 14,
				splitLine: {
					show: false
				}, //去除网格线
				interval: 20,
				position: 'right',
			}],
			series: [{
				name: '订单总量',
				type: 'line',
				symbol: 'circle',
				symbolSize: 15,
				//                stack: '数量', 
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
				//data:[null,40,80,120,100,140,140,119,160,100,140,100,120] 
				data: []
			}, {
				name: '总吨数',
				type: 'line',
				symbol: 'circle',
				symbolSize: 15,
				//              stack: '数量', 
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
				//data:[null,8000,12000,16200,13000,18000,20000,17000,20500,19000,21000,15000,19000]
				data: []
			}]
		};
		var projectCode = $("#frmProjectDisp [name=projectId]").FdSelect2({
			type: 'projectCode'
		});
	});
}(jQuery));

