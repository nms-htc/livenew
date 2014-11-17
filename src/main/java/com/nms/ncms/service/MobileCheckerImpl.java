/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights reserved.
 */
package com.nms.ncms.service;

import com.nms.ws.checksubscriber.CHARGING;
import com.nms.ncms.web.util.UserAgentInfo;
import com.nms.ws.checksubscriber.CHARGINGSoap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named("mobileChecker")
@SessionScoped
public class MobileCheckerImpl implements MobileChecker {

    private static final String MSISDN = "MSISDN";
    private static final String X_FORWARD_FOR = "X-FORWARDED-FOR";
    private static final long serialVersionUID = -1751612000675015696L;
    private static final Logger LOGGER = Logger.getLogger(MobileCheckerImpl.class.getName());
    private UserAgentInfo agentInfo;
    private String phoneNumber;

    @PostConstruct
    public void init() {
        agentInfo = UserAgentInfo.createInstance();
        /********************
        * Check phone Number
        *********************/
        // detech header
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        phoneNumber = request.getHeader(MSISDN);
        // hard code
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            phoneNumber = "84924032453";
        }
        // check ip and call service
        if (phoneNumber == null) {
            String ipAddress = request.getHeader(X_FORWARD_FOR);
            if (ipAddress == null || ipAddress.trim().isEmpty()) {
                ipAddress = request.getRemoteAddr();
            }

            if (ipAddress != null && !ipAddress.trim().isEmpty()) {
                try {
                    CHARGING service = new CHARGING();
                    CHARGINGSoap charginService = service.getCHARGINGSoap();
                    phoneNumber = charginService.getmsisdn("cuongnt", "thanhlong", ipAddress);
                } catch (Exception e) {
                    LOGGER.log(Level.WARNING, "MobileCheckerImpl:getPhoneNumber() Error when check phonenumber by ip", e);
                }
            }

            if (phoneNumber != null && !phoneNumber.matches("^\\+?[0-9]{10,12}$")) {
                LOGGER.log(Level.WARNING, "Error code when call CHARGING WS, errorCode: {0}", phoneNumber);
                phoneNumber = null;

            }
        }
    }

    @Override
    public boolean isVnmSubsriber() {
        return phoneNumber != null;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public boolean isMobile() {
        return agentInfo.isMobileDevice();
    }

    @Override
    public boolean isIos() {
        return agentInfo.detectIos();
    }

    @Override
    public boolean isAndroid() {
        return agentInfo.detectAndroid();
    }

    @Override
    public boolean isWindowPhone() {
        return agentInfo.detectWindowsMobile();
    }

    @Override
    public int getOsCode() {
        if (isAndroid()) {
            return 1;
        } else if (isIos()) {
            return 2;
        } else if (isWindowPhone()) {
            return 4;
        } else if (isMobile()) {
            return 3;
        } else {
            return 5;
        }
    }
}
