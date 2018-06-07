<script src="admin_page/js/jquery-1.10.2.js"></script>

<script src="admin_page/bootstrap/js/jquery.form.js"></script>

<script src="admin_page/js/bootstrap.min.js"></script>
<script src="admin_page/js/paper-dashboard.js"></script>
<script src="js/validate.js"></script>

<!--active menu-->
<script type="text/javascript">
    $(document).ready(function () {
        var pgurl = window.location.href.substr(window.location.href.lastIndexOf("/") + 1);
//    console.log(pgurl);
        $(".M--menu a").each(function () {
            if ($(this).attr("href") === pgurl || $(this).attr("href") == '')
                $(this).parent().addClass('active').siblings().removeClass('active');
        });
    });
</script>
