/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights reserved.
 */
package com.nms.ncms.rs;

import com.nms.ncms.entity.Category;
import com.nms.ncms.entity.GameCategory;
import com.nms.ncms.entity.Game;
import com.nms.ncms.entity.Music;
import com.nms.ncms.entity.MusicCategory;
import com.nms.ncms.entity.Picture;
import com.nms.ncms.entity.PictureCategory;
import com.nms.ncms.entity.Product;
import com.nms.ncms.entity.Video;
import com.nms.ncms.entity.VideoCategory;
import com.nms.ncms.search.OrderType;
import com.nms.ncms.service.entity.GameCategoryService;
import com.nms.ncms.service.entity.GameService;
import com.nms.ncms.service.entity.MusicCategoryService;
import com.nms.ncms.service.entity.MusicService;
import com.nms.ncms.service.entity.PictureCategoryService;
import com.nms.ncms.service.entity.PictureService;
import com.nms.ncms.service.entity.ProductService;
import com.nms.ncms.service.entity.VideoCategoryService;
import com.nms.ncms.service.entity.VideoService;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
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
@Path("/")
@Produces({MediaType.APPLICATION_JSON + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8"})
public class NcmsContentResource {

    private static final Logger LOGGER = Logger.getLogger(NcmsContentResource.class.getName());
    @EJB
    private GameCategoryService gameCategoryFacade;
    @EJB
    private MusicCategoryService musicCategoryService;
    @EJB
    private PictureCategoryService pictureCategoryService;
    @EJB
    private VideoCategoryService videoCategoryService;
    @EJB
    private GameService gameEntryFacade;
    @EJB
    private MusicService musicService;
    @EJB
    private PictureService pictureService;
    @EJB
    private VideoService videoService;

    @GET
    @Path("category/game")
    public List<GameCategory> getGameCategories() {

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
    @Path("category/video")
    public List<VideoCategory> getVideoCategories() {

        List<VideoCategory> categories = null;

        try {
            categories = videoCategoryService.findAll();
        } catch (Exception e) {
            LOGGER.severe("Error when getCategories");
            throw new WebApplicationException(e, Response.Status.INTERNAL_SERVER_ERROR);
        }

        return categories;
    }

    @GET
    @Path("category/music")
    public List<MusicCategory> getMusicCategories() {

        List<MusicCategory> categories = null;

        try {
            categories = musicCategoryService.findAll();
        } catch (Exception e) {
            LOGGER.severe("Error when getCategories");
            throw new WebApplicationException(e, Response.Status.INTERNAL_SERVER_ERROR);
        }

        return categories;
    }

    @GET
    @Path("category/picture")
    public List<PictureCategory> getPictureCategories() {

        List<PictureCategory> categories = null;

        try {
            categories = pictureCategoryService.findAll();
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
    @Path("search/game")
    public List<Game> searchGameEntries(
            @QueryParam("categoryId") Long categoryId,
            @QueryParam("keyword") String keyword,
            @QueryParam("flatform") @DefaultValue("0") int flatform,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("range") @DefaultValue("10") int range,
            @QueryParam("orderType") String orderType) {

        List<Game> gameEntries = new ArrayList<>();

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
                case "VIEW":
                    orderTypeEnum = OrderType.TOP_VIEW;
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
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        return gameEntries;
    }

    @GET
    @Path("search/music")
    public List<Music> searchMusicEntries(
            @QueryParam("categoryId") Long categoryId,
            @QueryParam("keyword") String keyword,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("range") @DefaultValue("10") int range,
            @QueryParam("orderType") String orderType) {

        List<Music> musics = search(() -> {
            if (categoryId != null) {
                return musicCategoryService.find(categoryId);
            }
            return null;
        }, () -> {
            return musicService;
        }, keyword, page, range, orderType);

        return musics;
    }
    
    @GET
    @Path("search/picture")
    public List<Picture> searchPictureEntries(
            @QueryParam("categoryId") Long categoryId,
            @QueryParam("keyword") String keyword,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("range") @DefaultValue("10") int range,
            @QueryParam("orderType") String orderType) {

        List<Picture> pictures = search(() -> {
            return pictureCategoryService.find(categoryId);
        }, () -> {
            return pictureService;
        }, keyword, page, range, orderType);

        return pictures;
    }
    
    @GET
    @Path("search/video")
    public List<Video> searchVideoEntries(
            @QueryParam("categoryId") Long categoryId,
            @QueryParam("keyword") String keyword,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("range") @DefaultValue("10") int range,
            @QueryParam("orderType") String orderType) {

        List<Video> videos = search(() -> {
            return videoCategoryService.find(categoryId);
        }, () -> {
            return videoService;
        }, keyword, page, range, orderType);

        return videos;
    }

    private <E extends Product, C extends Category, S extends ProductService> List<E> search(Supplier<C> catSupplier,
            Supplier<S> serviceSupplier, String keywords, int page, int range, String orderType) {

        List<E> products = null;

        String orderField = getOrderField(orderType);
        boolean asc = isAsc(orderType);

        C cat = null;

        try {
            cat = catSupplier.get();
        } catch (Exception e) {
            // nothing
        }

        try {
            products = serviceSupplier.get().search(keywords, cat, null, orderField, asc, page * range, range);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error when search Product with"
                    + " keyword = {0}, page = {1}, range = {2},"
                    + "orderType = {3}, Exception message = {4}", new Object[]{
                        keywords, page, range, orderType, e.toString()});
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        return products;
    }

    private String getOrderField(String orderType) {
        String orderField = null;
        // validate orderType
        if (orderType != null && !orderType.trim().isEmpty()) {
            orderType = orderType.trim().toUpperCase();

            switch (orderType) {
                case "HOT":
                    orderField = "hot";
                    break;
                case "DOWNLOAD":
                    orderField = "downloadCount";
                    break;
                case "NEW":
                    orderField = "createdDate";
                    break;
                case "VIEW":
                    orderField = "viewCount";
                    break;
            }
        }
        return orderField;
    }

    private boolean isAsc(String orderType) {
        return false;
    }
}
