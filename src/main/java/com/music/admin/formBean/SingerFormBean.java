package com.music.admin.formBean;

/**
 * Created by donald.tang on 2019/7/30.
 */
public class SingerFormBean {

    private Integer id;
    private String singerName;
    private String singerImgPath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getSingerImgPath() {
        return singerImgPath;
    }

    public void setSingerImgPath(String singerImgPath) {
        this.singerImgPath = singerImgPath;
    }
}
