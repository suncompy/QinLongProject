/*
      $(document).ready(function(){ 
        $(".orderNum ul li a").click(function(){
            //切换选中的按钮高亮状态
                    $(this).addClass("active").parent().siblings().children().removeClass("active");
                });
                var chart = echarts.init(document.getElementById("line")); 
                var option1 = {  
                    tooltip : {  
                        trigger: 'axis'  
                    },  
                    legend: {  
                        show:false  
                    },  
                    toolbox: {  
                       show:false
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
                            data : [ ' ','周一','周二','周三','周四','周五','周六','周日','']  ,
                            splitLine:{show:true}
                        }  
                    ],  
                    yAxis : [  
                        {  
                            type : 'value' ,
                            splitNumber:5,
                        }  
                    ],  
                    series : [  
                        {  
                            name:'订单数量',
                            color:['#4C9AEA'],
                            type:'line',
                            symbol:'circle',
                            symbolSize:8,  
                            stack: '数量', 
                            itemStyle : {  
                                normal : {  
                                    lineStyle:{  
                                        color:'#4C9AEA'  
                                    }  
                                }  
                            },   
                            data:[null,10, 34,28 , 35, 16, 30,38]  
                        }  
                    ]  
                };  
                var option2 = {  
                  
                    tooltip : {  
                        trigger: 'axis'  
                    },  
                    legend: {  
                        show:false  
                    },  
                    toolbox: {  
                       show:false
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
                            data : [ ' ','一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月','']  ,
                            splitLine:{show:true},
                            axisLabel :{
                                    interval:0
                                    }
                        }  
                    ],  
                    yAxis : [  
                        {  
                            type : 'value' ,
                            splitNumber:5,
                        }  
                    ],  
                    series : [  
                        {  
                            name:'订单数量',
                            color:['#4C9AEA'],
                            type:'line',
                            symbol:'circle',
                            symbolSize:8,  
                            stack: '数量', 
                            itemStyle : {  
                                normal : {  
                                    lineStyle:{  
                                        color:'#4C9AEA'  
                                    }  
                                }  
                            },   
                            data:[null,10, 34,28 , 35, 16, 30,38,50,34,54,74,58]  
                        }  
                    ]  
                };  
                  
                chart.setOption(option1,true); 
//           按月查看
         $('.week').click(function(){
            chart.setOption(option1,true); 
         });
        
         $('.month').click(function(){
            chart.setOption(option2,true); 
         });
  
            });
     
*/