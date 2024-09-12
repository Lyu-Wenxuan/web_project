package edu.hit.LvWenXuan.dao.Impl;

import edu.hit.LvWenXuan.dao.RouteDao;
import edu.hit.LvWenXuan.domain.Route;
import edu.hit.LvWenXuan.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public int findTotalCount(int cid, String rname) {
        String sql = "select count(*) from tab_route where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();
        if(cid != 0) {
            params.add(cid);
            sb.append(" and cid = ?");
        }

        if(rname != null && rname.length() > 0 && !"null".equals(rname)){
            params.add("%" + rname + "%");
            sb.append(" and rname like ? ");
        }
        sql = sb.toString();
        System.out.println(sql);
        System.out.println(params);
        return template.queryForObject(sql, Integer.class, params.toArray());
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize, String rname) {
        String sql = "select * from tab_route where 1 = 1";
        StringBuilder sb = new StringBuilder(sql);
        List params = new ArrayList();

        if(cid != 0) {
            params.add(cid);
            sb.append(" and cid = ? ");
        }
        if(rname !=null && rname.length() > 0 && !"null".equals(rname)) {
            sb.append(" and rname like ?");
            params.add("%"+rname+"%");
        }
        sb.append(" limit ? , ? ");
        params.add(start);
        params.add(pageSize);
        sql = sb.toString();
        return template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), params.toArray());
    }
}