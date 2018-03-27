package com.pw.util.query;

import com.pw.util.support.constant.PwConstant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * PageHelper
 * Created by PoemWhite on 2017/10/28.
 */
public class PageHelper {

    public static Log logger = LogFactory.getLog(PageHelper.class);

    /**
     * 构造常用page 对象
     * @param p 起始页
     * @param q 分页数
     * @return Page对象
     */
    public static Page getCommonPage(String p, String q){

        //分页
        Page page = new Page();
        int currentPageIndex = 1;
        int pageSize = PwConstant.PAGINATE_LIMIT_DEFAULT ;

        if(!"".equals(p)){
            try{
                currentPageIndex = Integer.parseInt(p);
            }catch(Exception e){
                logger.error(e);
            }
        }
        if(!"".equals(q)){
            try{
                pageSize = Integer.parseInt(q);
            }catch(Exception e){
                logger.error(e);
            }
        }
        int start = (currentPageIndex - 1) * pageSize;

        page.setStart(start);
        page.setLimit(pageSize);

        return page;
    }
}
