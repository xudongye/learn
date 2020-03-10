package me.own.learn.mall.aythority.vo;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/7 13:50
 */
public class MallMenuNodeVo extends MallMenuVo {

    private List<MallMenuNodeVo> children;

    public List<MallMenuNodeVo> getChildren() {
        return children;
    }

    public void setChildren(List<MallMenuNodeVo> children) {
        this.children = children;
    }
}
