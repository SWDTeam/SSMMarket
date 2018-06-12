<div class="collapse navbar-collapse">
    <ul class="nav navbar-nav navbar-right">
        <li>
            <a href="index_products.jsp" class="dropdown-toggle" data-toggle="dropdown"><i class="ti-panel"></i>
                <p>View All Products</p>
            </a>
        </li>
        <li>
            <a href="ShowCategoryController" class="dropdown-toggle" data-toggle="dropdown"><i class="ti-panel"></i>
                <p>Add Product</p>
            </a>
        </li>
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="ti-settings"></i>
                <p>${sessionScope.INFO.username}</p>
                <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
                <li><a href="profile.jsp">Profile</a></li>
                <li><a href="change_password.jsp">Change Password</a></li>
                <li><a href="LogoutController">Log out</a></li>
            </ul>
        </li>
    </ul>
</div>