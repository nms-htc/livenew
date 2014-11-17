/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights reserved.
 */
package com.nms.ncms.rs;

import com.nms.ncms.entity.GameCategory;
import com.nms.ncms.entity.Game;
import com.nms.ncms.search.OrderType;
import com.nms.ncms.service.entity.GameCategoryService;
import com.nms.ncms.service.entity.GameService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Public Eip resources via RESTFull Webservice.
 *
 * @author Cuong
 */
@Stateless
@Path("/")
@Produces({MediaType.APPLICATION_JSON + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8"})
public class EipContentResource {

    private static final Logger LOGGER = Logger.getLogger(EipContentResource.class.getName());
    @EJB
    private GameCategoryService gameCategoryFacade;
    @EJB
    private GameService gameEntryFacade;

    @GET
    @Path("game/categories")
    public List<GameCategory> getCategories() {

        List<GameCategory> categories = null;

        try {
            categories = gameCategoryFacade.findAll();
        } catch (Exception e) {
            LOGGER.severe("Error when getCategories");
            throw new WebApplicationException(e, Response.Status.INTERNAL_SERVER_ERROR);
        }

        return categories;
    }

    @GET
    @Path("game/{id}")
    public Game getGameEntry(@PathParam("id") Long id) {
        Game gameEntry = null;
        try {
            gameEntry = gameEntryFacade.find(id);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error when find GameEntry with Id = {0}", id);
        }

        if (gameEntry == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return gameEntry;
    }

    @GET
    @Path("game/search")
    public List<Game> searchGameEntries(
            @QueryParam("categoryId") Long categoryId,
            @QueryParam("keyword") String keyword,
            @QueryParam("flatform") @DefaultValue("0") int flatform,
            @QueryParam("page") @DefaultValue("0") int page, 
            @QueryParam("range") @DefaultValue("10") int range,
            @QueryParam("orderType") String orderType) {

        List<Game> gameEntries = null;

        Game.Flatform flatformEnum = null;
        OrderType orderTypeEnum = null;

        // validate flatform
        switch (flatform) {
            case 0:
                flatformEnum = Game.Flatform.Android;
                break;
            case 1:
                flatformEnum = Game.Flatform.Ios;
                break;
            case 2:
                flatformEnum = Game.Flatform.Java;
                break;
            case 3:
                flatformEnum = Game.Flatform.Window_Phone;
                break;
        }

        // validate orderType
        if (orderType != null && !orderType.trim().isEmpty()) {
            orderType = orderType.trim().toUpperCase();

            switch (orderType) {
                case "HOT":
                    orderTypeEnum = OrderType.TOP_HOT;
                    break;
                case "DOWNLOAD":
                    orderTypeEnum = OrderType.TOP_DOWNLOAD;
                    break;
                case "NEW":
                    orderTypeEnum = OrderType.TOP_NEW;
                    break;
            }
        }

        try {

            //gameEntries = gameEntryFacade.search(categoryId, keyword, orderTypeEnum, page, range, flatformEnum);
        } catch (Exception e) {

            LOGGER.log(Level.SEVERE, "Error when searchGameEntries with categoryId = {0}, "
                    + " keyword = {1}, flatform = {2}, page = {3}, range = {4},"
                    + "orderType = {5}, Exception message = {6}", new Object[]{
                        categoryId, keyword, flatform, page, range, orderType, e.toString()});
        }

        return gameEntries;
    }
}
