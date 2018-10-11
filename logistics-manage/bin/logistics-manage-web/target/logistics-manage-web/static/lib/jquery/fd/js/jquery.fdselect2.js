;
(function($, window, document, undefined) {
    'use strict';
    $(function() {
    	
    	$.fd.select2.types = {
			//角色
    		role:{
				ajax: {
					url : "api/basicdata/types/roles"
				}
			},
			//职务
    		workDuty:{
				ajax: {
					url : "api/basicdata/types/workDuty"
				}
			},
			//维度
			dimension:{
				ajax: {
					url : "api/basicdata/types/dimension"
				},
				minimumResultsForSearch: 0
			},
			//民族
			nationType:{
				ajax: {
					url : "api/basicdata/dims/1001",
				},
				minimumResultsForSearch: 0
			},	
			//学历
			degreeType:{
				ajax: {
					url : "api/basicdata/dims/1003"
				}
			},	
			//党派
			partyType:{
				ajax: {
					url : "api/basicdata/dims/1005"
				}
			},	
			//职业
			jobType:{
				ajax: {
					url : "api/basicdata/dims/1006"
				}
			},	
			//性别
			sexType:{
				ajax: {
					url : "api/basicdata/dims/1002"
				}
			},	
			
			//婚姻
			marriageType:{
				ajax: {
					url : "api/basicdata/dims/1004"
				}
			},
			
			//政治面貌
			partyStyleType:{
				ajax: {
					url : "api/basicdata/dims/1014"
				}
			},

            //案由
            ayType:{
                ajax:{
                    url:"api/basicdata/dims/120001"
                }
            },

			//案件类型
            caseType:{
                ajax:{
                    url:"api/basicdata/dims/120002"
                }
            },

            //法院层级
            courtHierarcgrType:{
                ajax:{
                    url:"api/basicdata/dims/120003"
                }
            },

            //判决类型
            judgementType:{
                ajax:{
                    url:"api/basicdata/dims/120004"
                }
            },

            //判决层级
            caseHierarcgyType:{
                ajax:{
                    url:"api/basicdata/dims/120005"
                }
            },

            //案件结果
            caseCloseMannerType:{
                ajax:{
                    url:"api/basicdata/dims/120006"
                }
            },

            //判罚类型
            criminalJudgeType:{
                ajax:{
                    url:"api/basicdata/dims/120008"
                }
            },

            //金额范围（诈骗/盗窃）
            amountRangeType:{
                ajax:{
                    url:"api/basicdata/dims/120009"
                }
            },

            //伤害后果
            harmResType:{
                ajax:{
                    url:"api/basicdata/dims/120010"
                }
            },

            //事故责任
            accidentRespType:{
                ajax:{
                    url:"api/basicdata/dims/120011"
                }
            },

            //原告性别
            difGenderType:{
                ajax:{
                    url:"api/basicdata/dims/120013"
                }
            },

            //离婚原因
            causeDivorceType:{
                ajax:{
                    url:"api/basicdata/dims/120014"
                }
            },

            //房屋分配
            houseAllocationType:{
                ajax:{
                    url:"api/basicdata/dims/120015"
                }
            },

            //男孩/女孩抚养人
            childRaiserType:{
                ajax:{
                    url:"api/basicdata/dims/120016"
                }
            },

            //所有小孩抚养人_被告原告
            allKidRaiserType:{
                ajax:{
                    url:"api/basicdata/dims/120017"
                }
            },

            //怎么支付
            paymentType:{
                ajax:{
                    url:"api/basicdata/dims/120018"
                }
            },

            //子女年龄段
            kidAgeRangeType:{
                ajax:{
                    url:"api/basicdata/dims/120019"
                }
            },

            //担保方式
            guarantMeansType:{
                ajax:{
                    url:"api/basicdata/dims/120020"
                }
            },

            //还款方式
            repaymentType:{
                ajax:{
                    url:"api/basicdata/dims/120021"
                }
            },

            //借款载体
            loanCarrrierType:{
                ajax:{
                    url:"api/basicdata/dims/120022"
                }
            },

            //事故责任划分
            responsibilityType:{
                ajax:{
                    url:"api/basicdata/dims/120023"
                }
            },

            //案件发生双方
            bothSideType:{
                ajax:{
                    url:"api/basicdata/dims/120024"
                }
            },

            //投保类型
            insuranceType:{
                ajax:{
                    url:"api/basicdata/dims/120025"
                }
            },

            //肇事者与车主关系
            sidesRelationType:{
                ajax:{
                    url:"api/basicdata/dims/120026"
                }
            },

            //赔偿标准
            compensationStandardType:{
                ajax:{
                    url:"api/basicdata/dims/120027"
                }
            },

            //就医范围
            hospitalRangeType:{
                ajax:{
                    url:"api/basicdata/dims/120028"
                }
            },

            //违规情况
            ruleViolationType:{
                ajax:{
                    url:"api/basicdata/dims/120029"
                }
            },

            //多辆机动车主体责任
            mutiVehicleRespType:{
                ajax:{
                    url:"api/basicdata/dims/120030"
                }
            },

            //人物类型
            peopleType:{
                ajax:{
                    url:"api/basicdata/dims/120031"
                }
            },
            //名称类别
            nameType:{
                ajax:{
                    url:"api/basicdata/dims/120032"
                }
            },
            //伤残等级
            disableLevel:{
            	ajax:{
            		url:"api/basicdata/dims/120033"
            	}
            },
            edu:{
            	ajax:{
            		url:"api/basicdata/dims/1003"
            	}
            },
            nation:{
            	ajax:{
            		url:"api/basicdata/dims/1001"
            	}
            },
            sex:{
            	ajax:{
            		url:"api/basicdata/dims/120013"
            	}
            },
            checkUser:{
                ajax:{
                    url:"api/basicdata/types/sampleCheckUser"
                }
            }
    	};
    	
    	$.fd.select2.addTypes = function(types){
    		$.extend($.fd.select2.types, types);
    	};

    	var typeOpts = $.fd.select2.types;
    	
    	var _ajax = {
			dataType : 'json',
			processResults : function(d, params) {
				return {results : d.data};
			},
			data: function(term, page) {
				return {
					text: term.term
				};
			},
			cache : true
		};
		
		
		var opts_all;
    	
    	var defaults = {
			language: "zh-CN",
			allowClear: true,
			placeholder:'',
			minimumResultsForSearch: Infinity
		};
    	
    	var FdSelect2 = function(ele, options){
        	this.$element = ele;
        	this.options = options;
       	};

        
        FdSelect2.prototype = {
        	val: function(value){
        		var $select = this.$element;
        		if(!arguments.length){
        			return $select.val();
        		}
        		
        		if(!value){
        			$select.val(null).trigger('change');
        			return;
        		}
        		
        		if($.isPlainObject(value)){
        			if(!value.id && !value.text){
            			$select.val(null).trigger('change');
            			return;
            		}
        			
        			if(value.id && !value.text){
            			$select.empty().append($('<option selected></option>').val(value.id).text("")).trigger('change');
            			return;
            		}
        			
        			if(value.id && value.text){
            			$select.empty().append($('<option selected></option>').val(value.id).text(value.text)).trigger('change');
            			return;
            		}
        			
        		}else if($.isArray(value)){
        			$select.empty();
        			$.each(value, function(i, d){
        				$select.append(new Option(d.text, d.id, true, true));
					});
        			$select.trigger('change');
        		}
        	},
        	off: function(action){
        		return this.$element.off(action);
        	},
        	on: function(action, callback){
        		return this.$element.on(action, callback);
        	},
        	text: function(){
        		
        		var $select = this.$element;
        		
        		if ($select.select2('data') && $select.select2('data')[0]){
        		    return $select.select2('data')[0].text;
        		}
        		
        		return "";
        	},
        	
        	init: function(){
        		
        		var options = this.options, $select = this.$element;
        		if(!options){
        			return $select.select2(defaults);
        		}
        		// 维度数据
    			var opts_all = $.extend({},defaults, typeOpts[options.type]);
    			opts_all.ajax = $.extend({}, _ajax, typeOpts[options.type]['ajax'], options.ajax);
        		
        		if(options.language){
        			opts_all.itemLanguage = options.language;
        			if(options.language == "en"){
        				opts_all.templateSelection = opts_all.templateSelection || _templateSelectionEn;
        			}else if(options.language == "cn"){
        				opts_all.templateSelection = opts_all.templateSelection || _templateSelectionCn;
        			}
        		} 
        		
        		if(options.allowClear === false){
        			opts_all.allowClear = options.allowClear;	
        		}
        		if(options.closeOnSelect === false){
        			opts_all.closeOnSelect= false;
        		}
        		
        		return $select.select2(opts_all);
        	}
        };
        
        $.fn.FdSelect2 = function(options) {
            var select2 = new FdSelect2(this, options);
            select2.init();
            return select2;
        };
    });
})(jQuery, window, document);
