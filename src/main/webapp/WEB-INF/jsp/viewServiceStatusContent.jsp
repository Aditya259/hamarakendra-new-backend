<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.data.dashboard.model.BookedServices" %>
  
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
                    BookedServices bookService = (BookedServices) request.getAttribute("bookedService");
                    if(bookService!=null ){
                    %>
                      <form action="loadListOfServices" method="get">
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
                          <label class="col-sm-2 col-form-label" for="basic-default-name">Full Name</label>
                          <div class="col-sm-10">
                            <label class="col-sm-2 col-form-label" for="basic-default-phone"><%=bookService.getName() %> </label>
                            
                          </div>
                        </div>
                        <div class="row mb-3">
                          <label class="col-sm-2 col-form-label" for="basic-default-company">Mobile Number</label>
                          <div class="col-sm-10">
                          <label class="col-sm-2 col-form-label" for="basic-default-phone"><%=bookService.getMobileNo() %> </label>

                          </div>
                        </div>
                        <div class="row mb-3">
                          <label class="col-sm-2 col-form-label" for="basic-default-email">Email</label>
                          <div class="col-sm-10">
                            <div class="input-group input-group-merge">
                               <label class="col-sm-2 col-form-label" for="basic-default-phone"><%=bookService.getEmailId() %> </label>

                             
                            </div>
                            
                          </div>
                        </div>
                        <div class="row mb-3">
                          <label class="col-sm-2 col-form-label" for="basic-default-phone">Address Line 1 </label>
                          <div class="col-sm-10">
                            <label class="col-sm-2 col-form-label" for="basic-default-phone"><%=bookService.getAddress1() %> </label>

                          </div>
                        </div>
                         <div class="row mb-3">
                          <label class="col-sm-2 col-form-label" for="basic-default-phone">Address Line 2 </label>
                          <div class="col-sm-10">
                           <label class="col-sm-2 col-form-label" for="basic-default-phone"><%=bookService.getAddress2() %> </label>

                          </div>
                        </div>
                        <div class="row mb-3">
                          <label class="col-sm-2 col-form-label" for="basic-default-phone">Address Line 3 </label>
                          <div class="col-sm-10">
                           <label class="col-sm-2 col-form-label" for="basic-default-phone"><%=bookService.getAddress3()%> </label>

                          </div>
                        </div>
                        <div class="mb-3 row">
                        <label for="html5-date-input" class="col-md-2 col-form-label">Slot Date</label>
                        <div class="col-md-10">
                            <label class="col-sm-2 col-form-label" for="basic-default-phone"><%=bookService.getDate() %> </label>
                        </div>
                      </div>
                      <div class="mb-3 row">
                        <label for="html5-date-input" class="col-md-2 col-form-label">Slot Time</label>
                        <div class="col-md-10">
                            <label class="col-sm-2 col-form-label" for="basic-default-phone"><%=bookService.getTimeSlot() %> </label>
                        </div>
                      </div>
                       <div class="row mb-3">
                          <label class="col-sm-2 col-form-label" for="basic-default-phone">Selected Service </label>
                          <div class="col-sm-10">
                            <label class="col-sm-2 col-form-label" for="basic-default-phone"><%=bookService.getSelectedService() %> </label>

                          </div>
                        </div>
                        
                       <div class="row mb-3">
                       <label class="col-sm-2 col-form-label" for="basic-default-phone">Status </label>
                        <div class="col-sm-10">
                         <label class="col-sm-2 col-form-label" for="basic-default-phone"><%=bookService.getStatus() %> </label>
                       
                      </div>
                      </div>
                        
                        <div class="row mb-3">
                       <label class="col-sm-2 col-form-label" for="basic-default-phone">Employee Mapped </label>
                        <div class="col-sm-10">
                         <label class="col-sm-2 col-form-label" for="basic-default-phone"><%=bookService.getMappedEmp()%> </label>
                       
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