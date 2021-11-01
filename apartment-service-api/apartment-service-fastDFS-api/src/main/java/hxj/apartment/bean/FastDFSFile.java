package hxj.apartment.bean;

/**
 * @author HXJ
 * @create 2021-10-19 21:28
 */
public class FastDFSFile {
    //文件名字
    private String name;
    //文件内容
    private byte[] content;
    //文件扩展名
    private String ext;
    //文件MD5摘要值
    private String md5;
    //文件创建作者
    private String author;

    public FastDFSFile(String name, byte[] content, String ext, String
            height,
                       String width, String author) {
        super();
        this.name = name;
        this.content = content;
        this.ext = ext;
        this.author = author;
    }

    public FastDFSFile() {

    }

    public String getName() {
        return name;
    }

    public byte[] getContent() {
        return content;
    }

    public String getExt() {
        return ext;
    }

    public String getMd5() {
        return md5;
    }

    public String getAuthor() {
        return author;
    }

    public FastDFSFile(String name, byte[] content, String ext) {
        super();
        this.name = name;
        this.content = content;
        this.ext = ext;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
// getter 和 setter方法...
}

