
(function ($) {
    "use strict";

        // projects filters isotop
        $(".product-filters li").on('click', function () {
            
            $(".product-filters li").removeClass("active");
            $(this).addClass("active");

            var selector = $(this).attr('data-filter');

            $(".product-lists").isotope({
                filter: selector,
            });
            
        });
        
        // isotop inner
        $(".product-lists").isotope();

        //Datepicker
        $('[type="date"]').datepicker({
            format: "yyyy-mm-dd",
            autoclose: true,
            startDate: "today",
            clearBtn: true,
            calendarWeeks: true,
            todayHighlight: true,
            language: 'zh-TW'
        });

}(jQuery));