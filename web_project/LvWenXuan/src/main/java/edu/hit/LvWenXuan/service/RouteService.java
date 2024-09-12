package edu.hit.LvWenXuan.service;

import edu.hit.LvWenXuan.domain.PageBean;
import edu.hit.LvWenXuan.domain.Route;

/**
 * 线路Service
 */
public interface RouteService {
    PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname);
}