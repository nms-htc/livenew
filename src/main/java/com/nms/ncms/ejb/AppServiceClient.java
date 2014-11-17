/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights reserved.
 */
package com.nms.ncms.ejb;

//import com.nms.chargingwebservice.ChargingWebserviceImpl;
//import com.nms.chargingwebservice.ChargingWebserviceImplService;
//import com.nms.chargingwebservice.ContentPurcharseReq;
//import com.nms.chargingwebservice.ContentPurcharseRes;
import com.nms.ncms.web.util.MessageUtil;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
//import nmscgw.CHARGING;
//import nmscgw.CHARGINGSoap;

/**
 *
 * @author Cuong
 */
@Stateless
public class AppServiceClient implements Serializable {

    public  final String MSISDN = "MSISDN";
    public static final String X_FORWARD_FOR = "X-FORWARDED-FOR";
    private static final long serialVersionUID = -2727007622815437439L;
    //@WebServiceRef(wsdlLocation = "WEB-INF/wsdl/203.128.246.85/NMS.asmx.wsdl")
    //private CHARGING chargingService;
    //@WebServiceRef(wsdlLocation = "WEB-INF/wsdl/192.168.88.44_8080/EntertainmentWS/ChargingWebserviceImplService.wsdl")
    //private  ChargingWebserviceImplService nmsChargingService;

    public String checkPhoneNumber() {
        if (false) {
            return "84925003155";
        }
        
        String phoneNumber;
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();

        phoneNumber = request.getHeader(MSISDN);
        
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            String ipAddress = request.getHeader(X_FORWARD_FOR);

            if (ipAddress == null || ipAddress.trim().isEmpty()) {
                ipAddress = request.getRemoteAddr();
            }

            if (ipAddress != null && !ipAddress.trim().isEmpty()) {
                
                //CHARGINGSoap port = chargingService.getCHARGINGSoap();
                String usename = MessageUtil.getBundleMessage("Service.CheckIpUserName");
                String password = MessageUtil.getBundleMessage("Service.CheckIpPassword");
                //phoneNumber = port.getmsisdn(usename, password, ipAddress);

                if (!isValidPhoneNumber(phoneNumber)) {
                    phoneNumber = null;
                }
            }
        }

        return phoneNumber;
    }

    public boolean charging(String contentCode, String cpName, String isdn, double price, String shortCode,String osCode) {
        LOGGER.log(Level.INFO, "Start call NMS_CHARGING Service");
        long startTime = System.currentTimeMillis();
        //ChargingWebserviceImpl port = nmsChargingService.getChargingWebserviceImplPort();

        try {
//            ContentPurcharseReq request = new ContentPurcharseReq();
//            request.setContentCode(contentCode);
//            request.setCpName(cpName);
//            request.setIsdn(isdn);
//            request.setOsCode(osCode);
//            request.setPrice(price);
//            request.setShortCode(shortCode);
//            request.setTimeout(0);
//            request.setUserName(MessageUtil.getBundleMessage("Service.NmsCharging.Username"));
//            request.setPassword(MessageUtil.getBundleMessage("Service.NmsCharging.Password"));

            //ContentPurcharseRes response = //port.chargeItems(request);
            LOGGER.log(Level.INFO, "End call NMS_CHARGING Service with total time: {0} s", (System.currentTimeMillis() - startTime) / 1000);
            //return response.getResult() == 0;
            return false;

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "End call NMS_CHARGING with message: {0}", e.getMessage());
            LOGGER.log(Level.INFO, "End call NMS_CHARGING Service with total time: {0} s", (System.currentTimeMillis() - startTime) / 1000);
            return false;
        }
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber != null && !phoneNumber.trim().isEmpty()) {
            return phoneNumber.matches("^\\+?[0-9]{10,12}$");
        }
        return false;
    }
    
    private static final Logger LOGGER = Logger.getLogger(AppServiceClient.class.getName());
}
