package hxj.apartment.util;

/**
 * @author HXJ
 * @create 2021-10-19 21:26
 */

import hxj.apartment.bean.FastDFSFile;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 实现FastDFS文件管理
 * 上传、删除、下载
 * 文件信息获取
 * Storage信息获取
 * Tracker信息获取
 */
public class fastDFSUtil {

    /**
     * 加载Tracker连接信息
     */
    static {
        //resources下文件fdfs_client.conf的路径
        String path = new ClassPathResource("fdfs_client.conf").getPath();
//        System.out.println(path);
        try {
            ClientGlobal.init(path);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 返回TrackerClient
     * @return
     */
    public static TrackerClient getTrackerClient(){
//        创建Tracker访问客户端对象TrackerClient
        return new TrackerClient();
    }

    public static TrackerServer getTrackerServer(TrackerClient trackerClient) throws IOException {
//        使用TrackerClient访问TrackerServer服务,获取连接信息
        return trackerClient.getConnection();
    }


    /**
     * 上传文件
     */
    public static String[] upload(FastDFSFile fastDFSFile) throws Exception {

        //1.创建Tracker访问客户端对象TrackerClient
//        TrackerClient trackerClient = new TrackerClient();


        //2.使用TrackerClient访问TrackerServer服务,获取连接信息
//        TrackerServer connection = trackerClient.getConnection();

        //封装1.2.步,获取trackerServer
        TrackerServer trackerServer = getTrackerServer(getTrackerClient());

        //3.通过连接信息获取Storage的链接信息,创建StorageClient对象存储的Storage链接信息
        StorageClient storageClient = new StorageClient(trackerServer, null);


        //4.通过Storage链接信息访问Storage,进行文件上传,获取文件上传后的存储信息
        /* 参数：
                file_buff           –文件内容,字节数组/ buff
                file_ext_name       –文件扩展名，不包含点号（.）
                meta_list           –元信息数组,附加信息,NameValuePair数组,NameValuePair是key/value

        NameValuePair[] nameValuePairs = new NameValuePair[1];
        nameValuePairs[0] = new NameValuePair("author", fastDFSFile.getAuthor());
        storageClient.upload_file(fastDFSFile.getContent(), fastDFSFile.getExt(), nameValuePairs);
        */
//        System.out.println(fastDFSFile.getExt());
        String[] uploads = storageClient.upload_file(fastDFSFile.getContent(), fastDFSFile.getExt(), null);
        /*
        String[] uploads
            uploads[0]:文件上传所存储的storage的组名 group1
             uploads[1]:文件存储到storage的文件名  M00/01/32/adwad.jpg
         */
        return uploads;

    }

    /**
     * 获取文件
     * @param groupName         组名 group1
     * @param remoteFileName  文件名 M00/00/00/rBAABGAZTH6AaFmzAAAm31pkj1g381.png
     */
    public static FileInfo getFile(String groupName, String remoteFileName)throws Exception{
//          1.创建TrackerClient
//        TrackerClient trackerClient = new TrackerClient();
//        //2.获取TrackerServer对象
//        TrackerServer connection = trackerClient.getConnection();

        //封装1.2.步,获取trackerServer
        TrackerServer trackerServer = getTrackerServer(getTrackerClient());

        //3.获取storage信息,创建storageClient对象存储Storage信息
        StorageClient storageClient = new StorageClient(trackerServer, null);
        //获取文件信息,并返回
        return storageClient.get_file_info(groupName, remoteFileName);

    }


    /**
     * 文件下载
     * @param groupName         组名 group1
     * @param remoteFileName  文件名 M00/00/00/rBAABGAZTH6AaFmzAAAm31pkj1g381.png
     * @return
     */
    public static InputStream downFile(String groupName, String remoteFileName)throws Exception{
//        //  1.创建TrackerClient
//        TrackerClient trackerClient = new TrackerClient();
//        //2.获取TrackerServer对象
//        TrackerServer connection = trackerClient.getConnection();

        //封装1.2.步,获取trackerServer
        TrackerServer trackerServer = getTrackerServer(getTrackerClient());

        //3.获取storage信息,创建storageClient对象存储Storage信息
        StorageClient storageClient = new StorageClient(trackerServer, null);

        //4.下载文件为byte[]
        byte[] bytes = storageClient.download_file(groupName, remoteFileName);
        //5.封装为InputStream
        return  new ByteArrayInputStream(bytes);

    }

    /**
     * 文件删除
     * @param groupName         组名 group1
     * @param remoteFileName  文件名 M00/00/00/rBAABGAZTH6AaFmzAAAm31pkj1g381.png
     */
    public static void delFile(String groupName, String remoteFileName)throws Exception{
        //  1.创建TrackerClient
//        TrackerClient trackerClient = new TrackerClient();
//        //2.获取TrackerServer对象
//        TrackerServer connection = trackerClient.getConnection();

        //封装1.2.步,获取trackerServer
        TrackerServer trackerServer = getTrackerServer(getTrackerClient());


        //3.获取storage信息,创建storageClient对象存储Storage信息
        StorageClient storageClient = new StorageClient(trackerServer, null);

        //4.删除文件
        int i = storageClient.delete_file(groupName, remoteFileName);
        System.out.println(i);
    }


    /**
     *  获取storage信息

     * @throws Exception
     */
    public static StorageServer getStorages() throws Exception {

        TrackerClient trackerClient = getTrackerClient();
        TrackerServer trackerServer = getTrackerServer(trackerClient);
        //获取storage信息
        return trackerClient.getStoreStorage(trackerServer);
    }


    /**
     * 获取Storage信息,IP和端口
     * @param groupname
     * @param filename
     * @return
     * @throws IOException
     */
    public static ServerInfo[] getServerInfo(String groupname,String filename) throws IOException {

        TrackerClient trackerClient = getTrackerClient();
        TrackerServer trackerServer = getTrackerServer(trackerClient);

        //获取storage的ip和端口信息
        return trackerClient.getFetchStorages(trackerServer, groupname, filename);

    }


    /**
     * 获取Tracker服务地址
     * @throws Exception
     */
    public static String getTrackerUrl() throws Exception{
        TrackerServer trackerServer = getTrackerServer(getTrackerClient());
        return trackerServer.getInetSocketAddress().getHostString() + ":" + ClientGlobal.getG_tracker_http_port() + "/";
    }


    public static void main(String[] args) throws Exception {
        /**
         先要运行application，使用postman上传一个文件至fdfs下的M00/00/00目录下
         然后运行底下的单元测试
         */

        /* 读取文件信息  */
//        FileInfo file = getFile("group1", "M00/00/00/rBAABGAZTH6AaFmzAAAm31pkj1g381.png");
//        System.out.println(file.getSourceIpAddr());
//        System.out.println(file.getFileSize());
//        System.out.println(file.getCreateTimestamp());

        /*下载文件*/
//        InputStream is = downFile("group1", "M00/00/00/rBAABGAZTH6AaFmzAAAm31pkj1g381.png");
//        FileOutputStream outputStream = new FileOutputStream("C:/Users/shen_i/Desktop/1.png");
//        byte[] bytes = new byte[1024];
//        while (is.read(bytes) !=-1)
//        {
//            outputStream.write(bytes);
//        }
//        outputStream.flush();
//        outputStream.close();
//        is.close();

        /*删除文件*/
//        delFile("group1", "M00/00/00/rBAABGAZTH6AaFmzAAAm31pkj1g381.png");

        /*获取storage信息*/
//        StorageServer storages = getStorages();
//        System.out.println(storages.getStorePathIndex());
//
//        System.out.println(storages.getInetSocketAddress().getAddress());
//        System.out.println(storages.getInetSocketAddress().getHostString());

        /*获取storage的ip和端口信息*/
//        ServerInfo[] group1s = getServerInfo("group1", "M00/00/00/rBAABGAbxNOAKxGxAAWlzKpH5DY693.png");
//        for (ServerInfo group1 : group1s) {
//            System.out.println(group1.getPort());
//            System.out.println(group1.getIpAddr());
//        }

        /*获取Tracker服务地址*/
//        System.out.println(getTrackerUrl());
    }
}

