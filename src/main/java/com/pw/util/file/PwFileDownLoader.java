package com.pw.util.file;

import com.pw.util.io.PwInputStreamUtil;
import com.pw.util.string.PwStringUtil;
import com.pw.util.support.constant.PwConstant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by PoemWhite on 2017/9/14.
 * 文件下载
 */
public class PwFileDownLoader {

    public static Log logger = LogFactory.getLog(PwFileDownLoader.class);

    private PwFileDownLoader(){

    }

    public static String getFilename(String fileName){

        if(PwConstant.WINDOWS){
            try {
                fileName=new String(fileName.getBytes(),"iso-8859-1");
            } catch (UnsupportedEncodingException e) {
                logger.error(e);
            }
        }else{
            fileName= PwStringUtil.getUtf8String(fileName);
        }
        return fileName;
    }

    /**
     * 下载文件
     * @param fileName 被下载的文件名
     * @param response
     * @param newFileName 新的文件名
     * @return 1 -- 下载成功 ，-1 -- 下载失败
     */
    public static void downloadFile(String fileName, HttpServletResponse response, String newFileName) {

        fileName = PwStringUtil.getFormatPath(fileName);
        boolean isFile = PwFileHelper.isFile(fileName);
        if(isFile){
            newFileName = PwStringUtil.getString(newFileName,"");
            if("".equals(newFileName)){
                newFileName = PwStringUtil.getLastString(fileName,"/");
            }
            response.reset();
            response.resetBuffer();
            response.setContentType("bin");
            response.addHeader("Content-Disposition","attachment; filename=\"" + getFilename(newFileName) + "\"");
//			循环取出流中的数据
            byte[] b = new byte[1024*10];
            int len;
            InputStream inStream = null;
            OutputStream outputStream = null;
            try {
                inStream = new FileInputStream(fileName);
                while((len=inStream.read(b)) >0) {
                    outputStream=response.getOutputStream();
                    outputStream.write(b,0,len);
                }
            } catch (Exception e) {
                logger.error(e);
            }finally {
                if(inStream!=null) {
                    try {
                        inStream.close();
                    }catch(Exception e1) {
                        logger.error(e1);
                    }
                }
                if(outputStream!=null) {
                    try {
                        outputStream.close();
                    }catch(Exception e1) {
                        logger.error(e1);
                    }
                }
            }
        }
    }

    /**
     * 下载文件
     * @param response
     * @param newfilename 新的文件名
     * @return 1 -- 下载成功 ，-1 -- 下载失败
     */
    public static int downloadFile(byte[] theBytes,HttpServletResponse response,String newfilename){
        int flag=0;
        response.reset();
        response.resetBuffer();
        response.setContentType("bin");
        response.addHeader("Content-Disposition","attachment; filename=\"" + getFilename(newfilename) + "\"");
        // 循环取出流中的数据
        byte[] b = new byte[1024 * 10];
        int len;
        InputStream inStream = null;
        OutputStream outputStream =null;
        try {
            outputStream = response.getOutputStream();
            inStream = PwInputStreamUtil.bytesToInputStream(theBytes);
            while ((len = inStream.read(b)) > 0) {
                outputStream.write(b, 0, len);
            }
            flag = 1;
        } catch (Exception e) {
            logger.error(e);
            flag = -1;
        } finally {
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (Exception e1) {
                    logger.error(e1);
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception e1) {
                    logger.error(e1);
                }
            }
        }
        return flag;
    }

    /**
     * 下载文件
     * @param inStream 被下载的文件名
     * @param response
     * @param newfilename 新的文件名
     * @return 1 -- 下载成功 ，-1 -- 下载失败
     */
    public static int downloadFile(InputStream inStream,HttpServletResponse response,String newfilename){
        int flag=0;
        response.reset();
        response.resetBuffer();
        response.setContentType("bin");
        response.addHeader("Content-Disposition","attachment; filename=\"" + getFilename(newfilename) + "\"");
        // 循环取出流中的数据
        byte[] b = new byte[1024 * 10];
        int len;
        OutputStream outputStream =null;
        try {
            outputStream = response.getOutputStream();
            while ((len = inStream.read(b)) > 0) {
                outputStream.write(b, 0, len);
            }
            flag = 1;
        } catch (Exception e) {
            logger.error(e);
            flag = -1;
        } finally {
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (Exception e1) {
                    logger.error(e1);
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception e1) {
                    logger.error(e1);
                }
            }
        }
        return flag;
    }
}
