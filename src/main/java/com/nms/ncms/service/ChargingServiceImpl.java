/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights reserved.
 */
package com.nms.ncms.service;

import com.nms.ncms.entity.Product;
import com.nms.ncms.exception.AppException;
import com.nms.ncms.exception.ErrorInfo;
import com.nms.ncms.web.util.MessageUtil;
import com.nms.ws.charging.ChargingWebserviceImpl;
import com.nms.ws.charging.ChargingWebserviceImplService;
import com.nms.ws.charging.ContentPurcharseReq;
import com.nms.ws.charging.ContentPurcharseRes;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

@Dependent
public class ChargingServiceImpl implements ChargingService {

    private static final long serialVersionUID = 8077722183941509246L;

    @Inject
    private MobileChecker mobileChecker;

    @Override
    public FacesMessage chargeProduct(Product product) {
        
        if (product != null) {
            FacesMessage message = null;
            ChargingWebserviceImplService chargingServicePort = new ChargingWebserviceImplService();
            ChargingWebserviceImpl chargingService = chargingServicePort.getChargingWebserviceImplPort();
            ContentPurcharseReq request = new ContentPurcharseReq();
            request.setUserName("");
            request.setPassword("");
            //request.setTimeout(60000);
            request.setCpName("NGUYEN TRONG CUONG");
            request.setContentCode(product.getCode());
            request.setIsdn(mobileChecker.getPhoneNumber());
            request.setOsCode(String.valueOf(mobileChecker.getOsCode()));
            request.setPrice(product.getPrice());
            request.setShortCode("");
            ContentPurcharseRes response = chargingService.chargeItems(request);
            
            int resutl = response.getResult();
            String content = response.getContent();
            String detail = response.getDetail();
            
            if (resutl == 0) {
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, 
                        MessageUtil.getBundleMessage("vnm-purcharse-success"), 
                        "");
                RequestContext.getCurrentInstance().execute("window.open('" + content + "', '_blank')");
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "vnm-purcharse-error", content + " - " + detail);
            }
            
            return message;
        } else {
            throw new AppException(ErrorInfo.UNKNOW_ERROR);
        }
    }

}
