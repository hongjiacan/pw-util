package com.pw.util.file;

import com.pw.util.string.PwStringUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by PoemWhite on 2017/9/14.
 */
public class PwFileHelper {

    private PwFileHelper() {

    }

    /**
     * 取得当前路径
     *
     * @return String
     * @throws IOException
     */

    public static String getCurrentPath() throws IOException {
        File directory = new File(".");
        String nowPath = "";
        nowPath = directory.getCanonicalFile().toString();
        return nowPath;
    }

    /**
     * 创建文件
     *
     * @param filename
     *            文件名称
     * @return boolean
     * @throws IOException
     */
    public static boolean createFile(String filename) throws IOException {
        filename = PwStringUtil.getFormatPath(filename);
        if (isExists(filename)) {
            return true;
        }
        String newfilepath = PwStringUtil.getPreString(filename, "/");
        if (!isExists(newfilepath)) {
            File directory = new File(newfilepath);
            directory.mkdirs();
        }
        File newFile = new File(filename);
        return newFile.createNewFile();
    }

    /**
     * 创建文件夹
     *
     * @param dirName
     *            文件夹名称
     *
     * @return boolean
     */
    public static boolean createDir(String dirName) {
        dirName = PwStringUtil.getFormatPath(dirName);
        boolean dirflag = isDir(dirName);
        if (!dirflag) {
            File dir = new File(dirName);
            return dir.mkdirs();
        } else {
            return false;
        }
    }

    /**
     * 文件是否存在
     *
     * @param filename1
     *            文件名称
     * @return boolean
     */

    public static boolean isExists(String filename1) {
        filename1 = PwStringUtil.getFormatpathNoFileStart(filename1);
        if (filename1.equals("")) {
            return false;
        }
        File file = new File(filename1);
        return file.exists();
    }

    /**
     * 文件是否存在
     *
     * @param filename1
     *            文件名称
     * @return boolean
     */
    public static boolean isFile(String filename1) {
        filename1 = PwStringUtil.getFormatpathNoFileStart(filename1);
        if (filename1.equals("")) {
            return false;
        }
        File file = new File(filename1);
        if (isExists(filename1)) {
            return file.isFile();
        } else {
            return (false);
        }
    }

    /**
     * 文件夹是否存在
     *
     * @param filename1
     *            文件夹名称
     * @return boolean
     */

    public static boolean isDir(String filename1) {
        filename1 = PwStringUtil.getFormatpathNoFileStart(filename1);
        if (filename1.equals("")) {
            return false;
        }
        File file = new File(filename1);
        if (isExists(filename1)) {
            return file.isDirectory();
        } else {
            return false;
        }
    }

    /**
     * 获取配置文件最后修改时间
     *
     * @param fileName
     * @return long
     */
    public static long getLastModifyTime(String fileName) {
        if (!isFile(fileName)) {
            return -1;
        }
        fileName = PwStringUtil.getFormatPath(fileName);
        File file = new File(fileName);
        return file.lastModified();
    }

    /**
     * 取得文件大小
     *
     * @param fileName
     * @return long if(return==0) 文件不存在
     * @throws IOException
     */
    public static long getFileSize(String fileName) throws IOException {
        if (!isFile(fileName)) {
            return 0;
        }
        fileName = PwStringUtil.getFormatPath(fileName);
        long filesize = 0;
        FileInputStream in = null;
        try {
            if (isFile(fileName)) {
                File checkfile = new File(fileName);
                in = new FileInputStream(checkfile);
                filesize = in.available();
            }
        } finally {
            in.close();
        }
        return filesize;
    }

    /**
     * 取得文件大小
     *
     * @param fileName
     * @return long if(return==0) 文件不存在
     * @throws IOException
     */
    public static long getFileSize(File fileName) throws IOException {
        if (!fileName.isFile()) {
            return 0;
        }
        long filesize = 0;
        FileInputStream in = null;
        try {
            if (fileName.isFile()) {
                in = new FileInputStream(fileName);
                filesize = in.available();
            }
        } finally {
            in.close();
        }
        return filesize;
    }

    /**
     * 文件改名
     *
     * @param fileName
     * @param toFileName
     * @return boolean
     */
    public static boolean renameFile(String fileName, String toFileName) {
        if (!isFile(fileName)) {
            return false;
        }
        fileName = PwStringUtil.getFormatPath(fileName);
        toFileName = PwStringUtil.getFormatPath(toFileName);
        File file = new File(fileName);
        File fileto = new File(toFileName);
        return file.renameTo(fileto);
    }

    /**
     * 文件改名
     *
     * @param fileName
     * @param toFileName
     * @return boolean
     */
    public static boolean renameFile(File fileName, File toFileName) {
        if (!fileName.isFile()) {
            return false;
        }
        return fileName.renameTo(toFileName);
    }

    /**
     * 复制文件fromfilename到文件tofilename
     *
     * @param fromfilename
     * @param tofilename
     * @throws IOException
     */
    public static void copyFile(String fromfilename, String tofilename)
            throws IOException {
        fromfilename = PwStringUtil.getFormatPath(fromfilename);
        tofilename = PwStringUtil.getFormatPath(tofilename);
        boolean fileflag = false;
        BufferedOutputStream fout = null;
        try {
            File file = new File(fromfilename); // 判断被复复制文件是否存在
            fileflag = file.isFile(); // 如果被复复制文件不存在则返回
            if (!fileflag) {
                throw new IOException(fromfilename + " inexistence !");
            } else if (fileflag) { // 如果被复复制文件存在
                String prestring = "";
                prestring = PwStringUtil.getPreString(tofilename, "/");
                File filedir = new File(prestring);
                boolean fsflag = filedir.exists(); // 判断新文件路径是否存在，如果不存在则创建
                if (!fsflag) {
                    filedir.mkdirs();
                }
                // 复制fromfilename到tofilename
                fout = new BufferedOutputStream(
                        new FileOutputStream(tofilename));
                append(fout, new File(fromfilename));
            }
        } finally {
            if (fout != null) {
                fout.close();
            }
        }
    }

    /**
     * 移动文件夹
     *
     * @param frompath
     * @param topath
     * @throws IOException
     */
    public static void moveFolder(String frompath, String topath)
            throws IOException {
        frompath = PwStringUtil.getFormatPath(frompath);
        topath = PwStringUtil.getFormatPath(topath);

        if (!isDir(frompath)) {
            return;
        }
        copyFolder(frompath, topath);
        delFolder(frompath);
    }

    /**
     * 剪切文件fromfilename到文件tofilename
     *
     * @param fromfilename
     * @param tofilename
     * @throws IOException
     */
    public static void moveFile(String fromfilename, String tofilename)
            throws IOException {
        fromfilename = PwStringUtil.getFormatPath(fromfilename);
        tofilename = PwStringUtil.getFormatPath(tofilename);
        copyFile(fromfilename, tofilename);
        deleteFile(fromfilename);
    }

    /**
     * 删除文件
     *
     * @param filename
     * @return boolean
     */

    public static boolean deleteFile(String filename) {
        filename = PwStringUtil.getFormatPath(filename);
        File file = new File(filename);
        boolean fileflag = file.isFile();
        if (!fileflag) {
            return false;
        } else {
            file.delete();
            return true;
        }
    }

    /**
     * 删除文件夹
     *
     * @param folderPath
     *            String 文件夹路径及名称 如c:/fqf
     * @return boolean
     */

    public static boolean delFolder(String folderPath) {
        folderPath = PwStringUtil.getFormatPath(folderPath);
        boolean flag = true;
        try {
            delAllFile(folderPath); // 删除完里面所有内容
            File filePath = new File(folderPath);
            filePath.delete(); // 删除空文件夹
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 删除文件夹里面的所有文件
     *
     * @param path
     *            String 文件夹路径 如 c:/fqf
     * @return boolean
     */

    public static boolean delAllFile(String path) {
        path = PwStringUtil.getFormatPath(path);
        File file = new File(path);
        boolean flag = true;
        if (!isDir(path)) {
            return false;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);// 再删除空文件夹
            }
        }
        return flag;
    }

    /**
     * 复制整个文件夹内容
     *
     * @param oldPath
     *            String 原文件路径 如：c:/fqf
     * @param newPath
     *            String 复制后路径 如：f:/fqf/ff
     * @throws IOException
     */
    public static void copyFolder(String oldPath, String newPath)
            throws IOException {
        oldPath = PwStringUtil.getFormatPath(oldPath);
        if (!isDir(oldPath)) {
            return;
        }
        newPath = PwStringUtil.getFormatPath(newPath);
        createDir(newPath);

        File fileList = new File(oldPath);
        String[] file = fileList.list();
        String tempFileName = null;
        for (int i = 0; i < file.length; i++) {
            tempFileName = PwStringUtil.getFormatPath(oldPath + "/" + file[i]);
            File tempFile = new File(tempFileName);
            if (tempFile.isFile()) {
                copyFile(tempFileName, newPath + "/" + tempFile.getName());
            }
            if (tempFile.isDirectory()) {// 如果是子文件夹
                copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
            }
        }
    }

    /**
     * 将文件内容读到字符串中
     *
     * @param filename
     *            文件名
     * @return String
     * @throws IOException
     */
    public static String fileToString(String filename) throws IOException {
        return fileToString(filename, null);
    }

    /**
     * 将文件内容读到字符串中
     *
     * @param filename
     *            文件名
     * @param charsetName
     *            字符集
     * @return String
     * @throws IOException
     */
    public static String fileToString(String filename, String charsetName)
            throws IOException {
        filename = PwStringUtil.getFormatPath(filename);
        if (!isFile(filename)) {
            return "";
        }
        StringWriter wirter = new StringWriter();
        append(wirter, new File(filename), charsetName);
        return wirter.toString();
    }

    /**
     * 从BufferedReader中读取固定的行数到字符串中
     *
     * @param bf
     *            BufferedReader
     * @param fromline
     *            从第几行开始
     * @param toline
     *            到第几行结束
     * @return String
     */
    public static String getLineToString(BufferedReader bf, int fromline,
                                         int toline) {
        StringBuffer linetostring = null;
        if (bf != null) {
            int count = 0;
            linetostring = new StringBuffer();
            String temp = "";
            try {
                while (((temp = bf.readLine()) != null) && (count <= toline)) {
                    if (count >= fromline) {
                        linetostring.append(temp);
                        linetostring.append("\n");
                    }
                    count++;
                }
            } catch (Exception e) {

            }
            return linetostring.toString();
        } else {
            return "";
        }
    }

    /**
     * 将filelist中的所有文件的内容按顺序写入newfile中
     *
     * @param filelist
     * @param tofilename
     * @return boolean
     * @throws IOException
     */
    public static void mergeFiles(List filelist, String tofilename)
            throws IOException {
        tofilename = PwStringUtil.getFormatPath(tofilename);
        FileOutputStream out = new FileOutputStream(tofilename);
        try {
            createFile(tofilename);
            Iterator it = filelist.iterator(); // 将所有新文件的内容都读入字符串中，如果其中的文件不存在则跳过该文件
            while (it.hasNext()) {
                String filename = (String) it.next();
                filename = PwStringUtil.getFormatPath(filename);
                append(out, new File(filename));
            }
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 将filelist中的所有字符串按顺序写入newfile中
     *
     * @param content
     * @param tofilename
     * @throws IOException
     */
    public static void stringToFile(String content, String tofilename)
            throws IOException {
        tofilename = PwStringUtil.getFormatPath(tofilename);
        FileOutputStream out = new FileOutputStream(tofilename);
        createFile(tofilename);
        append(out, content);
    }
    /**
     * 将字符串内容写入到文件中
     * @param content 字符串内容
     * @param tofilename 文件名称
     * @param append 是否合并
     * @throws IOException
     */
    public static void stringToFile(String content, String tofilename,boolean append)
            throws IOException {
        tofilename = PwStringUtil.getFormatPath(tofilename);
        PrintWriter out = null;
        try {
            createFile(tofilename);
            out = new PrintWriter(new BufferedWriter(new FileWriter(tofilename,
                    append)));
            out.print(content);
            out.close();
        } finally {
            if (out != null) {
                out.close();
            }
        }

    }

    /**
     * 把InputStream读到文件中
     *
     * @param inputStream
     * @param destFile
     * @return boolean
     * @throws IOException
     */
    public static void inputStreamToFile(InputStream inputStream,
                                         String destFile) throws IOException {
        destFile = PwStringUtil.getFormatPath(destFile);
        if (!isFile(destFile)) {
            createFile(destFile);
        }

        BufferedOutputStream bufferedOutputStream = null;
        try {
            bufferedOutputStream = new BufferedOutputStream(
                    new FileOutputStream(destFile));
            append(bufferedOutputStream, inputStream);
        } finally {
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    /**
     * 把文件读到DataInputStream中
     *
     * @param filename
     * @return DataInputStream
     */
    public static DataInputStream fileToDataInputStream(String filename) {
        filename = PwStringUtil.getFormatPath(filename);
        if (!isFile(filename)) {
            return null;
        } else {
            try {
                DataInputStream input = new DataInputStream(
                        new BufferedInputStream(new FileInputStream(filename)));
                return input;
            } catch (Exception e) {

            }
        }
        return null;
    }

    /**
     * 返回路径path下所有文件列表,包括子文件夹
     *
     * @param path
     *            文件路径
     * @return ArrayList
     */
    public static List getAllFileList(String path) {
        path = PwStringUtil.getFormatPath(path);
        path = path + "/";
        File filePath = new File(path);
        if (!isDir(path)) {
            return null;
        }
        String[] filelist = filePath.list();
        List filelistFilter = new ArrayList();

        for (int i = 0; i < filelist.length; i++) {
            String tempfilename = PwStringUtil.getFormatPath(path + filelist[i]);
            File filterFile = new File(tempfilename);
            if (filterFile.isFile()) {
                filelistFilter.add(tempfilename);
            } else if (filterFile.isDirectory()) {
                filelistFilter.addAll(getAllFileList(tempfilename));
            }
        }

        return filelistFilter;
    }

    /**
     * 获取文件夹下的所以文件列表
     *
     * @param path
     * @return String[]
     */
    public static String[] getList(String path) {
        path = PwStringUtil.getFormatPath(path);
        if (!isDir(path)) {
            return null;
        }
        File file = new File(path);
        String[] list = file.list();
        return list;
    }

    /**
     * 返回路径path下所有文件列表,不包括子文件夹
     *
     * @param path
     *            文件路径
     * @return ArrayList
     */
    public static List getFileList(String path) {
        path = PwStringUtil.getFormatPath(path);
        path = path + "/";
        File filePath = new File(path);
        if (!isDir(path)) {
            return null;
        }
        String[] filelist = filePath.list();
        List filelistFilter = new ArrayList();

        for (int i = 0; i < filelist.length; i++) {
            String tempfilename = PwStringUtil.getFormatPath(path + filelist[i]);
            File filterFile = new File(tempfilename);
            if (filterFile.isFile()) {
                filelistFilter.add(tempfilename);
            }
        }

        return filelistFilter;
    }

    /**
     * 返回路径path下所有文件夹列表,不包括子文件夹
     *
     * @param path
     *            文件路径
     * @return ArrayList
     */
    public static List getDirList(String path) {
        List dirList = new ArrayList();

        path = PwStringUtil.getFormatPath(path);
        path = path + "/";
        File filePath = new File(path);
        if (!isDir(path)) {
            return null;
        }
        String[] filelist = filePath.list();

        for (int i = 0; i < filelist.length; i++) {
            String tempfilename = PwStringUtil.getFormatPath(path + filelist[i]);
            File filterFile = new File(tempfilename);
            if (filterFile.isDirectory()) {
                dirList.add(tempfilename);
            }
        }

        return dirList;
    }

    /**
     * 返回路径path下所有文件夹列表,包括子文件夹
     *
     * @param path
     *            文件路径
     * @return ArrayList
     */
    public static List getAllDirList(String path) {
        List dirList = new ArrayList();

        path = PwStringUtil.getFormatPath(path);
        path = path + "/";
        File filePath = new File(path);
        if (!isDir(path)) {
            return null;
        }
        String[] filelist = filePath.list();

        for (int i = 0; i < filelist.length; i++) {
            String tempfilename = PwStringUtil.getFormatPath(path + filelist[i]);
            File filterFile = new File(tempfilename);
            if (filterFile.isDirectory()) {
                dirList.add(tempfilename);
            } else if (filterFile.isDirectory()) {
                dirList.addAll(getAllDirList(tempfilename));
            }
        }

        return dirList;
    }

    /**
     * 获取文件后缀名
     *
     * @param filename
     * @return
     */
    public static String getFileType(String filename) {
        String fileType = "";
        filename = PwStringUtil.getFormatPath(filename);
        if (filename.indexOf(".") < 0) {
            return "";
        } else {
            fileType = filename.substring(filename.lastIndexOf(".") + 1,
                    filename.length());
        }

        return fileType;
    }

    /**
     * 获取文件后缀名
     *
     * @param file
     * @return
     */
    public static String getFileType(File file) {
        String fileType = "";
        fileType = getFileType(file.getAbsolutePath());
        return fileType;
    }

    /**
     * 将byte[]转为文件
     *
     * @param theBytes
     * @param tofilename
     * @throws IOException
     */
    public static void byteToFile(byte[] theBytes, String tofilename)
            throws IOException {

        InputStream in = new ByteArrayInputStream(theBytes);
        try {
            DataInputStream dataInputStream = new DataInputStream(in);
            inputStreamToFile(dataInputStream, tofilename);
        } finally {
            in.close();
        }

    }

    /**
     * 保存对象到文件中
     *
     * @param object
     *            对象
     * @param filename
     *            文件名
     * @return boolean
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static boolean saveObject(Object object, String filename)
            throws FileNotFoundException, IOException {
        filename = PwStringUtil.getFormatPath(filename);
        if (!isFile(filename)) {
            createFile(filename);
        }
        boolean flag = true;
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(
                    filename));
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
        } finally {
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
        }
        return flag;
    }

    /**
     * 把对象从文件中读取出来
     *
     * @param filename
     * @return Object
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     */
    public static Object readObject(String filename)
            throws FileNotFoundException, IOException, ClassNotFoundException {
        Object object = null;
        filename = PwStringUtil.getFormatPath(filename);
        if (!isFile(filename)) {
            return null;
        }
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(
                    filename));
            object = objectInputStream.readObject();
            objectInputStream.close();
        } finally {
            if (objectInputStream != null) {
                objectInputStream.close();
            }
        }
        return object;
    }

    /**
     * 把文件读到OutputStream中
     *
     * @param filename
     * @return OutputStream
     * @throws FileNotFoundException
     */
    public static OutputStream fileToOutputStream(String filename)
            throws FileNotFoundException {
        filename = PwStringUtil.getFormatPath(filename);
        FileOutputStream output = new FileOutputStream(filename);
        return output;
    }

    /**
     * 把文件读到InputStream中
     *
     * @param filename
     * @return InputStream
     * @throws FileNotFoundException
     */
    public static InputStream fileToInputStream(String filename)
            throws FileNotFoundException {
        filename = PwStringUtil.getFormatPath(filename);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(
                new FileInputStream(filename));
        return bufferedInputStream;
    }

    /**
     * 把BufferedReader写入文件
     *
     * @param bf
     * @throws IOException
     */
    public static boolean bufferedReaderToFile(BufferedReader bf,
                                               String tofilename) throws IOException {
        tofilename = PwStringUtil.getFormatPath(tofilename);
        if (!isFile(tofilename)) {
            createFile(tofilename);
        }
        boolean flag = true;
        PrintWriter out = null;
        String temp = null;
        try {
            out = new PrintWriter(
                    new BufferedWriter(new FileWriter(tofilename)));
            while ((temp = bf.readLine()) != null) {
                out.println(temp + "\n");
            }
        } finally {
            if (bf != null) {
                bf.close();
            }
            if (out != null) {
                out.close();
            }
        }
        return flag;
    }

    /**
     * 把DataInputStream写入文件
     *
     * @param datainputstream
     * @param tofilename
     * @return boolean
     * @throws IOException
     */
    public static boolean dataInputStreamToFile(
            DataInputStream datainputstream, String tofilename)
            throws IOException {
        tofilename = PwStringUtil.getFormatPath(tofilename);
        if (!isFile(tofilename)) {
            createFile(tofilename);
        }
        boolean flag = true;
        DataOutputStream dataoutputstream = null;
        try {
            dataoutputstream = new DataOutputStream(new BufferedOutputStream(
                    new FileOutputStream(tofilename)));
            byte[] b = new byte[1024 * 10];
            int len = 0;
            while ((len = datainputstream.read(b, 0, 1024)) != -1) {
                dataoutputstream.write(b, 0, len);
            }
            dataoutputstream.flush();
        } finally {
            if (datainputstream != null) {
                datainputstream.close();
            }
            if (dataoutputstream != null) {
                dataoutputstream.close();
            }
        }
        return flag;
    }

    public static void append(OutputStream os, File originalFile)
            throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
                originalFile));
        try {
            append(os, bis);
            os.flush();
        } finally {
            if (bis != null) {
                bis.close();
            }
        }
    }

    public static void append(OutputStream os, InputStream fileInputStream)
            throws IOException {
        byte[] buffer = new byte[1024 * 5];
        int bytesum = 0;
        int byteread = 0;
        while ((byteread = fileInputStream.read(buffer)) != -1) {
            bytesum += byteread;
            os.write(buffer, 0, byteread);
        }
        os.flush();
    }

    public static void append(OutputStream os, String content)
            throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(content.getBytes());
        try {
            byte[] buffer = new byte[1024 * 5];
            int bytesum = 0;
            int byteread = 0;
            while ((byteread = bis.read(buffer)) != -1) {
                bytesum += byteread;
                os.write(buffer, 0, byteread);
            }
            os.flush();
        } finally {
            if (bis != null) {
                bis.close();
            }
        }
    }

    public static void append(Writer writer, File file, String charsetName)
            throws IOException {
        InputStream inputStream = new FileInputStream(file);
        try {
            append(writer, inputStream, charsetName);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    public static void append(Writer writer, InputStream fileInputStream,
                              String charsetName) throws IOException {
        Reader reader = null;
        try {
            if (charsetName == null) {
                reader = new InputStreamReader(fileInputStream);
            } else {
                reader = new InputStreamReader(fileInputStream, charsetName);
            }
            int c;
            while ((c = reader.read()) > -1) {
                writer.append((char) c);
            }
            writer.flush();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    public static void main(String[] args) {
        // try {
        // copyFolder("E:/Download/1", "E:/Download/2");
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // try{
        // System.out.println(fileToString("E:/Download/1/a.txt"));
        // }catch(Exception e){
        // e.printStackTrace();
        // }

        // try{
        // List list=new ArrayList();
        // list.add("E:/Download/1/a.txt");
        // list.add("E:/Download/1/b.txt");
        // list.add("E:/Download/1/c.txt");
        // list.add("E:/Download/1/d.txt");
        // mergeFiles(list,"E:/Download/1/e.txt");
        // }catch(Exception e){
        // e.printStackTrace();
        // }

        try {
            stringToFile("敬爱洛杉矶飞离开三大减肥的睡觉奥富家大室安抚撒旦法were温柔12321321323",
                    "e:/download/1/f.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
