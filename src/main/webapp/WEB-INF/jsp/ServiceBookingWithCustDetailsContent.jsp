<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.data.dashboard.model.BookedServices" %>
  <%@ page import="com.data.dashboard.model.BookingServicesDetails" %>
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
              
                      <form action="serviceBookingData" method="post">
                       <input
                              type="hidden"
                              value=""
                              name="id"
                            >
                            <input
                              type="hidden"
                              value="${authToken}"
                              name="hamaraKendra"
                            > 
                            <input
                              type="hidden"
                              value="${username}"
                              name="userName"
                            >  
                            
                        <div class="row mb-3">
                          <label class="col-sm-2 col-form-label" for="basic-default-name">Full Name</label>
                          <div class="col-sm-10">
                            <input type="text" class="form-control" id="basic-default-name"  name="name" value="" placeholder="Enter your Full Name Ex- John Doe" />
                          </div>
                        </div>
                        <div class="row mb-3">
                          <label class="col-sm-2 col-form-label" for="basic-default-company">Mobile Number</label>
                          <div class="col-sm-10">
                            <input
                              type="text"
                              class="form-control"
                              id="basic-default-company"
                              placeholder="Enter without +91 and without 0 Ex- 9000000000"
                              value=""
                              name="mobileNo"
                            />
                          </div>
                        </div>
                        <div class="row mb-3">
                          <label class="col-sm-2 col-form-label" for="basic-default-email">Email</label>
                          <div class="col-sm-10">
                            <div class="input-group input-group-merge">
                              <input
                                type="text"
                                id="basic-default-email"
                                class="form-control"
                                placeholder="john@abc.com"
                                aria-label="john.doe"
                                aria-describedby="basic-default-email2"
                                value=""
                                name="emailId"
                              />
                             
                            </div>
                            
                          </div>
                        </div>
                        <div class="row mb-3">
                          <label class="col-sm-2 col-form-label" for="basic-default-phone">Address Line 1 </label>
                          <div class="col-sm-10">
                            <input
                              type="text"
                              id="basic-default-phone"
                              class="form-control phone-mask"
                              placeholder="( Flat No/ Plot No , Apartment/Home Name )"
                              aria-describedby="basic-default-phone"
                              value=""
                              name = "address1"
                            />
                          </div>
                        </div>
                         <div class="row mb-3">
                          <label class="col-sm-2 col-form-label" for="basic-default-phone">Address Line 2 </label>
                          <div class="col-sm-10">
                            <input
                              type="text"
                              id="basic-default-phone"
                              class="form-control phone-mask"
                              placeholder="(Locality,Area / Nearest Land Mark)"
                              aria-describedby="basic-default-phone"
                              value=""
                              name = "address2"
                            />
                          </div>
                        </div>
                        <div class="row mb-3">
                          <label class="col-sm-2 col-form-label" for="basic-default-phone">Address Line 3 </label>
                          <div class="col-sm-10">
                            <input
                              type="text"
                              id="basic-default-phone"
                              class="form-control phone-mask"
                              placeholder="(City, State, Country, Pincode)"
                              aria-describedby="basic-default-phone"
                              value=""
                              name = "address3"
                            />
                          </div>
                        </div>
                        <div class="mb-3 row">
                        <label for="html5-date-input" class="col-md-2 col-form-label">Slot Date</label>
                        <div class="col-md-10">
                          <input class="form-control" type="date"  name = "date" value="" id="html5-date-input" />
                        </div>
                      </div>
                       <!--<div class="row mb-3">
                          <label class="col-sm-2 col-form-label" for="basic-default-phone">Selected Service </label>
                          <div class="col-sm-10">
                            <input
                              type="text"
                              id="basic-default-phone"
                              class="form-control phone-mask"
                              placeholder=""
                              aria-describedby="basic-default-phone"
                              value=""
                             name = "selectedService"
                             readonly="readonly"
                            />
                          </div>
                        </div>
                        -->
                       <div class="row mb-3">
                       <label class="col-sm-2 col-form-label" for="basic-default-phone">Status </label>
                        <div class="col-sm-10">
                        <select name="status" class="form-select" id="status" aria-label="Default select example">
                         <option value="pending">Pending</option>
                         <option value="completed"  >Completed</option>
                        </select>
                      </div>
                      </div>
  
                        <div class="row mb-3">
                       <label class="col-sm-2 col-form-label" for="basic-default-phone">Services </label>
                        <div class="col-sm-10">
                        <select name="selectedService" class="form-select"  id="selectedService" aria-label="Default select example">
                        <% 
                        
                        List<BookingServicesDetails> bookingServicesDetails = ( List<BookingServicesDetails>) request.getAttribute("bookingServicesDetails");
                    		if(bookingServicesDetails!=null){
                        	for(BookingServicesDetails bookServiceDetails : bookingServicesDetails){
                        %>
                         <option value="<%=bookServiceDetails.getHeading() %>"  ><%=bookServiceDetails.getHeading() %></option>
                        <%}
                    		}
                    		%>
                        </select>
                      </div>
                      </div>
                      
                        
                        <div class="row justify-content-end">
                          <div class="col-sm-10">
                            <button type="submit" class="btn btn-primary">Submit</button>
                          </div>
                        </div>
                          <div class="row justify-content-end">
                          <div class="col-sm-10">
                          	<% 
                          		String msg = (String) request.getAttribute("message");
                    		   if(msg!=null){
                          	%>
                          </div>
                         	<center> <h5 ><%=msg %></h5> </center>
                         
                          <%} %>
                        </div>
                      </form>
                    
                    </div>
                  </div>
                </div>
                </div>