package com.pw.util.io;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;

/**
 * Created by PoemWhite on 2017/9/14.
 */
public class PwInputStreamUtil {

    public static Log logger = LogFactory.getLog(PwInputStreamUtil.class);

    private PwInputStreamUtil(){

    }

    /**
     * 字符串转InputStream
     * @param string
     * @param charsetName
     * @return InputStream
     * @throws UnsupportedEncodingException
     */
    public static InputStream stringToInputStream(String string, String charsetName) throws UnsupportedEncodingException {
        InputStream  inputStream = null;
        inputStream = new ByteArrayInputStream(string.getBytes(charsetName));
        return inputStream;
    }

    /**
     * 从InputStream里面取得byte[]
     * @param inputStream
     * @return byte[]
     * @throws IOException
     */
    public static byte[] getBytes(InputStream inputStream) throws IOException{
        ByteArrayOutputStream byteArrayOutputStream=null;
        BufferedInputStream bufferedInputStream=null;
        try{
            byteArrayOutputStream=new ByteArrayOutputStream();
            bufferedInputStream=new BufferedInputStream(inputStream);
            int b;
            while ((b=bufferedInputStream.read()) >=0) {
                byteArrayOutputStream.write(b);
            }
            byte[] resultBytes=byteArrayOutputStream.toByteArray();
            return resultBytes;
        }finally{
            if(bufferedInputStream!=null){
                try{
                    bufferedInputStream.close();
                }catch(Exception e){
                    logger.error(e);
                }
            }
            if(byteArrayOutputStream!=null){
                try{
                    byteArrayOutputStream.close();
                }catch(Exception e){
                    logger.error(e);
                }
            }
        }

//		ByteArrayOutputStream baos=new ByteArrayOutputStream();
//		BufferedInputStream bufferedInputStream=new BufferedInputStream(inputStream);
//		int b;
//		while((b=bufferedInputStream.read())>=0){
//			baos.write(b);
//		}
//		return baos.toByteArray();
    }

//	/**
//	 * 从InputStream里面取得固定长度的byte[]
//	 * @param inputStream
//	 * @return byte[]
//	 * @throws IOException
//	 */
//	public static byte[] getBytes(InputStream inputStream,int length) throws IOException{
//		ByteArrayOutputStream baos=new ByteArrayOutputStream();
//		int b;
//		int i = 0;
//		while(((b=inputStream.read())>=0)&&(i<length)){
//			baos.write(b);
//			i++;
//		}
//		return baos.toByteArray();
//	}

    /**
     * 方法说明：把InputStream转换成encoding类型的字符串
     * @param inputStream
     * @param encoding
     * @return
     * @throws IOException
     */
    public static String getContentsAsString(InputStream inputStream, String encoding) throws IOException{
        String string="";
        if(encoding!=null) {
            string=new String(getBytes(inputStream), encoding);
        }else {
            string=new String(getBytes(inputStream));
        }
        return string;
    }

//	/**
//	 * 把InputStream中的固定长度的部分转换成encoding类型的字符串
//	 * @param inputStream
//	 * @param encoding
//	 * @return String
//	 */
//    public static String getContentsAsString(InputStream inputStream, int length,String encoding){
//        String string="";
//        try{
//            if(encoding!=null) {
//            	string=new String(getBytes(inputStream,length), encoding);
//            }else {
//            	string=new String(getBytes(inputStream,length));
//            }
//        }catch(Exception e){
//        	e.printStackTrace();
//        }
//    	return string;
//    }

    /**
     * 比较两个InputStream是否完全一样
     * @param inputStream1
     * @param inputStream2
     * @return boolean
     * @throws IOException
     */
    public static boolean compare(InputStream inputStream1, InputStream inputStream2) throws IOException {
        for (int b = 0; b >= 0;){
            if ((b = inputStream1.read()) != inputStream2.read()){
                return false;
            }
        }
        return true;
    }

    /**
     * 关闭InputStream
     *
     * @param inputStream
     */
    public static void closeInputStream(InputStream inputStream){
        try{
            if(inputStream!=null){
                inputStream.close();
            }
        }catch(Exception e){
            logger.error(e);
        }
    }

    /**
     * 取得一个空的InputStream
     *
     */
    public static InputStream getEmptyInputStream(){
        return new ByteArrayInputStream(new byte[0]);
    }

    /**
     * 方法说明：将byte[] 转化为 InputStream
     * @param theBytes
     * @return
     */
    public static InputStream bytesToInputStream(byte[] theBytes){
        return new ByteArrayInputStream(theBytes);
    }
}
