<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.data.dashboard.model.EmployeeDetails" %>
  
  <div class="container-xxl flex-grow-1 container-p-y">

              <!-- Basic Layout & Basic with Icons -->
              <div class="row">
                <!-- Basic Layout -->
                <div class="col-xxl">
                  <div class="card mb-4">
                    <div class="card-header d-flex align-items-center justify-content-between">
                      <h5 class="mb-0"></h5>
                      <small class="text-muted float-end"></small>
                    </div>
                    <div class="card-body">
                    <%
                    EmployeeDetails bookService = (EmployeeDetails) request.getAttribute("bookedService");
                    if(bookService!=null ){
                    %>
                      <form action="loadListOfEmployees" method="get">
                       <input
                              type="hidden"
                              value="<%=bookService.getId() %>"
                              name="id"
                            >
                              <input
                              type="hidden"
                              value="${authToken}"
                              name="hamaraKendra"
                            >  
                        <div class="row mb-3">
                          <label class="col-sm-2 col-form-label" for="basic-default-name">Name</label>
                          <div class="col-sm-10">
                            <label class="col-sm-2 col-form-label" for="basic-default-phone"><%=bookService.getEmployeeName() %> </label>
                            
                          </div>
                        </div>
                        <div class="row mb-3">
                          <label class="col-sm-2 col-form-label" for="basic-default-company">Mobile Number</label>
                          <div class="col-sm-10">
                          <label class="col-sm-2 col-form-label" for="basic-default-phone"><%=bookService.getEmployeeMobileNo()%> </label>

                          </div>
                        </div>
                        <div class="row mb-3">
                          <label class="col-sm-2 col-form-label" for="basic-default-email">Email</label>
                          <div class="col-sm-10">
                            <div class="input-group input-group-merge">
                               <label class="col-sm-2 col-form-label" for="basic-default-phone"><%=bookService.getEmployeeEmailAddress() %> </label>

                             
                            </div>
                            
                          </div>
                        </div>
                        <div class="row mb-3">
                          <label class="col-sm-2 col-form-label" for="basic-default-phone">Address </label>
                          <div class="col-sm-10">
                            <label class="col-sm-2 col-form-label" for="basic-default-phone"><%=bookService.getEmployeeEmailAddress() %> </label>

                          </div>
                        </div>
                         <div class="row mb-3">
                          <label class="col-sm-2 col-form-label" for="basic-default-phone">City </label>
                          <div class="col-sm-10">
                           <label class="col-sm-2 col-form-label" for="basic-default-phone"><%=bookService.getEmployeeCity() %> </label>

                          </div>
                        </div>
                        <div class="row mb-3">
                          <label class="col-sm-2 col-form-label" for="basic-default-phone">State </label>
                          <div class="col-sm-10">
                           <label class="col-sm-2 col-form-label" for="basic-default-phone"><%=bookService.getEmployeeState()%> </label>

                          </div>
                        </div>
                        <div class="mb-3 row">
                        <label for="html5-date-input" class="col-md-2 col-form-label">Country</label>
                        <div class="col-md-10">
                            <label class="col-sm-2 col-form-label" for="basic-default-phone"><%=bookService.getEmployeeCountry() %> </label>
                        </div>
                      </div>
                       <div class="row mb-3">
                          <label class="col-sm-2 col-form-label" for="basic-default-phone">UserName </label>
                          <div class="col-sm-10">
                            <label class="col-sm-2 col-form-label" for="basic-default-phone"><%=bookService.getEmployeeUserName() %> </label>

                          </div>
                        </div>
                        
                       <div class="row mb-3">
                       <label class="col-sm-2 col-form-label" for="basic-default-phone">Status </label>
                        <div class="col-sm-10">
                         <label class="col-sm-2 col-form-label" for="basic-default-phone"><%=bookService.getEmployeeStatus() %> </label>
                       
                      </div>
                      </div>
                        
                         <div class="row justify-content-end">
                           <div class="row mb-3">
                            <button type="submit" class="btn btn-primary">Back</button>
                          </div>
                        </div>
                          
                      </form>
                      <%} %>
                    </div>
                  </div>
                </div>
                </div>