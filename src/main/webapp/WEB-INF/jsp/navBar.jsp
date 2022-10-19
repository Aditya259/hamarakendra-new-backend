<aside id="layout-menu"
   class="layout-menu menu-vertical menu bg-menu-theme">
   <div class="app-brand demo">
      <a href="javascript:void(0);"
         class="layout-menu-toggle menu-link text-large ms-auto d-block d-xl-none">
      <i class="bx bx-chevron-left bx-sm align-middle"></i>
      </a>
   </div>
   <div class="menu-inner-shadow"></div>
   <ul class="menu-inner py-1">
      <!-- Dashboard -->
      <li class="menu-item active">
         <a href="dashboard?hamaraKendra=${authToken}"
            class="menu-link">
            <i
               class="menu-icon tf-icons bx bx-home-circle"></i>
            <div data-i18n="Analytics">Dashboard</div>
         </a>
      </li>
      <li class="menu-header small text-uppercase"><span
         class="menu-header-text">Services</span></li>
      <li class="menu-item">
         <a href="javascript:void(0);"
            class="menu-link menu-toggle">
            <i
               class="menu-icon tf-icons bx bx-dock-top"></i>
            <div data-i18n="Account Settings">Requested Services</div>
         </a>
         <ul class="menu-sub">
            <li class="menu-item">
               <%   
                  String role = (String) request.getAttribute("role");
                  int id = 1;
                  if(role!=null && role.equals("admin")){
                  	id = 0;
                  }
                  %> 
               <a href="loadListOfServices?hamaraKendra=${authToken}" class="menu-link">
                  <div data-i18n="Account">List Of Requested Services</div>
               </a>
               
                <a href="loadPendingListOfServices?hamaraKendra=${authToken}" class="menu-link">
                  <div data-i18n="Account">List Of Pending Services</div>
               </a>
               
               <a href="loadCompletedListOfServices?hamaraKendra=${authToken}" class="menu-link">
                  <div data-i18n="Account">List Of Completed Services</div>
               </a>
            </li>
         </ul>
      </li>
      <%  if(role.equals("admin")){%>
      <li class="menu-header small text-uppercase"><span
         class="menu-header-text">Employee</span></li>
      <li class="menu-item">
         <a href="javascript:void(0);"
            class="menu-link menu-toggle">
            <i
               class="menu-icon tf-icons bx bx-dock-top"></i>
            <div data-i18n="Account Settings">Employee</div>
         </a>
         <ul class="menu-sub">
            <li class="menu-item">
               <a href="loadListOfEmployees?hamaraKendra=${authToken}"
                  class="menu-link">
                  <div data-i18n="Account">List Of Employee</div>
               </a>
            </li>
            <li class="menu-item">
               <a href="createEmployee?hamaraKendra=${authToken}"
                  class="menu-link">
                  <div data-i18n="Account">Add Employee</div>
               </a>
            </li>
         </ul>
      </li>
      <li class="menu-header small text-uppercase"><span
         class="menu-header-text">Employee Service Mapping</span></li>
      <li class="menu-item">
         <a href="javascript:void(0);"
            class="menu-link menu-toggle">
            <i
               class="menu-icon tf-icons bx bx-dock-top"></i>
            <div data-i18n="Account Settings">Service Allocation</div>
         </a>
         <ul class="menu-sub">
            <li class="menu-item">
            <li class="menu-item">
               <a href="employeeServiceMapping?hamaraKendra=${authToken}"
                  class="menu-link">
                  <div data-i18n="Account">Employee To Service Mapping</div>
               </a>
            </li>
         </ul>
      </li>
      <li class="menu-header small text-uppercase"><span
         class="menu-header-text">Service Pricing Update</span></li>
      <li class="menu-item">
         <a href="javascript:void(0);"
            class="menu-link menu-toggle">
            <i
               class="menu-icon tf-icons bx bx-dock-top"></i>
            <div data-i18n="Account Settings">Service Fees Update / Delete</div>
         </a>
         <ul class="menu-sub">
            <li class="menu-item">
            <li class="menu-item">
               <a href="updateServicePricing?hamaraKendra=${authToken}"
                  class="menu-link">
                  <div data-i18n="Account">Update / Delete Service Pricing</div>
               </a>
            </li>
         </ul>
      </li>
      <%} %>
      <%if(!role.equals("admin")){ %>
      <li class="menu-header small text-uppercase"><span
         class="menu-header-text">Service Booking With Customer</span></li>
      <li class="menu-item">
         <a href="javascript:void(0);"
            class="menu-link menu-toggle">
            <i
               class="menu-icon tf-icons bx bx-dock-top"></i>
            <div data-i18n="Account Settings">Book Service </div>
         </a>
         <ul class="menu-sub">
            <li class="menu-item">
               <a href="ServiceBookingWithCustDetails?hamaraKendra=${authToken}"
                  class="menu-link">
                  <div data-i18n="Account">Book Now</div>
               </a>
            </li>
         </ul>
      </li>
      <%} %>
   </ul>
</aside>
<!-- Layout wrapper -->
<div class="layout-wrapper layout-content-navbar">
<div class="layout-container">
<!-- Menu -->
<!-- / Menu -->
<!-- Layout container -->
<div class="layout-page">
<!-- Navbar -->
<nav
   class="layout-navbar container-xxl navbar navbar-expand-xl navbar-detached align-items-center bg-navbar-theme"
   id="layout-navbar">
   <div
      class="layout-menu-toggle navbar-nav align-items-xl-center me-3 me-xl-0 d-xl-none">
      <a class="nav-item nav-link px-0 me-xl-4" href="javascript:void(0)">
      <i class="bx bx-menu bx-sm"></i>
      </a>
   </div>
   <div class="navbar-nav-right d-flex align-items-center"
      id="navbar-collapse">
      <!-- Search -->
      <div class="navbar-nav align-items-center">
         <div class="nav-item d-flex align-items-center">
            <i class="bx bx-search fs-4 lh-0"></i> <input type="text"
               class="form-control border-0 shadow-none"
               placeholder="Search..." aria-label="Search..." />
         </div>
      </div>
      <!-- /Search -->
      <ul class="navbar-nav flex-row align-items-center ms-auto">
         <!-- Place this tag where you want the button to render. -->
         <!-- User -->
         <li class="nav-item navbar-dropdown dropdown-user dropdown">
            <a class="nav-link dropdown-toggle hide-arrow"
               href="javascript:void(0);" data-bs-toggle="dropdown">
               <div class="avatar avatar-online">
                  <img src="../assets/img/avatars/1.png" alt
                     class="w-px-40 h-auto rounded-circle" />
               </div>
            </a>
            <ul class="dropdown-menu dropdown-menu-end">
               <li>
                  <a class="dropdown-item" href="#">
                     <div class="d-flex">
                        <div class="flex-shrink-0 me-3">
                           <div class="avatar avatar-online">
                              <img src="../assets/img/avatars/1.png" alt
                                 class="w-px-40 h-auto rounded-circle" />
                           </div>
                        </div>
                        <div class="flex-grow-1">
                           <span class="fw-semibold d-block">${firstName} &nbsp;
                           ${lastName}</span> <small class="text-muted">${role}</small>
                        </div>
                     </div>
                  </a>
               </li>
               <li>
                  <div class="dropdown-divider"></div>
               </li>
               <li><a class="dropdown-item" href="#"> <i
                  class="bx bx-user me-2"></i> <span class="align-middle">Last
                  Login - ${lastLoginDate}</span>
                  </a>
               </li>
               <li>
                  <div class="dropdown-divider"></div>
               </li>
               <li><a class="dropdown-item" href="logout1?hamaraKendra=${authToken}"> <i
                  class="bx bx-power-off me-2"></i> <span class="align-middle">Log
                  Out</span>
                  </a>
               </li>
            </ul>
         </li>
         <!--/ User -->
      </ul>
   </div>
</nav>