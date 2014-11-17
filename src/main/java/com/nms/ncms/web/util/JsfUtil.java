/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.ncms.web.util;

import com.nms.ncms.exception.AppException;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UISelectItem;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;

/**
 * JsfUtil functionalitis.
 *
 * @author Nguyen Trong Cuong (cuongnt1987@gmail.com)
 * @since 08/26/2014
 * @version 1.0
 */
public class JsfUtil {

    /**
     * Build array selectItem form list of object
     *
     * @param entities
     * @param selectOne
     * @return
     */
    public static SelectItem[] getSelectItems(List<?> entities, boolean selectOne) {
        int size = selectOne ? entities.size() + 1 : entities.size();
        SelectItem[] selectItems = new SelectItem[size];

        int i = 0;

        // Insert null value if select one.
        if (selectOne) {
            selectItems[0] = new SelectItem("", "---");
            i++;
        }

        for (Object x : entities) {
            selectItems[i++] = new SelectItem(x, x.toString());
        }

        return selectItems;
    }

    /**
     * Check a list component with item has label is 'value' is dummy?
     *
     * @param component
     * @param value dummy label
     * @return
     */
    public static boolean isDummySelectItem(UIComponent component, String value) {
        for (UIComponent children : component.getChildren()) {
            /* First check if children is UISelectItem */
            if (children instanceof UISelectItem) {
                UISelectItem item = (UISelectItem) children;
                if (item.getItemValue() == null && item.getItemLabel().equals(value)) {
                    return true;
                }
                break;
                /* Second check is children is UISelectItems */
            } else if (children instanceof UISelectItems) {
                UISelectItems items = (UISelectItems) children;
                Object itemsObject = items.getValue();
                if (itemsObject instanceof SelectItem[]) {
                    SelectItem[] itemSi = (SelectItem[]) itemsObject;
                    for (SelectItem si : itemSi) {
                        if (si.getValue() == null && si.getLabel().equals(value)) {
                            return true;
                        }
                        break;
                    }
                }
            }
        }
        return false;
    }

    public static String getRequestParameter(String key) {
        return FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap().get(key);
    }

    public static Object getObjectFromRequestParameter(String requestParameter, Converter converter,
            UIComponent component) {

        String theId = getRequestParameter(requestParameter);
        return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
    }

    public static Throwable getRootCause(Throwable cause) {
        if (cause != null) {
            Throwable source = cause.getCause();
            if (source != null) {
                return getRootCause(source);
            } else {
                return cause;
            }
        }
        return null;
    }

    public static void addErrorMessage(Exception ex, String defaultMsg) {

        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            addErrorMessage(msg);
        } else {
            addErrorMessage(defaultMsg);
        }
    }

    public static void addErrorMessages(List<String> messages) {
        messages.stream().forEach((message) -> {
            addErrorMessage(message);
        });
    }

    /**
     * Process action on
     *
     * @param <T>
     * @param consumer
     * @param t
     * @param successMessage
     * @param errorMessage
     */
    public static <T> void processAction(Consumer<T> consumer, T t,
            String successMessage, String errorMessage) {
        try {
            consumer.accept(t);
            MessageUtil.addGlobalInfoMessage(successMessage);
        } catch (Exception e) {
            handleException(e, errorMessage);
        }
    }

    public static void addErrorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        FacesContext.getCurrentInstance().validationFailed(); // Invalidate JSF page if we raise an error message

    }

    public static void addSuccessMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

    public static boolean isValidationFailed() {
        return FacesContext.getCurrentInstance().isValidationFailed();
    }

    public static String getComponentMessages(String clientComponent, String defaultMessage) {
        FacesContext fc = FacesContext.getCurrentInstance();
        UIComponent component = UIComponent.getCurrentComponent(fc).findComponent(clientComponent);
        if (component instanceof UIInput) {
            UIInput inputComponent = (UIInput) component;
            if (inputComponent.isValid()) {
                return defaultMessage;
            } else {
                Iterator<FacesMessage> iter = fc.getMessages(inputComponent.getClientId());
                if (iter.hasNext()) {
                    return iter.next().getDetail();
                }
            }
        }
        return "";
    }

    public static void handleException(Exception e, String defaultMessage) {
        if (e instanceof AppException) {
            MessageUtil.addGlobalErrorMessage(e);
        } else {
            Throwable t = getRootCause(e);
            if (t instanceof EJBException) {
                MessageUtil.addGlobalErrorMessage(t);
            } else {
                MessageUtil.addGlobalErrorMessage(defaultMessage, t);
            }
        }
    }
}
