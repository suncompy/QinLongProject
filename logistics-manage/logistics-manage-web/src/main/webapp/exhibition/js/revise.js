$(function(){
		/* 声明一个数组用来存input值 */
        var array = ["", "",""];
        /*方法调用*/
        inputFn("input:first", 0);
        inputFn("input:eq(1)", 1);
        inputFn("input:eq(2)", 2);
        /* 方法封装 */
        /*键盘弹起时实时生效*/
        function inputFn(ele, num) {
            $(ele).keyup(function(){
                var val = $(this).val().trim();
                console.log(val);
                array[num] = val;
                inputArray();
            });
        }
        /*遍历数组，判断input值是否存在于数组中*/
        function inputArray() {
            for (var i = 0, arrayLen = array.length; i < arrayLen; i++) {
                //  如果值不在数组中 就返回 并 去掉颜色
                if (array[i] == "" ||　array[i] == null) {
                    $("button").removeClass("submit");
                    console.log(array[i] + "无法提交" + i)
                    return;
                }
            }
            
            $("button").addClass("submit");
        }
})