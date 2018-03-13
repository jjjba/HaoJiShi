/**
 *
 * Created by snhua on 2017/7/15.
 */
/**
 * 获取小程序id和acid、小程序名称name
 */
function loadApplets($acidShopList) {
    $.ajax({
        type: "GET",
        url: $('#baseUrl').attr('href') + 'applets/list',
        async: false,
        success: function (res) {
            $.each(res.data, function (index, item) {
                $acidShopList.append('<option value="' + item.acid + '">' + item.name + '</option>');
            });
            $acidShopList.val(localStorage.acid);
        }
    });
}

/**
 * 获取行业
 */
function loadIndustry($industryList) {
    $.ajax({
        type: "GET",
        url: $('#baseUrl').attr('href') + 'industry/list',
        async: false,
        success: function (res) {
            $industryList.append('<option value="0">请选择</option>');
            $.each(res.data, function (index, item) {
                $industryList.append('<option value="' + item.id + '">' + item.name + '</option>');
            });
            // $acidShopList.val(localStorage.acid);
        }
    });
}


