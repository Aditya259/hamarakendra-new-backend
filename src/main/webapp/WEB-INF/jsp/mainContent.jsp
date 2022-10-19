<%@ page import="java.util.List" %>
<%@ page import="com.data.dashboard.model.BookedServices" %>
  

          <!-- / Navbar -->

          <!-- Content wrapper -->
          <div class="content-wrapper">
            <!-- Content -->

            <div class="container-xxl flex-grow-1 container-p-y">
              <div class="row">
                <div class="col-lg-8 mb-4 order-0">
                  <div class="card">
                    <div class="d-flex align-items-end row">
                      <div class="col-sm-7">
                        <div class="card-body">
                          <h5 class="card-title text-primary">Welcome  ${firstName} &nbsp; ${lastName}!</h5>
                          <p class="mb-4">
                            <br>
                            <br>
                          </p>

                        
                        </div>
                      </div>
                      <div class="col-sm-5 text-center text-sm-left">
                        <div class="card-body pb-0 px-0 px-md-4">
                          <img
                            src="../assets/img/illustrations/man-with-laptop-light.png"
                            height="140"
                            alt="View Badge User"
                            data-app-dark-img="illustrations/man-with-laptop-dark.png"
                            data-app-light-img="illustrations/man-with-laptop-light.png"
                          />
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-lg-4 col-md-4 order-1">
                  <div class="row">
                    <div class="col-lg-6 col-md-12 col-6 mb-4">
                      <div class="card">
                        <div class="card-body">
                          <div class="card-title d-flex align-items-start justify-content-between">
                          </div>
                          <span>Services Registered</span>
                          <h3 class="card-title mb-2">${noOfServices }</h3>
                          
                        </div>
                      </div>
                    </div>
                    <div class="col-lg-6 col-md-12 col-6 mb-4">
                      <div class="card">
                        <div class="card-body">
                          <div class="card-title d-flex align-items-start justify-content-between">
                          </div>
                          <span>Available Employee</span>
                          <h3 class="card-title mb-2">${noOfEmployee }</h3>
                        
                          
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <!-- Total Revenue -->
                
                <!--/ Total Revenue -->
                <div class="col-12 col-md-8 col-lg-12 order-3 order-md-2">
                  <div class="row">
                <div class="col-lg-8 mb-4 order-0">
                  <div class="card">
                    <div class="d-flex align-items-end row">
                      <div class="card-body">
                      <div class="col-sm-7">
                         <h5 class="card-title text-primary">UpComming Bookings</h5>
                      </div>
                      <div class="col-12">
                          <div class="table-responsive text-nowrap">
                  <table class="table">
                    <thead class="table-light">
                      <tr>
                        <th>Sr No.</th>
                        <th>Name</th>
                        <th>Requested Services</th>
                        <th>Email Id</th>
                        <th>Mobile No</th>
                        <th>Address</th>
                        <th>Date</th>
                         <th>Time</th>
                        <th>Status</th>
                        
                      </tr>
                    </thead>
                    <tbody class="table-border-bottom-0">
                    <%
                    List<BookedServices> bookedServicesDash= ( List<BookedServices>)request.getAttribute("listOfbookedServicesDashLimit");
                   	if(bookedServicesDash!=null && !bookedServicesDash.isEmpty()){
                   		for(BookedServices bookedService:bookedServicesDash){
                    %>
                    
                      <tr>
                        <td><i class="fab fa-angular fa-lg text-danger me-3"></i> <strong><%=bookedService.getId() %></strong></td>
                        <td><%=bookedService.getName() %></td>
                       <td><%=bookedService.getSelectedService() %></td>
                       <td><%=bookedService.getEmailId() %></td>
                       <td><%=bookedService.getMobileNo() %></td>
                       <td><%=bookedService.getAddress1() %></td>
                       <td><%=bookedService.getDate() %></td>
                        <td><%=bookedService.getTimeSlot() %></td>
                        <td><%=bookedService.getStatus() %></td>
                       
                      </tr>
                      
                      <%} 
                      }%>
                     
                      
                      
                    </tbody>
                  </table>
                  <br>
                  <br>
                  <br>
                  <br>
                  <br>
                  <br>
                </div>
                      </div>
                    </div>
                    </div>
                  </div>
                </div>
                 
                  </div>
                </div>
              </div>
              <div class="row">
                <!-- Order Statistics -->
                <div class="col-md-6 col-lg-4 col-xl-4 order-0 mb-4">
                  <div class="card h-100">
                    
                  
                  </div>
                </div>
                <!--/ Order Statistics -->

                <!-- Expense Overview -->
                <div class="col-md-6 col-lg-4 order-1 mb-4">
                  <div class="card h-100">
                
               
                  </div>
                </div>
                <!--/ Expense Overview -->

                <!-- Transactions -->
                <div class="col-md-6 col-lg-4 order-2 mb-4">
                  <div class="card h-100">
                 
                  </div>
                </div>
                <!--/ Transactions -->
              </div>
            </div>
            <!-- / Content -->
