
      $(document).ready(function(){ 
      var mchart = echarts.init(document.getElementById("line"));
      var option = {  
                    tooltip : {  
                        trigger: 'axis'  
                    },  
                    legend: { 
                    	 align:'left',
                        show:true,
                        right:[150],
                        data:['订单数量','订单车次']
                    },  
                    toolbox: {  
                       show:true
                    },  
                    grid: {  
                        left: '3%',  
                        right: '4%',  
                        top: '10%', 
                        bottom:'3%' ,  
                        containLabel: true  
                    },  
                    xAxis : [  
                        {  
                            type : 'category',  
                            boundaryGap : false,  
                            data : [ ' ','1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月',' ']  ,
                            splitLine:{show:true}
                        }  
                    ],  
                    yAxis : [  
                        {  
                            type : 'value' ,
                            name:'订单数量',
                            splitNumber:14,
                            interval:2000,
                            position:'left',
                        } ,
                        {  
                            type : 'value' ,
                            name:'订单车次',
                            splitNumber:14,
                            splitLine:{show: false},//去除网格线
                            interval:20,
                            position:'right',
                        } ,
                    ],  
                    series : [  
                        {  
                            name:'订单车次',
                            type:'line',
                            symbol:'circle',
                            symbolSize:15,  
//                          stack: '车次', 
														yAxisIndex:1,
            								clipOverflow:true,
                            itemStyle : {  
                                normal : {  
                                color:'#04A9ED',
                             		borderWidth:2,
                             		borderColor:'#fff'
                                }  
                            },
                            lineStyle:{  //线条的样式
                                 normal:{
                                 color:'#04A9ED',  //折线颜色
                                 width:10,
                              }
                     				},
                            data:[null,40,80,120,100,140,140,119,160,100,140,100,120]  
                        } ,
                        {
                        	name:'订单数量',
                        	type:'line',
                        	symbol:'circle',
                          symbolSize:15,  
//                        stack: '数量', 
													yAxisIndex:0,
													clipOverflow:true,
                        	itemStyle : {  
                                normal : { 
                                color:'#FFAE00',
                             		borderWidth:2,
                             		borderColor:'#fff'
                                }  
                            }, 
                          lineStyle:{  //线条的样式
                                 normal:{
                                 color:'#FFAE00',  //折线颜色
                                 width:10,
                              }
                     				}, 
                        	data:[null,8000,12000,16200,13000,18000,20000,17000,20500,19000,21000,15000,19000]
                        }
                    ]  
                };  
      mchart.setOption(option);
      
      
      
      
      
      
      
      });
     
