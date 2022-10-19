<%@ page import="java.util.List" %>
<%@ page import="com.data.dashboard.model.EmployeeDetails" %>
  
  <div class="content-wrapper">
            <!-- Content -->

            <div class="container-xxl flex-grow-1 container-p-y">
  
   <div class="card">
                <h5 class="card-header">Request For Services</h5>
                <div class="table-responsive text-nowrap">
                  <table class="table">
                    <thead class="table-light">
                      <tr>
                        <th>Employee Id.</th>
                        <th>Employee Name</th>
                        <th>User Name</th>
                        <th>Email Id</th>
                        <th>Mobile No</th>
                        <th>Address</th>
                        <th>Status</th>
                        <th>Actions</th>
                      </tr>
                    </thead>
                    <tbody class="table-border-bottom-0">
                    <%
                    List<EmployeeDetails> listOfEmployeeDetails= ( List<EmployeeDetails>)request.getAttribute("listOfBookedServices");
                   	if(listOfEmployeeDetails!=null && !listOfEmployeeDetails.isEmpty()){
                   		for(EmployeeDetails bookedService:listOfEmployeeDetails){
                    %>
                    
                      <tr>
                        <td><i class="fab fa-angular fa-lg text-danger me-3"></i> <strong><%=bookedService.getEmployeeId() %></strong></td>
                        <td><%=bookedService.getEmployeeName() %></td>
                       <td><%=bookedService.getEmployeeUserName() %></td>
                       <td><%=bookedService.getEmployeeEmailAddress() %></td>
                       <td><%=bookedService.getEmployeeMobileNo() %></td>
                       <td><%=bookedService.getEmployeeAddress() %></td>
                        <td><%=bookedService.getEmployeeStatus() %></td>
                        <td>
                          <div class="dropdown">
                            <button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown">
                              <i class="bx bx-dots-vertical-rounded"></i>
                            </button>
                            <div class="dropdown-menu">
                              <a class="dropdown-item" href="editEmployeeDetails?id=<%=bookedService.getEmployeeId() %>&hamaraKendra=${authToken}"
                                ><i class="bx bx-edit-alt me-1"></i> Edit</a
                              >
                               <a class="dropdown-item" href="viewlistofemployee?id=<%=bookedService.getEmployeeId() %>&hamaraKendra=${authToken}"
                                ><i class="bx bx-edit-alt me-1"></i> View</a
                              >
                            </div>
                            
                           
                          </div>
                        </td>
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