package com.pw.util.query;

import com.pw.util.string.PwStringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by PoemWhite on 2017/5/11.
 */
public abstract class BaseRepository {

    public static Log logger = LogFactory.getLog(BaseRepository.class);

    public abstract JdbcTemplate getJdbcTemplate();

    /**
     * queryForBeanList
     * @param sql
     * @param params
     * @param beanClass
     * @return
     */
    public List queryForBeanList(String sql, Object[] params, Class beanClass){

        if(logger.isInfoEnabled()){
            logger.info(sql);
            logger.info(Arrays.toString(params));
        }

        List list = getJdbcTemplate().query(sql, params, new BeanPropertyRowMapper(beanClass));
        return (list!=null && list.size()>0)?list:null;
    }

    /**
     * queryForMap
     * @param sql
     * @param params
     * @return
     */
    public Map queryForMap(String sql, Object[] params){

        if(logger.isInfoEnabled()){
            logger.info(sql);
            logger.info(Arrays.toString(params));
        }

        try {
            return getJdbcTemplate().queryForMap(sql, params);
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    /**
     * queryForMapList
     * @param sql
     * @param params
     * @return
     */
    public List queryForMapList(String sql, Object[] params){

        if(logger.isInfoEnabled()){
            logger.info(sql);
            logger.info(Arrays.toString(params));
        }

        return getJdbcTemplate().queryForList(sql,params);
    }

    /**
     * queryForBeanList
     * @param sql
     * @param beanClass
     * @return
     */
    public List queryForBeanList(String sql, Class beanClass){
        return this.queryForBeanList(sql, null, beanClass);
    }

    /**
     * queryForBean
     * @param sql
     * @param params
     * @param beanClass
     * @return
     */
    public Object queryForBean(String sql, Object[] params, Class beanClass){
        List list = this.queryForBeanList(sql, params, beanClass);
        return (list!=null)?list.get(0):null;
    }

    /**
     * queryForBean
     * @param sql
     * @param beanClass
     * @return
     */
    public Object queryForBean(String sql, Class beanClass){
        return this.queryForBean(sql, null, beanClass);
    }

    /**
     * insert update delete
     * @param sql
     * @param params
     */
    public int executeUpdate(String sql, Object[] params){

        if(logger.isInfoEnabled()){
            logger.info(sql);
            logger.info(Arrays.toString(params));
        }
        return getJdbcTemplate().update(sql,params);
    }

    /**
     * batch insert update delete
     * @param sql
     * @param batchArgs
     */
    public int[] executeBatchUpdate(String sql, List<Object[]> batchArgs){

        if(logger.isInfoEnabled()){
            logger.info(sql);
        }
        return getJdbcTemplate().batchUpdate(sql,batchArgs);
    }

    /**
     * executePaginate
     * @param select
     * @param pojoClass
     * @param <T>
     * @return
     */
    public <T> QueryResult<T> executePaginate(Select select, Class<T> pojoClass){

        List<T> list = new ArrayList<T>();

        if(select!=null && !"".equals(PwStringUtil.getString(select.getSql()))){

            list = this.queryForBeanList(select.getSql(),select.getParameters(),pojoClass);
            if(select instanceof PageSelect){
                int total = getRecordCount(((PageSelect) select).getWrappedSelect());
                return new QueryResult<T>(list, total);
            }
        }

        return new QueryResult<T>(list, list==null ?0 :list.size());
    }

    private int getRecordCount(Select select){

        RecordCountSelect recordCountSelect = new RecordCountSelect(select);

        Map map = this.queryForMap(recordCountSelect.getSql(),recordCountSelect.getParameters());

        return Integer.parseInt(PwStringUtil.getString(map.get(RecordCountSelect.RECORD_COUNT)));
    }

    public String getSysdate(){
        Map map = this.queryForMap("select date_format(now(),'%Y-%m-%d %H:%i:%s') sysdate from dual",null);
        if(map != null){
            return PwStringUtil.getString(map.get("sysdate"));
        }
        return "";
    }

    public String getSysdate(String format){
        Map map = this.queryForMap("select date_format(now(),?) sysdate from dual",new Object[]{format});
        if(map != null){
            return PwStringUtil.getString(map.get("sysdate"));
        }
        return "";
    }
}
